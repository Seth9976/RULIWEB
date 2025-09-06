package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder;
    private final List factories;
    private FieldNamingStrategy fieldNamingPolicy;
    private boolean generateNonExecutableJson;
    private final List hierarchyFactories;
    private final Map instanceCreators;
    private boolean lenient;
    private LongSerializationPolicy longSerializationPolicy;
    private ToNumberStrategy numberToNumberStrategy;
    private ToNumberStrategy objectToNumberStrategy;
    private boolean prettyPrinting;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private int timeStyle;

    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
        this.objectToNumberStrategy = ToNumberPolicy.DOUBLE;
        this.numberToNumberStrategy = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    }

    GsonBuilder(Gson gson0) {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        HashMap hashMap0 = new HashMap();
        this.instanceCreators = hashMap0;
        ArrayList arrayList0 = new ArrayList();
        this.factories = arrayList0;
        ArrayList arrayList1 = new ArrayList();
        this.hierarchyFactories = arrayList1;
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
        this.objectToNumberStrategy = ToNumberPolicy.DOUBLE;
        this.numberToNumberStrategy = ToNumberPolicy.LAZILY_PARSED_NUMBER;
        this.excluder = gson0.excluder;
        this.fieldNamingPolicy = gson0.fieldNamingStrategy;
        hashMap0.putAll(gson0.instanceCreators);
        this.serializeNulls = gson0.serializeNulls;
        this.complexMapKeySerialization = gson0.complexMapKeySerialization;
        this.generateNonExecutableJson = gson0.generateNonExecutableJson;
        this.escapeHtmlChars = gson0.htmlSafe;
        this.prettyPrinting = gson0.prettyPrinting;
        this.lenient = gson0.lenient;
        this.serializeSpecialFloatingPointValues = gson0.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson0.longSerializationPolicy;
        this.datePattern = gson0.datePattern;
        this.dateStyle = gson0.dateStyle;
        this.timeStyle = gson0.timeStyle;
        arrayList0.addAll(gson0.builderFactories);
        arrayList1.addAll(gson0.builderHierarchyFactories);
        this.objectToNumberStrategy = gson0.objectToNumberStrategy;
        this.numberToNumberStrategy = gson0.numberToNumberStrategy;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy exclusionStrategy0) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy0, false, true);
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy exclusionStrategy0) {
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy0, true, false);
        return this;
    }

    private void addTypeAdaptersForDate(String s, int v, int v1, List list0) {
        TypeAdapterFactory typeAdapterFactory2;
        TypeAdapterFactory typeAdapterFactory1;
        boolean z = SqlTypesSupport.SUPPORTS_SQL_TYPES;
        TypeAdapterFactory typeAdapterFactory0 = null;
        if(s != null && !s.trim().isEmpty()) {
            typeAdapterFactory1 = DateType.DATE.createAdapterFactory(s);
            if(z) {
                typeAdapterFactory0 = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(s);
                typeAdapterFactory2 = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(s);
            }
            else {
                typeAdapterFactory2 = null;
            }
            goto label_19;
        }
        else if(v != 2 && v1 != 2) {
            TypeAdapterFactory typeAdapterFactory3 = DateType.DATE.createAdapterFactory(v, v1);
            if(z) {
                typeAdapterFactory0 = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(v, v1);
                typeAdapterFactory1 = typeAdapterFactory3;
                typeAdapterFactory2 = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(v, v1);
            }
            else {
                typeAdapterFactory1 = typeAdapterFactory3;
                typeAdapterFactory2 = null;
            }
        label_19:
            list0.add(typeAdapterFactory1);
            if(z) {
                list0.add(typeAdapterFactory0);
                list0.add(typeAdapterFactory2);
            }
        }
    }

    public Gson create() {
        ArrayList arrayList0 = new ArrayList(this.factories.size() + this.hierarchyFactories.size() + 3);
        arrayList0.addAll(this.factories);
        Collections.reverse(arrayList0);
        ArrayList arrayList1 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(arrayList1);
        arrayList0.addAll(arrayList1);
        this.addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, arrayList0);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, this.factories, this.hierarchyFactories, arrayList0, this.objectToNumberStrategy, this.numberToNumberStrategy);
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int[] arr_v) {
        this.excluder = this.excluder.withModifiers(arr_v);
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type type0, Object object0) {
        .Gson.Preconditions.checkArgument(object0 instanceof JsonSerializer || object0 instanceof JsonDeserializer || object0 instanceof InstanceCreator || object0 instanceof TypeAdapter);
        if(object0 instanceof InstanceCreator) {
            this.instanceCreators.put(type0, ((InstanceCreator)object0));
        }
        if(object0 instanceof JsonSerializer || object0 instanceof JsonDeserializer) {
            TypeAdapterFactory typeAdapterFactory0 = TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type0), object0);
            this.factories.add(typeAdapterFactory0);
        }
        if(object0 instanceof TypeAdapter) {
            TypeAdapterFactory typeAdapterFactory1 = TypeAdapters.newFactory(TypeToken.get(type0), ((TypeAdapter)object0));
            this.factories.add(typeAdapterFactory1);
        }
        return this;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory0) {
        this.factories.add(typeAdapterFactory0);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class class0, Object object0) {
        .Gson.Preconditions.checkArgument(object0 instanceof JsonSerializer || object0 instanceof JsonDeserializer || object0 instanceof TypeAdapter);
        if(object0 instanceof JsonDeserializer || object0 instanceof JsonSerializer) {
            TypeAdapterFactory typeAdapterFactory0 = TreeTypeAdapter.newTypeHierarchyFactory(class0, object0);
            this.hierarchyFactories.add(typeAdapterFactory0);
        }
        if(object0 instanceof TypeAdapter) {
            TypeAdapterFactory typeAdapterFactory1 = TypeAdapters.newTypeHierarchyFactory(class0, ((TypeAdapter)object0));
            this.factories.add(typeAdapterFactory1);
        }
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder setDateFormat(int v) {
        this.dateStyle = v;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int v, int v1) {
        this.dateStyle = v;
        this.timeStyle = v1;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(String s) {
        this.datePattern = s;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy[] arr_exclusionStrategy) {
        for(int v = 0; v < arr_exclusionStrategy.length; ++v) {
            this.excluder = this.excluder.withExclusionStrategy(arr_exclusionStrategy[v], true, true);
        }
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy0) {
        this.fieldNamingPolicy = fieldNamingPolicy0;
        return this;
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy0) {
        this.fieldNamingPolicy = fieldNamingStrategy0;
        return this;
    }

    public GsonBuilder setLenient() {
        this.lenient = true;
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy0) {
        this.longSerializationPolicy = longSerializationPolicy0;
        return this;
    }

    public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy toNumberStrategy0) {
        this.numberToNumberStrategy = toNumberStrategy0;
        return this;
    }

    public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy toNumberStrategy0) {
        this.objectToNumberStrategy = toNumberStrategy0;
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public GsonBuilder setVersion(double f) {
        this.excluder = this.excluder.withVersion(f);
        return this;
    }
}

