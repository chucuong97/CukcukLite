package com.example.cukcuklite.sell.menu.chooseIcon;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import java.io.InputStream;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {


    private String[] listIcon;
    private Context context;
    private String iconCurrent;
    private OnClickChooseIcon clickChooseIcon;
    public IconAdapter(Context context, String iconCurrent, OnClickChooseIcon clickChooseIcon){
        this.context = context;
        this.listIcon = Common.getListIcon(context);
        this.iconCurrent = iconCurrent;
        this.clickChooseIcon = clickChooseIcon;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_choose_icon,viewGroup,false);
            return new IconViewHolder(v);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final IconViewHolder iconViewHolder, final int i) {
        try{
            if (listIcon.length > 0){
                final String icon = listIcon[i];
                Common.setImageFromAsset(context,icon,iconViewHolder.ivIcon);
                iconViewHolder.ivIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iconCurrent = icon;
                        clickChooseIcon.onClick(iconCurrent);
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
            return listIcon.length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    public class IconViewHolder extends RecyclerView.ViewHolder{

        public ImageView ivIcon;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = (itemView).findViewById(R.id.iv_icon);
        }

    }
}
