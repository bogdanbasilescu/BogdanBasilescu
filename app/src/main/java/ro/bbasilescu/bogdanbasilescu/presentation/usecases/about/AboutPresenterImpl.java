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
        //Mock user for now
        User user = new User();
        user.setName("Bogdan Basilescu");
        user.setDescription("Knowledge harvester, public speaker addicted to success " +
                "& driven to overcome anything.");
        user.setObjective("I am eager to inspire, coordinate and " +
                "motivate people in different projects in " +
                "order to achieve success through hard " +
                "work and dedication, therefore my " +
                "professional goal for the next 3 years is " +
                "to become a Product Manager.");
        getView().displayUser(user);
    }
}
