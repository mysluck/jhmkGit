//package com.jhmk.earlywaring.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.jhmk.earlywaring.util.HttpHeadersUtils;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.med.cdss.dao.wiki.WikiDao;
//import org.med.cdss.util.ConfigUtil;
//import org.med.cdss.util.HbaseUtil;
//import org.med.cdss.util.HttpHeadersUtils;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.Resource;
//import java.nio.charset.Charset;
//import java.util.Collections;
//import java.util.Map;
//
//@Repository(value = "wikiDao")
//public class WikiDaoImpl implements WikiDao {
//
//    private static final Logger logger = LogManager.getLogger(WikiDaoImpl.class);
//
//    @Resource(name="restTemplate")
//    public RestTemplate restTemplate;
//
//    private String url;
//    private String statisUrl;
//    private String initstatisUrl;
//    private String sameWordUrl;
//    private String staticUrl;
//    private String childrenListUrl;
//    private String parentListUrl;
//    private String searchField;
//    private String historyField;
//    private String diseaseName;
//    private String diseaseSymptomExample;
//    private String diseaseDiagnosisName;
//    private String diseaseDiseaseCategory ;
//    private String diseaseRiskName;
//    private String diseaseExamExample;
//    private String diseaseLabExample;
//    private String diseaseSymptomProbability;
//    private String diseaseSymptomOrigin;
//
//
//    @PostConstruct//初始化方法的注解方式  等同与init-method=init
//    private void initialize(){
//        //常量定义
//        diseaseName = "disease_disease_name";
//        diseaseSymptomExample = "disease_symptom_symptom_symptom_example";
//        diseaseSymptomProbability = "disease_symptom_symptom_symptom_probability";
//        diseaseSymptomOrigin = "disease_symptom_symptom_symptom_origin";
//        diseaseDiagnosisName = "disease_differential_diagnosis_differential_diagnosis_name";
//        diseaseDiseaseCategory = "disease_disease_category";
//        diseaseRiskName = "disease_etiology_and_risk_factors_etiology_name";
//        diseaseExamExample = "disease_exam_exam_exam_example";
//        diseaseLabExample = "disease_lab_lab_lab_item_example";
//
//        Map<String, String> restUrlMap = ConfigUtil.getRestFullUrl();
//        String URL = restUrlMap.get("wikiSearchUrl");
//        url = URL + "/med/cdss/query.json";
//        statisUrl = URL + "/med/cdss/statisticsQuery.json";
//        initstatisUrl = URL + "/med/cdss/statisticsInitQuery.json";
//        sameWordUrl = URL + "/med/cdss/sameWord.json";
//        childrenListUrl = URL + "/med/cdss/childrenList.json";
//        staticUrl = URL + "/med/cdss/statisticsQuery.json";
//        parentListUrl = URL + "/med/cdss/parentList.json";
//        searchField = "patient_ruyuanjilu_content_chief_complaint_symptom_name";
//        historyField = "patient_ruyuanjilu_content_history_of_present_illness_timeline_time_symptoms_symptom_symptom_name";
//    }
//
//    @PreDestroy //销毁方法的注解方式  等同于destory-method=destory
//    private void destory(){}
//
//    @Override
//    public String getWikiInfo(String expJsonStr) {
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(expJsonStr,httpHeaders) ;
//        return restTemplate.<String>postForObject(url, httpEntity,String.class);
//    }
//
//    @Override
//    public String getWikiInfoByExpress(String expJsonStr) {
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(expJsonStr,httpHeaders) ;
//        return restTemplate.postForObject(url, httpEntity,String.class);
//    }
//
//    @Override
//    public String getWikiInfoByExpressSimple(String expJsonStr) {
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(expJsonStr,httpHeaders) ;
//        return restTemplate.postForObject(url, httpEntity,String.class);
//    }
//
//    @Override
//    public String getWikiInfoByName(String expJsonStr) {
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(expJsonStr,httpHeaders) ;
//        HbaseUtil h = new HbaseUtil();
//        return restTemplate.postForObject(url, httpEntity,String.class);
//    }
//
//    @Override
//    public String getStaticValues(Map<String,String> paramMap) {
//        JSONObject json = (JSONObject) JSONObject.toJSON(paramMap);
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(json,httpHeaders) ;
//        return restTemplate.postForObject(statisUrl, httpEntity,String.class);
//    }
//
//    @Override
//    public String getInitStaticValues(Map<String,String> paramMap) {
//        JSONObject json = (JSONObject) JSONObject.toJSON(paramMap);
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(json,httpHeaders) ;
//        return restTemplate.postForObject(initstatisUrl, httpEntity,String.class);
//    }
//
//    @Override
//    public String getWikiInfoByExpressSimpleTmp(String expJsonStr) {
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(expJsonStr,httpHeaders) ;
//        return restTemplate.postForObject(url, httpEntity,String.class);
//    }
//
//    @Override
//    public ResponseEntity<String> getSamilarWord(MultiValueMap<String, String> map) {
//        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(sameWordUrl, request, String.class);
//        return response;
//    }
//
//    @Override
//    public ResponseEntity<String> getDiseaseChildrenList(MultiValueMap<String, String> map) {
//        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(childrenListUrl, request, String.class);
//        return response;
//    }
//
//    @Override
//    public ResponseEntity<String> getParentList(MultiValueMap<String, String> map) {
//        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(parentListUrl, request, String.class);
//        return response;
//    }
//
//    @Override
//    public String getDiseaseStatic(Map<String,String> paramMap) {
//        JSONObject json = (JSONObject) JSONObject.toJSON(paramMap);
//        HttpHeaders httpHeaders = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
//                Collections.singletonList(MediaType.ALL));
//        HttpEntity<String> httpEntity = new HttpEntity(json,httpHeaders) ;
//        return restTemplate.postForObject(staticUrl, httpEntity,String.class);
//    }
//}
