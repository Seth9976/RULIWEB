package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.Buffer;
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

        @CheckReturnValue
        public static Options of(String[] arr_s) {
            try {
                ByteString[] arr_byteString = new ByteString[arr_s.length];
                Buffer buffer0 = new Buffer();
                for(int v = 0; v < arr_s.length; ++v) {
                    JsonUtf8Writer.string(buffer0, arr_s[v]);
                    buffer0.readByte();
                    arr_byteString[v] = buffer0.readByteString();
                }
                return new Options(((String[])arr_s.clone()), okio.Options.of(arr_byteString));
            }
            catch(IOException iOException0) {
                throw new AssertionError(iOException0);
            }
        }

        public List strings() {
            return Collections.unmodifiableList(Arrays.asList(this.strings));
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

    boolean failOnUnknown;
    boolean lenient;
    int[] pathIndices;
    String[] pathNames;
    int[] scopes;
    int stackSize;
    private Map tags;

    JsonReader() {
        this.scopes = new int[0x20];
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
    }

    JsonReader(JsonReader jsonReader0) {
        this.stackSize = jsonReader0.stackSize;
        this.scopes = (int[])jsonReader0.scopes.clone();
        this.pathNames = (String[])jsonReader0.pathNames.clone();
        this.pathIndices = (int[])jsonReader0.pathIndices.clone();
        this.lenient = jsonReader0.lenient;
        this.failOnUnknown = jsonReader0.failOnUnknown;
    }

    public abstract void beginArray() throws IOException;

    public abstract void beginObject() throws IOException;

    public abstract void endArray() throws IOException;

    public abstract void endObject() throws IOException;

    @CheckReturnValue
    public final boolean failOnUnknown() {
        return this.failOnUnknown;
    }

    @CheckReturnValue
    public final String getPath() [...] // 潜在的解密器

    @CheckReturnValue
    public abstract boolean hasNext() throws IOException;

    @CheckReturnValue
    public final boolean isLenient() {
        return this.lenient;
    }

    public abstract boolean nextBoolean() throws IOException;

    public abstract double nextDouble() throws IOException;

    public abstract int nextInt() throws IOException;

    public abstract long nextLong() throws IOException;

    @CheckReturnValue
    public abstract String nextName() throws IOException;

    @Nullable
    public abstract Object nextNull() throws IOException;

    public abstract BufferedSource nextSource() throws IOException;

    public abstract String nextString() throws IOException;

    @CheckReturnValue
    public static JsonReader of(BufferedSource bufferedSource0) {
        return new JsonUtf8Reader(bufferedSource0);
    }

    @CheckReturnValue
    public abstract Token peek() throws IOException;

    @CheckReturnValue
    public abstract JsonReader peekJson();

    public abstract void promoteNameToValue() throws IOException;

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

    @Nullable
    public final Object readJsonValue() throws IOException {
        switch(com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[this.peek().ordinal()]) {
            case 1: {
                ArrayList arrayList0 = new ArrayList();
                this.beginArray();
                while(this.hasNext()) {
                    arrayList0.add(this.readJsonValue());
                }
                this.endArray();
                return arrayList0;
            }
            case 2: {
                LinkedHashTreeMap linkedHashTreeMap0 = new LinkedHashTreeMap();
                this.beginObject();
                while(this.hasNext()) {
                    String s = this.nextName();
                    Object object0 = this.readJsonValue();
                    Object object1 = linkedHashTreeMap0.put(s, object0);
                    if(object1 != null) {
                        throw new JsonDataException("Map key \'" + s + "\' has multiple values at path " + "$" + ": " + object1 + " and " + object0);
                    }
                    if(false) {
                        break;
                    }
                }
                this.endObject();
                return linkedHashTreeMap0;
            }
            case 3: {
                return this.nextString();
            }
            case 4: {
                return this.nextDouble();
            }
            case 5: {
                return Boolean.valueOf(this.nextBoolean());
            }
            case 6: {
                return this.nextNull();
            }
            default: {
                throw new IllegalStateException("Expected a value but was " + this.peek() + " at path " + "$");
            }
        }
    }

    @CheckReturnValue
    public abstract int selectName(Options arg1) throws IOException;

    @CheckReturnValue
    public abstract int selectString(Options arg1) throws IOException;

    public final void setFailOnUnknown(boolean z) {
        this.failOnUnknown = z;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final void setTag(Class class0, Object object0) {
        if(!class0.isAssignableFrom(object0.getClass())) {
            throw new IllegalArgumentException("Tag value must be of type " + class0.getName());
        }
        if(this.tags == null) {
            this.tags = new LinkedHashMap();
        }
        this.tags.put(class0, object0);
    }

    public abstract void skipName() throws IOException;

    public abstract void skipValue() throws IOException;

    // 去混淆评级： 低(20)
    final JsonEncodingException syntaxError(String s) throws JsonEncodingException {
        throw new JsonEncodingException(s + " at path " + "$");
    }

    @CheckReturnValue
    @Nullable
    public final Object tag(Class class0) {
        return this.tags == null ? null : this.tags.get(class0);
    }

    // 去混淆评级： 中等(50)
    final JsonDataException typeMismatch(@Nullable Object object0, Object object1) {
        return object0 == null ? new JsonDataException("Expected " + object1 + " but was null at path " + "$") : new JsonDataException("Expected " + object1 + " but was " + object0 + ", a " + object0.getClass().getName() + ", at path " + "$");
    }

    class com.squareup.moshi.JsonReader.1 {
        static final int[] $SwitchMap$com$squareup$moshi$JsonReader$Token;

        static {
            int[] arr_v = new int[Token.values().length];
            com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token = arr_v;
            try {
                arr_v[Token.BEGIN_ARRAY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[Token.BEGIN_OBJECT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[Token.STRING.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[Token.NUMBER.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[Token.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.squareup.moshi.JsonReader.1.$SwitchMap$com$squareup$moshi$JsonReader$Token[Token.NULL.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

