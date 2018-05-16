package com.jhmk.earlywaring.auth;

import com.jhmk.earlywaring.base.BaseController;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.controller.HosptailLogController;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
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
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        if (token == null) {
            String requestURI = request.getRequestURI();
            System.out.println(requestURI);
            if (requestURI.equals("/rule/ruleMatch")
                    || requestURI.contains("/users")
                    || requestURI.contains("/dept") || requestURI.contains("/login")
                    ) {
                chain.doFilter(request, response);
            } else {
                logger.info("请求网址为：" + requestURI);
                writer.write("user not login！please logn again");
                writer.flush();
                writer.close();
            }
        } else {
            String username = null;
            try {
                Claims claims = JWTUtil.parseJWT(token);
                if (claims != null) {
                    Map map = claims;
                    username = (String) map.get("jti");
                    SmUsers one = smUsersRepService.findOne(username);
                    if (one != null) {
                        chain.doFilter(request, response);
                        logger.info("authenticated user " + username + ", setting security context");
                    } else {
                        logger.info("请求用户为：" + username);
                        writer.write("用户未注册，请注册后使用");
                    }
//                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
//                            request));
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //可以在这里指定重定向还是返回错误接口示例
                logger.info("请求用户为：" + username);
                writer.write("user not login！please logn again");
                writer.flush();
                writer.close();
            }
        }

    }

}