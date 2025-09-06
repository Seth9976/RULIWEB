package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public final class ClientSettings {
    public static final class Builder {
        @Nullable
        private Account zaa;
        private ArraySet zab;
        private String zac;
        private String zad;
        private final SignInOptions zae;

        public Builder() {
            this.zae = SignInOptions.zaa;
        }

        public ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, null, 0, null, this.zac, this.zad, this.zae, false);
        }

        public Builder setRealClientPackageName(String s) {
            this.zac = s;
            return this;
        }

        public final Builder zaa(Collection collection0) {
            if(this.zab == null) {
                this.zab = new ArraySet();
            }
            this.zab.addAll(collection0);
            return this;
        }

        public final Builder zab(@Nullable Account account0) {
            this.zaa = account0;
            return this;
        }

        public final Builder zac(String s) {
            this.zad = s;
            return this;
        }
    }

    @Nullable
    private final Account zaa;
    private final Set zab;
    private final Set zac;
    private final Map zad;
    private final int zae;
    @Nullable
    private final View zaf;
    private final String zag;
    private final String zah;
    private final SignInOptions zai;
    private Integer zaj;

    public ClientSettings(Account account0, Set set0, Map map0, int v, @Nullable View view0, String s, String s1, @Nullable SignInOptions signInOptions0) {
        this(account0, set0, map0, v, view0, s, s1, signInOptions0, false);
    }

    public ClientSettings(@Nullable Account account0, Set set0, Map map0, int v, @Nullable View view0, String s, String s1, @Nullable SignInOptions signInOptions0, boolean z) {
        this.zaa = account0;
        Set set1 = set0 == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set0);
        this.zab = set1;
        if(map0 == null) {
            map0 = Collections.EMPTY_MAP;
        }
        this.zad = map0;
        this.zaf = view0;
        this.zae = v;
        this.zag = s;
        this.zah = s1;
        if(signInOptions0 == null) {
            signInOptions0 = SignInOptions.zaa;
        }
        this.zai = signInOptions0;
        HashSet hashSet0 = new HashSet(set1);
        for(Object object0: map0.values()) {
            hashSet0.addAll(((zab)object0).zaa);
        }
        this.zac = Collections.unmodifiableSet(hashSet0);
    }

    public static ClientSettings createDefault(Context context0) {
        return new com.google.android.gms.common.api.GoogleApiClient.Builder(context0).zaa();
    }

    public Account getAccount() {
        return this.zaa;
    }

    @Deprecated
    public String getAccountName() {
        return this.zaa == null ? null : this.zaa.name;
    }

    public Account getAccountOrDefault() {
        return this.zaa == null ? new Account("<<default account>>", "com.google") : this.zaa;
    }

    public Set getAllRequestedScopes() {
        return this.zac;
    }

    public Set getApplicableScopes(Api api0) {
        zab zab0 = (zab)this.zad.get(api0);
        if(zab0 != null && !zab0.zaa.isEmpty()) {
            Set set0 = new HashSet(this.zab);
            set0.addAll(zab0.zaa);
            return set0;
        }
        return this.zab;
    }

    public int getGravityForPopups() {
        return this.zae;
    }

    public String getRealClientPackageName() {
        return this.zag;
    }

    public Set getRequiredScopes() {
        return this.zab;
    }

    public View getViewForPopups() {
        return this.zaf;
    }

    public final SignInOptions zaa() {
        return this.zai;
    }

    public final Integer zab() {
        return this.zaj;
    }

    public final String zac() {
        return this.zah;
    }

    public final Map zad() {
        return this.zad;
    }

    public final void zae(Integer integer0) {
        this.zaj = integer0;
    }
}

