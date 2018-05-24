package com.jhmk.earlywaring.controller;

import com.jhmk.earlywaring.base.BaseEntityController;
import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/warn/depts")
public class DeptsController extends BaseEntityController<SmDepts> {

    @Autowired
    SmDeptsRepService smDeptsRepService;

    //增删改查
    @RequestMapping("")
    public String list() {
        return "/list";
    }


    //分页展示
    @RequestMapping(value = "/listData")
    @ResponseBody
    public AtResponse userList(@RequestParam Map<String, Object> reqParams) {


        AtResponse<Map<String, Object>> resp = listData(reqParams, smDeptsRepService, null);
        Map<String, Object> data = resp.getData();
        Map<String, String> mm = (Map<String, String>) data.get("params");
        List<SmDepts> uu = (List<SmDepts>) data.get(LIST_DATA);
        data.put(LIST_DATA, uu);
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(data);
        return resp;
    }


    @RequestMapping(value = "add")
    @ResponseBody
    public AtResponse save(@ModelAttribute SmDepts dept) {
        SmDepts save = smDeptsRepService.save(dept);
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        resp.setResponseCode(ResponseCode.OK);
        return resp;
    }


    //删除
    @RequestMapping(value = "/delete")
    @ResponseBody
    public AtResponse<String> delete(@RequestParam(name = "userId", required = true) String userId) {
        smDeptsRepService.delete(userId);
        String message;
        AtResponse<String> resp = new AtResponse(System.currentTimeMillis());
        message = "删除成功";
        resp.setResponseCode(ResponseCode.OK);
        resp.setData(message);
        return resp;

    }


    @RequestMapping(value = "view")
    @ResponseBody
    public AtResponse view(@RequestParam String id) {
        Map<String, Object> params = new HashMap<>();
        SmDepts one = smDeptsRepService.findOne(id);
        params.put("user", one);
        String message;
        AtResponse<Map<String, Object>> resp = new AtResponse(System.currentTimeMillis());
        if (one != null) {
            resp.setResponseCode(ResponseCode.OK);
        } else {
            resp.setResponseCode(ResponseCode.INERERROR);
        }
        resp.setData(params);
        return resp;

    }

    @RequestMapping(value = "editor")
    @ResponseBody
    public AtResponse edit(@ModelAttribute SmDepts dept) {
        AtResponse<Object> resp = new AtResponse(System.currentTimeMillis());
        String message;
        SmDepts save = smDeptsRepService.save(dept);
        if (save != null) {
            resp.setResponseCode(ResponseCode.OK);
            resp.setData(save);
        } else {
            resp.setResponseCode(ResponseCode.INERERROR);
            resp.setData(save);
        }
        return resp;
    }


}
