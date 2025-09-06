package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public abstract class VariableDescriptorWithInitializerImpl extends VariableDescriptorImpl {
    static final boolean $assertionsDisabled;
    protected NullableLazyValue compileTimeInitializer;
    protected Function0 compileTimeInitializerFactory;
    private final boolean isVar;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        switch(v) {
            case 1: {
                arr_object[0] = "annotations";
                break;
            }
            case 2: {
                arr_object[0] = "name";
                break;
            }
            case 3: {
                arr_object[0] = "source";
                break;
            }
            case 4: 
            case 5: {
                arr_object[0] = "compileTimeInitializerFactory";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/VariableDescriptorWithInitializerImpl";
        switch(v) {
            case 4: {
                arr_object[2] = "setCompileTimeInitializerFactory";
                break;
            }
            case 5: {
                arr_object[2] = "setCompileTimeInitializer";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    static {
    }

    public VariableDescriptorWithInitializerImpl(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, KotlinType kotlinType0, boolean z, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(3);
        }
        super(declarationDescriptor0, annotations0, name0, kotlinType0, sourceElement0);
        this.isVar = z;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public ConstantValue getCompileTimeInitializer() {
        return this.compileTimeInitializer == null ? null : ((ConstantValue)this.compileTimeInitializer.invoke());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isVar() {
        return this.isVar;
    }

    public void setCompileTimeInitializer(NullableLazyValue nullableLazyValue0, Function0 function00) {
        if(function00 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(5);
        }
        this.compileTimeInitializerFactory = function00;
        if(nullableLazyValue0 == null) {
            nullableLazyValue0 = (NullableLazyValue)function00.invoke();
        }
        this.compileTimeInitializer = nullableLazyValue0;
    }

    public void setCompileTimeInitializerFactory(Function0 function00) {
        if(function00 == null) {
            VariableDescriptorWithInitializerImpl.$$$reportNull$$$0(4);
        }
        this.setCompileTimeInitializer(null, function00);
    }
}

