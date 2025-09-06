package androidx.work.impl.model;

import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class WorkTagDao.-CC {
    public static void $default$insertTags(WorkTagDao _this, String s, Set set0) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(set0, "tags");
        for(Object object0: set0) {
            _this.insert(new WorkTag(((String)object0), s));
        }
    }
}

