package com.jhmk.earlywaring.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class JsonDemo {
    public void A(String map) {
        Map<String, String> paramMap = (Map) JSON.parse(map);
        Map<String, String> endparamMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            if (key.equals("ruyuanjilu")) {
                String ryjl = String.valueOf(paramMap.get("ruyuanjilu"));
                JSONArray jsonArray = JSON.parseArray(ryjl);
                Iterator<Object> it = jsonArray.iterator();
                Map<String, String> ryjlMap = new HashMap<String, String>();
                while (it.hasNext()) {
                    JSONObject ob = (JSONObject) it.next();
                    String ryjlkey = ob.getString("key");
                    String value = ob.getString("value");
                    if (value != null && !value.isEmpty()) {
                        if (ryjlkey.equals("既往史")) {
                            ryjlMap.put("history_of_past_illness", value);
                        } else if (ryjlkey.equals("主诉")) {
                            ryjlMap.put("chief_complaint", value);
                        } else if (ryjlkey.equals("现病史")) {
                            ryjlMap.put("history_of_present_illness", value);
                        } else if (ryjlkey.equals("家族史")) {
                            ryjlMap.put("history_of_family_member_diseases", value);
                        } else if (ryjlkey.equals("婚姻史")) {
                            ryjlMap.put("menstrual_and_obstetrical_histories", value);
                        } else if (ryjlkey.equals("辅助检查")) {
                            ryjlMap.put("auxiliary_examination", value);
                        } else if (ryjlkey.equals("体格检查")) {
                            ryjlMap.put("history_of_past_illness", value);
                        }
                    }
                }
                endparamMap.put("ruyuanjilu", JSONObject.toJSONString(ryjlMap));
            } else if (key.equals("jianyanbaogao")) {
                String jybg = String.valueOf(paramMap.get("jianyanbaogao"));
                JSONArray jsonArray = JSON.parseArray(jybg);
                Iterator<Object> it = jsonArray.iterator();
                List<Map<String, String>> jybgMap = new ArrayList<Map<String, String>>();
                while (it.hasNext()) {
                    JSONObject ob = (JSONObject) it.next();
                    String jcbgkey = ob.getString("key");
                    String value = ob.getString("value");
                    Map<String, String> jcbg = new HashMap<String, String>();
                    if (!jcbgkey.equals("labTestItems")) {
                        jcbg.put(key, value);
                    } else {
                        String sub = String.valueOf(paramMap.get("labTestItems"));
                        JSONArray sbjsonArray = JSON.parseArray(sub);
                        for (Object object : sbjsonArray) {
                            JSONObject sbobj = (JSONObject) object;
                            if (sbobj.getString("name") != null)
                                jcbg.put("lab_sub_item_name", sbobj.getString("name"));
                            if (sbobj.getString("unit") != null)
                                jcbg.put("lab_result_value_unit", sbobj.getString("unit"));
                            if (sbobj.getString("lab_result") != null)
                                jcbg.put("lab_result_value", sbobj.getString("lab_result"));
                            if (sbobj.getString("result_status_code") != null)
                                jcbg.put("result_status_code", sbobj.getString("result_status_code"));
                        }
                    }
                    jybgMap.add(jcbg);
                }

            } else {
                endparamMap.put(key, paramMap.get(key));
            }
        }
    }
}
