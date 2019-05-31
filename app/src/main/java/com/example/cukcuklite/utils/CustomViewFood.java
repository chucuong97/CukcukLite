package com.example.cukcuklite.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cukcuklite.R;

public class CustomViewFood extends LinearLayout {

    int bg_color;
    int icon_food;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomViewFood(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomViewFood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomViewFood(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewFood);
        bg_color = typedArray.getColor(R.styleable.CustomViewFood_bg_color, Color.WHITE);
        icon_food = typedArray.getResourceId(R.styleable.CustomViewFood_icon_food, R.drawable.ic_check_done);
        typedArray.recycle();

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.row_oder_food,this,true);

        FrameLayout frameCircle = findViewById(R.id.frame_circle);
        ImageView iv_food = findViewById(R.id.iv_food);

        Common.setBackGroundCircle(Color.BLACK,frameCircle);
        iv_food.setImageResource(icon_food);

    }



}
