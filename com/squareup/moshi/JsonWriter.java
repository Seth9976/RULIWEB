package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import okio.BufferedSink;
import okio.BufferedSource;

public abstract class JsonWriter implements Closeable, Flushable {
    int flattenStackSize;
    String indent;
    boolean lenient;
    int[] pathIndices;
    String[] pathNames;
    boolean promoteValueToName;
    int[] scopes;
    boolean serializeNulls;
    int stackSize;
    private Map tags;

    JsonWriter() {
        this.stackSize = 0;
        this.scopes = new int[0x20];
        this.pathNames = new String[0x20];
        this.pathIndices = new int[0x20];
        this.flattenStackSize = -1;
    }

    public abstract JsonWriter beginArray() throws IOException;

    @CheckReturnValue
    public final int beginFlatten() {
        switch(this.peekScope()) {
            case 1: 
            case 2: 
            case 3: 
            case 5: {
                int v = this.flattenStackSize;
                this.flattenStackSize = this.stackSize;
                return v;
            }
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
        }
    }

    public abstract JsonWriter beginObject() throws IOException;

    final boolean checkStack() {
        int v = this.stackSize;
        int[] arr_v = this.scopes;
        if(v != arr_v.length) {
            return false;
        }
        if(v == 0x100) {
            throw new JsonDataException("Nesting too deep at $: circular reference?");
        }
        this.scopes = Arrays.copyOf(arr_v, arr_v.length * 2);
        this.pathNames = (String[])Arrays.copyOf(this.pathNames, this.pathNames.length * 2);
        this.pathIndices = Arrays.copyOf(this.pathIndices, this.pathIndices.length * 2);
        if(this instanceof JsonValueWriter) {
            ((JsonValueWriter)this).stack = Arrays.copyOf(((JsonValueWriter)this).stack, ((JsonValueWriter)this).stack.length * 2);
        }
        return true;
    }

    public abstract JsonWriter endArray() throws IOException;

    public final void endFlatten(int v) {
        this.flattenStackSize = v;
    }

    public abstract JsonWriter endObject() throws IOException;

    @CheckReturnValue
    public final String getIndent() [...] // 潜在的解密器

    @CheckReturnValue
    public final String getPath() [...] // 潜在的解密器

    @CheckReturnValue
    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    @CheckReturnValue
    public final boolean isLenient() {
        return this.lenient;
    }

    public final JsonWriter jsonValue(@Nullable Object object0) throws IOException {
        if(object0 instanceof Map) {
            this.beginObject();
            for(Object object1: ((Map)object0).entrySet()) {
                Object object2 = ((Map.Entry)object1).getKey();
                if(!(object2 instanceof String)) {
                    throw new IllegalArgumentException((object2 == null ? "Map keys must be non-null" : "Map keys must be of type String: " + object2.getClass().getName()));
                }
                this.name(((String)object2));
                this.jsonValue(((Map.Entry)object1).getValue());
            }
            this.endObject();
            return this;
        }
        if(object0 instanceof List) {
            this.beginArray();
            for(Object object3: ((List)object0)) {
                this.jsonValue(object3);
            }
            this.endArray();
            return this;
        }
        if(object0 instanceof String) {
            this.value(((String)object0));
            return this;
        }
        if(object0 instanceof Boolean) {
            this.value(((Boolean)object0).booleanValue());
            return this;
        }
        if(object0 instanceof Double) {
            this.value(((double)(((Double)object0))));
            return this;
        }
        if(object0 instanceof Long) {
            this.value(((long)(((Long)object0))));
            return this;
        }
        if(object0 instanceof Number) {
            this.value(((Number)object0));
            return this;
        }
        if(object0 != null) {
            throw new IllegalArgumentException("Unsupported type: " + object0.getClass().getName());
        }
        this.nullValue();
        return this;
    }

    public abstract JsonWriter name(String arg1) throws IOException;

    public abstract JsonWriter nullValue() throws IOException;

    @CheckReturnValue
    public static JsonWriter of(BufferedSink bufferedSink0) {
        return new JsonUtf8Writer(bufferedSink0);
    }

    final int peekScope() {
        int v = this.stackSize;
        if(v == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.scopes[v - 1];
    }

    public final void promoteValueToName() throws IOException {
        switch(this.peekScope()) {
            case 3: 
            case 5: {
                this.promoteValueToName = true;
                return;
            }
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
        }
    }

    final void pushScope(int v) {
        int v1 = this.stackSize;
        this.stackSize = v1 + 1;
        this.scopes[v1] = v;
    }

    final void replaceTop(int v) {
        this.scopes[this.stackSize - 1] = v;
    }

    public void setIndent(String s) {
        if(s.isEmpty()) {
            s = null;
        }
        this.indent = s;
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
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

    @CheckReturnValue
    @Nullable
    public final Object tag(Class class0) {
        return this.tags == null ? null : this.tags.get(class0);
    }

    public abstract JsonWriter value(double arg1) throws IOException;

    public abstract JsonWriter value(long arg1) throws IOException;

    public abstract JsonWriter value(@Nullable Boolean arg1) throws IOException;

    public abstract JsonWriter value(@Nullable Number arg1) throws IOException;

    public abstract JsonWriter value(@Nullable String arg1) throws IOException;

    public final JsonWriter value(BufferedSource bufferedSource0) throws IOException {
        if(!this.promoteValueToName) {
            try(BufferedSink bufferedSink0 = this.valueSink()) {
                bufferedSource0.readAll(bufferedSink0);
                return this;
            }
        }
        throw new IllegalStateException("BufferedSource cannot be used as a map key in JSON at path $");
    }

    public abstract JsonWriter value(boolean arg1) throws IOException;

    @CheckReturnValue
    public abstract BufferedSink valueSink() throws IOException;
}

