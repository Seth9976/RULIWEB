package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

final class zzin implements Runnable {
    final zzio zza;
    private final URL zzb;
    private final String zzc;
    private final zzgb zzd;

    public zzin(zzio zzio0, String s, URL uRL0, byte[] arr_b, Map map0, zzgb zzgb0) {
        this.zza = zzio0;
        super();
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(uRL0);
        Preconditions.checkNotNull(zzgb0);
        this.zzb = uRL0;
        this.zzd = zzgb0;
        this.zzc = s;
    }

    @Override
    public final void run() {
        byte[] arr_b1;
        InputStream inputStream0;
        IOException iOException1;
        int v;
        Map map0;
        HttpURLConnection httpURLConnection0;
        this.zza.zzaz();
        try {
            URLConnection uRLConnection0 = this.zzb.openConnection();
            if(!(uRLConnection0 instanceof HttpURLConnection)) {
                throw new IOException("Failed to obtain HTTP connection");
            }
            httpURLConnection0 = (HttpURLConnection)uRLConnection0;
            httpURLConnection0.setDefaultUseCaches(false);
            httpURLConnection0.setConnectTimeout(60000);
            httpURLConnection0.setReadTimeout(61000);
            httpURLConnection0.setInstanceFollowRedirects(false);
            httpURLConnection0.setDoInput(true);
        }
        catch(IOException iOException0) {
            httpURLConnection0 = null;
            map0 = null;
            iOException1 = iOException0;
            v = 0;
            goto label_50;
        }
        catch(Throwable throwable0) {
            httpURLConnection0 = null;
            map0 = null;
            goto label_28;
        }
        try {
            v = httpURLConnection0.getResponseCode();
            goto label_31;
        }
        catch(IOException iOException0) {
            map0 = null;
            iOException1 = iOException0;
            v = 0;
            goto label_50;
        }
        catch(Throwable throwable0) {
            map0 = null;
        }
    label_28:
        Throwable throwable1 = throwable0;
        v = 0;
        goto label_55;
        try {
        label_31:
            map0 = null;
            map0 = httpURLConnection0.getHeaderFields();
            try {
                ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                inputStream0 = null;
                inputStream0 = httpURLConnection0.getInputStream();
                byte[] arr_b = new byte[0x400];
                int v1;
                while((v1 = inputStream0.read(arr_b)) > 0) {
                    byteArrayOutputStream0.write(arr_b, 0, v1);
                }
                arr_b1 = byteArrayOutputStream0.toByteArray();
            }
            catch(Throwable throwable2) {
                if(inputStream0 != null) {
                    inputStream0.close();
                }
                throw throwable2;
            }
            inputStream0.close();
            goto label_59;
        }
        catch(IOException iOException1) {
        label_50:
            if(httpURLConnection0 != null) {
                httpURLConnection0.disconnect();
            }
            this.zzb(v, iOException1, null, map0);
            return;
        }
        catch(Throwable throwable1) {
        }
    label_55:
        if(httpURLConnection0 != null) {
            httpURLConnection0.disconnect();
        }
        this.zzb(v, null, null, map0);
        throw throwable1;
    label_59:
        httpURLConnection0.disconnect();
        this.zzb(v, null, arr_b1, map0);
    }

    // 检测为 Lambda 实现
    final void zza(int v, Exception exception0, byte[] arr_b, Map map0) [...]

    private final void zzb(int v, Exception exception0, byte[] arr_b, Map map0) {
        this.zza.zzt.zzaB().zzp(() -> this.zzd.zza.zzC(this.zzc, v, exception0, arr_b, map0));
    }
}

