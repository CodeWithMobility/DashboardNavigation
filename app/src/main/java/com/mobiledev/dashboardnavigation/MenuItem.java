package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * MenuItem.java
 * Author: wangkunlin
 * Date: 2016-01-29
 * Email: 1040057694@qq.com
 */
@SuppressLint("AppCompatCustomView")
public class MenuItem extends ImageView {
    public MenuItem(Context context) {
        super(context);
    }

    public MenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float angle;
    private boolean rotate = false;

    public void setAngle(float angle) {
        rotate = true;
        if (this.angle == angle) {
            return;
        }
        this.angle = angle;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (rotate) {
            float cx = getWidth() / 2;
            float cy = getHeight() / 2;
            canvas.rotate(angle, cx, cy);
        }
        super.onDraw(canvas);
    }
}