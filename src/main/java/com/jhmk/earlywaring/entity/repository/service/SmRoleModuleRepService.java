package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmRoleModule;
import com.jhmk.earlywaring.entity.SmRoleModule;
import com.jhmk.earlywaring.entity.repository.SmRoleModuleRepository;
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
public class SmRoleModuleRepService extends BaseRepService<SmRoleModule, Integer> {


    @Autowired
    SmRoleModuleRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmRoleModule save(SmRoleModule role) {
        return repository.save(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmRoleModule> save(List<SmRoleModule> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmRoleModule role) {
        repository.delete(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmRoleModule> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmRoleModule findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmRoleModule> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmRoleModule> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmRoleModule> findAll(Specification<SmRoleModule> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmRoleModule> findAllByRoleId(String roleId) {
        return repository.findAllByRoleId(roleId);
    }


}