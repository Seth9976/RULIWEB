package com.google.firebase.installations.local;

final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    static final class Builder extends com.google.firebase.installations.local.PersistedInstallationEntry.Builder {
        private String authToken;
        private Long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private RegistrationStatus registrationStatus;
        private Long tokenCreationEpochInSecs;

        Builder() {
        }

        private Builder(PersistedInstallationEntry persistedInstallationEntry0) {
            this.firebaseInstallationId = persistedInstallationEntry0.getFirebaseInstallationId();
            this.registrationStatus = persistedInstallationEntry0.getRegistrationStatus();
            this.authToken = persistedInstallationEntry0.getAuthToken();
            this.refreshToken = persistedInstallationEntry0.getRefreshToken();
            this.expiresInSecs = persistedInstallationEntry0.getExpiresInSecs();
            this.tokenCreationEpochInSecs = persistedInstallationEntry0.getTokenCreationEpochInSecs();
            this.fisError = persistedInstallationEntry0.getFisError();
        }

        Builder(PersistedInstallationEntry persistedInstallationEntry0, com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry.1 autoValue_PersistedInstallationEntry$10) {
            this(persistedInstallationEntry0);
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public PersistedInstallationEntry build() {
            String s = this.registrationStatus == null ? " registrationStatus" : "";
            if(this.expiresInSecs == null) {
                s = s + " expiresInSecs";
            }
            if(this.tokenCreationEpochInSecs == null) {
                s = s + " tokenCreationEpochInSecs";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, ((long)this.expiresInSecs), ((long)this.tokenCreationEpochInSecs), this.fisError, null);
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setAuthToken(String s) {
            this.authToken = s;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setExpiresInSecs(long v) {
            this.expiresInSecs = v;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setFirebaseInstallationId(String s) {
            this.firebaseInstallationId = s;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setFisError(String s) {
            this.fisError = s;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setRefreshToken(String s) {
            this.refreshToken = s;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setRegistrationStatus(RegistrationStatus persistedInstallation$RegistrationStatus0) {
            if(persistedInstallation$RegistrationStatus0 == null) {
                throw new NullPointerException("Null registrationStatus");
            }
            this.registrationStatus = persistedInstallation$RegistrationStatus0;
            return this;
        }

        @Override  // com.google.firebase.installations.local.PersistedInstallationEntry$Builder
        public com.google.firebase.installations.local.PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long v) {
            this.tokenCreationEpochInSecs = v;
            return this;
        }
    }

    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    private AutoValue_PersistedInstallationEntry(String s, RegistrationStatus persistedInstallation$RegistrationStatus0, String s1, String s2, long v, long v1, String s3) {
        this.firebaseInstallationId = s;
        this.registrationStatus = persistedInstallation$RegistrationStatus0;
        this.authToken = s1;
        this.refreshToken = s2;
        this.expiresInSecs = v;
        this.tokenCreationEpochInSecs = v1;
        this.fisError = s3;
    }

    AutoValue_PersistedInstallationEntry(String s, RegistrationStatus persistedInstallation$RegistrationStatus0, String s1, String s2, long v, long v1, String s3, com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry.1 autoValue_PersistedInstallationEntry$10) {
        this(s, persistedInstallation$RegistrationStatus0, s1, s2, v, v1, s3);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof PersistedInstallationEntry) {
            String s = this.firebaseInstallationId;
            if(s != null) {
                if(s.equals(((PersistedInstallationEntry)object0).getFirebaseInstallationId())) {
                label_8:
                    RegistrationStatus persistedInstallation$RegistrationStatus0 = ((PersistedInstallationEntry)object0).getRegistrationStatus();
                    if(this.registrationStatus.equals(persistedInstallation$RegistrationStatus0)) {
                        String s1 = this.authToken;
                        if(s1 != null) {
                            if(s1.equals(((PersistedInstallationEntry)object0).getAuthToken())) {
                            label_15:
                                String s2 = this.refreshToken;
                                if(s2 != null) {
                                    if(s2.equals(((PersistedInstallationEntry)object0).getRefreshToken())) {
                                    label_20:
                                        long v = ((PersistedInstallationEntry)object0).getExpiresInSecs();
                                        if(this.expiresInSecs == v) {
                                            long v1 = ((PersistedInstallationEntry)object0).getTokenCreationEpochInSecs();
                                            if(this.tokenCreationEpochInSecs == v1) {
                                                return this.fisError == null ? ((PersistedInstallationEntry)object0).getFisError() == null : this.fisError.equals(((PersistedInstallationEntry)object0).getFisError());
                                            }
                                        }
                                    }
                                }
                                else if(((PersistedInstallationEntry)object0).getRefreshToken() == null) {
                                    goto label_20;
                                }
                            }
                        }
                        else if(((PersistedInstallationEntry)object0).getAuthToken() == null) {
                            goto label_15;
                        }
                    }
                }
            }
            else if(((PersistedInstallationEntry)object0).getFirebaseInstallationId() == null) {
                goto label_8;
            }
        }
        return false;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getAuthToken() {
        return this.authToken;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFisError() {
        return this.fisError;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = (((((((this.firebaseInstallationId == null ? 0 : this.firebaseInstallationId.hashCode()) ^ 1000003) * 1000003 ^ this.registrationStatus.hashCode()) * 1000003 ^ (this.authToken == null ? 0 : this.authToken.hashCode())) * 1000003 ^ (this.refreshToken == null ? 0 : this.refreshToken.hashCode())) * 1000003 ^ ((int)(this.expiresInSecs ^ this.expiresInSecs >>> 0x20))) * 1000003 ^ ((int)(this.tokenCreationEpochInSecs ^ this.tokenCreationEpochInSecs >>> 0x20))) * 1000003;
        String s = this.fisError;
        if(s != null) {
            v = s.hashCode();
        }
        return v1 ^ v;
    }

    @Override  // com.google.firebase.installations.local.PersistedInstallationEntry
    public com.google.firebase.installations.local.PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this, null);
    }

    @Override
    public String toString() {
        return "PersistedInstallationEntry{firebaseInstallationId=" + this.firebaseInstallationId + ", registrationStatus=" + this.registrationStatus + ", authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", expiresInSecs=" + this.expiresInSecs + ", tokenCreationEpochInSecs=" + this.tokenCreationEpochInSecs + ", fisError=" + this.fisError + "}";
    }

    class com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry.1 {
    }

}

