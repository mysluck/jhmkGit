package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmHosptailLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SmHosptailLogRepository extends PagingAndSortingRepository<SmHosptailLog, Integer>, JpaSpecificationExecutor<SmHosptailLog> {


    /**
     * 触发预警统计功能模块
     * 根据日期查询 科室预警或者住院预警次数
     *
     * @param alarmCode   1-科室预警 2-住院预警
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select count(1) from  SmHosptailLog l  where l.alarmCode=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4")
    int getCountAllByAlarmCodeAndData(int alarmCode, String alarmStatus, Date startTime, Date endTime);


    /**
     * 触发预警统计功能模块
     * 科室预警或者住院预警次数
     *
     * @param alarmCode   1-科室预警 2-住院预警
     * @param alarmStatus 1 触发警报 0 未触发
     * @return
     */
    @Query("select count(1) from  SmHosptailLog l  where l.alarmCode=?1 and l.alarmStatus=?2")
    int getCountAllByAlarmCode(String alarmCode, String alarmStatus);


    /**
     * 科室预警次数
     *
     * @param deptId      部门id
     * @param alarmStatus 预警状态 1 触发警报 0 未触发'
     * @return
     */
    int countAllByDeptIdAndAlarmStatus(int deptId, String alarmStatus);

    /**
     * 根据日期查询 科室预警次数
     *
     * @param deptId
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select count(1) from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4")
    int getCountAllByDeptAndData(int deptId, String alarmStatus, Date startTime, Date endTime);


    @Query("select count(1) from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 ")
    int getCountAllByData(int deptId, String alarmStatus);

    //todo 返回值问题
    @Query("select l.deptId,count(1) from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4 group by l.deptId")
    List<Map<String, Object>> getMapByDeptIdAndYear(int deptId, String status, Date startTime, Date endTim);


    /**
     * 总预警次数
     *
     * @param alarmStatus
     * @return
     */
    int countAllByAlarmStatus(String alarmStatus);


    /**
     * 根据部门 触发预警状态 日期 查询
     *
     * @param deptId
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4 ")
    List<SmHosptailLog> getAllByDeptAndYear(String deptId, String alarmStatus, Date startTime, Date endTime);

    /**
     * 查询部门疾病触发统计
     *
     * @param deptId
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @param pageable
     * @return
     */
    @Query("select l.affirmSickness,count(1) from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4 group by l.affirmSickness ")
// limit 10
    List<Object[]> getCountByAffirmSicknessAndDeptId(String deptId, String alarmStatus, Date startTime, Date endTime, Pageable pageable);


    /**
     * 统计时间范围内所有触发规则的信息
     *
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHosptailLog l  where l.alarmStatus=?1 and l.createTime>=?2 and l.createTime<=?3 ")
    List<SmHosptailLog> getDeptCountByYear(String alarmStatus, Date startTime, Date endTime);


    /**
     *
     * @param deptId
     * @param alarmStatus
     * @param startTime
     * @param endTime
     * @return
     */
    @Query("select l from  SmHosptailLog l  where l.deptId=?1 and l.alarmStatus=?2 and l.createTime>=?3 and l.createTime<=?4 group by l.affirmSickness ")
    List<SmHosptailLog> getAllByDeptIdAndYear(int deptId, String alarmStatus, Date startTime, Date endTime);


}
