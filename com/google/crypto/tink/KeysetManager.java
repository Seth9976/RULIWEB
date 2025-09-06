package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyStatusTypeProtoConverter;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset.Builder;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.SecretKeyAccess;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import java.security.GeneralSecurityException;

public final class KeysetManager {
    private final Builder keysetBuilder;

    private KeysetManager(Builder keyset$Builder0) {
        this.keysetBuilder = keyset$Builder0;
    }

    public KeysetManager add(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            this.addNewKey(keyTemplate0.getProto(), false);
            return this;
        }
    }

    @Deprecated
    public KeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            this.addNewKey(keyTemplate0, false);
            return this;
        }
    }

    public KeysetManager add(KeyHandle keyHandle0) throws GeneralSecurityException {
        ProtoKey protoKey0;
        synchronized(this) {
            try {
                protoKey0 = (ProtoKey)keyHandle0.getKey(SecretKeyAccess.insecureSecretAccess());
            }
            catch(ClassCastException classCastException0) {
                throw new UnsupportedOperationException("KeyHandles which contain TinkKeys that are not ProtoKeys are not yet supported.", classCastException0);
            }
        }
        if(this.keyIdExists(keyHandle0.getId())) {
            throw new GeneralSecurityException("Trying to add a key with an ID already contained in the keyset.");
        }
        Key keyset$Key0 = (Key)Key.newBuilder().setKeyData(protoKey0.getProtoKey()).setKeyId(keyHandle0.getId()).setStatus(KeyStatusTypeProtoConverter.toProto(keyHandle0.getStatus())).setOutputPrefixType(KeyTemplate.toProto(protoKey0.getOutputPrefixType())).build();
        this.keysetBuilder.addKey(keyset$Key0);
        return this;
    }

    public KeysetManager add(KeyHandle keyHandle0, KeyAccess keyAccess0) throws GeneralSecurityException {
        synchronized(this) {
            return this.add(keyHandle0);
        }
    }

    @Deprecated
    public int addNewKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate0, boolean z) throws GeneralSecurityException {
        Key keyset$Key0;
        synchronized(this) {
            keyset$Key0 = this.newKey(keyTemplate0);
            this.keysetBuilder.addKey(keyset$Key0);
            if(z) {
                this.keysetBuilder.setPrimaryKeyId(keyset$Key0.getKeyId());
            }
        }
        return keyset$Key0.getKeyId();
    }

    private Key createKeysetKey(KeyData keyData0, OutputPrefixType outputPrefixType0) throws GeneralSecurityException {
        synchronized(this) {
            int v1 = this.newKeyId();
            if(outputPrefixType0 != OutputPrefixType.UNKNOWN_PREFIX) {
                return (Key)Key.newBuilder().setKeyData(keyData0).setKeyId(v1).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType0).build();
            }
        }
        throw new GeneralSecurityException("unknown output prefix type");
    }

    public KeysetManager delete(int v) throws GeneralSecurityException {
        synchronized(this) {
            if(v != this.keysetBuilder.getPrimaryKeyId()) {
                for(int v2 = 0; v2 < this.keysetBuilder.getKeyCount(); ++v2) {
                    if(this.keysetBuilder.getKey(v2).getKeyId() == v) {
                        this.keysetBuilder.removeKey(v2);
                        return this;
                    }
                }
                throw new GeneralSecurityException("key not found: " + v);
            }
        }
        throw new GeneralSecurityException("cannot delete the primary key");
    }

    public KeysetManager destroy(int v) throws GeneralSecurityException {
        synchronized(this) {
            if(v != this.keysetBuilder.getPrimaryKeyId()) {
                for(int v2 = 0; v2 < this.keysetBuilder.getKeyCount(); ++v2) {
                    Key keyset$Key0 = this.keysetBuilder.getKey(v2);
                    if(keyset$Key0.getKeyId() == v) {
                        if(keyset$Key0.getStatus() != KeyStatusType.ENABLED && keyset$Key0.getStatus() != KeyStatusType.DISABLED && keyset$Key0.getStatus() != KeyStatusType.DESTROYED) {
                            throw new GeneralSecurityException("cannot destroy key with id " + v);
                        }
                        Key keyset$Key1 = (Key)((com.google.crypto.tink.proto.Keyset.Key.Builder)keyset$Key0.toBuilder()).setStatus(KeyStatusType.DESTROYED).clearKeyData().build();
                        this.keysetBuilder.setKey(v2, keyset$Key1);
                        return this;
                    }
                }
                throw new GeneralSecurityException("key not found: " + v);
            }
        }
        throw new GeneralSecurityException("cannot destroy the primary key");
    }

    public KeysetManager disable(int v) throws GeneralSecurityException {
        synchronized(this) {
            if(v != this.keysetBuilder.getPrimaryKeyId()) {
                for(int v2 = 0; v2 < this.keysetBuilder.getKeyCount(); ++v2) {
                    Key keyset$Key0 = this.keysetBuilder.getKey(v2);
                    if(keyset$Key0.getKeyId() == v) {
                        if(keyset$Key0.getStatus() != KeyStatusType.ENABLED && keyset$Key0.getStatus() != KeyStatusType.DISABLED) {
                            throw new GeneralSecurityException("cannot disable key with id " + v);
                        }
                        Key keyset$Key1 = (Key)((com.google.crypto.tink.proto.Keyset.Key.Builder)keyset$Key0.toBuilder()).setStatus(KeyStatusType.DISABLED).build();
                        this.keysetBuilder.setKey(v2, keyset$Key1);
                        return this;
                    }
                }
                throw new GeneralSecurityException("key not found: " + v);
            }
        }
        throw new GeneralSecurityException("cannot disable the primary key");
    }

    public KeysetManager enable(int v) throws GeneralSecurityException {
        synchronized(this) {
            for(int v2 = 0; v2 < this.keysetBuilder.getKeyCount(); ++v2) {
                Key keyset$Key0 = this.keysetBuilder.getKey(v2);
                if(keyset$Key0.getKeyId() == v) {
                    if(keyset$Key0.getStatus() != KeyStatusType.ENABLED && keyset$Key0.getStatus() != KeyStatusType.DISABLED) {
                        throw new GeneralSecurityException("cannot enable key with id " + v);
                    }
                    Key keyset$Key1 = (Key)((com.google.crypto.tink.proto.Keyset.Key.Builder)keyset$Key0.toBuilder()).setStatus(KeyStatusType.ENABLED).build();
                    this.keysetBuilder.setKey(v2, keyset$Key1);
                    return this;
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + v);
    }

    public KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        synchronized(this) {
            return KeysetHandle.fromKeyset(((Keyset)this.keysetBuilder.build()));
        }
    }

    private boolean keyIdExists(int v) {
        synchronized(this) {
            for(Object object0: this.keysetBuilder.getKeyList()) {
                if(((Key)object0).getKeyId() == v) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }
    }

    private Key newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            return this.createKeysetKey(Registry.newKeyData(keyTemplate0), keyTemplate0.getOutputPrefixType());
        }
    }

    private int newKeyId() {
        int v1;
        synchronized(this) {
            do {
                v1 = Util.randKeyId();
            }
            while(this.keyIdExists(v1));
            return v1;
        }
    }

    @Deprecated
    public KeysetManager promote(int v) throws GeneralSecurityException {
        synchronized(this) {
            return this.setPrimary(v);
        }
    }

    @Deprecated
    public KeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate0) throws GeneralSecurityException {
        synchronized(this) {
            this.addNewKey(keyTemplate0, true);
            return this;
        }
    }

    public KeysetManager setPrimary(int v) throws GeneralSecurityException {
        synchronized(this) {
            for(int v2 = 0; v2 < this.keysetBuilder.getKeyCount(); ++v2) {
                Key keyset$Key0 = this.keysetBuilder.getKey(v2);
                if(keyset$Key0.getKeyId() == v) {
                    if(!keyset$Key0.getStatus().equals(KeyStatusType.ENABLED)) {
                        throw new GeneralSecurityException("cannot set key as primary because it\'s not enabled: " + v);
                    }
                    this.keysetBuilder.setPrimaryKeyId(v);
                    return this;
                }
            }
        }
        throw new GeneralSecurityException("key not found: " + v);
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle0) {
        return new KeysetManager(((Builder)keysetHandle0.getKeyset().toBuilder()));
    }
}

