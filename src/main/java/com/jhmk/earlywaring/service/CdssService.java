//package com.jhmk.earlywaring.service;
//
//
//import com.jhmk.earlywaring.config.BaseConstants;
//import com.jhmk.earlywaring.entity.Binglizhenduan;
//import com.jhmk.earlywaring.entity.SickBean;
//import com.mongodb.client.AggregateIterable;
//import com.mongodb.client.MongoCollection;
//import org.bson.Document;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//import static com.jhmk.earlywaring.util.MongoUtils.getCollection;
//
//
//@Service
//public class CdssService {
//     MongoCollection<Document> binganshouye = getCollection(BaseConstants.DATASOURCES, "binganshouye");
//    //入院记录
//     MongoCollection<Document> ruyuanjilu = getCollection(BaseConstants.DATASOURCES, "ruyuanjilu");
//    //病理诊断 初诊
//     MongoCollection<Document> binglizhenduan = getCollection(BaseConstants.DATASOURCES, BaseConstants.BINGLIZHENDUAN);
//     MongoCollection<Document> shouyezhenduan = getCollection(BaseConstants.DATASOURCES, BaseConstants.SHOUYEZHENDUAN);
//
//    public SickBean selbinganshouye(String patient_id, String visitId) {
//        SickBean sickBean = new SickBean();
//        sickBean.setPatient_id(patient_id);
//        sickBean.setVisit_id(visitId);
//        List<Document> countPatientId = Arrays.asList(
//
//                new Document("$match", new Document("patient_id", patient_id)),
//                new Document("$match", new Document("visit_id", visitId)),
//                new Document("$project", new Document("patient_id", 1).append("visit_id", 1).append("binganshouye", 1))
//        );
//        AggregateIterable<Document> output = binganshouye.aggregate(countPatientId);
//        for (Document document : output) {
//            if (document == null) {
//                continue;
//            }
//            Document binganshouye = (Document) document.get("binganshouye");
//            Document patInfo = (Document) binganshouye.get("pat_info");
//            Document patVisit = (Document) binganshouye.get("pat_visit");
//            //病案首页
//            Map<String, String> map = new HashMap<>();
//            map.put("pat_info_sex_name", (String) patInfo.get("sex_name"));
//            map.put("pat_info_age_value", (String) patVisit.get("age_value"));
//            map.put("pat_info_age_value_unit", (String) patVisit.get("age_value_unit"));
//            map.put("pat_info_marital_status_name", (String) patVisit.get("marital_status_name"));
//            map.put("pregnancyStatus", "");
//            //暂时写死 todo
//            map.put("pat_visit_dept_admission_to_name", "1010113");
//            map.put("pat_visit_dept_discharge_from_name", (String) patVisit.get("dept_discharge_from_name"));
//            sickBean.setBinganshouye(map);
//        }
//        return sickBean;
//    }
//
//    //获取病理诊断
//    public SickBean selbinglizhenduan(SickBean selbinganshouye) {
//
//        List<Document> countPatientId = Arrays.asList(
//                new Document("$unwind", "$binglizhenduan"),
//                new Document("$match", new Document("patient_id", selbinganshouye.getPatient_id())),
//                new Document("$match", new Document("visit_id", selbinganshouye.getVisit_id())),
//                new Document("$project", new Document("_id", 1).append("patient_id", 1).append("visit_id", 1).append("binglizhenduan", 1))
//        );
//        AggregateIterable<Document> binli = binglizhenduan.aggregate(countPatientId);
//        List<Binglizhenduan> list = new ArrayList<>();
//        for (Document document : binli) {
//            Binglizhenduan binglizhenduanBean = new Binglizhenduan();
//            Document binglizhenduan = (Document) document.get("binglizhenduan");
//            binglizhenduanBean.setDiagnosis_name((String) binglizhenduan.get("diagnosis_name"));//初诊
//            binglizhenduanBean.setDiagnosis_time((String) binglizhenduan.get("diagnosis_time"));
//            binglizhenduanBean.setDiagnosis_num((String) binglizhenduan.get("diagnosis_num"));
//            //todo  数据库无此字段
////            binglizhenduanBean.setDiagnosis_code((String) binglizhenduan.get("diagnosis_name"));
//            binglizhenduanBean.setDiagnosis_type_name((String) binglizhenduan.get("diagnosis_type_name"));
//            list.add(binglizhenduanBean);
//        }
//        selbinganshouye.setBinglizhenduan(list);
//        return selbinganshouye;
//    }
//
//    //入院记录
//    public SickBean selruyuanjilu(SickBean selbinglizhenduan) {
//        List<Map<String, String>> ruyuanjiluList = new ArrayList<>();
//        List<Document> countPatientId2 = Arrays.asList(
//                //过滤数据
//                new Document("$match", new Document("patient_id", selbinglizhenduan.getPatient_id())),
//                new Document("$match", new Document("visit_id", selbinglizhenduan.getVisit_id())),
//                new Document("$project", new Document("patient_id", 1).append("visit_id", 1).append("ruyuanjilu", 1)
//                )
//
//        );
//        AggregateIterable<Document> output = ruyuanjilu.aggregate(countPatientId2);
//        for (Document document : output) {
//            Document ruyuanjilu = (Document) document.get("ruyuanjilu");
//            if (ruyuanjilu == null) {
//                continue;
//            }
//            if (ruyuanjilu.get("chief_complaint") == null) {
//                continue;
//            }
//            Document chief_complaint = (Document) ruyuanjilu.get("chief_complaint");
//            Map<String, String> mapzs = new LinkedHashMap<>();
//            mapzs.put("key", "主诉");
//            mapzs.put("value", flagObj(chief_complaint.get("src")));
//            //主诉
//            ruyuanjiluList.add(mapzs);
//            //一般情况
//            //淋巴结
//            //头部
//            //现病史
//            Document history_of_present_illness = (Document) ruyuanjilu.get("history_of_present_illness");
//            Map<String, String> mapxbs = new LinkedHashMap<>();
//            mapxbs.put("key", "现病史");
//            mapxbs.put("value", flagObj(history_of_present_illness.get("src")));
//            ruyuanjiluList.add(mapxbs);
//            //既往史
//            Document history_of_past_illness = (Document) ruyuanjilu.get("history_of_past_illness");
//            Map<String, String> jws = new LinkedHashMap<>();
//            jws.put("key", "既往史");
//            jws.put("value", flagObj(history_of_past_illness.get("src")));
//            ruyuanjiluList.add(jws);
//            //个人史
//            Document social_history = (Document) ruyuanjilu.get("social_history");
//            Map<String, String> grs = new LinkedHashMap<>();
//            grs.put("key", "个人史");
//            grs.put("value", flagObj(social_history.get("src")));
//            ruyuanjiluList.add(grs);
//            //婚姻史
//            Document menstrual_and_obstetrical_histories = (Document) ruyuanjilu.get("menstrual_and_obstetrical_histories");
//            Map<String, String> hys = new LinkedHashMap<>();
//            hys.put("key", "婚姻史");
//            hys.put("value", flagObj(menstrual_and_obstetrical_histories.get("src")));
//            ruyuanjiluList.add(hys);
//            //家族史
//            Document history_of_family_member_diseases = (Document) ruyuanjilu.get("history_of_family_member_diseases");
//            Map<String, String> jzs = new LinkedHashMap<>();
//            jzs.put("key", "家族史");
//            jzs.put("value", flagObj(history_of_family_member_diseases.get("src")));
//            ruyuanjiluList.add(jzs);
//
//            //辅助检查
//            Document physical_examination = (Document) ruyuanjilu.get("physical_examination");
//            Map<String, String> fzjc = new LinkedHashMap<>();
//            fzjc.put("key", "辅助检查");
//            fzjc.put("value", flagObj(physical_examination.get("src")));
//            ruyuanjiluList.add(fzjc);
//
//            //专科检查
//            Document special_examination = (Document) ruyuanjilu.get("special_examination");
//            if (special_examination != null) {
//                Map<String, String> zkjc = new LinkedHashMap<>();
//                zkjc.put("key", "辅助检查");
//                zkjc.put("value", flagObj(special_examination.get("src")));
//                ruyuanjiluList.add(zkjc);
//            }
//
//
//            //专科查体
//            Document auxiliary_examination = (Document) ruyuanjilu.get("auxiliary_examination");
//            Map<String, String> zkct = new LinkedHashMap<>();
//            zkct.put("key", "辅助检查");
//            zkct.put("value", flagObj(auxiliary_examination.get("src")));
//            ruyuanjiluList.add(fzjc);
//            selbinglizhenduan.setRuyuanjiluList(ruyuanjiluList);
//        }
//        return selbinglizhenduan;
//    }
//
//    //类似于3目运算符
//    public static String flagObj(Object str) {
//        return (String) Optional.ofNullable(str)
//                .orElse("");
//    }
//}
