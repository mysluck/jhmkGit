//package com.jhmk.earlywaring.entity.repository.service;
//
//import com.jhmk.earlywaring.base.BaseRepService;
//import com.jhmk.earlywaring.entity.SmMapping;
//import com.jhmk.earlywaring.entity.SmMapping;
//import com.jhmk.earlywaring.entity.repository.SmMappingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class SmMappingRepService extends BaseRepService<SmMapping, Integer> {
//    @Autowired
//    SmMappingRepository repository;
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public SmMapping save(SmMapping smMapping) {
//        return repository.save(smMapping);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public Iterable<SmMapping> save(List<SmMapping> list) {
//        return repository.save(list);
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void delete(Integer id) {
//        repository.delete(id);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void delete(SmMapping smMapping) {
//        repository.delete(smMapping);
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void delete(List<SmMapping> list) {
//        repository.delete(list);
//    }
//
//    @Override
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public SmMapping findOne(Integer id) {
//        return repository.findOne(id);
//    }
//
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public Iterable<SmMapping> findAll() {
//        return repository.findAll();
//    }
//
//
//    @Override
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public Page<SmMapping> findAll(Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    public List<SmMapping> findByKey(String key) {
//        return repository.findByKey(key);
//    }
//
//}
