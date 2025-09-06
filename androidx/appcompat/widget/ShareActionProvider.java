package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.TypedValue;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.string;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ActionProvider;

public class ShareActionProvider extends ActionProvider {
    public interface OnShareTargetSelectedListener {
        boolean onShareTargetSelected(ShareActionProvider arg1, Intent arg2);
    }

    class ShareActivityChooserModelPolicy implements OnChooseActivityListener {
        @Override  // androidx.appcompat.widget.ActivityChooserModel$OnChooseActivityListener
        public boolean onChooseActivity(ActivityChooserModel activityChooserModel0, Intent intent0) {
            if(ShareActionProvider.this.mOnShareTargetSelectedListener != null) {
                ShareActionProvider.this.mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, intent0);
            }
            return false;
        }
    }

    class ShareMenuItemOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        @Override  // android.view.MenuItem$OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem0) {
            Intent intent0 = ActivityChooserModel.get(ShareActionProvider.this.mContext, ShareActionProvider.this.mShareHistoryFileName).chooseActivity(menuItem0.getItemId());
            if(intent0 != null) {
                String s = intent0.getAction();
                if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s)) {
                    ShareActionProvider.this.updateIntent(intent0);
                }
                ShareActionProvider.this.mContext.startActivity(intent0);
            }
            return true;
        }
    }

    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    final Context mContext;
    private int mMaxShownActivityCount;
    private OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener;
    OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    String mShareHistoryFileName;

    public ShareActionProvider(Context context0) {
        super(context0);
        this.mMaxShownActivityCount = 4;
        this.mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener(this);
        this.mShareHistoryFileName = "share_history.xml";
        this.mContext = context0;
    }

    @Override  // androidx.core.view.ActionProvider
    public boolean hasSubMenu() {
        return true;
    }

    @Override  // androidx.core.view.ActionProvider
    public View onCreateActionView() {
        View view0 = new ActivityChooserView(this.mContext);
        if(!((ActivityChooserView)view0).isInEditMode()) {
            ((ActivityChooserView)view0).setActivityChooserModel(ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName));
        }
        TypedValue typedValue0 = new TypedValue();
        this.mContext.getTheme().resolveAttribute(attr.actionModeShareDrawable, typedValue0, true);
        ((ActivityChooserView)view0).setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(this.mContext, typedValue0.resourceId));
        ((ActivityChooserView)view0).setProvider(this);
        ((ActivityChooserView)view0).setDefaultActionButtonContentDescription(string.abc_shareactionprovider_share_with_application);
        ((ActivityChooserView)view0).setExpandActivityOverflowButtonContentDescription(string.abc_shareactionprovider_share_with);
        return view0;
    }

    @Override  // androidx.core.view.ActionProvider
    public void onPrepareSubMenu(SubMenu subMenu0) {
        subMenu0.clear();
        ActivityChooserModel activityChooserModel0 = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        PackageManager packageManager0 = this.mContext.getPackageManager();
        int v = activityChooserModel0.getActivityCount();
        int v1 = Math.min(v, this.mMaxShownActivityCount);
        for(int v2 = 0; v2 < v1; ++v2) {
            ResolveInfo resolveInfo0 = activityChooserModel0.getActivity(v2);
            subMenu0.add(0, v2, v2, resolveInfo0.loadLabel(packageManager0)).setIcon(resolveInfo0.loadIcon(packageManager0)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
        }
        if(v1 < v) {
            SubMenu subMenu1 = subMenu0.addSubMenu(0, v1, v1, this.mContext.getString(string.abc_activity_chooser_view_see_all));
            for(int v3 = 0; v3 < v; ++v3) {
                ResolveInfo resolveInfo1 = activityChooserModel0.getActivity(v3);
                subMenu1.add(0, v3, v3, resolveInfo1.loadLabel(packageManager0)).setIcon(resolveInfo1.loadIcon(packageManager0)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
            }
        }
    }

    private void setActivityChooserPolicyIfNeeded() {
        if(this.mOnShareTargetSelectedListener == null) {
            return;
        }
        if(this.mOnChooseActivityListener == null) {
            this.mOnChooseActivityListener = new ShareActivityChooserModelPolicy(this);
        }
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setOnChooseActivityListener(this.mOnChooseActivityListener);
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener shareActionProvider$OnShareTargetSelectedListener0) {
        this.mOnShareTargetSelectedListener = shareActionProvider$OnShareTargetSelectedListener0;
        this.setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String s) {
        this.mShareHistoryFileName = s;
        this.setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent0) {
        if(intent0 != null) {
            String s = intent0.getAction();
            if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s)) {
                this.updateIntent(intent0);
            }
        }
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setIntent(intent0);
    }

    void updateIntent(Intent intent0) {
        intent0.addFlags(0x8080000);
    }
}

