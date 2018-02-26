package com.mobiledev.dashboardnavigation.ease;

/**
 * Created by manu on 1/7/2018.
 */

public class Point2D {

    public Point2D(float _x, float _y) {
        mX = _x;
        mY = _y;
    }

    public Point2D() {
    }

    public float getX() {
        return mX;
    }

    public void setX(float _x) {
        mX = _x;
    }

    public float getY() {
        return mY;
    }

    public void setY(float _y) {
        mY = _y;
    }

    public float[] getFloatArray() {
        return new float[]{mX, mY};
    }

    private float mX;
    private float mY;
}