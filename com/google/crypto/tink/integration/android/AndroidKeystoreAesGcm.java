package com.google.crypto.tink.integration.android;

import android.util.Log;
import com.google.crypto.tink.Aead;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.ProviderException;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public final class AndroidKeystoreAesGcm implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 100;
    private static final String TAG = "AndroidKeystoreAesGcm";
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey key;

    static {
    }

    public AndroidKeystoreAesGcm(String s) throws GeneralSecurityException, IOException {
        KeyStore keyStore0 = KeyStore.getInstance("AndroidKeyStore");
        keyStore0.load(null);
        SecretKey secretKey0 = (SecretKey)keyStore0.getKey(s, null);
        this.key = secretKey0;
        if(secretKey0 == null) {
            throw new InvalidKeyException("Keystore cannot load the key with ID: " + s);
        }
    }

    AndroidKeystoreAesGcm(String s, KeyStore keyStore0) throws GeneralSecurityException {
        SecretKey secretKey0 = (SecretKey)keyStore0.getKey(s, null);
        this.key = secretKey0;
        if(secretKey0 == null) {
            throw new InvalidKeyException("Keystore cannot load the key with ID: " + s);
        }
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length >= 28) {
            try {
                return this.decryptInternal(arr_b, arr_b1);
            }
            catch(AEADBadTagException aEADBadTagException0) {
                throw aEADBadTagException0;
            }
            catch(ProviderException | GeneralSecurityException providerException0) {
                Log.w("AndroidKeystoreAesGcm", "encountered a potentially transient KeyStore error, will wait and retry", providerException0);
                AndroidKeystoreAesGcm.sleepRandomAmount();
                return this.decryptInternal(arr_b, arr_b1);
            }
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private byte[] decryptInternal(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        GCMParameterSpec gCMParameterSpec0 = new GCMParameterSpec(0x80, arr_b, 0, 12);
        Cipher cipher0 = Cipher.getInstance("AES/GCM/NoPadding");
        cipher0.init(2, this.key, gCMParameterSpec0);
        cipher0.updateAAD(arr_b1);
        return cipher0.doFinal(arr_b, 12, arr_b.length - 12);
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        try {
            return this.encryptInternal(arr_b, arr_b1);
        }
        catch(ProviderException | GeneralSecurityException providerException0) {
            Log.w("AndroidKeystoreAesGcm", "encountered a potentially transient KeyStore error, will wait and retry", providerException0);
            AndroidKeystoreAesGcm.sleepRandomAmount();
            return this.encryptInternal(arr_b, arr_b1);
        }
    }

    private byte[] encryptInternal(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        if(arr_b.length > 0x7FFFFFE3) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] arr_b2 = new byte[arr_b.length + 28];
        Cipher cipher0 = Cipher.getInstance("AES/GCM/NoPadding");
        cipher0.init(1, this.key);
        cipher0.updateAAD(arr_b1);
        cipher0.doFinal(arr_b, 0, arr_b.length, arr_b2, 12);
        System.arraycopy(cipher0.getIV(), 0, arr_b2, 0, 12);
        return arr_b2;
    }

    private static void sleepRandomAmount() {
        try {
            Thread.sleep(((int)(Math.random() * 100.0)));
        }
        catch(InterruptedException unused_ex) {
        }
    }
}

