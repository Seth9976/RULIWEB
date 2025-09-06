package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.internal.Util;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002 !BU\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001C\u0010\u0005\u001A\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006\u0012\u001A\u0010\t\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\u0015\u0010\u0014\u001A\u00028\u00002\u0006\u0010\u0015\u001A\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J\u001F\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u001B2\b\u0010\u001C\u001A\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u001DJ\b\u0010\u001E\u001A\u00020\u001FH\u0016R\'\u0010\u0005\u001A\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R%\u0010\t\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00070\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u000ER\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013¨\u0006\""}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter;", "T", "Lcom/squareup/moshi/JsonAdapter;", "constructor", "Lkotlin/reflect/KFunction;", "allBindings", "", "Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "", "nonTransientBindings", "options", "Lcom/squareup/moshi/JsonReader$Options;", "(Lkotlin/reflect/KFunction;Ljava/util/List;Ljava/util/List;Lcom/squareup/moshi/JsonReader$Options;)V", "getAllBindings", "()Ljava/util/List;", "getConstructor", "()Lkotlin/reflect/KFunction;", "getNonTransientBindings", "getOptions", "()Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "(Lcom/squareup/moshi/JsonReader;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "(Lcom/squareup/moshi/JsonWriter;Ljava/lang/Object;)V", "toString", "", "Binding", "IndexedParameterMap", "reflect"}, k = 1, mv = {1, 4, 0})
public final class KotlinJsonAdapter extends JsonAdapter {
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003BK\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0005\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00020\b\u0012\u0012\u0010\t\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\n\u0012\b\u0010\u000B\u001A\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001A\u00020\u000E\u00A2\u0006\u0002\u0010\u000FJ\t\u0010\u001B\u001A\u00020\u0005H\u00C6\u0003J\u000B\u0010\u001C\u001A\u0004\u0018\u00010\u0005H\u00C6\u0003J\u000F\u0010\u001D\u001A\b\u0012\u0004\u0012\u00028\u00020\bH\u00C6\u0003J\u0015\u0010\u001E\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\nH\u00C6\u0003J\u000B\u0010\u001F\u001A\u0004\u0018\u00010\fH\u00C6\u0003J\t\u0010 \u001A\u00020\u000EH\u00C6\u0003Jg\u0010!\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001A\u00020\u00052\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00052\u000E\b\u0002\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00020\b2\u0014\b\u0002\u0010\t\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\n2\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001A\u00020\u000EH\u00C6\u0001J\u0013\u0010\"\u001A\u00020#2\b\u0010$\u001A\u0004\u0018\u00010\u0003H\u00D6\u0003J\u0013\u0010%\u001A\u00028\u00022\u0006\u0010&\u001A\u00028\u0001\u00A2\u0006\u0002\u0010\'J\t\u0010(\u001A\u00020\u000EH\u00D6\u0001J\u001B\u0010)\u001A\u00020*2\u0006\u0010+\u001A\u00028\u00012\u0006\u0010&\u001A\u00028\u0002\u00A2\u0006\u0002\u0010,J\t\u0010-\u001A\u00020\u0005H\u00D6\u0001R\u0017\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00020\b\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001A\u0004\u0018\u00010\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0013R\u0013\u0010\u000B\u001A\u0004\u0018\u00010\f\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u001D\u0010\t\u001A\u000E\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001A\u00020\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001A\u00A8\u0006."}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "K", "P", "", "name", "", "jsonName", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "property", "Lkotlin/reflect/KProperty1;", "parameter", "Lkotlin/reflect/KParameter;", "propertyIndex", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/squareup/moshi/JsonAdapter;Lkotlin/reflect/KProperty1;Lkotlin/reflect/KParameter;I)V", "getAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "getJsonName", "()Ljava/lang/String;", "getName", "getParameter", "()Lkotlin/reflect/KParameter;", "getProperty", "()Lkotlin/reflect/KProperty1;", "getPropertyIndex", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "get", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "set", "", "result", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "reflect"}, k = 1, mv = {1, 4, 0})
    public static final class Binding {
        private final JsonAdapter adapter;
        private final String jsonName;
        private final String name;
        private final KParameter parameter;
        private final KProperty1 property;
        private final int propertyIndex;

        public Binding(String s, String s1, JsonAdapter jsonAdapter0, KProperty1 kProperty10, KParameter kParameter0, int v) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(jsonAdapter0, "adapter");
            Intrinsics.checkNotNullParameter(kProperty10, "property");
            super();
            this.name = s;
            this.jsonName = s1;
            this.adapter = jsonAdapter0;
            this.property = kProperty10;
            this.parameter = kParameter0;
            this.propertyIndex = v;
        }

        public final String component1() {
            return this.name;
        }

        public final String component2() {
            return this.jsonName;
        }

        public final JsonAdapter component3() {
            return this.adapter;
        }

        public final KProperty1 component4() {
            return this.property;
        }

        public final KParameter component5() {
            return this.parameter;
        }

        public final int component6() {
            return this.propertyIndex;
        }

        public final Binding copy(String s, String s1, JsonAdapter jsonAdapter0, KProperty1 kProperty10, KParameter kParameter0, int v) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(jsonAdapter0, "adapter");
            Intrinsics.checkNotNullParameter(kProperty10, "property");
            return new Binding(s, s1, jsonAdapter0, kProperty10, kParameter0, v);
        }

        public static Binding copy$default(Binding kotlinJsonAdapter$Binding0, String s, String s1, JsonAdapter jsonAdapter0, KProperty1 kProperty10, KParameter kParameter0, int v, int v1, Object object0) {
            if((v1 & 1) != 0) {
                s = kotlinJsonAdapter$Binding0.name;
            }
            if((v1 & 2) != 0) {
                s1 = kotlinJsonAdapter$Binding0.jsonName;
            }
            if((v1 & 4) != 0) {
                jsonAdapter0 = kotlinJsonAdapter$Binding0.adapter;
            }
            if((v1 & 8) != 0) {
                kProperty10 = kotlinJsonAdapter$Binding0.property;
            }
            if((v1 & 16) != 0) {
                kParameter0 = kotlinJsonAdapter$Binding0.parameter;
            }
            if((v1 & 0x20) != 0) {
                v = kotlinJsonAdapter$Binding0.propertyIndex;
            }
            return kotlinJsonAdapter$Binding0.copy(s, s1, jsonAdapter0, kProperty10, kParameter0, v);
        }

        // 去混淆评级： 中等(60)
        @Override
        public boolean equals(Object object0) {
            return this == object0 || object0 instanceof Binding && Intrinsics.areEqual(this.name, ((Binding)object0).name) && Intrinsics.areEqual(this.jsonName, ((Binding)object0).jsonName) && Intrinsics.areEqual(this.adapter, ((Binding)object0).adapter) && Intrinsics.areEqual(this.property, ((Binding)object0).property) && Intrinsics.areEqual(this.parameter, ((Binding)object0).parameter) && this.propertyIndex == ((Binding)object0).propertyIndex;
        }

        public final Object get(Object object0) {
            return this.property.get(object0);
        }

        public final JsonAdapter getAdapter() {
            return this.adapter;
        }

        public final String getJsonName() {
            return this.jsonName;
        }

        public final String getName() {
            return this.name;
        }

        public final KParameter getParameter() {
            return this.parameter;
        }

        public final KProperty1 getProperty() {
            return this.property;
        }

        public final int getPropertyIndex() {
            return this.propertyIndex;
        }

        @Override
        public int hashCode() {
            int v = 0;
            int v1 = this.name == null ? 0 : this.name.hashCode();
            int v2 = this.jsonName == null ? 0 : this.jsonName.hashCode();
            int v3 = this.adapter == null ? 0 : this.adapter.hashCode();
            int v4 = this.property == null ? 0 : this.property.hashCode();
            KParameter kParameter0 = this.parameter;
            if(kParameter0 != null) {
                v = kParameter0.hashCode();
            }
            return ((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v) * 0x1F + this.propertyIndex;
        }

        public final void set(Object object0, Object object1) {
            if(object1 != KotlinJsonAdapterKt.ABSENT_VALUE) {
                KProperty1 kProperty10 = this.property;
                if(kProperty10 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.reflect.KMutableProperty1<K, P>");
                }
                ((KMutableProperty1)kProperty10).set(object0, object1);
            }
        }

        @Override
        public String toString() {
            return "Binding(name=" + this.name + ", jsonName=" + this.jsonName + ", adapter=" + this.adapter + ", property=" + this.property + ", parameter=" + this.parameter + ", propertyIndex=" + this.propertyIndex + ")";
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\'\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0005\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B#\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u000E\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0002H\u0016J\u0013\u0010\u0012\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001A\u00020\u0002H\u0096\u0002J\u001C\u0010\u0013\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001A\u00020\u00022\b\u0010\u0014\u001A\u0004\u0018\u00010\u0003H\u0016R(\u0010\t\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000B0\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000E¨\u0006\u0015"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap;", "Lkotlin/collections/AbstractMutableMap;", "Lkotlin/reflect/KParameter;", "", "parameterKeys", "", "parameterValues", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "[Ljava/lang/Object;", "containsKey", "", "key", "get", "put", "value", "reflect"}, k = 1, mv = {1, 4, 0})
    public static final class IndexedParameterMap extends AbstractMutableMap {
        private final List parameterKeys;
        private final Object[] parameterValues;

        public IndexedParameterMap(List list0, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(list0, "parameterKeys");
            Intrinsics.checkNotNullParameter(arr_object, "parameterValues");
            super();
            this.parameterKeys = list0;
            this.parameterValues = arr_object;
        }

        // 去混淆评级： 低(20)
        @Override
        public final boolean containsKey(Object object0) {
            return object0 instanceof KParameter ? this.containsKey(((KParameter)object0)) : false;
        }

        public boolean containsKey(KParameter kParameter0) {
            Intrinsics.checkNotNullParameter(kParameter0, "key");
            return this.parameterValues[kParameter0.getIndex()] != KotlinJsonAdapterKt.ABSENT_VALUE;
        }

        // 去混淆评级： 低(20)
        @Override
        public final Object get(Object object0) {
            return object0 instanceof KParameter ? this.get(((KParameter)object0)) : null;
        }

        public Object get(KParameter kParameter0) {
            Intrinsics.checkNotNullParameter(kParameter0, "key");
            Object object0 = this.parameterValues[kParameter0.getIndex()];
            return object0 == KotlinJsonAdapterKt.ABSENT_VALUE ? null : object0;
        }

        @Override  // kotlin.collections.AbstractMutableMap
        public Set getEntries() {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.parameterKeys, 10));
            int v = 0;
            for(Object object0: this.parameterKeys) {
                if(v < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList0.add(new AbstractMap.SimpleEntry(((KParameter)object0), this.parameterValues[v]));
                ++v;
            }
            Collection collection0 = new LinkedHashSet();
            for(Object object1: arrayList0) {
                if(((AbstractMap.SimpleEntry)object1).getValue() != KotlinJsonAdapterKt.ABSENT_VALUE) {
                    collection0.add(object1);
                }
            }
            return (Set)collection0;
        }

        // 去混淆评级： 低(20)
        @Override
        public final Object getOrDefault(Object object0, Object object1) {
            return object0 instanceof KParameter ? this.getOrDefault(((KParameter)object0), object1) : object1;
        }

        public Object getOrDefault(KParameter kParameter0, Object object0) {
            return super.getOrDefault(kParameter0, object0);
        }

        @Override  // kotlin.collections.AbstractMutableMap
        public Object put(Object object0, Object object1) {
            return this.put(((KParameter)object0), object1);
        }

        public Object put(KParameter kParameter0, Object object0) {
            Intrinsics.checkNotNullParameter(kParameter0, "key");
            return null;
        }

        // 去混淆评级： 低(20)
        @Override
        public final Object remove(Object object0) {
            return object0 instanceof KParameter ? this.remove(((KParameter)object0)) : null;
        }

        public Object remove(KParameter kParameter0) {
            return super.remove(kParameter0);
        }

        // 去混淆评级： 低(20)
        @Override
        public final boolean remove(Object object0, Object object1) {
            return object0 instanceof KParameter ? this.remove(((KParameter)object0), object1) : false;
        }

        public boolean remove(KParameter kParameter0, Object object0) {
            return super.remove(kParameter0, object0);
        }
    }

    private final List allBindings;
    private final KFunction constructor;
    private final List nonTransientBindings;
    private final Options options;

    public KotlinJsonAdapter(KFunction kFunction0, List list0, List list1, Options jsonReader$Options0) {
        Intrinsics.checkNotNullParameter(kFunction0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "allBindings");
        Intrinsics.checkNotNullParameter(list1, "nonTransientBindings");
        Intrinsics.checkNotNullParameter(jsonReader$Options0, "options");
        super();
        this.constructor = kFunction0;
        this.allBindings = list0;
        this.nonTransientBindings = list1;
        this.options = jsonReader$Options0;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public Object fromJson(JsonReader jsonReader0) {
        Intrinsics.checkNotNullParameter(jsonReader0, "reader");
        int v = this.constructor.getParameters().size();
        int v1 = this.allBindings.size();
        Object[] arr_object = new Object[v1];
        for(int v3 = 0; v3 < v1; ++v3) {
            arr_object[v3] = KotlinJsonAdapterKt.access$getABSENT_VALUE$p();
        }
        jsonReader0.beginObject();
        while(jsonReader0.hasNext()) {
            int v4 = jsonReader0.selectName(this.options);
            if(v4 == -1) {
                jsonReader0.skipName();
                jsonReader0.skipValue();
                continue;
            }
            Binding kotlinJsonAdapter$Binding0 = (Binding)this.nonTransientBindings.get(v4);
            int v5 = kotlinJsonAdapter$Binding0.getPropertyIndex();
            if(arr_object[v5] != KotlinJsonAdapterKt.access$getABSENT_VALUE$p()) {
                throw new JsonDataException("Multiple values for \'" + kotlinJsonAdapter$Binding0.getProperty().getName() + "\' at " + "$");
            }
            Object object0 = kotlinJsonAdapter$Binding0.getAdapter().fromJson(jsonReader0);
            arr_object[v5] = object0;
            if(object0 != null || kotlinJsonAdapter$Binding0.getProperty().getReturnType().isMarkedNullable()) {
                continue;
            }
            JsonDataException jsonDataException0 = Util.unexpectedNull(kotlinJsonAdapter$Binding0.getProperty().getName(), kotlinJsonAdapter$Binding0.getJsonName(), jsonReader0);
            Intrinsics.checkNotNullExpressionValue(jsonDataException0, "Util.unexpectedNull(\n   …         reader\n        )");
            throw jsonDataException0;
        }
        jsonReader0.endObject();
        for(int v2 = 0; true; ++v2) {
            String s = null;
            if(v2 >= v) {
                break;
            }
            if(arr_object[v2] == KotlinJsonAdapterKt.access$getABSENT_VALUE$p() && !((KParameter)this.constructor.getParameters().get(v2)).isOptional()) {
                if(!((KParameter)this.constructor.getParameters().get(v2)).getType().isMarkedNullable()) {
                    String s1 = ((KParameter)this.constructor.getParameters().get(v2)).getName();
                    Binding kotlinJsonAdapter$Binding1 = (Binding)this.allBindings.get(v2);
                    if(kotlinJsonAdapter$Binding1 != null) {
                        s = kotlinJsonAdapter$Binding1.getJsonName();
                    }
                    JsonDataException jsonDataException1 = Util.missingProperty(s1, s, jsonReader0);
                    Intrinsics.checkNotNullExpressionValue(jsonDataException1, "Util.missingProperty(\n  …       reader\n          )");
                    throw jsonDataException1;
                }
                arr_object[v2] = null;
            }
        }
        IndexedParameterMap kotlinJsonAdapter$IndexedParameterMap0 = new IndexedParameterMap(this.constructor.getParameters(), arr_object);
        Object object1 = this.constructor.callBy(kotlinJsonAdapter$IndexedParameterMap0);
        int v6 = this.allBindings.size();
        while(v < v6) {
            Object object2 = this.allBindings.get(v);
            Intrinsics.checkNotNull(object2);
            ((Binding)object2).set(object1, arr_object[v]);
            ++v;
        }
        return object1;
    }

    public final List getAllBindings() {
        return this.allBindings;
    }

    public final KFunction getConstructor() {
        return this.constructor;
    }

    public final List getNonTransientBindings() {
        return this.nonTransientBindings;
    }

    public final Options getOptions() {
        return this.options;
    }

    @Override  // com.squareup.moshi.JsonAdapter
    public void toJson(JsonWriter jsonWriter0, Object object0) {
        Intrinsics.checkNotNullParameter(jsonWriter0, "writer");
        if(object0 == null) {
            throw new NullPointerException("value == null");
        }
        jsonWriter0.beginObject();
        for(Object object1: this.allBindings) {
            Binding kotlinJsonAdapter$Binding0 = (Binding)object1;
            if(kotlinJsonAdapter$Binding0 != null) {
                jsonWriter0.name(kotlinJsonAdapter$Binding0.getName());
                kotlinJsonAdapter$Binding0.getAdapter().toJson(jsonWriter0, kotlinJsonAdapter$Binding0.get(object0));
            }
        }
        jsonWriter0.endObject();
    }

    @Override
    public String toString() {
        return "KotlinJsonAdapter(" + this.constructor.getReturnType() + ')';
    }
}

