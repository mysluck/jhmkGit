//package com.jhmk.earlywaring.controller;
//
//
//import com.jhmk.earlywaring.base.BaseEntityController;
//import com.jhmk.earlywaring.entity.RoleComponent;
//import com.jhmk.earlywaring.entity.SmComponent;
//import com.jhmk.earlywaring.entity.repository.service.RoleComponentRepService;
//import com.jhmk.earlywaring.entity.repository.service.SmComponentRepService;
//import com.jhmk.earlywaring.model.AtResponse;
//import com.jhmk.earlywaring.model.ResponseCode;
//import com.jhmk.earlywaring.entity.repository.RoleComponentRepository;
//import com.jhmk.earlywaring.entity.repository.SmComponentRepository;
//import com.jhmk.earlywaring.service.RoleComponentService;
//import com.jhmk.earlywaring.service.RoleFunctionService;
//import com.jhmk.earlywaring.service.SmComponentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.sql.Timestamp;
//import java.util.*;
//
//@Controller
//@RequestMapping("/component")
//public class ComponentController extends BaseEntityController<SmComponent> {
//
//
//    @Autowired
//    RoleComponentRepService roleComponentRepService;
//    @Autowired
//    RoleComponentService roleComponentService;
//
//    @Autowired
//    SmComponentService smComponentService;
//    @Autowired
//    SmComponentRepService smComponentRepService;
//
//
//    @Autowired
//    RoleFunctionService roleFunService;
//
//
//    @RequestMapping(value = "moduleRoleList")
//    @ResponseBody
//    public AtResponse selectModuleRolelists(@RequestParam String roleId) {
//        List<RoleComponent> allByEntryRole = roleComponentRepService.findAllByEntryRole(roleId);
//        AtResponse<List> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(allByEntryRole);
//        return resp;
//
//    }
//
////    @RequestMapping(value = "moduleRoleList")
////    @ResponseBody
////    public AtResponse selectModuleRolelists(@RequestParam String roleId) {
////        List<SmComponent> componentList = (List<SmComponent>) componentRepo.findAll();
////        //查询角色对应的所有的组件操作信息
////        List<Map<String, Object>> comFunMapList = roleFunService.getComFunction(roleId);
////        //查询所有的组件操作信息
////        List<Map<String, Object>> comFunMapListAll = roleFunService.getComFunctionAll();
////        List<RoleComponent> roleCompList = roleComponentRepository.getEntComponentByEntryRole(roleId);
////
////        //角色包含的componet Map
////        Map<String, String> roleCompMap = new HashMap<String, String>();
////        for (RoleComponent roleComponent : roleCompList) {
////            roleCompMap.put(roleComponent.getEntryComponent(), roleComponent.getEntryComponent());
////        }
////
////        //组装module-component list map
////        Map<String, List<SmComponent>> modComMap = new HashMap<String, List<SmComponent>>();
////        for (SmComponent component : componentList) {
////            String modId = component.getComModule();
////            if (modComMap.containsKey(modId)) {
////                List<SmComponent> comList = modComMap.get(modId);
////                comList.add(component);
////            } else {
////                List<SmComponent> comList = new ArrayList<SmComponent>();
////                comList.add(component);
////                modComMap.put(modId, comList);
////            }
////        }
////
////        List<ModuleBlock> moduleBlockLists = new ArrayList<ModuleBlock>();
////        for (SmModule m : modList) {
////            //创建ModuleBlock
////            ModuleBlock omb = new ModuleBlock();
////            //创建组件集合
////            List<ComponentBlock> componentBlockLists = new ArrayList<ComponentBlock>();
////
////            omb.setModuleName(m.getModNameLocal());
////            List<SmComponent> comList = modComMap.get(m.getId());
////            if (CollectionUtils.isEmpty(comList)) {
////                continue;
////            }
////            for (SmComponent com : comList) {
////                ComponentBlock comBlock = new ComponentBlock();
////                comBlock.setComponentName(com.getComponentNameLocal());
////                comBlock.setId(com.getId());
////                if (roleCompMap.containsKey(com.getId())) {
////                    comBlock.setFlag("true");
////                } else {
////                    comBlock.setFlag("false");
////                }
////                //遍历所有组件，把组件下的操作设置为’false‘
////                for (Map<String, Object> comFunMap : comFunMapListAll) {
////                    if (comFunMap.get("com").equals(com.getId())) {
////                        if ("create".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setCreate("false");
////                        } else if ("view".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setView("false");
////                        } else if ("edit".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setEdit("false");
////                        } else if ("delete".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setDelete("false");
////                        } else if ("auth".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setAuth("false");
////                        }
////                    }
////                }
////                //遍历当前用户下的所有组件，把组件下的操作设置为’false‘,空的话组件下不存在此权限，’true‘的话就是存在且用户有此权限
////                for (Map<String, Object> comFunMap : comFunMapList) {
////                    if (comFunMap.get("com").equals(com.getId())) {
////                        if ("create".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setCreate("true");
////                        } else if ("view".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setView("true");
////                        } else if ("edit".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setEdit("true");
////                        } else if ("delete".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setDelete("true");
////                        } else if ("auth".equals(comFunMap.get("fun"))) {
////                            comBlock.getFb().setAuth("true");
////                        }
////                    }
////                }
////                componentBlockLists.add(comBlock);
////            }
////            omb.setComponentBlockLists(componentBlockLists);
////            moduleBlockLists.add(omb);
////        }
////
////        AtResponse<List> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(moduleBlockLists);
////        return resp;
////    }
////
////    @Transactional(propagation = Propagation.REQUIRED)
////    @RequestMapping(value = "updateModuleRolelists")
////    @ResponseBody
////    public AtResponse updateModuleRolelists(@RequestParam String jsonStr, @RequestParam String roleId) {
////        try {
////            String userId = getUserId();
////            String[] comFunArray = jsonStr.split("\\|");
////            String[] componentArray = new String[jsonStr.split("\\|").length];
////            List<Map<String, Object>> comFunMapList = new ArrayList<Map<String, Object>>();
////            int i = 0;
////            //生成componentArray和comFunMapList
////            for (String conFun : comFunArray) {
////                if (conFun.contains(",")) {
////                    componentArray[i++] = conFun.substring(0, conFun.indexOf(","));
////                    String[] funArray = conFun.substring(conFun.indexOf(",") + 1, conFun.length()).split(",");
////                    for (String fun : funArray) {
////                        Map<String, Object> comFumMap = new HashMap<String, Object>();
////                        comFumMap.put(componentArray[i - 1], fun);
////                        comFunMapList.add(comFumMap);
////                    }
////                } else {
////                    componentArray[i++] = conFun;
////                }
////            }
////            //更新角色组件信息
////            roleComponentService.updateRoleComponent(componentArray, roleId, userId);
////            //更新角色操作信息
////            roleFunService.updateRoleFunction(comFunMapList, roleId, userId);
////        } catch (Exception e) {
////            e.printStackTrace();
////            AtResponse<List> resp = new AtResponse(System.currentTimeMillis());
////            resp.setResponseCode(ResponseCode.INERERROR);
////            resp.setMessage(e.getMessage());
////            return resp;
////        }
////        AtResponse<List> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        return resp;
////    }
//
//
//    /*****************************************组件管理***************************************************/
//    /**
//     * 跳转组件管理页面
//     *
//     * @return
//     */
//    @RequestMapping("")
//    public String list() {
//        return "/sys/component/list";
//    }
//
//    /*
//     * 条件查询
//     * @param module
//     * @param pageNum
//     * @return
//     */
//    @RequestMapping(value = "/serachCom")
//    @ResponseBody
//    public AtResponse serachCom(@ModelAttribute SmComponent com, @RequestParam(required = false) String pageNum) {
//        int page = 0;
//        //当前页不为空
//        if (pageNum != null && !"".equals(pageNum.trim())) {
//            page = new Integer(pageNum) - 1;
//        }
//
//        //查询组件信息
//        Map<String, Object> params = smComponentService.serachCom(com, page);
//        params.put("com", com);
//
////        //查询模块列表信息
////        List<CodeTableBean> codeList = new ArrayList<CodeTableBean>();
////        Iterable<SmModule> iterable = moduleRepo.findAll();
////        Iterator<SmModule> moduleIt = iterable.iterator();
////        while (moduleIt.hasNext()) {
////            SmModule module = moduleIt.next();
////            CodeTableBean ct = new CodeTableBean();
////            ct.setId(module.getId());
////            ct.setValue(module.getModName());
////            codeList.add(ct);
////        }
////        params.put("vsfdodeList);
////
////        //操作查询
////        OperateBean operate = getOperateByRoleId(getCurrentRole());
////        params.put(super.LIST_OPERATE, operate);
//
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(params);
//        return resp;
//    }
//
//    //删除
//    @RequestMapping(value = "/deleteCom")
//    @ResponseBody
//    public AtResponse<String> deleteCom(@RequestParam(name = "id", required = true) String id) {
//
//        String message;
//        try {
//            smComponentRepService.delete(id);
//            message = "删除成功";
//        } catch (Exception e) {
//            message = "删除失败,请重新操作";
//        }
//
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(message);
//        return resp;
//    }
//
//
//    //根据id查询组件是否存在
////    @RequestMapping(value = "/checkComCode")
////    @ResponseBody
////    public AtResponse<String> checkComCode(@RequestParam String id) {
////        SmComponent com = componentService.findOne(id);
////
////        String existCom = "Y";
////        if (com == null) {
////            existCom = "N";
////        }
////        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(existCom);
////        return resp;
////    }
//
////    //根据名称查询组件是否存在
////    @RequestMapping(value = "/checkComName")
////    @ResponseBody
////    public AtResponse<String> checkComName(@RequestParam String comName) {
////        int nameCount = componentService.countByComName(comName);
////
////        String existCom = "Y";
////        if (nameCount == 0) {
////            existCom = "N";
////        }
////        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(existCom);
////        return resp;
////    }
//
////    //根据中文名称查询组件是否存在
////    @RequestMapping(value = "/checkComNameLocal")
////    @ResponseBody
////    public AtResponse<String> checkComNameLocal(@RequestParam String comNameLocal) {
////        int nameLocalCount = componentService.countByComNameLocal(comNameLocal);
////
////        String existCom = "Y";
////        if (nameLocalCount == 0) {
////            existCom = "N";
////        }
////        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(existCom);
////        return resp;
////    }
//
//    //编辑
//    @RequestMapping(value = "/toEditCom")
//    @ResponseBody
//    public AtResponse toEditCom(@RequestParam(name = "id", required = true) String id) {
//        //根据id查询组件信息
//        Map<String, Object> params = smComponentService.view(id);
//
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(params);
//        return resp;
//    }
//
//    //编辑保存
//    @RequestMapping(value = "/editCom")
//    @ResponseBody
//    public AtResponse editCom(@ModelAttribute SmComponent com) {
//        com.setComUpdatedby(getUserId());
//        com.setComUpdatedtime(new Timestamp(System.currentTimeMillis()));
//        smComponentRepService.save(com);
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        return resp;
//    }
//
//    //添加
////    @RequestMapping(value = "/toAddCom")
////    @ResponseBody
////    public AtResponse toAddCom() {
////        List<CodeTableBean> codeList = new ArrayList<CodeTableBean>();
////        Map<String, Object> params = new HashMap<String, Object>();
////        //查询模块列表信息
////        Iterable<SmModule> iterable = moduleRepo.findAll();
////        Iterator<SmModule> moduleIt = iterable.iterator();
////        while (moduleIt.hasNext()) {
////            CodeTableBean ct = new CodeTableBean();
////            ct.setId(moduleIt.next().getId());
////            ct.setValue(moduleIt.next().getModName());
////            codeList.add(ct);
////        }
////        params.put("modules", codeList);
////        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(params);
////        return resp;
////    }
//
//    //添加
//    @RequestMapping(value = "/addCom")
//    @ResponseBody
//    public AtResponse addCom(@ModelAttribute SmComponent com) {
//        com.setComUpdatedtime(new Timestamp(System.currentTimeMillis()));
//        com.setComUpdatedby(getUserId());
//        smComponentRepService.save(com);
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        return resp;
//    }
//
//
//}
