package kotlin.reflect.jvm.internal.impl.descriptors;

public interface DeclarationDescriptorVisitor {
    Object visitClassDescriptor(ClassDescriptor arg1, Object arg2);

    Object visitConstructorDescriptor(ConstructorDescriptor arg1, Object arg2);

    Object visitFunctionDescriptor(FunctionDescriptor arg1, Object arg2);

    Object visitModuleDeclaration(ModuleDescriptor arg1, Object arg2);

    Object visitPackageFragmentDescriptor(PackageFragmentDescriptor arg1, Object arg2);

    Object visitPackageViewDescriptor(PackageViewDescriptor arg1, Object arg2);

    Object visitPropertyDescriptor(PropertyDescriptor arg1, Object arg2);

    Object visitPropertyGetterDescriptor(PropertyGetterDescriptor arg1, Object arg2);

    Object visitPropertySetterDescriptor(PropertySetterDescriptor arg1, Object arg2);

    Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor arg1, Object arg2);

    Object visitTypeAliasDescriptor(TypeAliasDescriptor arg1, Object arg2);

    Object visitTypeParameterDescriptor(TypeParameterDescriptor arg1, Object arg2);

    Object visitValueParameterDescriptor(ValueParameterDescriptor arg1, Object arg2);
}

