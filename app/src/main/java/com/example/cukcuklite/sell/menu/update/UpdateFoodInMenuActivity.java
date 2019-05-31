package com.example.cukcuklite.sell.menu.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.chooseColor.ChooseColorDialog;
import com.example.cukcuklite.sell.menu.chooseIcon.ChooseIconDialog;
import com.example.cukcuklite.sell.menu.chooseColor.IgetColorChoosen;
import com.example.cukcuklite.sell.menu.chooseIcon.IgetIconChoosen;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.sell.menu.unit.UnitSelectedActivity;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop sua doi cac mon an
 */
public class UpdateFoodInMenuActivity extends AppCompatActivity implements UpdateFoodContract.View {


    @BindView(R.id.ibtn_back)
    ImageButton ibtnBack;
    @BindView(R.id.tv_stored_bar)
    TextView tvStoredBar;
    @BindView(R.id.layout_update_food_bar)
    RelativeLayout layoutUpdateFoodBar;
    @BindView(R.id.edt_food_name)
    EditText edtFoodName;
    @BindView(R.id.layout_name_food)
    LinearLayout layoutNameFood;
    @BindView(R.id.edt_food_price)
    EditText edtFoodPrice;
    @BindView(R.id.layout_price_food)
    LinearLayout layoutPriceFood;
    @BindView(R.id.tv_unit_name)
    TextView tvUnitName;
    @BindView(R.id.layout_unit)
    LinearLayout layoutUnit;
    @BindView(R.id.frame_circle_palette)
    FrameLayout frameCirclePalette;
    @BindView(R.id.frame_circle_symbol)
    FrameLayout frameCircleSymbol;
    @BindView(R.id.tv_delete_food)
    TextView tvDeleteFood;
    @BindView(R.id.frame_delete_food)
    CardView frameDeleteFood;
    @BindView(R.id.tv_stored_food)
    TextView tvStoredFood;
    @BindView(R.id.cb_state)
    CheckBox cbState;
    @BindView(R.id.iv_choose_color)
    ImageView ivChooseColor;
    @BindView(R.id.iv_choose_symbol)
    ImageView ivChooseSymbol;
    private Food foodReceive;
    private Context context;
    private UpdateFoodPresenter updateFoodPresenter;
    private Unit currentUnit;
    private Dialog deleteFooddialog;

    private ImageView ivDeleteDialog;
    private EditText edtResultUnit;
    private TextView tvCancelDialog;
    private TextView tvStoredDialog;
    private TextView tvTitleUnitDialog;

