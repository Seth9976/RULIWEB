package com.android.volley.toolbox;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageLoader {
    static class BatchedImageRequest {
        private final List mContainers;
        private VolleyError mError;
        private final Request mRequest;
        private Bitmap mResponseBitmap;

        public BatchedImageRequest(Request request0, ImageContainer imageLoader$ImageContainer0) {
            ArrayList arrayList0 = new ArrayList();
            this.mContainers = arrayList0;
            this.mRequest = request0;
            arrayList0.add(imageLoader$ImageContainer0);
        }

        static Bitmap access$000(BatchedImageRequest imageLoader$BatchedImageRequest0) {
            return imageLoader$BatchedImageRequest0.mResponseBitmap;
        }

        public void addContainer(ImageContainer imageLoader$ImageContainer0) {
            this.mContainers.add(imageLoader$ImageContainer0);
        }

        public VolleyError getError() {
            return this.mError;
        }

        public boolean removeContainerAndCancelIfNecessary(ImageContainer imageLoader$ImageContainer0) {
            this.mContainers.remove(imageLoader$ImageContainer0);
            if(this.mContainers.size() == 0) {
                this.mRequest.cancel();
                return true;
            }
            return false;
        }

        public void setError(VolleyError volleyError0) {
            this.mError = volleyError0;
        }
    }

    public interface ImageCache {
        Bitmap getBitmap(String arg1);

        void putBitmap(String arg1, Bitmap arg2);
    }

    public class ImageContainer {
        private Bitmap mBitmap;
        private final String mCacheKey;
        private final ImageListener mListener;
        private final String mRequestUrl;

        public ImageContainer(Bitmap bitmap0, String s, String s1, ImageListener imageLoader$ImageListener0) {
            this.mBitmap = bitmap0;
            this.mRequestUrl = s;
            this.mCacheKey = s1;
            this.mListener = imageLoader$ImageListener0;
        }

        static ImageListener access$400(ImageContainer imageLoader$ImageContainer0) {
            return imageLoader$ImageContainer0.mListener;
        }

        static Bitmap access$502(ImageContainer imageLoader$ImageContainer0, Bitmap bitmap0) {
            imageLoader$ImageContainer0.mBitmap = bitmap0;
            return bitmap0;
        }

        public void cancelRequest() {
            Threads.throwIfNotOnMainThread();
            if(this.mListener != null) {
                BatchedImageRequest imageLoader$BatchedImageRequest0 = (BatchedImageRequest)ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
                if(imageLoader$BatchedImageRequest0 == null) {
                    BatchedImageRequest imageLoader$BatchedImageRequest1 = (BatchedImageRequest)ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
                    if(imageLoader$BatchedImageRequest1 != null) {
                        imageLoader$BatchedImageRequest1.removeContainerAndCancelIfNecessary(this);
                        if(imageLoader$BatchedImageRequest1.mContainers.size() == 0) {
                            ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
                        }
                    }
                }
                else if(imageLoader$BatchedImageRequest0.removeContainerAndCancelIfNecessary(this)) {
                    ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
                }
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }
    }

    public interface ImageListener extends ErrorListener {
        void onResponse(ImageContainer arg1, boolean arg2);
    }

    private int mBatchResponseDelayMs;
    private final HashMap mBatchedResponses;
    private final ImageCache mCache;
    private final Handler mHandler;
    private final HashMap mInFlightRequests;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;

    public ImageLoader(RequestQueue requestQueue0, ImageCache imageLoader$ImageCache0) {
        this.mBatchResponseDelayMs = 100;
        this.mInFlightRequests = new HashMap();
        this.mBatchedResponses = new HashMap();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = requestQueue0;
        this.mCache = imageLoader$ImageCache0;
    }

    private void batchResponse(String s, BatchedImageRequest imageLoader$BatchedImageRequest0) {
        this.mBatchedResponses.put(s, imageLoader$BatchedImageRequest0);
        if(this.mRunnable == null) {
            com.android.volley.toolbox.ImageLoader.4 imageLoader$40 = new Runnable() {
                @Override
                public void run() {
                    for(Object object0: ImageLoader.this.mBatchedResponses.values()) {
                        BatchedImageRequest imageLoader$BatchedImageRequest0 = (BatchedImageRequest)object0;
                        for(Object object1: BatchedImageRequest.access$300(imageLoader$BatchedImageRequest0)) {
                            ImageContainer imageLoader$ImageContainer0 = (ImageContainer)object1;
                            if(ImageContainer.access$400(imageLoader$ImageContainer0) == null) {
                            }
                            else if(imageLoader$BatchedImageRequest0.getError() == null) {
                                ImageContainer.access$502(imageLoader$ImageContainer0, BatchedImageRequest.access$000(imageLoader$BatchedImageRequest0));
                                ImageContainer.access$400(imageLoader$ImageContainer0).onResponse(imageLoader$ImageContainer0, false);
                            }
                            else {
                                ImageContainer.access$400(imageLoader$ImageContainer0).onErrorResponse(imageLoader$BatchedImageRequest0.getError());
                            }
                        }
                    }
                    ImageLoader.this.mBatchedResponses.clear();
                    ImageLoader.this.mRunnable = null;
                }
            };
            this.mRunnable = imageLoader$40;
            this.mHandler.postDelayed(imageLoader$40, ((long)this.mBatchResponseDelayMs));
        }
    }

    public ImageContainer get(String s, ImageListener imageLoader$ImageListener0) {
        return this.get(s, imageLoader$ImageListener0, 0, 0);
    }

    public ImageContainer get(String s, ImageListener imageLoader$ImageListener0, int v, int v1) {
        return this.get(s, imageLoader$ImageListener0, v, v1, ImageView.ScaleType.CENTER_INSIDE);
    }

    public ImageContainer get(String s, ImageListener imageLoader$ImageListener0, int v, int v1, ImageView.ScaleType imageView$ScaleType0) {
        Threads.throwIfNotOnMainThread();
        String s1 = ImageLoader.getCacheKey(s, v, v1, imageView$ScaleType0);
        Bitmap bitmap0 = this.mCache.getBitmap(s1);
        if(bitmap0 != null) {
            ImageContainer imageLoader$ImageContainer0 = new ImageContainer(this, bitmap0, s, null, null);
            imageLoader$ImageListener0.onResponse(imageLoader$ImageContainer0, true);
            return imageLoader$ImageContainer0;
        }
        ImageContainer imageLoader$ImageContainer1 = new ImageContainer(this, null, s, s1, imageLoader$ImageListener0);
        imageLoader$ImageListener0.onResponse(imageLoader$ImageContainer1, true);
        BatchedImageRequest imageLoader$BatchedImageRequest0 = (BatchedImageRequest)this.mInFlightRequests.get(s1);
        if(imageLoader$BatchedImageRequest0 == null) {
            imageLoader$BatchedImageRequest0 = (BatchedImageRequest)this.mBatchedResponses.get(s1);
        }
        if(imageLoader$BatchedImageRequest0 != null) {
            imageLoader$BatchedImageRequest0.addContainer(imageLoader$ImageContainer1);
            return imageLoader$ImageContainer1;
        }
        Request request0 = this.makeImageRequest(s, v, v1, imageView$ScaleType0, s1);
        this.mRequestQueue.add(request0);
        BatchedImageRequest imageLoader$BatchedImageRequest1 = new BatchedImageRequest(request0, imageLoader$ImageContainer1);
        this.mInFlightRequests.put(s1, imageLoader$BatchedImageRequest1);
        return imageLoader$ImageContainer1;
    }

    private static String getCacheKey(String s, int v, int v1, ImageView.ScaleType imageView$ScaleType0) {
        return "#W" + v + "#H" + v1 + "#S" + imageView$ScaleType0.ordinal() + s;
    }

    public static ImageListener getImageListener(ImageView imageView0, int v, int v1) {
        return new ImageListener() {
            @Override  // com.android.volley.Response$ErrorListener
            public void onErrorResponse(VolleyError volleyError0) {
                int v = v1;
                if(v != 0) {
                    imageView0.setImageResource(v);
                }
            }

            @Override  // com.android.volley.toolbox.ImageLoader$ImageListener
            public void onResponse(ImageContainer imageLoader$ImageContainer0, boolean z) {
                if(imageLoader$ImageContainer0.getBitmap() != null) {
                    imageView0.setImageBitmap(imageLoader$ImageContainer0.getBitmap());
                    return;
                }
                int v = v;
                if(v != 0) {
                    imageView0.setImageResource(v);
                }
            }
        };
    }

    public boolean isCached(String s, int v, int v1) {
        return this.isCached(s, v, v1, ImageView.ScaleType.CENTER_INSIDE);
    }

    public boolean isCached(String s, int v, int v1, ImageView.ScaleType imageView$ScaleType0) {
        Threads.throwIfNotOnMainThread();
        String s1 = ImageLoader.getCacheKey(s, v, v1, imageView$ScaleType0);
        return this.mCache.getBitmap(s1) != null;
    }

    protected Request makeImageRequest(String s, int v, int v1, ImageView.ScaleType imageView$ScaleType0, String s1) {
        return new ImageRequest(s, new Listener() {
            public void onResponse(Bitmap bitmap0) {
                ImageLoader.this.onGetImageSuccess(s1, bitmap0);
            }

            @Override  // com.android.volley.Response$Listener
            public void onResponse(Object object0) {
                this.onResponse(((Bitmap)object0));
            }
        }, v, v1, imageView$ScaleType0, Bitmap.Config.RGB_565, (VolleyError volleyError0) -> {
            BatchedImageRequest imageLoader$BatchedImageRequest0 = (BatchedImageRequest)ImageLoader.this.mInFlightRequests.remove(this.val$cacheKey);
            if(imageLoader$BatchedImageRequest0 != null) {
                imageLoader$BatchedImageRequest0.setError(volleyError0);
                ImageLoader.this.batchResponse(this.val$cacheKey, imageLoader$BatchedImageRequest0);
            }
        });

        class com.android.volley.toolbox.ImageLoader.3 implements ErrorListener {
            com.android.volley.toolbox.ImageLoader.3(String s) {
            }

            @Override  // com.android.volley.Response$ErrorListener
            public void onErrorResponse(VolleyError volleyError0) {
                ImageLoader.this.onGetImageError(this.val$cacheKey, volleyError0);
            }
        }

    }

    // 检测为 Lambda 实现
    protected void onGetImageError(String s, VolleyError volleyError0) [...]

    protected void onGetImageSuccess(String s, Bitmap bitmap0) {
        this.mCache.putBitmap(s, bitmap0);
        BatchedImageRequest imageLoader$BatchedImageRequest0 = (BatchedImageRequest)this.mInFlightRequests.remove(s);
        if(imageLoader$BatchedImageRequest0 != null) {
            imageLoader$BatchedImageRequest0.mResponseBitmap = bitmap0;
            this.batchResponse(s, imageLoader$BatchedImageRequest0);
        }
    }

    public void setBatchedResponseDelay(int v) {
        this.mBatchResponseDelayMs = v;
    }
}

