package androidx.security.crypto;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import android.util.Pair;
import androidx.collection.ArraySet;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.integration.android.AndroidKeysetManager.Builder;
import com.google.crypto.tink.subtle.Base64;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class EncryptedSharedPreferences implements SharedPreferences {
    static final class Editor implements SharedPreferences.Editor {
        private final AtomicBoolean mClearRequested;
        private final SharedPreferences.Editor mEditor;
        private final EncryptedSharedPreferences mEncryptedSharedPreferences;
        private final List mKeysChanged;

        Editor(EncryptedSharedPreferences encryptedSharedPreferences0, SharedPreferences.Editor sharedPreferences$Editor0) {
            this.mClearRequested = new AtomicBoolean(false);
            this.mEncryptedSharedPreferences = encryptedSharedPreferences0;
            this.mEditor = sharedPreferences$Editor0;
            this.mKeysChanged = new CopyOnWriteArrayList();
        }

        @Override  // android.content.SharedPreferences$Editor
        public void apply() {
            this.clearKeysIfNeeded();
            this.mEditor.apply();
            this.notifyListeners();
            this.mKeysChanged.clear();
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor clear() {
            this.mClearRequested.set(true);
            return this;
        }

        private void clearKeysIfNeeded() {
            if(this.mClearRequested.getAndSet(false)) {
                for(Object object0: this.mEncryptedSharedPreferences.getAll().keySet()) {
                    String s = (String)object0;
                    if(!this.mKeysChanged.contains(s) && !this.mEncryptedSharedPreferences.isReservedKey(s)) {
                        String s1 = this.mEncryptedSharedPreferences.encryptKey(s);
                        this.mEditor.remove(s1);
                    }
                }
            }
        }

        @Override  // android.content.SharedPreferences$Editor
        public boolean commit() {
            this.clearKeysIfNeeded();
            try {
                return this.mEditor.commit();
            }
            finally {
                this.notifyListeners();
                this.mKeysChanged.clear();
            }
        }

        private void notifyListeners() {
            for(Object object0: this.mEncryptedSharedPreferences.mListeners) {
                SharedPreferences.OnSharedPreferenceChangeListener sharedPreferences$OnSharedPreferenceChangeListener0 = (SharedPreferences.OnSharedPreferenceChangeListener)object0;
                for(Object object1: this.mKeysChanged) {
                    sharedPreferences$OnSharedPreferenceChangeListener0.onSharedPreferenceChanged(this.mEncryptedSharedPreferences, ((String)object1));
                }
            }
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putBoolean(String s, boolean z) {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(5);
            byteBuffer0.putInt(EncryptedType.BOOLEAN.getId());
            byteBuffer0.put(((byte)z));
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        private void putEncryptedObject(String s, byte[] arr_b) {
            if(!this.mEncryptedSharedPreferences.isReservedKey(s)) {
                this.mKeysChanged.add(s);
                if(s == null) {
                    s = "__NULL__";
                }
                try {
                    Pair pair0 = this.mEncryptedSharedPreferences.encryptKeyValuePair(s, arr_b);
                    this.mEditor.putString(((String)pair0.first), ((String)pair0.second));
                    return;
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    throw new SecurityException("Could not encrypt data: " + generalSecurityException0.getMessage(), generalSecurityException0);
                }
            }
            throw new SecurityException(s + " is a reserved key for the encryption keyset.");
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putFloat(String s, float f) {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(8);
            byteBuffer0.putInt(EncryptedType.FLOAT.getId());
            byteBuffer0.putFloat(f);
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putInt(String s, int v) {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(8);
            byteBuffer0.putInt(EncryptedType.INT.getId());
            byteBuffer0.putInt(v);
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putLong(String s, long v) {
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(12);
            byteBuffer0.putInt(EncryptedType.LONG.getId());
            byteBuffer0.putLong(v);
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putString(String s, String s1) {
            if(s1 == null) {
                s1 = "__NULL__";
            }
            byte[] arr_b = s1.getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(arr_b.length + 8);
            byteBuffer0.putInt(EncryptedType.STRING.getId());
            byteBuffer0.putInt(arr_b.length);
            byteBuffer0.put(arr_b);
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor putStringSet(String s, Set set0) {
            if(set0 == null) {
                set0 = new ArraySet();
                set0.add("__NULL__");
            }
            ArrayList arrayList0 = new ArrayList(set0.size());
            int v = set0.size() * 4;
            for(Object object0: set0) {
                byte[] arr_b = ((String)object0).getBytes(StandardCharsets.UTF_8);
                arrayList0.add(arr_b);
                v += arr_b.length;
            }
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(v + 4);
            byteBuffer0.putInt(EncryptedType.STRING_SET.getId());
            for(Object object1: arrayList0) {
                byteBuffer0.putInt(((byte[])object1).length);
                byteBuffer0.put(((byte[])object1));
            }
            this.putEncryptedObject(s, byteBuffer0.array());
            return this;
        }

        @Override  // android.content.SharedPreferences$Editor
        public SharedPreferences.Editor remove(String s) {
            if(this.mEncryptedSharedPreferences.isReservedKey(s)) {
                throw new SecurityException(s + " is a reserved key for the encryption keyset.");
            }
            String s1 = this.mEncryptedSharedPreferences.encryptKey(s);
            this.mEditor.remove(s1);
            this.mKeysChanged.add(s);
            return this;
        }
    }

    static enum EncryptedType {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);

        private final int mId;

        private static EncryptedType[] $values() [...] // Inlined contents

        private EncryptedType(int v1) {
            this.mId = v1;
        }

        public static EncryptedType fromId(int v) {
            switch(v) {
                case 0: {
                    return EncryptedType.STRING;
                }
                case 1: {
                    return EncryptedType.STRING_SET;
                }
                case 2: {
                    return EncryptedType.INT;
                }
                case 3: {
                    return EncryptedType.LONG;
                }
                case 4: {
                    return EncryptedType.FLOAT;
                }
                case 5: {
                    return EncryptedType.BOOLEAN;
                }
                default: {
                    return null;
                }
            }
        }

        public int getId() {
            return this.mId;
        }
    }

    public static enum PrefKeyEncryptionScheme {
        AES256_SIV("AES256_SIV");

        private final String mDeterministicAeadKeyTemplateName;

        private static PrefKeyEncryptionScheme[] $values() [...] // Inlined contents

        private PrefKeyEncryptionScheme(String s1) {
            this.mDeterministicAeadKeyTemplateName = s1;
        }

        KeyTemplate getKeyTemplate() throws GeneralSecurityException {
            return KeyTemplates.get(this.mDeterministicAeadKeyTemplateName);
        }
    }

    public static enum PrefValueEncryptionScheme {
        AES256_GCM("AES256_GCM");

        private final String mAeadKeyTemplateName;

        private static PrefValueEncryptionScheme[] $values() [...] // Inlined contents

        private PrefValueEncryptionScheme(String s1) {
            this.mAeadKeyTemplateName = s1;
        }

        KeyTemplate getKeyTemplate() throws GeneralSecurityException {
            return KeyTemplates.get(this.mAeadKeyTemplateName);
        }
    }

    private static final String KEY_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_key_keyset__";
    private static final String NULL_VALUE = "__NULL__";
    private static final String VALUE_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_value_keyset__";
    final String mFileName;
    final DeterministicAead mKeyDeterministicAead;
    final CopyOnWriteArrayList mListeners;
    final String mMasterKeyAlias;
    final SharedPreferences mSharedPreferences;
    final Aead mValueAead;

    EncryptedSharedPreferences(String s, String s1, SharedPreferences sharedPreferences0, Aead aead0, DeterministicAead deterministicAead0) {
        this.mFileName = s;
        this.mSharedPreferences = sharedPreferences0;
        this.mMasterKeyAlias = s1;
        this.mValueAead = aead0;
        this.mKeyDeterministicAead = deterministicAead0;
        this.mListeners = new CopyOnWriteArrayList();
    }

    @Override  // android.content.SharedPreferences
    public boolean contains(String s) {
        if(this.isReservedKey(s)) {
            throw new SecurityException(s + " is a reserved key for the encryption keyset.");
        }
        String s1 = this.encryptKey(s);
        return this.mSharedPreferences.contains(s1);
    }

    public static SharedPreferences create(Context context0, String s, MasterKey masterKey0, PrefKeyEncryptionScheme encryptedSharedPreferences$PrefKeyEncryptionScheme0, PrefValueEncryptionScheme encryptedSharedPreferences$PrefValueEncryptionScheme0) throws GeneralSecurityException, IOException {
        return EncryptedSharedPreferences.create(s, masterKey0.getKeyAlias(), context0, encryptedSharedPreferences$PrefKeyEncryptionScheme0, encryptedSharedPreferences$PrefValueEncryptionScheme0);
    }

    @Deprecated
    public static SharedPreferences create(String s, String s1, Context context0, PrefKeyEncryptionScheme encryptedSharedPreferences$PrefKeyEncryptionScheme0, PrefValueEncryptionScheme encryptedSharedPreferences$PrefValueEncryptionScheme0) throws GeneralSecurityException, IOException {
        DeterministicAeadConfig.register();
        AeadConfig.register();
        Context context1 = context0.getApplicationContext();
        KeysetHandle keysetHandle0 = new Builder().withKeyTemplate(encryptedSharedPreferences$PrefKeyEncryptionScheme0.getKeyTemplate()).withSharedPref(context1, "__androidx_security_crypto_encrypted_prefs_key_keyset__", s).withMasterKeyUri("android-keystore://" + s1).build().getKeysetHandle();
        KeysetHandle keysetHandle1 = new Builder().withKeyTemplate(encryptedSharedPreferences$PrefValueEncryptionScheme0.getKeyTemplate()).withSharedPref(context1, "__androidx_security_crypto_encrypted_prefs_value_keyset__", s).withMasterKeyUri("android-keystore://" + s1).build().getKeysetHandle();
        Object object0 = keysetHandle0.getPrimitive(DeterministicAead.class);
        Object object1 = keysetHandle1.getPrimitive(Aead.class);
        return new EncryptedSharedPreferences(s, s1, context1.getSharedPreferences(s, 0), ((Aead)object1), ((DeterministicAead)object0));
    }

    String decryptKey(String s) {
        try {
            byte[] arr_b = Base64.decode(s, 0);
            String s1 = new String(this.mKeyDeterministicAead.decryptDeterministically(arr_b, this.mFileName.getBytes()), StandardCharsets.UTF_8);
            return s1.equals("__NULL__") ? null : s1;
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new SecurityException("Could not decrypt key. " + generalSecurityException0.getMessage(), generalSecurityException0);
        }
    }

    @Override  // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new Editor(this, this.mSharedPreferences.edit());
    }

    String encryptKey(String s) {
        if(s == null) {
            s = "__NULL__";
        }
        try {
            return Base64.encode(this.mKeyDeterministicAead.encryptDeterministically(s.getBytes(StandardCharsets.UTF_8), this.mFileName.getBytes()));
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new SecurityException("Could not encrypt key. " + generalSecurityException0.getMessage(), generalSecurityException0);
        }
    }

    Pair encryptKeyValuePair(String s, byte[] arr_b) throws GeneralSecurityException {
        String s1 = this.encryptKey(s);
        return new Pair(s1, Base64.encode(this.mValueAead.encrypt(arr_b, s1.getBytes(StandardCharsets.UTF_8))));
    }

    @Override  // android.content.SharedPreferences
    public Map getAll() {
        Map map0 = new HashMap();
        for(Object object0: this.mSharedPreferences.getAll().entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(!this.isReservedKey(((String)map$Entry0.getKey()))) {
                String s = this.decryptKey(((String)map$Entry0.getKey()));
                map0.put(s, this.getDecryptedObject(s));
            }
        }
        return map0;
    }

    @Override  // android.content.SharedPreferences
    public boolean getBoolean(String s, boolean z) {
        Object object0 = this.getDecryptedObject(s);
        return object0 instanceof Boolean ? ((Boolean)object0).booleanValue() : z;
    }

    private Object getDecryptedObject(String s) throws SecurityException {
        if(!this.isReservedKey(s)) {
            if(s == null) {
                s = "__NULL__";
            }
            try {
                String s1 = this.encryptKey(s);
                String s2 = this.mSharedPreferences.getString(s1, null);
                if(s2 == null) {
                    return null;
                }
                boolean z = false;
                byte[] arr_b = Base64.decode(s2, 0);
                ByteBuffer byteBuffer0 = ByteBuffer.wrap(this.mValueAead.decrypt(arr_b, s1.getBytes(StandardCharsets.UTF_8)));
                byteBuffer0.position(0);
                int v = byteBuffer0.getInt();
                EncryptedType encryptedSharedPreferences$EncryptedType0 = EncryptedType.fromId(v);
                if(encryptedSharedPreferences$EncryptedType0 != null) {
                    switch(androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[encryptedSharedPreferences$EncryptedType0.ordinal()]) {
                        case 1: {
                            int v1 = byteBuffer0.getInt();
                            ByteBuffer byteBuffer1 = byteBuffer0.slice();
                            byteBuffer0.limit(v1);
                            String s3 = StandardCharsets.UTF_8.decode(byteBuffer1).toString();
                            return s3.equals("__NULL__") ? null : s3;
                        }
                        case 2: {
                            return byteBuffer0.getInt();
                        }
                        case 3: {
                            return byteBuffer0.getLong();
                        }
                        case 4: {
                            return byteBuffer0.getFloat();
                        }
                        case 5: {
                            if(byteBuffer0.get() != 0) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        }
                        case 6: {
                            ArraySet arraySet0 = new ArraySet();
                            while(byteBuffer0.hasRemaining()) {
                                int v2 = byteBuffer0.getInt();
                                ByteBuffer byteBuffer2 = byteBuffer0.slice();
                                byteBuffer2.limit(v2);
                                byteBuffer0.position(byteBuffer0.position() + v2);
                                arraySet0.add(StandardCharsets.UTF_8.decode(byteBuffer2).toString());
                            }
                            return arraySet0.size() == 1 && "__NULL__".equals(arraySet0.valueAt(0)) ? null : arraySet0;
                        }
                        default: {
                            throw new SecurityException("Unhandled type for encrypted pref value: " + encryptedSharedPreferences$EncryptedType0);
                        }
                    }
                }
                throw new SecurityException("Unknown type ID for encrypted pref value: " + v);
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new SecurityException("Could not decrypt value. " + generalSecurityException0.getMessage(), generalSecurityException0);
            }
        }
        throw new SecurityException(s + " is a reserved key for the encryption keyset.");
    }

    @Override  // android.content.SharedPreferences
    public float getFloat(String s, float f) {
        Object object0 = this.getDecryptedObject(s);
        return object0 instanceof Float ? ((float)(((Float)object0))) : f;
    }

    @Override  // android.content.SharedPreferences
    public int getInt(String s, int v) {
        Object object0 = this.getDecryptedObject(s);
        return object0 instanceof Integer ? ((int)(((Integer)object0))) : v;
    }

    @Override  // android.content.SharedPreferences
    public long getLong(String s, long v) {
        Object object0 = this.getDecryptedObject(s);
        return object0 instanceof Long ? ((long)(((Long)object0))) : v;
    }

    @Override  // android.content.SharedPreferences
    public String getString(String s, String s1) {
        Object object0 = this.getDecryptedObject(s);
        return object0 instanceof String ? ((String)object0) : s1;
    }

    @Override  // android.content.SharedPreferences
    public Set getStringSet(String s, Set set0) {
        Set set1;
        Object object0 = this.getDecryptedObject(s);
        if(object0 instanceof Set) {
            set1 = (Set)object0;
            return set1.size() <= 0 ? set0 : set1;
        }
        set1 = new ArraySet();
        return set1.size() <= 0 ? set0 : set1;
    }

    // 去混淆评级： 低(20)
    boolean isReservedKey(String s) {
        return "__androidx_security_crypto_encrypted_prefs_key_keyset__".equals(s) || "__androidx_security_crypto_encrypted_prefs_value_keyset__".equals(s);
    }

    @Override  // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferences$OnSharedPreferenceChangeListener0) {
        this.mListeners.add(sharedPreferences$OnSharedPreferenceChangeListener0);
    }

    @Override  // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferences$OnSharedPreferenceChangeListener0) {
        this.mListeners.remove(sharedPreferences$OnSharedPreferenceChangeListener0);
    }

    class androidx.security.crypto.EncryptedSharedPreferences.1 {
        static final int[] $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType;

        static {
            int[] arr_v = new int[EncryptedType.values().length];
            androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType = arr_v;
            try {
                arr_v[EncryptedType.STRING.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.INT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.LONG.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.FLOAT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.security.crypto.EncryptedSharedPreferences.1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.STRING_SET.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

