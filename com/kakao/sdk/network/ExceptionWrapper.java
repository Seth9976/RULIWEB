package com.kakao.sdk.network;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/kakao/sdk/network/ExceptionWrapper;", "Ljava/io/IOException;", "origin", "", "(Ljava/lang/Throwable;)V", "getOrigin", "()Ljava/lang/Throwable;", "network_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class ExceptionWrapper extends IOException {
    private final Throwable origin;

    public ExceptionWrapper(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "origin");
        super();
        this.origin = throwable0;
    }

    public final Throwable getOrigin() {
        return this.origin;
    }
}

