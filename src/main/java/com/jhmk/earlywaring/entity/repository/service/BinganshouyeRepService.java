package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.BasicInfoRepository;
import com.jhmk.earlywaring.entity.repository.BinganshouyeRepository;
import com.jhmk.earlywaring.entity.rule.Binganshouye;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BinganshouyeRepService extends BaseRepService<Binganshouye, Integer> {
    @Autowired
    BinganshouyeRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Binganshouye save(Binganshouye binganshouye) {
        return repository.save(binganshouye);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Binganshouye> save(List<Binganshouye> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Binganshouye Binganshouye) {
        repository.delete(Binganshouye);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Binganshouye> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Binganshouye findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Binganshouye> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Binganshouye> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public Binganshouye findByPatient_idAndVisit_id(String patient_id, String visit_id) {
        return repository.findByPatient_idAndVisit_id(patient_id, visit_id);
    }

}
