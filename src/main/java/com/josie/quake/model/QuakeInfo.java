package com.josie.quake.model;

import java.util.Date;

/**
 * Created by Josie on 16/5/12.
 */
public class QuakeInfo {
    private int id;
    private String title;
    private String description;
    private String type;
    private int manager;
    private int status;
    private String jumpTo;
    private Date createTime;
    private Date verifyTime;
    private Date publishTime;

    public static enum Status {

        Disable(0), Enable(1), UNVERIFY(2);

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJumpTo() {
        return jumpTo;
    }

    public void setJumpTo(String jumpTo) {
        this.jumpTo = jumpTo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "QuakeInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", manager=" + manager +
                ", status=" + status +
                ", jumpTo='" + jumpTo + '\'' +
                ", createTime=" + createTime +
                ", verifyTime=" + verifyTime +
                ", publishTime=" + publishTime +
                '}';
    }
}
