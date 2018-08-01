package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.rule.LogMapping;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LogMappingRepository extends PagingAndSortingRepository<LogMapping, Integer>, JpaSpecificationExecutor<LogMapping> {

}
