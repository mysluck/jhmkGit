package com.jhmk.earlywaring.controller;

import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

/**
 * 辅助诊疗
 */
@Controller
@RequestMapping("/warn/diagnosis")

public class DiagnosisController {
    String uri = "http://192.168.8.20:8820/demo/med/machinelearn/Emrdiseaseinfo.json";

    /**
     * todo
     *
     * @return
     */
    @RequestMapping(value = "")
    @ResponseBody
    public AtResponse pre() {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        AtResponse<Map<String, Object>> resp = new AtResponse<>();
        resp.setResponseCode(ResponseCode.OK);

//        resp.setData(data);
        return resp;
    }

    /**
     * 专科化辅助诊断推荐情况统计
     *
     * @return
     */
    @RequestMapping(value = "getRateForSpecialty")
    @ResponseBody
    public AtResponse getRateForSpecialty(@RequestParam String deptId) {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        AtResponse<Map<String, Object>> resp = new AtResponse<>();
        resp.setResponseCode(ResponseCode.OK);

//        resp.setData(data);
        return resp;
    }
}
