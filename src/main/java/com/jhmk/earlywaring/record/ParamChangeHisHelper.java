///**
// * Copyright (c) 2017. 北京江融信科技有限公司 版权所有
// * Created on 17/5/10.
// */
//
//package com.jhmk.earlywaring.record;
//
//import com.jhmk.earlywaring.util.ObjectUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
///**
// * @author wangshuguan
// */
//
//@Service
//public class ParamChangeHisHelper {
//
//    private static final String PARAM_HIS_PACKAGE = "jrx.anytxn.entity.parameter.entity.history";
//
//    @Autowired
//    private ObjectUtils objectUtils;
//
//    @Autowired
//    EntityManagerFactory entityManagerFactory;
//
//    public String getHisClazzName(String srcParamCalzz) {
//
//        //前提: 约定包名及实体命名规范
//        int beginIndex = srcParamCalzz.lastIndexOf(".");
//
//        String hisObjName = srcParamCalzz.substring(beginIndex) + "History";
//
//        String hisClazzName = PARAM_HIS_PACKAGE + hisObjName;
//
//        return hisClazzName;
//    }
//
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void saveHisParams(Object object, String entityName, EntityManager entityManager) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//
//        Object hisObj = Class.forName(entityName).newInstance();
//
//        objectUtils.copyValuesFromObject(object, hisObj);
//
//
//        try {
//            entityManager.persist(hisObj);
//
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//
//}
