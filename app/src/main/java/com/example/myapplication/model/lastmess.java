package com.example.myapplication.model;

public class lastmess {
    String lastmess, name, img,id;
    public lastmess(String lastmess,String name,String img ,String id){
        this.img=img;
        this.lastmess=lastmess;
        this.name=name;
        this.id=id;

    }
    public lastmess(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastmess(String lastmess) {
        this.lastmess = lastmess;
    }

    public String getLastmess() {
        return lastmess;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
