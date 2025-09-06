package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyTemplate;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class KmsEnvelopeAead implements Aead {
    private static final byte[] EMPTY_AAD = null;
    private static final int LENGTH_ENCRYPTED_DEK = 4;
    private final KeyTemplate dekTemplate;
    private final Aead remote;

    static {
        KmsEnvelopeAead.EMPTY_AAD = new byte[0];
    }

    public KmsEnvelopeAead(KeyTemplate keyTemplate0, Aead aead0) {
        this.dekTemplate = keyTemplate0;
        this.remote = aead0;
    }

    private byte[] buildCiphertext(byte[] arr_b, byte[] arr_b1) {
        return ByteBuffer.allocate(arr_b.length + 4 + arr_b1.length).putInt(arr_b.length).put(arr_b).put(arr_b1).array();
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        try {
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
            int v = byteBuffer0.getInt();
            if(v <= 0 || v > arr_b.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] arr_b2 = new byte[v];
            byteBuffer0.get(arr_b2, 0, v);
            byte[] arr_b3 = new byte[byteBuffer0.remaining()];
            byteBuffer0.get(arr_b3, 0, byteBuffer0.remaining());
            return ((Aead)Registry.getPrimitive("", this.remote.decrypt(arr_b2, KmsEnvelopeAead.EMPTY_AAD), Aead.class)).decrypt(arr_b3, arr_b1);
        }
        catch(IndexOutOfBoundsException | BufferUnderflowException | NegativeArraySizeException indexOutOfBoundsException0) {
            throw new GeneralSecurityException("invalid ciphertext", indexOutOfBoundsException0);
        }
    }

    @Override  // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        byte[] arr_b2 = Registry.newKey(this.dekTemplate).toByteArray();
        return this.buildCiphertext(this.remote.encrypt(arr_b2, KmsEnvelopeAead.EMPTY_AAD), ((Aead)Registry.getPrimitive("", arr_b2, Aead.class)).encrypt(arr_b, arr_b1));
    }
}

