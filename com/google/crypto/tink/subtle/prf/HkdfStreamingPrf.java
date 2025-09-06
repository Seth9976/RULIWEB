package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums.HashType;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Immutable
public class HkdfStreamingPrf implements StreamingPrf {
    class HkdfInputStream extends InputStream {
        private ByteBuffer buffer;
        private int ctr;
        private final byte[] input;
        private Mac mac;
        private byte[] prk;

        public HkdfInputStream(byte[] arr_b) {
            this.ctr = -1;
            this.input = Arrays.copyOf(arr_b, arr_b.length);
        }

        private void initialize() throws GeneralSecurityException, IOException {
            try {
                String s = HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType);
                this.mac = (Mac)EngineFactory.MAC.getInstance(s);
            }
            catch(GeneralSecurityException generalSecurityException0) {
                throw new IOException("Creating HMac failed", generalSecurityException0);
            }
            if(HkdfStreamingPrf.this.salt == null || HkdfStreamingPrf.this.salt.length == 0) {
                this.mac.init(new SecretKeySpec(new byte[this.mac.getMacLength()], HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
            }
            else {
                this.mac.init(new SecretKeySpec(HkdfStreamingPrf.this.salt, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
            }
            this.mac.update(HkdfStreamingPrf.this.ikm);
            this.prk = this.mac.doFinal();
            ByteBuffer byteBuffer0 = ByteBuffer.allocateDirect(0);
            this.buffer = byteBuffer0;
            byteBuffer0.mark();
            this.ctr = 0;
        }

        @Override
        public int read() throws IOException {
            byte[] arr_b = new byte[1];
            int v = this.read(arr_b, 0, 1);
            if(v == 1) {
                return arr_b[0] & 0xFF;
            }
            if(v != -1) {
                throw new IOException("Reading failed");
            }
            return -1;
        }

        @Override
        public int read(byte[] arr_b) throws IOException {
            return this.read(arr_b, 0, arr_b.length);
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) throws IOException {
            try {
                if(this.ctr == -1) {
                    this.initialize();
                }
                int v2;
                for(v2 = 0; v2 < v1; v2 += v3) {
                    if(!this.buffer.hasRemaining()) {
                        if(this.ctr == 0xFF) {
                            break;
                        }
                        this.updateBuffer();
                    }
                    int v3 = Math.min(v1 - v2, this.buffer.remaining());
                    this.buffer.get(arr_b, v, v3);
                    v += v3;
                }
                return v2;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.mac = null;
                throw new IOException("HkdfInputStream failed", generalSecurityException0);
            }
        }

        private void updateBuffer() throws GeneralSecurityException, IOException {
            this.mac.init(new SecretKeySpec(this.prk, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
            this.buffer.reset();
            this.mac.update(this.buffer);
            this.mac.update(this.input);
            int v = this.ctr + 1;
            this.ctr = v;
            this.mac.update(((byte)v));
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(this.mac.doFinal());
            this.buffer = byteBuffer0;
            byteBuffer0.mark();
        }
    }

    private final HashType hashType;
    private final byte[] ikm;
    private final byte[] salt;

    public HkdfStreamingPrf(HashType enums$HashType0, byte[] arr_b, byte[] arr_b1) {
        this.hashType = enums$HashType0;
        this.ikm = Arrays.copyOf(arr_b, arr_b.length);
        this.salt = Arrays.copyOf(arr_b1, arr_b1.length);
    }

    @Override  // com.google.crypto.tink.subtle.prf.StreamingPrf
    public InputStream computePrf(byte[] arr_b) {
        return new HkdfInputStream(this, arr_b);
    }

    private static String getJavaxHmacName(HashType enums$HashType0) throws GeneralSecurityException {
        switch(com.google.crypto.tink.subtle.prf.HkdfStreamingPrf.1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[enums$HashType0.ordinal()]) {
            case 1: {
                return "HmacSha1";
            }
            case 2: {
                return "HmacSha256";
            }
            case 3: {
                return "HmacSha384";
            }
            case 4: {
                return "HmacSha512";
            }
            default: {
                throw new GeneralSecurityException("No getJavaxHmacName for given hash " + enums$HashType0 + " known");
            }
        }
    }
}

