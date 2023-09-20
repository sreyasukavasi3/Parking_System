package com.example.helloworld.Util;

import com.example.helloworld.model.User;

public class UserValidator {
    public static boolean isValidUser(User user){
        if(user.getEmail()==null || user.getEmail()=="")
            return false;
        else return true;
    }
}
