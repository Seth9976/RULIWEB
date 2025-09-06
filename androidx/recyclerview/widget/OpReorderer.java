package androidx.recyclerview.widget;

import java.util.List;

class OpReorderer {
    interface Callback {
        UpdateOp obtainUpdateOp(int arg1, int arg2, int arg3, Object arg4);

        void recycleUpdateOp(UpdateOp arg1);
    }

    final Callback mCallback;

    OpReorderer(Callback opReorderer$Callback0) {
        this.mCallback = opReorderer$Callback0;
    }

    private int getLastMoveOutOfOrder(List list0) {
        int v = list0.size() - 1;
        boolean z = false;
        while(v >= 0) {
            if(((UpdateOp)list0.get(v)).cmd != 8) {
                z = true;
            }
            else if(z) {
                return v;
            }
            --v;
        }
        return -1;
    }

    void reorderOps(List list0) {
        int v;
        while((v = this.getLastMoveOutOfOrder(list0)) != -1) {
            this.swapMoveOp(list0, v, v + 1);
        }
    }

    private void swapMoveAdd(List list0, int v, UpdateOp adapterHelper$UpdateOp0, int v1, UpdateOp adapterHelper$UpdateOp1) {
        int v2 = adapterHelper$UpdateOp0.itemCount >= adapterHelper$UpdateOp1.positionStart ? 0 : -1;
        if(adapterHelper$UpdateOp0.positionStart < adapterHelper$UpdateOp1.positionStart) {
            ++v2;
        }
        if(adapterHelper$UpdateOp1.positionStart <= adapterHelper$UpdateOp0.positionStart) {
            adapterHelper$UpdateOp0.positionStart += adapterHelper$UpdateOp1.itemCount;
        }
        if(adapterHelper$UpdateOp1.positionStart <= adapterHelper$UpdateOp0.itemCount) {
            adapterHelper$UpdateOp0.itemCount += adapterHelper$UpdateOp1.itemCount;
        }
        adapterHelper$UpdateOp1.positionStart += v2;
        list0.set(v, adapterHelper$UpdateOp1);
        list0.set(v1, adapterHelper$UpdateOp0);
    }

    private void swapMoveOp(List list0, int v, int v1) {
        Object object0 = list0.get(v);
        Object object1 = list0.get(v1);
        switch(((UpdateOp)object1).cmd) {
            case 1: {
                this.swapMoveAdd(list0, v, ((UpdateOp)object0), v1, ((UpdateOp)object1));
                return;
            }
            case 2: {
                this.swapMoveRemove(list0, v, ((UpdateOp)object0), v1, ((UpdateOp)object1));
                return;
            }
            case 4: {
                this.swapMoveUpdate(list0, v, ((UpdateOp)object0), v1, ((UpdateOp)object1));
            }
        }
    }

