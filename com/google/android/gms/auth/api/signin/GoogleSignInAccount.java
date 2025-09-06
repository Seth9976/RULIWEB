package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    public static final Clock zaa;
    final int zab;
    final List zac;
    private final String zad;
    private final String zae;
    private final String zaf;
    private final String zag;
    private final Uri zah;
    private String zai;
    private final long zaj;
    private final String zak;
    private final String zal;
    private final String zam;
    private final Set zan;

    static {
        GoogleSignInAccount.CREATOR = new zab();
        GoogleSignInAccount.zaa = DefaultClock.getInstance();
    }

    GoogleSignInAccount(int v, String s, String s1, String s2, String s3, Uri uri0, String s4, long v1, String s5, List list0, String s6, String s7) {
        this.zan = new HashSet();
        this.zab = v;
        this.zad = s;
        this.zae = s1;
        this.zaf = s2;
        this.zag = s3;
        this.zah = uri0;
        this.zai = s4;
        this.zaj = v1;
        this.zak = s5;
        this.zac = list0;
        this.zal = s6;
        this.zam = s7;
    }

    public static GoogleSignInAccount createDefault() {
        return GoogleSignInAccount.zae(new Account("<<default account>>", "com.google"), new HashSet());
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        return object0 instanceof GoogleSignInAccount ? ((GoogleSignInAccount)object0).zak.equals(this.zak) && ((GoogleSignInAccount)object0).getRequestedScopes().equals(this.getRequestedScopes()) : false;
    }

    public static GoogleSignInAccount fromAccount(Account account0) {
        return GoogleSignInAccount.zae(account0, new ArraySet());
    }

    public static GoogleSignInAccount fromAccountAndScopes(Account account0, Scope scope0, Scope[] arr_scope) {
        Preconditions.checkNotNull(account0);
        Preconditions.checkNotNull(scope0);
        HashSet hashSet0 = new HashSet();
        hashSet0.add(scope0);
        hashSet0.addAll(Arrays.asList(arr_scope));
        return GoogleSignInAccount.zae(account0, hashSet0);
    }

    public Account getAccount() {
        return this.zaf == null ? null : new Account(this.zaf, "com.google");
    }

    public String getDisplayName() {
        return this.zag;
    }

    public String getEmail() {
        return this.zaf;
    }

    public String getFamilyName() {
        return this.zam;
    }

    public String getGivenName() {
        return this.zal;
    }

    public Set getGrantedScopes() {
        return new HashSet(this.zac);
    }

    public String getId() {
        return this.zad;
    }

    public String getIdToken() {
        return this.zae;
    }

    public Uri getPhotoUrl() {
        return this.zah;
    }

    public Set getRequestedScopes() {
        Set set0 = new HashSet(this.zac);
        set0.addAll(this.zan);
        return set0;
    }

    public String getServerAuthCode() {
        return this.zai;
    }

    @Override
    public int hashCode() {
        int v = this.getRequestedScopes().hashCode();
        return (this.zak.hashCode() + 0x20F) * 0x1F + v;
    }

    public boolean isExpired() {
        return GoogleSignInAccount.zaa.currentTimeMillis() / 1000L >= this.zaj - 300L;
    }

    public GoogleSignInAccount requestExtraScopes(Scope[] arr_scope) {
        if(arr_scope != null) {
            Collections.addAll(this.zan, arr_scope);
        }
        return this;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zab);
        SafeParcelWriter.writeString(parcel0, 2, this.getId(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.getIdToken(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getEmail(), false);
        SafeParcelWriter.writeString(parcel0, 5, this.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.getPhotoUrl(), v, false);
        SafeParcelWriter.writeString(parcel0, 7, this.getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel0, 8, this.zaj);
        SafeParcelWriter.writeString(parcel0, 9, this.zak, false);
        SafeParcelWriter.writeTypedList(parcel0, 10, this.zac, false);
        SafeParcelWriter.writeString(parcel0, 11, this.getGivenName(), false);
        SafeParcelWriter.writeString(parcel0, 12, this.getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static GoogleSignInAccount zaa(String s, String s1, String s2, String s3, String s4, String s5, Uri uri0, Long long0, String s6, Set set0) {
        return new GoogleSignInAccount(3, s, s1, s2, s3, uri0, null, ((long)long0), Preconditions.checkNotEmpty(s6), new ArrayList(((Collection)Preconditions.checkNotNull(set0))), s4, s5);
    }

    public static GoogleSignInAccount zab(String s) throws JSONException {
        String s1 = null;
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        JSONObject jSONObject0 = new JSONObject(s);
        String s2 = jSONObject0.optString("photoUrl");
        Uri uri0 = TextUtils.isEmpty(s2) ? null : Uri.parse(s2);
        long v = Long.parseLong(jSONObject0.getString("expirationTime"));
        HashSet hashSet0 = new HashSet();
        JSONArray jSONArray0 = jSONObject0.getJSONArray("grantedScopes");
        int v1 = jSONArray0.length();
        for(int v2 = 0; v2 < v1; ++v2) {
            hashSet0.add(new Scope(jSONArray0.getString(v2)));
        }
        GoogleSignInAccount googleSignInAccount0 = GoogleSignInAccount.zaa(jSONObject0.optString("id"), (jSONObject0.has("tokenId") ? jSONObject0.optString("tokenId") : null), (jSONObject0.has("email") ? jSONObject0.optString("email") : null), (jSONObject0.has("displayName") ? jSONObject0.optString("displayName") : null), (jSONObject0.has("givenName") ? jSONObject0.optString("givenName") : null), (jSONObject0.has("familyName") ? jSONObject0.optString("familyName") : null), uri0, v, jSONObject0.getString("obfuscatedIdentifier"), hashSet0);
        if(jSONObject0.has("serverAuthCode")) {
            s1 = jSONObject0.optString("serverAuthCode");
        }
        googleSignInAccount0.zai = s1;
        return googleSignInAccount0;
    }

    public final String zac() {
        return this.zak;
    }

    public final String zad() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            if(this.getId() != null) {
                jSONObject0.put("id", this.getId());
            }
            if(this.getIdToken() != null) {
                jSONObject0.put("tokenId", this.getIdToken());
            }
            if(this.getEmail() != null) {
                jSONObject0.put("email", this.getEmail());
            }
            if(this.getDisplayName() != null) {
                jSONObject0.put("displayName", this.getDisplayName());
            }
            if(this.getGivenName() != null) {
                jSONObject0.put("givenName", this.getGivenName());
            }
            if(this.getFamilyName() != null) {
                jSONObject0.put("familyName", this.getFamilyName());
            }
            Uri uri0 = this.getPhotoUrl();
            if(uri0 != null) {
                jSONObject0.put("photoUrl", uri0.toString());
            }
            if(this.getServerAuthCode() != null) {
                jSONObject0.put("serverAuthCode", this.getServerAuthCode());
            }
            jSONObject0.put("expirationTime", this.zaj);
            jSONObject0.put("obfuscatedIdentifier", this.zak);
            JSONArray jSONArray0 = new JSONArray();
            Scope[] arr_scope = (Scope[])this.zac.toArray(new Scope[this.zac.size()]);
            Arrays.sort(arr_scope, zaa.zaa);
            for(int v = 0; v < arr_scope.length; ++v) {
                jSONArray0.put(arr_scope[v].getScopeUri());
            }
            jSONObject0.put("grantedScopes", jSONArray0);
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
        jSONObject0.remove("serverAuthCode");
        return jSONObject0.toString();
    }

    private static GoogleSignInAccount zae(Account account0, Set set0) {
        return GoogleSignInAccount.zaa(null, null, account0.name, null, null, null, null, 0L, account0.name, set0);
    }
}

