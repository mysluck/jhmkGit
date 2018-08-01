package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.rule.LogMapping;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LogMappingRepository extends PagingAndSortingRepository<LogMapping, Integer>, JpaSpecificationExecutor<LogMapping> {

    List<LogMapping> findAllByLogId(int logId);
}
