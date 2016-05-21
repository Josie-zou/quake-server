package com.josie.quake.model;

import java.util.Date;

/**
 * Created by Josie on 16/5/21.
 */
public class FilterWhiteList {
    private int id;
    private String url;
    private int operater;
    private int status;
    private Date createTime;

    public static enum Status {

        Disable(2), Enable(1);

        private int value;

        Status(
                int value) {
            this.value = value;
        }

        public int toInt() {
            return value;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOperater() {
        return operater;
    }

    public void setOperater(int operater) {
        this.operater = operater;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FilterWhiteList{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", operater=" + operater +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
