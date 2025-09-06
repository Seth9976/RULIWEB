package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public class ValueParameterDescriptorImpl extends VariableDescriptorImpl implements ValueParameterDescriptor {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final ValueParameterDescriptorImpl createWithDestructuringDeclarations(CallableDescriptor callableDescriptor0, ValueParameterDescriptor valueParameterDescriptor0, int v, Annotations annotations0, Name name0, KotlinType kotlinType0, boolean z, boolean z1, boolean z2, KotlinType kotlinType1, SourceElement sourceElement0, Function0 function00) {
            Intrinsics.checkNotNullParameter(callableDescriptor0, "containingDeclaration");
            Intrinsics.checkNotNullParameter(annotations0, "annotations");
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(kotlinType0, "outType");
            Intrinsics.checkNotNullParameter(sourceElement0, "source");
            return function00 == null ? new ValueParameterDescriptorImpl(callableDescriptor0, valueParameterDescriptor0, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, sourceElement0) : new WithDestructuringDeclaration(callableDescriptor0, valueParameterDescriptor0, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, sourceElement0, function00);
        }
    }

    public static final class WithDestructuringDeclaration extends ValueParameterDescriptorImpl {
        private final Lazy destructuringVariables$delegate;

        public WithDestructuringDeclaration(CallableDescriptor callableDescriptor0, ValueParameterDescriptor valueParameterDescriptor0, int v, Annotations annotations0, Name name0, KotlinType kotlinType0, boolean z, boolean z1, boolean z2, KotlinType kotlinType1, SourceElement sourceElement0, Function0 function00) {
            Intrinsics.checkNotNullParameter(callableDescriptor0, "containingDeclaration");
            Intrinsics.checkNotNullParameter(annotations0, "annotations");
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(kotlinType0, "outType");
            Intrinsics.checkNotNullParameter(sourceElement0, "source");
            Intrinsics.checkNotNullParameter(function00, "destructuringVariables");
            super(callableDescriptor0, valueParameterDescriptor0, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, sourceElement0);
            this.destructuringVariables$delegate = LazyKt.lazy(function00);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl
        public ValueParameterDescriptor copy(CallableDescriptor callableDescriptor0, Name name0, int v) {
            Intrinsics.checkNotNullParameter(callableDescriptor0, "newOwner");
            Intrinsics.checkNotNullParameter(name0, "newName");
            Annotations annotations0 = this.getAnnotations();
            Intrinsics.checkNotNullExpressionValue(annotations0, "annotations");
            KotlinType kotlinType0 = this.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "type");
            boolean z = this.declaresDefaultValue();
            boolean z1 = this.isCrossinline();
            boolean z2 = this.isNoinline();
            KotlinType kotlinType1 = this.getVarargElementType();
            Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
            kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl.WithDestructuringDeclaration.copy.1 valueParameterDescriptorImpl$WithDestructuringDeclaration$copy$10 = new Function0() {
                {
                    WithDestructuringDeclaration.this = valueParameterDescriptorImpl$WithDestructuringDeclaration0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return WithDestructuringDeclaration.this.getDestructuringVariables();
                }
            };
            return new WithDestructuringDeclaration(callableDescriptor0, null, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, SourceElement.NO_SOURCE, valueParameterDescriptorImpl$WithDestructuringDeclaration$copy$10);
        }

        public final List getDestructuringVariables() {
            return (List)this.destructuringVariables$delegate.getValue();
        }
    }

    public static final Companion Companion;
    private final boolean declaresDefaultValue;
    private final int index;
    private final boolean isCrossinline;
    private final boolean isNoinline;
    private final ValueParameterDescriptor original;
    private final KotlinType varargElementType;

    static {
        ValueParameterDescriptorImpl.Companion = new Companion(null);
    }

    public ValueParameterDescriptorImpl(CallableDescriptor callableDescriptor0, ValueParameterDescriptor valueParameterDescriptor0, int v, Annotations annotations0, Name name0, KotlinType kotlinType0, boolean z, boolean z1, boolean z2, KotlinType kotlinType1, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(kotlinType0, "outType");
        Intrinsics.checkNotNullParameter(sourceElement0, "source");
        super(callableDescriptor0, annotations0, name0, kotlinType0, sourceElement0);
        this.index = v;
        this.declaresDefaultValue = z;
        this.isCrossinline = z1;
        this.isNoinline = z2;
        this.varargElementType = kotlinType1;
        ValueParameterDescriptor valueParameterDescriptor1 = valueParameterDescriptor0 == null ? this : valueParameterDescriptor0;
        this.original = valueParameterDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
        return declarationDescriptorVisitor0.visitValueParameterDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public ValueParameterDescriptor copy(CallableDescriptor callableDescriptor0, Name name0, int v) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "newOwner");
        Intrinsics.checkNotNullParameter(name0, "newName");
        Annotations annotations0 = this.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations0, "annotations");
        KotlinType kotlinType0 = this.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "type");
        boolean z = this.declaresDefaultValue();
        Intrinsics.checkNotNullExpressionValue(SourceElement.NO_SOURCE, "NO_SOURCE");
        return new ValueParameterDescriptorImpl(callableDescriptor0, null, v, annotations0, name0, kotlinType0, z, this.isCrossinline(), this.isNoinline(), this.getVarargElementType(), SourceElement.NO_SOURCE);
    }

    @JvmStatic
    public static final ValueParameterDescriptorImpl createWithDestructuringDeclarations(CallableDescriptor callableDescriptor0, ValueParameterDescriptor valueParameterDescriptor0, int v, Annotations annotations0, Name name0, KotlinType kotlinType0, boolean z, boolean z1, boolean z2, KotlinType kotlinType1, SourceElement sourceElement0, Function0 function00) {
        return ValueParameterDescriptorImpl.Companion.createWithDestructuringDeclarations(callableDescriptor0, valueParameterDescriptor0, v, annotations0, name0, kotlinType0, z, z1, z2, kotlinType1, sourceElement0, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean declaresDefaultValue() {
        if(this.declaresDefaultValue) {
            CallableDescriptor callableDescriptor0 = this.getContainingDeclaration();
            Intrinsics.checkNotNull(callableDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableMemberDescriptor");
            return ((CallableMemberDescriptor)callableDescriptor0).getKind().isReal();
        }
        return false;
    }

    public Void getCompileTimeInitializer() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public ConstantValue getCompileTimeInitializer() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public CallableDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = super.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        return (CallableDescriptor)declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.ValueDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public int getIndex() {
        return this.index;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public ValueParameterDescriptor getOriginal() {
        ValueParameterDescriptor valueParameterDescriptor0 = this.original;
        return valueParameterDescriptor0 == this ? this : valueParameterDescriptor0.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl
    public VariableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public Collection getOverriddenDescriptors() {
        Collection collection0 = this.getContainingDeclaration().getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(collection0, "containingDeclaration.overriddenDescriptors");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
        for(Object object0: collection0) {
            arrayList0.add(((ValueParameterDescriptor)((CallableDescriptor)object0).getValueParameters().get(this.getIndex())));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public KotlinType getVarargElementType() {
        return this.varargElementType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.LOCAL, "LOCAL");
        return DescriptorVisibilities.LOCAL;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean isCrossinline() {
        return this.isCrossinline;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isLateInit() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor
    public boolean isNoinline() {
        return this.isNoinline;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public boolean isVar() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.VariableDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    public ValueParameterDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        Intrinsics.checkNotNullParameter(typeSubstitutor0, "substitutor");
        if(!typeSubstitutor0.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor
    public VariableDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }
}

