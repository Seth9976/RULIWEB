package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HkdfPrfKey;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HkdfPrfKeyManager extends KeyTypeManager {
    private static final int MIN_KEY_SIZE = 0x20;

    HkdfPrfKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public StreamingPrf getPrimitive(HkdfPrfKey hkdfPrfKey0) throws GeneralSecurityException {
                return new HkdfStreamingPrf(HkdfPrfKeyManager.access$000(hkdfPrfKey0.getParams().getHash()), hkdfPrfKey0.getKeyValue().toByteArray(), hkdfPrfKey0.getParams().getSalt().toByteArray());
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((HkdfPrfKey)messageLite0));
            }
        }, new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public Prf getPrimitive(HkdfPrfKey hkdfPrfKey0) throws GeneralSecurityException {
                return PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.access$000(hkdfPrfKey0.getParams().getHash()), hkdfPrfKey0.getKeyValue().toByteArray(), hkdfPrfKey0.getParams().getSalt().toByteArray()));
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((HkdfPrfKey)messageLite0));
            }
        }};
        super(HkdfPrfKey.class, arr_primitiveFactory);
    }

    static HashType access$000(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        return HkdfPrfKeyManager.convertHash(hashType0);
    }

    private static HashType convertHash(com.google.crypto.tink.proto.HashType hashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.prf.HkdfPrfKeyManager.4.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType0.ordinal()]) {
            case 1: {
                return HashType.SHA1;
            }
            case 2: {
                return HashType.SHA256;
            }
            case 3: {
                return HashType.SHA384;
            }
            case 4: {
                return HashType.SHA512;
            }
            default: {
                throw new GeneralSecurityException("HashType " + hashType0.name() + " not known in");
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    // 去混淆评级： 低(20)
    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.HkdfPrfKey", ((HkdfPrfKeyFormat)HkdfPrfKeyFormat.newBuilder().setKeySize(0x20).setParams(HkdfPrfParams.newBuilder().setHash(com.google.crypto.tink.proto.HashType.SHA256)).build()).toByteArray(), OutputPrefixType.RAW);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(HkdfPrfKeyFormat.class) {
            public HkdfPrfKey createKey(HkdfPrfKeyFormat hkdfPrfKeyFormat0) throws GeneralSecurityException {
                return (HkdfPrfKey)HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(hkdfPrfKeyFormat0.getKeySize()))).setVersion(0).setParams(hkdfPrfKeyFormat0.getParams()).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((HkdfPrfKeyFormat)messageLite0));
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() throws GeneralSecurityException {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("HKDF_SHA256", new KeyFormat(((HkdfPrfKeyFormat)HkdfPrfKeyFormat.newBuilder().setKeySize(0x20).setParams(HkdfPrfParams.newBuilder().setHash(com.google.crypto.tink.proto.HashType.SHA256)).build()), OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap0);
            }

            public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return HkdfPrfKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(HkdfPrfKeyFormat hkdfPrfKeyFormat0) throws GeneralSecurityException {
                HkdfPrfKeyManager.validateKeySize(hkdfPrfKeyFormat0.getKeySize());
                HkdfPrfKeyManager.validateParams(hkdfPrfKeyFormat0.getParams());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((HkdfPrfKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public HkdfPrfKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return HkdfPrfKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), z);
        HkdfPrfProtoSerialization.register();
    }

    // 去混淆评级： 低(20)
    public static String staticKeyType() [...] // 潜在的解密器

    public void validateKey(HkdfPrfKey hkdfPrfKey0) throws GeneralSecurityException {
        Validators.validateVersion(hkdfPrfKey0.getVersion(), 0);
        HkdfPrfKeyManager.validateKeySize(hkdfPrfKey0.getKeyValue().size());
        HkdfPrfKeyManager.validateParams(hkdfPrfKey0.getParams());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((HkdfPrfKey)messageLite0));
    }

    private static void validateKeySize(int v) throws GeneralSecurityException {
        if(v < 0x20) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    private static void validateParams(HkdfPrfParams hkdfPrfParams0) throws GeneralSecurityException {
        if(hkdfPrfParams0.getHash() != com.google.crypto.tink.proto.HashType.SHA256 && hkdfPrfParams0.getHash() != com.google.crypto.tink.proto.HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    class com.google.crypto.tink.prf.HkdfPrfKeyManager.4 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] arr_v = new int[com.google.crypto.tink.proto.HashType.values().length];
            com.google.crypto.tink.prf.HkdfPrfKeyManager.4.$SwitchMap$com$google$crypto$tink$proto$HashType = arr_v;
            try {
                arr_v[com.google.crypto.tink.proto.HashType.SHA1.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfKeyManager.4.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA256.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfKeyManager.4.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA384.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.prf.HkdfPrfKeyManager.4.$SwitchMap$com$google$crypto$tink$proto$HashType[com.google.crypto.tink.proto.HashType.SHA512.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

