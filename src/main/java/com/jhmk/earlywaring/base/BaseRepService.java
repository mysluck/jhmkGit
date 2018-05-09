package com.jhmk.earlywaring.base;

import com.jhmk.earlywaring.util.HttpHeadersUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collections;

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

    private HttpHeaders getHeader() {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders headers = HttpHeadersUtils.getHttpHeadersByCharsAndMed(Collections.singletonList(Charset.forName("UTF-8")),
                Collections.singletonList(MediaType.ALL));
        headers.setContentType(type);
        return headers;
    }
}
