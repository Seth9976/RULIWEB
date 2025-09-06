package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public final class IndexedParametersSubstitution extends TypeSubstitution {
    private final boolean approximateContravariantCapturedTypes;
    private final TypeProjection[] arguments;
    private final TypeParameterDescriptor[] parameters;

    public IndexedParametersSubstitution(List list0, List list1) {
        Intrinsics.checkNotNullParameter(list0, "parameters");
        Intrinsics.checkNotNullParameter(list1, "argumentsList");
        this(((TypeParameterDescriptor[])list0.toArray(new TypeParameterDescriptor[0])), ((TypeProjection[])list1.toArray(new TypeProjection[0])), false, 4, null);
    }

    public IndexedParametersSubstitution(TypeParameterDescriptor[] arr_typeParameterDescriptor, TypeProjection[] arr_typeProjection, boolean z) {
        Intrinsics.checkNotNullParameter(arr_typeParameterDescriptor, "parameters");
        Intrinsics.checkNotNullParameter(arr_typeProjection, "arguments");
        super();
        this.parameters = arr_typeParameterDescriptor;
        this.arguments = arr_typeProjection;
        this.approximateContravariantCapturedTypes = z;
    }

    public IndexedParametersSubstitution(TypeParameterDescriptor[] arr_typeParameterDescriptor, TypeProjection[] arr_typeProjection, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            z = false;
        }
        this(arr_typeParameterDescriptor, arr_typeProjection, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean approximateContravariantCapturedTypes() {
        return this.approximateContravariantCapturedTypes;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public TypeProjection get(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "key");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        TypeParameterDescriptor typeParameterDescriptor0 = classifierDescriptor0 instanceof TypeParameterDescriptor ? ((TypeParameterDescriptor)classifierDescriptor0) : null;
        if(typeParameterDescriptor0 == null) {
            return null;
        }
        int v = typeParameterDescriptor0.getIndex();
        return v >= this.parameters.length || !Intrinsics.areEqual(this.parameters[v].getTypeConstructor(), typeParameterDescriptor0.getTypeConstructor()) ? null : this.arguments[v];
    }

    public final TypeProjection[] getArguments() {
        return this.arguments;
    }

    public final TypeParameterDescriptor[] getParameters() {
        return this.parameters;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeSubstitution
    public boolean isEmpty() {
        return this.arguments.length == 0;
    }
}

