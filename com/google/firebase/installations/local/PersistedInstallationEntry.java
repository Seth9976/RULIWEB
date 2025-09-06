package com.google.firebase.installations.local;

public abstract class PersistedInstallationEntry {
    public static abstract class Builder {
        public abstract PersistedInstallationEntry build();

        public abstract Builder setAuthToken(String arg1);

        public abstract Builder setExpiresInSecs(long arg1);

        public abstract Builder setFirebaseInstallationId(String arg1);

        public abstract Builder setFisError(String arg1);

        public abstract Builder setRefreshToken(String arg1);

        public abstract Builder setRegistrationStatus(RegistrationStatus arg1);

        public abstract Builder setTokenCreationEpochInSecs(long arg1);
    }

    public static PersistedInstallationEntry INSTANCE;

    static {
        PersistedInstallationEntry.INSTANCE = PersistedInstallationEntry.builder().build();
    }

    public static Builder builder() {
        return new com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry.Builder().setTokenCreationEpochInSecs(0L).setRegistrationStatus(RegistrationStatus.ATTEMPT_MIGRATION).setExpiresInSecs(0L);
    }

    public abstract String getAuthToken();

    public abstract long getExpiresInSecs();

    public abstract String getFirebaseInstallationId();

    public abstract String getFisError();

    public abstract String getRefreshToken();

    public abstract RegistrationStatus getRegistrationStatus();

    public abstract long getTokenCreationEpochInSecs();

    public boolean isErrored() {
        return this.getRegistrationStatus() == RegistrationStatus.REGISTER_ERROR;
    }

    public boolean isNotGenerated() {
        return this.getRegistrationStatus() == RegistrationStatus.NOT_GENERATED || this.getRegistrationStatus() == RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public boolean isRegistered() {
        return this.getRegistrationStatus() == RegistrationStatus.REGISTERED;
    }

    public boolean isUnregistered() {
        return this.getRegistrationStatus() == RegistrationStatus.UNREGISTERED;
    }

    public boolean shouldAttemptMigration() {
        return this.getRegistrationStatus() == RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public abstract Builder toBuilder();

    public PersistedInstallationEntry withAuthToken(String s, long v, long v1) {
        return this.toBuilder().setAuthToken(s).setExpiresInSecs(v).setTokenCreationEpochInSecs(v1).build();
    }

    public PersistedInstallationEntry withClearedAuthToken() {
        return this.toBuilder().setAuthToken(null).build();
    }

    public PersistedInstallationEntry withFisError(String s) {
        return this.toBuilder().setFisError(s).setRegistrationStatus(RegistrationStatus.REGISTER_ERROR).build();
    }

    public PersistedInstallationEntry withNoGeneratedFid() {
        return this.toBuilder().setRegistrationStatus(RegistrationStatus.NOT_GENERATED).build();
    }

    public PersistedInstallationEntry withRegisteredFid(String s, String s1, long v, String s2, long v1) {
        return this.toBuilder().setFirebaseInstallationId(s).setRegistrationStatus(RegistrationStatus.REGISTERED).setAuthToken(s2).setRefreshToken(s1).setExpiresInSecs(v1).setTokenCreationEpochInSecs(v).build();
    }

    public PersistedInstallationEntry withUnregisteredFid(String s) {
        return this.toBuilder().setFirebaseInstallationId(s).setRegistrationStatus(RegistrationStatus.UNREGISTERED).build();
    }
}

