package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.model.AbsBaseModel;

public class AboutModelImpl extends AbsBaseModel {
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

    User getUser() {
        if (user != null) {
            user.setName("Bogdan Basilescu");
            user.setDescription("Knowledge harvester, public speaker addicted to success " +
                    "& driven to overcome anything.");
            user.setObjective("I am eager to inspire, coordinate and " +
                    "motivate people in different projects in " +
                    "order to achieve success through hard " +
                    "work and dedication, therefore my " +
                    "professional goal for the next 3 years is " +
                    "to become a Product Manager.");
        }
        return user;
    }
}
