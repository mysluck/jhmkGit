package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.SmShowLog;
import com.jhmk.earlywaring.entity.repository.SmShowLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmShowLogRepService extends BaseRepService<SmShowLog, Integer> {
    @Autowired
    SmShowLogRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SmShowLog save(SmShowLog user) {
        return repository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<SmShowLog> save(List<SmShowLog> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(SmShowLog user) {
        repository.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<SmShowLog> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmShowLog findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<SmShowLog> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmShowLog> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    //筛选列表
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<SmShowLog> findAll(Specification<SmShowLog> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmShowLog findFirstByDoctorIdAndPatientIdAndRuleIdAndVisitId(String doctorId, String patientId, String ruleId,String visitId) {
        return repository.findFirstByDoctorIdAndPatientIdAndRuleIdAndVisitId(doctorId, patientId, ruleId,visitId);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmShowLog> findByDoctorIdAndRuleStatus(String doctorId, int ruleStatus) {
        return repository.findByDoctorIdAndRuleStatus(doctorId, ruleStatus);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<SmShowLog> findByDoctorIdAndPatientIdAndVisitIdOrderByDateDesc(String doctorId, String patientId,String visitId) {
        return repository.findByDoctorIdAndPatientIdAndVisitIdOrderByDateDesc(doctorId, patientId,visitId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public int update(int ruleStatus, int id) {
        return repository.update(ruleStatus, id);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public SmShowLog findFirstByDoctorIdAndPatientIdAndItemNameAndTypeAndStatAndVisitId(String doctorId, String patientId, String itemName, String type, String stat,String visitId) {
        return repository.findFirstByDoctorIdAndPatientIdAndItemNameAndTypeAndStatAndVisitId(doctorId, patientId, itemName, type, stat,visitId);
    }

}
