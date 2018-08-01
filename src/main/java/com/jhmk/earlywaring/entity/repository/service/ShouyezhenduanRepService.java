package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.ShouyezhenduanRepository;
import com.jhmk.earlywaring.entity.rule.Shouyezhenduan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShouyezhenduanRepService extends BaseRepService<Shouyezhenduan, Integer> {
    @Autowired
    ShouyezhenduanRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Shouyezhenduan save(Shouyezhenduan shouyezhenduan) {
        return repository.save(shouyezhenduan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Shouyezhenduan> save(List<Shouyezhenduan> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Shouyezhenduan Shouyezhenduan) {
        repository.delete(Shouyezhenduan);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Shouyezhenduan> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Shouyezhenduan findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Shouyezhenduan> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Shouyezhenduan> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public List<Shouyezhenduan> findAllByPatient_idAndVisit_id(String patient_id, String visit_id) {
        return repository.findAllByPatient_idAndVisit_id(patient_id, visit_id);
    }

}
