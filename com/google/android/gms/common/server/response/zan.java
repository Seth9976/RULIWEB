package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private final HashMap zab;
    private final String zac;

    static {
        zan.CREATOR = new zao();
    }

    zan(int v, ArrayList arrayList0, String s) {
        this.zaa = v;
        HashMap hashMap0 = new HashMap();
        int v1 = arrayList0.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            zal zal0 = (zal)arrayList0.get(v2);
            String s1 = zal0.zab;
            HashMap hashMap1 = new HashMap();
            int v3 = ((ArrayList)Preconditions.checkNotNull(zal0.zac)).size();
            for(int v4 = 0; v4 < v3; ++v4) {
                zam zam0 = (zam)zal0.zac.get(v4);
                hashMap1.put(zam0.zab, zam0.zac);
            }
            hashMap0.put(s1, hashMap1);
        }
        this.zab = hashMap0;
        this.zac = (String)Preconditions.checkNotNull(s);
        this.zad();
    }

    public zan(Class class0) {
        this.zaa = 1;
        this.zab = new HashMap();
        this.zac = (String)Preconditions.checkNotNull(class0.getCanonicalName());
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: this.zab.keySet()) {
            stringBuilder0.append(((String)object0));
            stringBuilder0.append(":\n");
            Map map0 = (Map)this.zab.get(((String)object0));
            for(Object object1: map0.keySet()) {
                stringBuilder0.append("  ");
                stringBuilder0.append(((String)object1));
                stringBuilder0.append(": ");
                stringBuilder0.append(map0.get(((String)object1)));
            }
        }
        return stringBuilder0.toString();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.zab.keySet()) {
            arrayList0.add(new zal(((String)object0), ((Map)this.zab.get(((String)object0)))));
        }
        SafeParcelWriter.writeTypedList(parcel0, 2, arrayList0, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final String zaa() {
        return this.zac;
    }

    public final Map zab(String s) {
        return (Map)this.zab.get(s);
    }

    public final void zac() {
        for(Object object0: this.zab.keySet()) {
            Map map0 = (Map)this.zab.get(((String)object0));
            HashMap hashMap0 = new HashMap();
            for(Object object1: map0.keySet()) {
                hashMap0.put(((String)object1), ((Field)map0.get(((String)object1))).zab());
            }
            this.zab.put(((String)object0), hashMap0);
        }
    }

    public final void zad() {
        for(Object object0: this.zab.keySet()) {
            Map map0 = (Map)this.zab.get(((String)object0));
            for(Object object1: map0.keySet()) {
                ((Field)map0.get(((String)object1))).zai(this);
            }
        }
    }

    public final void zae(Class class0, Map map0) {
        String s = (String)Preconditions.checkNotNull(class0.getCanonicalName());
        this.zab.put(s, map0);
    }

    public final boolean zaf(Class class0) {
        Object object0 = Preconditions.checkNotNull(class0.getCanonicalName());
        return this.zab.containsKey(object0);
    }
}

