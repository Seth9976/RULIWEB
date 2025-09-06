package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.AbstractAnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.PossiblyExternalAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemInferenceExtensionContext;

final class SignatureParts extends AbstractSignatureParts {
    private final AnnotationQualifierApplicabilityType containerApplicabilityType;
    private final LazyJavaResolverContext containerContext;
    private final boolean isCovariant;
    private final boolean skipRawTypeArguments;
    private final Annotated typeContainer;

    public SignatureParts(Annotated annotated0, boolean z, LazyJavaResolverContext lazyJavaResolverContext0, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0, boolean z1) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "containerContext");
        Intrinsics.checkNotNullParameter(annotationQualifierApplicabilityType0, "containerApplicabilityType");
        super();
        this.typeContainer = annotated0;
        this.isCovariant = z;
        this.containerContext = lazyJavaResolverContext0;
        this.containerApplicabilityType = annotationQualifierApplicabilityType0;
        this.skipRawTypeArguments = z1;
    }

    public SignatureParts(Annotated annotated0, boolean z, LazyJavaResolverContext lazyJavaResolverContext0, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0, boolean z1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(annotated0, z, lazyJavaResolverContext0, annotationQualifierApplicabilityType0, ((v & 16) == 0 ? z1 : false));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean forceWarning(Object object0, KotlinTypeMarker kotlinTypeMarker0) {
        return this.forceWarning(((AnnotationDescriptor)object0), kotlinTypeMarker0);
    }

    // 去混淆评级： 低(40)
    public boolean forceWarning(AnnotationDescriptor annotationDescriptor0, KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(annotationDescriptor0, "<this>");
        return annotationDescriptor0 instanceof PossiblyExternalAnnotationDescriptor && ((PossiblyExternalAnnotationDescriptor)annotationDescriptor0).isIdeExternalAnnotation() || annotationDescriptor0 instanceof LazyJavaAnnotationDescriptor && !this.getEnableImprovementsInStrictMode() && (((LazyJavaAnnotationDescriptor)annotationDescriptor0).isFreshlySupportedTypeUseAnnotation() || this.getContainerApplicabilityType() == AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS) || kotlinTypeMarker0 != null && KotlinBuiltIns.isPrimitiveArray(((KotlinType)kotlinTypeMarker0)) && this.getAnnotationTypeQualifierResolver().isTypeUseAnnotation(annotationDescriptor0) && !this.containerContext.getComponents().getSettings().getEnhancePrimitiveArrays();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public AbstractAnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver() {
        return this.getAnnotationTypeQualifierResolver();
    }

    public AnnotationTypeQualifierResolver getAnnotationTypeQualifierResolver() {
        return this.containerContext.getComponents().getAnnotationTypeQualifierResolver();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public Iterable getAnnotations(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return ((KotlinType)kotlinTypeMarker0).getAnnotations();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public Iterable getContainerAnnotations() {
        Annotated annotated0 = this.typeContainer;
        if(annotated0 != null) {
            Annotations annotations0 = annotated0.getAnnotations();
            if(annotations0 != null) {
                return annotations0;
            }
        }
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public AnnotationQualifierApplicabilityType getContainerApplicabilityType() {
        return this.containerApplicabilityType;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public JavaTypeQualifiersByElementType getContainerDefaultTypeQualifiers() {
        return this.containerContext.getDefaultTypeQualifiers();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean getContainerIsVarargParameter() {
        return this.typeContainer instanceof ValueParameterDescriptor && ((ValueParameterDescriptor)this.typeContainer).getVarargElementType() != null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean getEnableImprovementsInStrictMode() {
        return this.containerContext.getComponents().getSettings().getTypeEnhancementImprovementsInStrictMode();
    }

    public KotlinType getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return TypeWithEnhancementKt.getEnhancement(((KotlinType)kotlinTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public KotlinTypeMarker getEnhancedForWarnings(KotlinTypeMarker kotlinTypeMarker0) {
        return this.getEnhancedForWarnings(kotlinTypeMarker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public FqNameUnsafe getFqNameUnsafe(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        ClassDescriptor classDescriptor0 = TypeUtils.getClassDescriptor(((KotlinType)kotlinTypeMarker0));
        return classDescriptor0 == null ? null : DescriptorUtils.getFqName(classDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean getSkipRawTypeArguments() {
        return this.skipRawTypeArguments;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public TypeSystemContext getTypeSystem() {
        return this.getTypeSystem();
    }

    public TypeSystemInferenceExtensionContext getTypeSystem() {
        return SimpleClassicTypeSystemContext.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean isArrayOrPrimitiveArray(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return KotlinBuiltIns.isArrayOrPrimitiveArray(((KotlinType)kotlinTypeMarker0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean isCovariant() {
        return this.isCovariant;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean isEqual(KotlinTypeMarker kotlinTypeMarker0, KotlinTypeMarker kotlinTypeMarker1) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinTypeMarker1, "other");
        return this.containerContext.getComponents().getKotlinTypeChecker().equalTypes(((KotlinType)kotlinTypeMarker0), ((KotlinType)kotlinTypeMarker1));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean isFromJava(TypeParameterMarker typeParameterMarker0) {
        Intrinsics.checkNotNullParameter(typeParameterMarker0, "<this>");
        return typeParameterMarker0 instanceof LazyJavaTypeParameterDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.AbstractSignatureParts
    public boolean isNotNullTypeParameterCompat(KotlinTypeMarker kotlinTypeMarker0) {
        Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "<this>");
        return ((KotlinType)kotlinTypeMarker0).unwrap() instanceof NotNullTypeParameterImpl;
    }
}

