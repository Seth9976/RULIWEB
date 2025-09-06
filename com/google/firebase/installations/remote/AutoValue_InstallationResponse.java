package com.google.firebase.installations.remote;

final class AutoValue_InstallationResponse extends InstallationResponse {
    static final class Builder extends com.google.firebase.installations.remote.InstallationResponse.Builder {
        private TokenResult authToken;
        private String fid;
        private String refreshToken;
        private ResponseCode responseCode;
        private String uri;

        Builder() {
        }

        private Builder(InstallationResponse installationResponse0) {
            this.uri = installationResponse0.getUri();
            this.fid = installationResponse0.getFid();
            this.refreshToken = installationResponse0.getRefreshToken();
            this.authToken = installationResponse0.getAuthToken();
            this.responseCode = installationResponse0.getResponseCode();
        }

        Builder(InstallationResponse installationResponse0, com.google.firebase.installations.remote.AutoValue_InstallationResponse.1 autoValue_InstallationResponse$10) {
            this(installationResponse0);
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public InstallationResponse build() {
            return new AutoValue_InstallationResponse(this.uri, this.fid, this.refreshToken, this.authToken, this.responseCode, null);
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public com.google.firebase.installations.remote.InstallationResponse.Builder setAuthToken(TokenResult tokenResult0) {
            this.authToken = tokenResult0;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public com.google.firebase.installations.remote.InstallationResponse.Builder setFid(String s) {
            this.fid = s;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public com.google.firebase.installations.remote.InstallationResponse.Builder setRefreshToken(String s) {
            this.refreshToken = s;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public com.google.firebase.installations.remote.InstallationResponse.Builder setResponseCode(ResponseCode installationResponse$ResponseCode0) {
            this.responseCode = installationResponse$ResponseCode0;
            return this;
        }

        @Override  // com.google.firebase.installations.remote.InstallationResponse$Builder
        public com.google.firebase.installations.remote.InstallationResponse.Builder setUri(String s) {
            this.uri = s;
            return this;
        }
    }

    private final TokenResult authToken;
    private final String fid;
    private final String refreshToken;
    private final ResponseCode responseCode;
    private final String uri;

    private AutoValue_InstallationResponse(String s, String s1, String s2, TokenResult tokenResult0, ResponseCode installationResponse$ResponseCode0) {
        this.uri = s;
        this.fid = s1;
        this.refreshToken = s2;
        this.authToken = tokenResult0;
        this.responseCode = installationResponse$ResponseCode0;
    }

    AutoValue_InstallationResponse(String s, String s1, String s2, TokenResult tokenResult0, ResponseCode installationResponse$ResponseCode0, com.google.firebase.installations.remote.AutoValue_InstallationResponse.1 autoValue_InstallationResponse$10) {
        this(s, s1, s2, tokenResult0, installationResponse$ResponseCode0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof InstallationResponse) {
            String s = this.uri;
            if(s != null) {
                if(s.equals(((InstallationResponse)object0).getUri())) {
                label_8:
                    String s1 = this.fid;
                    if(s1 != null) {
                        if(s1.equals(((InstallationResponse)object0).getFid())) {
                        label_13:
                            String s2 = this.refreshToken;
                            if(s2 != null) {
                                if(s2.equals(((InstallationResponse)object0).getRefreshToken())) {
                                label_18:
                                    TokenResult tokenResult0 = this.authToken;
                                    if(tokenResult0 == null) {
                                        if(((InstallationResponse)object0).getAuthToken() == null) {
                                            return this.responseCode == null ? ((InstallationResponse)object0).getResponseCode() == null : this.responseCode.equals(((InstallationResponse)object0).getResponseCode());
                                        }
                                    }
                                    else if(tokenResult0.equals(((InstallationResponse)object0).getAuthToken())) {
                                        return this.responseCode == null ? ((InstallationResponse)object0).getResponseCode() == null : this.responseCode.equals(((InstallationResponse)object0).getResponseCode());
                                    }
                                }
                            }
                            else if(((InstallationResponse)object0).getRefreshToken() == null) {
                                goto label_18;
                            }
                        }
                    }
                    else if(((InstallationResponse)object0).getFid() == null) {
                        goto label_13;
                    }
                }
            }
            else if(((InstallationResponse)object0).getUri() == null) {
                goto label_8;
            }
        }
        return false;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public TokenResult getAuthToken() {
        return this.authToken;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public String getFid() {
        return this.fid;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public String getUri() {
        return this.uri;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.uri == null ? 0 : this.uri.hashCode();
        int v2 = this.fid == null ? 0 : this.fid.hashCode();
        int v3 = this.refreshToken == null ? 0 : this.refreshToken.hashCode();
        int v4 = this.authToken == null ? 0 : this.authToken.hashCode();
        ResponseCode installationResponse$ResponseCode0 = this.responseCode;
        if(installationResponse$ResponseCode0 != null) {
            v = installationResponse$ResponseCode0.hashCode();
        }
        return ((((v1 ^ 1000003) * 1000003 ^ v2) * 1000003 ^ v3) * 1000003 ^ v4) * 1000003 ^ v;
    }

    @Override  // com.google.firebase.installations.remote.InstallationResponse
    public com.google.firebase.installations.remote.InstallationResponse.Builder toBuilder() {
        return new Builder(this, null);
    }

    @Override
    public String toString() {
        return "InstallationResponse{uri=" + this.uri + ", fid=" + this.fid + ", refreshToken=" + this.refreshToken + ", authToken=" + this.authToken + ", responseCode=" + this.responseCode + "}";
    }

    class com.google.firebase.installations.remote.AutoValue_InstallationResponse.1 {
    }

}

