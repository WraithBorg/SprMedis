package com.sprmedis.react.entity;

public class Coffee {
    private String id;
    private String name;

    /************************************* Constructor *************************************/
    public Coffee() {
    }

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /************************************ Getter Setter ************************************/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
