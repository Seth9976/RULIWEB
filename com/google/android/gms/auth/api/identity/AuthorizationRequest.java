package com.google.android.gms.auth.api.identity;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;
import java.util.List;

public class AuthorizationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final class Builder {
        private List zba;
        private String zbb;
        private boolean zbc;
        private boolean zbd;
        private Account zbe;
        private String zbf;
        private String zbg;
        private boolean zbh;
        private Bundle zbi;
        private boolean zbj;

        public Builder addResourceParameter(ResourceParameter authorizationRequest$ResourceParameter0, String s) {
            Preconditions.checkNotNull(authorizationRequest$ResourceParameter0, "Resource parameter cannot be null");
            Preconditions.checkNotNull(s, "Resource parameter value cannot be null");
            if(this.zbi == null) {
                this.zbi = new Bundle();
            }
            this.zbi.putString(authorizationRequest$ResourceParameter0.zba, s);
            return this;
        }

        public AuthorizationRequest build() {
            return new AuthorizationRequest(this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi, this.zbj);
        }

        public Builder filterByHostedDomain(String s) {
            this.zbf = Preconditions.checkNotEmpty(s);
            return this;
        }

        public Builder requestOfflineAccess(String s) {
            this.requestOfflineAccess(s, false);
            return this;
        }

        public Builder requestOfflineAccess(String s, boolean z) {
            this.zbc(s);
            this.zbb = s;
            this.zbc = true;
            this.zbh = z;
            return this;
        }

        public Builder setAccount(Account account0) {
            this.zbe = (Account)Preconditions.checkNotNull(account0);
            return this;
        }

        public Builder setOptOutIncludingGrantedScopes(boolean z) {
            this.zbj = z;
            return this;
        }

        public Builder setRequestedScopes(List list0) {
            Preconditions.checkArgument(list0 != null && !list0.isEmpty(), "requestedScopes cannot be null or empty");
            this.zba = list0;
            return this;
        }

        public final Builder zba(String s) {
            this.zbc(s);
            this.zbb = s;
            this.zbd = true;
            return this;
        }

        public final Builder zbb(String s) {
            this.zbg = s;
            return this;
        }

