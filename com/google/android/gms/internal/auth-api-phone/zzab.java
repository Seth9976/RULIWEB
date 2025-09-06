package com.google.android.gms.internal.auth-api-phone;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

public final class zzab extends SmsRetrieverClient {
    public zzab(Activity activity0) {
        super(activity0);
    }

    public zzab(Context context0) {
        super(context0);
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsRetrieverClient
    public final Task startSmsRetriever() {
        return this.doWrite(TaskApiCall.builder().run(new zzx(this)).setFeatures(new Feature[]{zzac.zzc}).setMethodKey(0x61F).build());
    }

    @Override  // com.google.android.gms.auth.api.phone.SmsRetrieverClient
    public final Task startSmsUserConsent(String s) {
        return this.doWrite(TaskApiCall.builder().run(new zzy(this, s)).setFeatures(new Feature[]{zzac.zzd}).setMethodKey(0x620).build());
    }
}

