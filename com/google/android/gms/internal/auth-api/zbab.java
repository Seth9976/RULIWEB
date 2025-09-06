package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbab implements RemoteCall {
    public final zbaf zba;
    public final SavePasswordRequest zbb;

    public zbab(zbaf zbaf0, SavePasswordRequest savePasswordRequest0) {
        this.zba = zbaf0;
        this.zbb = savePasswordRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        zbae zbae0 = new zbae(this.zba, ((TaskCompletionSource)object1));
        ((zbm)((zbg)object0).getService()).zbd(zbae0, ((SavePasswordRequest)Preconditions.checkNotNull(this.zbb)));
    }
}

