package com.google.firebase.installations.local;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class IidStore {
    private static final String[] ALLOWABLE_SCOPES = null;
    private static final String IID_SHARED_PREFS_NAME = "com.google.android.gms.appid";
    private static final String JSON_ENCODED_PREFIX = "{";
    private static final String JSON_TOKEN_KEY = "token";
    private static final String STORE_KEY_ID = "|S|id";
    private static final String STORE_KEY_PUB = "|S||P|";
    private static final String STORE_KEY_SEPARATOR = "|";
    private static final String STORE_KEY_TOKEN = "|T|";
    private final String defaultSenderId;
    private final SharedPreferences iidPrefs;

    static {
        IidStore.ALLOWABLE_SCOPES = new String[]{"*", "FCM", "GCM", ""};
    }

    public IidStore(SharedPreferences sharedPreferences0, String s) {
        this.iidPrefs = sharedPreferences0;
        this.defaultSenderId = s;
    }

    public IidStore(FirebaseApp firebaseApp0) {
        this.iidPrefs = firebaseApp0.getApplicationContext().getSharedPreferences("com.google.android.gms.appid", 0);
        this.defaultSenderId = IidStore.getDefaultSenderId(firebaseApp0);
    }

    private String createTokenKey(String s, String s1) {
        return "|T|" + s + "|" + s1;
    }

    private static String getDefaultSenderId(FirebaseApp firebaseApp0) {
        String s = firebaseApp0.getOptions().getGcmSenderId();
        if(s != null) {
            return s;
        }
        String s1 = firebaseApp0.getOptions().getApplicationId();
        if(!s1.startsWith("1:") && !s1.startsWith("2:")) {
            return s1;
        }
        String[] arr_s = s1.split(":");
        if(arr_s.length != 4) {
            return null;
        }
        String s2 = arr_s[1];
        return s2.isEmpty() ? null : s2;
    }

    private static String getIdFromPublicKey(PublicKey publicKey0) {
        byte[] arr_b = publicKey0.getEncoded();
        try {
            byte[] arr_b1 = MessageDigest.getInstance("SHA1").digest(arr_b);
            arr_b1[0] = (byte)((arr_b1[0] & 15) + 0x70 & 0xFF);
            return Base64.encodeToString(arr_b1, 0, 8, 11);
        }
        catch(NoSuchAlgorithmException unused_ex) {
            Log.w("ContentValues", "Unexpected error, device missing required algorithms");
            return null;
        }
    }

    private String parseIidTokenFromJson(String s) {
        try {
            return new JSONObject(s).getString("token");
        }
        catch(JSONException unused_ex) {
            return null;
        }
    }

    private PublicKey parseKey(String s) {
        try {
            byte[] arr_b = Base64.decode(s, 8);
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arr_b));
        }
        catch(IllegalArgumentException | InvalidKeySpecException | NoSuchAlgorithmException illegalArgumentException0) {
            Log.w("ContentValues", "Invalid key stored " + illegalArgumentException0);
            return null;
        }
    }

    public String readIid() {
        synchronized(this.iidPrefs) {
            String s = this.readInstanceIdFromLocalStorage();
            if(s != null) {
                return s;
            }
        }
        return this.readPublicKeyFromLocalStorageAndCalculateInstanceId();
    }

    private String readInstanceIdFromLocalStorage() {
        synchronized(this.iidPrefs) {
        }
        return this.iidPrefs.getString("|S|id", null);
    }

    private String readPublicKeyFromLocalStorageAndCalculateInstanceId() {
        PublicKey publicKey0;
        synchronized(this.iidPrefs) {
            String s = this.iidPrefs.getString("|S||P|", null);
            if(s == null) {
                return null;
            }
            publicKey0 = this.parseKey(s);
            if(publicKey0 == null) {
                return null;
            }
        }
        return IidStore.getIdFromPublicKey(publicKey0);
    }

    public String readToken() {
        synchronized(this.iidPrefs) {
            String[] arr_s = IidStore.ALLOWABLE_SCOPES;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                String s = this.createTokenKey(this.defaultSenderId, arr_s[v1]);
                String s1 = this.iidPrefs.getString(s, null);
                if(s1 != null && !s1.isEmpty()) {
                    if(s1.startsWith("{")) {
                        s1 = this.parseIidTokenFromJson(s1);
                    }
                    return s1;
                }
            }
            return null;
        }
    }
}

