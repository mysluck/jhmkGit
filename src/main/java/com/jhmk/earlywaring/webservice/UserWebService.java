package com.jhmk.earlywaring.webservice;

import com.jhmk.earlywaring.entity.SmDepts;
import com.jhmk.earlywaring.entity.SmUsers;
import com.jhmk.earlywaring.entity.repository.service.SmUsersRepService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService()
public interface UserWebService {


    String addUser(SmUsers smUsers);
    String delUser(String id);
    String addDept(SmDepts smDepts);
    String delDept(String id);

}
