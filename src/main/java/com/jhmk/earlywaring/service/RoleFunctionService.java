package com.jhmk.earlywaring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleFunctionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询组件操作
     *
     * @param roleId
     * @return
     */
    public List<Map<String, Object>> getComFunction(String roleId) {
        StringBuffer sqlstr = new StringBuffer();
        sqlstr.append("select sf.fun_component com,sf.fun_method fun from sm_role_function rf ");
        sqlstr.append("join sm_function sf on rf.prv_fun_id=sf.fun_id ");
        sqlstr.append("where rf.prv_ent_role = ?");
        List<String> params = new ArrayList<>();
        params.add(roleId);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlstr.toString(), params.toArray());
        return list;
    }



    /**
     * 查询组件操作
     * @return
     */
    public List<Map<String,Object>> getComFunctionAll(){
        StringBuffer sqlstr = new StringBuffer();
        sqlstr.append("select sf.fun_component com,sf.fun_method fun from sm_function sf");
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sqlstr.toString());
        return list;
    }

}
