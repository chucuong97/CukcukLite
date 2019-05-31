package com.example.cukcuklite.sell.menu.insert;

import android.content.Context;
import android.speech.tts.UtteranceProgressListener;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.utils.Utils;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop presenter trao doi du lieu voi database va view chuc nang them mon an
 */
public class InsertPresenter implements InsertContract.Presenter {

    private InsertContract.View iView;
    private IInsertModel iInsertModel;
    private Context context;

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham set view
     * @param iView: View cua activity goi den presenter
     */
    public void setView(InsertContract.View iView) {
        this.iView = iView;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham khoi tao presenter
     * @param v: View cua activity goi den presenter
     * @param iInsertModel: interface
     */
    public InsertPresenter(InsertContract.View v, IInsertModel iInsertModel, Context context){
        try {
            this.iView = v;
            this.iInsertModel = iInsertModel;
            this.context = context;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: ham goi den truy van them mon an vao database
     * @param food
     */
    @Override
    public void insertData(Food food) {
        try {
            if (food != null) {
                if (food.getmFoodName().equals("")){
                    iView.insertFail(context.getString(R.string.food_name_cannot_empty));
                }else{
                    if (iInsertModel.insertFood(food) > 0){
                        iView.insertSuccess(food);
                    }else {
                        iView.insertFail(Utils.ERROR_INSERT_DATABASE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham goi db lay unit dau tien de lam gia tri mac dinh cho truong unit name
     */
    @Override
    public void getFirstUnit() {
        try {
            Unit unit = iInsertModel.getFirstUnit();
            if (unit.getmUnitID() > 0){
                iView.getFirstUnitSuccess(unit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
