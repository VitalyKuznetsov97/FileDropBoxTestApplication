package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model;

import static com.vitaly_kuznetsov.file_dropbox_test_application.presentation.constants.PresentationConstants.EMPTY;

public class EmptyModel implements IModel {
    @Override
    public int getType() {
        return EMPTY;
    }
}