    void swapMoveRemove(List list0, int v, UpdateOp adapterHelper$UpdateOp0, int v1, UpdateOp adapterHelper$UpdateOp1) {
        boolean z1;
        boolean z = false;
        if(adapterHelper$UpdateOp0.positionStart >= adapterHelper$UpdateOp0.itemCount) {
            if(adapterHelper$UpdateOp1.positionStart != adapterHelper$UpdateOp0.itemCount + 1 || adapterHelper$UpdateOp1.itemCount != adapterHelper$UpdateOp0.positionStart - adapterHelper$UpdateOp0.itemCount) {
                z1 = true;
            }
            else {
                z1 = true;
                z = true;
            }
        }
        else if(adapterHelper$UpdateOp1.positionStart == adapterHelper$UpdateOp0.positionStart && adapterHelper$UpdateOp1.itemCount == adapterHelper$UpdateOp0.itemCount - adapterHelper$UpdateOp0.positionStart) {
            z1 = false;
            z = true;
        }
        else {
            z1 = false;
        }
        if(adapterHelper$UpdateOp0.itemCount < adapterHelper$UpdateOp1.positionStart) {
            --adapterHelper$UpdateOp1.positionStart;
            goto label_24;
        }
        else if(adapterHelper$UpdateOp0.itemCount < adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount) {
            --adapterHelper$UpdateOp1.itemCount;
            adapterHelper$UpdateOp0.cmd = 2;
            adapterHelper$UpdateOp0.itemCount = 1;
            if(adapterHelper$UpdateOp1.itemCount == 0) {
                list0.remove(v1);
                this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp1);
            }
        }
        else {
        label_24:
            UpdateOp adapterHelper$UpdateOp2 = null;
            if(adapterHelper$UpdateOp0.positionStart <= adapterHelper$UpdateOp1.positionStart) {
                ++adapterHelper$UpdateOp1.positionStart;
            }
            else if(adapterHelper$UpdateOp0.positionStart < adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount) {
                adapterHelper$UpdateOp2 = this.mCallback.obtainUpdateOp(2, adapterHelper$UpdateOp0.positionStart + 1, adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount - adapterHelper$UpdateOp0.positionStart, null);
                adapterHelper$UpdateOp1.itemCount = adapterHelper$UpdateOp0.positionStart - adapterHelper$UpdateOp1.positionStart;
            }
            if(z) {
                list0.set(v, adapterHelper$UpdateOp1);
                list0.remove(v1);
                this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp0);
                return;
            }
            if(z1) {
                if(adapterHelper$UpdateOp2 != null) {
                    if(adapterHelper$UpdateOp0.positionStart > adapterHelper$UpdateOp2.positionStart) {
                        adapterHelper$UpdateOp0.positionStart -= adapterHelper$UpdateOp2.itemCount;
                    }
                    if(adapterHelper$UpdateOp0.itemCount > adapterHelper$UpdateOp2.positionStart) {
                        adapterHelper$UpdateOp0.itemCount -= adapterHelper$UpdateOp2.itemCount;
                    }
                }
                if(adapterHelper$UpdateOp0.positionStart > adapterHelper$UpdateOp1.positionStart) {
                    adapterHelper$UpdateOp0.positionStart -= adapterHelper$UpdateOp1.itemCount;
                }
                if(adapterHelper$UpdateOp0.itemCount > adapterHelper$UpdateOp1.positionStart) {
                    adapterHelper$UpdateOp0.itemCount -= adapterHelper$UpdateOp1.itemCount;
                }
            }
            else {
                if(adapterHelper$UpdateOp2 != null) {
                    if(adapterHelper$UpdateOp0.positionStart >= adapterHelper$UpdateOp2.positionStart) {
                        adapterHelper$UpdateOp0.positionStart -= adapterHelper$UpdateOp2.itemCount;
                    }
                    if(adapterHelper$UpdateOp0.itemCount >= adapterHelper$UpdateOp2.positionStart) {
                        adapterHelper$UpdateOp0.itemCount -= adapterHelper$UpdateOp2.itemCount;
                    }
                }
                if(adapterHelper$UpdateOp0.positionStart >= adapterHelper$UpdateOp1.positionStart) {
                    adapterHelper$UpdateOp0.positionStart -= adapterHelper$UpdateOp1.itemCount;
                }
                if(adapterHelper$UpdateOp0.itemCount >= adapterHelper$UpdateOp1.positionStart) {
                    adapterHelper$UpdateOp0.itemCount -= adapterHelper$UpdateOp1.itemCount;
                }
            }
            list0.set(v, adapterHelper$UpdateOp1);
            if(adapterHelper$UpdateOp0.positionStart == adapterHelper$UpdateOp0.itemCount) {
                list0.remove(v1);
            }
            else {
                list0.set(v1, adapterHelper$UpdateOp0);
            }
            if(adapterHelper$UpdateOp2 != null) {
                list0.add(v, adapterHelper$UpdateOp2);
            }
        }
    }

    void swapMoveUpdate(List list0, int v, UpdateOp adapterHelper$UpdateOp0, int v1, UpdateOp adapterHelper$UpdateOp1) {
        UpdateOp adapterHelper$UpdateOp3;
        UpdateOp adapterHelper$UpdateOp2 = null;
        if(adapterHelper$UpdateOp0.itemCount < adapterHelper$UpdateOp1.positionStart) {
            --adapterHelper$UpdateOp1.positionStart;
            adapterHelper$UpdateOp3 = null;
        }
        else if(adapterHelper$UpdateOp0.itemCount < adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount) {
            --adapterHelper$UpdateOp1.itemCount;
            adapterHelper$UpdateOp3 = this.mCallback.obtainUpdateOp(4, adapterHelper$UpdateOp0.positionStart, 1, adapterHelper$UpdateOp1.payload);
        }
        else {
            adapterHelper$UpdateOp3 = null;
        }
        if(adapterHelper$UpdateOp0.positionStart <= adapterHelper$UpdateOp1.positionStart) {
            ++adapterHelper$UpdateOp1.positionStart;
        }
        else if(adapterHelper$UpdateOp0.positionStart < adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount) {
            int v2 = adapterHelper$UpdateOp1.positionStart + adapterHelper$UpdateOp1.itemCount - adapterHelper$UpdateOp0.positionStart;
            adapterHelper$UpdateOp2 = this.mCallback.obtainUpdateOp(4, adapterHelper$UpdateOp0.positionStart + 1, v2, adapterHelper$UpdateOp1.payload);
            adapterHelper$UpdateOp1.itemCount -= v2;
        }
        list0.set(v1, adapterHelper$UpdateOp0);
        if(adapterHelper$UpdateOp1.itemCount > 0) {
            list0.set(v, adapterHelper$UpdateOp1);
        }
        else {
            list0.remove(v);
            this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp1);
        }
        if(adapterHelper$UpdateOp3 != null) {
            list0.add(v, adapterHelper$UpdateOp3);
        }
        if(adapterHelper$UpdateOp2 != null) {
            list0.add(v, adapterHelper$UpdateOp2);
        }
    }
}

