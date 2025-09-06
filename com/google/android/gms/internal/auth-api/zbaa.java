package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbaa implements RemoteCall {
    public final zbaf zba;
    public final SaveAccountLinkingTokenRequest zbb;

    public zbaa(zbaf zbaf0, SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest0) {
        this.zba = zbaf0;
        this.zbb = saveAccountLinkingTokenRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zbad zbad0 = new zbad(this.zba, ((TaskCompletionSource)object1));
        ((zbm)((zbg)object0).getService()).zbc(zbad0, ((SaveAccountLinkingTokenRequest)Preconditions.checkNotNull(this.zbb)));
    }
}

