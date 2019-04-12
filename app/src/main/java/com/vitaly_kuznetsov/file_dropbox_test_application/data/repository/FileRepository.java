package com.vitaly_kuznetsov.file_dropbox_test_application.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.google.gson.Gson;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.application.MyApplication;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.entity_mapper.EntityMapper;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.repository.IFileRepository;

import java.util.ArrayList;

import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.ACCESS_TOKEN;
import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.LAST_FILE;
import static com.vitaly_kuznetsov.file_dropbox_test_application.data.constants.DataConstants.SHARED_PREFS_PACKAGE;

public class FileRepository implements IFileRepository {

    @Override
    public File getLastChosenFile() {
        Context context = MyApplication.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_PACKAGE, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(LAST_FILE)) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString(LAST_FILE, "Error");
            return gson.fromJson(json, File.class);
        }
        return null;
    }

    @Override
    public ArrayList<Entity> getAllFiles(String... directory) {

        Context context = MyApplication.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_PACKAGE, Context.MODE_PRIVATE);

        DbxRequestConfig config = new DbxRequestConfig("ClientId.", "en_US");
        DbxClientV2 client = new DbxClientV2(config, sharedPreferences.getString(ACCESS_TOKEN, ""));

        try {
            ListFolderResult listFolderResult = client.files().listFolder(directory[0]);
            ArrayList<Entity> entities = new ArrayList<>();
            for (Metadata metadata : listFolderResult.getEntries()) {
                entities.add(EntityMapper.transform(metadata, client));
            }
            return entities;
        } catch (DbxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveFile(File file) {
        Context context = MyApplication.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_PACKAGE, Context.MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(file);
        prefsEditor.putString(LAST_FILE, json);

        prefsEditor.apply();
    }

}
