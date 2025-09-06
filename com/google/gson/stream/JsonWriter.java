package com.google.gson.stream;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class JsonWriter implements Closeable, Flushable, AutoCloseable {
    private static final String[] HTML_SAFE_REPLACEMENT_CHARS;
    private static final String[] REPLACEMENT_CHARS;
    private String deferredName;
    private boolean htmlSafe;
    private String indent;
    private boolean lenient;
    private final Writer out;
    private String separator;
    private boolean serializeNulls;
    private int[] stack;
    private int stackSize;

    static {
        JsonWriter.REPLACEMENT_CHARS = new String[0x80];
        for(int v = 0; v <= 0x1F; ++v) {
            JsonWriter.REPLACEMENT_CHARS[v] = "\\u001f";
        }
        JsonWriter.REPLACEMENT_CHARS[34] = "\\\"";
        JsonWriter.REPLACEMENT_CHARS[92] = "\\\\";
        JsonWriter.REPLACEMENT_CHARS[9] = "\\t";
        JsonWriter.REPLACEMENT_CHARS[8] = "\\b";
        JsonWriter.REPLACEMENT_CHARS[10] = "\\n";
        JsonWriter.REPLACEMENT_CHARS[13] = "\\r";
        JsonWriter.REPLACEMENT_CHARS[12] = "\\f";
        String[] arr_s = (String[])JsonWriter.REPLACEMENT_CHARS.clone();
        JsonWriter.HTML_SAFE_REPLACEMENT_CHARS = arr_s;
        arr_s[60] = "\\u003c";
        arr_s[62] = "\\u003e";
        arr_s[38] = "\\u0026";
        arr_s[61] = "\\u003d";
        arr_s[39] = "\\u0027";
    }

    public JsonWriter(Writer writer0) {
        this.stack = new int[0x20];
        this.stackSize = 0;
        this.push(6);
        this.separator = ":";
        this.serializeNulls = true;
        if(writer0 == null) {
            throw new NullPointerException("out == null");
        }
        this.out = writer0;
    }

    private void beforeName() throws IOException {
        int v = this.peek();
        if(v == 5) {
            this.out.write(44);
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
        switch(this.peek()) {
            case 1: {
                this.replaceTop(2);
                this.newline();
                return;
            }
            case 2: {
                this.out.append(',');
                this.newline();
                return;
            }
            case 4: {
                this.out.append(this.separator);
                this.replaceTop(5);
                return;
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
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
        }
        this.replaceTop(7);
    }

    public JsonWriter beginArray() throws IOException {
        this.writeDeferredName();
        return this.open(1, '[');
    }

    public JsonWriter beginObject() throws IOException {
        this.writeDeferredName();
        return this.open(3, '{');
    }

    private JsonWriter close(int v, int v1, char c) throws IOException {
        int v2 = this.peek();
        if(v2 != v1 && v2 != v) {
            throw new IllegalStateException("Nesting problem.");
        }
        if(this.deferredName != null) {
            throw new IllegalStateException("Dangling name: " + this.deferredName);
        }
        --this.stackSize;
        if(v2 == v1) {
            this.newline();
        }
        this.out.write(((int)c));
        return this;
    }

    @Override
    public void close() throws IOException {
        this.out.close();
        if(this.stackSize > 1 || this.stackSize == 1 && this.stack[0] != 7) {
            throw new IOException("Incomplete document");
        }
        this.stackSize = 0;
    }

    public JsonWriter endArray() throws IOException {
        return this.close(1, 2, ']');
    }

    public JsonWriter endObject() throws IOException {
        return this.close(3, 5, '}');
    }

    @Override
    public void flush() throws IOException {
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }

    public final boolean getSerializeNulls() {
        return this.serializeNulls;
    }

    public final boolean isHtmlSafe() {
        return this.htmlSafe;
    }

    public boolean isLenient() {
        return this.lenient;
    }

    public JsonWriter jsonValue(String s) throws IOException {
        if(s == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.out.append(s);
        return this;
    }

    public JsonWriter name(String s) throws IOException {
        if(s == null) {
            throw new NullPointerException("name == null");
        }
        if(this.deferredName != null) {
            throw new IllegalStateException();
        }
        if(this.stackSize == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.deferredName = s;
        return this;
    }

    private void newline() throws IOException {
        if(this.indent != null) {
            this.out.write(10);
            int v = this.stackSize;
            for(int v1 = 1; v1 < v; ++v1) {
                this.out.write(this.indent);
            }
        }
    }

    public JsonWriter nullValue() throws IOException {
        if(this.deferredName != null) {
            if(this.serializeNulls) {
                this.writeDeferredName();
                this.beforeValue();
                this.out.write("null");
                return this;
            }
            this.deferredName = null;
            return this;
        }
        this.beforeValue();
        this.out.write("null");
        return this;
    }

    private JsonWriter open(int v, char c) throws IOException {
        this.beforeValue();
        this.push(v);
        this.out.write(((int)c));
        return this;
    }

    private int peek() {
        int v = this.stackSize;
        if(v == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.stack[v - 1];
    }

    private void push(int v) {
        int v1 = this.stackSize;
        int[] arr_v = this.stack;
        if(v1 == arr_v.length) {
            this.stack = Arrays.copyOf(arr_v, v1 * 2);
        }
        int v2 = this.stackSize;
        this.stackSize = v2 + 1;
        this.stack[v2] = v;
    }

    private void replaceTop(int v) {
        this.stack[this.stackSize - 1] = v;
    }

    public final void setHtmlSafe(boolean z) {
        this.htmlSafe = z;
    }

    public final void setIndent(String s) {
        if(s.length() == 0) {
            this.indent = null;
            this.separator = ":";
            return;
        }
        this.indent = s;
        this.separator = ": ";
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public final void setSerializeNulls(boolean z) {
        this.serializeNulls = z;
    }

    private void string(String s) throws IOException {
        String s2;
        String[] arr_s = this.htmlSafe ? JsonWriter.HTML_SAFE_REPLACEMENT_CHARS : JsonWriter.REPLACEMENT_CHARS;
        this.out.write(34);
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
                    this.out.write(s, v2, v1 - v2);
                }
                this.out.write(s2);
                v2 = v1 + 1;
            }
        label_21:
            ++v1;
        }
        if(v2 < v) {
            this.out.write(s, v2, v - v2);
        }
        this.out.write(34);
    }

    public JsonWriter value(double f) throws IOException {
        this.writeDeferredName();
        if(!this.lenient && (Double.isNaN(f) || Double.isInfinite(f))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + f);
        }
        this.beforeValue();
        this.out.append(Double.toString(f));
        return this;
    }

    public JsonWriter value(long v) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        this.out.write(Long.toString(v));
        return this;
    }

    public JsonWriter value(Boolean boolean0) throws IOException {
        if(boolean0 == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.out.write((boolean0.booleanValue() ? "true" : "false"));
        return this;
    }

    public JsonWriter value(Number number0) throws IOException {
        if(number0 == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        String s = number0.toString();
        if(!this.lenient && (s.equals("-Infinity") || s.equals("Infinity") || s.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number0);
        }
        this.beforeValue();
        this.out.append(s);
        return this;
    }

    public JsonWriter value(String s) throws IOException {
        if(s == null) {
            return this.nullValue();
        }
        this.writeDeferredName();
        this.beforeValue();
        this.string(s);
        return this;
    }

    public JsonWriter value(boolean z) throws IOException {
        this.writeDeferredName();
        this.beforeValue();
        this.out.write((z ? "true" : "false"));
        return this;
    }

    private void writeDeferredName() throws IOException {
        if(this.deferredName != null) {
            this.beforeName();
            this.string(this.deferredName);
            this.deferredName = null;
        }
    }
}

