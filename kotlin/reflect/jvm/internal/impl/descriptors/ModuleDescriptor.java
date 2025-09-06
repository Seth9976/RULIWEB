package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public interface ModuleDescriptor extends DeclarationDescriptor {
    public static final class DefaultImpls {
        public static Object accept(ModuleDescriptor moduleDescriptor0, DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
            Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
            return declarationDescriptorVisitor0.visitModuleDeclaration(moduleDescriptor0, object0);
        }

        public static DeclarationDescriptor getContainingDeclaration(ModuleDescriptor moduleDescriptor0) [...] // Inlined contents
    }

    KotlinBuiltIns getBuiltIns();

    Object getCapability(ModuleCapability arg1);

    List getExpectedByModules();

    PackageViewDescriptor getPackage(FqName arg1);

    Collection getSubPackagesOf(FqName arg1, Function1 arg2);

    boolean shouldSeeInternalsOf(ModuleDescriptor arg1);
}

