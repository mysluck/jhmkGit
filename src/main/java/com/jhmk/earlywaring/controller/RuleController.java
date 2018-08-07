package com.jhmk.earlywaring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.entity.OriRule;
import com.jhmk.earlywaring.entity.SmHospitalLog;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.repository.service.OriRuleRepService;
import com.jhmk.earlywaring.entity.repository.service.SmHospitalLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmShowLogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.entity.rule.AnalyzeBean;
import com.jhmk.earlywaring.entity.rule.Rule;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.HosptailLogService;
import com.jhmk.earlywaring.service.RuleService;
import com.jhmk.earlywaring.service.UserModelService;
import com.jhmk.earlywaring.util.DateFormatUtil;
import com.jhmk.earlywaring.webservice.AnalysisXmlService;
import com.jhmk.earlywaring.webservice.CdrService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.*;

/**
 * 规则管理
 */
@Controller
@RequestMapping("/warn/rule")
public class RuleController extends BaseEntityController<Object> {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RuleService ruleService;
    @Autowired
    SmShowLogRepService smShowLogRepService;
    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    HosptailLogService hosptailLogService;

    @Autowired
    UserModelService userModelService;
    @Autowired
    UrlConfig urlConfig;
    @Autowired
    OriRuleRepService oriRuleRepService;
    @Autowired
    CdrService cdrService;
    @Autowired
    AnalysisXmlService analysisXmlService;
    private static final Logger logger = LoggerFactory.getLogger(RuleController.class);


    /**
     * 获取变量列表
     *
     * @param response
     */
    @GetMapping("/getVariableList")
    @ResponseBody
    public void getVariableList(HttpServletResponse response) {
        String forObject = restTemplate.getForObject(urlConfig.getCdssurl() + BaseConstants.getVariableList, String.class);
        wirte(response, forObject);
    }

