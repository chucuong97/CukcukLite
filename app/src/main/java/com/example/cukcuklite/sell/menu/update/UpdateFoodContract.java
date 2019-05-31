package com.example.cukcuklite.sell.menu.update;

import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;

/**
 * created by: cvcuong
 * date: 5/28/2019
 * target: interface chung chua View va presenter cho chuc nang cap nhat va xoa mon an
 */
public interface UpdateFoodContract {
    public interface Presenter{
        void getUnitNameByID(int id);
        void updateFood(Food food);
        void deleteFood(Food food);
    }

    public interface View{
        void getUnitNameSuccess(String nameUnit);
        void updateFoodSuccess();
        void updateFoodFail(String error);

        void deleteFoodSuccess();
        void deleteFoodFail(String error);
    }
}
