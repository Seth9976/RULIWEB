package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class NewCapturedTypeConstructor implements CapturedTypeConstructor {
    private final Lazy _supertypes$delegate;
    private final NewCapturedTypeConstructor original;
    private final TypeProjection projection;
    private Function0 supertypesComputation;
    private final TypeParameterDescriptor typeParameter;

    public NewCapturedTypeConstructor(TypeProjection typeProjection0, List list0, NewCapturedTypeConstructor newCapturedTypeConstructor0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "projection");
        Intrinsics.checkNotNullParameter(list0, "supertypes");
        this(typeProjection0, new Function0() {
            final List $supertypes;

            {
                this.$supertypes = list0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return this.$supertypes;
            }
        }, newCapturedTypeConstructor0, null, 8, null);
    }

    public NewCapturedTypeConstructor(TypeProjection typeProjection0, List list0, NewCapturedTypeConstructor newCapturedTypeConstructor0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            newCapturedTypeConstructor0 = null;
        }
        this(typeProjection0, list0, newCapturedTypeConstructor0);
    }

    public NewCapturedTypeConstructor(TypeProjection typeProjection0, Function0 function00, NewCapturedTypeConstructor newCapturedTypeConstructor0, TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "projection");
        super();
        this.projection = typeProjection0;
        this.supertypesComputation = function00;
        this.original = newCapturedTypeConstructor0;
        this.typeParameter = typeParameterDescriptor0;
        Function0 function01 = new Function0() {
            {
                NewCapturedTypeConstructor.this = newCapturedTypeConstructor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                Function0 function00 = NewCapturedTypeConstructor.access$getSupertypesComputation$p(NewCapturedTypeConstructor.this);
                return function00 == null ? null : ((List)function00.invoke());
            }
        };
        this._supertypes$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    public NewCapturedTypeConstructor(TypeProjection typeProjection0, Function0 function00, NewCapturedTypeConstructor newCapturedTypeConstructor0, TypeParameterDescriptor typeParameterDescriptor0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            function00 = null;
        }
        if((v & 4) != 0) {
            newCapturedTypeConstructor0 = null;
        }
        if((v & 8) != 0) {
            typeParameterDescriptor0 = null;
        }
        this(typeProjection0, function00, newCapturedTypeConstructor0, typeParameterDescriptor0);
    }

    public static final Function0 access$getSupertypesComputation$p(NewCapturedTypeConstructor newCapturedTypeConstructor0) {
        return newCapturedTypeConstructor0.supertypesComputation;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!Intrinsics.areEqual(this.getClass(), (object0 == null ? null : object0.getClass()))) {
            return false;
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedTypeConstructor");
        NewCapturedTypeConstructor newCapturedTypeConstructor0 = (NewCapturedTypeConstructor)object0;
        NewCapturedTypeConstructor newCapturedTypeConstructor1 = this.original == null ? this : this.original;
        NewCapturedTypeConstructor newCapturedTypeConstructor2 = newCapturedTypeConstructor0.original;
        if(newCapturedTypeConstructor2 != null) {
            newCapturedTypeConstructor0 = newCapturedTypeConstructor2;
        }
        return newCapturedTypeConstructor1 == newCapturedTypeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        KotlinType kotlinType0 = this.getProjection().getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "projection.type");
        return TypeUtilsKt.getBuiltIns(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public List getParameters() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor
    public TypeProjection getProjection() {
        return this.projection;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public Collection getSupertypes() {
        return this.getSupertypes();
    }

    public List getSupertypes() {
        List list0 = this.get_supertypes();
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    private final List get_supertypes() {
        return (List)this._supertypes$delegate.getValue();
    }

    @Override
    public int hashCode() {
        return this.original == null ? super.hashCode() : this.original.hashCode();
    }

    public final void initializeSupertypes(List list0) {
        Intrinsics.checkNotNullParameter(list0, "supertypes");
        this.supertypesComputation = new Function0() {
            final List $supertypes;

            {
                this.$supertypes = list0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return this.$supertypes;
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public boolean isDenotable() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public NewCapturedTypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        TypeProjection typeProjection0 = this.getProjection().refine(kotlinTypeRefiner0);
        Intrinsics.checkNotNullExpressionValue(typeProjection0, "projection.refine(kotlinTypeRefiner)");
        return this.supertypesComputation == null ? new NewCapturedTypeConstructor(typeProjection0, null, (this.original == null ? this : this.original), this.typeParameter) : new NewCapturedTypeConstructor(typeProjection0, new Function0(kotlinTypeRefiner0) {
            final KotlinTypeRefiner $kotlinTypeRefiner;

            {
                NewCapturedTypeConstructor.this = newCapturedTypeConstructor0;
                this.$kotlinTypeRefiner = kotlinTypeRefiner0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                Iterable iterable0 = NewCapturedTypeConstructor.this.getSupertypes();
                KotlinTypeRefiner kotlinTypeRefiner0 = this.$kotlinTypeRefiner;
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    arrayList0.add(((UnwrappedType)object0).refine(kotlinTypeRefiner0));
                }
                return arrayList0;
            }
        }, (this.original == null ? this : this.original), this.typeParameter);
    }

    @Override
    public String toString() {
        return "CapturedType(" + this.getProjection() + ')';
    }
}

