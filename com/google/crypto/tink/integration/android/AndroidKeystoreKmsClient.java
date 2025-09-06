package com.google.crypto.tink.integration.android;

import android.util.Log;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

public final class AndroidKeystoreKmsClient implements KmsClient {
    public static final class Builder {
        KeyStore keyStore;
        String keyUri;

        public Builder() {
            this.keyUri = null;
            this.keyStore = null;
            try {
                KeyStore keyStore0 = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore0;
                keyStore0.load(null);
            }
            catch(GeneralSecurityException | IOException generalSecurityException0) {
                throw new IllegalStateException(generalSecurityException0);
            }
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this, null);
        }

        public Builder setKeyStore(KeyStore keyStore0) {
            if(keyStore0 == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.keyStore = keyStore0;
            return this;
        }

        public Builder setKeyUri(String s) {
            if(s == null || !s.toLowerCase(Locale.US).startsWith("android-keystore://")) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = s;
            return this;
        }
    }

    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 40;
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = "AndroidKeystoreKmsClient";
    private static final Object keyCreationLock;
    private KeyStore keyStore;
    private final String keyUri;

    static {
        AndroidKeystoreKmsClient.keyCreationLock = new Object();
    }

    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        this(new Builder());
    }

    private AndroidKeystoreKmsClient(Builder androidKeystoreKmsClient$Builder0) {
        this.keyUri = androidKeystoreKmsClient$Builder0.keyUri;
        this.keyStore = androidKeystoreKmsClient$Builder0.keyStore;
    }

    AndroidKeystoreKmsClient(Builder androidKeystoreKmsClient$Builder0, com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.1 androidKeystoreKmsClient$10) {
        this(androidKeystoreKmsClient$Builder0);
    }

    @Deprecated
    public AndroidKeystoreKmsClient(String s) {
        this(new Builder().setKeyUri(s));
    }

    // 去混淆评级： 低(20)
    static boolean access$000() [...] // 潜在的解密器

    public void deleteKey(String s) throws GeneralSecurityException {
        synchronized(this) {
            String s1 = Validators.validateKmsKeyUriAndRemovePrefix("android-keystore://", s);
            this.keyStore.deleteEntry(s1);
        }
    }

    @Override  // com.google.crypto.tink.KmsClient
    public boolean doesSupport(String s) {
        boolean z = true;
        synchronized(this) {
            if(this.keyUri != null && this.keyUri.equals(s)) {
                return true;
            }
            if(this.keyUri != null || !s.toLowerCase(Locale.US).startsWith("android-keystore://")) {
                z = false;
            }
            return z;
        }
    }

    static boolean generateKeyIfNotExist(String s) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient0 = new AndroidKeystoreKmsClient();
        synchronized(AndroidKeystoreKmsClient.keyCreationLock) {
            if(!androidKeystoreKmsClient0.hasKey(s)) {
                AndroidKeystoreKmsClient.generateNewAesGcmKeyWithoutExistenceCheck(s);
                return true;
            }
            return false;
        }
    }

    public static void generateNewAeadKey(String s) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient0 = new AndroidKeystoreKmsClient();
        synchronized(AndroidKeystoreKmsClient.keyCreationLock) {
            if(!androidKeystoreKmsClient0.hasKey(s)) {
                AndroidKeystoreKmsClient.generateNewAesGcmKeyWithoutExistenceCheck(s);
                return;
            }
        }
        throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", s));
    }

    static void generateNewAesGcmKeyWithoutExistenceCheck(String s) throws GeneralSecurityException {
        String s1 = Validators.validateKmsKeyUriAndRemovePrefix("android-keystore://", s);
        KeyGenerator keyGenerator0 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator0.init(PrintHelper..ExternalSyntheticApiModelOutline0.m(s1, 3).setKeySize(0x100).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
        keyGenerator0.generateKey();
    }

    @Override  // com.google.crypto.tink.KmsClient
    public Aead getAead(String s) throws GeneralSecurityException {
        synchronized(this) {
            if(this.keyUri != null && !this.keyUri.equals(s)) {
                throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, s));
            }
            return AndroidKeystoreKmsClient.validateAead(new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix("android-keystore://", s), this.keyStore));
        }
    }

    public static Aead getOrGenerateNewAeadKey(String s) throws GeneralSecurityException, IOException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient0 = new AndroidKeystoreKmsClient();
        synchronized(AndroidKeystoreKmsClient.keyCreationLock) {
            if(!androidKeystoreKmsClient0.hasKey(s)) {
                AndroidKeystoreKmsClient.generateNewAesGcmKeyWithoutExistenceCheck(s);
            }
        }
        return androidKeystoreKmsClient0.getAead(s);
    }

    boolean hasKey(String s) throws GeneralSecurityException {
        synchronized(this) {
            String s1 = Validators.validateKmsKeyUriAndRemovePrefix("android-keystore://", s);
            try {
                return this.keyStore.containsAlias(s1);
            }
            catch(NullPointerException unused_ex) {
                Log.w("AndroidKeystoreKmsClient", "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
                try {
                    AndroidKeystoreKmsClient.sleepRandomAmount();
                    KeyStore keyStore0 = KeyStore.getInstance("AndroidKeyStore");
                    this.keyStore = keyStore0;
                    keyStore0.load(null);
                }
                catch(IOException iOException0) {
                    throw new GeneralSecurityException(iOException0);
                }
                return this.keyStore.containsAlias(s1);
            }
        }
    }

    private static boolean isAtLeastM() [...] // 潜在的解密器

    private static void sleepRandomAmount() {
        try {
            Thread.sleep(((int)(Math.random() * 40.0)));
        }
        catch(InterruptedException unused_ex) {
        }
    }

    private static Aead validateAead(Aead aead0) throws GeneralSecurityException {
        byte[] arr_b = {-75, 3, -87, 0x75, 36, -25, -104, 18, -57, -19};
        byte[] arr_b1 = new byte[0];
        if(!Arrays.equals(arr_b, aead0.decrypt(aead0.encrypt(arr_b, arr_b1), arr_b1))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return aead0;
    }

    @Override  // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String s) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override  // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    class com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.1 {
    }

}

