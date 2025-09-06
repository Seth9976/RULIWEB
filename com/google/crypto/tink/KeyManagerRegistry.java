package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

final class KeyManagerRegistry {
    interface KeyManagerContainer {
        Class getImplementingClass();

        KeyManager getKeyManager(Class arg1) throws GeneralSecurityException;

        KeyManager getUntypedKeyManager();

        MessageLite parseKey(ByteString arg1) throws GeneralSecurityException, InvalidProtocolBufferException;

        Class publicKeyManagerClassOrNull();

        Set supportedPrimitives();
    }

    private final ConcurrentMap keyManagerMap;
    private static final Logger logger;

    static {
        KeyManagerRegistry.logger = Logger.getLogger("com.google.crypto.tink.KeyManagerRegistry");
    }

    KeyManagerRegistry() {
        this.keyManagerMap = new ConcurrentHashMap();
    }

    KeyManagerRegistry(KeyManagerRegistry keyManagerRegistry0) {
        this.keyManagerMap = new ConcurrentHashMap(keyManagerRegistry0.keyManagerMap);
    }

    private static Object checkNotNull(Object object0) {
        object0.getClass();
        return object0;
    }

    private static KeyManagerContainer createContainerFor(KeyManager keyManager0) {
        return new KeyManagerContainer() {
            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class getImplementingClass() {
                return keyManager0.getClass();
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getKeyManager(Class class0) throws GeneralSecurityException {
                if(!keyManager0.getPrimitiveClass().equals(class0)) {
                    throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
                }
                return keyManager0;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getUntypedKeyManager() {
                return keyManager0;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws GeneralSecurityException, InvalidProtocolBufferException {
                return null;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class publicKeyManagerClassOrNull() {
                return null;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Set supportedPrimitives() {
                return Collections.singleton(keyManager0.getPrimitiveClass());
            }
        };
    }

    private static KeyManagerContainer createContainerFor(KeyTypeManager keyTypeManager0) {
        return new KeyManagerContainer() {
            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class getImplementingClass() {
                return keyTypeManager0.getClass();
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getKeyManager(Class class0) throws GeneralSecurityException {
                try {
                    return new KeyManagerImpl(keyTypeManager0, class0);
                }
                catch(IllegalArgumentException illegalArgumentException0) {
                    throw new GeneralSecurityException("Primitive type not supported", illegalArgumentException0);
                }
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getUntypedKeyManager() {
                Class class0 = keyTypeManager0.firstSupportedPrimitiveClass();
                return new KeyManagerImpl(keyTypeManager0, class0);
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public MessageLite parseKey(ByteString byteString0) throws GeneralSecurityException, InvalidProtocolBufferException {
                MessageLite messageLite0 = keyTypeManager0.parseKey(byteString0);
                keyTypeManager0.validateKey(messageLite0);
                return messageLite0;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class publicKeyManagerClassOrNull() {
                return null;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Set supportedPrimitives() {
                return keyTypeManager0.supportedPrimitives();
            }
        };
    }

    private static KeyManagerContainer createPrivateKeyContainerFor(PrivateKeyTypeManager privateKeyTypeManager0, KeyTypeManager keyTypeManager0) {
        return new KeyManagerContainer() {
            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class getImplementingClass() {
                return privateKeyTypeManager0.getClass();
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getKeyManager(Class class0) throws GeneralSecurityException {
                try {
                    return new PrivateKeyManagerImpl(privateKeyTypeManager0, keyTypeManager0, class0);
                }
                catch(IllegalArgumentException illegalArgumentException0) {
                    throw new GeneralSecurityException("Primitive type not supported", illegalArgumentException0);
                }
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public KeyManager getUntypedKeyManager() {
                Class class0 = privateKeyTypeManager0.firstSupportedPrimitiveClass();
                return new PrivateKeyManagerImpl(privateKeyTypeManager0, keyTypeManager0, class0);
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public MessageLite parseKey(ByteString byteString0) throws GeneralSecurityException, InvalidProtocolBufferException {
                MessageLite messageLite0 = privateKeyTypeManager0.parseKey(byteString0);
                privateKeyTypeManager0.validateKey(messageLite0);
                return messageLite0;
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Class publicKeyManagerClassOrNull() {
                return keyTypeManager0.getClass();
            }

            @Override  // com.google.crypto.tink.KeyManagerRegistry$KeyManagerContainer
            public Set supportedPrimitives() {
                return privateKeyTypeManager0.supportedPrimitives();
            }
        };
    }

    @Deprecated
    KeyManager getKeyManager(String s) throws GeneralSecurityException {
        return this.getKeyManagerInternal(s, null);
    }

    KeyManager getKeyManager(String s, Class class0) throws GeneralSecurityException {
        return this.getKeyManagerInternal(s, ((Class)KeyManagerRegistry.checkNotNull(class0)));
    }

    private KeyManagerContainer getKeyManagerContainerOrThrow(String s) throws GeneralSecurityException {
        synchronized(this) {
            if(this.keyManagerMap.containsKey(s)) {
                return (KeyManagerContainer)this.keyManagerMap.get(s);
            }
        }
        throw new GeneralSecurityException("No key manager found for key type " + s);
    }

    private KeyManager getKeyManagerInternal(String s, Class class0) throws GeneralSecurityException {
        KeyManagerContainer keyManagerRegistry$KeyManagerContainer0 = this.getKeyManagerContainerOrThrow(s);
        if(class0 == null) {
            return keyManagerRegistry$KeyManagerContainer0.getUntypedKeyManager();
        }
        if(!keyManagerRegistry$KeyManagerContainer0.supportedPrimitives().contains(class0)) {
            throw new GeneralSecurityException("Primitive type " + class0.getName() + " not supported by key manager of type " + keyManagerRegistry$KeyManagerContainer0.getImplementingClass() + ", supported primitives: " + KeyManagerRegistry.toCommaSeparatedString(keyManagerRegistry$KeyManagerContainer0.supportedPrimitives()));
        }
        return keyManagerRegistry$KeyManagerContainer0.getKeyManager(class0);
    }

    KeyManager getUntypedKeyManager(String s) throws GeneralSecurityException {
        return this.getKeyManagerContainerOrThrow(s).getUntypedKeyManager();
    }

    boolean isEmpty() {
        return this.keyManagerMap.isEmpty();
    }

    // 去混淆评级： 低(20)
    MessageLite parseKeyData(KeyData keyData0) throws GeneralSecurityException, InvalidProtocolBufferException {
        return this.getKeyManagerContainerOrThrow("").parseKey(keyData0.getValue());
    }

    void registerAsymmetricKeyManagers(PrivateKeyTypeManager privateKeyTypeManager0, KeyTypeManager keyTypeManager0) throws GeneralSecurityException {
        synchronized(this) {
            AlgorithmFipsCompatibility tinkFipsUtil$AlgorithmFipsCompatibility0 = privateKeyTypeManager0.fipsStatus();
            AlgorithmFipsCompatibility tinkFipsUtil$AlgorithmFipsCompatibility1 = keyTypeManager0.fipsStatus();
            if(tinkFipsUtil$AlgorithmFipsCompatibility0.isCompatible()) {
                if(!tinkFipsUtil$AlgorithmFipsCompatibility1.isCompatible()) {
                    throw new GeneralSecurityException("failed to register key manager " + keyTypeManager0.getClass() + " as it is not FIPS compatible.");
                }
                String s = privateKeyTypeManager0.getKeyType();
                String s1 = keyTypeManager0.getKeyType();
                if(this.keyManagerMap.containsKey(s) && ((KeyManagerContainer)this.keyManagerMap.get(s)).publicKeyManagerClassOrNull() != null) {
                    Class class0 = ((KeyManagerContainer)this.keyManagerMap.get(s)).publicKeyManagerClassOrNull();
                    if(class0 != null && !class0.getName().equals(keyTypeManager0.getClass().getName())) {
                        KeyManagerRegistry.logger.warning("Attempted overwrite of a registered key manager for key type " + s + " with inconsistent public key type " + s1);
                        throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", privateKeyTypeManager0.getClass().getName(), class0.getName(), keyTypeManager0.getClass().getName()));
                    }
                }
                this.registerKeyManagerContainer(KeyManagerRegistry.createPrivateKeyContainerFor(privateKeyTypeManager0, keyTypeManager0), true);
                this.registerKeyManagerContainer(KeyManagerRegistry.createContainerFor(keyTypeManager0), false);
                return;
            }
        }
        throw new GeneralSecurityException("failed to register key manager " + privateKeyTypeManager0.getClass() + " as it is not FIPS compatible.");
    }

    void registerKeyManager(KeyManager keyManager0) throws GeneralSecurityException {
        synchronized(this) {
            if(AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                this.registerKeyManagerContainer(KeyManagerRegistry.createContainerFor(keyManager0), false);
                return;
            }
        }
        throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
    }

    void registerKeyManager(KeyTypeManager keyTypeManager0) throws GeneralSecurityException {
        synchronized(this) {
            if(keyTypeManager0.fipsStatus().isCompatible()) {
                this.registerKeyManagerContainer(KeyManagerRegistry.createContainerFor(keyTypeManager0), false);
                return;
            }
        }
        throw new GeneralSecurityException("failed to register key manager " + keyTypeManager0.getClass() + " as it is not FIPS compatible.");
    }

    private void registerKeyManagerContainer(KeyManagerContainer keyManagerRegistry$KeyManagerContainer0, boolean z) throws GeneralSecurityException {
        synchronized(this) {
            String s = keyManagerRegistry$KeyManagerContainer0.getUntypedKeyManager().getKeyType();
            KeyManagerContainer keyManagerRegistry$KeyManagerContainer1 = (KeyManagerContainer)this.keyManagerMap.get(s);
            if(keyManagerRegistry$KeyManagerContainer1 != null && !keyManagerRegistry$KeyManagerContainer1.getImplementingClass().equals(keyManagerRegistry$KeyManagerContainer0.getImplementingClass())) {
                KeyManagerRegistry.logger.warning("Attempted overwrite of a registered key manager for key type " + s);
                throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", s, keyManagerRegistry$KeyManagerContainer1.getImplementingClass().getName(), keyManagerRegistry$KeyManagerContainer0.getImplementingClass().getName()));
            }
            if(z) {
                this.keyManagerMap.put(s, keyManagerRegistry$KeyManagerContainer0);
            }
            else {
                this.keyManagerMap.putIfAbsent(s, keyManagerRegistry$KeyManagerContainer0);
            }
        }
    }

    private static String toCommaSeparatedString(Set set0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        boolean z = true;
        for(Object object0: set0) {
            if(!z) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(((Class)object0).getCanonicalName());
            z = false;
        }
        return stringBuilder0.toString();
    }

    boolean typeUrlExists(String s) {
        return this.keyManagerMap.containsKey(s);
    }
}

