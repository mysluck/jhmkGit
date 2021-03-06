//package com.jhmk.earlywaring.auth;
//
//import com.jhmk.earlywaring.base.BaseController;
//import com.jhmk.earlywaring.config.BaseConstants;
//import com.jhmk.earlywaring.controller.HosptailLogController;
//import com.jhmk.earlywaring.entity.SmUsers;
//import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
//import com.jhmk.earlywaring.model.AtResponse;
//import com.jhmk.earlywaring.model.ResponseCode;
//import com.jhmk.earlywaring.util.JWTUtil;
//import io.jsonwebtoken.Claims;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//
//@Component
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
//
//
//    //    @Autowired
////    private AuthUserDetailsServiceImpl userDetailsService;
//    @Autowired
//    SmUsersRepService smUsersRepService;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain) throws ServletException, IOException {
//
//        String token = request.getHeader("token");
//        Object sessionToken = request.getSession().getAttribute("token");
//        //设置session过期时间，每次访问资源都会经过过滤器，如超过2小时时间不访问则过期
//        response.setCharacterEncoding("utf-8");
////        PrintWriter writer = response.getWriter();
//        AtResponse resp = new AtResponse();
//
//        if (token == null) {
//            String requestURI = request.getRequestURI();
////            System.out.println(requestURI);
//            if ("/warn/rule/ruleMatch".equals(requestURI)
//                    || requestURI.contains("warn/match")
//                    || requestURI.contains("warn/rule/match")
//                    || requestURI.contains("warn/users")
//                    || requestURI.contains("warn/dept")
//                    || requestURI.contains("warn/login")
//                    || requestURI.contains("/getShowLog")
//                    || requestURI.contains("/updateShowLog")
//                    || requestURI.contains("/getLogFile")
//                    ) {
//                chain.doFilter(request, response);
//            } else {
//                logger.info("请求网址为：" + requestURI);
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                resp.setResponseCode(ResponseCode.INERERROR3);
//                resp.setMessage("用户未登录，请重新登陆");
//            }
//        } else {
//            if (sessionToken == null) {
//                logger.info("用户登录过期，请重新登陆");
//                resp.setResponseCode(ResponseCode.INERERROR5);
//            } else {
//                if (token.equals(sessionToken)) {
//                    request.getSession().setMaxInactiveInterval(2 * 60 * 60);
//                    chain.doFilter(request, response);
//                } else {
//                    logger.info("无效token" + token);
//                    resp.setResponseCode(ResponseCode.INERERROR5);
//                }
//            }
//
//        }
//        BaseController.wirte(response,resp);
//    }
//
//}
//
