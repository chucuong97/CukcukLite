package com.example.cukcuklite.orderFood;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lớp Adapter bind dữ liệu màn hình Oder food
 */
public class OderFoodAdapter extends RecyclerView.Adapter<OderFoodAdapter.MyRcHolder> {


    private ArrayList<Food> listFood = new ArrayList<>();
    private Context context;

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Hàm khoi tao va gan dữ liệu list Item
     * @param list: danh sach item can gan du lieu
     */
    public OderFoodAdapter(Context c, ArrayList<Food> list) {
        try {
            this.context = c;
            this.listFood = list;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Hàm tạo View Holder
     * @param viewGroup
     * @param i
     * @return View holder
     */
    @NonNull
    @Override
    public MyRcHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.row_oder_food, viewGroup, false);
            return new MyRcHolder(view);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Hàm bind dữ liệu
     * @param myRcHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyRcHolder myRcHolder, int i) {
        try{
            Food food = listFood.get(i);
            myRcHolder.tvFoodName.setText(food.getmFoodName());
            String s = NumberFormat.getIntegerInstance(Locale.GERMAN).format((int)(food.getmFoodPrice()));
            myRcHolder.tvFoodPrice.setText(s);
            myRcHolder.tvFoodAmount.setText(String.valueOf(food.getmFoodAmount()));
            Common.setImageFromAsset(context,food.getmImgName(),myRcHolder.ivFood);
            Common.setBackGroundCircle(Color.parseColor(food.getmColorBackground()),myRcHolder.frameCircle);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Hàm trả về số lượng row
     * @return
     */
    @Override
    public int getItemCount() {
        try {
            return listFood.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Lớp ViewHolder
     */
    public class MyRcHolder extends RecyclerView.ViewHolder {

        ImageView ivFood;
        ImageView ivFoodDone;
        TextView tvFoodName;
        TextView tvFoodPrice;
        ImageView ivRemove;
        TextView tvFoodAmount;
        ImageView ivFoodAdd;
        LinearLayout layoutCalNumber;
        FrameLayout frameCircle;

        /**
         * created by: cvcuong
         * date: 5/22/2019
         * target : Ham khoi tao View Holder
         * @param itemView
         */
        public MyRcHolder(@NonNull View itemView) {
            super(itemView);
            try {
                initView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * created by ChuVanCuong
         * created date 5/23/2019
         * target: Ham bind View
         */
        private void initView(){
            try {
                ivFood = itemView.findViewById(R.id.iv_food);
                tvFoodName = itemView.findViewById(R.id.tv_food_name);
                ivFoodDone = itemView.findViewById(R.id.iv_food_done);
                tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
                ivRemove = itemView.findViewById(R.id.iv_remove);
                tvFoodAmount = itemView.findViewById(R.id.tv_food_amount);
                ivFoodAdd = itemView.findViewById(R.id.iv_food_add);
                layoutCalNumber = itemView.findViewById(R.id.layout_cal_number);
                frameCircle = itemView.findViewById(R.id.frame_circle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
