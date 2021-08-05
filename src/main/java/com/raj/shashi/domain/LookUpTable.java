package com.raj.shashi.domain;

import java.util.List;

public class LookUpTable {

    private String id;
    private List<String> idList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "LookUpTable{" +
                "id='" + id + '\'' +
                ", idList=" + idList +
                '}';
    }
}
