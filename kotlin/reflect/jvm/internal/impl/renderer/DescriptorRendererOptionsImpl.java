package kotlin.reflect.jvm.internal.impl.renderer;

import java.lang.reflect.Field;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;

public final class DescriptorRendererOptionsImpl implements DescriptorRendererOptions {
    static final KProperty[] $$delegatedProperties;
    private final ReadWriteProperty actualPropertiesInPrimaryConstructor$delegate;
    private final ReadWriteProperty alwaysRenderModifiers$delegate;
    private final ReadWriteProperty annotationArgumentsRenderingPolicy$delegate;
    private final ReadWriteProperty annotationFilter$delegate;
    private final ReadWriteProperty boldOnlyForNamesInHtml$delegate;
    private final ReadWriteProperty classWithPrimaryConstructor$delegate;
    private final ReadWriteProperty classifierNamePolicy$delegate;
    private final ReadWriteProperty debugMode$delegate;
    private final ReadWriteProperty defaultParameterValueRenderer$delegate;
    private final ReadWriteProperty eachAnnotationOnNewLine$delegate;
    private final ReadWriteProperty enhancedTypes$delegate;
    private final ReadWriteProperty excludedAnnotationClasses$delegate;
    private final ReadWriteProperty excludedTypeAnnotationClasses$delegate;
    private final ReadWriteProperty includeAdditionalModifiers$delegate;
    private final ReadWriteProperty includePropertyConstant$delegate;
    private final ReadWriteProperty informativeErrorType$delegate;
    private boolean isLocked;
    private final ReadWriteProperty modifiers$delegate;
    private final ReadWriteProperty normalizedVisibilities$delegate;
    private final ReadWriteProperty overrideRenderingPolicy$delegate;
    private final ReadWriteProperty parameterNameRenderingPolicy$delegate;
    private final ReadWriteProperty parameterNamesInFunctionalTypes$delegate;
    private final ReadWriteProperty presentableUnresolvedTypes$delegate;
    private final ReadWriteProperty propertyAccessorRenderingPolicy$delegate;
    private final ReadWriteProperty receiverAfterName$delegate;
    private final ReadWriteProperty renderCompanionObjectName$delegate;
    private final ReadWriteProperty renderConstructorDelegation$delegate;
    private final ReadWriteProperty renderConstructorKeyword$delegate;
    private final ReadWriteProperty renderDefaultAnnotationArguments$delegate;
    private final ReadWriteProperty renderDefaultModality$delegate;
    private final ReadWriteProperty renderDefaultVisibility$delegate;
    private final ReadWriteProperty renderFunctionContracts$delegate;
    private final ReadWriteProperty renderPrimaryConstructorParametersAsProperties$delegate;
    private final ReadWriteProperty renderTypeExpansions$delegate;
    private final ReadWriteProperty renderUnabbreviatedType$delegate;
    private final ReadWriteProperty secondaryConstructorsAsPrimary$delegate;
    private final ReadWriteProperty startFromDeclarationKeyword$delegate;
    private final ReadWriteProperty startFromName$delegate;
    private final ReadWriteProperty textFormat$delegate;
    private final ReadWriteProperty typeNormalizer$delegate;
    private final ReadWriteProperty uninferredTypeParameterAsName$delegate;
    private final ReadWriteProperty unitReturnType$delegate;
    private final ReadWriteProperty valueParametersHandler$delegate;
    private final ReadWriteProperty verbose$delegate;
    private final ReadWriteProperty withDefinedIn$delegate;
    private final ReadWriteProperty withSourceFileForTopLevel$delegate;
    private final ReadWriteProperty withoutReturnType$delegate;
    private final ReadWriteProperty withoutSuperTypes$delegate;
    private final ReadWriteProperty withoutTypeParameters$delegate;

