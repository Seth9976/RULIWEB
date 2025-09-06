package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class MethodInvocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final int zaa;
    private final int zab;
    private final int zac;
    private final long zad;
    private final long zae;
    private final String zaf;
    private final String zag;
    private final int zah;
    private final int zai;

    static {
        MethodInvocation.CREATOR = new zan();
    }

    @Deprecated
    public MethodInvocation(int v, int v1, int v2, long v3, long v4, String s, String s1, int v5) {
        this(v, v1, v2, v3, v4, s, s1, v5, -1);
    }

    public MethodInvocation(int v, int v1, int v2, long v3, long v4, String s, String s1, int v5, int v6) {
        this.zaa = v;
        this.zab = v1;
        this.zac = v2;
        this.zad = v3;
        this.zae = v4;
        this.zaf = s;
        this.zag = s1;
        this.zah = v5;
        this.zai = v6;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel0, 2, this.zab);
        SafeParcelWriter.writeInt(parcel0, 3, this.zac);
        SafeParcelWriter.writeLong(parcel0, 4, this.zad);
        SafeParcelWriter.writeLong(parcel0, 5, this.zae);
        SafeParcelWriter.writeString(parcel0, 6, this.zaf, false);
        SafeParcelWriter.writeString(parcel0, 7, this.zag, false);
        SafeParcelWriter.writeInt(parcel0, 8, this.zah);
        SafeParcelWriter.writeInt(parcel0, 9, this.zai);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

