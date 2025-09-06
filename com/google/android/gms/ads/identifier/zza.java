package com.google.android.gms.ads.identifier;

import android.net.Uri.Builder;
import android.net.Uri;
import java.util.Map;

final class zza extends Thread {
    final Map zza;

    zza(AdvertisingIdClient advertisingIdClient0, Map map0) {
        this.zza = map0;
        super();
    }

    @Override
    public final void run() {
        Map map0 = this.zza;
        Uri.Builder uri$Builder0 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for(Object object0: map0.keySet()) {
            uri$Builder0.appendQueryParameter(((String)object0), ((String)map0.get(((String)object0))));
        }
        zzc.zza(uri$Builder0.build().toString());
    }
}

