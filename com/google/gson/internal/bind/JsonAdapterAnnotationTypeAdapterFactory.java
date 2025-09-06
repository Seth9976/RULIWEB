package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor0) {
        this.constructorConstructor = constructorConstructor0;
    }

    @Override  // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
        JsonAdapter jsonAdapter0 = (JsonAdapter)typeToken0.getRawType().getAnnotation(JsonAdapter.class);
        return jsonAdapter0 == null ? null : this.getTypeAdapter(this.constructorConstructor, gson0, typeToken0, jsonAdapter0);
    }

    TypeAdapter getTypeAdapter(ConstructorConstructor constructorConstructor0, Gson gson0, TypeToken typeToken0, JsonAdapter jsonAdapter0) {
        TypeAdapter typeAdapter0;
        JsonDeserializer jsonDeserializer0 = null;
        Object object0 = constructorConstructor0.get(TypeToken.get(jsonAdapter0.value())).construct();
        if(object0 instanceof TypeAdapter) {
            typeAdapter0 = (TypeAdapter)object0;
            return typeAdapter0 == null || !jsonAdapter0.nullSafe() ? typeAdapter0 : typeAdapter0.nullSafe();
        }
        if(object0 instanceof TypeAdapterFactory) {
            typeAdapter0 = ((TypeAdapterFactory)object0).create(gson0, typeToken0);
            return typeAdapter0 == null || !jsonAdapter0.nullSafe() ? typeAdapter0 : typeAdapter0.nullSafe();
        }
        if(!(object0 instanceof JsonSerializer) && !(object0 instanceof JsonDeserializer)) {
            throw new IllegalArgumentException("Invalid attempt to bind an instance of " + object0.getClass().getName() + " as a @JsonAdapter for " + typeToken0.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
        }
        if(object0 instanceof JsonDeserializer) {
            jsonDeserializer0 = (JsonDeserializer)object0;
        }
        typeAdapter0 = new TreeTypeAdapter((object0 instanceof JsonSerializer ? ((JsonSerializer)object0) : null), jsonDeserializer0, gson0, typeToken0, null);
        return typeAdapter0 == null || !jsonAdapter0.nullSafe() ? typeAdapter0 : typeAdapter0.nullSafe();
    }
}

