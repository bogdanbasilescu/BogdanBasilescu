package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.presenter.AbsPresenter;

public class AboutPresenterImpl extends AbsPresenter<AboutContract.AboutView> implements AboutContract.AboutPresenter<AboutContract.AboutView> {

    private AboutModelImpl aboutModel;

    public AboutPresenterImpl(AboutFragment view) {
        super(view);
        aboutModel = AboutModelImpl.getInstance();
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void getUser() {
        User user = aboutModel.getUser();
        getView().displayUser(user);
    }

    @Override
    public void onProfilePhotoClicked() {
        getView().displayProfilePhotoImgPicker();
    }

    @Override
    public void getToast() {
        getView().showToast();
    }
}
