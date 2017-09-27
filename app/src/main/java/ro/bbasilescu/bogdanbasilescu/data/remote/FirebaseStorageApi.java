package ro.bbasilescu.bogdanbasilescu.data.remote;

public interface FirebaseStorageApi {
    void downloadFile(String fileUrlReference);

    void uploadFile(String filePath);
}
