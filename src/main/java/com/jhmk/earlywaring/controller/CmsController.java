package com.jhmk.earlywaring.controller;

import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
public class CmsController {
    @Autowired
    SmUsersRepService smUsersRepService;

    private static final Logger logger = LoggerFactory.getLogger(CmsController.class);

//    @RequestMapping("/")
//    public String index() {
//        return "login";
//    }


//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginGet(HttpServletRequest request, Model model) {
//        return "/login";
//    }


//    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
//    public String loginOut(HttpServletRequest request, Model model) {
//        return "/login";
//    }
//
//    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
//    public String loginSuccess(HttpServletRequest request, Model model) {
//        return "/login";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AtResponse<Map<String, Object>> loginPost(HttpServletRequest request, @RequestParam(required = true) String username,
                                                     @RequestParam(required = true) String password, Model model) {

        AtResponse<Map<String, Object>> resp = new AtResponse<>(System.currentTimeMillis());
        Map<String, Object> data = new HashMap<>();
        SmUsers admin = smUsersRepService.findOne(username);

        if (admin != null) {
            if (password.equals(admin.getUserPwd())) {

            } else {
                resp.setResponseCode(ResponseCode.INERERROR2);

                data.put("message", "密码错误");

            }
            data.put("status", "200");
            request.getSession().setAttribute("user", admin);
        } else {
            resp.setResponseCode(ResponseCode.CUSTOMER_NOT_EXIST);

            data.put("message", "用户不存在");

        }
        resp.setData(data);
        return resp;
    }

}
