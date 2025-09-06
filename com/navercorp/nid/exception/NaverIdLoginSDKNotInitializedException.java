package com.navercorp.nid.exception;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u0014\u0010\u0004\u001A\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/navercorp/nid/exception/NaverIdLoginSDKNotInitializedException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NaverIdLoginSDKNotInitializedException extends Exception {
    @Override
    public String getMessage() {
        return "SDK 초기화가 필요합니다.";
    }
}

