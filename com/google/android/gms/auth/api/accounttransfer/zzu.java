package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzu extends zzbz {
    public static final Parcelable.Creator CREATOR;
    final Set zza;
    final int zzb;
    private static final HashMap zzc;
    private zzw zzd;
    private String zze;
    private String zzf;
    private String zzg;

    static {
        zzu.CREATOR = new zzv();
        HashMap hashMap0 = new HashMap();
        zzu.zzc = hashMap0;
        hashMap0.put("authenticatorInfo", Field.forConcreteType("authenticatorInfo", 2, zzw.class));
        hashMap0.put("signature", Field.forString("signature", 3));
        hashMap0.put("package", Field.forString("package", 4));
    }

    public zzu() {
        this.zza = new HashSet(3);
        this.zzb = 1;
    }

    zzu(Set set0, int v, zzw zzw0, String s, String s1, String s2) {
        this.zza = set0;
        this.zzb = v;
        this.zzd = zzw0;
        this.zze = s;
        this.zzf = s1;
        this.zzg = s2;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeInternal(Field fastJsonResponse$Field0, String s, FastJsonResponse fastJsonResponse0) {
        int v = fastJsonResponse$Field0.getSafeParcelableFieldId();
        if(v != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", v, fastJsonResponse0.getClass().getCanonicalName()));
        }
        this.zzd = (zzw)fastJsonResponse0;
        this.zza.add(2);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map getFieldMappings() {
        return zzu.zzc;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final Object getFieldValue(Field fastJsonResponse$Field0) {
        switch(fastJsonResponse$Field0.getSafeParcelableFieldId()) {
            case 1: {
                return this.zzb;
            }
            case 2: {
                return this.zzd;
            }
            case 3: {
                return this.zze;
            }
            case 4: {
                return this.zzf;
            }
            default: {
                throw new IllegalStateException("Unknown SafeParcelable id=" + fastJsonResponse$Field0.getSafeParcelableFieldId());
            }
        }
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final boolean isFieldSet(Field fastJsonResponse$Field0) {
        return this.zza.contains(fastJsonResponse$Field0.getSafeParcelableFieldId());
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringInternal(Field fastJsonResponse$Field0, String s, String s1) {
        int v = fastJsonResponse$Field0.getSafeParcelableFieldId();
        switch(v) {
            case 3: {
                this.zze = s1;
                break;
            }
            case 4: {
                this.zzf = s1;
                break;
            }
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", v));
            }
        }
        this.zza.add(v);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        Set set0 = this.zza;
        if(set0.contains(1)) {
            SafeParcelWriter.writeInt(parcel0, 1, this.zzb);
        }
        if(set0.contains(2)) {
            SafeParcelWriter.writeParcelable(parcel0, 2, this.zzd, v, true);
        }
        if(set0.contains(3)) {
            SafeParcelWriter.writeString(parcel0, 3, this.zze, true);
        }
        if(set0.contains(4)) {
            SafeParcelWriter.writeString(parcel0, 4, this.zzf, true);
        }
        if(set0.contains(5)) {
            SafeParcelWriter.writeString(parcel0, 5, this.zzg, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

