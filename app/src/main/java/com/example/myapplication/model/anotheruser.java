package com.example.myapplication.model;

public class anotheruser {
    public String id, name,image;


    public  anotheruser(String id, String name, String image){
        this.id=id;
        this.name=name;
        this.image=image;
    }
    public anotheruser(){}
    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
