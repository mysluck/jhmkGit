package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.UserDataModelMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataModelMappingRepository extends JpaRepository<UserDataModelMapping, Integer> {
    UserDataModelMapping findByUmNamePath(String umNamePath);
    UserDataModelMapping findByDmNamePath(String dmNamePath);
}
