package ro.bbasilescu.bogdanbasilescu.presentation.mvp;

public interface Presenter<V extends View> {
    // Get reference to View
    V getView();

    // Manage component Lifecycle
    void onCreate();

    void onActivityCreated();

    void onResume();

    void onPause();

    void onDestroy();

    void getToast();
}
