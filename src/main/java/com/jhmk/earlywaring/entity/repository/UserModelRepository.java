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
     * @param hname 医院名称
     * @return
     */

    List<UserModel> findByUmParentIdAndUmHospitalName(Integer pid, String hname);

    /**
     * @param hname 医院名称
     * @return
     */
    List<UserModel> findByUmHospitalName(String hname);

    /**
     * @param umname 字典名称
     * @param hname
     * @return
     */
    UserModel findByUmNameAndUmHospitalName(String umname, String hname);


}
