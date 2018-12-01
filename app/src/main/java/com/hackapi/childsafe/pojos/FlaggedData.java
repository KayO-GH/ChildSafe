package com.hackapi.childsafe.pojos;

import java.io.Serializable;

public class FlaggedData implements Serializable {
    String imgUrl;
    String initiatorUrl;
    String dateTime;

    public FlaggedData(String imgUrl, String initiatorUrl, String dateTime) {
        this.imgUrl = imgUrl;
        this.initiatorUrl = initiatorUrl;
        this.dateTime = dateTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getInitiatorUrl() {
        return initiatorUrl;
    }

    public void setInitiatorUrl(String initiatorUrl) {
        this.initiatorUrl = initiatorUrl;
    }
}