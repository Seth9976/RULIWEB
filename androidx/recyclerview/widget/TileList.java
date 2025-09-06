package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;

class TileList {
    public static class Tile {
        public int mItemCount;
        public final Object[] mItems;
        Tile mNext;
        public int mStartPosition;

        Tile(Class class0, int v) {
            this.mItems = (Object[])Array.newInstance(class0, v);
        }

        boolean containsPosition(int v) {
            return this.mStartPosition <= v && v < this.mStartPosition + this.mItemCount;
        }

        Object getByPosition(int v) {
            return this.mItems[v - this.mStartPosition];
        }
    }

    Tile mLastAccessedTile;
    final int mTileSize;
    private final SparseArray mTiles;

    public TileList(int v) {
        this.mTiles = new SparseArray(10);
        this.mTileSize = v;
    }

    public Tile addOrReplace(Tile tileList$Tile0) {
        int v = this.mTiles.indexOfKey(tileList$Tile0.mStartPosition);
        if(v < 0) {
            this.mTiles.put(tileList$Tile0.mStartPosition, tileList$Tile0);
            return null;
        }
        Tile tileList$Tile1 = (Tile)this.mTiles.valueAt(v);
        this.mTiles.setValueAt(v, tileList$Tile0);
        if(this.mLastAccessedTile == tileList$Tile1) {
            this.mLastAccessedTile = tileList$Tile0;
        }
        return tileList$Tile1;
    }

    public void clear() {
        this.mTiles.clear();
    }

    public Tile getAtIndex(int v) {
        return v < 0 || v >= this.mTiles.size() ? null : ((Tile)this.mTiles.valueAt(v));
    }

    public Object getItemAt(int v) {
        if(this.mLastAccessedTile == null || !this.mLastAccessedTile.containsPosition(v)) {
            int v1 = this.mTiles.indexOfKey(v - v % this.mTileSize);
            if(v1 < 0) {
                return null;
            }
            this.mLastAccessedTile = (Tile)this.mTiles.valueAt(v1);
        }
        return this.mLastAccessedTile.getByPosition(v);
    }

    public Tile removeAtPos(int v) {
        Tile tileList$Tile0 = (Tile)this.mTiles.get(v);
        if(this.mLastAccessedTile == tileList$Tile0) {
            this.mLastAccessedTile = null;
        }
        this.mTiles.delete(v);
        return tileList$Tile0;
    }

    public int size() {
        return this.mTiles.size();
    }
}

