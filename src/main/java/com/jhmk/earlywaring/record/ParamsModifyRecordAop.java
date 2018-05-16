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
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
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
//    private ParamChangeHelper hisHelper;
//
//    @Autowired
//    EntityUtil entityUtil;
//
////    @Autowired
////    @Qualifier("entityManagerFactory")
////    EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    EntityManager entityManager;
//
//    private static final Logger logger = LoggerFactory.getLogger(ParamsModifyRecordAop.class);
//
//    //    @Pointcut("execution(* jrx.anytxn.entity.parameter.repository.service.*.*(..) )")
//    @Pointcut("execution(* com.jhmk.earlywaring.controller.RuleController.ruleMatch(..) )")
//    public void paramPkgCut() {
//    }
//
////    @Pointcut("execution(* ruleMatch(..) )")
////    public void paramSaveMethodCut() {
////    }
//
//
////    @Pointcut("paramPkgCut() && (paramSaveMethodCut())")
////    public void paramChangeCut() {
////    }
//
//
//    @Before("paramPkgCut()")
//    @Transactional
//    public void aroundAction(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        logger.info("interceptor around save method return");
//
//        //获取参数 json格式
//        Object[] vls = joinPoint.getArgs();
//
//        hisHelper.saveParams(vls, entityManager);
//
//
//    }
//
//}
