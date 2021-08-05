package com.raj.shashi.dao;

import com.couchbase.client.java.Scope;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.raj.shashi.couchbase.CouchbaseConnector;
import com.raj.shashi.domain.LookUpTable;
import com.raj.shashi.domain.User;

public class DbHelper{

    private Scope scope;
    private ObjectMapper mapper;

    public DbHelper(){
        this.scope = CouchbaseConnector.getScope();
        this.mapper = new ObjectMapper();
        this.mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public User find(String id){
        return this.scope.collection("_default").get(id).contentAs(User.class);
    }

    public User create(User user){
        this.scope.collection("_default").insert(user.getId(), user);
        return this.scope.collection("_default").get(user.getId()).contentAs(User.class);
    }

    public void create(LookUpTable lookUpTable){
        this.scope.collection("_default").insert(lookUpTable.getId(), lookUpTable);
    }

    public LookUpTable findLookUpTable(String id){
        return this.scope.collection("_default").get(id).contentAs(LookUpTable.class);
    }

    public void update(LookUpTable lookUpTable){
        this.scope.collection("_default").upsert(lookUpTable.getId(), lookUpTable);
    }

}
