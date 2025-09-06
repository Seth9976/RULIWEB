package com.kakao.sdk.network;

import com.kakao.sdk.common.model.ApiError;
import com.kakao.sdk.common.model.ApiErrorCause;
import com.kakao.sdk.common.model.ApiErrorResponse;
import com.kakao.sdk.common.util.KakaoJson;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\u001AP\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u000426\u0010\u0005\u001A2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000B\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u001AR\u0010\f\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u000428\u0010\r\u001A4\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u000E¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\u00010\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"proceedApiError", "Lokhttp3/Response;", "Lokhttp3/Interceptor$Chain;", "request", "Lokhttp3/Request;", "errorHandler", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "response", "Lcom/kakao/sdk/common/model/ApiError;", "error", "proceedBodyString", "bodyHandler", "", "bodyString", "network_release"}, k = 2, mv = {1, 5, 1}, xi = 0x30)
public final class UtilityKt {
    public static final Response proceedApiError(Chain interceptor$Chain0, Request request0, Function2 function20) {
        ResponseBody responseBody1;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "<this>");
        Intrinsics.checkNotNullParameter(request0, "request");
        Intrinsics.checkNotNullParameter(function20, "errorHandler");
        Response response0 = interceptor$Chain0.proceed(request0);
        ResponseBody responseBody0 = response0.body();
        ApiErrorCause apiErrorCause0 = null;
        String s = responseBody0 == null ? null : responseBody0.string();
        Builder response$Builder0 = response0.newBuilder();
        if(s == null) {
            responseBody1 = null;
        }
        else {
            MediaType mediaType0 = responseBody0.contentType();
            responseBody1 = ResponseBody.Companion.create(s, mediaType0);
        }
        Response response1 = response$Builder0.body(responseBody1).build();
        if(s != null) {
            MediaType mediaType1 = responseBody0.contentType();
            ResponseBody.Companion.create(s, mediaType1);
        }
        if(!response1.isSuccessful()) {
            ApiErrorResponse apiErrorResponse0 = s == null ? null : ((ApiErrorResponse)KakaoJson.INSTANCE.fromJson(s, ApiErrorResponse.class));
            if(apiErrorResponse0 != null) {
                apiErrorCause0 = (ApiErrorCause)KakaoJson.INSTANCE.fromJson(String.valueOf(apiErrorResponse0.getCode()), ApiErrorCause.class);
            }
            return apiErrorCause0 == null ? response1 : ((Response)function20.invoke(response1, new ApiError(response1.code(), apiErrorCause0, apiErrorResponse0)));
        }
        return response1;
    }

    public static final Response proceedBodyString(Chain interceptor$Chain0, Request request0, Function2 function20) {
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "<this>");
        Intrinsics.checkNotNullParameter(request0, "request");
        Intrinsics.checkNotNullParameter(function20, "bodyHandler");
        Response response0 = interceptor$Chain0.proceed(request0);
        ResponseBody responseBody0 = response0.body();
        ResponseBody responseBody1 = null;
        String s = responseBody0 == null ? null : responseBody0.string();
        Builder response$Builder0 = response0.newBuilder();
        if(s != null) {
            MediaType mediaType0 = responseBody0.contentType();
            responseBody1 = ResponseBody.Companion.create(s, mediaType0);
        }
        Response response1 = response$Builder0.body(responseBody1).build();
        if(s != null) {
            MediaType mediaType1 = responseBody0.contentType();
            ResponseBody.Companion.create(s, mediaType1);
        }
        return (Response)function20.invoke(response1, s);
    }
}

