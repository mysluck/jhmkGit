package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.base.BaseRepository;
import com.jhmk.earlywaring.entity.SmRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface SmRoleRepository extends PagingAndSortingRepository<SmRole, String>, JpaSpecificationExecutor<SmRole>, BaseRepository<SmRole> {
    SmRole findByRoleId(String roleId);

    @Query("select r from SmRole r where r.orgId like ?1 and r.roleIsvalid = ?2")
    Set<SmRole> findRoleByOrgId(String orgId, String roleIsvalid);

    @Query("select r.orgId from SmRole r where r.orgId like ?1 and r.roleIsvalid = ?2")
    String getOneRoleByUserId(String username);
}
