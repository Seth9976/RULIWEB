package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

@Immutable
public final class PrfHmacJce implements Prf {
    public static final AlgorithmFipsCompatibility FIPS = null;
    static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private final String algorithm;
    private final Key key;
    private final ThreadLocal localMac;
    private final int maxOutputLength;

    static {
        PrfHmacJce.FIPS = AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    public PrfHmacJce(String s, Key key0) throws GeneralSecurityException {
        com.google.crypto.tink.subtle.PrfHmacJce.1 prfHmacJce$10 = new ThreadLocal() {
            @Override
            protected Object initialValue() {
                return this.initialValue();
            }

            protected Mac initialValue() {
                try {
                    Mac mac0 = (Mac)EngineFactory.MAC.getInstance(PrfHmacJce.this.algorithm);
                    mac0.init(PrfHmacJce.this.key);
                    return mac0;
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new IllegalStateException(generalSecurityException0);
                }
            }
        };
        this.localMac = prfHmacJce$10;
        if(!PrfHmacJce.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.algorithm = s;
        this.key = key0;
        if(key0.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        s.hashCode();
        switch(s) {
            case "HMACSHA1": {
                this.maxOutputLength = 20;
                break;
            }
            case "HMACSHA224": {
                this.maxOutputLength = 28;
                break;
            }
            case "HMACSHA256": {
                this.maxOutputLength = 0x20;
                break;
            }
            case "HMACSHA384": {
                this.maxOutputLength = 0x30;
                break;
            }
            case "HMACSHA512": {
                this.maxOutputLength = 0x40;
                break;
            }
            default: {
                throw new NoSuchAlgorithmException("unknown Hmac algorithm: " + s);
            }
        }
        prfHmacJce$10.get();
    }

    @Override  // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] arr_b, int v) throws GeneralSecurityException {
        if(v > this.maxOutputLength) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        ((Mac)this.localMac.get()).update(arr_b);
        return Arrays.copyOf(((Mac)this.localMac.get()).doFinal(), v);
    }

    public int getMaxOutputLength() {
        return this.maxOutputLength;
    }
}

