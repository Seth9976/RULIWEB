package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ChannelIdValue extends AbstractSafeParcelable {
    public static enum ChannelIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);

        public static final Parcelable.Creator CREATOR;
        private final int zzb;

        static {
            ChannelIdValueType.CREATOR = new zza();
        }

        private ChannelIdValueType(int v1) {
            this.zzb = v1;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.zzb);
        }

        static int zza(ChannelIdValueType channelIdValue$ChannelIdValueType0) {
            return channelIdValue$ChannelIdValueType0.zzb;
        }
    }

    public static class UnsupportedChannelIdValueTypeException extends Exception {
        public UnsupportedChannelIdValueTypeException(int v) {
            super(String.format("ChannelIdValueType %s not supported", v));
        }
    }

    public static final ChannelIdValue ABSENT;
    public static final Parcelable.Creator CREATOR;
    public static final ChannelIdValue UNAVAILABLE;
    public static final ChannelIdValue UNUSED;
    private final ChannelIdValueType zza;
    private final String zzb;
    private final String zzc;

    static {
        ChannelIdValue.CREATOR = new zzb();
        ChannelIdValue.ABSENT = new ChannelIdValue();
        ChannelIdValue.UNAVAILABLE = new ChannelIdValue("unavailable");
        ChannelIdValue.UNUSED = new ChannelIdValue("unused");
    }

    private ChannelIdValue() {
        this.zza = ChannelIdValueType.ABSENT;
        this.zzc = null;
        this.zzb = null;
    }

    ChannelIdValue(int v, String s, String s1) {
        try {
            this.zza = ChannelIdValue.toChannelIdValueType(v);
            this.zzb = s;
            this.zzc = s1;
        }
        catch(UnsupportedChannelIdValueTypeException channelIdValue$UnsupportedChannelIdValueTypeException0) {
            throw new IllegalArgumentException(channelIdValue$UnsupportedChannelIdValueTypeException0);
        }
    }

    private ChannelIdValue(String s) {
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zza = ChannelIdValueType.STRING;
        this.zzc = null;
    }

    public ChannelIdValue(JSONObject jSONObject0) {
        this.zzc = (String)Preconditions.checkNotNull(jSONObject0.toString());
        this.zza = ChannelIdValueType.OBJECT;
        this.zzb = null;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ChannelIdValue)) {
            return false;
        }
        if(!this.zza.equals(((ChannelIdValue)object0).zza)) {
            return false;
        }
        int v = this.zza.ordinal();
        switch(v) {
            case 0: {
                return true;
            }
            case 1: {
                return this.zzb.equals(((ChannelIdValue)object0).zzb);
            }
            default: {
                return v == 2 ? this.zzc.equals(((ChannelIdValue)object0).zzc) : false;
            }
        }
    }

    public JSONObject getObjectValue() {
        if(this.zzc == null) {
            return null;
        }
        try {
            return new JSONObject(this.zzc);
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    public String getObjectValueAsString() {
        return this.zzc;
    }

    public String getStringValue() {
        return this.zzb;
    }

    public ChannelIdValueType getType() {
        return this.zza;
    }

    public int getTypeAsInt() {
        return ChannelIdValueType.zza(this.zza);
    }

    @Override
    public int hashCode() {
        int v = this.zza.hashCode();
        switch(this.zza.ordinal()) {
            case 1: {
                return (v + 0x1F) * 0x1F + this.zzb.hashCode();
            }
            case 2: {
                return (v + 0x1F) * 0x1F + this.zzc.hashCode();
            }
            default: {
                return v + 0x1F;
            }
        }
    }

    public static ChannelIdValueType toChannelIdValueType(int v) throws UnsupportedChannelIdValueTypeException {
        ChannelIdValueType[] arr_channelIdValue$ChannelIdValueType = ChannelIdValueType.values();
        for(int v1 = 0; v1 < arr_channelIdValue$ChannelIdValueType.length; ++v1) {
            ChannelIdValueType channelIdValue$ChannelIdValueType0 = arr_channelIdValue$ChannelIdValueType[v1];
            if(v == ChannelIdValueType.zza(channelIdValue$ChannelIdValueType0)) {
                return channelIdValue$ChannelIdValueType0;
            }
        }
        throw new UnsupportedChannelIdValueTypeException(v);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 2, this.getTypeAsInt());
        SafeParcelWriter.writeString(parcel0, 3, this.getStringValue(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getObjectValueAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

