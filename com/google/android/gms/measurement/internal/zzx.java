package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzek;
import com.google.android.gms.internal.measurement.zzem;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzoy;
import java.util.HashSet;
import java.util.Iterator;

final class zzx extends zzy {
    final zzaa zza;
    private final zzek zzh;

    zzx(zzaa zzaa0, String s, int v, zzek zzek0) {
        this.zza = zzaa0;
        super(s, v);
        this.zzh = zzek0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final int zza() {
        return this.zzh.zzb();
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final boolean zzb() {
        return this.zzh.zzo();
    }

    @Override  // com.google.android.gms.measurement.internal.zzy
    final boolean zzc() {
        return false;
    }

    final boolean zzd(Long long0, Long long1, zzft zzft0, long v, zzaq zzaq0, boolean z) {
        zzoy.zzc();
        boolean z1 = this.zza.zzt.zzf().zzs(this.zzb, zzeg.zzY);
        long v1 = this.zzh.zzn() ? zzaq0.zze : v;
        Integer integer0 = null;
        if(Log.isLoggable(this.zza.zzt.zzaA().zzr(), 2)) {
            this.zza.zzt.zzaA().zzj().zzd("Evaluating filter. audience, filter, event", this.zzc, (this.zzh.zzp() ? this.zzh.zzb() : null), this.zza.zzt.zzj().zzd(""));
            this.zza.zzt.zzaA().zzj().zzb("Filter definition", this.zza.zzf.zzu().zzp(this.zzh));
        }
        if(this.zzh.zzp() && this.zzh.zzb() <= 0x100) {
            boolean z2 = this.zzh.zzk() || this.zzh.zzm() || this.zzh.zzn();
            if(z && !z2) {
                zzer zzer0 = this.zza.zzt.zzaA().zzj();
                Integer integer1 = this.zzc;
                if(this.zzh.zzp()) {
                    integer0 = this.zzh.zzb();
                }
                zzer0.zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", integer1, integer0);
                return true;
            }
            zzek zzek0 = this.zzh;
            if(zzek0.zzo()) {
                Boolean boolean0 = zzx.zzh(v1, zzek0.zzf());
                if(boolean0 != null) {
                    if(!boolean0.booleanValue()) {
                        integer0 = Boolean.FALSE;
                        goto label_53;
                    }
                    goto label_23;
                }
            }
            else {
            label_23:
                HashSet hashSet0 = new HashSet();
                Iterator iterator0 = zzek0.zzh().iterator();
                if(iterator0.hasNext()) {
                    iterator0.next();
                    this.zza.zzt.zzaA().zzk().zzb("null or empty param name in filter. event", this.zza.zzt.zzj().zzd(""));
                }
                else {
                    ArrayMap arrayMap0 = new ArrayMap();
                    for(Object object0: zzft0.zzi()) {
                        zzfx zzfx0 = (zzfx)object0;
                        if(!hashSet0.contains("")) {
                            continue;
                        }
                        if(zzfx0.zzw()) {
                            arrayMap0.put("", (zzfx0.zzw() ? zzfx0.zzd() : null));
                            continue;
                        }
                        if(zzfx0.zzu()) {
                            arrayMap0.put("", (zzfx0.zzu() ? zzfx0.zza() : null));
                            continue;
                        }
                        if(zzfx0.zzy()) {
                            arrayMap0.put("", "");
                            continue;
                        }
                        this.zza.zzt.zzaA().zzk().zzc("Unknown value for param. event, param", this.zza.zzt.zzj().zzd(""), this.zza.zzt.zzj().zze(""));
                        goto label_53;
                    }
                    Iterator iterator2 = zzek0.zzh().iterator();
                    if(iterator2.hasNext()) {
                        Object object1 = iterator2.next();
                        boolean z3 = !((zzem)object1).zzh() || !((zzem)object1).zzg();
                        this.zza.zzt.zzaA().zzk().zzb("Event has empty param name. event", this.zza.zzt.zzj().zzd(""));
                    }
                    else {
                        integer0 = Boolean.TRUE;
                    }
                }
            }
        label_53:
            zzer zzer1 = this.zza.zzt.zzaA().zzj();
            String s = integer0 == null ? "null" : integer0;
            zzer1.zzb("Event filter result", s);
            if(integer0 == null) {
                return false;
            }
            this.zzd = Boolean.TRUE;
            if(!((Boolean)integer0).booleanValue()) {
                return true;
            }
            this.zze = Boolean.TRUE;
            if(z2 && zzft0.zzu()) {
                Long long2 = zzft0.zzd();
                if(this.zzh.zzm()) {
                    if(!z1 || !this.zzh.zzo()) {
                        long0 = long2;
                    }
                    this.zzg = long0;
                    return true;
                }
                if(z1 && this.zzh.zzo()) {
                    long2 = long1;
                }
                this.zzf = long2;
            }
            return true;
        }
        zzer zzer2 = this.zza.zzt.zzaA().zzk();
        Object object2 = zzet.zzn(this.zzb);
        if(this.zzh.zzp()) {
            integer0 = this.zzh.zzb();
        }
        zzer2.zzc("Invalid event filter ID. appId, id", object2, String.valueOf(integer0));
        return false;
    }
}

