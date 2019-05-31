package com.example.cukcuklite.sell.menu.showmenu;
import com.example.cukcuklite.orderFood.Food;
import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/23/2019
 * target : Lop interface tong quat chua presenter va view cho chuc nang hien thi danh sach mon an
 */
public interface MenuContract {

    interface Presenter{
        void getListFood();
    };

    interface View{
        void getDataSuccess(ArrayList<Food> listFood);
        void getDataFail(String error);
    };
}
