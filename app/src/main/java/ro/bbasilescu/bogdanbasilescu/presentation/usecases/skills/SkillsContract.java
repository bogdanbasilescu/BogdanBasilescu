package ro.bbasilescu.bogdanbasilescu.presentation.usecases.skills;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.Presenter;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.View;

public interface SkillsContract {
    interface SkillsPresenter<V extends View> extends Presenter<V> {
        void getSkills();
    }

    interface SkillsView<P extends Presenter> extends View<P> {
        void displaySkills();
    }
}
