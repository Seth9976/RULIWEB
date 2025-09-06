package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;

public final class SQLiteEventStore..ExternalSyntheticLambda7 implements Function {
    public final long f$0;
    public final TransportContext f$1;

    public SQLiteEventStore..ExternalSyntheticLambda7(long v, TransportContext transportContext0) {
        this.f$0 = v;
        this.f$1 = transportContext0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return SQLiteEventStore.lambda$recordNextCallTime$7(this.f$0, this.f$1, ((SQLiteDatabase)object0));
    }
}

