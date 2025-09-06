package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;

public final class DeviceProperties {
    private static Boolean zza;
    private static Boolean zzb;
    private static Boolean zzc;
    private static Boolean zzd;
    private static Boolean zze;
    private static Boolean zzf;
    private static Boolean zzg;
    private static Boolean zzh;
    private static Boolean zzi;
    private static Boolean zzj;
    private static Boolean zzk;
    private static Boolean zzl;
    private static Boolean zzm;
    private static Boolean zzn;

    public static boolean isAuto(Context context0) {
        boolean z = false;
        PackageManager packageManager0 = context0.getPackageManager();
        if(DeviceProperties.zzj == null) {
            if(packageManager0.hasSystemFeature("android.hardware.type.automotive")) {
                z = true;
            }
            DeviceProperties.zzj = Boolean.valueOf(z);
        }
        return DeviceProperties.zzj.booleanValue();
    }

    public static boolean isBstar(Context context0) {
        boolean z = false;
        if(DeviceProperties.zzm == null) {
            if(context0.getPackageManager().hasSystemFeature("com.google.android.play.feature.HPE_EXPERIENCE")) {
                z = true;
            }
            DeviceProperties.zzm = Boolean.valueOf(z);
        }
        return DeviceProperties.zzm.booleanValue();
    }

    public static boolean isFoldable(Context context0) {
        if(DeviceProperties.zzc == null) {
            SensorManager sensorManager0 = (SensorManager)context0.getSystemService("sensor");
            DeviceProperties.zzc = Boolean.valueOf(sensorManager0 != null && sensorManager0.getDefaultSensor(36) != null);
        }
        return DeviceProperties.zzc.booleanValue();
    }

    public static boolean isLatchsky(Context context0) {
        if(DeviceProperties.zzg == null) {
            PackageManager packageManager0 = context0.getPackageManager();
            DeviceProperties.zzg = Boolean.valueOf(packageManager0.hasSystemFeature("com.google.android.feature.services_updater") && packageManager0.hasSystemFeature("cn.google.services"));
        }
        return DeviceProperties.zzg.booleanValue();
    }

    public static boolean isPhone(Context context0) {
        boolean z = true;
        if(DeviceProperties.zza == null) {
            if(!DeviceProperties.isFoldable(context0)) {
                if(DeviceProperties.isTablet(context0) || DeviceProperties.isWearable(context0) || DeviceProperties.zzb(context0)) {
                    z = false;
                }
                else {
                    if(DeviceProperties.zzi == null) {
                        DeviceProperties.zzi = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("org.chromium.arc"));
                    }
                    if(DeviceProperties.zzi.booleanValue() || DeviceProperties.isAuto(context0) || DeviceProperties.isTv(context0)) {
                        z = false;
                    }
                    else {
                        if(DeviceProperties.zzl == null) {
                            DeviceProperties.zzl = Boolean.valueOf(context0.getPackageManager().hasSystemFeature("com.google.android.feature.AMATI_EXPERIENCE"));
                        }
                        if(DeviceProperties.zzl.booleanValue() || DeviceProperties.isBstar(context0) || DeviceProperties.isXr(context0)) {
                            z = false;
                        }
                    }
                }
            }
            DeviceProperties.zza = Boolean.valueOf(z);
        }
        return DeviceProperties.zza.booleanValue();
    }

    public static boolean isSevenInchTablet(Context context0) {
        return DeviceProperties.zzc(context0.getResources());
    }

    public static boolean isSidewinder(Context context0) {
        return DeviceProperties.zza(context0);
    }

    public static boolean isTablet(Context context0) {
        return DeviceProperties.isTablet(context0.getResources());
    }

    public static boolean isTablet(Resources resources0) {
        boolean z = false;
        if(resources0 == null) {
            return false;
        }
        if(DeviceProperties.zzb == null) {
            if((resources0.getConfiguration().screenLayout & 15) > 3) {
                z = true;
            }
            else if(DeviceProperties.zzc(resources0)) {
                z = true;
            }
            DeviceProperties.zzb = Boolean.valueOf(z);
        }
        return DeviceProperties.zzb.booleanValue();
    }

    public static boolean isTv(Context context0) {
        boolean z = true;
        PackageManager packageManager0 = context0.getPackageManager();
        if(DeviceProperties.zzk == null) {
            if(!packageManager0.hasSystemFeature("com.google.android.tv") && !packageManager0.hasSystemFeature("android.hardware.type.television") && !packageManager0.hasSystemFeature("android.software.leanback")) {
                z = false;
            }
            DeviceProperties.zzk = Boolean.valueOf(z);
        }
        return DeviceProperties.zzk.booleanValue();
    }

    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }

    public static boolean isWearable(Context context0) {
        return DeviceProperties.zzd(context0.getPackageManager());
    }

    // 去混淆评级： 中等(110)
    public static boolean isWearableWithoutPlayStore(Context context0) {
        return DeviceProperties.isWearable(context0) && false || DeviceProperties.zza(context0);
    }

    public static boolean isXr(Context context0) {
        PackageManager packageManager0 = context0.getPackageManager();
        if(DeviceProperties.zzn == null) {
            DeviceProperties.zzn = Boolean.valueOf(packageManager0.hasSystemFeature("android.software.xr.immersive"));
        }
        return DeviceProperties.zzn.booleanValue();
    }

    public static boolean zza(Context context0) {
        boolean z = false;
        if(DeviceProperties.zzf == null) {
            if(context0.getPackageManager().hasSystemFeature("cn.google")) {
                z = true;
            }
            DeviceProperties.zzf = Boolean.valueOf(z);
        }
        return DeviceProperties.zzf.booleanValue();
    }

    public static boolean zzb(Context context0) {
        boolean z = true;
        if(DeviceProperties.zzh == null) {
            if(!context0.getPackageManager().hasSystemFeature("android.hardware.type.iot") && !context0.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
                z = false;
            }
            DeviceProperties.zzh = Boolean.valueOf(z);
        }
        return DeviceProperties.zzh.booleanValue();
    }

    public static boolean zzc(Resources resources0) {
        boolean z = false;
        if(resources0 == null) {
            return false;
        }
        if(DeviceProperties.zzd == null) {
            Configuration configuration0 = resources0.getConfiguration();
            if((configuration0.screenLayout & 15) <= 3 && configuration0.smallestScreenWidthDp >= 600) {
                z = true;
            }
            DeviceProperties.zzd = Boolean.valueOf(z);
        }
        return DeviceProperties.zzd.booleanValue();
    }

    public static boolean zzd(PackageManager packageManager0) {
        boolean z = false;
        if(DeviceProperties.zze == null) {
            if(packageManager0.hasSystemFeature("android.hardware.type.watch")) {
                z = true;
            }
            DeviceProperties.zze = Boolean.valueOf(z);
        }
        return DeviceProperties.zze.booleanValue();
    }
}

