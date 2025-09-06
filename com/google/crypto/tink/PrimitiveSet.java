package com.google.crypto.tink;

import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Hex;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

public final class PrimitiveSet {
    public static class Builder {
        private MonitoringAnnotations annotations;
        private Entry primary;
        private final Class primitiveClass;
        private ConcurrentMap primitives;

        private Builder(Class class0) {
            this.primitives = new ConcurrentHashMap();
            this.primitiveClass = class0;
            this.annotations = MonitoringAnnotations.EMPTY;
        }

        Builder(Class class0, com.google.crypto.tink.PrimitiveSet.1 primitiveSet$10) {
            this(class0);
        }

        public Builder addFullPrimitiveAndOptionalPrimitive(@Nullable Object object0, @Nullable Object object1, Key keyset$Key0) throws GeneralSecurityException {
            return this.addPrimitive(object0, object1, keyset$Key0, false);
        }

        public Builder addPrimaryFullPrimitiveAndOptionalPrimitive(@Nullable Object object0, @Nullable Object object1, Key keyset$Key0) throws GeneralSecurityException {
            return this.addPrimitive(object0, object1, keyset$Key0, true);
        }

        public Builder addPrimaryPrimitive(Object object0, Key keyset$Key0) throws GeneralSecurityException {
            return this.addPrimitive(null, object0, keyset$Key0, true);
        }

        private Builder addPrimitive(@Nullable Object object0, @Nullable Object object1, Key keyset$Key0, boolean z) throws GeneralSecurityException {
            if(this.primitives == null) {
                throw new IllegalStateException("addPrimitive cannot be called after build");
            }
            if(object0 == null && object1 == null) {
                throw new GeneralSecurityException("at least one of the `fullPrimitive` or `primitive` must be set");
            }
            if(keyset$Key0.getStatus() != KeyStatusType.ENABLED) {
                throw new GeneralSecurityException("only ENABLED key is allowed");
            }
            Entry primitiveSet$Entry0 = PrimitiveSet.addEntryToMap(object0, object1, keyset$Key0, this.primitives);
            if(z) {
                if(this.primary != null) {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
                this.primary = primitiveSet$Entry0;
                return this;
            }
            return this;
        }

        public Builder addPrimitive(Object object0, Key keyset$Key0) throws GeneralSecurityException {
            return this.addPrimitive(null, object0, keyset$Key0, false);
        }

        public PrimitiveSet build() throws GeneralSecurityException {
            if(this.primitives == null) {
                throw new IllegalStateException("build cannot be called twice");
            }
            PrimitiveSet primitiveSet0 = new PrimitiveSet(this.primitives, this.primary, this.annotations, this.primitiveClass, null);
            this.primitives = null;
            return primitiveSet0;
        }

        public Builder setAnnotations(MonitoringAnnotations monitoringAnnotations0) {
            if(this.primitives == null) {
                throw new IllegalStateException("setAnnotations cannot be called after build");
            }
            this.annotations = monitoringAnnotations0;
            return this;
        }
    }

    public static final class Entry {
        @Nullable
        private final Object fullPrimitive;
        private final byte[] identifier;
        private final com.google.crypto.tink.Key key;
        private final int keyId;
        private final String keyType;
        private final OutputPrefixType outputPrefixType;
        @Nullable
        private final Object primitive;
        private final KeyStatusType status;

        Entry(@Nullable Object object0, @Nullable Object object1, byte[] arr_b, KeyStatusType keyStatusType0, OutputPrefixType outputPrefixType0, int v, String s, com.google.crypto.tink.Key key0) {
            this.fullPrimitive = object0;
            this.primitive = object1;
            this.identifier = Arrays.copyOf(arr_b, arr_b.length);
            this.status = keyStatusType0;
            this.outputPrefixType = outputPrefixType0;
            this.keyId = v;
            this.keyType = s;
            this.key = key0;
        }

        @Nullable
        public Object getFullPrimitive() {
            return this.fullPrimitive;
        }

        @Nullable
        public final byte[] getIdentifier() {
            return this.identifier == null ? null : Arrays.copyOf(this.identifier, this.identifier.length);
        }

        public com.google.crypto.tink.Key getKey() {
            return this.key;
        }

        public int getKeyId() {
            return this.keyId;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public OutputPrefixType getOutputPrefixType() {
            return this.outputPrefixType;
        }

        @Nullable
        public Parameters getParameters() {
            return this.key == null ? null : this.key.getParameters();
        }

        @Nullable
        public Object getPrimitive() {
            return this.primitive;
        }

        public KeyStatusType getStatus() {
            return this.status;
        }
    }

    static class Prefix implements Comparable {
        private final byte[] prefix;

        private Prefix(byte[] arr_b) {
            this.prefix = Arrays.copyOf(arr_b, arr_b.length);
        }

        Prefix(byte[] arr_b, com.google.crypto.tink.PrimitiveSet.1 primitiveSet$10) {
            this(arr_b);
        }

