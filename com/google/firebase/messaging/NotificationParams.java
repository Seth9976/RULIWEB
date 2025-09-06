package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;

public class NotificationParams {
    private static final int COLOR_TRANSPARENT_IN_HEX = 0xFF000000;
    private static final int EMPTY_JSON_ARRAY_LENGTH = 1;
    private static final String TAG = "NotificationParams";
    private static final int VISIBILITY_MAX = 1;
    private static final int VISIBILITY_MIN = -1;
    private final Bundle data;

    public NotificationParams(Bundle bundle0) {
        if(bundle0 == null) {
            throw new NullPointerException("data");
        }
        this.data = new Bundle(bundle0);
    }

    public boolean getBoolean(String s) {
        String s1 = this.getString(s);
        return "1".equals(s1) || Boolean.parseBoolean(s1);
    }

    public Integer getInteger(String s) {
        String s1 = this.getString(s);
        if(!TextUtils.isEmpty(s1)) {
            try {
                return Integer.parseInt(s1);
            }
            catch(NumberFormatException unused_ex) {
                Log.w("NotificationParams", "Couldn\'t parse value of " + NotificationParams.userFriendlyKey(s) + "(" + s1 + ") into an int");
            }
        }
        return null;
    }

    public JSONArray getJSONArray(String s) {
        String s1 = this.getString(s);
        if(!TextUtils.isEmpty(s1)) {
            try {
                return new JSONArray(s1);
            }
            catch(JSONException unused_ex) {
                Log.w("NotificationParams", "Malformed JSON for key " + NotificationParams.userFriendlyKey(s) + ": " + s1 + ", falling back to default");
            }
        }
        return null;
    }

    private static int getLightColor(String s) {
        int v = Color.parseColor(s);
        if(v == 0xFF000000) {
            throw new IllegalArgumentException("Transparent color is invalid");
        }
        return v;
    }

    int[] getLightSettings() {
        JSONArray jSONArray0 = this.getJSONArray("gcm.n.light_settings");
        if(jSONArray0 == null) {
            return null;
        }
        try {
            int[] arr_v = new int[3];
            if(jSONArray0.length() == 3) {
                arr_v[0] = NotificationParams.getLightColor(jSONArray0.optString(0));
                arr_v[1] = jSONArray0.optInt(1);
                arr_v[2] = jSONArray0.optInt(2);
                return arr_v;
            }
        }
        catch(JSONException illegalArgumentException0) {
            Log.w("NotificationParams", "LightSettings is invalid: " + jSONArray0 + ". " + illegalArgumentException0.getMessage() + ". Skipping setting LightSettings");
            return null;
        }
        catch(IllegalArgumentException unused_ex) {
        }
        Log.w("NotificationParams", "LightSettings is invalid: " + jSONArray0 + ". Skipping setting LightSettings");
        return null;
    }

    public Uri getLink() {
        String s = this.getString("gcm.n.link_android");
        if(TextUtils.isEmpty(s)) {
            s = this.getString("gcm.n.link");
        }
        return TextUtils.isEmpty(s) ? null : Uri.parse(s);
    }

    public Object[] getLocalizationArgsForKey(String s) {
        JSONArray jSONArray0 = this.getJSONArray(s + "_loc_args");
        if(jSONArray0 == null) {
            return null;
        }
        int v = jSONArray0.length();
        Object[] arr_object = new String[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = jSONArray0.optString(v1);
        }
        return arr_object;
    }

    public String getLocalizationResourceForKey(String s) {
        return this.getString(s + "_loc_key");
    }

    public String getLocalizedString(Resources resources0, String s, String s1) {
        String s2 = this.getLocalizationResourceForKey(s1);
        if(TextUtils.isEmpty(s2)) {
            return null;
        }
        int v = resources0.getIdentifier(s2, "string", s);
        if(v == 0) {
            Log.w("NotificationParams", NotificationParams.userFriendlyKey((s1 + "_loc_key")) + " resource not found: " + s1 + " Default value will be used.");
            return null;
        }
        Object[] arr_object = this.getLocalizationArgsForKey(s1);
        if(arr_object == null) {
            return resources0.getString(v);
        }
        try {
            return resources0.getString(v, arr_object);
        }
        catch(MissingFormatArgumentException missingFormatArgumentException0) {
            Log.w("NotificationParams", "Missing format argument for " + NotificationParams.userFriendlyKey(s1) + ": " + Arrays.toString(arr_object) + " Default value will be used.", missingFormatArgumentException0);
            return null;
        }
    }

