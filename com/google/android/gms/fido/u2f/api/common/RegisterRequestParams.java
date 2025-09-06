package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Deprecated
public class RegisterRequestParams extends RequestParams {
    public static final class Builder {
        Integer zza;
        Double zzb;
        Uri zzc;
        List zzd;
        List zze;
        ChannelIdValue zzf;
        String zzg;

        public RegisterRequestParams build() {
            return new RegisterRequestParams(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public Builder setAppId(Uri uri0) {
            this.zzc = uri0;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue0) {
            this.zzf = channelIdValue0;
            return this;
        }

        public Builder setDisplayHint(String s) {
            this.zzg = s;
            return this;
        }

        public Builder setRegisterRequests(List list0) {
            this.zzd = list0;
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
    private final List zzd;
    private final List zze;
    private final ChannelIdValue zzf;
    private final String zzg;
    private Set zzh;

    static {
        RegisterRequestParams.CREATOR = new zzh();
    }

    RegisterRequestParams(Integer integer0, Double double0, Uri uri0, List list0, List list1, ChannelIdValue channelIdValue0, String s) {
        this.zza = integer0;
        this.zzb = double0;
        this.zzc = uri0;
        boolean z = false;
        Preconditions.checkArgument(list0 != null && !list0.isEmpty(), "empty list of register requests is provided");
        this.zzd = list0;
        this.zze = list1;
        this.zzf = channelIdValue0;
        HashSet hashSet0 = new HashSet();
        if(uri0 != null) {
            hashSet0.add(uri0);
        }
        for(Object object0: list0) {
            RegisterRequest registerRequest0 = (RegisterRequest)object0;
            Preconditions.checkArgument(uri0 != null || registerRequest0.getAppId() != null, "register request has null appId and no request appId is provided");
            if(registerRequest0.getAppId() != null) {
                hashSet0.add(Uri.parse(registerRequest0.getAppId()));
            }
        }
        for(Object object1: list1) {
            RegisteredKey registeredKey0 = (RegisteredKey)object1;
            Preconditions.checkArgument(uri0 != null || registeredKey0.getAppId() != null, "registered key has null appId and no request appId is provided");
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

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof RegisterRequestParams)) {
            return false;
        }
        if(Objects.equal(this.zza, ((RegisterRequestParams)object0).zza) && Objects.equal(this.zzb, ((RegisterRequestParams)object0).zzb) && Objects.equal(this.zzc, ((RegisterRequestParams)object0).zzc) && Objects.equal(this.zzd, ((RegisterRequestParams)object0).zzd)) {
            List list0 = this.zze;
            if(list0 == null && ((RegisterRequestParams)object0).zze == null) {
                return Objects.equal(this.zzf, ((RegisterRequestParams)object0).zzf) && Objects.equal(this.zzg, ((RegisterRequestParams)object0).zzg);
            }
            else if(list0 != null) {
                List list1 = ((RegisterRequestParams)object0).zze;
                return list1 == null || !list0.containsAll(list1) || !((RegisterRequestParams)object0).zze.containsAll(this.zze) ? false : Objects.equal(this.zzf, ((RegisterRequestParams)object0).zzf) && Objects.equal(this.zzg, ((RegisterRequestParams)object0).zzg);
            }
        }
        return false;
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

    @Override  // com.google.android.gms.fido.u2f.api.common.RequestParams
    public String getDisplayHint() {
        return this.zzg;
    }

    public List getRegisterRequests() {
        return this.zzd;
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
        return Objects.hashCode(new Object[]{this.zza, this.zzc, this.zzb, this.zzd, this.zze, this.zzf, this.zzg});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeIntegerObject(parcel0, 2, this.getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel0, 3, this.getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.getAppId(), v, false);
        SafeParcelWriter.writeTypedList(parcel0, 5, this.getRegisterRequests(), false);
        SafeParcelWriter.writeTypedList(parcel0, 6, this.getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.getChannelIdValue(), v, false);
        SafeParcelWriter.writeString(parcel0, 8, this.getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

