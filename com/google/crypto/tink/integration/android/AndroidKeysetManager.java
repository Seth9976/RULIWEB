package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.BinaryKeysetReader;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.subtle.Hex;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;

public final class AndroidKeysetManager {
    public static final class Builder {
        private Context context;
        private KeyTemplate keyTemplate;
        private KeysetManager keysetManager;
        private String keysetName;
        private Aead masterAead;
        private String masterKeyUri;
        private String prefFileName;
        private boolean useKeystore;

        public Builder() {
            this.context = null;
            this.keysetName = null;
            this.prefFileName = null;
            this.masterKeyUri = null;
            this.masterAead = null;
            this.useKeystore = true;
            this.keyTemplate = null;
        }

        static Context access$000(Builder androidKeysetManager$Builder0) {
            return androidKeysetManager$Builder0.context;
        }

        static String access$100(Builder androidKeysetManager$Builder0) {
            return androidKeysetManager$Builder0.keysetName;
        }

        static String access$200(Builder androidKeysetManager$Builder0) {
            return androidKeysetManager$Builder0.prefFileName;
        }

        static Aead access$300(Builder androidKeysetManager$Builder0) {
            return androidKeysetManager$Builder0.masterAead;
        }

        static KeysetManager access$400(Builder androidKeysetManager$Builder0) {
            return androidKeysetManager$Builder0.keysetManager;
        }

        public AndroidKeysetManager build() throws GeneralSecurityException, IOException {
            synchronized(this) {
                if(this.keysetName != null) {
                    Object object0 = AndroidKeysetManager.lock;
                    synchronized(object0) {
                        byte[] arr_b = Builder.readKeysetFromPrefs(this.context, this.keysetName, this.prefFileName);
                        if(arr_b == null) {
                            if(this.masterKeyUri != null) {
                                this.masterAead = this.readOrGenerateNewMasterKey();
                            }
                            this.keysetManager = this.generateKeysetAndWriteToPrefs();
                        }
                        else if(this.masterKeyUri == null) {
                            this.keysetManager = this.readKeysetInCleartext(arr_b);
                        }
                        else {
                            this.keysetManager = this.readMasterkeyDecryptAndParseKeyset(arr_b);
                        }
                        return new AndroidKeysetManager(this, null);
                    }
                }
            }
            throw new IllegalArgumentException("keysetName cannot be null");
        }

        @Deprecated
        public Builder doNotUseKeystore() {
            this.masterKeyUri = null;
            this.useKeystore = false;
            return this;
        }

        private KeysetManager generateKeysetAndWriteToPrefs() throws GeneralSecurityException, IOException {
            if(this.keyTemplate == null) {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
            KeysetManager keysetManager0 = KeysetManager.withEmptyKeyset().add(this.keyTemplate);
            KeysetManager keysetManager1 = keysetManager0.setPrimary(keysetManager0.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
            SharedPrefKeysetWriter sharedPrefKeysetWriter0 = new SharedPrefKeysetWriter(this.context, this.keysetName, this.prefFileName);
            if(this.masterAead != null) {
                keysetManager1.getKeysetHandle().write(sharedPrefKeysetWriter0, this.masterAead);
                return keysetManager1;
            }
            CleartextKeysetHandle.write(keysetManager1.getKeysetHandle(), sharedPrefKeysetWriter0);
            return keysetManager1;
        }

        private static byte[] readKeysetFromPrefs(Context context0, String s, String s1) throws IOException {
            if(s != null) {
                Context context1 = context0.getApplicationContext();
                SharedPreferences sharedPreferences0 = s1 == null ? PreferenceManager.getDefaultSharedPreferences(context1) : context1.getSharedPreferences(s1, 0);
                try {
                    String s2 = sharedPreferences0.getString(s, null);
                    return s2 == null ? null : Hex.decode(s2);
                }
                catch(ClassCastException | IllegalArgumentException unused_ex) {
                    throw new CharConversionException(String.format("can\'t read keyset; the pref value %s is not a valid hex string", s));
                }
            }
            throw new IllegalArgumentException("keysetName cannot be null");
        }

        private KeysetManager readKeysetInCleartext(byte[] arr_b) throws GeneralSecurityException, IOException {
            return KeysetManager.withKeysetHandle(CleartextKeysetHandle.read(BinaryKeysetReader.withBytes(arr_b)));
        }

        private KeysetManager readMasterkeyDecryptAndParseKeyset(byte[] arr_b) throws GeneralSecurityException, IOException {
            try {
                this.masterAead = new AndroidKeystoreKmsClient().getAead(this.masterKeyUri);
            }
            catch(GeneralSecurityException | ProviderException generalSecurityException0) {
                try {
                    KeysetManager keysetManager0 = this.readKeysetInCleartext(arr_b);
                    Log.w("AndroidKeysetManager", "cannot use Android Keystore, it\'ll be disabled", generalSecurityException0);
                    return keysetManager0;
                }
                catch(IOException unused_ex) {
                    throw generalSecurityException0;
                }
            }
            try {
                return KeysetManager.withKeysetHandle(KeysetHandle.read(BinaryKeysetReader.withBytes(arr_b), this.masterAead));
            }
            catch(IOException | GeneralSecurityException iOException0) {
                try {
                    return this.readKeysetInCleartext(arr_b);
                }
                catch(IOException unused_ex) {
                    throw iOException0;
                }
            }
        }

        private Aead readOrGenerateNewMasterKey() throws GeneralSecurityException {
            AndroidKeystoreKmsClient androidKeystoreKmsClient0 = new AndroidKeystoreKmsClient();
            try {
                boolean z = AndroidKeystoreKmsClient.generateKeyIfNotExist(this.masterKeyUri);
            }
            catch(GeneralSecurityException | ProviderException generalSecurityException0) {
                Log.w("AndroidKeysetManager", "cannot use Android Keystore, it\'ll be disabled", generalSecurityException0);
                return null;
            }
            try {
                return androidKeystoreKmsClient0.getAead(this.masterKeyUri);
            }
            catch(GeneralSecurityException | ProviderException generalSecurityException1) {
                if(!z) {
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.masterKeyUri), generalSecurityException1);
                }
                Log.w("AndroidKeysetManager", "cannot use Android Keystore, it\'ll be disabled", generalSecurityException1);
                return null;
            }
        }

