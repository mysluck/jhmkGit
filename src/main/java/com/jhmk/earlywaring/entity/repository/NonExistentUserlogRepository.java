package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.NonExistentUserlog;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.RoleUserId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NonExistentUserlogRepository extends PagingAndSortingRepository<NonExistentUserlog, Integer> {
}
