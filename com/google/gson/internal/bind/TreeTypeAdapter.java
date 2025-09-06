package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public final class TreeTypeAdapter extends TypeAdapter {
    final class GsonContextImpl implements JsonDeserializationContext, JsonSerializationContext {
        private GsonContextImpl() {
        }

        GsonContextImpl(com.google.gson.internal.bind.TreeTypeAdapter.1 treeTypeAdapter$10) {
        }

        @Override  // com.google.gson.JsonDeserializationContext
        public Object deserialize(JsonElement jsonElement0, Type type0) throws JsonParseException {
            return TreeTypeAdapter.this.gson.fromJson(jsonElement0, type0);
        }

        @Override  // com.google.gson.JsonSerializationContext
        public JsonElement serialize(Object object0) {
            return TreeTypeAdapter.this.gson.toJsonTree(object0);
        }

        @Override  // com.google.gson.JsonSerializationContext
        public JsonElement serialize(Object object0, Type type0) {
            return TreeTypeAdapter.this.gson.toJsonTree(object0, type0);
        }
    }

    static final class SingleTypeFactory implements TypeAdapterFactory {
        private final JsonDeserializer deserializer;
        private final TypeToken exactType;
        private final Class hierarchyType;
        private final boolean matchRawType;
        private final JsonSerializer serializer;

        SingleTypeFactory(Object object0, TypeToken typeToken0, boolean z, Class class0) {
            JsonDeserializer jsonDeserializer0 = null;
            JsonSerializer jsonSerializer0 = object0 instanceof JsonSerializer ? ((JsonSerializer)object0) : null;
            this.serializer = jsonSerializer0;
            if(object0 instanceof JsonDeserializer) {
                jsonDeserializer0 = (JsonDeserializer)object0;
            }
            this.deserializer = jsonDeserializer0;
            .Gson.Preconditions.checkArgument(jsonSerializer0 != null || jsonDeserializer0 != null);
            this.exactType = typeToken0;
            this.matchRawType = z;
            this.hierarchyType = class0;
        }

        @Override  // com.google.gson.TypeAdapterFactory
        public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
            boolean z;
            TypeToken typeToken1 = this.exactType;
            if(typeToken1 == null) {
                z = this.hierarchyType.isAssignableFrom(typeToken0.getRawType());
            }
            else if(!typeToken1.equals(typeToken0) && (!this.matchRawType || this.exactType.getType() != typeToken0.getRawType())) {
                z = false;
            }
            else {
                z = true;
            }
            return z ? new TreeTypeAdapter(this.serializer, this.deserializer, gson0, typeToken0, this) : null;
        }
    }

    private final GsonContextImpl context;
    private TypeAdapter delegate;
    private final JsonDeserializer deserializer;
    final Gson gson;
    private final JsonSerializer serializer;
    private final TypeAdapterFactory skipPast;
    private final TypeToken typeToken;

    public TreeTypeAdapter(JsonSerializer jsonSerializer0, JsonDeserializer jsonDeserializer0, Gson gson0, TypeToken typeToken0, TypeAdapterFactory typeAdapterFactory0) {
        this.context = new GsonContextImpl(this, null);
        this.serializer = jsonSerializer0;
        this.deserializer = jsonDeserializer0;
        this.gson = gson0;
        this.typeToken = typeToken0;
        this.skipPast = typeAdapterFactory0;
    }

    private TypeAdapter delegate() {
        TypeAdapter typeAdapter0 = this.delegate;
        if(typeAdapter0 != null) {
            return typeAdapter0;
        }
        TypeAdapter typeAdapter1 = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
        this.delegate = typeAdapter1;
        return typeAdapter1;
    }

    public static TypeAdapterFactory newFactory(TypeToken typeToken0, Object object0) {
        return new SingleTypeFactory(object0, typeToken0, false, null);
    }

    public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken typeToken0, Object object0) {
        return typeToken0.getType() == typeToken0.getRawType() ? new SingleTypeFactory(object0, typeToken0, true, null) : new SingleTypeFactory(object0, typeToken0, false, null);
    }

    public static TypeAdapterFactory newTypeHierarchyFactory(Class class0, Object object0) {
        return new SingleTypeFactory(object0, null, false, class0);
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        if(this.deserializer == null) {
            return this.delegate().read(jsonReader0);
        }
        JsonElement jsonElement0 = Streams.parse(jsonReader0);
        return this.deserializer.deserialize(jsonElement0, this.typeToken.getType(), this.context);
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        JsonSerializer jsonSerializer0 = this.serializer;
        if(jsonSerializer0 == null) {
            this.delegate().write(jsonWriter0, object0);
            return;
        }
        if(object0 == null) {
            jsonWriter0.nullValue();
            return;
        }
        Streams.write(jsonSerializer0.serialize(object0, this.typeToken.getType(), this.context), jsonWriter0);
    }

    class com.google.gson.internal.bind.TreeTypeAdapter.1 {
    }

}

