package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.RoleUser;
import com.jhmk.earlywaring.entity.RoleUserId;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.RoleUserRepository;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import com.jhmk.earlywaring.entity.repository.service.RoleUserRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SmRoleService {
    @Autowired
    SmRoleRepository roleRepository;
    @Autowired
    SmUsersRepService userRepository;
    @Autowired
    RoleUserRepService roleUserRepository;

    /**
     * 更新用户角色
     *
     * @param id
     * @param role
     */
    public void updateRoleUser(String id, String role) {
        SmUsers user = userRepository.findOne(id);
        RoleUser byUser = roleUserRepository.findByUser(user);
        roleUserRepository.delete(byUser);
        if (StringUtils.isNotBlank(role)) {
            RoleUser r = new RoleUser();
            RoleUserId roleUserId = new RoleUserId();
            roleUserId.setRoleId(role);
            roleUserId.setUserId(id);
            r.setUserRoleId(roleUserId);
            roleUserRepository.save(r);
        }
    }


    //当前机构及其子机构下的所有角色
    public Set<SmRole> findRoleByOrgId(String orgId) {
        Set<SmRole> roleList = roleRepository.findRoleByOrgId(orgId, BaseConstants.PARAMTER_STATUS_1);
        return roleList;
    }


    public String getOneRoleByUserId(String userId) {
        SmUsers user = userRepository.findOne(userId);
        List<RoleUser> roleUserByUserId = null;
        //roleUserRepository.getRoleUserByUserId(userId);
        String roleId = null;
//        if (roleUser != null ) {
//            roleId = roleUser.getUserRoleId().getRoleId();
//        }
        return roleId;
    }

}
