package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Map;

public final class zal extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    final String zab;
    final ArrayList zac;

    static {
        zal.CREATOR = new zap();
    }

    zal(int v, String s, ArrayList arrayList0) {
        this.zaa = v;
        this.zab = s;
        this.zac = arrayList0;
    }

    zal(String s, Map map0) {
        ArrayList arrayList0;
        this.zaa = 1;
        this.zab = s;
        if(map0 == null) {
            arrayList0 = null;
        }
        else {
            arrayList0 = new ArrayList();
            for(Object object0: map0.keySet()) {
                arrayList0.add(new zam(((String)object0), ((Field)map0.get(((String)object0)))));
            }
        }
        this.zac = arrayList0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeString(parcel0, 2, this.zab, false);
        SafeParcelWriter.writeTypedList(parcel0, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

