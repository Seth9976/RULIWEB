package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyAccessorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner.Default;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class OverridingUtil {
    public static class OverrideCompatibilityInfo {
        public static enum Result {
            OVERRIDABLE,
            INCOMPATIBLE,
            CONFLICT;

        }

        private static final OverrideCompatibilityInfo SUCCESS;
        private final String debugMessage;
        private final Result overridable;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 1 || v == 2 || v == 3 || v == 4 ? 3 : 2)];
            switch(v) {
                case 3: {
                    arr_object[0] = "success";
                    break;
                }
                case 1: 
                case 2: 
                case 4: {
                    arr_object[0] = "debugMessage";
                    break;
                }
                default: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo";
                }
            }
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 4: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$OverrideCompatibilityInfo";
                    break;
                }
                case 5: {
                    arr_object[1] = "getResult";
                    break;
                }
                case 6: {
                    arr_object[1] = "getDebugMessage";
                    break;
                }
                default: {
                    arr_object[1] = "success";
                }
            }
            switch(v) {
                case 1: {
                    arr_object[2] = "incompatible";
                    break;
                }
                case 2: {
                    arr_object[2] = "conflict";
                    break;
                }
                case 3: 
                case 4: {
                    arr_object[2] = "<init>";
                }
            }
            String s = String.format((v == 1 || v == 2 || v == 3 || v == 4 ? "Argument for @NotNull parameter \'%s\' of %s.%s must not be null" : "@NotNull method %s.%s must not return null"), arr_object);
            IllegalArgumentException illegalArgumentException0 = v == 1 || v == 2 || v == 3 || v == 4 ? new IllegalArgumentException(s) : new IllegalStateException(s);
            throw illegalArgumentException0;
        }

        static {
            OverrideCompatibilityInfo.SUCCESS = new OverrideCompatibilityInfo(Result.OVERRIDABLE, "SUCCESS");
        }

        public OverrideCompatibilityInfo(Result overridingUtil$OverrideCompatibilityInfo$Result0, String s) {
            if(overridingUtil$OverrideCompatibilityInfo$Result0 == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(3);
            }
            if(s == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(4);
            }
            super();
            this.overridable = overridingUtil$OverrideCompatibilityInfo$Result0;
            this.debugMessage = s;
        }

        public static OverrideCompatibilityInfo conflict(String s) {
            if(s == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(2);
            }
            return new OverrideCompatibilityInfo(Result.CONFLICT, s);
        }

        public Result getResult() {
            Result overridingUtil$OverrideCompatibilityInfo$Result0 = this.overridable;
            if(overridingUtil$OverrideCompatibilityInfo$Result0 == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(5);
            }
            return overridingUtil$OverrideCompatibilityInfo$Result0;
        }

        public static OverrideCompatibilityInfo incompatible(String s) {
            if(s == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(1);
            }
            return new OverrideCompatibilityInfo(Result.INCOMPATIBLE, s);
        }

        public static OverrideCompatibilityInfo success() {
            OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = OverrideCompatibilityInfo.SUCCESS;
            if(overridingUtil$OverrideCompatibilityInfo0 == null) {
                OverrideCompatibilityInfo.$$$reportNull$$$0(0);
            }
            return overridingUtil$OverrideCompatibilityInfo0;
        }
    }

    static final boolean $assertionsDisabled;
    public static final OverridingUtil DEFAULT;
    private static final TypeConstructorEquality DEFAULT_TYPE_CONSTRUCTOR_EQUALITY;
    private static final List EXTERNAL_CONDITIONS;
    private final Function2 customSubtype;
    private final TypeConstructorEquality equalityAxioms;
    private final KotlinTypePreparator kotlinTypePreparator;
    private final KotlinTypeRefiner kotlinTypeRefiner;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        switch(v) {
            case 11: 
            case 12: 
            case 16: 
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 44: 
            case 45: 
            case 0x5F: 
            case 98: 
            case 103: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = v == 80 || v == 81 || v == 82 || v == 83 || v == 84 || (v == 90 || v == 91 || v == 92) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 11: 
            case 12: 
            case 16: 
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 44: 
            case 45: 
            case 0x5F: 
            case 98: 
            case 103: {
                v1 = 2;
                break;
            }
            default: {
                v1 = v == 80 || v == 81 || v == 82 || v == 83 || v == 84 || (v == 90 || v == 91 || v == 92) ? 2 : 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 2: {
                arr_object[0] = "customSubtype";
                break;
            }
            case 4: {
                arr_object[0] = "equalityAxioms";
                break;
            }
            case 5: {
                arr_object[0] = "axioms";
                break;
            }
            case 1: 
            case 7: {
                arr_object[0] = "kotlinTypePreparator";
                break;
            }
            case 8: 
            case 9: {
                arr_object[0] = "candidateSet";
                break;
            }
            case 10: {
                arr_object[0] = "transformFirst";
                break;
            }
            case 13: {
                arr_object[0] = "f";
                break;
            }
            case 14: {
                arr_object[0] = "g";
                break;
            }
            case 15: 
            case 17: {
                arr_object[0] = "descriptor";
                break;
            }
            case 18: {
                arr_object[0] = "result";
                break;
            }
            case 19: 
            case 22: 
            case 30: 
            case 40: {
                arr_object[0] = "superDescriptor";
                break;
            }
            case 20: 
            case 23: 
            case 0x1F: 
            case 41: {
                arr_object[0] = "subDescriptor";
                break;
            }
            case 42: {
                arr_object[0] = "firstParameters";
                break;
            }
            case 43: {
                arr_object[0] = "secondParameters";
                break;
            }
            case 46: {
                arr_object[0] = "typeInSuper";
                break;
            }
            case 0x2F: {
                arr_object[0] = "typeInSub";
                break;
            }
            case 49: {
                arr_object[0] = "superTypeParameter";
                break;
            }
            case 50: {
                arr_object[0] = "subTypeParameter";
                break;
            }
            case 52: {
                arr_object[0] = "name";
                break;
            }
            case 53: {
                arr_object[0] = "membersFromSupertypes";
                break;
            }
            case 54: {
                arr_object[0] = "membersFromCurrent";
                break;
            }
            case 57: {
                arr_object[0] = "overriding";
                break;
            }
            case 58: {
                arr_object[0] = "fromSuper";
                break;
            }
            case 59: {
                arr_object[0] = "fromCurrent";
                break;
            }
            case 60: {
                arr_object[0] = "descriptorsFromSuper";
                break;
            }
            case 0x3F: 
            case 65: {
                arr_object[0] = "notOverridden";
                break;
            }
            case 71: {
                arr_object[0] = "candidate";
                break;
            }
            case 67: 
            case 69: 
            case 73: {
                arr_object[0] = "a";
                break;
            }
            case 74: {
                arr_object[0] = "aReturnType";
                break;
            }
            case 68: 
            case 70: 
            case 75: {
                arr_object[0] = "b";
                break;
            }
            case 76: {
                arr_object[0] = "bReturnType";
                break;
            }
            case 0x30: 
            case 51: 
            case 77: {
                arr_object[0] = "typeCheckerState";
                break;
            }
            case 78: 
            case 85: {
                arr_object[0] = "overridables";
                break;
            }
            case 94: {
                arr_object[0] = "classModality";
                break;
            }
            case 55: 
            case 61: 
            case 0x40: 
            case 86: 
            case 89: 
            case 0x60: {
                arr_object[0] = "current";
                break;
            }
            case 97: {
                arr_object[0] = "toFilter";
                break;
            }
            case 0x4F: 
            case 101: {
                arr_object[0] = "descriptorByHandle";
                break;
            }
            case 102: {
                arr_object[0] = "onConflict";
                break;
            }
            case 11: 
            case 12: 
            case 16: 
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 44: 
            case 45: 
            case 80: 
            case 81: 
            case 82: 
            case 83: 
            case 84: 
            case 90: 
            case 91: 
            case 92: 
            case 0x5F: 
            case 98: 
            case 103: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                break;
            }
            case 99: 
            case 104: {
                arr_object[0] = "overrider";
                break;
            }
            case 100: 
            case 105: {
                arr_object[0] = "extractFrom";
                break;
            }
            case 56: 
            case 62: 
            case 66: 
            case 87: 
            case 106: {
                arr_object[0] = "strategy";
                break;
            }
            case 107: 
            case 108: {
                arr_object[0] = "memberDescriptor";
                break;
            }
            case 72: 
            case 88: 
            case 93: 
            case 109: {
                arr_object[0] = "descriptors";
                break;
            }
            default: {
                arr_object[0] = "kotlinTypeRefiner";
            }
        }
        switch(v) {
            case 11: 
            case 12: {
                arr_object[1] = "filterOverrides";
                break;
            }
            case 16: {
                arr_object[1] = "getOverriddenDeclarations";
                break;
            }
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: {
                arr_object[1] = "isOverridableBy";
                break;
            }
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: {
                arr_object[1] = "isOverridableByWithoutExternalConditions";
                break;
            }
            case 44: 
            case 45: {
                arr_object[1] = "createTypeCheckerState";
                break;
            }
            case 0x5F: {
                arr_object[1] = "getMinimalModality";
                break;
            }
            case 98: {
                arr_object[1] = "filterVisibleFakeOverrides";
                break;
            }
            case 103: {
                arr_object[1] = "extractMembersOverridableInBothWays";
                break;
            }
            default: {
                if(v == 80 || v == 81 || v == 82 || v == 83 || v == 84) {
                    arr_object[1] = "selectMostSpecificMember";
                }
                else if(v != 90 && v != 91 && v != 92) {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil";
                }
                else {
                    arr_object[1] = "determineModalityForFakeOverride";
                }
            }
        }
        switch(v) {
            case 1: 
            case 2: {
                arr_object[2] = "createWithTypePreparatorAndCustomSubtype";
                break;
            }
            case 3: 
            case 4: {
                arr_object[2] = "create";
                break;
            }
            case 5: 
            case 6: 
            case 7: {
                arr_object[2] = "<init>";
                break;
            }
            case 8: {
                arr_object[2] = "filterOutOverridden";
                break;
            }
            case 9: 
            case 10: {
                arr_object[2] = "filterOverrides";
                break;
            }
            case 13: 
            case 14: {
                arr_object[2] = "overrides";
                break;
            }
            case 15: {
                arr_object[2] = "getOverriddenDeclarations";
                break;
            }
            case 17: 
            case 18: {
                arr_object[2] = "collectOverriddenDeclarations";
                break;
            }
            case 19: 
            case 20: 
            case 22: 
            case 23: {
                arr_object[2] = "isOverridableBy";
                break;
            }
            case 30: 
            case 0x1F: {
                arr_object[2] = "isOverridableByWithoutExternalConditions";
                break;
            }
            case 40: 
            case 41: {
                arr_object[2] = "getBasicOverridabilityProblem";
                break;
            }
            case 42: 
            case 43: {
                arr_object[2] = "createTypeCheckerState";
                break;
            }
            case 46: 
            case 0x2F: 
            case 0x30: {
                arr_object[2] = "areTypesEquivalent";
                break;
            }
            case 49: 
            case 50: 
            case 51: {
                arr_object[2] = "areTypeParametersEquivalent";
                break;
            }
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: {
                arr_object[2] = "generateOverridesInFunctionGroup";
                break;
            }
            case 57: 
            case 58: {
                arr_object[2] = "isVisibleForOverride";
                break;
            }
            case 59: 
            case 60: 
            case 61: 
            case 62: {
                arr_object[2] = "extractAndBindOverridesForMember";
                break;
            }
            case 0x3F: {
                arr_object[2] = "allHasSameContainingDeclaration";
                break;
            }
            case 0x40: 
            case 65: 
            case 66: {
                arr_object[2] = "createAndBindFakeOverrides";
                break;
            }
            case 67: 
            case 68: {
                arr_object[2] = "isMoreSpecific";
                break;
            }
            case 69: 
            case 70: {
                arr_object[2] = "isVisibilityMoreSpecific";
                break;
            }
            case 71: 
            case 72: {
                arr_object[2] = "isMoreSpecificThenAllOf";
                break;
            }
            case 73: 
            case 74: 
            case 75: 
            case 76: 
            case 77: {
                arr_object[2] = "isReturnTypeMoreSpecific";
                break;
            }
            case 78: 
            case 0x4F: {
                arr_object[2] = "selectMostSpecificMember";
                break;
            }
            case 85: 
            case 86: 
            case 87: {
                arr_object[2] = "createAndBindFakeOverride";
                break;
            }
            case 88: 
            case 89: {
                arr_object[2] = "determineModalityForFakeOverride";
                break;
            }
            case 93: 
            case 94: {
                arr_object[2] = "getMinimalModality";
                break;
            }
            case 0x60: 
            case 97: {
                arr_object[2] = "filterVisibleFakeOverrides";
                break;
            }
            case 11: 
            case 12: 
            case 16: 
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 44: 
            case 45: 
            case 80: 
            case 81: 
            case 82: 
            case 83: 
            case 84: 
            case 90: 
            case 91: 
            case 92: 
            case 0x5F: 
            case 98: 
            case 103: {
                break;
            }
            case 99: 
            case 100: 
            case 101: 
            case 102: 
            case 104: 
            case 105: 
            case 106: {
                arr_object[2] = "extractMembersOverridableInBothWays";
                break;
            }
            case 107: {
                arr_object[2] = "resolveUnknownVisibilityForMember";
                break;
            }
            case 108: {
                arr_object[2] = "computeVisibilityToInherit";
                break;
            }
            case 109: {
                arr_object[2] = "findMaxVisibility";
                break;
            }
            default: {
                arr_object[2] = "createWithTypeRefiner";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 11: 
            case 12: 
            case 16: 
            case 21: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 44: 
            case 45: 
            case 0x5F: 
            case 98: 
            case 103: {
                illegalStateException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalStateException0 = v == 80 || v == 81 || v == 82 || v == 83 || v == 84 || (v == 90 || v == 91 || v == 92) ? new IllegalStateException(s1) : new IllegalArgumentException(s1);
            }
        }
        throw illegalStateException0;
    }

    static {
        OverridingUtil.EXTERNAL_CONDITIONS = CollectionsKt.toList(ServiceLoader.load(ExternalOverridabilityCondition.class, ExternalOverridabilityCondition.class.getClassLoader()));
        kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.1 overridingUtil$10 = new TypeConstructorEquality() {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "b" : "a";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
                arr_object[2] = "equals";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker$TypeConstructorEquality
            public boolean equals(TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) {
                if(typeConstructor0 == null) {
                    kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.1.$$$reportNull$$$0(0);
                }
                if(typeConstructor1 == null) {
                    kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.1.$$$reportNull$$$0(1);
                }
                return typeConstructor0.equals(typeConstructor1);
            }
        };
        OverridingUtil.DEFAULT_TYPE_CONSTRUCTOR_EQUALITY = overridingUtil$10;
        OverridingUtil.DEFAULT = new OverridingUtil(overridingUtil$10, Default.INSTANCE, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator.Default.INSTANCE, null);
    }

    private OverridingUtil(TypeConstructorEquality kotlinTypeChecker$TypeConstructorEquality0, KotlinTypeRefiner kotlinTypeRefiner0, KotlinTypePreparator kotlinTypePreparator0, Function2 function20) {
        if(kotlinTypeChecker$TypeConstructorEquality0 == null) {
            OverridingUtil.$$$reportNull$$$0(5);
        }
        if(kotlinTypeRefiner0 == null) {
            OverridingUtil.$$$reportNull$$$0(6);
        }
        if(kotlinTypePreparator0 == null) {
            OverridingUtil.$$$reportNull$$$0(7);
        }
        super();
        this.equalityAxioms = kotlinTypeChecker$TypeConstructorEquality0;
        this.kotlinTypeRefiner = kotlinTypeRefiner0;
        this.kotlinTypePreparator = kotlinTypePreparator0;
        this.customSubtype = function20;
    }

    private static boolean allHasSameContainingDeclaration(Collection collection0) {
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(0x3F);
        }
        if(collection0.size() < 2) {
            return true;
        }
        Object object0 = collection0.iterator().next();
        return CollectionsKt.all(collection0, new Function1() {
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                return callableMemberDescriptor0.getContainingDeclaration() == ((CallableMemberDescriptor)object0).getContainingDeclaration();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        });
    }

    private static boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor0, TypeParameterDescriptor typeParameterDescriptor1, TypeCheckerState typeCheckerState0) {
        if(typeParameterDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(49);
        }
        if(typeParameterDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(50);
        }
        if(typeCheckerState0 == null) {
            OverridingUtil.$$$reportNull$$$0(51);
        }
        List list0 = typeParameterDescriptor0.getUpperBounds();
        ArrayList arrayList0 = new ArrayList(typeParameterDescriptor1.getUpperBounds());
        if(list0.size() != arrayList0.size()) {
            return false;
        }
        for(Object object0: list0) {
            ListIterator listIterator0 = arrayList0.listIterator();
        label_14:
            if(listIterator0.hasNext()) {
                if(OverridingUtil.areTypesEquivalent(((KotlinType)object0), ((KotlinType)listIterator0.next()), typeCheckerState0)) {
                    listIterator0.remove();
                    continue;
                }
                goto label_14;
            }
            return false;
        }
        return true;
    }

    private static boolean areTypesEquivalent(KotlinType kotlinType0, KotlinType kotlinType1, TypeCheckerState typeCheckerState0) {
        if(kotlinType0 == null) {
            OverridingUtil.$$$reportNull$$$0(46);
        }
        if(kotlinType1 == null) {
            OverridingUtil.$$$reportNull$$$0(0x2F);
        }
        if(typeCheckerState0 == null) {
            OverridingUtil.$$$reportNull$$$0(0x30);
        }
        if(KotlinTypeKt.isError(kotlinType0) && KotlinTypeKt.isError(kotlinType1)) {
            return true;
        }
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        UnwrappedType unwrappedType1 = kotlinType1.unwrap();
        return AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerState0, unwrappedType0, unwrappedType1);
    }

    private static OverrideCompatibilityInfo checkReceiverAndParameterCount(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        int v = 1;
        int v1 = callableDescriptor0.getExtensionReceiverParameter() == null ? 1 : 0;
        if(callableDescriptor1.getExtensionReceiverParameter() != null) {
            v = 0;
        }
        if(v1 != v) {
            return OverrideCompatibilityInfo.incompatible("Receiver presence mismatch");
        }
        return callableDescriptor0.getValueParameters().size() == callableDescriptor1.getValueParameters().size() ? null : OverrideCompatibilityInfo.incompatible("Value parameter number mismatch");
    }

    private static void collectOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor0, Set set0) {
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(17);
        }
        if(set0 == null) {
            OverridingUtil.$$$reportNull$$$0(18);
        }
        if(callableMemberDescriptor0.getKind().isReal()) {
            set0.add(callableMemberDescriptor0);
            return;
        }
        if(callableMemberDescriptor0.getOverriddenDescriptors().isEmpty()) {
            throw new IllegalStateException("No overridden descriptors found for (fake override) " + callableMemberDescriptor0);
        }
        for(Object object0: callableMemberDescriptor0.getOverriddenDescriptors()) {
            OverridingUtil.collectOverriddenDeclarations(((CallableMemberDescriptor)object0), set0);
        }
    }

    private static List compiledValueParameters(CallableDescriptor callableDescriptor0) {
        ReceiverParameterDescriptor receiverParameterDescriptor0 = callableDescriptor0.getExtensionReceiverParameter();
        List list0 = new ArrayList();
        if(receiverParameterDescriptor0 != null) {
            list0.add(receiverParameterDescriptor0.getType());
        }
        for(Object object0: callableDescriptor0.getValueParameters()) {
            list0.add(((ValueParameterDescriptor)object0).getType());
        }
        return list0;
    }

    private static DescriptorVisibility computeVisibilityToInherit(CallableMemberDescriptor callableMemberDescriptor0) {
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(108);
        }
        Collection collection0 = callableMemberDescriptor0.getOverriddenDescriptors();
        DescriptorVisibility descriptorVisibility0 = OverridingUtil.findMaxVisibility(collection0);
        if(descriptorVisibility0 == null) {
            return null;
        }
        if(callableMemberDescriptor0.getKind() == Kind.FAKE_OVERRIDE) {
            for(Object object0: collection0) {
                if(((CallableMemberDescriptor)object0).getModality() != Modality.ABSTRACT && !((CallableMemberDescriptor)object0).getVisibility().equals(descriptorVisibility0)) {
                    return null;
                }
                if(false) {
                    break;
                }
            }
            return descriptorVisibility0;
        }
        return descriptorVisibility0.normalize();
    }

    public static OverridingUtil create(KotlinTypeRefiner kotlinTypeRefiner0, TypeConstructorEquality kotlinTypeChecker$TypeConstructorEquality0) {
        if(kotlinTypeRefiner0 == null) {
            OverridingUtil.$$$reportNull$$$0(3);
        }
        if(kotlinTypeChecker$TypeConstructorEquality0 == null) {
            OverridingUtil.$$$reportNull$$$0(4);
        }
        return new OverridingUtil(kotlinTypeChecker$TypeConstructorEquality0, kotlinTypeRefiner0, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator.Default.INSTANCE, null);
    }

    private static void createAndBindFakeOverride(Collection collection0, ClassDescriptor classDescriptor0, OverridingStrategy overridingStrategy0) {
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(85);
        }
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(86);
        }
        if(overridingStrategy0 == null) {
            OverridingUtil.$$$reportNull$$$0(87);
        }
        Collection collection1 = OverridingUtil.filterVisibleFakeOverrides(classDescriptor0, collection0);
        boolean z = collection1.isEmpty();
        if(!z) {
            collection0 = collection1;
        }
        Modality modality0 = OverridingUtil.determineModalityForFakeOverride(collection0, classDescriptor0);
        CallableMemberDescriptor callableMemberDescriptor0 = ((CallableMemberDescriptor)OverridingUtil.selectMostSpecificMember(collection0, new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }

            public CallableMemberDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                return callableMemberDescriptor0;
            }
        })).copy(classDescriptor0, modality0, (z ? DescriptorVisibilities.INVISIBLE_FAKE : DescriptorVisibilities.INHERITED), Kind.FAKE_OVERRIDE, false);
        overridingStrategy0.setOverriddenDescriptors(callableMemberDescriptor0, collection0);
        overridingStrategy0.addFakeOverride(callableMemberDescriptor0);
    }

    private static void createAndBindFakeOverrides(ClassDescriptor classDescriptor0, Collection collection0, OverridingStrategy overridingStrategy0) {
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(0x40);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(65);
        }
        if(overridingStrategy0 == null) {
            OverridingUtil.$$$reportNull$$$0(66);
        }
        if(OverridingUtil.allHasSameContainingDeclaration(collection0)) {
            for(Object object0: collection0) {
                OverridingUtil.createAndBindFakeOverride(Collections.singleton(((CallableMemberDescriptor)object0)), classDescriptor0, overridingStrategy0);
            }
            return;
        }
        LinkedList linkedList0 = new LinkedList(collection0);
        while(!linkedList0.isEmpty()) {
            OverridingUtil.createAndBindFakeOverride(OverridingUtil.extractMembersOverridableInBothWays(VisibilityUtilKt.findMemberWithMaxVisibility(linkedList0), linkedList0, overridingStrategy0), classDescriptor0, overridingStrategy0);
        }
    }

    private TypeCheckerState createTypeCheckerState(List list0, List list1) {
        if(list0 == null) {
            OverridingUtil.$$$reportNull$$$0(42);
        }
        if(list1 == null) {
            OverridingUtil.$$$reportNull$$$0(43);
        }
        if(list0.isEmpty()) {
            TypeCheckerState typeCheckerState0 = new OverridingUtilTypeSystemContext(null, this.equalityAxioms, this.kotlinTypeRefiner, this.kotlinTypePreparator, this.customSubtype).newTypeCheckerState(true, true);
            if(typeCheckerState0 == null) {
                OverridingUtil.$$$reportNull$$$0(44);
            }
            return typeCheckerState0;
        }
        HashMap hashMap0 = new HashMap();
        for(int v = 0; v < list0.size(); ++v) {
            hashMap0.put(((TypeParameterDescriptor)list0.get(v)).getTypeConstructor(), ((TypeParameterDescriptor)list1.get(v)).getTypeConstructor());
        }
        TypeCheckerState typeCheckerState1 = new OverridingUtilTypeSystemContext(hashMap0, this.equalityAxioms, this.kotlinTypeRefiner, this.kotlinTypePreparator, this.customSubtype).newTypeCheckerState(true, true);
        if(typeCheckerState1 == null) {
            OverridingUtil.$$$reportNull$$$0(45);
        }
        return typeCheckerState1;
    }

    public static OverridingUtil createWithTypeRefiner(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            OverridingUtil.$$$reportNull$$$0(0);
        }
        return new OverridingUtil(OverridingUtil.DEFAULT_TYPE_CONSTRUCTOR_EQUALITY, kotlinTypeRefiner0, kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypePreparator.Default.INSTANCE, null);
    }

    private static Modality determineModalityForFakeOverride(Collection collection0, ClassDescriptor classDescriptor0) {
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(88);
        }
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(89);
        }
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        for(Object object0: collection0) {
            CallableMemberDescriptor callableMemberDescriptor0 = (CallableMemberDescriptor)object0;
            switch(kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.8.$SwitchMap$org$jetbrains$kotlin$descriptors$Modality[callableMemberDescriptor0.getModality().ordinal()]) {
                case 1: {
                    Modality modality0 = Modality.FINAL;
                    if(modality0 == null) {
                        OverridingUtil.$$$reportNull$$$0(90);
                    }
                    return modality0;
                }
                case 2: {
                    throw new IllegalStateException("Member cannot have SEALED modality: " + callableMemberDescriptor0);
                }
                case 3: {
                    z1 = true;
                    break;
                }
                case 4: {
                    z2 = true;
                }
            }
        }
        if(classDescriptor0.isExpect() && classDescriptor0.getModality() != Modality.ABSTRACT && classDescriptor0.getModality() != Modality.SEALED) {
            z = true;
        }
        if(z1 && !z2) {
            Modality modality1 = Modality.OPEN;
            if(modality1 == null) {
                OverridingUtil.$$$reportNull$$$0(91);
            }
            return modality1;
        }
        if(!z1 && z2) {
            Modality modality2 = z ? classDescriptor0.getModality() : Modality.ABSTRACT;
            if(modality2 == null) {
                OverridingUtil.$$$reportNull$$$0(92);
            }
            return modality2;
        }
        HashSet hashSet0 = new HashSet();
        for(Object object1: collection0) {
            hashSet0.addAll(OverridingUtil.getOverriddenDeclarations(((CallableMemberDescriptor)object1)));
        }
        return OverridingUtil.getMinimalModality(OverridingUtil.filterOutOverridden(hashSet0), z, classDescriptor0.getModality());
    }

    private Collection extractAndBindOverridesForMember(CallableMemberDescriptor callableMemberDescriptor0, Collection collection0, ClassDescriptor classDescriptor0, OverridingStrategy overridingStrategy0) {
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(59);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(60);
        }
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(61);
        }
        if(overridingStrategy0 == null) {
            OverridingUtil.$$$reportNull$$$0(62);
        }
        Collection collection1 = new ArrayList(collection0.size());
        SmartSet smartSet0 = SmartSet.create();
        for(Object object0: collection0) {
            CallableMemberDescriptor callableMemberDescriptor1 = (CallableMemberDescriptor)object0;
            Result overridingUtil$OverrideCompatibilityInfo$Result0 = this.isOverridableBy(callableMemberDescriptor1, callableMemberDescriptor0, classDescriptor0).getResult();
            boolean z = OverridingUtil.isVisibleForOverride(callableMemberDescriptor0, callableMemberDescriptor1, false);
            switch(kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.8.$SwitchMap$org$jetbrains$kotlin$resolve$OverridingUtil$OverrideCompatibilityInfo$Result[overridingUtil$OverrideCompatibilityInfo$Result0.ordinal()]) {
                case 1: {
                    if(z) {
                        smartSet0.add(callableMemberDescriptor1);
                    }
                    collection1.add(callableMemberDescriptor1);
                    continue;
                }
                case 2: {
                    if(z) {
                        overridingStrategy0.overrideConflict(callableMemberDescriptor1, callableMemberDescriptor0);
                    }
                    break;
                }
                default: {
                    continue;
                }
            }
            collection1.add(callableMemberDescriptor1);
        }
        overridingStrategy0.setOverriddenDescriptors(callableMemberDescriptor0, smartSet0);
        return collection1;
    }

    public static Collection extractMembersOverridableInBothWays(Object object0, Collection collection0, Function1 function10, Function1 function11) {
        if(object0 == null) {
            OverridingUtil.$$$reportNull$$$0(99);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(100);
        }
        if(function10 == null) {
            OverridingUtil.$$$reportNull$$$0(101);
        }
        if(function11 == null) {
            OverridingUtil.$$$reportNull$$$0(102);
        }
        Collection collection1 = new ArrayList();
        collection1.add(object0);
        CallableDescriptor callableDescriptor0 = (CallableDescriptor)function10.invoke(object0);
        Iterator iterator0 = collection0.iterator();
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            CallableDescriptor callableDescriptor1 = (CallableDescriptor)function10.invoke(object1);
            if(object0 == object1) {
                iterator0.remove();
            }
            else {
                Result overridingUtil$OverrideCompatibilityInfo$Result0 = OverridingUtil.getBothWaysOverridability(callableDescriptor0, callableDescriptor1);
                if(overridingUtil$OverrideCompatibilityInfo$Result0 == Result.OVERRIDABLE) {
                    collection1.add(object1);
                    iterator0.remove();
                }
                else if(overridingUtil$OverrideCompatibilityInfo$Result0 == Result.CONFLICT) {
                    function11.invoke(object1);
                    iterator0.remove();
                }
            }
        }
        return collection1;
    }

    private static Collection extractMembersOverridableInBothWays(CallableMemberDescriptor callableMemberDescriptor0, Queue queue0, OverridingStrategy overridingStrategy0) {
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(104);
        }
        if(queue0 == null) {
            OverridingUtil.$$$reportNull$$$0(105);
        }
        if(overridingStrategy0 == null) {
            OverridingUtil.$$$reportNull$$$0(106);
        }
        return OverridingUtil.extractMembersOverridableInBothWays(callableMemberDescriptor0, queue0, new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }

            public CallableDescriptor invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                return callableMemberDescriptor0;
            }
        }, new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }

            public Unit invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                overridingStrategy0.inheritanceConflict(callableMemberDescriptor0, callableMemberDescriptor0);
                return Unit.INSTANCE;
            }
        });
    }

    public static Set filterOutOverridden(Set set0) {
        if(set0 == null) {
            OverridingUtil.$$$reportNull$$$0(8);
        }
        if(!set0.isEmpty()) {
            Object object0 = set0.iterator().next();
            return DescriptorUtilsKt.isTypeRefinementEnabled(DescriptorUtilsKt.getModule(((DeclarationDescriptor)object0))) ? OverridingUtil.filterOverrides(set0, true, null, new Function2() {
                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CallableDescriptor)object0), ((CallableDescriptor)object1));
                }

                public Pair invoke(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
                    return new Pair(callableDescriptor0, callableDescriptor1);
                }
            }) : OverridingUtil.filterOverrides(set0, false, null, new Function2() {
                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CallableDescriptor)object0), ((CallableDescriptor)object1));
                }

                public Pair invoke(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
                    return new Pair(callableDescriptor0, callableDescriptor1);
                }
            });
        }
        return OverridingUtil.filterOverrides(set0, false, null, new Function2() {
            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CallableDescriptor)object0), ((CallableDescriptor)object1));
            }

            public Pair invoke(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
                return new Pair(callableDescriptor0, callableDescriptor1);
            }
        });
    }

    public static Set filterOverrides(Set set0, boolean z, Function0 function00, Function2 function20) {
        if(set0 == null) {
            OverridingUtil.$$$reportNull$$$0(9);
        }
        if(function20 == null) {
            OverridingUtil.$$$reportNull$$$0(10);
        }
        if(set0.size() <= 1) {
            return set0;
        }
        Set set1 = new LinkedHashSet();
        Iterator iterator0 = set0.iterator();
    label_8:
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(function00 != null) {
                function00.invoke();
            }
            Iterator iterator1 = set1.iterator();
            while(iterator1.hasNext()) {
                Object object1 = iterator1.next();
                Pair pair0 = (Pair)function20.invoke(object0, object1);
                CallableDescriptor callableDescriptor0 = (CallableDescriptor)pair0.component1();
                CallableDescriptor callableDescriptor1 = (CallableDescriptor)pair0.component2();
                if(OverridingUtil.overrides(callableDescriptor0, callableDescriptor1, z, true)) {
                    iterator1.remove();
                }
                else {
                    if(OverridingUtil.overrides(callableDescriptor1, callableDescriptor0, z, true)) {
                        continue label_8;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            set1.add(object0);
        }
        return set1;
    }

    private static Collection filterVisibleFakeOverrides(ClassDescriptor classDescriptor0, Collection collection0) {
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(0x60);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(97);
        }
        Collection collection1 = CollectionsKt.filter(collection0, new Function1() {
            // 去混淆评级： 低(20)
            public Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                return Boolean.valueOf(!DescriptorVisibilities.isPrivate(callableMemberDescriptor0.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(callableMemberDescriptor0, classDescriptor0, false));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        });
        if(collection1 == null) {
            OverridingUtil.$$$reportNull$$$0(98);
        }
        return collection1;
    }

    public static DescriptorVisibility findMaxVisibility(Collection collection0) {
        DescriptorVisibility descriptorVisibility1;
        DescriptorVisibility descriptorVisibility0;
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(109);
        }
        if(collection0.isEmpty()) {
            return DescriptorVisibilities.DEFAULT_VISIBILITY;
        }
        Iterator iterator0 = collection0.iterator();
        do {
            descriptorVisibility0 = null;
        label_6:
            if(!iterator0.hasNext()) {
                goto label_15;
            }
            Object object0 = iterator0.next();
            descriptorVisibility1 = ((CallableMemberDescriptor)object0).getVisibility();
            if(descriptorVisibility0 == null) {
                descriptorVisibility0 = descriptorVisibility1;
                goto label_6;
            }
            Integer integer0 = DescriptorVisibilities.compare(descriptorVisibility1, descriptorVisibility0);
        }
        while(integer0 == null);
        if(((int)integer0) <= 0) {
            goto label_6;
        }
        descriptorVisibility0 = descriptorVisibility1;
        goto label_6;
    label_15:
        if(descriptorVisibility0 == null) {
            return null;
        }
        for(Object object1: collection0) {
            Integer integer1 = DescriptorVisibilities.compare(descriptorVisibility0, ((CallableMemberDescriptor)object1).getVisibility());
            if(integer1 == null || ((int)integer1) < 0) {
                return null;
            }
            if(false) {
                break;
            }
        }
        return descriptorVisibility0;
    }

    public void generateOverridesInFunctionGroup(Name name0, Collection collection0, Collection collection1, ClassDescriptor classDescriptor0, OverridingStrategy overridingStrategy0) {
        if(name0 == null) {
            OverridingUtil.$$$reportNull$$$0(52);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(53);
        }
        if(collection1 == null) {
            OverridingUtil.$$$reportNull$$$0(54);
        }
        if(classDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(55);
        }
        if(overridingStrategy0 == null) {
            OverridingUtil.$$$reportNull$$$0(56);
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(collection0);
        for(Object object0: collection1) {
            linkedHashSet0.removeAll(this.extractAndBindOverridesForMember(((CallableMemberDescriptor)object0), collection0, classDescriptor0, overridingStrategy0));
        }
        OverridingUtil.createAndBindFakeOverrides(classDescriptor0, linkedHashSet0, overridingStrategy0);
    }

    public static OverrideCompatibilityInfo getBasicOverridabilityProblem(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(40);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(41);
        }
        if(callableDescriptor0 instanceof FunctionDescriptor && !(callableDescriptor1 instanceof FunctionDescriptor) || callableDescriptor0 instanceof PropertyDescriptor && !(callableDescriptor1 instanceof PropertyDescriptor)) {
            return OverrideCompatibilityInfo.incompatible("Member kind mismatch");
        }
        if(!(callableDescriptor0 instanceof FunctionDescriptor) && !(callableDescriptor0 instanceof PropertyDescriptor)) {
            throw new IllegalArgumentException("This type of CallableDescriptor cannot be checked for overridability: " + callableDescriptor0);
        }
        if(!callableDescriptor0.getName().equals(callableDescriptor1.getName())) {
            return OverrideCompatibilityInfo.incompatible("Name mismatch");
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = OverridingUtil.checkReceiverAndParameterCount(callableDescriptor0, callableDescriptor1);
        return overridingUtil$OverrideCompatibilityInfo0 == null ? null : overridingUtil$OverrideCompatibilityInfo0;
    }

    public static Result getBothWaysOverridability(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        Result overridingUtil$OverrideCompatibilityInfo$Result0 = OverridingUtil.DEFAULT.isOverridableBy(callableDescriptor1, callableDescriptor0, null).getResult();
        Result overridingUtil$OverrideCompatibilityInfo$Result1 = OverridingUtil.DEFAULT.isOverridableBy(callableDescriptor0, callableDescriptor1, null).getResult();
        if(overridingUtil$OverrideCompatibilityInfo$Result0 == Result.OVERRIDABLE && overridingUtil$OverrideCompatibilityInfo$Result1 == Result.OVERRIDABLE) {
            return Result.OVERRIDABLE;
        }
        return overridingUtil$OverrideCompatibilityInfo$Result0 == Result.CONFLICT || overridingUtil$OverrideCompatibilityInfo$Result1 == Result.CONFLICT ? Result.CONFLICT : Result.INCOMPATIBLE;
    }

    private static Modality getMinimalModality(Collection collection0, boolean z, Modality modality0) {
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(93);
        }
        if(modality0 == null) {
            OverridingUtil.$$$reportNull$$$0(94);
        }
        Modality modality1 = Modality.ABSTRACT;
        for(Object object0: collection0) {
            CallableMemberDescriptor callableMemberDescriptor0 = (CallableMemberDescriptor)object0;
            Modality modality2 = !z || callableMemberDescriptor0.getModality() != Modality.ABSTRACT ? callableMemberDescriptor0.getModality() : modality0;
            if(modality2.compareTo(modality1) < 0) {
                modality1 = modality2;
            }
        }
        if(modality1 == null) {
            OverridingUtil.$$$reportNull$$$0(0x5F);
        }
        return modality1;
    }

    public static Set getOverriddenDeclarations(CallableMemberDescriptor callableMemberDescriptor0) {
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(15);
        }
        Set set0 = new LinkedHashSet();
        OverridingUtil.collectOverriddenDeclarations(callableMemberDescriptor0, set0);
        return set0;
    }

    private static boolean isAccessorMoreSpecific(PropertyAccessorDescriptor propertyAccessorDescriptor0, PropertyAccessorDescriptor propertyAccessorDescriptor1) {
        return propertyAccessorDescriptor0 == null || propertyAccessorDescriptor1 == null ? true : OverridingUtil.isVisibilityMoreSpecific(propertyAccessorDescriptor0, propertyAccessorDescriptor1);
    }

    public static boolean isMoreSpecific(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(67);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(68);
        }
        KotlinType kotlinType0 = callableDescriptor0.getReturnType();
        KotlinType kotlinType1 = callableDescriptor1.getReturnType();
        if(!OverridingUtil.isVisibilityMoreSpecific(callableDescriptor0, callableDescriptor1)) {
            return false;
        }
        List list0 = callableDescriptor0.getTypeParameters();
        List list1 = callableDescriptor1.getTypeParameters();
        TypeCheckerState typeCheckerState0 = OverridingUtil.DEFAULT.createTypeCheckerState(list0, list1);
        if(callableDescriptor0 instanceof FunctionDescriptor) {
            return OverridingUtil.isReturnTypeMoreSpecific(callableDescriptor0, kotlinType0, callableDescriptor1, kotlinType1, typeCheckerState0);
        }
        if(!(callableDescriptor0 instanceof PropertyDescriptor)) {
            throw new IllegalArgumentException("Unexpected callable: " + callableDescriptor0.getClass());
        }
        if(!OverridingUtil.isAccessorMoreSpecific(((PropertyDescriptor)callableDescriptor0).getSetter(), ((PropertyDescriptor)callableDescriptor1).getSetter())) {
            return false;
        }
        if(((PropertyDescriptor)callableDescriptor0).isVar() && ((PropertyDescriptor)callableDescriptor1).isVar()) {
            UnwrappedType unwrappedType0 = kotlinType0.unwrap();
            UnwrappedType unwrappedType1 = kotlinType1.unwrap();
            return AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerState0, unwrappedType0, unwrappedType1);
        }
        return (((PropertyDescriptor)callableDescriptor0).isVar() || !((PropertyDescriptor)callableDescriptor1).isVar()) && OverridingUtil.isReturnTypeMoreSpecific(callableDescriptor0, kotlinType0, callableDescriptor1, kotlinType1, typeCheckerState0);
    }

    private static boolean isMoreSpecificThenAllOf(CallableDescriptor callableDescriptor0, Collection collection0) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(71);
        }
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(72);
        }
        for(Object object0: collection0) {
            if(!OverridingUtil.isMoreSpecific(callableDescriptor0, ((CallableDescriptor)object0))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, ClassDescriptor classDescriptor0) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(19);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(20);
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = this.isOverridableBy(callableDescriptor0, callableDescriptor1, classDescriptor0, false);
        if(overridingUtil$OverrideCompatibilityInfo0 == null) {
            OverridingUtil.$$$reportNull$$$0(21);
        }
        return overridingUtil$OverrideCompatibilityInfo0;
    }

    public OverrideCompatibilityInfo isOverridableBy(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, ClassDescriptor classDescriptor0, boolean z) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(22);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(23);
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = this.isOverridableByWithoutExternalConditions(callableDescriptor0, callableDescriptor1, z);
        boolean z1 = overridingUtil$OverrideCompatibilityInfo0.getResult() == Result.OVERRIDABLE;
        for(Object object0: OverridingUtil.EXTERNAL_CONDITIONS) {
            ExternalOverridabilityCondition externalOverridabilityCondition0 = (ExternalOverridabilityCondition)object0;
            if(externalOverridabilityCondition0.getContract() != Contract.CONFLICTS_ONLY && (!z1 || externalOverridabilityCondition0.getContract() != Contract.SUCCESS_ONLY)) {
                switch(kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.8.$SwitchMap$org$jetbrains$kotlin$resolve$ExternalOverridabilityCondition$Result[externalOverridabilityCondition0.isOverridable(callableDescriptor0, callableDescriptor1, classDescriptor0).ordinal()]) {
                    case 1: {
                        z1 = true;
                        break;
                    }
                    case 2: {
                        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo1 = OverrideCompatibilityInfo.conflict("External condition failed");
                        if(overridingUtil$OverrideCompatibilityInfo1 == null) {
                            OverridingUtil.$$$reportNull$$$0(24);
                        }
                        return overridingUtil$OverrideCompatibilityInfo1;
                    }
                    case 3: {
                        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo2 = OverrideCompatibilityInfo.incompatible("External condition");
                        if(overridingUtil$OverrideCompatibilityInfo2 == null) {
                            OverridingUtil.$$$reportNull$$$0(25);
                        }
                        return overridingUtil$OverrideCompatibilityInfo2;
                    }
                }
            }
        }
        if(!z1) {
            return overridingUtil$OverrideCompatibilityInfo0;
        }
        for(Object object1: OverridingUtil.EXTERNAL_CONDITIONS) {
            ExternalOverridabilityCondition externalOverridabilityCondition1 = (ExternalOverridabilityCondition)object1;
            if(externalOverridabilityCondition1.getContract() != Contract.CONFLICTS_ONLY) {
                continue;
            }
            switch(kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.8.$SwitchMap$org$jetbrains$kotlin$resolve$ExternalOverridabilityCondition$Result[externalOverridabilityCondition1.isOverridable(callableDescriptor0, callableDescriptor1, classDescriptor0).ordinal()]) {
                case 1: {
                    throw new IllegalStateException("Contract violation in " + externalOverridabilityCondition1.getClass().getName() + " condition. It\'s not supposed to end with success");
                }
                case 2: {
                    OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo3 = OverrideCompatibilityInfo.conflict("External condition failed");
                    if(overridingUtil$OverrideCompatibilityInfo3 == null) {
                        OverridingUtil.$$$reportNull$$$0(27);
                    }
                    return overridingUtil$OverrideCompatibilityInfo3;
                }
                case 3: {
                    break;
                }
                default: {
                    continue;
                }
            }
            OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo4 = OverrideCompatibilityInfo.incompatible("External condition");
            if(overridingUtil$OverrideCompatibilityInfo4 == null) {
                OverridingUtil.$$$reportNull$$$0(28);
            }
            return overridingUtil$OverrideCompatibilityInfo4;
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo5 = OverrideCompatibilityInfo.success();
        if(overridingUtil$OverrideCompatibilityInfo5 == null) {
            OverridingUtil.$$$reportNull$$$0(29);
        }
        return overridingUtil$OverrideCompatibilityInfo5;
    }

    public OverrideCompatibilityInfo isOverridableByWithoutExternalConditions(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, boolean z) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(30);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(0x1F);
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = OverridingUtil.getBasicOverridabilityProblem(callableDescriptor0, callableDescriptor1);
        if(overridingUtil$OverrideCompatibilityInfo0 != null) {
            if(overridingUtil$OverrideCompatibilityInfo0 == null) {
                OverridingUtil.$$$reportNull$$$0(0x20);
            }
            return overridingUtil$OverrideCompatibilityInfo0;
        }
        List list0 = OverridingUtil.compiledValueParameters(callableDescriptor0);
        List list1 = OverridingUtil.compiledValueParameters(callableDescriptor1);
        List list2 = callableDescriptor0.getTypeParameters();
        List list3 = callableDescriptor1.getTypeParameters();
        int v = 0;
        if(list2.size() != list3.size()) {
            while(v < list0.size()) {
                KotlinType kotlinType0 = (KotlinType)list0.get(v);
                KotlinType kotlinType1 = (KotlinType)list1.get(v);
                if(!KotlinTypeChecker.DEFAULT.equalTypes(kotlinType0, kotlinType1)) {
                    OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo1 = OverrideCompatibilityInfo.incompatible("Type parameter number mismatch");
                    if(overridingUtil$OverrideCompatibilityInfo1 == null) {
                        OverridingUtil.$$$reportNull$$$0(33);
                    }
                    return overridingUtil$OverrideCompatibilityInfo1;
                }
                ++v;
            }
            OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo2 = OverrideCompatibilityInfo.conflict("Type parameter number mismatch");
            if(overridingUtil$OverrideCompatibilityInfo2 == null) {
                OverridingUtil.$$$reportNull$$$0(34);
            }
            return overridingUtil$OverrideCompatibilityInfo2;
        }
        TypeCheckerState typeCheckerState0 = this.createTypeCheckerState(list2, list3);
        for(int v1 = 0; v1 < list2.size(); ++v1) {
            if(!OverridingUtil.areTypeParametersEquivalent(((TypeParameterDescriptor)list2.get(v1)), ((TypeParameterDescriptor)list3.get(v1)), typeCheckerState0)) {
                OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo3 = OverrideCompatibilityInfo.incompatible("Type parameter bounds mismatch");
                if(overridingUtil$OverrideCompatibilityInfo3 == null) {
                    OverridingUtil.$$$reportNull$$$0(35);
                }
                return overridingUtil$OverrideCompatibilityInfo3;
            }
        }
        while(v < list0.size()) {
            if(!OverridingUtil.areTypesEquivalent(((KotlinType)list0.get(v)), ((KotlinType)list1.get(v)), typeCheckerState0)) {
                OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo4 = OverrideCompatibilityInfo.incompatible("Value parameter type mismatch");
                if(overridingUtil$OverrideCompatibilityInfo4 == null) {
                    OverridingUtil.$$$reportNull$$$0(36);
                }
                return overridingUtil$OverrideCompatibilityInfo4;
            }
            ++v;
        }
        if(callableDescriptor0 instanceof FunctionDescriptor && callableDescriptor1 instanceof FunctionDescriptor && ((FunctionDescriptor)callableDescriptor0).isSuspend() != ((FunctionDescriptor)callableDescriptor1).isSuspend()) {
            OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo5 = OverrideCompatibilityInfo.conflict("Incompatible suspendability");
            if(overridingUtil$OverrideCompatibilityInfo5 == null) {
                OverridingUtil.$$$reportNull$$$0(37);
            }
            return overridingUtil$OverrideCompatibilityInfo5;
        }
        if(z) {
            KotlinType kotlinType2 = callableDescriptor0.getReturnType();
            KotlinType kotlinType3 = callableDescriptor1.getReturnType();
            if(kotlinType2 != null && kotlinType3 != null && (!KotlinTypeKt.isError(kotlinType3) || !KotlinTypeKt.isError(kotlinType2))) {
                UnwrappedType unwrappedType0 = kotlinType3.unwrap();
                UnwrappedType unwrappedType1 = kotlinType2.unwrap();
                if(!AbstractTypeChecker.INSTANCE.isSubtypeOf(typeCheckerState0, unwrappedType0, unwrappedType1)) {
                    OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo6 = OverrideCompatibilityInfo.conflict("Return type mismatch");
                    if(overridingUtil$OverrideCompatibilityInfo6 == null) {
                        OverridingUtil.$$$reportNull$$$0(38);
                    }
                    return overridingUtil$OverrideCompatibilityInfo6;
                }
            }
        }
        OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo7 = OverrideCompatibilityInfo.success();
        if(overridingUtil$OverrideCompatibilityInfo7 == null) {
            OverridingUtil.$$$reportNull$$$0(39);
        }
        return overridingUtil$OverrideCompatibilityInfo7;
    }

    private static boolean isReturnTypeMoreSpecific(CallableDescriptor callableDescriptor0, KotlinType kotlinType0, CallableDescriptor callableDescriptor1, KotlinType kotlinType1, TypeCheckerState typeCheckerState0) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(73);
        }
        if(kotlinType0 == null) {
            OverridingUtil.$$$reportNull$$$0(74);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(75);
        }
        if(kotlinType1 == null) {
            OverridingUtil.$$$reportNull$$$0(76);
        }
        if(typeCheckerState0 == null) {
            OverridingUtil.$$$reportNull$$$0(77);
        }
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        UnwrappedType unwrappedType1 = kotlinType1.unwrap();
        return AbstractTypeChecker.INSTANCE.isSubtypeOf(typeCheckerState0, unwrappedType0, unwrappedType1);
    }

    private static boolean isVisibilityMoreSpecific(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility0, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility1) {
        if(declarationDescriptorWithVisibility0 == null) {
            OverridingUtil.$$$reportNull$$$0(69);
        }
        if(declarationDescriptorWithVisibility1 == null) {
            OverridingUtil.$$$reportNull$$$0(70);
        }
        Integer integer0 = DescriptorVisibilities.compare(declarationDescriptorWithVisibility0.getVisibility(), declarationDescriptorWithVisibility1.getVisibility());
        return integer0 == null || ((int)integer0) >= 0;
    }

    public static boolean isVisibleForOverride(MemberDescriptor memberDescriptor0, MemberDescriptor memberDescriptor1, boolean z) {
        if(memberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(57);
        }
        if(memberDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(58);
        }
        return !DescriptorVisibilities.isPrivate(memberDescriptor1.getVisibility()) && DescriptorVisibilities.isVisibleIgnoringReceiver(memberDescriptor1, memberDescriptor0, z);
    }

    public static boolean overrides(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, boolean z, boolean z1) {
        if(callableDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(13);
        }
        if(callableDescriptor1 == null) {
            OverridingUtil.$$$reportNull$$$0(14);
        }
        if(!callableDescriptor0.equals(callableDescriptor1)) {
            CallableDescriptor callableDescriptor2 = callableDescriptor0.getOriginal();
            CallableDescriptor callableDescriptor3 = callableDescriptor1.getOriginal();
            if(DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(callableDescriptor2, callableDescriptor3, z, z1)) {
                return true;
            }
        }
        CallableDescriptor callableDescriptor4 = callableDescriptor1.getOriginal();
        for(Object object0: DescriptorUtils.getAllOverriddenDescriptors(callableDescriptor0)) {
            if(DescriptorEquivalenceForOverrides.INSTANCE.areEquivalent(callableDescriptor4, ((CallableDescriptor)object0), z, z1)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static void resolveUnknownVisibilityForMember(CallableMemberDescriptor callableMemberDescriptor0, Function1 function10) {
        DescriptorVisibility descriptorVisibility1;
        if(callableMemberDescriptor0 == null) {
            OverridingUtil.$$$reportNull$$$0(107);
        }
        for(Object object0: callableMemberDescriptor0.getOverriddenDescriptors()) {
            CallableMemberDescriptor callableMemberDescriptor1 = (CallableMemberDescriptor)object0;
            if(callableMemberDescriptor1.getVisibility() == DescriptorVisibilities.INHERITED) {
                OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor1, function10);
            }
        }
        if(callableMemberDescriptor0.getVisibility() == DescriptorVisibilities.INHERITED) {
            DescriptorVisibility descriptorVisibility0 = OverridingUtil.computeVisibilityToInherit(callableMemberDescriptor0);
            if(descriptorVisibility0 == null) {
                if(function10 != null) {
                    function10.invoke(callableMemberDescriptor0);
                }
                descriptorVisibility1 = DescriptorVisibilities.PUBLIC;
            }
            else {
                descriptorVisibility1 = descriptorVisibility0;
            }
            if(callableMemberDescriptor0 instanceof PropertyDescriptorImpl) {
                ((PropertyDescriptorImpl)callableMemberDescriptor0).setVisibility(descriptorVisibility1);
                for(Object object1: ((PropertyDescriptor)callableMemberDescriptor0).getAccessors()) {
                    OverridingUtil.resolveUnknownVisibilityForMember(((PropertyAccessorDescriptor)object1), (descriptorVisibility0 == null ? null : function10));
                }
                return;
            }
            if(callableMemberDescriptor0 instanceof FunctionDescriptorImpl) {
                ((FunctionDescriptorImpl)callableMemberDescriptor0).setVisibility(descriptorVisibility1);
                return;
            }
            ((PropertyAccessorDescriptorImpl)callableMemberDescriptor0).setVisibility(descriptorVisibility1);
            if(descriptorVisibility1 != ((PropertyAccessorDescriptorImpl)callableMemberDescriptor0).getCorrespondingProperty().getVisibility()) {
                ((PropertyAccessorDescriptorImpl)callableMemberDescriptor0).setDefault(false);
            }
        }
    }

    public static Object selectMostSpecificMember(Collection collection0, Function1 function10) {
        if(collection0 == null) {
            OverridingUtil.$$$reportNull$$$0(78);
        }
        if(function10 == null) {
            OverridingUtil.$$$reportNull$$$0(0x4F);
        }
        if(collection0.size() == 1) {
            Object object0 = CollectionsKt.first(collection0);
            if(object0 == null) {
                OverridingUtil.$$$reportNull$$$0(80);
            }
            return object0;
        }
        ArrayList arrayList0 = new ArrayList(2);
        List list0 = CollectionsKt.map(collection0, function10);
        Object object1 = CollectionsKt.first(collection0);
        CallableDescriptor callableDescriptor0 = (CallableDescriptor)function10.invoke(object1);
        for(Object object2: collection0) {
            CallableDescriptor callableDescriptor1 = (CallableDescriptor)function10.invoke(object2);
            if(OverridingUtil.isMoreSpecificThenAllOf(callableDescriptor1, list0)) {
                arrayList0.add(object2);
            }
            if(OverridingUtil.isMoreSpecific(callableDescriptor1, callableDescriptor0) && !OverridingUtil.isMoreSpecific(callableDescriptor0, callableDescriptor1)) {
                object1 = object2;
            }
        }
        if(arrayList0.isEmpty()) {
            if(object1 == null) {
                OverridingUtil.$$$reportNull$$$0(81);
            }
            return object1;
        }
        if(arrayList0.size() == 1) {
            Object object3 = CollectionsKt.first(arrayList0);
            if(object3 == null) {
                OverridingUtil.$$$reportNull$$$0(82);
            }
            return object3;
        }
        Object object4 = null;
        for(Object object5: arrayList0) {
            if(!FlexibleTypesKt.isFlexible(((CallableDescriptor)function10.invoke(object5)).getReturnType())) {
                object4 = object5;
                break;
            }
        }
        if(object4 != null) {
            if(object4 == null) {
                OverridingUtil.$$$reportNull$$$0(83);
            }
            return object4;
        }
        Object object6 = CollectionsKt.first(arrayList0);
        if(object6 == null) {
            OverridingUtil.$$$reportNull$$$0(84);
        }
        return object6;
    }
}

