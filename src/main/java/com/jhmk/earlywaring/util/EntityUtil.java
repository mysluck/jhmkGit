/**
 * Copyright (c) 2017. 北京江融信科技有限公司 版权所有
 * Created on 17/5/10.
 */

package com.jhmk.earlywaring.util;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author  wangshuguan on 17/5/10.
 */
@Service
public class EntityUtil {

    /**
     * 未知实体类的情况下, 获取其主键的get方法
     * @param entity 必须是映射实体类
     * @return
     * @throws ClassNotFoundException
     */
    public Method getPriGetMethod(Object entity) throws ClassNotFoundException, NoSuchMethodException {

        Class clazz = Class.forName(entity.getClass().getName());

        Field[] targetFields = clazz.getDeclaredFields();

        String fieldName = null;

        Method getMethod = null;

        for (Field field : targetFields) {

            if (field.isAnnotationPresent(javax.persistence.Id.class)) {

                fieldName = field.getName();

                String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                getMethod = clazz.getMethod("get" + name);

                break;
            }

        }

        return getMethod;
    }

}