    /**
     * 添加规则
     *
     * @param response
     */
    @PostMapping("/addrule")
    @ResponseBody
    public void addrule(HttpServletResponse response, @RequestBody String map) {
        //获取原始规则条件
        Map<String, Object> param = (Map) JSONObject.parse(map);
        Object condition = param.get("ruleCondition");
        //转换条件格式
//        String ruleCondition = userModelService.analyzeOldRule(condition);
        String ruleCondition = userModelService.getOldRule(condition);
        param.put("ruleCondition", ruleCondition);

        String userId = getUserId();
        param.put("doctorId", userId.trim());
        String time = DateFormatUtil.format(new Timestamp(System.currentTimeMillis()), DateFormatUtil.DATETIME_PATTERN_SS);
        param.put("createTime", time);
        Object o = JSON.toJSON(param);
        String forObject = "";
        try {
            forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.addrule, o, String.class);
            //mongo库中_id
            String id = ruleService.getId(forObject);
            OriRule rule = new OriRule();
            JSONObject jsonObject = JSONObject.parseObject(map);
            String oriRule = JSONObject.toJSONString(jsonObject);
            rule.setRule(oriRule);
            rule.setMid(id);
            OriRule save = oriRuleRepService.save(rule);
            if (save == null) {
                logger.info("添加本地原始规则失败：" + oriRule);
            }
        } catch (Exception e) {
            logger.info("添加规则失败：" + e.getMessage());
        } finally {
            wirte(response, forObject);
        }
    }


    @PostMapping("/view")
    @ResponseBody
    public void view(HttpServletResponse response, @RequestBody String map) {
        JSONObject jsonObject = JSONObject.parseObject(map);
        String id = jsonObject.getString("_id");
        OriRule one = oriRuleRepService.findOne(id);
        String rule = one.getRule();
        wirte(response, rule);

    }


    /**
     * 6.查询所有未提交的规则信息
     *
     * @param response
     */
    @PostMapping("/findallnotsubmitrule")
    @ResponseBody
    public void findallnotsubmitrule(HttpServletResponse response, @RequestBody(required = false) Map map) {

        String userId = getUserId();
        Map<String, String> params = new HashMap<>();
        params.put("doctorId", userId);
        if (map != null) {
            params.putAll(map);
        }
        Object o = JSONObject.toJSON(params);
        String forObject = "";
        Map<String, Object> foramtData = null;
        try {
//            System.out.println(BaseConstants.findallnotsubmitrule);
            forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.findallnotsubmitrule, o, String.class);
            foramtData = ruleService.formatData(forObject);
        } catch (Exception e) {
            logger.info("查询所有未提交的规则信息失败：" + o);
        } finally {
            wirte(response, foramtData);
        }
    }

    /**
     * 6. 查询所有提交的规则信息
     *
     * @param response
     */
    @PostMapping("/findallsubmitrule")
    @ResponseBody
    public void findallsubmitrule(HttpServletResponse response) {
        String userId = getUserId();
        Map<String, String> params = new HashMap<>();
        params.put("doctorId", userId);
        Object o = JSONObject.toJSON(params);
        String forObject = "";
        Map<String, Object> foramtData = null;
        try {
            forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.findallsubmitrule, o, String.class);
            foramtData = ruleService.formatData(forObject);
        } catch (Exception e) {
            logger.info("查询所有以提交的规则信息失败：" + o);
        } finally {

            wirte(response, foramtData);
        }
    }

    /**
     * 根据条件查询
     *
     * @param response
     * @param map
     */
    @PostMapping("/findrulebycondition")
    @ResponseBody
    public void findRuleByCondition(HttpServletResponse response, @RequestBody(required = false) Map map) {

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> search = new HashMap<>();
        String currentRoleRange = getCurrentRoleRange();
        Map<String, Object> foramtData = new HashedMap();
        if (!"2".equals(currentRoleRange)) {
            String userId = getUserId();
            search.put("doctorId", userId);
            //前端控制修改权限标志
            foramtData.put("checkRule", "false");
        } else {
            //前台权限控制按钮 2表示可以查看所有并且修改 否则只能查看自己添加的规则
            foramtData.put("checkRule", "true");
        }
        //预警等级
        if (map.get("warninglevel") != null) {
            search.put("warninglevel", map.get("warninglevel"));
        }
        //是否运行
        if (map.get("is_run") != null) {
            search.put("isRun", map.get("is_run"));
        }
        //审核
        if (map.get("examine") != null) {
            search.put("examine", map.get("examine"));
        }
        //专科标志
        if (map.get("identification") != null) {
            search.put("identification", map.get("identification"));
        }
        //规则出处
        if (map.get("ruleSource") != null) {
            search.put("ruleSource", map.get("ruleSource"));
        }
        //规则分类
        if (map.get("classification") != null) {
            search.put("classification", map.get("classification"));
        }
        //规则模糊查询
        if (map.get("ruleCondition") != null) {
            search.put("ruleCondition", map.get("ruleCondition"));
        }

        params.put("search", search);
        if (map.get("pageSize") != null) {
            params.put("pageSize", map.get("pageSize"));
        }
        if (map.get("page") != null) {
            params.put("page", map.get("page"));
        }
        if (map.get("endDate") != null) {
            params.put("endDate", map.get("endDate"));
        }
        if (map.get("startDate") != null) {
            params.put("startDate", map.get("startDate"));
        }
        String o = JSONObject.toJSONString(params);
        String forObject = "";
        try {
            forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.findrulebycondition, params, String.class);
            Map<String, Object> mapData = ruleService.formatData(forObject);
            foramtData.putAll(mapData);
        } catch (Exception e) {
            logger.info("按条件查询规则信息失败：" + e.getMessage());
        } finally {

            wirte(response, foramtData);
        }

    }

    /**
     * 6. 提交规则
     *
     * @param response
     */
    @PostMapping("/submitrule")
    @ResponseBody
    public void submitrule(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.submitrule, parse, String.class);
        wirte(response, forObject);
    }


    /**
     * 改变预警等级
     *
     * @param response
     */
    @PostMapping("/changewarninglevel")
    @ResponseBody
    public void changewarninglevel(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.changewarninglevel, parse, String.class);
        wirte(response, forObject);
    }

    /**
     * 审核规则
     *
     * @param response
     */
    @PostMapping("/examinerule")
    @ResponseBody
    public void examinerule(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.examinerule, parse, String.class);
        wirte(response, forObject);
    }


    /**
     * 是否运行规则
     *
     * @param response
     */
    @PostMapping("/isrunruler")
    @ResponseBody
    public void isrunruler(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.isrunruler, parse, String.class);
        wirte(response, forObject);
    }

    /**
     * 专科标识
     *
     * @param response
     */
    @PostMapping("/changeIdentification")
    @ResponseBody
    public void changeIdentification(HttpServletResponse response, @RequestBody String map) {
        Object parse = JSONObject.parse(map);
        String forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.changeIdentification, parse, String.class);
        wirte(response, forObject);
    }




    /**
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @PostMapping("/ruleMatch")
    @ResponseBody
    public void ruleMatch(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        Map<String, String> paramMap = (Map) JSON.parse(map);
        //解析规则 一诉五史 检验报告等
        String s = ruleService.anaRule(paramMap);
        String s2 = ruleService.stringTransform(s);
        JSONObject parse = JSONObject.parseObject(s2);
        Rule rule = Rule.fill(parse);
        String data = "";
        try {
            //规则匹配
            data = ruleService.ruleMatchGetResp(rule);
            wirte(response, data);
        } catch (Exception e) {
            logger.info("规则匹配失败:{}" , e.getMessage());
        }
        if (StringUtils.isNotBlank(data)) {
            ruleService.add2LogTable(data, rule);
            ruleService.add2ShowLog(rule, data);
        }
        ruleService.getTipList2ShowLog(rule, map);
        //一诉五史信息入库
        ruleService.saveRule2Database(rule);

    }


    /**
     * 获取医生触发规则id
     *
     * @param response
     * @param map
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/getShowLog")
    @ResponseBody
    public void getShowRuleLog(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        AtResponse resp = new AtResponse();
        JSONObject jsonObject = JSONObject.parseObject(map);
        String doctor_id = jsonObject.getString("doctor_id");
        String patient_id = jsonObject.getString("patient_id");
        String visit_id = jsonObject.getString("visit_id");
        List<SmShowLog> byDoctorIdAndPatientId = smShowLogRepService.findByDoctorIdAndPatientIdAndVisitIdOrderByDateDesc(doctor_id, patient_id, visit_id);
        resp.setData(byDoctorIdAndPatientId);
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);
    }


    @PostMapping("/updateShowLog")
    @ResponseBody
    public void updateShowLog(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        AtResponse resp = new AtResponse();
        JSONObject jsonObject = JSONObject.parseObject(map);
        Integer id = jsonObject.getInteger("id");
        Integer ruleStatus = jsonObject.getInteger("ruleStatus");
        try {

            smShowLogRepService.update(ruleStatus, id);
        } catch (Exception e) {
            logger.info("更新失败：" + e.getMessage());
        }
        resp.setResponseCode(ResponseCode.OK);
        wirte(response, resp);

    }


    /**
     * 根据id获取原始 规则
     *
     * @param response
     * @param map
     */
    @PostMapping("/getRuleById")
    @ResponseBody
    public void getRuleById(HttpServletResponse response, @RequestBody String map) {

        //todo 根据id获取原始规则
        Object o = JSONObject.parse(map);
        List<AnalyzeBean> restList = null;
        String data = "";
        try {

            data = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.getruleforid, o, String.class);
            Map<String, String> parse = (Map) JSONObject.parse(data);
            String ruleCondition = parse.get("ruleCondition");
            String s = ruleService.disposeRuleCondition(ruleCondition);
            restList = ruleService.restoreRule(data);
        } catch (Exception e) {

        } finally {
            wirte(response, restList);
        }


    }


    @PostMapping("/deleterule")
    @ResponseBody
    public void deleterule(HttpServletResponse response, @RequestBody String map) {
        Map parse = (Map) JSONObject.parse(map);
        String result = null;
        try {
            result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.deleterule, parse, String.class);
//            result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.deleterule, parse, String.class);
        } catch (Exception e) {
            logger.info("删除规则失败：" + map);
        } finally {
            wirte(response, result);
        }

    }

    /**
     * 修改规则
     *
     * @param response
     * @param map
     */
    @PostMapping("/updaterule")
    @ResponseBody
    public void updaterule(HttpServletResponse response, @RequestBody String map) {

        //获取原始规则条件
        Map<String, Object> param = (Map) JSONObject.parse(map);
        Object condition = param.get("ruleCondition");
        //转换条件格式
        String ruleCondition = userModelService.getOldRule(condition);
        param.put("ruleCondition", ruleCondition);

        String userId = getUserId();
        param.put("doctorId", userId.trim());
        String time = DateFormatUtil.format(new Timestamp(System.currentTimeMillis()), DateFormatUtil.DATETIME_PATTERN_SS);
        param.put("createTime", time);
        Object o = JSON.toJSON(param);
        String forObject = "";
        try {
            forObject = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.updaterule, o, String.class);
            //mongo库中_id
            String id = ruleService.getId(forObject);
            OriRule rule = oriRuleRepService.findOne(id);
            JSONObject jsonObject = JSONObject.parseObject(map);
            String oriRule = JSONObject.toJSONString(jsonObject);
            rule.setRule(oriRule);
            OriRule save = oriRuleRepService.save(rule);
            if (save == null) {
                logger.info("更新本地原始规则失败：" + oriRule);
            }
        } catch (Exception e) {
            logger.info("更新规则失败：" + e.getMessage());
        } finally {
            wirte(response, forObject);
        }
    }





    /**
     * 统计规则数目
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/statisticsrulercount")
    @ResponseBody
    public void statisticsrulercount(HttpServletResponse response) throws ExecutionException, InterruptedException {
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.statisticsrulercount, "", String.class);

        wirte(response, result);
    }

    /**
     * 分组统计规则分类得数据
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/groupbyclassification")
    @ResponseBody
    public void groupbyclassification(HttpServletResponse response) throws ExecutionException, InterruptedException {
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.groupbyclassification, "", String.class);

        wirte(response, result);
    }

    /**
     * 分组统计预警等级数据
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/groupbywarninglevel")
    @ResponseBody
    public void groupbywarninglevel(HttpServletResponse response) throws ExecutionException, InterruptedException {
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.groupbywarninglevel, "", String.class);

        wirte(response, result);
    }

    /**
     * 分组统计专科标识数据
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/groupbyidentification")
    @ResponseBody
    public void groupbyidentification(HttpServletResponse response) throws ExecutionException, InterruptedException {
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.groupbyidentification, "", String.class);

        wirte(response, result);
    }

    /**
     * 分组统计专科标识数据
     *
     * @param response
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping("/groupbycreatetime")
    @ResponseBody
    public void groupbycreatetime(HttpServletResponse response, @RequestBody String map) throws ExecutionException, InterruptedException {
        Object o = JSONObject.parse(map);
        String result = restTemplate.postForObject(urlConfig.getCdssurl() + BaseConstants.groupbycreatetime, o, String.class);

        wirte(response, result);
    }


}
