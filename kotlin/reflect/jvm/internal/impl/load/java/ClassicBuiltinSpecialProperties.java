package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class ClassicBuiltinSpecialProperties {
    public static final ClassicBuiltinSpecialProperties INSTANCE;

    static {
        ClassicBuiltinSpecialProperties.INSTANCE = new ClassicBuiltinSpecialProperties();
    }

    public final String getBuiltinSpecialPropertyGetterName(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        KotlinBuiltIns.isBuiltIn(callableMemberDescriptor0);
        CallableMemberDescriptor callableMemberDescriptor1 = DescriptorUtilsKt.firstOverridden$default(DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0), false, kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1.INSTANCE, 1, null);
        if(callableMemberDescriptor1 == null) {
            return null;
        }
        Name name0 = (Name)BuiltinSpecialProperties.INSTANCE.getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP().get(DescriptorUtilsKt.getFqNameSafe(callableMemberDescriptor1));
        return name0 == null ? null : name0.asString();

        final class kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties.getBuiltinSpecialPropertyGetterName.descriptor.1() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return Boolean.valueOf(ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(callableMemberDescriptor0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }

    }

    public final boolean hasBuiltinSpecialPropertyFqName(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "callableMemberDescriptor");
        return BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(callableMemberDescriptor0.getName()) ? this.hasBuiltinSpecialPropertyFqNameImpl(callableMemberDescriptor0) : false;
    }

    private final boolean hasBuiltinSpecialPropertyFqNameImpl(CallableMemberDescriptor callableMemberDescriptor0) {
        if(CollectionsKt.contains(BuiltinSpecialProperties.INSTANCE.getSPECIAL_FQ_NAMES(), DescriptorUtilsKt.fqNameOrNull(callableMemberDescriptor0)) && callableMemberDescriptor0.getValueParameters().isEmpty()) {
            return true;
        }
        if(!KotlinBuiltIns.isBuiltIn(callableMemberDescriptor0)) {
            return false;
        }
        Collection collection0 = callableMemberDescriptor0.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(collection0, "overriddenDescriptors");
        if(collection0.isEmpty()) {
            return false;
        }
        for(Object object0: collection0) {
            Intrinsics.checkNotNullExpressionValue(((CallableMemberDescriptor)object0), "it");
            if(ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(((CallableMemberDescriptor)object0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }
}

