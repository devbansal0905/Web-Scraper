package com.example.imad;

public class job {
    String name;
    String cName;
    String cLoc;
    String date;
    String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcLoc() {
        return cLoc;
    }

    public void setcLoc(String cLoc) {
        this.cLoc = cLoc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public job(String name, String cName, String cLoc, String date, String link) {
        this.name = name;
        this.cName = cName;
        this.cLoc = cLoc;
        this.date = date;
        this.link = link;
    }


}
