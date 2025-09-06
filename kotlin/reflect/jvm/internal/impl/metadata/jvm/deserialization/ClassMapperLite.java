package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE;
    private static final String kotlin;
    private static final Map map;

    static {
        ClassMapperLite.INSTANCE = new ClassMapperLite();
        ClassMapperLite.kotlin = "kotlin";
        Map map0 = new LinkedHashMap();
        int v1 = ProgressionUtilKt.getProgressionLastElement(0, CollectionsKt.listOf(new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D"}).size() - 1, 2);
        if(v1 >= 0) {
            for(int v2 = 0; true; v2 += 2) {
                map0.put("kotlin" + '/' + "Double", "D");
                map0.put("kotlin" + '/' + "Double" + "Array", "[D");
                if(v2 == v1) {
                    break;
                }
            }
        }
        map0.put("kotlin/Unit", "V");
        ClassMapperLite.map$lambda$0$add(map0, "Any", "java/lang/Object");
        ClassMapperLite.map$lambda$0$add(map0, "Nothing", "java/lang/Void");
        ClassMapperLite.map$lambda$0$add(map0, "Annotation", "java/lang/annotation/Annotation");
        Iterator iterator0 = CollectionsKt.listOf(new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"}).iterator();
        while(iterator0.hasNext()) {
            ClassMapperLite.map$lambda$0$add(map0, "Enum", "java/lang/Enum");
        }
        Iterator iterator1 = CollectionsKt.listOf(new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"}).iterator();
        while(iterator1.hasNext()) {
            ClassMapperLite.map$lambda$0$add(map0, "collections/ListIterator", "java/util/ListIterator");
            ClassMapperLite.map$lambda$0$add(map0, "collections/MutableListIterator", "java/util/ListIterator");
        }
        ClassMapperLite.map$lambda$0$add(map0, "collections/Iterable", "java/lang/Iterable");
        ClassMapperLite.map$lambda$0$add(map0, "collections/MutableIterable", "java/lang/Iterable");
        ClassMapperLite.map$lambda$0$add(map0, "collections/Map.Entry", "java/util/Map$Entry");
        ClassMapperLite.map$lambda$0$add(map0, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for(int v = 0; v < 23; ++v) {
            ClassMapperLite.map$lambda$0$add(map0, "Function" + v, "kotlin/jvm/functions/Function" + v);
            ClassMapperLite.map$lambda$0$add(map0, "reflect/KFunction" + v, "kotlin/reflect/KFunction");
        }
        Iterator iterator2 = CollectionsKt.listOf(new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"}).iterator();
        while(iterator2.hasNext()) {
            ClassMapperLite.map$lambda$0$add(map0, "Enum.Companion", "kotlin/jvm/internal/EnumCompanionObject");
        }
        ClassMapperLite.map = map0;
    }

    private static final void map$lambda$0$add(Map map0, String s, String s1) {
        map0.put("kotlin" + '/' + s, "L" + s1 + ';');
    }

    @JvmStatic
    public static final String mapClass(String s) {
        Intrinsics.checkNotNullParameter(s, "classId");
        String s1 = (String)ClassMapperLite.map.get(s);
        return s1 == null ? "L" + StringsKt.replace$default(s, '.', '$', false, 4, null) + ';' : s1;
    }
}

