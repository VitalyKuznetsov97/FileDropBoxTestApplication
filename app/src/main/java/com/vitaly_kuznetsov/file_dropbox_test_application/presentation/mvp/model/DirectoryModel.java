package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.DIRECTORY;

/**
 * Class that represents a directory in the presentation layer.
 */

public class DirectoryModel implements IModel {

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

    @Override
    public int getType() {
        return DIRECTORY;
    }
}
