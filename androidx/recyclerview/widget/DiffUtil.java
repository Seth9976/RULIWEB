package androidx.recyclerview.widget;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class DiffUtil {
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int arg1, int arg2);

        public abstract boolean areItemsTheSame(int arg1, int arg2);

        public Object getChangePayload(int v, int v1) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    static class CenteredArray {
        private final int[] mData;
        private final int mMid;

        CenteredArray(int v) {
            int[] arr_v = new int[v];
            this.mData = arr_v;
            this.mMid = arr_v.length / 2;
        }

        int[] backingData() {
            return this.mData;
        }

        public void fill(int v) {
            Arrays.fill(this.mData, v);
        }

        int get(int v) {
            return this.mData[v + this.mMid];
        }

        void set(int v, int v1) {
            this.mData[v + this.mMid] = v1;
        }
    }

    static class Diagonal {
        public final int size;
        public final int x;
        public final int y;

        Diagonal(int v, int v1, int v2) {
            this.x = v;
            this.y = v1;
            this.size = v2;
        }

        int endX() {
            return this.x + this.size;
        }

        int endY() {
            return this.y + this.size;
        }
    }

    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_MASK = 15;
        private static final int FLAG_MOVED = 12;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 4;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final List mDiagonals;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;

        DiffResult(Callback diffUtil$Callback0, List list0, int[] arr_v, int[] arr_v1, boolean z) {
            this.mDiagonals = list0;
            this.mOldItemStatuses = arr_v;
            this.mNewItemStatuses = arr_v1;
            Arrays.fill(arr_v, 0);
            Arrays.fill(arr_v1, 0);
            this.mCallback = diffUtil$Callback0;
            this.mOldListSize = diffUtil$Callback0.getOldListSize();
            this.mNewListSize = diffUtil$Callback0.getNewListSize();
            this.mDetectMoves = z;
            this.addEdgeDiagonals();
            this.findMatchingItems();
        }

        private void addEdgeDiagonals() {
            Diagonal diffUtil$Diagonal0 = this.mDiagonals.isEmpty() ? null : ((Diagonal)this.mDiagonals.get(0));
            if(diffUtil$Diagonal0 == null || diffUtil$Diagonal0.x != 0 || diffUtil$Diagonal0.y != 0) {
                Diagonal diffUtil$Diagonal1 = new Diagonal(0, 0, 0);
                this.mDiagonals.add(0, diffUtil$Diagonal1);
            }
            Diagonal diffUtil$Diagonal2 = new Diagonal(this.mOldListSize, this.mNewListSize, 0);
            this.mDiagonals.add(diffUtil$Diagonal2);
        }

        public int convertNewPositionToOld(int v) {
            if(v < 0 || v >= this.mNewListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + v + ", new list size = " + this.mNewListSize);
            }
            int v1 = this.mNewItemStatuses[v];
            return (v1 & 15) == 0 ? -1 : v1 >> 4;
        }

        public int convertOldPositionToNew(int v) {
            if(v < 0 || v >= this.mOldListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + v + ", old list size = " + this.mOldListSize);
            }
            int v1 = this.mOldItemStatuses[v];
            return (v1 & 15) == 0 ? -1 : v1 >> 4;
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback0) {
            BatchingListUpdateCallback batchingListUpdateCallback0 = listUpdateCallback0 instanceof BatchingListUpdateCallback ? ((BatchingListUpdateCallback)listUpdateCallback0) : new BatchingListUpdateCallback(listUpdateCallback0);
            int v = this.mOldListSize;
            ArrayDeque arrayDeque0 = new ArrayDeque();
            int v1 = this.mOldListSize;
            int v2 = this.mNewListSize;
            int v3 = this.mDiagonals.size() - 1;
            while(v3 >= 0) {
                Diagonal diffUtil$Diagonal0 = (Diagonal)this.mDiagonals.get(v3);
                int v4 = diffUtil$Diagonal0.endX();
                int v5 = diffUtil$Diagonal0.endY();
                while(v1 > v4) {
                    --v1;
                    int v7 = this.mOldItemStatuses[v1];
                    if((v7 & 12) == 0) {
                        batchingListUpdateCallback0.onRemoved(v1, 1);
                        --v;
                    }
                    else {
                        PostponedUpdate diffUtil$PostponedUpdate0 = DiffResult.getPostponedUpdate(arrayDeque0, v7 >> 4, false);
                        if(diffUtil$PostponedUpdate0 == null) {
                            arrayDeque0.add(new PostponedUpdate(v1, v - v1 - 1, true));
                        }
                        else {
                            int v8 = v - diffUtil$PostponedUpdate0.currentPos - 1;
                            batchingListUpdateCallback0.onMoved(v1, v8);
                            if((v7 & 4) == 0) {
                                continue;
                            }
                            batchingListUpdateCallback0.onChanged(v8, 1, this.mCallback.getChangePayload(v1, v7 >> 4));
                        }
                    }
                }
                while(v2 > v5) {
                    --v2;
                    int v9 = this.mNewItemStatuses[v2];
                    if((v9 & 12) == 0) {
                        batchingListUpdateCallback0.onInserted(v1, 1);
                        ++v;
                    }
                    else {
                        PostponedUpdate diffUtil$PostponedUpdate1 = DiffResult.getPostponedUpdate(arrayDeque0, v9 >> 4, true);
                        if(diffUtil$PostponedUpdate1 == null) {
                            arrayDeque0.add(new PostponedUpdate(v2, v - v1, false));
                        }
                        else {
                            batchingListUpdateCallback0.onMoved(v - diffUtil$PostponedUpdate1.currentPos - 1, v1);
                            if((v9 & 4) == 0) {
                                continue;
                            }
                            batchingListUpdateCallback0.onChanged(v1, 1, this.mCallback.getChangePayload(v9 >> 4, v2));
                        }
                    }
                }
                int v10 = diffUtil$Diagonal0.x;
                int v11 = diffUtil$Diagonal0.y;
                for(int v6 = 0; v6 < diffUtil$Diagonal0.size; ++v6) {
                    if((this.mOldItemStatuses[v10] & 15) == 2) {
                        batchingListUpdateCallback0.onChanged(v10, 1, this.mCallback.getChangePayload(v10, v11));
                    }
                    ++v10;
                    ++v11;
                }
                v1 = diffUtil$Diagonal0.x;
                v2 = diffUtil$Diagonal0.y;
                --v3;
            }
            batchingListUpdateCallback0.dispatchLastEvent();
        }

        public void dispatchUpdatesTo(Adapter recyclerView$Adapter0) {
            this.dispatchUpdatesTo(new AdapterListUpdateCallback(recyclerView$Adapter0));
        }

        private void findMatchingAddition(int v) {
            int v1 = this.mDiagonals.size();
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                Diagonal diffUtil$Diagonal0 = (Diagonal)this.mDiagonals.get(v2);
                while(v3 < diffUtil$Diagonal0.y) {
                    if(this.mNewItemStatuses[v3] == 0 && this.mCallback.areItemsTheSame(v, v3)) {
                        int v4 = this.mCallback.areContentsTheSame(v, v3) ? 8 : 4;
                        this.mOldItemStatuses[v] = v3 << 4 | v4;
                        this.mNewItemStatuses[v3] = v << 4 | v4;
                        return;
                    }
                    ++v3;
                }
                v3 = diffUtil$Diagonal0.endY();
            }
        }

        private void findMatchingItems() {
            for(Object object0: this.mDiagonals) {
                Diagonal diffUtil$Diagonal0 = (Diagonal)object0;
                for(int v = 0; v < diffUtil$Diagonal0.size; ++v) {
                    int v1 = diffUtil$Diagonal0.x + v;
                    int v2 = diffUtil$Diagonal0.y + v;
                    int v3 = this.mCallback.areContentsTheSame(v1, v2) ? 1 : 2;
                    this.mOldItemStatuses[v1] = v2 << 4 | v3;
                    this.mNewItemStatuses[v2] = v1 << 4 | v3;
                }
            }
            if(this.mDetectMoves) {
                this.findMoveMatches();
            }
        }

        private void findMoveMatches() {
            int v = 0;
            for(Object object0: this.mDiagonals) {
                while(v < ((Diagonal)object0).x) {
                    if(this.mOldItemStatuses[v] == 0) {
                        this.findMatchingAddition(v);
                    }
                    ++v;
                }
                v = ((Diagonal)object0).endX();
            }
        }

        private static PostponedUpdate getPostponedUpdate(Collection collection0, int v, boolean z) {
            PostponedUpdate diffUtil$PostponedUpdate0;
            Iterator iterator0 = collection0.iterator();
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                diffUtil$PostponedUpdate0 = (PostponedUpdate)object0;
                if(diffUtil$PostponedUpdate0.posInOwnerList != v || diffUtil$PostponedUpdate0.removal != z) {
                    continue;
                }
                iterator0.remove();
                goto label_8;
            }
            diffUtil$PostponedUpdate0 = null;
        label_8:
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                PostponedUpdate diffUtil$PostponedUpdate1 = (PostponedUpdate)object1;
                if(z) {
                    --diffUtil$PostponedUpdate1.currentPos;
                }
                else {
                    ++diffUtil$PostponedUpdate1.currentPos;
                }
            }
            return diffUtil$PostponedUpdate0;
        }
    }

    public static abstract class ItemCallback {
        public abstract boolean areContentsTheSame(Object arg1, Object arg2);

        public abstract boolean areItemsTheSame(Object arg1, Object arg2);

        public Object getChangePayload(Object object0, Object object1) {
            return null;
        }
    }

    static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        PostponedUpdate(int v, int v1, boolean z) {
            this.posInOwnerList = v;
            this.currentPos = v1;
            this.removal = z;
        }
    }

    static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int v, int v1, int v2, int v3) {
            this.oldListStart = v;
            this.oldListEnd = v1;
            this.newListStart = v2;
            this.newListEnd = v3;
        }

        int newSize() {
            return this.newListEnd - this.newListStart;
        }

        int oldSize() {
            return this.oldListEnd - this.oldListStart;
        }
    }

    static class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        int diagonalSize() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }

        boolean hasAdditionOrRemoval() {
            return this.endY - this.startY != this.endX - this.startX;
        }

        boolean isAddition() {
            return this.endY - this.startY > this.endX - this.startX;
        }

        Diagonal toDiagonal() {
            if(this.hasAdditionOrRemoval()) {
                if(this.reverse) {
                    return new Diagonal(this.startX, this.startY, this.diagonalSize());
                }
                return this.isAddition() ? new Diagonal(this.startX, this.startY + 1, this.diagonalSize()) : new Diagonal(this.startX + 1, this.startY, this.diagonalSize());
            }
            return new Diagonal(this.startX, this.startY, this.endX - this.startX);
        }
    }

    private static final Comparator DIAGONAL_COMPARATOR;

    static {
        DiffUtil.DIAGONAL_COMPARATOR = new Comparator() {
            public int compare(Diagonal diffUtil$Diagonal0, Diagonal diffUtil$Diagonal1) {
                return diffUtil$Diagonal0.x - diffUtil$Diagonal1.x;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Diagonal)object0), ((Diagonal)object1));
            }
        };
    }

    private static Snake backward(Range diffUtil$Range0, Callback diffUtil$Callback0, CenteredArray diffUtil$CenteredArray0, CenteredArray diffUtil$CenteredArray1, int v) {
        int v5;
        int v4;
        boolean z = (diffUtil$Range0.oldSize() - diffUtil$Range0.newSize()) % 2 == 0;
        int v1 = diffUtil$Range0.oldSize();
        int v2 = diffUtil$Range0.newSize();
        for(int v3 = -v; v3 <= v; v3 += 2) {
            if(v3 == -v || v3 != v && diffUtil$CenteredArray1.get(v3 + 1) < diffUtil$CenteredArray1.get(v3 - 1)) {
                v4 = diffUtil$CenteredArray1.get(v3 + 1);
                v5 = v4;
            }
            else {
                v4 = diffUtil$CenteredArray1.get(v3 - 1);
                v5 = v4 - 1;
            }
            int v6 = diffUtil$Range0.newListEnd - (diffUtil$Range0.oldListEnd - v5 - v3);
            int v7 = v == 0 || v5 != v4 ? v6 : v6 + 1;
            while(v5 > diffUtil$Range0.oldListStart && v6 > diffUtil$Range0.newListStart && diffUtil$Callback0.areItemsTheSame(v5 - 1, v6 - 1)) {
                --v5;
                --v6;
            }
            diffUtil$CenteredArray1.set(v3, v5);
            if(z) {
                int v8 = v1 - v2 - v3;
                if(v8 >= -v && v8 <= v && diffUtil$CenteredArray0.get(v8) >= v5) {
                    Snake diffUtil$Snake0 = new Snake();
                    diffUtil$Snake0.startX = v5;
                    diffUtil$Snake0.startY = v6;
                    diffUtil$Snake0.endX = v4;
                    diffUtil$Snake0.endY = v7;
                    diffUtil$Snake0.reverse = true;
                    return diffUtil$Snake0;
                }
            }
        }
        return null;
    }

    public static DiffResult calculateDiff(Callback diffUtil$Callback0) {
        return DiffUtil.calculateDiff(diffUtil$Callback0, true);
    }

    public static DiffResult calculateDiff(Callback diffUtil$Callback0, boolean z) {
        int v = diffUtil$Callback0.getOldListSize();
        int v1 = diffUtil$Callback0.getNewListSize();
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(new Range(0, v, 0, v1));
        int v2 = (v + v1 + 1) / 2 * 2 + 1;
        CenteredArray diffUtil$CenteredArray0 = new CenteredArray(v2);
        CenteredArray diffUtil$CenteredArray1 = new CenteredArray(v2);
        ArrayList arrayList2 = new ArrayList();
        while(!arrayList1.isEmpty()) {
            Range diffUtil$Range0 = (Range)arrayList1.remove(arrayList1.size() - 1);
            Snake diffUtil$Snake0 = DiffUtil.midPoint(diffUtil$Range0, diffUtil$Callback0, diffUtil$CenteredArray0, diffUtil$CenteredArray1);
            if(diffUtil$Snake0 == null) {
                arrayList2.add(diffUtil$Range0);
            }
            else {
                if(diffUtil$Snake0.diagonalSize() > 0) {
                    arrayList0.add(diffUtil$Snake0.toDiagonal());
                }
                Range diffUtil$Range1 = arrayList2.isEmpty() ? new Range() : ((Range)arrayList2.remove(arrayList2.size() - 1));
                diffUtil$Range1.oldListStart = diffUtil$Range0.oldListStart;
                diffUtil$Range1.newListStart = diffUtil$Range0.newListStart;
                diffUtil$Range1.oldListEnd = diffUtil$Snake0.startX;
                diffUtil$Range1.newListEnd = diffUtil$Snake0.startY;
                arrayList1.add(diffUtil$Range1);
                diffUtil$Range0.oldListEnd = diffUtil$Range0.oldListEnd;
                diffUtil$Range0.newListEnd = diffUtil$Range0.newListEnd;
                diffUtil$Range0.oldListStart = diffUtil$Snake0.endX;
                diffUtil$Range0.newListStart = diffUtil$Snake0.endY;
                arrayList1.add(diffUtil$Range0);
            }
        }
        Collections.sort(arrayList0, DiffUtil.DIAGONAL_COMPARATOR);
        return new DiffResult(diffUtil$Callback0, arrayList0, diffUtil$CenteredArray0.backingData(), diffUtil$CenteredArray1.backingData(), z);
    }

    private static Snake forward(Range diffUtil$Range0, Callback diffUtil$Callback0, CenteredArray diffUtil$CenteredArray0, CenteredArray diffUtil$CenteredArray1, int v) {
        int v5;
        int v4;
        boolean z = Math.abs(diffUtil$Range0.oldSize() - diffUtil$Range0.newSize()) % 2 == 1;
        int v1 = diffUtil$Range0.oldSize();
        int v2 = diffUtil$Range0.newSize();
        for(int v3 = -v; v3 <= v; v3 += 2) {
            if(v3 == -v || v3 != v && diffUtil$CenteredArray0.get(v3 + 1) > diffUtil$CenteredArray0.get(v3 - 1)) {
                v4 = diffUtil$CenteredArray0.get(v3 + 1);
                v5 = v4;
            }
            else {
                v4 = diffUtil$CenteredArray0.get(v3 - 1);
                v5 = v4 + 1;
            }
            int v6 = diffUtil$Range0.newListStart + (v5 - diffUtil$Range0.oldListStart) - v3;
            int v7 = v == 0 || v5 != v4 ? v6 : v6 - 1;
            while(v5 < diffUtil$Range0.oldListEnd && v6 < diffUtil$Range0.newListEnd && diffUtil$Callback0.areItemsTheSame(v5, v6)) {
                ++v5;
                ++v6;
            }
            diffUtil$CenteredArray0.set(v3, v5);
            if(z) {
                int v8 = v1 - v2 - v3;
                if(v8 >= 1 - v && v8 <= v - 1 && diffUtil$CenteredArray1.get(v8) <= v5) {
                    Snake diffUtil$Snake0 = new Snake();
                    diffUtil$Snake0.startX = v4;
                    diffUtil$Snake0.startY = v7;
                    diffUtil$Snake0.endX = v5;
                    diffUtil$Snake0.endY = v6;
                    diffUtil$Snake0.reverse = false;
                    return diffUtil$Snake0;
                }
            }
        }
        return null;
    }

    private static Snake midPoint(Range diffUtil$Range0, Callback diffUtil$Callback0, CenteredArray diffUtil$CenteredArray0, CenteredArray diffUtil$CenteredArray1) {
        if(diffUtil$Range0.oldSize() >= 1 && diffUtil$Range0.newSize() >= 1) {
            int v = diffUtil$Range0.oldSize();
            int v1 = diffUtil$Range0.newSize();
            diffUtil$CenteredArray0.set(1, diffUtil$Range0.oldListStart);
            diffUtil$CenteredArray1.set(1, diffUtil$Range0.oldListEnd);
            for(int v2 = 0; v2 < (v + v1 + 1) / 2; ++v2) {
                Snake diffUtil$Snake0 = DiffUtil.forward(diffUtil$Range0, diffUtil$Callback0, diffUtil$CenteredArray0, diffUtil$CenteredArray1, v2);
                if(diffUtil$Snake0 != null) {
                    return diffUtil$Snake0;
                }
                Snake diffUtil$Snake1 = DiffUtil.backward(diffUtil$Range0, diffUtil$Callback0, diffUtil$CenteredArray0, diffUtil$CenteredArray1, v2);
                if(diffUtil$Snake1 != null) {
                    return diffUtil$Snake1;
                }
            }
        }
        return null;
    }
}

