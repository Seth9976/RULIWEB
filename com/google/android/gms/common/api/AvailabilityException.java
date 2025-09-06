package com.google.android.gms.common.api;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

public class AvailabilityException extends Exception {
    private final ArrayMap zaa;

    public AvailabilityException(ArrayMap arrayMap0) {
        this.zaa = arrayMap0;
    }

    public ConnectionResult getConnectionResult(GoogleApi googleApi0) {
        ApiKey apiKey0 = googleApi0.getApiKey();
        Preconditions.checkArgument(this.zaa.get(apiKey0) != null, "The given API (" + apiKey0.zaa() + ") was not part of the availability request.");
        return (ConnectionResult)Preconditions.checkNotNull(((ConnectionResult)this.zaa.get(apiKey0)));
    }

    public ConnectionResult getConnectionResult(HasApiKey hasApiKey0) {
        ApiKey apiKey0 = hasApiKey0.getApiKey();
        Preconditions.checkArgument(this.zaa.get(apiKey0) != null, "The given API (" + apiKey0.zaa() + ") was not part of the availability request.");
        return (ConnectionResult)Preconditions.checkNotNull(((ConnectionResult)this.zaa.get(apiKey0)));
    }

    @Override
    public String getMessage() {
        ArrayList arrayList0 = new ArrayList();
        int v = 1;
        for(Object object0: this.zaa.keySet()) {
            ConnectionResult connectionResult0 = (ConnectionResult)Preconditions.checkNotNull(((ConnectionResult)this.zaa.get(((ApiKey)object0))));
            v &= !connectionResult0.isSuccess();
            arrayList0.add(((ApiKey)object0).zaa() + ": " + connectionResult0);
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        if(v == 0) {
            stringBuilder0.append("Some of the queried APIs are unavailable. ");
        }
        else {
            stringBuilder0.append("None of the queried APIs are available. ");
        }
        stringBuilder0.append(TextUtils.join("; ", arrayList0));
        return stringBuilder0.toString();
    }
}

