package ro.bbasilescu.bogdanbasilescu.presentation.usecases.skills;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.presenter.AbsPresenter;

public class SkillsPresenterImpl extends AbsPresenter<SkillsContract.SkillsView> implements SkillsContract.SkillsPresenter<SkillsContract.SkillsView> {

    private SkillsModelImpl skillsModel;

    public SkillsPresenterImpl(SkillsContract.SkillsView view) {
        super(view);
        this.skillsModel = SkillsModelImpl.getInstance();

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
    public void getSkills() {

    }

    @Override
    public void getToast() {

    }
}
