package com.example.cukcuklite.sell.menu.insert;

import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;

public interface IInsertModel {
    public int insertFood(Food food);
    public Unit getFirstUnit();
}
