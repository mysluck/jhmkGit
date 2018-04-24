package com.jhmk.earlywaring.controller;


import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public AtResponse roleList(@RequestParam Map<String, Object> reqParams) {
        return super.listData(reqParams, roleRepository, "roleId");
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public AtResponse add(@ModelAttribute SmRole role) {
        //角色机构，录入时选择
        role.setRoleUpdatedby(getUserId());
        role.setRoleUpdatedtime(new Timestamp(System.currentTimeMillis()));
        return super.add(role, roleRepository);
    }


    @RequestMapping(value = "/editor")
    @ResponseBody
    public AtResponse editor(@ModelAttribute SmRole role) {
        role.setRoleUpdatedby(getUserId());
        role.setRoleUpdatedtime(new Timestamp(System.currentTimeMillis()));
        return super.editSave(role, role.getRoleId(), roleRepository);
    }

    @RequestMapping(value = "/view")
    @ResponseBody
    public AtResponse viewDetail(@RequestParam String roleId) {
        SmRole role = roleRepository.findOne(roleId);
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        Map<String, Object> params = new HashMap<>();
        params.put("role", role);
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public AtResponse delete(@RequestParam String roleId) {
        SmRole role = roleRepository.findOne(roleId);
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        try {
            roleRepository.delete(role);
            resp.setData("删除成功！");
            resp.setResponseCode(ResponseCode.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setData("删除失败！");
            resp.setResponseCode(ResponseCode.INERERROR1);
        }
        return resp;
    }
}
