package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.entity.repository.service.BingchengjiluRepService;
import com.jhmk.earlywaring.entity.repository.service.RuyuanjiluRepService;
import com.jhmk.earlywaring.entity.rule.Bingchengjilu;
import com.jhmk.earlywaring.entity.rule.Rule;
import com.jhmk.earlywaring.entity.rule.Ruyuanjilu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 14:18
 */

@Service
public class BingchengjiluService {
    @Autowired
    BingchengjiluRepService bingchengjiluRepService;

    @Transactional
    public void saveAndFlush(Rule rule) {
        String patient_id = rule.getPatient_id();
        String visit_id = rule.getVisit_id();
        Bingchengjilu byPatientIdAndVisitId = bingchengjiluRepService.findByPatient_idAndVisit_id(patient_id, visit_id);


        if (byPatientIdAndVisitId != null) {
            bingchengjiluRepService.delete(byPatientIdAndVisitId);
        }
        Bingchengjilu bingchengjilu = rule.getBingchengjilu();
        if (bingchengjilu != null ) {
            bingchengjiluRepService.save(bingchengjilu);
        }
    }
}
