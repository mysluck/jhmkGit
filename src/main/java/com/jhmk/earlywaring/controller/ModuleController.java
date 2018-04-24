package com.jhmk.earlywaring.controller;

import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmModule;
import com.jhmk.earlywaring.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseEntityController<SmModule> {

    @Autowired
    RoleModuleService roleModuleService;

    @PostMapping("/moduleList")
    @ResponseBody
    public void moduleList(HttpServletResponse response, @RequestBody String roleId) {
        List<SmModule> modules = roleModuleService.queryComponetByRole(roleId);
        wirte(response, roleId);
    }

//    /**
//     * 角色对应功能
//     * @return
//     */
//    @RequestMapping("/list")
//    @ResponseBody
//    public AtResponse<Map<String, List<SmModule>>> list() {
//        AtResponse<Map<String, List<SmModule>>> response = new AtResponse<>(System.currentTimeMillis());
//        Map<String, List<SmModule>> map = new HashMap<>();
//        String currentRole = getCurrentRole();
//        List<SmModule> modules = roleModuleService.queryComponetByRole(currentRole);
//        map.put("modules", modules);
//        response.setResponseCode(ResponseCode.OK);
//        response.setData(map);
//        return response;
//    }

    /**
     * 角色对应功能
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public void list(HttpServletResponse response) {
        String currentRole = getCurrentRole();
        List<SmModule> modules = roleModuleService.queryComponetByRole(currentRole);
        wirte(response, modules);
    }
}
