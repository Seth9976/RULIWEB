package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

final class JsonValueWriter extends JsonWriter {
    @Nullable
    private String deferredName;
    Object[] stack;

    JsonValueWriter() {
        this.stack = new Object[0x20];
        this.pushScope(6);
    }

    private JsonValueWriter add(@Nullable Object object0) {
        int v = this.peekScope();
        if(this.stackSize == 1) {
            if(v != 6) {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
            this.scopes[this.stackSize - 1] = 7;
            this.stack[this.stackSize - 1] = object0;
            return this;
        }
        if(v == 3 && this.deferredName != null) {
            if(object0 != null || this.serializeNulls) {
                Object object1 = ((Map)this.stack[this.stackSize - 1]).put(this.deferredName, object0);
                if(object1 != null) {
                    throw new IllegalArgumentException("Map key \'" + this.deferredName + "\' has multiple values at path " + this.getPath() + ": " + object1 + " and " + object0);
                }
            }
            this.deferredName = null;
            return this;
        }
        if(v != 1) {
            throw v == 9 ? new IllegalStateException("Sink from valueSink() was not closed") : new IllegalStateException("Nesting problem.");
        }
        ((List)this.stack[this.stackSize - 1]).add(object0);
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter beginArray() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + this.getPath());
        }
        if(this.stackSize == this.flattenStackSize && this.scopes[this.stackSize - 1] == 1) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        this.checkStack();
        ArrayList arrayList0 = new ArrayList();
        this.add(arrayList0);
        this.stack[this.stackSize] = arrayList0;
        this.pathIndices[this.stackSize] = 0;
        this.pushScope(1);
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter beginObject() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + this.getPath());
        }
        if(this.stackSize == this.flattenStackSize && this.scopes[this.stackSize - 1] == 3) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        this.checkStack();
        LinkedHashTreeMap linkedHashTreeMap0 = new LinkedHashTreeMap();
        this.add(linkedHashTreeMap0);
        this.stack[this.stackSize] = linkedHashTreeMap0;
        this.pushScope(3);
        return this;
    }

    @Override
    public void close() throws IOException {
        if(this.stackSize > 1 || this.stackSize == 1 && this.scopes[0] != 7) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter endArray() throws IOException {
        if(this.peekScope() != 1) {
            throw new IllegalStateException("Nesting problem.");
        }
        if(this.stackSize == ~this.flattenStackSize) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        --this.stackSize;
        this.stack[this.stackSize] = null;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter endObject() throws IOException {
        if(this.peekScope() != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        if(this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        if(this.stackSize == ~this.flattenStackSize) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        this.promoteValueToName = false;
        --this.stackSize;
        this.stack[this.stackSize] = null;
        this.pathNames[this.stackSize] = null;
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override
    public void flush() throws IOException {
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter name(String s) throws IOException {
        if(s == null) {
            throw new NullPointerException("name == null");
        }
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        if(this.peekScope() != 3 || this.deferredName != null || this.promoteValueToName) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.deferredName = s;
        this.pathNames[this.stackSize - 1] = s;
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter nullValue() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.add(null);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    public Object root() {
        if(this.stackSize > 1 || this.stackSize == 1 && this.scopes[0] != 7) {
            throw new IllegalStateException("Incomplete document");
        }
        return this.stack[0];
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(double f) throws IOException {
        if(!this.lenient && (Double.isNaN(f) || f == -Infinity || f == Infinity)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + f);
        }
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(Double.toString(f));
        }
        this.add(f);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(long v) throws IOException {
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(Long.toString(v));
        }
        this.add(v);
        int v1 = this.stackSize - 1;
        ++this.pathIndices[v1];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(@Nullable Boolean boolean0) throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.add(boolean0);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(@Nullable Number number0) throws IOException {
        if(!(number0 instanceof Byte) && !(number0 instanceof Short) && !(number0 instanceof Integer) && !(number0 instanceof Long)) {
            if(!(number0 instanceof Float) && !(number0 instanceof Double)) {
                if(number0 == null) {
                    return this.nullValue();
                }
                BigDecimal bigDecimal0 = number0 instanceof BigDecimal ? ((BigDecimal)number0) : new BigDecimal(number0.toString());
                if(this.promoteValueToName) {
                    this.promoteValueToName = false;
                    return this.name(bigDecimal0.toString());
                }
                this.add(bigDecimal0);
                int v = this.stackSize - 1;
                ++this.pathIndices[v];
                return this;
            }
            return this.value(number0.doubleValue());
        }
        return this.value(number0.longValue());
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(@Nullable String s) throws IOException {
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(s);
        }
        this.add(s);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(boolean z) throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.add(Boolean.valueOf(z));
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public BufferedSink valueSink() {
        if(this.promoteValueToName) {
            throw new IllegalStateException("BufferedSink cannot be used as a map key in JSON at path " + this.getPath());
        }
        if(this.peekScope() == 9) {
            throw new IllegalStateException("Sink from valueSink() was not closed");
        }
        this.pushScope(9);
        Buffer buffer0 = new Buffer();
        return Okio.buffer(new ForwardingSink(buffer0) {
            @Override  // okio.ForwardingSink
            public void close() throws IOException {
                if(JsonValueWriter.this.peekScope() != 9 || JsonValueWriter.this.stack[JsonValueWriter.this.stackSize] != null) {
                    throw new AssertionError();
                }
                --JsonValueWriter.this.stackSize;
                Object object0 = JsonReader.of(buffer0).readJsonValue();
                boolean z = JsonValueWriter.this.serializeNulls;
                JsonValueWriter.this.serializeNulls = true;
                try {
                    JsonValueWriter.this.add(object0);
                }
                finally {
                    JsonValueWriter.this.serializeNulls = z;
                }
                int[] arr_v = JsonValueWriter.this.pathIndices;
                int v1 = JsonValueWriter.this.stackSize - 1;
                ++arr_v[v1];
            }
        });
    }
}

