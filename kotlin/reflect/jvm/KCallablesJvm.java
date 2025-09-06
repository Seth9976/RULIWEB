package kotlin.reflect.jvm;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty.Setter;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty.Getter;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.UtilKt;
import kotlin.reflect.jvm.internal.calls.Caller;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\",\u0010\u0002\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0000\u001A\u00020\u00018F@FX\u0086\u000E¢\u0006\f\u001A\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"value", "", "isAccessible", "Lkotlin/reflect/KCallable;", "(Lkotlin/reflect/KCallable;)Z", "setAccessible", "(Lkotlin/reflect/KCallable;Z)V", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KCallablesJvm {
    public static final boolean isAccessible(KCallable kCallable0) {
        Member member0;
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        if(kCallable0 instanceof KMutableProperty) {
            Field field0 = ReflectJvmMapping.getJavaField(((KProperty)kCallable0));
            if((field0 == null ? true : field0.isAccessible())) {
                Method method0 = ReflectJvmMapping.getJavaGetter(((KProperty)kCallable0));
                if((method0 == null ? true : method0.isAccessible())) {
                    Method method1 = ReflectJvmMapping.getJavaSetter(((KMutableProperty)kCallable0));
                    return method1 == null ? true : method1.isAccessible();
                }
            }
            return false;
        }
        if(kCallable0 instanceof KProperty) {
            Field field1 = ReflectJvmMapping.getJavaField(((KProperty)kCallable0));
            if((field1 == null ? true : field1.isAccessible())) {
                Method method2 = ReflectJvmMapping.getJavaGetter(((KProperty)kCallable0));
                return method2 == null ? true : method2.isAccessible();
            }
            return false;
        }
        if(kCallable0 instanceof Getter) {
            Field field2 = ReflectJvmMapping.getJavaField(((Getter)kCallable0).getProperty());
            if((field2 == null ? true : field2.isAccessible())) {
                Method method3 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
                return method3 == null ? true : method3.isAccessible();
            }
            return false;
        }
        if(kCallable0 instanceof Setter) {
            Field field3 = ReflectJvmMapping.getJavaField(((Setter)kCallable0).getProperty());
            if((field3 == null ? true : field3.isAccessible())) {
                Method method4 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
                return method4 == null ? true : method4.isAccessible();
            }
            return false;
        }
        if(!(kCallable0 instanceof KFunction)) {
            throw new UnsupportedOperationException("Unknown callable: " + kCallable0 + " (" + kCallable0.getClass() + ')');
        }
        Method method5 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
        if((method5 == null ? true : method5.isAccessible())) {
            KCallableImpl kCallableImpl0 = UtilKt.asKCallableImpl(kCallable0);
            AccessibleObject accessibleObject0 = null;
            if(kCallableImpl0 == null) {
                member0 = null;
            }
            else {
                Caller caller0 = kCallableImpl0.getDefaultCaller();
                member0 = caller0 == null ? null : caller0.getMember();
            }
            if(member0 instanceof AccessibleObject) {
                accessibleObject0 = (AccessibleObject)member0;
            }
            if((accessibleObject0 == null ? true : accessibleObject0.isAccessible())) {
                Constructor constructor0 = ReflectJvmMapping.getJavaConstructor(((KFunction)kCallable0));
                return constructor0 == null ? true : constructor0.isAccessible();
            }
        }
        return false;
    }

    public static final void setAccessible(KCallable kCallable0, boolean z) {
        Member member0;
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        if(kCallable0 instanceof KMutableProperty) {
            Field field0 = ReflectJvmMapping.getJavaField(((KProperty)kCallable0));
            if(field0 != null) {
                field0.setAccessible(z);
            }
            Method method0 = ReflectJvmMapping.getJavaGetter(((KProperty)kCallable0));
            if(method0 != null) {
                method0.setAccessible(z);
            }
            Method method1 = ReflectJvmMapping.getJavaSetter(((KMutableProperty)kCallable0));
            if(method1 == null) {
                return;
            }
            method1.setAccessible(z);
            return;
        }
        if(kCallable0 instanceof KProperty) {
            Field field1 = ReflectJvmMapping.getJavaField(((KProperty)kCallable0));
            if(field1 != null) {
                field1.setAccessible(z);
            }
            Method method2 = ReflectJvmMapping.getJavaGetter(((KProperty)kCallable0));
            if(method2 == null) {
                return;
            }
            method2.setAccessible(z);
            return;
        }
        if(kCallable0 instanceof Getter) {
            Field field2 = ReflectJvmMapping.getJavaField(((Getter)kCallable0).getProperty());
            if(field2 != null) {
                field2.setAccessible(z);
            }
            Method method3 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
            if(method3 == null) {
                return;
            }
            method3.setAccessible(z);
            return;
        }
        if(kCallable0 instanceof Setter) {
            Field field3 = ReflectJvmMapping.getJavaField(((Setter)kCallable0).getProperty());
            if(field3 != null) {
                field3.setAccessible(z);
            }
            Method method4 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
            if(method4 == null) {
                return;
            }
            method4.setAccessible(z);
            return;
        }
        if(!(kCallable0 instanceof KFunction)) {
            throw new UnsupportedOperationException("Unknown callable: " + kCallable0 + " (" + kCallable0.getClass() + ')');
        }
        Method method5 = ReflectJvmMapping.getJavaMethod(((KFunction)kCallable0));
        if(method5 != null) {
            method5.setAccessible(z);
        }
        KCallableImpl kCallableImpl0 = UtilKt.asKCallableImpl(kCallable0);
        AccessibleObject accessibleObject0 = null;
        if(kCallableImpl0 == null) {
            member0 = null;
        }
        else {
            Caller caller0 = kCallableImpl0.getDefaultCaller();
            member0 = caller0 == null ? null : caller0.getMember();
        }
        if(member0 instanceof AccessibleObject) {
            accessibleObject0 = (AccessibleObject)member0;
        }
        if(accessibleObject0 != null) {
            accessibleObject0.setAccessible(true);
        }
        Constructor constructor0 = ReflectJvmMapping.getJavaConstructor(((KFunction)kCallable0));
        if(constructor0 == null) {
            return;
        }
        constructor0.setAccessible(z);
    }
}

