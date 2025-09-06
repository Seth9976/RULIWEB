package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED;
    private static final Reader UNREADABLE_READER;
    private int[] pathIndices;
    private String[] pathNames;
    private Object[] stack;
    private int stackSize;

    static {
        JsonTreeReader.UNREADABLE_READER = new AutoCloseable() {
            @Override
            public void close() throws IOException {
                throw new AssertionError();
            }

            @Override
            public int read(char[] arr_c, int v, int v1) throws IOException {
                throw new AssertionError();
            }
        };
        JsonTreeReader.SENTINEL_CLOSED = new Object();
    }

    public JsonTreeReader(JsonElement jsonElement0) {
        super(JsonTreeReader.UNREADABLE_READER);
        this.stack = new Object[0x20];
        this.stackSize = 0;
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
        this.push(jsonElement0);
    }

    @Override  // com.google.gson.stream.JsonReader
    public void beginArray() throws IOException {
        this.expect(JsonToken.BEGIN_ARRAY);
        this.push(((JsonArray)this.peekStack()).iterator());
        this.pathIndices[this.stackSize - 1] = 0;
    }

    @Override  // com.google.gson.stream.JsonReader
    public void beginObject() throws IOException {
        this.expect(JsonToken.BEGIN_OBJECT);
        this.push(((JsonObject)this.peekStack()).entrySet().iterator());
    }

    @Override  // com.google.gson.stream.JsonReader
    public void close() throws IOException {
        this.stack = new Object[]{JsonTreeReader.SENTINEL_CLOSED};
        this.stackSize = 1;
    }

    @Override  // com.google.gson.stream.JsonReader
    public void endArray() throws IOException {
        this.expect(JsonToken.END_ARRAY);
        this.popStack();
        this.popStack();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
    }

    @Override  // com.google.gson.stream.JsonReader
    public void endObject() throws IOException {
        this.expect(JsonToken.END_OBJECT);
        this.popStack();
        this.popStack();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
    }

    private void expect(JsonToken jsonToken0) throws IOException {
        if(this.peek() != jsonToken0) {
            throw new IllegalStateException("Expected " + jsonToken0 + " but was " + this.peek() + this.locationString());
        }
    }

    @Override  // com.google.gson.stream.JsonReader
    public String getPath() {
        StringBuilder stringBuilder0 = new StringBuilder("$");
        for(int v = 0; true; ++v) {
            int v1 = this.stackSize;
            if(v >= v1) {
                break;
            }
            Object[] arr_object = this.stack;
            Object object0 = arr_object[v];
            if(object0 instanceof JsonArray) {
                ++v;
                if(v < v1 && arr_object[v] instanceof Iterator) {
                    stringBuilder0.append('[');
                    stringBuilder0.append(this.pathIndices[v]);
                    stringBuilder0.append(']');
                }
            }
            else if(object0 instanceof JsonObject) {
                ++v;
                if(v < v1 && arr_object[v] instanceof Iterator) {
                    stringBuilder0.append('.');
                    String s = this.pathNames[v];
                    if(s != null) {
                        stringBuilder0.append(s);
                    }
                }
            }
        }
        return stringBuilder0.toString();
    }

    @Override  // com.google.gson.stream.JsonReader
    public boolean hasNext() throws IOException {
        JsonToken jsonToken0 = this.peek();
        return jsonToken0 != JsonToken.END_OBJECT && jsonToken0 != JsonToken.END_ARRAY;
    }

    private String locationString() {
        return " at path " + this.getPath();
    }

    @Override  // com.google.gson.stream.JsonReader
    public boolean nextBoolean() throws IOException {
        this.expect(JsonToken.BOOLEAN);
        boolean z = ((JsonPrimitive)this.popStack()).getAsBoolean();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
        return z;
    }

    @Override  // com.google.gson.stream.JsonReader
    public double nextDouble() throws IOException {
        JsonToken jsonToken0 = this.peek();
        if(jsonToken0 != JsonToken.NUMBER && jsonToken0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonToken0 + this.locationString());
        }
        double f = ((JsonPrimitive)this.peekStack()).getAsDouble();
        if(!this.isLenient() && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + f);
        }
        this.popStack();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
        return f;
    }

    @Override  // com.google.gson.stream.JsonReader
    public int nextInt() throws IOException {
        JsonToken jsonToken0 = this.peek();
        if(jsonToken0 != JsonToken.NUMBER && jsonToken0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonToken0 + this.locationString());
        }
        int v = ((JsonPrimitive)this.peekStack()).getAsInt();
        this.popStack();
        int v1 = this.stackSize;
        if(v1 > 0) {
            ++this.pathIndices[v1 - 1];
        }
        return v;
    }

    JsonElement nextJsonElement() throws IOException {
        JsonToken jsonToken0 = this.peek();
        if(jsonToken0 == JsonToken.NAME || jsonToken0 == JsonToken.END_ARRAY || jsonToken0 == JsonToken.END_OBJECT || jsonToken0 == JsonToken.END_DOCUMENT) {
            throw new IllegalStateException("Unexpected " + jsonToken0 + " when reading a JsonElement.");
        }
        JsonElement jsonElement0 = (JsonElement)this.peekStack();
        this.skipValue();
        return jsonElement0;
    }

    @Override  // com.google.gson.stream.JsonReader
    public long nextLong() throws IOException {
        JsonToken jsonToken0 = this.peek();
        if(jsonToken0 != JsonToken.NUMBER && jsonToken0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + JsonToken.NUMBER + " but was " + jsonToken0 + this.locationString());
        }
        long v = ((JsonPrimitive)this.peekStack()).getAsLong();
        this.popStack();
        int v1 = this.stackSize;
        if(v1 > 0) {
            ++this.pathIndices[v1 - 1];
        }
        return v;
    }

    @Override  // com.google.gson.stream.JsonReader
    public String nextName() throws IOException {
        this.expect(JsonToken.NAME);
        Object object0 = ((Iterator)this.peekStack()).next();
        String s = (String)((Map.Entry)object0).getKey();
        this.pathNames[this.stackSize - 1] = s;
        this.push(((Map.Entry)object0).getValue());
        return s;
    }

    @Override  // com.google.gson.stream.JsonReader
    public void nextNull() throws IOException {
        this.expect(JsonToken.NULL);
        this.popStack();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
    }

    @Override  // com.google.gson.stream.JsonReader
    public String nextString() throws IOException {
        JsonToken jsonToken0 = this.peek();
        if(jsonToken0 != JsonToken.STRING && jsonToken0 != JsonToken.NUMBER) {
            throw new IllegalStateException("Expected " + JsonToken.STRING + " but was " + jsonToken0 + this.locationString());
        }
        String s = ((JsonPrimitive)this.popStack()).getAsString();
        int v = this.stackSize;
        if(v > 0) {
            ++this.pathIndices[v - 1];
        }
        return s;
    }

    @Override  // com.google.gson.stream.JsonReader
    public JsonToken peek() throws IOException {
        if(this.stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object object0 = this.peekStack();
        if(object0 instanceof Iterator) {
            boolean z = this.stack[this.stackSize - 2] instanceof JsonObject;
            if(((Iterator)object0).hasNext()) {
                if(z) {
                    return JsonToken.NAME;
                }
                Object object1 = ((Iterator)object0).next();
                this.push(object1);
                return this.peek();
            }
            return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
        }
        if(object0 instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        }
        if(object0 instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
        }
        if(object0 instanceof JsonPrimitive) {
            if(((JsonPrimitive)object0).isString()) {
                return JsonToken.STRING;
            }
            if(((JsonPrimitive)object0).isBoolean()) {
                return JsonToken.BOOLEAN;
            }
            if(!((JsonPrimitive)object0).isNumber()) {
                throw new AssertionError();
            }
            return JsonToken.NUMBER;
        }
        if(object0 instanceof JsonNull) {
            return JsonToken.NULL;
        }
        if(object0 != JsonTreeReader.SENTINEL_CLOSED) {
            throw new AssertionError();
        }
        throw new IllegalStateException("JsonReader is closed");
    }

    private Object peekStack() {
        return this.stack[this.stackSize - 1];
    }

    private Object popStack() {
        Object[] arr_object = this.stack;
        int v = this.stackSize - 1;
        this.stackSize = v;
        Object object0 = arr_object[v];
        arr_object[v] = null;
        return object0;
    }

    public void promoteNameToValue() throws IOException {
        this.expect(JsonToken.NAME);
        Object object0 = ((Iterator)this.peekStack()).next();
        this.push(((Map.Entry)object0).getValue());
        this.push(new JsonPrimitive(((String)((Map.Entry)object0).getKey())));
    }

    private void push(Object object0) {
        int v = this.stackSize;
        Object[] arr_object = this.stack;
        if(v == arr_object.length) {
            this.stack = Arrays.copyOf(arr_object, v * 2);
            this.pathIndices = Arrays.copyOf(this.pathIndices, v * 2);
            this.pathNames = (String[])Arrays.copyOf(this.pathNames, v * 2);
        }
        int v1 = this.stackSize;
        this.stackSize = v1 + 1;
        this.stack[v1] = object0;
    }

    @Override  // com.google.gson.stream.JsonReader
    public void skipValue() throws IOException {
        if(this.peek() == JsonToken.NAME) {
            this.nextName();
            this.pathNames[this.stackSize - 2] = "null";
        }
        else {
            this.popStack();
            int v = this.stackSize;
            if(v > 0) {
                this.pathNames[v - 1] = "null";
            }
        }
        int v1 = this.stackSize;
        if(v1 > 0) {
            ++this.pathIndices[v1 - 1];
        }
    }

    @Override  // com.google.gson.stream.JsonReader
    public String toString() {
        return this.getClass().getSimpleName() + this.locationString();
    }
}

