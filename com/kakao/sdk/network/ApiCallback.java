package com.kakao.sdk.network;

import com.kakao.sdk.common.model.ApiError;
import com.kakao.sdk.common.model.ApiErrorCause;
import com.kakao.sdk.common.model.ApiErrorResponse;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.common.util.SdkLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u0011*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0003J!\u0010\u0004\u001A\u00020\u00052\b\u0010\u0006\u001A\u0004\u0018\u00018\u00002\b\u0010\u0007\u001A\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001E\u0010\n\u001A\u00020\u00052\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\r\u001A\u00020\bH\u0016J$\u0010\u000E\u001A\u00020\u00052\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00028\u00000\f2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0016¨\u0006\u0012"}, d2 = {"Lcom/kakao/sdk/network/ApiCallback;", "T", "Lretrofit2/Callback;", "()V", "onComplete", "", "model", "error", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "onFailure", "call", "Lretrofit2/Call;", "t", "onResponse", "response", "Lretrofit2/Response;", "Companion", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public abstract class ApiCallback implements Callback {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004¨\u0006\u0006"}, d2 = {"Lcom/kakao/sdk/network/ApiCallback$Companion;", "", "()V", "translateError", "", "t", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Throwable translateError(Throwable throwable0) {
            Intrinsics.checkNotNullParameter(throwable0, "t");
            try {
                if(throwable0 instanceof HttpException) {
                    Response response0 = ((HttpException)throwable0).response();
                    String s = null;
                    if(response0 != null) {
                        ResponseBody responseBody0 = response0.errorBody();
                        if(responseBody0 != null) {
                            s = responseBody0.string();
                        }
                    }
                    Intrinsics.checkNotNull(s);
                    ApiErrorResponse apiErrorResponse0 = (ApiErrorResponse)KakaoJson.INSTANCE.fromJson(s, ApiErrorResponse.class);
                    ApiErrorCause apiErrorCause0 = (ApiErrorCause)KakaoJson.INSTANCE.fromJson(String.valueOf(apiErrorResponse0.getCode()), ApiErrorCause.class);
                    if(apiErrorCause0 == null) {
                        apiErrorCause0 = ApiErrorCause.Unknown;
                    }
                    return new ApiError(((HttpException)throwable0).code(), apiErrorCause0, apiErrorResponse0);
                }
                return throwable0;
            }
            catch(Throwable throwable1) {
                return throwable1;
            }
        }
    }

    public static final Companion Companion;

    static {
        ApiCallback.Companion = new Companion(null);
    }

    public abstract void onComplete(Object arg1, Throwable arg2);

    @Override  // retrofit2.Callback
    public void onFailure(Call call0, Throwable throwable0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(throwable0, "t");
        Throwable throwable1 = ExceptionWrapperKt.getOrigin(throwable0);
        SdkLog.Companion.e(throwable1);
        this.onComplete(null, throwable1);
    }

    @Override  // retrofit2.Callback
    public void onResponse(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
        Object object0 = response0.body();
        if(object0 != null) {
            SdkLog.Companion.i(object0);
            this.onComplete(object0, null);
            return;
        }
        Throwable throwable0 = new HttpException(response0);
        this.onFailure(call0, ApiCallback.Companion.translateError(throwable0));
    }
}