    private ChooseColorDialog colorDialog;
    private ChooseIconDialog iconDialog;
    private String colorCurrent;
    private String iconCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update_food_in_menu);
            ButterKnife.bind(this);
            initPresenter();
            getDataFromIntent();
            setContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham khoi tao presenter cho chuc nang cap nhat mon an
     */
    public void initPresenter() {
        try {
            updateFoodPresenter = new UpdateFoodPresenter(this, new UpdateFoodModel(context), context);
            updateFoodPresenter.setView(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham test set background
     */
    public void setBackGroundCircle(String color) {
        try {
            Common.setBackGroundCircle(Color.parseColor(color), frameCirclePalette);
            Common.setBackGroundCircle(Color.parseColor(color), frameCircleSymbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham nhan du lieu tu activity menu
     */
    public void getDataFromIntent() {
        try {
            this.context = UpdateFoodInMenuActivity.this;
            Intent receiveIntent = getIntent();
            foodReceive = (Food) receiveIntent.getSerializableExtra(Utils.SELECTED_FOOD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham anh xa du lieu mon an tu activity menu food gui sang
     */
    public void setContent() {
        try {
            edtFoodName.setText(foodReceive.getmFoodName() + "");
            edtFoodName.setSelection(foodReceive.getmFoodName().length());
            String s = NumberFormat.getIntegerInstance(Locale.GERMAN).format((int) (foodReceive.getmFoodPrice()));
            edtFoodPrice.setText(s);
            if (foodReceive.getmUnitID() == Utils.ID_NULL) {
                tvUnitName.setHint(context.getString(R.string.enter_unit));
                currentUnit = new Unit();
            } else {
                updateFoodPresenter.getUnitNameByID(foodReceive.getmUnitID());
                currentUnit = updateFoodPresenter.getUnitByID(foodReceive.getmUnitID());
            }
            if (foodReceive.getmItemState() == Utils.STOP_SELL) {
                cbState.setChecked(true);
            } else {
                cbState.setChecked(false);
            }

            colorCurrent = foodReceive.getmColorBackground();
            iconCurrent = foodReceive.getmImgName();
            Common.setImageFromAsset(context, iconCurrent, ivChooseSymbol);
            setBackGroundCircle(colorCurrent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham nhan thong bao lay ten unit thanh cong
     *
     * @param name
     */
    @Override
    public void getUnitNameSuccess(String name) {
        try {
            tvUnitName.setText(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly khi nhan duoc thong bao update thanh cong
     */
    @Override
    public void updateFoodSuccess() {
        try {
            Intent intentBroadCast = new Intent(Utils.ACTION_NOTIFY_DATA_CHANGE);
            sendBroadcast(intentBroadCast);
            Common.putColorToPreferences(this,colorCurrent);
            Common.putImgToPreferences(this,iconCurrent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly khi cap nhat that bai
     *
     * @param error: loi cap nhat
     */
    @Override
    public void updateFoodFail(String error) {
        try {
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly thong tin khi xoa thanh cong mon an
     */
    @Override
    public void deleteFoodSuccess() {
        try {
            Intent intentBroadCast = new Intent(Utils.ACTION_NOTIFY_DATA_CHANGE);
            sendBroadcast(intentBroadCast);
            deleteFooddialog.dismiss();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly thong tin khi xoa that bai
     *
     * @param error
     */
    @Override
    public void deleteFoodFail(String error) {
        try {
            Toast.makeText(context, "" + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.tv_stored_bar, R.id.tv_delete_food, R.id.tv_stored_food, R.id.ibtn_back, R.id.layout_unit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_unit: { // khi click chon don vi
                try {
                    Intent intentUnit = new Intent(UpdateFoodInMenuActivity.this, UnitSelectedActivity.class);
                    intentUnit.putExtra(Utils.UNIT_NAME, currentUnit);
                    startActivityForResult(intentUnit, Utils.CODE_SELECT_UNIT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.tv_stored_bar: { // khi an cat du lieu
                try {
                    saveData();
                    updateFoodPresenter.updateFood(foodReceive);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.tv_delete_food: { //Khi an xoa du lieu
                try {
                    showDeleteFoodDialog(foodReceive);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.tv_stored_food: { //khi an cat du lieu
                try {
                    saveData();
                    updateFoodPresenter.updateFood(foodReceive);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.ibtn_back: { // khi an phim back
                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham xu ly du lieu khi nhan duoc result tu activity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == Utils.CODE_SELECT_UNIT) {
                if (resultCode == Activity.RESULT_OK) {
                    currentUnit = (Unit) data.getSerializableExtra(Utils.RESULT_NEW_UNIT_NAME);
                    tvUnitName.setText(currentUnit.getmUnitName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham luu gia tri cua mon an can cap nhat
     */
    public void saveData() {
        try {
            String foodName = edtFoodName.getText().toString();
            String foodPrice = edtFoodPrice.getText().toString();
            foodPrice = Common.nomarlizedPrice(foodPrice);
            int foodState;
            if (cbState.isChecked()) {
                foodState = Utils.STOP_SELL;
            } else {
                foodState = Utils.SELL;
            }
            foodReceive.setmFoodName(foodName);
            foodReceive.setmFoodPrice(Double.valueOf(foodPrice));
            foodReceive.setmColorBackground(colorCurrent);
            foodReceive.setmImgName(iconCurrent);
            foodReceive.setmItemState(foodState);
            foodReceive.setmUnitID(currentUnit.getmUnitID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : ham hien thi dialog xoa mon an
     *
     * @param food
     */
    public void showDeleteFoodDialog(final Food food) {
        try {
            deleteFooddialog = new Dialog(context);
            setViewDialog(deleteFooddialog);
            tvTitleUnitDialog.setText(context.getString(R.string.app_name_upper));
            tvCancelDialog.setText(context.getString(R.string.no));
            tvStoredDialog.setText(context.getString(R.string.yes));
            edtResultUnit.setText(String.format(context.getString(R.string.are_you_sure_to_delete_food), food.getmFoodName()));
            edtResultUnit.setTextSize(Utils.TEXT_SMALL);
            deleteFooddialog.show();

            ivDeleteDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        deleteFooddialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            tvCancelDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        deleteFooddialog.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            tvStoredDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        updateFoodPresenter.deleteFood(food);
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
     * target : Ham setView cho cac item ben trong dialog
     *
     * @param dialog
     */
    private void setViewDialog(Dialog dialog) {
        try {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_crud_unit);
            ivDeleteDialog = dialog.findViewById(R.id.iv_delete_dialog);
            edtResultUnit = dialog.findViewById(R.id.edt_result_unit);
            tvCancelDialog = dialog.findViewById(R.id.tv_cancel_dialog);
            tvStoredDialog = dialog.findViewById(R.id.tv_stored_dialog);
            tvTitleUnitDialog = dialog.findViewById(R.id.tv_title_unit_dialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.iv_choose_color, R.id.iv_choose_symbol})
    public void onViewChooseClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_choose_color:
                FragmentManager fmColor = getSupportFragmentManager();
                colorDialog = ChooseColorDialog.newInstance(colorCurrent, new IgetColorChoosen() {
                    @Override
                    public void getColorChoosen(String color) {
                        updateBackgroundColor(color);
                    }
                });
                colorDialog.show(fmColor, null);
                break;
            case R.id.iv_choose_symbol:
                FragmentManager fmIcon = getSupportFragmentManager();
                iconDialog = ChooseIconDialog.newInstance(iconCurrent, new IgetIconChoosen() {
                    @Override
                    public void getIconChoosen(String icon) {
                        updateBackgroundIcon(icon);
                    }
                });
                iconDialog.show(fmIcon, null);
                break;
        }
    }

    public void updateBackgroundColor(String color) {
        setBackGroundCircle(color);
        colorCurrent = color;
        colorDialog.dismiss();
    }

    public void updateBackgroundIcon(String iconName) {
        Common.setImageFromAsset(context,iconName,ivChooseSymbol);
        iconCurrent = iconName;
        iconDialog.dismiss();
    }
}
