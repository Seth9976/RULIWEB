package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import java.util.Map;

public final class SQLiteEventStore..ExternalSyntheticLambda9 implements Function {
    public final Map f$0;

    public SQLiteEventStore..ExternalSyntheticLambda9(Map map0) {
        this.f$0 = map0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return SQLiteEventStore.lambda$loadMetadata$16(this.f$0, ((Cursor)object0));
    }
}

