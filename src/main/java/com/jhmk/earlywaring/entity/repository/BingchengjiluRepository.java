package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.rule.BasicInfo;
import com.jhmk.earlywaring.entity.rule.Bingchengjilu;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BingchengjiluRepository extends PagingAndSortingRepository<Bingchengjilu, Integer>, JpaSpecificationExecutor<Bingchengjilu> {
    @Query("select b  from  Bingchengjilu b where b.patient_id = ?1 and b.visit_id = ?2")

    Bingchengjilu findByPatient_idAndVisit_id(String patient_id, String visit_id);
}
