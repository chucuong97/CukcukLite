package com.example.cukcuklite.sell;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.utils.Common;

import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lop adapter bind du lieu cho man hinh ban hang
 */
public class SellAdapter extends RecyclerView.Adapter<SellAdapter.SellRcHolder>{

    ArrayList<Table> listTable = new ArrayList<>();

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham khoi tao Adapter
     * @param list
     */
    public SellAdapter(ArrayList<Table> list){
        try {
            this.listTable = list;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target :Ham tao ViewHolder
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public SellRcHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.row_sell_item,viewGroup,false);
            return new SellRcHolder(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham bind du lieu
     * @param sellRcHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull SellRcHolder sellRcHolder, int i) {
        try{
            Table t = listTable.get(i);
            sellRcHolder.tvtotalMoney.setText(String.valueOf(t.getmTotalMoney()));
            sellRcHolder.tvListFood.setText(t.getmListFood());
            sellRcHolder.tvCustomerNumber.setText(String.valueOf(t.getmTotalCustomer()));
            sellRcHolder.tvTableNumber.setText(String.valueOf(t.getmTableNumber()));
            Common.setBackGroundCircle(Color.parseColor("#43A047"),sellRcHolder.frameLayoutCirleTv);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham tra ve so luong row
     * @return
     */
    @Override
    public int getItemCount() {
        try {
            return listTable.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Lop View holder
     */
    public class SellRcHolder extends RecyclerView.ViewHolder{
        TextView tvTableNumber;
        TextView tvCustomerNumber;
        TextView tvListFood;
        TextView tvtotalMoney;
        FrameLayout frameLayoutCirleTv;

        /**
         * created by: cvcuong
         * date: 5/22/2019
         * target : Ham khoi tao View Holder
         * @param itemView
         */
        public SellRcHolder(@NonNull View itemView) {
            super(itemView);
            try {
                initView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * created by: cvcuong
         * date: 5/22/2019
         * target : Ham tao view
         */
        private void initView() {
            try {
                tvTableNumber = itemView.findViewById(R.id.tv_table_number);
                tvCustomerNumber = itemView.findViewById(R.id.tv_customer_number);
                tvListFood = itemView.findViewById(R.id.tv_list_food);
                tvtotalMoney = itemView.findViewById(R.id.tv_total_money);
                frameLayoutCirleTv = itemView.findViewById(R.id.frame_circle_tv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
