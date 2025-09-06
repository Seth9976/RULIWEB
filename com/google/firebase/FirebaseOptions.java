package com.google.firebase;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.util.Strings;

public final class FirebaseOptions {
    public static final class Builder {
        private String apiKey;
        private String applicationId;
        private String databaseUrl;
        private String gaTrackingId;
        private String gcmSenderId;
        private String projectId;
        private String storageBucket;

        public Builder() {
        }

        public Builder(FirebaseOptions firebaseOptions0) {
            this.applicationId = firebaseOptions0.applicationId;
            this.apiKey = firebaseOptions0.apiKey;
            this.databaseUrl = firebaseOptions0.databaseUrl;
            this.gaTrackingId = firebaseOptions0.gaTrackingId;
            this.gcmSenderId = firebaseOptions0.gcmSenderId;
            this.storageBucket = firebaseOptions0.storageBucket;
            this.projectId = firebaseOptions0.projectId;
        }

        public FirebaseOptions build() {
            return new FirebaseOptions(this.applicationId, this.apiKey, this.databaseUrl, this.gaTrackingId, this.gcmSenderId, this.storageBucket, this.projectId, null);
        }

        public Builder setApiKey(String s) {
            this.apiKey = Preconditions.checkNotEmpty(s, "ApiKey must be set.");
            return this;
        }

        public Builder setApplicationId(String s) {
            this.applicationId = Preconditions.checkNotEmpty(s, "ApplicationId must be set.");
            return this;
        }

        public Builder setDatabaseUrl(String s) {
            this.databaseUrl = s;
            return this;
        }

        public Builder setGaTrackingId(String s) {
            this.gaTrackingId = s;
            return this;
        }

        public Builder setGcmSenderId(String s) {
            this.gcmSenderId = s;
            return this;
        }

        public Builder setProjectId(String s) {
            this.projectId = s;
            return this;
        }

        public Builder setStorageBucket(String s) {
            this.storageBucket = s;
            return this;
        }
    }

    private static final String API_KEY_RESOURCE_NAME = "google_api_key";
    private static final String APP_ID_RESOURCE_NAME = "google_app_id";
    private static final String DATABASE_URL_RESOURCE_NAME = "firebase_database_url";
    private static final String GA_TRACKING_ID_RESOURCE_NAME = "ga_trackingId";
    private static final String GCM_SENDER_ID_RESOURCE_NAME = "gcm_defaultSenderId";
    private static final String PROJECT_ID_RESOURCE_NAME = "project_id";
    private static final String STORAGE_BUCKET_RESOURCE_NAME = "google_storage_bucket";
    private final String apiKey;
    private final String applicationId;
    private final String databaseUrl;
    private final String gaTrackingId;
    private final String gcmSenderId;
    private final String projectId;
    private final String storageBucket;

    private FirebaseOptions(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        Preconditions.checkState(!Strings.isEmptyOrWhitespace(s), "ApplicationId must be set.");
        this.applicationId = s;
        this.apiKey = s1;
        this.databaseUrl = s2;
        this.gaTrackingId = s3;
        this.gcmSenderId = s4;
        this.storageBucket = s5;
        this.projectId = s6;
    }

    FirebaseOptions(String s, String s1, String s2, String s3, String s4, String s5, String s6, com.google.firebase.FirebaseOptions.1 firebaseOptions$10) {
        this(s, s1, s2, s3, s4, s5, s6);
    }

    // 去混淆评级： 中等(80)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof FirebaseOptions ? Objects.equal(this.applicationId, ((FirebaseOptions)object0).applicationId) && Objects.equal(this.apiKey, ((FirebaseOptions)object0).apiKey) && Objects.equal(this.databaseUrl, ((FirebaseOptions)object0).databaseUrl) && Objects.equal(this.gaTrackingId, ((FirebaseOptions)object0).gaTrackingId) && Objects.equal(this.gcmSenderId, ((FirebaseOptions)object0).gcmSenderId) && Objects.equal(this.storageBucket, ((FirebaseOptions)object0).storageBucket) && Objects.equal(this.projectId, ((FirebaseOptions)object0).projectId) : false;
    }

    public static FirebaseOptions fromResource(Context context0) {
        StringResourceValueReader stringResourceValueReader0 = new StringResourceValueReader(context0);
        String s = stringResourceValueReader0.getString("google_app_id");
        return TextUtils.isEmpty(s) ? null : new FirebaseOptions(s, stringResourceValueReader0.getString("google_api_key"), stringResourceValueReader0.getString("firebase_database_url"), stringResourceValueReader0.getString("ga_trackingId"), stringResourceValueReader0.getString("gcm_defaultSenderId"), stringResourceValueReader0.getString("google_storage_bucket"), stringResourceValueReader0.getString("project_id"));
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getDatabaseUrl() {
        return this.databaseUrl;
    }

    public String getGaTrackingId() {
        return this.gaTrackingId;
    }

    public String getGcmSenderId() {
        return this.gcmSenderId;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getStorageBucket() {
        return this.storageBucket;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.applicationId, this.apiKey, this.databaseUrl, this.gaTrackingId, this.gcmSenderId, this.storageBucket, this.projectId});
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("applicationId", this.applicationId).add("apiKey", this.apiKey).add("databaseUrl", this.databaseUrl).add("gcmSenderId", this.gcmSenderId).add("storageBucket", this.storageBucket).add("projectId", this.projectId).toString();
    }

    class com.google.firebase.FirebaseOptions.1 {
    }

}

