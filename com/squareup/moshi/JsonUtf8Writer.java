package com.squareup.moshi;

import java.io.IOException;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Timeout;

final class JsonUtf8Writer extends JsonWriter {
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private String separator;
    private final BufferedSink sink;

    static {
        JsonUtf8Writer.REPLACEMENT_CHARS = new String[0x80];
        for(int v = 0; v <= 0x1F; ++v) {
            JsonUtf8Writer.REPLACEMENT_CHARS[v] = "\\u001f";
        }
        JsonUtf8Writer.REPLACEMENT_CHARS[34] = "\\\"";
        JsonUtf8Writer.REPLACEMENT_CHARS[92] = "\\\\";
        JsonUtf8Writer.REPLACEMENT_CHARS[9] = "\\t";
        JsonUtf8Writer.REPLACEMENT_CHARS[8] = "\\b";
        JsonUtf8Writer.REPLACEMENT_CHARS[10] = "\\n";
        JsonUtf8Writer.REPLACEMENT_CHARS[13] = "\\r";
        JsonUtf8Writer.REPLACEMENT_CHARS[12] = "\\f";
    }

    JsonUtf8Writer(BufferedSink bufferedSink0) {
        this.separator = ":";
        if(bufferedSink0 == null) {
            throw new NullPointerException("sink == null");
        }
        this.sink = bufferedSink0;
        this.pushScope(6);
    }

    private void beforeName() throws IOException {
        int v = this.peekScope();
        if(v == 5) {
            this.sink.writeByte(44);
        }
        else if(v == 3) {
        }
        else {
            throw new IllegalStateException("Nesting problem.");
        }
        this.newline();
        this.replaceTop(4);
    }

    private void beforeValue() throws IOException {
        int v = 2;
        switch(this.peekScope()) {
            case 1: {
                this.newline();
                break;
            }
            case 2: {
                this.sink.writeByte(44);
                this.newline();
                break;
            }
            case 4: {
                this.sink.writeUtf8(this.separator);
                v = 5;
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                if(!this.lenient) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            }
            case 9: {
                throw new IllegalStateException("Sink from valueSink() was not closed");
            }
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
        }
        this.replaceTop(v);
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter beginArray() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.writeDeferredName();
        return this.open(1, 2, '[');
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter beginObject() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.writeDeferredName();
        return this.open(3, 5, '{');
    }

