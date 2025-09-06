package com.google.crypto.tink;

import com.google.crypto.tink.internal.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class TinkJsonProtoKeysetFormat {
    public static KeysetHandle parseEncryptedKeyset(String s, Aead aead0, byte[] arr_b) throws GeneralSecurityException {
        try {
            return KeysetHandle.readWithAssociatedData(JsonKeysetReader.withString(s), aead0, arr_b);
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static KeysetHandle parseKeyset(String s, SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 != null) {
            try {
                return CleartextKeysetHandle.read(JsonKeysetReader.withString(s));
            }
            catch(IOException unused_ex) {
                throw new GeneralSecurityException("Parse keyset failed");
            }
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }

    public static KeysetHandle parseKeysetWithoutSecret(String s) throws GeneralSecurityException {
        try {
            return KeysetHandle.readNoSecret(JsonKeysetReader.withString(s));
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static String serializeEncryptedKeyset(KeysetHandle keysetHandle0, Aead aead0, byte[] arr_b) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            keysetHandle0.writeWithAssociatedData(JsonKeysetWriter.withOutputStream(byteArrayOutputStream0), aead0, arr_b);
            return new String(byteArrayOutputStream0.toByteArray(), Util.UTF_8);
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }

    public static String serializeKeyset(KeysetHandle keysetHandle0, SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                CleartextKeysetHandle.write(keysetHandle0, JsonKeysetWriter.withOutputStream(byteArrayOutputStream0));
                return new String(byteArrayOutputStream0.toByteArray(), Util.UTF_8);
            }
            catch(IOException unused_ex) {
                throw new GeneralSecurityException("Serialize keyset failed");
            }
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }

    public static String serializeKeysetWithoutSecret(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            keysetHandle0.writeNoSecret(JsonKeysetWriter.withOutputStream(byteArrayOutputStream0));
            return new String(byteArrayOutputStream0.toByteArray(), Util.UTF_8);
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }
}

