package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter {
    public static final TypeAdapterFactory FACTORY;
    private final List dateFormats;

    static {
        DateTypeAdapter.FACTORY = new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == Date.class ? new DateTypeAdapter() : null;
            }
        };
    }

    public DateTypeAdapter() {
        ArrayList arrayList0 = new ArrayList();
        this.dateFormats = arrayList0;
        arrayList0.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            arrayList0.add(DateFormat.getDateTimeInstance(2, 2));
        }
    }

    private Date deserializeToDate(String s) {
        synchronized(this) {
            Iterator iterator0 = this.dateFormats.iterator();
        label_3:
            if(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                DateFormat dateFormat0 = (DateFormat)object0;
                try {
                    return dateFormat0.parse(s);
                }
                catch(ParseException unused_ex) {
                    goto label_3;
                }
            }
            try {
                return ISO8601Utils.parse(s, new ParsePosition(0));
            }
            catch(ParseException parseException0) {
                throw new JsonSyntaxException(s, parseException0);
            }
        }
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        return this.read(jsonReader0);
    }

    public Date read(JsonReader jsonReader0) throws IOException {
        if(jsonReader0.peek() == JsonToken.NULL) {
            jsonReader0.nextNull();
            return null;
        }
        return this.deserializeToDate(jsonReader0.nextString());
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.write(jsonWriter0, ((Date)object0));
    }

    public void write(JsonWriter jsonWriter0, Date date0) throws IOException {
        synchronized(this) {
            if(date0 == null) {
                jsonWriter0.nullValue();
                return;
            }
            jsonWriter0.value(((DateFormat)this.dateFormats.get(0)).format(date0));
        }
    }
}

