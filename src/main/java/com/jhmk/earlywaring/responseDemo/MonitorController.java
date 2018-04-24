//package com.jhmk.earlywaring.responseDemo;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.med.cdss.model.DecisionRule;
//import org.med.cdss.result.GlobalErrorInfoException;
//import org.med.cdss.result.ResultBody;
//import org.med.cdss.service.MonitorService;
//import org.med.cdss.util.ResponseUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
//import static org.med.cdss.result.check.MonitorCheck.checkAddDecisionRule;
//import static org.med.cdss.result.check.MonitorCheck.checkParam;
//
//@RequestMapping(value = "/cindecision")
//@Controller
//public class MonitorController {
//
//    private static Logger logger = LoggerFactory.getLogger(MonitorController.class);
//
//    @Autowired
//    private MonitorService monitorService;
//
//    /**
//     * 获取全院数据科室分析之科室门诊数和住院数
//     */
//    @ResponseBody
//    @RequestMapping(value = "/gethospizationinfo.json", method = RequestMethod.POST)
//    public void getHospitalizationInfo(HttpServletResponse response, @RequestBody String map) {
//        response.setCharacterEncoding("UTF-8");
//        try {
//            Map<String, String> paramMap = (Map) JSON.parse(map);
//            String department = paramMap.get("department");
//            String startDate = paramMap.get("startdate");
//            String endDate = paramMap.get("enddate");
//            String flag = paramMap.get("flag");
//
//            //检验参数
//            checkParam(department, flag, startDate, endDate);
//
//            Map<String, Object> data = monitorService.getHospitalizationInfo(department, flag, startDate, endDate);
//            ResponseUtils.write(response, data);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","gethospizationinfo.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","gethospizationinfo.json");
//            ResponseUtils.write(response, "服务器错误:" + e2.getMessage());
//        }
//
//    }
//
//    /**
//     * 获取患者数
//     */
//    @ResponseBody
//    @RequestMapping(value = "/patinetmednum.json", method = RequestMethod.POST)
//    public void getPatientMedNum(HttpServletResponse response, @RequestBody String map) {
//        response.setCharacterEncoding("UTF-8");
//        try {
//            Map<String, String> paramMap = (Map) JSON.parse(map);
//            String department = paramMap.get("department");
//            String hospitalName = paramMap.get("hospitalname");
//            String flag = paramMap.get("flag");
//
//            //检验参数
//            checkParam(department, hospitalName, flag);
//
//            Map<String, Object> data = monitorService.getPatientMedNum(department, hospitalName, flag);
//            ResponseUtils.write(response, data);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常","patinetmednum.json");
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","patinetmednum.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 全院数据科室分析
//     */
//    @ResponseBody
//    @RequestMapping(value = "/medicalRecordsOfDepartment.json", method = RequestMethod.GET)
//    public void medicalRecordsOfDepartment(HttpServletResponse response) {
//        response.setCharacterEncoding("UTF-8");
//        try {
//            Map<String, Object> data = monitorService.medicalRecordsOfDepartment();
//            ResponseUtils.write(response, data);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","medicalRecordsOfDepartment.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","medicalRecordsOfDepartment.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 获取变量列表
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getVariableList.json",method = RequestMethod.GET)
//    public void getVariableList(HttpServletResponse response) {
//        response.setCharacterEncoding("UTF-8");
//        try {
//            List<Object> data = monitorService.getVariableList();
//            ResponseUtils.write(response, data);
//        }catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","getVariableList.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        }catch (Exception e2) {
//            logger.error("访问{}接口出现异常","getVariableList.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 增加一条规则
//     */
//    @ResponseBody
//    @RequestMapping(value = "/addrule.json",method = RequestMethod.POST)
//    public void addDecisionRule(HttpServletResponse response, @RequestBody String map) {
//        response.setCharacterEncoding("UTF-8");
//        try {
//            DecisionRule decisionRule = JSONObject.parseObject(map,DecisionRule.class);
//            checkAddDecisionRule(decisionRule);
//            monitorService.addDecisionRule(decisionRule);
//            ResponseUtils.write(response);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","addrule.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","addrule.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 查询所有未提交的规则信息
//     */
//    @ResponseBody
//    @RequestMapping(value = "/findallnotsubmitrule.json",method = RequestMethod.POST)
//    public void findAllNotSubmit(HttpServletResponse response,@RequestBody String map) {
//        try {
//            Map<String,String> param = (Map<String, String>) JSONObject.parse(map);
//            String doctorId = param.get("doctorId");
//            checkParam(doctorId);
//            List<DecisionRule> data = monitorService.findAllNotSubmit(doctorId);
//            ResponseUtils.write(response, data);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","findallnotsubmitrule.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","findallnotsubmitrule.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 查询所有提交的规则信息
//     */
//    @ResponseBody
//    @RequestMapping(value = "/findallsubmitrule.json",method = RequestMethod.POST)
//    public void findAllSubmit(HttpServletResponse response,@RequestBody String map) {
//        try {
//            Map<String,String> param = (Map<String, String>) JSONObject.parse(map);
//            String doctorId = param.get("doctorId");
//            checkParam(doctorId);
//            List<DecisionRule> data = monitorService.findAllSubmit(doctorId);
//            ResponseUtils.write(response, data);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","findallsubmitrule.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","findallsubmitrule.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//    /**
//     * 提交规则数据
//     */
//    @ResponseBody
//    @RequestMapping(value = "/submitrule.json",method = RequestMethod.POST)
//    public void submitDecisionRule(HttpServletResponse response,@RequestBody String map) {
//        try {
//            Map<String,String> param = (Map<String, String>) JSONObject.parse(map);
//            String _id = param.get("_id");
//            checkParam(_id);
//            monitorService.submitDecisionRule(_id);
//            ResponseUtils.write(response);
//        } catch (GlobalErrorInfoException e) {
//            logger.error("访问{}接口出现异常，错误编码:{},错误信息:{}","submitrule.json",e.getErrorInfo().getCode(),e.getErrorInfo().getMessage());
//            ResultBody resultBody = new ResultBody(e.getErrorInfo());
//            ResponseUtils.write(response,resultBody);
//        } catch (Exception e2) {
//            logger.error("访问{}接口出现异常","submitrule.json");
//            ResponseUtils.write(response,"服务器错误:" + e2.getMessage());
//        }
//    }
//
//
//}
