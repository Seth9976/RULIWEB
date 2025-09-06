package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.X25519;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Immutable
final class X25519HpkeKem implements HpkeKem {
    private final HkdfHpkeKdf hkdf;

    X25519HpkeKem(HkdfHpkeKdf hkdfHpkeKdf0) {
        this.hkdf = hkdfHpkeKdf0;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] arr_b, HpkeKemPrivateKey hpkeKemPrivateKey0) throws GeneralSecurityException {
        return this.deriveKemSharedSecret(X25519.computeSharedSecret(hpkeKemPrivateKey0.getSerializedPrivate().toByteArray(), arr_b), arr_b, hpkeKemPrivateKey0.getSerializedPublic().toByteArray());
    }

    private byte[] deriveKemSharedSecret(byte[] arr_b, byte[] arr_b1, byte[] arr_b2) throws GeneralSecurityException {
        byte[] arr_b3 = Bytes.concat(new byte[][]{arr_b1, arr_b2});
        int v = this.hkdf.getMacLength();
        return this.hkdf.extractAndExpand(null, arr_b, "eae_prk", arr_b3, "shared_secret", new byte[]{75, 69, 77, 0, 0x20}, v);
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] arr_b) throws GeneralSecurityException {
        return this.encapsulate(arr_b, new byte[]{-9, 43, -37, -81, -62, 53, 91, 0x7E, -20, 62, -18, -109, 120, 27, 80, 62, (byte)0x80, 0x77, 0x72, 0x70, 23, (byte)0x85, 87, 103, 50, 11, 53, 0x1F, -83, -120, (byte)0x86, -72});
    }

    HpkeKemEncapOutput encapsulate(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = X25519.computeSharedSecret(arr_b1, arr_b);
        byte[] arr_b3 = X25519.publicFromPrivate(arr_b1);
        return new HpkeKemEncapOutput(this.deriveKemSharedSecret(arr_b2, arr_b3, arr_b), arr_b3);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        if(!Arrays.equals(this.hkdf.getKdfId(), HpkeUtil.HKDF_SHA256_KDF_ID)) {
            throw new GeneralSecurityException("Could not determine HPKE KEM ID");
        }
        return HpkeUtil.X25519_HKDF_SHA256_KEM_ID;
    }
}

