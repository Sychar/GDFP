package com.jess.gdfp.DatenBank;

public class Users {
    private   String name;
    private  String preveling;
    int COUNT;
    int id ;

    public Users(){

    }
    public Users(String name , String preveling){
        this.name=name;
        this.preveling=preveling;
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }

    public String getName() {
        return name;
    }

    public String getPreveling() {
        return preveling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreveling(String preveling) {
        this.preveling = preveling;
    }
}
