package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmHosptailLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SmHosptailLogRepository extends PagingAndSortingRepository<SmHosptailLog, Integer>, JpaSpecificationExecutor<SmHosptailLog> {




    @Query("select distinct (d.doctorId)from SmHosptailLog d ")
    long getDistinctDoctorIdCount();


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
    @Query("select l from  SmHosptailLog l  where l.deptCode=?1 and l.createTime>=?2 and l.createTime<=?3 ")
    List<SmHosptailLog> getAllByDeptAndYear(String deptCode, Date startTime, Date endTime);

    /**
     * 查询部门疾病触发统计
     *
     * @param deptCode
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    @Query("select l.diagnosisName,count(1) from  SmHosptailLog l  where l.deptCode=?1  and l.createTime>=?2 and l.createTime<=?3 group by l.diagnosisName ")
// limit 10
    List<Object[]> getCountByDiagnosisNameAndDeptCode(String deptCode, Date startTime, Date endTime, Pageable pageable);


    /**
     * 统计时间范围内所有触发规则的信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHosptailLog l  where l.createTime>=?1 and l.createTime<=?2 ")
    List<SmHosptailLog> getDeptCountByYear(Date startTime, Date endTime);


    @Query("select distinct (d.deptCode)from SmHosptailLog d ")
    List<String> getCountByDistinctDeptCode();


    @Query("select distinct (d.doctorId),d.doctorName from SmHosptailLog d ")
    List<SmHosptailLog> getCountByDistinctDoctorId();


}
