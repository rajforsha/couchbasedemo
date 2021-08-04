package com.raj.shashi.service;

import com.raj.shashi.dao.DbHelper;
import com.raj.shashi.domain.User;

import java.util.Random;

public class TestService {

    private DbHelper dbHelper;

    public TestService(){
       this.dbHelper = new DbHelper();
    }

    public User getUserById(String id){
        return dbHelper.find(id);
    }

    public User createUser(User user){
        user.setId(String.valueOf(new Random().nextInt(1000)));
        return dbHelper.create(user);
    }
}
