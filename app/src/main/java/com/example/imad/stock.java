package com.example.imad;

public class stock {

    private String name;
    private String mktPrice;
    private String clsPrice;
    private String mktCap;
    private String chng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMktPrice() {
        return mktPrice;
    }

    public void setMktPrice(String mktPrice) {
        this.mktPrice = mktPrice;
    }

    public String getClsPrice() {
        return clsPrice;
    }

    public void setClsPrice(String clsPrice) {
        this.clsPrice = clsPrice;
    }

    public String getMktCap() {
        return mktCap;
    }

    public void setMktCap(String mktCap) {
        this.mktCap = mktCap;
    }

    public String getChng() {
        return chng;
    }

    public void setChng(String chng) {
        this.chng = chng;
    }

    public stock(String name, String mktPrice, String clsPrice, String mktCap, String chng) {
        this.name = name;
        this.mktPrice = mktPrice;
        this.clsPrice = clsPrice;
        this.mktCap = mktCap;
        this.chng = chng;
    }
}
