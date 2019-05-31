package com.example.cukcuklite.sell.menu.unit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop bind du lieu trong man hinh danh sach don vi
 */
public class UnitAdapter extends ArrayAdapter<Unit> {

    private Context context;
    private int resource;
    private ArrayList<Unit> arrUnit;
    private UnitSelectedActivity unitSelectedActivity;

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham khoi tao adapter bind du lieu
     * @param context: context cua activity goi den adapter
     * @param resource: layout cac hang cua listView
     * @param arrUnit danh sach unit
     */
    public UnitAdapter(Context context, int resource, ArrayList<Unit> arrUnit) {
        super(context, resource, arrUnit);
        try {
            this.context = context;
            this.resource = resource;
            this.arrUnit = arrUnit;
            this.unitSelectedActivity = (UnitSelectedActivity) context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham getView
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.row_unit_layout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.initView(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final Unit unit = arrUnit.get(position);
            if (unit != null){
                viewHolder.tvUnitName.setText(unit.getmUnitName());
                updateItemSelected(unit,viewHolder);
                setItemSelected(unit,viewHolder);
                deleteItemSelected(unit,viewHolder);
            }
            return convertView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LayoutInflater.from(context).inflate(R.layout.row_unit_layout, parent, false);
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Lop View Holder quan ly du lieu
     */
    public class ViewHolder {
        TextView tvUnitName;
        ImageView ivUpdateUnit;
        ImageView ivCheckSelected;
        LinearLayout layoutUnit;


        /**
         * created by ChuVanCuong
         * created date 5/23/2019
         * target: Ham bind du lieu
         * @param v
         */
        public void initView(View v){
            try {
                tvUnitName = (TextView) v.findViewById(R.id.tv_unit_name);
                ivUpdateUnit = (ImageView) v.findViewById(R.id.iv_update_unit);
                ivCheckSelected = (ImageView) v.findViewById(R.id.iv_check_selected);
                layoutUnit = (LinearLayout) v.findViewById(R.id.layout_unit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham cap nhat view cho hang duoc chon
     * @param unit: dong duoc chon
     * @param viewHolder: viewholder
     */
    public void setItemSelected(final Unit unit, ViewHolder viewHolder){
        try {
            if (unit != null){
                if (unit.getmIsSelected() == Utils.SELECTED){
                    viewHolder.ivCheckSelected.setVisibility(View.VISIBLE);
                }else{
                    viewHolder.ivCheckSelected.setVisibility(View.INVISIBLE);
                }

                viewHolder.layoutUnit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            unit.setmIsSelected(Utils.SELECTED);
                            setUnSelected(arrUnit,unit.getmUnitID());
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham cap nhat view cho hang duoc chon
     * @param unit: dong duoc chon
     * @param viewHolder: viewholder
     */
    public void updateItemSelected(final Unit unit, ViewHolder viewHolder){
        try {
            viewHolder.ivUpdateUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (unit != null) {
                            unitSelectedActivity.showUpdateDialog(unit);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham xoa view cho hang duoc chon
     * @param unit: dong duoc chon
     * @param viewHolder: viewholder
     */
    public void deleteItemSelected(final Unit unit, final ViewHolder viewHolder){
        try {
            viewHolder.layoutUnit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    try {
                        if (unit != null) {
                            unitSelectedActivity.showPopupDelete(viewHolder.tvUnitName,unit);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham set un check cho cac item con lai
     * @param list: danh sach unit can uncheck
     * @param unitID: unit dang duoc check
     */
    public void setUnSelected(ArrayList<Unit> list, int unitID){
        try {
            if (!list.isEmpty()) {
                for (Unit u: list){
                    if (u.getmUnitID() != unitID){
                        u.setmIsSelected(Utils.NOT_SELECTED);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by: cvcuong
     * date: 5/27/2019
     * target : Ham tra ve unit dang duoc chon
     * @return
     */
    public Unit getUnitSelected(){
        try {
            if (!arrUnit.isEmpty()) {
                for (Unit u : arrUnit){
                    if (u.getmIsSelected() == Utils.SELECTED){
                        return u;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : Ham cap nhat lai danh sach sau khi xoa 1 unit
     * @param unit
     */
    public void deleteUnit(Unit unit){
        try {
            if (unit != null && !arrUnit.isEmpty()) {
                arrUnit.remove(unit);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
