package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.Moshi;
import com.squareup.moshi._MoshiKotlinTypesExtensionsKt;
import com.squareup.moshi.internal.Util;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u001B\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u000E\u0010\u0007\u001A\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\n\u001A\u00020\u000BH\u0016¨\u0006\f"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory;", "Lcom/squareup/moshi/JsonAdapter$Factory;", "()V", "create", "Lcom/squareup/moshi/JsonAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "moshi", "Lcom/squareup/moshi/Moshi;", "reflect"}, k = 1, mv = {1, 4, 0})
public final class KotlinJsonAdapterFactory implements Factory {
    @Override  // com.squareup.moshi.JsonAdapter$Factory
    public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
        String s4;
        String s1;
        JsonAdapter jsonAdapter0;
        Intrinsics.checkNotNullParameter(type0, "type");
        Intrinsics.checkNotNullParameter(set0, "annotations");
        Intrinsics.checkNotNullParameter(moshi0, "moshi");
        if(!set0.isEmpty()) {
            return null;
        }
        Class class0 = _MoshiKotlinTypesExtensionsKt.getRawType(type0);
        if(class0.isInterface()) {
            return null;
        }
        if(class0.isEnum()) {
            return null;
        }
        if(!class0.isAnnotationPresent(KotlinJsonAdapterKt.KOTLIN_METADATA)) {
            return null;
        }
        if(Util.isPlatformType(class0)) {
            return null;
        }
        try {
            jsonAdapter0 = Util.generatedAdapter(moshi0, type0, class0);
        }
        catch(RuntimeException runtimeException0) {
            if(!(runtimeException0.getCause() instanceof ClassNotFoundException)) {
                throw runtimeException0;
            }
            goto label_21;
        }
        if(jsonAdapter0 != null) {
            return jsonAdapter0;
        }
    label_21:
        if(class0.isLocalClass()) {
            throw new IllegalArgumentException(("Cannot serialize local class or object expression " + class0.getName()).toString());
        }
        KClass kClass0 = JvmClassMappingKt.getKotlinClass(class0);
        if(kClass0.isAbstract()) {
            throw new IllegalArgumentException(("Cannot serialize abstract class " + class0.getName()).toString());
        }
        if(kClass0.isInner()) {
            throw new IllegalArgumentException(("Cannot serialize inner class " + class0.getName()).toString());
        }
        if(kClass0.getObjectInstance() != null) {
            throw new IllegalArgumentException(("Cannot serialize object declaration " + class0.getName()).toString());
        }
        if(kClass0.isSealed()) {
            throw new IllegalArgumentException(("Cannot reflectively serialize sealed class " + class0.getName() + ". Please register an adapter.").toString());
        }
        KFunction kFunction0 = KClasses.getPrimaryConstructor(kClass0);
        if(kFunction0 != null) {
            Iterable iterable0 = kFunction0.getParameters();
            Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
            for(Object object0: iterable0) {
                map0.put(((KParameter)object0).getName(), object0);
            }
            KCallablesJvm.setAccessible(kFunction0, true);
            LinkedHashMap linkedHashMap0 = new LinkedHashMap();
            for(Object object1: KClasses.getMemberProperties(kClass0)) {
                KProperty1 kProperty10 = (KProperty1)object1;
                KParameter kParameter0 = (KParameter)map0.get(kProperty10.getName());
                Field field0 = ReflectJvmMapping.getJavaField(kProperty10);
                if(Modifier.isTransient((field0 == null ? 0 : field0.getModifiers()))) {
                    if(kParameter0 != null && !kParameter0.isOptional()) {
                        throw new IllegalArgumentException(("No default value for transient constructor " + kParameter0).toString());
                    }
                    continue;
                }
                if(kParameter0 == null || Intrinsics.areEqual(kParameter0.getType(), kProperty10.getReturnType())) {
                    if(!(kProperty10 instanceof KMutableProperty1) && kParameter0 == null) {
                        continue;
                    }
                    Object object2 = null;
                    KCallablesJvm.setAccessible(kProperty10, true);
                    List list0 = CollectionsKt.toMutableList(kProperty10.getAnnotations());
                    for(Object object3: kProperty10.getAnnotations()) {
                        if(((Annotation)object3) instanceof Json) {
                            object2 = object3;
                            break;
                        }
                    }
                    Json json0 = (Json)object2;
                    if(kParameter0 != null) {
                        CollectionsKt.addAll(list0, kParameter0.getAnnotations());
                        if(json0 == null) {
                            Object object4 = null;
                            for(Object object5: kParameter0.getAnnotations()) {
                                if(((Annotation)object5) instanceof Json) {
                                    object4 = object5;
                                    break;
                                }
                            }
                            json0 = (Json)object4;
                        }
                    }
                    if(json0 == null) {
                        s1 = kProperty10.getName();
                    }
                    else {
                        String s = json0.name();
                        if(s != null) {
                            s1 = s;
                        }
                    }
                    Type type1 = Util.resolve(type0, class0, ReflectJvmMapping.getJavaType(kProperty10.getReturnType()));
                    Object[] arr_object = list0.toArray(new Annotation[0]);
                    if(arr_object == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    JsonAdapter jsonAdapter1 = moshi0.adapter(type1, Util.jsonAnnotations(((Annotation[])arr_object)), kProperty10.getName());
                    String s2 = kProperty10.getName();
                    if(json0 == null) {
                        s4 = s1;
                    }
                    else {
                        String s3 = json0.name();
                        if(s3 != null) {
                            s4 = s3;
                        }
                    }
                    Intrinsics.checkNotNullExpressionValue(jsonAdapter1, "adapter");
                    linkedHashMap0.put(s2, new Binding(s1, s4, jsonAdapter1, kProperty10, kParameter0, (kParameter0 == null ? -1 : kParameter0.getIndex())));
                    continue;
                }
                Intrinsics.checkNotNull(kParameter0);
                throw new IllegalArgumentException(("\'" + kProperty10.getName() + "\' has a constructor parameter of type " + kParameter0.getType() + " but a property of type " + kProperty10.getReturnType() + '.').toString());
            }
            ArrayList arrayList0 = new ArrayList();
            for(Object object6: kFunction0.getParameters()) {
                KParameter kParameter1 = (KParameter)object6;
                String s5 = kParameter1.getName();
                Binding kotlinJsonAdapter$Binding0 = (Binding)TypeIntrinsics.asMutableMap(linkedHashMap0).remove(s5);
                if(kotlinJsonAdapter$Binding0 == null && !kParameter1.isOptional()) {
                    throw new IllegalArgumentException(("No property for required constructor " + kParameter1).toString());
                }
                arrayList0.add(kotlinJsonAdapter$Binding0);
            }
            int v = arrayList0.size();
            Iterator iterator5 = linkedHashMap0.entrySet().iterator();
            while(true) {
                int v1 = v;
                if(!iterator5.hasNext()) {
                    break;
                }
                Object object7 = iterator5.next();
                v = v1 + 1;
                arrayList0.add(Binding.copy$default(((Binding)((Map.Entry)object7).getValue()), null, null, null, null, null, v1, 0x1F, null));
            }
            List list1 = CollectionsKt.filterNotNull(arrayList0);
            ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object8: list1) {
                arrayList1.add(((Binding)object8).getName());
            }
            Object[] arr_object1 = arrayList1.toArray(new String[0]);
            if(arr_object1 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            Options jsonReader$Options0 = Options.of(((String[])Arrays.copyOf(((String[])arr_object1), ((String[])arr_object1).length)));
            Intrinsics.checkNotNullExpressionValue(jsonReader$Options0, "options");
            return new KotlinJsonAdapter(kFunction0, arrayList0, list1, jsonReader$Options0).nullSafe();
        }
        return null;
    }
}

