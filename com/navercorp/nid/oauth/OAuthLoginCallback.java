package com.navercorp.nid.oauth;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\u0018\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\b\u0010\n\u001A\u00020\u0003H&¨\u0006\u000B"}, d2 = {"Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "", "onError", "", "errorCode", "", "message", "", "onFailure", "httpStatus", "onSuccess", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public interface OAuthLoginCallback {
    void onError(int arg1, String arg2);

    void onFailure(int arg1, String arg2);

    void onSuccess();
}

