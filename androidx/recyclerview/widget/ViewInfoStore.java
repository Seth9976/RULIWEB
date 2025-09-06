package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SimplePool;

class ViewInfoStore {
    static class InfoRecord {
        static final int FLAG_APPEAR = 2;
        static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
        static final int FLAG_APPEAR_PRE_AND_POST = 14;
        static final int FLAG_DISAPPEARED = 1;
        static final int FLAG_POST = 8;
        static final int FLAG_PRE = 4;
        static final int FLAG_PRE_AND_POST = 12;
        int flags;
        ItemHolderInfo postInfo;
        ItemHolderInfo preInfo;
        static Pool sPool;

        static {
            InfoRecord.sPool = new SimplePool(20);
        }

        static void drainCache() {
            while(InfoRecord.sPool.acquire() != null) {
            }
        }

        static InfoRecord obtain() {
            InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)InfoRecord.sPool.acquire();
            return viewInfoStore$InfoRecord0 == null ? new InfoRecord() : viewInfoStore$InfoRecord0;
        }

        static void recycle(InfoRecord viewInfoStore$InfoRecord0) {
            viewInfoStore$InfoRecord0.flags = 0;
            viewInfoStore$InfoRecord0.preInfo = null;
            viewInfoStore$InfoRecord0.postInfo = null;
            InfoRecord.sPool.release(viewInfoStore$InfoRecord0);
        }
    }

    interface ProcessCallback {
        void processAppeared(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void processDisappeared(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void processPersistent(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void unused(ViewHolder arg1);
    }

    private static final boolean DEBUG = false;
    final SimpleArrayMap mLayoutHolderMap;
    final LongSparseArray mOldChangedHolders;

    ViewInfoStore() {
        this.mLayoutHolderMap = new SimpleArrayMap();
        this.mOldChangedHolders = new LongSparseArray();
    }

    void addToAppearedInPreLayoutHolders(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 == null) {
            viewInfoStore$InfoRecord0 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0);
        }
        viewInfoStore$InfoRecord0.flags |= 2;
        viewInfoStore$InfoRecord0.preInfo = recyclerView$ItemAnimator$ItemHolderInfo0;
    }

    void addToDisappearedInLayout(ViewHolder recyclerView$ViewHolder0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 == null) {
            viewInfoStore$InfoRecord0 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0);
        }
        viewInfoStore$InfoRecord0.flags |= 1;
    }

    void addToOldChangeHolders(long v, ViewHolder recyclerView$ViewHolder0) {
        this.mOldChangedHolders.put(v, recyclerView$ViewHolder0);
    }

    void addToPostLayout(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 == null) {
            viewInfoStore$InfoRecord0 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0);
        }
        viewInfoStore$InfoRecord0.postInfo = recyclerView$ItemAnimator$ItemHolderInfo0;
        viewInfoStore$InfoRecord0.flags |= 8;
    }

    void addToPreLayout(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 == null) {
            viewInfoStore$InfoRecord0 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0);
        }
        viewInfoStore$InfoRecord0.preInfo = recyclerView$ItemAnimator$ItemHolderInfo0;
        viewInfoStore$InfoRecord0.flags |= 4;
    }

    void clear() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.clear();
    }

    ViewHolder getFromOldChangeHolders(long v) {
        return (ViewHolder)this.mOldChangedHolders.get(v);
    }

    boolean isDisappearing(ViewHolder recyclerView$ViewHolder0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        return viewInfoStore$InfoRecord0 != null && (viewInfoStore$InfoRecord0.flags & 1) != 0;
    }

    boolean isInPreLayout(ViewHolder recyclerView$ViewHolder0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        return viewInfoStore$InfoRecord0 != null && (viewInfoStore$InfoRecord0.flags & 4) != 0;
    }

    void onDetach() {
        InfoRecord.drainCache();
    }

    public void onViewDetached(ViewHolder recyclerView$ViewHolder0) {
        this.removeFromDisappearedInLayout(recyclerView$ViewHolder0);
    }

    private ItemHolderInfo popFromLayoutStep(ViewHolder recyclerView$ViewHolder0, int v) {
        ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0;
        int v1 = this.mLayoutHolderMap.indexOfKey(recyclerView$ViewHolder0);
        if(v1 < 0) {
            return null;
        }
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.valueAt(v1);
        if(viewInfoStore$InfoRecord0 != null && (viewInfoStore$InfoRecord0.flags & v) != 0) {
            viewInfoStore$InfoRecord0.flags &= ~v;
            if(v == 4) {
                recyclerView$ItemAnimator$ItemHolderInfo0 = viewInfoStore$InfoRecord0.preInfo;
            }
            else if(v == 8) {
                recyclerView$ItemAnimator$ItemHolderInfo0 = viewInfoStore$InfoRecord0.postInfo;
            }
            else {
                throw new IllegalArgumentException("Must provide flag PRE or POST");
            }
            if((viewInfoStore$InfoRecord0.flags & 12) == 0) {
                this.mLayoutHolderMap.removeAt(v1);
                InfoRecord.recycle(viewInfoStore$InfoRecord0);
            }
            return recyclerView$ItemAnimator$ItemHolderInfo0;
        }
        return null;
    }

    ItemHolderInfo popFromPostLayout(ViewHolder recyclerView$ViewHolder0) {
        return this.popFromLayoutStep(recyclerView$ViewHolder0, 8);
    }

    ItemHolderInfo popFromPreLayout(ViewHolder recyclerView$ViewHolder0) {
        return this.popFromLayoutStep(recyclerView$ViewHolder0, 4);
    }

    void process(ProcessCallback viewInfoStore$ProcessCallback0) {
        for(int v = this.mLayoutHolderMap.size() - 1; v >= 0; --v) {
            ViewHolder recyclerView$ViewHolder0 = (ViewHolder)this.mLayoutHolderMap.keyAt(v);
            InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.removeAt(v);
            if((viewInfoStore$InfoRecord0.flags & 3) == 3) {
                viewInfoStore$ProcessCallback0.unused(recyclerView$ViewHolder0);
            }
            else if((viewInfoStore$InfoRecord0.flags & 1) == 0) {
                if((viewInfoStore$InfoRecord0.flags & 14) == 14) {
                    viewInfoStore$ProcessCallback0.processAppeared(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0.preInfo, viewInfoStore$InfoRecord0.postInfo);
                }
                else if((viewInfoStore$InfoRecord0.flags & 12) == 12) {
                    viewInfoStore$ProcessCallback0.processPersistent(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0.preInfo, viewInfoStore$InfoRecord0.postInfo);
                }
                else if((viewInfoStore$InfoRecord0.flags & 4) != 0) {
                    viewInfoStore$ProcessCallback0.processDisappeared(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0.preInfo, null);
                }
                else if((viewInfoStore$InfoRecord0.flags & 8) != 0) {
                    viewInfoStore$ProcessCallback0.processAppeared(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0.preInfo, viewInfoStore$InfoRecord0.postInfo);
                }
            }
            else if(viewInfoStore$InfoRecord0.preInfo == null) {
                viewInfoStore$ProcessCallback0.unused(recyclerView$ViewHolder0);
            }
            else {
                viewInfoStore$ProcessCallback0.processDisappeared(recyclerView$ViewHolder0, viewInfoStore$InfoRecord0.preInfo, viewInfoStore$InfoRecord0.postInfo);
            }
            InfoRecord.recycle(viewInfoStore$InfoRecord0);
        }
    }

    void removeFromDisappearedInLayout(ViewHolder recyclerView$ViewHolder0) {
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.get(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 == null) {
            return;
        }
        viewInfoStore$InfoRecord0.flags &= -2;
    }

    void removeViewHolder(ViewHolder recyclerView$ViewHolder0) {
        for(int v = this.mOldChangedHolders.size() - 1; v >= 0; --v) {
            if(recyclerView$ViewHolder0 == this.mOldChangedHolders.valueAt(v)) {
                this.mOldChangedHolders.removeAt(v);
                break;
            }
        }
        InfoRecord viewInfoStore$InfoRecord0 = (InfoRecord)this.mLayoutHolderMap.remove(recyclerView$ViewHolder0);
        if(viewInfoStore$InfoRecord0 != null) {
            InfoRecord.recycle(viewInfoStore$InfoRecord0);
        }
    }
}

