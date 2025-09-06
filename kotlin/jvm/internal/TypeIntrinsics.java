package kotlin.jvm.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.jvm.internal.markers.KMutableMap.Entry;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.jvm.internal.markers.KMutableSet;

public class TypeIntrinsics {
    public static Collection asMutableCollection(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableCollection)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableCollection");
        }
        return TypeIntrinsics.castToCollection(object0);
    }

    public static Collection asMutableCollection(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableCollection)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToCollection(object0);
    }

    public static Iterable asMutableIterable(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableIterable)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableIterable");
        }
        return TypeIntrinsics.castToIterable(object0);
    }

    public static Iterable asMutableIterable(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableIterable)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToIterable(object0);
    }

    public static Iterator asMutableIterator(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableIterator)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableIterator");
        }
        return TypeIntrinsics.castToIterator(object0);
    }

    public static Iterator asMutableIterator(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableIterator)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToIterator(object0);
    }

    public static List asMutableList(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableList)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableList");
        }
        return TypeIntrinsics.castToList(object0);
    }

    public static List asMutableList(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableList)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToList(object0);
    }

    public static ListIterator asMutableListIterator(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableListIterator)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableListIterator");
        }
        return TypeIntrinsics.castToListIterator(object0);
    }

    public static ListIterator asMutableListIterator(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableListIterator)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToListIterator(object0);
    }

    public static Map asMutableMap(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableMap)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableMap");
        }
        return TypeIntrinsics.castToMap(object0);
    }

    public static Map asMutableMap(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableMap)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToMap(object0);
    }

    public static Map.Entry asMutableMapEntry(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof Entry)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableMap.MutableEntry");
        }
        return TypeIntrinsics.castToMapEntry(object0);
    }

    public static Map.Entry asMutableMapEntry(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof Entry)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToMapEntry(object0);
    }

    public static Set asMutableSet(Object object0) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableSet)) {
            TypeIntrinsics.throwCce(object0, "kotlin.collections.MutableSet");
        }
        return TypeIntrinsics.castToSet(object0);
    }

    public static Set asMutableSet(Object object0, String s) {
        if(object0 instanceof KMappedMarker && !(object0 instanceof KMutableSet)) {
            TypeIntrinsics.throwCce(s);
        }
        return TypeIntrinsics.castToSet(object0);
    }

    public static Object beforeCheckcastToFunctionOfArity(Object object0, int v) {
        if(object0 != null && !TypeIntrinsics.isFunctionOfArity(object0, v)) {
            TypeIntrinsics.throwCce(object0, "kotlin.jvm.functions.Function" + v);
        }
        return object0;
    }

    public static Object beforeCheckcastToFunctionOfArity(Object object0, int v, String s) {
        if(object0 != null && !TypeIntrinsics.isFunctionOfArity(object0, v)) {
            TypeIntrinsics.throwCce(s);
        }
        return object0;
    }

    public static Collection castToCollection(Object object0) {
        try {
            return (Collection)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static Iterable castToIterable(Object object0) {
        try {
            return (Iterable)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static Iterator castToIterator(Object object0) {
        try {
            return (Iterator)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static List castToList(Object object0) {
        try {
            return (List)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static ListIterator castToListIterator(Object object0) {
        try {
            return (ListIterator)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static Map castToMap(Object object0) {
        try {
            return (Map)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static Map.Entry castToMapEntry(Object object0) {
        try {
            return (Map.Entry)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static Set castToSet(Object object0) {
        try {
            return (Set)object0;
        }
        catch(ClassCastException classCastException0) {
            throw TypeIntrinsics.throwCce(classCastException0);
        }
    }

    public static int getFunctionArity(Object object0) {
        if(object0 instanceof FunctionBase) {
            return ((FunctionBase)object0).getArity();
        }
        if(object0 instanceof Function0) {
            return 0;
        }
        if(object0 instanceof Function1) {
            return 1;
        }
        if(object0 instanceof Function2) {
            return 2;
        }
        if(object0 instanceof Function3) {
            return 3;
        }
        if(object0 instanceof Function4) {
            return 4;
        }
        if(object0 instanceof Function5) {
            return 5;
        }
        if(object0 instanceof Function6) {
            return 6;
        }
        if(object0 instanceof Function7) {
            return 7;
        }
        if(object0 instanceof Function8) {
            return 8;
        }
        if(object0 instanceof Function9) {
            return 9;
        }
        if(object0 instanceof Function10) {
            return 10;
        }
        if(object0 instanceof Function11) {
            return 11;
        }
        if(object0 instanceof Function12) {
            return 12;
        }
        if(object0 instanceof Function13) {
            return 13;
        }
        if(object0 instanceof Function14) {
            return 14;
        }
        if(object0 instanceof Function15) {
            return 15;
        }
        if(object0 instanceof Function16) {
            return 16;
        }
        if(object0 instanceof Function17) {
            return 17;
        }
        if(object0 instanceof Function18) {
            return 18;
        }
        if(object0 instanceof Function19) {
            return 19;
        }
        if(object0 instanceof Function20) {
            return 20;
        }
        if(object0 instanceof Function21) {
            return 21;
        }
        return object0 instanceof Function22 ? 22 : -1;
    }

    public static boolean isFunctionOfArity(Object object0, int v) {
        return object0 instanceof Function && TypeIntrinsics.getFunctionArity(object0) == v;
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableCollection(Object object0) {
        return object0 instanceof Collection && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableCollection);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableIterable(Object object0) {
        return object0 instanceof Iterable && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableIterable);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableIterator(Object object0) {
        return object0 instanceof Iterator && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableIterator);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableList(Object object0) {
        return object0 instanceof List && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableList);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableListIterator(Object object0) {
        return object0 instanceof ListIterator && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableListIterator);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableMap(Object object0) {
        return object0 instanceof Map && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableMap);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableMapEntry(Object object0) {
        return object0 instanceof Map.Entry && (!(object0 instanceof KMappedMarker) || object0 instanceof Entry);
    }

    // 去混淆评级： 低(30)
    public static boolean isMutableSet(Object object0) {
        return object0 instanceof Set && (!(object0 instanceof KMappedMarker) || object0 instanceof KMutableSet);
    }

    // 去混淆评级： 低(20)
    private static Throwable sanitizeStackTrace(Throwable throwable0) {
        return Intrinsics.sanitizeStackTrace(throwable0, "kotlin.jvm.internal.TypeIntrinsics");
    }

    public static ClassCastException throwCce(ClassCastException classCastException0) {
        throw (ClassCastException)TypeIntrinsics.sanitizeStackTrace(classCastException0);
    }

    public static void throwCce(Object object0, String s) {
        TypeIntrinsics.throwCce(((object0 == null ? "null" : object0.getClass().getName()) + " cannot be cast to " + s));
    }

    public static void throwCce(String s) {
        throw TypeIntrinsics.throwCce(new ClassCastException(s));
    }
}

