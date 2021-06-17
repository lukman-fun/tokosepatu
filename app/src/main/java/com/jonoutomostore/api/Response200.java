package com.jonoutomostore.api;

import com.google.gson.annotations.SerializedName;

public class Response200 {
    @SerializedName("status")
    String status;

    @SerializedName("msg")
    String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
