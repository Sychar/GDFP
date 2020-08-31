package com.jess.gdfp.View;

public class K_linie {
    private  String name;
    private  String Numm;
    private  int id;
    private int count;

    public K_linie(){

    }
    public K_linie(String name ){
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getNumm() {
        return Numm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNumm(String numm) {
        Numm = numm;
    }
}

