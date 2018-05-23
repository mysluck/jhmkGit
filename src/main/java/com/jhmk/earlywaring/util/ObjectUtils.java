package com.jhmk.earlywaring.util;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author zzy
 */
@Service
public class ObjectUtils {

    /**
     * 拷贝实体字段value
     *
     * @param srcObj
     * @param targetObj
     * @return
     */
    public Object copyValuesFromObject(Object srcObj, Object targetObj) {
        Field[] targetFields = targetObj.getClass().getDeclaredFields();
        Field[] srcFields = srcObj.getClass().getDeclaredFields();
        for (Field field : srcFields) {
            for (Field field1 : targetFields) {

                if (field1.getName().equals(field.getName())) {
                    String name = field1.getName().substring(0, 1).toUpperCase() + field1.getName().substring(1);
                    Method getMethod = null;
                    Method setMethod = null;
                    try {

                        Class typeClass = field1.getType();
                        getMethod = srcObj.getClass().getMethod("get" + name);
                        setMethod = targetObj.getClass().getMethod("set" + name, typeClass);
                    } catch (Exception e) {
                        throw new RuntimeException("generate method error, method name is " + name, e);
                    }
                    try {
                        setMethod.invoke(targetObj, getMethod.invoke(srcObj));
                    } catch (Exception e) {
                        throw new RuntimeException("invoke set method error, method name is: " + name, e);
                    }

                }
                if ("modifyDate".equals(field1.getName())) {

                    String name = field1.getName().substring(0, 1).toUpperCase() + field1.getName().substring(1);
                    Method setMethod = null;
                    try {

                        Class typeClass = field1.getType();
                        String typeName = typeClass.getTypeName();
                        System.out.print(typeName);
                        if ("java.util.Date".equals(typeName)) {
                            setMethod = targetObj.getClass().getMethod("set" + name, typeClass);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException("generate method error, method name is " + name, e);
                    }
                    try {

                        setMethod.invoke(targetObj, new Date());
                    } catch (Exception e) {
                        throw new RuntimeException("invoke set method error, method name is: " + name, e);
                    }

                }
            }
        }

        return targetObj;

    }

    public static void setListValue(List<Map<String, Object>> mapList, List<Object> objList) {

        for (Map<String, Object> map : mapList) {
            Object object = new Object();
            setValue(map, object);
            objList.add(object);
        }
    }


    /**
     * 将单个map转换为object
     *
     * @param map
     * @param thisObj
     */
    public static void setValue(Map map, Object thisObj) {
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            Object val = map.get(obj);
            setMethod(obj, val, thisObj);
        }
    }

    public static void setMethod(Object method, Object value, Object thisObj) {
        String definition = "set";
        Class c;
        try {
            c = Class.forName(thisObj.getClass().getName());
            String met = (String) method;
            met = met.trim();
            if (!met.substring(0, 1).equals(met.substring(0, 1).toUpperCase())) {
                met = met.substring(0, 1).toUpperCase() + met.substring(1);
            }
            if (!String.valueOf(method).startsWith(definition)) {
                met = definition + met;
            }
            Method m = c.getMethod(met, String.class);
            m.invoke(thisObj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝实体字段value 不拷贝空字段
     *
     * @param srcObj
     * @param targetObj
     * @return
     */
    public Object copyNotNullValuesFromObject(Object srcObj, Object targetObj) {
        Field[] targetFields = targetObj.getClass().getDeclaredFields();
        Field[] srcFields = srcObj.getClass().getDeclaredFields();
        for (Field field : srcFields) {
            for (Field field1 : targetFields) {
                if (field1.getName().equals(field.getName())) {
                    String name = field1.getName().substring(0, 1).toUpperCase() + field1.getName().substring(1);
                    Method getMethod = null;
                    Method setMethod = null;
                    try {
                        Class typeClass = field1.getType();
                        getMethod = srcObj.getClass().getMethod("get" + name);
                        setMethod = targetObj.getClass().getMethod("set" + name, typeClass);
                    } catch (Exception e) {
                        throw new RuntimeException("generate method error, method name is " + name, e);
                    }
                    try {
                        /**实体属性值
                         *
                         */
                        Object objValue = getMethod.invoke(srcObj);
                        if (objValue != null) {
                            setMethod.invoke(targetObj, getMethod.invoke(srcObj));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("invoke set method error, method name is: " + name, e);
                    }

                }
            }
        }

        return targetObj;

    }

//    类似于3目运算符

    public static String flagObj(Object str) {
        return (String) Optional.ofNullable(str)
                .orElse("");
    }
}
