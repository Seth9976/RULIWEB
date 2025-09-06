package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;

public final class SQLiteEventStore..ExternalSyntheticLambda18 implements Function {
    public final long f$0;

    public SQLiteEventStore..ExternalSyntheticLambda18(long v) {
        this.f$0 = v;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return SQLiteEventStore.lambda$getTimeWindow$21(this.f$0, ((Cursor)object0));
    }
}

