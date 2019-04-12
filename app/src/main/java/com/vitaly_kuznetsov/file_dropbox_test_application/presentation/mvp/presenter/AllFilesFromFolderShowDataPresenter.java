package com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.vitaly_kuznetsov.file_dropbox_test_application.data.repository.FileRepository;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.Entity;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.entity.File;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.DefaultObserver;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.SaveFileUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.domain.interactor.ShowAllFilesFromFolderUseCase;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mapper.ModelMapper;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.DirectoryModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.FileModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.mvp.model.IModel;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UIThread;
import com.vitaly_kuznetsov.file_dropbox_test_application.presentation.thread.UseCaseExecutionThread;

import java.util.ArrayList;

@InjectViewState
public class AllFilesFromFolderShowDataPresenter extends AbstractShowDataPresenter{

    private ShowAllFilesFromFolderUseCase showAllFilesFromFolder;
    private SaveFileUseCase saveFileUseCase;
    private ArrayList<String> path = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        showAllFilesFromFolder = new ShowAllFilesFromFolderUseCase(new UseCaseExecutionThread(), new UIThread(),
                new FileRepository());
        saveFileUseCase = new SaveFileUseCase(new UseCaseExecutionThread(), new UIThread(),
                new FileRepository());
        disposeOnDestroy(showAllFilesFromFolder);
        disposeOnDestroy(saveFileUseCase);
        path.add("");
        refreshData();
    }

    @Override
    public void showData(ArrayList<Entity> entities) {
        super.showData(entities);
        getViewState().adaptUi(path.get(path.size() - 1));
    }

    @Override
    void startShowDataUseCase() {
        showAllFilesFromFolder.execute(new LoadDataUseCaseObserver(this), path.get(path.size() - 1));
    }

    private void startSaveFileUseCase(File file) {
        saveFileUseCase.execute(new SaveFileObserver(this), file);
    }

    final class SaveFileObserver extends DefaultObserver<Object> {

        private AllFilesFromFolderShowDataPresenter presenter;

        SaveFileObserver(AllFilesFromFolderShowDataPresenter presenter) {
            this.presenter = presenter;
        }

        @Override public void onComplete() {
            presenter.fileSaved();
        }
    }

    private void fileSaved(){
        getViewState().finishActivity();
    }

    public void onItemClicked(IModel iModel){
        if (iModel instanceof DirectoryModel) {
            path.add(((DirectoryModel) iModel).getPathLower());
            refreshData();
        }
        else if (iModel instanceof FileModel) {
            FileModel fileModel = (FileModel) iModel;
            startSaveFileUseCase(ModelMapper.transform(fileModel));
        }
    }

    public boolean onBackPressed(){
        if(path.size() > 1) {
            path.remove(path.size() - 1);
            refreshData();
            return false;
        }
        return true;
    }
}