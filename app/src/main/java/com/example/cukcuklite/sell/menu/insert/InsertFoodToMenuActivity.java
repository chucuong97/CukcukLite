package com.example.cukcuklite.sell.menu.insert;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.caculate.CaculatorDialog;
import com.example.cukcuklite.sell.menu.chooseColor.ChooseColorDialog;
import com.example.cukcuklite.sell.menu.chooseColor.IgetColorChoosen;
import com.example.cukcuklite.sell.menu.chooseIcon.ChooseIconDialog;
import com.example.cukcuklite.sell.menu.chooseIcon.IgetIconChoosen;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.sell.menu.unit.UnitSelectedActivity;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lop them mon an
 */
public class InsertFoodToMenuActivity extends AppCompatActivity implements InsertContract.View {

    @BindView(R.id.frame_circle_palette)
    FrameLayout frameCirclePalette;
    @BindView(R.id.frame_circle_symbol)
    FrameLayout frameCircleSymbol;
    @BindView(R.id.layout_unit)
    LinearLayout layoutUnit;
    @BindView(R.id.ibtn_back)
    ImageButton ibtnBack;
    @BindView(R.id.layout_name_food)
    LinearLayout layoutNameFood;
    @BindView(R.id.layout_price_food)
    LinearLayout layoutPriceFood;
    @BindView(R.id.tv_stored)
    TextView tvStored;
    @BindView(R.id.tv_unit_name)
    TextView tvUnitName;


    InsertPresenter insertPresenter;
    @BindView(R.id.btn_stored)
    TextView btnStored;
    @BindView(R.id.edt_food_name)
    EditText edtFoodName;
    @BindView(R.id.edt_food_price)
    EditText edtFoodPrice;
    @BindView(R.id.iv_choose_color)
    ImageView ivChooseColor;
    @BindView(R.id.iv_choose_symbol)
    ImageView ivChooseSymbol;
    private Unit unitReceive;
    private Context context;
    private ChooseColorDialog colorDialog;
    private ChooseIconDialog iconDialog;
    private String colorCurrent;
    private String iconCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food_to_menu);
        ButterKnife.bind(this);
        context = InsertFoodToMenuActivity.this;
        colorCurrent = Common.getColorFromPreferences(this);
        iconCurrent = Common.getImgFromPreferences(this);
        Common.setImageFromAsset(context,iconCurrent,ivChooseSymbol);
        setBackGroundCircle(colorCurrent);
        initPresenter();
    }


    /**
     * created by: cvcuong
     * date: 5/27/2019
     * target : Ham khoi tao presenter cho chuc nang them mon an
     */
    private void initPresenter() {
        try {
            insertPresenter = new InsertPresenter(this, new InsertModel(context), context);
            insertPresenter.setView(this);
            insertPresenter.getFirstUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham xu ly su kien khi an cac view
     *
     * @param view
     */
    @OnClick({R.id.ibtn_back, R.id.tv_stored, R.id.layout_unit, R.id.btn_stored, R.id.edt_food_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_back: { // Khi an nut back
                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.tv_stored: { // Khi an nut Cat
                try {
                    saveData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.layout_unit: { //Khi an cac item trong listView
                try {
                    Intent i = new Intent(InsertFoodToMenuActivity.this, UnitSelectedActivity.class);
                    if (unitReceive == null) {
                        unitReceive = new Unit();
                    }
                    i.putExtra(Utils.UNIT_NAME, unitReceive);
                    startActivityForResult(i, Utils.CODE_SELECT_UNIT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.btn_stored: { //Khi an button luu
                try {
                    saveData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.edt_food_price: { //Khi an bang tinh
                try {
                    FragmentManager fmCaculate = getSupportFragmentManager();
                    CaculatorDialog caculatorDialog = new CaculatorDialog();
                    caculatorDialog.show(fmCaculate, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * Ham test bo tron anh
     */
    public void setBackGroundCircle(String color) {
        try {
            Common.setBackGroundCircle(Color.parseColor(color),frameCirclePalette);
            Common.setBackGroundCircle(Color.parseColor(color),frameCircleSymbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly thong tin khi nhan duoc result tu activity con
     *
     * @param requestCode : Ma code cho ac con
     * @param resultCode: Ma ket qua tra ve
     * @param data:       intent chua du lieu
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == Utils.CODE_SELECT_UNIT) {
                if (resultCode == Activity.RESULT_OK) {
                    unitReceive = (Unit) data.getSerializableExtra(Utils.RESULT_NEW_UNIT_NAME);
                    tvUnitName.setText(unitReceive.getmUnitName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly cua View khi nhan dc thong bao tu presenter
     *
     * @param food
     */
    @Override
    public void insertSuccess(Food food) {
        try {
            Intent intentDatachange = new Intent(Utils.ACTION_NOTIFY_DATA_CHANGE);
            sendBroadcast(intentDatachange);
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
     * target : Ham xu ly cua View khi nhan duoc thong bao loi insert tu presenter
     *
     * @param error
     */
    @Override
    public void insertFail(String error) {
        try {
            Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xu ly cua View khi nhan dc thong bao lay du lieu thanh cong tu presenter
     *
     * @param unit: unit dau tien cua db
     */
    @Override
    public void getFirstUnitSuccess(Unit unit) {
        try {
            tvUnitName.setText(unit.getmUnitName());
            unitReceive = unit;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham tao doi tuong mon an de them vao db
     */
    public void saveData() {
        Food food = new Food();
        food.setmFoodName(edtFoodName.getText().toString());
        food.setmFoodPrice(Double.valueOf(edtFoodPrice.getText().toString()));
        food.setmUnitID(unitReceive.getmUnitID());
        food.setmColorBackground(colorCurrent);    //Dang set cung
        food.setmImgName(iconCurrent);
        food.setmItemState(Utils.SELL);
        insertPresenter.insertData(food);
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
