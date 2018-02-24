package ro.bbasilescu.bogdanbasilescu.presentation.usecases.skills;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.model.AbsBaseModel;

public class SkillsModelImpl extends AbsBaseModel {
    private static SkillsModelImpl instance;

    private SkillsModelImpl() {
    }

    public static SkillsModelImpl getInstance() {
        if (instance == null) instance = new SkillsModelImpl();
        return instance;
    }

}
