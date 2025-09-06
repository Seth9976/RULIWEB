package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzlp extends zzjl implements zzlq, RandomAccess {
    @Deprecated
    public static final zzlq zza;
    private static final zzlp zzb;
    private final List zzc;

    static {
        zzlp zzlp0 = new zzlp(false);
        zzlp.zzb = zzlp0;
        zzlp.zza = zzlp0;
    }

    public zzlp() {
        this(10);
    }

    public zzlp(int v) {
        ArrayList arrayList0 = new ArrayList(v);
        super(true);
        this.zzc = arrayList0;
    }

    private zzlp(ArrayList arrayList0) {
        super(true);
        this.zzc = arrayList0;
    }

    private zzlp(boolean z) {
        super(false);
        this.zzc = Collections.EMPTY_LIST;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final void add(int v, Object object0) {
        this.zzbW();
        this.zzc.add(v, ((String)object0));
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final boolean addAll(int v, Collection collection0) {
        this.zzbW();
        if(collection0 instanceof zzlq) {
            collection0 = ((zzlq)collection0).zzh();
        }
        boolean z = this.zzc.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final void clear() {
        this.zzbW();
        this.zzc.clear();
        ++this.modCount;
    }

    @Override
    public final Object get(int v) {
        return this.zzg(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final Object remove(int v) {
        this.zzbW();
        Object object0 = this.zzc.remove(v);
        ++this.modCount;
        return zzlp.zzj(object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzjl
    public final Object set(int v, Object object0) {
        this.zzbW();
        return zzlp.zzj(this.zzc.set(v, ((String)object0)));
    }

    @Override
    public final int size() {
        return this.zzc.size();
    }

    @Override  // com.google.android.gms.internal.measurement.zzli
    public final zzli zzd(int v) {
        if(v < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList0 = new ArrayList(v);
        arrayList0.addAll(this.zzc);
        return new zzlp(arrayList0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final zzlq zze() {
        return this.zzc() ? new zznp(this) : this;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final Object zzf(int v) {
        return this.zzc.get(v);
    }

    public final String zzg(int v) {
        Object object0 = this.zzc.get(v);
        if(object0 instanceof String) {
            return (String)object0;
        }
        if(object0 instanceof zzka) {
            String s = ((zzka)object0).zzm(zzlj.zzb);
            if(((zzka)object0).zzi()) {
                this.zzc.set(v, s);
            }
            return s;
        }
        String s1 = zzlj.zzd(((byte[])object0));
        if(zznz.zzd(((byte[])object0))) {
            this.zzc.set(v, s1);
        }
        return s1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlq
    public final void zzi(zzka zzka0) {
        this.zzbW();
        this.zzc.add(zzka0);
        ++this.modCount;
    }

    private static String zzj(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof zzka ? ((zzka)object0).zzm(zzlj.zzb) : zzlj.zzd(((byte[])object0));
    }
}

