package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type.Argument.Projection;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter.Variance;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;

public final class ProtoEnumFlags {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;
        public static final int[] $EnumSwitchMapping$2;
        public static final int[] $EnumSwitchMapping$3;
        public static final int[] $EnumSwitchMapping$4;
        public static final int[] $EnumSwitchMapping$5;
        public static final int[] $EnumSwitchMapping$6;
        public static final int[] $EnumSwitchMapping$7;

        static {
            int[] arr_v = new int[Modality.values().length];
            try {
                arr_v[Modality.FINAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Modality.OPEN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Modality.ABSTRACT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Modality.SEALED.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[kotlin.reflect.jvm.internal.impl.descriptors.Modality.values().length];
            try {
                arr_v1[kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[kotlin.reflect.jvm.internal.impl.descriptors.Modality.OPEN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[kotlin.reflect.jvm.internal.impl.descriptors.Modality.ABSTRACT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[kotlin.reflect.jvm.internal.impl.descriptors.Modality.SEALED.ordinal()] = 4;
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
            int[] arr_v3 = new int[Kind.values().length];
            try {
                arr_v3[Kind.CLASS.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.INTERFACE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.ENUM_CLASS.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.ENUM_ENTRY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.ANNOTATION_CLASS.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.OBJECT.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v3[Kind.COMPANION_OBJECT.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$3 = arr_v3;
            int[] arr_v4 = new int[ClassKind.values().length];
            try {
                arr_v4[ClassKind.CLASS.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v4[ClassKind.INTERFACE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v4[ClassKind.ENUM_CLASS.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v4[ClassKind.ENUM_ENTRY.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v4[ClassKind.ANNOTATION_CLASS.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v4[ClassKind.OBJECT.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$4 = arr_v4;
            int[] arr_v5 = new int[Variance.values().length];
            try {
                arr_v5[Variance.IN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v5[Variance.OUT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v5[Variance.INV.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$5 = arr_v5;
            int[] arr_v6 = new int[Projection.values().length];
            try {
                arr_v6[Projection.IN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v6[Projection.OUT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v6[Projection.INV.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v6[Projection.STAR.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$6 = arr_v6;
            int[] arr_v7 = new int[kotlin.reflect.jvm.internal.impl.types.Variance.values().length];
            try {
                arr_v7[kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v7[kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v7[kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$7 = arr_v7;
        }
    }

    public static final ProtoEnumFlags INSTANCE;

    static {
        ProtoEnumFlags.INSTANCE = new ProtoEnumFlags();
    }

    public final ClassKind classKind(Kind protoBuf$Class$Kind0) {
        switch((protoBuf$Class$Kind0 == null ? -1 : WhenMappings.$EnumSwitchMapping$3[protoBuf$Class$Kind0.ordinal()])) {
            case 1: {
                return ClassKind.CLASS;
            }
            case 2: {
                return ClassKind.INTERFACE;
            }
            case 3: {
                return ClassKind.ENUM_CLASS;
            }
            case 4: {
                return ClassKind.ENUM_ENTRY;
            }
            case 5: {
                return ClassKind.ANNOTATION_CLASS;
            }
            case 6: 
            case 7: {
                return ClassKind.OBJECT;
            }
            default: {
                return ClassKind.CLASS;
            }
        }
    }

    public final kotlin.reflect.jvm.internal.impl.descriptors.Modality modality(Modality protoBuf$Modality0) {
        switch((protoBuf$Modality0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[protoBuf$Modality0.ordinal()])) {
            case 1: {
                return kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL;
            }
            case 2: {
                return kotlin.reflect.jvm.internal.impl.descriptors.Modality.OPEN;
            }
            case 3: {
                return kotlin.reflect.jvm.internal.impl.descriptors.Modality.ABSTRACT;
            }
            case 4: {
                return kotlin.reflect.jvm.internal.impl.descriptors.Modality.SEALED;
            }
            default: {
                return kotlin.reflect.jvm.internal.impl.descriptors.Modality.FINAL;
            }
        }
    }

    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(Projection protoBuf$Type$Argument$Projection0) {
        Intrinsics.checkNotNullParameter(protoBuf$Type$Argument$Projection0, "projection");
        switch(WhenMappings.$EnumSwitchMapping$6[protoBuf$Type$Argument$Projection0.ordinal()]) {
            case 1: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            }
            case 2: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            }
            case 3: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            }
            case 4: {
                throw new IllegalArgumentException("Only IN, OUT and INV are supported. Actual argument: " + protoBuf$Type$Argument$Projection0);
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public final kotlin.reflect.jvm.internal.impl.types.Variance variance(Variance protoBuf$TypeParameter$Variance0) {
        Intrinsics.checkNotNullParameter(protoBuf$TypeParameter$Variance0, "variance");
        switch(WhenMappings.$EnumSwitchMapping$5[protoBuf$TypeParameter$Variance0.ordinal()]) {
            case 1: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE;
            }
            case 2: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.OUT_VARIANCE;
            }
            case 3: {
                return kotlin.reflect.jvm.internal.impl.types.Variance.INVARIANT;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}

