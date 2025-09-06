package com.kakao.sdk.user;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/kakao/sdk/user/UserApiClient;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class UserApiClient.Companion.instance.2 extends Lambda implements Function0 {
    public static final UserApiClient.Companion.instance.2 INSTANCE;

    static {
        UserApiClient.Companion.instance.2.INSTANCE = new UserApiClient.Companion.instance.2();
    }

    UserApiClient.Companion.instance.2() {
        super(0);
    }

    public final UserApiClient invoke() {
        return new UserApiClient(null, null, 3, null);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

