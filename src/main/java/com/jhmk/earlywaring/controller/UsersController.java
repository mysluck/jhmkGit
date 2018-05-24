package com.jhmk.earlywaring.controller;


import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.service.SmUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@Controller
@RequestMapping("/warn/users")
public class UsersController extends BaseEntityController<SmUsers> {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    SmUserService smUserService;
    @Autowired
    RoleUserRepository roleUserRepository;


    @PostMapping("")
    public String list() {
        return "/users/list";
    }

    //分页展示
    @PostMapping(value = "/list")
    @ResponseBody
    public void userList(HttpServletResponse response, @RequestBody String map) {
        Map<String, Object> reqParams = (Map) JSON.parse(map);
        AtResponse<Map<String, Object>> resp = listData(reqParams, smUsersRepService, "userId");
        Map<String, Object> data = resp.getData();
        List<SmUsers> uu = (List<SmUsers>) data.get(LIST_DATA);
        data.put(LIST_DATA, uu);
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(data);
        wirte(response, resp);
    }


    //筛选功能
    @RequestMapping(value = "/serach")
    @ResponseBody
    public void serach(HttpServletResponse response, @RequestBody String map) {
        Map<String, String> fromMap = (Map) JSON.parse(map);
        String pageNum = fromMap.get("pageNum");
        SmUsers user = new SmUsers();
        try {
            BeanUtils.copyProperties(fromMap, user);
        } catch (IllegalAccessException e) {
            logger.error("用户转换失败1：" + fromMap.toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            logger.error("用户转换失败2：" + fromMap.toString());
            logger.error(e.getMessage());
            e.printStackTrace();
        }
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
        wirte(response, resp);
    }


    //添加操作
    @PostMapping(value = "/add")
    @ResponseBody
    public void addUser(HttpServletResponse response, @RequestBody SmUsers user) {
        AtResponse<String> resp = new AtResponse<>();
        SmUsers one = smUsersRepService.findOne(user.getUserId());
        if (one != null) {
            resp.setResponseCode(ResponseCode.INERERROR);
            resp.setMessage("用户已存在");
        } else {
            SmUsers save = smUsersRepService.save(user);
            if (save != null) {
                resp.setResponseCode(ResponseCode.OK);
                resp.setMessage("用户添加成功");
            } else {
                logger.debug("用户添加失败：" + save.toString());
                resp.setResponseCode(ResponseCode.INERERROR);
                resp.setMessage("用户添加失败");
            }
        }
        wirte(response, resp);
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
    public void delete(HttpServletResponse response, @RequestBody(required = true) String map) {
        Map<String, String> parse = (Map) JSON.parse(map);
        String userId = parse.get("userId");
        smUsersRepService.delete(userId);
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        wirte(response, resp);
    }

    @RequestMapping(value = "/editor")
    @ResponseBody
    public AtResponse editor(HttpServletResponse response, @RequestBody SmUsers map) {
        AtResponse<String> stringAtResponse = super.editSave(map, map.getUserId(), smUsersRepService);
        return stringAtResponse;
    }

    //修改密码
    @RequestMapping(value = "/changePassword")
    @ResponseBody
    public void updPwd(HttpServletResponse response, @RequestBody String map) {
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        Map<String, String> parse = (Map) JSON.parse(map);
        SmUsers user = smUsersRepService.findOne(getUserId());
//        if (user.getUserPwd().equals(encoder.encodePassword(jiuPassword, null))) {
        if (user.getUserPwd().equals(parse.get("jiuPassword"))) {
//            String pwd = encoder.encodePassword(password, null);
            smUsersRepService.setPasswordFor(parse.get("password"), getUserId());
            resp.setResponseCode(ResponseCode.OK);
            resp.setMessage("修改成功！");
        } else {
            resp.setResponseCode(ResponseCode.INERERROR2);
            resp.setMessage("原密码错误！");
        }

        wirte(response, resp);


    }

    //保存编辑
    @PostMapping(value = "/editorUser")
    @ResponseBody
    public AtResponse editorUser(HttpServletResponse response, @RequestBody SmUsers user) {

        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        if (StringUtils.isEmpty(user.getRoleId())) {
            resp.setResponseCode(ResponseCode.INERERROR);
            resp.setMessage("请选择角色！");
            return resp;
        }
        SmUsers updateUser = smUserService.editor(user, user.getRoleId());
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