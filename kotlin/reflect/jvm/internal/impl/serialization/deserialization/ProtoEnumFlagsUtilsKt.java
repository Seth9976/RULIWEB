package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.MemberKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;

public final class ProtoEnumFlagsUtilsKt {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;
        public static final int[] $EnumSwitchMapping$2;

        static {
            int[] arr_v = new int[MemberKind.values().length];
            try {
                arr_v[MemberKind.DECLARATION.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[MemberKind.FAKE_OVERRIDE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[MemberKind.DELEGATION.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[MemberKind.SYNTHESIZED.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[Kind.values().length];
            try {
                arr_v1[Kind.DECLARATION.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[Kind.FAKE_OVERRIDE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[Kind.DELEGATION.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[Kind.SYNTHESIZED.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$1 = arr_v1;
            int[] arr_v2 = new int[Visibility.values().length];
            try {
                arr_v2[Visibility.INTERNAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[Visibility.PRIVATE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[Visibility.PRIVATE_TO_THIS.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[Visibility.PROTECTED.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[Visibility.PUBLIC.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v2[Visibility.LOCAL.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$2 = arr_v2;
        }
    }

    public static final DescriptorVisibility descriptorVisibility(ProtoEnumFlags protoEnumFlags0, Visibility protoBuf$Visibility0) {
        Intrinsics.checkNotNullParameter(protoEnumFlags0, "<this>");
        switch((protoBuf$Visibility0 == null ? -1 : WhenMappings.$EnumSwitchMapping$2[protoBuf$Visibility0.ordinal()])) {
            case 1: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.INTERNAL, "INTERNAL");
                return DescriptorVisibilities.INTERNAL;
            }
            case 2: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PRIVATE, "PRIVATE");
                return DescriptorVisibilities.PRIVATE;
            }
            case 3: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PRIVATE_TO_THIS, "PRIVATE_TO_THIS");
                return DescriptorVisibilities.PRIVATE_TO_THIS;
            }
            case 4: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PROTECTED, "PROTECTED");
                return DescriptorVisibilities.PROTECTED;
            }
            case 5: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PUBLIC, "PUBLIC");
                return DescriptorVisibilities.PUBLIC;
            }
            case 6: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.LOCAL, "LOCAL");
                return DescriptorVisibilities.LOCAL;
            }
            default: {
                Intrinsics.checkNotNullExpressionValue(DescriptorVisibilities.PRIVATE, "PRIVATE");
                return DescriptorVisibilities.PRIVATE;
            }
        }
    }

    public static final Kind memberKind(ProtoEnumFlags protoEnumFlags0, MemberKind protoBuf$MemberKind0) {
        Intrinsics.checkNotNullParameter(protoEnumFlags0, "<this>");
        switch((protoBuf$MemberKind0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[protoBuf$MemberKind0.ordinal()])) {
            case 1: {
                return Kind.DECLARATION;
            }
            case 2: {
                return Kind.FAKE_OVERRIDE;
            }
            case 3: {
                return Kind.DELEGATION;
            }
            case 4: {
                return Kind.SYNTHESIZED;
            }
            default: {
                return Kind.DECLARATION;
            }
        }
    }
}

