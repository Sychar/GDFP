package com.jess.gdfp.DatenBank;

public class Datenlogger {

    String param;
    String Vaule;

    public Datenlogger(String param, String vaule){
        this.param=param;
        this.Vaule=vaule;
    }

    public String getParam() {
        return param;
    }

    public String getVaule() {
        return Vaule;
    }
}
