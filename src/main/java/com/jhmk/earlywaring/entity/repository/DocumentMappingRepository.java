package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.DocumentMapping;
import com.jhmk.earlywaring.entity.rule.BasicInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DocumentMappingRepository extends PagingAndSortingRepository<DocumentMapping, Integer>, JpaSpecificationExecutor<DocumentMapping> {

    /**
     * 用英文名查询中文名
     * @param urlPath
     * @return
     */
    DocumentMapping findFirstByUrlPath(String urlPath);
}
