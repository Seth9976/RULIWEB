package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Hkdf {
    public static byte[] computeEciesHkdfSymmetricKey(byte[] arr_b, byte[] arr_b1, String s, byte[] arr_b2, byte[] arr_b3, int v) throws GeneralSecurityException {
        return Hkdf.computeHkdf(s, Bytes.concat(new byte[][]{arr_b, arr_b1}), arr_b2, arr_b3, v);
    }

    public static byte[] computeHkdf(String s, byte[] arr_b, byte[] arr_b1, byte[] arr_b2, int v) throws GeneralSecurityException {
        Mac mac0 = (Mac)EngineFactory.MAC.getInstance(s);
        if(v > mac0.getMacLength() * 0xFF) {
            throw new GeneralSecurityException("size too large");
        }
        if(arr_b1 == null || arr_b1.length == 0) {
            mac0.init(new SecretKeySpec(new byte[mac0.getMacLength()], s));
        }
        else {
            mac0.init(new SecretKeySpec(arr_b1, s));
        }
        byte[] arr_b3 = mac0.doFinal(arr_b);
        byte[] arr_b4 = new byte[v];
        mac0.init(new SecretKeySpec(arr_b3, s));
        byte[] arr_b5 = new byte[0];
        int v2 = 0;
        for(int v1 = 1; true; ++v1) {
            mac0.update(arr_b5);
            mac0.update(arr_b2);
            mac0.update(((byte)v1));
            arr_b5 = mac0.doFinal();
            if(arr_b5.length + v2 >= v) {
                break;
            }
            System.arraycopy(arr_b5, 0, arr_b4, v2, arr_b5.length);
            v2 += arr_b5.length;
        }
        System.arraycopy(arr_b5, 0, arr_b4, v2, v - v2);
        return arr_b4;
    }
}

