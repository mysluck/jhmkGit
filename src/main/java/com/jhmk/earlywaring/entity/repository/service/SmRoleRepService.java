package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmRole;
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
public class SmRoleRepService extends BaseRepService<SmRole, String> {


    @Autowired
    SmRoleRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmRole save(SmRole role) {
        return repository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmRole> save(List<SmRole> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmRole role) {
        repository.delete(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmRole> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmRole findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmRole> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmRole> findAll(Specification<SmRole> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

}