package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

final class AdapterMethodsFactory implements Factory {
    static abstract class AdapterMethod {
        final Object adapter;
        final int adaptersOffset;
        final Set annotations;
        final JsonAdapter[] jsonAdapters;
        final Method method;
        final boolean nullable;
        final Type type;

        AdapterMethod(Type type0, Set set0, Object object0, Method method0, int v, int v1, boolean z) {
            this.type = Util.canonicalize(type0);
            this.annotations = set0;
            this.adapter = object0;
            this.method = method0;
            this.adaptersOffset = v1;
            this.jsonAdapters = new JsonAdapter[v - v1];
            this.nullable = z;
        }

        public void bind(Moshi moshi0, Factory jsonAdapter$Factory0) {
            if(this.jsonAdapters.length > 0) {
                Type[] arr_type = this.method.getGenericParameterTypes();
                Annotation[][] arr2_annotation = this.method.getParameterAnnotations();
                for(int v = this.adaptersOffset; v < arr_type.length; ++v) {
                    Type type0 = ((ParameterizedType)arr_type[v]).getActualTypeArguments()[0];
                    Set set0 = Util.jsonAnnotations(arr2_annotation[v]);
                    this.jsonAdapters[v - this.adaptersOffset] = !Types.equals(this.type, type0) || !this.annotations.equals(set0) ? moshi0.adapter(type0, set0) : moshi0.nextAdapter(jsonAdapter$Factory0, type0, set0);
                }
            }
        }

        @Nullable
        public Object fromJson(Moshi moshi0, JsonReader jsonReader0) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }

        @Nullable
        protected Object invoke(@Nullable Object object0) throws InvocationTargetException {
            Object[] arr_object = new Object[this.jsonAdapters.length + 1];
            arr_object[0] = object0;
            System.arraycopy(this.jsonAdapters, 0, arr_object, 1, this.jsonAdapters.length);
            try {
                return this.method.invoke(this.adapter, arr_object);
            }
            catch(IllegalAccessException unused_ex) {
                throw new AssertionError();
            }
        }

        protected Object invoke(@Nullable Object object0, @Nullable Object object1) throws InvocationTargetException {
            Object[] arr_object = new Object[this.jsonAdapters.length + 2];
            arr_object[0] = object0;
            arr_object[1] = object1;
            System.arraycopy(this.jsonAdapters, 0, arr_object, 2, this.jsonAdapters.length);
            try {
                return this.method.invoke(this.adapter, arr_object);
            }
            catch(IllegalAccessException unused_ex) {
                throw new AssertionError();
            }
        }

