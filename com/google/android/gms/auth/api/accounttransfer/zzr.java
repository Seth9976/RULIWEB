package com.google.android.gms.auth.api.accounttransfer;

import android.os.Bundle;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.Collections;

public final class zzr implements Optional {
    public static final zzr zza;
    private final Bundle zzb;

    static {
        Bundle bundle0 = new Bundle();
        if(!bundle0.containsKey("accountTypes")) {
            bundle0.putStringArrayList("accountTypes", new ArrayList(0));
        }
        zzr.zza = new zzr(bundle0, null);
    }

    zzr(Bundle bundle0, zzq zzq0) {
        this.zzb = bundle0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzr)) {
            return false;
        }
        Bundle bundle0 = this.zza();
        Bundle bundle1 = ((zzr)object0).zza();
        if(bundle0.size() != bundle1.size()) {
            return false;
        }
        for(Object object1: bundle0.keySet()) {
            if(!bundle1.containsKey(((String)object1))) {
                return false;
            }
            if(!Objects.equal(bundle0.get(((String)object1)), bundle1.get(((String)object1)))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        Bundle bundle0 = this.zza();
        int v = bundle0.size();
        ArrayList arrayList0 = new ArrayList(v + v);
        ArrayList arrayList1 = new ArrayList(bundle0.keySet());
        Collections.sort(arrayList1);
        int v1 = arrayList1.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            String s = (String)arrayList1.get(v2);
            arrayList0.add(s);
            arrayList0.add(bundle0.get(s));
        }
        return Objects.hashCode(new Object[]{arrayList0});
    }

    public final Bundle zza() {
        return new Bundle(this.zzb);
    }
}

