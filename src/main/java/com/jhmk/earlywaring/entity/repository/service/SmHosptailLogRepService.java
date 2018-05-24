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


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getAllByDeptAndYear(String deptId, String alarmStatus, Date startTime, Date endTime) {
        return repository.getAllByDeptAndYear(deptId, alarmStatus, startTime, endTime);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int getCountAllByAlarmCodeAndData(int alarmCode, String alarmStatus, Date startTime, Date endTime) {
        return repository.getCountAllByAlarmCodeAndData(alarmCode, alarmStatus, startTime, endTime);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int getCountAllByAlarmCode(String alarmCode, String alarmStatus) {
        return repository.getCountAllByAlarmCode(alarmCode, alarmStatus);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int countAllByDeptIdAndAlarmStatus(int deptId, String alarmStatus) {
        return repository.countAllByDeptIdAndAlarmStatus(deptId, alarmStatus);

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Object[]> getCountByAffirmSicknessAndDeptId(String deptId, String alarmStatus, Date startTime, Date endTime, Pageable pageable) {
        return repository.getCountByAffirmSicknessAndDeptId(deptId, alarmStatus, startTime, endTime, pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getDeptCountByYear(String alarmStatus, Date startTime, Date endTime) {
        return repository.getDeptCountByYear(alarmStatus, startTime, endTime);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getAllByDeptIdAndYear(int deptId, String alarmStatus, Date startTime, Date endTime) {
        return repository.getAllByDeptIdAndYear(deptId, alarmStatus, startTime, endTime);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmHosptailLog> getCountByDistinctDeptId() {
        return repository.getCountByDistinctDeptId();
    }

}