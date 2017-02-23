package com.example.zimpelman.sqlbirds;

/**
 * Created by Christopher on 2/21/2017.
 */

public class Bird {

    private int id;
    private String name;
    private String description;

    public Bird(){
        //empty constructor
    }

    public Bird(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public  Bird(String name, String description){
        this.name = name;
        this.description = description;
    }

    public  Bird(String name){
        this.name = name;
        this.description = "Unknown";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
