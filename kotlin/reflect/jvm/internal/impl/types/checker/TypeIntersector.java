package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StubTypeForBuilderInference;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

public final class TypeIntersector {
    static abstract enum ResultNullability {
        static final class ACCEPT_NULL extends ResultNullability {
            ACCEPT_NULL(String s, int v) {
                super(null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability
            public ResultNullability combine(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "nextType");
                return this.getResultNullability(unwrappedType0);
            }
        }

        static final class NOT_NULL extends ResultNullability {
            NOT_NULL(String s, int v) {
                super(null);
            }

            public NOT_NULL combine(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "nextType");
                return this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability
            public ResultNullability combine(UnwrappedType unwrappedType0) {
                return this.combine(unwrappedType0);
            }
        }

        static final class START extends ResultNullability {
            START(String s, int v) {
                super(null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability
            public ResultNullability combine(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "nextType");
                return this.getResultNullability(unwrappedType0);
            }
        }

        static final class UNKNOWN extends ResultNullability {
            UNKNOWN(String s, int v) {
                super(null);
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability
            public ResultNullability combine(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "nextType");
                ResultNullability typeIntersector$ResultNullability0 = this.getResultNullability(unwrappedType0);
                return typeIntersector$ResultNullability0 == ResultNullability.ACCEPT_NULL ? this : typeIntersector$ResultNullability0;
            }
        }

        START /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability$START */,
        ACCEPT_NULL /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability$ACCEPT_NULL */,
        UNKNOWN /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability$UNKNOWN */,
        NOT_NULL /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.types.checker.TypeIntersector$ResultNullability$NOT_NULL */;

        private static final ResultNullability[] $values() [...] // Inlined contents

        private ResultNullability() {
        }

        public ResultNullability(DefaultConstructorMarker defaultConstructorMarker0) {
            this();
        }

        public abstract ResultNullability combine(UnwrappedType arg1);

        protected final ResultNullability getResultNullability(UnwrappedType unwrappedType0) {
            Intrinsics.checkNotNullParameter(unwrappedType0, "<this>");
            if(unwrappedType0.isMarkedNullable()) {
                return ResultNullability.ACCEPT_NULL;
            }
            if(unwrappedType0 instanceof DefinitelyNotNullType && ((DefinitelyNotNullType)unwrappedType0).getOriginal() instanceof StubTypeForBuilderInference) {
                return ResultNullability.NOT_NULL;
            }
            if(unwrappedType0 instanceof StubTypeForBuilderInference) {
                return ResultNullability.UNKNOWN;
            }
            return NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType0) ? ResultNullability.NOT_NULL : ResultNullability.UNKNOWN;
        }
    }

    public static final TypeIntersector INSTANCE;

    static {
        TypeIntersector.INSTANCE = new TypeIntersector();
    }

