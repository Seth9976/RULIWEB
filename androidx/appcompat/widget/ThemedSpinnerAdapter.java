package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.view.LayoutInflater;
import android.widget.SpinnerAdapter;
import androidx.appcompat.view.ContextThemeWrapper;

public interface ThemedSpinnerAdapter extends SpinnerAdapter {
    public static final class Helper {
        private final Context mContext;
        private LayoutInflater mDropDownInflater;
        private final LayoutInflater mInflater;

        public Helper(Context context0) {
            this.mContext = context0;
            this.mInflater = LayoutInflater.from(context0);
        }

        public LayoutInflater getDropDownViewInflater() {
            return this.mDropDownInflater == null ? this.mInflater : this.mDropDownInflater;
        }

        public Resources.Theme getDropDownViewTheme() {
            return this.mDropDownInflater == null ? null : this.mDropDownInflater.getContext().getTheme();
        }

        public void setDropDownViewTheme(Resources.Theme resources$Theme0) {
            if(resources$Theme0 == null) {
                this.mDropDownInflater = null;
                return;
            }
            if(resources$Theme0.equals(this.mContext.getTheme())) {
                this.mDropDownInflater = this.mInflater;
                return;
            }
            this.mDropDownInflater = LayoutInflater.from(new ContextThemeWrapper(this.mContext, resources$Theme0));
        }
    }

    Resources.Theme getDropDownViewTheme();

    void setDropDownViewTheme(Resources.Theme arg1);
}

