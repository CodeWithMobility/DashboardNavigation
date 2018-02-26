package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;



/**
 *
 * @author Szugyi
 * Custom ImageView for the CircleLayout class.
 * Makes it possible for the image to have an angle, position and a name.
 * Angle is used for the positioning in the circle menu.
 */
@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {

    private float angle = 0;
    private int position = 0;
    private String name;

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /**
     * @param context
     */
    public CircleImageView(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CircleImageView);

            name = a.getString(R.styleable.CircleImageView_name);
        }
    }

}