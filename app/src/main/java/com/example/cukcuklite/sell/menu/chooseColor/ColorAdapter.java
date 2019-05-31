package com.example.cukcuklite.sell.menu.chooseColor;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.sell.menu.insert.InsertFoodToMenuActivity;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {


    private String[] listColor;
    private Context context;
    private String colorCurrent;
    private OnClickChooseColor onClickChooseColor;
    public ColorAdapter(Context context, String colorCurrent, OnClickChooseColor clickChooseColor){
        this.listColor = Utils.listColor;
        this.context = context;
        this.colorCurrent = colorCurrent;
        this.onClickChooseColor = clickChooseColor;
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_choose_color,viewGroup,false);
            return new ColorViewHolder(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final ColorViewHolder colorViewHolder, final int i) {
        try{
            if (listColor.length > 0){
                final int color = Color.parseColor(listColor[i]);
                Common.setBackGroundCircle(color,colorViewHolder.frameBgColor);
                if (colorCurrent.equals(listColor[i])){
                    colorViewHolder.ivCheckDone.setVisibility(View.VISIBLE);
                }else{
                    colorViewHolder.ivCheckDone.setVisibility(View.GONE);
                }
                colorViewHolder.frameBgColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        colorCurrent = listColor[i];
                        notifyDataSetChanged();
//                        ((InsertFoodToMenuActivity)context).updateBackgroundColor(colorCurrent);
                        onClickChooseColor.onClick(colorCurrent);
                    }
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        try {
            return listColor.length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{

        public FrameLayout frameBgColor;
        public ImageView ivCheckDone;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            frameBgColor = (itemView).findViewById(R.id.frame_bg_color);
            ivCheckDone = (itemView).findViewById(R.id.iv_check_done);
        }

    }
}
