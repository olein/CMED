package com.cmed.database;

import com.cmed.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserData {

    List<User> userList = new ArrayList<>();

    public UserData() {
        User adminUser = new User();
        adminUser.setId(1);
        adminUser.setUserName("Admin");
        adminUser.setPassword("admin");
        userList.add(adminUser);
    }

    public User findUserByNameAndPassword(String name, String password){
        try{
            return userList.stream().filter(ud -> ud.getUserName().equals(name) &&
                    ud.getPassword().equals(password)).findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
