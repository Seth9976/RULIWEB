package com.google.crypto.tink;

import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.LegacyProtoParameters;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.TinkBugException;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.internal.InternalKeyHandle;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public final class KeysetHandle {
    public static final class Builder {
        public static final class Entry {
            @Nullable
            private Builder builder;
            private boolean isPrimary;
            @Nullable
            private final Key key;
            private KeyStatus keyStatus;
            @Nullable
            private final Parameters parameters;
            private KeyIdStrategy strategy;

            private Entry(Key key0) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = key0;
                this.parameters = null;
            }

            Entry(Key key0, com.google.crypto.tink.KeysetHandle.1 keysetHandle$10) {
                this(key0);
            }

            private Entry(Parameters parameters0) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = null;
                this.parameters = parameters0;
            }

            Entry(Parameters parameters0, com.google.crypto.tink.KeysetHandle.1 keysetHandle$10) {
                this(parameters0);
            }

            static KeyStatus access$1200(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.keyStatus;
            }

            static boolean access$300(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.isPrimary;
            }

            static boolean access$302(Entry keysetHandle$Builder$Entry0, boolean z) {
                keysetHandle$Builder$Entry0.isPrimary = z;
                return z;
            }

            static Builder access$400(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.builder;
            }

            static Builder access$402(Entry keysetHandle$Builder$Entry0, Builder keysetHandle$Builder0) {
                keysetHandle$Builder$Entry0.builder = keysetHandle$Builder0;
                return keysetHandle$Builder0;
            }

            static KeyIdStrategy access$500(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.strategy;
            }

            static Key access$800(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.key;
            }

            static Parameters access$900(Entry keysetHandle$Builder$Entry0) {
                return keysetHandle$Builder$Entry0.parameters;
            }

            public KeyStatus getStatus() {
                return this.keyStatus;
            }

            public boolean isPrimary() {
                return this.isPrimary;
            }

            public Entry makePrimary() {
                Builder keysetHandle$Builder0 = this.builder;
                if(keysetHandle$Builder0 != null) {
                    keysetHandle$Builder0.clearPrimary();
                }
                this.isPrimary = true;
                return this;
            }

            public Entry setStatus(KeyStatus keyStatus0) {
                this.keyStatus = keyStatus0;
                return this;
            }

            public Entry withFixedId(int v) {
                this.strategy = KeyIdStrategy.access$100(v);
                return this;
            }

            public Entry withRandomId() {
                this.strategy = KeyIdStrategy.access$200();
                return this;
            }
        }

        static class KeyIdStrategy {
            private static final KeyIdStrategy RANDOM_ID;
            private final int fixedId;

            static {
                KeyIdStrategy.RANDOM_ID = new KeyIdStrategy();
            }

            private KeyIdStrategy() {
                this.fixedId = 0;
            }

            private KeyIdStrategy(int v) {
                this.fixedId = v;
            }

            static KeyIdStrategy access$100(int v) {
                return KeyIdStrategy.fixedId(v);
            }

            static KeyIdStrategy access$200() {
                return KeyIdStrategy.randomId();
            }

            static KeyIdStrategy access$600() {
                return KeyIdStrategy.RANDOM_ID;
            }

            static int access$700(KeyIdStrategy keysetHandle$Builder$KeyIdStrategy0) {
                return keysetHandle$Builder$KeyIdStrategy0.getFixedId();
            }

            private static KeyIdStrategy fixedId(int v) {
                return new KeyIdStrategy(v);
            }

            private int getFixedId() {
                return this.fixedId;
            }

            private static KeyIdStrategy randomId() {
                return KeyIdStrategy.RANDOM_ID;
            }
        }

        private final List entries;

        public Builder() {
            this.entries = new ArrayList();
        }

        public Builder addEntry(Entry keysetHandle$Builder$Entry0) {
            if(Entry.access$400(keysetHandle$Builder$Entry0) != null) {
                throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
            }
            if(Entry.access$300(keysetHandle$Builder$Entry0)) {
                this.clearPrimary();
            }
            Entry.access$402(keysetHandle$Builder$Entry0, this);
            this.entries.add(keysetHandle$Builder$Entry0);
            return this;
        }

        public KeysetHandle build() throws GeneralSecurityException {
            com.google.crypto.tink.proto.Keyset.Builder keyset$Builder0 = Keyset.newBuilder();
            Builder.checkIdAssignments(this.entries);
            HashSet hashSet0 = new HashSet();
            Integer integer0 = null;
            for(Object object0: this.entries) {
                if(Entry.access$1200(((Entry)object0)) == null) {
                    throw new GeneralSecurityException("Key Status not set.");
                }
                int v = Builder.getNextIdFromBuilderEntry(((Entry)object0), hashSet0);
                if(hashSet0.contains(v)) {
                    throw new GeneralSecurityException("Id " + v + " is used twice in the keyset");
                }
                hashSet0.add(v);
                keyset$Builder0.addKey(Builder.createKeysetKeyFromBuilderEntry(((Entry)object0), v));
                if(Entry.access$300(((Entry)object0))) {
                    if(integer0 != null) {
                        throw new GeneralSecurityException("Two primaries were set");
                    }
                    integer0 = v;
                }
            }
            if(integer0 == null) {
                throw new GeneralSecurityException("No primary was set");
            }
            keyset$Builder0.setPrimaryKeyId(((int)integer0));
            return KeysetHandle.fromKeyset(((Keyset)keyset$Builder0.build()));
        }

        private static void checkIdAssignments(List list0) throws GeneralSecurityException {
            for(int v = 0; v < list0.size() - 1; ++v) {
                if(Entry.access$500(((Entry)list0.get(v))) == KeyIdStrategy.access$600() && Entry.access$500(((Entry)list0.get(v + 1))) != KeyIdStrategy.access$600()) {
                    throw new GeneralSecurityException("Entries with \'withRandomId()\' may only be followed by other entries with \'withRandomId()\'.");
                }
            }
        }

        private void clearPrimary() {
            for(Object object0: this.entries) {
                Entry.access$302(((Entry)object0), false);
            }
        }

        private static com.google.crypto.tink.proto.Keyset.Key createKeyFromParameters(Parameters parameters0, int v, KeyStatusType keyStatusType0) throws GeneralSecurityException {
            ProtoParametersSerialization protoParametersSerialization0 = parameters0 instanceof LegacyProtoParameters ? ((LegacyProtoParameters)parameters0).getSerialization() : ((ProtoParametersSerialization)MutableSerializationRegistry.globalInstance().serializeParameters(parameters0, ProtoParametersSerialization.class));
            KeyData keyData0 = Registry.newKeyData(protoParametersSerialization0.getKeyTemplate());
            return (com.google.crypto.tink.proto.Keyset.Key)com.google.crypto.tink.proto.Keyset.Key.newBuilder().setKeyId(v).setStatus(keyStatusType0).setKeyData(keyData0).setOutputPrefixType(protoParametersSerialization0.getKeyTemplate().getOutputPrefixType()).build();
        }

        private static com.google.crypto.tink.proto.Keyset.Key createKeysetKeyFromBuilderEntry(Entry keysetHandle$Builder$Entry0, int v) throws GeneralSecurityException {
            if(Entry.access$800(keysetHandle$Builder$Entry0) == null) {
                return Builder.createKeyFromParameters(Entry.access$900(keysetHandle$Builder$Entry0), v, KeysetHandle.serializeStatus(keysetHandle$Builder$Entry0.getStatus()));
            }
            ProtoKeySerialization protoKeySerialization0 = Entry.access$800(keysetHandle$Builder$Entry0) instanceof LegacyProtoKey ? ((LegacyProtoKey)Entry.access$800(keysetHandle$Builder$Entry0)).getSerialization(InsecureSecretKeyAccess.get()) : ((ProtoKeySerialization)MutableSerializationRegistry.globalInstance().serializeKey(Entry.access$800(keysetHandle$Builder$Entry0), ProtoKeySerialization.class, InsecureSecretKeyAccess.get()));
            Integer integer0 = protoKeySerialization0.getIdRequirementOrNull();
            if(integer0 != null && ((int)integer0) != v) {
                throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
            }
            return KeysetHandle.toKeysetKey(v, KeysetHandle.serializeStatus(keysetHandle$Builder$Entry0.getStatus()), protoKeySerialization0);
        }

        public Builder deleteAt(int v) {
            this.entries.remove(v);
            return this;
        }

        public Entry getAt(int v) {
            return (Entry)this.entries.get(v);
        }

        private static int getNextIdFromBuilderEntry(Entry keysetHandle$Builder$Entry0, Set set0) throws GeneralSecurityException {
            if(Entry.access$500(keysetHandle$Builder$Entry0) == null) {
                throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
            }
            return Entry.access$500(keysetHandle$Builder$Entry0) == KeyIdStrategy.access$600() ? Builder.randomIdNotInSet(set0) : KeyIdStrategy.access$700(Entry.access$500(keysetHandle$Builder$Entry0));
        }

        private static int randomIdNotInSet(Set set0) {
            int v;
            for(v = 0; v == 0 || set0.contains(v); v = Util.randKeyId()) {
            }
            return v;
        }

        @Deprecated
        public Entry removeAt(int v) {
            return (Entry)this.entries.remove(v);
        }

        public int size() {
            return this.entries.size();
        }
    }

    @Immutable
    public static final class com.google.crypto.tink.KeysetHandle.Entry {
        private final int id;
        private final boolean isPrimary;
        private final Key key;
        private final KeyStatus keyStatus;

        private com.google.crypto.tink.KeysetHandle.Entry(Key key0, KeyStatus keyStatus0, int v, boolean z) {
            this.key = key0;
            this.keyStatus = keyStatus0;
            this.id = v;
            this.isPrimary = z;
        }

        com.google.crypto.tink.KeysetHandle.Entry(Key key0, KeyStatus keyStatus0, int v, boolean z, com.google.crypto.tink.KeysetHandle.1 keysetHandle$10) {
            this(key0, keyStatus0, v, z);
        }

        public int getId() {
            return this.id;
        }

        public Key getKey() {
            return this.key;
        }

        public KeyStatus getStatus() {
            return this.keyStatus;
        }

        public boolean isPrimary() {
            return this.isPrimary;
        }
    }

    private final MonitoringAnnotations annotations;
    private final List entries;
    private final Keyset keyset;

    private KeysetHandle(Keyset keyset0, List list0) {
        this.keyset = keyset0;
        this.entries = list0;
        this.annotations = MonitoringAnnotations.EMPTY;
    }

    private KeysetHandle(Keyset keyset0, List list0, MonitoringAnnotations monitoringAnnotations0) {
        this.keyset = keyset0;
        this.entries = list0;
        this.annotations = monitoringAnnotations0;
    }

    private static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset encryptedKeyset0) throws GeneralSecurityException {
        if(encryptedKeyset0 == null || encryptedKeyset0.getEncryptedKeyset().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void assertEnoughKeyMaterial(Keyset keyset0) throws GeneralSecurityException {
        if(keyset0 == null || keyset0.getKeyCount() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void assertNoSecretKeyMaterial(Keyset keyset0) throws GeneralSecurityException {
        for(Object object0: keyset0.getKeyList()) {
            com.google.crypto.tink.proto.Keyset.Key keyset$Key0 = (com.google.crypto.tink.proto.Keyset.Key)object0;
            if(keyset$Key0.getKeyData().getKeyMaterialType() == KeyMaterialType.UNKNOWN_KEYMATERIAL || keyset$Key0.getKeyData().getKeyMaterialType() == KeyMaterialType.SYMMETRIC || keyset$Key0.getKeyData().getKeyMaterialType() == KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", keyset$Key0.getKeyData().getKeyMaterialType().name(), ""));
            }
            if(false) {
                break;
            }
        }
    }

    @Deprecated
    public static final KeysetHandle createFromKey(KeyHandle keyHandle0, KeyAccess keyAccess0) throws GeneralSecurityException {
        KeysetManager keysetManager0 = KeysetManager.withEmptyKeyset().add(keyHandle0);
        keysetManager0.setPrimary(keysetManager0.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
        return keysetManager0.getKeysetHandle();
    }

    private static KeyData createPublicKeyData(KeyData keyData0) throws GeneralSecurityException {
        if(keyData0.getKeyMaterialType() != KeyMaterialType.ASYMMETRIC_PRIVATE) {
            throw new GeneralSecurityException("The keyset contains a non-private key");
        }
        KeyData keyData1 = Registry.getPublicKeyData("", keyData0.getValue());
        KeysetHandle.validate(keyData1);
        return keyData1;
    }

    private static Keyset decrypt(EncryptedKeyset encryptedKeyset0, Aead aead0, byte[] arr_b) throws GeneralSecurityException {
        try {
            Keyset keyset0 = Keyset.parseFrom(aead0.decrypt(encryptedKeyset0.getEncryptedKeyset().toByteArray(), arr_b), ExtensionRegistryLite.getEmptyRegistry());
            KeysetHandle.assertEnoughKeyMaterial(keyset0);
            return keyset0;
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static EncryptedKeyset encrypt(Keyset keyset0, Aead aead0, byte[] arr_b) throws GeneralSecurityException {
        byte[] arr_b1 = aead0.encrypt(keyset0.toByteArray(), arr_b);
        try {
            if(!Keyset.parseFrom(aead0.decrypt(arr_b1, arr_b), ExtensionRegistryLite.getEmptyRegistry()).equals(keyset0)) {
                throw new GeneralSecurityException("cannot encrypt keyset");
            }
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
        return (EncryptedKeyset)EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(arr_b1)).setKeysetInfo(com.google.crypto.tink.Util.getKeysetInfo(keyset0)).build();
    }

    private com.google.crypto.tink.KeysetHandle.Entry entryByIndex(int v) {
        if(this.entries.get(v) == null) {
            throw new IllegalStateException("Keyset-Entry at position i has wrong status or key parsing failed");
        }
        return (com.google.crypto.tink.KeysetHandle.Entry)this.entries.get(v);
    }

    static final KeysetHandle fromKeyset(Keyset keyset0) throws GeneralSecurityException {
        KeysetHandle.assertEnoughKeyMaterial(keyset0);
        return new KeysetHandle(keyset0, KeysetHandle.getEntriesFromKeyset(keyset0));
    }

    static final KeysetHandle fromKeysetAndAnnotations(Keyset keyset0, MonitoringAnnotations monitoringAnnotations0) throws GeneralSecurityException {
        KeysetHandle.assertEnoughKeyMaterial(keyset0);
        return new KeysetHandle(keyset0, KeysetHandle.getEntriesFromKeyset(keyset0), monitoringAnnotations0);
    }

    public static Entry generateEntryFromParameters(Parameters parameters0) {
        return new Entry(parameters0, null);
    }

    public static Entry generateEntryFromParametersName(String s) throws GeneralSecurityException {
        if(!Registry.keyTemplateMap().containsKey(s)) {
            throw new GeneralSecurityException("cannot find key template: " + s);
        }
        return new Entry(MutableSerializationRegistry.globalInstance().parseParametersWithLegacyFallback(ProtoParametersSerialization.create(((KeyTemplate)Registry.keyTemplateMap().get(s)).getProto())), null);
    }

    public static final KeysetHandle generateNew(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        LegacyProtoParameters legacyProtoParameters0 = new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate0.getProto()));
        return KeysetHandle.newBuilder().addEntry(KeysetHandle.generateEntryFromParameters(legacyProtoParameters0).makePrimary().withRandomId()).build();
    }

    @Deprecated
    public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        LegacyProtoParameters legacyProtoParameters0 = new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate0));
        return KeysetHandle.newBuilder().addEntry(KeysetHandle.generateEntryFromParameters(legacyProtoParameters0).makePrimary().withRandomId()).build();
    }

    public com.google.crypto.tink.KeysetHandle.Entry getAt(int v) {
        if(v < 0 || v >= this.size()) {
            throw new IndexOutOfBoundsException("Invalid index " + v + " for keyset of size " + this.size());
        }
        return this.entryByIndex(v);
    }

    private static List getEntriesFromKeyset(Keyset keyset0) {
        ArrayList arrayList0 = new ArrayList(keyset0.getKeyCount());
        for(Object object0: keyset0.getKeyList()) {
            com.google.crypto.tink.proto.Keyset.Key keyset$Key0 = (com.google.crypto.tink.proto.Keyset.Key)object0;
            int v = keyset$Key0.getKeyId();
            ProtoKeySerialization protoKeySerialization0 = KeysetHandle.toProtoKeySerialization(keyset$Key0);
            try {
                arrayList0.add(new com.google.crypto.tink.KeysetHandle.Entry(MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(protoKeySerialization0, InsecureSecretKeyAccess.get()), KeysetHandle.parseStatus(keyset$Key0.getStatus()), v, v == keyset0.getPrimaryKeyId(), null));
            }
            catch(GeneralSecurityException unused_ex) {
                arrayList0.add(null);
            }
        }
        return Collections.unmodifiableList(arrayList0);
    }

    @Nullable
    private Object getFullPrimitiveOrNull(Key key0, Class class0) throws GeneralSecurityException {
        try {
            return Registry.getFullPrimitive(key0, class0);
        }
        catch(GeneralSecurityException unused_ex) {
            return null;
        }
    }

    @Deprecated
    public List getKeys() {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.keyset.getKeyList()) {
            arrayList0.add(new InternalKeyHandle(new ProtoKey(((com.google.crypto.tink.proto.Keyset.Key)object0).getKeyData(), KeyTemplate.fromProto(((com.google.crypto.tink.proto.Keyset.Key)object0).getOutputPrefixType())), ((com.google.crypto.tink.proto.Keyset.Key)object0).getStatus(), ((com.google.crypto.tink.proto.Keyset.Key)object0).getKeyId()));
        }
        return Collections.unmodifiableList(arrayList0);
    }

    Keyset getKeyset() {
        return this.keyset;
    }

    public KeysetInfo getKeysetInfo() {
        return com.google.crypto.tink.Util.getKeysetInfo(this.keyset);
    }

    @Nullable
    private static Object getLegacyPrimitiveOrNull(com.google.crypto.tink.proto.Keyset.Key keyset$Key0, Class class0) throws GeneralSecurityException {
        try {
            return Registry.getPrimitive(keyset$Key0.getKeyData(), class0);
        }
        catch(GeneralSecurityException generalSecurityException0) {
            if(!generalSecurityException0.getMessage().contains("No key manager found for key type ") && !generalSecurityException0.getMessage().contains(" not supported by key manager of type ")) {
                throw generalSecurityException0;
            }
            return null;
        }
    }

    public com.google.crypto.tink.KeysetHandle.Entry getPrimary() {
        for(int v = 0; v < this.keyset.getKeyCount(); ++v) {
            if(this.keyset.getKey(v).getKeyId() == this.keyset.getPrimaryKeyId()) {
                com.google.crypto.tink.KeysetHandle.Entry keysetHandle$Entry0 = this.entryByIndex(v);
                if(keysetHandle$Entry0.getStatus() != KeyStatus.ENABLED) {
                    throw new IllegalStateException("Keyset has primary which isn\'t enabled");
                }
                return keysetHandle$Entry0;
            }
        }
        throw new IllegalStateException("Keyset has no primary");
    }

    public Object getPrimitive(Class class0) throws GeneralSecurityException {
        Class class1 = Registry.getInputPrimitive(class0);
        if(class1 == null) {
            throw new GeneralSecurityException("No wrapper found for " + class0.getName());
        }
        return this.getPrimitiveWithKnownInputPrimitive(class0, class1);
    }

    private Object getPrimitiveWithKnownInputPrimitive(Class class0, Class class1) throws GeneralSecurityException {
        com.google.crypto.tink.Util.validateKeyset(this.keyset);
        com.google.crypto.tink.PrimitiveSet.Builder primitiveSet$Builder0 = PrimitiveSet.newBuilder(class1);
        primitiveSet$Builder0.setAnnotations(this.annotations);
        for(int v = 0; v < this.size(); ++v) {
            com.google.crypto.tink.proto.Keyset.Key keyset$Key0 = this.keyset.getKey(v);
            if(keyset$Key0.getStatus().equals(KeyStatusType.ENABLED)) {
                Object object0 = KeysetHandle.getLegacyPrimitiveOrNull(keyset$Key0, class1);
                Object object1 = this.entries.get(v) == null ? null : this.getFullPrimitiveOrNull(((com.google.crypto.tink.KeysetHandle.Entry)this.entries.get(v)).getKey(), class1);
                if(keyset$Key0.getKeyId() == this.keyset.getPrimaryKeyId()) {
                    primitiveSet$Builder0.addPrimaryFullPrimitiveAndOptionalPrimitive(object1, object0, keyset$Key0);
                }
                else {
                    primitiveSet$Builder0.addFullPrimitiveAndOptionalPrimitive(object1, object0, keyset$Key0);
                }
            }
        }
        return Registry.wrap(primitiveSet$Builder0.build(), class0);
    }

    public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
        if(this.keyset == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        com.google.crypto.tink.proto.Keyset.Builder keyset$Builder0 = Keyset.newBuilder();
        for(Object object0: this.keyset.getKeyList()) {
            KeyData keyData0 = KeysetHandle.createPublicKeyData(((com.google.crypto.tink.proto.Keyset.Key)object0).getKeyData());
            keyset$Builder0.addKey(((com.google.crypto.tink.proto.Keyset.Key)((com.google.crypto.tink.proto.Keyset.Key.Builder)((com.google.crypto.tink.proto.Keyset.Key)object0).toBuilder()).setKeyData(keyData0).build()));
        }
        keyset$Builder0.setPrimaryKeyId(this.keyset.getPrimaryKeyId());
        return KeysetHandle.fromKeyset(((Keyset)keyset$Builder0.build()));
    }

    public static Entry importKey(Key key0) {
        Entry keysetHandle$Builder$Entry0 = new Entry(key0, null);
        Integer integer0 = key0.getIdRequirementOrNull();
        if(integer0 != null) {
            keysetHandle$Builder$Entry0.withFixedId(((int)integer0));
        }
        return keysetHandle$Builder$Entry0;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(KeysetHandle keysetHandle0) {
        Builder keysetHandle$Builder0 = new Builder();
        for(int v = 0; v < keysetHandle0.size(); ++v) {
            com.google.crypto.tink.KeysetHandle.Entry keysetHandle$Entry0 = keysetHandle0.entryByIndex(v);
            Entry keysetHandle$Builder$Entry0 = KeysetHandle.importKey(keysetHandle$Entry0.getKey()).withFixedId(keysetHandle$Entry0.getId());
            keysetHandle$Builder$Entry0.setStatus(keysetHandle$Entry0.getStatus());
            if(keysetHandle$Entry0.isPrimary()) {
                keysetHandle$Builder$Entry0.makePrimary();
            }
            keysetHandle$Builder0.addEntry(keysetHandle$Builder$Entry0);
        }
        return keysetHandle$Builder0;
    }

    private static KeyStatus parseStatus(KeyStatusType keyStatusType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.KeysetHandle.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType0.ordinal()]) {
            case 1: {
                return KeyStatus.ENABLED;
            }
            case 2: {
                return KeyStatus.DISABLED;
            }
            case 3: {
                return KeyStatus.DESTROYED;
            }
            default: {
                throw new GeneralSecurityException("Unknown key status");
            }
        }
    }

    @Deprecated
    public KeyHandle primaryKey() throws GeneralSecurityException {
        int v = this.keyset.getPrimaryKeyId();
        for(Object object0: this.keyset.getKeyList()) {
            com.google.crypto.tink.proto.Keyset.Key keyset$Key0 = (com.google.crypto.tink.proto.Keyset.Key)object0;
            if(keyset$Key0.getKeyId() == v) {
                return new InternalKeyHandle(new ProtoKey(keyset$Key0.getKeyData(), KeyTemplate.fromProto(keyset$Key0.getOutputPrefixType())), keyset$Key0.getStatus(), keyset$Key0.getKeyId());
            }
            if(false) {
                break;
            }
        }
        throw new GeneralSecurityException("No primary key found in keyset.");
    }

    public static final KeysetHandle read(KeysetReader keysetReader0, Aead aead0) throws GeneralSecurityException, IOException {
        return KeysetHandle.readWithAssociatedData(keysetReader0, aead0, new byte[0]);
    }

    public static final KeysetHandle readNoSecret(KeysetReader keysetReader0) throws GeneralSecurityException, IOException {
        try {
            Keyset keyset0 = keysetReader0.read();
            KeysetHandle.assertNoSecretKeyMaterial(keyset0);
            return KeysetHandle.fromKeyset(keyset0);
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    @Deprecated
    public static final KeysetHandle readNoSecret(byte[] arr_b) throws GeneralSecurityException {
        try {
            Keyset keyset0 = Keyset.parseFrom(arr_b, ExtensionRegistryLite.getEmptyRegistry());
            KeysetHandle.assertNoSecretKeyMaterial(keyset0);
            return KeysetHandle.fromKeyset(keyset0);
        }
        catch(InvalidProtocolBufferException unused_ex) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle readWithAssociatedData(KeysetReader keysetReader0, Aead aead0, byte[] arr_b) throws GeneralSecurityException, IOException {
        EncryptedKeyset encryptedKeyset0 = keysetReader0.readEncrypted();
        KeysetHandle.assertEnoughEncryptedKeyMaterial(encryptedKeyset0);
        return KeysetHandle.fromKeyset(KeysetHandle.decrypt(encryptedKeyset0, aead0, arr_b));
    }

    private static KeyStatusType serializeStatus(KeyStatus keyStatus0) {
        if(KeyStatus.ENABLED.equals(keyStatus0)) {
            return KeyStatusType.ENABLED;
        }
        if(KeyStatus.DISABLED.equals(keyStatus0)) {
            return KeyStatusType.DISABLED;
        }
        if(!KeyStatus.DESTROYED.equals(keyStatus0)) {
            throw new IllegalStateException("Unknown key status");
        }
        return KeyStatusType.DESTROYED;
    }

    public int size() {
        return this.keyset.getKeyCount();
    }

    private static com.google.crypto.tink.proto.Keyset.Key toKeysetKey(int v, KeyStatusType keyStatusType0, ProtoKeySerialization protoKeySerialization0) {
        return (com.google.crypto.tink.proto.Keyset.Key)com.google.crypto.tink.proto.Keyset.Key.newBuilder().setKeyData(KeyData.newBuilder().setTypeUrl(protoKeySerialization0.getTypeUrl()).setValue(protoKeySerialization0.getValue()).setKeyMaterialType(protoKeySerialization0.getKeyMaterialType())).setStatus(keyStatusType0).setKeyId(v).setOutputPrefixType(protoKeySerialization0.getOutputPrefixType()).build();
    }

    private static ProtoKeySerialization toProtoKeySerialization(com.google.crypto.tink.proto.Keyset.Key keyset$Key0) {
        Integer integer0 = keyset$Key0.getOutputPrefixType() == OutputPrefixType.RAW ? null : keyset$Key0.getKeyId();
        try {
            return ProtoKeySerialization.create("", keyset$Key0.getKeyData().getValue(), keyset$Key0.getKeyData().getKeyMaterialType(), keyset$Key0.getOutputPrefixType(), integer0);
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new TinkBugException("Creating a protokey serialization failed", generalSecurityException0);
        }
    }

    @Override
    public String toString() {
        return this.getKeysetInfo().toString();
    }

    private static void validate(KeyData keyData0) throws GeneralSecurityException {
        Registry.getPrimitive(keyData0);
    }

    public void write(KeysetWriter keysetWriter0, Aead aead0) throws GeneralSecurityException, IOException {
        this.writeWithAssociatedData(keysetWriter0, aead0, new byte[0]);
    }

    public void writeNoSecret(KeysetWriter keysetWriter0) throws GeneralSecurityException, IOException {
        KeysetHandle.assertNoSecretKeyMaterial(this.keyset);
        keysetWriter0.write(this.keyset);
    }

    public void writeWithAssociatedData(KeysetWriter keysetWriter0, Aead aead0, byte[] arr_b) throws GeneralSecurityException, IOException {
        keysetWriter0.write(KeysetHandle.encrypt(this.keyset, aead0, arr_b));
    }

    class com.google.crypto.tink.KeysetHandle.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;

        static {
            int[] arr_v = new int[KeyStatusType.values().length];
            com.google.crypto.tink.KeysetHandle.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType = arr_v;
            try {
                arr_v[KeyStatusType.ENABLED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeysetHandle.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.KeysetHandle.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

