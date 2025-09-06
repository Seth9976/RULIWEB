package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil.AlgorithmFipsCompatibility;
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
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
    class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
        static final boolean $assertionsDisabled;
        private Cipher cipher;
        private SecretKeySpec hmacKeySpec;
        private SecretKeySpec keySpec;
        private Mac mac;
        private byte[] noncePrefix;

        static {
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public void decryptSegment(ByteBuffer byteBuffer0, int v, boolean z, ByteBuffer byteBuffer1) throws GeneralSecurityException {
            synchronized(this) {
                int v2 = byteBuffer0.position();
                byte[] arr_b = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, ((long)v), z);
                int v3 = byteBuffer0.remaining();
                if(v3 >= AesCtrHmacStreaming.this.tagSizeInBytes) {
                    int v4 = v2 + (v3 - AesCtrHmacStreaming.this.tagSizeInBytes);
                    ByteBuffer byteBuffer2 = byteBuffer0.duplicate();
                    byteBuffer2.limit(v4);
                    ByteBuffer byteBuffer3 = byteBuffer0.duplicate();
                    byteBuffer3.position(v4);
                    this.mac.init(this.hmacKeySpec);
                    this.mac.update(arr_b);
                    this.mac.update(byteBuffer2);
                    byte[] arr_b1 = Arrays.copyOf(this.mac.doFinal(), AesCtrHmacStreaming.this.tagSizeInBytes);
                    byte[] arr_b2 = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];
                    byteBuffer3.get(arr_b2);
                    if(!Bytes.equal(arr_b2, arr_b1)) {
                        throw new GeneralSecurityException("Tag mismatch");
                    }
                    byteBuffer0.limit(v4);
                    this.cipher.init(1, this.keySpec, new IvParameterSpec(arr_b));
                    this.cipher.doFinal(byteBuffer0, byteBuffer1);
                    return;
                }
            }
            throw new GeneralSecurityException("Ciphertext too short");
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public void init(ByteBuffer byteBuffer0, byte[] arr_b) throws GeneralSecurityException {
            synchronized(this) {
                if(byteBuffer0.remaining() == AesCtrHmacStreaming.this.getHeaderLength()) {
                    if(byteBuffer0.get() != AesCtrHmacStreaming.this.getHeaderLength()) {
                        throw new GeneralSecurityException("Invalid ciphertext");
                    }
                    this.noncePrefix = new byte[7];
                    byte[] arr_b1 = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
                    byteBuffer0.get(arr_b1);
                    byteBuffer0.get(this.noncePrefix);
                    byte[] arr_b2 = AesCtrHmacStreaming.this.deriveKeyMaterial(arr_b1, arr_b);
                    this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(arr_b2);
                    this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(arr_b2);
                    this.cipher = AesCtrHmacStreaming.cipherInstance();
                    this.mac = AesCtrHmacStreaming.this.macInstance();
                    return;
                }
            }
            throw new InvalidAlgorithmParameterException("Invalid header length");
        }
    }

    class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher;
        private long encryptedSegments;
        private ByteBuffer header;
        private final SecretKeySpec hmacKeySpec;
        private final SecretKeySpec keySpec;
        private final Mac mac;
        private final byte[] noncePrefix;

        public AesCtrHmacStreamEncrypter(byte[] arr_b) throws GeneralSecurityException {
            this.encryptedSegments = 0L;
            this.cipher = AesCtrHmacStreaming.cipherInstance();
            this.mac = aesCtrHmacStreaming0.macInstance();
            this.encryptedSegments = 0L;
            byte[] arr_b1 = aesCtrHmacStreaming0.randomSalt();
            byte[] arr_b2 = AesCtrHmacStreaming.access$300(aesCtrHmacStreaming0);
            this.noncePrefix = arr_b2;
            ByteBuffer byteBuffer0 = ByteBuffer.allocate(aesCtrHmacStreaming0.getHeaderLength());
            this.header = byteBuffer0;
            byteBuffer0.put(((byte)aesCtrHmacStreaming0.getHeaderLength()));
            this.header.put(arr_b1);
            this.header.put(arr_b2);
            this.header.flip();
            byte[] arr_b3 = aesCtrHmacStreaming0.deriveKeyMaterial(arr_b1, arr_b);
            this.keySpec = aesCtrHmacStreaming0.deriveKeySpec(arr_b3);
            this.hmacKeySpec = aesCtrHmacStreaming0.deriveHmacKeySpec(arr_b3);
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public void encryptSegment(ByteBuffer byteBuffer0, ByteBuffer byteBuffer1, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            synchronized(this) {
                byte[] arr_b = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z);
                IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b);
                this.cipher.init(1, this.keySpec, ivParameterSpec0);
                ++this.encryptedSegments;
                this.cipher.update(byteBuffer0, byteBuffer2);
                this.cipher.doFinal(byteBuffer1, byteBuffer2);
                ByteBuffer byteBuffer3 = byteBuffer2.duplicate();
                byteBuffer3.flip();
                byteBuffer3.position(byteBuffer2.position());
                this.mac.init(this.hmacKeySpec);
                this.mac.update(arr_b);
                this.mac.update(byteBuffer3);
                byteBuffer2.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
            }
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public void encryptSegment(ByteBuffer byteBuffer0, boolean z, ByteBuffer byteBuffer1) throws GeneralSecurityException {
            synchronized(this) {
                byte[] arr_b = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z);
                IvParameterSpec ivParameterSpec0 = new IvParameterSpec(arr_b);
                this.cipher.init(1, this.keySpec, ivParameterSpec0);
                ++this.encryptedSegments;
                this.cipher.doFinal(byteBuffer0, byteBuffer1);
                ByteBuffer byteBuffer2 = byteBuffer1.duplicate();
                byteBuffer2.flip();
                byteBuffer2.position(byteBuffer1.position());
                this.mac.init(this.hmacKeySpec);
                this.mac.update(arr_b);
                this.mac.update(byteBuffer2);
                byteBuffer1.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
            }
        }

        @Override  // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }
    }

    public static final AlgorithmFipsCompatibility FIPS = null;
    private static final int HMAC_KEY_SIZE_IN_BYTES = 0x20;
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlgo;
    private final byte[] ikm;
    private final int keySizeInBytes;
    private final int plaintextSegmentSize;
    private final String tagAlgo;
    private final int tagSizeInBytes;

    static {
        AesCtrHmacStreaming.FIPS = AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    }

    public AesCtrHmacStreaming(byte[] arr_b, String s, int v, String s1, int v1, int v2, int v3) throws GeneralSecurityException {
        if(!AesCtrHmacStreaming.FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CTR-HMAC streaming in FIPS-mode.");
        }
        AesCtrHmacStreaming.validateParameters(arr_b.length, v, s1, v1, v2, v3);
        this.ikm = Arrays.copyOf(arr_b, arr_b.length);
        this.hkdfAlgo = s;
        this.keySizeInBytes = v;
        this.tagAlgo = s1;
        this.tagSizeInBytes = v1;
        this.ciphertextSegmentSize = v2;
        this.firstSegmentOffset = v3;
        this.plaintextSegmentSize = v2 - v1;
    }

    // 去混淆评级： 低(20)
    static byte[] access$300(AesCtrHmacStreaming aesCtrHmacStreaming0) {
        return new byte[]{-61, 101, 108, 18, (byte)0x87, 37, -45};
    }

    private static Cipher cipherInstance() throws GeneralSecurityException {
        return (Cipher)EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    private SecretKeySpec deriveHmacKeySpec(byte[] arr_b) throws GeneralSecurityException {
        return new SecretKeySpec(arr_b, this.keySizeInBytes, 0x20, this.tagAlgo);
    }

    private byte[] deriveKeyMaterial(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
        return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, arr_b, arr_b1, this.keySizeInBytes + 0x20);
    }

    private SecretKeySpec deriveKeySpec(byte[] arr_b) throws GeneralSecurityException {
        return new SecretKeySpec(arr_b, 0, this.keySizeInBytes, "AES");
    }

    public long expectedCiphertextSize(long v) {
        long v1 = v + ((long)this.getCiphertextOffset());
        long v2 = v1 / ((long)this.plaintextSegmentSize) * ((long)this.ciphertextSegmentSize);
        long v3 = v1 % ((long)this.plaintextSegmentSize);
        return v3 <= 0L ? v2 : v2 + (v3 + ((long)this.tagSizeInBytes));
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOffset() {
        return this.getHeaderLength() + this.firstSegmentOffset;
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOverhead() {
        return this.tagSizeInBytes;
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

    private Mac macInstance() throws GeneralSecurityException {
        return (Mac)EngineFactory.MAC.getInstance(this.tagAlgo);
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

    public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesCtrHmacStreamDecrypter(this);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public StreamSegmentDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return this.newStreamSegmentDecrypter();
    }

    public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] arr_b) throws GeneralSecurityException {
        return new AesCtrHmacStreamEncrypter(this, arr_b);
    }

    @Override  // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public StreamSegmentEncrypter newStreamSegmentEncrypter(byte[] arr_b) throws GeneralSecurityException {
        return this.newStreamSegmentEncrypter(arr_b);
    }

    private byte[] nonceForSegment(byte[] arr_b, long v, boolean z) throws GeneralSecurityException {
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(16);
        byteBuffer0.order(ByteOrder.BIG_ENDIAN);
        byteBuffer0.put(arr_b);
        SubtleUtil.putAsUnsigedInt(byteBuffer0, v);
        byteBuffer0.put(((byte)z));
        byteBuffer0.putInt(0);
        return byteBuffer0.array();
    }

    // 去混淆评级： 低(20)
    private byte[] randomNonce() [...] // 潜在的解密器

    private byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    private static void validateParameters(int v, int v1, String s, int v2, int v3, int v4) throws InvalidAlgorithmParameterException {
        if(v < 16 || v < v1) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, v1));
        }
        Validators.validateAesKeySize(v1);
        if(v2 < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small " + v2);
        }
        if(s.equals("HmacSha1") && v2 > 20 || s.equals("HmacSha256") && v2 > 0x20 || s.equals("HmacSha512") && v2 > 0x40) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        if(v3 - v4 - v2 - v1 - 8 <= 0) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
    }
}

