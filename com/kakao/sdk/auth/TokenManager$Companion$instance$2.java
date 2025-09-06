package com.kakao.sdk.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/kakao/sdk/auth/TokenManager;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class TokenManager.Companion.instance.2 extends Lambda implements Function0 {
    public static final TokenManager.Companion.instance.2 INSTANCE;

    static {
        TokenManager.Companion.instance.2.INSTANCE = new TokenManager.Companion.instance.2();
    }

    TokenManager.Companion.instance.2() {
        super(0);
    }

    public final TokenManager invoke() {
        return new TokenManager(null, null, 3, null);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

