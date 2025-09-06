package com.google.firebase.encoders.json;

import android.util.Base64;
import android.util.JsonWriter;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Map;

final class JsonValueObjectEncoderContext implements ObjectEncoderContext, ValueEncoderContext {
    private boolean active;
    private JsonValueObjectEncoderContext childContext;
    private final ObjectEncoder fallbackEncoder;
    private final boolean ignoreNullValues;
    private final JsonWriter jsonWriter;
    private final Map objectEncoders;
    private final Map valueEncoders;

    private JsonValueObjectEncoderContext(JsonValueObjectEncoderContext jsonValueObjectEncoderContext0) {
        this.childContext = null;
        this.active = true;
        this.jsonWriter = jsonValueObjectEncoderContext0.jsonWriter;
        this.objectEncoders = jsonValueObjectEncoderContext0.objectEncoders;
        this.valueEncoders = jsonValueObjectEncoderContext0.valueEncoders;
        this.fallbackEncoder = jsonValueObjectEncoderContext0.fallbackEncoder;
        this.ignoreNullValues = jsonValueObjectEncoderContext0.ignoreNullValues;
    }

    JsonValueObjectEncoderContext(Writer writer0, Map map0, Map map1, ObjectEncoder objectEncoder0, boolean z) {
        this.childContext = null;
        this.active = true;
        this.jsonWriter = new JsonWriter(writer0);
        this.objectEncoders = map0;
        this.valueEncoders = map1;
        this.fallbackEncoder = objectEncoder0;
        this.ignoreNullValues = z;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, double f) throws IOException {
        return this.add(fieldDescriptor0.getName(), f);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, float f) throws IOException {
        return this.add(fieldDescriptor0.getName(), ((double)f));
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, int v) throws IOException {
        return this.add(fieldDescriptor0.getName(), v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, long v) throws IOException {
        return this.add(fieldDescriptor0.getName(), v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, Object object0) throws IOException {
        return this.add(fieldDescriptor0.getName(), object0);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(FieldDescriptor fieldDescriptor0, boolean z) throws IOException {
        return this.add(fieldDescriptor0.getName(), z);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, double f) throws IOException {
        return this.add(s, f);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, int v) throws IOException {
        return this.add(s, v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, long v) throws IOException {
        return this.add(s, v);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, Object object0) throws IOException {
        return this.add(s, object0);
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext add(String s, boolean z) throws IOException {
        return this.add(s, z);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(double f) throws IOException {
        return this.add(f);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(float f) throws IOException {
        return this.add(f);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(int v) throws IOException {
        return this.add(v);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(long v) throws IOException {
        return this.add(v);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(String s) throws IOException {
        return this.add(s);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(boolean z) throws IOException {
        return this.add(z);
    }

    @Override  // com.google.firebase.encoders.ValueEncoderContext
    public ValueEncoderContext add(byte[] arr_b) throws IOException {
        return this.add(arr_b);
    }

    public JsonValueObjectEncoderContext add(double f) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(f);
        return this;
    }

    public JsonValueObjectEncoderContext add(float f) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(((double)f));
        return this;
    }

    public JsonValueObjectEncoderContext add(int v) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(((long)v));
        return this;
    }

    public JsonValueObjectEncoderContext add(long v) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(v);
        return this;
    }

    JsonValueObjectEncoderContext add(Object object0, boolean z) throws IOException {
        int v = 0;
        if(z && this.cannotBeInline(object0)) {
            throw new EncodingException(String.format("%s cannot be encoded inline", (object0 == null ? null : object0.getClass())));
        }
        if(object0 == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        if(object0 instanceof Number) {
            this.jsonWriter.value(((Number)object0));
            return this;
        }
        if(object0.getClass().isArray()) {
            if(object0 instanceof byte[]) {
                return this.add(((byte[])object0));
            }
            this.jsonWriter.beginArray();
            if(object0 instanceof int[]) {
                while(v < ((int[])object0).length) {
                    this.jsonWriter.value(((long)((int[])object0)[v]));
                    ++v;
                }
            }
            else if(object0 instanceof long[]) {
                while(v < ((long[])object0).length) {
                    this.add(((long[])object0)[v]);
                    ++v;
                }
            }
            else if(object0 instanceof double[]) {
                while(v < ((double[])object0).length) {
                    this.jsonWriter.value(((double[])object0)[v]);
                    ++v;
                }
            }
            else if(object0 instanceof boolean[]) {
                while(v < ((boolean[])object0).length) {
                    this.jsonWriter.value(((boolean[])object0)[v]);
                    ++v;
                }
            }
            else if(object0 instanceof Number[]) {
                for(int v1 = 0; v1 < ((Number[])object0).length; ++v1) {
                    this.add(((Number[])object0)[v1], false);
                }
            }
            else {
                for(int v2 = 0; v2 < ((Object[])object0).length; ++v2) {
                    this.add(((Object[])object0)[v2], false);
                }
            }
            this.jsonWriter.endArray();
            return this;
        }
        if(object0 instanceof Collection) {
            this.jsonWriter.beginArray();
            for(Object object1: ((Collection)object0)) {
                this.add(object1, false);
            }
            this.jsonWriter.endArray();
            return this;
        }
        if(object0 instanceof Map) {
            this.jsonWriter.beginObject();
            for(Object object2: ((Map)object0).entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object2;
                Object object3 = map$Entry0.getKey();
                try {
                    this.add(((String)object3), map$Entry0.getValue());
                }
                catch(ClassCastException classCastException0) {
                    throw new EncodingException(String.format("Only String keys are currently supported in maps, got %s of type %s instead.", object3, object3.getClass()), classCastException0);
                }
            }
            this.jsonWriter.endObject();
            return this;
        }
        Class class0 = object0.getClass();
        ObjectEncoder objectEncoder0 = (ObjectEncoder)this.objectEncoders.get(class0);
        if(objectEncoder0 != null) {
            return this.doEncode(objectEncoder0, object0, z);
        }
        Class class1 = object0.getClass();
        ValueEncoder valueEncoder0 = (ValueEncoder)this.valueEncoders.get(class1);
        if(valueEncoder0 != null) {
            valueEncoder0.encode(object0, this);
            return this;
        }
        if(object0 instanceof Enum) {
            this.add(((Enum)object0).name());
            return this;
        }
        return this.doEncode(this.fallbackEncoder, object0, z);
    }

    public JsonValueObjectEncoderContext add(String s) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(s);
        return this;
    }

    public JsonValueObjectEncoderContext add(String s, double f) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.name(s);
        return this.add(f);
    }

    public JsonValueObjectEncoderContext add(String s, int v) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.name(s);
        return this.add(v);
    }

    public JsonValueObjectEncoderContext add(String s, long v) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.name(s);
        return this.add(v);
    }

    // 去混淆评级： 低(20)
    public JsonValueObjectEncoderContext add(String s, Object object0) throws IOException {
        return this.ignoreNullValues ? this.internalAddIgnoreNullValues(s, object0) : this.internalAdd(s, object0);
    }

    public JsonValueObjectEncoderContext add(String s, boolean z) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.name(s);
        return this.add(z);
    }

    public JsonValueObjectEncoderContext add(boolean z) throws IOException {
        this.maybeUnNest();
        this.jsonWriter.value(z);
        return this;
    }

    public JsonValueObjectEncoderContext add(byte[] arr_b) throws IOException {
        this.maybeUnNest();
        if(arr_b == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        String s = Base64.encodeToString(arr_b, 2);
        this.jsonWriter.value(s);
        return this;
    }

    // 去混淆评级： 中等(50)
    private boolean cannotBeInline(Object object0) {
        return object0 == null || object0.getClass().isArray() || object0 instanceof Collection || object0 instanceof Date || object0 instanceof Enum || object0 instanceof Number;
    }

    void close() throws IOException {
        this.maybeUnNest();
        this.jsonWriter.flush();
    }

    JsonValueObjectEncoderContext doEncode(ObjectEncoder objectEncoder0, Object object0, boolean z) throws IOException {
        if(!z) {
            this.jsonWriter.beginObject();
        }
        objectEncoder0.encode(object0, this);
        if(!z) {
            this.jsonWriter.endObject();
        }
        return this;
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext inline(Object object0) throws IOException {
        return this.add(object0, true);
    }

    private JsonValueObjectEncoderContext internalAdd(String s, Object object0) throws IOException, EncodingException {
        this.maybeUnNest();
        this.jsonWriter.name(s);
        if(object0 == null) {
            this.jsonWriter.nullValue();
            return this;
        }
        return this.add(object0, false);
    }

    private JsonValueObjectEncoderContext internalAddIgnoreNullValues(String s, Object object0) throws IOException, EncodingException {
        if(object0 == null) {
            return this;
        }
        this.maybeUnNest();
        this.jsonWriter.name(s);
        return this.add(object0, false);
    }

    private void maybeUnNest() throws IOException {
        if(!this.active) {
            throw new IllegalStateException("Parent context used since this context was created. Cannot use this context anymore.");
        }
        JsonValueObjectEncoderContext jsonValueObjectEncoderContext0 = this.childContext;
        if(jsonValueObjectEncoderContext0 != null) {
            jsonValueObjectEncoderContext0.maybeUnNest();
            this.childContext.active = false;
            this.childContext = null;
            this.jsonWriter.endObject();
        }
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(FieldDescriptor fieldDescriptor0) throws IOException {
        return this.nested(fieldDescriptor0.getName());
    }

    @Override  // com.google.firebase.encoders.ObjectEncoderContext
    public ObjectEncoderContext nested(String s) throws IOException {
        this.maybeUnNest();
        this.childContext = new JsonValueObjectEncoderContext(this);
        this.jsonWriter.name(s);
        this.jsonWriter.beginObject();
        return this.childContext;
    }
}

