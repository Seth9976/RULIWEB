package com.kakao.sdk.common.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001A\u00020\u0004HÆ\u0003J\t\u0010\r\u001A\u00020\u0006HÆ\u0003J\u001D\u0010\u000E\u001A\u00020\u00002\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u0006HÆ\u0001J\t\u0010\u000F\u001A\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001A\u00020\u00122\b\u0010\u0013\u001A\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001A\u00020\u0010HÖ\u0001J\t\u0010\u0016\u001A\u00020\u0006HÖ\u0001J\u0019\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u0010HÖ\u0001R\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u001C"}, d2 = {"Lcom/kakao/sdk/common/model/ClientError;", "Lcom/kakao/sdk/common/model/KakaoSdkError;", "Landroid/os/Parcelable;", "reason", "Lcom/kakao/sdk/common/model/ClientErrorCause;", "msg", "", "(Lcom/kakao/sdk/common/model/ClientErrorCause;Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "getReason", "()Lcom/kakao/sdk/common/model/ClientErrorCause;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ClientError extends KakaoSdkError implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final ClientError createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new ClientError(ClientErrorCause.valueOf(parcel0.readString()), parcel0.readString());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final ClientError[] newArray(int v) {
            return new ClientError[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String msg;
    private final ClientErrorCause reason;

    static {
        ClientError.CREATOR = new Creator();
    }

    public ClientError(ClientErrorCause clientErrorCause0, String s) {
        Intrinsics.checkNotNullParameter(clientErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(s, "msg");
        super(s, null);
        this.reason = clientErrorCause0;
        this.msg = s;
    }

    public ClientError(ClientErrorCause clientErrorCause0, String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            Description description0 = (Description)clientErrorCause0.getClass().getField(clientErrorCause0.name()).getAnnotation(Description.class);
            s = description0 == null ? "Client-side error" : description0.value();
        }
        this(clientErrorCause0, s);
    }

    public final ClientErrorCause component1() {
        return this.reason;
    }

    public final String component2() {
        return this.getMsg();
    }

    public final ClientError copy(ClientErrorCause clientErrorCause0, String s) {
        Intrinsics.checkNotNullParameter(clientErrorCause0, "reason");
        Intrinsics.checkNotNullParameter(s, "msg");
        return new ClientError(clientErrorCause0, s);
    }

    public static ClientError copy$default(ClientError clientError0, ClientErrorCause clientErrorCause0, String s, int v, Object object0) {
        if((v & 1) != 0) {
            clientErrorCause0 = clientError0.reason;
        }
        if((v & 2) != 0) {
            s = clientError0.getMsg();
        }
        return clientError0.copy(clientErrorCause0, s);
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
        if(!(object0 instanceof ClientError)) {
            return false;
        }
        return this.reason == ((ClientError)object0).reason ? Intrinsics.areEqual(this.getMsg(), ((ClientError)object0).getMsg()) : false;
    }

    @Override  // com.kakao.sdk.common.model.KakaoSdkError
    public String getMsg() {
        return this.msg;
    }

    public final ClientErrorCause getReason() {
        return this.reason;
    }

    @Override
    public int hashCode() {
        return this.reason.hashCode() * 0x1F + this.getMsg().hashCode();
    }

    @Override
    public String toString() {
        return "ClientError(reason=" + this.reason + ", msg=" + this.getMsg() + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.reason.name());
        parcel0.writeString(this.msg);
    }
}

