package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.entity.repository.service.BinglizhenduanRepService;
import com.jhmk.earlywaring.entity.repository.service.ShouyezhenduanRepService;
import com.jhmk.earlywaring.entity.rule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ziyu.zhou
 * @date 2018/7/30 14:06
 */
@Service
public class ZhenduanService {
    @Autowired
    BinglizhenduanRepService binglizhenduanRepService;
    @Autowired
    ShouyezhenduanRepService shouyezhenduanRepService;

    @Transactional
    public void saveAndFlush(Rule rule) {
        String patient_id = rule.getPatient_id();
        String visit_id = rule.getVisit_id();
        List<Binglizhenduan> binglizhenduanList = binglizhenduanRepService.findLessThanVisit_id(patient_id, visit_id);
        if (binglizhenduanList != null) {
            binglizhenduanRepService.delete(binglizhenduanList);
        }
        List<Binglizhenduan> binglizhenduan = rule.getBinglizhenduan();
        if (binglizhenduan != null) {
            binglizhenduanRepService.save(binglizhenduan);
        }
        List<Shouyezhenduan> shouyezhenduanList = shouyezhenduanRepService.findLessThanVisit_id(patient_id, visit_id);
        if (shouyezhenduanList != null) {
            shouyezhenduanRepService.delete(shouyezhenduanList);
        }
        List<Shouyezhenduan> shouyezhenduan = rule.getShouyezhenduan();
        if (shouyezhenduan != null) {
            shouyezhenduanRepService.save(shouyezhenduan);
        }
    }


}
