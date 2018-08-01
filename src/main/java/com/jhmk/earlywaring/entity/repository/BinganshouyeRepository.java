package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.rule.BasicInfo;
import com.jhmk.earlywaring.entity.rule.Binganshouye;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BinganshouyeRepository extends PagingAndSortingRepository<Binganshouye, Integer>, JpaSpecificationExecutor<Binganshouye> {

    @Query("select b  from  Binganshouye b where b.patient_id = ?1 and b.visit_id = ?2")
    Binganshouye findByPatient_idAndVisit_id(String patient_id, String visit_id);
}
