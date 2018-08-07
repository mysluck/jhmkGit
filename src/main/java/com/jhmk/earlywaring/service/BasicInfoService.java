package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.entity.repository.service.BasicInfoRepService;
import com.jhmk.earlywaring.entity.rule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 13:57
 */

@Service
public class BasicInfoService {
    @Autowired
    BasicInfoRepService basicInfoRepService;

    /**
     * 添加、覆盖规则基本信息 如果存在 就执行先删除 在添加操作
     * @param rule
     */
    @Transactional
    public void saveAndFlush(Rule rule) {
        String patient_id = rule.getPatient_id();
        String visit_id = rule.getVisit_id();
        List<BasicInfo> lessThanVisit_id = basicInfoRepService.findLessThanVisit_id(patient_id, visit_id);
        if (lessThanVisit_id .size()>0) {
            basicInfoRepService.delete(lessThanVisit_id);
        }
        String pageSource = rule.getPageSource();
        String warnSource = rule.getWarnSource();
        String doctor_id = rule.getDoctor_id();
        String doctor_name = rule.getDoctor_name();
        String dept_code = rule.getDept_code();
        //基本信息
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setPatient_id(patient_id);
        basicInfo.setVisit_id(visit_id);
        basicInfo.setDept_name(dept_code);
        basicInfo.setDoctor_id(doctor_id);
        basicInfo.setPageSource(pageSource);
        basicInfo.setWarnSource(warnSource);
        basicInfo.setDoctor_name(doctor_name);
        basicInfoRepService.save(basicInfo);
    }

}
