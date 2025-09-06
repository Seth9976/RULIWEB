package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;

public class DeclarationDescriptorVisitorEmptyBodies implements DeclarationDescriptorVisitor {
    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitClassDescriptor(ClassDescriptor classDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor0, Object object0) {
        return this.visitFunctionDescriptor(constructorDescriptor0, object0);
    }

    public Object visitDeclarationDescriptor(DeclarationDescriptor declarationDescriptor0, Object object0) [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitModuleDeclaration(ModuleDescriptor moduleDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPropertyDescriptor(PropertyDescriptor propertyDescriptor0, Object object0) {
        return this.visitVariableDescriptor(propertyDescriptor0, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor0, Object object0) {
        return this.visitFunctionDescriptor(propertyGetterDescriptor0, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor0, Object object0) {
        return this.visitFunctionDescriptor(propertySetterDescriptor0, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor0, Object object0) {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
    public Object visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor0, Object object0) {
        return this.visitVariableDescriptor(valueParameterDescriptor0, object0);
    }

    public Object visitVariableDescriptor(VariableDescriptor variableDescriptor0, Object object0) {
        return null;
    }
}

