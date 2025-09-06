package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GetPhoneNumberHintIntentRequest extends AbstractSafeParcelable {
    public static final class Builder {
        private Builder() {
            throw null;
        }

        Builder(zbg zbg0) {
        }

        public GetPhoneNumberHintIntentRequest build() {
            return new GetPhoneNumberHintIntentRequest(0);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final int zba;

    static {
        GetPhoneNumberHintIntentRequest.CREATOR = new zbh();
    }

    GetPhoneNumberHintIntentRequest(int v) {
        this.zba = v;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof GetPhoneNumberHintIntentRequest ? Objects.equal(this.zba, ((GetPhoneNumberHintIntentRequest)object0).zba) : false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zba);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

