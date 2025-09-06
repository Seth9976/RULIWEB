package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static final String HEARTBEAT_PREFERENCES_NAME = "FirebaseHeartBeat";
    private static final int HEART_BEAT_COUNT_LIMIT = 30;
    private static final String HEART_BEAT_COUNT_TAG = "fire-count";
    private static final String LAST_STORED_DATE = "last-used-date";
    private static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    private final SharedPreferences firebaseSharedPreferences;
    private static HeartBeatInfoStorage instance;

    static {
    }

    public HeartBeatInfoStorage(Context context0, String s) {
        this.firebaseSharedPreferences = context0.getSharedPreferences("FirebaseHeartBeat" + s, 0);
    }

    HeartBeatInfoStorage(SharedPreferences sharedPreferences0) {
        this.firebaseSharedPreferences = sharedPreferences0;
    }

    private void cleanUpStoredHeartBeats() {
        synchronized(this) {
            long v1 = this.firebaseSharedPreferences.getLong("fire-count", 0L);
            String s = "";
            String s1 = null;
            for(Object object0: this.firebaseSharedPreferences.getAll().entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(map$Entry0.getValue() instanceof Set) {
                    for(Object object1: ((Set)map$Entry0.getValue())) {
                        String s2 = (String)object1;
                        if(s1 == null || s1.compareTo(s2) > 0) {
                            s = (String)map$Entry0.getKey();
                            s1 = s2;
                        }
                    }
                }
            }
            HashSet hashSet0 = new HashSet();
            HashSet hashSet1 = new HashSet(this.firebaseSharedPreferences.getStringSet(s, hashSet0));
            hashSet1.remove(s1);
            this.firebaseSharedPreferences.edit().putStringSet(s, hashSet1).putLong("fire-count", v1 - 1L).commit();
        }
    }

    void deleteAllHeartBeats() {
        synchronized(this) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.firebaseSharedPreferences.edit();
            int v1 = 0;
            for(Object object0: this.firebaseSharedPreferences.getAll().entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(map$Entry0.getValue() instanceof Set) {
                    Set set0 = (Set)map$Entry0.getValue();
                    String s = (String)map$Entry0.getKey();
                    if(set0.contains("2025-09-05")) {
                        HashSet hashSet0 = new HashSet();
                        hashSet0.add("2025-09-05");
                        ++v1;
                        sharedPreferences$Editor0.putStringSet(s, hashSet0);
                    }
                    else {
                        sharedPreferences$Editor0.remove(s);
                    }
                }
            }
            if(v1 == 0) {
                sharedPreferences$Editor0.remove("fire-count");
            }
            else {
                sharedPreferences$Editor0.putLong("fire-count", ((long)v1));
            }
            sharedPreferences$Editor0.commit();
        }
    }

    List getAllHeartBeats() {
        synchronized(this) {
            List list0 = new ArrayList();
            for(Object object0: this.firebaseSharedPreferences.getAll().entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(map$Entry0.getValue() instanceof Set) {
                    HashSet hashSet0 = new HashSet(((Set)map$Entry0.getValue()));
                    hashSet0.remove("2025-09-05");
                    if(!hashSet0.isEmpty()) {
                        ((ArrayList)list0).add(HeartBeatResult.create(((String)map$Entry0.getKey()), new ArrayList(hashSet0)));
                    }
                }
            }
            this.updateGlobalHeartBeat(System.currentTimeMillis());
            return list0;
        }
    }

    private String getFormattedDate(long v) [...] // 潜在的解密器

    int getHeartBeatCount() {
        return (int)this.firebaseSharedPreferences.getLong("fire-count", 0L);
    }

    long getLastGlobalHeartBeat() {
        synchronized(this) {
            return this.firebaseSharedPreferences.getLong("fire-global", -1L);
        }
    }

    private String getStoredUserAgentString(String s) {
        synchronized(this) {
            for(Object object0: this.firebaseSharedPreferences.getAll().entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(map$Entry0.getValue() instanceof Set) {
                    for(Object object1: ((Set)map$Entry0.getValue())) {
                        if(s.equals(((String)object1))) {
                            return (String)map$Entry0.getKey();
                        }
                        if(false) {
                            break;
                        }
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return null;
        }
    }

    boolean isSameDateUtc(long v, long v1) {
        synchronized(this) {
            return this.getFormattedDate(v).equals(this.getFormattedDate(v1));
        }
    }

    void postHeartBeatCleanUp() {
        synchronized(this) {
            this.firebaseSharedPreferences.edit().putString("last-used-date", "2025-09-05").commit();
            this.removeStoredDate("2025-09-05");
        }
    }

    private void removeStoredDate(String s) {
        synchronized(this) {
            String s1 = this.getStoredUserAgentString(s);
            if(s1 == null) {
                return;
            }
            HashSet hashSet0 = new HashSet();
            HashSet hashSet1 = new HashSet(this.firebaseSharedPreferences.getStringSet(s1, hashSet0));
            hashSet1.remove(s);
            if(hashSet1.isEmpty()) {
                this.firebaseSharedPreferences.edit().remove(s1).commit();
            }
            else {
                this.firebaseSharedPreferences.edit().putStringSet(s1, hashSet1).commit();
            }
        }
    }

    boolean shouldSendGlobalHeartBeat(long v) {
        synchronized(this) {
            return this.shouldSendSdkHeartBeat("fire-global", v);
        }
    }

    boolean shouldSendSdkHeartBeat(String s, long v) {
        synchronized(this) {
            if(this.firebaseSharedPreferences.contains(s)) {
                if(!this.isSameDateUtc(this.firebaseSharedPreferences.getLong(s, -1L), v)) {
                    this.firebaseSharedPreferences.edit().putLong(s, v).commit();
                    return true;
                }
                return false;
            }
            this.firebaseSharedPreferences.edit().putLong(s, v).commit();
            return true;
        }
    }

    void storeHeartBeat(long v, String s) {
        synchronized(this) {
            String s1 = this.getFormattedDate(v);
            if(this.firebaseSharedPreferences.getString("last-used-date", "").equals(s1)) {
                String s2 = this.getStoredUserAgentString(s1);
                if(s2 == null) {
                    return;
                }
                if(s2.equals(s)) {
                    return;
                }
                this.updateStoredUserAgent(s, s1);
                return;
            }
            long v2 = this.firebaseSharedPreferences.getLong("fire-count", 0L);
            if(v2 + 1L == 30L) {
                this.cleanUpStoredHeartBeats();
                v2 = this.firebaseSharedPreferences.getLong("fire-count", 0L);
            }
            HashSet hashSet0 = new HashSet();
            HashSet hashSet1 = new HashSet(this.firebaseSharedPreferences.getStringSet(s, hashSet0));
            hashSet1.add(s1);
            this.firebaseSharedPreferences.edit().putStringSet(s, hashSet1).putLong("fire-count", v2 + 1L).putString("last-used-date", s1).commit();
        }
    }

    void updateGlobalHeartBeat(long v) {
        synchronized(this) {
            this.firebaseSharedPreferences.edit().putLong("fire-global", v).commit();
        }
    }

    private void updateStoredUserAgent(String s, String s1) {
        synchronized(this) {
            this.removeStoredDate(s1);
            HashSet hashSet0 = new HashSet();
            HashSet hashSet1 = new HashSet(this.firebaseSharedPreferences.getStringSet(s, hashSet0));
            hashSet1.add(s1);
            this.firebaseSharedPreferences.edit().putStringSet(s, hashSet1).commit();
        }
    }
}

