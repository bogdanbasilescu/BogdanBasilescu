package ro.bbasilescu.bogdanbasilescu.data.remote;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import ro.bbasilescu.bogdanbasilescu.BBApplication;
import ro.bbasilescu.bogdanbasilescu.utils.FileUtils;
import ro.bbasilescu.bogdanbasilescu.utils.NotificationUtils;
import timber.log.Timber;

public class FirebaseApiImpl implements FirebaseStorageApi {
    private static FirebaseApiImpl firebaseApi = null;
    private StorageReference storageReference;

    private FirebaseApiImpl() {
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public static FirebaseApiImpl getInstance() {
        if (firebaseApi == null) {
            firebaseApi = new FirebaseApiImpl();
        }
        return firebaseApi;
    }

    @VisibleForTesting
    @Override
    public void downloadFile(String fileUrlReference) {
        final File localFile;
        StorageReference gsReference = storageReference.getStorage()
                .getReferenceFromUrl(fileUrlReference);
        try {
            final String fileName = "BogdanBasilescuCV.pdf";
            localFile = FileUtils.getFile(Environment.DIRECTORY_DOWNLOADS, fileName);
            gsReference.getFile(localFile)
                    .addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            NotificationUtils.onDownloadProgressNotification(BBApplication.getInstance()
                                    , taskSnapshot, fileName, localFile);
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            Timber.d("Firebase local file created at " + localFile.toString());
                            Toast.makeText(BBApplication.getInstance(), "CV downloaded successfully!"
                                    , Toast.LENGTH_SHORT).show();
                            // CALLBACK FOR HANDLING download file
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // CALLBACK FOR HANDLING failed download file
                            Timber.d("Firebase local file not created " + exception.toString());
                            Toast.makeText(BBApplication.getInstance(), "CV download failed!"
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("VisibleForTests")
    @Override
    public void uploadFile(String filePath) {
        Uri file = Uri.fromFile(new File("path/to/" + filePath));
        StorageReference imageReference = storageReference.child(filePath);

        imageReference.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // CALLBACK FOR HANDLING upload file
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // CALLBACK FOR HANDLING failed upload file
                    }
                });
    }
}
