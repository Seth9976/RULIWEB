package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Immutable
final class HkdfHpkeKdf implements HpkeKdf {
    private final String macAlgorithm;

    HkdfHpkeKdf(String s) {
        this.macAlgorithm = s;
    }

    private byte[] expand(byte[] arr_b, byte[] arr_b1, int v) throws GeneralSecurityException {
        Mac mac0 = (Mac)EngineFactory.MAC.getInstance(this.macAlgorithm);
        if(v > mac0.getMacLength() * 0xFF) {
            throw new GeneralSecurityException("size too large");
        }
        byte[] arr_b2 = new byte[v];
        mac0.init(new SecretKeySpec(arr_b, this.macAlgorithm));
        byte[] arr_b3 = new byte[0];
        int v2 = 0;
        for(int v1 = 1; true; ++v1) {
            mac0.update(arr_b3);
            mac0.update(arr_b1);
            mac0.update(((byte)v1));
            arr_b3 = mac0.doFinal();
            if(arr_b3.length + v2 >= v) {
                break;
            }
            System.arraycopy(arr_b3, 0, arr_b2, v2, arr_b3.length);
            v2 += arr_b3.length;
        }
        System.arraycopy(arr_b3, 0, arr_b2, v2, v - v2);
        return arr_b2;
    }

    private byte[] extract(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        Mac mac0 = (Mac)EngineFactory.MAC.getInstance(this.macAlgorithm);
        if(arr_b1 != null && arr_b1.length != 0) {
            mac0.init(new SecretKeySpec(arr_b1, this.macAlgorithm));
            return mac0.doFinal(arr_b);
        }
        mac0.init(new SecretKeySpec(new byte[mac0.getMacLength()], this.macAlgorithm));
        return mac0.doFinal(arr_b);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] extractAndExpand(byte[] arr_b, byte[] arr_b1, String s, byte[] arr_b2, String s1, byte[] arr_b3, int v) throws GeneralSecurityException {
        return this.expand(this.extract(HpkeUtil.labelIkm(s, arr_b1, arr_b3), arr_b), HpkeUtil.labelInfo(s1, arr_b2, arr_b3, v), v);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] getKdfId() throws GeneralSecurityException {
        this.macAlgorithm.hashCode();
        switch(this.macAlgorithm) {
            case "HmacSha256": {
                return HpkeUtil.HKDF_SHA256_KDF_ID;
            }
            case "HmacSha384": {
                return HpkeUtil.HKDF_SHA384_KDF_ID;
            }
            case "HmacSha512": {
                return HpkeUtil.HKDF_SHA512_KDF_ID;
            }
            default: {
                throw new GeneralSecurityException("Could not determine HPKE KDF ID");
            }
        }
    }

    int getMacLength() throws GeneralSecurityException {
        return Mac.getInstance(this.macAlgorithm).getMacLength();
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExpand(byte[] arr_b, byte[] arr_b1, String s, byte[] arr_b2, int v) throws GeneralSecurityException {
        return this.expand(arr_b, HpkeUtil.labelInfo(s, arr_b1, arr_b2, v), v);
    }

    @Override  // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExtract(byte[] arr_b, byte[] arr_b1, String s, byte[] arr_b2) throws GeneralSecurityException {
        return this.extract(HpkeUtil.labelIkm(s, arr_b1, arr_b2), arr_b);
    }
}

