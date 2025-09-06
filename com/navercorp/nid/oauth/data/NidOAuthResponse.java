package com.navercorp.nid.oauth.data;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u000E\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010\nJ\u000B\u0010\u0016\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u0017\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u0018\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u0019\u001A\u0004\u0018\u00010\u0003H\u00C2\u0003J\u000B\u0010\u001A\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u001B\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\u000B\u0010\u001C\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J]\u0010\u001D\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\u0003H\u00C6\u0001J\u0013\u0010\u001E\u001A\u00020\u001F2\b\u0010 \u001A\u0004\u0018\u00010\u0001H\u00D6\u0003J\t\u0010!\u001A\u00020\"H\u00D6\u0001J\t\u0010#\u001A\u00020\u0003H\u00D6\u0001R\u0018\u0010\u0002\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0018\u0010\b\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0018\u0010\t\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\fR\u0011\u0010\u000F\u001A\u00020\u00108F\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0006\u001A\u0004\u0018\u00010\u00038\u0002X\u0083\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\fR\u0018\u0010\u0007\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\fR\u0018\u0010\u0005\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\f\u00A8\u0006$"}, d2 = {"Lcom/navercorp/nid/oauth/data/NidOAuthResponse;", "", "accessToken", "", "refreshToken", "tokenType", "expiresInString", "result", "error", "errorDescription", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getError", "getErrorDescription", "expiresIn", "", "getExpiresIn", "()J", "getRefreshToken", "getResult", "getTokenType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthResponse {
    @SerializedName("access_token")
    private final String accessToken;
    @SerializedName("error")
    private final String error;
    @SerializedName("error_description")
    private final String errorDescription;
    @SerializedName("expires_in")
    private final String expiresInString;
    @SerializedName("refresh_token")
    private final String refreshToken;
    @SerializedName("result")
    private final String result;
    @SerializedName("token_type")
    private final String tokenType;

    public NidOAuthResponse(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        this.accessToken = s;
        this.refreshToken = s1;
        this.tokenType = s2;
        this.expiresInString = s3;
        this.result = s4;
        this.error = s5;
        this.errorDescription = s6;
    }

    public final String component1() {
        return this.accessToken;
    }

    public final String component2() {
        return this.refreshToken;
    }

    public final String component3() {
        return this.tokenType;
    }

    private final String component4() {
        return this.expiresInString;
    }

    public final String component5() {
        return this.result;
    }

    public final String component6() {
        return this.error;
    }

    public final String component7() {
        return this.errorDescription;
    }

    public final NidOAuthResponse copy(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        return new NidOAuthResponse(s, s1, s2, s3, s4, s5, s6);
    }

    public static NidOAuthResponse copy$default(NidOAuthResponse nidOAuthResponse0, String s, String s1, String s2, String s3, String s4, String s5, String s6, int v, Object object0) {
        if((v & 1) != 0) {
            s = nidOAuthResponse0.accessToken;
        }
        if((v & 2) != 0) {
            s1 = nidOAuthResponse0.refreshToken;
        }
        if((v & 4) != 0) {
            s2 = nidOAuthResponse0.tokenType;
        }
        if((v & 8) != 0) {
            s3 = nidOAuthResponse0.expiresInString;
        }
        if((v & 16) != 0) {
            s4 = nidOAuthResponse0.result;
        }
        if((v & 0x20) != 0) {
            s5 = nidOAuthResponse0.error;
        }
        if((v & 0x40) != 0) {
            s6 = nidOAuthResponse0.errorDescription;
        }
        return nidOAuthResponse0.copy(s, s1, s2, s3, s4, s5, s6);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof NidOAuthResponse)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.accessToken, ((NidOAuthResponse)object0).accessToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.refreshToken, ((NidOAuthResponse)object0).refreshToken)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.tokenType, ((NidOAuthResponse)object0).tokenType)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.expiresInString, ((NidOAuthResponse)object0).expiresInString)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.result, ((NidOAuthResponse)object0).result)) {
            return false;
        }
        return Intrinsics.areEqual(this.error, ((NidOAuthResponse)object0).error) ? Intrinsics.areEqual(this.errorDescription, ((NidOAuthResponse)object0).errorDescription) : false;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getError() {
        return this.error;
    }

    public final String getErrorDescription() {
        return this.errorDescription;
    }

    public final long getExpiresIn() {
        return this.expiresInString == null ? 3600L : Long.parseLong(this.expiresInString);
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final String getResult() {
        return this.result;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.accessToken == null ? 0 : this.accessToken.hashCode();
        int v2 = this.refreshToken == null ? 0 : this.refreshToken.hashCode();
        int v3 = this.tokenType == null ? 0 : this.tokenType.hashCode();
        int v4 = this.expiresInString == null ? 0 : this.expiresInString.hashCode();
        int v5 = this.result == null ? 0 : this.result.hashCode();
        int v6 = this.error == null ? 0 : this.error.hashCode();
        String s = this.errorDescription;
        if(s != null) {
            v = s.hashCode();
        }
        return (((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v;
    }

    @Override
    public String toString() {
        return "NidOAuthResponse(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", tokenType=" + this.tokenType + ", expiresInString=" + this.expiresInString + ", result=" + this.result + ", error=" + this.error + ", errorDescription=" + this.errorDescription + ")";
    }
}

