package androidx.security.crypto;

import android.security.keystore.KeyGenParameterSpec;
import androidx.print.PrintHelper..ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.ProviderException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;

@Deprecated
public final class MasterKeys {
    public static final KeyGenParameterSpec AES256_GCM_SPEC = null;
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    static final int KEY_SIZE = 0x100;
    static final String MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    private static final Object sLock;

    static {
        MasterKeys.AES256_GCM_SPEC = MasterKeys.createAES256GCMKeyGenParameterSpec("_androidx_security_master_key_");
        MasterKeys.sLock = new Object();
    }

    private static KeyGenParameterSpec createAES256GCMKeyGenParameterSpec(String s) {
        return PrintHelper..ExternalSyntheticApiModelOutline0.m(s, 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).setKeySize(0x100).build();
    }

    private static void generateKey(KeyGenParameterSpec keyGenParameterSpec0) throws GeneralSecurityException {
        try {
            KeyGenerator keyGenerator0 = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator0.init(keyGenParameterSpec0);
            keyGenerator0.generateKey();
        }
        catch(ProviderException providerException0) {
            throw new GeneralSecurityException(providerException0.getMessage(), providerException0);
        }
    }

    public static String getOrCreate(KeyGenParameterSpec keyGenParameterSpec0) throws GeneralSecurityException, IOException {
        MasterKeys.validate(keyGenParameterSpec0);
        synchronized(MasterKeys.sLock) {
            if(!MasterKeys.keyExists(keyGenParameterSpec0.getKeystoreAlias())) {
                MasterKeys.generateKey(keyGenParameterSpec0);
            }
        }
        return keyGenParameterSpec0.getKeystoreAlias();
    }

    private static boolean keyExists(String s) throws GeneralSecurityException, IOException {
        KeyStore keyStore0 = KeyStore.getInstance("AndroidKeyStore");
        keyStore0.load(null);
        return keyStore0.containsAlias(s);
    }

    static void validate(KeyGenParameterSpec keyGenParameterSpec0) {
        if(keyGenParameterSpec0.getKeySize() != 0x100) {
            throw new IllegalArgumentException("invalid key size, want 256 bits got " + keyGenParameterSpec0.getKeySize() + " bits");
        }
        if(!Arrays.equals(keyGenParameterSpec0.getBlockModes(), new String[]{"GCM"})) {
            throw new IllegalArgumentException("invalid block mode, want GCM got " + Arrays.toString(keyGenParameterSpec0.getBlockModes()));
        }
        if(keyGenParameterSpec0.getPurposes() != 3) {
            throw new IllegalArgumentException("invalid purposes mode, want PURPOSE_ENCRYPT | PURPOSE_DECRYPT got " + keyGenParameterSpec0.getPurposes());
        }
        if(!Arrays.equals(keyGenParameterSpec0.getEncryptionPaddings(), new String[]{"NoPadding"})) {
            throw new IllegalArgumentException("invalid padding mode, want NoPadding got " + Arrays.toString(keyGenParameterSpec0.getEncryptionPaddings()));
        }
        if(keyGenParameterSpec0.isUserAuthenticationRequired() && keyGenParameterSpec0.getUserAuthenticationValidityDurationSeconds() < 1) {
            throw new IllegalArgumentException("per-operation authentication is not supported (UserAuthenticationValidityDurationSeconds must be >0)");
        }
    }
}

