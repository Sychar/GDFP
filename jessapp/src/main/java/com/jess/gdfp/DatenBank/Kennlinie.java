package com.jess.gdfp.DatenBank;

public class Kennlinie {
    private String Verfahren;
    private String Werkstoff;
    private String Draht;
    private String Gas;



    public Kennlinie(String Verfahren, String Werkstoff, String Draht, String Gas) {
        this.Verfahren = Verfahren;
        this.Werkstoff = Werkstoff;
        this.Gas = Gas;
        this.Draht = Draht;
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

    public String getWerkstoff() {
        return Werkstoff;
    }
}



