package com.jhmk.earlywaring.base;

import com.alibaba.fastjson.JSON;
import com.jhmk.earlywaring.config.BaseConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseController {

//	@Autowired
//    protected HttpServletRequest request;

    public void addSessionData(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public Object getSessionData(String key) {
        return getSession().getAttribute(key);
    }

    HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getHttpRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attr.getRequest();
    }

    public String getUserId() {
        String userId = (String) getSessionData(BaseConstants.USER_ID);
        return userId;
    }

    public String getCurrentRole() {
        String currentRoleId = (String) getSessionData(BaseConstants.CURRENT_ROLE_ID);
        return currentRoleId;
    }

    public void setCurrentRoleId(String roleId) {
        addSessionData(BaseConstants.CURRENT_ROLE_ID, roleId);
    }


    public String getDeptId() {
        String currentOrgId = (String) getSessionData(BaseConstants.DEPT_ID);
        return currentOrgId;
    }

    public String getFuDeptId() {
        String fuDeptName = (String) getSessionData(BaseConstants.FT_DEPT_ID);
        return fuDeptName;
    }

    public String getFuDeptName() {
        String fuDeptName = (String) getSessionData(BaseConstants.FT_DEPT_NAME);
        return fuDeptName;
    }

//
//    public String getCurrentRole(HttpSession httpSession) {
////        String currentRoleId = (String) httpSession.getAttribute("currentRoleId");
//    	  String currentRoleId = (String) getSessionData(CoreConstants.CURRENT_ROLE_ID);
//        return currentRoleId;
//    }
//
//    public void setCurrentRoleId(String roleId, HttpSession httpSession) {
////        httpSession.setAttribute("currentRoleId", roleId);
//    	addSessionData(CoreConstants.CURRENT_ROLE_ID, roleId);
//    }


    public void wirte(HttpServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSON(obj));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}