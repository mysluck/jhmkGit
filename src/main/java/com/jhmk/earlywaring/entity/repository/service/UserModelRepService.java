package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.UserModel;
import com.jhmk.earlywaring.entity.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserModelRepService extends BaseRepService<UserModel, Integer> {


    @Autowired
    UserModelRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserModel save(UserModel user) {
        return repository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<UserModel> save(List<UserModel> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(UserModel user) {
        repository.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<UserModel> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserModel findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<UserModel> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<UserModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<UserModel> findAll(Specification<UserModel> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<UserModel>findByUmParentId(Integer pid){
        return repository.findByUmParentId(pid);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public  UserModel findByUmName(String umname){
        return repository.findByUmName(umname);
    }

}