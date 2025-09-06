package androidx.security.crypto;

import android.content.Context;
import android.os.Build.VERSION;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public final class MasterKey {
    static class Api23Impl {
        static int getUserAuthenticationValidityDurationSeconds(KeyGenParameterSpec keyGenParameterSpec0) {
            return keyGenParameterSpec0.getUserAuthenticationValidityDurationSeconds();
        }

        static boolean isUserAuthenticationRequired(KeyGenParameterSpec keyGenParameterSpec0) {
            return keyGenParameterSpec0.isUserAuthenticationRequired();
        }
    }

    static class Api28Impl {
        static boolean isStrongBoxBacked(KeyGenParameterSpec keyGenParameterSpec0) {
            return keyGenParameterSpec0.isStrongBoxBacked();
        }
    }

    public static final class Builder {
        static class androidx.security.crypto.MasterKey.Builder.Api23Impl {
            static class androidx.security.crypto.MasterKey.Builder.Api23Impl.Api28Impl {
                static void setIsStrongBoxBacked(KeyGenParameterSpec.Builder keyGenParameterSpec$Builder0) {
                    keyGenParameterSpec$Builder0.setIsStrongBoxBacked(true);
                }
            }

            static class Api30Impl {
                static void setUserAuthenticationParameters(KeyGenParameterSpec.Builder keyGenParameterSpec$Builder0, int v, int v1) {
                    keyGenParameterSpec$Builder0.setUserAuthenticationParameters(v, v1);
                }
            }

            static MasterKey build(Builder masterKey$Builder0) throws GeneralSecurityException, IOException {
                if(masterKey$Builder0.mKeyScheme == null && masterKey$Builder0.mKeyGenParameterSpec == null) {
                    throw new IllegalArgumentException("build() called before setKeyGenParameterSpec or setKeyScheme.");
                }
                if(masterKey$Builder0.mKeyScheme == KeyScheme.AES256_GCM) {
                    KeyGenParameterSpec.Builder keyGenParameterSpec$Builder0 = new KeyGenParameterSpec.Builder(masterKey$Builder0.mKeyAlias, 3).setBlockModes(new String[]{"GCM"}).setEncryptionPaddings(new String[]{"NoPadding"}).setKeySize(0x100);
                    if(masterKey$Builder0.mAuthenticationRequired) {
                        keyGenParameterSpec$Builder0.setUserAuthenticationRequired(true);
                        if(Build.VERSION.SDK_INT >= 30) {
                            Api30Impl.setUserAuthenticationParameters(keyGenParameterSpec$Builder0, masterKey$Builder0.mUserAuthenticationValidityDurationSeconds, 3);
                        }
                        else {
                            keyGenParameterSpec$Builder0.setUserAuthenticationValidityDurationSeconds(masterKey$Builder0.mUserAuthenticationValidityDurationSeconds);
                        }
                    }
                    if(Build.VERSION.SDK_INT >= 28 && masterKey$Builder0.mRequestStrongBoxBacked && masterKey$Builder0.mContext.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        androidx.security.crypto.MasterKey.Builder.Api23Impl.Api28Impl.setIsStrongBoxBacked(keyGenParameterSpec$Builder0);
                    }
                    masterKey$Builder0.mKeyGenParameterSpec = keyGenParameterSpec$Builder0.build();
                }
                if(masterKey$Builder0.mKeyGenParameterSpec == null) {
                    throw new NullPointerException("KeyGenParameterSpec was null after build() check");
                }
                return new MasterKey(MasterKeys.getOrCreate(masterKey$Builder0.mKeyGenParameterSpec), masterKey$Builder0.mKeyGenParameterSpec);
            }

            static String getKeystoreAlias(KeyGenParameterSpec keyGenParameterSpec0) {
                return keyGenParameterSpec0.getKeystoreAlias();
            }
        }

        boolean mAuthenticationRequired;
        final Context mContext;
        final String mKeyAlias;
        KeyGenParameterSpec mKeyGenParameterSpec;
        KeyScheme mKeyScheme;
        boolean mRequestStrongBoxBacked;
        int mUserAuthenticationValidityDurationSeconds;

        public Builder(Context context0) {
            this(context0, "_androidx_security_master_key_");
        }

        public Builder(Context context0, String s) {
            this.mContext = context0.getApplicationContext();
            this.mKeyAlias = s;
        }

        public MasterKey build() throws GeneralSecurityException, IOException {
            return Build.VERSION.SDK_INT < 23 ? new MasterKey(this.mKeyAlias, null) : androidx.security.crypto.MasterKey.Builder.Api23Impl.build(this);
        }

        public Builder setKeyGenParameterSpec(KeyGenParameterSpec keyGenParameterSpec0) {
            if(this.mKeyScheme != null) {
                throw new IllegalArgumentException("KeyGenParamSpec set after setting a KeyScheme");
            }
            String s = androidx.security.crypto.MasterKey.Builder.Api23Impl.getKeystoreAlias(keyGenParameterSpec0);
            if(!this.mKeyAlias.equals(s)) {
                throw new IllegalArgumentException("KeyGenParamSpec\'s key alias does not match provided alias (" + this.mKeyAlias + " vs " + androidx.security.crypto.MasterKey.Builder.Api23Impl.getKeystoreAlias(keyGenParameterSpec0));
            }
            this.mKeyGenParameterSpec = keyGenParameterSpec0;
            return this;
        }

        public Builder setKeyScheme(KeyScheme masterKey$KeyScheme0) {
            if(androidx.security.crypto.MasterKey.1.$SwitchMap$androidx$security$crypto$MasterKey$KeyScheme[masterKey$KeyScheme0.ordinal()] != 1) {
                throw new IllegalArgumentException("Unsupported scheme: " + masterKey$KeyScheme0);
            }
            if(Build.VERSION.SDK_INT >= 23 && this.mKeyGenParameterSpec != null) {
                throw new IllegalArgumentException("KeyScheme set after setting a KeyGenParamSpec");
            }
            this.mKeyScheme = masterKey$KeyScheme0;
            return this;
        }

        public Builder setRequestStrongBoxBacked(boolean z) {
            this.mRequestStrongBoxBacked = z;
            return this;
        }

        public Builder setUserAuthenticationRequired(boolean z) {
            return this.setUserAuthenticationRequired(z, 300);
        }

        public Builder setUserAuthenticationRequired(boolean z, int v) {
            this.mAuthenticationRequired = z;
            this.mUserAuthenticationValidityDurationSeconds = v;
            return this;
        }
    }

    public static enum KeyScheme {
        AES256_GCM;

        private static KeyScheme[] $values() [...] // Inlined contents
    }

    public static final int DEFAULT_AES_GCM_MASTER_KEY_SIZE = 0x100;
    private static final int DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS = 300;
    public static final String DEFAULT_MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    static final String KEYSTORE_PATH_URI = "android-keystore://";
    private final String mKeyAlias;
    private final KeyGenParameterSpec mKeyGenParameterSpec;

    MasterKey(String s, Object object0) {
        this.mKeyAlias = s;
        if(Build.VERSION.SDK_INT >= 23) {
            this.mKeyGenParameterSpec = (KeyGenParameterSpec)object0;
            return;
        }
        this.mKeyGenParameterSpec = null;
    }

    public static int getDefaultAuthenticationValidityDurationSeconds() [...] // Inlined contents

    String getKeyAlias() {
        return this.mKeyAlias;
    }

    public int getUserAuthenticationValidityDurationSeconds() {
        if(Build.VERSION.SDK_INT < 23) {
            return 0;
        }
        return this.mKeyGenParameterSpec == null ? 0 : Api23Impl.getUserAuthenticationValidityDurationSeconds(this.mKeyGenParameterSpec);
    }

    public boolean isKeyStoreBacked() {
        if(Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            KeyStore keyStore0 = KeyStore.getInstance("AndroidKeyStore");
            keyStore0.load(null);
            return keyStore0.containsAlias(this.mKeyAlias);
        }
        catch(KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException unused_ex) {
            return false;
        }
    }

    public boolean isStrongBoxBacked() {
        if(Build.VERSION.SDK_INT >= 28) {
            return this.mKeyGenParameterSpec == null ? false : Api28Impl.isStrongBoxBacked(this.mKeyGenParameterSpec);
        }
        return false;
    }

    public boolean isUserAuthenticationRequired() {
        return Build.VERSION.SDK_INT >= 23 ? this.mKeyGenParameterSpec != null && Api23Impl.isUserAuthenticationRequired(this.mKeyGenParameterSpec) : false;
    }

    @Override
    public String toString() {
        return "MasterKey{keyAlias=" + this.mKeyAlias + ", isKeyStoreBacked=" + this.isKeyStoreBacked() + "}";
    }

    class androidx.security.crypto.MasterKey.1 {
        static final int[] $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme;

        static {
            int[] arr_v = new int[KeyScheme.values().length];
            androidx.security.crypto.MasterKey.1.$SwitchMap$androidx$security$crypto$MasterKey$KeyScheme = arr_v;
            try {
                arr_v[KeyScheme.AES256_GCM.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

