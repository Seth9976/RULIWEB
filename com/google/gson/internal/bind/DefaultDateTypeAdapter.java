package com.google.gson.internal.bind;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DefaultDateTypeAdapter extends TypeAdapter {
    public static abstract class DateType {
        public static final DateType DATE;
        private final Class dateClass;

        static {
            DateType.DATE = new DateType(Date.class) {
                @Override  // com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType
                protected Date deserialize(Date date0) {
                    return date0;
                }
            };
        }

        protected DateType(Class class0) {
            this.dateClass = class0;
        }

        public final TypeAdapterFactory createAdapterFactory(int v) {
            return this.createFactory(new DefaultDateTypeAdapter(this, v, null));
        }

        public final TypeAdapterFactory createAdapterFactory(int v, int v1) {
            return this.createFactory(new DefaultDateTypeAdapter(this, v, v1, null));
        }

        public final TypeAdapterFactory createAdapterFactory(String s) {
            return this.createFactory(new DefaultDateTypeAdapter(this, s, null));
        }

        public final TypeAdapterFactory createDefaultsAdapterFactory() {
            return this.createFactory(new DefaultDateTypeAdapter(this, 2, 2, null));
        }

        private final TypeAdapterFactory createFactory(DefaultDateTypeAdapter defaultDateTypeAdapter0) {
            return TypeAdapters.newFactory(this.dateClass, defaultDateTypeAdapter0);
        }

        protected abstract Date deserialize(Date arg1);
    }

    private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
    private final List dateFormats;
    private final DateType dateType;

    private DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, int v) {
        ArrayList arrayList0 = new ArrayList();
        this.dateFormats = arrayList0;
        this.dateType = (DateType).Gson.Preconditions.checkNotNull(defaultDateTypeAdapter$DateType0);
        arrayList0.add(DateFormat.getDateInstance(v, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            arrayList0.add(DateFormat.getDateInstance(v));
        }
    }

    private DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, int v, int v1) {
        ArrayList arrayList0 = new ArrayList();
        this.dateFormats = arrayList0;
        this.dateType = (DateType).Gson.Preconditions.checkNotNull(defaultDateTypeAdapter$DateType0);
        arrayList0.add(DateFormat.getDateTimeInstance(v, v1, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            arrayList0.add(DateFormat.getDateTimeInstance(v, v1));
        }
    }

    DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, int v, int v1, com.google.gson.internal.bind.DefaultDateTypeAdapter.1 defaultDateTypeAdapter$10) {
        this(defaultDateTypeAdapter$DateType0, v, v1);
    }

    DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, int v, com.google.gson.internal.bind.DefaultDateTypeAdapter.1 defaultDateTypeAdapter$10) {
        this(defaultDateTypeAdapter$DateType0, v);
    }

    private DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, String s) {
        ArrayList arrayList0 = new ArrayList();
        this.dateFormats = arrayList0;
        this.dateType = (DateType).Gson.Preconditions.checkNotNull(defaultDateTypeAdapter$DateType0);
        arrayList0.add(new SimpleDateFormat(s, Locale.US));
        if(!Locale.getDefault().equals(Locale.US)) {
            arrayList0.add(new SimpleDateFormat(s));
        }
    }

    DefaultDateTypeAdapter(DateType defaultDateTypeAdapter$DateType0, String s, com.google.gson.internal.bind.DefaultDateTypeAdapter.1 defaultDateTypeAdapter$10) {
        this(defaultDateTypeAdapter$DateType0, s);
    }

    private Date deserializeToDate(String s) {
        synchronized(this.dateFormats) {
            Iterator iterator0 = this.dateFormats.iterator();
        label_4:
            if(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                DateFormat dateFormat0 = (DateFormat)object0;
                try {
                    return dateFormat0.parse(s);
                }
                catch(ParseException unused_ex) {
                    goto label_4;
                }
            }
        }
        try {
            return ISO8601Utils.parse(s, new ParsePosition(0));
        }
        catch(ParseException parseException0) {
            throw new JsonSyntaxException(s, parseException0);
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
        Date date0 = this.deserializeToDate(jsonReader0.nextString());
        return this.dateType.deserialize(date0);
    }

    @Override
    public String toString() {
        DateFormat dateFormat0 = (DateFormat)this.dateFormats.get(0);
        return dateFormat0 instanceof SimpleDateFormat ? "DefaultDateTypeAdapter(" + ((SimpleDateFormat)dateFormat0).toPattern() + ')' : "DefaultDateTypeAdapter(" + dateFormat0.getClass().getSimpleName() + ')';
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        this.write(jsonWriter0, ((Date)object0));
    }

    public void write(JsonWriter jsonWriter0, Date date0) throws IOException {
        if(date0 == null) {
            jsonWriter0.nullValue();
            return;
        }
        synchronized(this.dateFormats) {
            jsonWriter0.value(((DateFormat)this.dateFormats.get(0)).format(date0));
        }
    }

    class com.google.gson.internal.bind.DefaultDateTypeAdapter.1 {
    }

}

