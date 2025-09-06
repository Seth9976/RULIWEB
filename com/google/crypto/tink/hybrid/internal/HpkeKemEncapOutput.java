package com.google.crypto.tink.hybrid.internal;

final class HpkeKemEncapOutput {
    private final byte[] encapsulatedKey;
    private final byte[] sharedSecret;

    HpkeKemEncapOutput(byte[] arr_b, byte[] arr_b1) {
        this.sharedSecret = arr_b;
        this.encapsulatedKey = arr_b1;
    }

    byte[] getEncapsulatedKey() {
        return this.encapsulatedKey;
    }

    byte[] getSharedSecret() {
        return this.sharedSecret;
    }
}

