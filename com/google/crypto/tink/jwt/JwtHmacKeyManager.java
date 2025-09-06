package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtHmacAlgorithm;
import com.google.crypto.tink.proto.JwtHmacKey;
import com.google.crypto.tink.proto.JwtHmacKeyFormat;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.subtle.PrfHmacJce;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.Immutable;
import com.google.gson.JsonObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.spec.SecretKeySpec;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class JwtHmacKeyManager extends KeyTypeManager {
    @Immutable
    static final class JwtHmac implements JwtMacInternal {
        private final String algorithm;
        private final Optional customKidFromHmacKey;
        private final PrfMac prfMac;

        public JwtHmac(String s, Optional optional0, PrfMac prfMac0) {
            this.algorithm = s;
            this.customKidFromHmacKey = optional0;
            this.prfMac = prfMac0;
        }

        @Override  // com.google.crypto.tink.jwt.JwtMacInternal
        public String computeMacAndEncodeWithKid(RawJwt rawJwt0, Optional optional0) throws GeneralSecurityException {
            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.customKidFromHmacKey)) {
                if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(optional0)) {
                    throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                }
                optional0 = this.customKidFromHmacKey;
            }
            String s = JwtFormat.createUnsignedCompact(this.algorithm, optional0, rawJwt0);
            return JwtFormat.createSignedCompact(s, this.prfMac.computeMac(s.getBytes(StandardCharsets.US_ASCII)));
        }

        @Override  // com.google.crypto.tink.jwt.JwtMacInternal
        public VerifiedJwt verifyMacAndDecodeWithKid(String s, JwtValidator jwtValidator0, Optional optional0) throws GeneralSecurityException {
            Parts jwtFormat$Parts0 = JwtFormat.splitSignedCompact(s);
            this.prfMac.verifyMac(jwtFormat$Parts0.signatureOrMac, jwtFormat$Parts0.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
            JsonObject jsonObject0 = JsonUtil.parseJson(jwtFormat$Parts0.header);
            JwtFormat.validateHeader(this.algorithm, optional0, this.customKidFromHmacKey, jsonObject0);
            return jwtValidator0.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(jsonObject0), jwtFormat$Parts0.payload));
        }
    }

    public JwtHmacKeyManager() {
        PrimitiveFactory[] arr_primitiveFactory = {new PrimitiveFactory(/*ERROR_MISSING_ARG_0/*) {
            public JwtMacInternal getPrimitive(JwtHmacKey jwtHmacKey0) throws GeneralSecurityException {
                Optional optional0;
                JwtHmacAlgorithm jwtHmacAlgorithm0 = jwtHmacKey0.getAlgorithm();
                SecretKeySpec secretKeySpec0 = new SecretKeySpec(jwtHmacKey0.getKeyValue().toByteArray(), "HMAC");
                PrfHmacJce prfHmacJce0 = new PrfHmacJce(JwtHmacKeyManager.getHmacAlgorithm(jwtHmacAlgorithm0), secretKeySpec0);
                PrfMac prfMac0 = new PrfMac(prfHmacJce0, prfHmacJce0.getMaxOutputLength());
                if(jwtHmacKey0.hasCustomKid()) {
                    optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m("");
                    return new JwtHmac(JwtHmacKeyManager.getAlgorithmName(jwtHmacAlgorithm0), optional0, prfMac0);
                }
                optional0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m();
                return new JwtHmac(JwtHmacKeyManager.getAlgorithmName(jwtHmacAlgorithm0), optional0, prfMac0);
            }

            @Override  // com.google.crypto.tink.internal.PrimitiveFactory
            public Object getPrimitive(MessageLite messageLite0) throws GeneralSecurityException {
                return this.getPrimitive(((JwtHmacKey)messageLite0));
            }
        }};
        super(JwtHmacKey.class, arr_primitiveFactory);
    }

    private static KeyFormat createKeyFormat(JwtHmacAlgorithm jwtHmacAlgorithm0, int v, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyFormat(((JwtHmacKeyFormat)JwtHmacKeyFormat.newBuilder().setAlgorithm(jwtHmacAlgorithm0).setKeySize(v).build()), keyTemplate$OutputPrefixType0);
    }

    // 去混淆评级： 低(20)
    private static KeyTemplate createTemplate(JwtHmacAlgorithm jwtHmacAlgorithm0, int v) {
        return KeyTemplate.create("type.googleapis.com/google.crypto.tink.JwtHmacKey", ((JwtHmacKeyFormat)JwtHmacKeyFormat.newBuilder().setAlgorithm(jwtHmacAlgorithm0).setKeySize(v).build()).toByteArray(), OutputPrefixType.RAW);
    }

    private static final String getAlgorithmName(JwtHmacAlgorithm jwtHmacAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm0.ordinal()]) {
            case 1: {
                return "HS256";
            }
            case 2: {
                return "HS384";
            }
            case 3: {
                return "HS512";
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
    }

    private static final String getHmacAlgorithm(JwtHmacAlgorithm jwtHmacAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm0.ordinal()]) {
            case 1: {
                return "HMACSHA256";
            }
            case 2: {
                return "HMACSHA384";
            }
            case 3: {
                return "HMACSHA512";
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() [...] // 潜在的解密器

    private static final int getMinimumKeySizeInBytes(JwtHmacAlgorithm jwtHmacAlgorithm0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[jwtHmacAlgorithm0.ordinal()]) {
            case 1: {
                return 0x20;
            }
            case 2: {
                return 0x30;
            }
            case 3: {
                return 0x40;
            }
            default: {
                throw new GeneralSecurityException("unknown algorithm");
            }
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() [...] // Inlined contents

    public static final KeyTemplate hs256Template() {
        return JwtHmacKeyManager.createTemplate(JwtHmacAlgorithm.HS256, 0x20);
    }

    public static final KeyTemplate hs384Template() {
        return JwtHmacKeyManager.createTemplate(JwtHmacAlgorithm.HS384, 0x30);
    }

    public static final KeyTemplate hs512Template() {
        return JwtHmacKeyManager.createTemplate(JwtHmacAlgorithm.HS512, 0x40);
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyFactory keyFactory() {
        return new KeyFactory(JwtHmacKeyFormat.class) {
            public JwtHmacKey createKey(JwtHmacKeyFormat jwtHmacKeyFormat0) {
                return (JwtHmacKey)JwtHmacKey.newBuilder().setVersion(0).setAlgorithm(jwtHmacKeyFormat0.getAlgorithm()).setKeyValue(ByteString.copyFrom(Random.randBytes(jwtHmacKeyFormat0.getKeySize()))).build();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite createKey(MessageLite messageLite0) throws GeneralSecurityException {
                return this.createKey(((JwtHmacKeyFormat)messageLite0));
            }

            public JwtHmacKey deriveKey(JwtHmacKeyFormat jwtHmacKeyFormat0, InputStream inputStream0) throws GeneralSecurityException {
                throw new UnsupportedOperationException();
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite deriveKey(MessageLite messageLite0, InputStream inputStream0) throws GeneralSecurityException {
                return this.deriveKey(((JwtHmacKeyFormat)messageLite0), inputStream0);
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public Map keyFormats() {
                HashMap hashMap0 = new HashMap();
                hashMap0.put("JWT_HS256_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 0x20, OutputPrefixType.RAW));
                hashMap0.put("JWT_HS256", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS256, 0x20, OutputPrefixType.TINK));
                hashMap0.put("JWT_HS384_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 0x30, OutputPrefixType.RAW));
                hashMap0.put("JWT_HS384", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS384, 0x30, OutputPrefixType.TINK));
                hashMap0.put("JWT_HS512_RAW", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 0x40, OutputPrefixType.RAW));
                hashMap0.put("JWT_HS512", JwtHmacKeyManager.createKeyFormat(JwtHmacAlgorithm.HS512, 0x40, OutputPrefixType.TINK));
                return Collections.unmodifiableMap(hashMap0);
            }

            public JwtHmacKeyFormat parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return JwtHmacKeyFormat.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public MessageLite parseKeyFormat(ByteString byteString0) throws InvalidProtocolBufferException {
                return this.parseKeyFormat(byteString0);
            }

            public void validateKeyFormat(JwtHmacKeyFormat jwtHmacKeyFormat0) throws GeneralSecurityException {
                if(jwtHmacKeyFormat0.getKeySize() < JwtHmacKeyManager.getMinimumKeySizeInBytes(jwtHmacKeyFormat0.getAlgorithm())) {
                    throw new GeneralSecurityException("key too short");
                }
            }

            @Override  // com.google.crypto.tink.internal.KeyTypeManager$KeyFactory
            public void validateKeyFormat(MessageLite messageLite0) throws GeneralSecurityException {
                this.validateKeyFormat(((JwtHmacKeyFormat)messageLite0));
            }
        };
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public KeyMaterialType keyMaterialType() {
        return KeyMaterialType.SYMMETRIC;
    }

    public JwtHmacKey parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return JwtHmacKey.parseFrom(byteString0, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public MessageLite parseKey(ByteString byteString0) throws InvalidProtocolBufferException {
        return this.parseKey(byteString0);
    }

    public static void register(boolean z) throws GeneralSecurityException {
        Registry.registerKeyManager(new JwtHmacKeyManager(), z);
    }

    public void validateKey(JwtHmacKey jwtHmacKey0) throws GeneralSecurityException {
        Validators.validateVersion(jwtHmacKey0.getVersion(), 0);
        int v = JwtHmacKeyManager.getMinimumKeySizeInBytes(jwtHmacKey0.getAlgorithm());
        if(jwtHmacKey0.getKeyValue().size() < v) {
            throw new GeneralSecurityException("key too short");
        }
    }

    @Override  // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(MessageLite messageLite0) throws GeneralSecurityException {
        this.validateKey(((JwtHmacKey)messageLite0));
    }

    class com.google.crypto.tink.jwt.JwtHmacKeyManager.3 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm;

        static {
            int[] arr_v = new int[JwtHmacAlgorithm.values().length];
            com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm = arr_v;
            try {
                arr_v[JwtHmacAlgorithm.HS256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[JwtHmacAlgorithm.HS384.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.jwt.JwtHmacKeyManager.3.$SwitchMap$com$google$crypto$tink$proto$JwtHmacAlgorithm[JwtHmacAlgorithm.HS512.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

