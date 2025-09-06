package com.google.android.gms.internal.measurement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public final class zzah implements zzap {
    private final Double zza;

    public zzah(Double double0) {
        if(double0 == null) {
            this.zza = NaN;
            return;
        }
        this.zza = double0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof zzah ? this.zza.equals(((zzah)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override
    public final String toString() {
        return this.zzi();
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbU(String s, zzg zzg0, List list0) {
        if(!"toString".equals(s)) {
            throw new IllegalArgumentException(String.format("%s.%s is not a function.", this.zzi(), s));
        }
        return new zzat(this.zzi());
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzah(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.valueOf(!Double.isNaN(((double)this.zza)) && ((double)this.zza) != 0.0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        if(Double.isNaN(((double)this.zza))) {
            return "NaN";
        }
        if(Double.isInfinite(((double)this.zza))) {
            return ((double)this.zza) > 0.0 ? "Infinity" : "-Infinity";
        }
        BigDecimal bigDecimal0 = zzah..ExternalSyntheticBackportWithForwarding0.m(BigDecimal.valueOf(((double)this.zza)));
        DecimalFormat decimalFormat0 = new DecimalFormat("0E0");
        decimalFormat0.setRoundingMode(RoundingMode.HALF_UP);
        decimalFormat0.setMinimumFractionDigits((bigDecimal0.scale() <= 0 ? bigDecimal0.scale() : bigDecimal0.precision()) - 1);
        String s = decimalFormat0.format(bigDecimal0);
        int v = s.indexOf("E");
        if(v > 0) {
            int v1 = Integer.parseInt(s.substring(v + 1));
            return (v1 >= 0 || v1 <= -7) && (v1 < 0 || v1 >= 21) ? s.replace("E-", "e-").replace("E", "e+") : bigDecimal0.toPlainString();
        }
        return s;
    }

    @Override  // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return null;
    }
}

