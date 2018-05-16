/**
 * Copyright (c) 2017. 北京江融信科技有限公司 版权所有
 * Created on 17/5/10.
 */

package com.jhmk.earlywaring.record;

import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.entity.NonExistentUserlog;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.NonExistentUserlogRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Map;

/**
 * @author zzy
 */

@Service
public class ParamChangeHelper {


    @Autowired
    NonExistentUserlogRepService nonExistentUserlogRepService;
    @Autowired
    SmUsersRepService smUsersRepService;


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveParams(Object[] object, EntityManager entityManager) throws IllegalAccessException, InstantiationException {
        System.out.println(object[1].toString());
        if(object!=null){

        }
        Map<String, String> map = (Map<String, String>) object[1];
        String id = map.get("doctor_id");
        SmUsers one = smUsersRepService.findOne(id);
        if (one == null) {
            NonExistentUserlog nonExistentUserlog = new NonExistentUserlog();
            nonExistentUserlog.setUserId(id);
            nonExistentUserlogRepService.save(nonExistentUserlog);
        }
    }

}
