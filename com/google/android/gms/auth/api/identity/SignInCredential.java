package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

@Deprecated
public final class SignInCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final Uri zbe;
    private final String zbf;
    private final String zbg;
    private final String zbh;
    private final PublicKeyCredential zbi;

    static {
        SignInCredential.CREATOR = new zbr();
    }

    SignInCredential(String s, String s1, String s2, String s3, Uri uri0, String s4, String s5, String s6, PublicKeyCredential publicKeyCredential0) {
        this.zba = (String)Preconditions.checkNotNull(s);
        this.zbb = s1;
        this.zbc = s2;
        this.zbd = s3;
        this.zbe = uri0;
        this.zbf = s4;
        this.zbg = s5;
        this.zbh = s6;
        this.zbi = publicKeyCredential0;
    }

    // 去混淆评级： 中等(100)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SignInCredential ? Objects.equal(this.zba, ((SignInCredential)object0).zba) && Objects.equal(this.zbb, ((SignInCredential)object0).zbb) && Objects.equal(this.zbc, ((SignInCredential)object0).zbc) && Objects.equal(this.zbd, ((SignInCredential)object0).zbd) && Objects.equal(this.zbe, ((SignInCredential)object0).zbe) && Objects.equal(this.zbf, ((SignInCredential)object0).zbf) && Objects.equal(this.zbg, ((SignInCredential)object0).zbg) && Objects.equal(this.zbh, ((SignInCredential)object0).zbh) && Objects.equal(this.zbi, ((SignInCredential)object0).zbi) : false;
    }

    public String getDisplayName() {
        return this.zbb;
    }

    public String getFamilyName() {
        return this.zbd;
    }

    public String getGivenName() {
        return this.zbc;
    }

    public String getGoogleIdToken() {
        return this.zbg;
    }

    public String getId() {
        return this.zba;
    }

    public String getPassword() {
        return this.zbf;
    }

    @Deprecated
    public String getPhoneNumber() {
        return this.zbh;
    }

    public Uri getProfilePictureUri() {
        return this.zbe;
    }

    public PublicKeyCredential getPublicKeyCredential() {
        return this.zbi;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.getId(), false);
        SafeParcelWriter.writeString(parcel0, 2, this.getDisplayName(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.getGivenName(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel0, 5, this.getProfilePictureUri(), v, false);
        SafeParcelWriter.writeString(parcel0, 6, this.getPassword(), false);
        SafeParcelWriter.writeString(parcel0, 7, this.getGoogleIdToken(), false);
        SafeParcelWriter.writeString(parcel0, 8, this.getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(parcel0, 9, this.getPublicKeyCredential(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

