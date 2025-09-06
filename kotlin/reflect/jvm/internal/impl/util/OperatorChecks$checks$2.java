package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

final class OperatorChecks.checks.2 extends Lambda implements Function1 {
    public static final OperatorChecks.checks.2 INSTANCE;

    static {
        OperatorChecks.checks.2.INSTANCE = new OperatorChecks.checks.2();
    }

    OperatorChecks.checks.2() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((FunctionDescriptor)object0));
    }

    public final String invoke(FunctionDescriptor functionDescriptor0) {
        boolean z;
        Intrinsics.checkNotNullParameter(functionDescriptor0, "$this$$receiver");
        DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "containingDeclaration");
        if(OperatorChecks.checks.2.invoke$isAny(declarationDescriptor0)) {
            z = true;
        }
        else {
            Collection collection0 = functionDescriptor0.getOverriddenDescriptors();
            Intrinsics.checkNotNullExpressionValue(collection0, "overriddenDescriptors");
            if(!collection0.isEmpty()) {
                for(Object object0: collection0) {
                    DeclarationDescriptor declarationDescriptor1 = ((FunctionDescriptor)object0).getContainingDeclaration();
                    Intrinsics.checkNotNullExpressionValue(declarationDescriptor1, "it.containingDeclaration");
                    if(!OperatorChecks.checks.2.invoke$isAny(declarationDescriptor1)) {
                        continue;
                    }
                    z = true;
                    goto label_21;
                }
            }
            z = DescriptorUtilKt.isTypedEqualsInValueClass(functionDescriptor0);
        }
    label_21:
        if(!z) {
            StringBuilder stringBuilder0 = new StringBuilder("must override \'\'equals()\'\' in Any");
            DeclarationDescriptor declarationDescriptor2 = functionDescriptor0.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(declarationDescriptor2, "containingDeclaration");
            if(InlineClassesUtilsKt.isValueClass(declarationDescriptor2)) {
                DeclarationDescriptor declarationDescriptor3 = functionDescriptor0.getContainingDeclaration();
                Intrinsics.checkNotNull(declarationDescriptor3, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                SimpleType simpleType0 = ((ClassDescriptor)declarationDescriptor3).getDefaultType();
                Intrinsics.checkNotNullExpressionValue(simpleType0, "containingDeclaration as…ssDescriptor).defaultType");
                KotlinType kotlinType0 = TypeUtilsKt.replaceArgumentsWithStarProjections(simpleType0);
                stringBuilder0.append(" or define \'\'equals(other: " + DescriptorRenderer.SHORT_NAMES_IN_TYPES.renderType(kotlinType0) + "): Boolean\'\'");
            }
            String s = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
            return s;
        }
        return null;
    }

    // 去混淆评级： 低(20)
    private static final boolean invoke$isAny(DeclarationDescriptor declarationDescriptor0) {
        return declarationDescriptor0 instanceof ClassDescriptor && KotlinBuiltIns.isAny(((ClassDescriptor)declarationDescriptor0));
    }
}

