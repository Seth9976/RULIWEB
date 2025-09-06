package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class MultiFieldValueClassRepresentation extends ValueClassRepresentation {
    private final Map map;
    private final List underlyingPropertyNamesToTypes;

    public MultiFieldValueClassRepresentation(List list0) {
        Intrinsics.checkNotNullParameter(list0, "underlyingPropertyNamesToTypes");
        super(null);
        this.underlyingPropertyNamesToTypes = list0;
        Map map0 = MapsKt.toMap(this.getUnderlyingPropertyNamesToTypes());
        if(map0.size() != this.getUnderlyingPropertyNamesToTypes().size()) {
            throw new IllegalArgumentException("Some properties have the same names");
        }
        this.map = map0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation
    public List getUnderlyingPropertyNamesToTypes() {
        return this.underlyingPropertyNamesToTypes;
    }

    @Override
    public String toString() {
        return "MultiFieldValueClassRepresentation(underlyingPropertyNamesToTypes=" + this.getUnderlyingPropertyNamesToTypes() + ')';
    }
}

