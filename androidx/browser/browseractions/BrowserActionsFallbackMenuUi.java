package androidx.browser.browseractions;

import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface.OnShowListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.browser.R.id;
import androidx.browser.R.layout;
import androidx.browser.R.string;
import androidx.core.widget.TextViewCompat;
import java.util.ArrayList;
import java.util.List;

@Deprecated
class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View arg1);
    }

    private static final String TAG = "BrowserActionskMenuUi";
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    final Context mContext;
    private final List mMenuItems;
    BrowserActionsFallMenuUiListener mMenuUiListener;
    final Uri mUri;

    BrowserActionsFallbackMenuUi(Context context0, Uri uri0, List list0) {
        this.mContext = context0;
        this.mUri = uri0;
        this.mMenuItems = this.buildFallbackMenuItemList(list0);
    }

    private Runnable buildCopyAction() {
        return new Runnable() {
            @Override
            public void run() {
                ((ClipboardManager)BrowserActionsFallbackMenuUi.this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("url", BrowserActionsFallbackMenuUi.this.mUri.toString()));
                String s = BrowserActionsFallbackMenuUi.this.mContext.getString(string.copy_toast_msg);
                Toast.makeText(BrowserActionsFallbackMenuUi.this.mContext, s, 0).show();
            }
        };
    }

    private List buildFallbackMenuItemList(List list0) {
        List list1 = new ArrayList();
        list1.add(new BrowserActionItem(this.mContext.getString(string.fallback_menu_item_open_in_browser), this.buildOpenInBrowserAction()));
        list1.add(new BrowserActionItem(this.mContext.getString(string.fallback_menu_item_copy_link), this.buildCopyAction()));
        list1.add(new BrowserActionItem(this.mContext.getString(string.fallback_menu_item_share_link), this.buildShareAction()));
        list1.addAll(list0);
        return list1;
    }

    private PendingIntent buildOpenInBrowserAction() {
        Intent intent0 = new Intent("android.intent.action.VIEW", this.mUri);
        return PendingIntent.getActivity(this.mContext, 0, intent0, 0x4000000);
    }

    private PendingIntent buildShareAction() {
        Intent intent0 = new Intent("android.intent.action.SEND");
        intent0.putExtra("android.intent.extra.TEXT", this.mUri.toString());
        intent0.setType("text/plain");
        return PendingIntent.getActivity(this.mContext, 0, intent0, 0x4000000);
    }

    public void displayMenu() {
        View view0 = LayoutInflater.from(this.mContext).inflate(layout.browser_actions_context_menu_page, null);
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView0 = this.initMenuView(view0);
        BrowserActionsFallbackMenuDialog browserActionsFallbackMenuDialog0 = new BrowserActionsFallbackMenuDialog(this.mContext, browserActionsFallbackMenuView0);
        this.mBrowserActionsDialog = browserActionsFallbackMenuDialog0;
        browserActionsFallbackMenuDialog0.setContentView(view0);
        if(this.mMenuUiListener != null) {
            this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override  // android.content.DialogInterface$OnShowListener
                public void onShow(DialogInterface dialogInterface0) {
                    if(BrowserActionsFallbackMenuUi.this.mMenuUiListener == null) {
                        Log.e("BrowserActionskMenuUi", "Cannot trigger menu item listener, it is null");
                        return;
                    }
                    BrowserActionsFallbackMenuUi.this.mMenuUiListener.onMenuShown(view0);
                }
            });
        }
        this.mBrowserActionsDialog.show();
    }

    private BrowserActionsFallbackMenuView initMenuView(View view0) {
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView0 = (BrowserActionsFallbackMenuView)view0.findViewById(id.browser_actions_menu_view);
        TextView textView0 = (TextView)view0.findViewById(id.browser_actions_header_text);
        textView0.setText(this.mUri.toString());
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(TextViewCompat.getMaxLines(textView0) == 0x7FFFFFFF) {
                    textView0.setMaxLines(1);
                    textView0.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                textView0.setMaxLines(0x7FFFFFFF);
                textView0.setEllipsize(null);
            }
        });
        ListView listView0 = (ListView)view0.findViewById(id.browser_actions_menu_items);
        listView0.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
        listView0.setOnItemClickListener(this);
        return browserActionsFallbackMenuView0;
    }

    @Override  // android.widget.AdapterView$OnItemClickListener
    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
        BrowserActionItem browserActionItem0 = (BrowserActionItem)this.mMenuItems.get(v);
        if(browserActionItem0.getAction() != null) {
            try {
                browserActionItem0.getAction().send();
            }
            catch(PendingIntent.CanceledException pendingIntent$CanceledException0) {
                Log.e("BrowserActionskMenuUi", "Failed to send custom item action", pendingIntent$CanceledException0);
            }
        }
        else if(browserActionItem0.getRunnableAction() != null) {
            browserActionItem0.getRunnableAction().run();
        }
        BrowserActionsFallbackMenuDialog browserActionsFallbackMenuDialog0 = this.mBrowserActionsDialog;
        if(browserActionsFallbackMenuDialog0 == null) {
            Log.e("BrowserActionskMenuUi", "Cannot dismiss dialog, it has already been dismissed.");
            return;
        }
        browserActionsFallbackMenuDialog0.dismiss();
    }

    void setMenuUiListener(BrowserActionsFallMenuUiListener browserActionsFallbackMenuUi$BrowserActionsFallMenuUiListener0) {
        this.mMenuUiListener = browserActionsFallbackMenuUi$BrowserActionsFallMenuUiListener0;
    }
}

