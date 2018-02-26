package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */


public class PieCharBean {

    public static final int FLAG_NO_MATCH = -361;

    private float mPercentage = 0f;

    private String mName;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

    private float mAngleSize = 0f;

    private float mAngleStart = 0f;

    private float mAngleEnd = 0f;

    private float mAnimAngleSize = 0f;

    public PieCharBean(float percentage, String name, int index) {
        this.mPercentage = percentage;
        this.mName = name;
        this.index = index;
    }

    public float getPercentage() {
        return mPercentage;
    }

    public void setPercentage(float percentage) {
        this.mPercentage = percentage;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public float getAngleSize() {
        return mAngleSize;
    }

    public void setAngleSize(float angleSize) {
        this.mAngleSize = angleSize;
    }

    public float getAngleStart() {
        return mAngleStart;
    }

    public void setAngleStart(float angleStart) {
        this.mAngleStart = angleStart;
    }

    public float getAngleEnd() {
        return mAngleEnd;
    }

    public void setAngleEnd(float angleEnd) {
        this.mAngleEnd = angleEnd;
    }

    public float getAnimAngleSize() {
        return mAnimAngleSize;
    }

    public void setAnimAngleSize(float animAngleSize) {
        this.mAnimAngleSize = animAngleSize;
    }

    /**
     * is contain select
     * @param startAngle
     * @param selectAngle
     * @return the middle angle
     */
    public float isContainSelect(float startAngle, float selectAngle) { // contain start
        float posModeStartAngle = getPosMode360(startAngle + mAngleStart);
        float posModeEndAngle = getPosMode360(startAngle + mAngleEnd);
//        Log.e("xx", "posStar=" + posModeStartAngle + " posEnd=" + posModeEndAngle + " select=" + selectAngle);
        if (posModeStartAngle < posModeEndAngle) {
            if (selectAngle < posModeEndAngle && selectAngle >= posModeStartAngle) {
                return (posModeEndAngle + posModeStartAngle)/2;
            }
        } else if (posModeEndAngle < posModeStartAngle) {
            if (selectAngle >= posModeStartAngle || selectAngle < posModeEndAngle) {
                float factEndAngle = posModeEndAngle - 360;
                return (factEndAngle + posModeStartAngle) /2;
            }
        }
        return FLAG_NO_MATCH;
    }

    /**
     * Get Position mode 360.
     * @param num
     * @return
     */
    private float getPosMode360(float num) {
        float modeNum = num % 360;
        if (modeNum < 0) {
            modeNum += 360;
        }
        return modeNum;
    }
}