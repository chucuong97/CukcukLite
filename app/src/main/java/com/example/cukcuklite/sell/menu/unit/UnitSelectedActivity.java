package com.example.cukcuklite.sell.menu.unit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop hien thi va thao tac voi cac don vi tinh
 */
public class UnitSelectedActivity extends AppCompatActivity implements UnitContract.View {

    @BindView(R.id.lv_unit)
    ListView lvUnit;
    UnitAdapter myAdapter;
    @BindView(R.id.ibtn_back)
    ImageButton ibtnBack;
    @BindView(R.id.imgbtn_add)
    ImageButton imgbtnAdd;
    @BindView(R.id.layout_unit_bar)
    RelativeLayout layoutUnitBar;
    @BindView(R.id.frame_btn_done)
    CardView frameBtnDone;
    private Context context;
    private UnitPresenter unitPresenter;
    private Dialog dialogAddUnit;
    private Dialog dialogUpdateUnit;
    private Dialog dialogDeleteUnit;
    private ImageView ivDeleteDialog;
    private EditText edtResultUnit;
    private TextView tvCancelDialog;
    private TextView tvStoredDialog;
    private TextView tvTitleUnitDialog;
    private PopupWindow popupWindow;

    private Unit unitReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_unit_selected);
            context = UnitSelectedActivity.this;
            ButterKnife.bind(this);
            Intent intent = getIntent();
            unitReceive = (Unit) intent.getSerializableExtra(Utils.UNIT_NAME);
            initPresenter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : HÀm tạo dữ liệu mẫu
     */
    public void initPresenter() {
        try {
            unitPresenter = new UnitPresenter(this, new UnitModel(context),context);
            unitPresenter.setiView(this);
            unitPresenter.getListUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham xu ly su kien click cac view tren man hinh
     *
     * @param view
     */
    @OnClick({R.id.ibtn_back, R.id.imgbtn_add,R.id.frame_btn_done})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.ibtn_back: { // khi an nut back
                    try {
                        Unit currentSelectedUnit = myAdapter.getUnitSelected();
                        if (currentSelectedUnit == null) {
                            currentSelectedUnit = unitReceive;
                            currentSelectedUnit.setmUnitID(Utils.ID_NULL);
                            sendUnitNameToInsertFoodAc(currentSelectedUnit);
                        } else {
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case R.id.imgbtn_add: {// khi can nut them
                    try {
                        showAddDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case R.id.frame_btn_done: { // khi can nut xong
                    try {
                        Unit u = myAdapter.getUnitSelected();
                        if (u == null) {
                            Toast.makeText(context, context.getString(R.string.you_must_enter_unit), Toast.LENGTH_SHORT).show();
                        } else {
                            sendUnitNameToInsertFoodAc(u);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham nhan duoc thonng bao lay du lieu tu database thanh cong
     *
     * @param list
     */
    @Override
    public void getListUnitSuccess(ArrayList<Unit> list) {
        try {
            unitPresenter.updateUnitSelected(unitReceive,list);
            Common.sortListUnit(list);
            myAdapter = new UnitAdapter(UnitSelectedActivity.this, R.layout.row_unit_layout, list);
            lvUnit.setAdapter(myAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham nhan thong bao viec lay du lieu that bai
     *
     * @param error
     */
    @Override
    public void getListUnitFail(String error) {
        try {
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham nhan duoc thong bao them du lieu don vi tinh thanh cong
     */
    @Override
    public void insertUnitSuccess(Unit unit) {
        try {
            myAdapter.notifyDataSetChanged();
            unitPresenter.getListUnit();
            dialogAddUnit.dismiss();
            sendUnitNameToInsertFoodAc(unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham nhan duoc thong bao them du lieu that bai
     * @param error
     */
    @Override
    public void insertUnitFail(String error) {
        try{
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target: Ham xu ly cua View khi nhan duoc thong bao cap nhat thanh cong tu presenter
     * @param unit
     */
    @Override
    public void updateUnitSuccess(Unit unit) {
        try {
            myAdapter.notifyDataSetChanged();
            dialogUpdateUnit.dismiss();
            sendUnitNameToInsertFoodAc(unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target: Ham xu ly cua View khi nhan duoc thong bao cap nhat that bai tu presenter
     */
    @Override
    public void updateUnitFail(String error) {
        try{
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham xu ly cua View khi xoa don vi thanh cong
     */
    @Override
    public void deleteUnitSuccess(Unit unit) {
        myAdapter.deleteUnit(unit);
        popupWindow.dismiss();
        dialogDeleteUnit.dismiss();
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham xu ly cua view khi xoa don vi that bai
     * @param error
     */
    @Override
    public void deleteUnitFail(String error) {
        dialogDeleteUnit.dismiss();
        popupWindow.dismiss();
        Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham hien dialog them don vi tinh
     */
    public void showAddDialog() {
        try {
            dialogAddUnit = new Dialog(UnitSelectedActivity.this);
            setViewUnit(dialogAddUnit);
            resgisterDismissDialog(dialogAddUnit);
            tvStoredDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String result = edtResultUnit.getText().toString();
                        Unit unit = new Unit(result,Utils.SELECTED);
                        unitPresenter.insertUnit(unit);
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
     * target: Ham bind View cho dialog
     *
     * @param dialog
     */
    private void setViewUnit(Dialog dialog) {
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_crud_unit);

            ivDeleteDialog = dialog.findViewById(R.id.iv_delete_dialog);
            edtResultUnit = dialog.findViewById(R.id.edt_result_unit);
            tvCancelDialog = dialog.findViewById(R.id.tv_cancel_dialog);
            tvStoredDialog = dialog.findViewById(R.id.tv_stored_dialog);
            tvTitleUnitDialog = dialog.findViewById(R.id.tv_title_unit_dialog);

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham hien thi dialog update Unit
     */
    public void showUpdateDialog(final Unit unit) {
        try {
            dialogUpdateUnit = new Dialog(context);
            setViewUnit(dialogUpdateUnit);

            tvTitleUnitDialog.setText(context.getString(R.string.update_unit));
            edtResultUnit.setText(unit.getmUnitName());

            resgisterDismissDialog(dialogUpdateUnit);
            tvStoredDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String unitNameUpdate = edtResultUnit.getText().toString();
                        unitPresenter.updateUnit(unitNameUpdate,unit);
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
     * target: Ham gui du lieu quay lai man hinh Them do an
     * @param unit: unit can gui
     */
    public void sendUnitNameToInsertFoodAc(Unit unit){
        try {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(Utils.RESULT_NEW_UNIT_NAME,unit);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham goi db xoa don vi
     * @param unit
     */
    public void deleteUnit(Unit unit){
        try {
            unitPresenter.deleteUnit(unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham hien thi dialog xoa mot unit
     */
    public void showDeleteDialog(final Unit unit) {
        try {
            dialogDeleteUnit = new Dialog(context);
            setViewUnit(dialogDeleteUnit);

            tvTitleUnitDialog.setText(context.getString(R.string.app_name_upper));
            tvCancelDialog.setText(context.getString(R.string.no));
            tvStoredDialog.setText(context.getString(R.string.yes));
            edtResultUnit.setText(String.format(context.getString(R.string.are_you_sure_to_delete_unit),unit.getmUnitName()));
            edtResultUnit.setTextSize(Utils.TEXT_SMALL);

            resgisterDismissDialog(dialogDeleteUnit);
            tvStoredDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        deleteUnit(unit);
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
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham hien thi popup delete
     * @param itemView: vi tri tuong doi can hien thi
     * @param unit
     */
    public void showPopupDelete(View itemView,final Unit unit){
        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_delete_unit,null);
            if (popupWindow != null){
                popupWindow.dismiss();
            }
            popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAsDropDown(itemView);
            popupView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteDialog(unit);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham dang ky su kien dissmiss Dialog
     * @param dialog
     */
    public void resgisterDismissDialog(final Dialog dialog){
        ivDeleteDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        tvCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}