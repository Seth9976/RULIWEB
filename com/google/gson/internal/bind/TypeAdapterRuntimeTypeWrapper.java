package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper extends TypeAdapter {
    private final Gson context;
    private final TypeAdapter delegate;
    private final Type type;

    TypeAdapterRuntimeTypeWrapper(Gson gson0, TypeAdapter typeAdapter0, Type type0) {
        this.context = gson0;
        this.delegate = typeAdapter0;
        this.type = type0;
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type0, Object object0) {
        return object0 != null && (type0 == Object.class || type0 instanceof TypeVariable || type0 instanceof Class) ? object0.getClass() : type0;
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        return this.delegate.read(jsonReader0);
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        TypeAdapter typeAdapter0 = this.delegate;
        Type type0 = this.getRuntimeTypeIfMoreSpecific(this.type, object0);
        if(type0 != this.type) {
            TypeToken typeToken0 = TypeToken.get(type0);
            typeAdapter0 = this.context.getAdapter(typeToken0);
            if(typeAdapter0 instanceof Adapter) {
                TypeAdapter typeAdapter1 = this.delegate;
                if(!(typeAdapter1 instanceof Adapter)) {
                    typeAdapter0 = typeAdapter1;
                }
            }
        }
        typeAdapter0.write(jsonWriter0, object0);
    }
}

