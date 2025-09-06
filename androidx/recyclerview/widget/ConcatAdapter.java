package androidx.recyclerview.widget;

import android.util.Pair;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ConcatAdapter extends Adapter {
    public static final class Config {
        public static final class Builder {
            private boolean mIsolateViewTypes;
            private StableIdMode mStableIdMode;

            public Builder() {
                this.mIsolateViewTypes = Config.DEFAULT.isolateViewTypes;
                this.mStableIdMode = Config.DEFAULT.stableIdMode;
            }

            public Config build() {
                return new Config(this.mIsolateViewTypes, this.mStableIdMode);
            }

            public Builder setIsolateViewTypes(boolean z) {
                this.mIsolateViewTypes = z;
                return this;
            }

            public Builder setStableIdMode(StableIdMode concatAdapter$Config$StableIdMode0) {
                this.mStableIdMode = concatAdapter$Config$StableIdMode0;
                return this;
            }
        }

        public static enum StableIdMode {
            NO_STABLE_IDS,
            ISOLATED_STABLE_IDS,
            SHARED_STABLE_IDS;

            private static StableIdMode[] $values() [...] // Inlined contents
        }

        public static final Config DEFAULT;
        public final boolean isolateViewTypes;
        public final StableIdMode stableIdMode;

        static {
            Config.DEFAULT = new Config(true, StableIdMode.NO_STABLE_IDS);
        }

        Config(boolean z, StableIdMode concatAdapter$Config$StableIdMode0) {
            this.isolateViewTypes = z;
            this.stableIdMode = concatAdapter$Config$StableIdMode0;
        }
    }

    static final String TAG = "ConcatAdapter";
    private final ConcatAdapterController mController;

    public ConcatAdapter(Config concatAdapter$Config0, List list0) {
        this.mController = new ConcatAdapterController(this, concatAdapter$Config0);
        for(Object object0: list0) {
            this.addAdapter(((Adapter)object0));
        }
        super.setHasStableIds(this.mController.hasStableIds());
    }

    @SafeVarargs
    public ConcatAdapter(Config concatAdapter$Config0, Adapter[] arr_recyclerView$Adapter) {
        this(concatAdapter$Config0, Arrays.asList(arr_recyclerView$Adapter));
    }

    public ConcatAdapter(List list0) {
        this(Config.DEFAULT, list0);
    }

    @SafeVarargs
    public ConcatAdapter(Adapter[] arr_recyclerView$Adapter) {
        this(Config.DEFAULT, arr_recyclerView$Adapter);
    }

    public boolean addAdapter(int v, Adapter recyclerView$Adapter0) {
        return this.mController.addAdapter(v, recyclerView$Adapter0);
    }

    public boolean addAdapter(Adapter recyclerView$Adapter0) {
        return this.mController.addAdapter(recyclerView$Adapter0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int findRelativeAdapterPositionIn(Adapter recyclerView$Adapter0, ViewHolder recyclerView$ViewHolder0, int v) {
        return this.mController.getLocalAdapterPosition(recyclerView$Adapter0, recyclerView$ViewHolder0, v);
    }

    public List getAdapters() {
        return Collections.unmodifiableList(this.mController.getCopyOfAdapters());
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemCount() {
        return this.mController.getTotalCount();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public long getItemId(int v) {
        return this.mController.getItemId(v);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemViewType(int v) {
        return this.mController.getItemViewType(v);
    }

    public Pair getWrappedAdapterAndPosition(int v) {
        return this.mController.getWrappedAdapterAndPosition(v);
    }

    void internalSetStateRestorationPolicy(StateRestorationPolicy recyclerView$Adapter$StateRestorationPolicy0) {
        super.setStateRestorationPolicy(recyclerView$Adapter$StateRestorationPolicy0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView0) {
        this.mController.onAttachedToRecyclerView(recyclerView0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
        this.mController.onBindViewHolder(recyclerView$ViewHolder0, v);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.mController.onCreateViewHolder(viewGroup0, v);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView0) {
        this.mController.onDetachedFromRecyclerView(recyclerView0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public boolean onFailedToRecycleView(ViewHolder recyclerView$ViewHolder0) {
        return this.mController.onFailedToRecycleView(recyclerView$ViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onViewAttachedToWindow(ViewHolder recyclerView$ViewHolder0) {
        this.mController.onViewAttachedToWindow(recyclerView$ViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onViewDetachedFromWindow(ViewHolder recyclerView$ViewHolder0) {
        this.mController.onViewDetachedFromWindow(recyclerView$ViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void onViewRecycled(ViewHolder recyclerView$ViewHolder0) {
        this.mController.onViewRecycled(recyclerView$ViewHolder0);
    }

    public boolean removeAdapter(Adapter recyclerView$Adapter0) {
        return this.mController.removeAdapter(recyclerView$Adapter0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Calling setHasStableIds is not allowed on the ConcatAdapter. Use the Config object passed in the constructor to control this behavior");
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public void setStateRestorationPolicy(StateRestorationPolicy recyclerView$Adapter$StateRestorationPolicy0) {
        throw new UnsupportedOperationException("Calling setStateRestorationPolicy is not allowed on the ConcatAdapter. This value is inferred from added adapters");
    }
}

