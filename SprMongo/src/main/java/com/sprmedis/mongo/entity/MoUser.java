package com.sprmedis.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("mo_user")//等同mysql中的表,collection值表示mongodb中集合的名称，不写默认为实体类名article
public class MoUser {
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("age")
    private int age;

    /************************************* Constructor *************************************/
    private MoUser() {
    }

    public MoUser(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MoUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
