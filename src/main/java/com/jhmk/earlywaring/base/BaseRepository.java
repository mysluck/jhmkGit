package com.jhmk.earlywaring.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

public interface BaseRepository<T>{
    /**
     * 带条件分页查询
     *
     * @param specification
     * @param pageable
     * @return 分页
     */
    Page<T> findAll(Specification<T> specification, Pageable pageable);

//    T findOne(ID var1);
//
//    public Page<T> findAll(Pageable var1);
//
//    public <S extends T> S save(S var1);
//
//    public <S extends T> Iterable<S> save(Iterable<S> var1);
//
//    public void delete(ID var1);

}
