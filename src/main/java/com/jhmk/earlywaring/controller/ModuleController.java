package com.jhmk.earlywaring.controller;

import com.jhmk.earlywaring.auth.AuthUserDetailsServiceImpl;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmModule;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.RoleModuleService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseEntityController<SmModule> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);


    @Autowired
    SmRoleRepService smRoleRepService;
    @Autowired
    RoleModuleService roleModuleService;

    @PostMapping("/moduleList")
    @ResponseBody
    public void moduleList(HttpServletResponse response, @RequestBody String roleId) {
        SmRole one = smRoleRepService.findOne(roleId);
        AtResponse atResponse = new AtResponse();
        if (one == null) {
            atResponse.setResponseCode(ResponseCode.INERERROR);
            logger.info("角色为空，请管理员添加角色后访问" + roleId);
            wirte(response, atResponse);
        } else {
            List<SmModule> modules = roleModuleService.queryComponetByRole(roleId);
            atResponse.setResponseCode(ResponseCode.OK);
            wirte(response, modules);

        }
    }


    /**
     * 角色对应功能
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public void list(HttpServletResponse response) {
        //获取当前角色
        AtResponse<List> atResponse = new AtResponse();
        String currentRole = getCurrentRole();
        List<SmModule> modules = roleModuleService.queryComponetByRole(currentRole);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setData(modules);
        wirte(response, atResponse);
    }
}
