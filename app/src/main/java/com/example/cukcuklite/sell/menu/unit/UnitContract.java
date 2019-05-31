package com.example.cukcuklite.sell.menu.unit;

import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop interface tong quat chua presenter va view chuc nang hien thi, insert,update,delete don vi tinh
 */
public interface UnitContract {
    interface Presenter{
        void getListUnit();
        void insertUnit(Unit unit);
        void updateUnit(String newNameUnit,Unit unit);
        void deleteUnit(Unit unit);
    };

    interface View{
        void getListUnitSuccess(ArrayList<Unit> list);
        void getListUnitFail(String error);

        void insertUnitSuccess(Unit unit);
        void insertUnitFail(String error);

        void updateUnitSuccess(Unit unit);
        void updateUnitFail(String error);

        void deleteUnitSuccess(Unit unit);
        void deleteUnitFail(String error);
    };

}
