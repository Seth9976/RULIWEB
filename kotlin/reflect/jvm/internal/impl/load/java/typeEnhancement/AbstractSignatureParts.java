package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDefaultQualifiers;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.RawTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

public abstract class AbstractSignatureParts {
    static final class TypeAndDefaultQualifiers {
        private final JavaTypeQualifiersByElementType defaultQualifiers;
        private final KotlinTypeMarker type;
        private final TypeParameterMarker typeParameterForArgument;

        public TypeAndDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker0, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType0, TypeParameterMarker typeParameterMarker0) {
            this.type = kotlinTypeMarker0;
            this.defaultQualifiers = javaTypeQualifiersByElementType0;
            this.typeParameterForArgument = typeParameterMarker0;
        }

        public final JavaTypeQualifiersByElementType getDefaultQualifiers() {
            return this.defaultQualifiers;
        }

        public final KotlinTypeMarker getType() {
            return this.type;
        }

        public final TypeParameterMarker getTypeParameterForArgument() {
            return this.typeParameterForArgument;
        }
    }

    public final Function1 computeIndexedQualifiers(KotlinTypeMarker kotlinTypeMarker0, Iterable iterable0, TypeEnhancementInfo typeEnhancementInfo0, boolean z) {
        JavaTypeQualifiers javaTypeQualifiers1;
        int v;
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "overrides");
        List list0 = this.toIndexed(kotlinTypeMarker0);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(this.toIndexed(((KotlinTypeMarker)object0)));
        }
        if(this.isCovariant() && (!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty())) {
            for(Object object1: iterable0) {
                if(this.isEqual(kotlinTypeMarker0, ((KotlinTypeMarker)object1))) {
                    continue;
                }
                v = 1;
                goto label_17;
            }
        }
        v = list0.size();
    label_17:
        JavaTypeQualifiers[] arr_javaTypeQualifiers = new JavaTypeQualifiers[v];
        for(int v1 = 0; v1 < v; ++v1) {
            JavaTypeQualifiers javaTypeQualifiers0 = this.extractQualifiersFromAnnotations(((TypeAndDefaultQualifiers)list0.get(v1)));
            Collection collection0 = new ArrayList();
            for(Object object2: arrayList0) {
                TypeAndDefaultQualifiers abstractSignatureParts$TypeAndDefaultQualifiers0 = (TypeAndDefaultQualifiers)CollectionsKt.getOrNull(((List)object2), v1);
                if(abstractSignatureParts$TypeAndDefaultQualifiers0 == null) {
                    javaTypeQualifiers1 = null;
                }
                else {
                    KotlinTypeMarker kotlinTypeMarker1 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
                    if(kotlinTypeMarker1 != null) {
                        javaTypeQualifiers1 = this.extractQualifiers(kotlinTypeMarker1);
                    }
                }
                if(javaTypeQualifiers1 != null) {
                    collection0.add(javaTypeQualifiers1);
                }
            }
            arr_javaTypeQualifiers[v1] = TypeEnhancementUtilsKt.computeQualifiersForOverride(javaTypeQualifiers0, ((List)collection0), v1 == 0 && this.isCovariant(), v1 == 0 && this.getContainerIsVarargParameter(), z);
        }
        return new Function1(arr_javaTypeQualifiers) {
            final JavaTypeQualifiers[] $computedResult;
            final TypeEnhancementInfo $predefined;

            {
                this.$predefined = typeEnhancementInfo0;
                this.$computedResult = arr_javaTypeQualifiers;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Number)object0).intValue());
            }

            public final JavaTypeQualifiers invoke(int v) {
                TypeEnhancementInfo typeEnhancementInfo0 = this.$predefined;
                if(typeEnhancementInfo0 != null) {
                    Map map0 = typeEnhancementInfo0.getMap();
                    if(map0 != null) {
                        JavaTypeQualifiers javaTypeQualifiers0 = (JavaTypeQualifiers)map0.get(v);
                        if(javaTypeQualifiers0 != null) {
                            return javaTypeQualifiers0;
                        }
                    }
                }
                return v < 0 || v > ArraysKt.getLastIndex(this.$computedResult) ? JavaTypeQualifiers.Companion.getNONE() : this.$computedResult[v];
            }
        };
    }

    private final JavaTypeQualifiersByElementType extractAndMergeDefaultQualifiers(KotlinTypeMarker kotlinTypeMarker0, JavaTypeQualifiersByElementType javaTypeQualifiersByElementType0) {
        return this.getAnnotationTypeQualifierResolver().extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementType0, this.getAnnotations(kotlinTypeMarker0));
    }

    private final JavaTypeQualifiers extractQualifiers(KotlinTypeMarker kotlinTypeMarker0) {
        NullabilityQualifier nullabilityQualifier1;
        NullabilityQualifier nullabilityQualifier0 = this.getNullabilityQualifier(kotlinTypeMarker0);
        MutabilityQualifier mutabilityQualifier0 = null;
        if(nullabilityQualifier0 == null) {
            KotlinTypeMarker kotlinTypeMarker1 = this.getEnhancedForWarnings(kotlinTypeMarker0);
            nullabilityQualifier1 = kotlinTypeMarker1 == null ? null : this.getNullabilityQualifier(kotlinTypeMarker1);
        }
        else {
            nullabilityQualifier1 = nullabilityQualifier0;
        }
        TypeSystemContext typeSystemContext0 = this.getTypeSystem();
        FqNameUnsafe fqNameUnsafe0 = this.getFqNameUnsafe(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker0));
        if(JavaToKotlinClassMap.INSTANCE.isReadOnly(fqNameUnsafe0)) {
            mutabilityQualifier0 = MutabilityQualifier.READ_ONLY;
        }
        else {
            FqNameUnsafe fqNameUnsafe1 = this.getFqNameUnsafe(typeSystemContext0.upperBoundIfFlexible(kotlinTypeMarker0));
            if(JavaToKotlinClassMap.INSTANCE.isMutable(fqNameUnsafe1)) {
                mutabilityQualifier0 = MutabilityQualifier.MUTABLE;
            }
        }
        boolean z = false;
        boolean z1 = this.getTypeSystem().isDefinitelyNotNullType(kotlinTypeMarker0) || this.isNotNullTypeParameterCompat(kotlinTypeMarker0);
        if(nullabilityQualifier1 != nullabilityQualifier0) {
            z = true;
        }
        return new JavaTypeQualifiers(nullabilityQualifier1, mutabilityQualifier0, z1, z);
    }

    private final JavaTypeQualifiers extractQualifiersFromAnnotations(TypeAndDefaultQualifiers abstractSignatureParts$TypeAndDefaultQualifiers0) {
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus3;
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus2;
        TypeParameterMarker typeParameterMarker1;
        Iterable iterable0;
        NullabilityQualifier nullabilityQualifier0 = null;
        if(abstractSignatureParts$TypeAndDefaultQualifiers0.getType() == null) {
            TypeParameterMarker typeParameterMarker0 = abstractSignatureParts$TypeAndDefaultQualifiers0.getTypeParameterForArgument();
            if((typeParameterMarker0 == null ? null : this.getTypeSystem().getVariance(typeParameterMarker0)) == TypeVariance.IN) {
                return JavaTypeQualifiers.Companion.getNONE();
            }
        }
        boolean z = true;
        boolean z1 = abstractSignatureParts$TypeAndDefaultQualifiers0.getTypeParameterForArgument() == null;
        KotlinTypeMarker kotlinTypeMarker0 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
        if(kotlinTypeMarker0 == null) {
            iterable0 = CollectionsKt.emptyList();
        }
        else {
            iterable0 = this.getAnnotations(kotlinTypeMarker0);
            if(iterable0 == null) {
                iterable0 = CollectionsKt.emptyList();
            }
        }
        TypeSystemContext typeSystemContext0 = this.getTypeSystem();
        KotlinTypeMarker kotlinTypeMarker1 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
        if(kotlinTypeMarker1 == null) {
            typeParameterMarker1 = null;
        }
        else {
            TypeConstructorMarker typeConstructorMarker0 = typeSystemContext0.typeConstructor(kotlinTypeMarker1);
            typeParameterMarker1 = typeConstructorMarker0 == null ? null : typeSystemContext0.getTypeParameterClassifier(typeConstructorMarker0);
        }
        boolean z2 = this.getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS;
        if(z1) {
            if(z2 || !this.getEnableImprovementsInStrictMode()) {
                iterable0 = CollectionsKt.plus(this.getContainerAnnotations(), iterable0);
            }
            else {
                KotlinTypeMarker kotlinTypeMarker2 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
                if(kotlinTypeMarker2 == null || !this.isArrayOrPrimitiveArray(kotlinTypeMarker2)) {
                    iterable0 = CollectionsKt.plus(this.getContainerAnnotations(), iterable0);
                }
                else {
                    Iterable iterable1 = this.getContainerAnnotations();
                    Collection collection0 = new ArrayList();
                    for(Object object0: iterable1) {
                        if(!this.getAnnotationTypeQualifierResolver().isTypeUseAnnotation(object0)) {
                            collection0.add(object0);
                        }
                    }
                    iterable0 = CollectionsKt.plus(((List)collection0), iterable0);
                }
            }
        }
        MutabilityQualifier mutabilityQualifier0 = this.getAnnotationTypeQualifierResolver().extractMutability(iterable0);
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0 = this.getAnnotationTypeQualifierResolver().extractNullability(iterable0, new Function1(abstractSignatureParts$TypeAndDefaultQualifiers0) {
            final TypeAndDefaultQualifiers $this_extractQualifiersFromAnnotations;

            {
                AbstractSignatureParts.this = abstractSignatureParts0;
                this.$this_extractQualifiersFromAnnotations = abstractSignatureParts$TypeAndDefaultQualifiers0;
                super(1);
            }

            public final Boolean invoke(Object object0) {
                Intrinsics.checkNotNullParameter(object0, "$this$extractNullability");
                KotlinTypeMarker kotlinTypeMarker0 = this.$this_extractQualifiersFromAnnotations.getType();
                return Boolean.valueOf(AbstractSignatureParts.this.forceWarning(object0, kotlinTypeMarker0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        });
        if(nullabilityQualifierWithMigrationStatus0 != null) {
            NullabilityQualifier nullabilityQualifier1 = nullabilityQualifierWithMigrationStatus0.getQualifier();
            if(nullabilityQualifierWithMigrationStatus0.getQualifier() != NullabilityQualifier.NOT_NULL || typeParameterMarker1 == null) {
                z = false;
            }
            return new JavaTypeQualifiers(nullabilityQualifier1, mutabilityQualifier0, z, nullabilityQualifierWithMigrationStatus0.isForWarningOnly());
        }
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementType0 = abstractSignatureParts$TypeAndDefaultQualifiers0.getDefaultQualifiers();
        JavaDefaultQualifiers javaDefaultQualifiers0 = javaTypeQualifiersByElementType0 == null ? null : javaTypeQualifiersByElementType0.get((z1 || z2 ? this.getContainerApplicabilityType() : AnnotationQualifierApplicabilityType.TYPE_USE));
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus1 = typeParameterMarker1 == null ? null : this.getBoundsNullability(typeParameterMarker1);
        if(nullabilityQualifierWithMigrationStatus1 == null) {
            nullabilityQualifierWithMigrationStatus2 = javaDefaultQualifiers0 == null ? null : javaDefaultQualifiers0.getNullabilityQualifier();
        }
        else {
            nullabilityQualifierWithMigrationStatus2 = NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus1, NullabilityQualifier.NOT_NULL, false, 2, null);
            if(nullabilityQualifierWithMigrationStatus2 == null) {
                nullabilityQualifierWithMigrationStatus2 = javaDefaultQualifiers0 == null ? null : javaDefaultQualifiers0.getNullabilityQualifier();
            }
        }
        boolean z3 = (nullabilityQualifierWithMigrationStatus1 == null ? null : nullabilityQualifierWithMigrationStatus1.getQualifier()) == NullabilityQualifier.NOT_NULL || typeParameterMarker1 != null && javaDefaultQualifiers0 != null && javaDefaultQualifiers0.getDefinitelyNotNull();
        TypeParameterMarker typeParameterMarker2 = abstractSignatureParts$TypeAndDefaultQualifiers0.getTypeParameterForArgument();
        if(typeParameterMarker2 == null) {
            nullabilityQualifierWithMigrationStatus3 = null;
        }
        else {
            nullabilityQualifierWithMigrationStatus3 = this.getBoundsNullability(typeParameterMarker2);
            if(nullabilityQualifierWithMigrationStatus3 == null) {
                nullabilityQualifierWithMigrationStatus3 = null;
            }
            else if(nullabilityQualifierWithMigrationStatus3.getQualifier() == NullabilityQualifier.NULLABLE) {
                nullabilityQualifierWithMigrationStatus3 = NullabilityQualifierWithMigrationStatus.copy$default(nullabilityQualifierWithMigrationStatus3, NullabilityQualifier.FORCE_FLEXIBILITY, false, 2, null);
            }
        }
        NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus4 = this.mostSpecific(nullabilityQualifierWithMigrationStatus3, nullabilityQualifierWithMigrationStatus2);
        if(nullabilityQualifierWithMigrationStatus4 != null) {
            nullabilityQualifier0 = nullabilityQualifierWithMigrationStatus4.getQualifier();
        }
        if(nullabilityQualifierWithMigrationStatus4 == null || !nullabilityQualifierWithMigrationStatus4.isForWarningOnly()) {
            z = false;
        }
        return new JavaTypeQualifiers(nullabilityQualifier0, mutabilityQualifier0, z3, z);
    }

    private final List flattenTree(Object object0, Function1 function10) {
        ArrayList arrayList0 = new ArrayList(1);
        this.flattenTree(object0, arrayList0, function10);
        return arrayList0;
    }

    private final void flattenTree(Object object0, List list0, Function1 function10) {
        list0.add(object0);
        Iterable iterable0 = (Iterable)function10.invoke(object0);
        if(iterable0 != null) {
            for(Object object1: iterable0) {
                this.flattenTree(object1, list0, function10);
            }
        }
    }

    public abstract boolean forceWarning(Object arg1, KotlinTypeMarker arg2);

    public abstract AbstractAnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver();

    public abstract Iterable getAnnotations(KotlinTypeMarker arg1);

    private final NullabilityQualifierWithMigrationStatus getBoundsNullability(TypeParameterMarker typeParameterMarker0) {
        List list1;
        TypeSystemContext typeSystemContext0 = this.getTypeSystem();
        if(!this.isFromJava(typeParameterMarker0)) {
            return null;
        }
        List list0 = typeSystemContext0.getUpperBounds(typeParameterMarker0);
        if(!(list0 instanceof Collection) || !list0.isEmpty()) {
        alab1:
            for(Object object0: list0) {
                if(!typeSystemContext0.isError(((KotlinTypeMarker)object0))) {
                    if(!(list0 instanceof Collection) || !list0.isEmpty()) {
                        for(Object object1: list0) {
                            if(this.getNullabilityQualifier(((KotlinTypeMarker)object1)) == null) {
                                continue;
                            }
                            list1 = list0;
                            goto label_30;
                        }
                    }
                    if(list0 instanceof Collection && list0.isEmpty()) {
                        break;
                    }
                    Iterator iterator2 = list0.iterator();
                    while(true) {
                        if(!iterator2.hasNext()) {
                            break alab1;
                        }
                        Object object2 = iterator2.next();
                        if(this.getEnhancedForWarnings(((KotlinTypeMarker)object2)) == null) {
                            continue;
                        }
                        Collection collection0 = new ArrayList();
                        for(Object object3: list0) {
                            KotlinTypeMarker kotlinTypeMarker0 = this.getEnhancedForWarnings(((KotlinTypeMarker)object3));
                            if(kotlinTypeMarker0 != null) {
                                collection0.add(kotlinTypeMarker0);
                            }
                        }
                        list1 = (List)collection0;
                    label_30:
                        if(!(list1 instanceof Collection) || !list1.isEmpty()) {
                            for(Object object4: list1) {
                                if(!typeSystemContext0.isNullableType(((KotlinTypeMarker)object4))) {
                                    return list1 == list0 ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, false) : new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NOT_NULL, true);
                                }
                                if(false) {
                                    break;
                                }
                            }
                        }
                        return list1 == list0 ? new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, false) : new NullabilityQualifierWithMigrationStatus(NullabilityQualifier.NULLABLE, true);
                    }
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    public abstract Iterable getContainerAnnotations();

    public abstract AnnotationQualifierApplicabilityType getContainerApplicabilityType();

    public abstract JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers();

    public abstract boolean getContainerIsVarargParameter();

    public abstract boolean getEnableImprovementsInStrictMode();

    public abstract KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker arg1);

    public boolean getForceOnlyHeadTypeConstructor() [...] // Inlined contents

    public abstract FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker arg1);

    private final NullabilityQualifier getNullabilityQualifier(KotlinTypeMarker kotlinTypeMarker0) {
        TypeSystemContext typeSystemContext0 = this.getTypeSystem();
        if(typeSystemContext0.isMarkedNullable(typeSystemContext0.lowerBoundIfFlexible(kotlinTypeMarker0))) {
            return NullabilityQualifier.NULLABLE;
        }
        return typeSystemContext0.isMarkedNullable(typeSystemContext0.upperBoundIfFlexible(kotlinTypeMarker0)) ? null : NullabilityQualifier.NOT_NULL;
    }

    public abstract boolean getSkipRawTypeArguments();

    public abstract TypeSystemContext getTypeSystem();

    public abstract boolean isArrayOrPrimitiveArray(KotlinTypeMarker arg1);

    public abstract boolean isCovariant();

    public abstract boolean isEqual(KotlinTypeMarker arg1, KotlinTypeMarker arg2);

    public abstract boolean isFromJava(TypeParameterMarker arg1);

    public boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return false;
    }

    // 去混淆评级： 中等(50)
    private final NullabilityQualifierWithMigrationStatus mostSpecific(NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus0, NullabilityQualifierWithMigrationStatus nullabilityQualifierWithMigrationStatus1) {
        return nullabilityQualifierWithMigrationStatus0 == null || nullabilityQualifierWithMigrationStatus1 != null && (nullabilityQualifierWithMigrationStatus0.isForWarningOnly() && !nullabilityQualifierWithMigrationStatus1.isForWarningOnly() || (nullabilityQualifierWithMigrationStatus0.isForWarningOnly() || !nullabilityQualifierWithMigrationStatus1.isForWarningOnly()) && (nullabilityQualifierWithMigrationStatus0.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus1.getQualifier()) < 0 || nullabilityQualifierWithMigrationStatus0.getQualifier().compareTo(nullabilityQualifierWithMigrationStatus1.getQualifier()) <= 0)) ? nullabilityQualifierWithMigrationStatus1 : nullabilityQualifierWithMigrationStatus0;
    }

    private final List toIndexed(KotlinTypeMarker kotlinTypeMarker0) {
        TypeSystemContext typeSystemContext0 = this.getTypeSystem();
        return this.flattenTree(new TypeAndDefaultQualifiers(kotlinTypeMarker0, this.extractAndMergeDefaultQualifiers(kotlinTypeMarker0, this.getContainerDefaultTypeQualifiers()), null), new Function1(typeSystemContext0) {
            final TypeSystemContext $this_with;

            {
                AbstractSignatureParts.this = abstractSignatureParts0;
                this.$this_with = typeSystemContext0;
                super(1);
            }

            public final Iterable invoke(TypeAndDefaultQualifiers abstractSignatureParts$TypeAndDefaultQualifiers0) {
                TypeAndDefaultQualifiers abstractSignatureParts$TypeAndDefaultQualifiers1;
                RawTypeMarker rawTypeMarker0;
                Intrinsics.checkNotNullParameter(abstractSignatureParts$TypeAndDefaultQualifiers0, "it");
                if(AbstractSignatureParts.this.getSkipRawTypeArguments()) {
                    KotlinTypeMarker kotlinTypeMarker0 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
                    if(kotlinTypeMarker0 == null) {
                        rawTypeMarker0 = null;
                    }
                    else {
                        FlexibleTypeMarker flexibleTypeMarker0 = this.$this_with.asFlexibleType(kotlinTypeMarker0);
                        rawTypeMarker0 = flexibleTypeMarker0 == null ? null : this.$this_with.asRawType(flexibleTypeMarker0);
                    }
                    if(rawTypeMarker0 != null) {
                        return null;
                    }
                }
                KotlinTypeMarker kotlinTypeMarker1 = abstractSignatureParts$TypeAndDefaultQualifiers0.getType();
                if(kotlinTypeMarker1 != null) {
                    TypeConstructorMarker typeConstructorMarker0 = this.$this_with.typeConstructor(kotlinTypeMarker1);
                    if(typeConstructorMarker0 != null) {
                        List list0 = this.$this_with.getParameters(typeConstructorMarker0);
                        if(list0 != null) {
                            Iterable iterable0 = this.$this_with.getArguments(abstractSignatureParts$TypeAndDefaultQualifiers0.getType());
                            TypeSystemContext typeSystemContext0 = this.$this_with;
                            AbstractSignatureParts abstractSignatureParts0 = AbstractSignatureParts.this;
                            Iterator iterator0 = list0.iterator();
                            Iterator iterator1 = iterable0.iterator();
                            ArrayList arrayList0 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(list0, 10), CollectionsKt.collectionSizeOrDefault(iterable0, 10)));
                            while(iterator0.hasNext() && iterator1.hasNext()) {
                                Object object0 = iterator0.next();
                                Object object1 = iterator1.next();
                                TypeArgumentMarker typeArgumentMarker0 = (TypeArgumentMarker)object1;
                                TypeParameterMarker typeParameterMarker0 = (TypeParameterMarker)object0;
                                if(typeSystemContext0.isStarProjection(typeArgumentMarker0)) {
                                    abstractSignatureParts$TypeAndDefaultQualifiers1 = new TypeAndDefaultQualifiers(null, abstractSignatureParts$TypeAndDefaultQualifiers0.getDefaultQualifiers(), typeParameterMarker0);
                                }
                                else {
                                    KotlinTypeMarker kotlinTypeMarker2 = typeSystemContext0.getType(typeArgumentMarker0);
                                    abstractSignatureParts$TypeAndDefaultQualifiers1 = new TypeAndDefaultQualifiers(kotlinTypeMarker2, abstractSignatureParts0.extractAndMergeDefaultQualifiers(kotlinTypeMarker2, abstractSignatureParts$TypeAndDefaultQualifiers0.getDefaultQualifiers()), typeParameterMarker0);
                                }
                                arrayList0.add(abstractSignatureParts$TypeAndDefaultQualifiers1);
                            }
                            return arrayList0;
                        }
                    }
                }
                return null;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((TypeAndDefaultQualifiers)object0));
            }
        });
    }
}

