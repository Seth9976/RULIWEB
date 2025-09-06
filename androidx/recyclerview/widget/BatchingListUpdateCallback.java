package androidx.recyclerview.widget;

public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;
    int mLastEventCount;
    Object mLastEventPayload;
    int mLastEventPosition;
    int mLastEventType;
    final ListUpdateCallback mWrapped;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback0) {
        this.mLastEventType = 0;
        this.mLastEventPosition = -1;
        this.mLastEventCount = -1;
        this.mLastEventPayload = null;
        this.mWrapped = listUpdateCallback0;
    }

    public void dispatchLastEvent() {
        int v = this.mLastEventType;
        if(v == 0) {
            return;
        }
        switch(v) {
            case 1: {
                this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
                break;
            }
            case 2: {
                this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
                break;
            }
            case 3: {
                this.mWrapped.onChanged(this.mLastEventPosition, this.mLastEventCount, this.mLastEventPayload);
            }
        }
        this.mLastEventPayload = null;
        this.mLastEventType = 0;
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int v, int v1, Object object0) {
        if(this.mLastEventType == 3) {
            int v2 = this.mLastEventPosition;
            int v3 = this.mLastEventCount;
            if(v <= v2 + v3) {
                int v4 = v + v1;
                if(v4 >= v2 && this.mLastEventPayload == object0) {
                    this.mLastEventPosition = Math.min(v, v2);
                    this.mLastEventCount = Math.max(v3 + v2, v4) - this.mLastEventPosition;
                    return;
                }
            }
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = v;
        this.mLastEventCount = v1;
        this.mLastEventPayload = object0;
        this.mLastEventType = 3;
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int v, int v1) {
        if(this.mLastEventType == 1) {
            int v2 = this.mLastEventPosition;
            if(v >= v2) {
                int v3 = this.mLastEventCount;
                if(v <= v2 + v3) {
                    this.mLastEventCount = v3 + v1;
                    this.mLastEventPosition = Math.min(v, v2);
                    return;
                }
            }
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = v;
        this.mLastEventCount = v1;
        this.mLastEventType = 1;
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int v, int v1) {
        this.dispatchLastEvent();
        this.mWrapped.onMoved(v, v1);
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int v, int v1) {
        if(this.mLastEventType == 2 && (this.mLastEventPosition >= v && this.mLastEventPosition <= v + v1)) {
            this.mLastEventCount += v1;
            this.mLastEventPosition = v;
            return;
        }
        this.dispatchLastEvent();
        this.mLastEventPosition = v;
        this.mLastEventCount = v1;
        this.mLastEventType = 2;
    }
}

