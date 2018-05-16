package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/cdssTest")
public class CdssController extends BaseController {


    /**
     * 根据aptientID和vositId查询
     *
     * @param response
     * @param map
     */
    @PostMapping("/sel")
    @ResponseBody
    public void sel(HttpServletResponse response, @RequestBody String map) {

        Map<String, String> paramMap = (Map) JSON.parse(map);
        String patient_id = paramMap.get("patient_id");
        String visitId = paramMap.get("visit_id");

    }

    /**
     * 辅助诊疗
     * @param response
     * @param map
     */
    @PostMapping("/auxiliary")
    @ResponseBody
    public void auxiliaryDiagnosis (HttpServletResponse response, @RequestBody Map<String,String> map) {
//
//        Map<String, String> paramMap = (Map) JSON.parse(map);
//        String patient_id = paramMap.get("patient_id");
//        String visitId = paramMap.get("visit_id");

    }


    /**
     * 诊断./ 医嘱 预警
     * @param response
     * @param map
     */
    @PostMapping("/waring")
    @ResponseBody
    public void waring (HttpServletResponse response, @RequestBody  Map<String,String> map) {

//        String patient_id = paramMap.get("patient_id");
//        String visitId = paramMap.get("visit_id");

    }




}
