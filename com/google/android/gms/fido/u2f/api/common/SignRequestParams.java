package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
public class SignRequestParams extends RequestParams {
    public static final class Builder {
        Integer zza;
        Double zzb;
        Uri zzc;
        byte[] zzd;
        List zze;
        ChannelIdValue zzf;
        String zzg;

        public SignRequestParams build() {
            return new SignRequestParams(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public Builder setAppId(Uri uri0) {
            this.zzc = uri0;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue0) {
            this.zzf = channelIdValue0;
            return this;
        }

        public Builder setDefaultSignChallenge(byte[] arr_b) {
            this.zzd = arr_b;
            return this;
        }

        public Builder setDisplayHint(String s) {
            this.zzg = s;
            return this;
        }

        public Builder setRegisteredKeys(List list0) {
            this.zze = list0;
            return this;
        }

        public Builder setRequestId(Integer integer0) {
            this.zza = integer0;
            return this;
        }

        public Builder setTimeoutSeconds(Double double0) {
            this.zzb = double0;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR = null;
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Integer zza;
    private final Double zzb;
    private final Uri zzc;
    private final byte[] zzd;
    private final List zze;
    private final ChannelIdValue zzf;
    private final String zzg;
    private final Set zzh;

    static {
        SignRequestParams.CREATOR = new zzk();
    }

    SignRequestParams(Integer integer0, Double double0, Uri uri0, byte[] arr_b, List list0, ChannelIdValue channelIdValue0, String s) {
        this.zza = integer0;
        this.zzb = double0;
        this.zzc = uri0;
        this.zzd = arr_b;
        boolean z = false;
        Preconditions.checkArgument(list0 != null && !list0.isEmpty(), "registeredKeys must not be null or empty");
        this.zze = list0;
        this.zzf = channelIdValue0;
        HashSet hashSet0 = new HashSet();
        if(uri0 != null) {
            hashSet0.add(uri0);
        }
        for(Object object0: list0) {
            RegisteredKey registeredKey0 = (RegisteredKey)object0;
            Preconditions.checkArgument(registeredKey0.getAppId() != null || uri0 != null, "registered key has null appId and no request appId is provided");
            Preconditions.checkArgument(true, "register request has null challenge and no default challenge isprovided");
            if(registeredKey0.getAppId() != null) {
                hashSet0.add(Uri.parse(registeredKey0.getAppId()));
            }
        }
        this.zzh = hashSet0;
        if(s == null || s.length() <= 80) {
            z = true;
        }
        Preconditions.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.zzg = s;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof SignRequestParams ? Objects.equal(this.zza, ((SignRequestParams)object0).zza) && Objects.equal(this.zzb, ((SignRequestParams)object0).zzb) && Objects.equal(this.zzc, ((SignRequestParams)object0).zzc) && Arrays.equals(this.zzd, ((SignRequestParams)object0).zzd) && this.zze.containsAll(((SignRequestParams)object0).zze) && ((SignRequestParams)object0).zze.containsAll(this.zze) && Objects.equal(this.zzf, ((SignRequestParams)object0).zzf) && Objects.equal(this.zzg, ((SignRequestParams)object0).zzg) : false;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Set getAllAppIds() {
        return this.zzh;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Uri getAppId() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public ChannelIdValue getChannelIdValue() {
        return this.zzf;
    }

    public byte[] getDefaultSignChallenge() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public String getDisplayHint() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public List getRegisteredKeys() {
        return this.zze;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Integer getRequestId() {
        return this.zza;
    }

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        Integer integer0 = Arrays.hashCode(this.zzd);
        return Objects.hashCode(new Object[]{this.zza, this.zzc, this.zzb, this.zze, this.zzf, this.zzg, integer0});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeIntegerObject(parcel0, 2, this.getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel0, 3, this.getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.getAppId(), v, false);
        SafeParcelWriter.writeByteArray(parcel0, 5, this.getDefaultSignChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel0, 6, this.getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.getChannelIdValue(), v, false);
        SafeParcelWriter.writeString(parcel0, 8, this.getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

