package kotlin.reflect.jvm.internal.impl.util;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitClassReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class OperatorChecks extends AbstractModifierChecks {
    public static final OperatorChecks INSTANCE;
    private static final List checks;

    static {
        OperatorChecks.INSTANCE = new OperatorChecks();
        Checks[] arr_checks = new Checks[19];
        Check[] arr_check = {MemberOrExtension.INSTANCE, new AtLeast(1)};
        arr_checks[0] = new Checks(OperatorNameConventions.GET, arr_check, null, 4, null);
        Check[] arr_check1 = {MemberOrExtension.INSTANCE, new AtLeast(2)};
        arr_checks[1] = new Checks(OperatorNameConventions.SET, arr_check1, OperatorChecks.checks.1.INSTANCE);
        Check[] arr_check2 = {MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new AtLeast(2), IsKPropertyCheck.INSTANCE};
        arr_checks[2] = new Checks(OperatorNameConventions.GET_VALUE, arr_check2, null, 4, null);
        Check[] arr_check3 = {MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new AtLeast(3), IsKPropertyCheck.INSTANCE};
        arr_checks[3] = new Checks(OperatorNameConventions.SET_VALUE, arr_check3, null, 4, null);
        Check[] arr_check4 = {MemberOrExtension.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, new Equals(2), IsKPropertyCheck.INSTANCE};
        arr_checks[4] = new Checks(OperatorNameConventions.PROVIDE_DELEGATE, arr_check4, null, 4, null);
        arr_checks[5] = new Checks(OperatorNameConventions.INVOKE, new Check[]{MemberOrExtension.INSTANCE}, null, 4, null);
        arr_checks[6] = new Checks(OperatorNameConventions.CONTAINS, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE, ReturnsBoolean.INSTANCE}, null, 4, null);
        arr_checks[7] = new Checks(OperatorNameConventions.ITERATOR, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, null, 4, null);
        arr_checks[8] = new Checks(OperatorNameConventions.NEXT, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, null, 4, null);
        arr_checks[9] = new Checks(OperatorNameConventions.HAS_NEXT, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE, ReturnsBoolean.INSTANCE}, null, 4, null);
        arr_checks[10] = new Checks(OperatorNameConventions.RANGE_TO, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, null, 4, null);
        arr_checks[11] = new Checks(OperatorNameConventions.RANGE_UNTIL, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, null, 4, null);
        arr_checks[12] = new Checks(OperatorNameConventions.EQUALS, new Check[]{Member.INSTANCE}, OperatorChecks.checks.2.INSTANCE);
        arr_checks[13] = new Checks(OperatorNameConventions.COMPARE_TO, new Check[]{MemberOrExtension.INSTANCE, ReturnsInt.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, null, 4, null);
        arr_checks[14] = new Checks(OperatorNameConventions.BINARY_OPERATION_NAMES, new Check[]{MemberOrExtension.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, null, 4, null);
        arr_checks[15] = new Checks(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, null, 4, null);
        arr_checks[16] = new Checks(CollectionsKt.listOf(new Name[]{OperatorNameConventions.INC, OperatorNameConventions.DEC}), new Check[]{MemberOrExtension.INSTANCE}, OperatorChecks.checks.3.INSTANCE);
        arr_checks[17] = new Checks(OperatorNameConventions.ASSIGNMENT_OPERATIONS, new Check[]{MemberOrExtension.INSTANCE, ReturnsUnit.INSTANCE, SingleValueParameter.INSTANCE, NoDefaultAndVarargsCheck.INSTANCE}, null, 4, null);
        arr_checks[18] = new Checks(OperatorNameConventions.COMPONENT_REGEX, new Check[]{MemberOrExtension.INSTANCE, NoValueParameters.INSTANCE}, null, 4, null);
        OperatorChecks.checks = CollectionsKt.listOf(arr_checks);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.util.AbstractModifierChecks
    public List getChecks$descriptors() {
        return OperatorChecks.checks;
    }

    private final boolean incDecCheckForExpectClass(FunctionDescriptor functionDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor0) {
        ReceiverValue receiverValue0 = receiverParameterDescriptor0.getValue();
        Intrinsics.checkNotNullExpressionValue(receiverValue0, "receiver.value");
        if(!(receiverValue0 instanceof ImplicitClassReceiver)) {
            return false;
        }
        ClassDescriptor classDescriptor0 = ((ImplicitClassReceiver)receiverValue0).getClassDescriptor();
        if(!classDescriptor0.isExpect()) {
            return false;
        }
        ClassId classId0 = DescriptorUtilsKt.getClassId(classDescriptor0);
        if(classId0 == null) {
            return false;
        }
        ClassifierDescriptor classifierDescriptor0 = FindClassInModuleKt.findClassifierAcrossModuleDependencies(DescriptorUtilsKt.getModule(classDescriptor0), classId0);
        TypeAliasDescriptor typeAliasDescriptor0 = classifierDescriptor0 instanceof TypeAliasDescriptor ? ((TypeAliasDescriptor)classifierDescriptor0) : null;
        if(typeAliasDescriptor0 == null) {
            return false;
        }
        KotlinType kotlinType0 = functionDescriptor0.getReturnType();
        return kotlinType0 == null ? false : TypeUtilsKt.isSubtypeOf(kotlinType0, typeAliasDescriptor0.getExpandedType());
    }
}

