package com.jhmk.earlywaring.webservice;

import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmDeptsRepService;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@WebService(serviceName = "userWebService", targetNamespace = "http://webservice.earlywaring.com/",
//        endpointInterface = "com.jhmk.earlywaring.webservice.UserWebServiceImpl")

@Component
public class UserWebServiceImpl implements UserWebService {

    private static final String Y = "y";
    private static final String N = "n";
    @Autowired
    SmDeptsRepService smDeptsRepService;
    @Autowired
    SmUsersRepService smUsersRepService;

    @Override
    public String addUser(SmUsers smUsers) {
        SmUsers save = smUsersRepService.save(smUsers);
        if (save == null) {
            return Y;
        } else {
            return N;
        }
    }

    @Override
    public String delUser(String id) {
        try {
            smUsersRepService.delete(id);
            return Y;
        } catch (Exception e) {
            return N;
        }
    }

    @Override
    public String addDept(SmDepts smDepts) {
        SmDepts save = smDeptsRepService.save(smDepts);
        if (save == null) {
            return Y;
        } else {
            return N;
        }
    }

    @Override
    public String delDept(String id) {
        try {
            smDeptsRepService.delete(id);
            return Y;
        } catch (Exception e) {
            return N;
        }
    }

}
