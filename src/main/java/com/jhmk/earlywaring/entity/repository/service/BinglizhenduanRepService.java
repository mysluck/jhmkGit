package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.BinglizhenduanRepository;
import com.jhmk.earlywaring.entity.rule.Bingchengjilu;
import com.jhmk.earlywaring.entity.rule.Binglizhenduan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BinglizhenduanRepService extends BaseRepService<Binglizhenduan, Integer> {
    @Autowired
    BinglizhenduanRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Binglizhenduan save(Binglizhenduan binglizhenduan) {
        return repository.save(binglizhenduan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Binglizhenduan> save(List<Binglizhenduan> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Binglizhenduan Binglizhenduan) {
        repository.delete(Binglizhenduan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Binglizhenduan> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Binglizhenduan findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Binglizhenduan> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Binglizhenduan> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Binglizhenduan> findAllByPatient_idAndVisit_id(String patient_id, String visit_id) {
        return repository.findAllByPatient_idAndVisit_id(patient_id, visit_id);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Binglizhenduan> findLessThanVisit_id(String patient_id, String visit_id) {
        return repository.findLessThanVisit_id(patient_id,visit_id);
    }
}
