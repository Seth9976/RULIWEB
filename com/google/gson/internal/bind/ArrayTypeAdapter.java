package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class ArrayTypeAdapter extends TypeAdapter {
    public static final TypeAdapterFactory FACTORY;
    private final Class componentType;
    private final TypeAdapter componentTypeAdapter;

    static {
        ArrayTypeAdapter.FACTORY = new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                Type type0 = typeToken0.getType();
                if(!(type0 instanceof GenericArrayType) && (!(type0 instanceof Class) || !((Class)type0).isArray())) {
                    return null;
                }
                Type type1 = .Gson.Types.getArrayComponentType(type0);
                return new ArrayTypeAdapter(gson0, gson0.getAdapter(TypeToken.get(type1)), .Gson.Types.getRawType(type1));
            }
        };
    }

    public ArrayTypeAdapter(Gson gson0, TypeAdapter typeAdapter0, Class class0) {
        this.componentTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson0, typeAdapter0, class0);
        this.componentType = class0;
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        if(jsonReader0.peek() == JsonToken.NULL) {
            jsonReader0.nextNull();
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        jsonReader0.beginArray();
        while(jsonReader0.hasNext()) {
            arrayList0.add(this.componentTypeAdapter.read(jsonReader0));
        }
        jsonReader0.endArray();
        int v = arrayList0.size();
        Object object0 = Array.newInstance(this.componentType, v);
        for(int v1 = 0; v1 < v; ++v1) {
            Array.set(object0, v1, arrayList0.get(v1));
        }
        return object0;
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        if(object0 == null) {
            jsonWriter0.nullValue();
            return;
        }
        jsonWriter0.beginArray();
        int v = Array.getLength(object0);
        for(int v1 = 0; v1 < v; ++v1) {
            Object object1 = Array.get(object0, v1);
            this.componentTypeAdapter.write(jsonWriter0, object1);
        }
        jsonWriter0.endArray();
    }
}

