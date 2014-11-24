package com.mredrock.cyxbs.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by David on 2014/11/4.
 */
public class Result {

    private String info;
    private int status;
    private String version;

    public Result() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
