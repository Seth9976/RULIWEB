package com.android.volley.toolbox;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.widget.ImageView.ScaleType;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request.Priority;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

public class ImageRequest extends Request {
    public static final float DEFAULT_IMAGE_BACKOFF_MULT = 2.0f;
    public static final int DEFAULT_IMAGE_MAX_RETRIES = 2;
    public static final int DEFAULT_IMAGE_TIMEOUT_MS = 1000;
    private final Bitmap.Config mDecodeConfig;
    private Listener mListener;
    private final Object mLock;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final ImageView.ScaleType mScaleType;
    private static final Object sDecodeLock;

    static {
        ImageRequest.sDecodeLock = new Object();
    }

    @Deprecated
    public ImageRequest(String s, Listener response$Listener0, int v, int v1, Bitmap.Config bitmap$Config0, ErrorListener response$ErrorListener0) {
        this(s, response$Listener0, v, v1, ImageView.ScaleType.CENTER_INSIDE, bitmap$Config0, response$ErrorListener0);
    }

    public ImageRequest(String s, Listener response$Listener0, int v, int v1, ImageView.ScaleType imageView$ScaleType0, Bitmap.Config bitmap$Config0, ErrorListener response$ErrorListener0) {
        super(0, s, response$ErrorListener0);
        this.mLock = new Object();
        this.setRetryPolicy(new DefaultRetryPolicy(1000, 2, 2.0f));
        this.mListener = response$Listener0;
        this.mDecodeConfig = bitmap$Config0;
        this.mMaxWidth = v;
        this.mMaxHeight = v1;
        this.mScaleType = imageView$ScaleType0;
    }

    @Override  // com.android.volley.Request
    public void cancel() {
        super.cancel();
        synchronized(this.mLock) {
            this.mListener = null;
        }
    }

    protected void deliverResponse(Bitmap bitmap0) {
        Listener response$Listener0;
        synchronized(this.mLock) {
            response$Listener0 = this.mListener;
        }
        if(response$Listener0 != null) {
            response$Listener0.onResponse(bitmap0);
        }
    }

    @Override  // com.android.volley.Request
    protected void deliverResponse(Object object0) {
        this.deliverResponse(((Bitmap)object0));
    }

    private Response doParse(NetworkResponse networkResponse0) {
        Bitmap bitmap0;
        byte[] arr_b = networkResponse0.data;
        BitmapFactory.Options bitmapFactory$Options0 = new BitmapFactory.Options();
        if(this.mMaxWidth == 0 && this.mMaxHeight == 0) {
            bitmapFactory$Options0.inPreferredConfig = this.mDecodeConfig;
            bitmap0 = BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length, bitmapFactory$Options0);
            return bitmap0 == null ? Response.error(new ParseError(networkResponse0)) : Response.success(bitmap0, HttpHeaderParser.parseCacheHeaders(networkResponse0));
        }
        bitmapFactory$Options0.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length, bitmapFactory$Options0);
        int v = bitmapFactory$Options0.outWidth;
        int v1 = bitmapFactory$Options0.outHeight;
        int v2 = ImageRequest.getResizedDimension(this.mMaxWidth, this.mMaxHeight, v, v1, this.mScaleType);
        int v3 = ImageRequest.getResizedDimension(this.mMaxHeight, this.mMaxWidth, v1, v, this.mScaleType);
        bitmapFactory$Options0.inJustDecodeBounds = false;
        bitmapFactory$Options0.inSampleSize = ImageRequest.findBestSampleSize(v, v1, v2, v3);
        bitmap0 = BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length, bitmapFactory$Options0);
        if(bitmap0 != null && (bitmap0.getWidth() > v2 || bitmap0.getHeight() > v3)) {
            Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap0, v2, v3, true);
            bitmap0.recycle();
            bitmap0 = bitmap1;
        }
        return bitmap0 == null ? Response.error(new ParseError(networkResponse0)) : Response.success(bitmap0, HttpHeaderParser.parseCacheHeaders(networkResponse0));
    }

    static int findBestSampleSize(int v, int v1, int v2, int v3) {
        double f = Math.min(((double)v) / ((double)v2), ((double)v1) / ((double)v3));
        float f1;
        for(f1 = 1.0f; ((double)(2.0f * f1)) <= f; f1 *= 2.0f) {
        }
        return (int)f1;
    }

    @Override  // com.android.volley.Request
    public Priority getPriority() {
        return Priority.LOW;
    }

    private static int getResizedDimension(int v, int v1, int v2, int v3, ImageView.ScaleType imageView$ScaleType0) {
        if(v != 0 || v1 != 0) {
            if(imageView$ScaleType0 == ImageView.ScaleType.FIT_XY) {
                return v == 0 ? v2 : v;
            }
            if(v == 0) {
                return (int)(((double)v2) * (((double)v1) / ((double)v3)));
            }
            if(v1 != 0) {
                double f = ((double)v3) / ((double)v2);
                if(imageView$ScaleType0 == ImageView.ScaleType.CENTER_CROP) {
                    return ((double)v) * f < ((double)v1) ? ((int)(((double)v1) / f)) : v;
                }
                return ((double)v) * f > ((double)v1) ? ((int)(((double)v1) / f)) : v;
            }
            return v;
        }
        return v2;
    }

    @Override  // com.android.volley.Request
    protected Response parseNetworkResponse(NetworkResponse networkResponse0) {
        synchronized(ImageRequest.sDecodeLock) {
            try {
                return this.doParse(networkResponse0);
            }
            catch(OutOfMemoryError outOfMemoryError0) {
                String s = this.getUrl();
                VolleyLog.e("Caught OOM for %d byte image, url=%s", new Object[]{((int)networkResponse0.data.length), s});
                return Response.error(new ParseError(outOfMemoryError0));
            }
        }
    }
}

