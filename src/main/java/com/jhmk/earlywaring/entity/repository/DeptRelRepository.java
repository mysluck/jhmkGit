package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.DeptRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptRelRepository extends JpaRepository<DeptRel, String>, JpaSpecificationExecutor<DeptRel> {
    @Query("select r from DeptRel r where r.wardCode like ?1 or r.deptCode = ?1")
    List<DeptRel>findAllByDeptCodeOrWardCode(String code);

    @Query("select distinct (d.deptCode),d.deptName from DeptRel d ")
    List<DeptRel>findDistinctByDeptCode();

}
