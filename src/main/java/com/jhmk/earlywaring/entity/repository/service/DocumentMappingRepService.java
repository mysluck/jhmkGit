package com.jhmk.earlywaring.entity.repository.service;

import com.jhmk.earlywaring.base.BaseRepService;
import com.jhmk.earlywaring.entity.DocumentMapping;
import com.jhmk.earlywaring.entity.repository.DocumentMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentMappingRepService extends BaseRepService<DocumentMapping, Integer> {
    @Autowired
    DocumentMappingRepository repository;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public DocumentMapping save(DocumentMapping basicInfo) {
        return repository.save(basicInfo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Iterable<DocumentMapping> save(List<DocumentMapping> list) {
        return repository.save(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(DocumentMapping DocumentMapping) {
        repository.delete(DocumentMapping);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(List<DocumentMapping> list) {
        repository.delete(list);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DocumentMapping findOne(Integer id) {
        return repository.findOne(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Iterable<DocumentMapping> findAll() {
        return repository.findAll();
    }


    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<DocumentMapping> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DocumentMapping findFirstByUrlPath(String urlPath) {
        return repository.findFirstByUrlPath(urlPath);
    }


}
