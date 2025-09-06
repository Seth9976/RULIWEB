package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public abstract class TypeConstructorSubstitution extends TypeSubstitution {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final TypeSubstitution create(KotlinType kotlinType0) {
            Intrinsics.checkNotNullParameter(kotlinType0, "kotlinType");
            return this.create(kotlinType0.getConstructor(), kotlinType0.getArguments());
        }

        @JvmStatic
        public final TypeSubstitution create(TypeConstructor typeConstructor0, List list0) {
            Intrinsics.checkNotNullParameter(typeConstructor0, "typeConstructor");
            Intrinsics.checkNotNullParameter(list0, "arguments");
            List list1 = typeConstructor0.getParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "typeConstructor.parameters");
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)CollectionsKt.lastOrNull(list1);
            if(typeParameterDescriptor0 != null && typeParameterDescriptor0.isCapturedFromOuterDeclaration()) {
                List list2 = typeConstructor0.getParameters();
                Intrinsics.checkNotNullExpressionValue(list2, "typeConstructor.parameters");
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                for(Object object0: list2) {
                    arrayList0.add(((TypeParameterDescriptor)object0).getTypeConstructor());
                }
                return Companion.createByConstructorsMap$default(this, MapsKt.toMap(CollectionsKt.zip(arrayList0, list0)), false, 2, null);
            }
            return new IndexedParametersSubstitution(list1, list0);
        }

        @JvmStatic
        public final TypeConstructorSubstitution createByConstructorsMap(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "map");
            return Companion.createByConstructorsMap$default(this, map0, false, 2, null);
        }

        @JvmStatic
        public final TypeConstructorSubstitution createByConstructorsMap(Map map0, boolean z) {
            Intrinsics.checkNotNullParameter(map0, "map");
            return new TypeConstructorSubstitution() {
                @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean approximateCapturedTypes() {
                    return this.$approximateCapturedTypes;
                }

                @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
                public TypeProjection get(TypeConstructor typeConstructor0) {
                    Intrinsics.checkNotNullParameter(typeConstructor0, "key");
                    return (TypeProjection)z.get(typeConstructor0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
                public boolean isEmpty() {
                    return z.isEmpty();
                }
            };
        }

        public static TypeConstructorSubstitution createByConstructorsMap$default(Companion typeConstructorSubstitution$Companion0, Map map0, boolean z, int v, Object object0) {
            if((v & 2) != 0) {
                z = false;
            }
            return typeConstructorSubstitution$Companion0.createByConstructorsMap(map0, z);
        }
    }

    public static final Companion Companion;

    static {
        TypeConstructorSubstitution.Companion = new Companion(null);
    }

    @JvmStatic
    public static final TypeSubstitution create(TypeConstructor typeConstructor0, List list0) {
        return TypeConstructorSubstitution.Companion.create(typeConstructor0, list0);
    }

    @JvmStatic
    public static final TypeConstructorSubstitution createByConstructorsMap(Map map0) {
        return TypeConstructorSubstitution.Companion.createByConstructorsMap(map0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        return this.get(kotlinType0.getConstructor());
    }

    public abstract TypeProjection get(TypeConstructor arg1);
}

