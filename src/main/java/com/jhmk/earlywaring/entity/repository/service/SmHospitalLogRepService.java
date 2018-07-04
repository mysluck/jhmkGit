package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmHospitalLog;
import com.jhmk.earlywaring.entity.repository.SmHospitalLogRepository;
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
public class SmHospitalLogRepService extends BaseRepService<SmHospitalLog, Integer> {


    @Autowired
    SmHospitalLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmHospitalLog save(SmHospitalLog SmHospitalLog) {
        return repository.save(SmHospitalLog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmHospitalLog> save(List<SmHospitalLog> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmHospitalLog SmHospitalLog) {
        repository.delete(SmHospitalLog);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmHospitalLog> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmHospitalLog findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmHospitalLog> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmHospitalLog> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmHospitalLog> findAll(Specification<SmHospitalLog> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHospitalLog> findAll(Specification specification) {
        return repository.findAll(specification);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHospitalLog> getAllByDeptAndYear(String deptId, Date startTime, Date endTime) {
        return repository.getAllByDeptAndYear(deptId, startTime, endTime);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Object[]> getCountByDiagnosisNameAndDeptCode(String deptId, Date startTime, Date endTime) {
        return repository.getCountByDiagnosisNameAndDeptCode(deptId, startTime, endTime);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHospitalLog> getDeptCountByYear(Date startTime, Date endTime) {
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
    public List<SmHospitalLog> getCountByDistinctDoctorId() {
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