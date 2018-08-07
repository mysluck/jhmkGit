package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmOrder;
import com.jhmk.earlywaring.entity.repository.SmOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmOrderRepService extends BaseRepService<SmOrder, Integer> {
    @Autowired
    SmOrderRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmOrder save(SmOrder SmOrder) {
        return repository.save(SmOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmOrder> save(List<SmOrder> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmOrder SmOrder) {
        repository.delete(SmOrder);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmOrder> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmOrder findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmOrder> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmOrder> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<String> findAllByOrOrderNum(int num) {
        return repository.findAllByOrOrderNum(num);
    }

}
