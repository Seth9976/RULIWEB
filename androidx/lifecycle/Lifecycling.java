package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\r\u001A\u00020\f2\u000E\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000B2\u0006\u0010\u000F\u001A\u00020\u0001H\u0002J\u001E\u0010\u0010\u001A\f\u0012\u0006\b\u0001\u0012\u00020\f\u0018\u00010\u000B2\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\bH\u0002J\u0010\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0013H\u0007J\u0014\u0010\u0015\u001A\u00020\u00042\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\bH\u0002J\u0016\u0010\u0016\u001A\u00020\u00172\f\u0010\u0011\u001A\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0002J\u0010\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u000F\u001A\u00020\u0001H\u0007J\u0014\u0010\u001A\u001A\u00020\u00042\n\u0010\u0011\u001A\u0006\u0012\u0002\b\u00030\bH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001E\u0010\u0006\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\t\u001A \u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\f0\u000B0\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Landroidx/lifecycle/Lifecycling;", "", "()V", "GENERATED_CALLBACK", "", "REFLECTIVE_CALLBACK", "callbackCache", "", "Ljava/lang/Class;", "classToAdapters", "", "Ljava/lang/reflect/Constructor;", "Landroidx/lifecycle/GeneratedAdapter;", "createGeneratedAdapter", "constructor", "object", "generatedConstructor", "klass", "getAdapterName", "", "className", "getObserverConstructorType", "isLifecycleParent", "", "lifecycleEventObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "resolveObserverCallbackType", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Lifecycling {
    private static final int GENERATED_CALLBACK = 2;
    public static final Lifecycling INSTANCE = null;
    private static final int REFLECTIVE_CALLBACK = 1;
    private static final Map callbackCache;
    private static final Map classToAdapters;

    static {
        Lifecycling.INSTANCE = new Lifecycling();
        Lifecycling.callbackCache = new HashMap();
        Lifecycling.classToAdapters = new HashMap();
    }

    private final GeneratedAdapter createGeneratedAdapter(Constructor constructor0, Object object0) {
        try {
            Object object1 = constructor0.newInstance(object0);
            Intrinsics.checkNotNullExpressionValue(object1, "{\n            constructo…tance(`object`)\n        }");
            return (GeneratedAdapter)object1;
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException(illegalAccessException0);
        }
        catch(InstantiationException instantiationException0) {
            throw new RuntimeException(instantiationException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }

    private final Constructor generatedConstructor(Class class0) {
        try {
            Package package0 = class0.getPackage();
            String s = class0.getCanonicalName();
            String s1 = package0 == null ? "" : package0.getName();
            Intrinsics.checkNotNullExpressionValue(s1, "fullPackage");
            if(s1.length() != 0) {
                Intrinsics.checkNotNullExpressionValue(s, "name");
                s = s.substring(s1.length() + 1);
                Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String).substring(startIndex)");
            }
            Intrinsics.checkNotNullExpressionValue(s, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String s2 = Lifecycling.getAdapterName(s);
            if(s1.length() != 0) {
                s2 = s1 + '.' + s2;
            }
            Class class1 = Class.forName(s2);
            Intrinsics.checkNotNull(class1, "null cannot be cast to non-null type java.lang.Class<out androidx.lifecycle.GeneratedAdapter>");
            Constructor constructor0 = class1.getDeclaredConstructor(class0);
            if(!constructor0.isAccessible()) {
                constructor0.setAccessible(true);
            }
            return constructor0;
        }
        catch(ClassNotFoundException unused_ex) {
            return null;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new RuntimeException(noSuchMethodException0);
        }
    }

    @JvmStatic
    public static final String getAdapterName(String s) {
        Intrinsics.checkNotNullParameter(s, "className");
        return StringsKt.replace$default(s, ".", "_", false, 4, null) + "_LifecycleAdapter";
    }

    private final int getObserverConstructorType(Class class0) {
        Map map0 = Lifecycling.callbackCache;
        Integer integer0 = (Integer)map0.get(class0);
        if(integer0 != null) {
            return (int)integer0;
        }
        int v = this.resolveObserverCallbackType(class0);
        map0.put(class0, v);
        return v;
    }

    private final boolean isLifecycleParent(Class class0) {
        return class0 != null && LifecycleObserver.class.isAssignableFrom(class0);
    }

    @JvmStatic
    public static final LifecycleEventObserver lifecycleEventObserver(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "object");
        if(object0 instanceof LifecycleEventObserver && object0 instanceof DefaultLifecycleObserver) {
            return new DefaultLifecycleObserverAdapter(((DefaultLifecycleObserver)object0), ((LifecycleEventObserver)object0));
        }
        if(object0 instanceof DefaultLifecycleObserver) {
            return new DefaultLifecycleObserverAdapter(((DefaultLifecycleObserver)object0), null);
        }
        if(object0 instanceof LifecycleEventObserver) {
            return (LifecycleEventObserver)object0;
        }
        Class class0 = object0.getClass();
        Lifecycling lifecycling0 = Lifecycling.INSTANCE;
        if(lifecycling0.getObserverConstructorType(class0) == 2) {
            Object object1 = Lifecycling.classToAdapters.get(class0);
            Intrinsics.checkNotNull(object1);
            if(((List)object1).size() == 1) {
                return new SingleGeneratedAdapterObserver(lifecycling0.createGeneratedAdapter(((Constructor)((List)object1).get(0)), object0));
            }
            int v1 = ((List)object1).size();
            GeneratedAdapter[] arr_generatedAdapter = new GeneratedAdapter[v1];
            for(int v = 0; v < v1; ++v) {
                Constructor constructor0 = (Constructor)((List)object1).get(v);
                arr_generatedAdapter[v] = Lifecycling.INSTANCE.createGeneratedAdapter(constructor0, object0);
            }
            return new CompositeGeneratedAdaptersObserver(arr_generatedAdapter);
        }
        return new ReflectiveGenericLifecycleObserver(object0);
    }

    private final int resolveObserverCallbackType(Class class0) {
        List list1;
        if(class0.getCanonicalName() == null) {
            return 1;
        }
        Constructor constructor0 = this.generatedConstructor(class0);
        if(constructor0 != null) {
            List list0 = CollectionsKt.listOf(constructor0);
            Lifecycling.classToAdapters.put(class0, list0);
            return 2;
        }
        if(ClassesInfoCache.sInstance.hasLifecycleMethods(class0)) {
            return 1;
        }
        Class class1 = class0.getSuperclass();
        if(this.isLifecycleParent(class1)) {
            Intrinsics.checkNotNullExpressionValue(class1, "superclass");
            if(this.getObserverConstructorType(class1) == 1) {
                return 1;
            }
            Object object0 = Lifecycling.classToAdapters.get(class1);
            Intrinsics.checkNotNull(object0);
            list1 = new ArrayList(((Collection)object0));
        }
        else {
            list1 = null;
        }
        Class[] arr_class = class0.getInterfaces();
        Intrinsics.checkNotNullExpressionValue(arr_class, "klass.interfaces");
        for(int v = 0; v < arr_class.length; ++v) {
            Class class2 = arr_class[v];
            if(this.isLifecycleParent(class2)) {
                Intrinsics.checkNotNullExpressionValue(class2, "intrface");
                if(this.getObserverConstructorType(class2) == 1) {
                    return 1;
                }
                if(list1 == null) {
                    list1 = new ArrayList();
                }
                Object object1 = Lifecycling.classToAdapters.get(class2);
                Intrinsics.checkNotNull(object1);
                list1.addAll(((Collection)object1));
            }
        }
        if(list1 != null) {
            Lifecycling.classToAdapters.put(class0, list1);
            return 2;
        }
        return 1;
    }
}

