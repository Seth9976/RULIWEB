package kotlin.reflect.jvm.internal.impl.load.java.descriptors;

import java.util.List;
import kotlin.Pair;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface JavaCallableMemberDescriptor extends CallableMemberDescriptor {
    JavaCallableMemberDescriptor enhance(KotlinType arg1, List arg2, KotlinType arg3, Pair arg4);
}

