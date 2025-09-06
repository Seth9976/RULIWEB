package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

public class SpecialGenericSignatures {
    public static final class Companion {
        public static final class NameAndSignature {
            private final Name name;
            private final String signature;

            public NameAndSignature(Name name0, String s) {
                Intrinsics.checkNotNullParameter(name0, "name");
                Intrinsics.checkNotNullParameter(s, "signature");
                super();
                this.name = name0;
                this.signature = s;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                if(!(object0 instanceof NameAndSignature)) {
                    return false;
                }
                return Intrinsics.areEqual(this.name, ((NameAndSignature)object0).name) ? Intrinsics.areEqual(this.signature, ((NameAndSignature)object0).signature) : false;
            }

            public final Name getName() {
                return this.name;
            }

            public final String getSignature() {
                return this.signature;
            }

            @Override
            public int hashCode() {
                return this.name.hashCode() * 0x1F + this.signature.hashCode();
            }

            @Override
            public String toString() {
                return "NameAndSignature(name=" + this.name + ", signature=" + this.signature + ')';
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Name getBuiltinFunctionNamesByJvmName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return (Name)this.getJVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP().get(name0);
        }

        public final List getERASED_COLLECTION_PARAMETER_SIGNATURES() {
            return SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_SIGNATURES;
        }

        public final Set getERASED_VALUE_PARAMETERS_SHORT_NAMES() {
            return SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SHORT_NAMES;
        }

        public final Set getERASED_VALUE_PARAMETERS_SIGNATURES() {
            return SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SIGNATURES;
        }

        public final Map getJVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP() {
            return SpecialGenericSignatures.JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
        }

        public final List getORIGINAL_SHORT_NAMES() {
            return SpecialGenericSignatures.ORIGINAL_SHORT_NAMES;
        }

        public final NameAndSignature getREMOVE_AT_NAME_AND_SIGNATURE() {
            return SpecialGenericSignatures.REMOVE_AT_NAME_AND_SIGNATURE;
        }

        public final Map getSIGNATURE_TO_DEFAULT_VALUES_MAP() {
            return SpecialGenericSignatures.SIGNATURE_TO_DEFAULT_VALUES_MAP;
        }

        public final Map getSIGNATURE_TO_JVM_REPRESENTATION_NAME() {
            return SpecialGenericSignatures.SIGNATURE_TO_JVM_REPRESENTATION_NAME;
        }

        public final boolean getSameAsRenamedInJvmBuiltin(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "<this>");
            return this.getORIGINAL_SHORT_NAMES().contains(name0);
        }

        public final SpecialSignatureInfo getSpecialSignatureInfo(String s) {
            Intrinsics.checkNotNullParameter(s, "builtinSignature");
            if(this.getERASED_COLLECTION_PARAMETER_SIGNATURES().contains(s)) {
                return SpecialSignatureInfo.ONE_COLLECTION_PARAMETER;
            }
            return ((TypeSafeBarrierDescription)MapsKt.getValue(this.getSIGNATURE_TO_DEFAULT_VALUES_MAP(), s)) == TypeSafeBarrierDescription.NULL ? SpecialSignatureInfo.OBJECT_PARAMETER_GENERIC : SpecialSignatureInfo.OBJECT_PARAMETER_NON_GENERIC;
        }

        private final NameAndSignature method(String s, String s1, String s2, String s3) {
            Name name0 = Name.identifier(s1);
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(name)");
            return new NameAndSignature(name0, SignatureBuildingComponents.INSTANCE.signature(s, s1 + '(' + s2 + ')' + s3));
        }
    }

    public static enum SpecialSignatureInfo {
        ONE_COLLECTION_PARAMETER("Ljava/util/Collection<+Ljava/lang/Object;>;", false),
        OBJECT_PARAMETER_NON_GENERIC(null, true),
        OBJECT_PARAMETER_GENERIC("Ljava/lang/Object;", true);

        private final boolean isObjectReplacedWithTypeParameter;
        private final String valueParametersSignature;

        private static final SpecialSignatureInfo[] $values() [...] // Inlined contents

        private SpecialSignatureInfo(String s1, boolean z) {
            this.valueParametersSignature = s1;
            this.isObjectReplacedWithTypeParameter = z;
        }
    }

    public static enum TypeSafeBarrierDescription {
        static final class MAP_GET_OR_DEFAULT extends TypeSafeBarrierDescription {
            MAP_GET_OR_DEFAULT(String s, int v) {
                super(null, null);
            }
        }

