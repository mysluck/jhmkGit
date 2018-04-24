package com.jhmk.earlywaring.controller;


import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.SmUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseEntityController<SmUsers> {


    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    SmUserService smUserService;
    @Autowired
    RoleUserRepository roleUserRepository;


    @RequestMapping("")
    public String list() {
        return "/users/list";
    }

    //分页展示
    @RequestMapping(value = "/list")
    @ResponseBody
    public AtResponse userList(@RequestParam Map<String, Object> reqParams) {


        AtResponse<Map<String, Object>> resp = listData(reqParams, smUsersRepService, "userId");
        Map<String, Object> data = resp.getData();
        Map<String, String> mm = (Map<String, String>) data.get("params");
        List<SmUsers> uu = (List<SmUsers>) data.get(LIST_DATA);
        data.put(LIST_DATA, uu);
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(data);
        return resp;
    }


    //筛选功能
    @RequestMapping(value = "/serach")
    @ResponseBody
    public AtResponse serach(@ModelAttribute SmUsers user, String pageNum) {
        int page = 1;
        //当前页不为空
        if (pageNum != null && !"".equals(pageNum.trim())) {
            page = new Integer(pageNum) - 1;
        }
        Map<String, Object> params = smUserService.serachData(user, page);
        params.put("user", user);
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }


    //添加操作
    @RequestMapping(value = "/add")
    @ResponseBody
    public AtResponse addUser(@ModelAttribute SmUsers user, String role) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        if (StringUtils.isEmpty(role)) {
            resp.setMessage("请选择角色！");
            resp.setResponseCode(ResponseCode.INERERROR);
            return resp;
        }
        SmUsers saveUser = smUserService.save(user, role);
        if (saveUser == null) {
            resp.setMessage("添加失败,请重新添加！");
            resp.setResponseCode(ResponseCode.INERERROR);
            return resp;
        } else {
            resp.setMessage("添加成功！");
            resp.setResponseCode(ResponseCode.OK);
            return resp;
        }

    }

    //根据id查询用户是否存在
//    @RequestMapping(value = "/checkUserId")
//    @ResponseBody
//    public AtResponse<String> checkUserId(@RequestParam String userId) {
//        SmUsers user = userService.findOne(userId);
//
//        String existUser = "Y";
//        if (user == null) {
//            existUser = "N";
//        }
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        resp.setResponseCode(ResponseCode.OK);
//        resp.setData(existUser);
//        return resp;
//    }
//
//    //删除
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AtResponse<String> delete(@RequestParam(name = "userId", required = true) String userId) {
        smUsersRepService.delete(userId);
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        return resp;
    }

    @RequestMapping(value = "/editor")
    @ResponseBody
    public AtResponse editor(@RequestParam(name = "userId", required = true) String userId) {
        Map<String, Object> params = new HashMap<>();
        SmUsers user = smUsersRepService.findOne(userId);
        params.put("user", user);
        RoleUser roleUser = null;
        //roleUserRepository.findByUser(user);
        params.put("role", roleUser.getRole());
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(params);
        return resp;
    }

//    //修改密码
//    @RequestMapping(value = "/changePassword")
//    @ResponseBody
//    public AtResponse updPwd(String jiuPassword, String password) {
//        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
//        SmUsers user = smUsersRepService.findOne(getUserId());
//        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
//        if (user.getUserPwd().equals(encoder.encodePassword(jiuPassword, null))) {
//            String pwd = encoder.encodePassword(password, null);
//            smUsersRepService.setPasswordFor(pwd, getUserId());
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

    //保存编辑
    @RequestMapping(value = "/editorUser")
    @ResponseBody
    public AtResponse editorUser(@ModelAttribute SmUsers user, String role) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        if (StringUtils.isEmpty(role)) {
            resp.setResponseCode(ResponseCode.INERERROR);
            resp.setMessage("请选择角色！");
            return resp;
        }
        SmUsers updateUser = smUserService.editor(user, role);
        String message = "";
        if (updateUser == null) {
            resp.setResponseCode(ResponseCode.INERERROR);
            resp.setMessage("编辑失败,请重新添加！");
            return resp;
        } else {
            resp.setResponseCode(ResponseCode.OK);
            resp.setMessage("编辑成功！");
            return resp;
        }

    }
}