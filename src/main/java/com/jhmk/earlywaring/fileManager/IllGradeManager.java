//package com.jhmk.earlywaring.fileManager;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.jhmk.earlywaring.config.BaseConstants;
//import com.jhmk.earlywaring.config.UrlConfig;
//import com.jhmk.earlywaring.entity.rule.Zhenduan;
//import com.jhmk.earlywaring.util.SpringUtil;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.*;
//import java.util.*;
//
///**
// * 各科疾病评分表
// */
//
//@Component
//@Order(value = 1)
//public class IllGradeManager implements CommandLineRunner {
//    public static Map<String, List<String>> illMap = new HashMap<String, List<String>>(); //获取疾病对应的评分
//
//
//    @Override
//    public void run(String... strings) throws Exception {
//        Map<String, Set<String>> csvFile2Map = getCsvFile2Map();
//        for (Map.Entry<String,Set<String>> entry:csvFile2Map.entrySet()) {
//            System.out.println(entry.getKey()+":"+entry.getValue().toString());
//        }
//    }
//
//    /**
//     * 读取csv文件放入map中
//     *
//     * @return
//     */
//    private Map<String, Set<String>> getCsvFile2Map() {
//        Map<String, Set<String>> mapList = new HashMap<>();
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:illGrade.csv");
//            String s;
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));//这里如果csv文件编码格式是utf-8,改成utf-8即可
//            while ((s = br.readLine()) != null) {
//                String line = br.readLine();
//                System.out.println(line);
//                if (line == null || "".equals(line) || "null".equals(line)) {
//                    continue;
//                }
//                String[] split = line.split(",");
//                if (split.length == 2) {
//                    List<String> tempnames = new ArrayList<>();
//                    String illName = split[1];//疾病名称
//                    String grade = split[0];//评分名称
//                    List<String> samilarWord = getSamilarWord(illName);
//                    List<String> diseaseChildrenList = getDiseaseChildrenList(illName);
//                    tempnames.add(illName);
//                    tempnames.addAll(samilarWord);
//                    tempnames.addAll(diseaseChildrenList);
//                    for (String name : tempnames) {
//                        if (mapList.containsKey(name)) {
//                            Set<String> set = mapList.get(name);
//                            set.add(grade);
//                            mapList.put(name, set);
//                        } else {
//                            Set<String> set = new HashSet<>();
//                            set.add(grade);
//                            mapList.put(name, set);
//                        }
//                    }
//
//                }
//            }
//
//            return mapList;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    private List<String> getSamilarWord(String name) {
//
//        RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);
//        UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);
//        Map<String, String> param = new HashMap<>();
//        List<String> names = new ArrayList<>();
//        param.put("diseaseName", name);
//        Object parse1 = JSONObject.toJSON(param);
//        String sames = restTemplate.postForObject(urlConfig.getMed() + BaseConstants.getSamilarWord, parse1, String.class);
//        if (sames != null && !"[]".equals(sames.trim())) {
//            JSONArray objects = JSONArray.parseArray(sames);
//            Iterator<Object> iterator = objects.iterator();
//            while (iterator.hasNext()) {
//                Object next = iterator.next();
//                names.add(next.toString());
//            }
//        }
//        return names;
//    }
//
//    private List<String> getParentList(String name) {
//
//        RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);
//        UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);
//        Map<String, String> param = new HashMap<>();
//        List<String> names = new ArrayList<>();
//        param.put("diseaseName", name);
//        Object parse1 = JSONObject.toJSON(param);
//        String sames = restTemplate.postForObject(urlConfig.getMed() + BaseConstants.getParentList, parse1, String.class);
//        if (sames != null && !"[]".equals(sames.trim())) {
//            JSONArray objects = JSONArray.parseArray(sames);
//            Iterator<Object> iterator = objects.iterator();
//            while (iterator.hasNext()) {
//                Object next = iterator.next();
//                names.add(next.toString());
//            }
//        }
//        return names;
//    }
//
//    private List<String> getDiseaseChildrenList(String name) {
//
//        RestTemplate restTemplate = SpringUtil.getBean(RestTemplate.class);
//        UrlConfig urlConfig = SpringUtil.getBean(UrlConfig.class);
//        Map<String, String> param = new HashMap<>();
//        List<String> names = new ArrayList<>();
//        param.put("diseaseName", name);
//        Object parse1 = JSONObject.toJSON(param);
//        String sames = restTemplate.postForObject(urlConfig.getMed() + BaseConstants.getDiseaseChildrenList, parse1, String.class);
//        if (sames != null && !"[]".equals(sames.trim())) {
//            JSONArray objects = JSONArray.parseArray(sames);
//            Iterator<Object> iterator = objects.iterator();
//            while (iterator.hasNext()) {
//                Object next = iterator.next();
//                names.add(next.toString());
//            }
//        }
//        return names;
//    }
//
//
//}
