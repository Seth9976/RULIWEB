package kotlin.reflect.jvm.internal.impl.types.error;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.DefaultBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class ErrorModuleDescriptor implements ModuleDescriptor {
    public static final ErrorModuleDescriptor INSTANCE;
    private static final List allDependencyModules;
    private static final Set allExpectedByModules;
    private static final KotlinBuiltIns builtIns;
    private static final List expectedByModules;
    private static final Name stableName;

    static {
        ErrorModuleDescriptor.INSTANCE = new ErrorModuleDescriptor();
        Name name0 = Name.special("<Error module>");
        Intrinsics.checkNotNullExpressionValue(name0, "special(ErrorEntity.ERROR_MODULE.debugText)");
        ErrorModuleDescriptor.stableName = name0;
        ErrorModuleDescriptor.allDependencyModules = CollectionsKt.emptyList();
        ErrorModuleDescriptor.expectedByModules = CollectionsKt.emptyList();
        ErrorModuleDescriptor.allExpectedByModules = SetsKt.emptySet();
        ErrorModuleDescriptor.builtIns = DefaultBuiltIns.Companion.getInstance();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public KotlinBuiltIns getBuiltIns() {
        return ErrorModuleDescriptor.builtIns;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public Object getCapability(ModuleCapability moduleCapability0) {
        Intrinsics.checkNotNullParameter(moduleCapability0, "capability");
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public List getExpectedByModules() {
        return ErrorModuleDescriptor.expectedByModules;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        return this.getStableName();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public PackageViewDescriptor getPackage(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        throw new IllegalStateException("Should not be called!");
    }

    public Name getStableName() {
        return ErrorModuleDescriptor.stableName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "targetModule");
        return false;
    }
}

