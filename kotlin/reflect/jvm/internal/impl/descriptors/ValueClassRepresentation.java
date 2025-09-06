package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

public abstract class ValueClassRepresentation {
    private ValueClassRepresentation() {
    }

    public ValueClassRepresentation(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract List getUnderlyingPropertyNamesToTypes();

    public final ValueClassRepresentation mapUnderlyingType(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        if(this instanceof InlineClassRepresentation) {
            return new InlineClassRepresentation(((InlineClassRepresentation)this).getUnderlyingPropertyName(), ((SimpleTypeMarker)function10.invoke(((InlineClassRepresentation)this).getUnderlyingType())));
        }
        if(!(this instanceof MultiFieldValueClassRepresentation)) {
            throw new NoWhenBranchMatchedException();
        }
        Iterable iterable0 = this.getUnderlyingPropertyNamesToTypes();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(TuplesKt.to(((Name)((Pair)object0).component1()), function10.invoke(((SimpleTypeMarker)((Pair)object0).component2()))));
        }
        return new MultiFieldValueClassRepresentation(arrayList0);
    }
}

