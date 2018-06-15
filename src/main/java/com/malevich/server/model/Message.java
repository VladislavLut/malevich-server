package com.malevich.server.model;

public class Message {

    private String tableName;
    private Integer id;

    public Message() {

    }

    public Message(String tableName, Integer id) {
        this.tableName = tableName;
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
