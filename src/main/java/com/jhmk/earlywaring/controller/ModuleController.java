package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmModule;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.repository.service.SmModuleRepService;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/warn/module")
public class ModuleController extends BaseEntityController<SmModule> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);


    @Autowired
    SmRoleRepService smRoleRepService;
    @Autowired
    RoleModuleService roleModuleService;
    @Autowired
    SmModuleRepService smModuleRepService;

    /**
     * 添加菜单
     *
     * @param response
     * @param smModule
     */
    @PostMapping("/add")
    @ResponseBody
    public void add(HttpServletResponse response, @RequestBody SmModule smModule) {
        smModule.setModUpdatedtime(new Timestamp(System.currentTimeMillis()));
        smModule.setModUpdatedby(getUserId());
        AtResponse<String> resp = super.add(smModule, smModuleRepService);
        wirte(response, resp);
    }

    /**
     * 删除菜单
     *
     * @param response
     */

    @PostMapping("/del")
    @ResponseBody
    public void del(HttpServletResponse response, @RequestBody String map) {
        Map<String, String> param = (Map<String, String>) JSON.parse(map);
        AtResponse<String> resp = super.delete(param.get("modCode"), smModuleRepService);

        wirte(response, resp);
    }

    @PostMapping("/view")
    @ResponseBody
    public void view(HttpServletResponse response, @RequestBody SmModule smModule) {
        //查询所有菜单  菜单级别分层
        AtResponse<String> resp = super.editSave(smModule, smModuleRepService);
        wirte(response, resp);
    }


    /**
     * 查询所有菜单
     *
     * @param response
     */
    @PostMapping("/moduleList")
    @ResponseBody
    public void moduleList(HttpServletResponse response) {
        //查询所有菜单  菜单级别分层
        List<SmModule> allModule = roleModuleService.getAllModule();
        AtResponse atResponse = new AtResponse();
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setData(allModule);
        wirte(response, atResponse);
    }


    /**
     * 角色对应菜单
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public void list(HttpServletResponse response) {
        //获取当前角色
        AtResponse<List> atResponse = new AtResponse();
        String currentRole = getCurrentRole();
        String userId = getUserId();
        System.out.println(userId);
        List<SmModule> modules = roleModuleService.queryComponetByRole(currentRole);
        atResponse.setResponseCode(ResponseCode.OK);
        atResponse.setData(modules);
        wirte(response, atResponse);
    }

    /**
     * 为角色添加功能菜单
     * todo  前端确定传输内容
     *
     * @param response
     * @param map
     */
    @PostMapping("/addRoleModules")
    @ResponseBody
    public void addRoleModules(HttpServletResponse response, @RequestBody String map) {

    }
}
