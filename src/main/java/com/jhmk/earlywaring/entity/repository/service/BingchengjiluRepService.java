package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.BingchengjiluRepository;
import com.jhmk.earlywaring.entity.rule.Binganshouye;
import com.jhmk.earlywaring.entity.rule.Bingchengjilu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BingchengjiluRepService extends BaseRepService<Bingchengjilu, Integer> {
    @Autowired
    BingchengjiluRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Bingchengjilu save(Bingchengjilu bingchengjilu) {
        return repository.save(bingchengjilu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Bingchengjilu> save(List<Bingchengjilu> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Bingchengjilu Bingchengjilu) {
        repository.delete(Bingchengjilu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Bingchengjilu> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Bingchengjilu findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Bingchengjilu> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Bingchengjilu> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Bingchengjilu findByPatient_idAndVisit_id(String patient_id, String visit_id) {
        return repository.findByPatient_idAndVisit_id(patient_id, visit_id);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Bingchengjilu> findLessThanVisit_id(String patient_id, String visit_id) {
        return repository.findLessThanVisit_id(patient_id,visit_id);
    }
}
