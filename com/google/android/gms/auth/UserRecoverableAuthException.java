package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;

public class UserRecoverableAuthException extends GoogleAuthException {
    private final Intent zza;
    private final PendingIntent zzb;
    private final zzn zzc;

    public UserRecoverableAuthException(String s, Intent intent0) {
        this(s, intent0, null, zzn.zza);
    }

    private UserRecoverableAuthException(String s, Intent intent0, PendingIntent pendingIntent0, zzn zzn0) {
        super(s);
        this.zzb = pendingIntent0;
        this.zza = intent0;
        this.zzc = (zzn)Preconditions.checkNotNull(zzn0);
    }

    public Intent getIntent() {
        Intent intent0 = this.zza;
        if(intent0 == null) {
            switch(this.zzc.ordinal()) {
                case 0: {
                    Log.w("Auth", "Make sure that an intent was provided to class instantiation.");
                    return null;
                }
                case 1: {
                    Log.e("Auth", "This shouldn\'t happen. Gms API throwing this exception should support the recovery Intent.");
                    return null;
                }
                case 2: {
                    Log.e("Auth", "this instantiation of UserRecoverableAuthException doesn\'t support an Intent.");
                    return null;
                }
                default: {
                    return null;
                }
            }
        }
        return new Intent(intent0);
    }

    public static UserRecoverableAuthException zza(String s, Intent intent0, PendingIntent pendingIntent0) {
        Preconditions.checkNotNull(intent0);
        Preconditions.checkNotNull(pendingIntent0);
        return new UserRecoverableAuthException(s, intent0, pendingIntent0, zzn.zzb);
    }
}

