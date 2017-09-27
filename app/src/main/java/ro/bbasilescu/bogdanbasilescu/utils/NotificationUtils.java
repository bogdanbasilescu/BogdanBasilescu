package ro.bbasilescu.bogdanbasilescu.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.google.firebase.storage.FileDownloadTask;

import java.io.File;
import java.util.Locale;

public class NotificationUtils {

    private static final int NOTIFICATION_DOWNLOAD_FILE = 100;
    private static final int REQUEST_OPEN_PDF_FILE = 101;

    @VisibleForTesting
    public static void onDownloadProgressNotification(final Context context,
                                                      FileDownloadTask.TaskSnapshot taskSnapshot,
                                                      final String fileName, final File file) {
        double fileDownloadProgress = (100.0 * taskSnapshot.getBytesTransferred())
                / taskSnapshot.getTotalByteCount();

        final String progress = String.format(Locale.ENGLISH, "%.2f", fileDownloadProgress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(android.R.drawable.stat_sys_download)
                                .setContentTitle("Downloading " + fileName)
                                .setContentText(" " + progress + "% completed");
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(NOTIFICATION_DOWNLOAD_FILE, mBuilder.build());
            }
        }).start();

        if (taskSnapshot.getBytesTransferred() == taskSnapshot.getTotalByteCount()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    Uri fileUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                        fileUri = FileProvider.getUriForFile(context, context.getApplicationContext()
                                .getPackageName() + ".provider", file);
                    } else {
                        fileUri = Uri.fromFile(file);
                    }
                    intent.setDataAndType(fileUri, "application/pdf");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    PendingIntent pendingIntent = PendingIntent
                            .getActivity(context, REQUEST_OPEN_PDF_FILE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                                    .setContentTitle("Downloaded " + fileName)
                                    .setContentText("Download completed!")
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true);
                    NotificationManager mNotificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                    mNotificationManager.notify(NOTIFICATION_DOWNLOAD_FILE, mBuilder.build());
                }
            }).start();
        }
    }
}
