package com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity;

import java.util.Date;

public class File implements Entity {

    private String name;
    private String size;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}