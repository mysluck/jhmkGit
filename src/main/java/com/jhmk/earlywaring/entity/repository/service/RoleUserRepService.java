package com.jhmk.earlywaring.entity.repository.service;


import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.RoleUserId;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author  zzy on 2017/4/20.
 */
@Service
public class RoleUserRepService extends BaseRepService<RoleUser,String> {
    @Autowired
    RoleUserRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleUser save(RoleUser roleUser) {
        return repository.save(roleUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<RoleUser> save(List<RoleUser> list) {
        return repository.save(list);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(RoleUserId roleUserId) {
        repository.delete(roleUserId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<RoleUser> list) {
        repository.delete(list);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(RoleUser roleUser) {
        repository.delete(roleUser);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RoleUser findOne(RoleUserId id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<RoleUser> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<RoleUser> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public RoleUser findByUser(SmUsers smUser){
        return repository.findByUser(smUser);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<RoleUser> findAll(List<RoleUserId> list) {
        return repository.findAll(list);
    }

//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public List<RoleUser> getRoleUserByUserId(String userId) {
//        return repository.getRoleUserByUserId(userId);
//    }

}
