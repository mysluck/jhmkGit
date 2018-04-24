package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmEvaluate;
import com.jhmk.earlywaring.entity.repository.SmEvaluateRepository;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmEvaluateRepService extends BaseRepService<SmEvaluate, Integer> {


    @Autowired
    SmEvaluateRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmEvaluate save(SmEvaluate role) {
        return repository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmEvaluate> save(List<SmEvaluate> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmEvaluate role) {
        repository.delete(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmEvaluate> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmEvaluate findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmEvaluate> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmEvaluate> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmEvaluate> findAll(Specification<SmEvaluate> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }


}