package ro.bbasilescu.bogdanbasilescu.utils;

import android.os.Environment;

import java.io.File;

import timber.log.Timber;

public class FileUtils {
    public static File getFile(String externalDirectory, String fileName) {
        // Get the directory for the app's private pictures directory.
        File fileRootPath = new File(Environment
                .getExternalStoragePublicDirectory(externalDirectory), "BogdanBasilescu");
        if (!fileRootPath.mkdirs()) {
            Timber.d("Directory not created");
        } else{
            Timber.d("Directory created");
        }
        return new File(fileRootPath, fileName);
    }
}
