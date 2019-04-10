package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.ERROR;

/**
 * Class that represents an error in the presentation layer.
 */
public class ErrorModel implements IModel {

    private String message;

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    @Override
    public int getType() {
        return ERROR;
    }
}
