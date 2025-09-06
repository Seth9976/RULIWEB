package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class StandardJsonAdapters {
    static final class EnumJsonAdapter extends JsonAdapter {
        private final Enum[] constants;
        private final Class enumType;
        private final String[] nameStrings;
        private final Options options;

        EnumJsonAdapter(Class class0) {
            this.enumType = class0;
            try {
                Enum[] arr_enum = (Enum[])class0.getEnumConstants();
                this.constants = arr_enum;
                this.nameStrings = new String[arr_enum.length];
                for(int v = 0; true; ++v) {
                    Enum[] arr_enum1 = this.constants;
                    if(v >= arr_enum1.length) {
                        break;
                    }
                    Enum enum0 = arr_enum1[v];
                    Json json0 = (Json)class0.getField(enum0.name()).getAnnotation(Json.class);
                    this.nameStrings[v] = json0 == null ? enum0.name() : json0.name();
                }
                this.options = Options.of(this.nameStrings);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                throw new AssertionError("Missing field in " + class0.getName(), noSuchFieldException0);
            }
        }

        public Enum fromJson(JsonReader jsonReader0) throws IOException {
            int v = jsonReader0.selectString(this.options);
            if(v != -1) {
                return this.constants[v];
            }
            String s = jsonReader0.nextString();
            throw new JsonDataException("Expected one of " + Arrays.asList(this.nameStrings) + " but was " + s + " at path " + "$");
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader0) throws IOException {
            return this.fromJson(jsonReader0);
        }

        public void toJson(JsonWriter jsonWriter0, Enum enum0) throws IOException {
            jsonWriter0.value(this.nameStrings[enum0.ordinal()]);
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
            this.toJson(jsonWriter0, ((Enum)object0));
        }

        @Override
        public String toString() {
            return "JsonAdapter(" + this.enumType.getName() + ")";
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter {
        private final JsonAdapter booleanAdapter;
        private final JsonAdapter doubleAdapter;
        private final JsonAdapter listJsonAdapter;
        private final JsonAdapter mapAdapter;
        private final Moshi moshi;
        private final JsonAdapter stringAdapter;

        ObjectJsonAdapter(Moshi moshi0) {
            this.moshi = moshi0;
            this.listJsonAdapter = moshi0.adapter(List.class);
            this.mapAdapter = moshi0.adapter(Map.class);
            this.stringAdapter = moshi0.adapter(String.class);
            this.doubleAdapter = moshi0.adapter(Double.class);
            this.booleanAdapter = moshi0.adapter(Boolean.class);
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader0) throws IOException {
            switch(com.squareup.moshi.StandardJsonAdapters.11.$SwitchMap$com$squareup$moshi$JsonReader$Token[jsonReader0.peek().ordinal()]) {
                case 1: {
                    return this.listJsonAdapter.fromJson(jsonReader0);
                }
                case 2: {
                    return this.mapAdapter.fromJson(jsonReader0);
                }
                case 3: {
                    return this.stringAdapter.fromJson(jsonReader0);
                }
                case 4: {
                    return this.doubleAdapter.fromJson(jsonReader0);
                }
                case 5: {
                    return this.booleanAdapter.fromJson(jsonReader0);
                }
                case 6: {
                    return jsonReader0.nextNull();
                }
                default: {
                    throw new IllegalStateException("Expected a value but was " + jsonReader0.peek() + " at path " + "$");
                }
            }
        }

        @Override  // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
            Class class0 = object0.getClass();
            if(class0 == Object.class) {
                jsonWriter0.beginObject();
                jsonWriter0.endObject();
                return;
            }
            this.moshi.adapter(this.toJsonType(class0), Util.NO_ANNOTATIONS).toJson(jsonWriter0, object0);
        }

        private Class toJsonType(Class class0) {
            if(Map.class.isAssignableFrom(class0)) {
                return Map.class;
            }
            return Collection.class.isAssignableFrom(class0) ? Collection.class : class0;
        }

        @Override
        public String toString() {
            return "JsonAdapter(Object)";
        }
    }

    static final JsonAdapter BOOLEAN_JSON_ADAPTER = null;
    static final JsonAdapter BYTE_JSON_ADAPTER = null;
    static final JsonAdapter CHARACTER_JSON_ADAPTER = null;
    static final JsonAdapter DOUBLE_JSON_ADAPTER = null;
    private static final String ERROR_FORMAT = "Expected %s but was %s at path %s";
    public static final Factory FACTORY;
    static final JsonAdapter FLOAT_JSON_ADAPTER;
    static final JsonAdapter INTEGER_JSON_ADAPTER;
    static final JsonAdapter LONG_JSON_ADAPTER;
    static final JsonAdapter SHORT_JSON_ADAPTER;
    static final JsonAdapter STRING_JSON_ADAPTER;

    static {
        StandardJsonAdapters.FACTORY = new Factory() {
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                if(!set0.isEmpty()) {
                    return null;
                }
                if(type0 == Boolean.TYPE) {
                    return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER;
                }
                if(type0 == Byte.TYPE) {
                    return StandardJsonAdapters.BYTE_JSON_ADAPTER;
                }
                if(type0 == Character.TYPE) {
                    return StandardJsonAdapters.CHARACTER_JSON_ADAPTER;
                }
                if(type0 == Double.TYPE) {
                    return StandardJsonAdapters.DOUBLE_JSON_ADAPTER;
                }
                if(type0 == Float.TYPE) {
                    return StandardJsonAdapters.FLOAT_JSON_ADAPTER;
                }
                if(type0 == Integer.TYPE) {
                    return StandardJsonAdapters.INTEGER_JSON_ADAPTER;
                }
                if(type0 == Long.TYPE) {
                    return StandardJsonAdapters.LONG_JSON_ADAPTER;
                }
                if(type0 == Short.TYPE) {
                    return StandardJsonAdapters.SHORT_JSON_ADAPTER;
                }
                if(type0 == Boolean.class) {
                    return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Byte.class) {
                    return StandardJsonAdapters.BYTE_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Character.class) {
                    return StandardJsonAdapters.CHARACTER_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Double.class) {
                    return StandardJsonAdapters.DOUBLE_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Float.class) {
                    return StandardJsonAdapters.FLOAT_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Integer.class) {
                    return StandardJsonAdapters.INTEGER_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Long.class) {
                    return StandardJsonAdapters.LONG_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Short.class) {
                    return StandardJsonAdapters.SHORT_JSON_ADAPTER.nullSafe();
                }
                if(type0 == String.class) {
                    return StandardJsonAdapters.STRING_JSON_ADAPTER.nullSafe();
                }
                if(type0 == Object.class) {
                    return new ObjectJsonAdapter(moshi0).nullSafe();
                }
                Class class0 = Types.getRawType(type0);
                JsonAdapter jsonAdapter0 = Util.generatedAdapter(moshi0, type0, class0);
                if(jsonAdapter0 != null) {
                    return jsonAdapter0;
                }
                return class0.isEnum() ? new EnumJsonAdapter(class0).nullSafe() : null;
            }
        };
        StandardJsonAdapters.BOOLEAN_JSON_ADAPTER = new JsonAdapter() {
            public Boolean fromJson(JsonReader jsonReader0) throws IOException {
                return Boolean.valueOf(jsonReader0.nextBoolean());
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Boolean boolean0) throws IOException {
                jsonWriter0.value(boolean0.booleanValue());
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Boolean)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Boolean)";
            }
        };
        StandardJsonAdapters.BYTE_JSON_ADAPTER = new JsonAdapter() {
            public Byte fromJson(JsonReader jsonReader0) throws IOException {
                return (byte)StandardJsonAdapters.rangeCheckNextInt(jsonReader0, "a byte", 0xFFFFFF80, 0xFF);
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Byte byte0) throws IOException {
                jsonWriter0.value(((long)(byte0.intValue() & 0xFF)));
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Byte)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Byte)";
            }
        };
        StandardJsonAdapters.CHARACTER_JSON_ADAPTER = new JsonAdapter() {
            public Character fromJson(JsonReader jsonReader0) throws IOException {
                String s = jsonReader0.nextString();
                if(s.length() > 1) {
                    throw new JsonDataException(String.format("Expected %s but was %s at path %s", "a char", "\"" + s + '\"', "$"));
                }
                return Character.valueOf(s.charAt(0));
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Character character0) throws IOException {
                jsonWriter0.value(character0.toString());
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Character)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Character)";
            }
        };
        StandardJsonAdapters.DOUBLE_JSON_ADAPTER = new JsonAdapter() {
            public Double fromJson(JsonReader jsonReader0) throws IOException {
                return jsonReader0.nextDouble();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Double double0) throws IOException {
                jsonWriter0.value(((double)double0));
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Double)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Double)";
            }
        };
        StandardJsonAdapters.FLOAT_JSON_ADAPTER = new JsonAdapter() {
            public Float fromJson(JsonReader jsonReader0) throws IOException {
                float f = (float)jsonReader0.nextDouble();
                if(!jsonReader0.isLenient() && Float.isInfinite(f)) {
                    throw new JsonDataException("JSON forbids NaN and infinities: " + f + " at path " + "$");
                }
                return f;
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Float float0) throws IOException {
                float0.getClass();
                jsonWriter0.value(float0);
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Float)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Float)";
            }
        };
        StandardJsonAdapters.INTEGER_JSON_ADAPTER = new JsonAdapter() {
            public Integer fromJson(JsonReader jsonReader0) throws IOException {
                return jsonReader0.nextInt();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Integer integer0) throws IOException {
                jsonWriter0.value(((long)(((int)integer0))));
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Integer)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Integer)";
            }
        };
        StandardJsonAdapters.LONG_JSON_ADAPTER = new JsonAdapter() {
            public Long fromJson(JsonReader jsonReader0) throws IOException {
                return jsonReader0.nextLong();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public void toJson(JsonWriter jsonWriter0, Long long0) throws IOException {
                jsonWriter0.value(((long)long0));
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Long)object0));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Long)";
            }
        };
        StandardJsonAdapters.SHORT_JSON_ADAPTER = new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public Short fromJson(JsonReader jsonReader0) throws IOException {
                return (short)StandardJsonAdapters.rangeCheckNextInt(jsonReader0, "a short", 0xFFFF8000, 0x7FFF);
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((Short)object0));
            }

            public void toJson(JsonWriter jsonWriter0, Short short0) throws IOException {
                jsonWriter0.value(((long)short0.intValue()));
            }

            @Override
            public String toString() {
                return "JsonAdapter(Short)";
            }
        };
        StandardJsonAdapters.STRING_JSON_ADAPTER = new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                return this.fromJson(jsonReader0);
            }

            public String fromJson(JsonReader jsonReader0) throws IOException {
                return jsonReader0.nextString();
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
                this.toJson(jsonWriter0, ((String)object0));
            }

            public void toJson(JsonWriter jsonWriter0, String s) throws IOException {
                jsonWriter0.value(s);
            }

            @Override
            public String toString() {
                return "JsonAdapter(String)";
            }
        };
    }

    static int rangeCheckNextInt(JsonReader jsonReader0, String s, int v, int v1) throws IOException {
        int v2 = jsonReader0.nextInt();
        if(v2 < v || v2 > v1) {
            throw new JsonDataException(String.format("Expected %s but was %s at path %s", s, v2, "$"));
        }
        return v2;
    }
}

