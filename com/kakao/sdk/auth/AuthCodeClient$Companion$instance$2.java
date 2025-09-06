package com.kakao.sdk.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/kakao/sdk/auth/AuthCodeClient;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class AuthCodeClient.Companion.instance.2 extends Lambda implements Function0 {
    public static final AuthCodeClient.Companion.instance.2 INSTANCE;

    static {
        AuthCodeClient.Companion.instance.2.INSTANCE = new AuthCodeClient.Companion.instance.2();
    }

    AuthCodeClient.Companion.instance.2() {
        super(0);
    }

    public final AuthCodeClient invoke() {
        return new AuthCodeClient(null, null, null, null, 15, null);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

