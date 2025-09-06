package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped.Reason;

public final class SQLiteEventStore..ExternalSyntheticLambda12 implements Function {
    public final String f$0;
    public final Reason f$1;
    public final long f$2;

    public SQLiteEventStore..ExternalSyntheticLambda12(String s, Reason logEventDropped$Reason0, long v) {
        this.f$0 = s;
        this.f$1 = logEventDropped$Reason0;
        this.f$2 = v;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return SQLiteEventStore.lambda$recordLogEventDropped$18(this.f$0, this.f$1, this.f$2, ((SQLiteDatabase)object0));
    }
}

