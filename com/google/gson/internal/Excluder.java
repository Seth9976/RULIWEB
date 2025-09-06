package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = null;
    private static final double IGNORE_VERSIONS = -1.0;
    private List deserializationStrategies;
    private int modifiers;
    private boolean requireExpose;
    private List serializationStrategies;
    private boolean serializeInnerClasses;
    private double version;

    static {
        Excluder.DEFAULT = new Excluder();
    }

    public Excluder() {
        this.version = -1.0;
        this.modifiers = 0x88;
        this.serializeInnerClasses = true;
        this.serializationStrategies = Collections.EMPTY_LIST;
        this.deserializationStrategies = Collections.EMPTY_LIST;
    }

    protected Excluder clone() {
        try {
            return (Excluder)super.clone();
        }
        catch(CloneNotSupportedException cloneNotSupportedException0) {
            throw new AssertionError(cloneNotSupportedException0);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // com.google.gson.TypeAdapterFactory
    public TypeAdapter create(Gson gson0, TypeToken typeToken0) {
        Class class0 = typeToken0.getRawType();
        boolean z = this.excludeClassChecks(class0);
        boolean z1 = z || this.excludeClassInStrategy(class0, true);
        boolean z2 = z || this.excludeClassInStrategy(class0, false);
        return !z1 && !z2 ? null : new TypeAdapter() {
            private TypeAdapter delegate;

            private TypeAdapter delegate() {
                TypeAdapter typeAdapter0 = this.delegate;
                if(typeAdapter0 != null) {
                    return typeAdapter0;
                }
                TypeAdapter typeAdapter1 = gson0.getDelegateAdapter(Excluder.this, typeToken0);
                this.delegate = typeAdapter1;
                return typeAdapter1;
            }

            @Override  // com.google.gson.TypeAdapter
            public Object read(JsonReader jsonReader0) throws IOException {
                if(z2) {
                    jsonReader0.skipValue();
                    return null;
                }
                return this.delegate().read(jsonReader0);
            }

            @Override  // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter0, Object object0) throws IOException {
                if(z1) {
                    jsonWriter0.nullValue();
                    return;
                }
                this.delegate().write(jsonWriter0, object0);
            }
        };
    }

    public Excluder disableInnerClassSerialization() {
        Excluder excluder0 = this.clone();
        excluder0.serializeInnerClasses = false;
        return excluder0;
    }

    // 去混淆评级： 低(20)
    public boolean excludeClass(Class class0, boolean z) {
        return this.excludeClassChecks(class0) || this.excludeClassInStrategy(class0, z);
    }

    private boolean excludeClassChecks(Class class0) {
        if(this.version != -1.0 && !this.isValidVersion(((Since)class0.getAnnotation(Since.class)), ((Until)class0.getAnnotation(Until.class)))) {
            return true;
        }
        return this.serializeInnerClasses || !this.isInnerClass(class0) ? this.isAnonymousOrNonStaticLocal(class0) : true;
    }

    private boolean excludeClassInStrategy(Class class0, boolean z) {
        for(Object object0: (z ? this.serializationStrategies : this.deserializationStrategies)) {
            if(((ExclusionStrategy)object0).shouldSkipClass(class0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public boolean excludeField(Field field0, boolean z) {
        if((this.modifiers & field0.getModifiers()) != 0) {
            return true;
        }
        if(this.version != -1.0 && !this.isValidVersion(((Since)field0.getAnnotation(Since.class)), ((Until)field0.getAnnotation(Until.class)))) {
            return true;
        }
        if(field0.isSynthetic()) {
            return true;
        }
        if(this.requireExpose) {
            Expose expose0 = (Expose)field0.getAnnotation(Expose.class);
            if(expose0 == null) {
                return true;
            }
            if(z) {
                if(!expose0.serialize()) {
                    return true;
                }
            }
            else if(!expose0.deserialize()) {
                return true;
            }
        }
        if(!this.serializeInnerClasses && this.isInnerClass(field0.getType())) {
            return true;
        }
        if(this.isAnonymousOrNonStaticLocal(field0.getType())) {
            return true;
        }
        List list0 = z ? this.serializationStrategies : this.deserializationStrategies;
        if(!list0.isEmpty()) {
            FieldAttributes fieldAttributes0 = new FieldAttributes(field0);
            for(Object object0: list0) {
                if(((ExclusionStrategy)object0).shouldSkipField(fieldAttributes0)) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
        }
        return false;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder excluder0 = this.clone();
        excluder0.requireExpose = true;
        return excluder0;
    }

    // 去混淆评级： 低(40)
    private boolean isAnonymousOrNonStaticLocal(Class class0) {
        return !Enum.class.isAssignableFrom(class0) && !this.isStatic(class0) && (class0.isAnonymousClass() || class0.isLocalClass());
    }

    // 去混淆评级： 低(20)
    private boolean isInnerClass(Class class0) {
        return class0.isMemberClass() && !this.isStatic(class0);
    }

    private boolean isStatic(Class class0) {
        return (class0.getModifiers() & 8) != 0;
    }

    private boolean isValidSince(Since since0) {
        return since0 == null || since0.value() <= this.version;
    }

    private boolean isValidUntil(Until until0) {
        return until0 == null || until0.value() > this.version;
    }

    // 去混淆评级： 低(20)
    private boolean isValidVersion(Since since0, Until until0) {
        return this.isValidSince(since0) && this.isValidUntil(until0);
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy0, boolean z, boolean z1) {
        Excluder excluder0 = this.clone();
        if(z) {
            ArrayList arrayList0 = new ArrayList(this.serializationStrategies);
            excluder0.serializationStrategies = arrayList0;
            arrayList0.add(exclusionStrategy0);
        }
        if(z1) {
            ArrayList arrayList1 = new ArrayList(this.deserializationStrategies);
            excluder0.deserializationStrategies = arrayList1;
            arrayList1.add(exclusionStrategy0);
        }
        return excluder0;
    }

    public Excluder withModifiers(int[] arr_v) {
        Excluder excluder0 = this.clone();
        excluder0.modifiers = 0;
        for(int v = 0; v < arr_v.length; ++v) {
            excluder0.modifiers |= arr_v[v];
        }
        return excluder0;
    }

    public Excluder withVersion(double f) {
        Excluder excluder0 = this.clone();
        excluder0.version = f;
        return excluder0;
    }
}

