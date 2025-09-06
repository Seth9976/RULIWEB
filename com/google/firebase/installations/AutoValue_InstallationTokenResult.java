package com.google.firebase.installations;

final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    static final class Builder extends com.google.firebase.installations.InstallationTokenResult.Builder {
        private String token;
        private Long tokenCreationTimestamp;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(InstallationTokenResult installationTokenResult0) {
            this.token = installationTokenResult0.getToken();
            this.tokenExpirationTimestamp = installationTokenResult0.getTokenExpirationTimestamp();
            this.tokenCreationTimestamp = installationTokenResult0.getTokenCreationTimestamp();
        }

        Builder(InstallationTokenResult installationTokenResult0, com.google.firebase.installations.AutoValue_InstallationTokenResult.1 autoValue_InstallationTokenResult$10) {
            this(installationTokenResult0);
        }

        @Override  // com.google.firebase.installations.InstallationTokenResult$Builder
        public InstallationTokenResult build() {
            String s = this.token == null ? " token" : "";
            if(this.tokenExpirationTimestamp == null) {
                s = s + " tokenExpirationTimestamp";
            }
            if(this.tokenCreationTimestamp == null) {
                s = s + " tokenCreationTimestamp";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_InstallationTokenResult(this.token, ((long)this.tokenExpirationTimestamp), ((long)this.tokenCreationTimestamp), null);
        }

        @Override  // com.google.firebase.installations.InstallationTokenResult$Builder
        public com.google.firebase.installations.InstallationTokenResult.Builder setToken(String s) {
            if(s == null) {
                throw new NullPointerException("Null token");
            }
            this.token = s;
            return this;
        }

        @Override  // com.google.firebase.installations.InstallationTokenResult$Builder
        public com.google.firebase.installations.InstallationTokenResult.Builder setTokenCreationTimestamp(long v) {
            this.tokenCreationTimestamp = v;
            return this;
        }

        @Override  // com.google.firebase.installations.InstallationTokenResult$Builder
        public com.google.firebase.installations.InstallationTokenResult.Builder setTokenExpirationTimestamp(long v) {
            this.tokenExpirationTimestamp = v;
            return this;
        }
    }

    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    private AutoValue_InstallationTokenResult(String s, long v, long v1) {
        this.token = s;
        this.tokenExpirationTimestamp = v;
        this.tokenCreationTimestamp = v1;
    }

    AutoValue_InstallationTokenResult(String s, long v, long v1, com.google.firebase.installations.AutoValue_InstallationTokenResult.1 autoValue_InstallationTokenResult$10) {
        this(s, v, v1);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof InstallationTokenResult) {
            String s = ((InstallationTokenResult)object0).getToken();
            if(this.token.equals(s)) {
                long v = ((InstallationTokenResult)object0).getTokenExpirationTimestamp();
                if(this.tokenExpirationTimestamp == v) {
                    long v1 = ((InstallationTokenResult)object0).getTokenCreationTimestamp();
                    return this.tokenCreationTimestamp == v1;
                }
            }
        }
        return false;
    }

    @Override  // com.google.firebase.installations.InstallationTokenResult
    public String getToken() {
        return this.token;
    }

    @Override  // com.google.firebase.installations.InstallationTokenResult
    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    @Override  // com.google.firebase.installations.InstallationTokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    @Override
    public int hashCode() {
        return ((this.token.hashCode() ^ 1000003) * 1000003 ^ ((int)(this.tokenExpirationTimestamp ^ this.tokenExpirationTimestamp >>> 0x20))) * 1000003 ^ ((int)(this.tokenCreationTimestamp ^ this.tokenCreationTimestamp >>> 0x20));
    }

    @Override  // com.google.firebase.installations.InstallationTokenResult
    public com.google.firebase.installations.InstallationTokenResult.Builder toBuilder() {
        return new Builder(this, null);
    }

    @Override
    public String toString() {
        return "InstallationTokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", tokenCreationTimestamp=" + this.tokenCreationTimestamp + "}";
    }

    class com.google.firebase.installations.AutoValue_InstallationTokenResult.1 {
    }

}

