package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.entity.repository.service.RuyuanjiluRepService;
import com.jhmk.earlywaring.entity.repository.service.YizhunRepService;
import com.jhmk.earlywaring.entity.rule.Rule;
import com.jhmk.earlywaring.entity.rule.Ruyuanjilu;
import com.jhmk.earlywaring.entity.rule.Yizhu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 14:18
 */

@Service
public class RuyuanjiluService {
    @Autowired
    RuyuanjiluRepService ruyuanjiluRepService;

    @Transactional
    public void saveAndFlush(Rule rule) {
        String patient_id = rule.getPatient_id();
        String visit_id = rule.getVisit_id();
        List<Ruyuanjilu> allByPatientIdAndVisitId = ruyuanjiluRepService.findAllByPatientIdAndVisitId(patient_id, visit_id);


        if (allByPatientIdAndVisitId != null&&allByPatientIdAndVisitId.size()>0) {
            ruyuanjiluRepService.delete(allByPatientIdAndVisitId);
        }
        Ruyuanjilu ruyuanjilu = rule.getRuyuanjilu();
        if (ruyuanjilu != null ) {
            ruyuanjiluRepService.save(ruyuanjilu);
        }
    }
}
