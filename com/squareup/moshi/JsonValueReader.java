package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;

final class JsonValueReader extends JsonReader {
    static final class JsonIterator implements Cloneable, Iterator {
        final Object[] array;
        final Token endToken;
        int next;

        JsonIterator(Token jsonReader$Token0, Object[] arr_object, int v) {
            this.endToken = jsonReader$Token0;
            this.array = arr_object;
            this.next = v;
        }

        protected JsonIterator clone() {
            return new JsonIterator(this.endToken, this.array, this.next);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        @Override
        public boolean hasNext() {
            return this.next < this.array.length;
        }

        @Override
        public Object next() {
            int v = this.next;
            this.next = v + 1;
            return this.array[v];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static final Object JSON_READER_CLOSED;
    private Object[] stack;

    static {
        JsonValueReader.JSON_READER_CLOSED = new Object();
    }

    JsonValueReader(JsonValueReader jsonValueReader0) {
        super(jsonValueReader0);
        this.stack = (Object[])jsonValueReader0.stack.clone();
        for(int v = 0; v < this.stackSize; ++v) {
            Object[] arr_object = this.stack;
            Object object0 = arr_object[v];
            if(object0 instanceof JsonIterator) {
                arr_object[v] = ((JsonIterator)object0).clone();
            }
        }
    }

    JsonValueReader(Object object0) {
        this.scopes[this.stackSize] = 7;
        Object[] arr_object = new Object[0x20];
        this.stack = arr_object;
        int v = this.stackSize;
        this.stackSize = v + 1;
        arr_object[v] = object0;
    }

    @Override  // com.squareup.moshi.JsonReader
    public void beginArray() throws IOException {
        List list0 = (List)this.require(List.class, Token.BEGIN_ARRAY);
        Object[] arr_object = list0.toArray(new Object[list0.size()]);
        JsonIterator jsonValueReader$JsonIterator0 = new JsonIterator(Token.END_ARRAY, arr_object, 0);
        this.stack[this.stackSize - 1] = jsonValueReader$JsonIterator0;
        this.scopes[this.stackSize - 1] = 1;
        this.pathIndices[this.stackSize - 1] = 0;
        if(jsonValueReader$JsonIterator0.hasNext()) {
            this.push(jsonValueReader$JsonIterator0.next());
        }
    }

    @Override  // com.squareup.moshi.JsonReader
    public void beginObject() throws IOException {
        Map map0 = (Map)this.require(Map.class, Token.BEGIN_OBJECT);
        Object[] arr_object = map0.entrySet().toArray(new Object[map0.size()]);
        JsonIterator jsonValueReader$JsonIterator0 = new JsonIterator(Token.END_OBJECT, arr_object, 0);
        this.stack[this.stackSize - 1] = jsonValueReader$JsonIterator0;
        this.scopes[this.stackSize - 1] = 3;
        if(jsonValueReader$JsonIterator0.hasNext()) {
            this.push(jsonValueReader$JsonIterator0.next());
        }
    }

    @Override
    public void close() throws IOException {
        Arrays.fill(this.stack, 0, this.stackSize, null);
        this.stack[0] = JsonValueReader.JSON_READER_CLOSED;
        this.scopes[0] = 8;
        this.stackSize = 1;
    }

    @Override  // com.squareup.moshi.JsonReader
    public void endArray() throws IOException {
        JsonIterator jsonValueReader$JsonIterator0 = (JsonIterator)this.require(JsonIterator.class, Token.END_ARRAY);
        if(jsonValueReader$JsonIterator0.endToken != Token.END_ARRAY || jsonValueReader$JsonIterator0.hasNext()) {
            throw this.typeMismatch(jsonValueReader$JsonIterator0, Token.END_ARRAY);
        }
        this.remove();
    }

    @Override  // com.squareup.moshi.JsonReader
    public void endObject() throws IOException {
        JsonIterator jsonValueReader$JsonIterator0 = (JsonIterator)this.require(JsonIterator.class, Token.END_OBJECT);
        if(jsonValueReader$JsonIterator0.endToken != Token.END_OBJECT || jsonValueReader$JsonIterator0.hasNext()) {
            throw this.typeMismatch(jsonValueReader$JsonIterator0, Token.END_OBJECT);
        }
        this.pathNames[this.stackSize - 1] = null;
        this.remove();
    }

    @Override  // com.squareup.moshi.JsonReader
    public boolean hasNext() throws IOException {
        if(this.stackSize == 0) {
            return false;
        }
        Object object0 = this.stack[this.stackSize - 1];
        return !(object0 instanceof Iterator) || ((Iterator)object0).hasNext();
    }

    @Override  // com.squareup.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        Boolean boolean0 = (Boolean)this.require(Boolean.class, Token.BOOLEAN);
        this.remove();
        return boolean0.booleanValue();
    }

    @Override  // com.squareup.moshi.JsonReader
    public double nextDouble() throws IOException {
        double f;
        Object object0 = this.require(Object.class, Token.NUMBER);
        if(object0 instanceof Number) {
            f = ((Number)object0).doubleValue();
        }
        else if(object0 instanceof String) {
            try {
                f = Double.parseDouble(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                throw this.typeMismatch(object0, Token.NUMBER);
            }
        }
        else {
            throw this.typeMismatch(object0, Token.NUMBER);
        }
        if(!this.lenient && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + f + " at path " + this.getPath());
        }
        this.remove();
        return f;
    }

    @Override  // com.squareup.moshi.JsonReader
    public int nextInt() throws IOException {
        int v;
        Object object0 = this.require(Object.class, Token.NUMBER);
        if(object0 instanceof Number) {
            v = ((Number)object0).intValue();
        }
        else if(object0 instanceof String) {
            try {
                v = Integer.parseInt(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                try {
                    v = new BigDecimal(((String)object0)).intValueExact();
                }
                catch(NumberFormatException unused_ex) {
                    throw this.typeMismatch(object0, Token.NUMBER);
                }
            }
        }
        else {
            throw this.typeMismatch(object0, Token.NUMBER);
        }
        this.remove();
        return v;
    }

    @Override  // com.squareup.moshi.JsonReader
    public long nextLong() throws IOException {
        long v;
        Object object0 = this.require(Object.class, Token.NUMBER);
        if(object0 instanceof Number) {
            v = ((Number)object0).longValue();
        }
        else if(object0 instanceof String) {
            try {
                v = Long.parseLong(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                try {
                    v = new BigDecimal(((String)object0)).longValueExact();
                }
                catch(NumberFormatException unused_ex) {
                    throw this.typeMismatch(object0, Token.NUMBER);
                }
            }
        }
        else {
            throw this.typeMismatch(object0, Token.NUMBER);
        }
        this.remove();
        return v;
    }

    @Override  // com.squareup.moshi.JsonReader
    public String nextName() throws IOException {
        Map.Entry map$Entry0 = (Map.Entry)this.require(Map.Entry.class, Token.NAME);
        String s = this.stringKey(map$Entry0);
        Object[] arr_object = this.stack;
        int v = this.stackSize - 1;
        arr_object[v] = map$Entry0.getValue();
        this.pathNames[this.stackSize - 2] = s;
        return s;
    }

    @Override  // com.squareup.moshi.JsonReader
    @Nullable
    public Object nextNull() throws IOException {
        this.require(Void.class, Token.NULL);
        this.remove();
        return null;
    }

    @Override  // com.squareup.moshi.JsonReader
    public BufferedSource nextSource() throws IOException {
        try {
            Object object0 = this.readJsonValue();
            BufferedSource bufferedSource0 = new Buffer();
            try(JsonWriter jsonWriter0 = JsonWriter.of(((BufferedSink)bufferedSource0))) {
                jsonWriter0.jsonValue(object0);
            }
            return bufferedSource0;
        }
        catch(Throwable throwable0) {
            throw throwable0;
        }
    }

    @Override  // com.squareup.moshi.JsonReader
    public String nextString() throws IOException {
        Object object0 = this.stackSize == 0 ? null : this.stack[this.stackSize - 1];
        if(object0 instanceof String) {
            this.remove();
            return (String)object0;
        }
        if(object0 instanceof Number) {
            this.remove();
            return object0.toString();
        }
        if(object0 != JsonValueReader.JSON_READER_CLOSED) {
            throw this.typeMismatch(object0, Token.STRING);
        }
        throw new IllegalStateException("JsonReader is closed");
    }

    @Override  // com.squareup.moshi.JsonReader
    public Token peek() throws IOException {
        if(this.stackSize == 0) {
            return Token.END_DOCUMENT;
        }
        Object object0 = this.stack[this.stackSize - 1];
        if(object0 instanceof JsonIterator) {
            return ((JsonIterator)object0).endToken;
        }
        if(object0 instanceof List) {
            return Token.BEGIN_ARRAY;
        }
        if(object0 instanceof Map) {
            return Token.BEGIN_OBJECT;
        }
        if(object0 instanceof Map.Entry) {
            return Token.NAME;
        }
        if(object0 instanceof String) {
            return Token.STRING;
        }
        if(object0 instanceof Boolean) {
            return Token.BOOLEAN;
        }
        if(object0 instanceof Number) {
            return Token.NUMBER;
        }
        if(object0 == null) {
            return Token.NULL;
        }
        if(object0 != JsonValueReader.JSON_READER_CLOSED) {
            throw this.typeMismatch(object0, "a JSON value");
        }
        throw new IllegalStateException("JsonReader is closed");
    }

    @Override  // com.squareup.moshi.JsonReader
    public JsonReader peekJson() {
        return new JsonValueReader(this);
    }

    @Override  // com.squareup.moshi.JsonReader
    public void promoteNameToValue() throws IOException {
        if(this.hasNext()) {
            this.push(this.nextName());
        }
    }

    private void push(Object object0) {
        if(this.stackSize == this.stack.length) {
            if(this.stackSize == 0x100) {
                throw new JsonDataException("Nesting too deep at " + this.getPath());
            }
            this.scopes = Arrays.copyOf(this.scopes, this.scopes.length * 2);
            this.pathNames = (String[])Arrays.copyOf(this.pathNames, this.pathNames.length * 2);
            this.pathIndices = Arrays.copyOf(this.pathIndices, this.pathIndices.length * 2);
            this.stack = Arrays.copyOf(this.stack, this.stack.length * 2);
        }
        int v = this.stackSize;
        this.stackSize = v + 1;
        this.stack[v] = object0;
    }

    private void remove() {
        --this.stackSize;
        this.stack[this.stackSize] = null;
        this.scopes[this.stackSize] = 0;
        if(this.stackSize > 0) {
            int v = this.stackSize - 1;
            ++this.pathIndices[v];
            Object object0 = this.stack[this.stackSize - 1];
            if(object0 instanceof Iterator && ((Iterator)object0).hasNext()) {
                Object object1 = ((Iterator)object0).next();
                this.push(object1);
            }
        }
    }

    @Nullable
    private Object require(Class class0, Token jsonReader$Token0) throws IOException {
        Object object0 = this.stackSize == 0 ? null : this.stack[this.stackSize - 1];
        if(class0.isInstance(object0)) {
            return class0.cast(object0);
        }
        if(object0 == null && jsonReader$Token0 == Token.NULL) {
            return null;
        }
        if(object0 == JsonValueReader.JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw this.typeMismatch(object0, jsonReader$Token0);
    }

    @Override  // com.squareup.moshi.JsonReader
    public int selectName(Options jsonReader$Options0) throws IOException {
        Map.Entry map$Entry0 = (Map.Entry)this.require(Map.Entry.class, Token.NAME);
        String s = this.stringKey(map$Entry0);
        for(int v = 0; v < jsonReader$Options0.strings.length; ++v) {
            if(jsonReader$Options0.strings[v].equals(s)) {
                Object[] arr_object = this.stack;
                int v1 = this.stackSize - 1;
                arr_object[v1] = map$Entry0.getValue();
                this.pathNames[this.stackSize - 2] = s;
                return v;
            }
        }
        return -1;
    }

    @Override  // com.squareup.moshi.JsonReader
    public int selectString(Options jsonReader$Options0) throws IOException {
        Object object0 = this.stackSize == 0 ? null : this.stack[this.stackSize - 1];
        if(!(object0 instanceof String)) {
            if(object0 == JsonValueReader.JSON_READER_CLOSED) {
                throw new IllegalStateException("JsonReader is closed");
            }
            return -1;
        }
        for(int v = 0; v < jsonReader$Options0.strings.length; ++v) {
            if(jsonReader$Options0.strings[v].equals(((String)object0))) {
                this.remove();
                return v;
            }
        }
        return -1;
    }

    @Override  // com.squareup.moshi.JsonReader
    public void skipName() throws IOException {
        if(!this.failOnUnknown) {
            Map.Entry map$Entry0 = (Map.Entry)this.require(Map.Entry.class, Token.NAME);
            Object[] arr_object = this.stack;
            int v = this.stackSize - 1;
            arr_object[v] = map$Entry0.getValue();
            this.pathNames[this.stackSize - 2] = "null";
            return;
        }
        Token jsonReader$Token0 = this.peek();
        this.nextName();
        throw new JsonDataException("Cannot skip unexpected " + jsonReader$Token0 + " at " + this.getPath());
    }

    @Override  // com.squareup.moshi.JsonReader
    public void skipValue() throws IOException {
        if(this.failOnUnknown) {
            throw new JsonDataException("Cannot skip unexpected " + this.peek() + " at " + this.getPath());
        }
        if(this.stackSize > 1) {
            this.pathNames[this.stackSize - 2] = "null";
        }
        Object object0 = this.stackSize == 0 ? null : this.stack[this.stackSize - 1];
        if(object0 instanceof JsonIterator) {
            throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
        }
        if(object0 instanceof Map.Entry) {
            Object[] arr_object = this.stack;
            int v = this.stackSize - 1;
            arr_object[v] = ((Map.Entry)this.stack[this.stackSize - 1]).getValue();
            return;
        }
        if(this.stackSize <= 0) {
            throw new JsonDataException("Expected a value but was " + this.peek() + " at path " + this.getPath());
        }
        this.remove();
    }

    private String stringKey(Map.Entry map$Entry0) {
        Object object0 = map$Entry0.getKey();
        if(!(object0 instanceof String)) {
            throw this.typeMismatch(object0, Token.NAME);
        }
        return (String)object0;
    }
}

