package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public final class MutableSerializationRegistry {
    private static final MutableSerializationRegistry GLOBAL_INSTANCE;
    private final AtomicReference registry;

    static {
        MutableSerializationRegistry.GLOBAL_INSTANCE = new MutableSerializationRegistry();
    }

    public MutableSerializationRegistry() {
        this.registry = new AtomicReference(new Builder().build());
    }

    public static MutableSerializationRegistry globalInstance() {
        return MutableSerializationRegistry.GLOBAL_INSTANCE;
    }

    public boolean hasParserForKey(Serialization serialization0) {
        return ((SerializationRegistry)this.registry.get()).hasParserForKey(serialization0);
    }

    public boolean hasParserForParameters(Serialization serialization0) {
        return ((SerializationRegistry)this.registry.get()).hasParserForParameters(serialization0);
    }

    public boolean hasSerializerForKey(Key key0, Class class0) {
        return ((SerializationRegistry)this.registry.get()).hasSerializerForKey(key0, class0);
    }

    public boolean hasSerializerForParameters(Parameters parameters0, Class class0) {
        return ((SerializationRegistry)this.registry.get()).hasSerializerForParameters(parameters0, class0);
    }

    public Key parseKey(Serialization serialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        return ((SerializationRegistry)this.registry.get()).parseKey(serialization0, secretKeyAccess0);
    }

    public Key parseKeyWithLegacyFallback(ProtoKeySerialization protoKeySerialization0, SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 == null) {
            throw new NullPointerException("access cannot be null");
        }
        if(!this.hasParserForKey(protoKeySerialization0)) {
            try {
                return new LegacyProtoKey(protoKeySerialization0, secretKeyAccess0);
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new TinkBugException("Creating a LegacyProtoKey failed", generalSecurityException0);
            }
        }
        return this.parseKey(protoKeySerialization0, secretKeyAccess0);
    }

    public Parameters parseParameters(Serialization serialization0) throws GeneralSecurityException {
        return ((SerializationRegistry)this.registry.get()).parseParameters(serialization0);
    }

    public Parameters parseParametersWithLegacyFallback(ProtoParametersSerialization protoParametersSerialization0) {
        try {
            return this.parseParameters(protoParametersSerialization0);
        }
        catch(GeneralSecurityException unused_ex) {
            return new LegacyProtoParameters(protoParametersSerialization0);
        }
    }

    public void registerKeyParser(KeyParser keyParser0) throws GeneralSecurityException {
        synchronized(this) {
            SerializationRegistry serializationRegistry0 = new Builder(((SerializationRegistry)this.registry.get())).registerKeyParser(keyParser0).build();
            this.registry.set(serializationRegistry0);
        }
    }

    public void registerKeySerializer(KeySerializer keySerializer0) throws GeneralSecurityException {
        synchronized(this) {
            SerializationRegistry serializationRegistry0 = new Builder(((SerializationRegistry)this.registry.get())).registerKeySerializer(keySerializer0).build();
            this.registry.set(serializationRegistry0);
        }
    }

    public void registerParametersParser(ParametersParser parametersParser0) throws GeneralSecurityException {
        synchronized(this) {
            SerializationRegistry serializationRegistry0 = new Builder(((SerializationRegistry)this.registry.get())).registerParametersParser(parametersParser0).build();
            this.registry.set(serializationRegistry0);
        }
    }

    public void registerParametersSerializer(ParametersSerializer parametersSerializer0) throws GeneralSecurityException {
        synchronized(this) {
            SerializationRegistry serializationRegistry0 = new Builder(((SerializationRegistry)this.registry.get())).registerParametersSerializer(parametersSerializer0).build();
            this.registry.set(serializationRegistry0);
        }
    }

    public Serialization serializeKey(Key key0, Class class0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        return ((SerializationRegistry)this.registry.get()).serializeKey(key0, class0, secretKeyAccess0);
    }

    public Serialization serializeParameters(Parameters parameters0, Class class0) throws GeneralSecurityException {
        return ((SerializationRegistry)this.registry.get()).serializeParameters(parameters0, class0);
    }
}

