package com.kakao.sdk.auth.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0001)BC\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0003\u0012\u0006\u0010\u0007\u001A\u00020\u0005\u0012\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u00A2\u0006\u0002\u0010\u000BJ\t\u0010\u0015\u001A\u00020\u0003H\u00C6\u0003J\t\u0010\u0016\u001A\u00020\u0005H\u00C6\u0003J\t\u0010\u0017\u001A\u00020\u0003H\u00C6\u0003J\t\u0010\u0018\u001A\u00020\u0005H\u00C6\u0003J\u000B\u0010\u0019\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u0011\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nH\u00C6\u0003JO\u0010\u001B\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00032\b\b\u0002\u0010\u0007\u001A\u00020\u00052\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nH\u00C6\u0001J\t\u0010\u001C\u001A\u00020\u001DH\u00D6\u0001J\u0013\u0010\u001E\u001A\u00020\u001F2\b\u0010 \u001A\u0004\u0018\u00010!H\u00D6\u0003J\t\u0010\"\u001A\u00020\u001DH\u00D6\u0001J\t\u0010#\u001A\u00020\u0003H\u00D6\u0001J\u0019\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\'2\u0006\u0010(\u001A\u00020\u001DH\u00D6\u0001R\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0013\u0010\b\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\rR\u0011\u0010\u0006\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\rR\u0011\u0010\u0007\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u000FR\u0019\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014\u00A8\u0006*"}, d2 = {"Lcom/kakao/sdk/auth/model/OAuthToken;", "Landroid/os/Parcelable;", "accessToken", "", "accessTokenExpiresAt", "Ljava/util/Date;", "refreshToken", "refreshTokenExpiresAt", "idToken", "scopes", "", "(Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/util/List;)V", "getAccessToken", "()Ljava/lang/String;", "getAccessTokenExpiresAt", "()Ljava/util/Date;", "getIdToken", "getRefreshToken", "getRefreshTokenExpiresAt", "getScopes", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class OAuthToken implements Parcelable {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u0004¨\u0006\b"}, d2 = {"Lcom/kakao/sdk/auth/model/OAuthToken$Companion;", "", "()V", "fromResponse", "Lcom/kakao/sdk/auth/model/OAuthToken;", "response", "Lcom/kakao/sdk/auth/model/AccessTokenResponse;", "oldToken", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final OAuthToken fromResponse(AccessTokenResponse accessTokenResponse0, OAuthToken oAuthToken0) {
            Date date1;
            Intrinsics.checkNotNullParameter(accessTokenResponse0, "response");
            String s = accessTokenResponse0.getAccessToken();
            Date date0 = new Date(new Date().getTime() + accessTokenResponse0.getAccessTokenExpiresIn() * 1000L);
            String s1 = accessTokenResponse0.getRefreshToken();
            if(s1 == null) {
                if(oAuthToken0 == null) {
                    throw new ClientError(ClientErrorCause.TokenNotFound, "Refresh token not found in the response.");
                }
                s1 = oAuthToken0.getRefreshToken();
            }
            List list0 = null;
            if(accessTokenResponse0.getRefreshToken() == null) {
                date1 = oAuthToken0 == null ? null : oAuthToken0.getRefreshTokenExpiresAt();
                Intrinsics.checkNotNull(date1);
            }
            else {
                Long long0 = accessTokenResponse0.getRefreshTokenExpiresIn();
                date1 = long0 == null ? null : new Date(new Date().getTime() + long0.longValue() * 1000L);
                if(date1 == null) {
                    date1 = new Date();
                }
            }
            String s2 = accessTokenResponse0.getScope();
            List list1 = s2 == null ? null : StringsKt.split$default(s2, new String[]{" "}, false, 0, 6, null);
            if(list1 == null) {
                if(oAuthToken0 != null) {
                    list0 = oAuthToken0.getScopes();
                }
                return new OAuthToken(s, date0, s1, date1, accessTokenResponse0.getIdToken(), list0);
            }
            return new OAuthToken(s, date0, s1, date1, accessTokenResponse0.getIdToken(), list1);
        }

        public static OAuthToken fromResponse$default(Companion oAuthToken$Companion0, AccessTokenResponse accessTokenResponse0, OAuthToken oAuthToken0, int v, Object object0) {
            if((v & 2) != 0) {
                oAuthToken0 = null;
            }
            return oAuthToken$Companion0.fromResponse(accessTokenResponse0, oAuthToken0);
        }
    }

    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class Creator implements Parcelable.Creator {
        public final OAuthToken createFromParcel(Parcel parcel0) {
            Intrinsics.checkNotNullParameter(parcel0, "parcel");
            return new OAuthToken(parcel0.readString(), ((Date)parcel0.readSerializable()), parcel0.readString(), ((Date)parcel0.readSerializable()), parcel0.readString(), parcel0.createStringArrayList());
        }

        @Override  // android.os.Parcelable$Creator
        public Object createFromParcel(Parcel parcel0) {
            return this.createFromParcel(parcel0);
        }

        public final OAuthToken[] newArray(int v) {
            return new OAuthToken[v];
        }

        @Override  // android.os.Parcelable$Creator
        public Object[] newArray(int v) {
            return this.newArray(v);
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final Companion Companion;
    private final String accessToken;
    private final Date accessTokenExpiresAt;
    private final String idToken;
    private final String refreshToken;
    private final Date refreshTokenExpiresAt;
    private final List scopes;

    static {
        OAuthToken.Companion = new Companion(null);
        OAuthToken.CREATOR = new Creator();
    }

    public OAuthToken(String s, Date date0, String s1, Date date1, String s2, List list0) {
        Intrinsics.checkNotNullParameter(s, "accessToken");
        Intrinsics.checkNotNullParameter(date0, "accessTokenExpiresAt");
        Intrinsics.checkNotNullParameter(s1, "refreshToken");
        Intrinsics.checkNotNullParameter(date1, "refreshTokenExpiresAt");
        super();
        this.accessToken = s;
        this.accessTokenExpiresAt = date0;
        this.refreshToken = s1;
        this.refreshTokenExpiresAt = date1;
        this.idToken = s2;
        this.scopes = list0;
    }

    public OAuthToken(String s, Date date0, String s1, Date date1, String s2, List list0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 16) != 0) {
            s2 = null;
        }
        this(s, date0, s1, date1, s2, ((v & 0x20) == 0 ? list0 : null));
    }

    public final String component1() {
        return this.accessToken;
    }

    public final Date component2() {
        return this.accessTokenExpiresAt;
    }

    public final String component3() {
        return this.refreshToken;
    }

    public final Date component4() {
        return this.refreshTokenExpiresAt;
    }

    public final String component5() {
        return this.idToken;
    }

    public final List component6() {
        return this.scopes;
    }

    public final OAuthToken copy(String s, Date date0, String s1, Date date1, String s2, List list0) {
        Intrinsics.checkNotNullParameter(s, "accessToken");
        Intrinsics.checkNotNullParameter(date0, "accessTokenExpiresAt");
        Intrinsics.checkNotNullParameter(s1, "refreshToken");
        Intrinsics.checkNotNullParameter(date1, "refreshTokenExpiresAt");
        return new OAuthToken(s, date0, s1, date1, s2, list0);
    }

    public static OAuthToken copy$default(OAuthToken oAuthToken0, String s, Date date0, String s1, Date date1, String s2, List list0, int v, Object object0) {
        if((v & 1) != 0) {
            s = oAuthToken0.accessToken;
        }
        if((v & 2) != 0) {
            date0 = oAuthToken0.accessTokenExpiresAt;
        }
        if((v & 4) != 0) {
            s1 = oAuthToken0.refreshToken;
        }
        if((v & 8) != 0) {
            date1 = oAuthToken0.refreshTokenExpiresAt;
        }
        if((v & 16) != 0) {
            s2 = oAuthToken0.idToken;
        }
        if((v & 0x20) != 0) {
            list0 = oAuthToken0.scopes;
        }
        return oAuthToken0.copy(s, date0, s1, date1, s2, list0);
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
        if(!(object0 instanceof OAuthToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.accessToken, ((OAuthToken)object0).accessToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.accessTokenExpiresAt, ((OAuthToken)object0).accessTokenExpiresAt)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.refreshToken, ((OAuthToken)object0).refreshToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.refreshTokenExpiresAt, ((OAuthToken)object0).refreshTokenExpiresAt)) {
            return false;
        }
        return Intrinsics.areEqual(this.idToken, ((OAuthToken)object0).idToken) ? Intrinsics.areEqual(this.scopes, ((OAuthToken)object0).scopes) : false;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final Date getAccessTokenExpiresAt() {
        return this.accessTokenExpiresAt;
    }

    public final String getIdToken() {
        return this.idToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final Date getRefreshTokenExpiresAt() {
        return this.refreshTokenExpiresAt;
    }

    public final List getScopes() {
        return this.scopes;
    }

    @Override
    public int hashCode() {
        int v = this.accessToken.hashCode();
        int v1 = this.accessTokenExpiresAt.hashCode();
        int v2 = this.refreshToken.hashCode();
        int v3 = this.refreshTokenExpiresAt.hashCode();
        int v4 = 0;
        int v5 = this.idToken == null ? 0 : this.idToken.hashCode();
        List list0 = this.scopes;
        if(list0 != null) {
            v4 = list0.hashCode();
        }
        return ((((v * 0x1F + v1) * 0x1F + v2) * 0x1F + v3) * 0x1F + v5) * 0x1F + v4;
    }

    @Override
    public String toString() {
        return "OAuthToken(accessToken=" + this.accessToken + ", accessTokenExpiresAt=" + this.accessTokenExpiresAt + ", refreshToken=" + this.refreshToken + ", refreshTokenExpiresAt=" + this.refreshTokenExpiresAt + ", idToken=" + this.idToken + ", scopes=" + this.scopes + ')';
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        Intrinsics.checkNotNullParameter(parcel0, "out");
        parcel0.writeString(this.accessToken);
        parcel0.writeSerializable(this.accessTokenExpiresAt);
        parcel0.writeString(this.refreshToken);
        parcel0.writeSerializable(this.refreshTokenExpiresAt);
        parcel0.writeString(this.idToken);
        parcel0.writeStringList(this.scopes);
    }
}

