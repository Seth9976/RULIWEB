package com.google.crypto.tink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public final class TinkProtoKeysetFormat {
    public static KeysetHandle parseEncryptedKeyset(byte[] arr_b, Aead aead0, byte[] arr_b1) throws GeneralSecurityException {
        try {
            return KeysetHandle.readWithAssociatedData(BinaryKeysetReader.withBytes(arr_b), aead0, arr_b1);
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static KeysetHandle parseKeyset(byte[] arr_b, SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 != null) {
            try {
                return CleartextKeysetHandle.read(BinaryKeysetReader.withBytes(arr_b));
            }
            catch(IOException unused_ex) {
                throw new GeneralSecurityException("Parse keyset failed");
            }
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }

    public static KeysetHandle parseKeysetWithoutSecret(byte[] arr_b) throws GeneralSecurityException {
        try {
            return KeysetHandle.readNoSecret(BinaryKeysetReader.withBytes(arr_b));
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static byte[] serializeEncryptedKeyset(KeysetHandle keysetHandle0, Aead aead0, byte[] arr_b) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            keysetHandle0.writeWithAssociatedData(BinaryKeysetWriter.withOutputStream(byteArrayOutputStream0), aead0, arr_b);
            return byteArrayOutputStream0.toByteArray();
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }

    public static byte[] serializeKeyset(KeysetHandle keysetHandle0, SecretKeyAccess secretKeyAccess0) throws GeneralSecurityException {
        if(secretKeyAccess0 != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                CleartextKeysetHandle.write(keysetHandle0, BinaryKeysetWriter.withOutputStream(byteArrayOutputStream0));
                return byteArrayOutputStream0.toByteArray();
            }
            catch(IOException unused_ex) {
                throw new GeneralSecurityException("Serialize keyset failed");
            }
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }

    public static byte[] serializeKeysetWithoutSecret(KeysetHandle keysetHandle0) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
            keysetHandle0.writeNoSecret(BinaryKeysetWriter.withOutputStream(byteArrayOutputStream0));
            return byteArrayOutputStream0.toByteArray();
        }
        catch(IOException unused_ex) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }
}

