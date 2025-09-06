package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacPrfKey;
import com.google.crypto.tink.proto.HmacPrfKeyFormat;
import com.google.crypto.tink.proto.HmacPrfParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;

public final class HmacPrfKeyManager extends KeyTypeManager {
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;

    public HmacPrfKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Prf getPrimitive(HmacPrfKey hmacPrfKey0) throws GeneralSecurityException {
                HashType hashType0 = hmacPrfKey0.getParams().getHash();
                SecretKeySpec secretKeySpec0 = new SecretKeySpec(hmacPrfKey0.getKeyValue().toByteArray(), "HMAC");
                switch(com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
                    case 1: {
                        return new PrfHmacJce("HMACSHA1", secretKeySpec0);
                    }
                    case 2: {
                        return new PrfHmacJce("HMACSHA224", secretKeySpec0);
                    }
                    case 3: {
                        return new PrfHmacJce("HMACSHA256", secretKeySpec0);
                    }
                    case 4: {
                        return new PrfHmacJce("HMACSHA384", secretKeySpec0);
                    }
                    case 5: {
                        return new PrfHmacJce("HMACSHA512", secretKeySpec0);
                    }
                    default: {
                        throw new GeneralSecurityException("unknown hash");
                    }
                }
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((HmacPrfKey)messageLite0));
            }
        }};
        super(HmacPrfKey.class, arr_primitiveFactory);
    }

    private static KeyTemplate createTemplate(int v, HashType hashType0) {
        HmacPrfParams hmacPrfParams0 = (HmacPrfParams)HmacPrfParams.newBuilder().setHash(hashType0).build();
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.HmacPrfKey", ((HmacPrfKeyFormat)HmacPrfKeyFormat.newBuilder().setParams(hmacPrfParams0).setKeySize(v).build()).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public AlgorithmFipsCompatibility fipsStatus() {
        return AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    public static final KeyTemplate hmacSha256Template() {
        return HmacPrfKeyManager.createTemplate(0x20, HashType.SHA256);
    }

    public static final KeyTemplate hmacSha512Template() {
        return HmacPrfKeyManager.createTemplate(0x40, HashType.SHA512);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(HmacPrfKeyFormat.class) {
            public HmacPrfKey createKey(HmacPrfKeyFormat hmacPrfKeyFormat0) {
                return (HmacPrfKey)HmacPrfKey.newBuilder().setVersion(0).setParams(hmacPrfKeyFormat0.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(hmacPrfKeyFormat0.getKeySize()))).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((HmacPrfKeyFormat)messageLite0));
            }

            public HmacPrfKey deriveKey(HmacPrfKeyFormat hmacPrfKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                Validators.validateVersion(hmacPrfKeyFormat0.getVersion(), 0);
                byte[] arr_b = new byte[hmacPrfKeyFormat0.getKeySize()];
                try {
                    com.google.crypto.tink.prf.HmacPrfKeyManager.2.readFully(inputStream0, arr_b);
                    return (HmacPrfKey)HmacPrfKey.newBuilder().setVersion(0).setParams(hmacPrfKeyFormat0.getParams()).setKeyValue(ByteString.copyFrom(arr_b)).build();
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", iOException0);
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((HmacPrfKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("HMAC_SHA256_PRF", new KeyFormat(((HmacPrfKeyFormat)HmacPrfKeyFormat.newBuilder().setParams(((HmacPrfParams)HmacPrfParams.newBuilder().setHash(HashType.SHA256).build())).setKeySize(0x20).build()), OutputPrefixType.RAW));
                hashMap0.put("HMAC_SHA512_PRF", new KeyFormat(((HmacPrfKeyFormat)HmacPrfKeyFormat.newBuilder().setParams(((HmacPrfParams)HmacPrfParams.newBuilder().setHash(HashType.SHA512).build())).setKeySize(0x40).build()), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public HmacPrfKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return HmacPrfKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(HmacPrfKeyFormat hmacPrfKeyFormat0) throws GeneralSecurityException {
                if(hmacPrfKeyFormat0.getKeySize() < 16) {
                    throw new GeneralSecurityException("key too short");
                }
                HmacPrfKeyManager.validateParams(hmacPrfKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((HmacPrfKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public HmacPrfKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return HmacPrfKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HmacPrfKeyManager(), z);
    }

    public void validateKey(HmacPrfKey hmacPrfKey0) throws GeneralSecurityException {
        Validators.validateVersion(hmacPrfKey0.getVersion(), 0);
        if(hmacPrfKey0.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        HmacPrfKeyManager.validateParams(hmacPrfKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((HmacPrfKey)messageLite0));
    }

    private static void validateParams(HmacPrfParams hmacPrfParams0) throws GeneralSecurityException {
        if(hmacPrfParams0.getHash() != HashType.SHA1 && hmacPrfParams0.getHash() != HashType.SHA224 && hmacPrfParams0.getHash() != HashType.SHA256 && hmacPrfParams0.getHash() != HashType.SHA384 && hmacPrfParams0.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    class com.google.crypto.tink.prf.HmacPrfKeyManager.3 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] arr_v = new int[HashType.values().length];
            com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v;
            try {
                arr_v[HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HmacPrfKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

