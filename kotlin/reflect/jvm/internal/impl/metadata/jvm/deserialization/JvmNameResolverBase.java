package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record.Operation;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.StringTableTypes.Record;
import kotlin.text.StringsKt;

public class JvmNameResolverBase implements NameResolver {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Operation.values().length];
            try {
                arr_v[Operation.NONE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Operation.INTERNAL_TO_CLASS_ID.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Operation.DESC_TO_CLASS_ID.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Companion Companion;
    private static final List PREDEFINED_STRINGS;
    private static final Map PREDEFINED_STRINGS_MAP;
    private static final String kotlin;
    private final Set localNameIndices;
    private final List records;
    private final String[] strings;

    // 去混淆评级： 中等(154)
    static {
        JvmNameResolverBase.Companion = new Companion(null);
        List list0 = CollectionsKt.listOf(new String[]{"kotlin/Any", "kotlin/Nothing", "kotlin/Unit", "kotlin/Throwable", "kotlin/Number", "kotlin/Byte", "kotlin/Double", "kotlin/Float", "kotlin/Int", "kotlin/Long", "kotlin/Short", "kotlin/Boolean", "kotlin/Char", "kotlin/CharSequence", "kotlin/String", "kotlin/Comparable", "kotlin/Enum", "kotlin/Array", "kotlin/ByteArray", "kotlin/DoubleArray", "kotlin/FloatArray", "kotlin/IntArray", "kotlin/LongArray", "kotlin/ShortArray", "kotlin/BooleanArray", "kotlin/CharArray", "kotlin/Cloneable", "kotlin/Annotation", "kotlin/collections/Iterable", "kotlin/collections/MutableIterable", "kotlin/collections/Collection", "kotlin/collections/MutableCollection", "kotlin/collections/List", "kotlin/collections/MutableList", "kotlin/collections/Set", "kotlin/collections/MutableSet", "kotlin/collections/Map", "kotlin/collections/MutableMap", "kotlin/collections/Map.Entry", "kotlin/collections/MutableMap.MutableEntry", "kotlin/collections/Iterator", "kotlin/collections/MutableIterator", "kotlin/collections/ListIterator", "kotlin/collections/MutableListIterator"});
        JvmNameResolverBase.PREDEFINED_STRINGS = list0;
        Iterable iterable0 = CollectionsKt.withIndex(list0);
        Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
        for(Object object0: iterable0) {
            map0.put("kotlin/collections/MutableListIterator", ((IndexedValue)object0).getIndex());
        }
        JvmNameResolverBase.PREDEFINED_STRINGS_MAP = map0;
    }

    public JvmNameResolverBase(String[] arr_s, Set set0, List list0) {
        Intrinsics.checkNotNullParameter(arr_s, "strings");
        Intrinsics.checkNotNullParameter(set0, "localNameIndices");
        Intrinsics.checkNotNullParameter(list0, "records");
        super();
        this.strings = arr_s;
        this.localNameIndices = set0;
        this.records = list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getQualifiedClassName(int v) {
        return this.getString(v);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getString(int v) {
        String s;
        Record jvmProtoBuf$StringTableTypes$Record0 = (Record)this.records.get(v);
        if(jvmProtoBuf$StringTableTypes$Record0.hasString()) {
            s = jvmProtoBuf$StringTableTypes$Record0.getString();
        }
        else if(jvmProtoBuf$StringTableTypes$Record0.hasPredefinedIndex()) {
            List list0 = JvmNameResolverBase.PREDEFINED_STRINGS;
            int v1 = jvmProtoBuf$StringTableTypes$Record0.getPredefinedIndex();
            s = v1 < 0 || v1 >= list0.size() ? this.strings[v] : ((String)list0.get(jvmProtoBuf$StringTableTypes$Record0.getPredefinedIndex()));
        }
        else {
            s = this.strings[v];
        }
        if(jvmProtoBuf$StringTableTypes$Record0.getSubstringIndexCount() >= 2) {
            List list1 = jvmProtoBuf$StringTableTypes$Record0.getSubstringIndexList();
            Intrinsics.checkNotNullExpressionValue(list1, "substringIndexList");
            Integer integer0 = (Integer)list1.get(0);
            Integer integer1 = (Integer)list1.get(1);
            Intrinsics.checkNotNullExpressionValue(integer0, "begin");
            if(((int)integer0) >= 0) {
                Intrinsics.checkNotNullExpressionValue(integer1, "end");
                if(((int)integer0) <= ((int)integer1) && ((int)integer1) <= s.length()) {
                    Intrinsics.checkNotNullExpressionValue(s, "string");
                    s = s.substring(((int)integer0), ((int)integer1));
                    Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
                }
            }
        }
        String s1 = s;
        if(jvmProtoBuf$StringTableTypes$Record0.getReplaceCharCount() >= 2) {
            List list2 = jvmProtoBuf$StringTableTypes$Record0.getReplaceCharList();
            Intrinsics.checkNotNullExpressionValue(list2, "replaceCharList");
            Integer integer2 = (Integer)list2.get(0);
            Integer integer3 = (Integer)list2.get(1);
            Intrinsics.checkNotNullExpressionValue(s1, "string");
            s1 = StringsKt.replace$default(s1, ((char)(((int)integer2))), ((char)(((int)integer3))), false, 4, null);
        }
        String s2 = s1;
        switch(WhenMappings.$EnumSwitchMapping$0[(jvmProtoBuf$StringTableTypes$Record0.getOperation() == null ? Operation.NONE : jvmProtoBuf$StringTableTypes$Record0.getOperation()).ordinal()]) {
            case 2: {
                Intrinsics.checkNotNullExpressionValue(s2, "string");
                s2 = StringsKt.replace$default(s2, '$', '.', false, 4, null);
                break;
            }
            case 3: {
                if(s2.length() >= 2) {
                    Intrinsics.checkNotNullExpressionValue(s2, "string");
                    s2 = s2.substring(1, s2.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                Intrinsics.checkNotNullExpressionValue(s2, "string");
                s2 = StringsKt.replace$default(s2, '$', '.', false, 4, null);
            }
        }
        Intrinsics.checkNotNullExpressionValue(s2, "string");
        return s2;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int v) {
        return this.localNameIndices.contains(v);
    }
}

