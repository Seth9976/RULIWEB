package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Map;

public final class MapTypeAdapterFactory implements TypeAdapterFactory {
    final class Adapter extends TypeAdapter {
        private final ObjectConstructor constructor;
        private final TypeAdapter keyTypeAdapter;
        private final TypeAdapter valueTypeAdapter;

        public Adapter(Gson gson0, Type type0, TypeAdapter typeAdapter0, Type type1, TypeAdapter typeAdapter1, ObjectConstructor objectConstructor0) {
            this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson0, typeAdapter0, type0);
            this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(gson0, typeAdapter1, type1);
            this.constructor = objectConstructor0;
        }

        private String keyToString(JsonElement jsonElement0) {
            if(!jsonElement0.isJsonPrimitive()) {
                throw new AssertionError();
            }
            JsonPrimitive jsonPrimitive0 = jsonElement0.getAsJsonPrimitive();
            if(jsonPrimitive0.isNumber()) {
                return String.valueOf(jsonPrimitive0.getAsNumber());
            }
            if(jsonPrimitive0.isBoolean()) {
                return Boolean.toString(jsonPrimitive0.getAsBoolean());
            }
            if(!jsonPrimitive0.isString()) {
                throw new AssertionError();
            }
            return jsonPrimitive0.getAsString();
        }

        @Override  // com.google.gson.TypeAdapter
        public Object read(JsonReader jsonReader0) throws IOException {
            return this.read(jsonReader0);
        }

        public Map read(JsonReader jsonReader0) throws IOException {
            JsonToken jsonToken0 = jsonReader0.peek();
            if(jsonToken0 == JsonToken.NULL) {
                jsonReader0.nextNull();
                return null;
            }
            Map map0 = (Map)this.constructor.construct();
            if(jsonToken0 == JsonToken.BEGIN_ARRAY) {
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    jsonReader0.beginArray();
                    Object object0 = this.keyTypeAdapter.read(jsonReader0);
                    if(map0.put(object0, this.valueTypeAdapter.read(jsonReader0)) != null) {
                        throw new JsonSyntaxException("duplicate key: " + object0);
                    }
                    jsonReader0.endArray();
                }
                jsonReader0.endArray();
                return map0;
            }
            jsonReader0.beginObject();
            while(jsonReader0.hasNext()) {
                JsonReaderInternalAccess.INSTANCE.promoteNameToValue(jsonReader0);
                Object object1 = this.keyTypeAdapter.read(jsonReader0);
                if(map0.put(object1, this.valueTypeAdapter.read(jsonReader0)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + object1);
                }
                if(false) {
                    break;
                }
            }
            jsonReader0.endObject();
            return map0;
        }

        @Override  // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
            this.write(jsonWriter0, ((Map)object0));
        }

        public void write(JsonWriter jsonWriter0, Map map0) throws IOException {
            if(map0 == null) {
                jsonWriter0.nullValue();
                return;
            }
            if(!MapTypeAdapterFactory.this.complexMapKeySerialization) {
                jsonWriter0.beginObject();
                for(Object object0: map0.entrySet()) {
                    jsonWriter0.name(String.valueOf(((Map.Entry)object0).getKey()));
                    Object object1 = ((Map.Entry)object0).getValue();
                    this.valueTypeAdapter.write(jsonWriter0, object1);
                }
                jsonWriter0.endObject();
                return;
            }
            ArrayList arrayList0 = new ArrayList(map0.size());
            ArrayList arrayList1 = new ArrayList(map0.size());
            int v = 0;
            int v1 = 0;
            for(Object object2: map0.entrySet()) {
                Object object3 = ((Map.Entry)object2).getKey();
                JsonElement jsonElement0 = this.keyTypeAdapter.toJsonTree(object3);
                arrayList0.add(jsonElement0);
                arrayList1.add(((Map.Entry)object2).getValue());
                v1 |= (jsonElement0.isJsonArray() || jsonElement0.isJsonObject() ? 1 : 0);
            }
            if(v1 != 0) {
                jsonWriter0.beginArray();
                int v2 = arrayList0.size();
                while(v < v2) {
                    jsonWriter0.beginArray();
                    Streams.write(((JsonElement)arrayList0.get(v)), jsonWriter0);
                    Object object4 = arrayList1.get(v);
                    this.valueTypeAdapter.write(jsonWriter0, object4);
                    jsonWriter0.endArray();
                    ++v;
                }
                jsonWriter0.endArray();
                return;
            }
            jsonWriter0.beginObject();
            int v3 = arrayList0.size();
            while(v < v3) {
                jsonWriter0.name(this.keyToString(((JsonElement)arrayList0.get(v))));
                Object object5 = arrayList1.get(v);
                this.valueTypeAdapter.write(jsonWriter0, object5);
                ++v;
            }
            jsonWriter0.endObject();
        }
    }

    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;

    public MapTypeAdapterFactory(ConstructorConstructor constructorConstructor0, boolean z) {
        this.constructorConstructor = constructorConstructor0;
        this.complexMapKeySerialization = z;
    }

    @Override  // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
        Type type0 = typeToken0.getType();
        if(!Map.class.isAssignableFrom(typeToken0.getRawType())) {
            return null;
        }
        Type[] arr_type = .Gson.Types.getMapKeyAndValueTypes(type0, .Gson.Types.getRawType(type0));
        TypeAdapter typeAdapter0 = this.getKeyAdapter(gson0, arr_type[0]);
        TypeAdapter typeAdapter1 = gson0.getAdapter(TypeToken.get(arr_type[1]));
        ObjectConstructor objectConstructor0 = this.constructorConstructor.get(typeToken0);
        return new Adapter(this, gson0, arr_type[0], typeAdapter0, arr_type[1], typeAdapter1, objectConstructor0);
    }

    private TypeAdapter getKeyAdapter(Gson gson0, Type type0) {
        return type0 == Boolean.TYPE || type0 == Boolean.class ? TypeAdapters.BOOLEAN_AS_STRING : gson0.getAdapter(TypeToken.get(type0));
    }
}

