package com.jhmk.earlywaring.auth;

import com.jhmk.earlywaring.base.BaseController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.controller.HosptailLogController;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    Logger logger = Logger.getLogger(HosptailLogController.class);

    @Autowired
    private AuthUserDetailsServiceImpl userDetailsService;
    @Autowired
    SmUsersRepService smUsersRepService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        String token = request.getHeader("token");
        //设置session过期时间，每次访问资源都会经过过滤器，如超过2小时时间不访问则过期
        request.getSession().setMaxInactiveInterval(2 * 60 * 60);
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        if (token == null) {
            String requestURI = request.getRequestURI();
//            System.out.println(requestURI);
            if (requestURI.equals("/rule/ruleMatch")
                    || requestURI.contains("/users")
                    || requestURI.contains("/dept") || requestURI.contains("/login")
                    ) {
                chain.doFilter(request, response);
            } else {
                logger.info("请求网址为：" + requestURI);
                AtResponse resp = new AtResponse();
                resp.setResponseCode(ResponseCode.INERERROR3);
                resp.setMessage("用户未登录，请重新登陆");
                writer.print(resp);
            }
        } else {
            String username = null;
            String userPwd = null;
            try {
                Claims claims = JWTUtil.parseJWT(token);
                if (claims != null) {
                    Map map = claims;
                    username = (String) map.get("jti");
                    userPwd = (String) map.get("sub");
                    SmUsers one = smUsersRepService.findByUserIdAndUserPwd(username, userPwd);
                    if (one != null) {
                        chain.doFilter(request, response);
                        logger.info("authenticated user " + username + ", setting security context");
                    } else {
                        logger.info("用户名或密码错误，请重新登录");
                        AtResponse resp = new AtResponse();
                        resp.setResponseCode(ResponseCode.INERERROR2);
                        resp.setMessage("用户名或密码错误，请重新登录");
                        writer.print(resp);
                    }
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                logger.info("请求用户为：" + username);
                AtResponse resp = new AtResponse();
                resp.setResponseCode(ResponseCode.INERERROR3);
                resp.setMessage("用户未登录，请重新登录");
                writer.print(resp);
                writer.flush();
                writer.close();
            }
        }

    }

}