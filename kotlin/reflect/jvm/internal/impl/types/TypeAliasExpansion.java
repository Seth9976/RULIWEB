package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public final class TypeAliasExpansion {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final TypeAliasExpansion create(TypeAliasExpansion typeAliasExpansion0, TypeAliasDescriptor typeAliasDescriptor0, List list0) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "typeAliasDescriptor");
            Intrinsics.checkNotNullParameter(list0, "arguments");
            List list1 = typeAliasDescriptor0.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "typeAliasDescriptor.typeConstructor.parameters");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
            for(Object object0: list1) {
                arrayList0.add(((TypeParameterDescriptor)object0).getOriginal());
            }
            return new TypeAliasExpansion(typeAliasExpansion0, typeAliasDescriptor0, list0, MapsKt.toMap(CollectionsKt.zip(arrayList0, list0)), null);
        }
    }

    public static final Companion Companion;
    private final List arguments;
    private final TypeAliasDescriptor descriptor;
    private final Map mapping;
    private final TypeAliasExpansion parent;

    static {
        TypeAliasExpansion.Companion = new Companion(null);
    }

    private TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion0, TypeAliasDescriptor typeAliasDescriptor0, List list0, Map map0) {
        this.parent = typeAliasExpansion0;
        this.descriptor = typeAliasDescriptor0;
        this.arguments = list0;
        this.mapping = map0;
    }

    public TypeAliasExpansion(TypeAliasExpansion typeAliasExpansion0, TypeAliasDescriptor typeAliasDescriptor0, List list0, Map map0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(typeAliasExpansion0, typeAliasDescriptor0, list0, map0);
    }

    public final List getArguments() {
        return this.arguments;
    }

    public final TypeAliasDescriptor getDescriptor() {
        return this.descriptor;
    }

    public final TypeProjection getReplacement(TypeConstructor typeConstructor0) {
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        return classifierDescriptor0 instanceof TypeParameterDescriptor ? ((TypeProjection)this.mapping.get(classifierDescriptor0)) : null;
    }

    public final boolean isRecursion(TypeAliasDescriptor typeAliasDescriptor0) {
        Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "descriptor");
        if(!Intrinsics.areEqual(this.descriptor, typeAliasDescriptor0)) {
            return this.parent == null ? false : this.parent.isRecursion(typeAliasDescriptor0);
        }
        return true;
    }
}

