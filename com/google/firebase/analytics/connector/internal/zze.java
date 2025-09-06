package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhc;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class zze implements zza {
    final Set zza;
    private final AnalyticsConnectorListener zzb;
    private final AppMeasurementSdk zzc;
    private final zzd zzd;

    public zze(AppMeasurementSdk appMeasurementSdk0, AnalyticsConnectorListener analyticsConnector$AnalyticsConnectorListener0) {
        this.zzb = analyticsConnector$AnalyticsConnectorListener0;
        this.zzc = appMeasurementSdk0;
        zzd zzd0 = new zzd(this);
        this.zzd = zzd0;
        appMeasurementSdk0.registerOnMeasurementEventListener(zzd0);
        this.zza = new HashSet();
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set set0) {
        this.zza.clear();
        Set set1 = this.zza;
        HashSet hashSet0 = new HashSet();
        Iterator iterator0 = set0.iterator();
    label_4:
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            String s = (String)object0;
            if(hashSet0.size() >= 50) {
                break;
            }
            if(s != null && s.length() != 0) {
                int v = s.codePointAt(0);
                if(!Character.isLetter(v)) {
                    if(v != 0x5F) {
                        continue;
                    }
                    v = 0x5F;
                }
                int v1 = s.length();
                int v2 = Character.charCount(v);
                while(v2 < v1) {
                    int v3 = s.codePointAt(v2);
                    if(v3 != 0x5F && !Character.isLetterOrDigit(v3)) {
                        continue label_4;
                    }
                    v2 += Character.charCount(v3);
                }
                if(s.length() != 0) {
                    int v4 = s.codePointAt(0);
                    if(Character.isLetter(v4)) {
                        int v5 = s.length();
                        int v6 = Character.charCount(v4);
                        while(v6 < v5) {
                            int v7 = s.codePointAt(v6);
                            if(v7 != 0x5F && !Character.isLetterOrDigit(v7)) {
                                continue label_4;
                            }
                            v6 += Character.charCount(v7);
                        }
                        String s1 = zzhc.zzb(s);
                        if(s1 != null) {
                            s = s1;
                        }
                        Preconditions.checkNotNull(s);
                        hashSet0.add(s);
                    }
                }
            }
        }
        set1.addAll(hashSet0);
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
        this.zza.clear();
    }
}

