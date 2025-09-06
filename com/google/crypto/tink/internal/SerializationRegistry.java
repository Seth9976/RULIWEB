package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public final class SerializationRegistry {
    public static final class Builder {
        private final Map keyParserMap;
        private final Map keySerializerMap;
        private final Map parametersParserMap;
        private final Map parametersSerializerMap;

        public Builder() {
            this.keySerializerMap = new HashMap();
            this.keyParserMap = new HashMap();
            this.parametersSerializerMap = new HashMap();
            this.parametersParserMap = new HashMap();
        }

        public Builder(SerializationRegistry serializationRegistry0) {
            this.keySerializerMap = new HashMap(SerializationRegistry.access$000(serializationRegistry0));
            this.keyParserMap = new HashMap(serializationRegistry0.keyParserMap);
            this.parametersSerializerMap = new HashMap(serializationRegistry0.parametersSerializerMap);
            this.parametersParserMap = new HashMap(SerializationRegistry.access$300(serializationRegistry0));
        }

        static Map access$1000(Builder serializationRegistry$Builder0) {
            return serializationRegistry$Builder0.parametersParserMap;
        }

        static Map access$700(Builder serializationRegistry$Builder0) {
            return serializationRegistry$Builder0.keySerializerMap;
        }

        static Map access$800(Builder serializationRegistry$Builder0) {
            return serializationRegistry$Builder0.keyParserMap;
        }

        static Map access$900(Builder serializationRegistry$Builder0) {
            return serializationRegistry$Builder0.parametersSerializerMap;
        }

        SerializationRegistry build() {
            return new SerializationRegistry(this, null);
        }

        public Builder registerKeyParser(KeyParser keyParser0) throws GeneralSecurityException {
            ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(keyParser0.getSerializationClass(), keyParser0.getObjectIdentifier(), null);
            if(this.keyParserMap.containsKey(serializationRegistry$ParserIndex0)) {
                KeyParser keyParser1 = (KeyParser)this.keyParserMap.get(serializationRegistry$ParserIndex0);
                if(!keyParser1.equals(keyParser0) || !keyParser0.equals(keyParser1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + serializationRegistry$ParserIndex0);
                }
                return this;
            }
            this.keyParserMap.put(serializationRegistry$ParserIndex0, keyParser0);
            return this;
        }

        public Builder registerKeySerializer(KeySerializer keySerializer0) throws GeneralSecurityException {
            SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(keySerializer0.getKeyClass(), keySerializer0.getSerializationClass(), null);
            if(this.keySerializerMap.containsKey(serializationRegistry$SerializerIndex0)) {
                KeySerializer keySerializer1 = (KeySerializer)this.keySerializerMap.get(serializationRegistry$SerializerIndex0);
                if(!keySerializer1.equals(keySerializer0) || !keySerializer0.equals(keySerializer1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + serializationRegistry$SerializerIndex0);
                }
                return this;
            }
            this.keySerializerMap.put(serializationRegistry$SerializerIndex0, keySerializer0);
            return this;
        }

        public Builder registerParametersParser(ParametersParser parametersParser0) throws GeneralSecurityException {
            ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(parametersParser0.getSerializationClass(), parametersParser0.getObjectIdentifier(), null);
            if(this.parametersParserMap.containsKey(serializationRegistry$ParserIndex0)) {
                ParametersParser parametersParser1 = (ParametersParser)this.parametersParserMap.get(serializationRegistry$ParserIndex0);
                if(!parametersParser1.equals(parametersParser0) || !parametersParser0.equals(parametersParser1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + serializationRegistry$ParserIndex0);
                }
                return this;
            }
            this.parametersParserMap.put(serializationRegistry$ParserIndex0, parametersParser0);
            return this;
        }

        public Builder registerParametersSerializer(ParametersSerializer parametersSerializer0) throws GeneralSecurityException {
            SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(parametersSerializer0.getParametersClass(), parametersSerializer0.getSerializationClass(), null);
            if(this.parametersSerializerMap.containsKey(serializationRegistry$SerializerIndex0)) {
                ParametersSerializer parametersSerializer1 = (ParametersSerializer)this.parametersSerializerMap.get(serializationRegistry$SerializerIndex0);
                if(!parametersSerializer1.equals(parametersSerializer0) || !parametersSerializer0.equals(parametersSerializer1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + serializationRegistry$SerializerIndex0);
                }
                return this;
            }
            this.parametersSerializerMap.put(serializationRegistry$SerializerIndex0, parametersSerializer0);
            return this;
        }
    }

    static class ParserIndex {
        private final Class keySerializationClass;
        private final Bytes serializationIdentifier;

        private ParserIndex(Class class0, Bytes bytes0) {
            this.keySerializationClass = class0;
            this.serializationIdentifier = bytes0;
        }

        ParserIndex(Class class0, Bytes bytes0, com.google.crypto.tink.internal.SerializationRegistry.1 serializationRegistry$10) {
            this(class0, bytes0);
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof ParserIndex ? ((ParserIndex)object0).keySerializationClass.equals(this.keySerializationClass) && ((ParserIndex)object0).serializationIdentifier.equals(this.serializationIdentifier) : false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.keySerializationClass, this.serializationIdentifier});
        }

        @Override
        public String toString() {
            return this.keySerializationClass.getSimpleName() + ", object identifier: " + this.serializationIdentifier;
        }
    }

    static class SerializerIndex {
        private final Class keyClass;
        private final Class keySerializationClass;

        private SerializerIndex(Class class0, Class class1) {
            this.keyClass = class0;
            this.keySerializationClass = class1;
        }

        SerializerIndex(Class class0, Class class1, com.google.crypto.tink.internal.SerializationRegistry.1 serializationRegistry$10) {
            this(class0, class1);
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof SerializerIndex ? ((SerializerIndex)object0).keyClass.equals(this.keyClass) && ((SerializerIndex)object0).keySerializationClass.equals(this.keySerializationClass) : false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.keyClass, this.keySerializationClass});
        }

        @Override
        public String toString() {
            return this.keyClass.getSimpleName() + " with serialization type: " + this.keySerializationClass.getSimpleName();
        }
    }

    private final Map keyParserMap;
    private final Map keySerializerMap;
    private final Map parametersParserMap;
    private final Map parametersSerializerMap;

    private SerializationRegistry(Builder serializationRegistry$Builder0) {
        this.keySerializerMap = new HashMap(Builder.access$700(serializationRegistry$Builder0));
        this.keyParserMap = new HashMap(Builder.access$800(serializationRegistry$Builder0));
        this.parametersSerializerMap = new HashMap(Builder.access$900(serializationRegistry$Builder0));
        this.parametersParserMap = new HashMap(Builder.access$1000(serializationRegistry$Builder0));
    }

    SerializationRegistry(Builder serializationRegistry$Builder0, com.google.crypto.tink.internal.SerializationRegistry.1 serializationRegistry$10) {
        this(serializationRegistry$Builder0);
    }

    static Map access$000(SerializationRegistry serializationRegistry0) {
        return serializationRegistry0.keySerializerMap;
    }

    static Map access$300(SerializationRegistry serializationRegistry0) {
        return serializationRegistry0.parametersParserMap;
    }

    public boolean hasParserForKey(Serialization serialization0) {
        ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(serialization0.getClass(), serialization0.getObjectIdentifier(), null);
        return this.keyParserMap.containsKey(serializationRegistry$ParserIndex0);
    }

    public boolean hasParserForParameters(Serialization serialization0) {
        ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(serialization0.getClass(), serialization0.getObjectIdentifier(), null);
        return this.parametersParserMap.containsKey(serializationRegistry$ParserIndex0);
    }

    public boolean hasSerializerForKey(Key key0, Class class0) {
        SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(key0.getClass(), class0, null);
        return this.keySerializerMap.containsKey(serializationRegistry$SerializerIndex0);
    }

    public boolean hasSerializerForParameters(Parameters parameters0, Class class0) {
        SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(parameters0.getClass(), class0, null);
        return this.parametersSerializerMap.containsKey(serializationRegistry$SerializerIndex0);
    }

    public Key parseKey(Serialization serialization0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(serialization0.getClass(), serialization0.getObjectIdentifier(), null);
        if(!this.keyParserMap.containsKey(serializationRegistry$ParserIndex0)) {
            throw new GeneralSecurityException("No Key Parser for requested key type " + serializationRegistry$ParserIndex0 + " available");
        }
        return ((KeyParser)this.keyParserMap.get(serializationRegistry$ParserIndex0)).parseKey(serialization0, secretKeyAccess0);
    }

    public Parameters parseParameters(Serialization serialization0) throws GeneralSecurityException {
        ParserIndex serializationRegistry$ParserIndex0 = new ParserIndex(serialization0.getClass(), serialization0.getObjectIdentifier(), null);
        if(!this.parametersParserMap.containsKey(serializationRegistry$ParserIndex0)) {
            throw new GeneralSecurityException("No Parameters Parser for requested key type " + serializationRegistry$ParserIndex0 + " available");
        }
        return ((ParametersParser)this.parametersParserMap.get(serializationRegistry$ParserIndex0)).parseParameters(serialization0);
    }

    public Serialization serializeKey(Key key0, Class class0, @Nullable SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(key0.getClass(), class0, null);
        if(!this.keySerializerMap.containsKey(serializationRegistry$SerializerIndex0)) {
            throw new GeneralSecurityException("No Key serializer for " + serializationRegistry$SerializerIndex0 + " available");
        }
        return ((KeySerializer)this.keySerializerMap.get(serializationRegistry$SerializerIndex0)).serializeKey(key0, secretKeyAccess0);
    }

    public Serialization serializeParameters(Parameters parameters0, Class class0) throws GeneralSecurityException {
        SerializerIndex serializationRegistry$SerializerIndex0 = new SerializerIndex(parameters0.getClass(), class0, null);
        if(!this.parametersSerializerMap.containsKey(serializationRegistry$SerializerIndex0)) {
            throw new GeneralSecurityException("No Key Format serializer for " + serializationRegistry$SerializerIndex0 + " available");
        }
        return ((ParametersSerializer)this.parametersSerializerMap.get(serializationRegistry$SerializerIndex0)).serializeParameters(parameters0);
    }

    class com.google.crypto.tink.internal.SerializationRegistry.1 {
    }

}

