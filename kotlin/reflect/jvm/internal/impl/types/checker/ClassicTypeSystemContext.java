package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.DefinitelyNotNullType;
import kotlin.reflect.jvm.internal.impl.types.DynamicType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.NotNullTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.SimpleTypeWithEnhancement;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState.SupertypesPolicy;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeSystemCommonBackendContext;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.RawTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContextKt;
import kotlin.reflect.jvm.internal.impl.types.model.TypeSystemInferenceExtensionContext;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariableTypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public interface ClassicTypeSystemContext extends TypeSystemCommonBackendContext, TypeSystemInferenceExtensionContext {
    public static final class DefaultImpls {
        public static boolean areEqualTypeConstructors(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0, TypeConstructorMarker typeConstructorMarker1) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "c1");
            Intrinsics.checkNotNullParameter(typeConstructorMarker1, "c2");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            if(!(typeConstructorMarker1 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker1 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker1.getClass())).toString());
            }
            return Intrinsics.areEqual(typeConstructorMarker0, typeConstructorMarker1);
        }

        public static int argumentsCount(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return ((KotlinType)kotlinTypeMarker0).getArguments().size();
        }

        public static TypeArgumentListMarker asArgumentList(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return (TypeArgumentListMarker)simpleTypeMarker0;
        }

        public static CapturedTypeMarker asCapturedType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            if(simpleTypeMarker0 instanceof SimpleTypeWithEnhancement) {
                return classicTypeSystemContext0.asCapturedType(((SimpleTypeWithEnhancement)simpleTypeMarker0).getOrigin());
            }
            return simpleTypeMarker0 instanceof NewCapturedType ? ((NewCapturedType)simpleTypeMarker0) : null;
        }

        public static DefinitelyNotNullTypeMarker asDefinitelyNotNullType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return simpleTypeMarker0 instanceof DefinitelyNotNullType ? ((DefinitelyNotNullType)simpleTypeMarker0) : null;
        }

        public static DynamicTypeMarker asDynamicType(ClassicTypeSystemContext classicTypeSystemContext0, FlexibleTypeMarker flexibleTypeMarker0) {
            Intrinsics.checkNotNullParameter(flexibleTypeMarker0, "$receiver");
            if(!(flexibleTypeMarker0 instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + flexibleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(flexibleTypeMarker0.getClass())).toString());
            }
            return flexibleTypeMarker0 instanceof DynamicType ? ((DynamicType)flexibleTypeMarker0) : null;
        }

        public static FlexibleTypeMarker asFlexibleType(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            UnwrappedType unwrappedType0 = ((KotlinType)kotlinTypeMarker0).unwrap();
            return unwrappedType0 instanceof FlexibleType ? ((FlexibleType)unwrappedType0) : null;
        }

        public static RawTypeMarker asRawType(ClassicTypeSystemContext classicTypeSystemContext0, FlexibleTypeMarker flexibleTypeMarker0) {
            Intrinsics.checkNotNullParameter(flexibleTypeMarker0, "$receiver");
            if(!(flexibleTypeMarker0 instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + flexibleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(flexibleTypeMarker0.getClass())).toString());
            }
            return flexibleTypeMarker0 instanceof RawType ? ((RawType)flexibleTypeMarker0) : null;
        }

        public static SimpleTypeMarker asSimpleType(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            UnwrappedType unwrappedType0 = ((KotlinType)kotlinTypeMarker0).unwrap();
            return unwrappedType0 instanceof SimpleType ? ((SimpleType)unwrappedType0) : null;
        }

        public static TypeArgumentMarker asTypeArgument(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return TypeUtilsKt.asTypeProjection(((KotlinType)kotlinTypeMarker0));
        }

        public static SimpleTypeMarker captureFromArguments(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0, CaptureStatus captureStatus0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "type");
            Intrinsics.checkNotNullParameter(captureStatus0, "status");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return NewCapturedTypeKt.captureFromArguments(((SimpleType)simpleTypeMarker0), captureStatus0);
        }

        public static CaptureStatus captureStatus(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeMarker capturedTypeMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeMarker0, "$receiver");
            if(!(capturedTypeMarker0 instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + capturedTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(capturedTypeMarker0.getClass())).toString());
            }
            return ((NewCapturedType)capturedTypeMarker0).getCaptureStatus();
        }

        public static KotlinTypeMarker createFlexibleType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "lowerBound");
            Intrinsics.checkNotNullParameter(simpleTypeMarker1, "upperBound");
            if(!(simpleTypeMarker0 instanceof SimpleType) || !(simpleTypeMarker1 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + classicTypeSystemContext0 + ", " + Reflection.getOrCreateKotlinClass(classicTypeSystemContext0.getClass())).toString());
            }
            return KotlinTypeFactory.flexibleType(((SimpleType)simpleTypeMarker0), ((SimpleType)simpleTypeMarker1));
        }

        public static TypeArgumentMarker getArgument(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0, int v) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return (TypeArgumentMarker)((KotlinType)kotlinTypeMarker0).getArguments().get(v);
        }

        public static List getArguments(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return ((KotlinType)kotlinTypeMarker0).getArguments();
        }

        public static FqNameUnsafe getClassFqNameUnsafe(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            return DescriptorUtilsKt.getFqNameUnsafe(((ClassDescriptor)classifierDescriptor0));
        }

        public static TypeParameterMarker getParameter(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0, int v) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            Object object0 = ((TypeConstructor)typeConstructorMarker0).getParameters().get(v);
            Intrinsics.checkNotNullExpressionValue(object0, "this.parameters[index]");
            return (TypeParameterMarker)object0;
        }

        public static List getParameters(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            List list0 = ((TypeConstructor)typeConstructorMarker0).getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "this.parameters");
            return list0;
        }

        public static PrimitiveType getPrimitiveArrayType(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            return KotlinBuiltIns.getPrimitiveArrayType(((ClassDescriptor)classifierDescriptor0));
        }

        public static PrimitiveType getPrimitiveType(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            Intrinsics.checkNotNull(classifierDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            return KotlinBuiltIns.getPrimitiveType(((ClassDescriptor)classifierDescriptor0));
        }

        public static KotlinTypeMarker getRepresentativeUpperBound(ClassicTypeSystemContext classicTypeSystemContext0, TypeParameterMarker typeParameterMarker0) {
            Intrinsics.checkNotNullParameter(typeParameterMarker0, "$receiver");
            if(!(typeParameterMarker0 instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeParameterMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeParameterMarker0.getClass())).toString());
            }
            return TypeUtilsKt.getRepresentativeUpperBound(((TypeParameterDescriptor)typeParameterMarker0));
        }

        public static KotlinTypeMarker getType(ClassicTypeSystemContext classicTypeSystemContext0, TypeArgumentMarker typeArgumentMarker0) {
            Intrinsics.checkNotNullParameter(typeArgumentMarker0, "$receiver");
            if(!(typeArgumentMarker0 instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeArgumentMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeArgumentMarker0.getClass())).toString());
            }
            return ((TypeProjection)typeArgumentMarker0).getType().unwrap();
        }

        public static TypeParameterMarker getTypeParameter(ClassicTypeSystemContext classicTypeSystemContext0, TypeVariableTypeConstructorMarker typeVariableTypeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeVariableTypeConstructorMarker0, "$receiver");
            if(!(typeVariableTypeConstructorMarker0 instanceof NewTypeVariableConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeVariableTypeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeVariableTypeConstructorMarker0.getClass())).toString());
            }
            return ((NewTypeVariableConstructor)typeVariableTypeConstructorMarker0).getOriginalTypeParameter();
        }

        public static TypeParameterMarker getTypeParameterClassifier(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            return classifierDescriptor0 instanceof TypeParameterDescriptor ? ((TypeParameterDescriptor)classifierDescriptor0) : null;
        }

        public static KotlinTypeMarker getUnsubstitutedUnderlyingType(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return InlineClassesUtilsKt.unsubstitutedUnderlyingType(((KotlinType)kotlinTypeMarker0));
        }

        public static List getUpperBounds(ClassicTypeSystemContext classicTypeSystemContext0, TypeParameterMarker typeParameterMarker0) {
            Intrinsics.checkNotNullParameter(typeParameterMarker0, "$receiver");
            if(!(typeParameterMarker0 instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeParameterMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeParameterMarker0.getClass())).toString());
            }
            List list0 = ((TypeParameterDescriptor)typeParameterMarker0).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(list0, "this.upperBounds");
            return list0;
        }

        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext0, TypeArgumentMarker typeArgumentMarker0) {
            Intrinsics.checkNotNullParameter(typeArgumentMarker0, "$receiver");
            if(!(typeArgumentMarker0 instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeArgumentMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeArgumentMarker0.getClass())).toString());
            }
            Variance variance0 = ((TypeProjection)typeArgumentMarker0).getProjectionKind();
            Intrinsics.checkNotNullExpressionValue(variance0, "this.projectionKind");
            return TypeSystemContextKt.convertVariance(variance0);
        }

        public static TypeVariance getVariance(ClassicTypeSystemContext classicTypeSystemContext0, TypeParameterMarker typeParameterMarker0) {
            Intrinsics.checkNotNullParameter(typeParameterMarker0, "$receiver");
            if(!(typeParameterMarker0 instanceof TypeParameterDescriptor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeParameterMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeParameterMarker0.getClass())).toString());
            }
            Variance variance0 = ((TypeParameterDescriptor)typeParameterMarker0).getVariance();
            Intrinsics.checkNotNullExpressionValue(variance0, "this.variance");
            return TypeSystemContextKt.convertVariance(variance0);
        }

        public static boolean hasAnnotation(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0, FqName fqName0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return ((KotlinType)kotlinTypeMarker0).getAnnotations().hasAnnotation(fqName0);
        }

        public static boolean hasRecursiveBounds(ClassicTypeSystemContext classicTypeSystemContext0, TypeParameterMarker typeParameterMarker0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeParameterMarker0, "$receiver");
            if(!(typeParameterMarker0 instanceof TypeParameterDescriptor) || !(typeConstructorMarker0 == null ? true : typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeParameterMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeParameterMarker0.getClass())).toString());
            }
            return TypeUtilsKt.hasTypeParameterRecursiveBounds$default(((TypeParameterDescriptor)typeParameterMarker0), ((TypeConstructor)typeConstructorMarker0), null, 4, null);
        }

        public static boolean identicalArguments(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0, SimpleTypeMarker simpleTypeMarker1) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "a");
            Intrinsics.checkNotNullParameter(simpleTypeMarker1, "b");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            if(!(simpleTypeMarker1 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker1 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker1.getClass())).toString());
            }
            return ((SimpleType)simpleTypeMarker0).getArguments() == ((SimpleType)simpleTypeMarker1).getArguments();
        }

        public static KotlinTypeMarker intersectTypes(ClassicTypeSystemContext classicTypeSystemContext0, List list0) {
            Intrinsics.checkNotNullParameter(list0, "types");
            return IntersectionTypeKt.intersectTypes(list0);
        }

        public static boolean isAnyConstructor(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return KotlinBuiltIns.isTypeConstructorForGivenClass(((TypeConstructor)typeConstructorMarker0), FqNames.any);
        }

        public static boolean isClassTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor() instanceof ClassDescriptor;
        }

        public static boolean isCommonFinalClassConstructor(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
            return classDescriptor0 == null ? false : ModalityUtilsKt.isFinalClass(classDescriptor0) && classDescriptor0.getKind() != ClassKind.ENUM_ENTRY && classDescriptor0.getKind() != ClassKind.ANNOTATION_CLASS;
        }

        public static boolean isDenotable(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return ((TypeConstructor)typeConstructorMarker0).isDenotable();
        }

        public static boolean isError(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return KotlinTypeKt.isError(((KotlinType)kotlinTypeMarker0));
        }

        public static boolean isInlineClass(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            ValueClassRepresentation valueClassRepresentation0 = null;
            ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
            if(classDescriptor0 != null) {
                valueClassRepresentation0 = classDescriptor0.getValueClassRepresentation();
            }
            return valueClassRepresentation0 instanceof InlineClassRepresentation;
        }

        public static boolean isIntegerLiteralTypeConstructor(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return typeConstructorMarker0 instanceof IntegerLiteralTypeConstructor;
        }

        public static boolean isIntersection(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return typeConstructorMarker0 instanceof IntersectionTypeConstructor;
        }

        public static boolean isMarkedNullable(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return ((SimpleType)simpleTypeMarker0).isMarkedNullable();
        }

        public static boolean isNotNullTypeParameter(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            return kotlinTypeMarker0 instanceof NotNullTypeParameter;
        }

        public static boolean isNothingConstructor(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return KotlinBuiltIns.isTypeConstructorForGivenClass(((TypeConstructor)typeConstructorMarker0), FqNames.nothing);
        }

        public static boolean isNullableType(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return TypeUtils.isNullableType(((KotlinType)kotlinTypeMarker0));
        }

        public static boolean isOldCapturedType(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeMarker capturedTypeMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeMarker0, "$receiver");
            return capturedTypeMarker0 instanceof CapturedType;
        }

        public static boolean isPrimitiveType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof KotlinType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return KotlinBuiltIns.isPrimitiveType(((KotlinType)simpleTypeMarker0));
        }

        public static boolean isProjectionNotNull(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeMarker capturedTypeMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeMarker0, "$receiver");
            if(!(capturedTypeMarker0 instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + capturedTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(capturedTypeMarker0.getClass())).toString());
            }
            return ((NewCapturedType)capturedTypeMarker0).isProjectionNotNull();
        }

        // 去混淆评级： 低(20)
        public static boolean isSingleClassifierType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return !KotlinTypeKt.isError(((KotlinType)simpleTypeMarker0)) && !(((SimpleType)simpleTypeMarker0).getConstructor().getDeclarationDescriptor() instanceof TypeAliasDescriptor) && (((SimpleType)simpleTypeMarker0).getConstructor().getDeclarationDescriptor() != null || simpleTypeMarker0 instanceof CapturedType || simpleTypeMarker0 instanceof NewCapturedType || simpleTypeMarker0 instanceof DefinitelyNotNullType || ((SimpleType)simpleTypeMarker0).getConstructor() instanceof IntegerLiteralTypeConstructor || DefaultImpls.isSingleClassifierTypeWithEnhancement(classicTypeSystemContext0, simpleTypeMarker0));
        }

        // 去混淆评级： 低(20)
        private static boolean isSingleClassifierTypeWithEnhancement(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            return simpleTypeMarker0 instanceof SimpleTypeWithEnhancement && classicTypeSystemContext0.isSingleClassifierType(((SimpleTypeWithEnhancement)simpleTypeMarker0).getOrigin());
        }

        public static boolean isStarProjection(ClassicTypeSystemContext classicTypeSystemContext0, TypeArgumentMarker typeArgumentMarker0) {
            Intrinsics.checkNotNullParameter(typeArgumentMarker0, "$receiver");
            if(!(typeArgumentMarker0 instanceof TypeProjection)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeArgumentMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeArgumentMarker0.getClass())).toString());
            }
            return ((TypeProjection)typeArgumentMarker0).isStarProjection();
        }

        public static boolean isStubType(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return TypeUtilsKt.isStubType(((KotlinType)simpleTypeMarker0));
        }

        public static boolean isStubTypeForBuilderInference(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return TypeUtilsKt.isStubTypeForBuilderInference(((KotlinType)simpleTypeMarker0));
        }

        public static boolean isTypeVariableType(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            return kotlinTypeMarker0 instanceof UnwrappedType && ((UnwrappedType)kotlinTypeMarker0).getConstructor() instanceof NewTypeVariableConstructor;
        }

        public static boolean isUnderKotlinPackage(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            ClassifierDescriptor classifierDescriptor0 = ((TypeConstructor)typeConstructorMarker0).getDeclarationDescriptor();
            return classifierDescriptor0 != null && KotlinBuiltIns.isUnderKotlinPackage(classifierDescriptor0);
        }

        public static SimpleTypeMarker lowerBound(ClassicTypeSystemContext classicTypeSystemContext0, FlexibleTypeMarker flexibleTypeMarker0) {
            Intrinsics.checkNotNullParameter(flexibleTypeMarker0, "$receiver");
            if(!(flexibleTypeMarker0 instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + flexibleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(flexibleTypeMarker0.getClass())).toString());
            }
            return ((FlexibleType)flexibleTypeMarker0).getLowerBound();
        }

        public static KotlinTypeMarker lowerType(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeMarker capturedTypeMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeMarker0, "$receiver");
            if(!(capturedTypeMarker0 instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + capturedTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(capturedTypeMarker0.getClass())).toString());
            }
            return ((NewCapturedType)capturedTypeMarker0).getLowerType();
        }

        public static KotlinTypeMarker makeDefinitelyNotNullOrNotNull(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(!(kotlinTypeMarker0 instanceof UnwrappedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + kotlinTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(kotlinTypeMarker0.getClass())).toString());
            }
            return ClassicTypeSystemContextKt.access$makeDefinitelyNotNullOrNotNullInternal(((UnwrappedType)kotlinTypeMarker0));
        }

        public static TypeCheckerState newTypeCheckerState(ClassicTypeSystemContext classicTypeSystemContext0, boolean z, boolean z1) {
            return ClassicTypeCheckerStateKt.createClassicTypeCheckerState$default(z, z1, classicTypeSystemContext0, null, null, 24, null);
        }

        public static SimpleTypeMarker original(ClassicTypeSystemContext classicTypeSystemContext0, DefinitelyNotNullTypeMarker definitelyNotNullTypeMarker0) {
            Intrinsics.checkNotNullParameter(definitelyNotNullTypeMarker0, "$receiver");
            if(!(definitelyNotNullTypeMarker0 instanceof DefinitelyNotNullType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + definitelyNotNullTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(definitelyNotNullTypeMarker0.getClass())).toString());
            }
            return ((DefinitelyNotNullType)definitelyNotNullTypeMarker0).getOriginal();
        }

        public static int parametersCount(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            return ((TypeConstructor)typeConstructorMarker0).getParameters().size();
        }

        public static Collection possibleIntegerTypes(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            TypeConstructorMarker typeConstructorMarker0 = classicTypeSystemContext0.typeConstructor(simpleTypeMarker0);
            if(!(typeConstructorMarker0 instanceof IntegerLiteralTypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return ((IntegerLiteralTypeConstructor)typeConstructorMarker0).getPossibleTypes();
        }

        public static TypeArgumentMarker projection(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeConstructorMarker capturedTypeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeConstructorMarker0, "$receiver");
            if(!(capturedTypeConstructorMarker0 instanceof NewCapturedTypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + capturedTypeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(capturedTypeConstructorMarker0.getClass())).toString());
            }
            return ((NewCapturedTypeConstructor)capturedTypeConstructorMarker0).getProjection();
        }

        public static SupertypesPolicy substitutionSupertypePolicy(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "type");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return new ClassicTypeSystemContext.substitutionSupertypePolicy.2(classicTypeSystemContext0, TypeConstructorSubstitution.Companion.create(((KotlinType)simpleTypeMarker0)).buildSubstitutor());
        }

        public static Collection supertypes(ClassicTypeSystemContext classicTypeSystemContext0, TypeConstructorMarker typeConstructorMarker0) {
            Intrinsics.checkNotNullParameter(typeConstructorMarker0, "$receiver");
            if(!(typeConstructorMarker0 instanceof TypeConstructor)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + typeConstructorMarker0 + ", " + Reflection.getOrCreateKotlinClass(typeConstructorMarker0.getClass())).toString());
            }
            Collection collection0 = ((TypeConstructor)typeConstructorMarker0).getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collection0, "this.supertypes");
            return collection0;
        }

        public static CapturedTypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext0, CapturedTypeMarker capturedTypeMarker0) {
            Intrinsics.checkNotNullParameter(capturedTypeMarker0, "$receiver");
            if(!(capturedTypeMarker0 instanceof NewCapturedType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + capturedTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(capturedTypeMarker0.getClass())).toString());
            }
            return ((NewCapturedType)capturedTypeMarker0).getConstructor();
        }

        public static TypeConstructorMarker typeConstructor(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return ((SimpleType)simpleTypeMarker0).getConstructor();
        }

        public static SimpleTypeMarker upperBound(ClassicTypeSystemContext classicTypeSystemContext0, FlexibleTypeMarker flexibleTypeMarker0) {
            Intrinsics.checkNotNullParameter(flexibleTypeMarker0, "$receiver");
            if(!(flexibleTypeMarker0 instanceof FlexibleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + flexibleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(flexibleTypeMarker0.getClass())).toString());
            }
            return ((FlexibleType)flexibleTypeMarker0).getUpperBound();
        }

        public static KotlinTypeMarker withNullability(ClassicTypeSystemContext classicTypeSystemContext0, KotlinTypeMarker kotlinTypeMarker0, boolean z) {
            Intrinsics.checkNotNullParameter(kotlinTypeMarker0, "$receiver");
            if(kotlinTypeMarker0 instanceof SimpleTypeMarker) {
                return classicTypeSystemContext0.withNullability(((SimpleTypeMarker)kotlinTypeMarker0), z);
            }
            if(!(kotlinTypeMarker0 instanceof FlexibleTypeMarker)) {
                throw new IllegalStateException("sealed");
            }
            return classicTypeSystemContext0.createFlexibleType(classicTypeSystemContext0.withNullability(classicTypeSystemContext0.lowerBound(((FlexibleTypeMarker)kotlinTypeMarker0)), z), classicTypeSystemContext0.withNullability(classicTypeSystemContext0.upperBound(((FlexibleTypeMarker)kotlinTypeMarker0)), z));
        }

        public static SimpleTypeMarker withNullability(ClassicTypeSystemContext classicTypeSystemContext0, SimpleTypeMarker simpleTypeMarker0, boolean z) {
            Intrinsics.checkNotNullParameter(simpleTypeMarker0, "$receiver");
            if(!(simpleTypeMarker0 instanceof SimpleType)) {
                throw new IllegalArgumentException(("ClassicTypeSystemContext couldn\'t handle: " + simpleTypeMarker0 + ", " + Reflection.getOrCreateKotlinClass(simpleTypeMarker0.getClass())).toString());
            }
            return ((SimpleType)simpleTypeMarker0).makeNullableAsSpecified(z);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    CapturedTypeMarker asCapturedType(SimpleTypeMarker arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    SimpleTypeMarker asSimpleType(KotlinTypeMarker arg1);

    KotlinTypeMarker createFlexibleType(SimpleTypeMarker arg1, SimpleTypeMarker arg2);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    boolean isSingleClassifierType(SimpleTypeMarker arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    SimpleTypeMarker lowerBound(FlexibleTypeMarker arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    TypeConstructorMarker typeConstructor(SimpleTypeMarker arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    SimpleTypeMarker upperBound(FlexibleTypeMarker arg1);

    @Override  // kotlin.reflect.jvm.internal.impl.types.model.TypeSystemContext
    SimpleTypeMarker withNullability(SimpleTypeMarker arg1, boolean arg2);
}

