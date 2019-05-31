package com.example.cukcuklite.sell;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lop chua cac thuoc tinh ban
 */
public class Table {

    String mListFood;
    double mTotalMoney;
    int mTableNumber;
    int mTotalCustomer;

    public Table(String mListFood, double mTotalMoney, int mTableNumber, int mTotalCustomer) {
        this.mListFood = mListFood;
        this.mTotalMoney = mTotalMoney;
        this.mTableNumber = mTableNumber;
        this.mTotalCustomer = mTotalCustomer;
    }

    public String getmListFood() {
        return mListFood;
    }

    public void setmListFood(String mListFood) {
        this.mListFood = mListFood;
    }

    public double getmTotalMoney() {
        return mTotalMoney;
    }

    public void setmTotalMoney(double mTotalMoney) {
        this.mTotalMoney = mTotalMoney;
    }

    public int getmTableNumber() {
        return mTableNumber;
    }

    public void setmTableNumber(int mTableNumber) {
        this.mTableNumber = mTableNumber;
    }

    public int getmTotalCustomer() {
        return mTotalCustomer;
    }

    public void setmTotalCustomer(int mTotalCustomer) {
        this.mTotalCustomer = mTotalCustomer;
    }
}
