package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.entity.repository.service.YizhunRepService;
import com.jhmk.earlywaring.entity.rule.BasicInfo;
import com.jhmk.earlywaring.entity.rule.Rule;
import com.jhmk.earlywaring.entity.rule.Yizhu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 14:18
 */

@Service
public class YizhuService {
    @Autowired
    YizhunRepService yizhunRepService;

    @Transactional
    public void saveAndFlush(Rule rule) {
        String patient_id = rule.getPatient_id();
        String visit_id = rule.getVisit_id();
        List<Yizhu> allByPatientIdAndVisitId = yizhunRepService.findLessThanVisit_id(patient_id, visit_id);


        if (allByPatientIdAndVisitId != null&&allByPatientIdAndVisitId.size()>0) {
            yizhunRepService.delete(allByPatientIdAndVisitId);
        }
        List<Yizhu> yizhu = rule.getYizhu();
        if (yizhu != null && yizhu.size() > 0) {
            yizhunRepService.save(yizhu);
        }
    }
}
