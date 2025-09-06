package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;

public class Utf8Old extends Utf8 {
    static class Cache {
        final CharsetDecoder decoder;
        final CharsetEncoder encoder;
        CharSequence lastInput;
        ByteBuffer lastOutput;

        Cache() {
            this.lastInput = null;
            this.lastOutput = null;
            this.encoder = StandardCharsets.UTF_8.newEncoder();
            this.decoder = StandardCharsets.UTF_8.newDecoder();
        }
    }

    private static final ThreadLocal CACHE;

    static {
        Utf8Old.CACHE = ThreadLocal.withInitial(() -> new Cache());
    }

    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer0, int v, int v1) {
        CharsetDecoder charsetDecoder0 = ((Cache)Utf8Old.CACHE.get()).decoder;
        charsetDecoder0.reset();
        ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
        byteBuffer1.position(v);
        byteBuffer1.limit(v + v1);
        try {
            return charsetDecoder0.decode(byteBuffer1).toString();
        }
        catch(CharacterCodingException characterCodingException0) {
            throw new IllegalArgumentException("Bad encoding", characterCodingException0);
        }
    }

    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence0, ByteBuffer byteBuffer0) {
        Cache utf8Old$Cache0 = (Cache)Utf8Old.CACHE.get();
        if(utf8Old$Cache0.lastInput != charSequence0) {
            this.encodedLength(charSequence0);
        }
        byteBuffer0.put(utf8Old$Cache0.lastOutput);
    }

    @Override  // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence0) {
        Cache utf8Old$Cache0 = (Cache)Utf8Old.CACHE.get();
        int v = (int)(((float)charSequence0.length()) * utf8Old$Cache0.encoder.maxBytesPerChar());
        if(utf8Old$Cache0.lastOutput == null || utf8Old$Cache0.lastOutput.capacity() < v) {
            utf8Old$Cache0.lastOutput = ByteBuffer.allocate(Math.max(0x80, v));
        }
        utf8Old$Cache0.lastOutput.clear();
        utf8Old$Cache0.lastInput = charSequence0;
        CharBuffer charBuffer0 = charSequence0 instanceof CharBuffer ? ((CharBuffer)charSequence0) : CharBuffer.wrap(charSequence0);
        CoderResult coderResult0 = utf8Old$Cache0.encoder.encode(charBuffer0, utf8Old$Cache0.lastOutput, true);
        if(coderResult0.isError()) {
            try {
                coderResult0.throwException();
            }
            catch(CharacterCodingException characterCodingException0) {
                throw new IllegalArgumentException("bad character encoding", characterCodingException0);
            }
        }
        utf8Old$Cache0.lastOutput.flip();
        return utf8Old$Cache0.lastOutput.remaining();
    }

    // 检测为 Lambda 实现
    static Cache lambda$static$0() [...]
}

