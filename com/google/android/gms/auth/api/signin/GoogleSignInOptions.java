package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class GoogleSignInOptions extends AbstractSafeParcelable implements Optional, ReflectedParcelable {
    public static final class Builder {
        private Set zaa;
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map zah;
        private String zai;

        public Builder() {
            this.zaa = new HashSet();
            this.zah = new HashMap();
        }

        public Builder(GoogleSignInOptions googleSignInOptions0) {
            this.zaa = new HashSet();
            this.zah = new HashMap();
            Preconditions.checkNotNull(googleSignInOptions0);
            this.zaa = new HashSet(googleSignInOptions0.zah);
            this.zab = googleSignInOptions0.zak;
            this.zac = googleSignInOptions0.zal;
            this.zad = googleSignInOptions0.zaj;
            this.zae = googleSignInOptions0.zam;
            this.zaf = googleSignInOptions0.zai;
            this.zag = googleSignInOptions0.zan;
            this.zah = GoogleSignInOptions.zam(googleSignInOptions0.zao);
            this.zai = googleSignInOptions0.zap;
        }

        public Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
            if(this.zah.containsKey(googleSignInOptionsExtension0.getExtensionType())) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            List list0 = googleSignInOptionsExtension0.getImpliedScopes();
            if(list0 != null) {
                this.zaa.addAll(list0);
            }
            this.zah.put(googleSignInOptionsExtension0.getExtensionType(), new GoogleSignInOptionsExtensionParcelable(googleSignInOptionsExtension0));
            return this;
        }

        public GoogleSignInOptions build() {
            if(this.zaa.contains(GoogleSignInOptions.zae) && this.zaa.contains(GoogleSignInOptions.zad)) {
                this.zaa.remove(GoogleSignInOptions.zad);
            }
            if(this.zad && (this.zaf == null || !this.zaa.isEmpty())) {
                this.requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, null);
        }

        public Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        public Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        public Builder requestIdToken(String s) {
            this.zad = true;
            this.zaa(s);
            this.zae = s;
            return this;
        }

        public Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        public Builder requestScopes(Scope scope0, Scope[] arr_scope) {
            this.zaa.add(scope0);
            this.zaa.addAll(Arrays.asList(arr_scope));
            return this;
        }

        public Builder requestServerAuthCode(String s) {
            this.requestServerAuthCode(s, false);
            return this;
        }

        public Builder requestServerAuthCode(String s, boolean z) {
            this.zab = true;
            this.zaa(s);
            this.zae = s;
            this.zac = z;
            return this;
        }

        public Builder setAccountName(String s) {
            this.zaf = new Account(Preconditions.checkNotEmpty(s), "com.google");
            return this;
        }

        public Builder setHostedDomain(String s) {
            this.zag = Preconditions.checkNotEmpty(s);
            return this;
        }

        public Builder setLogSessionId(String s) {
            this.zai = s;
            return this;
        }

        private final String zaa(String s) {
            Preconditions.checkNotEmpty(s);
            Preconditions.checkArgument(this.zae == null || this.zae.equals(s), "two different server client ids provided");
            return s;
        }
    }

    public static final Parcelable.Creator CREATOR;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    public static final Scope zaa;
    public static final Scope zab;
    public static final Scope zac;
    public static final Scope zad;
    public static final Scope zae;
    final int zaf;
    private static final Comparator zag;
    private final ArrayList zah;
    private Account zai;
    private boolean zaj;
    private final boolean zak;
    private final boolean zal;
    private String zam;
    private String zan;
    private ArrayList zao;
    private String zap;
    private Map zaq;

    static {
        GoogleSignInOptions.zaa = new Scope("profile");
        GoogleSignInOptions.zab = new Scope("email");
        GoogleSignInOptions.zac = new Scope("openid");
        Scope scope0 = new Scope("https://www.googleapis.com/auth/games_lite");
        GoogleSignInOptions.zad = scope0;
        GoogleSignInOptions.zae = new Scope("https://www.googleapis.com/auth/games");
        Builder googleSignInOptions$Builder0 = new Builder();
        googleSignInOptions$Builder0.requestId();
        googleSignInOptions$Builder0.requestProfile();
        GoogleSignInOptions.DEFAULT_SIGN_IN = googleSignInOptions$Builder0.build();
        Builder googleSignInOptions$Builder1 = new Builder();
        googleSignInOptions$Builder1.requestScopes(scope0, new Scope[0]);
        GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN = googleSignInOptions$Builder1.build();
        GoogleSignInOptions.CREATOR = new zae();
        GoogleSignInOptions.zag = new zac();
    }

    GoogleSignInOptions(int v, ArrayList arrayList0, Account account0, boolean z, boolean z1, boolean z2, String s, String s1, ArrayList arrayList1, String s2) {
        this(v, arrayList0, account0, z, z1, z2, s, s1, GoogleSignInOptions.zam(arrayList1), s2);
    }

    private GoogleSignInOptions(int v, ArrayList arrayList0, Account account0, boolean z, boolean z1, boolean z2, String s, String s1, Map map0, String s2) {
        this.zaf = v;
        this.zah = arrayList0;
        this.zai = account0;
        this.zaj = z;
        this.zak = z1;
        this.zal = z2;
        this.zam = s;
        this.zan = s1;
        this.zao = new ArrayList(map0.values());
        this.zaq = map0;
        this.zap = s2;
    }

    GoogleSignInOptions(int v, ArrayList arrayList0, Account account0, boolean z, boolean z1, boolean z2, String s, String s1, Map map0, String s2, zad zad0) {
        this(3, arrayList0, account0, z, z1, z2, s, s1, map0, s2);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        try {
            if(this.zao.isEmpty() && ((GoogleSignInOptions)object0).zao.isEmpty()) {
                int v = ((GoogleSignInOptions)object0).getScopes().size();
                if(this.zah.size() == v) {
                    ArrayList arrayList0 = ((GoogleSignInOptions)object0).getScopes();
                    if(this.zah.containsAll(arrayList0)) {
                        Account account0 = this.zai;
                        if(account0 != null) {
                            if(account0.equals(((GoogleSignInOptions)object0).getAccount())) {
                            label_12:
                                if(!TextUtils.isEmpty(this.zam)) {
                                    if(this.zam.equals(((GoogleSignInOptions)object0).getServerClientId())) {
                                        return this.zal != ((GoogleSignInOptions)object0).isForceCodeForRefreshToken() || this.zaj != ((GoogleSignInOptions)object0).isIdTokenRequested() ? false : this.zak == ((GoogleSignInOptions)object0).isServerAuthCodeRequested() && TextUtils.equals(this.zap, ((GoogleSignInOptions)object0).getLogSessionId());
                                    }
                                }
                                else if(TextUtils.isEmpty(((GoogleSignInOptions)object0).getServerClientId())) {
                                    return this.zal != ((GoogleSignInOptions)object0).isForceCodeForRefreshToken() || this.zaj != ((GoogleSignInOptions)object0).isIdTokenRequested() ? false : this.zak == ((GoogleSignInOptions)object0).isServerAuthCodeRequested() && TextUtils.equals(this.zap, ((GoogleSignInOptions)object0).getLogSessionId());
                                }
                            }
                        }
                        else if(((GoogleSignInOptions)object0).getAccount() == null) {
                            goto label_12;
                        }
                    }
                }
            }
        }
        catch(ClassCastException unused_ex) {
        }
        return false;
    }

    public Account getAccount() {
        return this.zai;
    }

    public ArrayList getExtensions() {
        return this.zao;
    }

    public String getLogSessionId() {
        return this.zap;
    }

    public Scope[] getScopeArray() {
        return (Scope[])this.zah.toArray(new Scope[this.zah.size()]);
    }

    public ArrayList getScopes() {
        return new ArrayList(this.zah);
    }

    public String getServerClientId() {
        return this.zam;
    }

    @Override
    public int hashCode() {
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = this.zah;
        int v = arrayList1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList0.add(((Scope)arrayList1.get(v1)).getScopeUri());
        }
        Collections.sort(arrayList0);
        HashAccumulator hashAccumulator0 = new HashAccumulator();
        hashAccumulator0.addObject(arrayList0);
        hashAccumulator0.addObject(this.zai);
        hashAccumulator0.addObject(this.zam);
        hashAccumulator0.zaa(this.zal);
        hashAccumulator0.zaa(this.zaj);
        hashAccumulator0.zaa(this.zak);
        hashAccumulator0.addObject(this.zap);
        return hashAccumulator0.hash();
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zal;
    }

    public boolean isIdTokenRequested() {
        return this.zaj;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zak;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaf);
        SafeParcelWriter.writeTypedList(parcel0, 2, this.getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.getAccount(), v, false);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel0, 5, this.isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel0, 6, this.isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel0, 7, this.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel0, 8, this.zan, false);
        SafeParcelWriter.writeTypedList(parcel0, 9, this.getExtensions(), false);
        SafeParcelWriter.writeString(parcel0, 10, this.getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static GoogleSignInOptions zab(String s) throws JSONException {
        String s1 = null;
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        JSONObject jSONObject0 = new JSONObject(s);
        HashSet hashSet0 = new HashSet();
        JSONArray jSONArray0 = jSONObject0.getJSONArray("scopes");
        int v = jSONArray0.length();
        for(int v1 = 0; v1 < v; ++v1) {
            hashSet0.add(new Scope(jSONArray0.getString(v1)));
        }
        String s2 = jSONObject0.has("accountName") ? jSONObject0.optString("accountName") : null;
        Account account0 = TextUtils.isEmpty(s2) ? null : new Account(s2, "com.google");
        ArrayList arrayList0 = new ArrayList(hashSet0);
        boolean z = jSONObject0.getBoolean("idTokenRequested");
        boolean z1 = jSONObject0.getBoolean("serverAuthRequested");
        boolean z2 = jSONObject0.getBoolean("forceCodeForRefreshToken");
        String s3 = jSONObject0.has("serverClientId") ? jSONObject0.optString("serverClientId") : null;
        if(jSONObject0.has("hostedDomain")) {
            s1 = jSONObject0.optString("hostedDomain");
        }
        return new GoogleSignInOptions(3, arrayList0, account0, z, z1, z2, s3, s1, new HashMap(), null);
    }

    public final String zaf() {
        JSONObject jSONObject0 = new JSONObject();
        try {
            JSONArray jSONArray0 = new JSONArray();
            Collections.sort(this.zah, GoogleSignInOptions.zag);
            for(Object object0: this.zah) {
                jSONArray0.put(((Scope)object0).getScopeUri());
            }
            jSONObject0.put("scopes", jSONArray0);
            Account account0 = this.zai;
            if(account0 != null) {
                jSONObject0.put("accountName", account0.name);
            }
            jSONObject0.put("idTokenRequested", this.zaj);
            jSONObject0.put("forceCodeForRefreshToken", this.zal);
            jSONObject0.put("serverAuthRequested", this.zak);
            if(!TextUtils.isEmpty(this.zam)) {
                jSONObject0.put("serverClientId", this.zam);
            }
            if(!TextUtils.isEmpty(this.zan)) {
                jSONObject0.put("hostedDomain", this.zan);
            }
            return jSONObject0.toString();
        }
        catch(JSONException jSONException0) {
            throw new RuntimeException(jSONException0);
        }
    }

    private static Map zam(List list0) {
        Map map0 = new HashMap();
        if(list0 != null) {
            for(Object object0: list0) {
                map0.put(((GoogleSignInOptionsExtensionParcelable)object0).getType(), ((GoogleSignInOptionsExtensionParcelable)object0));
            }
        }
        return map0;
    }
}

