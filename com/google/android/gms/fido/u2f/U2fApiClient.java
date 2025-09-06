package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.fido.zzx;
import com.google.android.gms.tasks.Task;

@Deprecated
public class U2fApiClient extends GoogleApi {
    private static final ClientKey zza;
    private static final Api zzb;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        U2fApiClient.zza = api$ClientKey0;
        U2fApiClient.zzb = new Api("Fido.U2F_API", new zzx(), api$ClientKey0);
    }

    public U2fApiClient(Activity activity0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(activity0, U2fApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    public U2fApiClient(Context context0) {
        ApiExceptionMapper apiExceptionMapper0 = new ApiExceptionMapper();
        super(context0, U2fApiClient.zzb, ApiOptions.NO_OPTIONS, apiExceptionMapper0);
    }

    public Task getRegisterIntent(RegisterRequestParams registerRequestParams0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(0x1530).run(new zzb(this, registerRequestParams0)).build());
    }

    public Task getSignIntent(SignRequestParams signRequestParams0) {
        return this.doRead(TaskApiCall.builder().setMethodKey(5425).run(new zza(this, signRequestParams0)).build());
    }
}

