package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;
import java.util.ArrayList;
import java.util.HashMap;

public final class StringToIntConverter extends AbstractSafeParcelable implements FieldConverter {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private final HashMap zab;
    private final SparseArray zac;

    static {
        StringToIntConverter.CREATOR = new zad();
    }

    public StringToIntConverter() {
        this.zaa = 1;
        this.zab = new HashMap();
        this.zac = new SparseArray();
    }

    StringToIntConverter(int v, ArrayList arrayList0) {
        this.zaa = v;
        this.zab = new HashMap();
        this.zac = new SparseArray();
        int v1 = arrayList0.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            zac zac0 = (zac)arrayList0.get(v2);
            this.add(zac0.zab, zac0.zac);
        }
    }

    public StringToIntConverter add(String s, int v) {
        this.zab.put(s, v);
        this.zac.put(v, s);
        return this;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.zab.keySet()) {
            arrayList0.add(new zac(((String)object0), ((int)(((Integer)this.zab.get(((String)object0)))))));
        }
        SafeParcelWriter.writeTypedList(parcel0, 2, arrayList0, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter
    public final int zaa() {
        return 7;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter
    public final int zab() {
        return 0;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter
    public final Object zac(Object object0) {
        Integer integer0 = (Integer)this.zab.get(((String)object0));
        return integer0 == null ? ((Integer)this.zab.get("gms_unknown")) : integer0;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse$FieldConverter
    public final Object zad(Object object0) {
        String s = (String)this.zac.get(((int)(((Integer)object0))));
        return s != null || !this.zab.containsKey("gms_unknown") ? s : "gms_unknown";
    }
}

