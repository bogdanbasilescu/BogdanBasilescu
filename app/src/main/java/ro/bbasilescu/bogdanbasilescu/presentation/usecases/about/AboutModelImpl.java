package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.model.AbsBaseModel;

public class AboutModelImpl extends AbsBaseModel implements AboutModel {
    private User user;
    private static AboutModelImpl instance;

    private AboutModelImpl() {
        user = new User();
    }

    public static AboutModelImpl getInstance() {
        if (instance == null) {
            instance = new AboutModelImpl();
        }
        return instance;
    }
}
