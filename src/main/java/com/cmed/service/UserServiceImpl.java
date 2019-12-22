package com.cmed.service;

import com.cmed.database.PrescriptionData;
import com.cmed.database.UserData;
import com.cmed.dto.*;
import com.cmed.entity.Prescription;
import com.cmed.entity.User;
import com.cmed.util.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserData userData;

    @Autowired
    LoginUser loginUser;


    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        try{
            User user = userData.findUserByNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());

            if(user!=null) {
                String random = RandomStringUtils.randomNumeric(5);
                if(loginUser.loginUser(user.getId(), random)){
                    LoginResponse response = new LoginResponse();
                    response.setLoginId(random);
                    response.setUserId(user.getId());
                    return response;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
