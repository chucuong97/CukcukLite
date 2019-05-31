package com.example.cukcuklite.sell.menu.showmenu;

import android.content.Context;

import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.showmenu.MenuContract;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/23/2019
 * target : Lop presenter trao doi du lieu voi database va view
 */
public class MenuPresenter implements MenuContract.Presenter {

    private MenuContract.View iView;
    private IMenuModel iMenuModel;

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham bind du lieu View
     * @param view
     */
    public void setView(MenuContract.View view) {
        this.iView = view;
    }

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham khoi tao presenter
     * @param iView: interface view truyen vao
     * @param iModel: model cua chuc nang hien thi danh sach mon an
     */
    public MenuPresenter(MenuContract.View iView, IMenuModel iModel) {
        this.iView = iView;
        this.iMenuModel = iModel;
    }

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham yeu cau database lay du lie
     * @return
     */
    @Override
    public void getListFood() {
        try{
            ArrayList<Food> listFood = new ArrayList<>();
            listFood = iMenuModel.getListFood();
            if (!listFood.isEmpty()){
                iView.getDataSuccess(listFood);
            }else{
                iView.getDataFail(Utils.ERROR_GET_DATABASE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return;
    }

}
