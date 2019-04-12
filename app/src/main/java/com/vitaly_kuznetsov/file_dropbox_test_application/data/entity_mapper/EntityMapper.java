package com.vitaly_kuznetsov.file_dropbox_test_application.data.entity_mapper;

import android.annotation.SuppressLint;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Directory;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;

public class EntityMapper {

    private static DbxClientV2 client;

    public static Entity transform(Metadata metadata, DbxClientV2 client) {
        EntityMapper.client = client;
        if (metadata instanceof FileMetadata) return transform((FileMetadata) metadata);
        else if (metadata instanceof FolderMetadata) return transform((FolderMetadata) metadata);
        else return null;
    }

    private static Entity transform(FileMetadata fileMetadata) {
        File file = new File();
        file.setName(fileMetadata.getName());
        file.setSize(convertBytes(fileMetadata.getSize()));
        file.setDate(fileMetadata.getClientModified());
        return file;
    }

    private static Entity transform(FolderMetadata folderMetadata) {
        Directory directory = new Directory();
        directory.setName(folderMetadata.getName());
        directory.setPathLower(folderMetadata.getPathLower());
        try {
            ListFolderResult listFolderResult = client.files().listFolder(folderMetadata.getPathLower());
            directory.setAmountOfFiles(listFolderResult.getEntries().size());
            return directory;
        } catch (DbxException e) {
            e.printStackTrace();
            return directory;
        }
    }

    @SuppressLint("DefaultLocale")
    private static String convertBytes(long bytes) {
        int unit = 1000;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = String.valueOf(("kMGTPE").charAt(exp-1));
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
