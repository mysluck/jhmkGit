package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserService {
    private static final Logger logger = LoggerFactory.getLogger(RoleUserService.class);
    @Autowired
    SmRoleRepository smRoleRepository;
    @Autowired
    SmUsersRepService smUsersRepService;
    @Autowired
    RoleUserRepository roleUserRepository;

    /**
     * @param userId 用户id
     * @return role实体
     */
    public SmRole getRoleIdByUser(String userId) {
        SmUsers user = smUsersRepService.findOne(userId);
        if (user == null) {
            logger.error("用户名不存在或用户名状态错误");
            return null;
        }
        SmRole smRole = null;
                //roleUserRepository.findByUser(user).getRole();
        return smRole;

    }
}
