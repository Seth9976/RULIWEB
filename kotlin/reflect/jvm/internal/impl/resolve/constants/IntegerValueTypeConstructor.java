package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class IntegerValueTypeConstructor implements TypeConstructor {
    private final ModuleDescriptor module;
    private final ArrayList supertypes;
    private final long value;

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
    public KotlinBuiltIns getBuiltIns() {
        return this.module.getBuiltIns();
    }

    public Void getDeclarationDescriptor() [...] // Inlined contents

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
        return this.supertypes;
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
        return "IntegerValueType(" + this.value + ')';
    }
}

