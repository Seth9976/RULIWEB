package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce;
import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class AesGcmJce implements Aead {
    public static final AlgorithmFipsCompatibility FIPS;
    private final InsecureNonceAesGcmJce insecureNonceAesGcmJce;

    static {
        AesGcmJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public AesGcmJce(byte[] arr_b) throws GeneralSecurityException {
        if(!AesGcmJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.insecureNonceAesGcmJce = new InsecureNonceAesGcmJce(arr_b, true);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = Arrays.copyOf(arr_b, 12);
        return this.insecureNonceAesGcmJce.decrypt(arr_b2, arr_b, arr_b1);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return this.insecureNonceAesGcmJce.encrypt(new byte[]{8, -70, -106, -77, -12, -92, 70, -17, 92, 87, 76, 11}, arr_b, arr_b1);
    }
}

