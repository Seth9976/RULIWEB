package com.airbnb.lottie.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable.Callback;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageAssetManager {
    private static final Object bitmapHashLock;
    private final Context context;
    private ImageAssetDelegate delegate;
    private final Map imageAssets;
    private String imagesFolder;

    static {
        ImageAssetManager.bitmapHashLock = new Object();
    }

    public ImageAssetManager(Drawable.Callback drawable$Callback0, String s, ImageAssetDelegate imageAssetDelegate0, Map map0) {
        this.imagesFolder = s;
        if(!TextUtils.isEmpty(s) && this.imagesFolder.charAt(this.imagesFolder.length() - 1) != 0x2F) {
            this.imagesFolder = this.imagesFolder + '/';
        }
        if(!(drawable$Callback0 instanceof View)) {
            Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.imageAssets = new HashMap();
            this.context = null;
            return;
        }
        this.context = ((View)drawable$Callback0).getContext();
        this.imageAssets = map0;
        this.setDelegate(imageAssetDelegate0);
    }

    public Bitmap bitmapForId(String s) {
        LottieImageAsset lottieImageAsset0 = (LottieImageAsset)this.imageAssets.get(s);
        if(lottieImageAsset0 == null) {
            return null;
        }
        Bitmap bitmap0 = lottieImageAsset0.getBitmap();
        if(bitmap0 != null) {
            return bitmap0;
        }
        ImageAssetDelegate imageAssetDelegate0 = this.delegate;
        if(imageAssetDelegate0 != null) {
            Bitmap bitmap1 = imageAssetDelegate0.fetchBitmap(lottieImageAsset0);
            if(bitmap1 != null) {
                this.putBitmap(s, bitmap1);
            }
            return bitmap1;
        }
        String s1 = lottieImageAsset0.getFileName();
        BitmapFactory.Options bitmapFactory$Options0 = new BitmapFactory.Options();
        bitmapFactory$Options0.inScaled = true;
        bitmapFactory$Options0.inDensity = 0xA0;
        if(s1.startsWith("data:") && s1.indexOf("base64,") > 0) {
            try {
                byte[] arr_b = Base64.decode(s1.substring(s1.indexOf(44) + 1), 0);
                return this.putBitmap(s, BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length, bitmapFactory$Options0));
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                Logger.warning("data URL did not have correct base64 format.", illegalArgumentException0);
                return null;
            }
        }
        try {
            if(TextUtils.isEmpty(this.imagesFolder)) {
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            }
            return this.putBitmap(s, Utils.resizeBitmapIfNeeded(BitmapFactory.decodeStream(this.context.getAssets().open(this.imagesFolder + s1), null, bitmapFactory$Options0), lottieImageAsset0.getWidth(), lottieImageAsset0.getHeight()));
        }
        catch(IOException iOException0) {
            Logger.warning("Unable to open asset.", iOException0);
            return null;
        }
    }

    // 去混淆评级： 低(30)
    public boolean hasSameContext(Context context0) {
        return context0 == null && this.context == null || this.context.equals(context0);
    }

    private Bitmap putBitmap(String s, Bitmap bitmap0) {
        synchronized(ImageAssetManager.bitmapHashLock) {
            ((LottieImageAsset)this.imageAssets.get(s)).setBitmap(bitmap0);
            return bitmap0;
        }
    }

    public void setDelegate(ImageAssetDelegate imageAssetDelegate0) {
        this.delegate = imageAssetDelegate0;
    }

    public Bitmap updateBitmap(String s, Bitmap bitmap0) {
        if(bitmap0 == null) {
            LottieImageAsset lottieImageAsset0 = (LottieImageAsset)this.imageAssets.get(s);
            lottieImageAsset0.setBitmap(null);
            return lottieImageAsset0.getBitmap();
        }
        Bitmap bitmap1 = ((LottieImageAsset)this.imageAssets.get(s)).getBitmap();
        this.putBitmap(s, bitmap0);
        return bitmap1;
    }
}

