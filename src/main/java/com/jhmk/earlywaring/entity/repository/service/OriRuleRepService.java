package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.OriRule;
import com.jhmk.earlywaring.entity.OriRule;
import com.jhmk.earlywaring.entity.repository.OriRuleRepository;
import com.jhmk.earlywaring.entity.repository.OriRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OriRuleRepService extends BaseRepService<OriRule, String> {
    @Autowired
    OriRuleRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OriRule save(OriRule OriRule) {
        return repository.save(OriRule);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<OriRule> save(List<OriRule> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(OriRule OriRule) {
        repository.delete(OriRule);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<OriRule> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OriRule findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<OriRule> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<OriRule> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
