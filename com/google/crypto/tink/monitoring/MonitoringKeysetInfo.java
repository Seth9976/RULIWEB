package com.google.crypto.tink.monitoring;

import com.google.crypto.tink.KeyStatus;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

@Immutable
public final class MonitoringKeysetInfo {
    public static final class Builder {
        private MonitoringAnnotations builderAnnotations;
        @Nullable
        private ArrayList builderEntries;
        @Nullable
        private Integer builderPrimaryKeyId;

        public Builder() {
            this.builderEntries = new ArrayList();
            this.builderAnnotations = MonitoringAnnotations.EMPTY;
            this.builderPrimaryKeyId = null;
        }

        public Builder addEntry(KeyStatus keyStatus0, int v, String s, String s1) {
            ArrayList arrayList0 = this.builderEntries;
            if(arrayList0 == null) {
                throw new IllegalStateException("addEntry cannot be called after build()");
            }
            arrayList0.add(new Entry(keyStatus0, v, s, s1, null));
            return this;
        }

        public MonitoringKeysetInfo build() throws GeneralSecurityException {
            if(this.builderEntries == null) {
                throw new IllegalStateException("cannot call build() twice");
            }
            if(this.builderPrimaryKeyId != null && !this.isKeyIdInEntries(((int)this.builderPrimaryKeyId))) {
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            MonitoringKeysetInfo monitoringKeysetInfo0 = new MonitoringKeysetInfo(this.builderAnnotations, Collections.unmodifiableList(this.builderEntries), this.builderPrimaryKeyId, null);
            this.builderEntries = null;
            return monitoringKeysetInfo0;
        }

        private boolean isKeyIdInEntries(int v) {
            for(Object object0: this.builderEntries) {
                if(((Entry)object0).getKeyId() == v) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }

        public Builder setAnnotations(MonitoringAnnotations monitoringAnnotations0) {
            if(this.builderEntries == null) {
                throw new IllegalStateException("setAnnotations cannot be called after build()");
            }
            this.builderAnnotations = monitoringAnnotations0;
            return this;
        }

        public Builder setPrimaryKeyId(int v) {
            if(this.builderEntries == null) {
                throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
            }
            this.builderPrimaryKeyId = v;
            return this;
        }
    }

    @Immutable
    public static final class Entry {
        private final int keyId;
        private final String keyPrefix;
        private final String keyType;
        private final KeyStatus status;

        private Entry(KeyStatus keyStatus0, int v, String s, String s1) {
            this.status = keyStatus0;
            this.keyId = v;
            this.keyType = s;
            this.keyPrefix = s1;
        }

        Entry(KeyStatus keyStatus0, int v, String s, String s1, com.google.crypto.tink.monitoring.MonitoringKeysetInfo.1 monitoringKeysetInfo$10) {
            this(keyStatus0, v, s, s1);
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Entry ? this.status == ((Entry)object0).status && this.keyId == ((Entry)object0).keyId && this.keyType.equals(((Entry)object0).keyType) && this.keyPrefix.equals(((Entry)object0).keyPrefix) : false;
        }

        public int getKeyId() {
            return this.keyId;
        }

        public String getKeyPrefix() {
            return this.keyPrefix;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public KeyStatus getStatus() {
            return this.status;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.status, this.keyId, this.keyType, this.keyPrefix});
        }

        @Override
        public String toString() {
            return String.format("(status=%s, keyId=%s, keyType=\'%s\', keyPrefix=\'%s\')", this.status, this.keyId, this.keyType, this.keyPrefix);
        }
    }

    private final MonitoringAnnotations annotations;
    private final List entries;
    @Nullable
    private final Integer primaryKeyId;

    private MonitoringKeysetInfo(MonitoringAnnotations monitoringAnnotations0, List list0, Integer integer0) {
        this.annotations = monitoringAnnotations0;
        this.entries = list0;
        this.primaryKeyId = integer0;
    }

    MonitoringKeysetInfo(MonitoringAnnotations monitoringAnnotations0, List list0, Integer integer0, com.google.crypto.tink.monitoring.MonitoringKeysetInfo.1 monitoringKeysetInfo$10) {
        this(monitoringAnnotations0, list0, integer0);
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof MonitoringKeysetInfo ? this.annotations.equals(((MonitoringKeysetInfo)object0).annotations) && this.entries.equals(((MonitoringKeysetInfo)object0).entries) && Objects.equals(this.primaryKeyId, ((MonitoringKeysetInfo)object0).primaryKeyId) : false;
    }

    public MonitoringAnnotations getAnnotations() {
        return this.annotations;
    }

    public List getEntries() {
        return this.entries;
    }

    @Nullable
    public Integer getPrimaryKeyId() {
        return this.primaryKeyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.annotations, this.entries});
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.annotations, this.entries, this.primaryKeyId);
    }

    class com.google.crypto.tink.monitoring.MonitoringKeysetInfo.1 {
    }

}

