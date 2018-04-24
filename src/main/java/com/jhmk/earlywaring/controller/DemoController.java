package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    RestTemplate restTemplate;

    final String url = "http://192.168.8.20:8807/med/cdss/statisticsQuery.json";

    /***********HTTP GET method*************/

    @RequestMapping("/htm")
    public String welcome(ModelMap map) {
        Date date = new Date();
        //获取配置文件中的信息vv
        System.out.println(date);
        //传到template模板中（会把模板中能找到的所有的ftl文件中的名称都替换掉）
        map.addAttribute("vv", date.toString());
        //返回页面hello.ftl
        return "cdss";
    }

    @RequestMapping("/test")
    public void test1(@RequestParam String filterField, @RequestParam String keywords, @RequestParam String sortField) {
        System.out.println(filterField);
        System.out.println(keywords);
        System.out.println(sortField);
    }

    @RequestMapping("/cdss1")
    public String cdssTest2() {
        //头文件
        String uri="http://192.168.8.20:8820/demo/med/machinelearn/Emrdiseaseinfo.json";


        String s="binganshouye\t{…}\n" +
                "pat_info_age_value\t36\n" +
                "pat_info_age_value_unit\t年\n" +
                "pat_info_marital_status_name\t\n" +
                "pat_info_sex_name\t男\n" +
                "pat_visit_dept_admission_to_name\t1010113\n" +
                "pat_visit_dept_discharge_from_name\tnull\n" +
                "pregnancyStatus\t\n" +
                "binglizhenduan\t{…}\n" +
                "0\t{…}\n" +
                "diagnosis_code\tT13.209\n" +
                "diagnosis_name\t左膝前交叉韧带断裂\n" +
                "diagnosis_num\t1\n" +
                "diagnosis_time\t2017-12-24 09:34:58\n" +
                "diagnosis_type_name\tT13.209\n" +
                "pageSource\t2\n" +
                "patient_id\t000407778700\n" +
                "physicalSign\t{…}\n" +
                "bodyTempr\t\n" +
                "heartRate\t\n" +
                "highBldPress\t\n" +
                "lowBldPress\t\n" +
                "recordTime\t2017-12-2718:01:43\n" +
                "ruyuanjilu\t{…}\n" +
                "0\t\n" +
                "key\t主诉\n" +
                "value\t左膝外伤后疼痛、活动受限55天。\n" +
                "1\t\n" +
                "key\t一般情况\n" +
                "value\t发育正常，营养良好，正常面容，表情自如，自主体位，神志清楚，查体合作。\n" +
                "2\t\n" +
                "key\t淋巴结\n" +
                "value\t全身浅表淋巴结无肿大。\n" +
                "3\t\n" +
                "key\t头部\n" +
                "value\t头颅无畸形、压痛、包块、无眼睑水肿，结膜正常，眼球正常，巩膜无黄染，瞳孔等大同圆，对光反射正常，外耳道无异常分泌物，乳突无压痛，无听力粗试障碍。嗅觉正常。口唇无发绀，口腔粘膜正常。舌苔正常，伸舌无偏斜、震颤，齿龈正常，咽部粘膜正常，扁桃体无肿大。\n" +
                "4\t\n" +
                "key\t颈部\n" +
                "value\t颈软无抵抗，颈动脉搏动正常，颈静脉正常，气管居中，肝颈静脉回流征阴性，甲状腺正常，无压痛、震颤、血管杂音。\n" +
                "5\t\n" +
                "key\t胸部\n" +
                "value\t胸廓正常，胸骨无叩痛，乳房正常对称。呼吸运动正常，肋间隙正常，语颤正常。部位标志线叩诊清音，呼吸规整，\n" +
                "6\t\n" +
                "key\t腹部\n" +
                "value\t腹平坦，无腹壁静脉曲张，腹部柔软，无压痛、反跳痛，腹部无包块。肝脏未触及，脾脏未触及，Murphy氏征阴性，肾脏无叩击痛，无移动性浊音。肠鸣音正常，4次/分。\n" +
                "7\t\n" +
                "key\t现病史\n" +
                "value\t患者于55天前因踢足球导致左膝外翻伤，当即出现疼痛，不能继续运动，给予冰敷、制动治疗，1小时后出现左膝肿胀，经休息后2周肿胀疼痛缓解，可行走。现在，患者在不产生疼痛的情况下可进行步行，最近4周，快步走发生疼痛，疼痛的程度为2；膝关节没有肿胀，膝关节无明显肿胀情况下可进行的最大程度的活动是步行，未发生绞索，无卡压及关节弹响；膝关节不发生打软腿的情况下，能进行的最大程度的活动是步行；通常情况下能参加的最大程度的活动是步行；上楼困难很小，向前跪下困难很小，爬无困难，弯膝坐下无困难，从椅子上站起无困难，向前直跑中度困难，用患膝跳跃后落地不能完成，急起急停不能完成；认为自己基本可以 参加日常活动。在当地医院就诊，诊断为前交叉韧带断裂，内侧副韧带损伤，自行用护膝固定左膝1月，并给予外用药、休息治疗后，为求进一步诊治，来我院就诊，以左膝前交叉韧带断裂收入院，入院后查体患者左膝屈伸活动受限，建议患者回家锻炼膝关节，目前屈伸较前改善，再次来我院就诊，门诊以\"左前交叉韧带断裂\"收入院手术治疗。近来，不发热，饮食、睡眠可，二便均正常。\n" +
                "8\t\n" +
                "key\t既往史\n" +
                "value\t否认肝炎、结核、疟疾病史，否认高血压、心脏病史，否认糖尿病、脑血管疾病、精神疾病史，否认手术、外伤、输血史，否认食物、药物过敏史，预防接种史不详。无\n" +
                "9\t\n" +
                "key\t个人史\n" +
                "value\t生于山东省，久居本地，无疫区、疫情、疫水接触史，无牧区、矿山、高氟区、低碘区居住史无化学性物质、放射性物质、有毒物质接触史，无吸毒史，无吸烟、饮酒史专业运动员：否 婚育史：子女2人，体健；26岁结婚。\n" +
                "10\t\n" +
                "key\t婚姻史\n" +
                "value\t子女2人，体健；26\n" +
                "11\t\n" +
                "key\t家族史\n" +
                "value\t否认家族性遗传病史。\n" +
                "12\t\n" +
                "key\t辅助检查\n" +
                "value\t检查日期;项 目;结 果;检查单位;2017年11月03日;左膝MR;左膝前交叉韧带断裂，内侧副韧带损伤。 ;外院\n" +
                "13\t\n" +
                "key\t专科检查\n" +
                "value\t　　　　　　　　　　运医情况（膝关节）　　　　　　　　左膝\n" +
                "14\t\n" +
                "key\t专科查体\n" +
                "value\t阴性，阴性；正常，正常； 交叉韧带查体-左： ROM:0~120度，Lachman无抵抗，内侧开口感：0度可，30度稍松; 交叉韧带查体-右：\n" +
                "shouyezhenduan\t{}\n" +
                "visit_id\t2";

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
//        Map<String, String> postParameters = new HashMap<>();
        //body
//        postParameters.put("noumenon", "不稳定型心绞痛;稳定型心绞痛");
//        postParameters.put("field", "disease_symptom_statistics");
//        JSONObject json = (JSONObject) JSONObject.toJSON(postParameters);
//        HttpEntity<String> r = new HttpEntity(postParameters, headers);
//        JSONObject json = (JSONObject) JSONObject.toJSON(s);
//        System.out.println(json);
        //发送
        String data = restTemplate.postForObject(uri, s, String.class);
        System.out.println(data);

        return data;
    }

    @RequestMapping("/cdss2")
    public String cdssTest22() {
        //头文件

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        Map<String, String> postParameters = new HashMap<>();
        //body
        postParameters.put("department", "不稳定型心绞痛;稳定型心绞痛");
        postParameters.put("field", "disease_symptom_statistics");
        JSONObject json = (JSONObject) JSONObject.toJSON(postParameters);
        System.out.println(json);
        HttpEntity<String> r = new HttpEntity(postParameters, headers);
        //发送
        String data = restTemplate.postForObject(url, r, String.class);
        System.out.println(data);

        return data;
    }


