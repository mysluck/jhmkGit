package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.NonExistentUserlog;
import com.jhmk.earlywaring.entity.OriRule;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OriRuleRepository extends PagingAndSortingRepository<OriRule, String> {
}
