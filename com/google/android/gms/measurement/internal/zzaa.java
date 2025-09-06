package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzej;
import com.google.android.gms.internal.measurement.zzek;
import com.google.android.gms.internal.measurement.zzes;
import com.google.android.gms.internal.measurement.zzet;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzoy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jeb.synthetic.TWR;

final class zzaa extends zzku {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzaa(zzlh zzlh0) {
        super(zzlh0);
    }

    final List zza(String s, List list0, List list1, Long long0, Long long1) {
        Map map8;
        List list6;
        Integer integer5;
        zzer zzer0;
        zzet zzet0;
        Cursor cursor5;
        String s6;
        Iterator iterator10;
        Cursor cursor4;
        Map map6;
        zzek zzek1;
        long v5;
        Cursor cursor3;
        zzaq zzaq2;
        zzw zzw1;
        zzaq zzaq1;
        Iterator iterator6;
        boolean z2;
        Map map4;
        Map map3;
        zzgi zzgi0;
        ArrayMap arrayMap1;
        Cursor cursor1;
        Map map1;
        List list3;
        zzek zzek0;
        Cursor cursor0;
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(list0);
        Preconditions.checkNotNull(list1);
        this.zza = s;
        this.zzb = new HashSet();
        this.zzc = new ArrayMap();
        this.zzd = long0;
        this.zze = long1;
        Iterator iterator0 = list0.iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
        zzoy.zzc();
        boolean z = this.zzt.zzf().zzs(this.zza, zzeg.zzY);
        zzoy.zzc();
        boolean z1 = this.zzt.zzf().zzs(this.zza, zzeg.zzX);
        Map map0 = Collections.EMPTY_MAP;
        if(!z1 || !z) {
        label_56:
            map1 = map0;
        }
        else {
            zzak zzak0 = this.zzf.zzh();
            String s1 = this.zza;
            Preconditions.checkNotEmpty(s1);
            ArrayMap arrayMap0 = new ArrayMap();
            SQLiteDatabase sQLiteDatabase0 = zzak0.zzh();
            try {
                cursor0 = null;
                cursor0 = sQLiteDatabase0.query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{s1}, null, null, null);
                if(cursor0.moveToFirst()) {
                    while(true) {
                        byte[] arr_b = cursor0.getBlob(1);
                        try {
                            zzek0 = (zzek)((zzej)zzlj.zzm(zzek.zzc(), arr_b)).zzaD();
                        }
                        catch(IOException iOException0) {
                            zzak0.zzt.zzaA().zzd().zzc("Failed to merge filter. appId", com.google.android.gms.measurement.internal.zzet.zzn(s1), iOException0);
                            goto label_41;
                        }
                        if(zzek0.zzo()) {
                            Integer integer0 = cursor0.getInt(0);
                            List list2 = (List)arrayMap0.get(integer0);
                            if(list2 == null) {
                                list3 = new ArrayList();
                                arrayMap0.put(integer0, list3);
                            }
                            else {
                                list3 = list2;
                            }
                            list3.add(zzek0);
                        }
                    label_41:
                        if(cursor0.moveToNext()) {
                            continue;
                        }
                        goto label_42;
                    }
                }
                else {
                    goto label_45;
                }
                goto label_57;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_48;
            }
            catch(Throwable throwable0) {
                goto label_53;
            }
        label_42:
            cursor0.close();
            map1 = arrayMap0;
            goto label_57;
            try {
                try {
                label_45:
                    map0 = Collections.EMPTY_MAP;
                }
                catch(SQLiteException sQLiteException0) {
                label_48:
                    zzak0.zzt.zzaA().zzd().zzc("Database error querying filters. appId", com.google.android.gms.measurement.internal.zzet.zzn(s1), sQLiteException0);
                    map0 = Collections.EMPTY_MAP;
                    if(cursor0 != null) {
                        goto label_55;
                    }
                    goto label_56;
                }
            }
            catch(Throwable throwable0) {
            label_53:
                TWR.safeClose$NT(cursor0, throwable0);
                throw throwable0;
            }
        label_55:
            cursor0.close();
            goto label_56;
        }
    label_57:
        zzak zzak1 = this.zzf.zzh();
        String s2 = this.zza;
        zzak1.zzW();
        zzak1.zzg();
        Preconditions.checkNotEmpty(s2);
        SQLiteDatabase sQLiteDatabase1 = zzak1.zzh();
        try {
            cursor1 = null;
            cursor1 = sQLiteDatabase1.query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{s2}, null, null, null);
            goto label_69;
        }
        catch(SQLiteException sQLiteException1) {
            goto label_86;
        }
        catch(Throwable throwable1) {
        }
        Cursor cursor2 = null;
        goto label_94;
        try {
        label_69:
            if(cursor1.moveToFirst()) {
                arrayMap1 = new ArrayMap();
                while(true) {
                    int v = cursor1.getInt(0);
                    byte[] arr_b1 = cursor1.getBlob(1);
                    try {
                        zzgi0 = (zzgi)((zzgh)zzlj.zzm(zzgi.zze(), arr_b1)).zzaD();
                    }
                    catch(IOException iOException1) {
                        zzak1.zzt.zzaA().zzd().zzd("Failed to merge filter results. appId, audienceId, error", com.google.android.gms.measurement.internal.zzet.zzn(s2), v, iOException1);
                        goto label_79;
                    }
                    arrayMap1.put(v, zzgi0);
                label_79:
                    if(cursor1.moveToNext()) {
                        continue;
                    }
                    goto label_80;
                }
            }
            else {
                goto label_83;
            }
            goto label_99;
        }
        catch(SQLiteException sQLiteException1) {
            goto label_86;
        }
        catch(Throwable throwable1) {
            goto label_93;
        }
    label_80:
        cursor1.close();
        Map map2 = arrayMap1;
        goto label_99;
        try {
            try {
            label_83:
                map3 = Collections.EMPTY_MAP;
                goto label_97;
            }
            catch(SQLiteException sQLiteException1) {
            }
        label_86:
            zzak1.zzt.zzaA().zzd().zzc("Database error querying filter results. appId", com.google.android.gms.measurement.internal.zzet.zzn(s2), sQLiteException1);
            map4 = Collections.EMPTY_MAP;
            if(cursor1 != null) {
                goto label_89;
            }
            goto label_90;
        }
        catch(Throwable throwable1) {
            goto label_93;
        }
    label_89:
        cursor1.close();
    label_90:
        map2 = map4;
        goto label_99;
    label_93:
        cursor2 = cursor1;
    label_94:
        if(cursor2 != null) {
            cursor2.close();
        }
        throw throwable1;
    label_97:
        cursor1.close();
        map2 = map3;
    label_99:
        if(!map2.isEmpty()) {
            for(Object object0: new HashSet(map2.keySet())) {
                Integer integer1 = (Integer)object0;
                integer1.intValue();
                zzgi zzgi1 = (zzgi)map2.get(integer1);
                BitSet bitSet0 = new BitSet();
                BitSet bitSet1 = new BitSet();
                ArrayMap arrayMap2 = new ArrayMap();
                if(zzgi1 != null && zzgi1.zza() != 0) {
                    for(Object object1: zzgi1.zzh()) {
                        zzfr zzfr0 = (zzfr)object1;
                        if(zzfr0.zzh()) {
                            arrayMap2.put(zzfr0.zza(), (zzfr0.zzg() ? zzfr0.zzb() : null));
                        }
                    }
                }
                ArrayMap arrayMap3 = new ArrayMap();
                if(zzgi1 != null && zzgi1.zzc() != 0) {
                    for(Object object2: zzgi1.zzj()) {
                        zzgk zzgk0 = (zzgk)object2;
                        if(zzgk0.zzi() && zzgk0.zza() > 0) {
                            arrayMap3.put(zzgk0.zzb(), zzgk0.zzc(zzgk0.zza() - 1));
                        }
                    }
                }
                zzgi zzgi2 = zzgi1;
                if(zzgi2 != null) {
                    int v1 = 0;
                    while(v1 < zzgi2.zzd() * 0x40) {
                        if(zzlj.zzw(zzgi2.zzk(), v1)) {
                            z2 = z;
                            this.zzt.zzaA().zzj().zzc("Filter already evaluated. audience ID, filter ID", integer1, v1);
                            bitSet1.set(v1);
                            if(zzlj.zzw(zzgi2.zzi(), v1)) {
                                bitSet0.set(v1);
                                goto label_139;
                            }
                        }
                        else {
                            z2 = z;
                        }
                        arrayMap2.remove(v1);
                    label_139:
                        ++v1;
                        z = z2;
                    }
                }
                Object object3 = map2.get(integer1);
                if(z1 && z) {
                    List list4 = (List)map1.get(integer1);
                    if(list4 != null && this.zze != null && this.zzd != null) {
                        for(Object object4: list4) {
                            int v2 = ((zzek)object4).zzb();
                            long v3 = ((long)this.zze) / 1000L;
                            if(((zzek)object4).zzm()) {
                                v3 = ((long)this.zzd) / 1000L;
                            }
                            Integer integer2 = v2;
                            if(arrayMap2.containsKey(integer2)) {
                                arrayMap2.put(integer2, v3);
                            }
                            if(arrayMap3.containsKey(integer2)) {
                                arrayMap3.put(integer2, v3);
                            }
                        }
                    }
                }
                zzu zzu0 = new zzu(this, this.zza, ((zzgi)object3), bitSet0, bitSet1, arrayMap2, arrayMap3, null);
                this.zzc.put(integer1, zzu0);
            }
        }
        if(!list0.isEmpty()) {
            zzw zzw0 = new zzw(this, null);
            ArrayMap arrayMap4 = new ArrayMap();
            Iterator iterator5 = list0.iterator();
            while(iterator5.hasNext()) {
                Object object5 = iterator5.next();
                zzft zzft0 = (zzft)object5;
                zzft zzft1 = zzw0.zza(this.zza, zzft0);
                if(zzft1 != null) {
                    zzak zzak2 = this.zzf.zzh();
                    String s3 = this.zza;
                    zzaq zzaq0 = zzak2.zzn(s3, "");
                    if(zzaq0 == null) {
                        zzak2.zzt.zzaA().zzk().zzc("Event aggregate wasn\'t created during raw event logging. appId, event", com.google.android.gms.measurement.internal.zzet.zzn(s3), zzak2.zzt.zzj().zzd(""));
                        iterator6 = iterator5;
                        zzaq1 = new zzaq(s3, "", 1L, 1L, 1L, zzft0.zzd(), 0L, null, null, null, null);
                    }
                    else {
                        iterator6 = iterator5;
                        zzaq1 = new zzaq(zzaq0.zza, zzaq0.zzb, zzaq0.zzc + 1L, zzaq0.zzd + 1L, zzaq0.zze + 1L, zzaq0.zzf, zzaq0.zzg, zzaq0.zzh, zzaq0.zzi, zzaq0.zzj, zzaq0.zzk);
                    }
                    this.zzf.zzh().zzE(zzaq1);
                    long v4 = zzaq1.zzc;
                    Map map5 = (Map)arrayMap4.get("");
                    if(map5 == null) {
                        zzak zzak3 = this.zzf.zzh();
                        String s4 = this.zza;
                        zzak3.zzW();
                        zzak3.zzg();
                        Preconditions.checkNotEmpty(s4);
                        Preconditions.checkNotEmpty("");
                        zzw1 = zzw0;
                        ArrayMap arrayMap5 = new ArrayMap();
                        SQLiteDatabase sQLiteDatabase2 = zzak3.zzh();
                        zzaq2 = zzaq1;
                        try {
                            cursor3 = sQLiteDatabase2.query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{s4, ""}, null, null, null);
                        }
                        catch(SQLiteException sQLiteException2) {
                            v5 = v4;
                            cursor3 = null;
                            goto label_227;
                        }
                        try {
                            if(cursor3.moveToFirst()) {
                                goto label_206;
                            }
                            else {
                                goto label_223;
                            }
                            goto label_235;
                        }
                        catch(SQLiteException sQLiteException2) {
                            v5 = v4;
                            goto label_227;
                        label_206:
                            v5 = v4;
                            try {
                                while(true) {
                                    byte[] arr_b2 = cursor3.getBlob(1);
                                    try {
                                        zzek1 = (zzek)((zzej)zzlj.zzm(zzek.zzc(), arr_b2)).zzaD();
                                    }
                                    catch(IOException iOException2) {
                                        zzak3.zzt.zzaA().zzd().zzc("Failed to merge filter. appId", com.google.android.gms.measurement.internal.zzet.zzn(s4), iOException2);
                                        goto label_219;
                                    }
                                    Integer integer3 = cursor3.getInt(0);
                                    List list5 = (List)arrayMap5.get(integer3);
                                    if(list5 == null) {
                                        list5 = new ArrayList();
                                        arrayMap5.put(integer3, list5);
                                    }
                                    list5.add(zzek1);
                                label_219:
                                    if(!cursor3.moveToNext()) {
                                        break;
                                    }
                                }
                            }
                            catch(SQLiteException sQLiteException2) {
                                goto label_227;
                            }
                            catch(Throwable throwable2) {
                                goto label_232;
                            }
                            cursor3.close();
                            map5 = arrayMap5;
                            goto label_235;
                            try {
                            label_223:
                                v5 = v4;
                                map5 = Collections.EMPTY_MAP;
                                goto label_234;
                            }
                            catch(SQLiteException sQLiteException2) {
                            }
                            catch(Throwable throwable2) {
                                goto label_232;
                            }
                            try {
                            label_227:
                                zzak3.zzt.zzaA().zzd().zzc("Database error querying filters. appId", com.google.android.gms.measurement.internal.zzet.zzn(s4), sQLiteException2);
                                map5 = Collections.EMPTY_MAP;
                                if(cursor3 == null) {
                                    goto label_235;
                                }
                            }
                            catch(Throwable throwable2) {
                                goto label_232;
                            }
                        }
                        catch(Throwable throwable2) {
                        label_232:
                            TWR.safeClose$NT(cursor3, throwable2);
                            throw throwable2;
                        }
                    label_234:
                        cursor3.close();
                    label_235:
                        arrayMap4.put("", map5);
                    }
                    else {
                        zzw1 = zzw0;
                        zzaq2 = zzaq1;
                        v5 = v4;
                    }
                    for(Object object6: map5.keySet()) {
                        Integer integer4 = (Integer)object6;
                        int v6 = (int)integer4;
                        if(this.zzb.contains(integer4)) {
                            this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", integer4);
                        }
                        else {
                            boolean z3 = true;
                            for(Object object7: ((List)map5.get(integer4))) {
                                zzx zzx0 = new zzx(this, this.zza, v6, ((zzek)object7));
                                map6 = map5;
                                z3 = zzx0.zzd(this.zzd, this.zze, zzft1, v5, zzaq2, this.zzf(v6, ((zzek)object7).zzb()));
                                zzx zzx1 = zzx0;
                                if(z3) {
                                    this.zzd(integer4).zzc(zzx1);
                                    map5 = map6;
                                    continue;
                                }
                                this.zzb.add(integer4);
                                goto label_263;
                            }
                            map6 = map5;
                        label_263:
                            if(!z3) {
                                this.zzb.add(integer4);
                            }
                            map5 = map6;
                        }
                    }
                    iterator5 = iterator6;
                    zzw0 = zzw1;
                }
            }
        }
        if(!list1.isEmpty()) {
            ArrayMap arrayMap6 = new ArrayMap();
            Iterator iterator9 = list1.iterator();
            while(iterator9.hasNext()) {
                Object object8 = iterator9.next();
                zzgm zzgm0 = (zzgm)object8;
                Map map7 = (Map)arrayMap6.get("");
                if(map7 == null) {
                    zzak zzak4 = this.zzf.zzh();
                    String s5 = this.zza;
                    zzak4.zzW();
                    zzak4.zzg();
                    Preconditions.checkNotEmpty(s5);
                    Preconditions.checkNotEmpty("");
                    ArrayMap arrayMap7 = new ArrayMap();
                    SQLiteDatabase sQLiteDatabase3 = zzak4.zzh();
                    try {
                        cursor4 = sQLiteDatabase3.query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{s5, ""}, null, null, null);
                    }
                    catch(SQLiteException sQLiteException3) {
                        iterator10 = iterator9;
                        s6 = s5;
                        cursor4 = null;
                        goto label_335;
                    }
                    catch(Throwable throwable3) {
                        cursor5 = null;
                        goto label_341;
                    }
                    try {
                        try {
                            if(cursor4.moveToFirst()) {
                                while(true) {
                                label_297:
                                    byte[] arr_b3 = cursor4.getBlob(1);
                                    try {
                                        zzet0 = (zzet)((zzes)zzlj.zzm(zzet.zzc(), arr_b3)).zzaD();
                                        goto label_306;
                                    }
                                    catch(IOException iOException3) {
                                    }
                                    goto label_301;
                                }
                            }
                            else {
                                goto label_331;
                            }
                            goto label_345;
                        }
                        catch(SQLiteException sQLiteException3) {
                            goto label_328;
                        }
                        try {
                        label_301:
                            iterator10 = iterator9;
                            zzer0 = zzak4.zzt.zzaA().zzd();
                            s6 = s5;
                        }
                        catch(SQLiteException sQLiteException3) {
                            goto label_319;
                        }
                        try {
                            zzer0.zzc("Failed to merge filter", com.google.android.gms.measurement.internal.zzet.zzn(s6), iOException3);
                            goto label_320;
                        }
                        catch(SQLiteException sQLiteException3) {
                            goto label_335;
                        }
                        try {
                        label_306:
                            integer5 = cursor4.getInt(0);
                            list6 = (List)arrayMap7.get(integer5);
                            if(list6 == null) {
                                goto label_309;
                            }
                            else {
                                goto label_313;
                            }
                            goto label_315;
                        }
                        catch(SQLiteException sQLiteException3) {
                            goto label_328;
                        }
                        try {
                        label_309:
                            iterator10 = iterator9;
                            List list7 = new ArrayList();
                            arrayMap7.put(integer5, list7);
                            goto label_315;
                        label_313:
                            iterator10 = iterator9;
                            list7 = list6;
                        label_315:
                            list7.add(zzet0);
                            s6 = s5;
                            goto label_320;
                        }
                        catch(SQLiteException sQLiteException3) {
                        }
                    }
                    catch(Throwable throwable3) {
                        goto label_340;
                    }
                label_319:
                    goto label_329;
                    try {
                    label_320:
                        if(!cursor4.moveToNext()) {
                            goto label_321;
                        }
                        goto label_324;
                    }
                    catch(SQLiteException sQLiteException3) {
                        goto label_335;
                    }
                    catch(Throwable throwable3) {
                        goto label_340;
                    }
                label_321:
                    cursor4.close();
                    map7 = arrayMap7;
                    goto label_345;
                    try {
                    label_324:
                        iterator9 = iterator10;
                        s5 = s6;
                        goto label_297;
                    }
                    catch(SQLiteException sQLiteException3) {
                    label_328:
                        iterator10 = iterator9;
                    label_329:
                        s6 = s5;
                        goto label_335;
                        try {
                        label_331:
                            iterator10 = iterator9;
                            map7 = Collections.EMPTY_MAP;
                            goto label_344;
                        }
                        catch(SQLiteException sQLiteException3) {
                        }
                        catch(Throwable throwable3) {
                            goto label_340;
                        }
                        try {
                        label_335:
                            zzak4.zzt.zzaA().zzd().zzc("Database error querying filters. appId", com.google.android.gms.measurement.internal.zzet.zzn(s6), sQLiteException3);
                            map7 = Collections.EMPTY_MAP;
                            if(cursor4 == null) {
                                goto label_345;
                            }
                            goto label_344;
                        }
                        catch(Throwable throwable3) {
                            goto label_340;
                        }
                    }
                    catch(Throwable throwable3) {
                    label_340:
                        cursor5 = cursor4;
                    }
                label_341:
                    if(cursor5 != null) {
                        cursor5.close();
                    }
                    throw throwable3;
                label_344:
                    cursor4.close();
                label_345:
                    arrayMap6.put("", map7);
                }
                else {
                    iterator10 = iterator9;
                }
                Iterator iterator11 = map7.keySet().iterator();
            label_349:
                while(iterator11.hasNext()) {
                    Object object9 = iterator11.next();
                    Integer integer6 = (Integer)object9;
                    int v7 = (int)integer6;
                    if(this.zzb.contains(integer6)) {
                        this.zzt.zzaA().zzj().zzb("Skipping failed audience ID", integer6);
                        break;
                    }
                    boolean z4 = true;
                    for(Object object10: ((List)map7.get(integer6))) {
                        zzet zzet1 = (zzet)object10;
                        if(Log.isLoggable(this.zzt.zzaA().zzr(), 2)) {
                            map8 = map7;
                            this.zzt.zzaA().zzj().zzd("Evaluating filter. audience, filter, property", integer6, (zzet1.zzj() ? zzet1.zza() : null), this.zzt.zzj().zzf(""));
                            this.zzt.zzaA().zzj().zzb("Filter definition", this.zzf.zzu().zzq(zzet1));
                        }
                        else {
                            map8 = map7;
                        }
                        if(zzet1.zzj() && zzet1.zza() <= 0x100) {
                            zzz zzz0 = new zzz(this, this.zza, v7, zzet1);
                            z4 = zzz0.zzd(this.zzd, this.zze, zzgm0, this.zzf(v7, zzet1.zza()));
                            if(z4) {
                                this.zzd(integer6).zzc(zzz0);
                                map7 = map8;
                                continue;
                            }
                            this.zzb.add(integer6);
                            goto label_381;
                        }
                        this.zzt.zzaA().zzk().zzc("Invalid property filter ID. appId, id", com.google.android.gms.measurement.internal.zzet.zzn(this.zza), String.valueOf((zzet1.zzj() ? zzet1.zza() : null)));
                        this.zzb.add(integer6);
                        map7 = map8;
                        continue label_349;
                    }
                    map8 = map7;
                label_381:
                    if(!z4) {
                        this.zzb.add(integer6);
                    }
                    map7 = map8;
                }
                iterator9 = iterator10;
            }
        }
        List list8 = new ArrayList();
        Set set0 = this.zzc.keySet();
        set0.removeAll(this.zzb);
        for(Object object11: set0) {
            zzu zzu1 = (zzu)this.zzc.get(((Integer)object11));
            Preconditions.checkNotNull(zzu1);
            zzfp zzfp0 = zzu1.zza(((int)(((Integer)object11))));
            list8.add(zzfp0);
            zzak zzak5 = this.zzf.zzh();
            String s7 = this.zza;
            zzgi zzgi3 = zzfp0.zzd();
            zzak5.zzW();
            zzak5.zzg();
            Preconditions.checkNotEmpty(s7);
            Preconditions.checkNotNull(zzgi3);
            byte[] arr_b4 = zzgi3.zzbx();
            ContentValues contentValues0 = new ContentValues();
            contentValues0.put("app_id", s7);
            contentValues0.put("audience_id", ((Integer)object11));
            contentValues0.put("current_results", arr_b4);
            try {
                if(zzak5.zzh().insertWithOnConflict("audience_filter_values", null, contentValues0, 5) != -1L) {
                    continue;
                }
                zzak5.zzt.zzaA().zzd().zzb("Failed to insert filter results (got -1). appId", com.google.android.gms.measurement.internal.zzet.zzn(s7));
            }
            catch(SQLiteException sQLiteException4) {
                zzak5.zzt.zzaA().zzd().zzc("Error storing filter results. appId", com.google.android.gms.measurement.internal.zzet.zzn(s7), sQLiteException4);
            }
        }
        return list8;
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    private final zzu zzd(Integer integer0) {
        if(this.zzc.containsKey(integer0)) {
            return (zzu)this.zzc.get(integer0);
        }
        zzu zzu0 = new zzu(this, this.zza, null);
        this.zzc.put(integer0, zzu0);
        return zzu0;
    }

    private final boolean zzf(int v, int v1) {
        zzu zzu0 = (zzu)this.zzc.get(v);
        return zzu0 == null ? false : zzu.zzb(zzu0).get(v1);
    }
}

