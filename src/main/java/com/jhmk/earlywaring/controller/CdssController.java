//package com.jhmk.earlywaring.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.jhmk.earlywaring.base.BaseController;
//import com.jhmk.earlywaring.entity.SickBean;
//import com.jhmk.earlywaring.service.CdssService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/cdss")
//public class CdssController extends BaseController {
//
//
//    @Autowired
//    CdssService cdssService;
//
//    /**
//     * 根据aptientID和vositId查询
//     * @param response
//     * @param map
//     */
//    @PostMapping("/sel")
//    @ResponseBody
//    public void sel(HttpServletResponse response, @RequestBody String map) {
//
//        Map<String, String> paramMap = (Map) JSON.parse(map);
//        String patient_id = paramMap.get("patient_id");
//        String visitId = paramMap.get("visit_id");
//        //查询  binganshouye
//        SickBean selbinganshouye = cdssService.selbinganshouye(patient_id, visitId);
//        //查询  binglizhenduan
//        SickBean selbinglizhenduan = cdssService.selbinglizhenduan(selbinganshouye);
//        //查询  shouyezhenduan 暂无 todo
//
//        //查询  ruyuanjilu
//        SickBean selruyuanjilu = cdssService.selruyuanjilu(selbinglizhenduan);
//        Object o = JSONObject.toJSON(selruyuanjilu);
//        wirte(response, o);
//    }
//
//
//}
