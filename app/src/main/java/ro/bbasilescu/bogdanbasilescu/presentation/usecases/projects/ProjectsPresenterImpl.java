package ro.bbasilescu.bogdanbasilescu.presentation.usecases.projects;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.presenter.AbsPresenter;

public class ProjectsPresenterImpl extends AbsPresenter<ProjectsContract.ProjectsView> implements ProjectsContract.ProjectsPresenter<ProjectsContract.ProjectsView> {

    private ProjectsModelImpl projectsModel;

    public ProjectsPresenterImpl(ProjectsContract.ProjectsView view) {
        super(view);
        projectsModel = ProjectsModelImpl.getInstance();
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
    public void onAttach() {

    }

    @Override
    public void getToast() {

    }

    @Override
    public void getProjects() {

    }

    @Override
    public void onProjectClicked() {

    }
}

