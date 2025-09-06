package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
final class NistCurvesHpkeKemPrivateKey implements HpkeKemPrivateKey {
    private final Bytes privateKey;
    private final Bytes publicKey;

    private NistCurvesHpkeKemPrivateKey(byte[] arr_b, byte[] arr_b1) {
        this.privateKey = Bytes.copyFrom(arr_b);
        this.publicKey = Bytes.copyFrom(arr_b1);
    }

    static NistCurvesHpkeKemPrivateKey fromBytes(byte[] arr_b, byte[] arr_b1, CurveType ellipticCurves$CurveType0) throws GeneralSecurityException {
        EllipticCurves.validatePublicKey(EllipticCurves.getEcPublicKey(ellipticCurves$CurveType0, PointFormatType.UNCOMPRESSED, arr_b1), EllipticCurves.getEcPrivateKey(ellipticCurves$CurveType0, arr_b));
        return new NistCurvesHpkeKemPrivateKey(arr_b, arr_b1);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPrivate() {
        return this.privateKey;
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPublic() {
        return this.publicKey;
    }
}

