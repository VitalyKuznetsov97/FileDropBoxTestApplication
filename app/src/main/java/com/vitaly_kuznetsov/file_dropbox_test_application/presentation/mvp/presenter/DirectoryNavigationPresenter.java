package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mapper.ModelMapper;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.DirectoryModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.view.IDirectoryNavigationView;

@InjectViewState
public class DirectoryNavigationPresenter extends BasePresenter<IDirectoryNavigationView> {

    private AllFilesFromFolderShowDataPresenter allFilesFromFolderShowDataPresenter;

    public void init(AllFilesFromFolderShowDataPresenter allFilesFromFolderShowDataPresenter){
        this.allFilesFromFolderShowDataPresenter = allFilesFromFolderShowDataPresenter;
    }

    public void onItemClicked(IModel iModel){
        if (iModel instanceof DirectoryModel) {
            allFilesFromFolderShowDataPresenter.setPath(((DirectoryModel) iModel).getPathLower());
            String path = allFilesFromFolderShowDataPresenter.getPath();
            getViewState().adaptUi(path);
            allFilesFromFolderShowDataPresenter.refreshData();
        }
        else if (iModel instanceof FileModel) {
            FileModel fileModel = (FileModel) iModel;
            allFilesFromFolderShowDataPresenter.startSaveFileUseCase(ModelMapper.transform(fileModel));
        }
    }

    public boolean onBackPressed(){
        if(!allFilesFromFolderShowDataPresenter.isLoading() && allFilesFromFolderShowDataPresenter.pathIsEmpty()) {
            allFilesFromFolderShowDataPresenter.removeLastPath();
            getViewState().adaptUi(allFilesFromFolderShowDataPresenter.getPath());
            allFilesFromFolderShowDataPresenter.refreshData();
            return false;
        }
        return true;
    }
}
