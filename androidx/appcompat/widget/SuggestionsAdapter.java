package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.ResourceCursorAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    static final class ChildViewCache {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View view0) {
            this.mText1 = (TextView)view0.findViewById(0x1020014);
            this.mText2 = (TextView)view0.findViewById(0x1020015);
            this.mIcon1 = (ImageView)view0.findViewById(0x1020007);
            this.mIcon2 = (ImageView)view0.findViewById(0x1020008);
            this.mIconRefine = (ImageView)view0.findViewById(id.edit_query);
        }
    }

    private static final boolean DBG = false;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE;
    private boolean mClosed;
    private final int mCommitIconResId;
    private int mFlagsCol;
    private int mIconName1Col;
    private int mIconName2Col;
    private final WeakHashMap mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement;
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col;
    private int mText2Col;
    private int mText2UrlCol;
    private ColorStateList mUrlColor;

    public SuggestionsAdapter(Context context0, SearchView searchView0, SearchableInfo searchableInfo0, WeakHashMap weakHashMap0) {
        super(context0, searchView0.getSuggestionRowLayout(), null, true);
        this.mClosed = false;
        this.mQueryRefinement = 1;
        this.mText1Col = -1;
        this.mText2Col = -1;
        this.mText2UrlCol = -1;
        this.mIconName1Col = -1;
        this.mIconName2Col = -1;
        this.mFlagsCol = -1;
        this.mSearchView = searchView0;
        this.mSearchable = searchableInfo0;
        this.mCommitIconResId = searchView0.getSuggestionCommitIconResId();
        this.mProviderContext = context0;
        this.mOutsideDrawablesCache = weakHashMap0;
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public void bindView(View view0, Context context0, Cursor cursor0) {
        ChildViewCache suggestionsAdapter$ChildViewCache0 = (ChildViewCache)view0.getTag();
        int v = this.mFlagsCol == -1 ? 0 : cursor0.getInt(this.mFlagsCol);
        if(suggestionsAdapter$ChildViewCache0.mText1 != null) {
            String s = SuggestionsAdapter.getStringOrNull(cursor0, this.mText1Col);
            this.setViewText(suggestionsAdapter$ChildViewCache0.mText1, s);
        }
        if(suggestionsAdapter$ChildViewCache0.mText2 != null) {
            String s1 = SuggestionsAdapter.getStringOrNull(cursor0, this.mText2UrlCol);
            CharSequence charSequence0 = s1 == null ? SuggestionsAdapter.getStringOrNull(cursor0, this.mText2Col) : this.formatUrl(s1);
            if(!TextUtils.isEmpty(charSequence0)) {
                if(suggestionsAdapter$ChildViewCache0.mText1 != null) {
                    suggestionsAdapter$ChildViewCache0.mText1.setSingleLine(true);
                    suggestionsAdapter$ChildViewCache0.mText1.setMaxLines(1);
                }
            }
            else if(suggestionsAdapter$ChildViewCache0.mText1 != null) {
                suggestionsAdapter$ChildViewCache0.mText1.setSingleLine(false);
                suggestionsAdapter$ChildViewCache0.mText1.setMaxLines(2);
            }
            this.setViewText(suggestionsAdapter$ChildViewCache0.mText2, charSequence0);
        }
        if(suggestionsAdapter$ChildViewCache0.mIcon1 != null) {
            Drawable drawable0 = this.getIcon1(cursor0);
            this.setViewDrawable(suggestionsAdapter$ChildViewCache0.mIcon1, drawable0, 4);
        }
        if(suggestionsAdapter$ChildViewCache0.mIcon2 != null) {
            Drawable drawable1 = this.getIcon2(cursor0);
            this.setViewDrawable(suggestionsAdapter$ChildViewCache0.mIcon2, drawable1, 8);
        }
        if(this.mQueryRefinement != 2 && (this.mQueryRefinement != 1 || (v & 1) == 0)) {
            suggestionsAdapter$ChildViewCache0.mIconRefine.setVisibility(8);
            return;
        }
        suggestionsAdapter$ChildViewCache0.mIconRefine.setVisibility(0);
        CharSequence charSequence1 = suggestionsAdapter$ChildViewCache0.mText1.getText();
        suggestionsAdapter$ChildViewCache0.mIconRefine.setTag(charSequence1);
        suggestionsAdapter$ChildViewCache0.mIconRefine.setOnClickListener(this);
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public void changeCursor(Cursor cursor0) {
        if(this.mClosed) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if(cursor0 != null) {
                cursor0.close();
            }
        }
        else {
            try {
                super.changeCursor(cursor0);
                if(cursor0 != null) {
                    this.mText1Col = cursor0.getColumnIndex("suggest_text_1");
                    this.mText2Col = cursor0.getColumnIndex("suggest_text_2");
                    this.mText2UrlCol = cursor0.getColumnIndex("suggest_text_2_url");
                    this.mIconName1Col = cursor0.getColumnIndex("suggest_icon_1");
                    this.mIconName2Col = cursor0.getColumnIndex("suggest_icon_2");
                    this.mFlagsCol = cursor0.getColumnIndex("suggest_flags");
                }
            }
            catch(Exception exception0) {
                Log.e("SuggestionsAdapter", "error changing cursor and caching columns", exception0);
            }
        }
    }

    private Drawable checkIconCache(String s) {
        Drawable.ConstantState drawable$ConstantState0 = (Drawable.ConstantState)this.mOutsideDrawablesCache.get(s);
        return drawable$ConstantState0 == null ? null : drawable$ConstantState0.newDrawable();
    }

    public void close() {
        this.changeCursor(null);
        this.mClosed = true;
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public CharSequence convertToString(Cursor cursor0) {
        if(cursor0 == null) {
            return null;
        }
        CharSequence charSequence0 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_query");
        if(charSequence0 != null) {
            return charSequence0;
        }
        if(this.mSearchable.shouldRewriteQueryFromData()) {
            CharSequence charSequence1 = SuggestionsAdapter.getColumnString(cursor0, "suggest_intent_data");
            if(charSequence1 != null) {
                return charSequence1;
            }
        }
        if(this.mSearchable.shouldRewriteQueryFromText()) {
            CharSequence charSequence2 = SuggestionsAdapter.getColumnString(cursor0, "suggest_text_1");
            return charSequence2 == null ? null : charSequence2;
        }
        return null;
    }

    private CharSequence formatUrl(CharSequence charSequence0) {
        if(this.mUrlColor == null) {
            TypedValue typedValue0 = new TypedValue();
            this.mProviderContext.getTheme().resolveAttribute(attr.textColorSearchUrl, typedValue0, true);
            this.mUrlColor = this.mProviderContext.getResources().getColorStateList(typedValue0.resourceId);
        }
        CharSequence charSequence1 = new SpannableString(charSequence0);
        ((SpannableString)charSequence1).setSpan(new TextAppearanceSpan(null, 0, 0, this.mUrlColor, null), 0, charSequence0.length(), 33);
        return charSequence1;
    }

    private Drawable getActivityIcon(ComponentName componentName0) {
        ActivityInfo activityInfo0;
        PackageManager packageManager0 = this.mProviderContext.getPackageManager();
        try {
            activityInfo0 = packageManager0.getActivityInfo(componentName0, 0x80);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.w("SuggestionsAdapter", packageManager$NameNotFoundException0.toString());
            return null;
        }
        int v = activityInfo0.getIconResource();
        if(v == 0) {
            return null;
        }
        Drawable drawable0 = packageManager0.getDrawable(componentName0.getPackageName(), v, activityInfo0.applicationInfo);
        if(drawable0 == null) {
            Log.w("SuggestionsAdapter", "Invalid icon resource " + v + " for " + componentName0.flattenToShortString());
            return null;
        }
        return drawable0;
    }

    private Drawable getActivityIconWithCache(ComponentName componentName0) {
        String s = componentName0.flattenToShortString();
        Drawable.ConstantState drawable$ConstantState0 = null;
        if(this.mOutsideDrawablesCache.containsKey(s)) {
            Drawable.ConstantState drawable$ConstantState1 = (Drawable.ConstantState)this.mOutsideDrawablesCache.get(s);
            return drawable$ConstantState1 == null ? null : drawable$ConstantState1.newDrawable(this.mProviderContext.getResources());
        }
        Drawable drawable0 = this.getActivityIcon(componentName0);
        if(drawable0 != null) {
            drawable$ConstantState0 = drawable0.getConstantState();
        }
        this.mOutsideDrawablesCache.put(s, drawable$ConstantState0);
        return drawable0;
    }

    public static String getColumnString(Cursor cursor0, String s) {
        return SuggestionsAdapter.getStringOrNull(cursor0, cursor0.getColumnIndex(s));
    }

    private Drawable getDefaultIcon1() {
        Drawable drawable0 = this.getActivityIconWithCache(this.mSearchable.getSearchActivity());
        return drawable0 == null ? this.mProviderContext.getPackageManager().getDefaultActivityIcon() : drawable0;
    }

    private Drawable getDrawable(Uri uri0) {
        try {
            if(!"android.resource".equals(uri0.getScheme())) {
                InputStream inputStream0 = this.mProviderContext.getContentResolver().openInputStream(uri0);
                if(inputStream0 == null) {
                    throw new FileNotFoundException("Failed to open " + uri0);
                }
                try {
                    return Drawable.createFromStream(inputStream0, null);
                }
                finally {
                    try {
                        inputStream0.close();
                    }
                    catch(IOException iOException0) {
                        Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri0, iOException0);
                    }
                }
            }
            try {
                return this.getDrawableFromResourceUri(uri0);
            }
            catch(Resources.NotFoundException unused_ex) {
                throw new FileNotFoundException("Resource does not exist: " + uri0);
            }
        }
        catch(FileNotFoundException fileNotFoundException0) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri0 + ", " + fileNotFoundException0.getMessage());
            return null;
        }
    }

    Drawable getDrawableFromResourceUri(Uri uri0) throws FileNotFoundException {
        int v1;
        Resources resources0;
        String s = uri0.getAuthority();
        if(TextUtils.isEmpty(s)) {
            throw new FileNotFoundException("No authority: " + uri0);
        }
        try {
            resources0 = this.mProviderContext.getPackageManager().getResourcesForApplication(s);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            throw new FileNotFoundException("No package found for authority: " + uri0);
        }
        List list0 = uri0.getPathSegments();
        if(list0 == null) {
            throw new FileNotFoundException("No path: " + uri0);
        }
        int v = list0.size();
        if(v == 1) {
            try {
                v1 = Integer.parseInt(((String)list0.get(0)));
            }
            catch(NumberFormatException unused_ex) {
                throw new FileNotFoundException("Single path segment is not a resource ID: " + uri0);
            }
        }
        else {
            if(v != 2) {
                throw new FileNotFoundException("More than two path segments: " + uri0);
            }
            v1 = resources0.getIdentifier(((String)list0.get(1)), ((String)list0.get(0)), s);
        }
        if(v1 == 0) {
            throw new FileNotFoundException("No resource found for: " + uri0);
        }
        return resources0.getDrawable(v1);
    }

    private Drawable getDrawableFromResourceValue(String s) {
        if(s != null && !s.isEmpty() && !"0".equals(s)) {
            try {
                int v = Integer.parseInt(s);
                Drawable drawable0 = this.checkIconCache("android.resource://com.ruliweb.www.ruliapp/" + v);
                if(drawable0 != null) {
                    return drawable0;
                }
                Drawable drawable1 = ContextCompat.getDrawable(this.mProviderContext, v);
                this.storeInIconCache("android.resource://com.ruliweb.www.ruliapp/" + v, drawable1);
                return drawable1;
            }
            catch(NumberFormatException unused_ex) {
                Drawable drawable2 = this.checkIconCache(s);
                if(drawable2 != null) {
                    return drawable2;
                }
                Drawable drawable3 = this.getDrawable(Uri.parse(s));
                this.storeInIconCache(s, drawable3);
                return drawable3;
            }
            catch(Resources.NotFoundException unused_ex) {
                Log.w("SuggestionsAdapter", "Icon resource not found: " + s);
                return null;
            }
        }
        return null;
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public View getDropDownView(int v, View view0, ViewGroup viewGroup0) {
        try {
            return super.getDropDownView(v, view0, viewGroup0);
        }
        catch(RuntimeException runtimeException0) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", runtimeException0);
            Cursor cursor0 = this.getCursor();
            View view1 = this.newDropDownView(this.mProviderContext, cursor0, viewGroup0);
            if(view1 != null) {
                ((ChildViewCache)view1.getTag()).mText1.setText(runtimeException0.toString());
            }
            return view1;
        }
    }

    private Drawable getIcon1(Cursor cursor0) {
        int v = this.mIconName1Col;
        if(v == -1) {
            return null;
        }
        Drawable drawable0 = this.getDrawableFromResourceValue(cursor0.getString(v));
        return drawable0 == null ? this.getDefaultIcon1() : drawable0;
    }

    private Drawable getIcon2(Cursor cursor0) {
        return this.mIconName2Col == -1 ? null : this.getDrawableFromResourceValue(cursor0.getString(this.mIconName2Col));
    }

    public int getQueryRefinement() {
        return this.mQueryRefinement;
    }

    Cursor getSearchManagerSuggestions(SearchableInfo searchableInfo0, String s, int v) {
        String[] arr_s = null;
        if(searchableInfo0 == null) {
            return null;
        }
        String s1 = searchableInfo0.getSuggestAuthority();
        if(s1 == null) {
            return null;
        }
        Uri.Builder uri$Builder0 = new Uri.Builder().scheme("content").authority(s1).query("").fragment("");
        String s2 = searchableInfo0.getSuggestPath();
        if(s2 != null) {
            uri$Builder0.appendEncodedPath(s2);
        }
        uri$Builder0.appendPath("search_suggest_query");
        String s3 = searchableInfo0.getSuggestSelection();
        if(s3 == null) {
            uri$Builder0.appendPath(s);
        }
        else {
            arr_s = new String[]{s};
        }
        if(v > 0) {
            uri$Builder0.appendQueryParameter("limit", String.valueOf(v));
        }
        Uri uri0 = uri$Builder0.build();
        return this.mProviderContext.getContentResolver().query(uri0, null, s3, arr_s, null);
    }

    private static String getStringOrNull(Cursor cursor0, int v) {
        if(v == -1) {
            return null;
        }
        try {
            return cursor0.getString(v);
        }
        catch(Exception exception0) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", exception0);
            return null;
        }
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public View getView(int v, View view0, ViewGroup viewGroup0) {
        try {
            return super.getView(v, view0, viewGroup0);
        }
        catch(RuntimeException runtimeException0) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", runtimeException0);
            Cursor cursor0 = this.getCursor();
            View view1 = this.newView(this.mProviderContext, cursor0, viewGroup0);
            if(view1 != null) {
                ((ChildViewCache)view1.getTag()).mText1.setText(runtimeException0.toString());
            }
            return view1;
        }
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override  // androidx.cursoradapter.widget.ResourceCursorAdapter
    public View newView(Context context0, Cursor cursor0, ViewGroup viewGroup0) {
        View view0 = super.newView(context0, cursor0, viewGroup0);
        view0.setTag(new ChildViewCache(view0));
        ((ImageView)view0.findViewById(id.edit_query)).setImageResource(this.mCommitIconResId);
        return view0;
    }

    @Override  // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.updateSpinnerState(this.getCursor());
    }

    @Override  // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        this.updateSpinnerState(this.getCursor());
    }

    @Override  // android.view.View$OnClickListener
    public void onClick(View view0) {
        Object object0 = view0.getTag();
        if(object0 instanceof CharSequence) {
            this.mSearchView.onQueryRefine(((CharSequence)object0));
        }
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public Cursor runQueryOnBackgroundThread(CharSequence charSequence0) {
        String s = charSequence0 == null ? "" : charSequence0.toString();
        if(this.mSearchView.getVisibility() == 0 && this.mSearchView.getWindowVisibility() == 0) {
            try {
                Cursor cursor0 = this.getSearchManagerSuggestions(this.mSearchable, s, 50);
                if(cursor0 != null) {
                    cursor0.getCount();
                    return cursor0;
                }
            }
            catch(RuntimeException runtimeException0) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", runtimeException0);
            }
        }
        return null;
    }

    public void setQueryRefinement(int v) {
        this.mQueryRefinement = v;
    }

    private void setViewDrawable(ImageView imageView0, Drawable drawable0, int v) {
        imageView0.setImageDrawable(drawable0);
        if(drawable0 == null) {
            imageView0.setVisibility(v);
            return;
        }
        imageView0.setVisibility(0);
        drawable0.setVisible(false, false);
        drawable0.setVisible(true, false);
    }

    private void setViewText(TextView textView0, CharSequence charSequence0) {
        textView0.setText(charSequence0);
        if(TextUtils.isEmpty(charSequence0)) {
            textView0.setVisibility(8);
            return;
        }
        textView0.setVisibility(0);
    }

    private void storeInIconCache(String s, Drawable drawable0) {
        if(drawable0 != null) {
            Drawable.ConstantState drawable$ConstantState0 = drawable0.getConstantState();
            this.mOutsideDrawablesCache.put(s, drawable$ConstantState0);
        }
    }

    private void updateSpinnerState(Cursor cursor0) {
        Bundle bundle0 = cursor0 == null ? null : cursor0.getExtras();
        if(bundle0 != null) {
            bundle0.getBoolean("in_progress");
        }
    }
}

