package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmOrder;
import com.jhmk.earlywaring.entity.rule.BasicInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SmOrderRepository extends PagingAndSortingRepository<SmOrder, Integer>, JpaSpecificationExecutor<SmOrder> {

    @Query("select s.orderName from SmOrder s where s.orderNum = ?1")
    List<String> findAllByOrOrderNum(int num);
}