        NULL(null),
        INDEX(-1),
        FALSE(Boolean.FALSE),
        MAP_GET_OR_DEFAULT /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures$TypeSafeBarrierDescription$MAP_GET_OR_DEFAULT */;

        private final Object defaultValue;

        private static final TypeSafeBarrierDescription[] $values() [...] // Inlined contents

        private TypeSafeBarrierDescription(Object object0) {
            this.defaultValue = object0;
        }

        public TypeSafeBarrierDescription(Object object0, DefaultConstructorMarker defaultConstructorMarker0) {
            this(object0);
        }
    }

    public static final Companion Companion;
    private static final List ERASED_COLLECTION_PARAMETER_NAMES;
    private static final List ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES;
    private static final List ERASED_COLLECTION_PARAMETER_SIGNATURES;
    private static final Set ERASED_VALUE_PARAMETERS_SHORT_NAMES;
    private static final Set ERASED_VALUE_PARAMETERS_SIGNATURES;
    private static final Map GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP;
    private static final Map JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP;
    private static final Map NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP;
    private static final List ORIGINAL_SHORT_NAMES;
    private static final NameAndSignature REMOVE_AT_NAME_AND_SIGNATURE;
    private static final Map SIGNATURE_TO_DEFAULT_VALUES_MAP;
    private static final Map SIGNATURE_TO_JVM_REPRESENTATION_NAME;