//    @RequestMapping("/cdss1")
//    public String cdssTest1() {
//
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
////        JSONObject jsonObj = JSONObject.parseObject(paras);
//        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
////        {"noumenon":"不稳定型心绞痛;稳定型心绞痛","field":"disease_symptom_statistics"}
//        requestEntity.add("noumenon", "不稳定型心绞痛;稳定型心绞痛");
//        requestEntity.add("field", "disease_symptom_statistics");
//        HttpEntity<String> formEntity = new HttpEntity<String>(requestEntity.toString(), headers);
//        String result = restTemplate.postForObject(url, formEntity, String.class);
//
//
//        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
////        {"noumenon":"不稳定型心绞痛;稳定型心绞痛","field":"disease_symptom_statistics"}
//        requestEntity.add("noumenon", "不稳定型心绞痛;稳定型心绞痛");
//        requestEntity.add("field", "disease_symptom_statistics");
////        requestEntity.add("verifyData", strMd5);
//        String s = restTemplate.postForObject("http://192.168.8.20:8807/med/cdss/statisticsQuery.json", requestEntity, String.class);
//        System.out.println(s.toString());
//        return s;
//    }


    @RequestMapping("/cdss")
    public String cdssTest() {
        String url = "http://192.168.8.20:8807/med/cdss/statisticsQuery.json";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }


    @RequestMapping("")
    public String hello() {
        String url = "http://localhost:8080/json";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    @RequestMapping("/json")
    public Object genJson() {
        JSONObject json = new JSONObject();
        json.put("descp", "this is spring rest template sample");
        return json;
    }

    /**********HTTP POST method**************/
    @RequestMapping("/postApi")
    public Object iAmPostApi(@RequestBody JSONObject parm) {
        System.out.println(parm.toJSONString());
        parm.put("result", "hello post");
        return parm;
    }

    @RequestMapping("/post")
    public Object testPost() {
        String url = "http://localhost:8080/postApi";
        JSONObject postData = new JSONObject();
        postData.put("descp", "request for post");
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        return json.toJSONString();
    }

}
