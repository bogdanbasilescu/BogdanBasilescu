package ro.bbasilescu.bogdanbasilescu.presentation.usecases.about;

import ro.bbasilescu.bogdanbasilescu.presentation.models.User;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.Presenter;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.View;

public interface AboutContract {

    interface AboutPresenter<V extends View> extends Presenter<V> {
        void getUser();
    }

    interface AboutView<P extends Presenter> extends View<P> {
        void displayUser(User user);
    }
}
