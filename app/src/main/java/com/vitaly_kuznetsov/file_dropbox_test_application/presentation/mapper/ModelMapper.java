package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mapper;

import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Directory;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.DirectoryModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.EmptyModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;

public class ModelMapper {

    public static IModel transform(Entity entity) {
        if (entity instanceof File) return transform((File) entity);
        else if (entity instanceof Directory) return transform((Directory) entity);
        return new EmptyModel();
    }

    public static File transform(FileModel fileModel) {
        File file = new File();
        file.setName(fileModel.getName());
        file.setDate(fileModel.getDate());
        file.setSize(fileModel.getSize());
        return file;
    }

    private static IModel transform(File file) {
        FileModel fileModel = new FileModel();
        fileModel.setSize(file.getSize());
        fileModel.setName(file.getName());
        fileModel.setDate(file.getDate());
        return fileModel;
    }

    private static IModel transform(Directory directory) {
        DirectoryModel directoryModel = new DirectoryModel();
        directoryModel.setName(directory.getName());
        directoryModel.setAmountOfFiles(directory.getAmountOfFiles());
        directoryModel.setPathLower(directory.getPathLower());
        return directoryModel;
    }
}
