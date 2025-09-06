package kotlin.reflect.jvm.internal.impl.resolve.calls.inference;

import java.util.ArrayList;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.IndexedParametersSubstitution;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public final class CapturedTypeConstructorKt {
    private static final TypeProjection createCapturedIfNeeded(TypeProjection typeProjection0, TypeParameterDescriptor typeParameterDescriptor0) {
        if(typeParameterDescriptor0 != null && typeProjection0.getProjectionKind() != Variance.INVARIANT) {
            if(typeParameterDescriptor0.getVariance() == typeProjection0.getProjectionKind()) {
                if(typeProjection0.isStarProjection()) {
                    Intrinsics.checkNotNullExpressionValue(LockBasedStorageManager.NO_LOCKS, "NO_LOCKS");
                    Function0 function00 = new Function0(typeProjection0) {
                        final TypeProjection $this_createCapturedIfNeeded;

                        {
                            this.$this_createCapturedIfNeeded = typeProjection0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final KotlinType invoke() {
                            KotlinType kotlinType0 = this.$this_createCapturedIfNeeded.getType();
                            Intrinsics.checkNotNullExpressionValue(kotlinType0, "this@createCapturedIfNeeded.type");
                            return kotlinType0;
                        }
                    };
                    return new TypeProjectionImpl(new LazyWrappedType(LockBasedStorageManager.NO_LOCKS, function00));
                }
                return new TypeProjectionImpl(typeProjection0.getType());
            }
            return new TypeProjectionImpl(CapturedTypeConstructorKt.createCapturedType(typeProjection0));
        }
        return typeProjection0;
    }

    public static final KotlinType createCapturedType(TypeProjection typeProjection0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "typeProjection");
        return new CapturedType(typeProjection0, null, false, null, 14, null);
    }

    public static final boolean isCaptured(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0.getConstructor() instanceof CapturedTypeConstructor;
    }

    public static final TypeSubstitution wrapWithCapturingSubstitution(TypeSubstitution typeSubstitution0, boolean z) {
        Intrinsics.checkNotNullParameter(typeSubstitution0, "<this>");
        if(typeSubstitution0 instanceof IndexedParametersSubstitution) {
            TypeParameterDescriptor[] arr_typeParameterDescriptor = ((IndexedParametersSubstitution)typeSubstitution0).getParameters();
            Iterable iterable0 = ArraysKt.zip(((IndexedParametersSubstitution)typeSubstitution0).getArguments(), ((IndexedParametersSubstitution)typeSubstitution0).getParameters());
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            for(Object object0: iterable0) {
                arrayList0.add(CapturedTypeConstructorKt.createCapturedIfNeeded(((TypeProjection)((Pair)object0).getFirst()), ((TypeParameterDescriptor)((Pair)object0).getSecond())));
            }
            return new IndexedParametersSubstitution(arr_typeParameterDescriptor, ((TypeProjection[])arrayList0.toArray(new TypeProjection[0])), z);
        }
        return new DelegatedTypeSubstitution(typeSubstitution0) {
            @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution
            public boolean approximateContravariantCapturedTypes() {
                return z;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatedTypeSubstitution
            public TypeProjection get(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "key");
                TypeProjection typeProjection0 = super.get(kotlinType0);
                TypeParameterDescriptor typeParameterDescriptor0 = null;
                if(typeProjection0 != null) {
                    ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
                    if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
                        typeParameterDescriptor0 = (TypeParameterDescriptor)classifierDescriptor0;
                    }
                    return CapturedTypeConstructorKt.createCapturedIfNeeded(typeProjection0, typeParameterDescriptor0);
                }
                return null;
            }
        };
    }

    public static TypeSubstitution wrapWithCapturingSubstitution$default(TypeSubstitution typeSubstitution0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = true;
        }
        return CapturedTypeConstructorKt.wrapWithCapturingSubstitution(typeSubstitution0, z);
    }
}

