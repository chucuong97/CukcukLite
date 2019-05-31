package com.example.cukcuklite.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Common {
    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target : Ham loai bo ky tu dau . de tranh thanh dau phay dong
     * @param price: chuoi ten gia mon an
     * @return: chuoi da duoc chuan hoa
     */
    public static String nomarlizedPrice(String price){
        return price.replace(".","");
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target : Ham sap xep danh sach don vi tinh theo ten
     * @param listUnit: danh sach don viu tinh can sap xep
     */
    public static void sortListUnit(ArrayList<Unit> listUnit){
        Collections.sort(listUnit, new Comparator<Unit>(){
            public int compare(Unit u1, Unit u2) {
                return u1.getmUnitName().compareToIgnoreCase(u2.getmUnitName());
            }
        });
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham sap xep danh sach mon an theo ten
     * @param listFood: danh sach can sap xep
     */
    public static void sortListFood(ArrayList<Food> listFood){
        try {
            Collections.sort(listFood, new Comparator<Food>(){
                public int compare(Food f1, Food f2) {
                    return f1.getmFoodName().compareToIgnoreCase(f2.getmFoodName());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham set background tron theo tung mau
     * @param color: mau can set
     * @param view: view can bo tron
     */
    public static void setBackGroundCircle(int color, View view) {
        try {
            if (view != null){
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.OVAL);
                gradientDrawable.setColor(color);
                view.setBackground(gradientDrawable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham luu gia tri color vao shared preference
     * @param context: context cua ac goi ham
     * @param color: gia tri color can luu
     */
    public static void putColorToPreferences(Context context, String color){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Utils.PREFERENCE_COLOR, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Utils.CURRENT_COLOR,color);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham lay gia tri color cuoi cung luu vao shared preference
     * @param context: context cua ac goi den ham
     * @return: gia tri color
     */
    public static String getColorFromPreferences(Context context){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Utils.PREFERENCE_COLOR, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Utils.CURRENT_COLOR,Utils.COLOR_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.COLOR_DEFAULT;
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham luu ten anh xuong shared preference
     * @param context: context cua ac can goi
     * @param imgName: ten anh can luu
     */
    public static void putImgToPreferences(Context context, String imgName){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Utils.PREFERENCE_IMG, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Utils.CURRENT_IMG,imgName);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham lay ten icon cuoi cung duoc luu vao shared preference
     * @param context: context cua ac goi ham
     * @return ten icon
     */
    public static String getImgFromPreferences(Context context){
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Utils.PREFERENCE_IMG, Context.MODE_PRIVATE);
            return sharedPreferences.getString(Utils.CURRENT_IMG,Utils.IMG_DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.IMG_DEFAULT;
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target :Ham set Icon cho image View tu file asset
     * @param context: context cua ac goi ham
     * @param iconName: ten icon can set
     * @param imageView: view can set icon
     */
    public static void setImageFromAsset(Context context, String iconName, ImageView imageView){
        try {
            InputStream ims = context.getAssets().open(Utils.ICON_PATH + iconName);
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/31/2019
     * target : Ham lay danh sach icon trong file asset
     * @param context
     * @return
     */
    public static String[] getListIcon(Context context){
        String[] listIcon = new String[0];
        try {
            listIcon = context.getAssets().list(Utils.PATH_FOLDER_ICON);
            if (listIcon.length > 0) {
                return listIcon;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listIcon;
    }
}
