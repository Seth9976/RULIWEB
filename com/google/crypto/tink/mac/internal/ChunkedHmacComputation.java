package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.mac.HmacParameters.Variant;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

final class ChunkedHmacComputation implements ChunkedMacComputation {
    private static final byte[] FORMAT_VERSION;
    private boolean finalized;
    private final HmacKey key;
    private final Mac mac;

    static {
        ChunkedHmacComputation.FORMAT_VERSION = new byte[]{0};
    }

    ChunkedHmacComputation(HmacKey hmacKey0) throws GeneralSecurityException {
        this.finalized = false;
        String s = ChunkedHmacComputation.composeAlgorithmName(hmacKey0);
        Mac mac0 = (Mac)EngineFactory.MAC.getInstance(s);
        this.mac = mac0;
        mac0.init(new SecretKeySpec(hmacKey0.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), "HMAC"));
        this.key = hmacKey0;
    }

    private static String composeAlgorithmName(HmacKey hmacKey0) {
        return "HMAC" + hmacKey0.getParameters().getHashType();
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacComputation
    public byte[] computeMac() throws GeneralSecurityException {
        if(this.finalized) {
            throw new IllegalStateException("Cannot compute after already computing the MAC tag. Please create a new object.");
        }
        if(this.key.getParameters().getVariant() == Variant.LEGACY) {
            this.update(ByteBuffer.wrap(ChunkedHmacComputation.FORMAT_VERSION));
        }
        this.finalized = true;
        return Bytes.concat(new byte[][]{this.key.getOutputPrefix().toByteArray(), Arrays.copyOf(this.mac.doFinal(), this.key.getParameters().getCryptographicTagSizeBytes())});
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacComputation
    public void update(ByteBuffer byteBuffer0) {
        if(this.finalized) {
            throw new IllegalStateException("Cannot update after computing the MAC tag. Please create a new object.");
        }
        this.mac.update(byteBuffer0);
    }
}

