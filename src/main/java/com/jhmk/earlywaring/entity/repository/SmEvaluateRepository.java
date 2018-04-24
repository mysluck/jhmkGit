package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.base.BaseRepository;
import com.jhmk.earlywaring.entity.SmEvaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SmEvaluateRepository extends JpaRepository<SmEvaluate, Integer>,BaseRepository<SmEvaluate>{
    //删除
    @Modifying
    @Query("update SmEvaluate u set u.status = ?1 where u.id = ?2")
    int setStatus(String status, String id);


}
