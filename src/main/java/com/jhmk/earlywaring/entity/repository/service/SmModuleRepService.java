package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmModule;
import com.jhmk.earlywaring.entity.repository.SmModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmModuleRepService extends BaseRepService<SmModule, String> {


    @Autowired
    SmModuleRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmModule save(SmModule role) {
        return repository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmModule> save(List<SmModule> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmModule role) {
        repository.delete(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmModule> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmModule findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmModule> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmModule> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmModule> findAll(Specification<SmModule> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmModule> findByModCodeIn(List<String> moduleList) {
        return repository.findByModCodeIn(moduleList);
}
}