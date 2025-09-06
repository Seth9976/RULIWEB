package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.IntersectionTypeConstructorMarker;

public final class IntersectionTypeConstructor implements TypeConstructor, IntersectionTypeConstructorMarker {
    private KotlinType alternative;
    private final int hashCode;
    private final LinkedHashSet intersectedTypes;

    public IntersectionTypeConstructor(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "typesToIntersect");
        super();
        collection0.isEmpty();
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(collection0);
        this.intersectedTypes = linkedHashSet0;
        this.hashCode = linkedHashSet0.hashCode();
    }

    private IntersectionTypeConstructor(Collection collection0, KotlinType kotlinType0) {
        this(collection0);
        this.alternative = kotlinType0;
    }

    public final MemberScope createScopeForKotlinType() {
        return TypeIntersectionScope.Companion.create("member scope for intersection type", this.intersectedTypes);
    }

    public final SimpleType createType() {
        return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(TypeAttributes.Companion.getEmpty(), this, CollectionsKt.emptyList(), false, this.createScopeForKotlinType(), new Function1() {
            {
                IntersectionTypeConstructor.this = intersectionTypeConstructor0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
                return IntersectionTypeConstructor.this.refine(kotlinTypeRefiner0).createType();
            }
        });
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof IntersectionTypeConstructor ? Intrinsics.areEqual(this.intersectedTypes, ((IntersectionTypeConstructor)object0).intersectedTypes) : false;
    }

    public final KotlinType getAlternativeType() {
        return this.alternative;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        Object object0 = this.intersectedTypes.iterator().next();
        KotlinBuiltIns kotlinBuiltIns0 = ((KotlinType)object0).getConstructor().getBuiltIns();
        Intrinsics.checkNotNullExpressionValue(kotlinBuiltIns0, "intersectedTypes.iterato…xt().constructor.builtIns");
        return kotlinBuiltIns0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        return this.intersectedTypes;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    public final String makeDebugNameForIntersectionType(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "getProperTypeRelatedToStringify");
        Comparator comparator0 = new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                Intrinsics.checkNotNullExpressionValue(((KotlinType)object0), "it");
                Object object2 = this.$getProperTypeRelatedToStringify$inlined.invoke(((KotlinType)object0));
                Intrinsics.checkNotNullExpressionValue(((KotlinType)object1), "it");
                return ComparisonsKt.compareValues(object2.toString(), this.$getProperTypeRelatedToStringify$inlined.invoke(((KotlinType)object1)).toString());
            }
        };
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(this.intersectedTypes, comparator0), " & ", "{", "}", 0, null, new Function1() {
            final Function1 $getProperTypeRelatedToStringify;

            {
                this.$getProperTypeRelatedToStringify = function10;
                super(1);
            }

            public final CharSequence invoke(KotlinType kotlinType0) {
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it");
                return this.$getProperTypeRelatedToStringify.invoke(kotlinType0).toString();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }
        }, 24, null);
    }

    public static String makeDebugNameForIntersectionType$default(IntersectionTypeConstructor intersectionTypeConstructor0, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            function10 = kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1.INSTANCE;
        }
        return intersectionTypeConstructor0.makeDebugNameForIntersectionType(function10);

        final class kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1();
            }

            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor.makeDebugNameForIntersectionType.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }

            public final String invoke(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "it");
                return kotlinType0.toString();
            }
        }

    }

    public IntersectionTypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        IntersectionTypeConstructor intersectionTypeConstructor0;
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        Iterable iterable0 = this.getSupertypes();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        Iterator iterator0 = iterable0.iterator();
        boolean z;
        for(z = false; true; z = true) {
            intersectionTypeConstructor0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            arrayList0.add(((KotlinType)object0).refine(kotlinTypeRefiner0));
        }
        if(z) {
            KotlinType kotlinType0 = this.getAlternativeType();
            if(kotlinType0 != null) {
                intersectionTypeConstructor0 = kotlinType0.refine(kotlinTypeRefiner0);
            }
            intersectionTypeConstructor0 = new IntersectionTypeConstructor(arrayList0).setAlternative(((KotlinType)intersectionTypeConstructor0));
        }
        return intersectionTypeConstructor0 == null ? this : intersectionTypeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public final IntersectionTypeConstructor setAlternative(KotlinType kotlinType0) {
        return new IntersectionTypeConstructor(this.intersectedTypes, kotlinType0);
    }

    @Override
    public String toString() {
        return IntersectionTypeConstructor.makeDebugNameForIntersectionType$default(this, null, 1, null);
    }
}

