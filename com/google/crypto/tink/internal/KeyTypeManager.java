package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class KeyTypeManager {
    public static abstract class KeyFactory {
        public static final class KeyFormat {
            public Object keyFormat;
            public OutputPrefixType outputPrefixType;

            public KeyFormat(Object object0, OutputPrefixType keyTemplate$OutputPrefixType0) {
                this.keyFormat = object0;
                this.outputPrefixType = keyTemplate$OutputPrefixType0;
            }
        }

        private final Class clazz;

        public KeyFactory(Class class0) {
            this.clazz = class0;
        }

        public abstract MessageLite createKey(MessageLite arg1) throws GeneralSecurityException;

        public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
            throw new GeneralSecurityException("deriveKey not implemented for key of type " + this.clazz);
        }

        public final Class getKeyFormatClass() {
            return this.clazz;
        }

        public Map keyFormats() throws GeneralSecurityException {
            return Collections.EMPTY_MAP;
        }

        public abstract MessageLite parseKeyFormat(ByteString arg1) throws InvalidProtocolBufferException;

        protected static void readFully(InputStream inputStream0, byte[] arr_b) throws IOException, GeneralSecurityException {
            for(int v = 0; v < arr_b.length; v += v1) {
                int v1 = inputStream0.read(arr_b, v, arr_b.length - v);
                if(v1 == -1) {
                    throw new GeneralSecurityException("Not enough pseudorandomness provided");
                }
            }
        }

        public abstract void validateKeyFormat(MessageLite arg1) throws GeneralSecurityException;
    }

    private final Class clazz;
    private final Map factories;
    private final Class firstPrimitiveClass;

    @SafeVarargs
    protected KeyTypeManager(Class class0, PrimitiveFactory[] arr_primitiveFactory) {
        this.clazz = class0;
        HashMap hashMap0 = new HashMap();
        for(int v = 0; v < arr_primitiveFactory.length; ++v) {
            PrimitiveFactory primitiveFactory0 = arr_primitiveFactory[v];
            if(hashMap0.containsKey(primitiveFactory0.getPrimitiveClass())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + primitiveFactory0.getPrimitiveClass().getCanonicalName());
            }
            hashMap0.put(primitiveFactory0.getPrimitiveClass(), primitiveFactory0);
        }
        this.firstPrimitiveClass = arr_primitiveFactory.length > 0 ? arr_primitiveFactory[0].getPrimitiveClass() : Void.class;
        this.factories = Collections.unmodifiableMap(hashMap0);
    }

    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public final Class firstSupportedPrimitiveClass() {
        return this.firstPrimitiveClass;
    }

    public final Class getKeyClass() {
        return this.clazz;
    }

    public abstract String getKeyType();

    public final Object getPrimitive(MessageLite messageLite0, Class class0) throws GeneralSecurityException {
        PrimitiveFactory primitiveFactory0 = (PrimitiveFactory)this.factories.get(class0);
        if(primitiveFactory0 == null) {
            throw new IllegalArgumentException("Requested primitive class " + class0.getCanonicalName() + " not supported.");
        }
        return primitiveFactory0.getPrimitive(messageLite0);
    }

    public abstract int getVersion();

    public KeyFactory keyFactory() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract KeyMaterialType keyMaterialType();

    public abstract MessageLite parseKey(ByteString arg1) throws InvalidProtocolBufferException;

    public final Set supportedPrimitives() {
        return this.factories.keySet();
    }

    public abstract void validateKey(MessageLite arg1) throws GeneralSecurityException;
}

