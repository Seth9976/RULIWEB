package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class Streams {
    static final class AppendableWriter extends Writer implements AutoCloseable {
        static class CurrentWrite implements CharSequence {
            char[] chars;

            @Override
            public char charAt(int v) {
                return this.chars[v];
            }

            @Override
            public int length() {
                return this.chars.length;
            }

            @Override
            public CharSequence subSequence(int v, int v1) {
                return new String(this.chars, v, v1 - v);
            }
        }

        private final Appendable appendable;
        private final CurrentWrite currentWrite;

        AppendableWriter(Appendable appendable0) {
            this.currentWrite = new CurrentWrite();
            this.appendable = appendable0;
        }

        @Override
        public void close() {
        }

        @Override
        public void flush() {
        }

        @Override
        public void write(int v) throws IOException {
            this.appendable.append(((char)v));
        }

        @Override
        public void write(char[] arr_c, int v, int v1) throws IOException {
            this.currentWrite.chars = arr_c;
            this.appendable.append(this.currentWrite, v, v1 + v);
        }
    }

    private Streams() {
        throw new UnsupportedOperationException();
    }

    public static JsonElement parse(JsonReader jsonReader0) throws JsonParseException {
        try {
            boolean z = true;
            jsonReader0.peek();
            z = false;
            return (JsonElement)TypeAdapters.JSON_ELEMENT.read(jsonReader0);
        }
        catch(EOFException eOFException0) {
            if(!z) {
                throw new JsonSyntaxException(eOFException0);
            }
            return JsonNull.INSTANCE;
        }
        catch(MalformedJsonException malformedJsonException0) {
            throw new JsonSyntaxException(malformedJsonException0);
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
        catch(NumberFormatException numberFormatException0) {
            throw new JsonSyntaxException(numberFormatException0);
        }
    }

    public static void write(JsonElement jsonElement0, JsonWriter jsonWriter0) throws IOException {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter0, jsonElement0);
    }

    public static Writer writerForAppendable(Appendable appendable0) {
        return appendable0 instanceof Writer ? ((Writer)appendable0) : new AppendableWriter(appendable0);
    }
}

