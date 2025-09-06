package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson {
    static class FutureTypeAdapter extends TypeAdapter {
        private TypeAdapter delegate;

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            TypeAdapter typeAdapter0 = this.delegate;
            if(typeAdapter0 == null) {
                throw new IllegalStateException();
            }
            return typeAdapter0.read(jsonReader0);
        }

        public void setDelegate(TypeAdapter typeAdapter0) {
            if(this.delegate != null) {
                throw new AssertionError();
            }
            this.delegate = typeAdapter0;
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            TypeAdapter typeAdapter0 = this.delegate;
            if(typeAdapter0 == null) {
                throw new IllegalStateException();
            }
            typeAdapter0.write(jsonWriter0, object0);
        }
    }

    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final boolean DEFAULT_LENIENT = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}\'\n";
    private static final TypeToken NULL_KEY_SURROGATE;
    final List builderFactories;
    final List builderHierarchyFactories;
    private final ThreadLocal calls;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final boolean lenient;
    final LongSerializationPolicy longSerializationPolicy;
    final ToNumberStrategy numberToNumberStrategy;
    final ToNumberStrategy objectToNumberStrategy;
    final boolean prettyPrinting;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final int timeStyle;
    private final Map typeTokenCache;

    static {
        Gson.NULL_KEY_SURROGATE = TypeToken.get(Object.class);
    }

    public Gson() {
        this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.EMPTY_MAP, false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, null, 2, 2, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, ToNumberPolicy.DOUBLE, ToNumberPolicy.LAZILY_PARSED_NUMBER);
    }

    Gson(Excluder excluder0, FieldNamingStrategy fieldNamingStrategy0, Map map0, boolean z, boolean z1, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, LongSerializationPolicy longSerializationPolicy0, String s, int v, int v1, List list0, List list1, List list2, ToNumberStrategy toNumberStrategy0, ToNumberStrategy toNumberStrategy1) {
        this.calls = new ThreadLocal();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = excluder0;
        this.fieldNamingStrategy = fieldNamingStrategy0;
        this.instanceCreators = map0;
        ConstructorConstructor constructorConstructor0 = new ConstructorConstructor(map0);
        this.constructorConstructor = constructorConstructor0;
        this.serializeNulls = z;
        this.complexMapKeySerialization = z1;
        this.generateNonExecutableJson = z2;
        this.htmlSafe = z3;
        this.prettyPrinting = z4;
        this.lenient = z5;
        this.serializeSpecialFloatingPointValues = z6;
        this.longSerializationPolicy = longSerializationPolicy0;
        this.datePattern = s;
        this.dateStyle = v;
        this.timeStyle = v1;
        this.builderFactories = list0;
        this.builderHierarchyFactories = list1;
        this.objectToNumberStrategy = toNumberStrategy0;
        this.numberToNumberStrategy = toNumberStrategy1;
        ArrayList arrayList0 = new ArrayList();
        arrayList0.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList0.add(ObjectTypeAdapter.getFactory(toNumberStrategy0));
        arrayList0.add(excluder0);
        arrayList0.addAll(list2);
        arrayList0.add(TypeAdapters.STRING_FACTORY);
        arrayList0.add(TypeAdapters.INTEGER_FACTORY);
        arrayList0.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList0.add(TypeAdapters.BYTE_FACTORY);
        arrayList0.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter typeAdapter0 = Gson.longAdapter(longSerializationPolicy0);
        arrayList0.add(TypeAdapters.newFactory(Long.TYPE, Long.class, typeAdapter0));
        Class class0 = Double.TYPE;
        TypeAdapter typeAdapter1 = this.doubleAdapter(z6);
        arrayList0.add(TypeAdapters.newFactory(class0, Double.class, typeAdapter1));
        Class class1 = Float.TYPE;
        TypeAdapter typeAdapter2 = this.floatAdapter(z6);
        arrayList0.add(TypeAdapters.newFactory(class1, Float.class, typeAdapter2));
        arrayList0.add(NumberTypeAdapter.getFactory(toNumberStrategy1));
        arrayList0.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        arrayList0.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        TypeAdapter typeAdapter3 = Gson.atomicLongAdapter(typeAdapter0);
        arrayList0.add(TypeAdapters.newFactory(AtomicLong.class, typeAdapter3));
        TypeAdapter typeAdapter4 = Gson.atomicLongArrayAdapter(typeAdapter0);
        arrayList0.add(TypeAdapters.newFactory(AtomicLongArray.class, typeAdapter4));
        arrayList0.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        arrayList0.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList0.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList0.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList0.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        arrayList0.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        arrayList0.add(TypeAdapters.URL_FACTORY);
        arrayList0.add(TypeAdapters.URI_FACTORY);
        arrayList0.add(TypeAdapters.UUID_FACTORY);
        arrayList0.add(TypeAdapters.CURRENCY_FACTORY);
        arrayList0.add(TypeAdapters.LOCALE_FACTORY);
        arrayList0.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList0.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList0.add(DateTypeAdapter.FACTORY);
        arrayList0.add(TypeAdapters.CALENDAR_FACTORY);
        if(SqlTypesSupport.SUPPORTS_SQL_TYPES) {
            arrayList0.add(SqlTypesSupport.TIME_FACTORY);
            arrayList0.add(SqlTypesSupport.DATE_FACTORY);
            arrayList0.add(SqlTypesSupport.TIMESTAMP_FACTORY);
        }
        arrayList0.add(ArrayTypeAdapter.FACTORY);
        arrayList0.add(TypeAdapters.CLASS_FACTORY);
        arrayList0.add(new CollectionTypeAdapterFactory(constructorConstructor0));
        arrayList0.add(new MapTypeAdapterFactory(constructorConstructor0, z1));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory0 = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor0);
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory0;
        arrayList0.add(jsonAdapterAnnotationTypeAdapterFactory0);
        arrayList0.add(TypeAdapters.ENUM_FACTORY);
        arrayList0.add(new ReflectiveTypeAdapterFactory(constructorConstructor0, fieldNamingStrategy0, excluder0, jsonAdapterAnnotationTypeAdapterFactory0));
        this.factories = Collections.unmodifiableList(arrayList0);
    }

    private static void assertFullConsumption(Object object0, JsonReader jsonReader0) {
        if(object0 == null) {
            return;
        }
        else {
            try {
                if(jsonReader0.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
                return;
            }
            catch(MalformedJsonException malformedJsonException0) {
            }
            catch(IOException iOException0) {
                throw new JsonIOException(iOException0);
            }
        }
        throw new JsonSyntaxException(malformedJsonException0);
    }

    private static TypeAdapter atomicLongAdapter(TypeAdapter typeAdapter0) {
        return new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public AtomicLong read(JsonReader jsonReader0) throws IOException {
                return new AtomicLong(((Number)typeAdapter0.read(jsonReader0)).longValue());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((AtomicLong)object0));
            }

            public void write(JsonWriter jsonWriter0, AtomicLong atomicLong0) throws IOException {
                Long long0 = atomicLong0.get();
                typeAdapter0.write(jsonWriter0, long0);
            }
        }.nullSafe();
    }

    private static TypeAdapter atomicLongArrayAdapter(TypeAdapter typeAdapter0) {
        return new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public AtomicLongArray read(JsonReader jsonReader0) throws IOException {
                ArrayList arrayList0 = new ArrayList();
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    arrayList0.add(((Number)typeAdapter0.read(jsonReader0)).longValue());
                }
                jsonReader0.endArray();
                int v = arrayList0.size();
                AtomicLongArray atomicLongArray0 = new AtomicLongArray(v);
                for(int v1 = 0; v1 < v; ++v1) {
                    atomicLongArray0.set(v1, ((long)(((Long)arrayList0.get(v1)))));
                }
                return atomicLongArray0;
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((AtomicLongArray)object0));
            }

            public void write(JsonWriter jsonWriter0, AtomicLongArray atomicLongArray0) throws IOException {
                jsonWriter0.beginArray();
                int v = atomicLongArray0.length();
                for(int v1 = 0; v1 < v; ++v1) {
                    Long long0 = atomicLongArray0.get(v1);
                    typeAdapter0.write(jsonWriter0, long0);
                }
                jsonWriter0.endArray();
            }
        }.nullSafe();
    }

    static void checkValidFloatingPoint(double f) {
        if(Double.isNaN(f) || Double.isInfinite(f)) {
            throw new IllegalArgumentException(f + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter doubleAdapter(boolean z) {
        return z ? TypeAdapters.DOUBLE : new TypeAdapter() {
            public Double read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return jsonReader0.nextDouble();
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                if(number0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint(number0.doubleValue());
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
    }

    @Deprecated
    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    private TypeAdapter floatAdapter(boolean z) {
        return z ? TypeAdapters.FLOAT : new TypeAdapter() {
            public Float read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return (float)jsonReader0.nextDouble();
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                if(number0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                Gson.checkValidFloatingPoint(number0.floatValue());
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
    }

    public Object fromJson(JsonElement jsonElement0, Class class0) throws JsonSyntaxException {
        return Primitives.wrap(class0).cast(this.fromJson(jsonElement0, class0));
    }

    public Object fromJson(JsonElement jsonElement0, Type type0) throws JsonSyntaxException {
        return jsonElement0 == null ? null : this.fromJson(new JsonTreeReader(jsonElement0), type0);
    }

    public Object fromJson(JsonReader jsonReader0, Type type0) throws JsonIOException, JsonSyntaxException {
        boolean z = jsonReader0.isLenient();
        boolean z1 = true;
        jsonReader0.setLenient(true);
        try {
            jsonReader0.peek();
            z1 = false;
            return this.getAdapter(TypeToken.get(type0)).read(jsonReader0);
        }
        catch(EOFException eOFException0) {
            if(!z1) {
                throw new JsonSyntaxException(eOFException0);
            }
            return null;
        }
        catch(IllegalStateException illegalStateException0) {
            throw new JsonSyntaxException(illegalStateException0);
        }
        catch(IOException iOException0) {
            throw new JsonSyntaxException(iOException0);
        }
        catch(AssertionError assertionError0) {
            AssertionError assertionError1 = new AssertionError("AssertionError (GSON 2.8.9): " + assertionError0.getMessage());
            assertionError1.initCause(assertionError0);
            throw assertionError1;
        }
        finally {
            jsonReader0.setLenient(z);
        }
    }

    public Object fromJson(Reader reader0, Class class0) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReader0 = this.newJsonReader(reader0);
        Object object0 = this.fromJson(jsonReader0, class0);
        Gson.assertFullConsumption(object0, jsonReader0);
        return Primitives.wrap(class0).cast(object0);
    }

    public Object fromJson(Reader reader0, Type type0) throws JsonIOException, JsonSyntaxException {
        JsonReader jsonReader0 = this.newJsonReader(reader0);
        Object object0 = this.fromJson(jsonReader0, type0);
        Gson.assertFullConsumption(object0, jsonReader0);
        return object0;
    }

    public Object fromJson(String s, Class class0) throws JsonSyntaxException {
        return Primitives.wrap(class0).cast(this.fromJson(s, class0));
    }

    public Object fromJson(String s, Type type0) throws JsonSyntaxException {
        return s == null ? null : this.fromJson(new StringReader(s), type0);
    }

    public TypeAdapter getAdapter(TypeToken typeToken0) {
        boolean z;
        TypeAdapter typeAdapter0 = (TypeAdapter)this.typeTokenCache.get((typeToken0 == null ? Gson.NULL_KEY_SURROGATE : typeToken0));
        if(typeAdapter0 != null) {
            return typeAdapter0;
        }
        Map map0 = (Map)this.calls.get();
        if(map0 == null) {
            map0 = new HashMap();
            this.calls.set(map0);
            z = true;
        }
        else {
            z = false;
        }
        TypeAdapter typeAdapter1 = (FutureTypeAdapter)map0.get(typeToken0);
        if(typeAdapter1 != null) {
            return typeAdapter1;
        }
        try {
            FutureTypeAdapter gson$FutureTypeAdapter0 = new FutureTypeAdapter();
            map0.put(typeToken0, gson$FutureTypeAdapter0);
            for(Object object0: this.factories) {
                TypeAdapter typeAdapter2 = ((TypeAdapterFactory)object0).create(this, typeToken0);
                if(typeAdapter2 != null) {
                    gson$FutureTypeAdapter0.setDelegate(typeAdapter2);
                    this.typeTokenCache.put(typeToken0, typeAdapter2);
                    return typeAdapter2;
                }
                if(false) {
                    break;
                }
            }
        }
        finally {
            map0.remove(typeToken0);
            if(z) {
                this.calls.remove();
            }
        }
        throw new IllegalArgumentException("GSON (2.8.9) cannot handle " + typeToken0);
    }

    public TypeAdapter getAdapter(Class class0) {
        return this.getAdapter(TypeToken.get(class0));
    }

    public TypeAdapter getDelegateAdapter(TypeAdapterFactory typeAdapterFactory0, TypeToken typeToken0) {
        if(!this.factories.contains(typeAdapterFactory0)) {
            typeAdapterFactory0 = this.jsonAdapterFactory;
        }
        boolean z = false;
        for(Object object0: this.factories) {
            TypeAdapterFactory typeAdapterFactory1 = (TypeAdapterFactory)object0;
            if(z) {
                TypeAdapter typeAdapter0 = typeAdapterFactory1.create(this, typeToken0);
                if(typeAdapter0 != null) {
                    return typeAdapter0;
                }
                if(false) {
                    break;
                }
            }
            else {
                if(typeAdapterFactory1 != typeAdapterFactory0) {
                    continue;
                }
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + typeToken0);
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    private static TypeAdapter longAdapter(LongSerializationPolicy longSerializationPolicy0) {
        return longSerializationPolicy0 == LongSerializationPolicy.DEFAULT ? TypeAdapters.LONG : new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return jsonReader0.nextLong();
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                if(number0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                jsonWriter0.value(number0.toString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
    }

    public GsonBuilder newBuilder() {
        return new GsonBuilder(this);
    }

    public JsonReader newJsonReader(Reader reader0) {
        JsonReader jsonReader0 = new JsonReader(reader0);
        jsonReader0.setLenient(this.lenient);
        return jsonReader0;
    }

    public JsonWriter newJsonWriter(Writer writer0) throws IOException {
        if(this.generateNonExecutableJson) {
            writer0.write(")]}\'\n");
        }
        JsonWriter jsonWriter0 = new JsonWriter(writer0);
        if(this.prettyPrinting) {
            jsonWriter0.setIndent("  ");
        }
        jsonWriter0.setSerializeNulls(this.serializeNulls);
        return jsonWriter0;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public String toJson(JsonElement jsonElement0) [...] // 潜在的解密器

    // 去混淆评级： 低(30)
    public String toJson(Object object0) {
        return object0 == null ? "null" : this.toJson(object0, object0.getClass());
    }

    public String toJson(Object object0, Type type0) {
        StringWriter stringWriter0 = new StringWriter();
        this.toJson(object0, type0, stringWriter0);
        return stringWriter0.toString();
    }

    public void toJson(JsonElement jsonElement0, JsonWriter jsonWriter0) throws JsonIOException {
        boolean z = jsonWriter0.isLenient();
        jsonWriter0.setLenient(true);
        boolean z1 = jsonWriter0.isHtmlSafe();
        jsonWriter0.setHtmlSafe(this.htmlSafe);
        boolean z2 = jsonWriter0.getSerializeNulls();
        jsonWriter0.setSerializeNulls(this.serializeNulls);
        try {
            Streams.write(jsonElement0, jsonWriter0);
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
        catch(AssertionError assertionError0) {
            AssertionError assertionError1 = new AssertionError("AssertionError (GSON 2.8.9): " + assertionError0.getMessage());
            assertionError1.initCause(assertionError0);
            throw assertionError1;
        }
        finally {
            jsonWriter0.setLenient(z);
            jsonWriter0.setHtmlSafe(z1);
            jsonWriter0.setSerializeNulls(z2);
        }
    }

    public void toJson(JsonElement jsonElement0, Appendable appendable0) throws JsonIOException {
        try {
            this.toJson(jsonElement0, this.newJsonWriter(Streams.writerForAppendable(appendable0)));
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
    }

    public void toJson(Object object0, Appendable appendable0) throws JsonIOException {
        if(object0 != null) {
            this.toJson(object0, object0.getClass(), appendable0);
            return;
        }
        this.toJson(JsonNull.INSTANCE, appendable0);
    }

    public void toJson(Object object0, Type type0, JsonWriter jsonWriter0) throws JsonIOException {
        TypeAdapter typeAdapter0 = this.getAdapter(TypeToken.get(type0));
        boolean z = jsonWriter0.isLenient();
        jsonWriter0.setLenient(true);
        boolean z1 = jsonWriter0.isHtmlSafe();
        jsonWriter0.setHtmlSafe(this.htmlSafe);
        boolean z2 = jsonWriter0.getSerializeNulls();
        jsonWriter0.setSerializeNulls(this.serializeNulls);
        try {
            typeAdapter0.write(jsonWriter0, object0);
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
        catch(AssertionError assertionError0) {
            AssertionError assertionError1 = new AssertionError("AssertionError (GSON 2.8.9): " + assertionError0.getMessage());
            assertionError1.initCause(assertionError0);
            throw assertionError1;
        }
        finally {
            jsonWriter0.setLenient(z);
            jsonWriter0.setHtmlSafe(z1);
            jsonWriter0.setSerializeNulls(z2);
        }
    }

    public void toJson(Object object0, Type type0, Appendable appendable0) throws JsonIOException {
        try {
            this.toJson(object0, type0, this.newJsonWriter(Streams.writerForAppendable(appendable0)));
        }
        catch(IOException iOException0) {
            throw new JsonIOException(iOException0);
        }
    }

    public JsonElement toJsonTree(Object object0) {
        return object0 == null ? JsonNull.INSTANCE : this.toJsonTree(object0, object0.getClass());
    }

    public JsonElement toJsonTree(Object object0, Type type0) {
        JsonTreeWriter jsonTreeWriter0 = new JsonTreeWriter();
        this.toJson(object0, type0, jsonTreeWriter0);
        return jsonTreeWriter0.get();
    }

    @Override
    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}

