package com.kakao.sdk.auth.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001A\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u0003HÆ\u0001J\t\u0010\t\u001A\u00020\nHÖ\u0001J\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u000EHÖ\u0003J\t\u0010\u000F\u001A\u00020\nHÖ\u0001J\t\u0010\u0010\u001A\u00020\u0003HÖ\u0001J\u0019\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\nHÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/kakao/sdk/auth/model/AgtResponse;", "Landroid/os/Parcelable;", "agt", "", "(Ljava/lang/String;)V", "getAgt", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AgtResponse implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final AgtResponse createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new AgtResponse(parcel0.readString());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final AgtResponse[] newArray(int v) {
            return new AgtResponse[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String agt;

    static {
        AgtResponse.CREATOR = new Creator();
    }

    public AgtResponse(String s) {
        Intrinsics.checkNotNullParameter(s, "agt");
        super();
        this.agt = s;
    }

    public final String component1() {
        return this.agt;
    }

    public final AgtResponse copy(String s) {
        Intrinsics.checkNotNullParameter(s, "agt");
        return new AgtResponse(s);
    }

    public static AgtResponse copy$default(AgtResponse agtResponse0, String s, int v, Object object0) {
        if((v & 1) != 0) {
            s = agtResponse0.agt;
        }
        return agtResponse0.copy(s);
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof AgtResponse ? Intrinsics.areEqual(this.agt, ((AgtResponse)object0).agt) : false;
    }

    public final String getAgt() {
        return this.agt;
    }

    @Override
    public int hashCode() {
        return this.agt.hashCode();
    }

    @Override
    public String toString() {
        return "AgtResponse(agt=" + this.agt + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.agt);
    }
}

