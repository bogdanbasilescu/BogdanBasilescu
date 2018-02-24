package ro.bbasilescu.bogdanbasilescu.presentation.usecases.projects;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.Presenter;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.View;

public interface ProjectsContract {

    interface ProjectsPresenter<V extends View> extends Presenter<V> {
        void getProjects();

        void onProjectClicked();
    }

    interface ProjectsView<P extends Presenter> extends View<P> {
        void showProjects();

        void showProjectDetails();
    }
}
