package com.nhn.android.oauth.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.nhn.android.oauth.R.id;
import com.nhn.android.oauth.R.layout;

public final class PackageListItemBinding implements ViewBinding {
    public final ImageView packageIcon;
    public final TextView packageName;
    private final LinearLayout rootView;

    private PackageListItemBinding(LinearLayout linearLayout0, ImageView imageView0, TextView textView0) {
        this.rootView = linearLayout0;
        this.packageIcon = imageView0;
        this.packageName = textView0;
    }

    public static PackageListItemBinding bind(View view0) {
        int v = id.package_icon;
        ImageView imageView0 = (ImageView)ViewBindings.findChildViewById(view0, v);
        if(imageView0 != null) {
            v = id.package_name;
            TextView textView0 = (TextView)ViewBindings.findChildViewById(view0, v);
            if(textView0 != null) {
                return new PackageListItemBinding(((LinearLayout)view0), imageView0, textView0);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view0.getResources().getResourceName(v));
    }

    @Override  // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.getRoot();
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static PackageListItemBinding inflate(LayoutInflater layoutInflater0) {
        return PackageListItemBinding.inflate(layoutInflater0, null, false);
    }

    public static PackageListItemBinding inflate(LayoutInflater layoutInflater0, ViewGroup viewGroup0, boolean z) {
        View view0 = layoutInflater0.inflate(layout.package_list_item, viewGroup0, false);
        if(z) {
            viewGroup0.addView(view0);
        }
        return PackageListItemBinding.bind(view0);
    }
}

