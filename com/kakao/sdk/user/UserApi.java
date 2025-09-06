package com.kakao.sdk.user;

import com.kakao.sdk.common.json.IntDate;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000E\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u000E\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003H\'J$\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0003\u0010\t\u001A\u00020\n2\n\b\u0003\u0010\u000B\u001A\u0004\u0018\u00010\fH\'J\u0018\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000E0\u00032\b\b\u0001\u0010\u000F\u001A\u00020\fH\'J\u001A\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u00032\n\b\u0003\u0010\u000F\u001A\u0004\u0018\u00010\fH\'J\u001A\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00110\u00032\n\b\u0003\u0010\u0012\u001A\u0004\u0018\u00010\fH\'J7\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00140\u00032\n\b\u0003\u0010\u0015\u001A\u0004\u0018\u00010\u00162\n\b\u0003\u0010\u0017\u001A\u0004\u0018\u00010\u00182\n\b\u0003\u0010\u0019\u001A\u0004\u0018\u00010\u001AH\'¢\u0006\u0002\u0010\u001BJ&\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00060\u00032\u0016\b\u0003\u0010\u000B\u001A\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u001DH\'J\u000E\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003H\'J$\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020\u00060\u00032\u0014\b\u0001\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u001DH\'¨\u0006 "}, d2 = {"Lcom/kakao/sdk/user/UserApi;", "", "accessTokenInfo", "Lretrofit2/Call;", "Lcom/kakao/sdk/user/model/AccessTokenInfo;", "logout", "", "me", "Lcom/kakao/sdk/user/model/User;", "secureResource", "", "properties", "", "revokeScopes", "Lcom/kakao/sdk/user/model/ScopeInfo;", "scopes", "serviceTerms", "Lcom/kakao/sdk/user/model/UserServiceTerms;", "extra", "shippingAddresses", "Lcom/kakao/sdk/user/model/UserShippingAddresses;", "addressId", "", "fromUpdatedAt", "Ljava/util/Date;", "pageSize", "", "(Ljava/lang/Long;Ljava/util/Date;Ljava/lang/Integer;)Lretrofit2/Call;", "signup", "", "unlink", "updateProfile", "user_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public interface UserApi {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class DefaultImpls {
        public static Call me$default(UserApi userApi0, boolean z, String s, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: me");
            }
            if((v & 1) != 0) {
                z = true;
            }
            if((v & 2) != 0) {
                s = null;
            }
            return userApi0.me(z, s);
        }

        public static Call scopes$default(UserApi userApi0, String s, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: scopes");
            }
            if((v & 1) != 0) {
                s = null;
            }
            return userApi0.scopes(s);
        }

        public static Call serviceTerms$default(UserApi userApi0, String s, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: serviceTerms");
            }
            if((v & 1) != 0) {
                s = null;
            }
            return userApi0.serviceTerms(s);
        }

        public static Call shippingAddresses$default(UserApi userApi0, Long long0, Date date0, Integer integer0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: shippingAddresses");
            }
            if((v & 1) != 0) {
                long0 = null;
            }
            if((v & 2) != 0) {
                date0 = null;
            }
            if((v & 4) != 0) {
                integer0 = null;
            }
            return userApi0.shippingAddresses(long0, date0, integer0);
        }

        public static Call signup$default(UserApi userApi0, Map map0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signup");
            }
            if((v & 1) != 0) {
                map0 = null;
            }
            return userApi0.signup(map0);
        }
    }

    @GET("/v1/user/access_token_info")
    Call accessTokenInfo();

    @POST("/v1/user/logout")
    Call logout();

    @GET("/v2/user/me")
    Call me(@Query("secure_resource") boolean arg1, @Query("property_keys") String arg2);

    @FormUrlEncoded
    @POST("/v2/user/revoke/scopes")
    Call revokeScopes(@Field("scopes") String arg1);

    @GET("/v2/user/scopes")
    Call scopes(@Query("scopes") String arg1);

    @GET("/v1/user/service/terms")
    Call serviceTerms(@Query("extra") String arg1);

    @GET("/v1/user/shipping_address")
    Call shippingAddresses(@Query("address_id") Long arg1, @IntDate @Query("from_updated_at") Date arg2, @Query("page_size") Integer arg3);

    @FormUrlEncoded
    @POST("/v1/user/signup")
    Call signup(@Field("properties") Map arg1);

    @POST("/v1/user/unlink")
    Call unlink();

    @FormUrlEncoded
    @POST("/v1/user/update_profile")
    Call updateProfile(@Field("properties") Map arg1);
}

