package com.google.firebase.messaging;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo.HeartBeat;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

class GmsRpc {
    static final String CMD_RST = "RST";
    static final String CMD_RST_FULL = "RST_FULL";
    static final String CMD_SYNC = "SYNC";
    static final String ERROR_INSTANCE_ID_RESET = "INSTANCE_ID_RESET";
    static final String ERROR_INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    static final String ERROR_INTERNAL_SERVER_ERROR_ALT = "InternalServerError";
    static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
    private static final String EXTRA_DELETE = "delete";
    private static final String EXTRA_ERROR = "error";
    private static final String EXTRA_IID_OPERATION = "iid-operation";
    private static final String EXTRA_REGISTRATION_ID = "registration_id";
    private static final String EXTRA_SCOPE = "scope";
    private static final String EXTRA_SENDER = "sender";
    private static final String EXTRA_SUBTYPE = "subtype";
    private static final String EXTRA_TOPIC = "gcm.topic";
    private static final String EXTRA_UNREGISTERED = "unregistered";
    static final String FIREBASE_IID_HEARTBEAT_TAG = "fire-iid";
    private static final String PARAM_APP_VER_CODE = "app_ver";
    private static final String PARAM_APP_VER_NAME = "app_ver_name";
    private static final String PARAM_CLIENT_VER = "cliv";
    private static final String PARAM_FIREBASE_APP_NAME_HASH = "firebase-app-name-hash";
    private static final String PARAM_FIS_AUTH_TOKEN = "Goog-Firebase-Installations-Auth";
    private static final String PARAM_GMP_APP_ID = "gmp_app_id";
    private static final String PARAM_GMS_VER = "gmsv";
    private static final String PARAM_HEARTBEAT_CODE = "Firebase-Client-Log-Type";
    private static final String PARAM_INSTANCE_ID = "appid";
    private static final String PARAM_OS_VER = "osv";
    private static final String PARAM_USER_AGENT = "Firebase-Client";
    private static final String SCOPE_ALL = "*";
    static final String TAG = "FirebaseMessaging";
    private static final String TOPIC_PREFIX = "/topics/";
    private final FirebaseApp app;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Provider heartbeatInfo;
    private final Metadata metadata;
    private final Rpc rpc;
    private final Provider userAgentPublisher;

    GmsRpc(FirebaseApp firebaseApp0, Metadata metadata0, Rpc rpc0, Provider provider0, Provider provider1, FirebaseInstallationsApi firebaseInstallationsApi0) {
        this.app = firebaseApp0;
        this.metadata = metadata0;
        this.rpc = rpc0;
        this.userAgentPublisher = provider0;
        this.heartbeatInfo = provider1;
        this.firebaseInstallations = firebaseInstallationsApi0;
    }

    GmsRpc(FirebaseApp firebaseApp0, Metadata metadata0, Provider provider0, Provider provider1, FirebaseInstallationsApi firebaseInstallationsApi0) {
        this(firebaseApp0, metadata0, new Rpc(firebaseApp0.getApplicationContext()), provider0, provider1, firebaseInstallationsApi0);
    }

    private static String base64UrlSafe(byte[] arr_b) {
        return Base64.encodeToString(arr_b, 11);
    }

    Task deleteToken() {
        Bundle bundle0 = new Bundle();
        bundle0.putString("delete", "1");
        return this.extractResponseWhenComplete(this.startRpc(Metadata.getDefaultSenderId(this.app), "*", bundle0));
    }

    private Task extractResponseWhenComplete(Task task0) {
        return task0.continueWith(new ProfileInstallReceiver..ExternalSyntheticLambda0(), (Task task0) -> this.handleResponse(((Bundle)task0.getResult(IOException.class))));
    }

    private String getHashedFirebaseAppName() {
        String s = this.app.getName();
        try {
            return GmsRpc.base64UrlSafe(MessageDigest.getInstance("SHA-1").digest(s.getBytes()));
        }
        catch(NoSuchAlgorithmException unused_ex) {
            return "[HASH-ERROR]";
        }
    }

    Task getToken() {
        return this.extractResponseWhenComplete(this.startRpc(Metadata.getDefaultSenderId(this.app), "*", new Bundle()));
    }

