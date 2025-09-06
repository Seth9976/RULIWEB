package kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers;

import kotlin.reflect.jvm.internal.impl.name.Name;

public interface ImplicitContextReceiver extends ImplicitReceiver {
    Name getCustomLabelName();
}

