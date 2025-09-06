package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Deprecated
public final class BeginSignInRequest extends AbstractSafeParcelable {
    @Deprecated
    public static final class Builder {
        private PasswordRequestOptions zba;
        private GoogleIdTokenRequestOptions zbb;
        private PasskeysRequestOptions zbc;
        private PasskeyJsonRequestOptions zbd;
        private String zbe;
        private boolean zbf;
        private int zbg;
        private boolean zbh;

        public Builder() {
            com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder beginSignInRequest$PasswordRequestOptions$Builder0 = PasswordRequestOptions.builder();
            beginSignInRequest$PasswordRequestOptions$Builder0.setSupported(false);
            this.zba = beginSignInRequest$PasswordRequestOptions$Builder0.build();
            com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder beginSignInRequest$GoogleIdTokenRequestOptions$Builder0 = GoogleIdTokenRequestOptions.builder();
            beginSignInRequest$GoogleIdTokenRequestOptions$Builder0.setSupported(false);
            this.zbb = beginSignInRequest$GoogleIdTokenRequestOptions$Builder0.build();
            com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder beginSignInRequest$PasskeysRequestOptions$Builder0 = PasskeysRequestOptions.builder();
            beginSignInRequest$PasskeysRequestOptions$Builder0.setSupported(false);
            this.zbc = beginSignInRequest$PasskeysRequestOptions$Builder0.build();
            com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder beginSignInRequest$PasskeyJsonRequestOptions$Builder0 = PasskeyJsonRequestOptions.builder();
            beginSignInRequest$PasskeyJsonRequestOptions$Builder0.setSupported(false);
            this.zbd = beginSignInRequest$PasskeyJsonRequestOptions$Builder0.build();
        }

        public BeginSignInRequest build() {
            return new BeginSignInRequest(this.zba, this.zbb, this.zbe, this.zbf, this.zbg, this.zbc, this.zbd, this.zbh);
        }

        public Builder setAutoSelectEnabled(boolean z) {
            this.zbf = z;
            return this;
        }

        public Builder setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions beginSignInRequest$GoogleIdTokenRequestOptions0) {
            this.zbb = (GoogleIdTokenRequestOptions)Preconditions.checkNotNull(beginSignInRequest$GoogleIdTokenRequestOptions0);
            return this;
        }

        public Builder setPasskeyJsonSignInRequestOptions(PasskeyJsonRequestOptions beginSignInRequest$PasskeyJsonRequestOptions0) {
            this.zbd = (PasskeyJsonRequestOptions)Preconditions.checkNotNull(beginSignInRequest$PasskeyJsonRequestOptions0);
            return this;
        }

        @Deprecated
        public Builder setPasskeysSignInRequestOptions(PasskeysRequestOptions beginSignInRequest$PasskeysRequestOptions0) {
            this.zbc = (PasskeysRequestOptions)Preconditions.checkNotNull(beginSignInRequest$PasskeysRequestOptions0);
            return this;
        }

        public Builder setPasswordRequestOptions(PasswordRequestOptions beginSignInRequest$PasswordRequestOptions0) {
            this.zba = (PasswordRequestOptions)Preconditions.checkNotNull(beginSignInRequest$PasswordRequestOptions0);
            return this;
        }

        public Builder setPreferImmediatelyAvailableCredentials(boolean z) {
            this.zbh = z;
            return this;
        }

        public final Builder zba(String s) {
            this.zbe = s;
            return this;
        }

