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
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class BuiltinSpecialProperties {
    private static final Map GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP;
    public static final BuiltinSpecialProperties INSTANCE;
    private static final Map PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    private static final Set SPECIAL_FQ_NAMES;
    private static final Set SPECIAL_SHORT_NAMES;

    static {
        BuiltinSpecialProperties.INSTANCE = new BuiltinSpecialProperties();
        Map map0 = MapsKt.mapOf(new Pair[]{TuplesKt.to(BuiltinSpecialPropertiesKt.access$childSafe(FqNames._enum, "name"), Name.identifier("name")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$childSafe(FqNames._enum, "ordinal"), Name.identifier("ordinal")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$child(FqNames.collection, "size"), Name.identifier("size")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$child(FqNames.map, "size"), Name.identifier("size")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$childSafe(FqNames.charSequence, "length"), Name.identifier("length")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$child(FqNames.map, "keys"), Name.identifier("keySet")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$child(FqNames.map, "values"), Name.identifier("values")), TuplesKt.to(BuiltinSpecialPropertiesKt.access$child(FqNames.map, "entries"), Name.identifier("entrySet"))});
        BuiltinSpecialProperties.PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP = map0;
        Iterable iterable0 = map0.entrySet();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(new Pair(((FqName)((Map.Entry)object0).getKey()).shortName(), ((Map.Entry)object0).getValue()));
        }
        Map map1 = new LinkedHashMap();
        for(Object object1: arrayList0) {
            Name name0 = (Name)((Pair)object1).getSecond();
            ArrayList arrayList1 = map1.get(name0);
            if(arrayList1 == null) {
                arrayList1 = new ArrayList();
                map1.put(name0, arrayList1);
            }
            arrayList1.add(((Name)((Pair)object1).getFirst()));
        }
        LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(map1.size()));
        for(Object object2: map1.entrySet()) {
            linkedHashMap0.put(((Map.Entry)object2).getKey(), CollectionsKt.distinct(((Iterable)((Map.Entry)object2).getValue())));
        }
        BuiltinSpecialProperties.GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP = linkedHashMap0;
        Set set0 = BuiltinSpecialProperties.PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP.keySet();
        BuiltinSpecialProperties.SPECIAL_FQ_NAMES = set0;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set0, 10));
        for(Object object3: set0) {
            arrayList2.add(((FqName)object3).shortName());
        }
        BuiltinSpecialProperties.SPECIAL_SHORT_NAMES = CollectionsKt.toSet(arrayList2);
    }

    public final Map getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP() {
        return BuiltinSpecialProperties.PROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP;
    }

    public final List getPropertyNameCandidatesBySpecialGetterName(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name1");
        List list0 = (List)BuiltinSpecialProperties.GETTER_JVM_NAME_TO_PROPERTIES_SHORT_NAME_MAP.get(name0);
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    public final Set getSPECIAL_FQ_NAMES() {
        return BuiltinSpecialProperties.SPECIAL_FQ_NAMES;
    }

    public final Set getSPECIAL_SHORT_NAMES() {
        return BuiltinSpecialProperties.SPECIAL_SHORT_NAMES;
    }
}

