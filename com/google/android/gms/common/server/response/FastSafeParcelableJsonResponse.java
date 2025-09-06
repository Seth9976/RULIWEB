package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class FastSafeParcelableJsonResponse extends FastJsonResponse implements SafeParcelable {
    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(this == object0) {
            return true;
        }
        if(!this.getClass().isInstance(object0)) {
            return false;
        }
        for(Object object1: this.getFieldMappings().values()) {
            Field fastJsonResponse$Field0 = (Field)object1;
            if(this.isFieldSet(fastJsonResponse$Field0)) {
                if(((FastJsonResponse)object0).isFieldSet(fastJsonResponse$Field0) && Objects.equal(this.getFieldValue(fastJsonResponse$Field0), ((FastJsonResponse)object0).getFieldValue(fastJsonResponse$Field0))) {
                    continue;
                }
                return false;
            }
            if(((FastJsonResponse)object0).isFieldSet(fastJsonResponse$Field0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public Object getValueObject(String s) {
        return null;
    }

    @Override
    public int hashCode() {
        int v = 0;
        for(Object object0: this.getFieldMappings().values()) {
            Field fastJsonResponse$Field0 = (Field)object0;
            if(this.isFieldSet(fastJsonResponse$Field0)) {
                v = v * 0x1F + Preconditions.checkNotNull(this.getFieldValue(fastJsonResponse$Field0)).hashCode();
            }
        }
        return v;
    }

    @Override  // com.google.android.gms.common.server.response.FastJsonResponse
    public boolean isPrimitiveFieldSet(String s) {
        return false;
    }

    public byte[] toByteArray() {
        Parcel parcel0 = Parcel.obtain();
        this.writeToParcel(parcel0, 0);
        byte[] arr_b = parcel0.marshall();
        parcel0.recycle();
        return arr_b;
    }
}

