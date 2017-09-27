package ro.bbasilescu.bogdanbasilescu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ro.bbasilescu.bogdanbasilescu.BBApplication;

public class ConnectionUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BBApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
