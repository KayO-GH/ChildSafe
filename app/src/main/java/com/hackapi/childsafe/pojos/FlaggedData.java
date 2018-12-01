package com.hackapi.childsafe.pojos;

import java.io.Serializable;

public class FlaggedData implements Serializable {
    String url;
    String dateTime;

    public FlaggedData(String url, String dateTime) {
        this.url = url;
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}