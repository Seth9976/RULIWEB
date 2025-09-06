package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

public final class InlineClassRepresentation extends ValueClassRepresentation {
    private final Name underlyingPropertyName;
    private final SimpleTypeMarker underlyingType;

    public InlineClassRepresentation(Name name0, SimpleTypeMarker simpleTypeMarker0) {
        Intrinsics.checkNotNullParameter(name0, "underlyingPropertyName");
        Intrinsics.checkNotNullParameter(simpleTypeMarker0, "underlyingType");
        super(null);
        this.underlyingPropertyName = name0;
        this.underlyingType = simpleTypeMarker0;
    }

    public final Name getUnderlyingPropertyName() {
        return this.underlyingPropertyName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation
    public List getUnderlyingPropertyNamesToTypes() {
        return CollectionsKt.listOf(TuplesKt.to(this.underlyingPropertyName, this.underlyingType));
    }

    public final SimpleTypeMarker getUnderlyingType() {
        return this.underlyingType;
    }

    @Override
    public String toString() {
        return "InlineClassRepresentation(underlyingPropertyName=" + this.underlyingPropertyName + ", underlyingType=" + this.underlyingType + ')';
    }
}

