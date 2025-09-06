package androidx.constraintlayout.core.state;

public interface RegistryCallback {
    String currentLayoutInformation();

    String currentMotionScene();

    long getLastModified();

    void onDimensions(int arg1, int arg2);

    void onNewMotionScene(String arg1);

    void onProgress(float arg1);

    void setDrawDebug(int arg1);

    void setLayoutInformationMode(int arg1);
}

