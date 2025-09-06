package com.google.crypto.tink.tinkkey;

import com.google.crypto.tink.KeyTemplate.OutputPrefixType;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
@Deprecated
public class KeyHandle {
    public static enum KeyStatusType {
        ENABLED,
        DISABLED,
        DESTROYED;

    }

    private final int id;
    private final TinkKey key;
    private final KeyStatusType status;

    private KeyHandle(TinkKey tinkKey0) {
        this.key = tinkKey0;
        this.status = KeyStatusType.ENABLED;
        this.id = Util.randKeyId();
    }

    protected KeyHandle(TinkKey tinkKey0, KeyStatusType keyHandle$KeyStatusType0, int v) {
        this.key = tinkKey0;
        this.status = keyHandle$KeyStatusType0;
        this.id = v;
    }

    private void checkAccess(KeyAccess keyAccess0) throws GeneralSecurityException {
        if(this.hasSecret() && !keyAccess0.canAccessSecret()) {
            throw new GeneralSecurityException("No access");
        }
    }

    @Deprecated
    public static KeyHandle createFromKey(KeyData keyData0, OutputPrefixType keyTemplate$OutputPrefixType0) {
        return new KeyHandle(new ProtoKey(keyData0, keyTemplate$OutputPrefixType0));
    }

    public static KeyHandle createFromKey(TinkKey tinkKey0, KeyAccess keyAccess0) throws GeneralSecurityException {
        KeyHandle keyHandle0 = new KeyHandle(tinkKey0);
        keyHandle0.checkAccess(keyAccess0);
        return keyHandle0;
    }

    public static KeyHandle generateNew(KeyTemplate keyTemplate0) throws GeneralSecurityException {
        return new KeyHandle(new ProtoKey(Registry.newKeyData(keyTemplate0), keyTemplate0.getOutputPrefixType()));
    }

    public int getId() {
        return this.id;
    }

    public TinkKey getKey(KeyAccess keyAccess0) throws GeneralSecurityException {
        this.checkAccess(keyAccess0);
        return this.key;
    }

    public KeyTemplate getKeyTemplate() {
        return this.key.getKeyTemplate();
    }

    public KeyStatusType getStatus() {
        return this.status;
    }

    public boolean hasSecret() {
        return this.key.hasSecret();
    }
}

