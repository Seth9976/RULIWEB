package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
    public static class Kotlin {
    }

    public static boolean areEqual(double f, Double double0) {
        return double0 != null && f == ((double)double0);
    }

    public static boolean areEqual(float f, Float float0) {
        return float0 != null && f == ((float)float0);
    }

    public static boolean areEqual(Double double0, double f) {
        return double0 != null && ((double)double0) == f;
    }

    public static boolean areEqual(Double double0, Double double1) {
        return double0 == null ? double1 == null : double1 != null && ((double)double0) == ((double)double1);
    }

    public static boolean areEqual(Float float0, float f) {
        return float0 != null && ((float)float0) == f;
    }

    public static boolean areEqual(Float float0, Float float1) {
        return float0 == null ? float1 == null : float1 != null && ((float)float0) == ((float)float1);
    }

    public static boolean areEqual(Object object0, Object object1) {
        return object0 == null ? object1 == null : object0.equals(object1);
    }

    public static void checkExpressionValueIsNotNull(Object object0, String s) {
        if(object0 == null) {
            throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException(s + " must not be null"));
        }
    }

    public static void checkFieldIsNotNull(Object object0, String s) {
        if(object0 == null) {
            throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException(s));
        }
    }

    public static void checkFieldIsNotNull(Object object0, String s, String s1) {
        if(object0 == null) {
            throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException("Field specified as non-null is null: " + s + "." + s1));
        }
    }

    public static void checkHasClass(String s) throws ClassNotFoundException {
        String s1 = s.replace('/', '.');
        try {
            Class.forName(s1);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw (ClassNotFoundException)Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + s1 + " is not found. Please update the Kotlin runtime to the latest version", classNotFoundException0));
        }
    }

    public static void checkHasClass(String s, String s1) throws ClassNotFoundException {
        String s2 = s.replace('/', '.');
        try {
            Class.forName(s2);
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw (ClassNotFoundException)Intrinsics.sanitizeStackTrace(new ClassNotFoundException("Class " + s2 + " is not found: this code requires the Kotlin runtime of version at least " + s1, classNotFoundException0));
        }
    }

    public static void checkNotNull(Object object0) [...] // 潜在的解密器

    public static void checkNotNull(Object object0, String s) [...] // 潜在的解密器

    public static void checkNotNullExpressionValue(Object object0, String s) [...] // 潜在的解密器

    public static void checkNotNullParameter(Object object0, String s) {
        if(object0 == null) {
            Intrinsics.throwParameterIsNullNPE(s);
        }
    }

    public static void checkParameterIsNotNull(Object object0, String s) {
        if(object0 == null) {
            Intrinsics.throwParameterIsNullIAE(s);
        }
    }

    public static void checkReturnedValueIsNotNull(Object object0, String s) {
        if(object0 == null) {
            throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException(s));
        }
    }

    public static void checkReturnedValueIsNotNull(Object object0, String s, String s1) {
        if(object0 == null) {
            throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException("Method specified as non-null returned null: " + s + "." + s1));
        }
    }

    public static int compare(int v, int v1) {
        if(v < v1) {
            return -1;
        }
        return v == v1 ? 0 : 1;
    }

    public static int compare(long v, long v1) {
        int v2 = Long.compare(v, v1);
        if(v2 < 0) {
            return -1;
        }
        return v2 == 0 ? 0 : 1;
    }

    private static String createParameterIsNullExceptionMessage(String s) {
        StackTraceElement[] arr_stackTraceElement = Thread.currentThread().getStackTrace();
        int v;
        for(v = 0; !arr_stackTraceElement[v].getClassName().equals("kotlin.jvm.internal.Intrinsics"); ++v) {
        }
        while(arr_stackTraceElement[v].getClassName().equals("kotlin.jvm.internal.Intrinsics")) {
            ++v;
        }
        StackTraceElement stackTraceElement0 = arr_stackTraceElement[v];
        return "Parameter specified as non-null is null: method " + stackTraceElement0.getClassName() + "." + stackTraceElement0.getMethodName() + ", parameter " + s;
    }

    public static void needClassReification() {
        Intrinsics.throwUndefinedForReified();
    }

    public static void needClassReification(String s) {
        Intrinsics.throwUndefinedForReified(s);
    }

    public static void reifiedOperationMarker(int v, String s) {
        Intrinsics.throwUndefinedForReified();
    }

    public static void reifiedOperationMarker(int v, String s, String s1) {
        Intrinsics.throwUndefinedForReified(s1);
    }

    // 去混淆评级： 低(20)
    private static Throwable sanitizeStackTrace(Throwable throwable0) {
        return Intrinsics.sanitizeStackTrace(throwable0, "kotlin.jvm.internal.Intrinsics");
    }

    static Throwable sanitizeStackTrace(Throwable throwable0, String s) {
        StackTraceElement[] arr_stackTraceElement = throwable0.getStackTrace();
        int v = -1;
        for(int v1 = 0; v1 < arr_stackTraceElement.length; ++v1) {
            if(s.equals(arr_stackTraceElement[v1].getClassName())) {
                v = v1;
            }
        }
        throwable0.setStackTrace(((StackTraceElement[])Arrays.copyOfRange(arr_stackTraceElement, v + 1, arr_stackTraceElement.length)));
        return throwable0;
    }

    public static String stringPlus(String s, Object object0) [...] // Inlined contents

    public static void throwAssert() {
        throw (AssertionError)Intrinsics.sanitizeStackTrace(new AssertionError());
    }

    public static void throwAssert(String s) {
        throw (AssertionError)Intrinsics.sanitizeStackTrace(new AssertionError(s));
    }

    public static void throwIllegalArgument() {
        throw (IllegalArgumentException)Intrinsics.sanitizeStackTrace(new IllegalArgumentException());
    }

    public static void throwIllegalArgument(String s) {
        throw (IllegalArgumentException)Intrinsics.sanitizeStackTrace(new IllegalArgumentException(s));
    }

    public static void throwIllegalState() {
        throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException());
    }

    public static void throwIllegalState(String s) {
        throw (IllegalStateException)Intrinsics.sanitizeStackTrace(new IllegalStateException(s));
    }

    public static void throwJavaNpe() {
        throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException());
    }

    public static void throwJavaNpe(String s) {
        throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException(s));
    }

    public static void throwNpe() {
        throw (KotlinNullPointerException)Intrinsics.sanitizeStackTrace(new KotlinNullPointerException());
    }

    public static void throwNpe(String s) {
        throw (KotlinNullPointerException)Intrinsics.sanitizeStackTrace(new KotlinNullPointerException(s));
    }

    private static void throwParameterIsNullIAE(String s) {
        throw (IllegalArgumentException)Intrinsics.sanitizeStackTrace(new IllegalArgumentException(Intrinsics.createParameterIsNullExceptionMessage(s)));
    }

    private static void throwParameterIsNullNPE(String s) {
        throw (NullPointerException)Intrinsics.sanitizeStackTrace(new NullPointerException(Intrinsics.createParameterIsNullExceptionMessage(s)));
    }

    public static void throwUndefinedForReified() {
        Intrinsics.throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void throwUndefinedForReified(String s) {
        throw new UnsupportedOperationException(s);
    }

    public static void throwUninitializedProperty(String s) {
        throw (UninitializedPropertyAccessException)Intrinsics.sanitizeStackTrace(new UninitializedPropertyAccessException(s));
    }

    public static void throwUninitializedPropertyAccessException(String s) {
        Intrinsics.throwUninitializedProperty(("lateinit property " + s + " has not been initialized"));
    }
}

