package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;
import java.util.Map;

final class zzey implements Runnable {
    final zzez zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzev zzd;
    private final String zze;
    private final Map zzf;

    public zzey(zzez zzez0, String s, URL uRL0, byte[] arr_b, Map map0, zzev zzev0) {
        this.zza = zzez0;
        super();
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(uRL0);
        Preconditions.checkNotNull(zzev0);
        this.zzb = uRL0;
        this.zzc = arr_b;
        this.zzd = zzev0;
        this.zze = s;
        this.zzf = map0;
    }

    @Override
    public final void run() {
        byte[] arr_b2;
        InputStream inputStream0;
        Map map3;
        int v1;
        int v;
        Map map1;
        Throwable throwable1;
        Map map0;
        IOException iOException1;
        HttpURLConnection httpURLConnection0;
        this.zza.zzaz();
        OutputStream outputStream0 = null;
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
            iOException1 = iOException0;
            httpURLConnection0 = null;
            map0 = null;
            v1 = 0;
            goto label_93;
        }
        catch(Throwable throwable0) {
            throwable1 = throwable0;
            httpURLConnection0 = null;
            map1 = null;
            goto label_60;
        }
        try {
            Map map2 = this.zzf;
            if(map2 != null) {
                for(Object object0: map2.entrySet()) {
                    httpURLConnection0.addRequestProperty(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
                }
            }
            if(this.zzc != null) {
                byte[] arr_b = this.zza.zzf.zzu().zzz(this.zzc);
                this.zza.zzt.zzaA().zzj().zzb("Uploading data. size", ((int)arr_b.length));
                httpURLConnection0.setDoOutput(true);
                httpURLConnection0.addRequestProperty("Content-Encoding", "gzip");
                httpURLConnection0.setFixedLengthStreamingMode(arr_b.length);
                httpURLConnection0.connect();
                OutputStream outputStream1 = httpURLConnection0.getOutputStream();
                try {
                    outputStream1.write(arr_b);
                    outputStream1.close();
                }
                catch(IOException iOException3) {
                    iOException1 = iOException3;
                    map0 = null;
                    outputStream0 = outputStream1;
                    v1 = 0;
                    goto label_93;
                }
                catch(Throwable throwable3) {
                    throwable1 = throwable3;
                    map1 = null;
                    outputStream0 = outputStream1;
                    goto label_60;
                }
            }
            v = httpURLConnection0.getResponseCode();
            goto label_62;
        }
        catch(IOException iOException2) {
            iOException1 = iOException2;
            map0 = null;
            v1 = 0;
            goto label_93;
        }
        catch(Throwable throwable2) {
            throwable1 = throwable2;
            map1 = null;
        }
    label_60:
        int v2 = 0;
        goto label_106;
        try {
        label_62:
            map3 = httpURLConnection0.getHeaderFields();
        }
        catch(IOException iOException4) {
            map0 = null;
            v1 = v;
            goto label_92;
        }
        catch(Throwable throwable4) {
            throwable1 = throwable4;
            map1 = null;
            v2 = v;
            goto label_106;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            inputStream0 = null;
            inputStream0 = httpURLConnection0.getInputStream();
            byte[] arr_b1 = new byte[0x400];
            int v3;
            while((v3 = inputStream0.read(arr_b1)) > 0) {
                byteArrayOutputStream0.write(arr_b1, 0, v3);
            }
            arr_b2 = byteArrayOutputStream0.toByteArray();
            goto label_87;
        }
        catch(Throwable throwable5) {
            try {
                if(inputStream0 != null) {
                    inputStream0.close();
                }
                throw throwable5;
            label_87:
                inputStream0.close();
                goto label_115;
            }
            catch(IOException iOException4) {
            }
            catch(Throwable throwable6) {
                goto label_103;
            }
        }
        v1 = v;
        map0 = map3;
    label_92:
        iOException1 = iOException4;
    label_93:
        if(outputStream0 != null) {
            try {
                outputStream0.close();
            }
            catch(IOException iOException5) {
                this.zza.zzt.zzaA().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzet.zzn(this.zze), iOException5);
            }
        }
        if(httpURLConnection0 != null) {
            httpURLConnection0.disconnect();
        }
        this.zza.zzt.zzaB().zzp(new zzex(this.zze, this.zzd, v1, iOException1, null, map0, null));
        return;
    label_103:
        throwable1 = throwable6;
        v2 = v;
        map1 = map3;
    label_106:
        if(outputStream0 != null) {
            try {
                outputStream0.close();
            }
            catch(IOException iOException6) {
                this.zza.zzt.zzaA().zzd().zzc("Error closing HTTP compressed POST connection output stream. appId", zzet.zzn(this.zze), iOException6);
            }
        }
        if(httpURLConnection0 != null) {
            httpURLConnection0.disconnect();
        }
        this.zza.zzt.zzaB().zzp(new zzex(this.zze, this.zzd, v2, null, null, map1, null));
        throw throwable1;
    label_115:
        httpURLConnection0.disconnect();
        this.zza.zzt.zzaB().zzp(new zzex(this.zze, this.zzd, v, null, arr_b2, map3, null));
    }
}

