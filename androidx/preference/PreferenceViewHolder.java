package androidx.preference;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class PreferenceViewHolder extends ViewHolder {
    private final Drawable mBackground;
    private final SparseArray mCachedViews;
    private boolean mDividerAllowedAbove;
    private boolean mDividerAllowedBelow;
    private ColorStateList mTitleTextColors;

    PreferenceViewHolder(View view0) {
        super(view0);
        SparseArray sparseArray0 = new SparseArray(4);
        this.mCachedViews = sparseArray0;
        TextView textView0 = (TextView)view0.findViewById(0x1020016);
        sparseArray0.put(0x1020016, textView0);
        sparseArray0.put(0x1020010, view0.findViewById(0x1020010));
        sparseArray0.put(0x1020006, view0.findViewById(0x1020006));
        View view1 = view0.findViewById(id.icon_frame);
        sparseArray0.put(id.icon_frame, view1);
        sparseArray0.put(0x102003E, view0.findViewById(0x102003E));
        this.mBackground = view0.getBackground();
        if(textView0 != null) {
            this.mTitleTextColors = textView0.getTextColors();
        }
    }

    public static PreferenceViewHolder createInstanceForTests(View view0) {
        return new PreferenceViewHolder(view0);
    }

    public View findViewById(int v) {
        View view0 = (View)this.mCachedViews.get(v);
        if(view0 != null) {
            return view0;
        }
        View view1 = this.itemView.findViewById(v);
        if(view1 != null) {
            this.mCachedViews.put(v, view1);
        }
        return view1;
    }

    public boolean isDividerAllowedAbove() {
        return this.mDividerAllowedAbove;
    }

    public boolean isDividerAllowedBelow() {
        return this.mDividerAllowedBelow;
    }

    void resetState() {
        if(this.itemView.getBackground() != this.mBackground) {
            ViewCompat.setBackground(this.itemView, this.mBackground);
        }
        TextView textView0 = (TextView)this.findViewById(0x1020016);
        if(textView0 != null && this.mTitleTextColors != null && !textView0.getTextColors().equals(this.mTitleTextColors)) {
            textView0.setTextColor(this.mTitleTextColors);
        }
    }

    public void setDividerAllowedAbove(boolean z) {
        this.mDividerAllowedAbove = z;
    }

    public void setDividerAllowedBelow(boolean z) {
        this.mDividerAllowedBelow = z;
    }
}

