package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import com.android.volley.VolleyError;

public class NetworkImageView extends ImageView {
    private Bitmap mDefaultImageBitmap;
    private Drawable mDefaultImageDrawable;
    private int mDefaultImageId;
    private Bitmap mErrorImageBitmap;
    private Drawable mErrorImageDrawable;
    private int mErrorImageId;
    private ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    public NetworkImageView(Context context0) {
        this(context0, null);
    }

    public NetworkImageView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public NetworkImageView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // android.widget.ImageView
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.invalidate();
    }

    void loadImageIfNecessary(boolean z) {
        boolean z3;
        boolean z2;
        int v = this.getWidth();
        int v1 = this.getHeight();
        ImageView.ScaleType imageView$ScaleType0 = this.getScaleType();
        boolean z1 = true;
        if(this.getLayoutParams() == null) {
            z2 = false;
            z3 = false;
        }
        else {
            z2 = this.getLayoutParams().width == -2;
            z3 = this.getLayoutParams().height == -2;
        }
        if(!z2 || !z3) {
            z1 = false;
        }
        if(v != 0 || v1 != 0 || z1) {
            if(TextUtils.isEmpty(this.mUrl)) {
                ImageContainer imageLoader$ImageContainer0 = this.mImageContainer;
                if(imageLoader$ImageContainer0 != null) {
                    imageLoader$ImageContainer0.cancelRequest();
                    this.mImageContainer = null;
                }
                this.setDefaultImageOrNull();
                return;
            }
            boolean z4 = false;
            if(this.mImageContainer == null || this.mImageContainer.getRequestUrl() == null) {
                z4 = true;
            }
            else if(!this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                z4 = true;
                this.mImageContainer.cancelRequest();
                this.setDefaultImageOrNull();
            }
            if(z4) {
                if(z2) {
                    v = 0;
                }
                this.mImageContainer = this.mImageLoader.get(this.mUrl, new ImageListener() {
                    @Override  // com.android.volley.Response$ErrorListener
                    public void onErrorResponse(VolleyError volleyError0) {
                        if(NetworkImageView.this.mErrorImageId != 0) {
                            int v = NetworkImageView.this.mErrorImageId;
                            NetworkImageView.this.setImageResource(v);
                            return;
                        }
                        if(NetworkImageView.this.mErrorImageDrawable != null) {
                            Drawable drawable0 = NetworkImageView.this.mErrorImageDrawable;
                            NetworkImageView.this.setImageDrawable(drawable0);
                            return;
                        }
                        if(NetworkImageView.this.mErrorImageBitmap != null) {
                            Bitmap bitmap0 = NetworkImageView.this.mErrorImageBitmap;
                            NetworkImageView.this.setImageBitmap(bitmap0);
                        }
                    }

                    // 检测为 Lambda 实现
                    @Override  // com.android.volley.toolbox.ImageLoader$ImageListener
                    public void onResponse(ImageContainer imageLoader$ImageContainer0, boolean z) [...]
                }, v, (z3 ? 0 : v1), imageView$ScaleType0);
            }
        }
    }

    @Override  // android.widget.ImageView
    protected void onDetachedFromWindow() {
        ImageContainer imageLoader$ImageContainer0 = this.mImageContainer;
        if(imageLoader$ImageContainer0 != null) {
            imageLoader$ImageContainer0.cancelRequest();
            this.setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    @Override  // android.view.View
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.loadImageIfNecessary(true);
    }

    public void setDefaultImageBitmap(Bitmap bitmap0) {
        this.mDefaultImageId = 0;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageBitmap = bitmap0;
    }

    public void setDefaultImageDrawable(Drawable drawable0) {
        this.mDefaultImageId = 0;
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = drawable0;
    }

    private void setDefaultImageOrNull() {
        int v = this.mDefaultImageId;
        if(v != 0) {
            this.setImageResource(v);
            return;
        }
        Drawable drawable0 = this.mDefaultImageDrawable;
        if(drawable0 != null) {
            this.setImageDrawable(drawable0);
            return;
        }
        Bitmap bitmap0 = this.mDefaultImageBitmap;
        if(bitmap0 != null) {
            this.setImageBitmap(bitmap0);
            return;
        }
        this.setImageBitmap(null);
    }

    public void setDefaultImageResId(int v) {
        this.mDefaultImageBitmap = null;
        this.mDefaultImageDrawable = null;
        this.mDefaultImageId = v;
    }

    public void setErrorImageBitmap(Bitmap bitmap0) {
        this.mErrorImageId = 0;
        this.mErrorImageDrawable = null;
        this.mErrorImageBitmap = bitmap0;
    }

    public void setErrorImageDrawable(Drawable drawable0) {
        this.mErrorImageId = 0;
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = drawable0;
    }

    public void setErrorImageResId(int v) {
        this.mErrorImageBitmap = null;
        this.mErrorImageDrawable = null;
        this.mErrorImageId = v;
    }

    public void setImageUrl(String s, ImageLoader imageLoader0) {
        Threads.throwIfNotOnMainThread();
        this.mUrl = s;
        this.mImageLoader = imageLoader0;
        this.loadImageIfNecessary(false);
    }
}

