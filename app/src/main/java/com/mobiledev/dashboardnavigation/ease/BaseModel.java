package com.mobiledev.dashboardnavigation.ease;

/**
 * Created by manu on 1/7/2018.
 */

import android.graphics.Rect;
import android.graphics.RectF;

/**
 * The BaseModel is the parent model of every chart model. It basically only holds the information
 * about the legend labels of the childs value.
 */
public abstract class BaseModel {

    protected BaseModel(String _legendLabel) {
        mLegendLabel = _legendLabel;
    }

    protected BaseModel() {
    }

    public String getLegendLabel() {
        return mLegendLabel;
    }

    public void setLegendLabel(String _LegendLabel) {
        mLegendLabel = _LegendLabel;
    }

    public boolean canShowLabel() {
        return mShowLabel;
    }

    public void setShowLabel(boolean _showLabel) {
        mShowLabel = _showLabel;
    }

    public int getLegendLabelPosition() {
        return mLegendLabelPosition;
    }

    public void setLegendLabelPosition(int _legendLabelPosition) {
        mLegendLabelPosition = _legendLabelPosition;
    }

    public RectF getLegendBounds() {
        return mLegendBounds;
    }

    public void setLegendBounds(RectF _legendBounds) {
        mLegendBounds = _legendBounds;
    }

    public Rect getTextBounds() {
        return mTextBounds;
    }

    public void setTextBounds(Rect _textBounds) {
        mTextBounds = _textBounds;
    }

    public boolean isIgnore() {
        return mIgnore;
    }

    public void setIgnore(boolean _ignore) {
        mIgnore = _ignore;
    }

    /**
     * Label value
     */
    protected String    mLegendLabel;

    /**
     * Indicates whether the label should be shown or not.
     */
    protected boolean   mShowLabel;

    /**
     * X-coordinate of the label.
     */
    private int     mLegendLabelPosition;

    /**
     * Boundaries of the label
     */
    private RectF   mLegendBounds;

    /**
     * Boundaries of the legend labels value
     */
    private Rect    mTextBounds;

    /**
     * Indicates if the label should be ignored, when the boundaries are calculated.
     */
    private boolean mIgnore = false;
}