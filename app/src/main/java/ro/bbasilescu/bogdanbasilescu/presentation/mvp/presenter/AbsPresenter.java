package ro.bbasilescu.bogdanbasilescu.presentation.mvp.presenter;

import ro.bbasilescu.bogdanbasilescu.presentation.mvp.Presenter;
import ro.bbasilescu.bogdanbasilescu.presentation.mvp.View;

public abstract class AbsPresenter<V extends View> implements Presenter<V> {
    private V view;

    public AbsPresenter(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

    public abstract void onAttach();

    public abstract void onCreate();

    public abstract void onActivityCreated();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onDestroy();

    public abstract void onDetach();
}
