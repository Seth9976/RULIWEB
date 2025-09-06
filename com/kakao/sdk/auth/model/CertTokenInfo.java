package com.kakao.sdk.auth.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000B\u001A\u00020\u0003HÆ\u0003J\t\u0010\f\u001A\u00020\u0005HÆ\u0003J\u001D\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\t\u0010\u000E\u001A\u00020\u000FHÖ\u0001J\u0013\u0010\u0010\u001A\u00020\u00112\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001A\u00020\u000FHÖ\u0001J\t\u0010\u0015\u001A\u00020\u0005HÖ\u0001J\u0019\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u000FHÖ\u0001R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u001B"}, d2 = {"Lcom/kakao/sdk/auth/model/CertTokenInfo;", "Landroid/os/Parcelable;", "token", "Lcom/kakao/sdk/auth/model/OAuthToken;", "txId", "", "(Lcom/kakao/sdk/auth/model/OAuthToken;Ljava/lang/String;)V", "getToken", "()Lcom/kakao/sdk/auth/model/OAuthToken;", "getTxId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class CertTokenInfo implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final CertTokenInfo createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new CertTokenInfo(((OAuthToken)OAuthToken.CREATOR.createFromParcel(parcel0)), parcel0.readString());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final CertTokenInfo[] newArray(int v) {
            return new CertTokenInfo[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final OAuthToken token;
    private final String txId;

    static {
        CertTokenInfo.CREATOR = new Creator();
    }

    public CertTokenInfo(OAuthToken oAuthToken0, String s) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "token");
        Intrinsics.checkNotNullParameter(s, "txId");
        super();
        this.token = oAuthToken0;
        this.txId = s;
    }

    public final OAuthToken component1() {
        return this.token;
    }

    public final String component2() {
        return this.txId;
    }

    public final CertTokenInfo copy(OAuthToken oAuthToken0, String s) {
        Intrinsics.checkNotNullParameter(oAuthToken0, "token");
        Intrinsics.checkNotNullParameter(s, "txId");
        return new CertTokenInfo(oAuthToken0, s);
    }

    public static CertTokenInfo copy$default(CertTokenInfo certTokenInfo0, OAuthToken oAuthToken0, String s, int v, Object object0) {
        if((v & 1) != 0) {
            oAuthToken0 = certTokenInfo0.token;
        }
        if((v & 2) != 0) {
            s = certTokenInfo0.txId;
        }
        return certTokenInfo0.copy(oAuthToken0, s);
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
        if(!(object0 instanceof CertTokenInfo)) {
            return false;
        }
        return Intrinsics.areEqual(this.token, ((CertTokenInfo)object0).token) ? Intrinsics.areEqual(this.txId, ((CertTokenInfo)object0).txId) : false;
    }

    public final OAuthToken getToken() {
        return this.token;
    }

    public final String getTxId() {
        return this.txId;
    }

    @Override
    public int hashCode() {
        return this.token.hashCode() * 0x1F + this.txId.hashCode();
    }

    @Override
    public String toString() {
        return "CertTokenInfo(token=" + this.token + ", txId=" + this.txId + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        this.token.writeToParcel(parcel0, v);
        parcel0.writeString(this.txId);
    }
}

