package com.jess.gdfp.DatenBank;

public class Jobs  {
    String time ;
    String Num;
    String name;
    public Jobs(String time , String Num ,String name){
        this.name=name;
        this.Num=Num;
        this.time=time;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return Num;
    }

    public String getTime() {
        return time;
    }
}
