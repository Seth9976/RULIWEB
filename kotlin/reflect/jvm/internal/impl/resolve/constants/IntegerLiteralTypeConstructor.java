package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class IntegerLiteralTypeConstructor implements TypeConstructor {
    public static final class Companion {
        static enum Mode {
            COMMON_SUPER_TYPE,
            INTERSECTION_TYPE;

            private static final Mode[] $values() [...] // Inlined contents
        }

        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[Mode.values().length];
                try {
                    arr_v[Mode.COMMON_SUPER_TYPE.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[Mode.INTERSECTION_TYPE.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final SimpleType findCommonSuperTypeOrIntersectionType(Collection collection0, Mode integerLiteralTypeConstructor$Companion$Mode0) {
            if(collection0.isEmpty()) {
                return null;
            }
            Iterator iterator0 = collection0.iterator();
            if(!iterator0.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can\'t be reduced.");
            }
            SimpleType simpleType0;
            for(simpleType0 = iterator0.next(); iterator0.hasNext(); simpleType0 = IntegerLiteralTypeConstructor.Companion.fold(simpleType0, ((SimpleType)object0), integerLiteralTypeConstructor$Companion$Mode0)) {
                Object object0 = iterator0.next();
            }
            return simpleType0;
        }

        public final SimpleType findIntersectionType(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "types");
            return this.findCommonSuperTypeOrIntersectionType(collection0, Mode.INTERSECTION_TYPE);
        }

        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor0, IntegerLiteralTypeConstructor integerLiteralTypeConstructor1, Mode integerLiteralTypeConstructor$Companion$Mode0) {
            Set set0;
            switch(WhenMappings.$EnumSwitchMapping$0[integerLiteralTypeConstructor$Companion$Mode0.ordinal()]) {
                case 1: {
                    set0 = CollectionsKt.intersect(integerLiteralTypeConstructor0.getPossibleTypes(), integerLiteralTypeConstructor1.getPossibleTypes());
                    break;
                }
                case 2: {
                    set0 = CollectionsKt.union(integerLiteralTypeConstructor0.getPossibleTypes(), integerLiteralTypeConstructor1.getPossibleTypes());
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            IntegerLiteralTypeConstructor integerLiteralTypeConstructor2 = new IntegerLiteralTypeConstructor(integerLiteralTypeConstructor0.value, integerLiteralTypeConstructor0.module, set0, null);
            return KotlinTypeFactory.integerLiteralType(TypeAttributes.Companion.getEmpty(), integerLiteralTypeConstructor2, false);
        }

        // 去混淆评级： 低(20)
        private final SimpleType fold(IntegerLiteralTypeConstructor integerLiteralTypeConstructor0, SimpleType simpleType0) {
            return integerLiteralTypeConstructor0.getPossibleTypes().contains(simpleType0) ? simpleType0 : null;
        }

        private final SimpleType fold(SimpleType simpleType0, SimpleType simpleType1, Mode integerLiteralTypeConstructor$Companion$Mode0) {
            if(simpleType0 != null && simpleType1 != null) {
                TypeConstructor typeConstructor0 = simpleType0.getConstructor();
                TypeConstructor typeConstructor1 = simpleType1.getConstructor();
                if(typeConstructor0 instanceof IntegerLiteralTypeConstructor && typeConstructor1 instanceof IntegerLiteralTypeConstructor) {
                    return this.fold(((IntegerLiteralTypeConstructor)typeConstructor0), ((IntegerLiteralTypeConstructor)typeConstructor1), integerLiteralTypeConstructor$Companion$Mode0);
                }
                if(typeConstructor0 instanceof IntegerLiteralTypeConstructor) {
                    return this.fold(((IntegerLiteralTypeConstructor)typeConstructor0), simpleType1);
                }
                return typeConstructor1 instanceof IntegerLiteralTypeConstructor ? this.fold(((IntegerLiteralTypeConstructor)typeConstructor1), simpleType0) : null;
            }
            return null;
        }
    }

    public static final Companion Companion;
    private final ModuleDescriptor module;
    private final Set possibleTypes;
    private final Lazy supertypes$delegate;
    private final SimpleType type;
    private final long value;

    static {
        IntegerLiteralTypeConstructor.Companion = new Companion(null);
    }

    private IntegerLiteralTypeConstructor(long v, ModuleDescriptor moduleDescriptor0, Set set0) {
        this.type = KotlinTypeFactory.integerLiteralType(TypeAttributes.Companion.getEmpty(), this, false);
        this.supertypes$delegate = LazyKt.lazy(new IntegerLiteralTypeConstructor.supertypes.2(this));
        this.value = v;
        this.module = moduleDescriptor0;
        this.possibleTypes = set0;
    }

    public IntegerLiteralTypeConstructor(long v, ModuleDescriptor moduleDescriptor0, Set set0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v, moduleDescriptor0, set0);
    }

    public static final SimpleType access$getType$p(IntegerLiteralTypeConstructor integerLiteralTypeConstructor0) {
        return integerLiteralTypeConstructor0.type;
    }

    public static final boolean access$isContainsOnlyUnsignedTypes(IntegerLiteralTypeConstructor integerLiteralTypeConstructor0) {
        return integerLiteralTypeConstructor0.isContainsOnlyUnsignedTypes();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    public final Set getPossibleTypes() {
        return this.possibleTypes;
    }

    private final List getSupertypes() {
        return (List)this.supertypes$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        return this.getSupertypes();
    }

    private final boolean isContainsOnlyUnsignedTypes() {
        Iterable iterable0 = PrimitiveTypeUtilKt.getAllSignedLiteralTypes(this.module);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return true;
        }
        for(Object object0: iterable0) {
            if(this.possibleTypes.contains(((KotlinType)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
    }

    @Override
    public String toString() {
        return "IntegerLiteralType" + this.valueToString();
    }

    private final String valueToString() {
        return "[" + CollectionsKt.joinToString$default(this.possibleTypes, ",", null, null, 0, null, kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1.INSTANCE, 30, null) + ']';

        final class kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1();
            }

            kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor.valueToString.1() {
                super(1);
            }

            public final CharSequence invoke(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "it");
                return kotlinType0.toString();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }
        }

    }
}

