package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.repository.SmDeptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmDeptsRepService extends BaseRepService<SmDepts, String> {


    @Autowired
    SmDeptsRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmDepts save(SmDepts SmDepts) {
        return repository.save(SmDepts);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmDepts> save(List<SmDepts> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmDepts SmDepts) {
        repository.delete(SmDepts);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmDepts> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmDepts findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmDepts> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmDepts> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }



}