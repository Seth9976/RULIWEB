package kotlin.reflect.jvm.internal.impl.name;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class FqNamesUtilKt {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[State.values().length];
            try {
                arr_v[State.BEGINNING.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.AFTER_DOT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[State.MIDDLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Object findValueForMostSpecificFqname(FqName fqName0, Map map0) {
        Intrinsics.checkNotNullParameter(fqName0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "values");
        Map map1 = new LinkedHashMap();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            FqName fqName1 = (FqName)map$Entry0.getKey();
            if(Intrinsics.areEqual(fqName0, fqName1) || FqNamesUtilKt.isChildOf(fqName0, fqName1)) {
                map1.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        if(map1.isEmpty()) {
            map1 = null;
        }
        if(map1 == null) {
            return null;
        }
        Iterator iterator1 = map1.entrySet().iterator();
        if(!iterator1.hasNext()) {
            return null;
        }
        Object object1 = iterator1.next();
        if(iterator1.hasNext()) {
            int v = FqNamesUtilKt.tail(((FqName)((Map.Entry)object1).getKey()), fqName0).asString().length();
            while(true) {
                Object object2 = iterator1.next();
                int v1 = FqNamesUtilKt.tail(((FqName)((Map.Entry)object2).getKey()), fqName0).asString().length();
                if(v > v1) {
                    object1 = object2;
                    v = v1;
                }
                if(!iterator1.hasNext()) {
                    break;
                }
            }
        }
        return ((Map.Entry)object1) == null ? null : ((Map.Entry)object1).getValue();
    }

    public static final boolean isChildOf(FqName fqName0, FqName fqName1) {
        Intrinsics.checkNotNullParameter(fqName0, "<this>");
        Intrinsics.checkNotNullParameter(fqName1, "packageName");
        return Intrinsics.areEqual(FqNamesUtilKt.parentOrNull(fqName0), fqName1);
    }

    private static final boolean isSubpackageOf(String s, String s1) {
        return StringsKt.startsWith$default(s, s1, false, 2, null) && s.charAt(s1.length()) == 46;
    }

    public static final boolean isSubpackageOf(FqName fqName0, FqName fqName1) {
        Intrinsics.checkNotNullParameter(fqName0, "<this>");
        Intrinsics.checkNotNullParameter(fqName1, "packageName");
        if(Intrinsics.areEqual(fqName0, fqName1)) {
            return true;
        }
        if(fqName1.isRoot()) {
            return true;
        }
        String s = fqName0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "this.asString()");
        String s1 = fqName1.asString();
        Intrinsics.checkNotNullExpressionValue(s1, "packageName.asString()");
        return FqNamesUtilKt.isSubpackageOf(s, s1);
    }

    public static final boolean isValidJavaFqName(String s) {
        if(s == null) {
            return false;
        }
        State state0 = State.BEGINNING;
        for(int v = 0; v < s.length(); ++v) {
            int v1 = s.charAt(v);
            switch(WhenMappings.$EnumSwitchMapping$0[state0.ordinal()]) {
                case 1: 
                case 2: {
                    if(!Character.isJavaIdentifierStart(((char)v1))) {
                        return false;
                    }
                    state0 = State.MIDDLE;
                    break;
                }
                case 3: {
                    if(v1 == 46) {
                        state0 = State.AFTER_DOT;
                    }
                    else if(!Character.isJavaIdentifierPart(((char)v1))) {
                        return false;
                    }
                }
            }
        }
        return state0 != State.AFTER_DOT;
    }

    public static final FqName parentOrNull(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "<this>");
        return fqName0.isRoot() ? null : fqName0.parent();
    }

    public static final FqName tail(FqName fqName0, FqName fqName1) {
        Intrinsics.checkNotNullParameter(fqName0, "<this>");
        Intrinsics.checkNotNullParameter(fqName1, "prefix");
        if(FqNamesUtilKt.isSubpackageOf(fqName0, fqName1) && !fqName1.isRoot()) {
            if(Intrinsics.areEqual(fqName0, fqName1)) {
                Intrinsics.checkNotNullExpressionValue(FqName.ROOT, "ROOT");
                return FqName.ROOT;
            }
            String s = fqName0.asString();
            Intrinsics.checkNotNullExpressionValue(s, "asString()");
            String s1 = s.substring(fqName1.asString().length() + 1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            return new FqName(s1);
        }
        return fqName0;
    }
}

