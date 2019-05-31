package com.example.cukcuklite.sell.menu.update;


import android.content.Context;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.utils.Utils;

/**
 * created by: cvcuong
 * date: 5/28/2019
 * target :Lop presenter cap nhat du lieu
 */
public class UpdateFoodPresenter implements UpdateFoodContract.Presenter {

    private UpdateFoodContract.View iView;
    private IUpdateFoodModel iUpdateFoodModel;
    private Context context;

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham khoi tao unit presenter
     * @param v
     * @param iUpdateFoodModel
     */
    public UpdateFoodPresenter(UpdateFoodContract.View v, IUpdateFoodModel iUpdateFoodModel ,Context c){
        try {
            this.iView = v;
            this.iUpdateFoodModel = iUpdateFoodModel;
            this.context = c;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham set view cho presenter
     * @param iView
     */
    public void setView(UpdateFoodContract.View iView) {
        try {
            this.iView = iView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham goi db lay ten unit
     * @param id
     */
    @Override
    public void getUnitNameByID(int id) {
        try {
            if (id > 0) {
                Unit u = iUpdateFoodModel.getUnitById(id);
                if (u.getmUnitID() == 0){
                    iView.getUnitNameSuccess("");
                }else{
                    iView.getUnitNameSuccess(u.getmUnitName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham goi db cap nhat thong tin mon an
     * @param food
     */
    @Override
    public void updateFood(Food food) {
        try {
            if (iUpdateFoodModel.updateFood(food) > 0){
                iView.updateFoodSuccess();
            }else{
                iView.updateFoodFail(Utils.ERROR_UPDATE_DATABASE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham goi db xoa mon an
     * @param food mon an can xoa
     */
    @Override
    public void deleteFood(Food food) {
        try {
            if (iUpdateFoodModel.deleteFood(food) > 0){
                iView.deleteFoodSuccess();
            }else{
                iView.deleteFoodFail(context.getString(R.string.delete_food_fail));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham lay unit theo id
     * @param id: id unit can lay trong db
     * @return: unit
     */
    public Unit getUnitByID(int id){
        try {
            return iUpdateFoodModel.getUnitById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Unit();
    }
}
