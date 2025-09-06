package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class zziq {
    public static Object zza(Object object0) {
        Object object1;
        ObjectOutputStream objectOutputStream0;
        ByteArrayOutputStream byteArrayOutputStream0;
        if(object0 != null) {
            try {
                byteArrayOutputStream0 = new ByteArrayOutputStream();
                objectOutputStream0 = new ObjectOutputStream(byteArrayOutputStream0);
                goto label_9;
            }
            catch(Throwable throwable0) {
                try {
                    Throwable throwable1 = throwable0;
                    ObjectInputStream objectInputStream0 = null;
                    objectOutputStream0 = null;
                    goto label_20;
                    try {
                    label_9:
                        objectOutputStream0.writeObject(object0);
                        objectOutputStream0.flush();
                        objectInputStream0 = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream0.toByteArray()));
                    }
                    catch(Throwable throwable2) {
                        throwable1 = throwable2;
                        objectInputStream0 = null;
                        goto label_20;
                    }
                    try {
                        object1 = objectInputStream0.readObject();
                        goto label_25;
                    }
                    catch(Throwable throwable1) {
                    }
                label_20:
                    if(objectOutputStream0 != null) {
                        objectOutputStream0.close();
                    }
                    if(objectInputStream0 != null) {
                        objectInputStream0.close();
                    }
                    throw throwable1;
                label_25:
                    objectOutputStream0.close();
                    objectInputStream0.close();
                    return object1;
                }
                catch(IOException | ClassNotFoundException unused_ex) {
                }
            }
        }
        return null;
    }

    public static String zzb(String s, String[] arr_s, String[] arr_s1) {
        Preconditions.checkNotNull(arr_s);
        Preconditions.checkNotNull(arr_s1);
        int v = Math.min(arr_s.length, arr_s1.length);
        for(int v1 = 0; v1 < v; ++v1) {
            String s1 = arr_s[v1];
            if(s == null && s1 == null || s != null && s.equals(s1)) {
                return arr_s1[v1];
            }
        }
        return null;
    }

    public static String zzc(Context context0, String s, String s1) {
        Preconditions.checkNotNull(context0);
        Resources resources0 = context0.getResources();
        if(TextUtils.isEmpty(s1)) {
            s1 = zzfv.zza(context0);
        }
        return zzfv.zzb("google_app_id", resources0, s1);
    }
}

