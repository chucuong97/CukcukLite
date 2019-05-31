package com.example.cukcuklite.sell.menu.showmenu;

import android.content.Context;

import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;

import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target : Lop model chua cac ham giao tiep voi db chuc nang hien thi danh sach mon an
 */
public class MenuModel implements IMenuModel {

    private Context context;
    private MyDatabaseCommand databaseCommand;

    public MenuModel(Context context){
        try {
            this.context = context;
            databaseCommand = MyDatabaseCommand.getInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Food> getListFood() {
        try{
            return databaseCommand.getListFood();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<Food>();
    }
}
