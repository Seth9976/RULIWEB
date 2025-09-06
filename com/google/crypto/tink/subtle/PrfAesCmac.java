package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.mac.internal.AesUtil;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Immutable
public final class PrfAesCmac implements Prf {
    public static final AlgorithmFipsCompatibility FIPS;
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;

    static {
        PrfAesCmac.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public PrfAesCmac(byte[] arr_b) throws GeneralSecurityException {
        Validators.validateAesKeySize(arr_b.length);
        this.keySpec = new SecretKeySpec(arr_b, "AES");
        this.generateSubKeys();
    }

    @Override  // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] arr_b, int v) throws GeneralSecurityException {
        if(v > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipher0 = PrfAesCmac.instance();
        cipher0.init(1, this.keySpec);
        int v1 = Math.max(1, ((int)Math.ceil(((double)arr_b.length) / 16.0)));
        byte[] arr_b1 = v1 * 16 == arr_b.length ? Bytes.xor(arr_b, (v1 - 1) * 16, this.subKey1, 0, 16) : Bytes.xor(AesUtil.cmacPad(Arrays.copyOfRange(arr_b, (v1 - 1) * 16, arr_b.length)), this.subKey2);
        byte[] arr_b2 = new byte[16];
        for(int v2 = 0; v2 < v1 - 1; ++v2) {
            arr_b2 = cipher0.doFinal(Bytes.xor(arr_b2, 0, arr_b, v2 * 16, 16));
        }
        return Arrays.copyOf(cipher0.doFinal(Bytes.xor(arr_b1, arr_b2)), v);
    }

    private void generateSubKeys() throws GeneralSecurityException {
        Cipher cipher0 = PrfAesCmac.instance();
        cipher0.init(1, this.keySpec);
        byte[] arr_b = AesUtil.dbl(cipher0.doFinal(new byte[16]));
        this.subKey1 = arr_b;
        this.subKey2 = AesUtil.dbl(arr_b);
    }

    private static Cipher instance() throws GeneralSecurityException {
        if(!PrfAesCmac.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        return (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }
}