        public Builder withKeyTemplate(KeyTemplate keyTemplate0) {
            this.keyTemplate = keyTemplate0;
            return this;
        }

        @Deprecated
        public Builder withKeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) {
            this.keyTemplate = KeyTemplate.create("", keyTemplate0.getValue().toByteArray(), AndroidKeysetManager.fromProto(keyTemplate0.getOutputPrefixType()));
            return this;
        }

        public Builder withMasterKeyUri(String s) {
            if(!s.startsWith("android-keystore://")) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            if(!this.useKeystore) {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            this.masterKeyUri = s;
            return this;
        }

        public Builder withSharedPref(Context context0, String s, String s1) throws IOException {
            if(context0 == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            if(s == null) {
                throw new IllegalArgumentException("need a keyset name");
            }
            this.context = context0;
            this.keysetName = s;
            this.prefFileName = s1;
            return this;
        }
    }

    private static final String TAG = "AndroidKeysetManager";
    private KeysetManager keysetManager;
    private static final Object lock;
    private final Aead masterAead;
    private final KeysetWriter writer;

    static {
        AndroidKeysetManager.lock = new Object();
    }

    private AndroidKeysetManager(Builder androidKeysetManager$Builder0) {
        this.writer = new SharedPrefKeysetWriter(Builder.access$000(androidKeysetManager$Builder0), Builder.access$100(androidKeysetManager$Builder0), Builder.access$200(androidKeysetManager$Builder0));
        this.masterAead = Builder.access$300(androidKeysetManager$Builder0);
        this.keysetManager = Builder.access$400(androidKeysetManager$Builder0);
    }

    AndroidKeysetManager(Builder androidKeysetManager$Builder0, com.google.crypto.tink.integration.android.AndroidKeysetManager.1 androidKeysetManager$10) {
        this(androidKeysetManager$Builder0);
    }

    // 去混淆评级： 低(20)
    static boolean access$700() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    static String access$900() [...] // 潜在的解密器

    public AndroidKeysetManager add(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.add(keyTemplate0);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    @Deprecated
    public AndroidKeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.add(keyTemplate0);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    public AndroidKeysetManager delete(int v) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.delete(v);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    public AndroidKeysetManager destroy(int v) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.destroy(v);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    public AndroidKeysetManager disable(int v) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.disable(v);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    public AndroidKeysetManager enable(int v) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.enable(v);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    private static OutputPrefixType fromProto(com.google.crypto.tink.proto.OutputPrefixType outputPrefixType0) {
        switch(com.google.crypto.tink.integration.android.AndroidKeysetManager.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType0.ordinal()]) {
            case 1: {
                return OutputPrefixType.TINK;
            }
            case 2: {
                return OutputPrefixType.LEGACY;
            }
            case 3: {
                return OutputPrefixType.RAW;
            }
            case 4: {
                return OutputPrefixType.CRUNCHY;
            }
            default: {
                throw new IllegalArgumentException("Unknown output prefix type");
            }
        }
    }

    public KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        synchronized(this) {
            return this.keysetManager.getKeysetHandle();
        }
    }

    private static boolean isAtLeastM() [...] // 潜在的解密器

    public boolean isUsingKeystore() {
        synchronized(this) {
            return this.shouldUseKeystore();
        }
    }

    @Deprecated
    public AndroidKeysetManager promote(int v) throws GeneralSecurityException {
        synchronized(this) {
            return this.setPrimary(v);
        }
    }

    @Deprecated
    public AndroidKeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.rotate(keyTemplate0);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    public AndroidKeysetManager setPrimary(int v) throws GeneralSecurityException {
        synchronized(this) {
            KeysetManager keysetManager0 = this.keysetManager.setPrimary(v);
            this.keysetManager = keysetManager0;
            this.write(keysetManager0);
            return this;
        }
    }

    // 去混淆评级： 低(30)
    private boolean shouldUseKeystore() {
        return this.masterAead != null;
    }

    private void write(KeysetManager keysetManager0) throws GeneralSecurityException {
        try {
            if(this.shouldUseKeystore()) {
                keysetManager0.getKeysetHandle().write(this.writer, this.masterAead);
                return;
            }
            CleartextKeysetHandle.write(keysetManager0.getKeysetHandle(), this.writer);
        }
        catch(IOException iOException0) {
            throw new GeneralSecurityException(iOException0);
        }
    }

    class com.google.crypto.tink.integration.android.AndroidKeysetManager.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] arr_v = new int[com.google.crypto.tink.proto.OutputPrefixType.values().length];
            com.google.crypto.tink.integration.android.AndroidKeysetManager.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = arr_v;
            try {
                arr_v[com.google.crypto.tink.proto.OutputPrefixType.TINK.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.integration.android.AndroidKeysetManager.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.LEGACY.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.integration.android.AndroidKeysetManager.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.RAW.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.integration.android.AndroidKeysetManager.1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

