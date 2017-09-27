package ro.bbasilescu.bogdanbasilescu.presentation.mvp.model;

public interface BaseRequestCallback<T> {
    void onSucceeded(T response);

    void onFailed(int errorCode, String message);
}
