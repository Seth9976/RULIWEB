package com.google.android.gms.internal.auth-api;

import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zbag implements RemoteCall {
    public final zbap zba;
    public final GetPhoneNumberHintIntentRequest zbb;

    public zbag(zbap zbap0, GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest0) {
        this.zba = zbap0;
        this.zbb = getPhoneNumberHintIntentRequest0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        this.zba.zba(this.zbb, ((zbaq)object0), ((TaskCompletionSource)object1));
    }
}