    private JsonWriter close(int v, int v1, char c) throws IOException {
        int v2 = this.peekScope();
        if(v2 != v1 && v2 != v) {
            throw new IllegalStateException("Nesting problem.");
        }
        if(this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        if(this.stackSize == ~this.flattenStackSize) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        --this.stackSize;
        this.pathNames[this.stackSize] = null;
        int v3 = this.stackSize - 1;
        ++this.pathIndices[v3];
        if(v2 == v1) {
            this.newline();
        }
        this.sink.writeByte(((int)c));
        return this;
    }

    @Override
    public void close() throws IOException {
        this.sink.close();
        if(this.stackSize > 1 || this.stackSize == 1 && this.scopes[0] != 7) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter endArray() throws IOException {
        return this.close(1, 2, ']');
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter endObject() throws IOException {
        this.promoteValueToName = false;
        return this.close(3, 5, '}');
    }

    @Override
    public void flush() throws IOException {
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.sink.flush();
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter name(String s) throws IOException {
        if(s == null) {
            throw new NullPointerException("name == null");
        }
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        switch(this.peekScope()) {
            case 3: 
            case 5: {
                if(this.deferredName == null && !this.promoteValueToName) {
                    this.deferredName = s;
                    this.pathNames[this.stackSize - 1] = s;
                    return this;
                }
            }
        }
        throw new IllegalStateException("Nesting problem.");
    }

    private void newline() throws IOException {
        if(this.indent != null) {
            this.sink.writeByte(10);
            int v = this.stackSize;
            for(int v1 = 1; v1 < v; ++v1) {
                this.sink.writeUtf8(this.indent);
            }
        }
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter nullValue() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + this.getPath());
        }
        if(this.deferredName != null) {
            if(this.serializeNulls) {
                this.writeDeferredName();
                goto label_7;
            }
            this.deferredName = null;
            return this;
        }
    label_7:
        this.beforeValue();
        this.sink.writeUtf8("null");
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    private JsonWriter open(int v, int v1, char c) throws IOException {
        if(this.stackSize == this.flattenStackSize && (this.scopes[this.stackSize - 1] == v || this.scopes[this.stackSize - 1] == v1)) {
            this.flattenStackSize = ~this.flattenStackSize;
            return this;
        }
        this.beforeValue();
        this.checkStack();
        this.pushScope(v);
        this.pathIndices[this.stackSize - 1] = 0;
        this.sink.writeByte(((int)c));
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public void setIndent(String s) {
        super.setIndent(s);
        this.separator = s.isEmpty() ? ":" : ": ";
    }

    static void string(BufferedSink bufferedSink0, String s) throws IOException {
        String s2;
        String[] arr_s = JsonUtf8Writer.REPLACEMENT_CHARS;
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

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(double f) throws IOException {
        if(!this.lenient && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + f);
        }
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(Double.toString(f));
        }
        this.writeDeferredName();
        this.beforeValue();
        this.sink.writeUtf8(Double.toString(f));
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
        this.writeDeferredName();
        this.beforeValue();
        this.sink.writeUtf8(Long.toString(v));
        int v1 = this.stackSize - 1;
        ++this.pathIndices[v1];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(Boolean boolean0) throws IOException {
        return boolean0 == null ? this.nullValue() : this.value(boolean0.booleanValue());
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(@Nullable Number number0) throws IOException {
        if(number0 == null) {
            return this.nullValue();
        }
        String s = number0.toString();
        if(!this.lenient && (s.equals("-Infinity") || s.equals("Infinity") || s.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number0);
        }
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(s);
        }
        this.writeDeferredName();
        this.beforeValue();
        this.sink.writeUtf8(s);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(String s) throws IOException {
        if(s == null) {
            return this.nullValue();
        }
        if(this.promoteValueToName) {
            this.promoteValueToName = false;
            return this.name(s);
        }
        this.writeDeferredName();
        this.beforeValue();
        JsonUtf8Writer.string(this.sink, s);
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public JsonWriter value(boolean z) throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.writeDeferredName();
        this.beforeValue();
        this.sink.writeUtf8((z ? "true" : "false"));
        int v = this.stackSize - 1;
        ++this.pathIndices[v];
        return this;
    }

    @Override  // com.squareup.moshi.JsonWriter
    public BufferedSink valueSink() throws IOException {
        if(this.promoteValueToName) {
            throw new IllegalStateException("BufferedSink cannot be used as a map key in JSON at path " + this.getPath());
        }
        this.writeDeferredName();
        this.beforeValue();
        this.pushScope(9);
        return Okio.buffer(new Object() {
            @Override  // okio.Sink
            public void close() {
                if(JsonUtf8Writer.this.peekScope() != 9) {
                    throw new AssertionError();
                }
                --JsonUtf8Writer.this.stackSize;
                int[] arr_v = JsonUtf8Writer.this.pathIndices;
                int v = JsonUtf8Writer.this.stackSize - 1;
                ++arr_v[v];
            }

            @Override  // okio.Sink
            public void flush() throws IOException {
                JsonUtf8Writer.this.sink.flush();
            }

            @Override  // okio.Sink
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override  // okio.Sink
            public void write(Buffer buffer0, long v) throws IOException {
                JsonUtf8Writer.this.sink.write(buffer0, v);
            }
        });
    }

    private void writeDeferredName() throws IOException {
        if(this.deferredName != null) {
            this.beforeName();
            JsonUtf8Writer.string(this.sink, this.deferredName);
            this.deferredName = null;
        }
    }
}

