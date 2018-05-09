///**
// * Copyright (c) 2017. 北京江融信科技有限公司 版权所有
// * Created on 17/5/9.
// */
//
//package com.jhmk.earlywaring.record;
//
//import com.jhmk.earlywaring.util.EntityUtil;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.lang.reflect.Method;
//
///**
// * Created by wangshuguan on 17/5/9.
// * 检查参数是否更新/保存,如果有则更新缓存数据
// */
//@Aspect
//@Service
//public class ParamsModifyRecordAop {
//
//    @Autowired
//    private ParamChangeHisHelper hisHelper;
//
//    @Autowired
//    EntityUtil entityUtil;
//
//    @Autowired
//    @Qualifier("entityManagerFactory")
//    EntityManagerFactory entityManagerFactory;
//
////    @Autowired
////    @Qualifier("entityManager")
////    EntityManager entityManager;
//
//    private static final Logger logger = LoggerFactory.getLogger(ParamsModifyRecordAop.class);
//
////    @Pointcut("execution(* jrx.anytxn.entity.parameter.repository.service.*.*(..) )")
//    @Pointcut("execution(* com.jhmk.earlywaring.controller.RuleController(..) )")
//    public void paramPkgCut() {
//    }
//
//    @Pointcut("execution(* ruleMatch(..) )")
//    public void paramSaveMethodCut() {
//    }
//
//
//    @Pointcut("paramPkgCut() && (paramSaveMethodCut())")
//    public void paramChangeCut() {
//    }
//
//
//    @Around("paramChangeCut()")
//    public Object aroundAction(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        logger.info("interceptor around save method return");
//
//        Object objectBeforeModify = null;
//
//        Object[] vls = joinPoint.getArgs();
//
//        String hisClazz = null;
//
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        for (Object object : vls) {
//
//            String objClzzName = object.getClass().getName();
//
//            hisClazz = hisHelper.getHisClazzName(objClzzName);
//            Method method = entityUtil.getPriGetMethod(object);
//            if (method != null) {//获取到主键get方法才执行
//                Object idValue = method.invoke(object);
//                if (idValue != null) {//操作对象主键有值时，才执行保存历史数据动作，避免新增时有些对象主键还未生成查询数据报错
//                    objectBeforeModify = entityManager.find(object.getClass(), idValue);
//                }
//            }
//        }
//
//        //执行save逻辑
//        Object result = joinPoint.proceed();
//
//        entityManager.getTransaction().begin();
//
//        if (objectBeforeModify != null) {
//
//            try {
//
//                hisHelper.saveHisParams(objectBeforeModify, hisClazz, entityManager);
//
//                entityManager.flush();
//
//                entityManager.getTransaction().commit();
//
//                entityManager.close();
//
//            } catch (ClassNotFoundException e) {
//
//                e.printStackTrace();
//
//            } catch (IllegalAccessException e) {
//
//                e.printStackTrace();
//
//            } catch (InstantiationException e) {
//
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//}