    public Long getLong(String s) {
        String s1 = this.getString(s);
        if(!TextUtils.isEmpty(s1)) {
            try {
                return Long.parseLong(s1);
            }
            catch(NumberFormatException unused_ex) {
                Log.w("NotificationParams", "Couldn\'t parse value of " + NotificationParams.userFriendlyKey(s) + "(" + s1 + ") into a long");
            }
        }
        return null;
    }

    public String getNotificationChannelId() {
        return this.getString("gcm.n.android_channel_id");
    }

    Integer getNotificationCount() {
        Integer integer0 = this.getInteger("gcm.n.notification_count");
        if(integer0 == null) {
            return null;
        }
        if(((int)integer0) < 0) {
            Log.w("FirebaseMessaging", "notificationCount is invalid: " + integer0 + ". Skipping setting notificationCount.");
            return null;
        }
        return integer0;
    }

    Integer getNotificationPriority() {
        Integer integer0 = this.getInteger("gcm.n.notification_priority");
        if(integer0 == null) {
            return null;
        }
        if(((int)integer0) >= -2 && ((int)integer0) <= 2) {
            return integer0;
        }
        Log.w("FirebaseMessaging", "notificationPriority is invalid " + integer0 + ". Skipping setting notificationPriority.");
        return null;
    }

    public String getPossiblyLocalizedString(Resources resources0, String s, String s1) {
        String s2 = this.getString(s1);
        return TextUtils.isEmpty(s2) ? this.getLocalizedString(resources0, s, s1) : s2;
    }

    public String getSoundResourceName() {
        String s = this.getString("gcm.n.sound2");
        return TextUtils.isEmpty(s) ? this.getString("gcm.n.sound") : s;
    }

    public String getString(String s) {
        String s1 = this.normalizePrefix(s);
        return this.data.getString(s1);
    }

    public long[] getVibrateTimings() {
        JSONArray jSONArray0 = this.getJSONArray("gcm.n.vibrate_timings");
        if(jSONArray0 == null) {
            return null;
        }
        try {
            if(jSONArray0.length() > 1) {
                int v = jSONArray0.length();
                long[] arr_v = new long[v];
                for(int v1 = 0; v1 < v; ++v1) {
                    arr_v[v1] = jSONArray0.optLong(v1);
                }
                return arr_v;
            }
        }
        catch(JSONException | NumberFormatException unused_ex) {
        }
        Log.w("NotificationParams", "User defined vibrateTimings is invalid: " + jSONArray0 + ". Skipping setting vibrateTimings.");
        return null;
    }

    Integer getVisibility() {
        Integer integer0 = this.getInteger("gcm.n.visibility");
        if(integer0 == null) {
            return null;
        }
        if(((int)integer0) >= -1 && ((int)integer0) <= 1) {
            return integer0;
        }
        Log.w("NotificationParams", "visibility is invalid: " + integer0 + ". Skipping setting visibility.");
        return null;
    }

    public boolean hasImage() {
        return !TextUtils.isEmpty(this.getString("gcm.n.image"));
    }

    // 去混淆评级： 低(20)
    private static boolean isAnalyticsKey(String s) {
        return s.startsWith("google.c.a.") || s.equals("from");
    }

    // 去混淆评级： 低(20)
    public static boolean isNotification(Bundle bundle0) {
        return "1".equals(bundle0.getString("gcm.n.e")) || "1".equals(bundle0.getString("gcm.notification.e"));
    }

    public boolean isNotification() {
        return this.getBoolean("gcm.n.e");
    }

    // 去混淆评级： 低(30)
    private static boolean isReservedKey(String s) {
        return s.startsWith("google.c.") || s.startsWith("gcm.n.") || s.startsWith("gcm.notification.");
    }

    private static String keyWithOldPrefix(String s) [...] // Inlined contents

    private String normalizePrefix(String s) {
        if(!this.data.containsKey(s) && s.startsWith("gcm.n.")) {
            String s1 = NotificationParams.keyWithOldPrefix(s);
            return this.data.containsKey(s1) ? s1 : s;
        }
        return s;
    }

    public Bundle paramsForAnalyticsIntent() {
        Bundle bundle0 = new Bundle(this.data);
        for(Object object0: this.data.keySet()) {
            String s = (String)object0;
            if(!NotificationParams.isAnalyticsKey(s)) {
                bundle0.remove(s);
            }
        }
        return bundle0;
    }

    public Bundle paramsWithReservedKeysRemoved() {
        Bundle bundle0 = new Bundle(this.data);
        for(Object object0: this.data.keySet()) {
            String s = (String)object0;
            if(NotificationParams.isReservedKey(s)) {
                bundle0.remove(s);
            }
        }
        return bundle0;
    }

    // 去混淆评级： 低(20)
    private static String userFriendlyKey(String s) {
        return s.startsWith("gcm.n.") ? s.substring(6) : s;
    }
}

