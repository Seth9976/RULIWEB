package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

final class ClassJsonAdapter extends JsonAdapter {
    static class FieldBinding {
        final JsonAdapter adapter;
        final Field field;
        final String name;

        FieldBinding(String s, Field field0, JsonAdapter jsonAdapter0) {
            this.name = s;
            this.field = field0;
            this.adapter = jsonAdapter0;
        }

        void read(JsonReader jsonReader0, Object object0) throws IOException, IllegalAccessException {
            Object object1 = this.adapter.fromJson(jsonReader0);
            this.field.set(object0, object1);
        }

        void write(JsonWriter jsonWriter0, Object object0) throws IllegalAccessException, IOException {
            Object object1 = this.field.get(object0);
            this.adapter.toJson(jsonWriter0, object1);
        }
    }

    public static final Factory FACTORY;
    private final ClassFactory classFactory;
    private final FieldBinding[] fieldsArray;
    private final Options options;

    static {
        ClassJsonAdapter.FACTORY = new Factory() {
            @Override  // com.squareup.moshi.JsonAdapter$Factory
            @Nullable
            public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
                if(!(type0 instanceof Class) && !(type0 instanceof ParameterizedType)) {
                    return null;
                }
                Class class0 = Types.getRawType(type0);
                if(class0.isInterface() || class0.isEnum() || !set0.isEmpty()) {
                    return null;
                }
                if(Util.isPlatformType(class0)) {
                    this.throwIfIsCollectionClass(type0, List.class);
                    this.throwIfIsCollectionClass(type0, Set.class);
                    this.throwIfIsCollectionClass(type0, Map.class);
                    this.throwIfIsCollectionClass(type0, Collection.class);
                    throw new IllegalArgumentException((type0 instanceof ParameterizedType ? "Platform " + class0 + " in " + type0 : "Platform " + class0) + " requires explicit JsonAdapter to be registered");
                }
                if(class0.isAnonymousClass()) {
                    throw new IllegalArgumentException("Cannot serialize anonymous class " + class0.getName());
                }
                if(class0.isLocalClass()) {
                    throw new IllegalArgumentException("Cannot serialize local class " + class0.getName());
                }
                if(class0.getEnclosingClass() != null && !Modifier.isStatic(class0.getModifiers())) {
                    throw new IllegalArgumentException("Cannot serialize non-static nested class " + class0.getName());
                }
                if(Modifier.isAbstract(class0.getModifiers())) {
                    throw new IllegalArgumentException("Cannot serialize abstract class " + class0.getName());
                }
                if(Util.isKotlin(class0)) {
                    throw new IllegalArgumentException("Cannot serialize Kotlin type " + class0.getName() + ". Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. Please use KotlinJsonAdapterFactory from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact.");
                }
                ClassFactory classFactory0 = ClassFactory.get(class0);
                TreeMap treeMap0 = new TreeMap();
                while(type0 != Object.class) {
                    this.createFieldBindings(moshi0, type0, treeMap0);
                    type0 = Types.getGenericSuperclass(type0);
                }
                return new ClassJsonAdapter(classFactory0, treeMap0).nullSafe();
            }

            private void createFieldBindings(Moshi moshi0, Type type0, Map map0) {
                Class class0 = Types.getRawType(type0);
                boolean z = Util.isPlatformType(class0);
                Field[] arr_field = class0.getDeclaredFields();
                for(int v = 0; v < arr_field.length; ++v) {
                    Field field0 = arr_field[v];
                    if(this.includeField(z, field0.getModifiers())) {
                        Type type1 = Util.resolve(type0, class0, field0.getGenericType());
                        Set set0 = Util.jsonAnnotations(field0);
                        String s = field0.getName();
                        JsonAdapter jsonAdapter0 = moshi0.adapter(type1, set0, s);
                        field0.setAccessible(true);
                        Json json0 = (Json)field0.getAnnotation(Json.class);
                        if(json0 != null) {
                            s = json0.name();
                        }
                        FieldBinding classJsonAdapter$FieldBinding0 = new FieldBinding(s, field0, jsonAdapter0);
                        FieldBinding classJsonAdapter$FieldBinding1 = (FieldBinding)map0.put(s, classJsonAdapter$FieldBinding0);
                        if(classJsonAdapter$FieldBinding1 != null) {
                            throw new IllegalArgumentException("Conflicting fields:\n    " + classJsonAdapter$FieldBinding1.field + "\n    " + classJsonAdapter$FieldBinding0.field);
                        }
                    }
                }
            }

            // 去混淆评级： 中等(50)
            private boolean includeField(boolean z, int v) {
                return !Modifier.isStatic(v) && !Modifier.isTransient(v) && (Modifier.isPublic(v) || Modifier.isProtected(v) || !z);
            }

            private void throwIfIsCollectionClass(Type type0, Class class0) {
                Class class1 = Types.getRawType(type0);
                if(class0.isAssignableFrom(class1)) {
                    throw new IllegalArgumentException("No JsonAdapter for " + type0 + ", you should probably use " + class0.getSimpleName() + " instead of " + class1.getSimpleName() + " (Moshi only supports the collection interfaces by default) or else register a custom JsonAdapter.");
                }
            }
        };
    }

    ClassJsonAdapter(ClassFactory classFactory0, Map map0) {
        this.classFactory = classFactory0;
        this.fieldsArray = (FieldBinding[])map0.values().toArray(new FieldBinding[map0.size()]);
        this.options = Options.of(((String[])map0.keySet().toArray(new String[map0.size()])));
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader0) throws IOException {
        Object object0;
        try {
            object0 = this.classFactory.newInstance();
        }
        catch(InstantiationException instantiationException0) {
            throw new RuntimeException(instantiationException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw Util.rethrowCause(invocationTargetException0);
        }
        catch(IllegalAccessException unused_ex) {
            throw new AssertionError();
        }
        try {
            jsonReader0.beginObject();
            while(jsonReader0.hasNext()) {
                int v = jsonReader0.selectName(this.options);
                if(v == -1) {
                    jsonReader0.skipName();
                    jsonReader0.skipValue();
                }
                else {
                    this.fieldsArray[v].read(jsonReader0, object0);
                }
            }
            jsonReader0.endObject();
            return object0;
        }
        catch(IllegalAccessException unused_ex) {
            throw new AssertionError();
        }
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, Object object0) throws IOException {
        try {
            jsonWriter0.beginObject();
            FieldBinding[] arr_classJsonAdapter$FieldBinding = this.fieldsArray;
            for(int v = 0; v < arr_classJsonAdapter$FieldBinding.length; ++v) {
                FieldBinding classJsonAdapter$FieldBinding0 = arr_classJsonAdapter$FieldBinding[v];
                jsonWriter0.name(classJsonAdapter$FieldBinding0.name);
                classJsonAdapter$FieldBinding0.write(jsonWriter0, object0);
            }
            jsonWriter0.endObject();
        }
        catch(IllegalAccessException unused_ex) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "JsonAdapter(" + this.classFactory + ")";
    }
}

