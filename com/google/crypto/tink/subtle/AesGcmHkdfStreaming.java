package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesGcmHkdfStreaming extends NonceBasedStreamingAead {
    class AesGcmHkdfStreamDecrypter implements StreamSegmentDecrypter {
        private Cipher cipher;
        private SecretKeySpec keySpec;
        private byte[] noncePrefix;

        @Override  // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public void decryptSegment(ByteBuffer byteBuffer0, int v, boolean z, ByteBuffer byteBuffer1) throws GeneralSecurityException {
            synchronized(this) {
                GCMParameterSpec gCMParameterSpec0 = AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, ((long)v), z);
                this.cipher.init(2, this.keySpec, gCMParameterSpec0);
                this.cipher.doFinal(byteBuffer0, byteBuffer1);
            }
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public void init(ByteBuffer byteBuffer0, byte[] arr_b) throws GeneralSecurityException {
            synchronized(this) {
                if(byteBuffer0.remaining() == AesGcmHkdfStreaming.this.getHeaderLength()) {
                    if(byteBuffer0.get() != AesGcmHkdfStreaming.this.getHeaderLength()) {
                        throw new GeneralSecurityException("Invalid ciphertext");
                    }
                    this.noncePrefix = new byte[7];
                    byte[] arr_b1 = new byte[AesGcmHkdfStreaming.this.keySizeInBytes];
                    byteBuffer0.get(arr_b1);
                    byteBuffer0.get(this.noncePrefix);
                    this.keySpec = AesGcmHkdfStreaming.this.deriveKeySpec(arr_b1, arr_b);
                    this.cipher = AesGcmHkdfStreaming.cipherInstance();
                    return;
                }
            }
            throw new InvalidAlgorithmParameterException("Invalid header length");
        }
    }

    class AesGcmHkdfStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher;
        private long encryptedSegments;
        private final ByteBuffer header;
        private final SecretKeySpec keySpec;
        private final byte[] noncePrefix;

        public AesGcmHkdfStreamEncrypter(byte[] arr_b) throws GeneralSecurityException {
            this.encryptedSegments = 0L;
            this.cipher = AesGcmHkdfStreaming.cipherInstance();
            this.encryptedSegments = 0L;
            byte[] arr_b1 = aesGcmHkdfStreaming0.randomSalt();
            byte[] arr_b2 = {-62, 0x40, 9, -98, -83, (byte)0x82, (byte)0x89};
            this.noncePrefix = arr_b2;
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(aesGcmHkdfStreaming0.getHeaderLength());
            this.header = byteBuffer0;
            byteBuffer0.put(((byte)aesGcmHkdfStreaming0.getHeaderLength()));
            byteBuffer0.put(arr_b1);
            byteBuffer0.put(arr_b2);
            byteBuffer0.flip();
            this.keySpec = aesGcmHkdfStreaming0.deriveKeySpec(arr_b1, arr_b);
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public void encryptSegment(ByteBuffer byteBuffer0, ByteBuffer byteBuffer1, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            synchronized(this) {
                GCMParameterSpec gCMParameterSpec0 = AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, this.encryptedSegments, z);
                this.cipher.init(1, this.keySpec, gCMParameterSpec0);
                ++this.encryptedSegments;
                if(byteBuffer1.hasRemaining()) {
                    this.cipher.update(byteBuffer0, byteBuffer2);
                    this.cipher.doFinal(byteBuffer1, byteBuffer2);
                }
                else {
                    this.cipher.doFinal(byteBuffer0, byteBuffer2);
                }
            }
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public void encryptSegment(ByteBuffer byteBuffer0, boolean z, ByteBuffer byteBuffer1) throws GeneralSecurityException {
            synchronized(this) {
                GCMParameterSpec gCMParameterSpec0 = AesGcmHkdfStreaming.paramsForSegment(this.noncePrefix, this.encryptedSegments, z);
                this.cipher.init(1, this.keySpec, gCMParameterSpec0);
                ++this.encryptedSegments;
                this.cipher.doFinal(byteBuffer0, byteBuffer1);
            }
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }
    }

    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 12;
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlg;
    private final byte[] ikm;
    private final int keySizeInBytes;
    private final int plaintextSegmentSize;

    public AesGcmHkdfStreaming(byte[] arr_b, String s, int v, int v1, int v2) throws InvalidAlgorithmParameterException {
        if(arr_b.length < 16 || arr_b.length < v) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, v));
        }
        Validators.validateAesKeySize(v);
        if(v1 <= this.getHeaderLength() + v2 + 16) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
        this.ikm = Arrays.copyOf(arr_b, arr_b.length);
        this.hkdfAlg = s;
        this.keySizeInBytes = v;
        this.ciphertextSegmentSize = v1;
        this.firstSegmentOffset = v2;
        this.plaintextSegmentSize = v1 - 16;
    }

    // 去混淆评级： 低(20)
    static byte[] access$200() [...] // 潜在的解密器

    private static Cipher cipherInstance() throws GeneralSecurityException {
        return (Cipher)EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
    }

    private SecretKeySpec deriveKeySpec(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return new SecretKeySpec(Hkdf.computeHkdf(this.hkdfAlg, this.ikm, arr_b, arr_b1, this.keySizeInBytes), "AES");
    }

    public long expectedCiphertextSize(long v) {
        long v1 = v + ((long)this.getCiphertextOffset());
        long v2 = v1 / ((long)this.plaintextSegmentSize) * ((long)this.ciphertextSegmentSize);
        long v3 = v1 % ((long)this.plaintextSegmentSize);
        return v3 <= 0L ? v2 : v2 + (v3 + 16L);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOffset() {
        return this.getHeaderLength() + this.firstSegmentOffset;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOverhead() {
        return 16;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize;
    }

    public int getFirstSegmentOffset() {
        return this.firstSegmentOffset;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getHeaderLength() {
        return this.keySizeInBytes + 8;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getPlaintextSegmentSize() {
        return this.plaintextSegmentSize;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return super.newDecryptingChannel(readableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public InputStream newDecryptingStream(InputStream inputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return super.newDecryptingStream(inputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return super.newEncryptingChannel(writableByteChannel0, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public OutputStream newEncryptingStream(OutputStream outputStream0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return super.newEncryptingStream(outputStream0, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel0, byte[] arr_b) throws GeneralSecurityException, IOException {
        return super.newSeekableDecryptingChannel(seekableByteChannel0, arr_b);
    }

    public AesGcmHkdfStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesGcmHkdfStreamDecrypter(this);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public StreamSegmentDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return this.newStreamSegmentDecrypter();
    }

    public AesGcmHkdfStreamEncrypter newStreamSegmentEncrypter(byte[] arr_b) throws GeneralSecurityException {
        return new AesGcmHkdfStreamEncrypter(this, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public StreamSegmentEncrypter newStreamSegmentEncrypter(byte[] arr_b) throws GeneralSecurityException {
        return this.newStreamSegmentEncrypter(arr_b);
    }

    private static GCMParameterSpec paramsForSegment(byte[] arr_b, long v, boolean z) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(12);
        byteBuffer0.order(ByteOrder.BIG_ENDIAN);
        byteBuffer0.put(arr_b);
        SubtleUtil.putAsUnsigedInt(byteBuffer0, v);
        byteBuffer0.put(((byte)z));
        return new GCMParameterSpec(0x80, byteBuffer0.array());
    }

    // 去混淆评级： 低(20)
    private static byte[] randomNonce() [...] // 潜在的解密器

    private byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }
}

