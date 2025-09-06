package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

class Store {
    static class Token {
        private static final String KEY_APP_VERSION = "appVersion";
        private static final String KEY_TIMESTAMP = "timestamp";
        private static final String KEY_TOKEN = "token";
        private static final long REFRESH_PERIOD_MILLIS;
        final String appVersion;
        final long timestamp;
        final String token;

        static {
            Token.REFRESH_PERIOD_MILLIS = TimeUnit.DAYS.toMillis(7L);
        }

        private Token(String s, String s1, long v) {
            this.token = s;
            this.appVersion = s1;
            this.timestamp = v;
        }

        static String encode(String s, String s1, long v) {
            try {
                JSONObject jSONObject0 = new JSONObject();
                jSONObject0.put("token", s);
                jSONObject0.put("appVersion", s1);
                jSONObject0.put("timestamp", v);
                return jSONObject0.toString();
            }
            catch(JSONException jSONException0) {
                Log.w("FirebaseMessaging", "Failed to encode token: " + jSONException0);
                return null;
            }
        }

        boolean needsRefresh(String s) {
            return System.currentTimeMillis() > this.timestamp + Token.REFRESH_PERIOD_MILLIS || !s.equals(this.appVersion);
        }

        static Token parse(String s) {
            if(TextUtils.isEmpty(s)) {
                return null;
            }
            if(s.startsWith("{")) {
                try {
                    JSONObject jSONObject0 = new JSONObject(s);
                    return new Token(jSONObject0.getString("token"), jSONObject0.getString("appVersion"), jSONObject0.getLong("timestamp"));
                }
                catch(JSONException jSONException0) {
                    Log.w("FirebaseMessaging", "Failed to parse token: " + jSONException0);
                    return null;
                }
            }
            return new Token(s, null, 0L);
        }
    }

    static final String NO_BACKUP_FILE = "com.google.android.gms.appid-no-backup";
    static final String PREFERENCES = "com.google.android.gms.appid";
    private static final String SCOPE_ALL = "*";
    private static final String STORE_KEY_TOKEN = "|T|";
    final SharedPreferences store;

    public Store(Context context0) {
        this.store = context0.getSharedPreferences("com.google.android.gms.appid", 0);
        this.checkForRestore(context0, "com.google.android.gms.appid-no-backup");
    }

    private void checkForRestore(Context context0, String s) {
        File file0 = new File(ContextCompat.getNoBackupFilesDir(context0), s);
        if(!file0.exists()) {
            try {
                if(file0.createNewFile() && !this.isEmpty()) {
                    Log.i("FirebaseMessaging", "App restored, clearing state");
                    this.deleteAll();
                }
            }
            catch(IOException iOException0) {
                if(Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Error creating file in no backup dir: " + iOException0.getMessage());
                }
            }
        }
    }

    private String createTokenKey(String s, String s1) [...] // Inlined contents

    public void deleteAll() {
        synchronized(this) {
            this.store.edit().clear().commit();
        }
    }

    public void deleteToken(String s, String s1) {
        synchronized(this) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.store.edit();
            sharedPreferences$Editor0.remove(s + "|T|" + s1 + "|*");
            sharedPreferences$Editor0.commit();
        }
    }

    public Token getToken(String s, String s1) {
        synchronized(this) {
            return Token.parse(this.store.getString(s + "|T|" + s1 + "|*", null));
        }
    }

    public boolean isEmpty() {
        synchronized(this) {
            return this.store.getAll().isEmpty();
        }
    }

    public void saveToken(String s, String s1, String s2, String s3) {
        synchronized(this) {
            String s4 = Token.encode(s2, s3, System.currentTimeMillis());
            if(s4 == null) {
                return;
            }
            SharedPreferences.Editor sharedPreferences$Editor0 = this.store.edit();
            sharedPreferences$Editor0.putString(s + "|T|" + s1 + "|*", s4);
            sharedPreferences$Editor0.commit();
        }
    }
}

