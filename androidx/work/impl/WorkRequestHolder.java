package androidx.work.impl;

import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import java.util.Set;
import java.util.UUID;

public class WorkRequestHolder extends WorkRequest {
    public WorkRequestHolder(UUID uUID0, WorkSpec workSpec0, Set set0) {
        super(uUID0, workSpec0, set0);
    }
}

