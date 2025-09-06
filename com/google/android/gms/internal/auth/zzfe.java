package com.google.android.gms.internal.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzfe extends zzdr implements zzff, RandomAccess {
    @Deprecated
    public static final zzff zza;
    private static final zzfe zzb;
    private final List zzc;

    static {
        zzfe zzfe0 = new zzfe(false);
        zzfe.zzb = zzfe0;
        zzfe.zza = zzfe0;
    }

    public zzfe() {
        this(10);
    }

    public zzfe(int v) {
        ArrayList arrayList0 = new ArrayList(v);
        super(true);
        this.zzc = arrayList0;
    }

    private zzfe(ArrayList arrayList0) {
        super(true);
        this.zzc = arrayList0;
    }

    private zzfe(boolean z) {
        super(false);
        this.zzc = Collections.EMPTY_LIST;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final void add(int v, Object object0) {
        this.zza();
        this.zzc.add(v, ((String)object0));
        ++this.modCount;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final boolean addAll(int v, Collection collection0) {
        this.zza();
        if(collection0 instanceof zzff) {
            collection0 = ((zzff)collection0).zzg();
        }
        boolean z = this.zzc.addAll(v, collection0);
        ++this.modCount;
        return z;
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final boolean addAll(Collection collection0) {
        return this.addAll(this.size(), collection0);
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final void clear() {
        this.zza();
        this.zzc.clear();
        ++this.modCount;
    }

    @Override
    public final Object get(int v) {
        return this.zzf(v);
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final Object remove(int v) {
        this.zza();
        Object object0 = this.zzc.remove(v);
        ++this.modCount;
        return zzfe.zzh(object0);
    }

    @Override  // com.google.android.gms.internal.auth.zzdr
    public final Object set(int v, Object object0) {
        this.zza();
        return zzfe.zzh(this.zzc.set(v, ((String)object0)));
    }

    @Override
    public final int size() {
        return this.zzc.size();
    }

    @Override  // com.google.android.gms.internal.auth.zzez
    public final zzez zzd(int v) {
        if(v < this.size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList0 = new ArrayList(v);
        arrayList0.addAll(this.zzc);
        return new zzfe(arrayList0);
    }

    @Override  // com.google.android.gms.internal.auth.zzff
    public final zzff zze() {
        return this.zzc() ? new zzhe(this) : this;
    }

    public final String zzf(int v) {
        Object object0 = this.zzc.get(v);
        if(object0 instanceof String) {
            return (String)object0;
        }
        if(object0 instanceof zzef) {
            String s = ((zzef)object0).zzl(zzfa.zzb);
            if(((zzef)object0).zzh()) {
                this.zzc.set(v, s);
            }
            return s;
        }
        String s1 = zzfa.zzd(((byte[])object0));
        if(zzhn.zzb(((byte[])object0))) {
            this.zzc.set(v, s1);
        }
        return s1;
    }

    @Override  // com.google.android.gms.internal.auth.zzff
    public final List zzg() {
        return Collections.unmodifiableList(this.zzc);
    }

    private static String zzh(Object object0) {
        if(object0 instanceof String) {
            return (String)object0;
        }
        return object0 instanceof zzef ? ((zzef)object0).zzl(zzfa.zzb) : zzfa.zzd(((byte[])object0));
    }
}

