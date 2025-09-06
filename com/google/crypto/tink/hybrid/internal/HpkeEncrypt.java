package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
final class HpkeEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA;
    private final HpkeAead aead;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkePublicKey recipientPublicKey;

    static {
        HpkeEncrypt.EMPTY_ASSOCIATED_DATA = new byte[0];
    }

    private HpkeEncrypt(HpkePublicKey hpkePublicKey0, HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0) {
        this.recipientPublicKey = hpkePublicKey0;
        this.kem = hpkeKem0;
        this.kdf = hpkeKdf0;
        this.aead = hpkeAead0;
    }

    static HpkeEncrypt createHpkeEncrypt(HpkePublicKey hpkePublicKey0) throws GeneralSecurityException {
        if(hpkePublicKey0.getPublicKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        HpkeParams hpkeParams0 = hpkePublicKey0.getParams();
        return new HpkeEncrypt(hpkePublicKey0, HpkePrimitiveFactory.createKem(hpkeParams0), HpkePrimitiveFactory.createKdf(hpkeParams0), HpkePrimitiveFactory.createAead(hpkeParams0));
    }

    @Override  // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        HpkeContext hpkeContext0 = HpkeContext.createSenderContext(this.recipientPublicKey, this.kem, this.kdf, this.aead, arr_b1);
        return Bytes.concat(new byte[][]{hpkeContext0.getEncapsulatedKey(), hpkeContext0.seal(arr_b, HpkeEncrypt.EMPTY_ASSOCIATED_DATA)});
    }
}

