package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PossiblyInnerType;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value.LocalClass;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value.NormalClass;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue.Value;
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbbreviatedType;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SpecialTypesKt;
import kotlin.reflect.jvm.internal.impl.types.StubTypeForBuilderInference;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.WrappedType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

public final class DescriptorRendererImpl extends DescriptorRenderer implements DescriptorRendererOptions {
    final class RenderDeclarationDescriptorVisitor implements DeclarationDescriptorVisitor {
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[PropertyAccessorRenderingPolicy.values().length];
                try {
                    arr_v[PropertyAccessorRenderingPolicy.PRETTY.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[PropertyAccessorRenderingPolicy.DEBUG.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[PropertyAccessorRenderingPolicy.NONE.ordinal()] = 3;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitClassDescriptor(ClassDescriptor classDescriptor0, Object object0) {
            this.visitClassDescriptor(classDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitClassDescriptor(ClassDescriptor classDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderClass(classDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor0, Object object0) {
            this.visitConstructorDescriptor(constructorDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(constructorDescriptor0, "constructorDescriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderConstructor(constructorDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitFunctionDescriptor(FunctionDescriptor functionDescriptor0, Object object0) {
            this.visitFunctionDescriptor(functionDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitFunctionDescriptor(FunctionDescriptor functionDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderFunction(functionDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitModuleDeclaration(ModuleDescriptor moduleDescriptor0, Object object0) {
            this.visitModuleDeclaration(moduleDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitModuleDeclaration(ModuleDescriptor moduleDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderName(moduleDescriptor0, stringBuilder0, true);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor0, Object object0) {
            this.visitPackageFragmentDescriptor(packageFragmentDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitPackageFragmentDescriptor(PackageFragmentDescriptor packageFragmentDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(packageFragmentDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderPackageFragment(packageFragmentDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor0, Object object0) {
            this.visitPackageViewDescriptor(packageViewDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitPackageViewDescriptor(PackageViewDescriptor packageViewDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(packageViewDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderPackageView(packageViewDescriptor0, stringBuilder0);
        }

        private final void visitPropertyAccessorDescriptor(PropertyAccessorDescriptor propertyAccessorDescriptor0, StringBuilder stringBuilder0, String s) {
            switch(WhenMappings.$EnumSwitchMapping$0[DescriptorRendererImpl.this.getPropertyAccessorRenderingPolicy().ordinal()]) {
                case 1: {
                    DescriptorRendererImpl.this.renderAccessorModifiers(propertyAccessorDescriptor0, stringBuilder0);
                    stringBuilder0.append(s + " for ");
                    PropertyDescriptor propertyDescriptor0 = propertyAccessorDescriptor0.getCorrespondingProperty();
                    Intrinsics.checkNotNullExpressionValue(propertyDescriptor0, "descriptor.correspondingProperty");
                    DescriptorRendererImpl.this.renderProperty(propertyDescriptor0, stringBuilder0);
                    return;
                }
                case 2: {
                    this.visitFunctionDescriptor(propertyAccessorDescriptor0, stringBuilder0);
                }
            }
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitPropertyDescriptor(PropertyDescriptor propertyDescriptor0, Object object0) {
            this.visitPropertyDescriptor(propertyDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitPropertyDescriptor(PropertyDescriptor propertyDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderProperty(propertyDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor0, Object object0) {
            this.visitPropertyGetterDescriptor(propertyGetterDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitPropertyGetterDescriptor(PropertyGetterDescriptor propertyGetterDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(propertyGetterDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            this.visitPropertyAccessorDescriptor(propertyGetterDescriptor0, stringBuilder0, "getter");
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor0, Object object0) {
            this.visitPropertySetterDescriptor(propertySetterDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitPropertySetterDescriptor(PropertySetterDescriptor propertySetterDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(propertySetterDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            this.visitPropertyAccessorDescriptor(propertySetterDescriptor0, stringBuilder0, "setter");
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor0, Object object0) {
            this.visitReceiverParameterDescriptor(receiverParameterDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitReceiverParameterDescriptor(ReceiverParameterDescriptor receiverParameterDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(receiverParameterDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            stringBuilder0.append(receiverParameterDescriptor0.getName());
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor0, Object object0) {
            this.visitTypeAliasDescriptor(typeAliasDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitTypeAliasDescriptor(TypeAliasDescriptor typeAliasDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(typeAliasDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderTypeAlias(typeAliasDescriptor0, stringBuilder0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor0, Object object0) {
            this.visitTypeParameterDescriptor(typeParameterDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitTypeParameterDescriptor(TypeParameterDescriptor typeParameterDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderTypeParameter(typeParameterDescriptor0, stringBuilder0, true);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
        public Object visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor0, Object object0) {
            this.visitValueParameterDescriptor(valueParameterDescriptor0, ((StringBuilder)object0));
            return Unit.INSTANCE;
        }

        public void visitValueParameterDescriptor(ValueParameterDescriptor valueParameterDescriptor0, StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "descriptor");
            Intrinsics.checkNotNullParameter(stringBuilder0, "builder");
            DescriptorRendererImpl.this.renderValueParameter(valueParameterDescriptor0, true, stringBuilder0, true);
        }
    }

    public final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] arr_v = new int[RenderingFormat.values().length];
            try {
                arr_v[RenderingFormat.PLAIN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[RenderingFormat.HTML.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[ParameterNameRenderingPolicy.values().length];
            try {
                arr_v1[ParameterNameRenderingPolicy.ALL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[ParameterNameRenderingPolicy.ONLY_NON_SYNTHESIZED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[ParameterNameRenderingPolicy.NONE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$1 = arr_v1;
        }
    }

    private final Lazy functionTypeAnnotationsRenderer$delegate;
    private final DescriptorRendererOptionsImpl options;

    public DescriptorRendererImpl(DescriptorRendererOptionsImpl descriptorRendererOptionsImpl0) {
        Intrinsics.checkNotNullParameter(descriptorRendererOptionsImpl0, "options");
        super();
        this.options = descriptorRendererOptionsImpl0;
        this.functionTypeAnnotationsRenderer$delegate = LazyKt.lazy(new Function0() {
            {
                DescriptorRendererImpl.this = descriptorRendererImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final DescriptorRendererImpl invoke() {
                DescriptorRenderer descriptorRenderer0 = DescriptorRendererImpl.this.withOptions(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1.INSTANCE);
                Intrinsics.checkNotNull(descriptorRenderer0, "null cannot be cast to non-null type org.jetbrains.kotlin.renderer.DescriptorRendererImpl");
                return (DescriptorRendererImpl)descriptorRenderer0;

                final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1 extends Lambda implements Function1 {
                    public static final kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1 INSTANCE;

                    static {
                        kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1();
                    }

                    kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.functionTypeAnnotationsRenderer.2.1() {
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((DescriptorRendererOptions)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(DescriptorRendererOptions descriptorRendererOptions0) {
                        Intrinsics.checkNotNullParameter(descriptorRendererOptions0, "$this$withOptions");
                        descriptorRendererOptions0.setExcludedTypeAnnotationClasses(SetsKt.plus(descriptorRendererOptions0.getExcludedTypeAnnotationClasses(), CollectionsKt.listOf(new FqName[]{FqNames.extensionFunctionType, FqNames.contextFunctionTypeParams})));
                    }
                }

            }
        });
    }

    private final void appendDefinedIn(StringBuilder stringBuilder0, DeclarationDescriptor declarationDescriptor0) {
        if(!(declarationDescriptor0 instanceof PackageFragmentDescriptor) && !(declarationDescriptor0 instanceof PackageViewDescriptor)) {
            DeclarationDescriptor declarationDescriptor1 = declarationDescriptor0.getContainingDeclaration();
            if(declarationDescriptor1 != null && !(declarationDescriptor1 instanceof ModuleDescriptor)) {
                stringBuilder0.append(" ");
                stringBuilder0.append(this.renderMessage("defined in"));
                stringBuilder0.append(" ");
                FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(declarationDescriptor1);
                Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "getFqName(containingDeclaration)");
                stringBuilder0.append((fqNameUnsafe0.isRoot() ? "root package" : this.renderFqName(fqNameUnsafe0)));
                if(this.getWithSourceFileForTopLevel() && declarationDescriptor1 instanceof PackageFragmentDescriptor && declarationDescriptor0 instanceof DeclarationDescriptorWithSource) {
                    String s = ((DeclarationDescriptorWithSource)declarationDescriptor0).getSource().getContainingFile().getName();
                    if(s != null) {
                        stringBuilder0.append(" ");
                        stringBuilder0.append(this.renderMessage("in file"));
                        stringBuilder0.append(" ");
                        stringBuilder0.append(s);
                    }
                }
            }
        }
    }

    private final void appendTypeProjections(StringBuilder stringBuilder0, List list0) {
        CollectionsKt.joinTo$default(list0, stringBuilder0, ", ", null, null, 0, null, new Function1() {
            {
                DescriptorRendererImpl.this = descriptorRendererImpl0;
                super(1);
            }

            public final CharSequence invoke(TypeProjection typeProjection0) {
                Intrinsics.checkNotNullParameter(typeProjection0, "it");
                if(typeProjection0.isStarProjection()) {
                    return "*";
                }
                KotlinType kotlinType0 = typeProjection0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
                String s = DescriptorRendererImpl.this.renderType(kotlinType0);
                return typeProjection0.getProjectionKind() == Variance.INVARIANT ? s : typeProjection0.getProjectionKind() + ' ' + s;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((TypeProjection)object0));
            }
        }, 60, null);
    }

    private final String arrow() {
        switch(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$0[this.getTextFormat().ordinal()]) {
            case 1: {
                return this.escape("->");
            }
            case 2: {
                return "&rarr;";
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final String escape(String s) {
        return this.getTextFormat().escape(s);
    }

    public boolean getActualPropertiesInPrimaryConstructor() {
        return this.options.getActualPropertiesInPrimaryConstructor();
    }

    public boolean getAlwaysRenderModifiers() {
        return this.options.getAlwaysRenderModifiers();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return this.options.getAnnotationArgumentsRenderingPolicy();
    }

    public Function1 getAnnotationFilter() {
        return this.options.getAnnotationFilter();
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return this.options.getBoldOnlyForNamesInHtml();
    }

    public boolean getClassWithPrimaryConstructor() {
        return this.options.getClassWithPrimaryConstructor();
    }

    public ClassifierNamePolicy getClassifierNamePolicy() {
        return this.options.getClassifierNamePolicy();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getDebugMode() {
        return this.options.getDebugMode();
    }

    public Function1 getDefaultParameterValueRenderer() {
        return this.options.getDefaultParameterValueRenderer();
    }

    public boolean getEachAnnotationOnNewLine() {
        return this.options.getEachAnnotationOnNewLine();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getEnhancedTypes() {
        return this.options.getEnhancedTypes();
    }

    public Set getExcludedAnnotationClasses() {
        return this.options.getExcludedAnnotationClasses();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public Set getExcludedTypeAnnotationClasses() {
        return this.options.getExcludedTypeAnnotationClasses();
    }

    private final DescriptorRendererImpl getFunctionTypeAnnotationsRenderer() {
        return (DescriptorRendererImpl)this.functionTypeAnnotationsRenderer$delegate.getValue();
    }

    public boolean getIncludeAdditionalModifiers() {
        return this.options.getIncludeAdditionalModifiers();
    }

    public boolean getIncludeAnnotationArguments() {
        return this.options.getIncludeAnnotationArguments();
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return this.options.getIncludeEmptyAnnotationArguments();
    }

    public boolean getIncludePropertyConstant() {
        return this.options.getIncludePropertyConstant();
    }

    public boolean getInformativeErrorType() {
        return this.options.getInformativeErrorType();
    }

    public Set getModifiers() {
        return this.options.getModifiers();
    }

    public boolean getNormalizedVisibilities() {
        return this.options.getNormalizedVisibilities();
    }

    public final DescriptorRendererOptionsImpl getOptions() {
        return this.options;
    }

    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return this.options.getOverrideRenderingPolicy();
    }

    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return this.options.getParameterNameRenderingPolicy();
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return this.options.getParameterNamesInFunctionalTypes();
    }

    public boolean getPresentableUnresolvedTypes() {
        return this.options.getPresentableUnresolvedTypes();
    }

    public PropertyAccessorRenderingPolicy getPropertyAccessorRenderingPolicy() {
        return this.options.getPropertyAccessorRenderingPolicy();
    }

    public boolean getReceiverAfterName() {
        return this.options.getReceiverAfterName();
    }

    public boolean getRenderCompanionObjectName() {
        return this.options.getRenderCompanionObjectName();
    }

    public boolean getRenderConstructorDelegation() {
        return this.options.getRenderConstructorDelegation();
    }

    public boolean getRenderConstructorKeyword() {
        return this.options.getRenderConstructorKeyword();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return this.options.getRenderDefaultAnnotationArguments();
    }

    public boolean getRenderDefaultModality() {
        return this.options.getRenderDefaultModality();
    }

    public boolean getRenderDefaultVisibility() {
        return this.options.getRenderDefaultVisibility();
    }

    public boolean getRenderPrimaryConstructorParametersAsProperties() {
        return this.options.getRenderPrimaryConstructorParametersAsProperties();
    }

    public boolean getRenderTypeExpansions() {
        return this.options.getRenderTypeExpansions();
    }

    public boolean getRenderUnabbreviatedType() {
        return this.options.getRenderUnabbreviatedType();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return this.options.getSecondaryConstructorsAsPrimary();
    }

    public boolean getStartFromDeclarationKeyword() {
        return this.options.getStartFromDeclarationKeyword();
    }

    public boolean getStartFromName() {
        return this.options.getStartFromName();
    }

    public RenderingFormat getTextFormat() {
        return this.options.getTextFormat();
    }

    public Function1 getTypeNormalizer() {
        return this.options.getTypeNormalizer();
    }

    public boolean getUninferredTypeParameterAsName() {
        return this.options.getUninferredTypeParameterAsName();
    }

    public boolean getUnitReturnType() {
        return this.options.getUnitReturnType();
    }

    public ValueParametersHandler getValueParametersHandler() {
        return this.options.getValueParametersHandler();
    }

    public boolean getVerbose() {
        return this.options.getVerbose();
    }

    public boolean getWithDefinedIn() {
        return this.options.getWithDefinedIn();
    }

    public boolean getWithSourceFileForTopLevel() {
        return this.options.getWithSourceFileForTopLevel();
    }

    public boolean getWithoutReturnType() {
        return this.options.getWithoutReturnType();
    }

    public boolean getWithoutSuperTypes() {
        return this.options.getWithoutSuperTypes();
    }

    public boolean getWithoutTypeParameters() {
        return this.options.getWithoutTypeParameters();
    }

    private final String gt() {
        return this.escape(">");
    }

    // 去混淆评级： 低(20)
    private final boolean hasModifiersOrAnnotations(KotlinType kotlinType0) {
        return FunctionTypesKt.isSuspendFunctionType(kotlinType0) || !kotlinType0.getAnnotations().isEmpty();
    }

    private final Modality implicitModalityWithoutExtensions(MemberDescriptor memberDescriptor0) {
        if(memberDescriptor0 instanceof ClassDescriptor) {
            return ((ClassDescriptor)memberDescriptor0).getKind() == ClassKind.INTERFACE ? Modality.ABSTRACT : Modality.FINAL;
        }
        DeclarationDescriptor declarationDescriptor0 = memberDescriptor0.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        if(classDescriptor0 == null) {
            return Modality.FINAL;
        }
        if(!(memberDescriptor0 instanceof CallableMemberDescriptor)) {
            return Modality.FINAL;
        }
        Collection collection0 = ((CallableMemberDescriptor)memberDescriptor0).getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(collection0, "this.overriddenDescriptors");
        if(!collection0.isEmpty() && classDescriptor0.getModality() != Modality.FINAL) {
            return Modality.OPEN;
        }
        if(classDescriptor0.getKind() == ClassKind.INTERFACE && !Intrinsics.areEqual(((CallableMemberDescriptor)memberDescriptor0).getVisibility(), DescriptorVisibilities.PRIVATE)) {
            return ((CallableMemberDescriptor)memberDescriptor0).getModality() == Modality.ABSTRACT ? Modality.ABSTRACT : Modality.OPEN;
        }
        return Modality.FINAL;
    }

    private final boolean isParameterName(AnnotationDescriptor annotationDescriptor0) {
        return Intrinsics.areEqual(annotationDescriptor0.getFqName(), FqNames.parameterName);
    }

    private final String lt() {
        return this.escape("<");
    }

    private final boolean overridesSomething(CallableMemberDescriptor callableMemberDescriptor0) {
        return !callableMemberDescriptor0.getOverriddenDescriptors().isEmpty();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String render(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "declarationDescriptor");
        StringBuilder stringBuilder0 = new StringBuilder();
        declarationDescriptor0.accept(new RenderDeclarationDescriptorVisitor(this), stringBuilder0);
        if(this.getWithDefinedIn()) {
            this.appendDefinedIn(stringBuilder0, declarationDescriptor0);
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    private final void renderAbbreviatedTypeExpansion(StringBuilder stringBuilder0, AbbreviatedType abbreviatedType0) {
        if(this.getTextFormat() == RenderingFormat.HTML) {
            stringBuilder0.append("<font color=\"808080\"><i>");
        }
        stringBuilder0.append(" /* = ");
        this.renderNormalizedTypeAsIs(stringBuilder0, abbreviatedType0.getExpandedType());
        stringBuilder0.append(" */");
        if(this.getTextFormat() == RenderingFormat.HTML) {
            stringBuilder0.append("</i></font>");
        }
    }

    private final void renderAccessorModifiers(PropertyAccessorDescriptor propertyAccessorDescriptor0, StringBuilder stringBuilder0) {
        this.renderMemberModifiers(propertyAccessorDescriptor0, stringBuilder0);
    }

    private final void renderAdditionalModifiers(FunctionDescriptor functionDescriptor0, StringBuilder stringBuilder0) {
        boolean z1;
        boolean z = true;
        if(functionDescriptor0.isOperator()) {
            z1 = true;
            Collection collection0 = functionDescriptor0.getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(collection0, "functionDescriptor.overriddenDescriptors");
            if(!collection0.isEmpty()) {
                for(Object object0: collection0) {
                    if(((FunctionDescriptor)object0).isOperator()) {
                        if(this.getAlwaysRenderModifiers()) {
                            break;
                        }
                        z1 = false;
                        break;
                    }
                }
            }
        }
        else {
            z1 = false;
        }
        if(functionDescriptor0.isInfix()) {
            Collection collection1 = functionDescriptor0.getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(collection1, "functionDescriptor.overriddenDescriptors");
            if(!collection1.isEmpty()) {
                for(Object object1: collection1) {
                    if(((FunctionDescriptor)object1).isInfix()) {
                        if(this.getAlwaysRenderModifiers()) {
                            break;
                        }
                        z = false;
                        break;
                    }
                }
            }
        }
        else {
            z = false;
        }
        this.renderModifier(stringBuilder0, functionDescriptor0.isTailrec(), "tailrec");
        this.renderSuspendModifier(functionDescriptor0, stringBuilder0);
        this.renderModifier(stringBuilder0, functionDescriptor0.isInline(), "inline");
        this.renderModifier(stringBuilder0, z, "infix");
        this.renderModifier(stringBuilder0, z1, "operator");
    }

    private final List renderAndSortAnnotationArguments(AnnotationDescriptor annotationDescriptor0) {
        Map map0 = annotationDescriptor0.getAllValueArguments();
        List list0 = null;
        ClassDescriptor classDescriptor0 = this.getRenderDefaultAnnotationArguments() ? DescriptorUtilsKt.getAnnotationClass(annotationDescriptor0) : null;
        if(classDescriptor0 != null) {
            ClassConstructorDescriptor classConstructorDescriptor0 = classDescriptor0.getUnsubstitutedPrimaryConstructor();
            if(classConstructorDescriptor0 != null) {
                List list1 = classConstructorDescriptor0.getValueParameters();
                if(list1 != null) {
                    Collection collection0 = new ArrayList();
                    for(Object object0: list1) {
                        if(((ValueParameterDescriptor)object0).declaresDefaultValue()) {
                            collection0.add(object0);
                        }
                    }
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
                    for(Object object1: ((List)collection0)) {
                        arrayList0.add(((ValueParameterDescriptor)object1).getName());
                    }
                    list0 = arrayList0;
                }
            }
        }
        if(list0 == null) {
            list0 = CollectionsKt.emptyList();
        }
        Collection collection1 = new ArrayList();
        for(Object object2: list0) {
            Intrinsics.checkNotNullExpressionValue(((Name)object2), "it");
            if(!map0.containsKey(((Name)object2))) {
                collection1.add(object2);
            }
        }
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection1), 10));
        for(Object object3: ((List)collection1)) {
            arrayList1.add(((Name)object3).asString() + " = ...");
        }
        Iterable iterable0 = map0.entrySet();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object4: iterable0) {
            Name name0 = (Name)((Map.Entry)object4).getKey();
            ConstantValue constantValue0 = (ConstantValue)((Map.Entry)object4).getValue();
            arrayList2.add(name0.asString() + " = " + (list0.contains(name0) ? "..." : this.renderConstant(constantValue0)));
        }
        return CollectionsKt.sorted(CollectionsKt.plus(arrayList1, arrayList2));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderAnnotation(AnnotationDescriptor annotationDescriptor0, AnnotationUseSiteTarget annotationUseSiteTarget0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "annotation");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append('@');
        if(annotationUseSiteTarget0 != null) {
            stringBuilder0.append(annotationUseSiteTarget0.getRenderName() + ':');
        }
        KotlinType kotlinType0 = annotationDescriptor0.getType();
        stringBuilder0.append(this.renderType(kotlinType0));
        if(this.getIncludeAnnotationArguments()) {
            List list0 = this.renderAndSortAnnotationArguments(annotationDescriptor0);
            if(this.getIncludeEmptyAnnotationArguments() || !list0.isEmpty()) {
                CollectionsKt.joinTo$default(list0, stringBuilder0, ", ", "(", ")", 0, null, null, 0x70, null);
            }
        }
        if(this.getVerbose() && (KotlinTypeKt.isError(kotlinType0) || kotlinType0.getConstructor().getDeclarationDescriptor() instanceof MockClassDescriptor)) {
            stringBuilder0.append(" /* annotation class not found */");
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    private final void renderAnnotations(StringBuilder stringBuilder0, Annotated annotated0, AnnotationUseSiteTarget annotationUseSiteTarget0) {
        if(this.getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            Set set0 = annotated0 instanceof KotlinType ? this.getExcludedTypeAnnotationClasses() : this.getExcludedAnnotationClasses();
            Function1 function10 = this.getAnnotationFilter();
            for(Object object0: annotated0.getAnnotations()) {
                AnnotationDescriptor annotationDescriptor0 = (AnnotationDescriptor)object0;
                if(!CollectionsKt.contains(set0, annotationDescriptor0.getFqName()) && !this.isParameterName(annotationDescriptor0) && (function10 == null || ((Boolean)function10.invoke(annotationDescriptor0)).booleanValue())) {
                    stringBuilder0.append(this.renderAnnotation(annotationDescriptor0, annotationUseSiteTarget0));
                    if(this.getEachAnnotationOnNewLine()) {
                        stringBuilder0.append('\n');
                        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
                    }
                    else {
                        stringBuilder0.append(" ");
                    }
                }
            }
        }
    }

    static void renderAnnotations$default(DescriptorRendererImpl descriptorRendererImpl0, StringBuilder stringBuilder0, Annotated annotated0, AnnotationUseSiteTarget annotationUseSiteTarget0, int v, Object object0) {
        if((v & 2) != 0) {
            annotationUseSiteTarget0 = null;
        }
        descriptorRendererImpl0.renderAnnotations(stringBuilder0, annotated0, annotationUseSiteTarget0);
    }

    private final void renderCapturedTypeParametersIfRequired(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0, StringBuilder stringBuilder0) {
        List list0 = classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "classifier.declaredTypeParameters");
        List list1 = classifierDescriptorWithTypeParameters0.getTypeConstructor().getParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "classifier.typeConstructor.parameters");
        if(this.getVerbose() && classifierDescriptorWithTypeParameters0.isInner() && list1.size() > list0.size()) {
            stringBuilder0.append(" /*captured type parameters: ");
            this.renderTypeParameterList(stringBuilder0, list1.subList(list0.size(), list1.size()));
            stringBuilder0.append("*/");
        }
    }

    private final void renderClass(ClassDescriptor classDescriptor0, StringBuilder stringBuilder0) {
        boolean z = classDescriptor0.getKind() == ClassKind.ENUM_ENTRY;
        if(!this.getStartFromName()) {
            DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, classDescriptor0, null, 2, null);
            List list0 = classDescriptor0.getContextReceivers();
            Intrinsics.checkNotNullExpressionValue(list0, "klass.contextReceivers");
            this.renderContextReceivers(list0, stringBuilder0);
            if(!z) {
                DescriptorVisibility descriptorVisibility0 = classDescriptor0.getVisibility();
                Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "klass.visibility");
                this.renderVisibility(descriptorVisibility0, stringBuilder0);
            }
            if((classDescriptor0.getKind() != ClassKind.INTERFACE || classDescriptor0.getModality() != Modality.ABSTRACT) && (!classDescriptor0.getKind().isSingleton() || classDescriptor0.getModality() != Modality.FINAL)) {
                Modality modality0 = classDescriptor0.getModality();
                Intrinsics.checkNotNullExpressionValue(modality0, "klass.modality");
                this.renderModality(modality0, stringBuilder0, this.implicitModalityWithoutExtensions(classDescriptor0));
            }
            this.renderMemberModifiers(classDescriptor0, stringBuilder0);
            this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.INNER) && classDescriptor0.isInner(), "inner");
            this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.DATA) && classDescriptor0.isData(), "data");
            this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.INLINE) && classDescriptor0.isInline(), "inline");
            this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.VALUE) && classDescriptor0.isValue(), "value");
            this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.FUN) && classDescriptor0.isFun(), "fun");
            this.renderClassKindPrefix(classDescriptor0, stringBuilder0);
        }
        if(DescriptorUtils.isCompanionObject(classDescriptor0)) {
            this.renderCompanionObjectName(classDescriptor0, stringBuilder0);
        }
        else {
            if(!this.getStartFromName()) {
                this.renderSpaceIfNeeded(stringBuilder0);
            }
            this.renderName(classDescriptor0, stringBuilder0, true);
        }
        if(z) {
            return;
        }
        List list1 = classDescriptor0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "klass.declaredTypeParameters");
        this.renderTypeParameters(list1, stringBuilder0, false);
        this.renderCapturedTypeParametersIfRequired(classDescriptor0, stringBuilder0);
        if(!classDescriptor0.getKind().isSingleton() && this.getClassWithPrimaryConstructor()) {
            ClassConstructorDescriptor classConstructorDescriptor0 = classDescriptor0.getUnsubstitutedPrimaryConstructor();
            if(classConstructorDescriptor0 != null) {
                stringBuilder0.append(" ");
                DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, classConstructorDescriptor0, null, 2, null);
                DescriptorVisibility descriptorVisibility1 = classConstructorDescriptor0.getVisibility();
                Intrinsics.checkNotNullExpressionValue(descriptorVisibility1, "primaryConstructor.visibility");
                this.renderVisibility(descriptorVisibility1, stringBuilder0);
                stringBuilder0.append(this.renderKeyword("constructor"));
                List list2 = classConstructorDescriptor0.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list2, "primaryConstructor.valueParameters");
                this.renderValueParameters(list2, classConstructorDescriptor0.hasSynthesizedParameterNames(), stringBuilder0);
            }
        }
        this.renderSuperTypes(classDescriptor0, stringBuilder0);
        this.renderWhereSuffix(list1, stringBuilder0);
    }

    private final void renderClassKindPrefix(ClassDescriptor classDescriptor0, StringBuilder stringBuilder0) {
        stringBuilder0.append(this.renderKeyword(DescriptorRenderer.Companion.getClassifierKindPrefix(classDescriptor0)));
    }

    public String renderClassifierName(ClassifierDescriptor classifierDescriptor0) {
        Intrinsics.checkNotNullParameter(classifierDescriptor0, "klass");
        return ErrorUtils.isError(classifierDescriptor0) ? classifierDescriptor0.getTypeConstructor().toString() : this.getClassifierNamePolicy().renderClassifier(classifierDescriptor0, this);
    }

    private final void renderCompanionObjectName(DeclarationDescriptor declarationDescriptor0, StringBuilder stringBuilder0) {
        if(this.getRenderCompanionObjectName()) {
            if(this.getStartFromName()) {
                stringBuilder0.append("companion object");
            }
            this.renderSpaceIfNeeded(stringBuilder0);
            DeclarationDescriptor declarationDescriptor1 = declarationDescriptor0.getContainingDeclaration();
            if(declarationDescriptor1 != null) {
                stringBuilder0.append("of ");
                Name name0 = declarationDescriptor1.getName();
                Intrinsics.checkNotNullExpressionValue(name0, "containingDeclaration.name");
                stringBuilder0.append(this.renderName(name0, false));
            }
        }
        if(!this.getVerbose() && Intrinsics.areEqual(declarationDescriptor0.getName(), SpecialNames.DEFAULT_NAME_FOR_COMPANION_OBJECT)) {
            return;
        }
        if(!this.getStartFromName()) {
            this.renderSpaceIfNeeded(stringBuilder0);
        }
        Name name1 = declarationDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name1, "descriptor.name");
        stringBuilder0.append(this.renderName(name1, true));
    }

    private final String renderConstant(ConstantValue constantValue0) {
        if(constantValue0 instanceof ArrayValue) {
            return CollectionsKt.joinToString$default(((Iterable)((ArrayValue)constantValue0).getValue()), ", ", "{", "}", 0, null, new Function1() {
                {
                    DescriptorRendererImpl.this = descriptorRendererImpl0;
                    super(1);
                }

                public final CharSequence invoke(ConstantValue constantValue0) {
                    Intrinsics.checkNotNullParameter(constantValue0, "it");
                    return DescriptorRendererImpl.this.renderConstant(constantValue0);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((ConstantValue)object0));
                }
            }, 24, null);
        }
        if(constantValue0 instanceof AnnotationValue) {
            return StringsKt.removePrefix(DescriptorRenderer.renderAnnotation$default(this, ((AnnotationDescriptor)((AnnotationValue)constantValue0).getValue()), null, 2, null), "@");
        }
        if(constantValue0 instanceof KClassValue) {
            Value kClassValue$Value0 = (Value)((KClassValue)constantValue0).getValue();
            if(kClassValue$Value0 instanceof LocalClass) {
                return ((LocalClass)kClassValue$Value0).getType() + "::class";
            }
            if(!(kClassValue$Value0 instanceof NormalClass)) {
                throw new NoWhenBranchMatchedException();
            }
            String s = ((NormalClass)kClassValue$Value0).getClassId().asSingleFqName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "classValue.classId.asSingleFqName().asString()");
            int v = ((NormalClass)kClassValue$Value0).getArrayDimensions();
            for(int v1 = 0; v1 < v; ++v1) {
                s = "kotlin.Array<" + s + '>';
            }
            return s + "::class";
        }
        return constantValue0.toString();
    }

    private final void renderConstructor(ConstructorDescriptor constructorDescriptor0, StringBuilder stringBuilder0) {
        boolean z;
        DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, constructorDescriptor0, null, 2, null);
        if(!this.options.getRenderDefaultVisibility() && constructorDescriptor0.getConstructedClass().getModality() == Modality.SEALED) {
            z = false;
        }
        else {
            DescriptorVisibility descriptorVisibility0 = constructorDescriptor0.getVisibility();
            Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "constructor.visibility");
            z = this.renderVisibility(descriptorVisibility0, stringBuilder0);
        }
        this.renderMemberKind(constructorDescriptor0, stringBuilder0);
        boolean z1 = this.getRenderConstructorKeyword() || !constructorDescriptor0.isPrimary() || z;
        if(z1) {
            stringBuilder0.append(this.renderKeyword("constructor"));
        }
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0 = constructorDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(classifierDescriptorWithTypeParameters0, "constructor.containingDeclaration");
        if(this.getSecondaryConstructorsAsPrimary()) {
            if(z1) {
                stringBuilder0.append(" ");
            }
            this.renderName(classifierDescriptorWithTypeParameters0, stringBuilder0, true);
            List list0 = constructorDescriptor0.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "constructor.typeParameters");
            this.renderTypeParameters(list0, stringBuilder0, false);
        }
        List list1 = constructorDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "constructor.valueParameters");
        this.renderValueParameters(list1, constructorDescriptor0.hasSynthesizedParameterNames(), stringBuilder0);
        if(this.getRenderConstructorDelegation() && !constructorDescriptor0.isPrimary() && classifierDescriptorWithTypeParameters0 instanceof ClassDescriptor) {
            ClassConstructorDescriptor classConstructorDescriptor0 = ((ClassDescriptor)classifierDescriptorWithTypeParameters0).getUnsubstitutedPrimaryConstructor();
            if(classConstructorDescriptor0 != null) {
                List list2 = classConstructorDescriptor0.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list2, "primaryConstructor.valueParameters");
                Collection collection0 = new ArrayList();
                for(Object object0: list2) {
                    if(!((ValueParameterDescriptor)object0).declaresDefaultValue() && ((ValueParameterDescriptor)object0).getVarargElementType() == null) {
                        collection0.add(object0);
                    }
                }
                if(!((List)collection0).isEmpty()) {
                    stringBuilder0.append(" : ");
                    stringBuilder0.append(this.renderKeyword("this"));
                    stringBuilder0.append(CollectionsKt.joinToString$default(((List)collection0), ", ", "(", ")", 0, null, kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1.INSTANCE, 24, null));
                }
            }
        }
        if(this.getSecondaryConstructorsAsPrimary()) {
            List list3 = constructorDescriptor0.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list3, "constructor.typeParameters");
            this.renderWhereSuffix(list3, stringBuilder0);
        }

        final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1();
            }

            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderConstructor.1() {
                super(1);
            }

            public final CharSequence invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                return "";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }
        }

    }

    private final void renderContextReceivers(List list0, StringBuilder stringBuilder0) {
        if(!list0.isEmpty()) {
            stringBuilder0.append("context(");
            int v = 0;
            for(Object object0: list0) {
                this.renderAnnotations(stringBuilder0, ((ReceiverParameterDescriptor)object0), AnnotationUseSiteTarget.RECEIVER);
                KotlinType kotlinType0 = ((ReceiverParameterDescriptor)object0).getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "contextReceiver.type");
                stringBuilder0.append(this.renderForReceiver(kotlinType0));
                if(v == CollectionsKt.getLastIndex(list0)) {
                    stringBuilder0.append(") ");
                }
                else {
                    stringBuilder0.append(", ");
                }
                ++v;
            }
        }
    }

    private final void renderDefaultType(StringBuilder stringBuilder0, KotlinType kotlinType0) {
        DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, kotlinType0, null, 2, null);
        SimpleType simpleType0 = null;
        DefinitelyNotNullType definitelyNotNullType0 = kotlinType0 instanceof DefinitelyNotNullType ? ((DefinitelyNotNullType)kotlinType0) : null;
        if(definitelyNotNullType0 != null) {
            simpleType0 = definitelyNotNullType0.getOriginal();
        }
        if(!KotlinTypeKt.isError(kotlinType0)) {
            if(kotlinType0 instanceof StubTypeForBuilderInference) {
                stringBuilder0.append(((StubTypeForBuilderInference)kotlinType0).getOriginalTypeVariable().toString());
            }
            else if(simpleType0 instanceof StubTypeForBuilderInference) {
                stringBuilder0.append(((StubTypeForBuilderInference)simpleType0).getOriginalTypeVariable().toString());
            }
            else {
                DescriptorRendererImpl.renderTypeConstructorAndArguments$default(this, stringBuilder0, kotlinType0, null, 2, null);
            }
        }
        else if(TypeUtilsKt.isUnresolvedType(kotlinType0) && this.getPresentableUnresolvedTypes()) {
            stringBuilder0.append(this.renderError(ErrorUtils.INSTANCE.unresolvedTypeAsItIs(kotlinType0)));
        }
        else {
            if(!(kotlinType0 instanceof ErrorType) || this.getInformativeErrorType()) {
                stringBuilder0.append(kotlinType0.getConstructor().toString());
            }
            else {
                stringBuilder0.append(((ErrorType)kotlinType0).getDebugMessage());
            }
            stringBuilder0.append(this.renderTypeArguments(kotlinType0.getArguments()));
        }
        if(kotlinType0.isMarkedNullable()) {
            stringBuilder0.append("?");
        }
        if(SpecialTypesKt.isDefinitelyNotNullType(kotlinType0)) {
            stringBuilder0.append(" & Any");
        }
    }

    private final String renderError(String s) {
        switch(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$0[this.getTextFormat().ordinal()]) {
            case 1: {
                return s;
            }
            case 2: {
                return "<font color=red><b>" + s + "</b></font>";
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderFlexibleType(String s, String s1, KotlinBuiltIns kotlinBuiltIns0) {
        Intrinsics.checkNotNullParameter(s, "lowerRendered");
        Intrinsics.checkNotNullParameter(s1, "upperRendered");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        if(RenderingUtilsKt.typeStringsDifferOnlyInNullability(s, s1)) {
            return StringsKt.startsWith$default(s1, "(", false, 2, null) ? "(" + s + ")!" : s + '!';
        }
        ClassifierNamePolicy classifierNamePolicy0 = this.getClassifierNamePolicy();
        ClassDescriptor classDescriptor0 = kotlinBuiltIns0.getCollection();
        Intrinsics.checkNotNullExpressionValue(classDescriptor0, "builtIns.collection");
        String s2 = StringsKt.substringBefore$default(classifierNamePolicy0.renderClassifier(classDescriptor0, this), "Collection", null, 2, null);
        String s3 = RenderingUtilsKt.replacePrefixesInTypeRepresentations(s, s2 + "Mutable", s1, s2, s2 + "(Mutable)");
        if(s3 != null) {
            return s3;
        }
        String s4 = RenderingUtilsKt.replacePrefixesInTypeRepresentations(s, s2 + "MutableMap.MutableEntry", s1, s2 + "Map.Entry", s2 + "(Mutable)Map.(Mutable)Entry");
        if(s4 != null) {
            return s4;
        }
        ClassifierNamePolicy classifierNamePolicy1 = this.getClassifierNamePolicy();
        ClassDescriptor classDescriptor1 = kotlinBuiltIns0.getArray();
        Intrinsics.checkNotNullExpressionValue(classDescriptor1, "builtIns.array");
        String s5 = StringsKt.substringBefore$default(classifierNamePolicy1.renderClassifier(classDescriptor1, this), "Array", null, 2, null);
        String s6 = RenderingUtilsKt.replacePrefixesInTypeRepresentations(s, s5 + this.escape("Array<"), s1, s5 + this.escape("Array<out "), s5 + this.escape("Array<(out) "));
        return s6 == null ? "(" + s + ".." + s1 + ')' : s6;
    }

    private final String renderForReceiver(KotlinType kotlinType0) {
        String s = this.renderType(kotlinType0);
        return (!this.shouldRenderAsPrettyFunctionType(kotlinType0) || TypeUtils.isNullableType(kotlinType0)) && !(kotlinType0 instanceof DefinitelyNotNullType) ? s : "(" + s + ')';
    }

    private final String renderFqName(List list0) {
        return this.escape(RenderingUtilsKt.renderFqName(list0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderFqName(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "fqName");
        List list0 = fqNameUnsafe0.pathSegments();
        Intrinsics.checkNotNullExpressionValue(list0, "fqName.pathSegments()");
        return this.renderFqName(list0);
    }

    private final void renderFunction(FunctionDescriptor functionDescriptor0, StringBuilder stringBuilder0) {
        if(!this.getStartFromName()) {
            if(!this.getStartFromDeclarationKeyword()) {
                DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, functionDescriptor0, null, 2, null);
                List list0 = functionDescriptor0.getContextReceiverParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "function.contextReceiverParameters");
                this.renderContextReceivers(list0, stringBuilder0);
                DescriptorVisibility descriptorVisibility0 = functionDescriptor0.getVisibility();
                Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "function.visibility");
                this.renderVisibility(descriptorVisibility0, stringBuilder0);
                this.renderModalityForCallable(functionDescriptor0, stringBuilder0);
                if(this.getIncludeAdditionalModifiers()) {
                    this.renderMemberModifiers(functionDescriptor0, stringBuilder0);
                }
                this.renderOverride(functionDescriptor0, stringBuilder0);
                if(this.getIncludeAdditionalModifiers()) {
                    this.renderAdditionalModifiers(functionDescriptor0, stringBuilder0);
                }
                else {
                    this.renderSuspendModifier(functionDescriptor0, stringBuilder0);
                }
                this.renderMemberKind(functionDescriptor0, stringBuilder0);
                if(this.getVerbose()) {
                    if(functionDescriptor0.isHiddenToOvercomeSignatureClash()) {
                        stringBuilder0.append("/*isHiddenToOvercomeSignatureClash*/ ");
                    }
                    if(functionDescriptor0.isHiddenForResolutionEverywhereBesideSupercalls()) {
                        stringBuilder0.append("/*isHiddenForResolutionEverywhereBesideSupercalls*/ ");
                    }
                }
            }
            stringBuilder0.append(this.renderKeyword("fun"));
            stringBuilder0.append(" ");
            List list1 = functionDescriptor0.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "function.typeParameters");
            this.renderTypeParameters(list1, stringBuilder0, true);
            this.renderReceiver(functionDescriptor0, stringBuilder0);
        }
        this.renderName(functionDescriptor0, stringBuilder0, true);
        List list2 = functionDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list2, "function.valueParameters");
        this.renderValueParameters(list2, functionDescriptor0.hasSynthesizedParameterNames(), stringBuilder0);
        this.renderReceiverAfterName(functionDescriptor0, stringBuilder0);
        KotlinType kotlinType0 = functionDescriptor0.getReturnType();
        if(!this.getWithoutReturnType() && (this.getUnitReturnType() || kotlinType0 == null || !KotlinBuiltIns.isUnit(kotlinType0))) {
            stringBuilder0.append(": ");
            stringBuilder0.append((kotlinType0 == null ? "[NULL]" : this.renderType(kotlinType0)));
        }
        List list3 = functionDescriptor0.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list3, "function.typeParameters");
        this.renderWhereSuffix(list3, stringBuilder0);
    }

    private final void renderFunctionType(StringBuilder stringBuilder0, KotlinType kotlinType0) {
        Name name0;
        int v = stringBuilder0.length();
        DescriptorRendererImpl.renderAnnotations$default(this.getFunctionTypeAnnotationsRenderer(), stringBuilder0, kotlinType0, null, 2, null);
        boolean z = stringBuilder0.length() != v;
        KotlinType kotlinType1 = FunctionTypesKt.getReceiverTypeFromFunctionType(kotlinType0);
        List list0 = FunctionTypesKt.getContextReceiverTypesFromFunctionType(kotlinType0);
        if(!list0.isEmpty()) {
            stringBuilder0.append("context(");
            for(Object object0: list0.subList(0, CollectionsKt.getLastIndex(list0))) {
                this.renderNormalizedType(stringBuilder0, ((KotlinType)object0));
                stringBuilder0.append(", ");
            }
            this.renderNormalizedType(stringBuilder0, ((KotlinType)CollectionsKt.last(list0)));
            stringBuilder0.append(") ");
        }
        boolean z1 = FunctionTypesKt.isSuspendFunctionType(kotlinType0);
        boolean z2 = kotlinType0.isMarkedNullable();
        boolean z3 = z2 || z && kotlinType1 != null;
        if(z3) {
            if(z1) {
                stringBuilder0.insert(v, '(');
            }
            else {
                if(z) {
                    CharsKt.isWhitespace(StringsKt.last(stringBuilder0));
                    if(stringBuilder0.charAt(StringsKt.getLastIndex(stringBuilder0) - 1) != 41) {
                        stringBuilder0.insert(StringsKt.getLastIndex(stringBuilder0), "()");
                    }
                }
                stringBuilder0.append("(");
            }
        }
        this.renderModifier(stringBuilder0, z1, "suspend");
        if(kotlinType1 != null) {
            boolean z4 = this.shouldRenderAsPrettyFunctionType(kotlinType1) && !kotlinType1.isMarkedNullable() || this.hasModifiersOrAnnotations(kotlinType1) || kotlinType1 instanceof DefinitelyNotNullType;
            if(z4) {
                stringBuilder0.append("(");
            }
            this.renderNormalizedType(stringBuilder0, kotlinType1);
            if(z4) {
                stringBuilder0.append(")");
            }
            stringBuilder0.append(".");
        }
        stringBuilder0.append("(");
        if(!FunctionTypesKt.isBuiltinExtensionFunctionalType(kotlinType0) || kotlinType0.getArguments().size() > 1) {
            int v1 = 0;
            for(Object object1: FunctionTypesKt.getValueParameterTypesFromFunctionType(kotlinType0)) {
                TypeProjection typeProjection0 = (TypeProjection)object1;
                if(v1 > 0) {
                    stringBuilder0.append(", ");
                }
                if(this.getParameterNamesInFunctionalTypes()) {
                    KotlinType kotlinType2 = typeProjection0.getType();
                    Intrinsics.checkNotNullExpressionValue(kotlinType2, "typeProjection.type");
                    name0 = FunctionTypesKt.extractParameterNameFromFunctionTypeArgument(kotlinType2);
                }
                else {
                    name0 = null;
                }
                if(name0 != null) {
                    stringBuilder0.append(this.renderName(name0, false));
                    stringBuilder0.append(": ");
                }
                stringBuilder0.append(this.renderTypeProjection(typeProjection0));
                ++v1;
            }
        }
        else {
            stringBuilder0.append("???");
        }
        stringBuilder0.append(") ");
        stringBuilder0.append(this.arrow());
        stringBuilder0.append(" ");
        this.renderNormalizedType(stringBuilder0, FunctionTypesKt.getReturnTypeFromFunctionType(kotlinType0));
        if(z3) {
            stringBuilder0.append(")");
        }
        if(z2) {
            stringBuilder0.append("?");
        }
    }

    private final void renderInitializer(VariableDescriptor variableDescriptor0, StringBuilder stringBuilder0) {
        if(this.getIncludePropertyConstant()) {
            ConstantValue constantValue0 = variableDescriptor0.getCompileTimeInitializer();
            if(constantValue0 != null) {
                stringBuilder0.append(" = ");
                stringBuilder0.append(this.escape(this.renderConstant(constantValue0)));
            }
        }
    }

    private final String renderKeyword(String s) {
        switch(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$0[this.getTextFormat().ordinal()]) {
            case 1: {
                return s;
            }
            case 2: {
                return this.getBoldOnlyForNamesInHtml() ? s : "<b>" + s + "</b>";
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final void renderMemberKind(CallableMemberDescriptor callableMemberDescriptor0, StringBuilder stringBuilder0) {
        if(this.getModifiers().contains(DescriptorRendererModifier.MEMBER_KIND) && this.getVerbose() && callableMemberDescriptor0.getKind() != Kind.DECLARATION) {
            stringBuilder0.append("/*");
            stringBuilder0.append(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(callableMemberDescriptor0.getKind().name()));
            stringBuilder0.append("*/ ");
        }
    }

    private final void renderMemberModifiers(MemberDescriptor memberDescriptor0, StringBuilder stringBuilder0) {
        this.renderModifier(stringBuilder0, memberDescriptor0.isExternal(), "external");
        boolean z = true;
        this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.EXPECT) && memberDescriptor0.isExpect(), "expect");
        if(!this.getModifiers().contains(DescriptorRendererModifier.ACTUAL) || !memberDescriptor0.isActual()) {
            z = false;
        }
        this.renderModifier(stringBuilder0, z, "actual");
    }

    public String renderMessage(String s) {
        Intrinsics.checkNotNullParameter(s, "message");
        switch(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$0[this.getTextFormat().ordinal()]) {
            case 1: {
                return s;
            }
            case 2: {
                return "<i>" + s + "</i>";
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final void renderModality(Modality modality0, StringBuilder stringBuilder0, Modality modality1) {
        if(!this.getRenderDefaultModality() && modality0 == modality1) {
            return;
        }
        this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.MODALITY), CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(modality0.name()));
    }

    private final void renderModalityForCallable(CallableMemberDescriptor callableMemberDescriptor0, StringBuilder stringBuilder0) {
        if(DescriptorUtils.isTopLevelDeclaration(callableMemberDescriptor0) && callableMemberDescriptor0.getModality() == Modality.FINAL || this.getOverrideRenderingPolicy() == OverrideRenderingPolicy.RENDER_OVERRIDE && callableMemberDescriptor0.getModality() == Modality.OPEN && this.overridesSomething(callableMemberDescriptor0)) {
            return;
        }
        Modality modality0 = callableMemberDescriptor0.getModality();
        Intrinsics.checkNotNullExpressionValue(modality0, "callable.modality");
        this.renderModality(modality0, stringBuilder0, this.implicitModalityWithoutExtensions(callableMemberDescriptor0));
    }

    private final void renderModifier(StringBuilder stringBuilder0, boolean z, String s) {
        if(z) {
            stringBuilder0.append(this.renderKeyword(s));
            stringBuilder0.append(" ");
        }
    }

    private final void renderName(DeclarationDescriptor declarationDescriptor0, StringBuilder stringBuilder0, boolean z) {
        Name name0 = declarationDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
        stringBuilder0.append(this.renderName(name0, z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderName(Name name0, boolean z) {
        Intrinsics.checkNotNullParameter(name0, "name");
        String s = this.escape(RenderingUtilsKt.render(name0));
        return !this.getBoldOnlyForNamesInHtml() || this.getTextFormat() != RenderingFormat.HTML || !z ? s : "<b>" + s + "</b>";
    }

    private final void renderNormalizedType(StringBuilder stringBuilder0, KotlinType kotlinType0) {
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        AbbreviatedType abbreviatedType0 = unwrappedType0 instanceof AbbreviatedType ? ((AbbreviatedType)unwrappedType0) : null;
        if(abbreviatedType0 != null) {
            if(this.getRenderTypeExpansions()) {
                this.renderNormalizedTypeAsIs(stringBuilder0, abbreviatedType0.getExpandedType());
                return;
            }
            this.renderNormalizedTypeAsIs(stringBuilder0, abbreviatedType0.getAbbreviation());
            if(this.getRenderUnabbreviatedType()) {
                this.renderAbbreviatedTypeExpansion(stringBuilder0, abbreviatedType0);
            }
            return;
        }
        this.renderNormalizedTypeAsIs(stringBuilder0, kotlinType0);
    }

    private final void renderNormalizedTypeAsIs(StringBuilder stringBuilder0, KotlinType kotlinType0) {
        if(kotlinType0 instanceof WrappedType && this.getDebugMode() && !((WrappedType)kotlinType0).isComputed()) {
            stringBuilder0.append("<Not computed yet>");
            return;
        }
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            stringBuilder0.append(((FlexibleType)unwrappedType0).render(this, this));
            return;
        }
        if(unwrappedType0 instanceof SimpleType) {
            this.renderSimpleType(stringBuilder0, ((SimpleType)unwrappedType0));
        }
    }

    private final void renderOverride(CallableMemberDescriptor callableMemberDescriptor0, StringBuilder stringBuilder0) {
        if(this.getModifiers().contains(DescriptorRendererModifier.OVERRIDE) && this.overridesSomething(callableMemberDescriptor0) && this.getOverrideRenderingPolicy() != OverrideRenderingPolicy.RENDER_OPEN) {
            this.renderModifier(stringBuilder0, true, "override");
            if(this.getVerbose()) {
                stringBuilder0.append("/*");
                stringBuilder0.append(callableMemberDescriptor0.getOverriddenDescriptors().size());
                stringBuilder0.append("*/ ");
            }
        }
    }

    private final void renderPackageFragment(PackageFragmentDescriptor packageFragmentDescriptor0, StringBuilder stringBuilder0) {
        this.renderPackageHeader(packageFragmentDescriptor0.getFqName(), "package-fragment", stringBuilder0);
        if(this.getDebugMode()) {
            stringBuilder0.append(" in ");
            this.renderName(packageFragmentDescriptor0.getContainingDeclaration(), stringBuilder0, false);
        }
    }

    private final void renderPackageHeader(FqName fqName0, String s, StringBuilder stringBuilder0) {
        stringBuilder0.append(this.renderKeyword(s));
        FqNameUnsafe fqNameUnsafe0 = fqName0.toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "fqName.toUnsafe()");
        String s1 = this.renderFqName(fqNameUnsafe0);
        if(s1.length() > 0) {
            stringBuilder0.append(" ");
            stringBuilder0.append(s1);
        }
    }

    private final void renderPackageView(PackageViewDescriptor packageViewDescriptor0, StringBuilder stringBuilder0) {
        this.renderPackageHeader(packageViewDescriptor0.getFqName(), "package", stringBuilder0);
        if(this.getDebugMode()) {
            stringBuilder0.append(" in context of ");
            this.renderName(packageViewDescriptor0.getModule(), stringBuilder0, false);
        }
    }

    private final void renderPossiblyInnerType(StringBuilder stringBuilder0, PossiblyInnerType possiblyInnerType0) {
        PossiblyInnerType possiblyInnerType1 = possiblyInnerType0.getOuterType();
        if(possiblyInnerType1 == null) {
            TypeConstructor typeConstructor0 = possiblyInnerType0.getClassifierDescriptor().getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor0, "possiblyInnerType.classi…escriptor.typeConstructor");
            stringBuilder0.append(this.renderTypeConstructor(typeConstructor0));
        }
        else {
            this.renderPossiblyInnerType(stringBuilder0, possiblyInnerType1);
            stringBuilder0.append('.');
            Name name0 = possiblyInnerType0.getClassifierDescriptor().getName();
            Intrinsics.checkNotNullExpressionValue(name0, "possiblyInnerType.classifierDescriptor.name");
            stringBuilder0.append(this.renderName(name0, false));
        }
        stringBuilder0.append(this.renderTypeArguments(possiblyInnerType0.getArguments()));
    }

    private final void renderProperty(PropertyDescriptor propertyDescriptor0, StringBuilder stringBuilder0) {
        if(!this.getStartFromName()) {
            if(!this.getStartFromDeclarationKeyword()) {
                this.renderPropertyAnnotations(propertyDescriptor0, stringBuilder0);
                List list0 = propertyDescriptor0.getContextReceiverParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "property.contextReceiverParameters");
                this.renderContextReceivers(list0, stringBuilder0);
                DescriptorVisibility descriptorVisibility0 = propertyDescriptor0.getVisibility();
                Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "property.visibility");
                this.renderVisibility(descriptorVisibility0, stringBuilder0);
                boolean z = false;
                this.renderModifier(stringBuilder0, this.getModifiers().contains(DescriptorRendererModifier.CONST) && propertyDescriptor0.isConst(), "const");
                this.renderMemberModifiers(propertyDescriptor0, stringBuilder0);
                this.renderModalityForCallable(propertyDescriptor0, stringBuilder0);
                this.renderOverride(propertyDescriptor0, stringBuilder0);
                if(this.getModifiers().contains(DescriptorRendererModifier.LATEINIT) && propertyDescriptor0.isLateInit()) {
                    z = true;
                }
                this.renderModifier(stringBuilder0, z, "lateinit");
                this.renderMemberKind(propertyDescriptor0, stringBuilder0);
            }
            DescriptorRendererImpl.renderValVarPrefix$default(this, propertyDescriptor0, stringBuilder0, false, 4, null);
            List list1 = propertyDescriptor0.getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "property.typeParameters");
            this.renderTypeParameters(list1, stringBuilder0, true);
            this.renderReceiver(propertyDescriptor0, stringBuilder0);
        }
        this.renderName(propertyDescriptor0, stringBuilder0, true);
        stringBuilder0.append(": ");
        KotlinType kotlinType0 = propertyDescriptor0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "property.type");
        stringBuilder0.append(this.renderType(kotlinType0));
        this.renderReceiverAfterName(propertyDescriptor0, stringBuilder0);
        this.renderInitializer(propertyDescriptor0, stringBuilder0);
        List list2 = propertyDescriptor0.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list2, "property.typeParameters");
        this.renderWhereSuffix(list2, stringBuilder0);
    }

    private final void renderPropertyAnnotations(PropertyDescriptor propertyDescriptor0, StringBuilder stringBuilder0) {
        if(this.getModifiers().contains(DescriptorRendererModifier.ANNOTATIONS)) {
            DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, propertyDescriptor0, null, 2, null);
            FieldDescriptor fieldDescriptor0 = propertyDescriptor0.getBackingField();
            if(fieldDescriptor0 != null) {
                this.renderAnnotations(stringBuilder0, fieldDescriptor0, AnnotationUseSiteTarget.FIELD);
            }
            FieldDescriptor fieldDescriptor1 = propertyDescriptor0.getDelegateField();
            if(fieldDescriptor1 != null) {
                this.renderAnnotations(stringBuilder0, fieldDescriptor1, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
            }
            if(this.getPropertyAccessorRenderingPolicy() == PropertyAccessorRenderingPolicy.NONE) {
                PropertyGetterDescriptor propertyGetterDescriptor0 = propertyDescriptor0.getGetter();
                if(propertyGetterDescriptor0 != null) {
                    this.renderAnnotations(stringBuilder0, propertyGetterDescriptor0, AnnotationUseSiteTarget.PROPERTY_GETTER);
                }
                PropertySetterDescriptor propertySetterDescriptor0 = propertyDescriptor0.getSetter();
                if(propertySetterDescriptor0 != null) {
                    this.renderAnnotations(stringBuilder0, propertySetterDescriptor0, AnnotationUseSiteTarget.PROPERTY_SETTER);
                    List list0 = propertySetterDescriptor0.getValueParameters();
                    Intrinsics.checkNotNullExpressionValue(list0, "setter.valueParameters");
                    ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)CollectionsKt.single(list0);
                    Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor0, "it");
                    this.renderAnnotations(stringBuilder0, valueParameterDescriptor0, AnnotationUseSiteTarget.SETTER_PARAMETER);
                }
            }
        }
    }

    private final void renderReceiver(CallableDescriptor callableDescriptor0, StringBuilder stringBuilder0) {
        ReceiverParameterDescriptor receiverParameterDescriptor0 = callableDescriptor0.getExtensionReceiverParameter();
        if(receiverParameterDescriptor0 != null) {
            this.renderAnnotations(stringBuilder0, receiverParameterDescriptor0, AnnotationUseSiteTarget.RECEIVER);
            KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "receiver.type");
            stringBuilder0.append(this.renderForReceiver(kotlinType0));
            stringBuilder0.append(".");
        }
    }

    private final void renderReceiverAfterName(CallableDescriptor callableDescriptor0, StringBuilder stringBuilder0) {
        if(this.getReceiverAfterName()) {
            ReceiverParameterDescriptor receiverParameterDescriptor0 = callableDescriptor0.getExtensionReceiverParameter();
            if(receiverParameterDescriptor0 != null) {
                stringBuilder0.append(" on ");
                KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "receiver.type");
                stringBuilder0.append(this.renderType(kotlinType0));
            }
        }
    }

    private final void renderSimpleType(StringBuilder stringBuilder0, SimpleType simpleType0) {
        if(!Intrinsics.areEqual(simpleType0, TypeUtils.CANNOT_INFER_FUNCTION_PARAM_TYPE) && !TypeUtils.isDontCarePlaceholder(simpleType0)) {
            if(ErrorUtils.isUninferredTypeVariable(simpleType0)) {
                if(this.getUninferredTypeParameterAsName()) {
                    TypeConstructor typeConstructor0 = simpleType0.getConstructor();
                    Intrinsics.checkNotNull(typeConstructor0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.error.ErrorTypeConstructor");
                    stringBuilder0.append(this.renderError(((ErrorTypeConstructor)typeConstructor0).getParam(0)));
                    return;
                }
                stringBuilder0.append("???");
                return;
            }
            if(KotlinTypeKt.isError(simpleType0)) {
                this.renderDefaultType(stringBuilder0, simpleType0);
                return;
            }
            if(this.shouldRenderAsPrettyFunctionType(simpleType0)) {
                this.renderFunctionType(stringBuilder0, simpleType0);
                return;
            }
            this.renderDefaultType(stringBuilder0, simpleType0);
            return;
        }
        stringBuilder0.append("???");
    }

    private final void renderSpaceIfNeeded(StringBuilder stringBuilder0) {
        int v = stringBuilder0.length();
        if(v != 0 && stringBuilder0.charAt(v - 1) == 0x20) {
            return;
        }
        stringBuilder0.append(' ');
    }

    private final void renderSuperTypes(ClassDescriptor classDescriptor0, StringBuilder stringBuilder0) {
        if(!this.getWithoutSuperTypes() && !KotlinBuiltIns.isNothing(classDescriptor0.getDefaultType())) {
            Collection collection0 = classDescriptor0.getTypeConstructor().getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collection0, "klass.typeConstructor.supertypes");
            if(!collection0.isEmpty()) {
                if(collection0.size() == 1) {
                    Object object0 = collection0.iterator().next();
                    if(!KotlinBuiltIns.isAnyOrNullableAny(((KotlinType)object0))) {
                        this.renderSpaceIfNeeded(stringBuilder0);
                        stringBuilder0.append(": ");
                        CollectionsKt.joinTo$default(collection0, stringBuilder0, ", ", null, null, 0, null, new Function1() {
                            {
                                DescriptorRendererImpl.this = descriptorRendererImpl0;
                                super(1);
                            }

                            public final CharSequence invoke(KotlinType kotlinType0) {
                                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it");
                                return DescriptorRendererImpl.this.renderType(kotlinType0);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                return this.invoke(((KotlinType)object0));
                            }
                        }, 60, null);
                    }
                }
                else {
                    this.renderSpaceIfNeeded(stringBuilder0);
                    stringBuilder0.append(": ");
                    CollectionsKt.joinTo$default(collection0, stringBuilder0, ", ", null, null, 0, null, new Function1() {
                        {
                            DescriptorRendererImpl.this = descriptorRendererImpl0;
                            super(1);
                        }

                        public final CharSequence invoke(KotlinType kotlinType0) {
                            Intrinsics.checkNotNullExpressionValue(kotlinType0, "it");
                            return DescriptorRendererImpl.this.renderType(kotlinType0);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            return this.invoke(((KotlinType)object0));
                        }
                    }, 60, null);
                }
            }
        }
    }

    private final void renderSuspendModifier(FunctionDescriptor functionDescriptor0, StringBuilder stringBuilder0) {
        this.renderModifier(stringBuilder0, functionDescriptor0.isSuspend(), "suspend");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        StringBuilder stringBuilder0 = new StringBuilder();
        this.renderNormalizedType(stringBuilder0, ((KotlinType)this.getTypeNormalizer().invoke(kotlinType0)));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    private final void renderTypeAlias(TypeAliasDescriptor typeAliasDescriptor0, StringBuilder stringBuilder0) {
        DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, typeAliasDescriptor0, null, 2, null);
        DescriptorVisibility descriptorVisibility0 = typeAliasDescriptor0.getVisibility();
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "typeAlias.visibility");
        this.renderVisibility(descriptorVisibility0, stringBuilder0);
        this.renderMemberModifiers(typeAliasDescriptor0, stringBuilder0);
        stringBuilder0.append(this.renderKeyword("typealias"));
        stringBuilder0.append(" ");
        this.renderName(typeAliasDescriptor0, stringBuilder0, true);
        List list0 = typeAliasDescriptor0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "typeAlias.declaredTypeParameters");
        this.renderTypeParameters(list0, stringBuilder0, false);
        this.renderCapturedTypeParametersIfRequired(typeAliasDescriptor0, stringBuilder0);
        stringBuilder0.append(" = ");
        stringBuilder0.append(this.renderType(typeAliasDescriptor0.getUnderlyingType()));
    }

    public String renderTypeArguments(List list0) {
        Intrinsics.checkNotNullParameter(list0, "typeArguments");
        if(list0.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(this.lt());
        this.appendTypeProjections(stringBuilder0, list0);
        stringBuilder0.append(this.gt());
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public String renderTypeConstructor(TypeConstructor typeConstructor0) {
        Intrinsics.checkNotNullParameter(typeConstructor0, "typeConstructor");
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        if(((classifierDescriptor0 instanceof TypeParameterDescriptor ? true : classifierDescriptor0 instanceof ClassDescriptor) ? true : classifierDescriptor0 instanceof TypeAliasDescriptor)) {
            return this.renderClassifierName(classifierDescriptor0);
        }
        if(classifierDescriptor0 != null) {
            throw new IllegalStateException(("Unexpected classifier: " + classifierDescriptor0.getClass()).toString());
        }
        return typeConstructor0 instanceof IntersectionTypeConstructor ? ((IntersectionTypeConstructor)typeConstructor0).makeDebugNameForIntersectionType(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1.INSTANCE) : typeConstructor0.toString();

        final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1();
            }

            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.renderTypeConstructor.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }

            public final Object invoke(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "it");
                return kotlinType0 instanceof StubTypeForBuilderInference ? ((StubTypeForBuilderInference)kotlinType0).getOriginalTypeVariable() : kotlinType0;
            }
        }

    }

    private final void renderTypeConstructorAndArguments(StringBuilder stringBuilder0, KotlinType kotlinType0, TypeConstructor typeConstructor0) {
        PossiblyInnerType possiblyInnerType0 = TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType0);
        if(possiblyInnerType0 == null) {
            stringBuilder0.append(this.renderTypeConstructor(typeConstructor0));
            stringBuilder0.append(this.renderTypeArguments(kotlinType0.getArguments()));
            return;
        }
        this.renderPossiblyInnerType(stringBuilder0, possiblyInnerType0);
    }

    static void renderTypeConstructorAndArguments$default(DescriptorRendererImpl descriptorRendererImpl0, StringBuilder stringBuilder0, KotlinType kotlinType0, TypeConstructor typeConstructor0, int v, Object object0) {
        if((v & 2) != 0) {
            typeConstructor0 = kotlinType0.getConstructor();
        }
        descriptorRendererImpl0.renderTypeConstructorAndArguments(stringBuilder0, kotlinType0, typeConstructor0);
    }

    private final void renderTypeParameter(TypeParameterDescriptor typeParameterDescriptor0, StringBuilder stringBuilder0, boolean z) {
        if(z) {
            stringBuilder0.append(this.lt());
        }
        if(this.getVerbose()) {
            stringBuilder0.append("/*");
            stringBuilder0.append(typeParameterDescriptor0.getIndex());
            stringBuilder0.append("*/ ");
        }
        this.renderModifier(stringBuilder0, typeParameterDescriptor0.isReified(), "reified");
        String s = typeParameterDescriptor0.getVariance().getLabel();
        boolean z1 = true;
        this.renderModifier(stringBuilder0, s.length() > 0, s);
        DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, typeParameterDescriptor0, null, 2, null);
        this.renderName(typeParameterDescriptor0, stringBuilder0, z);
        int v = typeParameterDescriptor0.getUpperBounds().size();
        if(v > 1 && !z || v == 1) {
            Object object0 = typeParameterDescriptor0.getUpperBounds().iterator().next();
            if(!KotlinBuiltIns.isDefaultBound(((KotlinType)object0))) {
                stringBuilder0.append(" : ");
                Intrinsics.checkNotNullExpressionValue(((KotlinType)object0), "upperBound");
                stringBuilder0.append(this.renderType(((KotlinType)object0)));
            }
        }
        else if(z) {
            for(Object object1: typeParameterDescriptor0.getUpperBounds()) {
                KotlinType kotlinType0 = (KotlinType)object1;
                if(!KotlinBuiltIns.isDefaultBound(kotlinType0)) {
                    if(z1) {
                        stringBuilder0.append(" : ");
                    }
                    else {
                        stringBuilder0.append(" & ");
                    }
                    Intrinsics.checkNotNullExpressionValue(kotlinType0, "upperBound");
                    stringBuilder0.append(this.renderType(kotlinType0));
                    z1 = false;
                }
            }
        }
        if(z) {
            stringBuilder0.append(this.gt());
        }
    }

    private final void renderTypeParameterList(StringBuilder stringBuilder0, List list0) {
        Iterator iterator0 = list0.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            this.renderTypeParameter(((TypeParameterDescriptor)object0), stringBuilder0, false);
            if(iterator0.hasNext()) {
                stringBuilder0.append(", ");
            }
        }
    }

    private final void renderTypeParameters(List list0, StringBuilder stringBuilder0, boolean z) {
        if(!this.getWithoutTypeParameters() && !list0.isEmpty()) {
            stringBuilder0.append(this.lt());
            this.renderTypeParameterList(stringBuilder0, list0);
            stringBuilder0.append(this.gt());
            if(z) {
                stringBuilder0.append(" ");
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer
    public String renderTypeProjection(TypeProjection typeProjection0) {
        Intrinsics.checkNotNullParameter(typeProjection0, "typeProjection");
        StringBuilder stringBuilder0 = new StringBuilder();
        this.appendTypeProjections(stringBuilder0, CollectionsKt.listOf(typeProjection0));
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    private final void renderValVarPrefix(VariableDescriptor variableDescriptor0, StringBuilder stringBuilder0, boolean z) {
        if(!z && variableDescriptor0 instanceof ValueParameterDescriptor) {
            return;
        }
        stringBuilder0.append(this.renderKeyword((variableDescriptor0.isVar() ? "var" : "val")));
        stringBuilder0.append(" ");
    }

    static void renderValVarPrefix$default(DescriptorRendererImpl descriptorRendererImpl0, VariableDescriptor variableDescriptor0, StringBuilder stringBuilder0, boolean z, int v, Object object0) {
        if((v & 4) != 0) {
            z = false;
        }
        descriptorRendererImpl0.renderValVarPrefix(variableDescriptor0, stringBuilder0, z);
    }

    private final void renderValueParameter(ValueParameterDescriptor valueParameterDescriptor0, boolean z, StringBuilder stringBuilder0, boolean z1) {
        boolean z2;
        if(z1) {
            stringBuilder0.append(this.renderKeyword("value-parameter"));
            stringBuilder0.append(" ");
        }
        if(this.getVerbose()) {
            stringBuilder0.append("/*");
            stringBuilder0.append(valueParameterDescriptor0.getIndex());
            stringBuilder0.append("*/ ");
        }
        DescriptorRendererImpl.renderAnnotations$default(this, stringBuilder0, valueParameterDescriptor0, null, 2, null);
        this.renderModifier(stringBuilder0, valueParameterDescriptor0.isCrossinline(), "crossinline");
        this.renderModifier(stringBuilder0, valueParameterDescriptor0.isNoinline(), "noinline");
        if(this.getRenderPrimaryConstructorParametersAsProperties()) {
            CallableDescriptor callableDescriptor0 = valueParameterDescriptor0.getContainingDeclaration();
            ClassConstructorDescriptor classConstructorDescriptor0 = callableDescriptor0 instanceof ClassConstructorDescriptor ? ((ClassConstructorDescriptor)callableDescriptor0) : null;
            z2 = classConstructorDescriptor0 == null || !classConstructorDescriptor0.isPrimary() ? false : true;
        }
        else {
            z2 = false;
        }
        if(z2) {
            this.renderModifier(stringBuilder0, this.getActualPropertiesInPrimaryConstructor(), "actual");
        }
        this.renderVariable(valueParameterDescriptor0, z, stringBuilder0, z1, z2);
        if(this.getDefaultParameterValueRenderer() != null && (this.getDebugMode() ? valueParameterDescriptor0.declaresDefaultValue() : DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor0))) {
            Function1 function10 = this.getDefaultParameterValueRenderer();
            Intrinsics.checkNotNull(function10);
            stringBuilder0.append(" = " + ((String)function10.invoke(valueParameterDescriptor0)));
        }
    }

    private final void renderValueParameters(Collection collection0, boolean z, StringBuilder stringBuilder0) {
        boolean z1 = this.shouldRenderParameterNames(z);
        int v = collection0.size();
        this.getValueParametersHandler().appendBeforeValueParameters(v, stringBuilder0);
        int v1 = 0;
        for(Object object0: collection0) {
            this.getValueParametersHandler().appendBeforeValueParameter(((ValueParameterDescriptor)object0), v1, v, stringBuilder0);
            this.renderValueParameter(((ValueParameterDescriptor)object0), z1, stringBuilder0, false);
            this.getValueParametersHandler().appendAfterValueParameter(((ValueParameterDescriptor)object0), v1, v, stringBuilder0);
            ++v1;
        }
        this.getValueParametersHandler().appendAfterValueParameters(v, stringBuilder0);
    }

    private final void renderVariable(VariableDescriptor variableDescriptor0, boolean z, StringBuilder stringBuilder0, boolean z1, boolean z2) {
        KotlinType kotlinType0 = variableDescriptor0.getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "variable.type");
        KotlinType kotlinType1 = null;
        ValueParameterDescriptor valueParameterDescriptor0 = variableDescriptor0 instanceof ValueParameterDescriptor ? ((ValueParameterDescriptor)variableDescriptor0) : null;
        if(valueParameterDescriptor0 != null) {
            kotlinType1 = valueParameterDescriptor0.getVarargElementType();
        }
        this.renderModifier(stringBuilder0, kotlinType1 != null, "vararg");
        if(z2 || z1 && !this.getStartFromName()) {
            this.renderValVarPrefix(variableDescriptor0, stringBuilder0, z2);
        }
        if(z) {
            this.renderName(variableDescriptor0, stringBuilder0, z1);
            stringBuilder0.append(": ");
        }
        stringBuilder0.append(this.renderType((kotlinType1 == null ? kotlinType0 : kotlinType1)));
        this.renderInitializer(variableDescriptor0, stringBuilder0);
        if(this.getVerbose() && kotlinType1 != null) {
            stringBuilder0.append(" /*");
            stringBuilder0.append(this.renderType(kotlinType0));
            stringBuilder0.append("*/");
        }
    }

    private final boolean renderVisibility(DescriptorVisibility descriptorVisibility0, StringBuilder stringBuilder0) {
        if(!this.getModifiers().contains(DescriptorRendererModifier.VISIBILITY)) {
            return false;
        }
        if(this.getNormalizedVisibilities()) {
            descriptorVisibility0 = descriptorVisibility0.normalize();
        }
        if(!this.getRenderDefaultVisibility() && Intrinsics.areEqual(descriptorVisibility0, DescriptorVisibilities.DEFAULT_VISIBILITY)) {
            return false;
        }
        stringBuilder0.append(this.renderKeyword(descriptorVisibility0.getInternalDisplayName()));
        stringBuilder0.append(" ");
        return true;
    }

    private final void renderWhereSuffix(List list0, StringBuilder stringBuilder0) {
        if(!this.getWithoutTypeParameters()) {
            ArrayList arrayList0 = new ArrayList(0);
            for(Object object0: list0) {
                TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)object0;
                List list1 = typeParameterDescriptor0.getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(list1, "typeParameter.upperBounds");
                for(Object object1: CollectionsKt.drop(list1, 1)) {
                    Name name0 = typeParameterDescriptor0.getName();
                    Intrinsics.checkNotNullExpressionValue(name0, "typeParameter.name");
                    Intrinsics.checkNotNullExpressionValue(((KotlinType)object1), "it");
                    arrayList0.add(this.renderName(name0, false) + " : " + this.renderType(((KotlinType)object1)));
                }
            }
            if(!arrayList0.isEmpty()) {
                stringBuilder0.append(" ");
                stringBuilder0.append(this.renderKeyword("where"));
                stringBuilder0.append(" ");
                CollectionsKt.joinTo$default(arrayList0, stringBuilder0, ", ", null, null, 0, null, null, 0x7C, null);
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy0) {
        Intrinsics.checkNotNullParameter(annotationArgumentsRenderingPolicy0, "<set-?>");
        this.options.setAnnotationArgumentsRenderingPolicy(annotationArgumentsRenderingPolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setClassifierNamePolicy(ClassifierNamePolicy classifierNamePolicy0) {
        Intrinsics.checkNotNullParameter(classifierNamePolicy0, "<set-?>");
        this.options.setClassifierNamePolicy(classifierNamePolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setDebugMode(boolean z) {
        this.options.setDebugMode(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setExcludedTypeAnnotationClasses(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "<set-?>");
        this.options.setExcludedTypeAnnotationClasses(set0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setModifiers(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "<set-?>");
        this.options.setModifiers(set0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy parameterNameRenderingPolicy0) {
        Intrinsics.checkNotNullParameter(parameterNameRenderingPolicy0, "<set-?>");
        this.options.setParameterNameRenderingPolicy(parameterNameRenderingPolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setReceiverAfterName(boolean z) {
        this.options.setReceiverAfterName(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setRenderCompanionObjectName(boolean z) {
        this.options.setRenderCompanionObjectName(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setStartFromName(boolean z) {
        this.options.setStartFromName(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setTextFormat(RenderingFormat renderingFormat0) {
        Intrinsics.checkNotNullParameter(renderingFormat0, "<set-?>");
        this.options.setTextFormat(renderingFormat0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setVerbose(boolean z) {
        this.options.setVerbose(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithDefinedIn(boolean z) {
        this.options.setWithDefinedIn(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutSuperTypes(boolean z) {
        this.options.setWithoutSuperTypes(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutTypeParameters(boolean z) {
        this.options.setWithoutTypeParameters(z);
    }

    private final boolean shouldRenderAsPrettyFunctionType(KotlinType kotlinType0) {
        if(FunctionTypesKt.isBuiltinFunctionalType(kotlinType0)) {
            Iterable iterable0 = kotlinType0.getArguments();
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object0: iterable0) {
                    if(((TypeProjection)object0).isStarProjection()) {
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private final boolean shouldRenderParameterNames(boolean z) {
        switch(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererImpl.WhenMappings.$EnumSwitchMapping$1[this.getParameterNameRenderingPolicy().ordinal()]) {
            case 1: {
                return true;
            }
            case 2: {
                return !z;
            }
            case 3: {
                return false;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}

