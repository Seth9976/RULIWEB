package com.kakao.sdk.common.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000F\b\u0004\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001A\u00020\tR\u0014\u0010\u0003\u001A\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\n\u000B\f¨\u0006\r"}, d2 = {"Lcom/kakao/sdk/common/model/KakaoSdkError;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "msg", "", "(Ljava/lang/String;)V", "getMsg", "()Ljava/lang/String;", "isInvalidTokenError", "", "Lcom/kakao/sdk/common/model/ApiError;", "Lcom/kakao/sdk/common/model/AuthError;", "Lcom/kakao/sdk/common/model/ClientError;", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public abstract class KakaoSdkError extends RuntimeException {
    private final String msg;

    private KakaoSdkError(String s) {
        super(s);
        this.msg = s;
    }

    public KakaoSdkError(String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s);
    }

    public String getMsg() {
        return this.msg;
    }

    // 去混淆评级： 低(30)
    public final boolean isInvalidTokenError() {
        return this instanceof AuthError ? ((AuthError)this).getReason() == AuthErrorCause.InvalidGrant : this instanceof ApiError && ((ApiError)this).getReason() == ApiErrorCause.InvalidToken;
    }
}

