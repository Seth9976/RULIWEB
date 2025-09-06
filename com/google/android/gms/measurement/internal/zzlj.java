package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzek;
import com.google.android.gms.internal.measurement.zzem;
import com.google.android.gms.internal.measurement.zzer;
import com.google.android.gms.internal.measurement.zzet;
import com.google.android.gms.internal.measurement.zzey;
import com.google.android.gms.internal.measurement.zzfp;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgi;
import com.google.android.gms.internal.measurement.zzgk;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzkn;
import com.google.android.gms.internal.measurement.zzll;
import com.google.android.gms.internal.measurement.zzmh;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public final class zzlj extends zzku {
    zzlj(zzlh zzlh0) {
        super(zzlh0);
    }

    static final void zzA(zzfs zzfs0, String s, Object object0) {
        List list0 = zzfs0.zzp();
        int v;
        for(v = 0; true; ++v) {
            if(v >= list0.size()) {
                v = -1;
                break;
            }
            if(s.equals("")) {
                break;
            }
        }
        zzfw zzfw0 = zzfx.zze();
        zzfw0.zzj(s);
        if(object0 instanceof Long) {
            zzfw0.zzi(((long)(((Long)object0))));
        }
        if(v >= 0) {
            zzfs0.zzj(v, zzfw0);
            return;
        }
        zzfs0.zze(zzfw0);
    }

    static final boolean zzB(zzau zzau0, zzq zzq0) {
        Preconditions.checkNotNull(zzau0);
        Preconditions.checkNotNull(zzq0);
        return !TextUtils.isEmpty(zzq0.zzb) || !TextUtils.isEmpty(zzq0.zzq);
    }

    static final zzfx zzC(zzft zzft0, String s) {
        for(Object object0: zzft0.zzi()) {
            zzfx zzfx0 = (zzfx)object0;
            if("".equals(s)) {
                return zzfx0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    static final Object zzD(zzft zzft0, String s) {
        zzfx zzfx0 = zzlj.zzC(zzft0, s);
        if(zzfx0 != null) {
            if(zzfx0.zzy()) {
                return "";
            }
            if(zzfx0.zzw()) {
                return zzfx0.zzd();
            }
            if(zzfx0.zzu()) {
                return zzfx0.zza();
            }
            if(zzfx0.zzc() > 0) {
                ArrayList arrayList0 = new ArrayList();
                for(Object object0: zzfx0.zzi()) {
                    zzfx zzfx1 = (zzfx)object0;
                    if(zzfx1 != null) {
                        Bundle bundle0 = new Bundle();
                        for(Object object1: zzfx1.zzi()) {
                            zzfx zzfx2 = (zzfx)object1;
                            if(zzfx2.zzy()) {
                                bundle0.putString("", "");
                            }
                            else if(zzfx2.zzw()) {
                                bundle0.putLong("", zzfx2.zzd());
                            }
                            else if(zzfx2.zzu()) {
                                bundle0.putDouble("", zzfx2.zza());
                            }
                        }
                        if(!bundle0.isEmpty()) {
                            arrayList0.add(bundle0);
                        }
                    }
                }
                return (Bundle[])arrayList0.toArray(new Bundle[arrayList0.size()]);
            }
        }
        return null;
    }

    private final void zzE(StringBuilder stringBuilder0, int v, List list0) {
        if(list0 != null) {
            for(Object object0: list0) {
                zzfx zzfx0 = (zzfx)object0;
                if(zzfx0 != null) {
                    zzlj.zzG(stringBuilder0, v + 1);
                    stringBuilder0.append("param {\n");
                    Double double0 = null;
                    zzlj.zzJ(stringBuilder0, v + 1, "name", (zzfx0.zzx() ? this.zzt.zzj().zze("") : null));
                    zzlj.zzJ(stringBuilder0, v + 1, "string_value", (zzfx0.zzy() ? "" : null));
                    zzlj.zzJ(stringBuilder0, v + 1, "int_value", (zzfx0.zzw() ? zzfx0.zzd() : null));
                    if(zzfx0.zzu()) {
                        double0 = zzfx0.zza();
                    }
                    zzlj.zzJ(stringBuilder0, v + 1, "double_value", double0);
                    if(zzfx0.zzc() > 0) {
                        this.zzE(stringBuilder0, v + 1, zzfx0.zzi());
                    }
                    zzlj.zzG(stringBuilder0, v + 1);
                    stringBuilder0.append("}\n");
                }
            }
        }
    }

    private final void zzF(StringBuilder stringBuilder0, int v, zzem zzem0) {
        String s;
        if(zzem0 == null) {
            return;
        }
        zzlj.zzG(stringBuilder0, v);
        stringBuilder0.append("filter {\n");
        if(zzem0.zzh()) {
            zzlj.zzJ(stringBuilder0, v, "complement", Boolean.valueOf(zzem0.zzg()));
        }
        if(zzem0.zzj()) {
            zzlj.zzJ(stringBuilder0, v, "param_name", this.zzt.zzj().zze(""));
        }
        if(zzem0.zzk()) {
            zzey zzey0 = zzem0.zzd();
            if(zzey0 != null) {
                zzlj.zzG(stringBuilder0, v + 1);
                stringBuilder0.append("string_filter {\n");
                if(zzey0.zzi()) {
                    switch(zzey0.zzj()) {
                        case 1: {
                            s = "UNKNOWN_MATCH_TYPE";
                            break;
                        }
                        case 2: {
                            s = "REGEXP";
                            break;
                        }
                        case 3: {
                            s = "BEGINS_WITH";
                            break;
                        }
                        case 4: {
                            s = "ENDS_WITH";
                            break;
                        }
                        case 5: {
                            s = "PARTIAL";
                            break;
                        }
                        case 6: {
                            s = "EXACT";
                            break;
                        }
                        default: {
                            s = "IN_LIST";
                        }
                    }
                    zzlj.zzJ(stringBuilder0, v + 1, "match_type", s);
                }
                if(zzey0.zzh()) {
                    zzlj.zzJ(stringBuilder0, v + 1, "expression", "");
                }
                if(zzey0.zzg()) {
                    zzlj.zzJ(stringBuilder0, v + 1, "case_sensitive", Boolean.valueOf(zzey0.zzf()));
                }
                if(zzey0.zza() > 0) {
                    zzlj.zzG(stringBuilder0, v + 2);
                    stringBuilder0.append("expression_list {\n");
                    for(Object object0: zzey0.zze()) {
                        zzlj.zzG(stringBuilder0, v + 3);
                        stringBuilder0.append(((String)object0));
                        stringBuilder0.append("\n");
                    }
                    stringBuilder0.append("}\n");
                }
                zzlj.zzG(stringBuilder0, v + 1);
                stringBuilder0.append("}\n");
            }
        }
        if(zzem0.zzi()) {
            zzlj.zzK(stringBuilder0, v + 1, "number_filter", zzem0.zzc());
        }
        zzlj.zzG(stringBuilder0, v);
        stringBuilder0.append("}\n");
    }

    private static final void zzG(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            stringBuilder0.append("  ");
        }
    }

    private static final String zzH(boolean z, boolean z1, boolean z2) {
        StringBuilder stringBuilder0 = new StringBuilder();
        if(z) {
            stringBuilder0.append("Dynamic ");
        }
        if(z1) {
            stringBuilder0.append("Sequence ");
        }
        if(z2) {
            stringBuilder0.append("Session-Scoped ");
        }
        return stringBuilder0.toString();
    }

    private static final void zzI(StringBuilder stringBuilder0, int v, String s, zzgi zzgi0) {
        if(zzgi0 == null) {
            return;
        }
        zzlj.zzG(stringBuilder0, 3);
        stringBuilder0.append(s);
        stringBuilder0.append(" {\n");
        if(zzgi0.zzb() != 0) {
            zzlj.zzG(stringBuilder0, 4);
            stringBuilder0.append("results: ");
            int v1 = 0;
            for(Object object0: zzgi0.zzi()) {
                if(v1 != 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(((Long)object0));
                ++v1;
            }
            stringBuilder0.append('\n');
        }
        if(zzgi0.zzd() != 0) {
            zzlj.zzG(stringBuilder0, 4);
            stringBuilder0.append("status: ");
            int v2 = 0;
            for(Object object1: zzgi0.zzk()) {
                if(v2 != 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(((Long)object1));
                ++v2;
            }
            stringBuilder0.append('\n');
        }
        if(zzgi0.zza() != 0) {
            zzlj.zzG(stringBuilder0, 4);
            stringBuilder0.append("dynamic_filter_timestamps: {");
            int v3 = 0;
            for(Object object2: zzgi0.zzh()) {
                zzfr zzfr0 = (zzfr)object2;
                if(v3 != 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append((zzfr0.zzh() ? zzfr0.zza() : null));
                stringBuilder0.append(":");
                stringBuilder0.append((zzfr0.zzg() ? zzfr0.zzb() : null));
                ++v3;
            }
            stringBuilder0.append("}\n");
        }
        if(zzgi0.zzc() != 0) {
            zzlj.zzG(stringBuilder0, 4);
            stringBuilder0.append("sequence_filter_timestamps: {");
            int v4 = 0;
            for(Object object3: zzgi0.zzj()) {
                zzgk zzgk0 = (zzgk)object3;
                if(v4 != 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append((zzgk0.zzi() ? zzgk0.zzb() : null));
                stringBuilder0.append(": [");
                int v5 = 0;
                for(Object object4: zzgk0.zzf()) {
                    long v6 = (long)(((Long)object4));
                    if(v5 != 0) {
                        stringBuilder0.append(", ");
                    }
                    stringBuilder0.append(v6);
                    ++v5;
                }
                stringBuilder0.append("]");
                ++v4;
            }
            stringBuilder0.append("}\n");
        }
        zzlj.zzG(stringBuilder0, 3);
        stringBuilder0.append("}\n");
    }

    private static final void zzJ(StringBuilder stringBuilder0, int v, String s, Object object0) {
        if(object0 == null) {
            return;
        }
        zzlj.zzG(stringBuilder0, v + 1);
        stringBuilder0.append(s);
        stringBuilder0.append(": ");
        stringBuilder0.append(object0);
        stringBuilder0.append('\n');
    }

    private static final void zzK(StringBuilder stringBuilder0, int v, String s, zzer zzer0) {
        String s1;
        if(zzer0 == null) {
            return;
        }
        zzlj.zzG(stringBuilder0, v);
        stringBuilder0.append(s);
        stringBuilder0.append(" {\n");
        if(zzer0.zzg()) {
            switch(zzer0.zzm()) {
                case 1: {
                    s1 = "UNKNOWN_COMPARISON_TYPE";
                    break;
                }
                case 2: {
                    s1 = "LESS_THAN";
                    break;
                }
                case 3: {
                    s1 = "GREATER_THAN";
                    break;
                }
                case 4: {
                    s1 = "EQUAL";
                    break;
                }
                default: {
                    s1 = "BETWEEN";
                }
            }
            zzlj.zzJ(stringBuilder0, v, "comparison_type", s1);
        }
        if(zzer0.zzi()) {
            zzlj.zzJ(stringBuilder0, v, "match_as_float", Boolean.valueOf(zzer0.zzf()));
        }
        if(zzer0.zzh()) {
            zzlj.zzJ(stringBuilder0, v, "comparison_value", "");
        }
        if(zzer0.zzk()) {
            zzlj.zzJ(stringBuilder0, v, "min_comparison_value", "");
        }
        if(zzer0.zzj()) {
            zzlj.zzJ(stringBuilder0, v, "max_comparison_value", "");
        }
        zzlj.zzG(stringBuilder0, v);
        stringBuilder0.append("}\n");
    }

    static int zza(zzgc zzgc0, String s) {
        for(int v = 0; v < zzgc0.zzb(); ++v) {
            if(s.equals("")) {
                return v;
            }
        }
        return -1;
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    // 去混淆评级： 低(20)
    final long zzd(String s) {
        return TextUtils.isEmpty(s) ? 0L : this.zzf(s.getBytes(Charset.forName("UTF-8")));
    }

    final long zzf(byte[] arr_b) {
        Preconditions.checkNotNull(arr_b);
        this.zzt.zzv().zzg();
        MessageDigest messageDigest0 = zzlp.zzF();
        if(messageDigest0 == null) {
            this.zzt.zzaA().zzd().zza("Failed to get MD5");
            return 0L;
        }
        return zzlp.zzp(messageDigest0.digest(arr_b));
    }

    final Bundle zzh(Map map0, boolean z) {
        Bundle bundle0 = new Bundle();
        for(Object object0: map0.keySet()) {
            String s = (String)object0;
            Object object1 = map0.get(s);
            if(object1 == null) {
                bundle0.putString(s, null);
            }
            else if(object1 instanceof Long) {
                bundle0.putLong(s, ((long)(((Long)object1))));
            }
            else if(object1 instanceof Double) {
                bundle0.putDouble(s, ((double)(((Double)object1))));
            }
            else if(!(object1 instanceof ArrayList)) {
                bundle0.putString(s, object1.toString());
            }
            else if(z) {
                ArrayList arrayList0 = (ArrayList)object1;
                ArrayList arrayList1 = new ArrayList();
                int v = arrayList0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    arrayList1.add(this.zzh(((Map)arrayList0.get(v1)), false));
                }
                bundle0.putParcelableArray(s, ((Parcelable[])arrayList1.toArray(new Parcelable[0])));
            }
        }
        return bundle0;
    }

    final Parcelable zzi(byte[] arr_b, Parcelable.Creator parcelable$Creator0) {
        if(arr_b == null) {
            return null;
        }
        Parcel parcel0 = Parcel.obtain();
        try {
            parcel0.unmarshall(arr_b, 0, arr_b.length);
            parcel0.setDataPosition(0);
            return (Parcelable)parcelable$Creator0.createFromParcel(parcel0);
        }
        catch(ParseException unused_ex) {
            this.zzt.zzaA().zzd().zza("Failed to load parcelable from buffer");
            return null;
        }
        finally {
            parcel0.recycle();
        }
    }

    final zzau zzj(zzaa zzaa0) {
        String s;
        Bundle bundle0 = this.zzh(zzaa0.zze(), true);
        if(bundle0.containsKey("_o")) {
            Object object0 = bundle0.get("_o");
            s = object0 == null ? "app" : object0.toString();
        }
        else {
            s = "app";
        }
        String s1 = zzhc.zzb(zzaa0.zzd());
        if(s1 == null) {
            s1 = zzaa0.zzd();
        }
        return new zzau(s1, new zzas(bundle0), s, zzaa0.zza());
    }

    final zzft zzl(zzap zzap0) {
        zzfs zzfs0 = zzft.zze();
        zzfs0.zzl(zzap0.zze);
        zzar zzar0 = new zzar(zzap0.zzf);
        while(zzar0.hasNext()) {
            String s = zzar0.zza();
            zzfw zzfw0 = zzfx.zze();
            zzfw0.zzj(s);
            Object object0 = zzap0.zzf.zzf(s);
            Preconditions.checkNotNull(object0);
            this.zzu(zzfw0, object0);
            zzfs0.zze(zzfw0);
        }
        return (zzft)zzfs0.zzaD();
    }

    static zzmh zzm(zzmh zzmh0, byte[] arr_b) throws zzll {
        zzkn zzkn0 = zzkn.zza();
        return zzkn0 == null ? zzmh0.zzay(arr_b) : zzmh0.zzaz(arr_b, zzkn0);
    }

    final String zzo(zzgb zzgb0) {
        if(zzgb0 == null) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("\nbatch {\n");
        for(Object object0: zzgb0.zzd()) {
            zzgd zzgd0 = (zzgd)object0;
            if(zzgd0 != null) {
                zzlj.zzG(stringBuilder0, 1);
                stringBuilder0.append("bundle {\n");
                if(zzgd0.zzbl()) {
                    zzlj.zzJ(stringBuilder0, 1, "protocol_version", zzgd0.zzd());
                }
                zzqu.zzc();
                if(this.zzt.zzf().zzs("", zzeg.zzao) && zzgd0.zzbo()) {
                    zzlj.zzJ(stringBuilder0, 1, "session_stitching_token", "");
                }
                zzlj.zzJ(stringBuilder0, 1, "platform", "");
                if(zzgd0.zzbh()) {
                    zzlj.zzJ(stringBuilder0, 1, "gmp_version", zzgd0.zzm());
                }
                if(zzgd0.zzbt()) {
                    zzlj.zzJ(stringBuilder0, 1, "uploading_gmp_version", zzgd0.zzs());
                }
                if(zzgd0.zzbf()) {
                    zzlj.zzJ(stringBuilder0, 1, "dynamite_version", zzgd0.zzj());
                }
                if(zzgd0.zzbc()) {
                    zzlj.zzJ(stringBuilder0, 1, "config_version", zzgd0.zzh());
                }
                zzlj.zzJ(stringBuilder0, 1, "gmp_app_id", "");
                zzlj.zzJ(stringBuilder0, 1, "admob_app_id", "");
                zzlj.zzJ(stringBuilder0, 1, "app_id", "");
                zzlj.zzJ(stringBuilder0, 1, "app_version", "");
                if(zzgd0.zzba()) {
                    zzlj.zzJ(stringBuilder0, 1, "app_version_major", zzgd0.zza());
                }
                zzlj.zzJ(stringBuilder0, 1, "firebase_instance_id", "");
                if(zzgd0.zzbe()) {
                    zzlj.zzJ(stringBuilder0, 1, "dev_cert_hash", zzgd0.zzi());
                }
                zzlj.zzJ(stringBuilder0, 1, "app_store", "");
                if(zzgd0.zzbs()) {
                    zzlj.zzJ(stringBuilder0, 1, "upload_timestamp_millis", zzgd0.zzr());
                }
                if(zzgd0.zzbp()) {
                    zzlj.zzJ(stringBuilder0, 1, "start_timestamp_millis", zzgd0.zzp());
                }
                if(zzgd0.zzbg()) {
                    zzlj.zzJ(stringBuilder0, 1, "end_timestamp_millis", zzgd0.zzk());
                }
                if(zzgd0.zzbk()) {
                    zzlj.zzJ(stringBuilder0, 1, "previous_bundle_start_timestamp_millis", zzgd0.zzo());
                }
                if(zzgd0.zzbj()) {
                    zzlj.zzJ(stringBuilder0, 1, "previous_bundle_end_timestamp_millis", zzgd0.zzn());
                }
                zzlj.zzJ(stringBuilder0, 1, "app_instance_id", "");
                zzlj.zzJ(stringBuilder0, 1, "resettable_device_id", "");
                zzlj.zzJ(stringBuilder0, 1, "ds_id", "");
                if(zzgd0.zzbi()) {
                    zzlj.zzJ(stringBuilder0, 1, "limited_ad_tracking", Boolean.valueOf(zzgd0.zzaY()));
                }
                zzlj.zzJ(stringBuilder0, 1, "os_version", "");
                zzlj.zzJ(stringBuilder0, 1, "device_model", "");
                zzlj.zzJ(stringBuilder0, 1, "user_default_language", "");
                if(zzgd0.zzbr()) {
                    zzlj.zzJ(stringBuilder0, 1, "time_zone_offset_minutes", zzgd0.zzf());
                }
                if(zzgd0.zzbb()) {
                    zzlj.zzJ(stringBuilder0, 1, "bundle_sequential_index", zzgd0.zzb());
                }
                if(zzgd0.zzbn()) {
                    zzlj.zzJ(stringBuilder0, 1, "service_upload", Boolean.valueOf(zzgd0.zzaZ()));
                }
                zzlj.zzJ(stringBuilder0, 1, "health_monitor", "");
                if(zzgd0.zzbm()) {
                    zzlj.zzJ(stringBuilder0, 1, "retry_counter", zzgd0.zze());
                }
                if(zzgd0.zzbd()) {
                    zzlj.zzJ(stringBuilder0, 1, "consent_signals", "");
                }
                zzpz.zzc();
                if(this.zzt.zzf().zzs(null, zzeg.zzaE) && zzgd0.zzbq()) {
                    zzlj.zzJ(stringBuilder0, 1, "target_os_version", zzgd0.zzq());
                }
                List list0 = zzgd0.zzP();
                if(list0 != null) {
                    for(Object object1: list0) {
                        zzgm zzgm0 = (zzgm)object1;
                        if(zzgm0 != null) {
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("user_property {\n");
                            zzlj.zzJ(stringBuilder0, 2, "set_timestamp_millis", (zzgm0.zzs() ? zzgm0.zzc() : null));
                            zzlj.zzJ(stringBuilder0, 2, "name", this.zzt.zzj().zzf(""));
                            zzlj.zzJ(stringBuilder0, 2, "string_value", "");
                            zzlj.zzJ(stringBuilder0, 2, "int_value", (zzgm0.zzr() ? zzgm0.zzb() : null));
                            zzlj.zzJ(stringBuilder0, 2, "double_value", (zzgm0.zzq() ? zzgm0.zza() : null));
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("}\n");
                        }
                    }
                }
                List list1 = zzgd0.zzN();
                if(list1 != null) {
                    for(Object object2: list1) {
                        zzfp zzfp0 = (zzfp)object2;
                        if(zzfp0 != null) {
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("audience_membership {\n");
                            if(zzfp0.zzk()) {
                                zzlj.zzJ(stringBuilder0, 2, "audience_id", zzfp0.zza());
                            }
                            if(zzfp0.zzm()) {
                                zzlj.zzJ(stringBuilder0, 2, "new_audience", Boolean.valueOf(zzfp0.zzj()));
                            }
                            zzlj.zzI(stringBuilder0, 2, "current_data", zzfp0.zzd());
                            if(zzfp0.zzn()) {
                                zzlj.zzI(stringBuilder0, 2, "previous_data", zzfp0.zze());
                            }
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("}\n");
                        }
                    }
                }
                List list2 = zzgd0.zzO();
                if(list2 != null) {
                    for(Object object3: list2) {
                        zzft zzft0 = (zzft)object3;
                        if(zzft0 != null) {
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("event {\n");
                            zzlj.zzJ(stringBuilder0, 2, "name", this.zzt.zzj().zzd(""));
                            if(zzft0.zzu()) {
                                zzlj.zzJ(stringBuilder0, 2, "timestamp_millis", zzft0.zzd());
                            }
                            if(zzft0.zzt()) {
                                zzlj.zzJ(stringBuilder0, 2, "previous_timestamp_millis", zzft0.zzc());
                            }
                            if(zzft0.zzs()) {
                                zzlj.zzJ(stringBuilder0, 2, "count", zzft0.zza());
                            }
                            if(zzft0.zzb() != 0) {
                                this.zzE(stringBuilder0, 2, zzft0.zzi());
                            }
                            zzlj.zzG(stringBuilder0, 2);
                            stringBuilder0.append("}\n");
                        }
                    }
                }
                zzlj.zzG(stringBuilder0, 1);
                stringBuilder0.append("}\n");
            }
        }
        stringBuilder0.append("}\n");
        return stringBuilder0.toString();
    }

    final String zzp(zzek zzek0) {
        if(zzek0 == null) {
            return "null";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("\nevent_filter {\n");
        if(zzek0.zzp()) {
            zzlj.zzJ(stringBuilder0, 0, "filter_id", zzek0.zzb());
        }
        zzlj.zzJ(stringBuilder0, 0, "event_name", this.zzt.zzj().zzd(""));
        String s = zzlj.zzH(zzek0.zzk(), zzek0.zzm(), zzek0.zzn());
        if(!s.isEmpty()) {
            zzlj.zzJ(stringBuilder0, 0, "filter_type", s);
        }
        if(zzek0.zzo()) {
            zzlj.zzK(stringBuilder0, 1, "event_count_filter", zzek0.zzf());
        }
        if(zzek0.zza() > 0) {
            stringBuilder0.append("  filters {\n");
            for(Object object0: zzek0.zzh()) {
                this.zzF(stringBuilder0, 2, ((zzem)object0));
            }
        }
        zzlj.zzG(stringBuilder0, 1);
        stringBuilder0.append("}\n}\n");
        return stringBuilder0.toString();
    }

    final String zzq(zzet zzet0) {
        if(zzet0 == null) {
            return "null";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("\nproperty_filter {\n");
        if(zzet0.zzj()) {
            zzlj.zzJ(stringBuilder0, 0, "filter_id", zzet0.zza());
        }
        zzlj.zzJ(stringBuilder0, 0, "property_name", this.zzt.zzj().zzf(""));
        String s = zzlj.zzH(zzet0.zzg(), zzet0.zzh(), zzet0.zzi());
        if(!s.isEmpty()) {
            zzlj.zzJ(stringBuilder0, 0, "filter_type", s);
        }
        this.zzF(stringBuilder0, 1, zzet0.zzb());
        stringBuilder0.append("}\n");
        return stringBuilder0.toString();
    }

    final List zzr(List list0, List list1) {
        int v3;
        ArrayList arrayList0 = new ArrayList(list0);
        for(Object object0: list1) {
            Integer integer0 = (Integer)object0;
            if(((int)integer0) < 0) {
                this.zzt.zzaA().zzk().zzb("Ignoring negative bit index to be cleared", integer0);
            }
            else {
                int v = ((int)integer0) / 0x40;
                if(v >= arrayList0.size()) {
                    this.zzt.zzaA().zzk().zzc("Ignoring bit index greater than bitSet size", integer0, arrayList0.size());
                }
                else {
                    arrayList0.set(v, ((long)(((long)(((Long)arrayList0.get(v)))) & ~(1L << ((int)integer0) % 0x40))));
                }
            }
        }
        int v1 = arrayList0.size();
        for(int v2 = arrayList0.size() - 1; true; v2 = v1 - 1) {
            v3 = v1;
            v1 = v2;
            if(v1 < 0 || ((long)(((Long)arrayList0.get(v1)))) != 0L) {
                break;
            }
        }
        return arrayList0.subList(0, v3);
    }

    static List zzs(BitSet bitSet0) {
        int v = (bitSet0.length() + 0x3F) / 0x40;
        List list0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            long v2 = 0L;
            for(int v3 = 0; v3 < 0x40; ++v3) {
                int v4 = v1 * 0x40 + v3;
                if(v4 >= bitSet0.length()) {
                    break;
                }
                if(bitSet0.get(v4)) {
                    v2 |= 1L << v3;
                }
            }
            ((ArrayList)list0).add(v2);
        }
        return list0;
    }

    final Map zzt(Bundle bundle0, boolean z) {
        Map map0 = new HashMap();
        for(Object object0: bundle0.keySet()) {
            String s = (String)object0;
            Object object1 = bundle0.get(s);
            boolean z1 = object1 instanceof Parcelable[];
            if(!z1 && !(object1 instanceof ArrayList) && !(object1 instanceof Bundle)) {
                if(object1 == null) {
                    continue;
                }
                map0.put(s, object1);
            }
            else if(z) {
                ArrayList arrayList0 = new ArrayList();
                if(z1) {
                    Parcelable[] arr_parcelable = (Parcelable[])object1;
                    for(int v = 0; v < arr_parcelable.length; ++v) {
                        Parcelable parcelable0 = arr_parcelable[v];
                        if(parcelable0 instanceof Bundle) {
                            arrayList0.add(this.zzt(((Bundle)parcelable0), false));
                        }
                    }
                }
                else if(object1 instanceof ArrayList) {
                    ArrayList arrayList1 = (ArrayList)object1;
                    int v1 = arrayList1.size();
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Object object2 = arrayList1.get(v2);
                        if(object2 instanceof Bundle) {
                            arrayList0.add(this.zzt(((Bundle)object2), false));
                        }
                    }
                }
                else if(object1 instanceof Bundle) {
                    arrayList0.add(this.zzt(((Bundle)object1), false));
                }
                map0.put(s, arrayList0);
            }
        }
        return map0;
    }

    final void zzu(zzfw zzfw0, Object object0) {
        Preconditions.checkNotNull(object0);
        zzfw0.zzg();
        zzfw0.zze();
        zzfw0.zzd();
        zzfw0.zzf();
        if(object0 instanceof String) {
            zzfw0.zzk(((String)object0));
            return;
        }
        if(object0 instanceof Long) {
            zzfw0.zzi(((long)(((Long)object0))));
            return;
        }
        if(object0 instanceof Double) {
            zzfw0.zzh(((double)(((Double)object0))));
            return;
        }
        if(object0 instanceof Bundle[]) {
            ArrayList arrayList0 = new ArrayList();
            int v = 0;
            while(v < ((Bundle[])object0).length) {
                Bundle bundle0 = ((Bundle[])object0)[v];
                if(bundle0 != null) {
                    zzfw zzfw1 = zzfx.zze();
                    for(Object object1: bundle0.keySet()) {
                        zzfw zzfw2 = zzfx.zze();
                        zzfw2.zzj(((String)object1));
                        Object object2 = bundle0.get(((String)object1));
                        if(object2 instanceof Long) {
                            zzfw2.zzi(((long)(((Long)object2))));
                        }
                        else if(object2 instanceof String) {
                            zzfw2.zzk(((String)object2));
                        }
                        else {
                            if(!(object2 instanceof Double)) {
                                continue;
                            }
                            zzfw2.zzh(((double)(((Double)object2))));
                        }
                        zzfw1.zzc(zzfw2);
                    }
                    if(zzfw1.zza() > 0) {
                        arrayList0.add(((zzfx)zzfw1.zzaD()));
                    }
                }
                ++v;
            }
            zzfw0.zzb(arrayList0);
            return;
        }
        this.zzt.zzaA().zzd().zzb("Ignoring invalid (type) event param value", object0);
    }

    final void zzv(zzgl zzgl0, Object object0) {
        Preconditions.checkNotNull(object0);
        zzgl0.zzc();
        zzgl0.zzb();
        zzgl0.zza();
        if(object0 instanceof String) {
            zzgl0.zzh(((String)object0));
            return;
        }
        if(object0 instanceof Long) {
            zzgl0.zze(((long)(((Long)object0))));
            return;
        }
        if(object0 instanceof Double) {
            zzgl0.zzd(((double)(((Double)object0))));
            return;
        }
        this.zzt.zzaA().zzd().zzb("Ignoring invalid (type) user attribute value", object0);
    }

    static boolean zzw(List list0, int v) {
        return v < list0.size() * 0x40 && (1L << v % 0x40 & ((long)(((Long)list0.get(v / 0x40))))) != 0L;
    }

    final boolean zzx(long v, long v1) {
        return v == 0L || v1 <= 0L || Math.abs(this.zzt.zzax().currentTimeMillis() - v) > v1;
    }

    static boolean zzy(String s) [...] // 潜在的解密器

    final byte[] zzz(byte[] arr_b) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream0 = new GZIPOutputStream(byteArrayOutputStream0);
            gZIPOutputStream0.write(arr_b);
            gZIPOutputStream0.close();
            byteArrayOutputStream0.close();
            return byteArrayOutputStream0.toByteArray();
        }
        catch(IOException iOException0) {
            this.zzt.zzaA().zzd().zzb("Failed to gzip content", iOException0);
            throw iOException0;
        }
    }
}

