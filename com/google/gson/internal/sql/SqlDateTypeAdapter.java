package com.google.gson.internal.sql;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import jeb.synthetic.FIN;

final class SqlDateTypeAdapter extends TypeAdapter {
    static final TypeAdapterFactory FACTORY;
    private final DateFormat format;

    static {
        SqlDateTypeAdapter.FACTORY = new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == Date.class ? new SqlDateTypeAdapter(null) : null;
            }
        };
    }

    private SqlDateTypeAdapter() {
        this.format = new SimpleDateFormat("MMM d, yyyy");
    }

    SqlDateTypeAdapter(com.google.gson.internal.sql.SqlDateTypeAdapter.1 sqlDateTypeAdapter$10) {
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        return this.read(jsonReader0);
    }

    public Date read(JsonReader jsonReader0) throws IOException {
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        if(jsonReader0.peek() == JsonToken.NULL) {
            jsonReader0.nextNull();
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(this);
            FIN.finallyCodeEnd$NT(v);
            return null;
        }
        try {
            String s = jsonReader0.nextString();
            Date date0 = new Date(this.format.parse(s).getTime());
            FIN.finallyExec$NT(v);
            return date0;
        }
        catch(ParseException parseException0) {
            FIN.finallyExec$NT(v);
            throw new JsonSyntaxException(parseException0);
        }
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.write(jsonWriter0, ((Date)object0));
    }

    public void write(JsonWriter jsonWriter0, Date date0) throws IOException {
        synchronized(this) {
            jsonWriter0.value((date0 == null ? null : this.format.format(date0)));
        }
    }
}

