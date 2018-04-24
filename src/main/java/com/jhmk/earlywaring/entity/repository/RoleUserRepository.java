package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.RoleUserId;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface RoleUserRepository extends PagingAndSortingRepository<RoleUser, RoleUserId> {


    RoleUser findByUser(SmUsers smUser);




}
