package com.kakao.sdk.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/kakao/sdk/auth/AuthApiClient;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class AuthApiClient.Companion.instance.2 extends Lambda implements Function0 {
    public static final AuthApiClient.Companion.instance.2 INSTANCE;

    static {
        AuthApiClient.Companion.instance.2.INSTANCE = new AuthApiClient.Companion.instance.2();
    }

    AuthApiClient.Companion.instance.2() {
        super(0);
    }

    public final AuthApiClient invoke() {
        return new AuthApiClient(null, null, 3, null);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

