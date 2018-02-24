package ro.bbasilescu.bogdanbasilescu.presentation.mvp;

public interface View<P extends Presenter> {
    // Get reference to Presenter
    P getPresenter();

    // Display Toast notification
    void showToast();
}
