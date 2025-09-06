package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.style;

class TooltipPopup {
    private static final String TAG = "TooltipPopup";
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos;
    private final int[] mTmpAppPos;
    private final Rect mTmpDisplayFrame;

    TooltipPopup(Context context0) {
        WindowManager.LayoutParams windowManager$LayoutParams0 = new WindowManager.LayoutParams();
        this.mLayoutParams = windowManager$LayoutParams0;
        this.mTmpDisplayFrame = new Rect();
        this.mTmpAnchorPos = new int[2];
        this.mTmpAppPos = new int[2];
        this.mContext = context0;
        View view0 = LayoutInflater.from(context0).inflate(layout.abc_tooltip, null);
        this.mContentView = view0;
        this.mMessageView = (TextView)view0.findViewById(id.message);
        windowManager$LayoutParams0.setTitle(this.getClass().getSimpleName());
        windowManager$LayoutParams0.packageName = "com.ruliweb.www.ruliapp";
        windowManager$LayoutParams0.type = 1002;
        windowManager$LayoutParams0.width = -2;
        windowManager$LayoutParams0.height = -2;
        windowManager$LayoutParams0.format = -3;
        windowManager$LayoutParams0.windowAnimations = style.Animation_AppCompat_Tooltip;
        windowManager$LayoutParams0.flags = 24;
    }

    private void computePosition(View view0, int v, int v1, boolean z, WindowManager.LayoutParams windowManager$LayoutParams0) {
        int v5;
        int v4;
        windowManager$LayoutParams0.token = view0.getApplicationWindowToken();
        int v2 = this.mContext.getResources().getDimensionPixelOffset(dimen.tooltip_precise_anchor_threshold);
        if(view0.getWidth() < v2) {
            v = view0.getWidth() / 2;
        }
        if(view0.getHeight() >= v2) {
            int v3 = this.mContext.getResources().getDimensionPixelOffset(dimen.tooltip_precise_anchor_extra_offset);
            v4 = v1 + v3;
            v5 = v1 - v3;
        }
        else {
            v4 = view0.getHeight();
            v5 = 0;
        }
        windowManager$LayoutParams0.gravity = 49;
        int v6 = this.mContext.getResources().getDimensionPixelOffset((z ? dimen.tooltip_y_offset_touch : dimen.tooltip_y_offset_non_touch));
        View view1 = TooltipPopup.getAppRootView(view0);
        if(view1 == null) {
            Log.e("TooltipPopup", "Cannot find app view");
            return;
        }
        view1.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        if(this.mTmpDisplayFrame.left < 0 && this.mTmpDisplayFrame.top < 0) {
            Resources resources0 = this.mContext.getResources();
            int v7 = resources0.getIdentifier("status_bar_height", "dimen", "android");
            int v8 = v7 == 0 ? 0 : resources0.getDimensionPixelSize(v7);
            DisplayMetrics displayMetrics0 = resources0.getDisplayMetrics();
            this.mTmpDisplayFrame.set(0, v8, displayMetrics0.widthPixels, displayMetrics0.heightPixels);
        }
        view1.getLocationOnScreen(this.mTmpAppPos);
        view0.getLocationOnScreen(this.mTmpAnchorPos);
        int v9 = this.mTmpAnchorPos[0] - this.mTmpAppPos[0];
        this.mTmpAnchorPos[0] = v9;
        this.mTmpAnchorPos[1] -= this.mTmpAppPos[1];
        windowManager$LayoutParams0.x = v9 + v - view1.getWidth() / 2;
        this.mContentView.measure(0, 0);
        int v10 = this.mContentView.getMeasuredHeight();
        int v11 = v5 + this.mTmpAnchorPos[1] - v6 - v10;
        int v12 = this.mTmpAnchorPos[1] + v4 + v6;
        if(z) {
            if(v11 >= 0) {
                windowManager$LayoutParams0.y = v11;
                return;
            }
            windowManager$LayoutParams0.y = v12;
            return;
        }
        if(v10 + v12 <= this.mTmpDisplayFrame.height()) {
            windowManager$LayoutParams0.y = v12;
            return;
        }
        windowManager$LayoutParams0.y = v11;
    }

    private static View getAppRootView(View view0) {
        View view1 = view0.getRootView();
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view1.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams)viewGroup$LayoutParams0).type != 2) {
            for(Context context0 = view0.getContext(); context0 instanceof ContextWrapper; context0 = ((ContextWrapper)context0).getBaseContext()) {
                if(context0 instanceof Activity) {
                    return ((Activity)context0).getWindow().getDecorView();
                }
            }
        }
        return view1;
    }

    void hide() {
        if(!this.isShowing()) {
            return;
        }
        ((WindowManager)this.mContext.getSystemService("window")).removeView(this.mContentView);
    }

    boolean isShowing() {
        return this.mContentView.getParent() != null;
    }

    void show(View view0, int v, int v1, boolean z, CharSequence charSequence0) {
        if(this.isShowing()) {
            this.hide();
        }
        this.mMessageView.setText(charSequence0);
        this.computePosition(view0, v, v1, z, this.mLayoutParams);
        ((WindowManager)this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }
}

