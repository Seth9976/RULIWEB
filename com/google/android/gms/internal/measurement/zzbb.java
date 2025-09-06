package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class zzbb {
    public static zzap zza(String s, zzae zzae0, zzg zzg0, List list0) {
        zzai zzai0;
        double f3;
        int v;
        switch(s) {
            case "concat": {
                v = 0;
                break;
            }
            case "every": {
                v = 1;
                break;
            }
            case "filter": {
                v = 2;
                break;
            }
            case "forEach": {
                v = 3;
                break;
            }
            case "indexOf": {
                v = 4;
                break;
            }
            case "join": {
                v = 5;
                break;
            }
            case "lastIndexOf": {
                v = 6;
                break;
            }
            case "map": {
                v = 7;
                break;
            }
            case "pop": {
                v = 8;
                break;
            }
            case "push": {
                v = 9;
                break;
            }
            case "reduce": {
                v = 10;
                break;
            }
            case "reduceRight": {
                v = 11;
                break;
            }
            case "reverse": {
                v = 12;
                break;
            }
            case "shift": {
                v = 13;
                break;
            }
            case "slice": {
                v = 14;
                break;
            }
            case "some": {
                v = 15;
                break;
            }
            case "sort": {
                v = 16;
                break;
            }
            case "splice": {
                v = 17;
                break;
            }
            case "toString": {
                v = 18;
                break;
            }
            case "unshift": {
                v = 19;
                break;
            }
            default: {
                v = -1;
            }
        }
        double f = 0.0;
        switch(v) {
            case 0: {
                zzap zzap0 = zzae0.zzd();
                if(!list0.isEmpty()) {
                    for(Object object0: list0) {
                        zzap zzap1 = zzg0.zzb(((zzap)object0));
                        if(zzap1 instanceof zzag) {
                            throw new IllegalStateException("Failed evaluation of arguments");
                        }
                        int v1 = ((zzae)zzap0).zzc();
                        if(zzap1 instanceof zzae) {
                            zzae zzae1 = (zzae)zzap1;
                            Iterator iterator1 = zzae1.zzk();
                            while(iterator1.hasNext()) {
                                Object object1 = iterator1.next();
                                ((zzae)zzap0).zzq(((int)(((Integer)object1))) + v1, zzae1.zze(((int)(((Integer)object1)))));
                            }
                        }
                        else {
                            ((zzae)zzap0).zzq(v1, zzap1);
                        }
                    }
                }
                return zzap0;
            }
            case 1: {
                zzh.zzh("every", 1, list0);
                zzap zzap2 = zzg0.zzb(((zzap)list0.get(0)));
                if(!(zzap2 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if(zzae0.zzc() == 0) {
                    return zzap.zzk;
                }
                return zzbb.zzb(zzae0, zzg0, ((zzao)zzap2), Boolean.FALSE, Boolean.TRUE).zzc() == zzae0.zzc() ? zzap.zzk : zzap.zzl;
            }
            case 2: {
                zzh.zzh("filter", 1, list0);
                zzap zzap3 = zzg0.zzb(((zzap)list0.get(0)));
                if(!(zzap3 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if(zzae0.zzb() == 0) {
                    return new zzae();
                }
                zzap zzap4 = zzae0.zzd();
                zzae zzae2 = zzbb.zzb(zzae0, zzg0, ((zzao)zzap3), null, Boolean.TRUE);
                zzap zzap5 = new zzae();
                Iterator iterator2 = zzae2.zzk();
                while(iterator2.hasNext()) {
                    Object object2 = iterator2.next();
                    zzap zzap6 = ((zzae)zzap4).zze(((int)(((Integer)object2))));
                    ((zzae)zzap5).zzq(((zzae)zzap5).zzc(), zzap6);
                }
                return zzap5;
            }
            case 3: {
                zzh.zzh("forEach", 1, list0);
                zzap zzap7 = zzg0.zzb(((zzap)list0.get(0)));
                if(!(zzap7 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if(zzae0.zzb() == 0) {
                    return zzap.zzf;
                }
                zzbb.zzb(zzae0, zzg0, ((zzao)zzap7), null, null);
                return zzap.zzf;
            }
            case 4: {
                zzh.zzj("indexOf", 2, list0);
                zzap zzap8 = list0.isEmpty() ? zzap.zzf : zzg0.zzb(((zzap)list0.get(0)));
                if(list0.size() > 1) {
                    double f1 = zzh.zza(((double)zzg0.zzb(((zzap)list0.get(1))).zzh()));
                    if(f1 >= ((double)zzae0.zzc())) {
                        return new zzah(-1.0);
                    }
                    f = f1 < 0.0 ? ((double)zzae0.zzc()) + f1 : f1;
                }
                Iterator iterator3 = zzae0.zzk();
                while(iterator3.hasNext()) {
                    Object object3 = iterator3.next();
                    int v2 = (int)(((Integer)object3));
                    double f2 = (double)v2;
                    if(f2 >= f && zzh.zzl(zzae0.zze(v2), zzap8)) {
                        return new zzah(f2);
                    }
                    if(false) {
                        break;
                    }
                }
                return new zzah(-1.0);
            }
            case 5: {
                zzh.zzj("join", 1, list0);
                if(zzae0.zzc() == 0) {
                    return zzap.zzm;
                }
                if(!list0.isEmpty()) {
                    zzap zzap9 = zzg0.zzb(((zzap)list0.get(0)));
                    return zzap9 instanceof zzan || zzap9 instanceof zzau ? new zzat("") : new zzat(zzae0.zzj(zzap9.zzi()));
                }
                return new zzat("");
            }
            case 6: {
                zzh.zzj("lastIndexOf", 2, list0);
                zzap zzap10 = list0.isEmpty() ? zzap.zzf : zzg0.zzb(((zzap)list0.get(0)));
                int v3 = zzae0.zzc();
                if(list0.size() > 1) {
                    zzap zzap11 = zzg0.zzb(((zzap)list0.get(1)));
                    f3 = Double.isNaN(((double)zzap11.zzh())) ? ((double)(zzae0.zzc() - 1)) : zzh.zza(((double)zzap11.zzh()));
                    if(f3 < 0.0) {
                        f3 += (double)zzae0.zzc();
                    }
                }
                else {
                    f3 = (double)(v3 - 1);
                }
                if(f3 < 0.0) {
                    return new zzah(-1.0);
                }
                for(int v4 = (int)Math.min(zzae0.zzc(), f3); v4 >= 0; --v4) {
                    if(zzae0.zzs(v4) && zzh.zzl(zzae0.zze(v4), zzap10)) {
                        return new zzah(((double)v4));
                    }
                }
                return new zzah(-1.0);
            }
            case 7: {
                zzh.zzh("map", 1, list0);
                zzap zzap12 = zzg0.zzb(((zzap)list0.get(0)));
                if(!(zzap12 instanceof zzao)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                return zzae0.zzc() == 0 ? new zzae() : zzbb.zzb(zzae0, zzg0, ((zzao)zzap12), null, null);
            }
            case 8: {
                zzh.zzh("pop", 0, list0);
                int v5 = zzae0.zzc();
                if(v5 == 0) {
                    return zzap.zzf;
                }
                zzap zzap13 = zzae0.zze(v5 - 1);
                zzae0.zzp(v5 - 1);
                return zzap13;
            }
            case 9: {
                if(!list0.isEmpty()) {
                    for(Object object4: list0) {
                        zzap zzap14 = zzg0.zzb(((zzap)object4));
                        zzae0.zzq(zzae0.zzc(), zzap14);
                    }
                }
                return new zzah(((double)zzae0.zzc()));
            }
            case 10: {
                return zzbb.zzc(zzae0, zzg0, list0, true);
            }
            case 11: {
                return zzbb.zzc(zzae0, zzg0, list0, false);
            }
            case 12: {
                zzh.zzh("reverse", 0, list0);
                int v14 = zzae0.zzc();
                if(v14 != 0) {
                    for(int v15 = 0; v15 < v14 / 2; ++v15) {
                        if(zzae0.zzs(v15)) {
                            zzap zzap24 = zzae0.zze(v15);
                            zzae0.zzq(v15, null);
                            int v16 = v14 - 1 - v15;
                            if(zzae0.zzs(v16)) {
                                zzae0.zzq(v15, zzae0.zze(v16));
                            }
                            zzae0.zzq(v16, zzap24);
                        }
                    }
                    return zzae0;
                }
                break;
            }
            case 13: {
                zzh.zzh("shift", 0, list0);
                if(zzae0.zzc() == 0) {
                    return zzap.zzf;
                }
                zzap zzap15 = zzae0.zze(0);
                zzae0.zzp(0);
                return zzap15;
            }
            case 14: {
                zzh.zzj("slice", 2, list0);
                if(list0.isEmpty()) {
                    return zzae0.zzd();
                }
                double f4 = (double)zzae0.zzc();
                double f5 = zzh.zza(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()));
                double f6 = f5 < 0.0 ? Math.max(f5 + f4, 0.0) : Math.min(f5, f4);
                if(list0.size() == 2) {
                    double f7 = zzh.zza(((double)zzg0.zzb(((zzap)list0.get(1))).zzh()));
                    f4 = f7 < 0.0 ? Math.max(f4 + f7, 0.0) : Math.min(f4, f7);
                }
                zzap zzap16 = new zzae();
                for(int v6 = (int)f6; ((double)v6) < f4; ++v6) {
                    zzap zzap17 = zzae0.zze(v6);
                    ((zzae)zzap16).zzq(((zzae)zzap16).zzc(), zzap17);
                }
                return zzap16;
            }
            case 15: {
                zzh.zzh("some", 1, list0);
                zzap zzap18 = zzg0.zzb(((zzap)list0.get(0)));
                if(!(zzap18 instanceof zzai)) {
                    throw new IllegalArgumentException("Callback should be a method");
                }
                if(zzae0.zzc() == 0) {
                    return zzap.zzl;
                }
                Iterator iterator5 = zzae0.zzk();
                while(iterator5.hasNext()) {
                    Object object5 = iterator5.next();
                    int v7 = (int)(((Integer)object5));
                    if(zzae0.zzs(v7) && ((zzai)zzap18).zza(zzg0, Arrays.asList(new zzap[]{zzae0.zze(v7), new zzah(((double)v7)), zzae0})).zzg().booleanValue()) {
                        return zzap.zzk;
                    }
                    if(false) {
                        break;
                    }
                }
                return zzap.zzl;
            }
            case 16: {
                zzh.zzj("sort", 1, list0);
                if(zzae0.zzc() >= 2) {
                    List list1 = zzae0.zzm();
                    if(list0.isEmpty()) {
                        zzai0 = null;
                    }
                    else {
                        zzap zzap25 = zzg0.zzb(((zzap)list0.get(0)));
                        if(!(zzap25 instanceof zzai)) {
                            throw new IllegalArgumentException("Comparator should be a method");
                        }
                        zzai0 = (zzai)zzap25;
                    }
                    Collections.sort(list1, new zzba(zzai0, zzg0));
                    zzae0.zzn();
                    Iterator iterator9 = list1.iterator();
                    for(int v17 = 0; true; ++v17) {
                        if(!iterator9.hasNext()) {
                            break;
                        }
                        Object object9 = iterator9.next();
                        zzae0.zzq(v17, ((zzap)object9));
                    }
                }
                break;
            }
            case 17: {
                if(list0.isEmpty()) {
                    return new zzae();
                }
                int v8 = (int)zzh.zza(((double)zzg0.zzb(((zzap)list0.get(0))).zzh()));
                if(v8 < 0) {
                    v8 = Math.max(0, v8 + zzae0.zzc());
                }
                else if(v8 > zzae0.zzc()) {
                    v8 = zzae0.zzc();
                }
                int v9 = zzae0.zzc();
                zzap zzap19 = new zzae();
                if(list0.size() > 1) {
                    int v10 = Math.max(0, ((int)zzh.zza(((double)zzg0.zzb(((zzap)list0.get(1))).zzh()))));
                    if(v10 > 0) {
                        for(int v11 = v8; v11 < Math.min(v9, v8 + v10); ++v11) {
                            zzap zzap20 = zzae0.zze(v8);
                            ((zzae)zzap19).zzq(((zzae)zzap19).zzc(), zzap20);
                            zzae0.zzp(v8);
                        }
                    }
                    if(list0.size() > 2) {
                        for(int v12 = 2; v12 < list0.size(); ++v12) {
                            zzap zzap21 = zzg0.zzb(((zzap)list0.get(v12)));
                            if(zzap21 instanceof zzag) {
                                throw new IllegalArgumentException("Failed to parse elements to add");
                            }
                            zzae0.zzo(v8 + v12 - 2, zzap21);
                        }
                    }
                }
                else {
                    while(v8 < v9) {
                        zzap zzap22 = zzae0.zze(v8);
                        ((zzae)zzap19).zzq(((zzae)zzap19).zzc(), zzap22);
                        zzae0.zzq(v8, null);
                        ++v8;
                    }
                }
                return zzap19;
            }
            case 18: {
                zzh.zzh("toString", 0, list0);
                return new zzat("");
            }
            case 19: {
                if(!list0.isEmpty()) {
                    zzae zzae3 = new zzae();
                    for(Object object6: list0) {
                        zzap zzap23 = zzg0.zzb(((zzap)object6));
                        if(zzap23 instanceof zzag) {
                            throw new IllegalStateException("Argument evaluation failed");
                        }
                        zzae3.zzq(zzae3.zzc(), zzap23);
                    }
                    int v13 = zzae3.zzc();
                    Iterator iterator7 = zzae0.zzk();
                    while(iterator7.hasNext()) {
                        Object object7 = iterator7.next();
                        zzae3.zzq(((int)(((Integer)object7))) + v13, zzae0.zze(((int)(((Integer)object7)))));
                    }
                    zzae0.zzn();
                    Iterator iterator8 = zzae3.zzk();
                    while(iterator8.hasNext()) {
                        Object object8 = iterator8.next();
                        zzae0.zzq(((int)(((Integer)object8))), zzae3.zze(((int)(((Integer)object8)))));
                    }
                }
                return new zzah(((double)zzae0.zzc()));
            }
            default: {
                throw new IllegalArgumentException("Command not supported");
            }
        }
        return zzae0;
    }

    private static zzae zzb(zzae zzae0, zzg zzg0, zzai zzai0, Boolean boolean0, Boolean boolean1) {
        zzae zzae1 = new zzae();
        Iterator iterator0 = zzae0.zzk();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            int v = (int)(((Integer)object0));
            if(zzae0.zzs(v)) {
                zzap zzap0 = zzai0.zza(zzg0, Arrays.asList(new zzap[]{zzae0.zze(v), new zzah(((double)v)), zzae0}));
                if(zzap0.zzg().equals(boolean0)) {
                    break;
                }
                if(boolean1 == null || zzap0.zzg().equals(boolean1)) {
                    zzae1.zzq(v, zzap0);
                }
            }
        }
        return zzae1;
    }

    private static zzap zzc(zzae zzae0, zzg zzg0, List list0, boolean z) {
        zzap zzap1;
        zzh.zzi("reduce", 1, list0);
        zzh.zzj("reduce", 2, list0);
        zzap zzap0 = zzg0.zzb(((zzap)list0.get(0)));
        if(!(zzap0 instanceof zzai)) {
            throw new IllegalArgumentException("Callback should be a method");
        }
        if(list0.size() == 2) {
            zzap1 = zzg0.zzb(((zzap)list0.get(1)));
            if(zzap1 instanceof zzag) {
                throw new IllegalArgumentException("Failed to parse initial value");
            }
        }
        else {
            if(zzae0.zzc() == 0) {
                throw new IllegalStateException("Empty array with no initial value error");
            }
            zzap1 = null;
        }
        int v = zzae0.zzc();
        int v1 = z ? 0 : v - 1;
        if(zzap1 == null) {
            zzap1 = zzae0.zze(v1);
            v1 += (z ? 1 : -1);
        }
        while(((z ? v - 1 : 0) - v1) * (z ? 1 : -1) >= 0) {
            if(zzae0.zzs(v1)) {
                zzap1 = ((zzai)zzap0).zza(zzg0, Arrays.asList(new zzap[]{zzap1, zzae0.zze(v1), new zzah(((double)v1)), zzae0}));
                if(zzap1 instanceof zzag) {
                    throw new IllegalStateException("Reduce operation failed");
                }
            }
            v1 += (z ? 1 : -1);
        }
        return zzap1;
    }
}

