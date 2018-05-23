package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.UserDataModelMapping;
import com.jhmk.earlywaring.entity.repository.UserDataModelMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDataModelMappingRepService extends BaseRepService<UserDataModelMapping, Integer> {
    @Autowired
    UserDataModelMappingRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDataModelMapping save(UserDataModelMapping user) {
        return repository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<UserDataModelMapping> save(List<UserDataModelMapping> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(UserDataModelMapping user) {
        repository.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<UserDataModelMapping> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserDataModelMapping findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<UserDataModelMapping> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<UserDataModelMapping> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserDataModelMapping findByUmNamePath(String umNamePath) {
        return repository.findByUmNamePath(umNamePath);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserDataModelMapping findByDmNamePath(String dmNamePath) {
        return repository.findByDmNamePath(dmNamePath);
    }


}
