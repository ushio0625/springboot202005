package com.example;

public class Person {

    private Integer id; // AUTO_INCREMENT で ID を付与
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person(id=" + id + ", name=" + name + ")";
    }
}