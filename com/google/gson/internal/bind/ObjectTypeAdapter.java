package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class ObjectTypeAdapter extends TypeAdapter {
    private static final TypeAdapterFactory DOUBLE_FACTORY;
    private final Gson gson;
    private final ToNumberStrategy toNumberStrategy;

    static {
        ObjectTypeAdapter.DOUBLE_FACTORY = ObjectTypeAdapter.newFactory(ToNumberPolicy.DOUBLE);
    }

    private ObjectTypeAdapter(Gson gson0, ToNumberStrategy toNumberStrategy0) {
        this.gson = gson0;
        this.toNumberStrategy = toNumberStrategy0;
    }

    ObjectTypeAdapter(Gson gson0, ToNumberStrategy toNumberStrategy0, com.google.gson.internal.bind.ObjectTypeAdapter.1 objectTypeAdapter$10) {
        this(gson0, toNumberStrategy0);
    }

    public static TypeAdapterFactory getFactory(ToNumberStrategy toNumberStrategy0) {
        return toNumberStrategy0 == ToNumberPolicy.DOUBLE ? ObjectTypeAdapter.DOUBLE_FACTORY : ObjectTypeAdapter.newFactory(toNumberStrategy0);
    }

    private static TypeAdapterFactory newFactory(ToNumberStrategy toNumberStrategy0) {
        return new TypeAdapterFactory() {
            @Override  // com.google.gson.TypeAdapterFactory
            public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
                return typeToken0.getRawType() == Object.class ? new ObjectTypeAdapter(gson0, toNumberStrategy0, null) : null;
            }
        };
    }

    @Override  // com.google.gson.TypeAdapter
    public Object read(JsonReader jsonReader0) throws IOException {
        switch(com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[jsonReader0.peek().ordinal()]) {
            case 1: {
                ArrayList arrayList0 = new ArrayList();
                jsonReader0.beginArray();
                while(jsonReader0.hasNext()) {
                    arrayList0.add(this.read(jsonReader0));
                }
                jsonReader0.endArray();
                return arrayList0;
            }
            case 2: {
                LinkedTreeMap linkedTreeMap0 = new LinkedTreeMap();
                jsonReader0.beginObject();
                while(jsonReader0.hasNext()) {
                    linkedTreeMap0.put(jsonReader0.nextName(), this.read(jsonReader0));
                }
                jsonReader0.endObject();
                return linkedTreeMap0;
            }
            case 3: {
                return jsonReader0.nextString();
            }
            case 4: {
                return this.toNumberStrategy.readNumber(jsonReader0);
            }
            case 5: {
                return Boolean.valueOf(jsonReader0.nextBoolean());
            }
            case 6: {
                jsonReader0.nextNull();
                return null;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    @Override  // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
        if(object0 == null) {
            jsonWriter0.nullValue();
            return;
        }
        Class class0 = object0.getClass();
        TypeAdapter typeAdapter0 = this.gson.getAdapter(class0);
        if(typeAdapter0 instanceof ObjectTypeAdapter) {
            jsonWriter0.beginObject();
            jsonWriter0.endObject();
            return;
        }
        typeAdapter0.write(jsonWriter0, object0);
    }

    class com.google.gson.internal.bind.ObjectTypeAdapter.2 {
        static final int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] arr_v = new int[JsonToken.values().length];
            com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken = arr_v;
            try {
                arr_v[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.gson.internal.bind.ObjectTypeAdapter.2.$SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

