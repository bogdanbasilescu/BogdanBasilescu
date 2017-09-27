package ro.bbasilescu.bogdanbasilescu.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import timber.log.Timber;

public class PermissionUtils {
    public static final int REQUEST_CALL_PHONE = 100;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 200;

    /**
     * Ask for calling phoneNo permission
     *
     * @param activity The activity in which it is requested
     * @return true if they are granted or false if not
     */
    public static boolean askForCallPhonePermission(final Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission = ContextCompat.checkSelfPermission
                    (activity, Manifest.permission.CALL_PHONE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        Manifest.permission.CALL_PHONE)) {
                    showMessageRationale(activity, "You need to allow access to call permission"
                                    + " in order to grant you access to making calls",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestCallPhonePermisssion(activity);
                                }
                            });
                    return false;
                }
                requestCallPhonePermisssion(activity);
                return false;
            }
        }
        return true;
    }

    /**
     * Ask for writing to external storage permission
     *
     * @param activity The activity in which it is requested
     * @return true if they are granted or false if not
     */
    public static boolean askForWriteToExternalStoragePermission(final Activity activity) {
        if (!isExternalStorageWritable()) {
            Timber.d("External Storage not available");
            return false;
        } else {
            Timber.d("External Storage available");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ContextCompat.checkSelfPermission
                        (activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        showMessageRationale(activity, "You need to allow access to external storage"
                                        + " in order for the file to be saved to your SD card",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestExternalStoragePermission(activity);
                                    }
                                });
                        return false;
                    }
                    requestExternalStoragePermission(activity);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Request call phone permission
     *
     * @param activity The activity in which it is requested
     */
    private static void requestCallPhonePermisssion(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.CALL_PHONE},
                REQUEST_CALL_PHONE);
    }

    /**
     * Request external storage permission
     *
     * @param activity The activity in which it is requested
     */
    private static void requestExternalStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    /**
     * Display message rationale to inform the user the reason for requesting writing to external storage
     *
     * @param context    The context in which the message is displayed
     * @param message    The content of the message
     * @param okListener Ok button click listener
     */
    private static void showMessageRationale(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    /**
     * Check if external storage is available for read and write
     *
     * @return true/false if available/not available
     */
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
