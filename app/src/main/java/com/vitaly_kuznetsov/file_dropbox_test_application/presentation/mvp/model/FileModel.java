package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model;

import java.util.Date;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.FILE;

/**
 * Class that represents a file in the presentation layer.
 */

public class FileModel implements IModel {

    private String name;
    private String url;
    private String size;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public int getType() {
        return FILE;
    }
}