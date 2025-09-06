package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public interface ClassDescriptorFactory {
    ClassDescriptor createClass(ClassId arg1);

    Collection getAllContributedClassesIfPossible(FqName arg1);

    boolean shouldCreateClass(FqName arg1, Name arg2);
}