        private final String zbc(String s) {
            Preconditions.checkNotNull(s);
            Preconditions.checkArgument(this.zbb == null || this.zbb.equals(s), "two different server client ids provided");
            return s;
        }
    }

    public static enum ResourceParameter {
        ACCOUNT_SELECTION_TOKEN("account_selection_token"),
        ACCOUNT_SELECTION_STATE("account_selection_state");

        final String zba;

        private ResourceParameter(String s1) {
            this.zba = s1;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final List zba;
    private final String zbb;
    private final boolean zbc;
    private final boolean zbd;
    private final Account zbe;
    private final String zbf;
    private final String zbg;
    private final boolean zbh;
    private final Bundle zbi;
    private final boolean zbj;

    static {
        AuthorizationRequest.CREATOR = new zbb();
    }

    AuthorizationRequest(List list0, String s, boolean z, boolean z1, Account account0, String s1, String s2, boolean z2, Bundle bundle0, boolean z3) {
        Preconditions.checkArgument(list0 != null && !list0.isEmpty(), "requestedScopes cannot be null or empty");
        this.zba = list0;
        this.zbb = s;
        this.zbc = z;
        this.zbd = z1;
        this.zbe = account0;
        this.zbf = s1;
        this.zbg = s2;
        this.zbh = z2;
        this.zbi = bundle0;
        this.zbj = z3;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof AuthorizationRequest)) {
            return false;
        }
        if(this.zba.size() == ((AuthorizationRequest)object0).zba.size() && this.zba.containsAll(((AuthorizationRequest)object0).zba)) {
            Bundle bundle0 = ((AuthorizationRequest)object0).zbi;
            Bundle bundle1 = this.zbi;
            if(bundle1 == null) {
                if(bundle0 != null) {
                    return false;
                }
                bundle0 = null;
            }
            if(bundle1 != null && bundle0 == null) {
                return false;
            }
            if(bundle1 != null) {
                if(bundle1.size() != bundle0.size()) {
                    return false;
                }
                for(Object object1: this.zbi.keySet()) {
                    if(!Objects.equal(this.zbi.getString(((String)object1)), bundle0.getString(((String)object1)))) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return this.zbc == ((AuthorizationRequest)object0).zbc && this.zbh == ((AuthorizationRequest)object0).zbh && this.zbd == ((AuthorizationRequest)object0).zbd && this.zbj == ((AuthorizationRequest)object0).zbj && Objects.equal(this.zbb, ((AuthorizationRequest)object0).zbb) && Objects.equal(this.zbe, ((AuthorizationRequest)object0).zbe) && Objects.equal(this.zbf, ((AuthorizationRequest)object0).zbf) && Objects.equal(this.zbg, ((AuthorizationRequest)object0).zbg);
        }
        return false;
    }

    public Account getAccount() {
        return this.zbe;
    }

    public String getHostedDomain() {
        return this.zbf;
    }

    public boolean getOptOutIncludingGrantedScopes() {
        return this.zbj;
    }

    public List getRequestedScopes() {
        return this.zba;
    }

    public String getResourceParameter(ResourceParameter authorizationRequest$ResourceParameter0) {
        return this.zbi == null ? null : this.zbi.getString(authorizationRequest$ResourceParameter0.zba);
    }

    public Bundle getResourceParameters() {
        return this.zbi;
    }

    public String getServerClientId() {
        return this.zbb;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, Boolean.valueOf(this.zbc), Boolean.valueOf(this.zbh), Boolean.valueOf(this.zbd), this.zbe, this.zbf, this.zbg, this.zbi, Boolean.valueOf(this.zbj)});
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zbh;
    }

    public boolean isOfflineAccessRequested() {
        return this.zbc;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeTypedList(parcel0, 1, this.getRequestedScopes(), false);
        SafeParcelWriter.writeString(parcel0, 2, this.getServerClientId(), false);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.isOfflineAccessRequested());
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zbd);
        SafeParcelWriter.writeParcelable(parcel0, 5, this.getAccount(), v, false);
        SafeParcelWriter.writeString(parcel0, 6, this.getHostedDomain(), false);
        SafeParcelWriter.writeString(parcel0, 7, this.zbg, false);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.isForceCodeForRefreshToken());
        SafeParcelWriter.writeBundle(parcel0, 9, this.getResourceParameters(), false);
        SafeParcelWriter.writeBoolean(parcel0, 10, this.getOptOutIncludingGrantedScopes());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static Builder zba(AuthorizationRequest authorizationRequest0) {
        ResourceParameter authorizationRequest$ResourceParameter0;
        String s4;
        Account account0;
        String s3;
        boolean z;
        Preconditions.checkNotNull(authorizationRequest0);
        Builder authorizationRequest$Builder0 = AuthorizationRequest.builder();
        authorizationRequest$Builder0.setRequestedScopes(authorizationRequest0.getRequestedScopes());
        Bundle bundle0 = authorizationRequest0.getResourceParameters();
        if(bundle0 == null) {
        label_24:
            z = authorizationRequest0.isForceCodeForRefreshToken();
            String s2 = authorizationRequest0.zbg;
            s3 = authorizationRequest0.getHostedDomain();
            account0 = authorizationRequest0.getAccount();
            s4 = authorizationRequest0.getServerClientId();
            if(s2 != null) {
                authorizationRequest$Builder0.zbb(s2);
            }
        }
        else {
            Iterator iterator0 = bundle0.keySet().iterator();
            while(true) {
                if(!iterator0.hasNext()) {
                    goto label_24;
                }
                Object object0 = iterator0.next();
                String s = (String)object0;
                String s1 = bundle0.getString(s);
                ResourceParameter[] arr_authorizationRequest$ResourceParameter = ResourceParameter.values();
                int v = 0;
                while(true) {
                    authorizationRequest$ResourceParameter0 = null;
                    if(v < arr_authorizationRequest$ResourceParameter.length) {
                        ResourceParameter authorizationRequest$ResourceParameter1 = arr_authorizationRequest$ResourceParameter[v];
                        if(authorizationRequest$ResourceParameter1.zba.equals(s)) {
                            authorizationRequest$ResourceParameter0 = authorizationRequest$ResourceParameter1;
                        }
                        else {
                            ++v;
                            continue;
                        }
                    }
                    break;
                }
                if(s1 != null && authorizationRequest$ResourceParameter0 != null) {
                    authorizationRequest$Builder0.addResourceParameter(authorizationRequest$ResourceParameter0, s1);
                }
            }
        }
        if(s3 != null) {
            authorizationRequest$Builder0.filterByHostedDomain(s3);
        }
        if(account0 != null) {
            authorizationRequest$Builder0.setAccount(account0);
        }
        if(authorizationRequest0.zbd && s4 != null) {
            authorizationRequest$Builder0.zba(s4);
        }
        if(authorizationRequest0.isOfflineAccessRequested() && s4 != null) {
            authorizationRequest$Builder0.requestOfflineAccess(s4, z);
        }
        authorizationRequest$Builder0.setOptOutIncludingGrantedScopes(authorizationRequest0.zbj);
        return authorizationRequest$Builder0;
    }
}

