package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J!\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0003H\u0016¢\u0006\u0002\u0010\u000BJ!\u0010\f\u001A\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\b\u001A\u00020\r2\u0006\u0010\n\u001A\u00020\u0003H\u0016¢\u0006\u0002\u0010\u000ER\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lkotlin/reflect/jvm/internal/CreateKCallableVisitor;", "Lkotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorVisitorEmptyBodies;", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "visitFunctionDescriptor", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "data", "(Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Lkotlin/Unit;)Lkotlin/reflect/jvm/internal/KCallableImpl;", "visitPropertyDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lkotlin/Unit;)Lkotlin/reflect/jvm/internal/KCallableImpl;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class CreateKCallableVisitor extends DeclarationDescriptorVisitorEmptyBodies {
    private final KDeclarationContainerImpl container;

    public CreateKCallableVisitor(KDeclarationContainerImpl kDeclarationContainerImpl0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        super();
        this.container = kDeclarationContainerImpl0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
    public Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor0, Object object0) {
        return this.visitFunctionDescriptor(functionDescriptor0, ((Unit)object0));
    }

    public KCallableImpl visitFunctionDescriptor(FunctionDescriptor functionDescriptor0, Unit unit0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(unit0, "data");
        return new KFunctionImpl(this.container, functionDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
    public Object visitPropertyDescriptor(PropertyDescriptor propertyDescriptor0, Object object0) {
        return this.visitPropertyDescriptor(propertyDescriptor0, ((Unit)object0));
    }

    public KCallableImpl visitPropertyDescriptor(PropertyDescriptor propertyDescriptor0, Unit unit0) {
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(unit0, "data");
        int v = 0;
        int v1 = propertyDescriptor0.getDispatchReceiverParameter() == null ? 0 : 1;
        if(propertyDescriptor0.getExtensionReceiverParameter() != null) {
            v = 1;
        }
        int v2 = v1 + v;
        if(propertyDescriptor0.isVar()) {
            switch(v2) {
                case 0: {
                    return new KMutableProperty0Impl(this.container, propertyDescriptor0);
                }
                case 1: {
                    return new KMutableProperty1Impl(this.container, propertyDescriptor0);
                }
                case 2: {
                    return new KMutableProperty2Impl(this.container, propertyDescriptor0);
                }
            }
        }
        else {
            switch(v2) {
                case 0: {
                    return new KProperty0Impl(this.container, propertyDescriptor0);
                }
                case 1: {
                    return new KProperty1Impl(this.container, propertyDescriptor0);
                }
                case 2: {
                    return new KProperty2Impl(this.container, propertyDescriptor0);
                }
            }
        }
        throw new KotlinReflectionInternalError("Unsupported property: " + propertyDescriptor0);
    }
}

