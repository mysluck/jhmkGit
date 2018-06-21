package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.SmUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmUsersRepService extends BaseRepService<SmUsers, String> {


    @Autowired
    SmUsersRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmUsers save(SmUsers user) {
        return repository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmUsers> save(List<SmUsers> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmUsers user) {
        repository.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmUsers> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmUsers findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmUsers> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmUsers> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmUsers> findAll(Specification<SmUsers> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int setPasswordFor(String password, String id) {
        return repository.setUserPasswordFor(password, id);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public SmUsers findByUserIdAndUserPwd(String userId, String userPwd) {
        return repository.findByUserIdAndUserPwd(userId, userPwd);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public long count() {
        return repository.count();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<SmUsers> findByUserName(String username) {
        return repository.findByUserName(username);
    }

}