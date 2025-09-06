package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.zal;
import java.util.ArrayList;
import java.util.Map;

final class zaao extends zaav {
    final zaaw zaa;
    private final Map zac;

    public zaao(zaaw zaaw0, Map map0) {
        this.zaa = zaaw0;
        super(zaaw0, null);
        this.zac = map0;
    }

    @Override  // com.google.android.gms.common.api.internal.zaav
    public final void zaa() {
        zal zal0 = new zal(zaaw.zaf(this.zaa));
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        for(Object object0: this.zac.keySet()) {
            Client api$Client0 = (Client)object0;
            if(api$Client0.requiresGooglePlayServices() && !((zaal)this.zac.get(api$Client0)).zac) {
                arrayList0.add(api$Client0);
            }
            else {
                arrayList1.add(api$Client0);
            }
        }
        int v = 0;
        int v1 = -1;
        if(arrayList0.isEmpty()) {
            int v2 = arrayList1.size();
            while(v < v2) {
                Client api$Client1 = (Client)arrayList1.get(v);
                v1 = zal0.zab(zaaw.zac(this.zaa), api$Client1);
                ++v;
                if(v1 == 0) {
                    break;
                }
            }
        }
        else {
            int v3 = arrayList0.size();
            while(v < v3) {
                Client api$Client2 = (Client)arrayList0.get(v);
                v1 = zal0.zab(zaaw.zac(this.zaa), api$Client2);
                ++v;
                if(v1 != 0) {
                    break;
                }
            }
        }
        if(v1 != 0) {
            ConnectionResult connectionResult0 = new ConnectionResult(v1, null);
            zaam zaam0 = new zaam(this, this.zaa, connectionResult0);
            zaaw.zak(this.zaa).zal(zaam0);
            return;
        }
        zaaw zaaw0 = this.zaa;
        if(zaaw.zav(zaaw0) && zaaw.zan(zaaw0) != null) {
            zaaw.zan(zaaw0).zab();
        }
        for(Object object1: this.zac.keySet()) {
            Client api$Client3 = (Client)object1;
            ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks0 = (ConnectionProgressReportCallbacks)this.zac.get(api$Client3);
            if(api$Client3.requiresGooglePlayServices() && zal0.zab(zaaw.zac(this.zaa), api$Client3) != 0) {
                zaan zaan0 = new zaan(this, this.zaa, baseGmsClient$ConnectionProgressReportCallbacks0);
                zaaw.zak(this.zaa).zal(zaan0);
            }
            else {
                api$Client3.connect(baseGmsClient$ConnectionProgressReportCallbacks0);
            }
        }
    }
}

