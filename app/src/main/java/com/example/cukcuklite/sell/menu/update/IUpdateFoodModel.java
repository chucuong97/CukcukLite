package com.example.cukcuklite.sell.menu.update;

import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target :Lop interface chua cac giao thuc giao tiep voi db
 */
public interface IUpdateFoodModel {

    public Unit getUnitById(int id);
    public int updateFood(Food food);
    public int deleteFood(Food food);

}
