package com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;

import java.util.ArrayList;

public interface IFileRepository {

    File getLastChosenFile();
    ArrayList<Entity> getAllFiles(String... directory);
    void saveFile(File file);

}
