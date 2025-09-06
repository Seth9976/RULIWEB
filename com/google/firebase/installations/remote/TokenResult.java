package com.google.firebase.installations.remote;

public abstract class TokenResult {
    public static abstract class Builder {
        public abstract TokenResult build();

        public abstract Builder setResponseCode(ResponseCode arg1);

        public abstract Builder setToken(String arg1);

        public abstract Builder setTokenExpirationTimestamp(long arg1);
    }

    public static enum ResponseCode {
        OK,
        BAD_CONFIG,
        AUTH_ERROR;

    }

    public static Builder builder() {
        return new com.google.firebase.installations.remote.AutoValue_TokenResult.Builder().setTokenExpirationTimestamp(0L);
    }

    public abstract ResponseCode getResponseCode();

    public abstract String getToken();

    public abstract long getTokenExpirationTimestamp();

    public abstract Builder toBuilder();
}

