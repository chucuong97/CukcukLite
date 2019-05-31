package com.example.cukcuklite.sell.menu.unit;

import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target : interface chua cac giao thuc voi db
 */
public interface IUnitModel {

    public ArrayList<Unit> getListUnit();
    public int insertUnit(Unit unit);
    public int updateUnit(String newNameUnit, Unit unit);
    public int deleteUnit(Unit unit);

    public boolean checkUnitExist(String nameUnit);
    public int getCountUnitID(Unit unit);

}
