package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionInterfacePackageFragment;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt;

public final class BuiltInFictitiousFunctionClassFactory implements ClassDescriptorFactory {
    private final ModuleDescriptor module;
    private final StorageManager storageManager;

    public BuiltInFictitiousFunctionClassFactory(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        super();
        this.storageManager = storageManager0;
        this.module = moduleDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public ClassDescriptor createClass(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        if(!classId0.isLocal() && !classId0.isNestedClass()) {
            String s = classId0.getRelativeClassName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "classId.relativeClassName.asString()");
            if(!StringsKt.contains$default(s, "Function", false, 2, null)) {
                return null;
            }
            FqName fqName0 = classId0.getPackageFqName();
            Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
            KindWithArity functionClassKind$Companion$KindWithArity0 = FunctionClassKind.Companion.parseClassName(s, fqName0);
            if(functionClassKind$Companion$KindWithArity0 == null) {
                return null;
            }
            FunctionClassKind functionClassKind0 = functionClassKind$Companion$KindWithArity0.component1();
            int v = functionClassKind$Companion$KindWithArity0.component2();
            Iterable iterable0 = this.module.getPackage(fqName0).getFragments();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                if(object0 instanceof BuiltInsPackageFragment) {
                    collection0.add(object0);
                }
            }
            Collection collection1 = new ArrayList();
            for(Object object1: ((List)collection0)) {
                if(object1 instanceof FunctionInterfacePackageFragment) {
                    collection1.add(object1);
                }
            }
            FunctionInterfacePackageFragment functionInterfacePackageFragment0 = (FunctionInterfacePackageFragment)CollectionsKt.firstOrNull(((List)collection1));
            if(functionInterfacePackageFragment0 == null) {
                functionInterfacePackageFragment0 = CollectionsKt.first(((List)collection0));
            }
            return new FunctionClassDescriptor(this.storageManager, functionInterfacePackageFragment0, functionClassKind0, v);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public Collection getAllContributedClassesIfPossible(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public boolean shouldCreateClass(FqName fqName0, Name name0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        Intrinsics.checkNotNullParameter(name0, "name");
        String s = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        return (StringsKt.startsWith$default(s, "Function", false, 2, null) || StringsKt.startsWith$default(s, "KFunction", false, 2, null) || StringsKt.startsWith$default(s, "SuspendFunction", false, 2, null) || StringsKt.startsWith$default(s, "KSuspendFunction", false, 2, null)) && FunctionClassKind.Companion.parseClassName(s, fqName0) != null;
    }
}

