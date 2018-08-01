package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.rule.BasicInfo;
import com.jhmk.earlywaring.entity.rule.Binglizhenduan;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BinglizhenduanRepository extends PagingAndSortingRepository<Binglizhenduan, Integer>, JpaSpecificationExecutor<Binglizhenduan> {
    @Query("select b  from  Binglizhenduan b where b.patient_id = ?1 and b.visit_id = ?2")
    List<Binglizhenduan> findAllByPatient_idAndVisit_id(String patient_id, String visit_id);
}
