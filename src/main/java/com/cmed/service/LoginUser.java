package com.cmed.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUser {

    Map<Integer, String> loggedInUser = new HashMap<>();

    public boolean loginUser(Integer userId, String loginId) {
        try{
            loggedInUser.put(userId, loginId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLoggedIn(Integer userId, String loginId){
        try{
            String id = loggedInUser.get(userId);

            if(id!=null && id.equals(loginId)) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean logoutUser(Integer userId) {
        try{
            loggedInUser.remove(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
