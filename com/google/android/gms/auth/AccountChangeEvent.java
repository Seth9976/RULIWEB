package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    final long zzb;
    final String zzc;
    final int zzd;
    final int zze;
    final String zzf;

    static {
        AccountChangeEvent.CREATOR = new zza();
    }

    AccountChangeEvent(int v, long v1, String s, int v2, int v3, String s1) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = (String)Preconditions.checkNotNull(s);
        this.zzd = v2;
        this.zze = v3;
        this.zzf = s1;
    }

    public AccountChangeEvent(long v, String s, int v1, int v2, String s1) {
        this.zza = 1;
        this.zzb = v;
        this.zzc = (String)Preconditions.checkNotNull(s);
        this.zzd = v1;
        this.zze = v2;
        this.zzf = s1;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof AccountChangeEvent)) {
            return false;
        }
        return object0 == this ? true : this.zza == ((AccountChangeEvent)object0).zza && this.zzb == ((AccountChangeEvent)object0).zzb && Objects.equal(this.zzc, ((AccountChangeEvent)object0).zzc) && this.zzd == ((AccountChangeEvent)object0).zzd && this.zze == ((AccountChangeEvent)object0).zze && Objects.equal(this.zzf, ((AccountChangeEvent)object0).zzf);
    }

    public String getAccountName() {
        return this.zzc;
    }

    public String getChangeData() {
        return this.zzf;
    }

    public int getChangeType() {
        return this.zzd;
    }

    public int getEventIndex() {
        return this.zze;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf});
    }

    @Override
    public String toString() {
        switch(this.zzd) {
            case 1: {
                return "AccountChangeEvent {accountName = " + this.zzc + ", changeType = " + "ADDED" + ", changeData = " + this.zzf + ", eventIndex = " + this.zze + "}";
            }
            case 2: {
                return "AccountChangeEvent {accountName = " + this.zzc + ", changeType = " + "REMOVED" + ", changeData = " + this.zzf + ", eventIndex = " + this.zze + "}";
            }
            case 3: {
                return "AccountChangeEvent {accountName = " + this.zzc + ", changeType = " + "RENAMED_FROM" + ", changeData = " + this.zzf + ", eventIndex = " + this.zze + "}";
            }
            case 4: {
                return "AccountChangeEvent {accountName = " + this.zzc + ", changeType = " + "RENAMED_TO" + ", changeData = " + this.zzf + ", eventIndex = " + this.zze + "}";
            }
            default: {
                return "AccountChangeEvent {accountName = " + this.zzc + ", changeType = " + "UNKNOWN" + ", changeData = " + this.zzf + ", eventIndex = " + this.zze + "}";
            }
        }
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeLong(parcel0, 2, this.zzb);
        SafeParcelWriter.writeString(parcel0, 3, this.zzc, false);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel0, 5, this.zze);
        SafeParcelWriter.writeString(parcel0, 6, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

