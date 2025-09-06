package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzo extends zzbz {
    public static final Parcelable.Creator CREATOR;
    final Set zza;
    final int zzb;
    private static final HashMap zzc;
    private ArrayList zzd;
    private int zze;
    private zzs zzf;

    static {
        zzo.CREATOR = new zzp();
        HashMap hashMap0 = new HashMap();
        zzo.zzc = hashMap0;
        hashMap0.put("authenticatorData", Field.forConcreteTypeArray("authenticatorData", 2, zzu.class));
        hashMap0.put("progress", Field.forConcreteType("progress", 4, zzs.class));
    }

    public zzo() {
        this.zza = new HashSet(1);
        this.zzb = 1;
    }

    zzo(Set set0, int v, ArrayList arrayList0, int v1, zzs zzs0) {
        this.zza = set0;
        this.zzb = v;
        this.zzd = arrayList0;
        this.zze = v1;
        this.zzf = zzs0;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeArrayInternal(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        int v = fastJsonResponse$Field0.getSafeParcelableFieldId();
        if(v != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known ConcreteTypeArray type. Found %s", v, arrayList0.getClass().getCanonicalName()));
        }
        this.zzd = arrayList0;
        this.zza.add(2);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final void addConcreteTypeInternal(Field fastJsonResponse$Field0, String s, FastJsonResponse fastJsonResponse0) {
        int v = fastJsonResponse$Field0.getSafeParcelableFieldId();
        if(v != 4) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", v, fastJsonResponse0.getClass().getCanonicalName()));
        }
        this.zzf = (zzs)fastJsonResponse0;
        this.zza.add(4);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map getFieldMappings() {
        return zzo.zzc;
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

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        Set set0 = this.zza;
        if(set0.contains(1)) {
            SafeParcelWriter.writeInt(parcel0, 1, this.zzb);
        }
        if(set0.contains(2)) {
            SafeParcelWriter.writeTypedList(parcel0, 2, this.zzd, true);
        }
        if(set0.contains(3)) {
            SafeParcelWriter.writeInt(parcel0, 3, this.zze);
        }
        if(set0.contains(4)) {
            SafeParcelWriter.writeParcelable(parcel0, 4, this.zzf, v, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

