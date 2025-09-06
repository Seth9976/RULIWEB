package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0005H\u0016J\u0012\u0010\n\u001A\u0004\u0018\u00010\u00062\u0006\u0010\t\u001A\u00020\u0005H\u0016J\u0016\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00060\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016J\u0010\u0010\u000E\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0005H\u0016R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/work/impl/StartStopTokensImpl;", "Landroidx/work/impl/StartStopTokens;", "()V", "runs", "", "Landroidx/work/impl/model/WorkGenerationalId;", "Landroidx/work/impl/StartStopToken;", "contains", "", "id", "remove", "", "workSpecId", "", "tokenFor", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StartStopTokensImpl implements StartStopTokens {
    private final Map runs;

    public StartStopTokensImpl() {
        this.runs = new LinkedHashMap();
    }

    @Override  // androidx.work.impl.StartStopTokens
    public boolean contains(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        return this.runs.containsKey(workGenerationalId0);
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken remove(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        return (StartStopToken)this.runs.remove(workGenerationalId0);
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken remove(WorkSpec workSpec0) {
        return StartStopTokens.-CC.$default$remove(this, workSpec0);
    }

    @Override  // androidx.work.impl.StartStopTokens
    public List remove(String s) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        for(Object object0: this.runs.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(Intrinsics.areEqual(((WorkGenerationalId)map$Entry0.getKey()).getWorkSpecId(), s)) {
                linkedHashMap0.put(map$Entry0.getKey(), map$Entry0.getValue());
            }
        }
        for(Object object1: linkedHashMap0.keySet()) {
            this.runs.remove(((WorkGenerationalId)object1));
        }
        return CollectionsKt.toList(linkedHashMap0.values());
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken tokenFor(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        Map map0 = this.runs;
        StartStopToken startStopToken0 = map0.get(workGenerationalId0);
        if(startStopToken0 == null) {
            startStopToken0 = new StartStopToken(workGenerationalId0);
            map0.put(workGenerationalId0, startStopToken0);
        }
        return startStopToken0;
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken tokenFor(WorkSpec workSpec0) {
        return StartStopTokens.-CC.$default$tokenFor(this, workSpec0);
    }
}

