package com.jhmk.earlywaring.auth;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.DeptRel;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.DeptRelRepService;
import com.jhmk.earlywaring.entity.repository.service.RoleUserRepService;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import com.jhmk.earlywaring.model.AtResponse;
import com.jhmk.earlywaring.model.ResponseCode;
import com.jhmk.earlywaring.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private AuthUserDetailsServiceImpl authUserDetailsServiceImpl;


    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    SmUsersRepService userRepository;
    @Autowired
    RoleUserRepService roleUserRepService;
    @Autowired
    DeptRelRepService deptRelRepService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsServiceImpl);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("users/**", "/rule/**", "/dept/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().successHandler(new LogonSucessHandler())
                .and().csrf().disable()
                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //开启匿名访问
                .anonymous().and()
                //允许跨域
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        //验证token
//                .addFilterAfter(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }


    class LogoutSucessHandler implements LogoutHandler {

        @Override
        public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            //TODO 处理登出的逻辑
            request.removeAttribute(BaseConstants.USER_ID);
            request.removeAttribute(BaseConstants.CURRENT_ROLE_ID);
            request.removeAttribute(BaseConstants.DEPT_ID);
            request.removeAttribute(BaseConstants.FT_DEPT_ID);
            request.removeAttribute(BaseConstants.FT_DEPT_NAME);
            request.removeAttribute(BaseConstants.TOKEN);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            logger.debug(userDetails.getUsername() + " logout.");
            PrintWriter out = null;
            AtResponse resp = new AtResponse(System.currentTimeMillis());
            try {
                out = response.getWriter();
                resp.setMessage(BaseConstants.SUCCESS);
                resp.setResponseCode(ResponseCode.OK);
                out.print(JSON.toJSON(resp));
            } catch (IOException e) {
                resp.setMessage(BaseConstants.FALSE);
                resp.setResponseCode(ResponseCode.INERERROR);
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }
    }

    class LogonSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

        @Autowired
        private ObjectMapper objectMapper;

//        public LogonSucessHandler(String successUrl) {
//            this.setDefaultTargetUrl(successUrl);
//        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //TODO 添加登录成功后的处理逻辑,将所需要的信息放在session之中
            SmUsers user = userRepository.findOne(userDetails.getUsername());
            httpServletRequest.getSession().setAttribute(BaseConstants.USER_ID, userDetails.getUsername());
            //todo token 写法
            String token = null;
            try {
                token = JWTUtil.createJWT(user.getUserId(), user.getUserPwd(), System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpServletRequest.getSession().setAttribute(BaseConstants.TOKEN, token);
            List<DeptRel> allByDeptCodeOrWardCode = deptRelRepService.findAllByDeptCodeOrWardCode(user.getUserDept());
            if (allByDeptCodeOrWardCode.size() > 0 && allByDeptCodeOrWardCode != null) {
                DeptRel deptRel = allByDeptCodeOrWardCode.get(0);
                //父科室名称
                httpServletRequest.getSession().setAttribute(BaseConstants.FT_DEPT_ID, deptRel.getDeptCode());
                httpServletRequest.getSession().setAttribute(BaseConstants.FT_DEPT_NAME, deptRel.getDeptName());
            }

            RoleUser byUser = roleUserRepService.findByUser(user);
            String currentRoleId = null;
            if (byUser != null) {
                currentRoleId = byUser.getRole().getRoleId();
            }

            httpServletRequest.getSession().setAttribute(BaseConstants.CURRENT_ROLE_ID, currentRoleId);
            //设置session超时时间(2小时)
            httpServletRequest.getSession().setMaxInactiveInterval(2 * 60 * 60);
//            System.out.println("session登陆：=================" + httpServletRequest.getSession().getId());

            logger.info("登录成功");
            httpServletResponse.setHeader(BaseConstants.TOKEN, token);
            PrintWriter out = null;
            AtResponse resp = new AtResponse(System.currentTimeMillis());

            try {
                out = httpServletResponse.getWriter();
                resp.setResponseCode(ResponseCode.OK);
                out.print(JSON.toJSON(resp));
            } catch (IOException e) {
                resp.setResponseCode(ResponseCode.INERERROR);
                e.printStackTrace();
            } finally {
                out.close();
            }
        }
    }


    class CaptchaException extends AuthenticationException {

        public CaptchaException(String msg) {
            super(msg);
        }
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

}
