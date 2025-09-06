package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;

public interface TypeParameterDescriptor extends ClassifierDescriptor, TypeParameterMarker {
    int getIndex();

    TypeParameterDescriptor getOriginal();

    StorageManager getStorageManager();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    TypeConstructor getTypeConstructor();

    List getUpperBounds();

    Variance getVariance();

    boolean isCapturedFromOuterDeclaration();

    boolean isReified();
}

