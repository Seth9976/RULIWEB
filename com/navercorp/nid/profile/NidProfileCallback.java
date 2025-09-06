package com.navercorp.nid.profile;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH&J\u0015\u0010\u000B\u001A\u00020\u00042\u0006\u0010\f\u001A\u00028\u0000H&¢\u0006\u0002\u0010\r¨\u0006\u000E"}, d2 = {"Lcom/navercorp/nid/profile/NidProfileCallback;", "T", "", "onError", "", "errorCode", "", "message", "", "onFailure", "httpStatus", "onSuccess", "result", "(Ljava/lang/Object;)V", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface NidProfileCallback {
    void onError(int arg1, String arg2);

    void onFailure(int arg1, String arg2);

    void onSuccess(Object arg1);
}

