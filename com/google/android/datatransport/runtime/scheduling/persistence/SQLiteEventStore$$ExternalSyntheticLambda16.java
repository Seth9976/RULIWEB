package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class SQLiteEventStore..ExternalSyntheticLambda16 implements Function {
    public final long f$0;

    public SQLiteEventStore..ExternalSyntheticLambda16(long v) {
        this.f$0 = v;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return SQLiteEventStore.lambda$getTimeWindow$22(this.f$0, ((SQLiteDatabase)object0));
    }
}

