package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

public abstract class KotlinTypeRefiner extends AbstractTypeRefiner {
    public static final class Default extends KotlinTypeRefiner {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public ClassDescriptor findClassAcrossModuleDependencies(ClassId classId0) {
            Intrinsics.checkNotNullParameter(classId0, "classId");
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public MemberScope getOrPutScopeForClass(ClassDescriptor classDescriptor0, Function0 function00) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            Intrinsics.checkNotNullParameter(function00, "compute");
            return (MemberScope)function00.invoke();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public boolean isRefinementNeededForModule(ModuleDescriptor moduleDescriptor0) {
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
            return false;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public boolean isRefinementNeededForTypeConstructor(TypeConstructor typeConstructor0) {
            Intrinsics.checkNotNullParameter(typeConstructor0, "typeConstructor");
            return false;
        }

        public ClassDescriptor refineDescriptor(DeclarationDescriptor declarationDescriptor0) {
            Intrinsics.checkNotNullParameter(declarationDescriptor0, "descriptor");
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public ClassifierDescriptor refineDescriptor(DeclarationDescriptor declarationDescriptor0) {
            return this.refineDescriptor(declarationDescriptor0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public Collection refineSupertypes(ClassDescriptor classDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            Collection collection0 = classDescriptor0.getTypeConstructor().getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collection0, "classDescriptor.typeConstructor.supertypes");
            return collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner
        public KotlinType refineType(KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "type");
            return (KotlinType)kotlinTypeMarker0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeRefiner
        public KotlinTypeMarker refineType(KotlinTypeMarker kotlinTypeMarker0) {
            return this.refineType(kotlinTypeMarker0);
        }
    }

    public abstract ClassDescriptor findClassAcrossModuleDependencies(ClassId arg1);

    public abstract MemberScope getOrPutScopeForClass(ClassDescriptor arg1, Function0 arg2);

    public abstract boolean isRefinementNeededForModule(ModuleDescriptor arg1);

    public abstract boolean isRefinementNeededForTypeConstructor(TypeConstructor arg1);

    public abstract ClassifierDescriptor refineDescriptor(DeclarationDescriptor arg1);

    public abstract Collection refineSupertypes(ClassDescriptor arg1);

    public abstract KotlinType refineType(KotlinTypeMarker arg1);
}

