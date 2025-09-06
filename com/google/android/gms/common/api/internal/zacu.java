package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zacu implements RemoteCall {
    public final BiConsumer zaa;

    public zacu(BiConsumer biConsumer0) {
        this.zaa = biConsumer0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        this.zaa.accept(((AnyClient)object0), ((TaskCompletionSource)object1));
    }
}