        public final Builder zbb(int v) {
            this.zbg = v;
            return this;
        }
    }

    @Deprecated
    public static final class GoogleIdTokenRequestOptions extends AbstractSafeParcelable {
        public static final class com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder {
            private boolean zba;
            private String zbb;
            private String zbc;
            private boolean zbd;
            private String zbe;
            private List zbf;
            private boolean zbg;

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder() {
                this.zba = false;
                this.zbb = null;
                this.zbc = null;
                this.zbd = true;
                this.zbe = null;
                this.zbf = null;
                this.zbg = false;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder associateLinkedAccounts(String s, List list0) {
                this.zbe = (String)Preconditions.checkNotNull(s, "linkedServiceId must be provided if you want to associate linked accounts.");
                this.zbf = list0;
                return this;
            }

            public GoogleIdTokenRequestOptions build() {
                return new GoogleIdTokenRequestOptions(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg);
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder setFilterByAuthorizedAccounts(boolean z) {
                this.zbd = z;
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder setNonce(String s) {
                this.zbc = s;
                return this;
            }

            @Deprecated
            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder setRequestVerifiedPhoneNumber(boolean z) {
                this.zbg = z;
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder setServerClientId(String s) {
                this.zbb = Preconditions.checkNotEmpty(s);
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public static final Parcelable.Creator CREATOR;
        private final boolean zba;
        private final String zbb;
        private final String zbc;
        private final boolean zbd;
        private final String zbe;
        private final List zbf;
        private final boolean zbg;

        static {
            GoogleIdTokenRequestOptions.CREATOR = new zbj();
        }

        GoogleIdTokenRequestOptions(boolean z, String s, String s1, boolean z1, String s2, List list0, boolean z2) {
            Preconditions.checkArgument(!z1 || !z2, "filterByAuthorizedAccounts and requestVerifiedPhoneNumber must not both be true; the Verified Phone Number feature only works in sign-ups.");
            this.zba = z;
            if(z) {
                Preconditions.checkNotNull(s, "serverClientId must be provided if Google ID tokens are requested");
            }
            this.zbb = s;
            this.zbc = s1;
            this.zbd = z1;
            List list1 = null;
            if(list0 != null && !list0.isEmpty()) {
                list1 = new ArrayList(list0);
                Collections.sort(list1);
            }
            this.zbf = list1;
            this.zbe = s2;
            this.zbg = z2;
        }

        public static com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder builder() {
            return new com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.Builder();
        }

        // 去混淆评级： 中等(50)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof GoogleIdTokenRequestOptions ? this.zba == ((GoogleIdTokenRequestOptions)object0).zba && Objects.equal(this.zbb, ((GoogleIdTokenRequestOptions)object0).zbb) && Objects.equal(this.zbc, ((GoogleIdTokenRequestOptions)object0).zbc) && this.zbd == ((GoogleIdTokenRequestOptions)object0).zbd && Objects.equal(this.zbe, ((GoogleIdTokenRequestOptions)object0).zbe) && Objects.equal(this.zbf, ((GoogleIdTokenRequestOptions)object0).zbf) && this.zbg == ((GoogleIdTokenRequestOptions)object0).zbg : false;
        }

        public boolean filterByAuthorizedAccounts() {
            return this.zbd;
        }

        public List getIdTokenDepositionScopes() {
            return this.zbf;
        }

        public String getLinkedServiceId() {
            return this.zbe;
        }

        public String getNonce() {
            return this.zbc;
        }

        public String getServerClientId() {
            return this.zbb;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb, this.zbc, Boolean.valueOf(this.zbd), this.zbe, this.zbf, Boolean.valueOf(this.zbg)});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Deprecated
        public boolean requestVerifiedPhoneNumber() {
            return this.zbg;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
            SafeParcelWriter.writeBoolean(parcel0, 1, this.isSupported());
            SafeParcelWriter.writeString(parcel0, 2, this.getServerClientId(), false);
            SafeParcelWriter.writeString(parcel0, 3, this.getNonce(), false);
            SafeParcelWriter.writeBoolean(parcel0, 4, this.filterByAuthorizedAccounts());
            SafeParcelWriter.writeString(parcel0, 5, this.getLinkedServiceId(), false);
            SafeParcelWriter.writeStringList(parcel0, 6, this.getIdTokenDepositionScopes(), false);
            SafeParcelWriter.writeBoolean(parcel0, 7, this.requestVerifiedPhoneNumber());
            SafeParcelWriter.finishObjectHeader(parcel0, v1);
        }
    }

    @Deprecated
    public static final class PasskeyJsonRequestOptions extends AbstractSafeParcelable {
        public static final class com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder {
            private boolean zba;
            private String zbb;

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder() {
                this.zba = false;
            }

            public PasskeyJsonRequestOptions build() {
                return new PasskeyJsonRequestOptions(this.zba, this.zbb);
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder setRequestJson(String s) {
                this.zbb = s;
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public static final Parcelable.Creator CREATOR;
        private final boolean zba;
        private final String zbb;

        static {
            PasskeyJsonRequestOptions.CREATOR = new zbk();
        }

        PasskeyJsonRequestOptions(boolean z, String s) {
            if(z) {
                Preconditions.checkNotNull(s);
            }
            this.zba = z;
            this.zbb = s;
        }

        public static com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder builder() {
            return new com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder();
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof PasskeyJsonRequestOptions ? this.zba == ((PasskeyJsonRequestOptions)object0).zba && Objects.equal(this.zbb, ((PasskeyJsonRequestOptions)object0).zbb) : false;
        }

        public String getRequestJson() {
            return this.zbb;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba), this.zbb});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
            SafeParcelWriter.writeBoolean(parcel0, 1, this.isSupported());
            SafeParcelWriter.writeString(parcel0, 2, this.getRequestJson(), false);
            SafeParcelWriter.finishObjectHeader(parcel0, v1);
        }
    }

    @Deprecated
    public static final class PasskeysRequestOptions extends AbstractSafeParcelable {
        public static final class com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder {
            private boolean zba;
            private byte[] zbb;
            private String zbc;

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder() {
                this.zba = false;
            }

            public PasskeysRequestOptions build() {
                return new PasskeysRequestOptions(this.zba, this.zbb, this.zbc);
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder setChallenge(byte[] arr_b) {
                this.zbb = arr_b;
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder setRpId(String s) {
                this.zbc = s;
                return this;
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public static final Parcelable.Creator CREATOR;
        private final boolean zba;
        private final byte[] zbb;
        private final String zbc;

        static {
            PasskeysRequestOptions.CREATOR = new zbl();
        }

        PasskeysRequestOptions(boolean z, byte[] arr_b, String s) {
            if(z) {
                Preconditions.checkNotNull(arr_b);
                Preconditions.checkNotNull(s);
            }
            this.zba = z;
            this.zbb = arr_b;
            this.zbc = s;
        }

        public static com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder builder() {
            return new com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder();
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof PasskeysRequestOptions ? this.zba == ((PasskeysRequestOptions)object0).zba && Arrays.equals(this.zbb, ((PasskeysRequestOptions)object0).zbb) && java.util.Objects.equals(this.zbc, ((PasskeysRequestOptions)object0).zbc) : false;
        }

        public byte[] getChallenge() {
            return this.zbb;
        }

        public String getRpId() {
            return this.zbc;
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(new Object[]{Boolean.valueOf(this.zba), this.zbc}) * 0x1F + Arrays.hashCode(this.zbb);
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
            SafeParcelWriter.writeBoolean(parcel0, 1, this.isSupported());
            SafeParcelWriter.writeByteArray(parcel0, 2, this.getChallenge(), false);
            SafeParcelWriter.writeString(parcel0, 3, this.getRpId(), false);
            SafeParcelWriter.finishObjectHeader(parcel0, v1);
        }
    }

    @Deprecated
    public static final class PasswordRequestOptions extends AbstractSafeParcelable {
        public static final class com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder {
            private boolean zba;

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder() {
                this.zba = false;
            }

            public PasswordRequestOptions build() {
                return new PasswordRequestOptions(this.zba);
            }

            public com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder setSupported(boolean z) {
                this.zba = z;
                return this;
            }
        }

        public static final Parcelable.Creator CREATOR;
        private final boolean zba;

        static {
            PasswordRequestOptions.CREATOR = new zbm();
        }

        PasswordRequestOptions(boolean z) {
            this.zba = z;
        }

        public static com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder builder() {
            return new com.google.android.gms.auth.api.identity.BeginSignInRequest.PasswordRequestOptions.Builder();
        }

        @Override
        public boolean equals(Object object0) {
            return object0 instanceof PasswordRequestOptions ? this.zba == ((PasswordRequestOptions)object0).zba : false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(new Object[]{Boolean.valueOf(this.zba)});
        }

        public boolean isSupported() {
            return this.zba;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
            SafeParcelWriter.writeBoolean(parcel0, 1, this.isSupported());
            SafeParcelWriter.finishObjectHeader(parcel0, v1);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final PasswordRequestOptions zba;
    private final GoogleIdTokenRequestOptions zbb;
    private final String zbc;
    private final boolean zbd;
    private final int zbe;
    private final PasskeysRequestOptions zbf;
    private final PasskeyJsonRequestOptions zbg;
    private final boolean zbh;

    static {
        BeginSignInRequest.CREATOR = new zbd();
    }

    BeginSignInRequest(PasswordRequestOptions beginSignInRequest$PasswordRequestOptions0, GoogleIdTokenRequestOptions beginSignInRequest$GoogleIdTokenRequestOptions0, String s, boolean z, int v, PasskeysRequestOptions beginSignInRequest$PasskeysRequestOptions0, PasskeyJsonRequestOptions beginSignInRequest$PasskeyJsonRequestOptions0, boolean z1) {
        this.zba = (PasswordRequestOptions)Preconditions.checkNotNull(beginSignInRequest$PasswordRequestOptions0);
        this.zbb = (GoogleIdTokenRequestOptions)Preconditions.checkNotNull(beginSignInRequest$GoogleIdTokenRequestOptions0);
        this.zbc = s;
        this.zbd = z;
        this.zbe = v;
        if(beginSignInRequest$PasskeysRequestOptions0 == null) {
            com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeysRequestOptions.Builder beginSignInRequest$PasskeysRequestOptions$Builder0 = PasskeysRequestOptions.builder();
            beginSignInRequest$PasskeysRequestOptions$Builder0.setSupported(false);
            beginSignInRequest$PasskeysRequestOptions0 = beginSignInRequest$PasskeysRequestOptions$Builder0.build();
        }
        this.zbf = beginSignInRequest$PasskeysRequestOptions0;
        if(beginSignInRequest$PasskeyJsonRequestOptions0 == null) {
            com.google.android.gms.auth.api.identity.BeginSignInRequest.PasskeyJsonRequestOptions.Builder beginSignInRequest$PasskeyJsonRequestOptions$Builder0 = PasskeyJsonRequestOptions.builder();
            beginSignInRequest$PasskeyJsonRequestOptions$Builder0.setSupported(false);
            beginSignInRequest$PasskeyJsonRequestOptions0 = beginSignInRequest$PasskeyJsonRequestOptions$Builder0.build();
        }
        this.zbg = beginSignInRequest$PasskeyJsonRequestOptions0;
        this.zbh = z1;
    }

    public static Builder builder() {
        return new Builder();
    }

    // 去混淆评级： 中等(60)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof BeginSignInRequest ? Objects.equal(this.zba, ((BeginSignInRequest)object0).zba) && Objects.equal(this.zbb, ((BeginSignInRequest)object0).zbb) && Objects.equal(this.zbf, ((BeginSignInRequest)object0).zbf) && Objects.equal(this.zbg, ((BeginSignInRequest)object0).zbg) && Objects.equal(this.zbc, ((BeginSignInRequest)object0).zbc) && this.zbd == ((BeginSignInRequest)object0).zbd && this.zbe == ((BeginSignInRequest)object0).zbe && this.zbh == ((BeginSignInRequest)object0).zbh : false;
    }

    public GoogleIdTokenRequestOptions getGoogleIdTokenRequestOptions() {
        return this.zbb;
    }

    public PasskeyJsonRequestOptions getPasskeyJsonRequestOptions() {
        return this.zbg;
    }

    public PasskeysRequestOptions getPasskeysRequestOptions() {
        return this.zbf;
    }

    public PasswordRequestOptions getPasswordRequestOptions() {
        return this.zba;
    }

    public boolean getPreferImmediatelyAvailableCredentials() {
        return this.zbh;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbf, this.zbg, this.zbc, Boolean.valueOf(this.zbd), this.zbe, Boolean.valueOf(this.zbh)});
    }

    public boolean isAutoSelectEnabled() {
        return this.zbd;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getPasswordRequestOptions(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.getGoogleIdTokenRequestOptions(), v, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zbc, false);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.isAutoSelectEnabled());
        SafeParcelWriter.writeInt(parcel0, 5, this.zbe);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.getPasskeysRequestOptions(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.getPasskeyJsonRequestOptions(), v, false);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.getPreferImmediatelyAvailableCredentials());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static Builder zba(BeginSignInRequest beginSignInRequest0) {
        Preconditions.checkNotNull(beginSignInRequest0);
        Builder beginSignInRequest$Builder0 = BeginSignInRequest.builder();
        beginSignInRequest$Builder0.setGoogleIdTokenRequestOptions(beginSignInRequest0.getGoogleIdTokenRequestOptions());
        beginSignInRequest$Builder0.setPasswordRequestOptions(beginSignInRequest0.getPasswordRequestOptions());
        beginSignInRequest$Builder0.setPasskeysSignInRequestOptions(beginSignInRequest0.getPasskeysRequestOptions());
        beginSignInRequest$Builder0.setPasskeyJsonSignInRequestOptions(beginSignInRequest0.getPasskeyJsonRequestOptions());
        beginSignInRequest$Builder0.setAutoSelectEnabled(beginSignInRequest0.zbd);
        beginSignInRequest$Builder0.zbb(beginSignInRequest0.zbe);
        beginSignInRequest$Builder0.setPreferImmediatelyAvailableCredentials(beginSignInRequest0.zbh);
        String s = beginSignInRequest0.zbc;
        if(s != null) {
            beginSignInRequest$Builder0.zba(s);
        }
        return beginSignInRequest$Builder0;
    }
}

