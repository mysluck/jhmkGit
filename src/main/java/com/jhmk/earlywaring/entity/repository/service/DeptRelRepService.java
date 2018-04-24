package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.repository.DeptRelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptRelRepService extends BaseRepService<DeptRel, String> {
    @Autowired
    DeptRelRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public DeptRel save(DeptRel deptRel) {
        return repository.save(deptRel);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<DeptRel> save(List<DeptRel> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(DeptRel DeptRel) {
        repository.delete(DeptRel);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<DeptRel> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DeptRel findOne(String id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<DeptRel> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<DeptRel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DeptRel> findAllByDeptCodeOrWardCode(String code) {
        return repository.findAllByDeptCodeOrWardCode(code);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DeptRel> findDistinctByDeptCode() {
        return repository.findDistinctByDeptCode();
    }


}