    private final Collection filterTypes(Collection collection0, Function2 function20) {
        ArrayList arrayList0 = new ArrayList(collection0);
        Iterator iterator0 = arrayList0.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator0, "filteredTypes.iterator()");
    label_3:
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            SimpleType simpleType0 = (SimpleType)object0;
            if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                Iterator iterator1 = arrayList0.iterator();
                while(true) {
                    if(!iterator1.hasNext()) {
                        continue label_3;
                    }
                    Object object1 = iterator1.next();
                    SimpleType simpleType1 = (SimpleType)object1;
                    if(simpleType1 != simpleType0) {
                        Intrinsics.checkNotNullExpressionValue(simpleType1, "lower");
                        Intrinsics.checkNotNullExpressionValue(simpleType0, "upper");
                        if(((Boolean)function20.invoke(simpleType1, simpleType0)).booleanValue()) {
                            break;
                        }
                    }
                }
                iterator0.remove();
            }
        }
        return arrayList0;
    }

    public final SimpleType intersectTypes$descriptors(List list0) {
        Intrinsics.checkNotNullParameter(list0, "types");
        list0.size();
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: list0) {
            SimpleType simpleType0 = (SimpleType)object0;
            if(simpleType0.getConstructor() instanceof IntersectionTypeConstructor) {
                Collection collection0 = simpleType0.getConstructor().getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collection0, "type.constructor.supertypes");
                Collection collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
                for(Object object1: collection0) {
                    Intrinsics.checkNotNullExpressionValue(((KotlinType)object1), "it");
                    SimpleType simpleType1 = FlexibleTypesKt.upperIfFlexible(((KotlinType)object1));
                    if(simpleType0.isMarkedNullable()) {
                        simpleType1 = simpleType1.makeNullableAsSpecified(true);
                    }
                    collection1.add(simpleType1);
                }
                arrayList0.addAll(((List)collection1));
            }
            else {
                arrayList0.add(simpleType0);
            }
        }
        ResultNullability typeIntersector$ResultNullability0 = ResultNullability.START;
        for(Object object2: arrayList0) {
            typeIntersector$ResultNullability0 = typeIntersector$ResultNullability0.combine(((UnwrappedType)object2));
        }
        Collection collection2 = new LinkedHashSet();
        for(Object object3: arrayList0) {
            SimpleType simpleType2 = (SimpleType)object3;
            if(typeIntersector$ResultNullability0 == ResultNullability.NOT_NULL) {
                if(simpleType2 instanceof NewCapturedType) {
                    simpleType2 = SpecialTypesKt.withNotNullProjection(((NewCapturedType)simpleType2));
                }
                simpleType2 = SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull$default(simpleType2, false, 1, null);
            }
            collection2.add(simpleType2);
        }
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object4: list0) {
            arrayList1.add(((SimpleType)object4).getAttributes());
        }
        Iterator iterator5 = arrayList1.iterator();
        if(!iterator5.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can\'t be reduced.");
        }
        TypeAttributes typeAttributes0;
        for(typeAttributes0 = iterator5.next(); iterator5.hasNext(); typeAttributes0 = typeAttributes0.intersect(((TypeAttributes)object5))) {
            Object object5 = iterator5.next();
        }
        return this.intersectTypesWithoutIntersectionType(((LinkedHashSet)collection2)).replaceAttributes(typeAttributes0);
    }

    private final SimpleType intersectTypesWithoutIntersectionType(Set set0) {
        if(set0.size() == 1) {
            return (SimpleType)CollectionsKt.single(set0);
        }
        new Function0() {
            final Set $inputTypes;

            {
                this.$inputTypes = set0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final String invoke() {
                return "This collections cannot be empty! input types: " + CollectionsKt.joinToString$default(this.$inputTypes, null, null, null, 0, null, null, 0x3F, null);
            }
        };
        Collection collection0 = this.filterTypes(set0, new Function2() {
            {
                super(2, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "isStrictSupertype";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(TypeIntersector.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "isStrictSupertype(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            }

            public final Boolean invoke(KotlinType kotlinType0, KotlinType kotlinType1) {
                Intrinsics.checkNotNullParameter(kotlinType0, "p0");
                Intrinsics.checkNotNullParameter(kotlinType1, "p1");
                return Boolean.valueOf(((TypeIntersector)this.receiver).isStrictSupertype(kotlinType0, kotlinType1));
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((KotlinType)object0), ((KotlinType)object1));
            }
        });
        collection0.isEmpty();
        SimpleType simpleType0 = IntegerLiteralTypeConstructor.Companion.findIntersectionType(collection0);
        if(simpleType0 != null) {
            return simpleType0;
        }
        Collection collection1 = this.filterTypes(collection0, new Function2() {
            {
                super(2, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "equalTypes";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(NewKotlinTypeCheckerImpl.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "equalTypes(Lorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/types/KotlinType;)Z";
            }

            public final Boolean invoke(KotlinType kotlinType0, KotlinType kotlinType1) {
                Intrinsics.checkNotNullParameter(kotlinType0, "p0");
                Intrinsics.checkNotNullParameter(kotlinType1, "p1");
                return Boolean.valueOf(((NewKotlinTypeCheckerImpl)this.receiver).equalTypes(kotlinType0, kotlinType1));
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((KotlinType)object0), ((KotlinType)object1));
            }
        });
        collection1.isEmpty();
        return collection1.size() >= 2 ? new IntersectionTypeConstructor(set0).createType() : ((SimpleType)CollectionsKt.single(collection1));
    }

    private final boolean isStrictSupertype(KotlinType kotlinType0, KotlinType kotlinType1) {
        NewKotlinTypeCheckerImpl newKotlinTypeCheckerImpl0 = NewKotlinTypeChecker.Companion.getDefault();
        return newKotlinTypeCheckerImpl0.isSubtypeOf(kotlinType0, kotlinType1) && !newKotlinTypeCheckerImpl0.isSubtypeOf(kotlinType1, kotlinType0);
    }
}

