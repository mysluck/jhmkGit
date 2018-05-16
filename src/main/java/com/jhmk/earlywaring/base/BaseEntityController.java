package com.jhmk.earlywaring.base;

import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.OperateBean;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.model.WebPage;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseEntityController<P> extends BaseController {
    public final String LIST_DATA = "listData";
    public final String LIST_OPERATE = "operate";

    protected Class<P> entityClass;

    @Autowired(required = false)
    JpaSpecificationExecutor<P> jpaSpecificationExecutor;

    public BaseEntityController() {
        entityClass = (Class<P>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * List列表，并根据角色Id获取相应的操作权限
     * @param params
     * @param repository
     * @param sortField
     * @return
     */
    public AtResponse<Map<String, Object>> listDataWithAuth(@RequestParam Map<String, Object> params,
                                                            PagingAndSortingRepository repository, String sortField) {
        String pageStr = (String) params.get(WebPage.PAGE_NUM);
        int page = 0;
        if (pageStr != null && !"".equals(pageStr.trim())) {
            // Pageable页面从0开始计
            page = new Integer(pageStr) - 1;
        }

        WebPage webPage = new WebPage();
        Pageable pageable = new PageRequest(page, webPage.getPageSize(), new Sort(Sort.Direction.ASC, sortField));

        Page<P> intrPage = repository.findAll(pageable);
        List<P> intrList = intrPage.getContent();
        params.put(LIST_DATA, intrList);

        int currentPage = page + 1;
        // 当前页
        webPage.setPageNo(currentPage);
        // 总页数
        webPage.setTotalPageNum(intrPage.getTotalPages());
        // 总记录数
        webPage.setTotalCount(intrPage.getTotalElements());

        if (currentPage < intrPage.getTotalPages()) {
            webPage.setHasNext(true);
        }
        if (currentPage > 1) {
            webPage.setHasPre(true);
        }
        params.put(WebPage.WEB_PAGE, webPage);
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }


    /**
     * List列表，并根据角色Id获取相应的操作权限
     * @param params
     * @param repository
     * @param sortField
     * @param roleId
     * @return
     */
    public AtResponse<Map<String, Object>> listDataWithAuth(@RequestParam Map<String, Object> params,
                                                            BaseRepService repository, String sortField, String roleId) {
        String pageStr = (String) params.get(WebPage.PAGE_NUM);
        int page = 0;
        if (pageStr != null && !"".equals(pageStr.trim())) {
            // Pageable页面从0开始计
            page = new Integer(pageStr) - 1;
        }

        WebPage webPage = new WebPage();
        Pageable pageable = new PageRequest(page, webPage.getPageSize(), new Sort(Sort.Direction.ASC, sortField));

        Page<P> intrPage = repository.findAll(pageable);
        List<P> intrList = intrPage.getContent();
        params.put(LIST_DATA, intrList);

        int currentPage = page + 1;
        // 当前页
        webPage.setPageNo(currentPage);
        // 总页数
        webPage.setTotalPageNum(intrPage.getTotalPages());
        // 总记录数
        webPage.setTotalCount(intrPage.getTotalElements());

        if (currentPage < intrPage.getTotalPages()) {
            webPage.setHasNext(true);
        }
        if (currentPage > 1) {
            webPage.setHasPre(true);
        }
        params.put(WebPage.WEB_PAGE, webPage);

//        OperateBean operate = this.getOperateByRoleId(roleId);
//        params.put(LIST_OPERATE, operate);

        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }


    /**
     * List列表，并根据角色Id和业务机构id获取相应的操作权限
     * @param params
     * @param repository
     * @param sortField
     * @return
     */
    public AtResponse<Map<String, Object>> listDataWithAuthByOrganizeId(@RequestParam Map<String, Object> params,
                                                                        BaseRepService repository, String sortField) {
        String pageStr = (String) params.get(WebPage.PAGE_NUM);
        int page = 0;
        if (pageStr != null && !"".equals(pageStr.trim())) {
            // Pageable页面从0开始计
            page = new Integer(pageStr) - 1;
        }

        WebPage webPage = new WebPage();
        Pageable pageable = new PageRequest(page, webPage.getPageSize(), new Sort(Sort.Direction.DESC, sortField));

        Specification    sf= getWhereClause(params);

        //调用方法查询
        Page<P> intrPage = jpaSpecificationExecutor.findAll(sf,pageable);
        //设置查询数据
        List<P> intrList = intrPage.getContent();
        params.put(LIST_DATA, intrList);
        int currentPage = page + 1;
        // 当前页
        webPage.setPageNo(currentPage);
        // 总页数
        webPage.setTotalPageNum(intrPage.getTotalPages());
        // 总记录数
        webPage.setTotalCount(intrPage.getTotalElements());

        if (currentPage < intrPage.getTotalPages()) {
            webPage.setHasNext(true);
        }
        if (currentPage > 1) {
            webPage.setHasPre(true);
        }
        params.put(WebPage.WEB_PAGE, webPage);

//        OperateBean operate = this.getOperateByRoleId(roleId);
//        params.put(LIST_OPERATE, operate);

        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }




  public AtResponse<Map<String, Object>> listDataByMap(@RequestParam Map<String, Object> params,
                                                                        BaseRepService service, String sortField) {
        String pageStr = (String) params.get(WebPage.PAGE_NUM);
        int page = 0;
        if (pageStr != null && !"".equals(pageStr.trim())) {
            // Pageable页面从0开始计
            page = new Integer(pageStr) - 1;
        }

        WebPage webPage = new WebPage();
        Pageable pageable = new PageRequest(page, webPage.getPageSize(), new Sort(Sort.Direction.DESC, sortField));

        Specification    sf= getWhereClause(params);

        //调用方法查询
        Page<P> intrPage = jpaSpecificationExecutor.findAll(sf,pageable);
        Page<P> intrPage1 = service.findAll(sf,pageable);
        //设置查询数据
        List<P> intrList = intrPage.getContent();
        params.put(LIST_DATA, intrList);
        int currentPage = page + 1;
        // 当前页
        webPage.setPageNo(currentPage);
        // 总页数
        webPage.setTotalPageNum(intrPage.getTotalPages());
        // 总记录数
        webPage.setTotalCount(intrPage.getTotalElements());

        if (currentPage < intrPage.getTotalPages()) {
            webPage.setHasNext(true);
        }
        if (currentPage > 1) {
            webPage.setHasPre(true);
        }
        params.put(WebPage.WEB_PAGE, webPage);

//        OperateBean operate = this.getOperateByRoleId(roleId);
//        params.put(LIST_OPERATE, operate);

        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }



    public Specification<T> getWhereClause(Map<String,Object> params) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                //拼接传入参数
                if(params!=null){
                    for(String key:params.keySet()){
                        if(WebPage.PAGE_NUM.equals(key)){
                            continue;
                        }else{
                            Object value = params.get(key);
                            if(!StringUtils.isEmpty(value.toString())){
                                list.add(cb.equal(root.get(key),value));
                            }
                        }
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                list.toArray(p);
                return cb.and(p);

            }
        };
    }

    /**
     * list列表
     *
     * @param params
     * @param
     * @param sortField
     * @return
     */
    public AtResponse<Map<String, Object>> listData(@RequestParam Map<String, Object> params,
                                                    BaseRepService repository, String sortField) {
        String pageStr = (String) params.get(WebPage.PAGE_NUM);
        int page = 0;
        if (pageStr != null && !"".equals(pageStr.trim())) {
            // Pageable页面从0开始计
            page = new Integer(pageStr) - 1;
        }

        WebPage webPage = new WebPage();
        Pageable pageable = new PageRequest(page, webPage.getPageSize(), new Sort(Sort.Direction.DESC,sortField));

        Page<P> intrPage = repository.findAll(pageable);
        List<P> intrList = intrPage.getContent();
        params.put(LIST_DATA, intrList);

        int currentPage = page + 1;
        // 当前页
        webPage.setPageNo(currentPage);
        // 总页数
        webPage.setTotalPageNum(intrPage.getTotalPages());
        // 总记录数
        webPage.setTotalCount(intrPage.getTotalElements());

        if (currentPage < intrPage.getTotalPages()) {
            webPage.setHasNext(true);
        }
        if (currentPage > 1) {
            webPage.setHasPre(true);
        }
        params.put(WebPage.WEB_PAGE, webPage);

        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }

    /**
     * 查看
     *
     * @param id
     * @param
     * @return
     */
    public AtResponse viewData(String id, BaseRepService repository) {
        AtResponse<P> resp = new AtResponse(System.currentTimeMillis());
        P p = (P) repository.findOne(id);
        if(p==null){
            resp.setMessage("查无数据");
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            resp.setData(p);
            resp.setResponseCode(ResponseCode.OK);
        }
        return resp;
    }

    /**
     * 添加操作
     * @param p
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> add(P p ,PagingAndSortingRepository repository){
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        P pNew = (P) repository.save(p);
        if(pNew==null){
            resp.setMessage("添加失败,请重新添加");
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            resp.setMessage("添加成功！");
            resp.setResponseCode(ResponseCode.OK);
        }
        return resp;
    }
    /**
     * 添加操作
     * @param p
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> add(P p ,BaseRepService repository){
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        P pNew = (P) repository.save(p);
        if(pNew==null){
            resp.setMessage("添加失败,请重新添加");
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            resp.setMessage("添加成功！");
            resp.setResponseCode(ResponseCode.OK);
        }
        return resp;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> delete(String tableId ,PagingAndSortingRepository repository){
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        repository.delete(tableId);
        resp.setMessage("删除成功！");
        resp.setResponseCode(ResponseCode.OK);
        return resp;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> delete(String tableId ,BaseRepService repository){
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        repository.delete(tableId);
        resp.setMessage("删除成功！");
        resp.setResponseCode(ResponseCode.OK);
        return resp;
    }

    /**
     * 保存修改信息，并记录修改日志
     * @param p
     * @param id
     * @param
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> editSave(P p,Object id,PagingAndSortingRepository repository) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());

        P pNew = (P) repository.save(p);
        String message=null;
        if(pNew==null){
            message="更新失败,请重新保存";
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            message="更新成功";
            resp.setResponseCode(ResponseCode.OK);
        }

        resp.setMessage(message);
        return resp;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> editSave(P p,BaseRepService repository) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());

        P pNew = (P) repository.save(p);
        String message=null;
        if(pNew==null){
            message="更新失败,请重新保存";
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            message="更新成功";
            resp.setResponseCode(ResponseCode.OK);
        }

        resp.setMessage(message);
        return resp;
    }
    /**
     * 保存修改信息，并记录修改日志
     * @param p
     * @param id
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AtResponse<String> editSave(P p,Object id,BaseRepService repository) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        P pNew = (P) repository.save(p);
        String message=null;
        if(pNew==null){
            message="更新失败,请重新保存";
            resp.setResponseCode(ResponseCode.INERERROR);
        }else{
            message="更新成功";
            resp.setResponseCode(ResponseCode.OK);
        }

        resp.setMessage(message);
        return resp;
    }







    private P getOldObj(Object id,BaseRepService rpository){

        P p = (P) rpository.findOne((Serializable) id);
        return p;
    }
}
