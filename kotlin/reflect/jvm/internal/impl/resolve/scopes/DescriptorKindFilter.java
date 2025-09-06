package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DescriptorKindFilter {
    public static final class Companion {
        static final class MaskToName {
            private final int mask;
            private final String name;

            public MaskToName(int v, String s) {
                Intrinsics.checkNotNullParameter(s, "name");
                super();
                this.mask = v;
                this.name = s;
            }

            public final int getMask() {
                return this.mask;
            }

            public final String getName() {
                return this.name;
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final int getALL_KINDS_MASK() [...] // 潜在的解密器

        public final int getCLASSIFIERS_MASK() [...] // 潜在的解密器

        public final int getFUNCTIONS_MASK() [...] // 潜在的解密器

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() [...] // 潜在的解密器

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() [...] // 潜在的解密器

        public final int getTYPE_ALIASES_MASK() [...] // 潜在的解密器

        public final int getVARIABLES_MASK() [...] // 潜在的解密器

        // 去混淆评级： 低(20)
        private final int nextMask() {
            DescriptorKindFilter.nextMaskValue = 0x100;
            return 0x80;
        }
    }

    public static final DescriptorKindFilter ALL;
    private static final int ALL_KINDS_MASK;
    public static final DescriptorKindFilter CALLABLES;
    private static final int CALLABLES_MASK;
    public static final DescriptorKindFilter CLASSIFIERS;
    private static final int CLASSIFIERS_MASK;
    public static final Companion Companion;
    private static final List DEBUG_MASK_BIT_NAMES;
    private static final List DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
    public static final DescriptorKindFilter FUNCTIONS;
    private static final int FUNCTIONS_MASK;
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS;
    private static final int NON_SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter PACKAGES;
    private static final int PACKAGES_MASK;
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS;
    private static final int SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter TYPE_ALIASES;
    private static final int TYPE_ALIASES_MASK;
    public static final DescriptorKindFilter VALUES;
    private static final int VALUES_MASK;
    public static final DescriptorKindFilter VARIABLES;
    private static final int VARIABLES_MASK;
    private final List excludes;
    private final int kindMask;
    private static int nextMaskValue;

    static {
        MaskToName descriptorKindFilter$Companion$MaskToName1;
        MaskToName descriptorKindFilter$Companion$MaskToName0;
        Companion descriptorKindFilter$Companion0 = new Companion(null);
        DescriptorKindFilter.Companion = descriptorKindFilter$Companion0;
        DescriptorKindFilter.nextMaskValue = 1;
        int v = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK = v;
        int v1 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK = v1;
        int v2 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.TYPE_ALIASES_MASK = v2;
        int v3 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.PACKAGES_MASK = v3;
        int v4 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.FUNCTIONS_MASK = v4;
        int v5 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.VARIABLES_MASK = v5;
        int v6 = descriptorKindFilter$Companion0.nextMask();
        DescriptorKindFilter.ALL_KINDS_MASK = v6 - 1;
        int v7 = v | v1 | v2;
        DescriptorKindFilter.CLASSIFIERS_MASK = v7;
        int v8 = v1 | v4 | v5;
        DescriptorKindFilter.VALUES_MASK = v8;
        int v9 = v4 | v5;
        DescriptorKindFilter.CALLABLES_MASK = v9;
        DescriptorKindFilter.ALL = new DescriptorKindFilter(v6 - 1, null, 2, null);
        DescriptorKindFilter.CALLABLES = new DescriptorKindFilter(v9, null, 2, null);
        DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(v, null, 2, null);
        DescriptorKindFilter.SINGLETON_CLASSIFIERS = new DescriptorKindFilter(v1, null, 2, null);
        DescriptorKindFilter.TYPE_ALIASES = new DescriptorKindFilter(v2, null, 2, null);
        DescriptorKindFilter.CLASSIFIERS = new DescriptorKindFilter(v7, null, 2, null);
        DescriptorKindFilter.PACKAGES = new DescriptorKindFilter(v3, null, 2, null);
        DescriptorKindFilter.FUNCTIONS = new DescriptorKindFilter(v4, null, 2, null);
        DescriptorKindFilter.VARIABLES = new DescriptorKindFilter(v5, null, 2, null);
        DescriptorKindFilter.VALUES = new DescriptorKindFilter(v8, null, 2, null);
        Class class0 = DescriptorKindFilter.class;
        Field[] arr_field = class0.getFields();
        Intrinsics.checkNotNullExpressionValue(arr_field, "T::class.java.fields");
        Collection collection0 = new ArrayList();
        for(int v11 = 0; v11 < arr_field.length; ++v11) {
            Object object0 = arr_field[v11];
            if(Modifier.isStatic(((Field)object0).getModifiers())) {
                collection0.add(object0);
            }
        }
        Collection collection1 = new ArrayList();
        for(Object object1: ((List)collection0)) {
            Field field0 = (Field)object1;
            Object object2 = field0.get(null);
            DescriptorKindFilter descriptorKindFilter0 = object2 instanceof DescriptorKindFilter ? ((DescriptorKindFilter)object2) : null;
            if(descriptorKindFilter0 == null) {
                descriptorKindFilter$Companion$MaskToName0 = null;
            }
            else {
                String s = field0.getName();
                Intrinsics.checkNotNullExpressionValue(s, "field.name");
                descriptorKindFilter$Companion$MaskToName0 = new MaskToName(descriptorKindFilter0.kindMask, s);
            }
            if(descriptorKindFilter$Companion$MaskToName0 != null) {
                collection1.add(descriptorKindFilter$Companion$MaskToName0);
            }
        }
        DescriptorKindFilter.DEBUG_PREDEFINED_FILTERS_MASK_NAMES = (List)collection1;
        Field[] arr_field1 = class0.getFields();
        Intrinsics.checkNotNullExpressionValue(arr_field1, "T::class.java.fields");
        Collection collection2 = new ArrayList();
        for(int v10 = 0; v10 < arr_field1.length; ++v10) {
            Object object3 = arr_field1[v10];
            if(Modifier.isStatic(((Field)object3).getModifiers())) {
                collection2.add(object3);
            }
        }
        Collection collection3 = new ArrayList();
        for(Object object4: ((List)collection2)) {
            if(Intrinsics.areEqual(((Field)object4).getType(), Integer.TYPE)) {
                collection3.add(object4);
            }
        }
        Collection collection4 = new ArrayList();
        for(Object object5: ((List)collection3)) {
            Field field1 = (Field)object5;
            Object object6 = field1.get(null);
            Intrinsics.checkNotNull(object6, "null cannot be cast to non-null type kotlin.Int");
            int v12 = (int)(((Integer)object6));
            if(v12 == (-v12 & v12)) {
                String s1 = field1.getName();
                Intrinsics.checkNotNullExpressionValue(s1, "field.name");
                descriptorKindFilter$Companion$MaskToName1 = new MaskToName(v12, s1);
            }
            else {
                descriptorKindFilter$Companion$MaskToName1 = null;
            }
            if(descriptorKindFilter$Companion$MaskToName1 != null) {
                collection4.add(descriptorKindFilter$Companion$MaskToName1);
            }
        }
        DescriptorKindFilter.DEBUG_MASK_BIT_NAMES = (List)collection4;
    }

    public DescriptorKindFilter(int v, List list0) {
        Intrinsics.checkNotNullParameter(list0, "excludes");
        super();
        this.excludes = list0;
        for(Object object0: list0) {
            v &= ~((DescriptorKindExclude)object0).getFullyExcludedDescriptorKinds();
        }
        this.kindMask = v;
    }

    public DescriptorKindFilter(int v, List list0, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            list0 = CollectionsKt.emptyList();
        }
        this(v, list0);
    }

    public final boolean acceptsKinds(int v) {
        return (v & this.kindMask) != 0;
    }

    public static final int access$getNextMaskValue$cp() [...] // 潜在的解密器

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!Intrinsics.areEqual(this.getClass(), (object0 == null ? null : object0.getClass()))) {
            return false;
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter");
        return Intrinsics.areEqual(this.excludes, ((DescriptorKindFilter)object0).excludes) ? this.kindMask == ((DescriptorKindFilter)object0).kindMask : false;
    }

    public final List getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    @Override
    public int hashCode() {
        return this.excludes.hashCode() * 0x1F + this.kindMask;
    }

    public final DescriptorKindFilter restrictedToKindsOrNull(int v) {
        int v1 = v & this.kindMask;
        return v1 == 0 ? null : new DescriptorKindFilter(v1, this.excludes);
    }

    @Override
    public String toString() {
        Object object0 = null;
        for(Object object1: DescriptorKindFilter.DEBUG_PREDEFINED_FILTERS_MASK_NAMES) {
            if(((MaskToName)object1).getMask() == this.kindMask) {
                object0 = object1;
                break;
            }
        }
        String s = ((MaskToName)object0) == null ? null : ((MaskToName)object0).getName();
        if(s == null) {
            Collection collection0 = new ArrayList();
            for(Object object2: DescriptorKindFilter.DEBUG_MASK_BIT_NAMES) {
                MaskToName descriptorKindFilter$Companion$MaskToName0 = (MaskToName)object2;
                String s1 = this.acceptsKinds(descriptorKindFilter$Companion$MaskToName0.getMask()) ? descriptorKindFilter$Companion$MaskToName0.getName() : null;
                if(s1 != null) {
                    collection0.add(s1);
                }
            }
            s = CollectionsKt.joinToString$default(((List)collection0), " | ", null, null, 0, null, null, 62, null);
        }
        return "DescriptorKindFilter(" + s + ", " + this.excludes + ')';
    }
}

