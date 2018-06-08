package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.repository.SmHosptailLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SmHosptailLogRepService extends BaseRepService<SmHosptailLog, Integer> {


    @Autowired
    SmHosptailLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmHosptailLog save(SmHosptailLog smHosptailLog) {
        return repository.save(smHosptailLog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmHosptailLog> save(List<SmHosptailLog> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmHosptailLog smHosptailLog) {
        repository.delete(smHosptailLog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmHosptailLog> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmHosptailLog findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmHosptailLog> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmHosptailLog> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmHosptailLog> findAll(Specification<SmHosptailLog> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> findAll(Specification specification) {
        return repository.findAll(specification);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getAllByDeptAndYear(String deptId, Date startTime, Date endTime) {
        return repository.getAllByDeptAndYear(deptId, startTime, endTime);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Object[]> getCountByDiagnosisNameAndDeptCode(String deptId, Date startTime, Date endTime, Pageable pageable) {
        return repository.getCountByDiagnosisNameAndDeptCode(deptId, startTime, endTime, pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getDeptCountByYear(Date startTime, Date endTime) {
        return repository.getDeptCountByYear(startTime, endTime);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<String> getCountByDistinctDeptCode() {
        return repository.getCountByDistinctDeptCode();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long getDistinctDoctorIdCount() {
        return repository.getDistinctDoctorIdCount();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getCountByDistinctDoctorId() {
        return repository.getCountByDistinctDoctorId();
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long count() {
        return repository.count();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countByWarnSource(String warnSource) {
        return repository.countByWarnSource(warnSource);
    }

}