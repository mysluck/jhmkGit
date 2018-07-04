package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmHospitalLog;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmShowLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface SmShowLogRepository extends PagingAndSortingRepository<SmShowLog, Integer>, JpaSpecificationExecutor<SmShowLog> {

    SmShowLog findFirstByDoctorIdAndPatientIdAndRuleId(String doctorId, String patientId, String ruleId);

    List<SmShowLog> findByDoctorIdAndRuleStatus(String doctorId, int ruleStatus);

    List<SmShowLog> findByDoctorIdAndPatientId(String doctorId, String patientId);

    @Modifying
    @Query("update SmShowLog l set l.ruleStatus = ?1 where l.id = ?2")
    int update(int ruleStatus, int id);

    SmShowLog findFirstByDoctorIdAndPatientIdAndItemNameAndTypeAndStat(String doctorId, String patientId, String itemName, String type, String stat);


}
