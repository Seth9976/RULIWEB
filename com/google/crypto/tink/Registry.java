package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory.KeyFormat;
import com.google.crypto.tink.internal.KeyTypeManager.KeyFactory;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class Registry {
    interface KeyDeriverContainer {
        KeyData deriveKey(ByteString arg1, InputStream arg2) throws GeneralSecurityException;
    }

    private static final ConcurrentMap catalogueMap;
    private static final ConcurrentMap keyDeriverMap;
    private static final AtomicReference keyManagerRegistry;
    private static final ConcurrentMap keyTemplateMap;
    private static final Logger logger;
    private static final ConcurrentMap newKeyAllowedMap;

    static {
        Registry.logger = Logger.getLogger("com.google.crypto.tink.Registry");
        Registry.keyManagerRegistry = new AtomicReference(new KeyManagerRegistry());
        Registry.keyDeriverMap = new ConcurrentHashMap();
        Registry.newKeyAllowedMap = new ConcurrentHashMap();
        Registry.catalogueMap = new ConcurrentHashMap();
        Registry.keyTemplateMap = new ConcurrentHashMap();
    }

    @Deprecated
    public static void addCatalogue(String s, Catalogue catalogue0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(s != null) {
                if(catalogue0 == null) {
                    throw new IllegalArgumentException("catalogue must be non-null.");
                }
                ConcurrentMap concurrentMap0 = Registry.catalogueMap;
                if(concurrentMap0.containsKey(s.toLowerCase(Locale.US))) {
                    Catalogue catalogue1 = (Catalogue)concurrentMap0.get(s.toLowerCase(Locale.US));
                    if(!catalogue0.getClass().getName().equals(catalogue1.getClass().getName())) {
                        Registry.logger.warning("Attempted overwrite of a catalogueName catalogue for name " + s);
                        throw new GeneralSecurityException("catalogue for name " + s + " has been already registered");
                    }
                }
                concurrentMap0.put(s.toLowerCase(Locale.US), catalogue0);
                return;
            }
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    private static KeyDeriverContainer createDeriverFor(KeyTypeManager keyTypeManager0) {
        return new KeyDeriverContainer() {
            @Override  // com.google.crypto.tink.Registry$KeyDeriverContainer
            public KeyData deriveKey(ByteString byteString0, InputStream inputStream0) throws GeneralSecurityException {
                MessageLite messageLite0 = this.deriveKeyWithFactory(byteString0, inputStream0, keyTypeManager0.keyFactory());
                return (KeyData)KeyData.newBuilder().setTypeUrl(keyTypeManager0.getKeyType()).setValue(messageLite0.toByteString()).setKeyMaterialType(keyTypeManager0.keyMaterialType()).build();
            }

            private MessageLite deriveKeyWithFactory(ByteString byteString0, InputStream inputStream0, KeyFactory keyTypeManager$KeyFactory0) throws GeneralSecurityException {
                MessageLite messageLite0;
                try {
                    messageLite0 = keyTypeManager$KeyFactory0.parseKeyFormat(byteString0);
                }
                catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", invalidProtocolBufferException0);
                }
                keyTypeManager$KeyFactory0.validateKeyFormat(messageLite0);
                return keyTypeManager$KeyFactory0.deriveKey(messageLite0, inputStream0);
            }
        };
    }

    static KeyData deriveKey(KeyTemplate keyTemplate0, InputStream inputStream0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            ConcurrentMap concurrentMap0 = Registry.keyDeriverMap;
            if(concurrentMap0.containsKey("")) {
                return ((KeyDeriverContainer)concurrentMap0.get("")).deriveKey(keyTemplate0.getValue(), inputStream0);
            }
        }
        throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for ");
    }

    private static void ensureKeyManagerInsertable(String s, Map map0, boolean z) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(z && (Registry.newKeyAllowedMap.containsKey(s) && !((Boolean)Registry.newKeyAllowedMap.get(s)).booleanValue())) {
                throw new GeneralSecurityException("New keys are already disallowed for key type " + s);
            }
            if(z) {
                if(((KeyManagerRegistry)Registry.keyManagerRegistry.get()).typeUrlExists(s)) {
                    for(Object object0: map0.entrySet()) {
                        Map.Entry map$Entry0 = (Map.Entry)object0;
                        Object object1 = map$Entry0.getKey();
                        if(!Registry.keyTemplateMap.containsKey(object1)) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String)map$Entry0.getKey()) + " from an existing key manager of type " + s);
                        }
                    }
                }
                else {
                    for(Object object2: map0.entrySet()) {
                        Map.Entry map$Entry1 = (Map.Entry)object2;
                        Object object3 = map$Entry1.getKey();
                        if(Registry.keyTemplateMap.containsKey(object3)) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template " + ((String)map$Entry1.getKey()));
                        }
                        if(false) {
                            break;
                        }
                    }
                }
            }
        }
    }

    @Deprecated
    public static Catalogue getCatalogue(String s) throws GeneralSecurityException {
        if(s == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        Catalogue catalogue0 = (Catalogue)Registry.catalogueMap.get(s.toLowerCase(Locale.US));
        if(catalogue0 == null) {
            String s1 = s.toLowerCase(Locale.US).startsWith("tinkaead") ? String.format("no catalogue found for %s. ", s) + "Maybe call AeadConfig.register()." : String.format("no catalogue found for %s. ", s);
            if(s.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
                s1 = s1 + "Maybe call DeterministicAeadConfig.register().";
            }
            else if(s.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
                s1 = s1 + "Maybe call StreamingAeadConfig.register().";
            }
            else if(s.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || s.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
                s1 = s1 + "Maybe call HybridConfig.register().";
            }
            else if(s.toLowerCase(Locale.US).startsWith("tinkmac")) {
                s1 = s1 + "Maybe call MacConfig.register().";
            }
            else if(s.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || s.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                s1 = s1 + "Maybe call SignatureConfig.register().";
            }
            else if(s.toLowerCase(Locale.US).startsWith("tink")) {
                s1 = s1 + "Maybe call TinkConfig.register().";
            }
            throw new GeneralSecurityException(s1);
        }
        return catalogue0;
    }

    static Object getFullPrimitive(Key key0, Class class0) throws GeneralSecurityException {
        return MutablePrimitiveRegistry.globalInstance().getPrimitive(key0, class0);
    }

    @Nullable
    public static Class getInputPrimitive(Class class0) {
        try {
            return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(class0);
        }
        catch(GeneralSecurityException unused_ex) {
            return null;
        }
    }

    @Deprecated
    public static KeyManager getKeyManager(String s) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s);
    }

    public static KeyManager getKeyManager(String s, Class class0) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s, class0);
    }

    // 去混淆评级： 低(20)
    @Deprecated
    public static Object getPrimitive(KeyData keyData0) throws GeneralSecurityException {
        return Registry.getPrimitive("", keyData0.getValue());
    }

    // 去混淆评级： 低(20)
    public static Object getPrimitive(KeyData keyData0, Class class0) throws GeneralSecurityException {
        return Registry.getPrimitive("", keyData0.getValue(), class0);
    }

    @Deprecated
    public static Object getPrimitive(String s, ByteString byteString0) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s).getPrimitive(byteString0);
    }

    public static Object getPrimitive(String s, ByteString byteString0, Class class0) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s, class0).getPrimitive(byteString0);
    }

    @Deprecated
    public static Object getPrimitive(String s, MessageLite messageLite0) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s).getPrimitive(messageLite0);
    }

    public static Object getPrimitive(String s, MessageLite messageLite0, Class class0) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getKeyManager(s, class0).getPrimitive(messageLite0);
    }

    @Deprecated
    public static Object getPrimitive(String s, byte[] arr_b) throws GeneralSecurityException {
        return Registry.getPrimitive(s, ByteString.copyFrom(arr_b));
    }

    public static Object getPrimitive(String s, byte[] arr_b, Class class0) throws GeneralSecurityException {
        return Registry.getPrimitive(s, ByteString.copyFrom(arr_b), class0);
    }

    public static KeyData getPublicKeyData(String s, ByteString byteString0) throws GeneralSecurityException {
        KeyManager keyManager0 = Registry.getKeyManager(s);
        if(!(keyManager0 instanceof PrivateKeyManager)) {
            throw new GeneralSecurityException("manager for key type " + s + " is not a PrivateKeyManager");
        }
        return ((PrivateKeyManager)keyManager0).getPublicKeyData(byteString0);
    }

    public static KeyManager getUntypedKeyManager(String s) throws GeneralSecurityException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).getUntypedKeyManager(s);
    }

    static Map keyTemplateMap() {
        synchronized(Registry.class) {
            return Collections.unmodifiableMap(Registry.keyTemplateMap);
        }
    }

    public static List keyTemplates() {
        synchronized(Registry.class) {
            ArrayList arrayList0 = new ArrayList();
            arrayList0.addAll(Registry.keyTemplateMap.keySet());
            return Collections.unmodifiableList(arrayList0);
        }
    }

    public static MessageLite newKey(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            KeyManager keyManager0 = Registry.getUntypedKeyManager("");
            if(((Boolean)Registry.newKeyAllowedMap.get("")).booleanValue()) {
                return keyManager0.newKey(keyTemplate0.getValue());
            }
        }
        throw new GeneralSecurityException("newKey-operation not permitted for key type ");
    }

    public static MessageLite newKey(String s, MessageLite messageLite0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            KeyManager keyManager0 = Registry.getKeyManager(s);
            if(((Boolean)Registry.newKeyAllowedMap.get(s)).booleanValue()) {
                return keyManager0.newKey(messageLite0);
            }
        }
        throw new GeneralSecurityException("newKey-operation not permitted for key type " + s);
    }

    public static KeyData newKeyData(com.google.crypto.tink.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            return Registry.newKeyData(keyTemplate0.getProto());
        }
    }

    public static KeyData newKeyData(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            KeyManager keyManager0 = Registry.getUntypedKeyManager("");
            if(((Boolean)Registry.newKeyAllowedMap.get("")).booleanValue()) {
                return keyManager0.newKeyData(keyTemplate0.getValue());
            }
        }
        throw new GeneralSecurityException("newKey-operation not permitted for key type ");
    }

    static MessageLite parseKeyData(KeyData keyData0) throws GeneralSecurityException, InvalidProtocolBufferException {
        return ((KeyManagerRegistry)Registry.keyManagerRegistry.get()).parseKeyData(keyData0);
    }

    public static void registerAsymmetricKeyManagers(PrivateKeyTypeManager privateKeyTypeManager0, KeyTypeManager keyTypeManager0, boolean z) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(privateKeyTypeManager0 != null && keyTypeManager0 != null) {
                AtomicReference atomicReference0 = Registry.keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry0 = new KeyManagerRegistry(((KeyManagerRegistry)atomicReference0.get()));
                keyManagerRegistry0.registerAsymmetricKeyManagers(privateKeyTypeManager0, keyTypeManager0);
                String s = privateKeyTypeManager0.getKeyType();
                String s1 = keyTypeManager0.getKeyType();
                Registry.ensureKeyManagerInsertable(s, (z ? privateKeyTypeManager0.keyFactory().keyFormats() : Collections.EMPTY_MAP), z);
                Registry.ensureKeyManagerInsertable(s1, Collections.EMPTY_MAP, false);
                if(!((KeyManagerRegistry)atomicReference0.get()).typeUrlExists(s)) {
                    KeyDeriverContainer registry$KeyDeriverContainer0 = Registry.createDeriverFor(privateKeyTypeManager0);
                    Registry.keyDeriverMap.put(s, registry$KeyDeriverContainer0);
                    if(z) {
                        Registry.registerKeyTemplates(privateKeyTypeManager0.getKeyType(), privateKeyTypeManager0.keyFactory().keyFormats());
                    }
                }
                Registry.newKeyAllowedMap.put(s, Boolean.valueOf(z));
                Registry.newKeyAllowedMap.put(s1, Boolean.FALSE);
                atomicReference0.set(keyManagerRegistry0);
                return;
            }
        }
        throw new IllegalArgumentException("given key managers must be non-null.");
    }

    public static void registerKeyManager(KeyManager keyManager0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            Registry.registerKeyManager(keyManager0, true);
        }
    }

    public static void registerKeyManager(KeyManager keyManager0, boolean z) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(keyManager0 != null) {
                AtomicReference atomicReference0 = Registry.keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry0 = new KeyManagerRegistry(((KeyManagerRegistry)atomicReference0.get()));
                keyManagerRegistry0.registerKeyManager(keyManager0);
                if(!AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                    throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
                }
                String s = keyManager0.getKeyType();
                Registry.ensureKeyManagerInsertable(s, Collections.EMPTY_MAP, z);
                Registry.newKeyAllowedMap.put(s, Boolean.valueOf(z));
                atomicReference0.set(keyManagerRegistry0);
                return;
            }
        }
        throw new IllegalArgumentException("key manager must be non-null.");
    }

    public static void registerKeyManager(KeyTypeManager keyTypeManager0, boolean z) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(keyTypeManager0 != null) {
                AtomicReference atomicReference0 = Registry.keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry0 = new KeyManagerRegistry(((KeyManagerRegistry)atomicReference0.get()));
                keyManagerRegistry0.registerKeyManager(keyTypeManager0);
                String s = keyTypeManager0.getKeyType();
                Registry.ensureKeyManagerInsertable(s, (z ? keyTypeManager0.keyFactory().keyFormats() : Collections.EMPTY_MAP), z);
                if(!((KeyManagerRegistry)atomicReference0.get()).typeUrlExists(s)) {
                    KeyDeriverContainer registry$KeyDeriverContainer0 = Registry.createDeriverFor(keyTypeManager0);
                    Registry.keyDeriverMap.put(s, registry$KeyDeriverContainer0);
                    if(z) {
                        Registry.registerKeyTemplates(s, keyTypeManager0.keyFactory().keyFormats());
                    }
                }
                Registry.newKeyAllowedMap.put(s, Boolean.valueOf(z));
                atomicReference0.set(keyManagerRegistry0);
                return;
            }
        }
        throw new IllegalArgumentException("key manager must be non-null.");
    }

    @Deprecated
    public static void registerKeyManager(String s, KeyManager keyManager0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            Registry.registerKeyManager(s, keyManager0, true);
        }
    }

    @Deprecated
    public static void registerKeyManager(String s, KeyManager keyManager0, boolean z) throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(keyManager0 != null) {
                if(!s.equals(keyManager0.getKeyType())) {
                    throw new GeneralSecurityException("Manager does not support key type " + s + ".");
                }
                Registry.registerKeyManager(keyManager0, z);
                return;
            }
        }
        throw new IllegalArgumentException("key manager must be non-null.");
    }

    private static void registerKeyTemplates(String s, Map map0) {
        for(Object object0: map0.entrySet()) {
            String s1 = (String)((Map.Entry)object0).getKey();
            com.google.crypto.tink.KeyTemplate keyTemplate0 = com.google.crypto.tink.KeyTemplate.create(s, ((MessageLite)((KeyFormat)((Map.Entry)object0).getValue()).keyFormat).toByteArray(), ((KeyFormat)((Map.Entry)object0).getValue()).outputPrefixType);
            Registry.keyTemplateMap.put(s1, keyTemplate0);
        }
    }

    public static void registerPrimitiveWrapper(PrimitiveWrapper primitiveWrapper0) throws GeneralSecurityException {
        synchronized(Registry.class) {
            MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(primitiveWrapper0);
        }
    }

    static void reset() {
        synchronized(Registry.class) {
            KeyManagerRegistry keyManagerRegistry0 = new KeyManagerRegistry();
            Registry.keyManagerRegistry.set(keyManagerRegistry0);
            MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
            Registry.keyDeriverMap.clear();
            Registry.newKeyAllowedMap.clear();
            Registry.catalogueMap.clear();
            Registry.keyTemplateMap.clear();
        }
    }

    public static void restrictToFipsIfEmpty() throws GeneralSecurityException {
        synchronized(Registry.class) {
            if(TinkFipsUtil.useOnlyFips()) {
                return;
            }
            if(((KeyManagerRegistry)Registry.keyManagerRegistry.get()).isEmpty()) {
                TinkFipsUtil.setFipsRestricted();
                return;
            }
        }
        throw new GeneralSecurityException("Could not enable FIPS mode as Registry is not empty.");
    }

    public static Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return Registry.wrap(primitiveSet0, primitiveSet0.getPrimitiveClass());
    }

    public static Object wrap(PrimitiveSet primitiveSet0, Class class0) throws GeneralSecurityException {
        return MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet0, class0);
    }
}

