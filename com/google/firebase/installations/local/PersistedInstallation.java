package com.google.firebase.installations.local;

import com.google.firebase.FirebaseApp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class PersistedInstallation {
    public static enum RegistrationStatus {
        ATTEMPT_MIGRATION,
        NOT_GENERATED,
        UNREGISTERED,
        REGISTERED,
        REGISTER_ERROR;

    }

    private static final String AUTH_TOKEN_KEY = "AuthToken";
    private static final String EXPIRES_IN_SECONDS_KEY = "ExpiresInSecs";
    private static final String FIREBASE_INSTALLATION_ID_KEY = "Fid";
    private static final String FIS_ERROR_KEY = "FisError";
    private static final String PERSISTED_STATUS_KEY = "Status";
    private static final String REFRESH_TOKEN_KEY = "RefreshToken";
    private static final String SETTINGS_FILE_NAME_PREFIX = "PersistedInstallation";
    private static final String TOKEN_CREATION_TIME_IN_SECONDS_KEY = "TokenCreationEpochInSecs";
    private File dataFile;
    private final FirebaseApp firebaseApp;

    public PersistedInstallation(FirebaseApp firebaseApp0) {
        this.firebaseApp = firebaseApp0;
    }

    public void clearForTesting() {
        this.getDataFile().delete();
    }

    private File getDataFile() {
        if(this.dataFile == null) {
            synchronized(this) {
                if(this.dataFile == null) {
                    this.dataFile = new File(this.firebaseApp.getApplicationContext().getFilesDir(), "PersistedInstallation." + this.firebaseApp.getPersistenceKey() + ".json");
                }
                return this.dataFile;
            }
        }
        return this.dataFile;
    }

    public PersistedInstallationEntry insertOrUpdatePersistedInstallationEntry(PersistedInstallationEntry persistedInstallationEntry0) {
        try {
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("Fid", persistedInstallationEntry0.getFirebaseInstallationId());
            jSONObject0.put("Status", persistedInstallationEntry0.getRegistrationStatus().ordinal());
            jSONObject0.put("AuthToken", persistedInstallationEntry0.getAuthToken());
            jSONObject0.put("RefreshToken", persistedInstallationEntry0.getRefreshToken());
            jSONObject0.put("TokenCreationEpochInSecs", persistedInstallationEntry0.getTokenCreationEpochInSecs());
            jSONObject0.put("ExpiresInSecs", persistedInstallationEntry0.getExpiresInSecs());
            jSONObject0.put("FisError", persistedInstallationEntry0.getFisError());
            File file0 = File.createTempFile("PersistedInstallation", "tmp", this.firebaseApp.getApplicationContext().getFilesDir());
            FileOutputStream fileOutputStream0 = new FileOutputStream(file0);
            fileOutputStream0.write(jSONObject0.toString().getBytes("UTF-8"));
            fileOutputStream0.close();
            file0.renameTo(this.getDataFile());
        }
        catch(JSONException | IOException unused_ex) {
        }
        return persistedInstallationEntry0;
    }

    private JSONObject readJSONFromFile() {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        byte[] arr_b = new byte[0x4000];
        try(FileInputStream fileInputStream0 = new FileInputStream(this.getDataFile())) {
            while(true) {
                int v = fileInputStream0.read(arr_b, 0, 0x4000);
                if(v < 0) {
                    return new JSONObject(byteArrayOutputStream0.toString());
                }
                byteArrayOutputStream0.write(arr_b, 0, v);
            }
        }
        catch(IOException | JSONException unused_ex) {
            return new JSONObject();
        }
    }

    public PersistedInstallationEntry readPersistedInstallationEntryValue() {
        JSONObject jSONObject0 = this.readJSONFromFile();
        String s = jSONObject0.optString("Fid", null);
        int v = jSONObject0.optInt("Status", RegistrationStatus.ATTEMPT_MIGRATION.ordinal());
        String s1 = jSONObject0.optString("AuthToken", null);
        String s2 = jSONObject0.optString("RefreshToken", null);
        long v1 = jSONObject0.optLong("TokenCreationEpochInSecs", 0L);
        long v2 = jSONObject0.optLong("ExpiresInSecs", 0L);
        String s3 = jSONObject0.optString("FisError", null);
        return PersistedInstallationEntry.builder().setFirebaseInstallationId(s).setRegistrationStatus(RegistrationStatus.values()[v]).setAuthToken(s1).setRefreshToken(s2).setTokenCreationEpochInSecs(v1).setExpiresInSecs(v2).setFisError(s3).build();
    }
}

