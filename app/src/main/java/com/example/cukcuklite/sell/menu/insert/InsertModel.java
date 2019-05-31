package com.example.cukcuklite.sell.menu.insert;

import android.content.Context;

import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.utils.Utils;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target : Lop model cua chuc nang them mon an
 */
public class InsertModel implements IInsertModel {

    private Context context;
    private MyDatabaseCommand databaseCommand;


    public InsertModel(Context context){
        this.context = context;
        databaseCommand = MyDatabaseCommand.getInstance(context);
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : Ham goi db them mon an
     * @param food: mon an can them
     * @return id cua mon an neu thanh cong
     *         -1 neu that bai
     */
    @Override
    public int insertFood(Food food) {
        try{
            return databaseCommand.insertFood(food);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target :Ham goi db lay ra unit dau tien de lam unit mac dinh
     * @return unit dau tien cua db
     */
    @Override
    public Unit getFirstUnit() {
        try{
            return databaseCommand.getFirstUnit();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Unit();
    }

}
