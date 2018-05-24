//package com.jhmk.earlywaring.auth;
//
//import com.jhmk.earlywaring.entity.SmUsers;
//import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//
//
//@Service
//public class AuthUserDetailsServiceImpl implements UserDetailsService {
//    /**
//     * Logger for this class
//     */
//    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailsServiceImpl.class);
//
//
//    @Autowired
//    SmUsersRepService userRepo;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//
//        SmUsers admin = null;
//        try {
//            admin = userRepo.findOne(userName);
//            logger.debug(admin.toString());
//        } catch (DataAccessException e) {
//            logger.error(e.getMessage());
//            throw new UsernameNotFoundException(userName + " not exsit");
//        }
//        UserDetails userDetail = null;
//        // 获取权限
//        GrantedAuthority authories = new SimpleGrantedAuthority("ROLE_ADMIN");
//        if (admin != null) {
//            userDetail = new org.springframework.security.core.userdetails.User(
//                    admin.getUserId(), admin.getUserPwd(), true, true, true, true,
//                    Arrays.asList(authories));
//        }
//
//        return userDetail;
//    }
//}
