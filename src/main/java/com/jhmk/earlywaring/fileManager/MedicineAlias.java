//package com.jhmk.earlywaring.fileManager;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * 项目启动后初始化加载到内存
// */
//@Component
//@Order(value = 1)
//public class MedicineAlias implements CommandLineRunner {
//    public static Map<String, String> dictionaryInfoMap = new HashMap<String, String>(); //获取同义词
//    public static List<String> standardMedicine = new ArrayList<>(); //保存药品标准名
//
//    @Override
//    public void run(String... strings) throws Exception {
//        Resource resource = new ClassPathResource("medicine_alias.txt");
//        File file = resource.getFile();
//        String s;
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//        while ((s = br.readLine()) != null) {
//            String line = br.readLine();
//            System.out.println(line);
//            if (line == null||"".equals(line)||"null".equals(line)) {
//                continue;
//            }
//            String[] split = line.split("#@#");
//            standardMedicine.add(split[0]);
//
//        }
//    }
//}
//
