package com.kakao.sdk.common.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001A\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/kakao/sdk/common/util/SdkLog;"}, k = 3, mv = {1, 5, 1}, xi = 0x30)
final class SdkLog.Companion.instance.2 extends Lambda implements Function0 {
    public static final SdkLog.Companion.instance.2 INSTANCE;

    static {
        SdkLog.Companion.instance.2.INSTANCE = new SdkLog.Companion.instance.2();
    }

    SdkLog.Companion.instance.2() {
        super(0);
    }

    public final SdkLog invoke() {
        return new SdkLog(false, 1, null);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }
}

