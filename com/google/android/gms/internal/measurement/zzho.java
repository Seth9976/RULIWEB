package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public final class zzho {
    private static volatile zzii zza;

    static {
    }

    public static zzii zza(Context context0) {
        zzhh zzhh0;
        zzii zzii2;
        File file0;
        zzii zzii1;
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0;
        zzii zzii0;
        synchronized(zzho.class) {
            zzii0 = zzho.zza;
            if(zzii0 == null) {
                if((Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug")) && (Build.TAGS.contains("dev-keys") || Build.TAGS.contains("test-keys"))) {
                    if(!context0.isDeviceProtectedStorage()) {
                        context0 = context0.createDeviceProtectedStorageContext();
                    }
                    strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
                    goto label_10;
                }
                else {
                    zzii1 = zzii.zzc();
                }
                zzii0 = zzii1;
                zzho.zza = zzii0;
                return zzii0;
            }
            return zzii0;
        }
    label_10:
        StrictMode.allowThreadDiskWrites();
        try {
            file0 = new File(context0.getDir("phenotype_hermetic", 0), "overrides.txt");
        }
        catch(RuntimeException runtimeException0) {
            Log.e("HermeticFileOverrides", "no data dir", runtimeException0);
            zzii2 = zzii.zzc();
            goto label_18;
        }
        zzii2 = file0.exists() ? zzii.zzd(file0) : zzii.zzc();
    label_18:
        if(zzii2.zzb()) {
            Object object0 = zzii2.zza();
            try(BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(new FileInputStream(((File)object0))))) {
                SimpleArrayMap simpleArrayMap0 = new SimpleArrayMap();
                HashMap hashMap0 = new HashMap();
                String s;
                while((s = bufferedReader0.readLine()) != null) {
                    String[] arr_s = s.split(" ", 3);
                    if(arr_s.length == 3) {
                        String s1 = new String(arr_s[0]);
                        String s2 = Uri.decode(new String(arr_s[1]));
                        String s3 = (String)hashMap0.get(arr_s[2]);
                        if(s3 == null) {
                            String s4 = new String(arr_s[2]);
                            s3 = Uri.decode(s4);
                            if(s3.length() < 0x400 || s3 == s4) {
                                hashMap0.put(s4, s3);
                            }
                        }
                        if(!simpleArrayMap0.containsKey(s1)) {
                            simpleArrayMap0.put(s1, new SimpleArrayMap());
                        }
                        ((SimpleArrayMap)simpleArrayMap0.get(s1)).put(s2, s3);
                    }
                    else {
                        Log.e("HermeticFileOverrides", "Invalid: " + s);
                    }
                }
                Log.w("HermeticFileOverrides", "Parsed " + object0.toString() + " for Android package " + "com.ruliweb.www.ruliapp");
                zzhh0 = new zzhh(simpleArrayMap0);
            }
            catch(IOException iOException0) {
                throw new RuntimeException(iOException0);
            }
            zzii1 = zzii.zzd(zzhh0);
        }
        else {
            zzii1 = zzii.zzc();
        }
        StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        zzii0 = zzii1;
        zzho.zza = zzii0;
        return zzii0;
    }
}

