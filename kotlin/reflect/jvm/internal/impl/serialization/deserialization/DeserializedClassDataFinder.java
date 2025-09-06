package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class DeserializedClassDataFinder implements ClassDataFinder {
    private final PackageFragmentProvider packageFragmentProvider;

    public DeserializedClassDataFinder(PackageFragmentProvider packageFragmentProvider0) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "packageFragmentProvider");
        super();
        this.packageFragmentProvider = packageFragmentProvider0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDataFinder
    public ClassData findClassData(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        FqName fqName0 = classId0.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
        for(Object object0: PackageFragmentProviderKt.packageFragments(this.packageFragmentProvider, fqName0)) {
            PackageFragmentDescriptor packageFragmentDescriptor0 = (PackageFragmentDescriptor)object0;
            if(packageFragmentDescriptor0 instanceof DeserializedPackageFragment) {
                ClassData classData0 = ((DeserializedPackageFragment)packageFragmentDescriptor0).getClassDataFinder().findClassData(classId0);
                if(classData0 != null) {
                    return classData0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }
}