    static {
        KProperty[] arr_kProperty = new KProperty[0x30];
        Class class0 = DescriptorRendererOptionsImpl.class;
        arr_kProperty[0] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "classifierNamePolicy", "getClassifierNamePolicy()Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;"));
        arr_kProperty[1] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "withDefinedIn", "getWithDefinedIn()Z"));
        arr_kProperty[2] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "withSourceFileForTopLevel", "getWithSourceFileForTopLevel()Z"));
        arr_kProperty[3] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "modifiers", "getModifiers()Ljava/util/Set;"));
        arr_kProperty[4] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "startFromName", "getStartFromName()Z"));
        arr_kProperty[5] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "startFromDeclarationKeyword", "getStartFromDeclarationKeyword()Z"));
        arr_kProperty[6] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "debugMode", "getDebugMode()Z"));
        arr_kProperty[7] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "classWithPrimaryConstructor", "getClassWithPrimaryConstructor()Z"));
        arr_kProperty[8] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "verbose", "getVerbose()Z"));
        arr_kProperty[9] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "unitReturnType", "getUnitReturnType()Z"));
        arr_kProperty[10] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "withoutReturnType", "getWithoutReturnType()Z"));
        arr_kProperty[11] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "enhancedTypes", "getEnhancedTypes()Z"));
        arr_kProperty[12] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "normalizedVisibilities", "getNormalizedVisibilities()Z"));
        arr_kProperty[13] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderDefaultVisibility", "getRenderDefaultVisibility()Z"));
        arr_kProperty[14] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderDefaultModality", "getRenderDefaultModality()Z"));
        arr_kProperty[15] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderConstructorDelegation", "getRenderConstructorDelegation()Z"));
        arr_kProperty[16] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderPrimaryConstructorParametersAsProperties", "getRenderPrimaryConstructorParametersAsProperties()Z"));
        arr_kProperty[17] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "actualPropertiesInPrimaryConstructor", "getActualPropertiesInPrimaryConstructor()Z"));
        arr_kProperty[18] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "uninferredTypeParameterAsName", "getUninferredTypeParameterAsName()Z"));
        arr_kProperty[19] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "includePropertyConstant", "getIncludePropertyConstant()Z"));
        arr_kProperty[20] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "withoutTypeParameters", "getWithoutTypeParameters()Z"));
        arr_kProperty[21] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "withoutSuperTypes", "getWithoutSuperTypes()Z"));
        arr_kProperty[22] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "typeNormalizer", "getTypeNormalizer()Lkotlin/jvm/functions/Function1;"));
        arr_kProperty[23] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "defaultParameterValueRenderer", "getDefaultParameterValueRenderer()Lkotlin/jvm/functions/Function1;"));
        arr_kProperty[24] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "secondaryConstructorsAsPrimary", "getSecondaryConstructorsAsPrimary()Z"));
        arr_kProperty[25] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "overrideRenderingPolicy", "getOverrideRenderingPolicy()Lorg/jetbrains/kotlin/renderer/OverrideRenderingPolicy;"));
        arr_kProperty[26] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "valueParametersHandler", "getValueParametersHandler()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer$ValueParametersHandler;"));
        arr_kProperty[27] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "textFormat", "getTextFormat()Lorg/jetbrains/kotlin/renderer/RenderingFormat;"));
        arr_kProperty[28] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "parameterNameRenderingPolicy", "getParameterNameRenderingPolicy()Lorg/jetbrains/kotlin/renderer/ParameterNameRenderingPolicy;"));
        arr_kProperty[29] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "receiverAfterName", "getReceiverAfterName()Z"));
        arr_kProperty[30] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderCompanionObjectName", "getRenderCompanionObjectName()Z"));
        arr_kProperty[0x1F] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "propertyAccessorRenderingPolicy", "getPropertyAccessorRenderingPolicy()Lorg/jetbrains/kotlin/renderer/PropertyAccessorRenderingPolicy;"));
        arr_kProperty[0x20] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderDefaultAnnotationArguments", "getRenderDefaultAnnotationArguments()Z"));
        arr_kProperty[33] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "eachAnnotationOnNewLine", "getEachAnnotationOnNewLine()Z"));
        arr_kProperty[34] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "excludedAnnotationClasses", "getExcludedAnnotationClasses()Ljava/util/Set;"));
        arr_kProperty[35] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "excludedTypeAnnotationClasses", "getExcludedTypeAnnotationClasses()Ljava/util/Set;"));
        arr_kProperty[36] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "annotationFilter", "getAnnotationFilter()Lkotlin/jvm/functions/Function1;"));
        arr_kProperty[37] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "annotationArgumentsRenderingPolicy", "getAnnotationArgumentsRenderingPolicy()Lorg/jetbrains/kotlin/renderer/AnnotationArgumentsRenderingPolicy;"));
        arr_kProperty[38] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "alwaysRenderModifiers", "getAlwaysRenderModifiers()Z"));
        arr_kProperty[39] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderConstructorKeyword", "getRenderConstructorKeyword()Z"));
        arr_kProperty[40] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderUnabbreviatedType", "getRenderUnabbreviatedType()Z"));
        arr_kProperty[41] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderTypeExpansions", "getRenderTypeExpansions()Z"));
        arr_kProperty[42] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "includeAdditionalModifiers", "getIncludeAdditionalModifiers()Z"));
        arr_kProperty[43] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "parameterNamesInFunctionalTypes", "getParameterNamesInFunctionalTypes()Z"));
        arr_kProperty[44] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "renderFunctionContracts", "getRenderFunctionContracts()Z"));
        arr_kProperty[45] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "presentableUnresolvedTypes", "getPresentableUnresolvedTypes()Z"));
        arr_kProperty[46] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "boldOnlyForNamesInHtml", "getBoldOnlyForNamesInHtml()Z"));
        arr_kProperty[0x2F] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(class0), "informativeErrorType", "getInformativeErrorType()Z"));
        DescriptorRendererOptionsImpl.$$delegatedProperties = arr_kProperty;
    }

    public DescriptorRendererOptionsImpl() {
        this.classifierNamePolicy$delegate = this.property(SOURCE_CODE_QUALIFIED.INSTANCE);
        this.withDefinedIn$delegate = this.property(Boolean.TRUE);
        this.withSourceFileForTopLevel$delegate = this.property(Boolean.TRUE);
        this.modifiers$delegate = this.property(DescriptorRendererModifier.ALL_EXCEPT_ANNOTATIONS);
        this.startFromName$delegate = this.property(Boolean.FALSE);
        this.startFromDeclarationKeyword$delegate = this.property(Boolean.FALSE);
        this.debugMode$delegate = this.property(Boolean.FALSE);
        this.classWithPrimaryConstructor$delegate = this.property(Boolean.FALSE);
        this.verbose$delegate = this.property(Boolean.FALSE);
        this.unitReturnType$delegate = this.property(Boolean.TRUE);
        this.withoutReturnType$delegate = this.property(Boolean.FALSE);
        this.enhancedTypes$delegate = this.property(Boolean.FALSE);
        this.normalizedVisibilities$delegate = this.property(Boolean.FALSE);
        this.renderDefaultVisibility$delegate = this.property(Boolean.TRUE);
        this.renderDefaultModality$delegate = this.property(Boolean.TRUE);
        this.renderConstructorDelegation$delegate = this.property(Boolean.FALSE);
        this.renderPrimaryConstructorParametersAsProperties$delegate = this.property(Boolean.FALSE);
        this.actualPropertiesInPrimaryConstructor$delegate = this.property(Boolean.FALSE);
        this.uninferredTypeParameterAsName$delegate = this.property(Boolean.FALSE);
        this.includePropertyConstant$delegate = this.property(Boolean.FALSE);
        this.withoutTypeParameters$delegate = this.property(Boolean.FALSE);
        this.withoutSuperTypes$delegate = this.property(Boolean.FALSE);
        this.typeNormalizer$delegate = this.property(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2.INSTANCE);
        this.defaultParameterValueRenderer$delegate = this.property(kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2.INSTANCE);
        this.secondaryConstructorsAsPrimary$delegate = this.property(Boolean.TRUE);
        this.overrideRenderingPolicy$delegate = this.property(OverrideRenderingPolicy.RENDER_OPEN);
        this.valueParametersHandler$delegate = this.property(DEFAULT.INSTANCE);
        this.textFormat$delegate = this.property(RenderingFormat.PLAIN);
        this.parameterNameRenderingPolicy$delegate = this.property(ParameterNameRenderingPolicy.ALL);
        this.receiverAfterName$delegate = this.property(Boolean.FALSE);
        this.renderCompanionObjectName$delegate = this.property(Boolean.FALSE);
        this.propertyAccessorRenderingPolicy$delegate = this.property(PropertyAccessorRenderingPolicy.DEBUG);
        this.renderDefaultAnnotationArguments$delegate = this.property(Boolean.FALSE);
        this.eachAnnotationOnNewLine$delegate = this.property(Boolean.FALSE);
        this.excludedAnnotationClasses$delegate = this.property(SetsKt.emptySet());
        this.excludedTypeAnnotationClasses$delegate = this.property(ExcludedTypeAnnotations.INSTANCE.getInternalAnnotationsForResolve());
        this.annotationFilter$delegate = this.property(null);
        this.annotationArgumentsRenderingPolicy$delegate = this.property(AnnotationArgumentsRenderingPolicy.NO_ARGUMENTS);
        this.alwaysRenderModifiers$delegate = this.property(Boolean.FALSE);
        this.renderConstructorKeyword$delegate = this.property(Boolean.TRUE);
        this.renderUnabbreviatedType$delegate = this.property(Boolean.TRUE);
        this.renderTypeExpansions$delegate = this.property(Boolean.FALSE);
        this.includeAdditionalModifiers$delegate = this.property(Boolean.TRUE);
        this.parameterNamesInFunctionalTypes$delegate = this.property(Boolean.TRUE);
        this.renderFunctionContracts$delegate = this.property(Boolean.FALSE);
        this.presentableUnresolvedTypes$delegate = this.property(Boolean.FALSE);
        this.boldOnlyForNamesInHtml$delegate = this.property(Boolean.FALSE);
        this.informativeErrorType$delegate = this.property(Boolean.TRUE);

        final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2();
            }

            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.defaultParameterValueRenderer.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }

            public final String invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                Intrinsics.checkNotNullParameter(valueParameterDescriptor0, "it");
                return "...";
            }
        }


        final class kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2();
            }

            kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptionsImpl.typeNormalizer.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinType)object0));
            }

            public final KotlinType invoke(KotlinType kotlinType0) {
                Intrinsics.checkNotNullParameter(kotlinType0, "it");
                return kotlinType0;
            }
        }

    }

    public final DescriptorRendererOptionsImpl copy() {
        DescriptorRendererOptionsImpl descriptorRendererOptionsImpl0 = new DescriptorRendererOptionsImpl();
        Field[] arr_field = this.getClass().getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(arr_field, "this::class.java.declaredFields");
        for(int v = 0; v < arr_field.length; ++v) {
            Field field0 = arr_field[v];
            if((field0.getModifiers() & 8) == 0) {
                field0.setAccessible(true);
                Object object0 = field0.get(this);
                ObservableProperty observableProperty0 = object0 instanceof ObservableProperty ? ((ObservableProperty)object0) : null;
                if(observableProperty0 != null) {
                    String s = field0.getName();
                    Intrinsics.checkNotNullExpressionValue(s, "field.name");
                    StringsKt.startsWith$default(s, "is", false, 2, null);
                    KDeclarationContainer kDeclarationContainer0 = Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class);
                    String s1 = field0.getName();
                    StringBuilder stringBuilder0 = new StringBuilder("get");
                    String s2 = field0.getName();
                    Intrinsics.checkNotNullExpressionValue(s2, "field.name");
                    if(s2.length() > 0) {
                        String s3 = s2.substring(1);
                        Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
                        s2 = Character.toUpperCase(s2.charAt(0)) + s3;
                    }
                    stringBuilder0.append(s2);
                    field0.set(descriptorRendererOptionsImpl0, descriptorRendererOptionsImpl0.property(observableProperty0.getValue(this, new PropertyReference1Impl(kDeclarationContainer0, s1, stringBuilder0.toString()))));
                }
            }
        }
        return descriptorRendererOptionsImpl0;
    }

    public boolean getActualPropertiesInPrimaryConstructor() {
        return ((Boolean)this.actualPropertiesInPrimaryConstructor$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[17])).booleanValue();
    }

    public boolean getAlwaysRenderModifiers() {
        return ((Boolean)this.alwaysRenderModifiers$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[38])).booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return (AnnotationArgumentsRenderingPolicy)this.annotationArgumentsRenderingPolicy$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[37]);
    }

    public Function1 getAnnotationFilter() {
        return (Function1)this.annotationFilter$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[36]);
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return ((Boolean)this.boldOnlyForNamesInHtml$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[46])).booleanValue();
    }

    public boolean getClassWithPrimaryConstructor() {
        return ((Boolean)this.classWithPrimaryConstructor$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[7])).booleanValue();
    }

    public ClassifierNamePolicy getClassifierNamePolicy() {
        return (ClassifierNamePolicy)this.classifierNamePolicy$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getDebugMode() {
        return ((Boolean)this.debugMode$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[6])).booleanValue();
    }

    public Function1 getDefaultParameterValueRenderer() {
        return (Function1)this.defaultParameterValueRenderer$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[23]);
    }

    public boolean getEachAnnotationOnNewLine() {
        return ((Boolean)this.eachAnnotationOnNewLine$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[33])).booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public boolean getEnhancedTypes() {
        return ((Boolean)this.enhancedTypes$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[11])).booleanValue();
    }

    public Set getExcludedAnnotationClasses() {
        return (Set)this.excludedAnnotationClasses$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[34]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public Set getExcludedTypeAnnotationClasses() {
        return (Set)this.excludedTypeAnnotationClasses$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[35]);
    }

    public boolean getIncludeAdditionalModifiers() {
        return ((Boolean)this.includeAdditionalModifiers$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[42])).booleanValue();
    }

    public boolean getIncludeAnnotationArguments() {
        return DefaultImpls.getIncludeAnnotationArguments(this);
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return DefaultImpls.getIncludeEmptyAnnotationArguments(this);
    }

    public boolean getIncludePropertyConstant() {
        return ((Boolean)this.includePropertyConstant$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[19])).booleanValue();
    }

    public boolean getInformativeErrorType() {
        return ((Boolean)this.informativeErrorType$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[0x2F])).booleanValue();
    }

    public Set getModifiers() {
        return (Set)this.modifiers$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[3]);
    }

    public boolean getNormalizedVisibilities() {
        return ((Boolean)this.normalizedVisibilities$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[12])).booleanValue();
    }

    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return (OverrideRenderingPolicy)this.overrideRenderingPolicy$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[25]);
    }

    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return (ParameterNameRenderingPolicy)this.parameterNameRenderingPolicy$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[28]);
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return ((Boolean)this.parameterNamesInFunctionalTypes$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[43])).booleanValue();
    }

    public boolean getPresentableUnresolvedTypes() {
        return ((Boolean)this.presentableUnresolvedTypes$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[45])).booleanValue();
    }

    public PropertyAccessorRenderingPolicy getPropertyAccessorRenderingPolicy() {
        return (PropertyAccessorRenderingPolicy)this.propertyAccessorRenderingPolicy$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[0x1F]);
    }

    public boolean getReceiverAfterName() {
        return ((Boolean)this.receiverAfterName$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[29])).booleanValue();
    }

    public boolean getRenderCompanionObjectName() {
        return ((Boolean)this.renderCompanionObjectName$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[30])).booleanValue();
    }

    public boolean getRenderConstructorDelegation() {
        return ((Boolean)this.renderConstructorDelegation$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[15])).booleanValue();
    }

    public boolean getRenderConstructorKeyword() {
        return ((Boolean)this.renderConstructorKeyword$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[39])).booleanValue();
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return ((Boolean)this.renderDefaultAnnotationArguments$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[0x20])).booleanValue();
    }

    public boolean getRenderDefaultModality() {
        return ((Boolean)this.renderDefaultModality$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[14])).booleanValue();
    }

    public boolean getRenderDefaultVisibility() {
        return ((Boolean)this.renderDefaultVisibility$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[13])).booleanValue();
    }

    public boolean getRenderPrimaryConstructorParametersAsProperties() {
        return ((Boolean)this.renderPrimaryConstructorParametersAsProperties$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[16])).booleanValue();
    }

    public boolean getRenderTypeExpansions() {
        return ((Boolean)this.renderTypeExpansions$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[41])).booleanValue();
    }

    public boolean getRenderUnabbreviatedType() {
        return ((Boolean)this.renderUnabbreviatedType$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[40])).booleanValue();
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return ((Boolean)this.secondaryConstructorsAsPrimary$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[24])).booleanValue();
    }

    public boolean getStartFromDeclarationKeyword() {
        return ((Boolean)this.startFromDeclarationKeyword$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[5])).booleanValue();
    }

    public boolean getStartFromName() {
        return ((Boolean)this.startFromName$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[4])).booleanValue();
    }

    public RenderingFormat getTextFormat() {
        return (RenderingFormat)this.textFormat$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[27]);
    }

    public Function1 getTypeNormalizer() {
        return (Function1)this.typeNormalizer$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[22]);
    }

    public boolean getUninferredTypeParameterAsName() {
        return ((Boolean)this.uninferredTypeParameterAsName$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[18])).booleanValue();
    }

    public boolean getUnitReturnType() {
        return ((Boolean)this.unitReturnType$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[9])).booleanValue();
    }

    public ValueParametersHandler getValueParametersHandler() {
        return (ValueParametersHandler)this.valueParametersHandler$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[26]);
    }

    public boolean getVerbose() {
        return ((Boolean)this.verbose$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[8])).booleanValue();
    }

    public boolean getWithDefinedIn() {
        return ((Boolean)this.withDefinedIn$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[1])).booleanValue();
    }

    public boolean getWithSourceFileForTopLevel() {
        return ((Boolean)this.withSourceFileForTopLevel$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[2])).booleanValue();
    }

    public boolean getWithoutReturnType() {
        return ((Boolean)this.withoutReturnType$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[10])).booleanValue();
    }

    public boolean getWithoutSuperTypes() {
        return ((Boolean)this.withoutSuperTypes$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[21])).booleanValue();
    }

    public boolean getWithoutTypeParameters() {
        return ((Boolean)this.withoutTypeParameters$delegate.getValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[20])).booleanValue();
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final void lock() {
        this.isLocked = true;
    }

    private final ReadWriteProperty property(Object object0) {
        return new ObservableProperty(this) {
            @Override  // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty kProperty0, Object object0, Object object1) {
                Intrinsics.checkNotNullParameter(kProperty0, "property");
                if(DescriptorRendererOptionsImpl.this.isLocked()) {
                    throw new IllegalStateException("Cannot modify readonly DescriptorRendererOptions");
                }
                return true;
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy0) {
        Intrinsics.checkNotNullParameter(annotationArgumentsRenderingPolicy0, "<set-?>");
        this.annotationArgumentsRenderingPolicy$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[37], annotationArgumentsRenderingPolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setClassifierNamePolicy(ClassifierNamePolicy classifierNamePolicy0) {
        Intrinsics.checkNotNullParameter(classifierNamePolicy0, "<set-?>");
        this.classifierNamePolicy$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[0], classifierNamePolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setDebugMode(boolean z) {
        this.debugMode$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[6], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setExcludedTypeAnnotationClasses(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "<set-?>");
        this.excludedTypeAnnotationClasses$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[35], set0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setModifiers(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "<set-?>");
        this.modifiers$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[3], set0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy parameterNameRenderingPolicy0) {
        Intrinsics.checkNotNullParameter(parameterNameRenderingPolicy0, "<set-?>");
        this.parameterNameRenderingPolicy$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[28], parameterNameRenderingPolicy0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setReceiverAfterName(boolean z) {
        this.receiverAfterName$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[29], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setRenderCompanionObjectName(boolean z) {
        this.renderCompanionObjectName$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[30], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setStartFromName(boolean z) {
        this.startFromName$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[4], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setTextFormat(RenderingFormat renderingFormat0) {
        Intrinsics.checkNotNullParameter(renderingFormat0, "<set-?>");
        this.textFormat$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[27], renderingFormat0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setVerbose(boolean z) {
        this.verbose$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[8], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithDefinedIn(boolean z) {
        this.withDefinedIn$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[1], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutSuperTypes(boolean z) {
        this.withoutSuperTypes$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[21], Boolean.valueOf(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions
    public void setWithoutTypeParameters(boolean z) {
        this.withoutTypeParameters$delegate.setValue(this, DescriptorRendererOptionsImpl.$$delegatedProperties[20], Boolean.valueOf(z));
    }
}

