package com.jhmk.earlywaring.entity.repository;

import com.jhmk.earlywaring.entity.SmDepts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SmDeptsRepository extends JpaRepository<SmDepts, String>, JpaSpecificationExecutor<SmDepts> {

}
