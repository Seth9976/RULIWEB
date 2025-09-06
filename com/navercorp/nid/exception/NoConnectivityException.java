package com.navercorp.nid.exception;

import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/navercorp/nid/exception/NoConnectivityException;", "Ljava/io/IOException;", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NoConnectivityException extends IOException {
    @Override
    public String getMessage() {
        return "No Internet Connection";
    }
}

