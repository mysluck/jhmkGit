package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.NonExistentUserlog;
import com.jhmk.earlywaring.entity.NonExistentUserlog;
import com.jhmk.earlywaring.entity.repository.NonExistentUserlogRepository;
import com.jhmk.earlywaring.entity.repository.NonExistentUserlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NonExistentUserlogRepService extends BaseRepService<NonExistentUserlog, String> {
    @Autowired
    NonExistentUserlogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public NonExistentUserlog save(NonExistentUserlog nonExistentUserlog) {
        return repository.save(nonExistentUserlog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<NonExistentUserlog> save(List<NonExistentUserlog> list) {
        return repository.save(list);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(NonExistentUserlog NonExistentUserlog) {
        repository.delete(NonExistentUserlog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<NonExistentUserlog> list) {
        repository.delete(list);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public NonExistentUserlog findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<NonExistentUserlog> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<NonExistentUserlog> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


}
