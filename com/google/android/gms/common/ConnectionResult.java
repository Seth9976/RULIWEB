package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class ConnectionResult extends AbstractSafeParcelable {
    public static final int API_DISABLED = 23;
    public static final int API_DISABLED_FOR_CONNECTION = 24;
    public static final int API_UNAVAILABLE = 16;
    public static final int CANCELED = 13;
    public static final Parcelable.Creator CREATOR = null;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated
    public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_ACTIVITY_NOT_FOUND = 22;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int RESTRICTED_PROFILE = 20;
    public static final ConnectionResult RESULT_SUCCESS = null;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 19;
    public static final int SERVICE_UPDATING = 18;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_FAILED = 17;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    public static final int UNKNOWN = -1;
    final int zza;
    private final int zzb;
    private final PendingIntent zzc;
    private final String zzd;

    static {
        ConnectionResult.RESULT_SUCCESS = new ConnectionResult(0);
        ConnectionResult.CREATOR = new zzb();
    }

    public ConnectionResult(int v) {
        this(v, null, null);
    }

    ConnectionResult(int v, int v1, PendingIntent pendingIntent0, String s) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = pendingIntent0;
        this.zzd = s;
    }

    public ConnectionResult(int v, PendingIntent pendingIntent0) {
        this(v, pendingIntent0, null);
    }

    public ConnectionResult(int v, PendingIntent pendingIntent0, String s) {
        this(1, v, pendingIntent0, s);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof ConnectionResult ? this.zzb == ((ConnectionResult)object0).zzb && Objects.equal(this.zzc, ((ConnectionResult)object0).zzc) && Objects.equal(this.zzd, ((ConnectionResult)object0).zzd) : false;
    }

    public int getErrorCode() {
        return this.zzb;
    }

    public String getErrorMessage() {
        return this.zzd;
    }

    public PendingIntent getResolution() {
        return this.zzc;
    }

    public boolean hasResolution() {
        return this.zzb != 0 && this.zzc != null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzb, this.zzc, this.zzd});
    }

    public boolean isSuccess() {
        return this.zzb == 0;
    }

    public void startResolutionForResult(Activity activity0, int v) throws IntentSender.SendIntentException {
        if(!this.hasResolution()) {
            return;
        }
        Preconditions.checkNotNull(this.zzc);
        activity0.startIntentSenderForResult(this.zzc.getIntentSender(), v, null, 0, 0, 0);
    }

    @Override
    public String toString() {
        ToStringHelper objects$ToStringHelper0 = Objects.toStringHelper(this);
        objects$ToStringHelper0.add("statusCode", ConnectionResult.zza(this.zzb));
        objects$ToStringHelper0.add("resolution", this.zzc);
        objects$ToStringHelper0.add("message", this.zzd);
        return objects$ToStringHelper0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeInt(parcel0, 2, this.getErrorCode());
        SafeParcelWriter.writeParcelable(parcel0, 3, this.getResolution(), v, false);
        SafeParcelWriter.writeString(parcel0, 4, this.getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    static String zza(int v) {
        switch(v) {
            case -1: {
                return "UNKNOWN";
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
            case 13: {
                return "CANCELED";
            }
            case 14: {
                return "TIMEOUT";
            }
            case 15: {
                return "INTERRUPTED";
            }
            case 16: {
                return "API_UNAVAILABLE";
            }
            case 17: {
                return "SIGN_IN_FAILED";
            }
            case 18: {
                return "SERVICE_UPDATING";
            }
            case 19: {
                return "SERVICE_MISSING_PERMISSION";
            }
            case 20: {
                return "RESTRICTED_PROFILE";
            }
            case 21: {
                return "API_VERSION_UPDATE_REQUIRED";
            }
            case 22: {
                return "RESOLUTION_ACTIVITY_NOT_FOUND";
            }
            case 23: {
                return "API_DISABLED";
            }
            case 24: {
                return "API_DISABLED_FOR_CONNECTION";
            }
            case 99: {
                return "UNFINISHED";
            }
            case 1500: {
                return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
            }
            default: {
                return "UNKNOWN_ERROR_CODE(" + v + ")";
            }
        }
    }
}

