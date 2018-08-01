package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.RuyuanjiluRepository;
import com.jhmk.earlywaring.entity.rule.Ruyuanjilu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RuyuanjiluRepService extends BaseRepService<Ruyuanjilu, Integer> {
    @Autowired
    RuyuanjiluRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Ruyuanjilu save(Ruyuanjilu ruyuanjilu) {
        return repository.save(ruyuanjilu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<Ruyuanjilu> save(List<Ruyuanjilu> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Ruyuanjilu Ruyuanjilu) {
        repository.delete(Ruyuanjilu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<Ruyuanjilu> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Ruyuanjilu findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<Ruyuanjilu> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Ruyuanjilu> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public List<Ruyuanjilu> findAllByPatientIdAndVisitId(String patient_id, String visit_id) {
        return repository.findAllByPatientIdAndVisitId(patient_id, visit_id);
    }

}