    static {
        SpecialGenericSignatures.Companion = new Companion(null);
        Iterable iterable0 = SetsKt.setOf(new String[]{"containsAll", "removeAll", "retainAll"});
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            String s = JvmPrimitiveType.BOOLEAN.getDesc();
            Intrinsics.checkNotNullExpressionValue(s, "BOOLEAN.desc");
            arrayList0.add(SpecialGenericSignatures.Companion.method("java/util/Collection", ((String)object0), "Ljava/util/Collection;", s));
        }
        SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES = arrayList0;
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList0, 10));
        for(Object object1: arrayList0) {
            arrayList1.add(((NameAndSignature)object1).getSignature());
        }
        SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_SIGNATURES = arrayList1;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES, 10));
        for(Object object2: SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES) {
            arrayList2.add(((NameAndSignature)object2).getName().asString());
        }
        SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_NAMES = arrayList2;
        Pair[] arr_pair = new Pair[10];
        String s1 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(s1, "BOOLEAN.desc");
        arr_pair[0] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Collection", "contains", "Ljava/lang/Object;", s1), TypeSafeBarrierDescription.FALSE);
        String s2 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(s2, "BOOLEAN.desc");
        arr_pair[1] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Collection", "remove", "Ljava/lang/Object;", s2), TypeSafeBarrierDescription.FALSE);
        String s3 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(s3, "BOOLEAN.desc");
        arr_pair[2] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "containsKey", "Ljava/lang/Object;", s3), TypeSafeBarrierDescription.FALSE);
        String s4 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(s4, "BOOLEAN.desc");
        arr_pair[3] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "containsValue", "Ljava/lang/Object;", s4), TypeSafeBarrierDescription.FALSE);
        String s5 = JvmPrimitiveType.BOOLEAN.getDesc();
        Intrinsics.checkNotNullExpressionValue(s5, "BOOLEAN.desc");
        arr_pair[4] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "remove", "Ljava/lang/Object;Ljava/lang/Object;", s5), TypeSafeBarrierDescription.FALSE);
        arr_pair[5] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.MAP_GET_OR_DEFAULT);
        arr_pair[6] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "get", "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL);
        arr_pair[7] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/Map", "remove", "Ljava/lang/Object;", "Ljava/lang/Object;"), TypeSafeBarrierDescription.NULL);
        String s6 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s6, "INT.desc");
        arr_pair[8] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/List", "indexOf", "Ljava/lang/Object;", s6), TypeSafeBarrierDescription.INDEX);
        String s7 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s7, "INT.desc");
        arr_pair[9] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/util/List", "lastIndexOf", "Ljava/lang/Object;", s7), TypeSafeBarrierDescription.INDEX);
        Map map0 = MapsKt.mapOf(arr_pair);
        SpecialGenericSignatures.GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP = map0;
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(map0.size()));
        for(Object object3: map0.entrySet()) {
            linkedHashMap0.put(((NameAndSignature)((Map.Entry)object3).getKey()).getSignature(), ((Map.Entry)object3).getValue());
        }
        SpecialGenericSignatures.SIGNATURE_TO_DEFAULT_VALUES_MAP = linkedHashMap0;
        Iterable iterable1 = SetsKt.plus(SpecialGenericSignatures.GENERIC_PARAMETERS_METHODS_TO_DEFAULT_VALUES_MAP.keySet(), SpecialGenericSignatures.ERASED_COLLECTION_PARAMETER_NAME_AND_SIGNATURES);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
        for(Object object4: iterable1) {
            arrayList3.add(((NameAndSignature)object4).getName());
        }
        SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SHORT_NAMES = CollectionsKt.toSet(arrayList3);
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
        for(Object object5: iterable1) {
            arrayList4.add(((NameAndSignature)object5).getSignature());
        }
        SpecialGenericSignatures.ERASED_VALUE_PARAMETERS_SIGNATURES = CollectionsKt.toSet(arrayList4);
        String s8 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s8, "INT.desc");
        NameAndSignature specialGenericSignatures$Companion$NameAndSignature0 = SpecialGenericSignatures.Companion.method("java/util/List", "removeAt", s8, "Ljava/lang/Object;");
        SpecialGenericSignatures.REMOVE_AT_NAME_AND_SIGNATURE = specialGenericSignatures$Companion$NameAndSignature0;
        Pair[] arr_pair1 = new Pair[8];
        String s9 = JvmPrimitiveType.BYTE.getDesc();
        Intrinsics.checkNotNullExpressionValue(s9, "BYTE.desc");
        arr_pair1[0] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toByte", "", s9), Name.identifier("byteValue"));
        String s10 = JvmPrimitiveType.SHORT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s10, "SHORT.desc");
        arr_pair1[1] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toShort", "", s10), Name.identifier("shortValue"));
        String s11 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s11, "INT.desc");
        arr_pair1[2] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toInt", "", s11), Name.identifier("intValue"));
        String s12 = JvmPrimitiveType.LONG.getDesc();
        Intrinsics.checkNotNullExpressionValue(s12, "LONG.desc");
        arr_pair1[3] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toLong", "", s12), Name.identifier("longValue"));
        String s13 = JvmPrimitiveType.FLOAT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s13, "FLOAT.desc");
        arr_pair1[4] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toFloat", "", s13), Name.identifier("floatValue"));
        String s14 = JvmPrimitiveType.DOUBLE.getDesc();
        Intrinsics.checkNotNullExpressionValue(s14, "DOUBLE.desc");
        arr_pair1[5] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/Number", "toDouble", "", s14), Name.identifier("doubleValue"));
        arr_pair1[6] = TuplesKt.to(specialGenericSignatures$Companion$NameAndSignature0, Name.identifier("remove"));
        String s15 = JvmPrimitiveType.INT.getDesc();
        Intrinsics.checkNotNullExpressionValue(s15, "INT.desc");
        String s16 = JvmPrimitiveType.CHAR.getDesc();
        Intrinsics.checkNotNullExpressionValue(s16, "CHAR.desc");
        arr_pair1[7] = TuplesKt.to(SpecialGenericSignatures.Companion.method("java/lang/CharSequence", "get", s15, s16), Name.identifier("charAt"));
        Map map1 = MapsKt.mapOf(arr_pair1);
        SpecialGenericSignatures.NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP = map1;
        LinkedHashMap linkedHashMap1 = new LinkedHashMap(MapsKt.mapCapacity(map1.size()));
        for(Object object6: map1.entrySet()) {
            linkedHashMap1.put(((NameAndSignature)((Map.Entry)object6).getKey()).getSignature(), ((Map.Entry)object6).getValue());
        }
        SpecialGenericSignatures.SIGNATURE_TO_JVM_REPRESENTATION_NAME = linkedHashMap1;
        Iterable iterable2 = SpecialGenericSignatures.NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.keySet();
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for(Object object7: iterable2) {
            arrayList5.add(((NameAndSignature)object7).getName());
        }
        SpecialGenericSignatures.ORIGINAL_SHORT_NAMES = arrayList5;
        Iterable iterable3 = SpecialGenericSignatures.NAME_AND_SIGNATURE_TO_JVM_REPRESENTATION_NAME_MAP.entrySet();
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        for(Object object8: iterable3) {
            arrayList6.add(new Pair(((NameAndSignature)((Map.Entry)object8).getKey()).getName(), ((Map.Entry)object8).getValue()));
        }
        Map map2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList6, 10)), 16));
        for(Object object9: arrayList6) {
            map2.put(((Name)((Pair)object9).getSecond()), ((Name)((Pair)object9).getFirst()));
        }
        SpecialGenericSignatures.JVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP = map2;
    }
}

