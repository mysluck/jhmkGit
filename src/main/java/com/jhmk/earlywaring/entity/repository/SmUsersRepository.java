package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmUsers;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmUsersRepository extends PagingAndSortingRepository<SmUsers, String>, JpaSpecificationExecutor<SmUsers>{

//    SmUsers findByUserIdAndStatus(String id, String status);

    //    //删除
//    @Modifying
//    @Query("update SmUser u set u.deleted = ?1 where u.userId = ?2")
//    int setUserDeleted(String deleted, String id);
//
    //修改密码
    @Modifying
    @Query("update SmUsers u set u.userPwd = ?1 where u.userId = ?2")
    int setUserPasswordFor(String password, String id);
}
