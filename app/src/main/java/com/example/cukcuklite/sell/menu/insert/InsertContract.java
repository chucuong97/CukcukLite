package com.example.cukcuklite.sell.menu.insert;

import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Interface tong quat chua presenter va view chuc nang them mon an
 */
public interface InsertContract {
    interface Presenter{
        void insertData(Food food);
        void getFirstUnit();
    };


    interface View{
        void insertSuccess(Food food);
        void insertFail(String error);
        void getFirstUnitSuccess(Unit unit);
    };
}
