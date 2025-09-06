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
public class SignResponseData extends ResponseData {
    public static final Parcelable.Creator CREATOR = null;
    public static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    public static final String JSON_RESPONSE_DATA_KEY_HANDLE = "keyHandle";
    public static final String JSON_RESPONSE_DATA_SIGNATURE_DATA = "signatureData";
    private final byte[] zza;
    private final String zzb;
    private final byte[] zzc;
    private final byte[] zzd;

    static {
        SignResponseData.CREATOR = new zzl();
    }

    @Deprecated
    public SignResponseData(byte[] arr_b, String s, byte[] arr_b1) {
        this(arr_b, s, arr_b1, new byte[0]);
    }

    public SignResponseData(byte[] arr_b, String s, byte[] arr_b1, byte[] arr_b2) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b1);
        this.zzd = (byte[])Preconditions.checkNotNull(arr_b2);
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SignResponseData ? Arrays.equals(this.zza, ((SignResponseData)object0).zza) && Objects.equal(this.zzb, ((SignResponseData)object0).zzb) && Arrays.equals(this.zzc, ((SignResponseData)object0).zzc) && Arrays.equals(this.zzd, ((SignResponseData)object0).zzd) : false;
    }

    public String getClientDataString() {
        return this.zzb;
    }

    public byte[] getKeyHandle() {
        return this.zza;
    }

    public byte[] getSignatureData() {
        return this.zzc;
    }

    @Override
    public int hashCode() {
        Integer integer0 = Arrays.hashCode(this.zza);
        Integer integer1 = Arrays.hashCode(this.zzc);
        Integer integer2 = Arrays.hashCode(this.zzd);
        return Objects.hashCode(new Object[]{integer0, this.zzb, integer1, integer2});
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.ResponseData
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject0 = new JSONObject();
            jSONObject0.put("keyHandle", Base64.encodeToString(this.zza, 11));
            jSONObject0.put("clientData", Base64.encodeToString(this.zzb.getBytes(), 11));
            jSONObject0.put("signatureData", Base64.encodeToString(this.zzc, 11));
            return jSONObject0;
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    @Override
    public String toString() {
        zzaj zzaj0 = zzak.zza(this);
        zzaj0.zzb("keyHandle", zzbf.zzd().zze(this.zza, 0, this.zza.length));
        zzaj0.zzb("clientDataString", this.zzb);
        zzaj0.zzb("signatureData", zzbf.zzd().zze(this.zzc, 0, this.zzc.length));
        zzaj0.zzb("application", zzbf.zzd().zze(this.zzd, 0, this.zzd.length));
        return zzaj0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getKeyHandle(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.getClientDataString(), false);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.getSignatureData(), false);
        SafeParcelWriter.writeByteArray(parcel0, 5, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

