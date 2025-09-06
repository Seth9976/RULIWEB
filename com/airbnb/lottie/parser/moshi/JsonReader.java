package com.airbnb.lottie.parser.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

public abstract class JsonReader implements Closeable {
    public static final class Options {
        final okio.Options doubleQuoteSuffix;
        final String[] strings;

        private Options(String[] arr_s, okio.Options options0) {
            this.strings = arr_s;
            this.doubleQuoteSuffix = options0;
        }

        public static Options of(String[] arr_s) {
            try {
                ByteString[] arr_byteString = new ByteString[arr_s.length];
                Buffer buffer0 = new Buffer();
                for(int v = 0; v < arr_s.length; ++v) {
                    JsonReader.string(buffer0, arr_s[v]);
                    buffer0.readByte();
                    arr_byteString[v] = buffer0.readByteString();
                }
                return new Options(((String[])arr_s.clone()), okio.Options.of(arr_byteString));
            }
            catch(IOException iOException0) {
                throw new AssertionError(iOException0);
            }
        }
    }

    public static enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT;

    }

    private static final String[] REPLACEMENT_CHARS;
    boolean failOnUnknown;
    boolean lenient;
    int[] pathIndices;
    String[] pathNames;
    int[] scopes;
    int stackSize;

    static {
        JsonReader.REPLACEMENT_CHARS = new String[0x80];
        for(int v = 0; v <= 0x1F; ++v) {
            JsonReader.REPLACEMENT_CHARS[v] = "\\u001f";
        }
        JsonReader.REPLACEMENT_CHARS[34] = "\\\"";
        JsonReader.REPLACEMENT_CHARS[92] = "\\\\";
        JsonReader.REPLACEMENT_CHARS[9] = "\\t";
        JsonReader.REPLACEMENT_CHARS[8] = "\\b";
        JsonReader.REPLACEMENT_CHARS[10] = "\\n";
        JsonReader.REPLACEMENT_CHARS[13] = "\\r";
        JsonReader.REPLACEMENT_CHARS[12] = "\\f";
    }

    JsonReader() {
        this.scopes = new int[0x20];
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
    }

    public abstract void beginArray() throws IOException;

    public abstract void beginObject() throws IOException;

    public abstract void endArray() throws IOException;

    public abstract void endObject() throws IOException;

    public final String getPath() [...] // 潜在的解密器

    public abstract boolean hasNext() throws IOException;

    public abstract boolean nextBoolean() throws IOException;

    public abstract double nextDouble() throws IOException;

    public abstract int nextInt() throws IOException;

    public abstract String nextName() throws IOException;

    public abstract String nextString() throws IOException;

    public static JsonReader of(BufferedSource bufferedSource0) {
        return new JsonUtf8Reader(bufferedSource0);
    }

    public abstract Token peek() throws IOException;

    final void pushScope(int v) {
        int v1 = this.stackSize;
        int[] arr_v = this.scopes;
        if(v1 == arr_v.length) {
            if(v1 == 0x100) {
                throw new JsonDataException("Nesting too deep at $");
            }
            this.scopes = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.pathNames = (String[])Arrays.copyOf(this.pathNames, this.pathNames.length * 2);
            this.pathIndices = Arrays.copyOf(this.pathIndices, this.pathIndices.length * 2);
        }
        int v2 = this.stackSize;
        this.stackSize = v2 + 1;
        this.scopes[v2] = v;
    }

    public abstract int selectName(Options arg1) throws IOException;

    public abstract void skipName() throws IOException;

    public abstract void skipValue() throws IOException;

    private static void string(BufferedSink bufferedSink0, String s) throws IOException {
        String s2;
        String[] arr_s = JsonReader.REPLACEMENT_CHARS;
        bufferedSink0.writeByte(34);
        int v = s.length();
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            int v3 = s.charAt(v1);
            if(v3 < 0x80) {
                String s1 = arr_s[v3];
                if(s1 != null) {
                    s2 = s1;
                    goto label_17;
                }
            }
            else {
                switch(v3) {
                    case 0x2028: {
                        s2 = "\\u2028";
                        break;
                    }
                    case 0x2029: {
                        s2 = "\\u2029";
                        break;
                    }
                    default: {
                        goto label_21;
                    }
                }
            label_17:
                if(v2 < v1) {
                    bufferedSink0.writeUtf8(s, v2, v1);
                }
                bufferedSink0.writeUtf8(s2);
                v2 = v1 + 1;
            }
        label_21:
            ++v1;
        }
        if(v2 < v) {
            bufferedSink0.writeUtf8(s, v2, v);
        }
        bufferedSink0.writeByte(34);
    }

    // 去混淆评级： 低(20)
    final JsonEncodingException syntaxError(String s) throws JsonEncodingException {
        throw new JsonEncodingException(s + " at path " + "$");
    }
}

