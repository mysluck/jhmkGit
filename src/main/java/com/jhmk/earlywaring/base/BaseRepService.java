package com.jhmk.earlywaring.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

/**
 * mycat 数据库访问服务接口类
 * 主要用于使得repService可替换BaseEntityController类中的PagingAndSortingRepository
 *
 * @author xiaochunbang
 */
public class BaseRepService<T, ID extends Serializable> {

    public Page<T> findAll(Pageable var1) {
        return null;
    }

    public Page<T> findAll(Specification<T> var1, Pageable var2) {
        return null;
    }

    public T findOne(ID var1) {
        return null;
    }

    public <S extends T> S save(S var1) {
        return null;
    }

    public <S extends T> Iterable<S> save(Iterable<S> var1) {
        return null;
    }

    public void delete(ID var1) {

    }
}
