package com.jess.gdfp.DatenBank;

public class Kennlinie  {
    String name ;
    String wert;

    public   Kennlinie(String name , String wert){
        this.name=name;
        this.wert=wert;
    }

    public String getName() {
        return name;
    }

    public String getWert() {
        return wert;
    }
}
