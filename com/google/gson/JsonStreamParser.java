package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser implements Iterator {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(Reader reader0) {
        JsonReader jsonReader0 = new JsonReader(reader0);
        this.parser = jsonReader0;
        jsonReader0.setLenient(true);
        this.lock = new Object();
    }

    public JsonStreamParser(String s) {
        this(new StringReader(s));
    }

    @Override
    public boolean hasNext() {
        synchronized(this.lock) {
            try {
                if(this.parser.peek() == JsonToken.END_DOCUMENT) {
                    return false;
                }
                else {
                    return true;
                }
                return z;
            }
            catch(MalformedJsonException malformedJsonException0) {
                throw new JsonSyntaxException(malformedJsonException0);
            }
            catch(IOException iOException0) {
                throw new JsonIOException(iOException0);
            }
        }
    }

    public JsonElement next() throws JsonParseException {
        if(this.hasNext()) {
            try {
                return Streams.parse(this.parser);
            }
            catch(StackOverflowError stackOverflowError0) {
                throw new JsonParseException("Failed parsing JSON source to Json", stackOverflowError0);
            }
            catch(OutOfMemoryError outOfMemoryError0) {
                throw new JsonParseException("Failed parsing JSON source to Json", outOfMemoryError0);
            }
            catch(JsonParseException jsonParseException0) {
                if(jsonParseException0.getCause() instanceof EOFException) {
                    jsonParseException0 = new NoSuchElementException();
                }
                throw jsonParseException0;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Object next() {
        return this.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

