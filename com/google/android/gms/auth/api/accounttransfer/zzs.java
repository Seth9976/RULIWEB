package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.auth.zzbz;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzs extends zzbz {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    private static final ArrayMap zzb;
    private List zzc;
    private List zzd;
    private List zze;
    private List zzf;
    private List zzg;

    static {
        zzs.CREATOR = new zzt();
        ArrayMap arrayMap0 = new ArrayMap();
        zzs.zzb = arrayMap0;
        arrayMap0.put("registered", Field.forStrings("registered", 2));
        arrayMap0.put("in_progress", Field.forStrings("in_progress", 3));
        arrayMap0.put("success", Field.forStrings("success", 4));
        arrayMap0.put("failed", Field.forStrings("failed", 5));
        arrayMap0.put("escrowed", Field.forStrings("escrowed", 6));
    }

    public zzs() {
        this.zza = 1;
    }

    zzs(int v, List list0, List list1, List list2, List list3, List list4) {
        this.zza = v;
        this.zzc = list0;
        this.zzd = list1;
        this.zze = list2;
        this.zzf = list3;
        this.zzg = list4;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public final Map getFieldMappings() {
        return zzs.zzb;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final Object getFieldValue(Field fastJsonResponse$Field0) {
        switch(fastJsonResponse$Field0.getSafeParcelableFieldId()) {
            case 1: {
                return this.zza;
            }
            case 2: {
                return this.zzc;
            }
            case 3: {
                return this.zzd;
            }
            case 4: {
                return this.zze;
            }
            case 5: {
                return this.zzf;
            }
            case 6: {
                return this.zzg;
            }
            default: {
                throw new IllegalStateException("Unknown SafeParcelable id=" + fastJsonResponse$Field0.getSafeParcelableFieldId());
            }
        }
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final boolean isFieldSet(Field fastJsonResponse$Field0) {
        return true;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    protected final void setStringsInternal(Field fastJsonResponse$Field0, String s, ArrayList arrayList0) {
        int v = fastJsonResponse$Field0.getSafeParcelableFieldId();
        switch(v) {
            case 2: {
                this.zzc = arrayList0;
                return;
            }
            case 3: {
                this.zzd = arrayList0;
                return;
            }
            case 4: {
                this.zze = arrayList0;
                return;
            }
            case 5: {
                this.zzf = arrayList0;
                return;
            }
            case 6: {
                this.zzg = arrayList0;
                return;
            }
            default: {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", v));
            }
        }
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeStringList(parcel0, 2, this.zzc, false);
        SafeParcelWriter.writeStringList(parcel0, 3, this.zzd, false);
        SafeParcelWriter.writeStringList(parcel0, 4, this.zze, false);
        SafeParcelWriter.writeStringList(parcel0, 5, this.zzf, false);
        SafeParcelWriter.writeStringList(parcel0, 6, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

