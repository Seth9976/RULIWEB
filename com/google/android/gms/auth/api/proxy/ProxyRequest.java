package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Patterns;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProxyRequest extends AbstractSafeParcelable {
    public static class Builder {
        private final String zza;
        private int zzb;
        private long zzc;
        private byte[] zzd;
        private final Bundle zze;

        public Builder(String s) {
            this.zzb = ProxyRequest.HTTP_METHOD_GET;
            this.zzc = 3000L;
            this.zzd = new byte[0];
            this.zze = new Bundle();
            Preconditions.checkNotEmpty(s);
            if(!Patterns.WEB_URL.matcher(s).matches()) {
                throw new IllegalArgumentException("The supplied url [ " + s + "] is not match Patterns.WEB_URL!");
            }
            this.zza = s;
        }

        public ProxyRequest build() {
            if(this.zzd == null) {
                this.zzd = new byte[0];
            }
            return new ProxyRequest(2, this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder putHeader(String s, String s1) {
            Preconditions.checkNotEmpty(s, "Header name cannot be null or empty!");
            Bundle bundle0 = this.zze;
            if(s1 == null) {
                s1 = "";
            }
            bundle0.putString(s, s1);
            return this;
        }

        public Builder setBody(byte[] arr_b) {
            this.zzd = arr_b;
            return this;
        }

        public Builder setHttpMethod(int v) {
            Preconditions.checkArgument(v >= 0 && v <= ProxyRequest.LAST_CODE, "Unrecognized http method code.");
            this.zzb = v;
            return this;
        }

        public Builder setTimeoutMillis(long v) {
            Preconditions.checkArgument(v >= 0L, "The specified timeout must be non-negative.");
            this.zzc = v;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR = null;
    public static final int HTTP_METHOD_DELETE = 0;
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_HEAD = 0;
    public static final int HTTP_METHOD_OPTIONS = 0;
    public static final int HTTP_METHOD_PATCH = 0;
    public static final int HTTP_METHOD_POST = 0;
    public static final int HTTP_METHOD_PUT = 0;
    public static final int HTTP_METHOD_TRACE = 0;
    public static final int LAST_CODE = 0;
    public static final int VERSION_CODE = 2;
    public final byte[] body;
    public final int httpMethod;
    public final long timeoutMillis;
    public final String url;
    final int zza;
    final Bundle zzb;

    static {
        ProxyRequest.CREATOR = new zza();
        ProxyRequest.HTTP_METHOD_GET = 0;
        ProxyRequest.HTTP_METHOD_POST = 1;
        ProxyRequest.HTTP_METHOD_PUT = 2;
        ProxyRequest.HTTP_METHOD_DELETE = 3;
        ProxyRequest.HTTP_METHOD_HEAD = 4;
        ProxyRequest.HTTP_METHOD_OPTIONS = 5;
        ProxyRequest.HTTP_METHOD_TRACE = 6;
        ProxyRequest.HTTP_METHOD_PATCH = 7;
        ProxyRequest.LAST_CODE = 7;
    }

    ProxyRequest(int v, String s, int v1, long v2, byte[] arr_b, Bundle bundle0) {
        this.zza = v;
        this.url = s;
        this.httpMethod = v1;
        this.timeoutMillis = v2;
        this.body = arr_b;
        this.zzb = bundle0;
    }

    public Map getHeaderMap() {
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(this.zzb.size());
        for(Object object0: this.zzb.keySet()) {
            String s = this.zzb.getString(((String)object0));
            if(s == null) {
                s = "";
            }
            linkedHashMap0.put(((String)object0), s);
        }
        return Collections.unmodifiableMap(linkedHashMap0);
    }

    @Override
    public String toString() {
        return "ProxyRequest[ url: " + this.url + ", method: " + this.httpMethod + " ]";
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.url, false);
        SafeParcelWriter.writeInt(parcel0, 2, this.httpMethod);
        SafeParcelWriter.writeLong(parcel0, 3, this.timeoutMillis);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.body, false);
        SafeParcelWriter.writeBundle(parcel0, 5, this.zzb, false);
        SafeParcelWriter.writeInt(parcel0, 1000, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

