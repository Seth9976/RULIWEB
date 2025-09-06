package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

class SqlTimestampTypeAdapter extends TypeAdapter {
    static final TypeAdapterFactory FACTORY;
    private final TypeAdapter dateTypeAdapter;

    static {
        SqlTimestampTypeAdapter.FACTORY = new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == Timestamp.class ? new SqlTimestampTypeAdapter(gson0.getAdapter(Date.class), null) : null;
            }
        };
    }

    private SqlTimestampTypeAdapter(TypeAdapter typeAdapter0) {
        this.dateTypeAdapter = typeAdapter0;
    }

    SqlTimestampTypeAdapter(TypeAdapter typeAdapter0, com.google.gson.internal.sql.SqlTimestampTypeAdapter.1 sqlTimestampTypeAdapter$10) {
        this(typeAdapter0);
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        return this.read(jsonReader0);
    }

    public Timestamp read(JsonReader jsonReader0) throws IOException {
        Date date0 = (Date)this.dateTypeAdapter.read(jsonReader0);
        return date0 == null ? null : new Timestamp(date0.getTime());
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.write(jsonWriter0, ((Timestamp)object0));
    }

    public void write(JsonWriter jsonWriter0, Timestamp timestamp0) throws IOException {
        this.dateTypeAdapter.write(jsonWriter0, timestamp0);
    }
}

