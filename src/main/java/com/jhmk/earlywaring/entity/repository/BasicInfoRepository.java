package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.rule.BasicInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface BasicInfoRepository extends PagingAndSortingRepository<BasicInfo, Integer>, JpaSpecificationExecutor<BasicInfo> {

    @Query("select b from BasicInfo b where b.patient_id = ?1  and b.visit_id = ?2 ")
    BasicInfo findByPatient_idAndVisit_id(String patient_id, String visit_id);
}
