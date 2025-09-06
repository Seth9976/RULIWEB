package androidx.core.os;

import androidx.core.util.ObjectsCompat;

public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String s) {
        super(ObjectsCompat.toString(s, "The operation has been canceled."));
    }
}

