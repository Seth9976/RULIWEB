package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum KotlinTarget {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    CLASS("class", false, 2, null),
    ANNOTATION_CLASS("annotation class", false, 2, null),
    TYPE_PARAMETER("type parameter", false),
    PROPERTY("property", false, 2, null),
    FIELD("field", false, 2, null),
    LOCAL_VARIABLE("local variable", false, 2, null),
    VALUE_PARAMETER("value parameter", false, 2, null),
    CONSTRUCTOR("constructor", false, 2, null),
    FUNCTION("function", false, 2, null),
    PROPERTY_GETTER("getter", false, 2, null),
    PROPERTY_SETTER("setter", false, 2, null),
    TYPE("type usage", false),
    EXPRESSION("expression", false),
    FILE("file", false),
    TYPEALIAS("typealias", false),
    TYPE_PROJECTION("type projection", false),
    STAR_PROJECTION("star projection", false),
    PROPERTY_PARAMETER("property constructor parameter", false),
    CLASS_ONLY("class", false),
    OBJECT("object", false),
    STANDALONE_OBJECT("standalone object", false),
    COMPANION_OBJECT("companion object", false),
    INTERFACE("interface", false),
    ENUM_CLASS("enum class", false),
    ENUM_ENTRY("enum entry", false),
    LOCAL_CLASS("local class", false),
    LOCAL_FUNCTION("local function", false),
    MEMBER_FUNCTION("member function", false),
    TOP_LEVEL_FUNCTION("top level function", false),
    MEMBER_PROPERTY("member property", false),
    MEMBER_PROPERTY_WITH_BACKING_FIELD("member property with backing field", false),
    MEMBER_PROPERTY_WITH_DELEGATE("member property with delegate", false),
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("member property without backing field or delegate", false),
    TOP_LEVEL_PROPERTY("top level property", false),
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD("top level property with backing field", false),
    TOP_LEVEL_PROPERTY_WITH_DELEGATE("top level property with delegate", false),
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("top level property without backing field or delegate", false),
    BACKING_FIELD("backing field", false, 2, null),
    INITIALIZER("initializer", false),
    DESTRUCTURING_DECLARATION("destructuring declaration", false),
    LAMBDA_EXPRESSION("lambda expression", false),
    ANONYMOUS_FUNCTION("anonymous function", false),
    OBJECT_LITERAL("object literal", false);

    private static final Set ALL_TARGET_SET;
    private static final List ANNOTATION_CLASS_LIST;
    private static final List CLASS_LIST;
    private static final List COMPANION_OBJECT_LIST;
    public static final Companion Companion;
    private static final Set DEFAULT_TARGET_SET;
    private static final List ENUM_ENTRY_LIST;
    private static final List ENUM_LIST;
    private static final List FILE_LIST;
    private static final List FUNCTION_LIST;
    private static final List INTERFACE_LIST;
    private static final List LOCAL_CLASS_LIST;
    private static final List OBJECT_LIST;
    private static final List PROPERTY_GETTER_LIST;
    private static final List PROPERTY_SETTER_LIST;
    private static final Map USE_SITE_MAPPING;
    private final String description;
    private final boolean isDefault;
    private static final HashMap map;

    private static final KotlinTarget[] $values() [...] // Inlined contents

    static {
        KotlinTarget.Companion = new Companion(null);
        KotlinTarget.map = new HashMap();
        KotlinTarget[] arr_kotlinTarget = (KotlinTarget[])KotlinTarget.$VALUES.clone();
        for(int v = 0; v < arr_kotlinTarget.length; ++v) {
            KotlinTarget kotlinTarget0 = arr_kotlinTarget[v];
            KotlinTarget.map.put(kotlinTarget0.name(), kotlinTarget0);
        }
        KotlinTarget[] arr_kotlinTarget1 = (KotlinTarget[])KotlinTarget.$VALUES.clone();
        Collection collection0 = new ArrayList();
        for(int v1 = 0; v1 < arr_kotlinTarget1.length; ++v1) {
            KotlinTarget kotlinTarget1 = arr_kotlinTarget1[v1];
            if(kotlinTarget1.isDefault) {
                collection0.add(kotlinTarget1);
            }
        }
        KotlinTarget.DEFAULT_TARGET_SET = CollectionsKt.toSet(((List)collection0));
        KotlinTarget.ALL_TARGET_SET = ArraysKt.toSet(((KotlinTarget[])KotlinTarget.$VALUES.clone()));
        KotlinTarget.ANNOTATION_CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.ANNOTATION_CLASS, KotlinTarget.CLASS});
        KotlinTarget.LOCAL_CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.LOCAL_CLASS, KotlinTarget.CLASS});
        KotlinTarget.CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.CLASS_ONLY, KotlinTarget.CLASS});
        KotlinTarget.COMPANION_OBJECT_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.COMPANION_OBJECT, KotlinTarget.OBJECT, KotlinTarget.CLASS});
        KotlinTarget.OBJECT_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.STANDALONE_OBJECT, KotlinTarget.OBJECT, KotlinTarget.CLASS});
        KotlinTarget.INTERFACE_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.INTERFACE, KotlinTarget.CLASS});
        KotlinTarget.ENUM_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.ENUM_CLASS, KotlinTarget.CLASS});
        KotlinTarget.ENUM_ENTRY_LIST = CollectionsKt.listOf(new KotlinTarget[]{KotlinTarget.ENUM_ENTRY, KotlinTarget.PROPERTY, KotlinTarget.FIELD});
        KotlinTarget.PROPERTY_SETTER_LIST = CollectionsKt.listOf(KotlinTarget.PROPERTY_SETTER);
        KotlinTarget.PROPERTY_GETTER_LIST = CollectionsKt.listOf(KotlinTarget.PROPERTY_GETTER);
        KotlinTarget.FUNCTION_LIST = CollectionsKt.listOf(KotlinTarget.FUNCTION);
        KotlinTarget.FILE_LIST = CollectionsKt.listOf(KotlinTarget.FILE);
        Pair pair0 = new Pair(AnnotationUseSiteTarget.FIELD, KotlinTarget.FIELD);
        KotlinTarget.USE_SITE_MAPPING = MapsKt.mapOf(new Pair[]{new Pair(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, KotlinTarget.VALUE_PARAMETER), pair0, TuplesKt.to(AnnotationUseSiteTarget.PROPERTY, KotlinTarget.PROPERTY), TuplesKt.to(AnnotationUseSiteTarget.FILE, KotlinTarget.FILE), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_GETTER, KotlinTarget.PROPERTY_GETTER), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_SETTER, KotlinTarget.PROPERTY_SETTER), TuplesKt.to(AnnotationUseSiteTarget.RECEIVER, KotlinTarget.VALUE_PARAMETER), TuplesKt.to(AnnotationUseSiteTarget.SETTER_PARAMETER, KotlinTarget.VALUE_PARAMETER), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD, KotlinTarget.FIELD)});
    }

    private KotlinTarget(String s1, boolean z) {
        this.description = s1;
        this.isDefault = z;
    }

    KotlinTarget(String s1, boolean z, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            z = true;
        }
        this(s1, z);
    }
}

