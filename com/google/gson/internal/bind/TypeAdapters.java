package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class TypeAdapters {
    static final class EnumTypeAdapter extends TypeAdapter {
        private final Map constantToName;
        private final Map nameToConstant;

        public EnumTypeAdapter(Class class0) {
            this.nameToConstant = new HashMap();
            this.constantToName = new HashMap();
            try {
                Field[] arr_field = class0.getDeclaredFields();
                for(int v = 0; v < arr_field.length; ++v) {
                    Field field0 = arr_field[v];
                    if(field0.isEnumConstant()) {
                        AccessController.doPrivileged(new PrivilegedAction() {
                            @Override
                            public Object run() {
                                return this.run();
                            }

                            public Void run() {
                                field0.setAccessible(true);
                                return null;
                            }
                        });
                        Enum enum0 = (Enum)field0.get(null);
                        String s = enum0.name();
                        SerializedName serializedName0 = (SerializedName)field0.getAnnotation(SerializedName.class);
                        if(serializedName0 != null) {
                            s = serializedName0.value();
                            String[] arr_s = serializedName0.alternate();
                            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                                this.nameToConstant.put(arr_s[v1], enum0);
                            }
                        }
                        this.nameToConstant.put(s, enum0);
                        this.constantToName.put(enum0, s);
                    }
                }
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new AssertionError(illegalAccessException0);
            }
        }

        public Enum read(JsonReader jsonReader0) throws IOException {
            if(jsonReader0.peek() == JsonToken.NULL) {
                jsonReader0.nextNull();
                return null;
            }
            String s = jsonReader0.nextString();
            return (Enum)this.nameToConstant.get(s);
        }

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            return this.read(jsonReader0);
        }

        public void write(JsonWriter jsonWriter0, Enum enum0) throws IOException {
            jsonWriter0.value((enum0 == null ? null : ((String)this.constantToName.get(enum0))));
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            this.write(jsonWriter0, ((Enum)object0));
        }
    }

    public static final TypeAdapter ATOMIC_BOOLEAN;
    public static final TypeAdapterFactory ATOMIC_BOOLEAN_FACTORY;
    public static final TypeAdapter ATOMIC_INTEGER;
    public static final TypeAdapter ATOMIC_INTEGER_ARRAY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_ARRAY_FACTORY;
    public static final TypeAdapterFactory ATOMIC_INTEGER_FACTORY;
    public static final TypeAdapter BIG_DECIMAL;
    public static final TypeAdapter BIG_INTEGER;
    public static final TypeAdapter BIT_SET;
    public static final TypeAdapterFactory BIT_SET_FACTORY;
    public static final TypeAdapter BOOLEAN;
    public static final TypeAdapter BOOLEAN_AS_STRING;
    public static final TypeAdapterFactory BOOLEAN_FACTORY;
    public static final TypeAdapter BYTE;
    public static final TypeAdapterFactory BYTE_FACTORY;
    public static final TypeAdapter CALENDAR;
    public static final TypeAdapterFactory CALENDAR_FACTORY;
    public static final TypeAdapter CHARACTER;
    public static final TypeAdapterFactory CHARACTER_FACTORY;
    public static final TypeAdapter CLASS;
    public static final TypeAdapterFactory CLASS_FACTORY;
    public static final TypeAdapter CURRENCY;
    public static final TypeAdapterFactory CURRENCY_FACTORY;
    public static final TypeAdapter DOUBLE;
    public static final TypeAdapterFactory ENUM_FACTORY;
    public static final TypeAdapter FLOAT;
    public static final TypeAdapter INET_ADDRESS;
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY;
    public static final TypeAdapter INTEGER;
    public static final TypeAdapterFactory INTEGER_FACTORY;
    public static final TypeAdapter JSON_ELEMENT;
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY;
    public static final TypeAdapter LOCALE;
    public static final TypeAdapterFactory LOCALE_FACTORY;
    public static final TypeAdapter LONG;
    public static final TypeAdapter SHORT;
    public static final TypeAdapterFactory SHORT_FACTORY;
    public static final TypeAdapter STRING;
    public static final TypeAdapter STRING_BUFFER;
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY;
    public static final TypeAdapter STRING_BUILDER;
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY;
    public static final TypeAdapterFactory STRING_FACTORY;
    public static final TypeAdapter URI;
    public static final TypeAdapterFactory URI_FACTORY;
    public static final TypeAdapter URL;
    public static final TypeAdapterFactory URL_FACTORY;
    public static final TypeAdapter UUID;
    public static final TypeAdapterFactory UUID_FACTORY;

    static {
        TypeAdapter typeAdapter0 = new TypeAdapter() {
            public Class read(JsonReader jsonReader0) throws IOException {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Class class0) throws IOException {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + class0.getName() + ". Forgot to register a type adapter?");
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Class)object0));
            }
        }.nullSafe();
        TypeAdapters.CLASS = typeAdapter0;
        TypeAdapters.CLASS_FACTORY = TypeAdapters.newFactory(Class.class, typeAdapter0);
        TypeAdapter typeAdapter1 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public BitSet read(JsonReader jsonReader0) throws IOException {
                BitSet bitSet0 = new BitSet();
                jsonReader0.beginArray();
                JsonToken jsonToken0 = jsonReader0.peek();
                int v = 0;
                while(jsonToken0 != JsonToken.END_ARRAY) {
                    boolean z = true;
                    switch(com.google.gson.internal.bind.TypeAdapters.34.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken0.ordinal()]) {
                        case 1: {
                            if(jsonReader0.nextInt() == 0) {
                                z = false;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            z = jsonReader0.nextBoolean();
                            break;
                        }
                        case 3: {
                            String s = jsonReader0.nextString();
                            try {
                                if(Integer.parseInt(s) == 0) {
                                    z = false;
                                    break;
                                }
                                break;
                            }
                            catch(NumberFormatException unused_ex) {
                                throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + s);
                            }
                            z = false;
                            break;
                        }
                        default: {
                            throw new JsonSyntaxException("Invalid bitset value type: " + jsonToken0);
                        }
                    }
                    if(z) {
                        bitSet0.set(v);
                    }
                    ++v;
                    jsonToken0 = jsonReader0.peek();
                }
                jsonReader0.endArray();
                return bitSet0;
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((BitSet)object0));
            }

            public void write(JsonWriter jsonWriter0, BitSet bitSet0) throws IOException {
                jsonWriter0.beginArray();
                int v = bitSet0.length();
                for(int v1 = 0; v1 < v; ++v1) {
                    jsonWriter0.value(((long)bitSet0.get(v1)));
                }
                jsonWriter0.endArray();
            }
        }.nullSafe();
        TypeAdapters.BIT_SET = typeAdapter1;
        TypeAdapters.BIT_SET_FACTORY = TypeAdapters.newFactory(BitSet.class, typeAdapter1);
        com.google.gson.internal.bind.TypeAdapters.3 typeAdapters$30 = new TypeAdapter() {
            public Boolean read(JsonReader jsonReader0) throws IOException {
                JsonToken jsonToken0 = jsonReader0.peek();
                if(jsonToken0 == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return jsonToken0 == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(jsonReader0.nextString())) : Boolean.valueOf(jsonReader0.nextBoolean());
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Boolean boolean0) throws IOException {
                jsonWriter0.value(boolean0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Boolean)object0));
            }
        };
        TypeAdapters.BOOLEAN = typeAdapters$30;
        TypeAdapters.BOOLEAN_AS_STRING = new TypeAdapter() {
            public Boolean read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return Boolean.valueOf(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Boolean boolean0) throws IOException {
                jsonWriter0.value((boolean0 == null ? "null" : boolean0.toString()));
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Boolean)object0));
            }
        };
        TypeAdapters.BOOLEAN_FACTORY = TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, typeAdapters$30);
        com.google.gson.internal.bind.TypeAdapters.5 typeAdapters$50 = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return (byte)jsonReader0.nextInt();
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        TypeAdapters.BYTE = typeAdapters$50;
        TypeAdapters.BYTE_FACTORY = TypeAdapters.newFactory(Byte.TYPE, Byte.class, typeAdapters$50);
        com.google.gson.internal.bind.TypeAdapters.6 typeAdapters$60 = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return (short)jsonReader0.nextInt();
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        TypeAdapters.SHORT = typeAdapters$60;
        TypeAdapters.SHORT_FACTORY = TypeAdapters.newFactory(Short.TYPE, Short.class, typeAdapters$60);
        com.google.gson.internal.bind.TypeAdapters.7 typeAdapters$70 = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return jsonReader0.nextInt();
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        TypeAdapters.INTEGER = typeAdapters$70;
        TypeAdapters.INTEGER_FACTORY = TypeAdapters.newFactory(Integer.TYPE, Integer.class, typeAdapters$70);
        TypeAdapter typeAdapter2 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public AtomicInteger read(JsonReader jsonReader0) throws IOException {
                try {
                    return new AtomicInteger(jsonReader0.nextInt());
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((AtomicInteger)object0));
            }

            public void write(JsonWriter jsonWriter0, AtomicInteger atomicInteger0) throws IOException {
                jsonWriter0.value(((long)atomicInteger0.get()));
            }
        }.nullSafe();
        TypeAdapters.ATOMIC_INTEGER = typeAdapter2;
        TypeAdapters.ATOMIC_INTEGER_FACTORY = TypeAdapters.newFactory(AtomicInteger.class, typeAdapter2);
        TypeAdapter typeAdapter3 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public AtomicBoolean read(JsonReader jsonReader0) throws IOException {
                return new AtomicBoolean(jsonReader0.nextBoolean());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((AtomicBoolean)object0));
            }

            public void write(JsonWriter jsonWriter0, AtomicBoolean atomicBoolean0) throws IOException {
                jsonWriter0.value(atomicBoolean0.get());
            }
        }.nullSafe();
        TypeAdapters.ATOMIC_BOOLEAN = typeAdapter3;
        TypeAdapters.ATOMIC_BOOLEAN_FACTORY = TypeAdapters.newFactory(AtomicBoolean.class, typeAdapter3);
        TypeAdapter typeAdapter4 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public AtomicIntegerArray read(JsonReader jsonReader0) throws IOException {
                ArrayList arrayList0 = new ArrayList();
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    try {
                        arrayList0.add(jsonReader0.nextInt());
                    }
                    catch(NumberFormatException numberFormatException0) {
                        throw new JsonSyntaxException(numberFormatException0);
                    }
                }
                jsonReader0.endArray();
                int v = arrayList0.size();
                AtomicIntegerArray atomicIntegerArray0 = new AtomicIntegerArray(v);
                for(int v1 = 0; v1 < v; ++v1) {
                    atomicIntegerArray0.set(v1, ((int)(((Integer)arrayList0.get(v1)))));
                }
                return atomicIntegerArray0;
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((AtomicIntegerArray)object0));
            }

            public void write(JsonWriter jsonWriter0, AtomicIntegerArray atomicIntegerArray0) throws IOException {
                jsonWriter0.beginArray();
                int v = atomicIntegerArray0.length();
                for(int v1 = 0; v1 < v; ++v1) {
                    jsonWriter0.value(((long)atomicIntegerArray0.get(v1)));
                }
                jsonWriter0.endArray();
            }
        }.nullSafe();
        TypeAdapters.ATOMIC_INTEGER_ARRAY = typeAdapter4;
        TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY = TypeAdapters.newFactory(AtomicIntegerArray.class, typeAdapter4);
        TypeAdapters.LONG = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return jsonReader0.nextLong();
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Number number0) throws IOException {
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        TypeAdapters.FLOAT = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
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
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        TypeAdapters.DOUBLE = new TypeAdapter() {
            public Number read(JsonReader jsonReader0) throws IOException {
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
                jsonWriter0.value(number0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Number)object0));
            }
        };
        com.google.gson.internal.bind.TypeAdapters.14 typeAdapters$140 = new TypeAdapter() {
            public Character read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                String s = jsonReader0.nextString();
                if(s.length() != 1) {
                    throw new JsonSyntaxException("Expecting character, got: " + s);
                }
                return Character.valueOf(s.charAt(0));
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, Character character0) throws IOException {
                jsonWriter0.value((character0 == null ? null : String.valueOf(character0)));
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Character)object0));
            }
        };
        TypeAdapters.CHARACTER = typeAdapters$140;
        TypeAdapters.CHARACTER_FACTORY = TypeAdapters.newFactory(Character.TYPE, Character.class, typeAdapters$140);
        com.google.gson.internal.bind.TypeAdapters.15 typeAdapters$150 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public String read(JsonReader jsonReader0) throws IOException {
                JsonToken jsonToken0 = jsonReader0.peek();
                if(jsonToken0 == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return jsonToken0 == JsonToken.BOOLEAN ? Boolean.toString(jsonReader0.nextBoolean()) : jsonReader0.nextString();
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((String)object0));
            }

            public void write(JsonWriter jsonWriter0, String s) throws IOException {
                jsonWriter0.value(s);
            }
        };
        TypeAdapters.STRING = typeAdapters$150;
        TypeAdapters.BIG_DECIMAL = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public BigDecimal read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return new BigDecimal(jsonReader0.nextString());
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((BigDecimal)object0));
            }

            public void write(JsonWriter jsonWriter0, BigDecimal bigDecimal0) throws IOException {
                jsonWriter0.value(bigDecimal0);
            }
        };
        TypeAdapters.BIG_INTEGER = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public BigInteger read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return new BigInteger(jsonReader0.nextString());
                }
                catch(NumberFormatException numberFormatException0) {
                    throw new JsonSyntaxException(numberFormatException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((BigInteger)object0));
            }

            public void write(JsonWriter jsonWriter0, BigInteger bigInteger0) throws IOException {
                jsonWriter0.value(bigInteger0);
            }
        };
        TypeAdapters.STRING_FACTORY = TypeAdapters.newFactory(String.class, typeAdapters$150);
        com.google.gson.internal.bind.TypeAdapters.18 typeAdapters$180 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public StringBuilder read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return new StringBuilder(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((StringBuilder)object0));
            }

            public void write(JsonWriter jsonWriter0, StringBuilder stringBuilder0) throws IOException {
                jsonWriter0.value((stringBuilder0 == null ? null : stringBuilder0.toString()));
            }
        };
        TypeAdapters.STRING_BUILDER = typeAdapters$180;
        TypeAdapters.STRING_BUILDER_FACTORY = TypeAdapters.newFactory(StringBuilder.class, typeAdapters$180);
        com.google.gson.internal.bind.TypeAdapters.19 typeAdapters$190 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public StringBuffer read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return new StringBuffer(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((StringBuffer)object0));
            }

            public void write(JsonWriter jsonWriter0, StringBuffer stringBuffer0) throws IOException {
                jsonWriter0.value((stringBuffer0 == null ? null : stringBuffer0.toString()));
            }
        };
        TypeAdapters.STRING_BUFFER = typeAdapters$190;
        TypeAdapters.STRING_BUFFER_FACTORY = TypeAdapters.newFactory(StringBuffer.class, typeAdapters$190);
        com.google.gson.internal.bind.TypeAdapters.20 typeAdapters$200 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public URL read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                String s = jsonReader0.nextString();
                return "null".equals(s) ? null : new URL(s);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((URL)object0));
            }

            public void write(JsonWriter jsonWriter0, URL uRL0) throws IOException {
                jsonWriter0.value((uRL0 == null ? null : uRL0.toExternalForm()));
            }
        };
        TypeAdapters.URL = typeAdapters$200;
        TypeAdapters.URL_FACTORY = TypeAdapters.newFactory(URL.class, typeAdapters$200);
        com.google.gson.internal.bind.TypeAdapters.21 typeAdapters$210 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public URI read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    String s = jsonReader0.nextString();
                    return "null".equals(s) ? null : new URI(s);
                }
                catch(URISyntaxException uRISyntaxException0) {
                    throw new JsonIOException(uRISyntaxException0);
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((URI)object0));
            }

            public void write(JsonWriter jsonWriter0, URI uRI0) throws IOException {
                jsonWriter0.value((uRI0 == null ? null : uRI0.toASCIIString()));
            }
        };
        TypeAdapters.URI = typeAdapters$210;
        TypeAdapters.URI_FACTORY = TypeAdapters.newFactory(URI.class, typeAdapters$210);
        com.google.gson.internal.bind.TypeAdapters.22 typeAdapters$220 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public InetAddress read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return InetAddress.getByName(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((InetAddress)object0));
            }

            public void write(JsonWriter jsonWriter0, InetAddress inetAddress0) throws IOException {
                jsonWriter0.value((inetAddress0 == null ? null : inetAddress0.getHostAddress()));
            }
        };
        TypeAdapters.INET_ADDRESS = typeAdapters$220;
        TypeAdapters.INET_ADDRESS_FACTORY = TypeAdapters.newTypeHierarchyFactory(InetAddress.class, typeAdapters$220);
        com.google.gson.internal.bind.TypeAdapters.23 typeAdapters$230 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public UUID read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                return UUID.fromString(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((UUID)object0));
            }

            public void write(JsonWriter jsonWriter0, UUID uUID0) throws IOException {
                jsonWriter0.value((uUID0 == null ? null : uUID0.toString()));
            }
        };
        TypeAdapters.UUID = typeAdapters$230;
        TypeAdapters.UUID_FACTORY = TypeAdapters.newFactory(UUID.class, typeAdapters$230);
        TypeAdapter typeAdapter5 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public Currency read(JsonReader jsonReader0) throws IOException {
                return Currency.getInstance(jsonReader0.nextString());
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Currency)object0));
            }

            public void write(JsonWriter jsonWriter0, Currency currency0) throws IOException {
                jsonWriter0.value(currency0.getCurrencyCode());
            }
        }.nullSafe();
        TypeAdapters.CURRENCY = typeAdapter5;
        TypeAdapters.CURRENCY_FACTORY = TypeAdapters.newFactory(Currency.class, typeAdapter5);
        com.google.gson.internal.bind.TypeAdapters.25 typeAdapters$250 = new TypeAdapter() {
            private static final String DAY_OF_MONTH = "dayOfMonth";
            private static final String HOUR_OF_DAY = "hourOfDay";
            private static final String MINUTE = "minute";
            private static final String MONTH = "month";
            private static final String SECOND = "second";
            private static final String YEAR = "year";

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public Calendar read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                jsonReader0.beginObject();
                int v = 0;
                int v1 = 0;
                int v2 = 0;
                int v3 = 0;
                int v4 = 0;
                int v5 = 0;
                while(jsonReader0.peek() != JsonToken.END_OBJECT) {
                    String s = jsonReader0.nextName();
                    int v6 = jsonReader0.nextInt();
                    if("year".equals(s)) {
                        v = v6;
                    }
                    else if("month".equals(s)) {
                        v1 = v6;
                    }
                    else if("dayOfMonth".equals(s)) {
                        v2 = v6;
                    }
                    else if("hourOfDay".equals(s)) {
                        v3 = v6;
                    }
                    else if("minute".equals(s)) {
                        v4 = v6;
                    }
                    else if("second".equals(s)) {
                        v5 = v6;
                    }
                }
                jsonReader0.endObject();
                return new GregorianCalendar(v, v1, v2, v3, v4, v5);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Calendar)object0));
            }

            public void write(JsonWriter jsonWriter0, Calendar calendar0) throws IOException {
                if(calendar0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                jsonWriter0.beginObject();
                jsonWriter0.name("year");
                jsonWriter0.value(((long)calendar0.get(1)));
                jsonWriter0.name("month");
                jsonWriter0.value(((long)calendar0.get(2)));
                jsonWriter0.name("dayOfMonth");
                jsonWriter0.value(((long)calendar0.get(5)));
                jsonWriter0.name("hourOfDay");
                jsonWriter0.value(((long)calendar0.get(11)));
                jsonWriter0.name("minute");
                jsonWriter0.value(((long)calendar0.get(12)));
                jsonWriter0.name("second");
                jsonWriter0.value(((long)calendar0.get(13)));
                jsonWriter0.endObject();
            }
        };
        TypeAdapters.CALENDAR = typeAdapters$250;
        TypeAdapters.CALENDAR_FACTORY = TypeAdapters.newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, typeAdapters$250);
        com.google.gson.internal.bind.TypeAdapters.26 typeAdapters$260 = new TypeAdapter() {
            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public Locale read(JsonReader jsonReader0) throws IOException {
                String s = null;
                if(jsonReader0.peek() == JsonToken.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                StringTokenizer stringTokenizer0 = new StringTokenizer(jsonReader0.nextString(), "_");
                String s1 = stringTokenizer0.hasMoreElements() ? stringTokenizer0.nextToken() : null;
                String s2 = stringTokenizer0.hasMoreElements() ? stringTokenizer0.nextToken() : null;
                if(stringTokenizer0.hasMoreElements()) {
                    s = stringTokenizer0.nextToken();
                }
                if(s2 == null && s == null) {
                    return new Locale(s1);
                }
                return s == null ? new Locale(s1, s2) : new Locale(s1, s2, s);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((Locale)object0));
            }

            public void write(JsonWriter jsonWriter0, Locale locale0) throws IOException {
                jsonWriter0.value((locale0 == null ? null : locale0.toString()));
            }
        };
        TypeAdapters.LOCALE = typeAdapters$260;
        TypeAdapters.LOCALE_FACTORY = TypeAdapters.newFactory(Locale.class, typeAdapters$260);
        com.google.gson.internal.bind.TypeAdapters.27 typeAdapters$270 = new TypeAdapter() {
            public JsonElement read(JsonReader jsonReader0) throws IOException {
                if(jsonReader0 instanceof JsonTreeReader) {
                    return ((JsonTreeReader)jsonReader0).nextJsonElement();
                }
                switch(com.google.gson.internal.bind.TypeAdapters.34.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader0.peek().ordinal()]) {
                    case 1: {
                        return new JsonPrimitive(new LazilyParsedNumber(jsonReader0.nextString()));
                    }
                    case 2: {
                        return new JsonPrimitive(Boolean.valueOf(jsonReader0.nextBoolean()));
                    }
                    case 3: {
                        return new JsonPrimitive(jsonReader0.nextString());
                    }
                    case 4: {
                        jsonReader0.nextNull();
                        return JsonNull.INSTANCE;
                    }
                    case 5: {
                        JsonElement jsonElement0 = new JsonArray();
                        jsonReader0.beginArray();
                        while(jsonReader0.hasNext()) {
                            ((JsonArray)jsonElement0).add(this.read(jsonReader0));
                        }
                        jsonReader0.endArray();
                        return jsonElement0;
                    }
                    case 6: {
                        JsonElement jsonElement1 = new JsonObject();
                        jsonReader0.beginObject();
                        while(jsonReader0.hasNext()) {
                            ((JsonObject)jsonElement1).add(jsonReader0.nextName(), this.read(jsonReader0));
                        }
                        jsonReader0.endObject();
                        return jsonElement1;
                    }
                    default: {
                        throw new IllegalArgumentException();
                    }
                }
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                return this.read(jsonReader0);
            }

            public void write(JsonWriter jsonWriter0, JsonElement jsonElement0) throws IOException {
                if(jsonElement0 != null) {
                    if(jsonElement0.isJsonPrimitive()) {
                        JsonPrimitive jsonPrimitive0 = jsonElement0.getAsJsonPrimitive();
                        if(jsonPrimitive0.isNumber()) {
                            jsonWriter0.value(jsonPrimitive0.getAsNumber());
                            return;
                        }
                        if(jsonPrimitive0.isBoolean()) {
                            jsonWriter0.value(jsonPrimitive0.getAsBoolean());
                            return;
                        }
                        jsonWriter0.value(jsonPrimitive0.getAsString());
                        return;
                    }
                    if(jsonElement0.isJsonArray()) {
                        jsonWriter0.beginArray();
                        for(Object object0: jsonElement0.getAsJsonArray()) {
                            this.write(jsonWriter0, ((JsonElement)object0));
                        }
                        jsonWriter0.endArray();
                        return;
                    }
                    if(!jsonElement0.isJsonObject()) {
                        throw new IllegalArgumentException("Couldn\'t write " + jsonElement0.getClass());
                    }
                    jsonWriter0.beginObject();
                    for(Object object1: jsonElement0.getAsJsonObject().entrySet()) {
                        jsonWriter0.name(((String)((Map.Entry)object1).getKey()));
                        this.write(jsonWriter0, ((JsonElement)((Map.Entry)object1).getValue()));
                    }
                    jsonWriter0.endObject();
                    return;
                }
                jsonWriter0.nullValue();
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.write(jsonWriter0, ((JsonElement)object0));
            }
        };
        TypeAdapters.JSON_ELEMENT = typeAdapters$270;
        TypeAdapters.JSON_ELEMENT_FACTORY = TypeAdapters.newTypeHierarchyFactory(JsonElement.class, typeAdapters$270);
        TypeAdapters.ENUM_FACTORY = new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                Class class0 = typeToken0.getRawType();
                if(Enum.class.isAssignableFrom(class0) && class0 != Enum.class) {
                    if(!class0.isEnum()) {
                        class0 = class0.getSuperclass();
                    }
                    return new EnumTypeAdapter(class0);
                }
                return null;
            }
        };
    }

    private TypeAdapters() {
        throw new UnsupportedOperationException();
    }

    public static TypeAdapterFactory newFactory(TypeToken typeToken0, TypeAdapter typeAdapter0) {
        return new TypeAdapterFactory() {
            // 去混淆评级： 低(20)
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.equals(typeToken0) ? typeAdapter0 : null;
            }
        };
    }

    public static TypeAdapterFactory newFactory(Class class0, TypeAdapter typeAdapter0) {
        return new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == class0 ? typeAdapter0 : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + class0.getName() + ",adapter=" + typeAdapter0 + "]";
            }
        };
    }

    public static TypeAdapterFactory newFactory(Class class0, Class class1, TypeAdapter typeAdapter0) {
        return new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                Class class0 = typeToken0.getRawType();
                return class0 == class0 || class0 == class1 ? typeAdapter0 : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + class1.getName() + "+" + class0.getName() + ",adapter=" + typeAdapter0 + "]";
            }
        };
    }

    public static TypeAdapterFactory newFactoryForMultipleTypes(Class class0, Class class1, TypeAdapter typeAdapter0) {
        return new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                Class class0 = typeToken0.getRawType();
                return class0 == class0 || class0 == class1 ? typeAdapter0 : null;
            }

            @Override
            public String toString() {
                return "Factory[type=" + class0.getName() + "+" + class1.getName() + ",adapter=" + typeAdapter0 + "]";
            }
        };
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class class0, TypeAdapter typeAdapter0) {
        return new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                Class class0 = typeToken0.getRawType();
                return !class0.isAssignableFrom(class0) ? null : new TypeAdapter() {
                    @Override  // com.google.gson.TypeAdapter
                    public Object read(JsonReader jsonReader0) throws IOException {
                        Object object0 = com.google.gson.internal.bind.TypeAdapters.33.this.val$typeAdapter.read(jsonReader0);
                        if(object0 != null && !class0.isInstance(object0)) {
                            throw new JsonSyntaxException("Expected a " + class0.getName() + " but was " + object0.getClass().getName());
                        }
                        return object0;
                    }

                    @Override  // com.google.gson.TypeAdapter
                    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                        com.google.gson.internal.bind.TypeAdapters.33.this.val$typeAdapter.write(jsonWriter0, object0);
                    }
                };
            }

            @Override
            public String toString() {
                return "Factory[typeHierarchy=" + class0.getName() + ",adapter=" + typeAdapter0 + "]";
            }
        };
    }
}

