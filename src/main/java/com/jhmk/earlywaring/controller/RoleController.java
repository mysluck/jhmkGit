package com.jhmk.earlywaring.controller;


import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseEntityController<SmRole> {


    @Autowired
    SmRoleRepService roleRepository;


    @RequestMapping(value = "/list")
    @ResponseBody
    public void roleList(HttpServletResponse response, @RequestBody String params) {
        Map<String, Object> parse = (Map) JSON.parse(params);
        AtResponse<Map<String, Object>> resp = super.listData(parse, roleRepository, "roleId");
        wirte(response, resp);
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public void add(HttpServletResponse response, @RequestBody SmRole role) {
        //角色机构，录入时选择
        role.setRoleUpdatedby(getUserId());
        role.setRoleUpdatedtime(new Timestamp(System.currentTimeMillis()));
        AtResponse<String> resp = super.add(role, roleRepository);
        wirte(response, resp);
    }


    @RequestMapping(value = "/editor")
    @ResponseBody
    public void editor(HttpServletResponse response, @RequestBody SmRole role) {
        role.setRoleUpdatedby(getUserId());
        role.setRoleUpdatedtime(new Timestamp(System.currentTimeMillis()));
        AtResponse<String> resp = super.editSave(role, role.getRoleId(), roleRepository);
        wirte(response, resp);

    }

    @RequestMapping(value = "/view")
    @ResponseBody
    public void viewDetail(HttpServletResponse response, @RequestBody String params) {
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        Map<String, Object> parse = (Map) JSON.parse(params);
        Object roleId = parse.get("roleId");
        if (roleId == null) {
            resp.setMessage("用户角色不能为空");
            resp.setResponseCode(ResponseCode.INERERROR);
        } else {
            SmRole role = roleRepository.findOne(roleId.toString());
            Map<String, Object> param = new HashMap<>();
            param.put("role", role);
            resp.setResponseCode(ResponseCode.OK);
            resp.setData(param);
        }
        wirte(response, resp);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public void delete(HttpServletResponse response, @RequestBody String params) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        Map<String, Object> parse = (Map) JSON.parse(params);
        Object roleId = parse.get("roleId");
        if (roleId == null) {
            resp.setMessage("用户角色不能为空");
            resp.setResponseCode(ResponseCode.INERERROR);
        } else {
            SmRole role = roleRepository.findOne(roleId.toString());
            try {
                roleRepository.delete(role);
                resp.setData("删除成功！");
                resp.setResponseCode(ResponseCode.OK);
            } catch (Exception e) {
                e.printStackTrace();
                resp.setData("删除失败！");
                resp.setResponseCode(ResponseCode.INERERROR1);
            }
        }
        wirte(response, resp);
    }
}
