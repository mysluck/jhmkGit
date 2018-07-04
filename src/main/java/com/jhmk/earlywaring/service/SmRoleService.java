package com.jhmk.earlywaring.service;

import com.jhmk.earlywaring.config.BaseConstants;
import com.jhmk.earlywaring.entity.SmRole;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.SmRoleRepository;
import com.jhmk.earlywaring.entity.repository.service.SmRoleRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SmRoleService {
    @Autowired
    SmRoleRepService roleRepository;
    @Autowired
    SmUsersRepService userRepository;

    /**
     * 更新用户角色
     *
     * @param id
     * @param role
     */
    public void updateRoleUser(String id, String role) {
        SmUsers user = userRepository.findOne(id);
        user.setRoleId(role);
        userRepository.save(user);
    }




}
