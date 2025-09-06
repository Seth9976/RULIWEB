package androidx.appcompat.widget;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.appcompat.R.attr;

public final class Toolbar.InspectionCompanion implements InspectionCompanion {
    private int mCollapseContentDescriptionId;
    private int mCollapseIconId;
    private int mContentInsetEndId;
    private int mContentInsetEndWithActionsId;
    private int mContentInsetLeftId;
    private int mContentInsetRightId;
    private int mContentInsetStartId;
    private int mContentInsetStartWithNavigationId;
    private int mLogoDescriptionId;
    private int mLogoId;
    private int mMenuId;
    private int mNavigationContentDescriptionId;
    private int mNavigationIconId;
    private int mPopupThemeId;
    private boolean mPropertiesMapped;
    private int mSubtitleId;
    private int mTitleId;
    private int mTitleMarginBottomId;
    private int mTitleMarginEndId;
    private int mTitleMarginStartId;
    private int mTitleMarginTopId;

    public Toolbar.InspectionCompanion() {
        this.mPropertiesMapped = false;
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void mapProperties(PropertyMapper propertyMapper0) {
        this.mCollapseContentDescriptionId = propertyMapper0.mapObject("collapseContentDescription", attr.collapseContentDescription);
        this.mCollapseIconId = propertyMapper0.mapObject("collapseIcon", attr.collapseIcon);
        this.mContentInsetEndId = propertyMapper0.mapInt("contentInsetEnd", attr.contentInsetEnd);
        this.mContentInsetEndWithActionsId = propertyMapper0.mapInt("contentInsetEndWithActions", attr.contentInsetEndWithActions);
        this.mContentInsetLeftId = propertyMapper0.mapInt("contentInsetLeft", attr.contentInsetLeft);
        this.mContentInsetRightId = propertyMapper0.mapInt("contentInsetRight", attr.contentInsetRight);
        this.mContentInsetStartId = propertyMapper0.mapInt("contentInsetStart", attr.contentInsetStart);
        this.mContentInsetStartWithNavigationId = propertyMapper0.mapInt("contentInsetStartWithNavigation", attr.contentInsetStartWithNavigation);
        this.mLogoId = propertyMapper0.mapObject("logo", attr.logo);
        this.mLogoDescriptionId = propertyMapper0.mapObject("logoDescription", attr.logoDescription);
        this.mMenuId = propertyMapper0.mapObject("menu", attr.menu);
        this.mNavigationContentDescriptionId = propertyMapper0.mapObject("navigationContentDescription", attr.navigationContentDescription);
        this.mNavigationIconId = propertyMapper0.mapObject("navigationIcon", attr.navigationIcon);
        this.mPopupThemeId = propertyMapper0.mapResourceId("popupTheme", attr.popupTheme);
        this.mSubtitleId = propertyMapper0.mapObject("subtitle", attr.subtitle);
        this.mTitleId = propertyMapper0.mapObject("title", attr.title);
        this.mTitleMarginBottomId = propertyMapper0.mapInt("titleMarginBottom", attr.titleMarginBottom);
        this.mTitleMarginEndId = propertyMapper0.mapInt("titleMarginEnd", attr.titleMarginEnd);
        this.mTitleMarginStartId = propertyMapper0.mapInt("titleMarginStart", attr.titleMarginStart);
        this.mTitleMarginTopId = propertyMapper0.mapInt("titleMarginTop", attr.titleMarginTop);
        this.mPropertiesMapped = true;
    }

    public void readProperties(Toolbar toolbar0, PropertyReader propertyReader0) {
        if(!this.mPropertiesMapped) {
            throw ComponentDialog..ExternalSyntheticApiModelOutline0.m();
        }
        propertyReader0.readObject(this.mCollapseContentDescriptionId, toolbar0.getCollapseContentDescription());
        propertyReader0.readObject(this.mCollapseIconId, toolbar0.getCollapseIcon());
        propertyReader0.readInt(this.mContentInsetEndId, toolbar0.getContentInsetEnd());
        propertyReader0.readInt(this.mContentInsetEndWithActionsId, toolbar0.getContentInsetEndWithActions());
        propertyReader0.readInt(this.mContentInsetLeftId, toolbar0.getContentInsetLeft());
        propertyReader0.readInt(this.mContentInsetRightId, toolbar0.getContentInsetRight());
        propertyReader0.readInt(this.mContentInsetStartId, toolbar0.getContentInsetStart());
        propertyReader0.readInt(this.mContentInsetStartWithNavigationId, toolbar0.getContentInsetStartWithNavigation());
        propertyReader0.readObject(this.mLogoId, toolbar0.getLogo());
        propertyReader0.readObject(this.mLogoDescriptionId, toolbar0.getLogoDescription());
        propertyReader0.readObject(this.mMenuId, toolbar0.getMenu());
        propertyReader0.readObject(this.mNavigationContentDescriptionId, toolbar0.getNavigationContentDescription());
        propertyReader0.readObject(this.mNavigationIconId, toolbar0.getNavigationIcon());
        propertyReader0.readResourceId(this.mPopupThemeId, toolbar0.getPopupTheme());
        propertyReader0.readObject(this.mSubtitleId, toolbar0.getSubtitle());
        propertyReader0.readObject(this.mTitleId, toolbar0.getTitle());
        propertyReader0.readInt(this.mTitleMarginBottomId, toolbar0.getTitleMarginBottom());
        propertyReader0.readInt(this.mTitleMarginEndId, toolbar0.getTitleMarginEnd());
        propertyReader0.readInt(this.mTitleMarginStartId, toolbar0.getTitleMarginStart());
        propertyReader0.readInt(this.mTitleMarginTopId, toolbar0.getTitleMarginTop());
    }

    @Override  // android.view.inspector.InspectionCompanion
    public void readProperties(Object object0, PropertyReader propertyReader0) {
        this.readProperties(((Toolbar)object0), propertyReader0);
    }
}

