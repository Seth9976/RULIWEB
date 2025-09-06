package androidx.viewpager2.adapter;

import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public final class FragmentViewHolder extends ViewHolder {
    private FragmentViewHolder(FrameLayout frameLayout0) {
        super(frameLayout0);
    }

    static FragmentViewHolder create(ViewGroup viewGroup0) {
        FrameLayout frameLayout0 = new FrameLayout(viewGroup0.getContext());
        frameLayout0.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout0.setId(ViewCompat.generateViewId());
        frameLayout0.setSaveEnabled(false);
        return new FragmentViewHolder(frameLayout0);
    }

    FrameLayout getContainer() {
        return (FrameLayout)this.itemView;
    }
}

