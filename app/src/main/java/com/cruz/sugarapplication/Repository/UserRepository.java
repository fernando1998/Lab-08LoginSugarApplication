package com.cruz.sugarapplication.Repository;

import com.cruz.sugarapplication.Model.User;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FERNANDO on 16/10/2017.
 */

public class UserRepository {


    //redundancia
    public static List<User> list(){
        List<User> users = SugarRecord.listAll(User.class);
        return users;
    }
    //-------------------------------------------------------------------------
    public static User login(String email, String password){
        List<User> users = SugarRecord.listAll(User.class);
        for (User user : users){
            if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

//--------------------------------------------------------------------------------------
public static User getUser(String fullname){
    List<User> users = SugarRecord.listAll(User.class);
    for (User user : users){
        if(user.getFullname().equalsIgnoreCase(fullname)){
            return user;
        }
    }
    return null;
}



    //------------------------------------------------------

    public static User read(Long id){
        User user = SugarRecord.findById(User.class, id);
        return user;
    }

    public static void create(String fullname, String email, String password){
        User user = new User(fullname, email, password);
        SugarRecord.save(user);
    }

    public static void update(String fullname, String email, String password, Long id){
        User user = SugarRecord.findById(User.class, id);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }

    public static void delete(Long id){
        User user = SugarRecord.findById(User.class, id);
        SugarRecord.delete(user);
    }

}
