package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmHospitalLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SmHospitalLogRepository extends PagingAndSortingRepository<SmHospitalLog, Integer>, JpaSpecificationExecutor<SmHospitalLog> {




    @Query("select distinct (d.doctorId)from SmHospitalLog d ")
    long getDistinctDoctorIdCount();


    @Override
    long count();

    long countByWarnSource(String warnSource);

    /**
     * 根据部门 触发预警状态 日期 查询
     *
     * @param deptCode
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHospitalLog l  where l.deptCode=?1 and l.createTime>=?2 and l.createTime<=?3 ")
    List<SmHospitalLog> getAllByDeptAndYear(String deptCode, Date startTime, Date endTime);

    /**
     * 查询部门疾病触发统计
     *
     * @param deptCode
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l.diagnosisName,count(1) from  SmHospitalLog l  where l.deptCode=?1  and l.createTime>=?2 and l.createTime<=?3 group by l.diagnosisName ")
// limit 10
    List<Object[]> getCountByDiagnosisNameAndDeptCode(String deptCode, Date startTime, Date endTime);


    /**
     * 统计时间范围内所有触发规则的信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHospitalLog l  where l.createTime>=?1 and l.createTime<=?2 ")
    List<SmHospitalLog> getDeptCountByYear(Date startTime, Date endTime);


    @Query("select distinct (d.deptCode)from SmHospitalLog d ")
    List<String> getCountByDistinctDeptCode();


    @Query("select distinct (d.doctorId),d.doctorName from SmHospitalLog d ")
    List<SmHospitalLog> getCountByDistinctDoctorId();


}
