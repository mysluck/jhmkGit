package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.UserDataModelMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataModelMappingRepository extends JpaRepository<UserDataModelMapping, Integer> {
    UserDataModelMapping findByUmNamePath(String umNamePath);

    List<UserDataModelMapping> findByDmNamePath(String dmNamePath);
}
