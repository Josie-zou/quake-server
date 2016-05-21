package com.josie.quake.model;

import java.util.Date;

/**
 * Created by Josie on 16/5/21.
 */
public class FilterRule {
    private int id;
    private String rule;
    private int operator;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
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
        return "FilterRule{" +
                "id=" + id +
                ", rule='" + rule + '\'' +
                ", operator=" + operator +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
