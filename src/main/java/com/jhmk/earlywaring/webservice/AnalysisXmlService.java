package com.jhmk.earlywaring.webservice;

import com.jhmk.earlywaring.util.RegularUtils;
import org.dom4j.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author ziyu.zhou
 * @date 2018/7/19 18:07
 */

public class AnalysisXmlService {

    /**
     * 解析病案首页
     *
     * @param str
     * @return
     */
    public Map<String, String> analysisXml2Binganshouye(String str) {
        Map<String, String> binganshouye = new HashMap<>();
        try {
            Document dom = DocumentHelper.parseText(str);
            Element root = dom.getRootElement();
            Element item = root.element("item");
            if (Objects.nonNull(item)) {

                List<Element> items = item.elements();
                Element age_valueElement = item.element("AGE_VALUE");
                if (Objects.nonNull(age_valueElement)) {
                    String text = age_valueElement.getText();
                    String s = RegularUtils.delCC(text);
                    //age
                    binganshouye.put("pat_info_age_value", s);
                }

                Element sex_name = item.element("SEX_NAME");
                if (Objects.nonNull(sex_name)) {
                    String text = sex_name.getText();
                    //性别
                    binganshouye.put("pat_info_sex_name", text);
                }
                Element MARITAL_STATUS_NAME = item.element("MARITAL_STATUS_NAME");
                if (Objects.nonNull(MARITAL_STATUS_NAME)) {
                    String marital_status_nameText = MARITAL_STATUS_NAME.getText();
                    //婚配
                    binganshouye.put("pat_info_marital_status_name", marital_status_nameText);
                }
                Element OCCUPATION_NAME = item.element("OCCUPATION_NAME");
                if (Objects.nonNull(OCCUPATION_NAME)) {
                    String text = OCCUPATION_NAME.getText();
                    //职业
                    binganshouye.put("pat_info_occupation_name", text);
                }
                Element dept_admission_to_name = item.element("DEPT_ADMISSION_TO_NAME");
                if (Objects.nonNull(dept_admission_to_name)) {
                    String text = dept_admission_to_name.getText();
                    //r入院部门
                    binganshouye.put("pat_visit_dept_admission_to_name", text);
                }
                Element dept_admission_to_code = item.element("DEPT_ADMISSION_TO_CODE");
                if (Objects.nonNull(dept_admission_to_code)) {
                    String text = dept_admission_to_code.getText();
                    //入院部门编码
                    binganshouye.put("pat_visit_dept_admission_to_code", text);
                }
                Element dept_discharge_from_name = item.element("DEPT_DISCHARGE_FROM_NAME");
                if (Objects.nonNull(dept_discharge_from_name)) {
                    String text = dept_discharge_from_name.getText();
                    //r入院部门
                    binganshouye.put("pat_visit_dept_discharge_from_name", text);
                }
                Element dept_discharge_from_code = item.element("DEPT_DISCHARGE_FROM_CODE");
                if (Objects.nonNull(dept_discharge_from_code)) {
                    String text = dept_discharge_from_code.getText();
                    //入院部门编码
                    binganshouye.put("pat_visit_dept_discharge_from_code", text);
                }

            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return binganshouye;
    }


    /**
     * 诊断(适用病例诊断、首页诊断)
     *
     * @param str
     * @return
     */
    public List<Map<String, String>> analysisXml2Zhenduan(String str) {
        List<Map<String, String>> blzdList = new LinkedList<>();
        try {
            Document dom = DocumentHelper.parseText(str);
            Element root = dom.getRootElement();
            List<Element> items = root.elements();
            if (Objects.nonNull(items)) {

//                List<Element> items = item.elements();
                for (int i = 0; i < items.size(); i++) {
                    Map<String, String> blzdMap = new HashMap<>();
                    Element element = items.get(i);
                    //诊断名称
                    Element diagnosis_name = element.element("DIAGNOSIS_NAME");
                    if (Objects.nonNull(diagnosis_name)) {
                        blzdMap.put("diagnosis_name", diagnosis_name.getText());
                    }
                    Element diagnosis_desc = element.element("DIAGNOSIS_DESC");
                    if (Objects.nonNull(diagnosis_desc)) {
                        blzdMap.put("diagnosis_desc", diagnosis_desc.getText());
                    }
                    Element diagnosis_time = element.element("DIAGNOSIS_TIME");
                    if (Objects.nonNull(diagnosis_time)) {
                        blzdMap.put("diagnosis_time", diagnosis_time.getText());
                    }

                    //初步诊断
                    Element diagnosis_property_name = element.element("DIAGNOSIS_PROPERTY_NAME");
                    if (Objects.nonNull(diagnosis_property_name)) {
                        blzdMap.put("diagnosis_type_name", diagnosis_property_name.getText());
                    }

                    //诊断编号
                    Element diagnosis_num = element.element("DIAGNOSIS_NUM");
                    if (Objects.nonNull(diagnosis_num)) {
                        blzdMap.put("diagnosis_num", diagnosis_num.getText());
                    }

                    Element diagnosis_sub_num = element.element("DIAGNOSIS_SUB_NUM");
                    if (Objects.nonNull(diagnosis_sub_num)) {
                        blzdMap.put("diagnosis_sub_num", diagnosis_sub_num.getText());
                    }
                    if (blzdMap.size() != 0) {
                        blzdList.add(blzdMap);
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return blzdList;
    }

    /**
     * 检查报告
     * @param str
     * @return
     */
    public List<Map<String, String>> analysisXml2Jianchabaogao(String str) {
        List<Map<String, String>> jcbgList = new LinkedList<>();
        try {
            Document dom = DocumentHelper.parseText(str);
            Element root = dom.getRootElement();
            List<Element> items = root.elements();
            if (Objects.nonNull(items)) {

//                List<Element> items = item.elementsm();
                for (int i = 0; i < items.size(); i++) {
                    Map<String, String> map = new HashMap<>();
                    Element element = items.get(i);
                    //诊断名称
                    Element exam_item_name = element.element("EXAM_ITEM_NAME");
                    if (Objects.nonNull(exam_item_name)) {
                        map.put("exam_item_name", exam_item_name.getText());
                    }
                    //检查诊断
                    Element exam_diag = element.element("EXAM_DIAG");
                    if (Objects.nonNull(exam_diag)) {
                        map.put("exam_diag", exam_diag.getText());
                    }
                    //检查特征
                    Element exam_feature = element.element("EXAM_FEATURE");
                    if (Objects.nonNull(exam_feature)) {
                        map.put("exam_feature", exam_feature.getText());
                    }

                    //检查时间
                    Element report_time = element.element("REPORT_TIME");
                    if (Objects.nonNull(report_time)) {
                        map.put("exam_time", report_time.getText());
                    }

                    if (map.size() != 0) {
                        jcbgList.add(map);
                    }
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return jcbgList;
    }


}
