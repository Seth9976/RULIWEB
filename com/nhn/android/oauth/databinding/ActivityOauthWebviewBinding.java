package com.nhn.android.oauth.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.navercorp.nid.oauth.view.DownloadBanner;
import com.nhn.android.oauth.R.id;
import com.nhn.android.oauth.R.layout;

public final class ActivityOauthWebviewBinding implements ViewBinding {
    public final DownloadBanner appDownloadBanner;
    public final ProgressBar progressBar;
    private final RelativeLayout rootView;
    public final WebView webView;
    public final ImageView webviewEndKey;
    public final LinearLayout webviewNaviBar;
    public final LinearLayout wholeView;

    private ActivityOauthWebviewBinding(RelativeLayout relativeLayout0, DownloadBanner downloadBanner0, ProgressBar progressBar0, WebView webView0, ImageView imageView0, LinearLayout linearLayout0, LinearLayout linearLayout1) {
        this.rootView = relativeLayout0;
        this.appDownloadBanner = downloadBanner0;
        this.progressBar = progressBar0;
        this.webView = webView0;
        this.webviewEndKey = imageView0;
        this.webviewNaviBar = linearLayout0;
        this.wholeView = linearLayout1;
    }

    public static ActivityOauthWebviewBinding bind(View view0) {
        int v = id.app_download_banner;
        View view1 = ViewBindings.findChildViewById(view0, v);
        if(((DownloadBanner)view1) != null) {
            v = id.progressBar;
            View view2 = ViewBindings.findChildViewById(view0, v);
            if(((ProgressBar)view2) != null) {
                v = id.webView;
                View view3 = ViewBindings.findChildViewById(view0, v);
                if(((WebView)view3) != null) {
                    v = id.webviewEndKey;
                    View view4 = ViewBindings.findChildViewById(view0, v);
                    if(((ImageView)view4) != null) {
                        v = id.webviewNaviBar;
                        View view5 = ViewBindings.findChildViewById(view0, v);
                        if(((LinearLayout)view5) != null) {
                            v = id.wholeView;
                            View view6 = ViewBindings.findChildViewById(view0, v);
                            if(((LinearLayout)view6) != null) {
                                return new ActivityOauthWebviewBinding(((RelativeLayout)view0), ((DownloadBanner)view1), ((ProgressBar)view2), ((WebView)view3), ((ImageView)view4), ((LinearLayout)view5), ((LinearLayout)view6));
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view0.getResources().getResourceName(v));
    }

    @Override  // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.getRoot();
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityOauthWebviewBinding inflate(LayoutInflater layoutInflater0) {
        return ActivityOauthWebviewBinding.inflate(layoutInflater0, null, false);
    }

    public static ActivityOauthWebviewBinding inflate(LayoutInflater layoutInflater0, ViewGroup viewGroup0, boolean z) {
        View view0 = layoutInflater0.inflate(layout.activity_oauth_webview, viewGroup0, false);
        if(z) {
            viewGroup0.addView(view0);
        }
        return ActivityOauthWebviewBinding.bind(view0);
    }
}

