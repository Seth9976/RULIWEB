package com.kakao.sdk.auth.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001A\u00020\u0003\u0012\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010\rJ\t\u0010\u001D\u001A\u00020\u0003H\u00C6\u0003J\u000B\u0010\u001E\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\t\u0010\u001F\u001A\u00020\u0006H\u00C6\u0003J\u0010\u0010 \u001A\u0004\u0018\u00010\u0006H\u00C6\u0003\u00A2\u0006\u0002\u0010\u0015J\u000B\u0010!\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\t\u0010\"\u001A\u00020\u0003H\u00C6\u0003J\u000B\u0010#\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010$\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010%\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003Jt\u0010&\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001A\u00020\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001A\u00020\u00032\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u0003H\u00C6\u0001\u00A2\u0006\u0002\u0010\'J\t\u0010(\u001A\u00020)H\u00D6\u0001J\u0013\u0010*\u001A\u00020+2\b\u0010,\u001A\u0004\u0018\u00010-H\u00D6\u0003J\t\u0010.\u001A\u00020)H\u00D6\u0001J\t\u0010/\u001A\u00020\u0003H\u00D6\u0001J\u0019\u00100\u001A\u0002012\u0006\u00102\u001A\u0002032\u0006\u00104\u001A\u00020)H\u00D6\u0001R\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0016\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u000FR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u000FR\u0015\u0010\u0007\u001A\u0004\u0018\u00010\u0006\u00A2\u0006\n\n\u0002\u0010\u0016\u001A\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u000FR\u001E\u0010\u000B\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\u000E\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001A\u0004\b\u001A\u0010\u000FR\u0011\u0010\t\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u000FR\u0013\u0010\f\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u000F\u00A8\u00065"}, d2 = {"Lcom/kakao/sdk/auth/model/AccessTokenResponse;", "Landroid/os/Parcelable;", "accessToken", "", "refreshToken", "accessTokenExpiresIn", "", "refreshTokenExpiresIn", "idToken", "tokenType", "scope", "scopes", "txId", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getAccessTokenExpiresIn", "()J", "getIdToken", "getRefreshToken", "getRefreshTokenExpiresIn", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getScope", "getScopes$annotations", "()V", "getScopes", "getTokenType", "getTxId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/kakao/sdk/auth/model/AccessTokenResponse;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AccessTokenResponse implements Parcelable {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final AccessTokenResponse createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            String s = parcel0.readString();
            String s1 = parcel0.readString();
            long v = parcel0.readLong();
            return parcel0.readInt() == 0 ? new AccessTokenResponse(s, s1, v, null, parcel0.readString(), parcel0.readString(), parcel0.readString(), parcel0.readString(), parcel0.readString()) : new AccessTokenResponse(s, s1, v, parcel0.readLong(), parcel0.readString(), parcel0.readString(), parcel0.readString(), parcel0.readString(), parcel0.readString());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final AccessTokenResponse[] newArray(int v) {
            return new AccessTokenResponse[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String accessToken;
    @SerializedName("expires_in")
    private final long accessTokenExpiresIn;
    private final String idToken;
    private final String refreshToken;
    private final Long refreshTokenExpiresIn;
    private final String scope;
    private final String scopes;
    private final String tokenType;
    private final String txId;

    static {
        AccessTokenResponse.CREATOR = new Creator();
    }

    public AccessTokenResponse(String s, String s1, long v, Long long0, String s2, String s3, String s4, String s5, String s6) {
        Intrinsics.checkNotNullParameter(s, "accessToken");
        Intrinsics.checkNotNullParameter(s3, "tokenType");
        super();
        this.accessToken = s;
        this.refreshToken = s1;
        this.accessTokenExpiresIn = v;
        this.refreshTokenExpiresIn = long0;
        this.idToken = s2;
        this.tokenType = s3;
        this.scope = s4;
        this.scopes = s5;
        this.txId = s6;
    }

    public AccessTokenResponse(String s, String s1, long v, Long long0, String s2, String s3, String s4, String s5, String s6, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            s1 = null;
        }
        if((v1 & 8) != 0) {
            long0 = null;
        }
        if((v1 & 16) != 0) {
            s2 = null;
        }
        if((v1 & 0x40) != 0) {
            s4 = null;
        }
        if((v1 & 0x80) != 0) {
            s5 = null;
        }
        this(s, s1, v, long0, s2, s3, s4, s5, ((v1 & 0x100) == 0 ? s6 : null));
    }

    public final String component1() {
        return this.accessToken;
    }

    public final String component2() {
        return this.refreshToken;
    }

    public final long component3() {
        return this.accessTokenExpiresIn;
    }

    public final Long component4() {
        return this.refreshTokenExpiresIn;
    }

    public final String component5() {
        return this.idToken;
    }

    public final String component6() {
        return this.tokenType;
    }

    public final String component7() {
        return this.scope;
    }

    public final String component8() {
        return this.scopes;
    }

    public final String component9() {
        return this.txId;
    }

    public final AccessTokenResponse copy(String s, String s1, long v, Long long0, String s2, String s3, String s4, String s5, String s6) {
        Intrinsics.checkNotNullParameter(s, "accessToken");
        Intrinsics.checkNotNullParameter(s3, "tokenType");
        return new AccessTokenResponse(s, s1, v, long0, s2, s3, s4, s5, s6);
    }

    public static AccessTokenResponse copy$default(AccessTokenResponse accessTokenResponse0, String s, String s1, long v, Long long0, String s2, String s3, String s4, String s5, String s6, int v1, Object object0) {
        if((v1 & 1) != 0) {
            s = accessTokenResponse0.accessToken;
        }
        if((v1 & 2) != 0) {
            s1 = accessTokenResponse0.refreshToken;
        }
        if((v1 & 4) != 0) {
            v = accessTokenResponse0.accessTokenExpiresIn;
        }
        if((v1 & 8) != 0) {
            long0 = accessTokenResponse0.refreshTokenExpiresIn;
        }
        if((v1 & 16) != 0) {
            s2 = accessTokenResponse0.idToken;
        }
        if((v1 & 0x20) != 0) {
            s3 = accessTokenResponse0.tokenType;
        }
        if((v1 & 0x40) != 0) {
            s4 = accessTokenResponse0.scope;
        }
        if((v1 & 0x80) != 0) {
            s5 = accessTokenResponse0.scopes;
        }
        if((v1 & 0x100) != 0) {
            s6 = accessTokenResponse0.txId;
        }
        return accessTokenResponse0.copy(s, s1, v, long0, s2, s3, s4, s5, s6);
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
        if(!(object0 instanceof AccessTokenResponse)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.accessToken, ((AccessTokenResponse)object0).accessToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.refreshToken, ((AccessTokenResponse)object0).refreshToken)) {
            return false;
        }
        if(this.accessTokenExpiresIn != ((AccessTokenResponse)object0).accessTokenExpiresIn) {
            return false;
        }
        if(!Intrinsics.areEqual(this.refreshTokenExpiresIn, ((AccessTokenResponse)object0).refreshTokenExpiresIn)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.idToken, ((AccessTokenResponse)object0).idToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.tokenType, ((AccessTokenResponse)object0).tokenType)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.scope, ((AccessTokenResponse)object0).scope)) {
            return false;
        }
        return Intrinsics.areEqual(this.scopes, ((AccessTokenResponse)object0).scopes) ? Intrinsics.areEqual(this.txId, ((AccessTokenResponse)object0).txId) : false;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final long getAccessTokenExpiresIn() {
        return this.accessTokenExpiresIn;
    }

    public final String getIdToken() {
        return this.idToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final Long getRefreshTokenExpiresIn() {
        return this.refreshTokenExpiresIn;
    }

    public final String getScope() {
        return this.scope;
    }

    public final String getScopes() {
        return this.scopes;
    }

    @Deprecated(message = "해당 property는 \'scope\'로 대체되었습니다. ")
    public static void getScopes$annotations() {
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public final String getTxId() {
        return this.txId;
    }

    @Override
    public int hashCode() {
        int v = this.accessToken.hashCode();
        int v1 = 0;
        int v2 = this.refreshToken == null ? 0 : this.refreshToken.hashCode();
        int v3 = (int)(this.accessTokenExpiresIn ^ this.accessTokenExpiresIn >>> 0x20);
        int v4 = this.refreshTokenExpiresIn == null ? 0 : this.refreshTokenExpiresIn.hashCode();
        int v5 = this.idToken == null ? 0 : this.idToken.hashCode();
        int v6 = this.tokenType.hashCode();
        int v7 = this.scope == null ? 0 : this.scope.hashCode();
        int v8 = this.scopes == null ? 0 : this.scopes.hashCode();
        String s = this.txId;
        if(s != null) {
            v1 = s.hashCode();
        }
        return (((((((v * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v7) * 0x1F + v8) * 0x1F + v1;
    }

    @Override
    public String toString() {
        return "AccessTokenResponse(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", accessTokenExpiresIn=" + this.accessTokenExpiresIn + ", refreshTokenExpiresIn=" + this.refreshTokenExpiresIn + ", idToken=" + this.idToken + ", tokenType=" + this.tokenType + ", scope=" + this.scope + ", scopes=" + this.scopes + ", txId=" + this.txId + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.accessToken);
        parcel0.writeString(this.refreshToken);
        parcel0.writeLong(this.accessTokenExpiresIn);
        Long long0 = this.refreshTokenExpiresIn;
        if(long0 == null) {
            parcel0.writeInt(0);
        }
        else {
            parcel0.writeInt(1);
            parcel0.writeLong(((long)long0));
        }
        parcel0.writeString(this.idToken);
        parcel0.writeString(this.tokenType);
        parcel0.writeString(this.scope);
        parcel0.writeString(this.scopes);
        parcel0.writeString(this.txId);
    }
}

