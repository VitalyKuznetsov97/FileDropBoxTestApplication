package com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity;

public class Directory implements Entity{

    private String name;
    private int amountOfFiles;
    private String pathLower;

    public String getPathLower() {
        return pathLower;
    }

    public void setPathLower(String pathLower) {
        this.pathLower = pathLower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfFiles() {
        return amountOfFiles;
    }

    public void setAmountOfFiles(int amountOfFiles) {
        this.amountOfFiles = amountOfFiles;
    }

}
