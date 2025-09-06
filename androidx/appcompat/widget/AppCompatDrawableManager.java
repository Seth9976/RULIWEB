package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.color;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.drawable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;

public final class AppCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = null;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String TAG = "AppCompatDrawableManag";
    private ResourceManagerInternal mResourceManager;

    static {
        AppCompatDrawableManager.DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    }

    public static AppCompatDrawableManager get() {
        synchronized(AppCompatDrawableManager.class) {
            if(AppCompatDrawableManager.INSTANCE == null) {
                AppCompatDrawableManager.preload();
            }
            return AppCompatDrawableManager.INSTANCE;
        }
    }

    public Drawable getDrawable(Context context0, int v) {
        synchronized(this) {
            return this.mResourceManager.getDrawable(context0, v);
        }
    }

    Drawable getDrawable(Context context0, int v, boolean z) {
        synchronized(this) {
            return this.mResourceManager.getDrawable(context0, v, z);
        }
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int v, PorterDuff.Mode porterDuff$Mode0) {
        synchronized(AppCompatDrawableManager.class) {
            return ResourceManagerInternal.getPorterDuffColorFilter(v, porterDuff$Mode0);
        }
    }

    ColorStateList getTintList(Context context0, int v) {
        synchronized(this) {
            return this.mResourceManager.getTintList(context0, v);
        }
    }

    public void onConfigurationChanged(Context context0) {
        synchronized(this) {
            this.mResourceManager.onConfigurationChanged(context0);
        }
    }

    Drawable onDrawableLoadedFromResources(Context context0, VectorEnabledTintResources vectorEnabledTintResources0, int v) {
        synchronized(this) {
            return this.mResourceManager.onDrawableLoadedFromResources(context0, vectorEnabledTintResources0, v);
        }
    }

    public static void preload() {
        synchronized(AppCompatDrawableManager.class) {
            if(AppCompatDrawableManager.INSTANCE == null) {
                AppCompatDrawableManager appCompatDrawableManager0 = new AppCompatDrawableManager();
                AppCompatDrawableManager.INSTANCE = appCompatDrawableManager0;
                appCompatDrawableManager0.mResourceManager = ResourceManagerInternal.get();
                AppCompatDrawableManager.INSTANCE.mResourceManager.setHooks(new ResourceManagerHooks() {
                    private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
                    private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
                    private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
                    private final int[] TINT_CHECKABLE_BUTTON_LIST;
                    private final int[] TINT_COLOR_CONTROL_NORMAL;
                    private final int[] TINT_COLOR_CONTROL_STATE_LIST;

                    {
                        this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_textfield_search_default_mtrl_alpha, drawable.abc_textfield_default_mtrl_alpha, drawable.abc_ab_share_pack_mtrl_alpha};
                        this.TINT_COLOR_CONTROL_NORMAL = new int[]{drawable.abc_ic_commit_search_api_mtrl_alpha, drawable.abc_seekbar_tick_mark_material, drawable.abc_ic_menu_share_mtrl_alpha, drawable.abc_ic_menu_copy_mtrl_am_alpha, drawable.abc_ic_menu_cut_mtrl_alpha, drawable.abc_ic_menu_selectall_mtrl_alpha, drawable.abc_ic_menu_paste_mtrl_am_alpha};
                        this.COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{drawable.abc_textfield_activated_mtrl_alpha, drawable.abc_textfield_search_activated_mtrl_alpha, drawable.abc_cab_background_top_mtrl_alpha, drawable.abc_text_cursor_material, drawable.abc_text_select_handle_left_mtrl, drawable.abc_text_select_handle_middle_mtrl, drawable.abc_text_select_handle_right_mtrl};
                        this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{drawable.abc_popup_background_mtrl_mult, drawable.abc_cab_background_internal_bg, drawable.abc_menu_hardkey_panel_mtrl_mult};
                        this.TINT_COLOR_CONTROL_STATE_LIST = new int[]{drawable.abc_tab_indicator_material, drawable.abc_textfield_search_material};
                        this.TINT_CHECKABLE_BUTTON_LIST = new int[]{drawable.abc_btn_check_material, drawable.abc_btn_radio_material, drawable.abc_btn_check_material_anim, drawable.abc_btn_radio_material_anim};
                    }

                    private boolean arrayContains(int[] arr_v, int v) {
                        for(int v1 = 0; v1 < arr_v.length; ++v1) {
                            if(arr_v[v1] == v) {
                                return true;
                            }
                        }
                        return false;
                    }

                    private ColorStateList createBorderlessButtonColorStateList(Context context0) {
                        return this.createButtonColorStateList(context0, 0);
                    }

                    private ColorStateList createButtonColorStateList(Context context0, int v) {
                        int[][] arr2_v = new int[4][];
                        int[] arr_v = new int[4];
                        int v1 = ThemeUtils.getThemeAttrColor(context0, attr.colorControlHighlight);
                        int v2 = ThemeUtils.getDisabledThemeAttrColor(context0, attr.colorButtonNormal);
                        arr2_v[0] = ThemeUtils.DISABLED_STATE_SET;
                        arr_v[0] = v2;
                        arr2_v[1] = ThemeUtils.PRESSED_STATE_SET;
                        arr_v[1] = ColorUtils.compositeColors(v1, v);
                        arr2_v[2] = ThemeUtils.FOCUSED_STATE_SET;
                        arr_v[2] = ColorUtils.compositeColors(v1, v);
                        arr2_v[3] = ThemeUtils.EMPTY_STATE_SET;
                        arr_v[3] = v;
                        return new ColorStateList(arr2_v, arr_v);
                    }

                    private ColorStateList createColoredButtonColorStateList(Context context0) {
                        return this.createButtonColorStateList(context0, ThemeUtils.getThemeAttrColor(context0, attr.colorAccent));
                    }

                    private ColorStateList createDefaultButtonColorStateList(Context context0) {
                        return this.createButtonColorStateList(context0, ThemeUtils.getThemeAttrColor(context0, attr.colorButtonNormal));
                    }

                    @Override  // androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks
                    public Drawable createDrawableFor(ResourceManagerInternal resourceManagerInternal0, Context context0, int v) {
                        if(v == drawable.abc_cab_background_top_material) {
                            return new LayerDrawable(new Drawable[]{resourceManagerInternal0.getDrawable(context0, drawable.abc_cab_background_internal_bg), resourceManagerInternal0.getDrawable(context0, drawable.abc_cab_background_top_mtrl_alpha)});
                        }
                        if(v == drawable.abc_ratingbar_material) {
                            return this.getRatingBarLayerDrawable(resourceManagerInternal0, context0, dimen.abc_star_big);
                        }
                        if(v == drawable.abc_ratingbar_indicator_material) {
                            return this.getRatingBarLayerDrawable(resourceManagerInternal0, context0, dimen.abc_star_medium);
                        }
                        return v == drawable.abc_ratingbar_small_material ? this.getRatingBarLayerDrawable(resourceManagerInternal0, context0, dimen.abc_star_small) : null;
                    }

                    private ColorStateList createSwitchThumbColorStateList(Context context0) {
                        int[][] arr2_v = new int[3][];
                        int[] arr_v = new int[3];
                        ColorStateList colorStateList0 = ThemeUtils.getThemeAttrColorStateList(context0, attr.colorSwitchThumbNormal);
                        if(colorStateList0 != null && colorStateList0.isStateful()) {
                            arr2_v[0] = ThemeUtils.DISABLED_STATE_SET;
                            arr_v[0] = colorStateList0.getColorForState(ThemeUtils.DISABLED_STATE_SET, 0);
                            arr2_v[1] = ThemeUtils.CHECKED_STATE_SET;
                            arr_v[1] = ThemeUtils.getThemeAttrColor(context0, attr.colorControlActivated);
                            arr2_v[2] = ThemeUtils.EMPTY_STATE_SET;
                            arr_v[2] = colorStateList0.getDefaultColor();
                            return new ColorStateList(arr2_v, arr_v);
                        }
                        arr2_v[0] = ThemeUtils.DISABLED_STATE_SET;
                        arr_v[0] = ThemeUtils.getDisabledThemeAttrColor(context0, attr.colorSwitchThumbNormal);
                        arr2_v[1] = ThemeUtils.CHECKED_STATE_SET;
                        arr_v[1] = ThemeUtils.getThemeAttrColor(context0, attr.colorControlActivated);
                        arr2_v[2] = ThemeUtils.EMPTY_STATE_SET;
                        arr_v[2] = ThemeUtils.getThemeAttrColor(context0, attr.colorSwitchThumbNormal);
                        return new ColorStateList(arr2_v, arr_v);
                    }

                    private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal resourceManagerInternal0, Context context0, int v) {
                        BitmapDrawable bitmapDrawable2;
                        BitmapDrawable bitmapDrawable1;
                        BitmapDrawable bitmapDrawable0;
                        int v1 = context0.getResources().getDimensionPixelSize(v);
                        Drawable drawable0 = resourceManagerInternal0.getDrawable(context0, drawable.abc_star_black_48dp);
                        Drawable drawable1 = resourceManagerInternal0.getDrawable(context0, drawable.abc_star_half_black_48dp);
                        if(!(drawable0 instanceof BitmapDrawable) || drawable0.getIntrinsicWidth() != v1 || drawable0.getIntrinsicHeight() != v1) {
                            Bitmap bitmap0 = Bitmap.createBitmap(v1, v1, Bitmap.Config.ARGB_8888);
                            Canvas canvas0 = new Canvas(bitmap0);
                            drawable0.setBounds(0, 0, v1, v1);
                            drawable0.draw(canvas0);
                            bitmapDrawable0 = new BitmapDrawable(bitmap0);
                            bitmapDrawable1 = new BitmapDrawable(bitmap0);
                        }
                        else {
                            bitmapDrawable0 = (BitmapDrawable)drawable0;
                            bitmapDrawable1 = new BitmapDrawable(bitmapDrawable0.getBitmap());
                        }
                        bitmapDrawable1.setTileModeX(Shader.TileMode.REPEAT);
                        if(!(drawable1 instanceof BitmapDrawable) || drawable1.getIntrinsicWidth() != v1 || drawable1.getIntrinsicHeight() != v1) {
                            Bitmap bitmap1 = Bitmap.createBitmap(v1, v1, Bitmap.Config.ARGB_8888);
                            Canvas canvas1 = new Canvas(bitmap1);
                            drawable1.setBounds(0, 0, v1, v1);
                            drawable1.draw(canvas1);
                            bitmapDrawable2 = new BitmapDrawable(bitmap1);
                        }
                        else {
                            bitmapDrawable2 = (BitmapDrawable)drawable1;
                        }
                        LayerDrawable layerDrawable0 = new LayerDrawable(new Drawable[]{bitmapDrawable0, bitmapDrawable2, bitmapDrawable1});
                        layerDrawable0.setId(0, 0x1020000);
                        layerDrawable0.setId(1, 0x102000F);
                        layerDrawable0.setId(2, 0x102000D);
                        return layerDrawable0;
                    }

                    @Override  // androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks
                    public ColorStateList getTintListForDrawableRes(Context context0, int v) {
                        if(v == drawable.abc_edit_text_material) {
                            return AppCompatResources.getColorStateList(context0, color.abc_tint_edittext);
                        }
                        if(v == drawable.abc_switch_track_mtrl_alpha) {
                            return AppCompatResources.getColorStateList(context0, color.abc_tint_switch_track);
                        }
                        if(v == drawable.abc_switch_thumb_material) {
                            return this.createSwitchThumbColorStateList(context0);
                        }
                        if(v == drawable.abc_btn_default_mtrl_shape) {
                            return this.createDefaultButtonColorStateList(context0);
                        }
                        if(v == drawable.abc_btn_borderless_material) {
                            return this.createBorderlessButtonColorStateList(context0);
                        }
                        if(v == drawable.abc_btn_colored_material) {
                            return this.createColoredButtonColorStateList(context0);
                        }
                        if(v != drawable.abc_spinner_mtrl_am_alpha && v != drawable.abc_spinner_textfield_background_material) {
                            if(this.arrayContains(this.TINT_COLOR_CONTROL_NORMAL, v)) {
                                return ThemeUtils.getThemeAttrColorStateList(context0, attr.colorControlNormal);
                            }
                            if(this.arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, v)) {
                                return AppCompatResources.getColorStateList(context0, color.abc_tint_default);
                            }
                            if(this.arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, v)) {
                                return AppCompatResources.getColorStateList(context0, color.abc_tint_btn_checkable);
                            }
                            return v == drawable.abc_seekbar_thumb_material ? AppCompatResources.getColorStateList(context0, color.abc_tint_seek_thumb) : null;
                        }
                        return AppCompatResources.getColorStateList(context0, color.abc_tint_spinner);
                    }

                    @Override  // androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks
                    public PorterDuff.Mode getTintModeForDrawableRes(int v) {
                        return v == drawable.abc_switch_thumb_material ? PorterDuff.Mode.MULTIPLY : null;
                    }

                    private void setPorterDuffColorFilter(Drawable drawable0, int v, PorterDuff.Mode porterDuff$Mode0) {
                        Drawable drawable1 = drawable0.mutate();
                        if(porterDuff$Mode0 == null) {
                            porterDuff$Mode0 = AppCompatDrawableManager.DEFAULT_MODE;
                        }
                        drawable1.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(v, porterDuff$Mode0));
                    }

                    @Override  // androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks
                    public boolean tintDrawable(Context context0, int v, Drawable drawable0) {
                        if(v == drawable.abc_seekbar_track_material) {
                            this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x1020000), ThemeUtils.getThemeAttrColor(context0, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                            this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x102000F), ThemeUtils.getThemeAttrColor(context0, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                            this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x102000D), ThemeUtils.getThemeAttrColor(context0, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                            return true;
                        }
                        if(v != drawable.abc_ratingbar_material && v != drawable.abc_ratingbar_indicator_material && v != drawable.abc_ratingbar_small_material) {
                            return false;
                        }
                        this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x1020000), ThemeUtils.getDisabledThemeAttrColor(context0, attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                        this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x102000F), ThemeUtils.getThemeAttrColor(context0, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                        this.setPorterDuffColorFilter(((LayerDrawable)drawable0).findDrawableByLayerId(0x102000D), ThemeUtils.getThemeAttrColor(context0, attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                        return true;
                    }

                    @Override  // androidx.appcompat.widget.ResourceManagerInternal$ResourceManagerHooks
                    public boolean tintDrawableUsingColorFilter(Context context0, int v, Drawable drawable0) {
                        boolean z;
                        int v2;
                        PorterDuff.Mode porterDuff$Mode1;
                        int v1;
                        PorterDuff.Mode porterDuff$Mode0 = AppCompatDrawableManager.DEFAULT_MODE;
                        if(this.arrayContains(this.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, v)) {
                            v1 = attr.colorControlNormal;
                            porterDuff$Mode1 = porterDuff$Mode0;
                            v2 = -1;
                            z = true;
                        }
                        else if(this.arrayContains(this.COLORFILTER_COLOR_CONTROL_ACTIVATED, v)) {
                            v1 = attr.colorControlActivated;
                            porterDuff$Mode1 = porterDuff$Mode0;
                            v2 = -1;
                            z = true;
                        }
                        else if(this.arrayContains(this.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, v)) {
                            porterDuff$Mode1 = PorterDuff.Mode.MULTIPLY;
                            v1 = 0x1010031;
                            v2 = -1;
                            z = true;
                        }
                        else if(v == drawable.abc_list_divider_mtrl_alpha) {
                            porterDuff$Mode1 = porterDuff$Mode0;
                            z = true;
                            v2 = 41;
                            v1 = 0x1010030;
                        }
                        else if(v == drawable.abc_dialog_material_background) {
                            porterDuff$Mode1 = porterDuff$Mode0;
                            v1 = 0x1010031;
                            v2 = -1;
                            z = true;
                        }
                        else {
                            porterDuff$Mode1 = porterDuff$Mode0;
                            v1 = 0;
                            v2 = -1;
                            z = false;
                        }
                        if(z) {
                            Drawable drawable1 = drawable0.mutate();
                            drawable1.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context0, v1), porterDuff$Mode1));
                            if(v2 != -1) {
                                drawable1.setAlpha(v2);
                            }
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
    }

    static void tintDrawable(Drawable drawable0, TintInfo tintInfo0, int[] arr_v) {
        ResourceManagerInternal.tintDrawable(drawable0, tintInfo0, arr_v);
    }

    boolean tintDrawableUsingColorFilter(Context context0, int v, Drawable drawable0) {
        return this.mResourceManager.tintDrawableUsingColorFilter(context0, v, drawable0);
    }
}

