package com.example.cukcuklite.sell.menu.unit;

import java.io.Serializable;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop chua thong tin chi tiet ve don vi tinh
 */
public class Unit implements Serializable {
    private int mUnitID;
    private String mUnitName;
    private int mIsSelected;

    public Unit(int mUnitID, String mUnitName, int mIsSelected) {
        this.mUnitID = mUnitID;
        this.mUnitName = mUnitName;
        this.mIsSelected = mIsSelected;
    }

    public Unit(){}

    public Unit(String mUnitName,int mIsSelected){
        this.mUnitName = mUnitName;
        this.mIsSelected = mIsSelected;
    }

    public int getmUnitID() {
        return mUnitID;
    }

    public void setmUnitID(int mUnitID) {
        this.mUnitID = mUnitID;
    }

    public String getmUnitName() {
        return mUnitName;
    }

    public void setmUnitName(String mUnitName) {
        this.mUnitName = mUnitName;
    }

    public int getmIsSelected() {
        return mIsSelected;
    }

    public void setmIsSelected(int mIsSelected) {
        this.mIsSelected = mIsSelected;
    }

}
