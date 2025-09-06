package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    final List zzb;

    static {
        AccountChangeEventsResponse.CREATOR = new zzc();
    }

    AccountChangeEventsResponse(int v, List list0) {
        this.zza = v;
        this.zzb = (List)Preconditions.checkNotNull(list0);
    }

    public AccountChangeEventsResponse(List list0) {
        this.zza = 1;
        this.zzb = (List)Preconditions.checkNotNull(list0);
    }

    public List getEvents() {
        return this.zzb;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeTypedList(parcel0, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

