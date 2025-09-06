package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Mac;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptThenAuthenticate implements Aead {
    private final IndCpaCipher cipher;
    private final Mac mac;
    private final int macLength;

    public EncryptThenAuthenticate(IndCpaCipher indCpaCipher0, Mac mac0, int v) {
        this.cipher = indCpaCipher0;
        this.mac = mac0;
        this.macLength = v;
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        int v = this.macLength;
        if(arr_b.length < v) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] arr_b2 = Arrays.copyOfRange(arr_b, 0, arr_b.length - v);
        byte[] arr_b3 = Arrays.copyOfRange(arr_b, arr_b.length - this.macLength, arr_b.length);
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        byte[] arr_b4 = Bytes.concat(new byte[][]{arr_b1, arr_b2, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long)arr_b1.length) * 8L).array(), 8)});
        this.mac.verifyMac(arr_b3, arr_b4);
        return this.cipher.decrypt(arr_b2);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = this.cipher.encrypt(arr_b);
        if(arr_b1 == null) {
            arr_b1 = new byte[0];
        }
        byte[] arr_b3 = Bytes.concat(new byte[][]{arr_b1, arr_b2, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long)arr_b1.length) * 8L).array(), 8)});
        return Bytes.concat(new byte[][]{arr_b2, this.mac.computeMac(arr_b3)});
    }

    public static Aead newAesCtrHmac(byte[] arr_b, int v, String s, byte[] arr_b1, int v1) throws GeneralSecurityException {
        return new EncryptThenAuthenticate(new AesCtrJceCipher(arr_b, v), new PrfMac(new PrfHmacJce(s, new SecretKeySpec(arr_b1, "HMAC")), v1), v1);
    }
}