        public void toJson(Moshi moshi0, JsonWriter jsonWriter0, @Nullable Object object0) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }
    }

    private final List fromAdapters;
    private final List toAdapters;

    AdapterMethodsFactory(List list0, List list1) {
        this.toAdapters = list0;
        this.fromAdapters = list1;
    }

    @Override  // com.squareup.moshi.JsonAdapter$Factory
    @Nullable
    public JsonAdapter create(Type type0, Set set0, Moshi moshi0) {
        AdapterMethod adapterMethodsFactory$AdapterMethod0 = AdapterMethodsFactory.get(this.toAdapters, type0, set0);
        AdapterMethod adapterMethodsFactory$AdapterMethod1 = AdapterMethodsFactory.get(this.fromAdapters, type0, set0);
        JsonAdapter jsonAdapter0 = null;
        if(adapterMethodsFactory$AdapterMethod0 == null && adapterMethodsFactory$AdapterMethod1 == null) {
            return null;
        }
        if(adapterMethodsFactory$AdapterMethod0 == null || adapterMethodsFactory$AdapterMethod1 == null) {
            try {
                jsonAdapter0 = moshi0.nextAdapter(this, type0, set0);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new IllegalArgumentException("No " + (adapterMethodsFactory$AdapterMethod0 == null ? "@ToJson" : "@FromJson") + " adapter for " + Util.typeAnnotatedWithAnnotations(type0, set0), illegalArgumentException0);
            }
        }
        if(adapterMethodsFactory$AdapterMethod0 != null) {
            adapterMethodsFactory$AdapterMethod0.bind(moshi0, this);
        }
        if(adapterMethodsFactory$AdapterMethod1 != null) {
            adapterMethodsFactory$AdapterMethod1.bind(moshi0, this);
        }
        return new JsonAdapter() {
            @Override  // com.squareup.moshi.JsonAdapter
            @Nullable
            public Object fromJson(JsonReader jsonReader0) throws IOException {
                AdapterMethod adapterMethodsFactory$AdapterMethod0 = adapterMethodsFactory$AdapterMethod1;
                if(adapterMethodsFactory$AdapterMethod0 == null) {
                    return jsonAdapter0.fromJson(jsonReader0);
                }
                if(!adapterMethodsFactory$AdapterMethod0.nullable && jsonReader0.peek() == Token.NULL) {
                    jsonReader0.nextNull();
                    return null;
                }
                try {
                    return adapterMethodsFactory$AdapterMethod1.fromJson(moshi0, jsonReader0);
                }
                catch(InvocationTargetException invocationTargetException0) {
                    Throwable throwable0 = invocationTargetException0.getCause();
                    if(!(throwable0 instanceof IOException)) {
                        throw new JsonDataException(throwable0 + " at " + "$", throwable0);
                    }
                    throw (IOException)throwable0;
                }
            }

            @Override  // com.squareup.moshi.JsonAdapter
            public void toJson(JsonWriter jsonWriter0, @Nullable Object object0) throws IOException {
                AdapterMethod adapterMethodsFactory$AdapterMethod0 = adapterMethodsFactory$AdapterMethod0;
                if(adapterMethodsFactory$AdapterMethod0 == null) {
                    jsonAdapter0.toJson(jsonWriter0, object0);
                    return;
                }
                if(!adapterMethodsFactory$AdapterMethod0.nullable && object0 == null) {
                    jsonWriter0.nullValue();
                    return;
                }
                try {
                    adapterMethodsFactory$AdapterMethod0.toJson(moshi0, jsonWriter0, object0);
                    return;
                }
                catch(InvocationTargetException invocationTargetException0) {
                    Throwable throwable0 = invocationTargetException0.getCause();
                    if(!(throwable0 instanceof IOException)) {
                        throw new JsonDataException(throwable0 + " at " + "$", throwable0);
                    }
                    throw (IOException)throwable0;
                }
            }

            @Override
            public String toString() {
                return "JsonAdapter" + set0 + "(" + type0 + ")";
            }
        };
    }

    static AdapterMethod fromAdapter(Object object0, Method method0) {
        method0.setAccessible(true);
        Type type0 = method0.getGenericReturnType();
        Set set0 = Util.jsonAnnotations(method0);
        Type[] arr_type = method0.getGenericParameterTypes();
        Annotation[][] arr2_annotation = method0.getParameterAnnotations();
        if(arr_type.length >= 1 && arr_type[0] == JsonReader.class && type0 != Void.TYPE && AdapterMethodsFactory.parametersAreJsonAdapters(1, arr_type)) {
            return new AdapterMethod(type0, set0, object0, method0, arr_type.length, 1, true) {
                @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
                public Object fromJson(Moshi moshi0, JsonReader jsonReader0) throws IOException, InvocationTargetException {
                    return this.invoke(jsonReader0);
                }
            };
        }
        if(arr_type.length != 1 || type0 == Void.TYPE) {
            throw new IllegalArgumentException("Unexpected signature for " + method0 + ".\n@FromJson method signatures may have one of the following structures:\n    <any access modifier> R fromJson(JsonReader jsonReader) throws <any>;\n    <any access modifier> R fromJson(JsonReader jsonReader, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R fromJson(T value) throws <any>;\n");
        }
        Set set1 = Util.jsonAnnotations(arr2_annotation[0]);
        boolean z = Util.hasNullable(arr2_annotation[0]);
        return new AdapterMethod(type0, set0, object0, method0, arr_type.length, 1, z) {
            JsonAdapter delegate;

            @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
            public void bind(Moshi moshi0, Factory jsonAdapter$Factory0) {
                super.bind(moshi0, jsonAdapter$Factory0);
                this.delegate = !Types.equals(arr_type[0], type0) || !set1.equals(set0) ? moshi0.adapter(arr_type[0], set1) : moshi0.nextAdapter(jsonAdapter$Factory0, arr_type[0], set1);
            }

            @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
            public Object fromJson(Moshi moshi0, JsonReader jsonReader0) throws IOException, InvocationTargetException {
                return this.invoke(this.delegate.fromJson(jsonReader0));
            }
        };
    }

    @Nullable
    private static AdapterMethod get(List list0, Type type0, Set set0) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            AdapterMethod adapterMethodsFactory$AdapterMethod0 = (AdapterMethod)list0.get(v1);
            if(Types.equals(adapterMethodsFactory$AdapterMethod0.type, type0) && adapterMethodsFactory$AdapterMethod0.annotations.equals(set0)) {
                return adapterMethodsFactory$AdapterMethod0;
            }
        }
        return null;
    }

    public static AdapterMethodsFactory get(Object object0) {
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        for(Class class0 = object0.getClass(); class0 != Object.class; class0 = class0.getSuperclass()) {
            Method[] arr_method = class0.getDeclaredMethods();
            for(int v = 0; v < arr_method.length; ++v) {
                Method method0 = arr_method[v];
                if(method0.isAnnotationPresent(ToJson.class)) {
                    AdapterMethod adapterMethodsFactory$AdapterMethod0 = AdapterMethodsFactory.toAdapter(object0, method0);
                    AdapterMethod adapterMethodsFactory$AdapterMethod1 = AdapterMethodsFactory.get(arrayList0, adapterMethodsFactory$AdapterMethod0.type, adapterMethodsFactory$AdapterMethod0.annotations);
                    if(adapterMethodsFactory$AdapterMethod1 != null) {
                        throw new IllegalArgumentException("Conflicting @ToJson methods:\n    " + adapterMethodsFactory$AdapterMethod1.method + "\n    " + adapterMethodsFactory$AdapterMethod0.method);
                    }
                    arrayList0.add(adapterMethodsFactory$AdapterMethod0);
                }
                if(method0.isAnnotationPresent(FromJson.class)) {
                    AdapterMethod adapterMethodsFactory$AdapterMethod2 = AdapterMethodsFactory.fromAdapter(object0, method0);
                    AdapterMethod adapterMethodsFactory$AdapterMethod3 = AdapterMethodsFactory.get(arrayList1, adapterMethodsFactory$AdapterMethod2.type, adapterMethodsFactory$AdapterMethod2.annotations);
                    if(adapterMethodsFactory$AdapterMethod3 != null) {
                        throw new IllegalArgumentException("Conflicting @FromJson methods:\n    " + adapterMethodsFactory$AdapterMethod3.method + "\n    " + adapterMethodsFactory$AdapterMethod2.method);
                    }
                    arrayList1.add(adapterMethodsFactory$AdapterMethod2);
                }
            }
        }
        if(arrayList0.isEmpty() && arrayList1.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one @ToJson or @FromJson method on " + object0.getClass().getName());
        }
        return new AdapterMethodsFactory(arrayList0, arrayList1);
    }

    private static boolean parametersAreJsonAdapters(int v, Type[] arr_type) {
        while(v < arr_type.length) {
            Type type0 = arr_type[v];
            if(!(type0 instanceof ParameterizedType)) {
                return false;
            }
            if(((ParameterizedType)type0).getRawType() != JsonAdapter.class) {
                return false;
            }
            ++v;
        }
        return true;
    }

    static AdapterMethod toAdapter(Object object0, Method method0) {
        method0.setAccessible(true);
        Type type0 = method0.getGenericReturnType();
        Type[] arr_type = method0.getGenericParameterTypes();
        Annotation[][] arr2_annotation = method0.getParameterAnnotations();
        if(arr_type.length >= 2 && arr_type[0] == JsonWriter.class && type0 == Void.TYPE && AdapterMethodsFactory.parametersAreJsonAdapters(2, arr_type)) {
            Set set0 = Util.jsonAnnotations(arr2_annotation[1]);
            return new AdapterMethod(arr_type[1], set0, object0, method0, arr_type.length, 2, true) {
                @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
                public void toJson(Moshi moshi0, JsonWriter jsonWriter0, @Nullable Object object0) throws IOException, InvocationTargetException {
                    this.invoke(jsonWriter0, object0);
                }
            };
        }
        if(arr_type.length != 1 || type0 == Void.TYPE) {
            throw new IllegalArgumentException("Unexpected signature for " + method0 + ".\n@ToJson method signatures may have one of the following structures:\n    <any access modifier> void toJson(JsonWriter writer, T value) throws <any>;\n    <any access modifier> void toJson(JsonWriter writer, T value, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R toJson(T value) throws <any>;\n");
        }
        Set set1 = Util.jsonAnnotations(method0);
        Set set2 = Util.jsonAnnotations(arr2_annotation[0]);
        boolean z = Util.hasNullable(arr2_annotation[0]);
        return new AdapterMethod(arr_type[0], set2, object0, method0, arr_type.length, 1, z) {
            private JsonAdapter delegate;

            @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
            public void bind(Moshi moshi0, Factory jsonAdapter$Factory0) {
                super.bind(moshi0, jsonAdapter$Factory0);
                this.delegate = !Types.equals(arr_type[0], type0) || !set2.equals(set1) ? moshi0.adapter(type0, set1) : moshi0.nextAdapter(jsonAdapter$Factory0, type0, set1);
            }

            @Override  // com.squareup.moshi.AdapterMethodsFactory$AdapterMethod
            public void toJson(Moshi moshi0, JsonWriter jsonWriter0, @Nullable Object object0) throws IOException, InvocationTargetException {
                Object object1 = this.invoke(object0);
                this.delegate.toJson(jsonWriter0, object1);
            }
        };
    }
}

