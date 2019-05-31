package com.example.cukcuklite.sell.menu.update;

import android.content.Context;

import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.utils.Utils;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target :Lop model cua chuc nang xoa va cap nhat mon an
 */
public class UpdateFoodModel implements IUpdateFoodModel {

    private Context context;
    private MyDatabaseCommand databaseCommand;

    public UpdateFoodModel(Context c){
        this.context = c;
        this.databaseCommand = MyDatabaseCommand.getInstance(c);
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target :Ham goi db lay unit theo id
     * @param id: id cua unit can lay
     * @return : unit lay duoc trong db
     */
    @Override
    public Unit getUnitById(int id) {
        try{
            return databaseCommand.getUnitByID(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Unit();
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : Ham goi db cap nhat mon an
     * @param food: mon an cap nhat
     * @return: so hang cap nhat neu thanh cong
     *          -1 neu that bai
     */
    @Override
    public int updateFood(Food food) {
        try{
            return databaseCommand.updateFood(food);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    @Override
    public int deleteFood(Food food) {
        try {
            return databaseCommand.deleteFood(food);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }
}
