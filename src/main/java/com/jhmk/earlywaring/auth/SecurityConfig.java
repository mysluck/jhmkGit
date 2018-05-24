//package com.jhmk.earlywaring.auth;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jhmk.earlywaring.config.BaseConstants;
//import com.jhmk.earlywaring.entity.DeptRel;
//import com.jhmk.earlywaring.entity.RoleUser;
//import com.jhmk.earlywaring.entity.SmUsers;
//import com.jhmk.earlywaring.entity.repository.service.DeptRelRepService;
//import com.jhmk.earlywaring.entity.repository.service.RoleUserRepService;
//import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
//import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
//import com.jhmk.earlywaring.model.AtResponse;
//import com.jhmk.earlywaring.model.ResponseCode;
//import com.jhmk.earlywaring.util.JWTUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//@EnableWebSecurity()
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Autowired
//    private AuthUserDetailsServiceImpl authUserDetailsServiceImpl;
//
//
//    @Autowired
//    SmUsersRepService smUsersRepService;
//    @Autowired
//    SmUsersRepService userRepository;
//    @Autowired
//    RoleUserRepService roleUserRepService;
//    @Autowired
//    DeptRelRepService deptRelRepService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(authUserDetailsServiceImpl);
//
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//
//                .antMatchers("/warn/users/**", "/warn/rule/**", "/warn/dept/**", "/warn/login").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().csrf().disable()
//                // 基于token，所以不需要session
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                //开启匿名访问
//                .anonymous().and()
//                //允许跨域
//                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
//                //验证token
//                .addFilterAfter(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//}
