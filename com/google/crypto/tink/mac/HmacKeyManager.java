package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

public final class HmacKeyManager extends KeyTypeManager {
    private static final PrimitiveConstructor CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = null;
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;

    static {
        HmacKeyManager.CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new HmacKeyManager..ExternalSyntheticLambda0(), HmacKey.class, ChunkedMac.class);
    }

    public HmacKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Mac getPrimitive(com.google.crypto.tink.proto.HmacKey hmacKey0) throws GeneralSecurityException {
                HashType hashType0 = hmacKey0.getParams().getHash();
                SecretKeySpec secretKeySpec0 = new SecretKeySpec(hmacKey0.getKeyValue().toByteArray(), "HMAC");
                int v = hmacKey0.getParams().getTagSize();
                switch(com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
                    case 1: {
                        return new PrfMac(new PrfHmacJce("HMACSHA1", secretKeySpec0), v);
                    }
                    case 2: {
                        return new PrfMac(new PrfHmacJce("HMACSHA224", secretKeySpec0), v);
                    }
                    case 3: {
                        return new PrfMac(new PrfHmacJce("HMACSHA256", secretKeySpec0), v);
                    }
                    case 4: {
                        return new PrfMac(new PrfHmacJce("HMACSHA384", secretKeySpec0), v);
                    }
                    case 5: {
                        return new PrfMac(new PrfHmacJce("HMACSHA512", secretKeySpec0), v);
                    }
                    default: {
                        throw new GeneralSecurityException("unknown hash");
                    }
                }
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((com.google.crypto.tink.proto.HmacKey)messageLite0));
            }
        }};
        super(com.google.crypto.tink.proto.HmacKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(int v, int v1, HashType hashType0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(((HmacParams)HmacParams.newBuilder().setHash(hashType0).setTagSize(v1).build())).setKeySize(v).build()), keyTemplate$OutputPrefixType0);
    }

    private static KeyTemplate createTemplate(int v, int v1, HashType hashType0) {
        HmacParams hmacParams0 = (HmacParams)HmacParams.newBuilder().setHash(hashType0).setTagSize(v1).build();
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.HmacKey", ((HmacKeyFormat)HmacKeyFormat.newBuilder().setParams(hmacParams0).setKeySize(v).build()).toByteArray(), OutputPrefixType.TINK);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    public static final KeyTemplate hmacSha256HalfDigestTemplate() {
        return HmacKeyManager.createTemplate(0x20, 16, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha256Template() {
        return HmacKeyManager.createTemplate(0x20, 0x20, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512HalfDigestTemplate() {
        return HmacKeyManager.createTemplate(0x40, 0x20, HashType.SHA512);
    }

    public static final KeyTemplate hmacSha512Template() {
        return HmacKeyManager.createTemplate(0x40, 0x40, HashType.SHA512);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(HmacKeyFormat.class) {
            public com.google.crypto.tink.proto.HmacKey createKey(HmacKeyFormat hmacKeyFormat0) throws GeneralSecurityException {
                return (com.google.crypto.tink.proto.HmacKey)com.google.crypto.tink.proto.HmacKey.newBuilder().setVersion(0).setParams(hmacKeyFormat0.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(hmacKeyFormat0.getKeySize()))).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((HmacKeyFormat)messageLite0));
            }

            public com.google.crypto.tink.proto.HmacKey deriveKey(HmacKeyFormat hmacKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(hmacKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[hmacKeyFormat0.getKeySize()];
                try {
                    com.google.crypto.tink.mac.HmacKeyManager.2.readFully(inputStream0, arr_b);
                    return (com.google.crypto.tink.proto.HmacKey)com.google.crypto.tink.proto.HmacKey.newBuilder().setVersion(0).setParams(hmacKeyFormat0.getParams()).setKeyValue(ByteString.copyFrom(arr_b)).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((HmacKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("HMAC_SHA256_128BITTAG", HmacKeyManager.createKeyFormat(0x20, 16, HashType.SHA256, OutputPrefixType.TINK));
                hashMap0.put("HMAC_SHA256_128BITTAG_RAW", HmacKeyManager.createKeyFormat(0x20, 16, HashType.SHA256, OutputPrefixType.RAW));
                hashMap0.put("HMAC_SHA256_256BITTAG", HmacKeyManager.createKeyFormat(0x20, 0x20, HashType.SHA256, OutputPrefixType.TINK));
                hashMap0.put("HMAC_SHA256_256BITTAG_RAW", HmacKeyManager.createKeyFormat(0x20, 0x20, HashType.SHA256, OutputPrefixType.RAW));
                hashMap0.put("HMAC_SHA512_128BITTAG", HmacKeyManager.createKeyFormat(0x40, 16, HashType.SHA512, OutputPrefixType.TINK));
                hashMap0.put("HMAC_SHA512_128BITTAG_RAW", HmacKeyManager.createKeyFormat(0x40, 16, HashType.SHA512, OutputPrefixType.RAW));
                hashMap0.put("HMAC_SHA512_256BITTAG", HmacKeyManager.createKeyFormat(0x40, 0x20, HashType.SHA512, OutputPrefixType.TINK));
                hashMap0.put("HMAC_SHA512_256BITTAG_RAW", HmacKeyManager.createKeyFormat(0x40, 0x20, HashType.SHA512, OutputPrefixType.RAW));
                hashMap0.put("HMAC_SHA512_512BITTAG", HmacKeyManager.createKeyFormat(0x40, 0x40, HashType.SHA512, OutputPrefixType.TINK));
                hashMap0.put("HMAC_SHA512_512BITTAG_RAW", HmacKeyManager.createKeyFormat(0x40, 0x40, HashType.SHA512, OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public HmacKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return HmacKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(HmacKeyFormat hmacKeyFormat0) throws GeneralSecurityException {
                if(hmacKeyFormat0.getKeySize() < 16) {
                    throw new GeneralSecurityException("key too short");
                }
                HmacKeyManager.validateParams(hmacKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((HmacKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public com.google.crypto.tink.proto.HmacKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return com.google.crypto.tink.proto.HmacKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacKeyManager(), z);
        HmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(HmacKeyManager.CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    public void validateKey(com.google.crypto.tink.proto.HmacKey hmacKey0) throws GeneralSecurityException {
        Validators.validateVersion(hmacKey0.getVersion(), 0);
        if(hmacKey0.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        HmacKeyManager.validateParams(hmacKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((com.google.crypto.tink.proto.HmacKey)messageLite0));
    }

    private static void validateParams(HmacParams hmacParams0) throws GeneralSecurityException {
        if(hmacParams0.getTagSize() >= 10) {
            switch(com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[hmacParams0.getHash().ordinal()]) {
                case 1: {
                    if(hmacParams0.getTagSize() > 20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                }
                case 2: {
                    if(hmacParams0.getTagSize() > 28) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                }
                case 3: {
                    if(hmacParams0.getTagSize() > 0x20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                }
                case 4: {
                    if(hmacParams0.getTagSize() > 0x30) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                }
                case 5: {
                    if(hmacParams0.getTagSize() > 0x40) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                }
                default: {
                    throw new GeneralSecurityException("unknown hash type");
                }
            }
        }
        throw new GeneralSecurityException("tag size too small");
    }

    class com.google.crypto.tink.mac.HmacKeyManager.3 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] arr_v = new int[HashType.values().length];
            com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v;
            try {
                arr_v[HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.mac.HmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

