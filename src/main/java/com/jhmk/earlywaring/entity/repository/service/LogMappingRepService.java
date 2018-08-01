package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.repository.LogMappingRepository;
import com.jhmk.earlywaring.entity.rule.LogMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogMappingRepService extends BaseRepService<LogMapping, Integer> {
    @Autowired
    LogMappingRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LogMapping save(LogMapping logMapping) {
        return repository.save(logMapping);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<LogMapping> save(List<LogMapping> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(LogMapping LogMapping) {
        repository.delete(LogMapping);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<LogMapping> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LogMapping findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<LogMapping> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<LogMapping> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<LogMapping> findAllByLogId(int logId) {
        return repository.findAllByLogId(logId);

    }

}
