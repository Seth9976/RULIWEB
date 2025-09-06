package com.kakao.sdk.common.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001A\u00020\u0003HÆ\u0003J\u000B\u0010\n\u001A\u0004\u0018\u00010\u0003HÆ\u0003J\u001F\u0010\u000B\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\f\u001A\u00020\rHÖ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001A\u00020\rHÖ\u0001J\t\u0010\u0013\u001A\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/kakao/sdk/common/model/AuthErrorResponse;", "Landroid/os/Parcelable;", "error", "", "errorDescription", "(Ljava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getErrorDescription", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthErrorResponse implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final AuthErrorResponse createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new AuthErrorResponse(parcel0.readString(), parcel0.readString());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final AuthErrorResponse[] newArray(int v) {
            return new AuthErrorResponse[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String error;
    private final String errorDescription;

    static {
        AuthErrorResponse.CREATOR = new Creator();
    }

    public AuthErrorResponse(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "error");
        super();
        this.error = s;
        this.errorDescription = s1;
    }

    public final String component1() {
        return this.error;
    }

    public final String component2() {
        return this.errorDescription;
    }

    public final AuthErrorResponse copy(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "error");
        return new AuthErrorResponse(s, s1);
    }

    public static AuthErrorResponse copy$default(AuthErrorResponse authErrorResponse0, String s, String s1, int v, Object object0) {
        if((v & 1) != 0) {
            s = authErrorResponse0.error;
        }
        if((v & 2) != 0) {
            s1 = authErrorResponse0.errorDescription;
        }
        return authErrorResponse0.copy(s, s1);
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
        if(!(object0 instanceof AuthErrorResponse)) {
            return false;
        }
        return Intrinsics.areEqual(this.error, ((AuthErrorResponse)object0).error) ? Intrinsics.areEqual(this.errorDescription, ((AuthErrorResponse)object0).errorDescription) : false;
    }

    public final String getError() {
        return this.error;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    @Override
    public int hashCode() {
        int v = this.error.hashCode();
        return this.errorDescription == null ? v * 0x1F : v * 0x1F + this.errorDescription.hashCode();
    }

    @Override
    public String toString() {
        return "AuthErrorResponse(error=" + this.error + ", errorDescription=" + this.errorDescription + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.error);
        parcel0.writeString(this.errorDescription);
    }
}

