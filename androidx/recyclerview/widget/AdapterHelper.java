package androidx.recyclerview.widget;

import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SimplePool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AdapterHelper implements Callback {
    interface androidx.recyclerview.widget.AdapterHelper.Callback {
        ViewHolder findViewHolder(int arg1);

        void markViewHoldersUpdated(int arg1, int arg2, Object arg3);

        void offsetPositionsForAdd(int arg1, int arg2);

        void offsetPositionsForMove(int arg1, int arg2);

        void offsetPositionsForRemovingInvisible(int arg1, int arg2);

        void offsetPositionsForRemovingLaidOutOrNewView(int arg1, int arg2);

        void onDispatchFirstPass(UpdateOp arg1);

        void onDispatchSecondPass(UpdateOp arg1);
    }

    static final class UpdateOp {
        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int v, int v1, int v2, Object object0) {
            this.cmd = v;
            this.positionStart = v1;
            this.itemCount = v2;
            this.payload = object0;
        }

        String cmdToString() {
            switch(this.cmd) {
                case 1: {
                    return "add";
                }
                case 2: {
                    return "rm";
                }
                case 4: {
                    return "up";
                }
                case 8: {
                    return "mv";
                }
                default: {
                    return "??";
                }
            }
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof UpdateOp)) {
                return false;
            }
            int v = this.cmd;
            if(v != ((UpdateOp)object0).cmd) {
                return false;
            }
            if(v == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == ((UpdateOp)object0).positionStart && this.positionStart == ((UpdateOp)object0).itemCount) {
                return true;
            }
            if(this.itemCount != ((UpdateOp)object0).itemCount) {
                return false;
            }
            if(this.positionStart != ((UpdateOp)object0).positionStart) {
                return false;
            }
            return this.payload == null ? ((UpdateOp)object0).payload == null : this.payload.equals(((UpdateOp)object0).payload);
        }

        @Override
        public int hashCode() {
            return (this.cmd * 0x1F + this.positionStart) * 0x1F + this.itemCount;
        }

        @Override
        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + this.cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }
    }

    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final androidx.recyclerview.widget.AdapterHelper.Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList mPendingUpdates;
    final ArrayList mPostponedList;
    private Pool mUpdateOpPool;

    AdapterHelper(androidx.recyclerview.widget.AdapterHelper.Callback adapterHelper$Callback0) {
        this(adapterHelper$Callback0, false);
    }

    AdapterHelper(androidx.recyclerview.widget.AdapterHelper.Callback adapterHelper$Callback0, boolean z) {
        this.mUpdateOpPool = new SimplePool(30);
        this.mPendingUpdates = new ArrayList();
        this.mPostponedList = new ArrayList();
        this.mExistingUpdateTypes = 0;
        this.mCallback = adapterHelper$Callback0;
        this.mDisableRecycler = z;
        this.mOpReorderer = new OpReorderer(this);
    }

    AdapterHelper addUpdateOp(UpdateOp[] arr_adapterHelper$UpdateOp) {
        Collections.addAll(this.mPendingUpdates, arr_adapterHelper$UpdateOp);
        return this;
    }

    private void applyAdd(UpdateOp adapterHelper$UpdateOp0) {
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp0);
    }

    private void applyMove(UpdateOp adapterHelper$UpdateOp0) {
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp0);
    }

    public int applyPendingUpdatesToPosition(int v) {
        int v1 = this.mPendingUpdates.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPendingUpdates.get(v2);
            switch(adapterHelper$UpdateOp0.cmd) {
                case 1: {
                    if(adapterHelper$UpdateOp0.positionStart <= v) {
                        v += adapterHelper$UpdateOp0.itemCount;
                    }
                    break;
                }
                case 2: {
                    if(adapterHelper$UpdateOp0.positionStart <= v) {
                        if(adapterHelper$UpdateOp0.positionStart + adapterHelper$UpdateOp0.itemCount > v) {
                            return -1;
                        }
                        v -= adapterHelper$UpdateOp0.itemCount;
                    }
                    break;
                }
                case 8: {
                    if(adapterHelper$UpdateOp0.positionStart == v) {
                        v = adapterHelper$UpdateOp0.itemCount;
                    }
                    else {
                        if(adapterHelper$UpdateOp0.positionStart < v) {
                            --v;
                        }
                        if(adapterHelper$UpdateOp0.itemCount <= v) {
                            ++v;
                        }
                    }
                }
            }
        }
        return v;
    }

    // This method was un-flattened
    private void applyRemove(UpdateOp adapterHelper$UpdateOp0) {
        int v5;
        int v = adapterHelper$UpdateOp0.positionStart;
        int v1 = adapterHelper$UpdateOp0.positionStart + adapterHelper$UpdateOp0.itemCount;
        int v2 = adapterHelper$UpdateOp0.positionStart;
        int v3 = -1;
        int v4 = 0;
        while(v2 < v1) {
            if(this.mCallback.findViewHolder(v2) != null || this.canFindInPreLayout(v2)) {
            label_13:
                if(v3 == 0) {
                    this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(2, v, v4, null));
                    v5 = 1;
                    v2 -= v4;
                    v1 -= v4;
                    v4 = 1;
                    goto label_22;
                }
                else {
                    v5 = 1;
                }
            }
            else {
                if(v3 == 1) {
                    this.postponeAndUpdateViewHolders(this.obtainUpdateOp(2, v, v4, null));
                    v5 = 0;
                    v2 -= v4;
                    v1 -= v4;
                    v4 = 1;
                    goto label_22;
                }
                else {
                    v5 = 0;
                    goto label_21;
                }
                goto label_13;
            }
        label_21:
            ++v4;
        label_22:
            ++v2;
            v3 = v5;
        }
        if(v4 != adapterHelper$UpdateOp0.itemCount) {
            this.recycleUpdateOp(adapterHelper$UpdateOp0);
            adapterHelper$UpdateOp0 = this.obtainUpdateOp(2, v, v4, null);
        }
        if(v3 == 0) {
            this.dispatchAndUpdateViewHolders(adapterHelper$UpdateOp0);
            return;
        }
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp0);
    }

    private void applyUpdate(UpdateOp adapterHelper$UpdateOp0) {
        int v = adapterHelper$UpdateOp0.positionStart;
        int v1 = adapterHelper$UpdateOp0.positionStart + adapterHelper$UpdateOp0.itemCount;
        int v2 = adapterHelper$UpdateOp0.positionStart;
        int v3 = -1;
        int v4 = 0;
        while(v2 < v1) {
            if(this.mCallback.findViewHolder(v2) != null || this.canFindInPreLayout(v2)) {
                if(v3 == 0) {
                    this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(4, v, v4, adapterHelper$UpdateOp0.payload));
                    v = v2;
                    v4 = 0;
                }
                v3 = 1;
            }
            else {
                if(v3 == 1) {
                    this.postponeAndUpdateViewHolders(this.obtainUpdateOp(4, v, v4, adapterHelper$UpdateOp0.payload));
                    v = v2;
                    v4 = 0;
                }
                v3 = 0;
            }
            ++v4;
            ++v2;
        }
        if(v4 != adapterHelper$UpdateOp0.itemCount) {
            Object object0 = adapterHelper$UpdateOp0.payload;
            this.recycleUpdateOp(adapterHelper$UpdateOp0);
            adapterHelper$UpdateOp0 = this.obtainUpdateOp(4, v, v4, object0);
        }
        if(v3 == 0) {
            this.dispatchAndUpdateViewHolders(adapterHelper$UpdateOp0);
            return;
        }
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp0);
    }

    private boolean canFindInPreLayout(int v) {
        int v1 = this.mPostponedList.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPostponedList.get(v2);
            if(adapterHelper$UpdateOp0.cmd == 8) {
                if(this.findPositionOffset(adapterHelper$UpdateOp0.itemCount, v2 + 1) == v) {
                    return true;
                }
            }
            else if(adapterHelper$UpdateOp0.cmd == 1) {
                int v3 = adapterHelper$UpdateOp0.positionStart + adapterHelper$UpdateOp0.itemCount;
                for(int v4 = adapterHelper$UpdateOp0.positionStart; v4 < v3; ++v4) {
                    if(this.findPositionOffset(v4, v2 + 1) == v) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void consumePostponedUpdates() {
        int v = this.mPostponedList.size();
        for(int v1 = 0; v1 < v; ++v1) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPostponedList.get(v1);
            this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp0);
        }
        this.recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    void consumeUpdatesInOnePass() {
        this.consumePostponedUpdates();
        int v = this.mPendingUpdates.size();
        for(int v1 = 0; v1 < v; ++v1) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPendingUpdates.get(v1);
            switch(adapterHelper$UpdateOp0.cmd) {
                case 1: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp0);
                    this.mCallback.offsetPositionsForAdd(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                    break;
                }
                case 2: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp0);
                    this.mCallback.offsetPositionsForRemovingInvisible(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                    break;
                }
                case 4: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp0);
                    this.mCallback.markViewHoldersUpdated(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount, adapterHelper$UpdateOp0.payload);
                    break;
                }
                case 8: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp0);
                    this.mCallback.offsetPositionsForMove(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                }
            }
            Runnable runnable0 = this.mOnItemProcessedCallback;
            if(runnable0 != null) {
                runnable0.run();
            }
        }
        this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp adapterHelper$UpdateOp0) {
        int v2;
        if(adapterHelper$UpdateOp0.cmd == 1 || adapterHelper$UpdateOp0.cmd == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int v = this.updatePositionWithPostponed(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.cmd);
        int v1 = adapterHelper$UpdateOp0.positionStart;
        switch(adapterHelper$UpdateOp0.cmd) {
            case 2: {
                v2 = 0;
                break;
            }
            case 4: {
                v2 = 1;
                break;
            }
            default: {
                throw new IllegalArgumentException("op should be remove or update." + adapterHelper$UpdateOp0);
            }
        }
        int v4 = 1;
        for(int v3 = 1; v3 < adapterHelper$UpdateOp0.itemCount; ++v3) {
            int v5 = this.updatePositionWithPostponed(adapterHelper$UpdateOp0.positionStart + v2 * v3, adapterHelper$UpdateOp0.cmd);
            switch(adapterHelper$UpdateOp0.cmd) {
                case 2: {
                    if(v5 == v) {
                        ++v4;
                        continue;
                    }
                    break;
                }
                case 4: {
                    if(v5 == v + 1) {
                        ++v4;
                        continue;
                    }
                }
            }
            UpdateOp adapterHelper$UpdateOp1 = this.obtainUpdateOp(adapterHelper$UpdateOp0.cmd, v, v4, adapterHelper$UpdateOp0.payload);
            this.dispatchFirstPassAndUpdateViewHolders(adapterHelper$UpdateOp1, v1);
            this.recycleUpdateOp(adapterHelper$UpdateOp1);
            if(adapterHelper$UpdateOp0.cmd == 4) {
                v1 += v4;
            }
            v = v5;
            v4 = 1;
        }
        Object object0 = adapterHelper$UpdateOp0.payload;
        this.recycleUpdateOp(adapterHelper$UpdateOp0);
        if(v4 > 0) {
            UpdateOp adapterHelper$UpdateOp2 = this.obtainUpdateOp(adapterHelper$UpdateOp0.cmd, v, v4, object0);
            this.dispatchFirstPassAndUpdateViewHolders(adapterHelper$UpdateOp2, v1);
            this.recycleUpdateOp(adapterHelper$UpdateOp2);
        }
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp adapterHelper$UpdateOp0, int v) {
        this.mCallback.onDispatchFirstPass(adapterHelper$UpdateOp0);
        switch(adapterHelper$UpdateOp0.cmd) {
            case 2: {
                this.mCallback.offsetPositionsForRemovingInvisible(v, adapterHelper$UpdateOp0.itemCount);
                return;
            }
            case 4: {
                this.mCallback.markViewHoldersUpdated(v, adapterHelper$UpdateOp0.itemCount, adapterHelper$UpdateOp0.payload);
                return;
            }
            default: {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
        }
    }

    int findPositionOffset(int v) {
        return this.findPositionOffset(v, 0);
    }

    int findPositionOffset(int v, int v1) {
        int v2 = this.mPostponedList.size();
        while(v1 < v2) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPostponedList.get(v1);
            if(adapterHelper$UpdateOp0.cmd != 8) {
                if(adapterHelper$UpdateOp0.positionStart <= v) {
                    switch(adapterHelper$UpdateOp0.cmd) {
                        case 1: {
                            v += adapterHelper$UpdateOp0.itemCount;
                            break;
                        }
                        case 2: {
                            if(v < adapterHelper$UpdateOp0.positionStart + adapterHelper$UpdateOp0.itemCount) {
                                return -1;
                            }
                            v -= adapterHelper$UpdateOp0.itemCount;
                        }
                    }
                }
            }
            else if(adapterHelper$UpdateOp0.positionStart == v) {
                v = adapterHelper$UpdateOp0.itemCount;
            }
            else {
                if(adapterHelper$UpdateOp0.positionStart < v) {
                    --v;
                }
                if(adapterHelper$UpdateOp0.itemCount <= v) {
                    ++v;
                }
            }
            ++v1;
        }
        return v;
    }

    boolean hasAnyUpdateTypes(int v) {
        return (v & this.mExistingUpdateTypes) != 0;
    }

    boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    // 去混淆评级： 低(20)
    boolean hasUpdates() {
        return !this.mPostponedList.isEmpty() && !this.mPendingUpdates.isEmpty();
    }

    @Override  // androidx.recyclerview.widget.OpReorderer$Callback
    public UpdateOp obtainUpdateOp(int v, int v1, int v2, Object object0) {
        UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mUpdateOpPool.acquire();
        if(adapterHelper$UpdateOp0 == null) {
            return new UpdateOp(v, v1, v2, object0);
        }
        adapterHelper$UpdateOp0.cmd = v;
        adapterHelper$UpdateOp0.positionStart = v1;
        adapterHelper$UpdateOp0.itemCount = v2;
        adapterHelper$UpdateOp0.payload = object0;
        return adapterHelper$UpdateOp0;
    }

    boolean onItemRangeChanged(int v, int v1, Object object0) {
        if(v1 < 1) {
            return false;
        }
        UpdateOp adapterHelper$UpdateOp0 = this.obtainUpdateOp(4, v, v1, object0);
        this.mPendingUpdates.add(adapterHelper$UpdateOp0);
        this.mExistingUpdateTypes |= 4;
        return this.mPendingUpdates.size() == 1;
    }

    boolean onItemRangeInserted(int v, int v1) {
        if(v1 < 1) {
            return false;
        }
        UpdateOp adapterHelper$UpdateOp0 = this.obtainUpdateOp(1, v, v1, null);
        this.mPendingUpdates.add(adapterHelper$UpdateOp0);
        this.mExistingUpdateTypes |= 1;
        return this.mPendingUpdates.size() == 1;
    }

    boolean onItemRangeMoved(int v, int v1, int v2) {
        if(v == v1) {
            return false;
        }
        if(v2 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        UpdateOp adapterHelper$UpdateOp0 = this.obtainUpdateOp(8, v, v1, null);
        this.mPendingUpdates.add(adapterHelper$UpdateOp0);
        this.mExistingUpdateTypes |= 8;
        return this.mPendingUpdates.size() == 1;
    }

    boolean onItemRangeRemoved(int v, int v1) {
        if(v1 < 1) {
            return false;
        }
        UpdateOp adapterHelper$UpdateOp0 = this.obtainUpdateOp(2, v, v1, null);
        this.mPendingUpdates.add(adapterHelper$UpdateOp0);
        this.mExistingUpdateTypes |= 2;
        return this.mPendingUpdates.size() == 1;
    }

    private void postponeAndUpdateViewHolders(UpdateOp adapterHelper$UpdateOp0) {
        this.mPostponedList.add(adapterHelper$UpdateOp0);
        switch(adapterHelper$UpdateOp0.cmd) {
            case 1: {
                this.mCallback.offsetPositionsForAdd(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                return;
            }
            case 2: {
                this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                return;
            }
            case 4: {
                this.mCallback.markViewHoldersUpdated(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount, adapterHelper$UpdateOp0.payload);
                return;
            }
            case 8: {
                this.mCallback.offsetPositionsForMove(adapterHelper$UpdateOp0.positionStart, adapterHelper$UpdateOp0.itemCount);
                return;
            }
            default: {
                throw new IllegalArgumentException("Unknown update op type for " + adapterHelper$UpdateOp0);
            }
        }
    }

    void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int v = this.mPendingUpdates.size();
        for(int v1 = 0; v1 < v; ++v1) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPendingUpdates.get(v1);
            switch(adapterHelper$UpdateOp0.cmd) {
                case 1: {
                    this.applyAdd(adapterHelper$UpdateOp0);
                    break;
                }
                case 2: {
                    this.applyRemove(adapterHelper$UpdateOp0);
                    break;
                }
                case 4: {
                    this.applyUpdate(adapterHelper$UpdateOp0);
                    break;
                }
                case 8: {
                    this.applyMove(adapterHelper$UpdateOp0);
                }
            }
            Runnable runnable0 = this.mOnItemProcessedCallback;
            if(runnable0 != null) {
                runnable0.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    @Override  // androidx.recyclerview.widget.OpReorderer$Callback
    public void recycleUpdateOp(UpdateOp adapterHelper$UpdateOp0) {
        if(!this.mDisableRecycler) {
            adapterHelper$UpdateOp0.payload = null;
            this.mUpdateOpPool.release(adapterHelper$UpdateOp0);
        }
    }

    void recycleUpdateOpsAndClearList(List list0) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.recycleUpdateOp(((UpdateOp)list0.get(v1)));
        }
        list0.clear();
    }

    void reset() {
        this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    private int updatePositionWithPostponed(int v, int v1) {
        int v4;
        int v3;
        for(int v2 = this.mPostponedList.size() - 1; v2 >= 0; --v2) {
            UpdateOp adapterHelper$UpdateOp0 = (UpdateOp)this.mPostponedList.get(v2);
            if(adapterHelper$UpdateOp0.cmd == 8) {
                if(adapterHelper$UpdateOp0.positionStart < adapterHelper$UpdateOp0.itemCount) {
                    v3 = adapterHelper$UpdateOp0.positionStart;
                    v4 = adapterHelper$UpdateOp0.itemCount;
                }
                else {
                    v3 = adapterHelper$UpdateOp0.itemCount;
                    v4 = adapterHelper$UpdateOp0.positionStart;
                }
                if(v < v3 || v > v4) {
                    if(v < adapterHelper$UpdateOp0.positionStart) {
                        if(v1 == 1) {
                            ++adapterHelper$UpdateOp0.positionStart;
                            ++adapterHelper$UpdateOp0.itemCount;
                        }
                        else if(v1 == 2) {
                            --adapterHelper$UpdateOp0.positionStart;
                            --adapterHelper$UpdateOp0.itemCount;
                        }
                    }
                }
                else if(v3 == adapterHelper$UpdateOp0.positionStart) {
                    if(v1 == 1) {
                        ++adapterHelper$UpdateOp0.itemCount;
                    }
                    else if(v1 == 2) {
                        --adapterHelper$UpdateOp0.itemCount;
                    }
                    ++v;
                }
                else {
                    if(v1 == 1) {
                        ++adapterHelper$UpdateOp0.positionStart;
                    }
                    else if(v1 == 2) {
                        --adapterHelper$UpdateOp0.positionStart;
                    }
                    --v;
                }
            }
            else if(adapterHelper$UpdateOp0.positionStart <= v) {
                switch(adapterHelper$UpdateOp0.cmd) {
                    case 1: {
                        v -= adapterHelper$UpdateOp0.itemCount;
                        break;
                    }
                    case 2: {
                        v += adapterHelper$UpdateOp0.itemCount;
                    }
                }
            }
            else if(v1 == 1) {
                ++adapterHelper$UpdateOp0.positionStart;
            }
            else if(v1 == 2) {
                --adapterHelper$UpdateOp0.positionStart;
            }
        }
        for(int v5 = this.mPostponedList.size() - 1; v5 >= 0; --v5) {
            UpdateOp adapterHelper$UpdateOp1 = (UpdateOp)this.mPostponedList.get(v5);
            if(adapterHelper$UpdateOp1.cmd != 8) {
                if(adapterHelper$UpdateOp1.itemCount <= 0) {
                    this.mPostponedList.remove(v5);
                    this.recycleUpdateOp(adapterHelper$UpdateOp1);
                }
            }
            else if(adapterHelper$UpdateOp1.itemCount == adapterHelper$UpdateOp1.positionStart || adapterHelper$UpdateOp1.itemCount < 0) {
                this.mPostponedList.remove(v5);
                this.recycleUpdateOp(adapterHelper$UpdateOp1);
            }
        }
        return v;
    }
}

