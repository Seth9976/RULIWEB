package com.google.android.gms.internal.measurement;

public enum zzlm {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, 0.0f),
    DOUBLE(Double.TYPE, Double.class, 0.0),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzka.class, zzka.class, zzka.zzb),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);

    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    private zzlm(Class class0, Class class1, Object object0) {
        this.zzl = class0;
        this.zzm = class1;
        this.zzn = object0;
    }

    public final Class zza() {
        return this.zzm;
    }
}

