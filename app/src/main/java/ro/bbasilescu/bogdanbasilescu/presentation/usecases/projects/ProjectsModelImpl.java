package ro.bbasilescu.bogdanbasilescu.presentation.usecases.projects;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.model.AbsBaseModel;

public class ProjectsModelImpl extends AbsBaseModel {
    private static ProjectsModelImpl instance;

    private ProjectsModelImpl() {
    }

    public static ProjectsModelImpl getInstance() {
        if (instance == null) instance = new ProjectsModelImpl();
        return instance;
    }

}
