package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public class GetSignInIntentRequest extends AbstractSafeParcelable {
    public static final class Builder {
        private String zba;
        private String zbb;
        private String zbc;
        private String zbd;
        private boolean zbe;
        private int zbf;

        public GetSignInIntentRequest build() {
            return new GetSignInIntentRequest(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf);
        }

        public Builder filterByHostedDomain(String s) {
            this.zbb = s;
            return this;
        }

        public Builder setNonce(String s) {
            this.zbd = s;
            return this;
        }

        @Deprecated
        public Builder setRequestVerifiedPhoneNumber(boolean z) {
            this.zbe = z;
            return this;
        }

        public Builder setServerClientId(String s) {
            Preconditions.checkNotNull(s);
            this.zba = s;
            return this;
        }

        public final Builder zba(String s) {
            this.zbc = s;
            return this;
        }

        public final Builder zbb(int v) {
            this.zbf = v;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final boolean zbe;
    private final int zbf;

    static {
        GetSignInIntentRequest.CREATOR = new zbi();
    }

    GetSignInIntentRequest(String s, String s1, String s2, String s3, boolean z, int v) {
        Preconditions.checkNotNull(s);
        this.zba = s;
        this.zbb = s1;
        this.zbc = s2;
        this.zbd = s3;
        this.zbe = z;
        this.zbf = v;
    }

    public static Builder builder() {
        return new Builder();
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof GetSignInIntentRequest ? Objects.equal(this.zba, ((GetSignInIntentRequest)object0).zba) && Objects.equal(this.zbd, ((GetSignInIntentRequest)object0).zbd) && Objects.equal(this.zbb, ((GetSignInIntentRequest)object0).zbb) && Objects.equal(Boolean.valueOf(this.zbe), Boolean.valueOf(((GetSignInIntentRequest)object0).zbe)) && this.zbf == ((GetSignInIntentRequest)object0).zbf : false;
    }

    public String getHostedDomainFilter() {
        return this.zbb;
    }

    public String getNonce() {
        return this.zbd;
    }

    public String getServerClientId() {
        return this.zba;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbd, Boolean.valueOf(this.zbe), this.zbf});
    }

    @Deprecated
    public boolean requestVerifiedPhoneNumber() {
        return this.zbe;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel0, 2, this.getHostedDomainFilter(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.zbc, false);
        SafeParcelWriter.writeString(parcel0, 4, this.getNonce(), false);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.requestVerifiedPhoneNumber());
        SafeParcelWriter.writeInt(parcel0, 6, this.zbf);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static Builder zba(GetSignInIntentRequest getSignInIntentRequest0) {
        Preconditions.checkNotNull(getSignInIntentRequest0);
        Builder getSignInIntentRequest$Builder0 = GetSignInIntentRequest.builder();
        getSignInIntentRequest$Builder0.setServerClientId(getSignInIntentRequest0.getServerClientId());
        getSignInIntentRequest$Builder0.setNonce(getSignInIntentRequest0.getNonce());
        getSignInIntentRequest$Builder0.filterByHostedDomain(getSignInIntentRequest0.getHostedDomainFilter());
        getSignInIntentRequest$Builder0.setRequestVerifiedPhoneNumber(getSignInIntentRequest0.zbe);
        getSignInIntentRequest$Builder0.zbb(getSignInIntentRequest0.zbf);
        String s = getSignInIntentRequest0.zbc;
        if(s != null) {
            getSignInIntentRequest$Builder0.zba(s);
        }
        return getSignInIntentRequest$Builder0;
    }
}

