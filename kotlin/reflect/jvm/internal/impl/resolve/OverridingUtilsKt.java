package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import java.util.LinkedList;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public final class OverridingUtilsKt {
    public static final Collection selectMostSpecificInEachOverridableGroup(Collection collection0, Function1 function10) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "descriptorByHandle");
        if(collection0.size() <= 1) {
            return collection0;
        }
        LinkedList linkedList0 = new LinkedList(collection0);
        SmartSet smartSet0 = SmartSet.Companion.create();
        while(!linkedList0.isEmpty()) {
            Object object0 = CollectionsKt.first(linkedList0);
            SmartSet smartSet1 = SmartSet.Companion.create();
            Collection collection1 = OverridingUtil.extractMembersOverridableInBothWays(object0, linkedList0, function10, new Function1(smartSet1) {
                final SmartSet $conflictedHandles;

                {
                    this.$conflictedHandles = smartSet0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(object0);
                    return Unit.INSTANCE;
                }

                public final void invoke(Object object0) {
                    Intrinsics.checkNotNullExpressionValue(object0, "it");
                    this.$conflictedHandles.add(object0);
                }
            });
            Intrinsics.checkNotNullExpressionValue(collection1, "conflictedHandles = Smar…nflictedHandles.add(it) }");
            if(collection1.size() == 1 && smartSet1.isEmpty()) {
                Object object1 = CollectionsKt.single(collection1);
                Intrinsics.checkNotNullExpressionValue(object1, "overridableGroup.single()");
                smartSet0.add(object1);
            }
            else {
                Object object2 = OverridingUtil.selectMostSpecificMember(collection1, function10);
                Intrinsics.checkNotNullExpressionValue(object2, "selectMostSpecificMember…roup, descriptorByHandle)");
                CallableDescriptor callableDescriptor0 = (CallableDescriptor)function10.invoke(object2);
                for(Object object3: collection1) {
                    Intrinsics.checkNotNullExpressionValue(object3, "it");
                    if(!OverridingUtil.isMoreSpecific(callableDescriptor0, ((CallableDescriptor)function10.invoke(object3)))) {
                        smartSet1.add(object3);
                    }
                }
                if(!smartSet1.isEmpty()) {
                    smartSet0.addAll(smartSet1);
                }
                smartSet0.add(object2);
            }
        }
        return smartSet0;
    }
}

