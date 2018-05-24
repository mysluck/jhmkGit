package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.SmDepts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmDeptsRepository extends JpaRepository<SmDepts, String>, JpaSpecificationExecutor<SmDepts> {
    @Query("select distinct (d.deptCode),d.deptName from SmDepts d ")
    List<SmDepts>findDistinctByDeptCode();
}
