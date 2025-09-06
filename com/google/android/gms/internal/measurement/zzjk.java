package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzjk implements zzmi {
    protected int zzb;

    public zzjk() {
        this.zzb = 0;
    }

    int zzbu(zzmt zzmt0) {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmi
    public final zzka zzbv() {
        try {
            int v = this.zzbz();
            byte[] arr_b = new byte[v];
            zzki zzki0 = zzki.zzz(arr_b, 0, v);
            this.zzbQ(zzki0);
            zzki0.zzA();
            return new zzjx(arr_b);
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a ByteString threw an IOException (should never happen).", iOException0);
        }
    }

    protected static void zzbw(Iterable iterable0, List list0) {
        iterable0.getClass();
        if(iterable0 instanceof zzlq) {
            List list1 = ((zzlq)iterable0).zzh();
            int v = list0.size();
            for(Object object0: list1) {
                if(object0 == null) {
                    String s = "Element at index " + (((zzlq)list0).size() - v) + " is null.";
                    int v1 = ((zzlq)list0).size();
                    while(true) {
                        --v1;
                        if(v1 < v) {
                            break;
                        }
                        ((zzlq)list0).remove(v1);
                    }
                    throw new NullPointerException(s);
                }
                if(object0 instanceof zzka) {
                    ((zzlq)list0).zzi(((zzka)object0));
                }
                else {
                    ((zzlq)list0).add(((String)object0));
                }
            }
            return;
        }
        if(!(iterable0 instanceof zzmp)) {
            if(list0 instanceof ArrayList && iterable0 instanceof Collection) {
                ((ArrayList)list0).ensureCapacity(list0.size() + ((Collection)iterable0).size());
            }
            int v2 = list0.size();
            for(Object object1: iterable0) {
                if(object1 == null) {
                    String s1 = "Element at index " + (list0.size() - v2) + " is null.";
                    int v3 = list0.size();
                    while(true) {
                        --v3;
                        if(v3 < v2) {
                            break;
                        }
                        list0.remove(v3);
                    }
                    throw new NullPointerException(s1);
                }
                list0.add(object1);
            }
            return;
        }
        list0.addAll(((Collection)iterable0));
    }

    public final byte[] zzbx() {
        try {
            int v = this.zzbz();
            byte[] arr_b = new byte[v];
            zzki zzki0 = zzki.zzz(arr_b, 0, v);
            this.zzbQ(zzki0);
            zzki0.zzA();
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing " + this.getClass().getName() + " to a byte array threw an IOException (should never happen).", iOException0);
        }
    }
}

