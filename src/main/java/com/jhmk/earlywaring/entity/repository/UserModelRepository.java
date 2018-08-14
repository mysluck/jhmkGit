package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.UserModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserModelRepository extends PagingAndSortingRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {
    /**
     * 根据父id和医院名称查询
     *
     * @param pid   父id
     * @return
     */

    List<UserModel> findByUmParentId(Integer pid );

    /**
     * @param hname 医院名称
     * @return
     */

    /**
     * @param umname 字典名称
     * @return
     */
    List<UserModel> findByUmName(String umname);


}
