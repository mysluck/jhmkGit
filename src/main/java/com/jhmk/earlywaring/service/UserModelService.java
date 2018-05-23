package com.jhmk.earlywaring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.LinkedList;
import java.util.List;

@Service
public class UserModelService {
    //    private static final String hosptialName = "chaoyang";
    @Value("${hospitalName}")
    String hospitalName;
    @Autowired
    UserModelRepService userModelRepService;
    @Autowired
    UserDataModelMappingRepService userDataModelMappingRepService;

    public List<UserModel> getVariableList() {
        //查询所有父类modul集合
        List<UserModel> pModuls = userModelRepService.findByUmHospitalName(hospitalName);
//        List<UserModel> pModuls = userModulRepService.findByUmParentIdAndUmHospitalName(null, hosptialName);
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
            List<UserModel> childList = userModelRepService.findByUmParentIdAndUmHospitalName(pModuls.get(i).getUmId(), hospitalName);
            if (childList.size() > 0) {
                return recursiveSel(childList);
            }
            pModuls.get(i).setUserModels(childList);
        }
        return pModuls;
    }

    /**
     * 解析前台规则()
     * eg:{"expressions":[[{"field":"病案首页_基本信息_年龄","exp":"等于","values":["33"],"flag":"1","unit":"岁"}],[{"field":"检验_检验_检验项目","exp":"等于","values":["心电图"],"flag":"1","unit":""}],[{"field":"病理_基本信息_病理号","exp":"包含","values":["3"],"flag":"1","unit":""}]]}
     * <p>
     * 解析成中间用and链接
     *
     * @param str
     * @return
     */
    public String analyzeData(String str) {

        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray expressions = (JSONArray) jsonObject.get("expressions");
        int size = expressions.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            StringBuffer sb = new StringBuffer("(");
            JSONArray o = (JSONArray) expressions.get(i);
            //如果o。size大于1  拼接or
            if (o.size() > 1) {
                for (int j = 0; j < o.size(); j++) {
                    AnalyzeBean child = JSON.parseObject(o.get(j).toString(), AnalyzeBean.class);
                    String field = child.getField();
                    UserDataModelMapping byUmNamePath = userDataModelMappingRepService.findByUmNamePath(field);
                    sb.append(byUmNamePath.getDmNamePath())
                            .append(child.getExp())
                            .append(child.getValues())
                            .append(child.getUnit());
                    if (j != o.size() - 1) {
                        sb.append("or");
                    }
                }
                //否则 不拼接
            } else {
                AnalyzeBean child = JSON.parseObject(o.get(0).toString(), AnalyzeBean.class);
                String field = child.getField();
                UserDataModelMapping byUmNamePath = userDataModelMappingRepService.findByUmNamePath(field);
                sb.append(byUmNamePath.getDmNamePath())
                        .append(child.getExp())
                        .append(child.getValues())
                        .append(child.getUnit());
                sb.append(")");
            }
            sb.append(")");
            if (i != size - 1) {
                sb.append("and");
            }
            stringBuffer.append(sb);
        }
        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

    public String analyzeCollection(Object str) {

        JSONArray expressions = (JSONArray) str;
        int size = expressions.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            StringBuffer sb = new StringBuffer("(");
            JSONArray o = (JSONArray) expressions.get(i);
            //如果o。size大于1  拼接or
            if (o.size() > 1) {
                for (int j = 0; j < o.size(); j++) {
                    AnalyzeBean child = JSON.parseObject(o.get(j).toString(), AnalyzeBean.class);
                    String field = child.getField();
                    UserDataModelMapping byUmNamePath = userDataModelMappingRepService.findByUmNamePath(field);
                    sb.append(byUmNamePath.getDmNamePath())
                            .append(child.getExp())
                            .append(child.getValues())
                            .append(child.getUnit());
                    if (j != o.size() - 1) {
                        sb.append("or");
                    }
                }
                //否则 不拼接
            } else {
                AnalyzeBean child = JSON.parseObject(o.get(0).toString(), AnalyzeBean.class);
                String field = child.getField();
                UserDataModelMapping byUmNamePath = userDataModelMappingRepService.findByUmNamePath(field);
                sb.append(byUmNamePath.getDmNamePath())
                        .append(child.getExp())
                        .append(child.getValues())
                        .append(child.getUnit());
                sb.append(")");
            }
            if (i != size - 1) {
                sb.append("and");
            }
            stringBuffer.append(sb);
        }
        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
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
            sb.append(ObjectUtils.flagObj(byUmNamePath.getDmNamePath()))
                    .append(ObjectUtils.flagObj(child.getExp()))
                    .append(ObjectUtils.flagObj(child.getValues()))
                    .append(ObjectUtils.flagObj(child.getUnit()));

            if (i != size - 1) {
                sb.append("and");
            }
        }
        sb.append(")");
        return sb.toString();
    }


}
