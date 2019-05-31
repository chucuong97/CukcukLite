package com.example.cukcuklite.sell.menu.insert;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.update.UpdateFoodInMenuActivity;
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
 * target : Lop Adapter bind du lieu man hinh thuc don
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuRcHolder> {


    private ArrayList<Food> listFood = new ArrayList<>();
    private Context context;

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham khoi tao adapter
     * @param list: Danh sach mon an
     * @param c: Context cua activity
     */
    public MenuAdapter(ArrayList<Food> list, Context c) {
        try {
            this.listFood = list;
            this.context = c;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham khoi tao View Holder
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MenuRcHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.row_menu_food, viewGroup, false);
            return new MenuRcHolder(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham bind du lieu
     * @param menuRcHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuRcHolder menuRcHolder, int i) {
        try{
            final Food food = listFood.get(i);
            menuRcHolder.tvFoodName.setText(food.getmFoodName());
            String s = NumberFormat.getIntegerInstance(Locale.GERMAN).format((int)(food.getmFoodPrice()));
            menuRcHolder.tvFoodPrice.setText(String.format(context.getString(R.string.price_food_with_agrument), s));
            menuRcHolder.onItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent i = new Intent(context, UpdateFoodInMenuActivity.class);
                    i.putExtra(Utils.SELECTED_FOOD,food);
                    context.startActivity(i);
                }
            });
            Common.setImageFromAsset(context,food.getmImgName(),menuRcHolder.ivFood);
            Common.setBackGroundCircle(Color.parseColor(food.getmColorBackground()),menuRcHolder.frameLayoutCircle);
            if (food.getmItemState() == Utils.STOP_SELL){
                menuRcHolder.frameLayoutStopSell.setVisibility(View.VISIBLE);
            }else{
                menuRcHolder.frameLayoutStopSell.setVisibility(View.GONE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * Ham tra ve so luong row
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
     * target : Lop View Holder
     */
    public class MenuRcHolder extends RecyclerView.ViewHolder  {

        private ImageView ivFood;
        private TextView tvFoodName;
        private TextView tvFoodPrice;
        private FrameLayout frameLayoutCircle;
        private ItemClickListener itemClickListener;
        private FrameLayout frameLayoutStopSell;

        /**
         * created by: cvcuong
         * date: 5/28/2019
         * target :Ham khai bao du lieu
         * @param itemView
         */
        public MenuRcHolder(@NonNull View itemView) {
            super(itemView);
            try {
                initView();
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClickListener.onClick(v,getAdapterPosition());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * created by: cvcuong
         * date: 5/22/2019
         * target : Lop khoi tao view
         */
        private void initView(){
            try {
                ivFood = itemView.findViewById(R.id.iv_food);
                tvFoodName = itemView.findViewById(R.id.tv_food_name);
                tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
                frameLayoutCircle = itemView.findViewById(R.id.frame_circle);
                frameLayoutStopSell = itemView.findViewById(R.id.layout_stop_sell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * created by: cvcuong
         * date: 5/22/2019
         * target : Ham bat su kien click
         * @param itemClickListener
         */
        public void onItemClickListener(ItemClickListener itemClickListener){
            try {
                this.itemClickListener = itemClickListener;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
