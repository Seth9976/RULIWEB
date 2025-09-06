package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED;
    private static final Writer UNWRITABLE_WRITER;
    private String pendingName;
    private JsonElement product;
    private final List stack;

    static {
        JsonTreeWriter.UNWRITABLE_WRITER = new AutoCloseable() {
            @Override
            public void close() throws IOException {
                throw new AssertionError();
            }

            @Override
            public void flush() throws IOException {
                throw new AssertionError();
            }

            @Override
            public void write(char[] arr_c, int v, int v1) {
                throw new AssertionError();
            }
        };
        JsonTreeWriter.SENTINEL_CLOSED = new JsonPrimitive("closed");
    }

    public JsonTreeWriter() {
        super(JsonTreeWriter.UNWRITABLE_WRITER);
        this.stack = new ArrayList();
        this.product = JsonNull.INSTANCE;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter beginArray() throws IOException {
        JsonArray jsonArray0 = new JsonArray();
        this.put(jsonArray0);
        this.stack.add(jsonArray0);
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter beginObject() throws IOException {
        JsonObject jsonObject0 = new JsonObject();
        this.put(jsonObject0);
        this.stack.add(jsonObject0);
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public void close() throws IOException {
        if(!this.stack.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.stack.add(JsonTreeWriter.SENTINEL_CLOSED);
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter endArray() throws IOException {
        if(this.stack.isEmpty() || this.pendingName != null || !(this.peek() instanceof JsonArray)) {
            throw new IllegalStateException();
        }
        this.stack.remove(this.stack.size() - 1);
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter endObject() throws IOException {
        if(this.stack.isEmpty() || this.pendingName != null || !(this.peek() instanceof JsonObject)) {
            throw new IllegalStateException();
        }
        this.stack.remove(this.stack.size() - 1);
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public void flush() throws IOException {
    }

    public JsonElement get() {
        if(!this.stack.isEmpty()) {
            throw new IllegalStateException("Expected one JSON element but was " + this.stack);
        }
        return this.product;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter name(String s) throws IOException {
        if(s == null) {
            throw new NullPointerException("name == null");
        }
        if(this.stack.isEmpty() || this.pendingName != null || !(this.peek() instanceof JsonObject)) {
            throw new IllegalStateException();
        }
        this.pendingName = s;
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter nullValue() throws IOException {
        this.put(JsonNull.INSTANCE);
        return this;
    }

    private JsonElement peek() {
        return (JsonElement)this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement jsonElement0) {
        if(this.pendingName != null) {
            ((JsonObject)this.peek()).add(this.pendingName, jsonElement0);
            this.pendingName = null;
            return;
        }
        if(this.stack.isEmpty()) {
            this.product = jsonElement0;
            return;
        }
        JsonElement jsonElement1 = this.peek();
        if(!(jsonElement1 instanceof JsonArray)) {
            throw new IllegalStateException();
        }
        ((JsonArray)jsonElement1).add(jsonElement0);
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(double f) throws IOException {
        if(!this.isLenient() && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new IllegalArgumentException("JSON forbids NaN and infinities: " + f);
        }
        this.put(new JsonPrimitive(f));
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(long v) throws IOException {
        this.put(new JsonPrimitive(v));
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(Boolean boolean0) throws IOException {
        if(boolean0 == null) {
            return this.nullValue();
        }
        this.put(new JsonPrimitive(boolean0));
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(Number number0) throws IOException {
        if(number0 == null) {
            return this.nullValue();
        }
        if(!this.isLenient()) {
            double f = number0.doubleValue();
            if(Double.isNaN(f) || Double.isInfinite(f)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number0);
            }
        }
        this.put(new JsonPrimitive(number0));
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(String s) throws IOException {
        if(s == null) {
            return this.nullValue();
        }
        this.put(new JsonPrimitive(s));
        return this;
    }

    @Override  // com.google.gson.stream.JsonWriter
    public JsonWriter value(boolean z) throws IOException {
        this.put(new JsonPrimitive(Boolean.valueOf(z)));
        return this;
    }
}

