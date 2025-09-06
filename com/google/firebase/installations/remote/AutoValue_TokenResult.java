package com.google.firebase.installations.remote;

final class AutoValue_TokenResult extends TokenResult {
    static final class Builder extends com.google.firebase.installations.remote.TokenResult.Builder {
        private ResponseCode responseCode;
        private String token;
        private Long tokenExpirationTimestamp;

        Builder() {
        }

        private Builder(TokenResult tokenResult0) {
            this.token = tokenResult0.getToken();
            this.tokenExpirationTimestamp = tokenResult0.getTokenExpirationTimestamp();
            this.responseCode = tokenResult0.getResponseCode();
        }

        Builder(TokenResult tokenResult0, com.google.firebase.installations.remote.AutoValue_TokenResult.1 autoValue_TokenResult$10) {
            this(tokenResult0);
        }

        @Override  // com.google.firebase.installations.remote.TokenResult$Builder
        public TokenResult build() {
            String s = this.tokenExpirationTimestamp == null ? " tokenExpirationTimestamp" : "";
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_TokenResult(this.token, ((long)this.tokenExpirationTimestamp), this.responseCode, null);
        }

        @Override  // com.google.firebase.installations.remote.TokenResult$Builder
        public com.google.firebase.installations.remote.TokenResult.Builder setResponseCode(ResponseCode tokenResult$ResponseCode0) {
            this.responseCode = tokenResult$ResponseCode0;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.TokenResult$Builder
        public com.google.firebase.installations.remote.TokenResult.Builder setToken(String s) {
            this.token = s;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.TokenResult$Builder
        public com.google.firebase.installations.remote.TokenResult.Builder setTokenExpirationTimestamp(long v) {
            this.tokenExpirationTimestamp = v;
            return this;
        }
    }

    private final ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    private AutoValue_TokenResult(String s, long v, ResponseCode tokenResult$ResponseCode0) {
        this.token = s;
        this.tokenExpirationTimestamp = v;
        this.responseCode = tokenResult$ResponseCode0;
    }

    AutoValue_TokenResult(String s, long v, ResponseCode tokenResult$ResponseCode0, com.google.firebase.installations.remote.AutoValue_TokenResult.1 autoValue_TokenResult$10) {
        this(s, v, tokenResult$ResponseCode0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof TokenResult) {
            String s = this.token;
            if(s != null) {
                if(s.equals(((TokenResult)object0).getToken())) {
                label_8:
                    long v = ((TokenResult)object0).getTokenExpirationTimestamp();
                    if(this.tokenExpirationTimestamp == v) {
                        return this.responseCode == null ? ((TokenResult)object0).getResponseCode() == null : this.responseCode.equals(((TokenResult)object0).getResponseCode());
                    }
                }
            }
            else if(((TokenResult)object0).getToken() == null) {
                goto label_8;
            }
        }
        return false;
    }

    @Override  // com.google.firebase.installations.remote.TokenResult
    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override  // com.google.firebase.installations.remote.TokenResult
    public String getToken() {
        return this.token;
    }

    @Override  // com.google.firebase.installations.remote.TokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = (((this.token == null ? 0 : this.token.hashCode()) ^ 1000003) * 1000003 ^ ((int)(this.tokenExpirationTimestamp ^ this.tokenExpirationTimestamp >>> 0x20))) * 1000003;
        ResponseCode tokenResult$ResponseCode0 = this.responseCode;
        if(tokenResult$ResponseCode0 != null) {
            v = tokenResult$ResponseCode0.hashCode();
        }
        return v1 ^ v;
    }

    @Override  // com.google.firebase.installations.remote.TokenResult
    public com.google.firebase.installations.remote.TokenResult.Builder toBuilder() {
        return new Builder(this, null);
    }

    @Override
    public String toString() {
        return "TokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", responseCode=" + this.responseCode + "}";
    }

    class com.google.firebase.installations.remote.AutoValue_TokenResult.1 {
    }

}

