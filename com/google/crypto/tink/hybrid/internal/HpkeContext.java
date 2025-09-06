package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.subtle.Bytes;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

final class HpkeContext {
    private static final byte[] EMPTY_IKM;
    private final HpkeAead aead;
    private final byte[] baseNonce;
    private final byte[] encapsulatedKey;
    private final byte[] key;
    private final BigInteger maxSequenceNumber;
    private BigInteger sequenceNumber;

    static {
        HpkeContext.EMPTY_IKM = new byte[0];
    }

    private HpkeContext(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, BigInteger bigInteger0, HpkeAead hpkeAead0) {
        this.encapsulatedKey = arr_b;
        this.key = arr_b1;
        this.baseNonce = arr_b2;
        this.sequenceNumber = BigInteger.ZERO;
        this.maxSequenceNumber = bigInteger0;
        this.aead = hpkeAead0;
    }

    private byte[] computeNonce() throws GeneralSecurityException {
        byte[] arr_b = BigIntegerEncoding.toBigEndianBytesOfFixedLength(this.sequenceNumber, this.aead.getNonceLength());
        return Bytes.xor(this.baseNonce, arr_b);
    }

    private byte[] computeNonceAndIncrementSequenceNumber() throws GeneralSecurityException {
        synchronized(this) {
            byte[] arr_b = this.computeNonce();
            this.incrementSequenceNumber();
            return arr_b;
        }
    }

    static HpkeContext createContext(byte[] arr_b, byte[] arr_b1, HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0, byte[] arr_b2) throws GeneralSecurityException {
        byte[] arr_b3 = HpkeUtil.hpkeSuiteId(hpkeKem0.getKemId(), hpkeKdf0.getKdfId(), hpkeAead0.getAeadId());
        byte[] arr_b4 = hpkeKdf0.labeledExtract(HpkeUtil.EMPTY_SALT, HpkeContext.EMPTY_IKM, "psk_id_hash", arr_b3);
        byte[] arr_b5 = hpkeKdf0.labeledExtract(HpkeUtil.EMPTY_SALT, arr_b2, "info_hash", arr_b3);
        byte[] arr_b6 = Bytes.concat(new byte[][]{HpkeUtil.BASE_MODE, arr_b4, arr_b5});
        byte[] arr_b7 = hpkeKdf0.labeledExtract(arr_b1, HpkeContext.EMPTY_IKM, "secret", arr_b3);
        return new HpkeContext(arr_b, hpkeKdf0.labeledExpand(arr_b7, arr_b6, "key", arr_b3, hpkeAead0.getKeyLength()), hpkeKdf0.labeledExpand(arr_b7, arr_b6, "base_nonce", arr_b3, hpkeAead0.getNonceLength()), HpkeContext.maxSequenceNumber(hpkeAead0.getNonceLength()), hpkeAead0);
    }

    static HpkeContext createRecipientContext(byte[] arr_b, HpkeKemPrivateKey hpkeKemPrivateKey0, HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0, byte[] arr_b1) throws GeneralSecurityException {
        return HpkeContext.createContext(arr_b, hpkeKem0.decapsulate(arr_b, hpkeKemPrivateKey0), hpkeKem0, hpkeKdf0, hpkeAead0, arr_b1);
    }

    static HpkeContext createSenderContext(HpkePublicKey hpkePublicKey0, HpkeKem hpkeKem0, HpkeKdf hpkeKdf0, HpkeAead hpkeAead0, byte[] arr_b) throws GeneralSecurityException {
        HpkeKemEncapOutput hpkeKemEncapOutput0 = hpkeKem0.encapsulate(hpkePublicKey0.getPublicKey().toByteArray());
        return HpkeContext.createContext(hpkeKemEncapOutput0.getEncapsulatedKey(), hpkeKemEncapOutput0.getSharedSecret(), hpkeKem0, hpkeKdf0, hpkeAead0, arr_b);
    }

    byte[] getBaseNonce() {
        return this.baseNonce;
    }

    byte[] getEncapsulatedKey() {
        return this.encapsulatedKey;
    }

    byte[] getKey() {
        return this.key;
    }

    private void incrementSequenceNumber() throws GeneralSecurityException {
        if(this.sequenceNumber.compareTo(this.maxSequenceNumber) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.sequenceNumber = this.sequenceNumber.add(BigInteger.ONE);
    }

    private static BigInteger maxSequenceNumber(int v) {
        return BigInteger.ONE.shiftLeft(v * 8).subtract(BigInteger.ONE);
    }

    byte[] open(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = this.computeNonceAndIncrementSequenceNumber();
        return this.aead.open(this.key, arr_b2, arr_b, arr_b1);
    }

    byte[] seal(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = this.computeNonceAndIncrementSequenceNumber();
        return this.aead.seal(this.key, arr_b2, arr_b, arr_b1);
    }
}