        public int compareTo(Prefix primitiveSet$Prefix0) {
            byte[] arr_b = this.prefix;
            byte[] arr_b1 = primitiveSet$Prefix0.prefix;
            if(arr_b.length != arr_b1.length) {
                return arr_b.length - arr_b1.length;
            }
            for(int v = 0; true; ++v) {
                byte[] arr_b2 = this.prefix;
                if(v >= arr_b2.length) {
                    break;
                }
                int v1 = arr_b2[v];
                int v2 = primitiveSet$Prefix0.prefix[v];
                if(v1 != v2) {
                    return v1 - v2;
                }
            }
            return 0;
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((Prefix)object0));
        }

        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Prefix ? Arrays.equals(this.prefix, ((Prefix)object0).prefix) : false;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(this.prefix);
        }

        @Override
        public String toString() {
            return Hex.encode(this.prefix);
        }
    }

    private final MonitoringAnnotations annotations;
    private final boolean isMutable;
    private Entry primary;
    private final Class primitiveClass;
    private final ConcurrentMap primitives;

    private PrimitiveSet(Class class0) {
        this.primitives = new ConcurrentHashMap();
        this.primitiveClass = class0;
        this.annotations = MonitoringAnnotations.EMPTY;
        this.isMutable = true;
    }

    private PrimitiveSet(ConcurrentMap concurrentMap0, Entry primitiveSet$Entry0, MonitoringAnnotations monitoringAnnotations0, Class class0) {
        this.primitives = concurrentMap0;
        this.primary = primitiveSet$Entry0;
        this.primitiveClass = class0;
        this.annotations = monitoringAnnotations0;
        this.isMutable = false;
    }

    PrimitiveSet(ConcurrentMap concurrentMap0, Entry primitiveSet$Entry0, MonitoringAnnotations monitoringAnnotations0, Class class0, com.google.crypto.tink.PrimitiveSet.1 primitiveSet$10) {
        this(concurrentMap0, primitiveSet$Entry0, monitoringAnnotations0, class0);
    }

    private static Entry addEntryToMap(@Nullable Object object0, @Nullable Object object1, Key keyset$Key0, ConcurrentMap concurrentMap0) throws GeneralSecurityException {
        Integer integer0 = keyset$Key0.getKeyId();
        if(keyset$Key0.getOutputPrefixType() == OutputPrefixType.RAW) {
            integer0 = null;
        }
        com.google.crypto.tink.Key key0 = MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(ProtoKeySerialization.create("", keyset$Key0.getKeyData().getValue(), keyset$Key0.getKeyData().getKeyMaterialType(), keyset$Key0.getOutputPrefixType(), integer0), InsecureSecretKeyAccess.get());
        Entry primitiveSet$Entry0 = new Entry(object0, object1, CryptoFormat.getOutputPrefix(keyset$Key0), keyset$Key0.getStatus(), keyset$Key0.getOutputPrefixType(), keyset$Key0.getKeyId(), "", key0);
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(primitiveSet$Entry0);
        Prefix primitiveSet$Prefix0 = new Prefix(primitiveSet$Entry0.getIdentifier(), null);
        List list0 = (List)concurrentMap0.put(primitiveSet$Prefix0, Collections.unmodifiableList(arrayList0));
        if(list0 != null) {
            ArrayList arrayList1 = new ArrayList();
            arrayList1.addAll(list0);
            arrayList1.add(primitiveSet$Entry0);
            concurrentMap0.put(primitiveSet$Prefix0, Collections.unmodifiableList(arrayList1));
        }
        return primitiveSet$Entry0;
    }

    @Deprecated
    public Entry addPrimitive(Object object0, Key keyset$Key0) throws GeneralSecurityException {
        if(!this.isMutable) {
            throw new IllegalStateException("addPrimitive cannot be called on an immutable primitive set");
        }
        if(keyset$Key0.getStatus() != KeyStatusType.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        return PrimitiveSet.addEntryToMap(null, object0, keyset$Key0, this.primitives);
    }

    public Collection getAll() {
        return this.primitives.values();
    }

    public MonitoringAnnotations getAnnotations() {
        return this.annotations;
    }

    @Nullable
    public Entry getPrimary() {
        return this.primary;
    }

    public List getPrimitive(byte[] arr_b) {
        Prefix primitiveSet$Prefix0 = new Prefix(arr_b, null);
        List list0 = (List)this.primitives.get(primitiveSet$Prefix0);
        return list0 == null ? Collections.EMPTY_LIST : list0;
    }

    public Class getPrimitiveClass() {
        return this.primitiveClass;
    }

    public List getRawPrimitives() {
        return this.getPrimitive(CryptoFormat.RAW_PREFIX);
    }

    public boolean hasAnnotations() {
        return !this.annotations.toMap().isEmpty();
    }

    public static Builder newBuilder(Class class0) {
        return new Builder(class0, null);
    }

    @Deprecated
    public static PrimitiveSet newPrimitiveSet(Class class0) {
        return new PrimitiveSet(class0);
    }

    @Deprecated
    public void setPrimary(Entry primitiveSet$Entry0) {
        if(!this.isMutable) {
            throw new IllegalStateException("setPrimary cannot be called on an immutable primitive set");
        }
        if(primitiveSet$Entry0 == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        }
        if(primitiveSet$Entry0.getStatus() != KeyStatusType.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        if(this.getPrimitive(primitiveSet$Entry0.getIdentifier()).isEmpty()) {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
        this.primary = primitiveSet$Entry0;
    }

    class com.google.crypto.tink.PrimitiveSet.1 {
    }

}

