package com.google.android.gms.common.api.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

public final class zal {
    private final ArrayMap zaa;
    private final ArrayMap zab;
    private final TaskCompletionSource zac;
    private int zad;
    private boolean zae;

    public zal(Iterable iterable0) {
        this.zab = new ArrayMap();
        this.zac = new TaskCompletionSource();
        this.zae = false;
        this.zaa = new ArrayMap();
        for(Object object0: iterable0) {
            ApiKey apiKey0 = ((HasApiKey)object0).getApiKey();
            this.zaa.put(apiKey0, null);
        }
        this.zad = this.zaa.keySet().size();
    }

    public final Task zaa() {
        return this.zac.getTask();
    }

    public final Set zab() {
        return this.zaa.keySet();
    }

    public final void zac(ApiKey apiKey0, ConnectionResult connectionResult0, String s) {
        this.zaa.put(apiKey0, connectionResult0);
        this.zab.put(apiKey0, s);
        --this.zad;
        if(!connectionResult0.isSuccess()) {
            this.zae = true;
        }
        if(this.zad == 0) {
            if(this.zae) {
                AvailabilityException availabilityException0 = new AvailabilityException(this.zaa);
                this.zac.setException(availabilityException0);
                return;
            }
            this.zac.setResult(this.zab);
        }
    }
}

