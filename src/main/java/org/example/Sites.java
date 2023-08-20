package org.example;

import java.net.URL;
import java.util.Hashtable;

public class Sites {
    private Hashtable<URL, String> todaySites;
    private Hashtable<URL, String> yesterdaySites;

    public Sites(Hashtable<URL, String> todaySites, Hashtable<URL, String> yesterdaySites) {
        this.todaySites = todaySites;
        this.yesterdaySites = yesterdaySites;
        if (this.todaySites == null) {
            this.todaySites = new Hashtable<>();
        }
        if (this.yesterdaySites == null) {
            this.yesterdaySites = new Hashtable<>();
        }
    }

    public Hashtable<URL, String> getToday() {
        return todaySites;
    }

    public Hashtable<URL, String> getYesterday() {
        return yesterdaySites;
    }

    public void setToday(Hashtable<URL, String> todaySites) {
        this.todaySites = todaySites;
    }

    public void setYesterday(Hashtable<URL, String> yesterdaySites) {
        this.yesterdaySites = yesterdaySites;
    }
}
