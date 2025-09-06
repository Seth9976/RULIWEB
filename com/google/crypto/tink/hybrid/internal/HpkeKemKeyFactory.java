package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkeKem;
import com.google.crypto.tink.proto.HpkePrivateKey;
import java.security.GeneralSecurityException;

final class HpkeKemKeyFactory {
    static HpkeKemPrivateKey createPrivate(HpkePrivateKey hpkePrivateKey0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkePrivateKey0.getPublicKey().getParams().getKem().ordinal()]) {
            case 1: {
                return X25519HpkeKemPrivateKey.fromBytes(hpkePrivateKey0.getPrivateKey().toByteArray());
            }
            case 2: 
            case 3: 
            case 4: {
                return NistCurvesHpkeKemPrivateKey.fromBytes(hpkePrivateKey0.getPrivateKey().toByteArray(), hpkePrivateKey0.getPublicKey().getPublicKey().toByteArray(), HpkeUtil.nistHpkeKemToCurve(hpkePrivateKey0.getPublicKey().getParams().getKem()));
            }
            default: {
                throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
            }
        }
    }

    class com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] arr_v = new int[HpkeKem.values().length];
            com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem = arr_v;
            try {
                arr_v[HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory.1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

