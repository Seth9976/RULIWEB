package com.google.firebase.installations;

public abstract class InstallationTokenResult {
    public static abstract class Builder {
        public abstract InstallationTokenResult build();

        public abstract Builder setToken(String arg1);

        public abstract Builder setTokenCreationTimestamp(long arg1);

        public abstract Builder setTokenExpirationTimestamp(long arg1);
    }

    public static Builder builder() {
        return new com.google.firebase.installations.AutoValue_InstallationTokenResult.Builder();
    }

    public abstract String getToken();

    public abstract long getTokenCreationTimestamp();

    public abstract long getTokenExpirationTimestamp();

    public abstract Builder toBuilder();
}

