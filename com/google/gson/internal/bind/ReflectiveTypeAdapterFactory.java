package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    public static final class Adapter extends TypeAdapter {
        private final Map boundFields;
        private final ObjectConstructor constructor;

        Adapter(ObjectConstructor objectConstructor0, Map map0) {
            this.constructor = objectConstructor0;
            this.boundFields = map0;
        }

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            if(jsonReader0.peek() == JsonToken.NULL) {
                jsonReader0.nextNull();
                return null;
            }
            Object object0 = this.constructor.construct();
            try {
                jsonReader0.beginObject();
                while(jsonReader0.hasNext()) {
                    String s = jsonReader0.nextName();
                    BoundField reflectiveTypeAdapterFactory$BoundField0 = (BoundField)this.boundFields.get(s);
                    if(reflectiveTypeAdapterFactory$BoundField0 != null && reflectiveTypeAdapterFactory$BoundField0.deserialized) {
                        reflectiveTypeAdapterFactory$BoundField0.read(jsonReader0, object0);
                    }
                    else {
                        jsonReader0.skipValue();
                    }
                }
            }
            catch(IllegalStateException illegalStateException0) {
                throw new JsonSyntaxException(illegalStateException0);
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new AssertionError(illegalAccessException0);
            }
            jsonReader0.endObject();
            return object0;
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            if(object0 == null) {
                jsonWriter0.nullValue();
                return;
            }
            jsonWriter0.beginObject();
            try {
                for(Object object1: this.boundFields.values()) {
                    BoundField reflectiveTypeAdapterFactory$BoundField0 = (BoundField)object1;
                    if(reflectiveTypeAdapterFactory$BoundField0.writeField(object0)) {
                        jsonWriter0.name(reflectiveTypeAdapterFactory$BoundField0.name);
                        reflectiveTypeAdapterFactory$BoundField0.write(jsonWriter0, object0);
                    }
                }
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new AssertionError(illegalAccessException0);
            }
            jsonWriter0.endObject();
        }
    }

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        protected BoundField(String s, boolean z, boolean z1) {
            this.name = s;
            this.serialized = z;
            this.deserialized = z1;
        }

        abstract void read(JsonReader arg1, Object arg2) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter arg1, Object arg2) throws IOException, IllegalAccessException;

        abstract boolean writeField(Object arg1) throws IOException, IllegalAccessException;
    }

    private final ReflectionAccessor accessor;
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor0, FieldNamingStrategy fieldNamingStrategy0, Excluder excluder0, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory0) {
        this.accessor = ReflectionAccessor.getInstance();
        this.constructorConstructor = constructorConstructor0;
        this.fieldNamingPolicy = fieldNamingStrategy0;
        this.excluder = excluder0;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory0;
    }

    @Override  // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
        Class class0 = typeToken0.getRawType();
        return !Object.class.isAssignableFrom(class0) ? null : new Adapter(this.constructorConstructor.get(typeToken0), this.getBoundFields(gson0, typeToken0, class0));
    }

    private BoundField createBoundField(Gson gson0, Field field0, String s, TypeToken typeToken0, boolean z, boolean z1) {
        boolean z2 = Primitives.isPrimitive(typeToken0.getRawType());
        JsonAdapter jsonAdapter0 = (JsonAdapter)field0.getAnnotation(JsonAdapter.class);
        TypeAdapter typeAdapter0 = jsonAdapter0 == null ? null : this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson0, typeToken0, jsonAdapter0);
        boolean z3 = typeAdapter0 != null;
        if(typeAdapter0 == null) {
            typeAdapter0 = gson0.getAdapter(typeToken0);
        }
        return new BoundField(s, z, z1) {
            @Override  // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField
            void read(JsonReader jsonReader0, Object object0) throws IOException, IllegalAccessException {
                Object object1 = typeAdapter0.read(jsonReader0);
                if(object1 == null && z2) {
                    return;
                }
                field0.set(object0, object1);
            }

            @Override  // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField
            void write(JsonWriter jsonWriter0, Object object0) throws IOException, IllegalAccessException {
                Object object1 = field0.get(object0);
                TypeAdapter typeAdapter0 = z3 ? typeAdapter0 : new TypeAdapterRuntimeTypeWrapper(gson0, typeAdapter0, typeToken0.getType());
                typeAdapter0.write(jsonWriter0, object1);
            }

            @Override  // com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField
            public boolean writeField(Object object0) throws IOException, IllegalAccessException {
                return this.serialized ? field0.get(object0) != object0 : false;
            }
        };
    }

    // 去混淆评级： 低(20)
    static boolean excludeField(Field field0, boolean z, Excluder excluder0) {
        return !excluder0.excludeClass(field0.getType(), z) && !excluder0.excludeField(field0, z);
    }

    public boolean excludeField(Field field0, boolean z) {
        return ReflectiveTypeAdapterFactory.excludeField(field0, z, this.excluder);
    }

    private Map getBoundFields(Gson gson0, TypeToken typeToken0, Class class0) {
        TypeToken typeToken2;
        Map map0 = new LinkedHashMap();
        if(!class0.isInterface()) {
            Type type0 = typeToken0.getType();
            TypeToken typeToken1 = typeToken0;
            for(Class class1 = class0; class1 != Object.class; class1 = typeToken1.getRawType()) {
                Field[] arr_field = class1.getDeclaredFields();
                int v = 0;
                int v1 = 0;
                while(v1 < arr_field.length) {
                    Field field0 = arr_field[v1];
                    boolean z = this.excludeField(field0, true);
                    boolean z1 = this.excludeField(field0, false);
                    if(z || z1) {
                        this.accessor.makeAccessible(field0);
                        Type type1 = .Gson.Types.resolve(typeToken1.getType(), class1, field0.getGenericType());
                        List list0 = this.getFieldNames(field0);
                        int v2 = list0.size();
                        BoundField reflectiveTypeAdapterFactory$BoundField0;
                        for(reflectiveTypeAdapterFactory$BoundField0 = null; v < v2; reflectiveTypeAdapterFactory$BoundField0 = reflectiveTypeAdapterFactory$BoundField1) {
                            String s = (String)list0.get(v);
                            if(v != 0) {
                                z = false;
                            }
                            BoundField reflectiveTypeAdapterFactory$BoundField1 = (BoundField)map0.put(s, this.createBoundField(gson0, field0, s, TypeToken.get(type1), z, z1));
                            if(reflectiveTypeAdapterFactory$BoundField0 != null) {
                                reflectiveTypeAdapterFactory$BoundField1 = reflectiveTypeAdapterFactory$BoundField0;
                            }
                            ++v;
                        }
                        typeToken2 = typeToken1;
                        if(reflectiveTypeAdapterFactory$BoundField0 != null) {
                            throw new IllegalArgumentException(type0 + " declares multiple JSON fields named " + reflectiveTypeAdapterFactory$BoundField0.name);
                        }
                    }
                    else {
                        typeToken2 = typeToken1;
                    }
                    ++v1;
                    v = 0;
                    typeToken1 = typeToken2;
                }
                typeToken1 = TypeToken.get(.Gson.Types.resolve(typeToken1.getType(), class1, class1.getGenericSuperclass()));
            }
        }
        return map0;
    }

    private List getFieldNames(Field field0) {
        SerializedName serializedName0 = (SerializedName)field0.getAnnotation(SerializedName.class);
        if(serializedName0 == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(field0));
        }
        String s = serializedName0.value();
        String[] arr_s = serializedName0.alternate();
        if(arr_s.length == 0) {
            return Collections.singletonList(s);
        }
        List list0 = new ArrayList(arr_s.length + 1);
        list0.add(s);
        for(int v = 0; v < arr_s.length; ++v) {
            list0.add(arr_s[v]);
        }
        return list0;
    }
}

