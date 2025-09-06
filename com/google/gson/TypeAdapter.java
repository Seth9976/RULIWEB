package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter {
    public final Object fromJson(Reader reader0) throws IOException {
        return this.read(new JsonReader(reader0));
    }

    public final Object fromJson(String s) throws IOException {
        return this.fromJson(new StringReader(s));
    }

    public final Object fromJsonTree(JsonElement jsonElement0) {
        try {
            return this.read(new JsonTreeReader(jsonElement0));
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
    }

    public final TypeAdapter nullSafe() {
        return new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return TypeAdapter.this.read(jsonReader0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                if(object0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                TypeAdapter.this.write(jsonWriter0, object0);
            }
        };
    }

    public abstract Object read(JsonReader arg1) throws IOException;

    public final String toJson(Object object0) {
        StringWriter stringWriter0 = new StringWriter();
        try {
            this.toJson(stringWriter0, object0);
            return stringWriter0.toString();
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
    }

    public final void toJson(Writer writer0, Object object0) throws IOException {
        this.write(new JsonWriter(writer0), object0);
    }

    public final JsonElement toJsonTree(Object object0) {
        try {
            JsonTreeWriter jsonTreeWriter0 = new JsonTreeWriter();
            this.write(jsonTreeWriter0, object0);
            return jsonTreeWriter0.get();
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
    }

    public abstract void write(JsonWriter arg1, Object arg2) throws IOException;
}

