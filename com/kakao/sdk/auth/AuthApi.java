package com.kakao.sdk.auth;

import kotlin.Metadata;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\u0007\u001A\u00020\u0006H\'JX\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\n\u001A\u00020\u00062\b\b\u0001\u0010\u000B\u001A\u00020\u00062\b\b\u0001\u0010\f\u001A\u00020\u00062\n\b\u0003\u0010\r\u001A\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000E\u001A\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u000F\u001A\u00020\u0006H\'JB\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001A\u00020\u00062\b\b\u0001\u0010\n\u001A\u00020\u00062\b\b\u0001\u0010\u0010\u001A\u00020\u00062\n\b\u0003\u0010\u000E\u001A\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u000F\u001A\u00020\u0006H\'¨\u0006\u0011"}, d2 = {"Lcom/kakao/sdk/auth/AuthApi;", "", "agt", "Lretrofit2/Call;", "Lcom/kakao/sdk/auth/model/AgtResponse;", "clientId", "", "accessToken", "issueAccessToken", "Lcom/kakao/sdk/auth/model/AccessTokenResponse;", "androidKeyHash", "code", "redirectUri", "codeVerifier", "approvalType", "grantType", "refreshToken", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public interface AuthApi {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class DefaultImpls {
        public static Call issueAccessToken$default(AuthApi authApi0, String s, String s1, String s2, String s3, String s4, String s5, String s6, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: issueAccessToken");
            }
            if((v & 16) != 0) {
                s4 = null;
            }
            if((v & 0x20) != 0) {
                s5 = null;
            }
            if((v & 0x40) != 0) {
                s6 = "authorization_code";
            }
            return authApi0.issueAccessToken(s, s1, s2, s3, s4, s5, s6);
        }

        public static Call refreshToken$default(AuthApi authApi0, String s, String s1, String s2, String s3, String s4, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: refreshToken");
            }
            if((v & 8) != 0) {
                s3 = null;
            }
            if((v & 16) != 0) {
                s4 = "refresh_token";
            }
            return authApi0.refreshToken(s, s1, s2, s3, s4);
        }
    }

    @FormUrlEncoded
    @POST("api/agt")
    Call agt(@Field("client_id") String arg1, @Field("access_token") String arg2);

    @FormUrlEncoded
    @POST("oauth/token")
    Call issueAccessToken(@Field("client_id") String arg1, @Field("android_key_hash") String arg2, @Field("code") String arg3, @Field("redirect_uri") String arg4, @Field("code_verifier") String arg5, @Field("approval_type") String arg6, @Field("grant_type") String arg7);

    @FormUrlEncoded
    @POST("oauth/token")
    Call refreshToken(@Field("client_id") String arg1, @Field("android_key_hash") String arg2, @Field("refresh_token") String arg3, @Field("approval_type") String arg4, @Field("grant_type") String arg5);
}

