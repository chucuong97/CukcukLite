package com.example.cukcuklite.orderFood;

import java.io.Serializable;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop thong tin chi tiet ve doi tuong mon an
 */
public class Food implements Serializable {
    int mFoodId;
    String mFoodName;
    double mFoodPrice;
    int mUnitID;
    String mColorBackground;
    String mImgName;
    int mItemState;
    int mFoodAmount;

    public Food(int mFoodId, String mFoodName, double mFoodPrice, int mUnitID, String mColorBackground, String mImgName, int mItemState) {
        this.mFoodId = mFoodId;
        this.mFoodName = mFoodName;
        this.mFoodPrice = mFoodPrice;
        this.mUnitID = mUnitID;
        this.mColorBackground = mColorBackground;
        this.mImgName = mImgName;
        this.mItemState = mItemState;
    }
    public Food(){

    }


    public int getmFoodId() {
        return mFoodId;
    }

    public void setmFoodId(int mFoodId) {
        this.mFoodId = mFoodId;
    }

    public String getmFoodName() {
        return mFoodName;
    }

    public void setmFoodName(String mFoodName) {
        this.mFoodName = mFoodName;
    }

    public double getmFoodPrice() {
        return mFoodPrice;
    }

    public void setmFoodPrice(double mFoodPrice) {
        this.mFoodPrice = mFoodPrice;
    }

    public int getmUnitID() {
        return mUnitID;
    }

    public void setmUnitID(int mUnitID) {
        this.mUnitID = mUnitID;
    }

    public String getmColorBackground() {
        return mColorBackground;
    }

    public void setmColorBackground(String mColorBackground) {
        this.mColorBackground = mColorBackground;
    }

    public String getmImgName() {
        return mImgName;
    }

    public void setmImgName(String mImgName) {
        this.mImgName = mImgName;
    }

    public int getmItemState() {
        return mItemState;
    }

    public void setmItemState(int mItemState) {
        this.mItemState = mItemState;
    }

    public int getmFoodAmount() {
        return mFoodAmount;
    }

    public void setmFoodAmount(int mFoodAmount) {
        this.mFoodAmount = mFoodAmount;
    }
}
