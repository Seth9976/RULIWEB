package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public class SavePasswordRequest extends AbstractSafeParcelable {
    @Deprecated
    public static final class Builder {
        private SignInPassword zba;
        private String zbb;
        private int zbc;

        public SavePasswordRequest build() {
            return new SavePasswordRequest(this.zba, this.zbb, this.zbc);
        }

        public Builder setSignInPassword(SignInPassword signInPassword0) {
            this.zba = signInPassword0;
            return this;
        }

        public final Builder zba(String s) {
            this.zbb = s;
            return this;
        }

        public final Builder zbb(int v) {
            this.zbc = v;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final SignInPassword zba;
    private final String zbb;
    private final int zbc;

    static {
        SavePasswordRequest.CREATOR = new zbp();
    }

    SavePasswordRequest(SignInPassword signInPassword0, String s, int v) {
        this.zba = (SignInPassword)Preconditions.checkNotNull(signInPassword0);
        this.zbb = s;
        this.zbc = v;
    }

    public static Builder builder() {
        return new Builder();
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SavePasswordRequest ? Objects.equal(this.zba, ((SavePasswordRequest)object0).zba) && Objects.equal(this.zbb, ((SavePasswordRequest)object0).zbb) && this.zbc == ((SavePasswordRequest)object0).zbc : false;
    }

    public SignInPassword getSignInPassword() {
        return this.zba;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getSignInPassword(), v, false);
        SafeParcelWriter.writeString(parcel0, 2, this.zbb, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zbc);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static Builder zba(SavePasswordRequest savePasswordRequest0) {
        Preconditions.checkNotNull(savePasswordRequest0);
        Builder savePasswordRequest$Builder0 = SavePasswordRequest.builder();
        savePasswordRequest$Builder0.setSignInPassword(savePasswordRequest0.getSignInPassword());
        savePasswordRequest$Builder0.zbb(savePasswordRequest0.zbc);
        String s = savePasswordRequest0.zbb;
        if(s != null) {
            savePasswordRequest$Builder0.zba(s);
        }
        return savePasswordRequest$Builder0;
    }
}

