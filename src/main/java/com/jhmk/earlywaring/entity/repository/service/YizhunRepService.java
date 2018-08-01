package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.YizhuRepository;
import com.jhmk.earlywaring.entity.rule.Yizhu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YizhunRepService extends BaseRepService<Yizhu, Integer> {
    @Autowired
    YizhuRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Yizhu save(Yizhu yizhu) {
        return repository.save(yizhu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Yizhu> save(List<Yizhu> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Yizhu Yizhu) {
        repository.delete(Yizhu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Yizhu> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Yizhu findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Yizhu> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Yizhu> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public List<Yizhu> findAllByPatientIdAndVisitId(String patient_id, String visit_id) {
        return repository.findAllByPatientIdAndVisitId(patient_id, visit_id);
    }

}
