package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Immutable
final class HpkeDecrypt implements HybridDecrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA;
    private final HpkeAead aead;
    private final int encapsulatedKeyLength;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkeKemPrivateKey recipientPrivateKey;

    static {
        HpkeDecrypt.EMPTY_ASSOCIATED_DATA = new byte[0];
    }

    private HpkeDecrypt(HpkeKemPrivateKey hpkeKemPrivateKey0, HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0, int v) {
        this.recipientPrivateKey = hpkeKemPrivateKey0;
        this.kem = hpkeKem0;
        this.kdf = hpkeKdf0;
        this.aead = hpkeAead0;
        this.encapsulatedKeyLength = v;
    }

    static HpkeDecrypt createHpkeDecrypt(HpkePrivateKey hpkePrivateKey0) throws GeneralSecurityException {
        if(!hpkePrivateKey0.hasPublicKey()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if(!hpkePrivateKey0.getPublicKey().hasParams()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if(hpkePrivateKey0.getPrivateKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        HpkeParams hpkeParams0 = hpkePrivateKey0.getPublicKey().getParams();
        HpkeKem hpkeKem0 = HpkePrimitiveFactory.createKem(hpkeParams0);
        HpkeKdf hpkeKdf0 = HpkePrimitiveFactory.createKdf(hpkeParams0);
        HpkeAead hpkeAead0 = HpkePrimitiveFactory.createAead(hpkeParams0);
        int v = HpkeDecrypt.encodingSizeInBytes(hpkeParams0.getKem());
        return new HpkeDecrypt(HpkeKemKeyFactory.createPrivate(hpkePrivateKey0), hpkeKem0, hpkeKdf0, hpkeAead0, v);
    }

    @Override  // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.encapsulatedKeyLength;
        if(arr_b.length < v) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        byte[] arr_b2 = Arrays.copyOf(arr_b, v);
        byte[] arr_b3 = Arrays.copyOfRange(arr_b, this.encapsulatedKeyLength, arr_b.length);
        return HpkeContext.createRecipientContext(arr_b2, this.recipientPrivateKey, this.kem, this.kdf, this.aead, arr_b1).open(arr_b3, HpkeDecrypt.EMPTY_ASSOCIATED_DATA);
    }

    private static int encodingSizeInBytes(com.google.crypto.tink.proto.HpkeKem hpkeKem0) {
        switch(com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKem0.ordinal()]) {
            case 1: {
                return 0x20;
            }
            case 2: {
                return 65;
            }
            case 3: {
                return 97;
            }
            case 4: {
                return 0x85;
            }
            default: {
                throw new IllegalArgumentException("Unable to determine KEM-encoding length for " + hpkeKem0.name());
            }
        }
    }

    class com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] arr_v = new int[com.google.crypto.tink.proto.HpkeKem.values().length];
            com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem = arr_v;
            try {
                arr_v[com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeDecrypt.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

