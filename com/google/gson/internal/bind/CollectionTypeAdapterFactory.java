package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class CollectionTypeAdapterFactory implements TypeAdapterFactory {
    static final class Adapter extends TypeAdapter {
        private final ObjectConstructor constructor;
        private final TypeAdapter elementTypeAdapter;

        public Adapter(Gson gson0, Type type0, TypeAdapter typeAdapter0, ObjectConstructor objectConstructor0) {
            this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson0, typeAdapter0, type0);
            this.constructor = objectConstructor0;
        }

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            return this.read(jsonReader0);
        }

        public Collection read(JsonReader jsonReader0) throws IOException {
            if(jsonReader0.peek() == JsonToken.NULL) {
                jsonReader0.nextNull();
                return null;
            }
            Collection collection0 = (Collection)this.constructor.construct();
            jsonReader0.beginArray();
            while(jsonReader0.hasNext()) {
                collection0.add(this.elementTypeAdapter.read(jsonReader0));
            }
            jsonReader0.endArray();
            return collection0;
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            this.write(jsonWriter0, ((Collection)object0));
        }

        public void write(JsonWriter jsonWriter0, Collection collection0) throws IOException {
            if(collection0 == null) {
                jsonWriter0.nullValue();
                return;
            }
            jsonWriter0.beginArray();
            for(Object object0: collection0) {
                this.elementTypeAdapter.write(jsonWriter0, object0);
            }
            jsonWriter0.endArray();
        }
    }

    private final ConstructorConstructor constructorConstructor;

    public CollectionTypeAdapterFactory(ConstructorConstructor constructorConstructor0) {
        this.constructorConstructor = constructorConstructor0;
    }

    @Override  // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
        Type type0 = typeToken0.getType();
        Class class0 = typeToken0.getRawType();
        if(!Collection.class.isAssignableFrom(class0)) {
            return null;
        }
        Type type1 = .Gson.Types.getCollectionElementType(type0, class0);
        return new Adapter(gson0, type1, gson0.getAdapter(TypeToken.get(type1)), this.constructorConstructor.get(typeToken0));
    }
}

