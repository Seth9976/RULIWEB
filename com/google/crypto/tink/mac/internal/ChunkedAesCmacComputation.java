package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.AesCmacParameters.Variant;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

final class ChunkedAesCmacComputation implements ChunkedMacComputation {
    private static final byte[] FORMAT_VERSION;
    private final Cipher aes;
    private boolean finalized;
    private final AesCmacKey key;
    private final ByteBuffer localStash;
    private final byte[] subKey1;
    private final byte[] subKey2;
    private final ByteBuffer x;
    private final ByteBuffer y;

    static {
        ChunkedAesCmacComputation.FORMAT_VERSION = new byte[]{0};
    }

    ChunkedAesCmacComputation(AesCmacKey aesCmacKey0) throws GeneralSecurityException {
        this.finalized = false;
        this.key = aesCmacKey0;
        Cipher cipher0 = (Cipher)EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        this.aes = cipher0;
        cipher0.init(1, new SecretKeySpec(aesCmacKey0.getAesKey().toByteArray(InsecureSecretKeyAccess.get()), "AES"));
        byte[] arr_b = AesUtil.dbl(cipher0.doFinal(new byte[16]));
        this.subKey1 = arr_b;
        this.subKey2 = AesUtil.dbl(arr_b);
        this.localStash = ByteBuffer.allocate(16);
        this.x = ByteBuffer.allocate(16);
        this.y = ByteBuffer.allocate(16);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacComputation
    public byte[] computeMac() throws GeneralSecurityException {
        if(this.finalized) {
            throw new IllegalStateException("Can not compute after computing the MAC tag. Please create a new object.");
        }
        if(this.key.getParameters().getVariant() == Variant.LEGACY) {
            this.update(ByteBuffer.wrap(ChunkedAesCmacComputation.FORMAT_VERSION));
        }
        this.finalized = true;
        byte[] arr_b = this.localStash.remaining() <= 0 ? Bytes.xor(this.localStash.array(), 0, this.subKey1, 0, 16) : Bytes.xor(AesUtil.cmacPad(Arrays.copyOf(this.localStash.array(), this.localStash.position())), this.subKey2);
        byte[] arr_b1 = this.key.getOutputPrefix().toByteArray();
        byte[] arr_b2 = Bytes.xor(arr_b, this.x.array());
        return Bytes.concat(new byte[][]{arr_b1, Arrays.copyOf(this.aes.doFinal(arr_b2), this.key.getParameters().getCryptographicTagSizeBytes())});
    }

    private void munch(ByteBuffer byteBuffer0) throws GeneralSecurityException {
        this.y.rewind();
        this.x.rewind();
        Bytes.xor(this.y, this.x, byteBuffer0, 16);
        this.y.rewind();
        this.x.rewind();
        this.aes.doFinal(this.y, this.x);
    }

    @Override  // com.google.crypto.tink.mac.ChunkedMacComputation
    public void update(ByteBuffer byteBuffer0) throws GeneralSecurityException {
        if(this.finalized) {
            throw new IllegalStateException("Can not update after computing the MAC tag. Please create a new object.");
        }
        if(this.localStash.remaining() != 16) {
            int v = Math.min(this.localStash.remaining(), byteBuffer0.remaining());
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = byteBuffer0.get();
                this.localStash.put(((byte)v2));
            }
        }
        if(this.localStash.remaining() == 0 && byteBuffer0.remaining() > 0) {
            this.localStash.rewind();
            this.munch(this.localStash);
            this.localStash.rewind();
        }
        while(byteBuffer0.remaining() > 16) {
            this.munch(byteBuffer0);
        }
        this.localStash.put(byteBuffer0);
    }
}

