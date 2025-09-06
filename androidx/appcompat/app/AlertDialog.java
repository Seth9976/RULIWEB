package androidx.appcompat.app;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.R.attr;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    public static class Builder {
        private final AlertParams P;
        private final int mTheme;

        public Builder(Context context0) {
            this(context0, AlertDialog.resolveDialogTheme(context0, 0));
        }

        public Builder(Context context0, int v) {
            this.P = new AlertParams(new ContextThemeWrapper(context0, AlertDialog.resolveDialogTheme(context0, v)));
            this.mTheme = v;
        }

        public AlertDialog create() {
            AlertDialog alertDialog0 = new AlertDialog(this.P.mContext, this.mTheme);
            this.P.apply(alertDialog0.mAlert);
            alertDialog0.setCancelable(this.P.mCancelable);
            if(this.P.mCancelable) {
                alertDialog0.setCanceledOnTouchOutside(true);
            }
            alertDialog0.setOnCancelListener(this.P.mOnCancelListener);
            alertDialog0.setOnDismissListener(this.P.mOnDismissListener);
            if(this.P.mOnKeyListener != null) {
                alertDialog0.setOnKeyListener(this.P.mOnKeyListener);
            }
            return alertDialog0;
        }

        public Context getContext() {
            return this.P.mContext;
        }

        public Builder setAdapter(ListAdapter listAdapter0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mAdapter = listAdapter0;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.P.mCancelable = z;
            return this;
        }

        public Builder setCursor(Cursor cursor0, DialogInterface.OnClickListener dialogInterface$OnClickListener0, String s) {
            this.P.mCursor = cursor0;
            this.P.mLabelColumn = s;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setCustomTitle(View view0) {
            this.P.mCustomTitleView = view0;
            return this;
        }

        public Builder setIcon(int v) {
            this.P.mIconId = v;
            return this;
        }

        public Builder setIcon(Drawable drawable0) {
            this.P.mIcon = drawable0;
            return this;
        }

        public Builder setIconAttribute(int v) {
            TypedValue typedValue0 = new TypedValue();
            this.P.mContext.getTheme().resolveAttribute(v, typedValue0, true);
            this.P.mIconId = typedValue0.resourceId;
            return this;
        }

        @Deprecated
        public Builder setInverseBackgroundForced(boolean z) {
            this.P.mForceInverseBackground = z;
            return this;
        }

        public Builder setItems(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(v);
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setItems(CharSequence[] arr_charSequence, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mItems = arr_charSequence;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setMessage(int v) {
            this.P.mMessage = this.P.mContext.getText(v);
            return this;
        }

        public Builder setMessage(CharSequence charSequence0) {
            this.P.mMessage = charSequence0;
            return this;
        }

        public Builder setMultiChoiceItems(int v, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(v);
            this.P.mOnCheckboxClickListener = dialogInterface$OnMultiChoiceClickListener0;
            this.P.mCheckedItems = arr_z;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor0, String s, String s1, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
            this.P.mCursor = cursor0;
            this.P.mOnCheckboxClickListener = dialogInterface$OnMultiChoiceClickListener0;
            this.P.mIsCheckedColumn = s;
            this.P.mLabelColumn = s1;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] arr_charSequence, boolean[] arr_z, DialogInterface.OnMultiChoiceClickListener dialogInterface$OnMultiChoiceClickListener0) {
            this.P.mItems = arr_charSequence;
            this.P.mOnCheckboxClickListener = dialogInterface$OnMultiChoiceClickListener0;
            this.P.mCheckedItems = arr_z;
            this.P.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mNegativeButtonText = this.P.mContext.getText(v);
            this.P.mNegativeButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mNegativeButtonText = charSequence0;
            this.P.mNegativeButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setNegativeButtonIcon(Drawable drawable0) {
            this.P.mNegativeButtonIcon = drawable0;
            return this;
        }

        public Builder setNeutralButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mNeutralButtonText = this.P.mContext.getText(v);
            this.P.mNeutralButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mNeutralButtonText = charSequence0;
            this.P.mNeutralButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setNeutralButtonIcon(Drawable drawable0) {
            this.P.mNeutralButtonIcon = drawable0;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
            this.P.mOnCancelListener = dialogInterface$OnCancelListener0;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener dialogInterface$OnDismissListener0) {
            this.P.mOnDismissListener = dialogInterface$OnDismissListener0;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
            this.P.mOnItemSelectedListener = adapterView$OnItemSelectedListener0;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener dialogInterface$OnKeyListener0) {
            this.P.mOnKeyListener = dialogInterface$OnKeyListener0;
            return this;
        }

        public Builder setPositiveButton(int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mPositiveButtonText = this.P.mContext.getText(v);
            this.P.mPositiveButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mPositiveButtonText = charSequence0;
            this.P.mPositiveButtonListener = dialogInterface$OnClickListener0;
            return this;
        }

        public Builder setPositiveButtonIcon(Drawable drawable0) {
            this.P.mPositiveButtonIcon = drawable0;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.P.mRecycleOnMeasure = z;
            return this;
        }

        public Builder setSingleChoiceItems(int v, int v1, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mItems = this.P.mContext.getResources().getTextArray(v);
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            this.P.mCheckedItem = v1;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor0, int v, String s, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mCursor = cursor0;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            this.P.mCheckedItem = v;
            this.P.mLabelColumn = s;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter0, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mAdapter = listAdapter0;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            this.P.mCheckedItem = v;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] arr_charSequence, int v, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
            this.P.mItems = arr_charSequence;
            this.P.mOnClickListener = dialogInterface$OnClickListener0;
            this.P.mCheckedItem = v;
            this.P.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(int v) {
            this.P.mTitle = this.P.mContext.getText(v);
            return this;
        }

        public Builder setTitle(CharSequence charSequence0) {
            this.P.mTitle = charSequence0;
            return this;
        }

        public Builder setView(int v) {
            this.P.mView = null;
            this.P.mViewLayoutResId = v;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view0) {
            this.P.mView = view0;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = false;
            return this;
        }

        @Deprecated
        public Builder setView(View view0, int v, int v1, int v2, int v3) {
            this.P.mView = view0;
            this.P.mViewLayoutResId = 0;
            this.P.mViewSpacingSpecified = true;
            this.P.mViewSpacingLeft = v;
            this.P.mViewSpacingTop = v1;
            this.P.mViewSpacingRight = v2;
            this.P.mViewSpacingBottom = v3;
            return this;
        }

        public AlertDialog show() {
            AlertDialog alertDialog0 = this.create();
            alertDialog0.show();
            return alertDialog0;
        }
    }

    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    final AlertController mAlert;

    protected AlertDialog(Context context0) {
        this(context0, 0);
    }

    protected AlertDialog(Context context0, int v) {
        super(context0, AlertDialog.resolveDialogTheme(context0, v));
        this.mAlert = new AlertController(this.getContext(), this, this.getWindow());
    }

    protected AlertDialog(Context context0, boolean z, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        this(context0, 0);
        this.setCancelable(z);
        this.setOnCancelListener(dialogInterface$OnCancelListener0);
    }

    public Button getButton(int v) {
        return this.mAlert.getButton(v);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.mAlert.installContent();
    }

    // 去混淆评级： 低(20)
    @Override  // android.app.Dialog
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        return this.mAlert.onKeyDown(v, keyEvent0) ? true : super.onKeyDown(v, keyEvent0);
    }

    // 去混淆评级： 低(20)
    @Override  // android.app.Dialog
    public boolean onKeyUp(int v, KeyEvent keyEvent0) {
        return this.mAlert.onKeyUp(v, keyEvent0) ? true : super.onKeyUp(v, keyEvent0);
    }

    static int resolveDialogTheme(Context context0, int v) {
        if((v >>> 24 & 0xFF) >= 1) {
            return v;
        }
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(attr.alertDialogTheme, typedValue0, true);
        return typedValue0.resourceId;
    }

    public void setButton(int v, CharSequence charSequence0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        this.mAlert.setButton(v, charSequence0, dialogInterface$OnClickListener0, null, null);
    }

    public void setButton(int v, CharSequence charSequence0, Drawable drawable0, DialogInterface.OnClickListener dialogInterface$OnClickListener0) {
        this.mAlert.setButton(v, charSequence0, dialogInterface$OnClickListener0, null, drawable0);
    }

    public void setButton(int v, CharSequence charSequence0, Message message0) {
        this.mAlert.setButton(v, charSequence0, null, message0, null);
    }

    void setButtonPanelLayoutHint(int v) {
        this.mAlert.setButtonPanelLayoutHint(v);
    }

    public void setCustomTitle(View view0) {
        this.mAlert.setCustomTitle(view0);
    }

    public void setIcon(int v) {
        this.mAlert.setIcon(v);
    }

    public void setIcon(Drawable drawable0) {
        this.mAlert.setIcon(drawable0);
    }

    public void setIconAttribute(int v) {
        TypedValue typedValue0 = new TypedValue();
        this.getContext().getTheme().resolveAttribute(v, typedValue0, true);
        this.mAlert.setIcon(typedValue0.resourceId);
    }

    public void setMessage(CharSequence charSequence0) {
        this.mAlert.setMessage(charSequence0);
    }

    @Override  // androidx.appcompat.app.AppCompatDialog
    public void setTitle(CharSequence charSequence0) {
        super.setTitle(charSequence0);
        this.mAlert.setTitle(charSequence0);
    }

    public void setView(View view0) {
        this.mAlert.setView(view0);
    }

    public void setView(View view0, int v, int v1, int v2, int v3) {
        this.mAlert.setView(view0, v, v1, v2, v3);
    }
}

