package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

public interface zzap {
    public static final zzap zzf;
    public static final zzap zzg;
    public static final zzap zzh;
    public static final zzap zzi;
    public static final zzap zzj;
    public static final zzap zzk;
    public static final zzap zzl;
    public static final zzap zzm;

    static {
        zzap.zzf = new zzau();
        zzap.zzg = new zzan();
        zzap.zzh = new zzag("continue");
        zzap.zzi = new zzag("break");
        zzap.zzj = new zzag("return");
        zzap.zzk = new zzaf(Boolean.TRUE);
        zzap.zzl = new zzaf(Boolean.FALSE);
        zzap.zzm = new zzat("");
    }

    zzap zzbU(String arg1, zzg arg2, List arg3);

    zzap zzd();

    Boolean zzg();

    Double zzh();

    String zzi();

    Iterator zzl();
}

