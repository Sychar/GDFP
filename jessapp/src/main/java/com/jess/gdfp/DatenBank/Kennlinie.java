package com.jess.gdfp.DatenBank;

public class Kennlinie {
    private String Verfahren;
    private String Werkstoff;
    private String Draht;
    private String Gas;
    private String KennNR;



    public Kennlinie(String Verfahren, String Werkstoff, String  Draht, String Gas ,String KennNrR ){
        this.Verfahren = Verfahren;
        this.Werkstoff = Werkstoff;
        this.Gas = Gas;
        this.KennNR=KennNrR;
        this.Draht=Draht;
    }

    public String getDraht() {
        return Draht;
    }

    public String getGas() {
        return Gas;
    }

    public String getVerfahren() {
        return Verfahren;
    }

    public String getKennNR() {
        return KennNR;
    }

    public String getWerkstoff() {
        return Werkstoff;
    }
}