    private String handleResponse(Bundle bundle0) throws IOException {
        if(bundle0 == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String s = bundle0.getString("registration_id");
        if(s != null) {
            return s;
        }
        String s1 = bundle0.getString("unregistered");
        if(s1 != null) {
            return s1;
        }
        String s2 = bundle0.getString("error");
        if("RST".equals(s2)) {
            throw new IOException("INSTANCE_ID_RESET");
        }
        if(s2 != null) {
            throw new IOException(s2);
        }
        Log.w("FirebaseMessaging", "Unexpected response: " + bundle0, new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }

    // 去混淆评级： 低(30)
    static boolean isErrorMessageForRetryableError(String s) {
        return "SERVICE_NOT_AVAILABLE".equals(s) || "INTERNAL_SERVER_ERROR".equals(s) || "InternalServerError".equals(s);
    }

    // 检测为 Lambda 实现
    String lambda$extractResponseWhenComplete$0$com-google-firebase-messaging-GmsRpc(Task task0) throws Exception [...]

    private void setDefaultAttributesToBundle(String s, String s1, Bundle bundle0) throws ExecutionException, InterruptedException {
        bundle0.putString("scope", s1);
        bundle0.putString("sender", s);
        bundle0.putString("subtype", s);
        bundle0.putString("gmp_app_id", this.app.getOptions().getApplicationId());
        bundle0.putString("gmsv", Integer.toString(this.metadata.getGmsVersionCode()));
        bundle0.putString("osv", "33");
        bundle0.putString("app_ver", this.metadata.getAppVersionCode());
        bundle0.putString("app_ver_name", this.metadata.getAppVersionName());
        bundle0.putString("firebase-app-name-hash", this.getHashedFirebaseAppName());
        try {
            String s2 = ((InstallationTokenResult)Tasks.await(this.firebaseInstallations.getToken(false))).getToken();
            if(TextUtils.isEmpty(s2)) {
                Log.w("FirebaseMessaging", "FIS auth token is empty");
            }
            else {
                bundle0.putString("Goog-Firebase-Installations-Auth", s2);
            }
        }
        catch(ExecutionException | InterruptedException executionException0) {
            Log.e("FirebaseMessaging", "Failed to get FIS auth token", executionException0);
        }
        bundle0.putString("appid", ((String)Tasks.await(this.firebaseInstallations.getId())));
        bundle0.putString("cliv", "fcm-23.2.1");
        HeartBeatInfo heartBeatInfo0 = (HeartBeatInfo)this.heartbeatInfo.get();
        UserAgentPublisher userAgentPublisher0 = (UserAgentPublisher)this.userAgentPublisher.get();
        if(heartBeatInfo0 != null && userAgentPublisher0 != null) {
            HeartBeat heartBeatInfo$HeartBeat0 = heartBeatInfo0.getHeartBeatCode("fire-iid");
            if(heartBeatInfo$HeartBeat0 != HeartBeat.NONE) {
                bundle0.putString("Firebase-Client-Log-Type", Integer.toString(heartBeatInfo$HeartBeat0.getCode()));
                bundle0.putString("Firebase-Client", userAgentPublisher0.getUserAgent());
            }
        }
    }

    private Task startRpc(String s, String s1, Bundle bundle0) {
        try {
            this.setDefaultAttributesToBundle(s, s1, bundle0);
            return this.rpc.send(bundle0);
        }
        catch(InterruptedException interruptedException0) {
            return Tasks.forException(interruptedException0);
        }
        catch(ExecutionException interruptedException0) {
            return Tasks.forException(interruptedException0);
        }
    }

    Task subscribeToTopic(String s, String s1) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("gcm.topic", "/topics/" + s1);
        return this.extractResponseWhenComplete(this.startRpc(s, "/topics/" + s1, bundle0));
    }

    Task unsubscribeFromTopic(String s, String s1) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("gcm.topic", "/topics/" + s1);
        bundle0.putString("delete", "1");
        return this.extractResponseWhenComplete(this.startRpc(s, "/topics/" + s1, bundle0));
    }
}

