package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;

public final class AbstractBinaryClassAnnotationLoaderKt {
    public static final MemberSignature getPropertySignature(Property protoBuf$Property0, NameResolver nameResolver0, TypeTable typeTable0, boolean z, boolean z1, boolean z2) {
        Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
        Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.propertySignature, "propertySignature");
        JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = (JvmPropertySignature)ProtoBufUtilKt.getExtensionOrNull(protoBuf$Property0, JvmProtoBuf.propertySignature);
        if(jvmProtoBuf$JvmPropertySignature0 == null) {
            return null;
        }
        if(z) {
            Field jvmMemberSignature$Field0 = JvmProtoBufUtil.INSTANCE.getJvmFieldSignature(protoBuf$Property0, nameResolver0, typeTable0, z2);
            return jvmMemberSignature$Field0 == null ? null : MemberSignature.Companion.fromJvmMemberSignature(jvmMemberSignature$Field0);
        }
        if(z1 && jvmProtoBuf$JvmPropertySignature0.hasSyntheticMethod()) {
            JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = jvmProtoBuf$JvmPropertySignature0.getSyntheticMethod();
            Intrinsics.checkNotNullExpressionValue(jvmProtoBuf$JvmMethodSignature0, "signature.syntheticMethod");
            return MemberSignature.Companion.fromMethod(nameResolver0, jvmProtoBuf$JvmMethodSignature0);
        }
        return null;
    }

    public static MemberSignature getPropertySignature$default(Property protoBuf$Property0, NameResolver nameResolver0, TypeTable typeTable0, boolean z, boolean z1, boolean z2, int v, Object object0) {
        if((v & 8) != 0) {
            z = false;
        }
        if((v & 16) != 0) {
            z1 = false;
        }
        if((v & 0x20) != 0) {
            z2 = true;
        }
        return AbstractBinaryClassAnnotationLoaderKt.getPropertySignature(protoBuf$Property0, nameResolver0, typeTable0, z, z1, z2);
    }
}

