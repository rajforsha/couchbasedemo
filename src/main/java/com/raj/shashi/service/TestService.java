package com.raj.shashi.service;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.raj.shashi.Constants;
import com.raj.shashi.dao.DbHelper;
import com.raj.shashi.domain.LookUpTable;
import com.raj.shashi.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        beforeCreate(user);
        afterCreate(user);
        return dbHelper.create(user);
    }

    public List<User> getUserByGender (String gender){
        String lookUpId = "";
        if(gender.equalsIgnoreCase("male")){
            lookUpId = Constants.maleIds;
        }
        else{
            lookUpId = Constants.femaleIds;
        }

        LookUpTable lookUpTable = dbHelper.findLookUpTable(lookUpId);
        List<User> list = new ArrayList<>();
        lookUpTable.getIdList().forEach(id->{
            list.add(dbHelper.find(id));
        });

        return list;
    }

    private void beforeCreate(User user){
        user.setId(String.valueOf(new Random().nextInt(1000)));
    }

    private void afterCreate(User  user){

        String lookUpId = "";
        if(user.getGender().equalsIgnoreCase("male")){
            lookUpId = Constants.maleIds;
        }
        else{
            lookUpId = Constants.femaleIds;
        }
        LookUpTable lookUpTable = null;

        try{
           lookUpTable = dbHelper.findLookUpTable(lookUpId);
            lookUpTable.getIdList().add(user.getId());
            dbHelper.update(lookUpTable);
        }
        catch(DocumentNotFoundException exception){
            lookUpTable = new LookUpTable();
            lookUpTable.setId(lookUpId);
            lookUpTable.setIdList(Arrays.asList(user.getId()));
            dbHelper.create(lookUpTable);
        }
    }
}
