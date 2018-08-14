package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jhmk.earlywaring.entity.rule.AnalyzeBean;
import com.jhmk.earlywaring.entity.UserDataModelMapping;
import com.jhmk.earlywaring.entity.UserModel;
import com.jhmk.earlywaring.entity.repository.service.UserDataModelMappingRepService;
import com.jhmk.earlywaring.entity.repository.service.UserModelRepService;
import com.jhmk.earlywaring.util.JsonUtil;
import com.jhmk.earlywaring.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserModelService {

    @Autowired
    UserModelRepService userModelRepService;
    @Autowired
    UserDataModelMappingRepService userDataModelMappingRepService;

    public List<UserModel> getVariableList() {
        //查询所有父类modul集合
        Iterable<UserModel> iterable = userModelRepService.findAll();
//        List<UserModel> pModuls = userModulRepService.findByUmParentIdAndUmHospitalName(null, hosptialName);
        Iterator<UserModel> iterator = iterable.iterator();
        List<UserModel> pModuls = new ArrayList<>();
        while (iterator.hasNext()) {
            pModuls.add(iterator.next());
        }
        List<UserModel> userModels = recursiveSel1(pModuls);
        return userModels;
    }

    public List<UserModel> recursiveSel1(List<UserModel> pModuls) {
        List<UserModel> l1List = new LinkedList<>();
        List<UserModel> l2List = new LinkedList<>();
        List<UserModel> l3List = new LinkedList<>();
        for (UserModel userModel : pModuls) {
            if (userModel.getUmLevel() == 1) {
                l1List.add(userModel);
            } else if (userModel.getUmLevel() == 2) {
                l2List.add(userModel);
            } else if (userModel.getUmLevel() == 3) {
                l3List.add(userModel);
            }
        }

        //把list3放到list2中
        List<UserModel> userModels = setClildList2FatherList(l2List, l3List);
        List<UserModel> l1 = setClildList2FatherList(l1List, userModels);
        return l1;

    }


    /**
     * @param flist 父亲集合
     * @param clist 子集合
     * @return
     */
    public List<UserModel> setClildList2FatherList(List<UserModel> flist, List<UserModel> clist) {
        for (int i = 0; i < flist.size(); i++) {
            for (int j = 0; j < clist.size(); j++) {
                UserModel fModul = flist.get(i);
                UserModel cModel = clist.get(j);
                if (fModul.getUmId() == cModel.getUmParentId()) {
                    fModul.getUserModels().add(cModel);
                }
            }

        }
        return flist;

    }

    public List<UserModel> recursiveSel(List<UserModel> pModuls) {
        for (int i = 0; i < pModuls.size(); i++) {
            List<UserModel> childList = userModelRepService.findByUmParentId(pModuls.get(i).getUmId());
            if (childList.size() > 0) {
                return recursiveSel(childList);
            }
            pModuls.get(i).setUserModels(childList);
        }
        return pModuls;
    }


    /**
     * 前台规则数据解析为cdss 规则
     *
     * @param str
     * @return
     */
    public String getOldRule(Object str) {
        String string = JSONObject.toJSONString(str);
        JSONArray ruleCondition = JSONArray.parseArray(string);
        Iterator<Object> iterator = ruleCondition.iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            String s = analyzeOldRule(next);
            list.add(s);
        }
        String s = appensRuleCondition(list);
        return s;
    }

    /**
     *
     * @param list 小并列条件信息
     * @return 一条大并列条件
     */
    public String appensRuleCondition(List<String> list) {
        StringBuffer sb = new StringBuffer();
        sb.append("(");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append("and");
            }
        }
        sb.append(")");
        return sb.toString().trim();
    }

    /**
     * 解析原始规则
     *
     * @param str
     * @return
     */
    public String analyzeOldRule(Object str) {

        JSONArray expressions = (JSONArray) str;
        int size = expressions.size();
        StringBuffer sb = new StringBuffer("(");
        for (int i = 0; i < size; i++) {
            Object obj = JSON.toJSON(expressions.get(i));
            AnalyzeBean child = JsonUtil.parseObject(obj.toString(), AnalyzeBean.class);
            String field = child.getField();
            UserDataModelMapping byUmNamePath = userDataModelMappingRepService.findByUmNamePath(field);
            String dmNamePath = byUmNamePath.getDmNamePath();

            if (dmNamePath.contains(",")) {
                String[] split = dmNamePath.split(",");
                sb.append(ObjectUtils.flagObj(split[0]))
                        .append(ObjectUtils.flagObj(child.getExp()))
                        .append(ObjectUtils.flagObj(child.getValues()))
                        .append(ObjectUtils.flagObj(child.getUnit()));
                for (int j = 1; j < split.length; j++) {
                    sb.append("and").append(split[j].replace("=", "等于"));
                }
                if (i != size - 1) {
                    sb.append("and");
                }

            } else {

                sb.append(ObjectUtils.flagObj(dmNamePath))
                        .append(ObjectUtils.flagObj(child.getExp()))
                        .append(ObjectUtils.flagObj(child.getValues()))
                        .append(ObjectUtils.flagObj(child.getUnit()));

                if (i != size - 1) {
                    sb.append("and");
                }
            }
        }

        sb.append(")");
        return sb.toString();
    }


}
