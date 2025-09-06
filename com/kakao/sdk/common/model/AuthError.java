package com.kakao.sdk.common.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001A\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001A\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001A\u00020\bHÆ\u0003J\'\u0010\u0013\u001A\u00020\u00002\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\b\b\u0002\u0010\u0007\u001A\u00020\bHÆ\u0001J\t\u0010\u0014\u001A\u00020\u0004HÖ\u0001J\u0013\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001A\u00020\u0004HÖ\u0001J\t\u0010\u001A\u001A\u00020\u001BHÖ\u0001J\u0019\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u001F2\u0006\u0010 \u001A\u00020\u0004HÖ\u0001R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0007\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000F¨\u0006!"}, d2 = {"Lcom/kakao/sdk/common/model/AuthError;", "Lcom/kakao/sdk/common/model/KakaoSdkError;", "Landroid/os/Parcelable;", "statusCode", "", "reason", "Lcom/kakao/sdk/common/model/AuthErrorCause;", "response", "Lcom/kakao/sdk/common/model/AuthErrorResponse;", "(ILcom/kakao/sdk/common/model/AuthErrorCause;Lcom/kakao/sdk/common/model/AuthErrorResponse;)V", "getReason", "()Lcom/kakao/sdk/common/model/AuthErrorCause;", "getResponse", "()Lcom/kakao/sdk/common/model/AuthErrorResponse;", "getStatusCode", "()I", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthError extends KakaoSdkError implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final AuthError createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new AuthError(parcel0.readInt(), AuthErrorCause.valueOf(parcel0.readString()), ((AuthErrorResponse)AuthErrorResponse.CREATOR.createFromParcel(parcel0)));
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final AuthError[] newArray(int v) {
            return new AuthError[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final AuthErrorCause reason;
    private final AuthErrorResponse response;
    private final int statusCode;

    static {
        AuthError.CREATOR = new Creator();
    }

    public AuthError(int v, AuthErrorCause authErrorCause0, AuthErrorResponse authErrorResponse0) {
        Intrinsics.checkNotNullParameter(authErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(authErrorResponse0, "response");
        String s = authErrorResponse0.getErrorDescription();
        if(s == null) {
            s = authErrorResponse0.getError();
        }
        super(s, null);
        this.statusCode = v;
        this.reason = authErrorCause0;
        this.response = authErrorResponse0;
    }

    public final int component1() {
        return this.statusCode;
    }

    public final AuthErrorCause component2() {
        return this.reason;
    }

    public final AuthErrorResponse component3() {
        return this.response;
    }

    public final AuthError copy(int v, AuthErrorCause authErrorCause0, AuthErrorResponse authErrorResponse0) {
        Intrinsics.checkNotNullParameter(authErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(authErrorResponse0, "response");
        return new AuthError(v, authErrorCause0, authErrorResponse0);
    }

    public static AuthError copy$default(AuthError authError0, int v, AuthErrorCause authErrorCause0, AuthErrorResponse authErrorResponse0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = authError0.statusCode;
        }
        if((v1 & 2) != 0) {
            authErrorCause0 = authError0.reason;
        }
        if((v1 & 4) != 0) {
            authErrorResponse0 = authError0.response;
        }
        return authError0.copy(v, authErrorCause0, authErrorResponse0);
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
        if(!(object0 instanceof AuthError)) {
            return false;
        }
        if(this.statusCode != ((AuthError)object0).statusCode) {
            return false;
        }
        return this.reason == ((AuthError)object0).reason ? Intrinsics.areEqual(this.response, ((AuthError)object0).response) : false;
    }

    public final AuthErrorCause getReason() {
        return this.reason;
    }

    public final AuthErrorResponse getResponse() {
        return this.response;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public int hashCode() {
        return (this.statusCode * 0x1F + this.reason.hashCode()) * 0x1F + this.response.hashCode();
    }

    @Override
    public String toString() {
        return "AuthError(statusCode=" + this.statusCode + ", reason=" + this.reason + ", response=" + this.response + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeInt(this.statusCode);
        parcel0.writeString(this.reason.name());
        this.response.writeToParcel(parcel0, v);
    }
}

