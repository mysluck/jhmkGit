package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.config.UrlConfig;
import com.jhmk.earlywaring.entity.UserDataModelMapping;
import com.jhmk.earlywaring.entity.UserModel;
import com.jhmk.earlywaring.entity.repository.service.UserDataModelMappingRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.UserModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userModel")
public class UserModelController extends BaseEntityController<UserModel> {

    public static final Logger logger = LoggerFactory.getLogger(UserModelController.class);
    @Autowired
    UserModelService userModelService;
    @Autowired
    UrlConfig urlConfig;
    @Autowired
    UserDataModelMappingRepService userDataModelMappingRepService;
    @Autowired
    RestTemplate restTemplate;

    //获取分层列表
    @RequestMapping(value = "/getVariableList")
    @ResponseBody
    public void getVariableList(HttpServletResponse response) {
        List<UserModel> variableListNew = userModelService.getVariableList();
        wirte(response, variableListNew);
    }

    //解析字段名
    @RequestMapping(value = "/analyzeField")
    @ResponseBody
    public void analyzeField(HttpServletResponse response, @RequestBody Map<String, String> map) {
        String s = map.get("");
        List<UserModel> variableListNew = userModelService.getVariableList();
        wirte(response, variableListNew);
    }


    /**
     * 值域搜索
     *
     * @param response
     * @param param
     */
    @RequestMapping(value = "/getTypeByField")
    @ResponseBody
    public void getTypeByField(HttpServletResponse response, @RequestBody String param) {
//        AtResponse resp=new AtResponse();
        Map<String, String> map = (Map) JSON.parse(param);
        String s = map.get("variableid");
        UserDataModelMapping mapping = userDataModelMappingRepService.findByUmNamePath(s);
        if (mapping != null) {
            map.put("variableid", mapping.getDmTypePath());
            Object o = JSON.toJSON(map);
            String data = "";
            try {
                data = restTemplate.postForObject(urlConfig.getYwdurl() + BaseConstants.getfieldbyid, o, String.class);
                Map<String, Object> parse = (Map<String, Object>) JSON.parse(data);
//                resp.setResponseCode(ResponseCode.OK);
//                resp.setData(data);
            } catch (Exception e) {
                logger.info("值域搜索服务器匹配异常!" + mapping.getDmTypePath());
//                resp.setResponseCode(ResponseCode.INERERROR);
            } finally {
                wirte(response, data);
            }
        }

    }

    /**
     * 高级检索单位变量
     *
     * @param response
     * @param param
     */
    @RequestMapping(value = "/getunitsbyid")
    @ResponseBody
    public void getunitsbyid(HttpServletResponse response, @RequestBody String param) {
        AtResponse resp = new AtResponse();
        Map<String, String> map = (Map) JSON.parse(param);
        String s = map.get("variableid");
        UserDataModelMapping mapping = userDataModelMappingRepService.findByUmNamePath(s);
        if (mapping != null) {
            map.put("variableid", mapping.getDmNamePath());
            Object o = JSON.toJSON(map);
            String data = "";
            try {

                data = restTemplate.postForObject(urlConfig.getYwdurl() +BaseConstants.getunitsbyid, o, String.class);
//                JSONObject list=JSON.parseObject(data);
//                Object o1 = list.get(0);
//                JSONArray objects = JSONArray.parseArray(data);
                resp.setData(data);
                resp.setResponseCode(ResponseCode.OK);
            } catch (Exception e) {
                logger.info("高级检索单位变量接口服务器匹配异常!" + mapping.getDmNamePath());
                resp.setResponseCode(ResponseCode.INERERROR);

            } finally {
                wirte(response, data);
            }
        }

    }

    /**
     * 高级检索根据关键字检索 拼音or汉字（post）
     *
     * @param response
     * @param param
     */
    @RequestMapping(value = "/searchbyvariablename")
    @ResponseBody
    public void searchbyvariablename(HttpServletResponse response, @RequestBody String param) {
        AtResponse resp = new AtResponse();
        Map<String, String> map = (Map) JSON.parse(param);
        String fieldname = map.get("fieldname");
        map.put("fieldname", fieldname);
        Object o = JSON.toJSON(map);
        String data = "";
        try {

            data = restTemplate.postForObject(urlConfig.getYwdurl() +BaseConstants.searchbyvariablename, o, String.class);
            resp.setResponseCode(ResponseCode.OK);
            resp.setData(data);
        } catch (Exception e) {
            logger.info("高级检索根据关键字检索接口服务器匹配异常!" + fieldname);
            resp.setResponseCode(ResponseCode.INERERROR);
        }
        wirte(response, data);
    }

    //    接口：获取字段的特殊类型
    @RequestMapping(value = "/getSpecialTypeByField")
    @ResponseBody
    public void getSpecialTypeByField(HttpServletResponse response, @RequestBody String param) {
        Map<String, String> map = (Map) JSON.parse(param);
        String s = map.get("variableid");
        AtResponse<String> resp = new AtResponse();
        UserDataModelMapping mapping = userDataModelMappingRepService.findByUmNamePath(s);
        LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap();
        if (mapping != null) {

            multiValueMap.add("variableid", mapping.getDmNamePath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<LinkedMultiValueMap> entity = new HttpEntity<LinkedMultiValueMap>(multiValueMap, headers);
            ResponseEntity<String> data = null;
            String body = "";

            try {
                data = restTemplate.postForEntity(urlConfig.getYwdurl() +BaseConstants.getSpecialTypeByField, entity, String.class);
                body = data.getBody();
                resp.setResponseCode(ResponseCode.OK);
            } catch (Exception e) {
                logger.info("获取字段的特殊类型接口服务器匹配异常!" + s);
                resp.setResponseCode(ResponseCode.INERERROR);
            } finally {
                resp.setData(body);
                wirte(response, resp);
            }
        }
    }

}



