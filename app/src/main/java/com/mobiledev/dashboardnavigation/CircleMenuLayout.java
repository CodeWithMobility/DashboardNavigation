package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * CircleMenuLayout.java
 * Author: wangkunlin
 * Date: 2016-01-29
 * Email: 1040057694@qq.com
 */
public class CircleMenuLayout extends ViewGroup {
    public CircleMenuLayout(Context context) {
        this(context, null);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private GestureDetector gestureDetector;
    private boolean[] quadrantTouched;
    private float angle;
    private float firstPosition = 270; // top
    private int speed = 25;
    private boolean itemRotate = false;
    private boolean fly = true;
    private float radius;
    private ValueAnimator animator;
    private View tappedView;
    private int selected;

    private void init(AttributeSet attrs) {
        gestureDetector = new GestureDetector(getContext(), new CircleGestureListener());
        quadrantTouched = new boolean[]{false, false, false, false, false};
        if (attrs == null) {
            return;
        }
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CircleMenuLayout);
        angle = a.getInt(R.styleable.CircleMenuLayout_firstPosition, (int) firstPosition);
        firstPosition = angle;
        speed = a.getInt(R.styleable.CircleMenuLayout_speed, speed);
        itemRotate = a.getBoolean(R.styleable.CircleMenuLayout_itemRotate, itemRotate);
        fly = a.getBoolean(R.styleable.CircleMenuLayout_fly, fly);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int childMaxWidth = 0;
        int childMaxHeight = 0;
        int childCount = getChildCount();

        int verticalPadding = getPaddingTop() + getPaddingBottom();
        int horizontalPadding = getPaddingLeft() + getPaddingRight();

        for (int i = 0; i < childCount; ++i) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            LayoutParams lp = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, horizontalPadding, lp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, verticalPadding, lp.height);

            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            childMaxWidth = Math.max(childWidth, childMaxWidth);
            childMaxHeight = Math.max(childHeight, childMaxHeight);
        }

        childMaxWidth = Math.max(childMaxHeight, childMaxWidth);
        childMaxHeight = Math.max(childMaxHeight, childMaxWidth);

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childMaxWidth, MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childMaxHeight, MeasureSpec.EXACTLY);
        for (int i = 0; i < childCount; ++i) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        int measuredWidth;
        int measuredHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            if (childMaxWidth * 4 > widthSize) {
                throw new RuntimeException("CircleMenuLayout too small!");
            }
            measuredWidth = widthSize;
        } else {
            measuredWidth = childMaxWidth * 5;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            measuredHeight = childMaxHeight * 5;
        }

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    public int getChildCount() {
        int childCount = super.getChildCount();
        int goneCount = 0;
        for (int i = 0; i < childCount; ++i) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                ++goneCount;
            }
        }
        return childCount - goneCount;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left, top;
        float angleDelay = 360.0f / childCount;
        int childWidth;
        int childHeight;
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            radius = (getWidth() - child.getMeasuredWidth()) / 2;
            if (angle > 360) {
                angle -= 360;
            } else {
                if (angle < 0) {
                    angle += 360;
                }
            }

            if (itemRotate && child instanceof MenuItem) {
                MenuItem item = (MenuItem) child;
                item.setAngle(angle - firstPosition);
            }

            child.setTag(angle);
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();

            left = Math
                    .round((float) (((getWidth() / 2) - childWidth / 2) + radius
                            * Math.cos(Math.toRadians(angle))));
            top = Math
                    .round((float) (((getHeight() / 2) - childHeight / 2) + radius
                            * Math.sin(Math.toRadians(angle))));

            child.layout(left, top, left + childWidth, top + childHeight);
            angle += angleDelay;
        }
    }

    public void setAngle(float angle) {
        float tmpAngle = angle % 360;
        if (this.angle == tmpAngle) {
            return;
        }
        this.angle = tmpAngle;
        setChildAngles();
    }

    private void setChildAngles() {
        int left, top, childCount = getChildCount();
        float angleDelay = 360.0f / childCount;
        float localAngle = angle;

        for (int i = 0; i < childCount; i++) {
            if (localAngle > 360) {
                localAngle -= 360;
            } else {
                if (localAngle < 0) {
                    localAngle += 360;
                }
            }

            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            left = Math
                    .round((float) (((getWidth() / 2) - childWidth / 2) + radius
                            * Math.cos(Math.toRadians(localAngle))));
            top = Math
                    .round((float) (((getHeight() / 2) - childHeight / 2) + radius
                            * Math.sin(Math.toRadians(localAngle))));
            if (itemRotate && child instanceof MenuItem) {
                MenuItem item = (MenuItem) child;
                item.setAngle(localAngle - firstPosition);
            }
            child.setTag(localAngle);

            if (Math.abs(localAngle - firstPosition) < (angleDelay / 2)
                    && selected != i) {
                selected = i;
            }

            child.layout(left, top, left + childWidth, top + childHeight);
            localAngle += angleDelay;
        }
    }

    private void rotateViewToCenter(View view) {
        float viewAngle = view.getTag() != null ? (Float) view.getTag() : 0;
        float destAngle = firstPosition - viewAngle;

        if (destAngle < 0) {
            destAngle += 360;
        }

        if (destAngle > 180) {
            destAngle = -1 * (360 - destAngle);
        }

        animateTo(angle + destAngle, 7500 / speed);
    }

    private void animateTo(float endDegree, long duration) {
        if (animator != null && animator.isRunning()
                || Math.abs(angle - endDegree) < 1) {
            return;
        }

        animator = ValueAnimator.ofFloat(angle, endDegree);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setAngle(value);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            private boolean wasCanceled = false;

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (wasCanceled) {
                    return;
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                wasCanceled = true;
            }
        });
        animator.start();
    }

    private double getPositionAngle(double xTouch, double yTouch) {
        double x = xTouch - (getWidth() / 2d);
        double y = getHeight() - yTouch - (getHeight() / 2d);

        switch (getPositionQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 2:
            case 3:
                return 180 - (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            default:
                // ignore, does not happen
                return 0;
        }
    }

    private void stopAnimation() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }
    }

    private int getPositionQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }

    private void rotateButtons(float degrees) {
        angle += degrees;
        setChildAngles();
    }

    private double touchStartAngle;
    private boolean didMove = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled()) {
            gestureDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // reset the touched quadrants
                    for (int i = 0; i < quadrantTouched.length; i++) {
                        quadrantTouched[i] = false;
                    }

                    stopAnimation();
                    touchStartAngle = getPositionAngle(event.getX(),
                            event.getY());
                    didMove = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    double currentAngle = getPositionAngle(event.getX(), event.getY());
                    rotateButtons((float) (touchStartAngle - currentAngle));
                    touchStartAngle = currentAngle;
                    didMove = true;
                    break;
                case MotionEvent.ACTION_UP:
                    if (didMove) {
                        rotateViewToCenter(getChildAt(selected));
                    }
                    break;
            }
            // set the touched quadrant to true
            quadrantTouched[getPositionQuadrant(event.getX() - (getWidth() / 2),
                    getHeight() - event.getY() - (getHeight() / 2))] = true;
            return true;
        }

        return false;
    }

    private class CircleGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (!fly) {
                return false;
            }
            // get the quadrant of the start and the end of the fling
            int q1 = getPositionQuadrant(e1.getX() - (getWidth() / 2),
                    getHeight() - e1.getY() - (getHeight() / 2));
            int q2 = getPositionQuadrant(e2.getX() - (getWidth() / 2),
                    getHeight() - e2.getY() - (getHeight() / 2));

            if ((q1 == 2 && q2 == 2 && Math.abs(velocityX) < Math
                    .abs(velocityY))
                    || (q1 == 3 && q2 == 3)
                    || (q1 == 1 && q2 == 3)
                    || (q1 == 4 && q2 == 4 && Math.abs(velocityX) > Math
                    .abs(velocityY))
                    || ((q1 == 2 && q2 == 3) || (q1 == 3 && q2 == 2))
                    || ((q1 == 3 && q2 == 4) || (q1 == 4 && q2 == 3))
                    || (q1 == 2 && q2 == 4 && quadrantTouched[3])
                    || (q1 == 4 && q2 == 2 && quadrantTouched[3])) {
                // the inverted rotations
                animateTo(
                        getCenteredAngle(angle - (velocityX + velocityY) / speed),
                        25000 / speed);
            } else {
                // the normal rotation
                animateTo(
                        getCenteredAngle(angle + (velocityX + velocityY) / speed),
                        25000 / speed);
            }

            return true;
        }

        private int tappedViewsPosition;
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            tappedViewsPosition = pointToPosition(e.getX(), e.getY());
            if (tappedViewsPosition >= 0) {
                tappedView = getChildAt(tappedViewsPosition);
                tappedView.setPressed(true);
            } else {
            }

            if (tappedView != null) {
                if (selected == tappedViewsPosition) {
                } else {
                    rotateViewToCenter(tappedView);
                }
                return true;
            }
            return super.onSingleTapUp(e);
        }
    }

    private int pointToPosition(float x, float y) {
        for (int i = 0; i < getChildCount(); i++) {
            View item = getChildAt(i);
            if (item.getLeft() < x && item.getRight() > x
                    & item.getTop() < y && item.getBottom() > y) {
                return i;
            }
        }
        return -1;
    }

    private float getCenteredAngle(float angle) {
        float angleDelay = 360 / getChildCount();
        float check = angle % angleDelay;
        if (check != 0) {
            angle = angle / Math.abs(angle) * angleDelay + angle - check;
        }
        float localAngle = angle % 360;
        if (localAngle < 0) {
            localAngle = 360 + localAngle;
        }

        for (float i = firstPosition; i < firstPosition + 360; i += angleDelay) {
            float locI = i % 360;
            float diff = localAngle - locI;
            if (Math.abs(diff) < angleDelay / 2) {
                angle -= diff;
                break;
            }
        }
        return angle;
    }

}