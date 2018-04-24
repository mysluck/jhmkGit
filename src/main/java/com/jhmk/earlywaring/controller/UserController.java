//package com.jhmk.earlywaring.controller;
//
//
//import com.jhmk.earlywaring.base.BaseEntityController;
//import com.jhmk.earlywaring.entity.RoleUser;
//import com.jhmk.earlywaring.entity.SmUser;
//import com.jhmk.earlywaring.model.AtResponse;
//import com.jhmk.earlywaring.model.ResponseCode;
//import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
//import com.jhmk.earlywaring.entity.repository.SmUserRepository;
//import com.jhmk.earlywaring.service.SmUserService;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.sql.Timestamp;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/user")
//public class UserController extends BaseEntityController<SmUser> {
//
//
//    @Autowired
//    SmUserRepository smUserRepository;
//    @Autowired
//    SmUserService smUserService;
//    @Autowired
//    RoleUserRepository roleUserRepository;
//
//
//    @RequestMapping("")
//    public String list() {
//        return "/user/list";
//    }
//
//    //分页展示
//    @RequestMapping(value = "/listData")
//    @ResponseBody
//    public AtResponse userList(@RequestParam Map<String, Object> reqParams) {
//
//
//        AtResponse<Map<String, Object>> resp = listData(reqParams, smUserRepository, "userId");
//        Map<String, Object> data = resp.getData();
//        Map<String, String> mm = (Map<String, String>) data.get("params");
//        List<SmUser> uu = (List<SmUser>) data.get(LIST_DATA);
//        data.put(LIST_DATA, uu);
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(data);
//        return resp;
//    }
//
//    //分页展示
//    @RequestMapping(value = "/listData1")
//    @ResponseBody
//    public AtResponse userList1(@RequestParam Map<String, Object> reqParams) {
//        reqParams = new HashMap<>();
//        reqParams.put("userId", "admin");
//        AtResponse<Map<String, Object>> resp = listDataWithAuthByOrganizeId(reqParams, smUserRepository, "userId");
//        Map<String, Object> data = resp.getData();
//        Map<String, String> mm = (Map<String, String>) data.get("params");
//        List<SmUser> uu = (List<SmUser>) data.get(LIST_DATA);
//        data.put(LIST_DATA, uu);
//        //2.机构列表
//
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(data);
//        return resp;
//    }
//
//
//    //筛选功能
//    @RequestMapping(value = "/serach")
//    @ResponseBody
//    public AtResponse serach(@ModelAttribute SmUser user, String pageNum) {
//        int page = 1;
//        //当前页不为空
//        if (pageNum != null && !"".equals(pageNum.trim())) {
//            page = new Integer(pageNum) - 1;
//        }
//        Map<String, Object> params = smUserService.serachData(user, page);
//        params.put("user", user);
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(params);
//        return resp;
//    }
//
//    //
////    //查询该用户下所有机构
////    @RequestMapping(value = "/QueryAllOrg")
////    @ResponseBody
////    public AtResponse findOrgAndRole() {
////        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
////        Map<String, Object> parmms = new HashMap<>();
////        List<Map<String,Object>> orgList = getSubOrgs(getOrgId());
////        parmms.put("orgList", orgList);
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(parmms);
////        return resp;
////    }
////
//    //跳转到添加 todo 重写url
//    @RequestMapping(value = "/toAdd")
//    public String toAdd() {
//        return "/sys/user/add";
//    }
//
//    //添加操作
//    @RequestMapping(value = "/addUser")
//    @ResponseBody
//    public AtResponse addUser(@ModelAttribute SmUser user, String role) {
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        if (StringUtils.isEmpty(role)) {
//            resp.setMessage("请选择角色！");
//            resp.setResponseCode(ResponseCode.INERERROR);
//            return resp;
//        }
////        user.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
//        user.setLastUpdateOperator(getUserId());
//        SmUser saveUser = smUserService.save(user, role);
//        String message = "";
//        if (saveUser == null) {
//            resp.setMessage("添加失败,请重新添加！");
//            resp.setResponseCode(ResponseCode.INERERROR);
//            return resp;
//        } else {
//            resp.setMessage("添加成功！");
//            resp.setResponseCode(ResponseCode.OK);
//            return resp;
//        }
//
//    }
//
//    //根据id查询用户是否存在
////    @RequestMapping(value = "/checkUserId")
////    @ResponseBody
////    public AtResponse<String> checkUserId(@RequestParam String userId) {
////        SmUser user = userService.findOne(userId);
////
////        String existUser = "Y";
////        if (user == null) {
////            existUser = "N";
////        }
////        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
////        resp.setResponseCode(ResponseCode.OK);
////        resp.setData(existUser);
////        return resp;
////    }
////
//    //删除
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public AtResponse<String> delete(@RequestParam(name = "userId", required = true) String userId) {
//        int index = smUserService.delete(userId);
//        String message;
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        if (index != 0) {
//            message = "删除成功";
//            resp.setResponseCode(ResponseCode.OK);
//            resp.setData(message);
//            return resp;
//        } else {
//            message = "删除失败,请重新操作";
//            resp.setResponseCode(ResponseCode.INERERROR);
//            resp.setData(message);
//            return resp;
//        }
//    }
//
//    //跳转到编辑 todo 重写url
//    @RequestMapping(value = "/toEditor")
//    public String toEditor(@RequestParam(name = "userId", required = true) String userId, ModelMap model) {
//        model.put("userId", userId);
//        return "/sys/user/editor";
//    }
//
//    @RequestMapping(value = "/editor")
//    @ResponseBody
//    public AtResponse editor(@RequestParam(name = "userId", required = true) String userId) {
//        Map<String, Object> params = new HashMap<>();
//        SmUser user = smUserRepository.findOne(userId);
//        params.put("user", user);
//        RoleUser roleUser = null;
//                //roleUserRepository.findByUser(user);
//        params.put("role", roleUser.getRole());
//        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(params);
//        return resp;
//    }
//
//    //修改密码
//    @RequestMapping(value = "/changePassword")
//    @ResponseBody
//    public AtResponse updPwd(String jiuPassword, String password) {
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        SmUser user = smUserRepository.findOne(getUserId());
//        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//        if (user.getPassword().equals(encoder.encodePassword(jiuPassword, null))) {
//            String pwd = encoder.encodePassword(password, null);
//            smUserRepository.setUserPassword(pwd, getUserId());
//            resp.setResponseCode(ResponseCode.OK);
//            resp.setMessage("修改成功！");
//        } else {
//            resp.setResponseCode(ResponseCode.INERERROR2);
//            resp.setMessage("原密码错误！");
//        }
//
//        return resp;
//
//
//    }
//
//    //保存编辑
//    @RequestMapping(value = "/editorUser")
//    @ResponseBody
//    public AtResponse editorUser(@ModelAttribute SmUser user, String role) {
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        if (StringUtils.isEmpty(role)) {
//            resp.setResponseCode(ResponseCode.INERERROR);
//            resp.setMessage("请选择角色！");
//            return resp;
//        }
////        user.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
//        user.setLastUpdateOperator(getUserId());
//        user.setDeleted("0");
//        SmUser updateUser = smUserService.editor(user, role);
//        String message = "";
//        if (updateUser == null) {
//            resp.setResponseCode(ResponseCode.INERERROR);
//            resp.setMessage("编辑失败,请重新添加！");
//            return resp;
//        } else {
//            resp.setResponseCode(ResponseCode.OK);
//            resp.setMessage("编辑成功！");
//            return resp;
//        }
//
//    }
//}