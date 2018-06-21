package com.jhmk.earlywaring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmHosptailLog;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller

public class CmsController extends BaseEntityController<SmUsers> {
    @Autowired
    SmDeptsRepService smDeptsRepService;
    @Autowired
    SmRoleRepService smRoleRepService;
    @Autowired
    SmUsersRepService smUsersRepService;
    private static final Logger logger = LoggerFactory.getLogger(CmsController.class);

    @RequestMapping(value = "/warn/login", method = RequestMethod.POST)
    @ResponseBody
    public void loginPost(HttpServletRequest httpServletRequest, HttpServletResponse response, @RequestBody String map) {
        Map<String, String> param = (Map) JSONObject.parse(map);

        AtResponse<Map<String, Object>> resp = new AtResponse<>(System.currentTimeMillis());
        Map<String, Object> data = new HashMap<>();
        if (param.get("username") == null || "".equals(param.get("username")) || "".equals(param.get("password"))) {
            resp.setResponseCode(ResponseCode.INERERROR2);
            resp.setMessage("用户名或密码为空");
            wirte(response, resp);
        } else {
            String userId = param.get("username");
            SmUsers admin = smUsersRepService.findOne(userId);
            String password = param.get("password");
            if (admin != null) {
                if (password.equals(admin.getUserPwd())) {
                    httpServletRequest.getSession().setAttribute(BaseConstants.USER_ID, userId.trim());
                    //todo token 写法
                    String token = null;
                    try {
                        token = JWTUtil.createJWT(userId, password, System.currentTimeMillis());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    httpServletRequest.getSession().setAttribute(BaseConstants.TOKEN, token);
                    String roleId = admin.getRoleId();
                    httpServletRequest.getSession().setAttribute(BaseConstants.CURRENT_ROLE_ID, roleId);
                    SmRole one = smRoleRepService.findOne(roleId);
                    httpServletRequest.getSession().setAttribute(BaseConstants.CURRENT_ROLE_RANGE, one.getRuleRange());


                    if (admin.getUserDept() != null) {
                        httpServletRequest.getSession().setAttribute(BaseConstants.DEPT_ID, admin.getUserDept());
                        SmDepts firstByDeptCode = smDeptsRepService.findFirstByDeptCode(admin.getUserDept());
                        if (firstByDeptCode != null) {
                            httpServletRequest.getSession().setAttribute(BaseConstants.DEPT_NAME, firstByDeptCode.getDeptName());
                        } else {
//                        httpServletRequest.getSession().setAttribute(BaseConstants.DEPT_NAME, firstByDeptCode.getDeptName());
                        }
                    }


                    httpServletRequest.getSession().setAttribute(BaseConstants.TOKEN, token);
                    //设置session超时时间(2小时)
                    httpServletRequest.getSession().setMaxInactiveInterval(2 * 60 * 60);

                    logger.info("登录成功");
                    resp.setMessage("登录成功");
                    resp.setResponseCode(ResponseCode.OK);
                    response.setHeader(BaseConstants.TOKEN, token);
                } else {
                    resp.setResponseCode(ResponseCode.INERERROR2);
                    resp.setMessage("密码错误");

                }
                data.put("status", "200");
            } else {
                resp.setResponseCode(ResponseCode.CUSTOMER_NOT_EXIST);
                resp.setMessage("用户不存在");
            }

            wirte(response, resp);

        }

    }

    @RequestMapping(value = "/warn/loginout", method = RequestMethod.POST)
    @ResponseBody
    public void loginout(HttpServletRequest request, HttpServletResponse response) {
        request.removeAttribute(BaseConstants.USER_ID);
        request.removeAttribute(BaseConstants.CURRENT_ROLE_ID);
        request.removeAttribute(BaseConstants.DEPT_ID);
//            request.removeAttribute(BaseConstants.FT_DEPT_ID);
//            request.removeAttribute(BaseConstants.FT_DEPT_NAME);
        request.removeAttribute(BaseConstants.TOKEN);

        logger.debug(getUserId() + " logout.");
        AtResponse resp = new AtResponse(System.currentTimeMillis());
        resp.setMessage(BaseConstants.SUCCESS);
        resp.setResponseCode(ResponseCode.OK);
        logger.info("退出成功");
        wirte(response, request);

    }

}
