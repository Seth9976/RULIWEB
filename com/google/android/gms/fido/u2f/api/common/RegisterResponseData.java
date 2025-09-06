package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class RegisterResponseData extends ResponseData {
    public static final Parcelable.Creator CREATOR;
    private final byte[] zza;
    private final ProtocolVersion zzb;
    private final String zzc;

    static {
        RegisterResponseData.CREATOR = new zzi();
    }

    public RegisterResponseData(byte[] arr_b) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = ProtocolVersion.V1;
        this.zzc = null;
    }

    public RegisterResponseData(byte[] arr_b, ProtocolVersion protocolVersion0, String s) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = (ProtocolVersion)Preconditions.checkNotNull(protocolVersion0);
        boolean z = true;
        Preconditions.checkArgument(protocolVersion0 != ProtocolVersion.UNKNOWN);
        if(protocolVersion0 == ProtocolVersion.V1) {
            if(s != null) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.zzc = null;
            return;
        }
        this.zzc = (String)Preconditions.checkNotNull(s);
    }

    RegisterResponseData(byte[] arr_b, String s, String s1) {
        this.zza = arr_b;
        try {
            this.zzb = ProtocolVersion.fromString(s);
            this.zzc = s1;
        }
        catch(UnsupportedProtocolException protocolVersion$UnsupportedProtocolException0) {
            throw new IllegalArgumentException(protocolVersion$UnsupportedProtocolException0);
        }
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof RegisterResponseData ? Objects.equal(this.zzb, ((RegisterResponseData)object0).zzb) && Arrays.equals(this.zza, ((RegisterResponseData)object0).zza) && Objects.equal(this.zzc, ((RegisterResponseData)object0).zzc) : false;
    }

    public String getClientDataString() {
        return this.zzc;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzb;
    }

    public byte[] getRegisterData() {
        return this.zza;
    }

    public int getVersionCode() {
        switch(this.zzb.ordinal()) {
            case 1: 
            case 2: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }

    @Override
    public int hashCode() {
        Integer integer0 = Arrays.hashCode(this.zza);
        return Objects.hashCode(new Object[]{this.zzb, integer0, this.zzc});
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.ResponseData
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("registrationData", Base64.encodeToString(this.zza, 11));
            jSONObject0.put("version", this.zzb.toString());
            String s = this.zzc;
            if(s != null) {
                jSONObject0.put("clientData", Base64.encodeToString(s.getBytes(), 11));
            }
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override
    public String toString() {
        zzaj zzaj0 = zzak.zza(this);
        zzaj0.zzb("protocolVersion", this.zzb);
        zzaj0.zzb("registerData", zzbf.zzd().zze(this.zza, 0, this.zza.length));
        String s = this.zzc;
        if(s != null) {
            zzaj0.zzb("clientDataString", s);
        }
        return zzaj0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getRegisterData(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzb.toString(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getClientDataString(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

