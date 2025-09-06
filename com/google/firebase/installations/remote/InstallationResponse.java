package com.google.firebase.installations.remote;

public abstract class InstallationResponse {
    public static abstract class Builder {
        public abstract InstallationResponse build();

        public abstract Builder setAuthToken(TokenResult arg1);

        public abstract Builder setFid(String arg1);

        public abstract Builder setRefreshToken(String arg1);

        public abstract Builder setResponseCode(ResponseCode arg1);

        public abstract Builder setUri(String arg1);
    }

    public static enum ResponseCode {
        OK,
        BAD_CONFIG;

    }

    public static Builder builder() {
        return new com.google.firebase.installations.remote.AutoValue_InstallationResponse.Builder();
    }

    public abstract TokenResult getAuthToken();

    public abstract String getFid();

    public abstract String getRefreshToken();

    public abstract ResponseCode getResponseCode();

    public abstract String getUri();

    public abstract Builder toBuilder();
}

