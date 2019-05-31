package com.example.cukcuklite.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.unit.Unit;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

/**
 * created by ChuVanCuong
 * created date 5/23/2019
 * target: Lop chua cac ham truy van database
 */
public class MyDatabaseCommand extends MyDatabaseOpenHelper {

    private static MyDatabaseCommand myDatabaseCommand;

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham khoi tao database command
     * @param context
     */
    public MyDatabaseCommand(Context context) {
        super(context);
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham lay the hien duy nhat cua database
     * @param context : Context cua activity gọi den database
     * @return the hien duy nhat cua database
     */
    public static MyDatabaseCommand getInstance(Context context){
        if (myDatabaseCommand == null){
            myDatabaseCommand = new MyDatabaseCommand(context);
        }
        return myDatabaseCommand;
    }




    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham lay danh sach don vi trong database
     * @return danh sach don vi
     */
    public ArrayList<Unit> getListUnit(){
        try {
            ArrayList<Unit> listUnit = new ArrayList<>();
            openDataBase();
            String sqlGetListUnit = "SELECT * FROM " + Utils.TABLE_UNIT;
            Cursor cursor = database.rawQuery(sqlGetListUnit,null);
            if (cursor.moveToFirst()) {
                do {
                    Unit unit = new Unit(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                    listUnit.add(0,unit);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
            return listUnit;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham them don vi vao database
     * @param unit: Doi tuong don vi
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public int insertUnit(Unit unit) {
        try {
            ContentValues values = new ContentValues();
            values.put(Utils.UNIT_NAME, unit.getmUnitName());
            openDataBase();
            long result = database.insert(Utils.TABLE_UNIT, null, values);
            database.close();
            return (int) result;
        } catch(SQLException e){
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham update don vi vao database
     * @param unit: Doi tuong don vi
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public int updateUnit(String nameUpdate,Unit unit) {
        try{
            ContentValues values = new ContentValues();
            values.put(Utils.UNIT_NAME,nameUpdate);
            openDataBase();
            long result = database.update(Utils.TABLE_UNIT,values,Utils.UNIT_ID +"=?",new String[] { String.valueOf(unit.getmUnitID())});
            database.close();
            return (int)result;
        }catch(Exception e){
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham delete don vi vao database
     * @param unit: Doi tuong don vi
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public int deleteUnit(Unit unit) {
        try{
            openDataBase();
            long result = database.delete(Utils.TABLE_UNIT, Utils.UNIT_ID + " = ?", new String[] { String.valueOf(unit.getmUnitID()) });
            database.close();
            return (int) result;
        }catch(Exception e){
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham kiem tra xem doi tuong da co trong csdl hay chua
     * @param nameUnit: ten doi tuong can them
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public boolean checkUnitExist(String nameUnit){
        try {
            ArrayList<Unit> listUnit = getListUnit();
            for (Unit u: listUnit){
                if (nameUnit.equals(u.getmUnitName())){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * created by: cvcuong
     * date: 5/27/2019
     * target : lay ten unit dau tien trong danh sach
     * @return
     */
    public Unit getFirstUnit(){
        try{
            return getListUnit().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Unit();
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham lay ten unit theo id
     * @param id
     * @return
     */
    public Unit getUnitByID(int id){
        try {
            for (Unit u : getListUnit()){
                if ( u.getmUnitID() == id){
                    return u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Hàm lấy danh sách item trong csdl
     * @return danh sach item lay tu database
     */
    public ArrayList<Food> getListFood(){
        try {
            ArrayList<Food> listFood = new ArrayList<>();
            openDataBase();
            String sqlGetListUser = "SELECT * FROM " + Utils.TABLE_ITEM;
            Cursor cursor = database.rawQuery(sqlGetListUser,null);
            if (cursor.moveToFirst()) {
                do {
                    Food food = new Food();
                    food.setmFoodId(cursor.getInt(0));
                    food.setmFoodName(cursor.getString(1));
                    food.setmFoodPrice(cursor.getDouble(2));
                    food.setmUnitID(cursor.getInt(3));
                    food.setmColorBackground(cursor.getString(4));
                    food.setmImgName(cursor.getString(5));
                    food.setmItemState(cursor.getInt(6));
                    listFood.add(0,food);
                } while (cursor.moveToNext());
            }
            cursor.close();
            database.close();
            return listFood;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham them item (Food)vao database
     * @param f: doi tuong item (Food) them vao database
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public int insertFood(Food f){
        try {
            ContentValues values = new ContentValues();
            values.put(Utils.ITEM_NAME,f.getmFoodName());
            values.put(Utils.ITEM_PRICE,f.getmFoodPrice());
            values.put(Utils.UNIT_ID,f.getmUnitID());
            values.put(Utils.ITEM_COLOR_BACKGROUND,f.getmColorBackground());
            values.put(Utils.ITEM_IMG_NAME,f.getmImgName());
            values.put(Utils.ITEM_STATE,f.getmItemState());
            openDataBase();
            long result = database.insert(Utils.TABLE_ITEM,null,values);
            database.close();
            return (int) result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    /**
     * created by ChuVanCuong
     * created date 5/23/2019
     * target: Ham update don vi vao database
     * @param food: Doi tuong mon an
     * @return true neu them thanh cong
     *         false neu that bai
     */
    public int updateFood(Food food) {
        try{
            ContentValues values = new ContentValues();
            values.put(Utils.ITEM_NAME,food.getmFoodName());
            values.put(Utils.ITEM_PRICE,food.getmFoodPrice());
            values.put(Utils.ITEM_COLOR_BACKGROUND,food.getmColorBackground());
            values.put(Utils.ITEM_STATE,food.getmItemState());
            values.put(Utils.ITEM_IMG_NAME,food.getmImgName());
            values.put(Utils.UNIT_ID,food.getmUnitID());
            openDataBase();
            long result = database.update(Utils.TABLE_ITEM,values,Utils.ITEM_ID +"=?",new String[] { String.valueOf(food.getmFoodId())});
            database.close();
            return (int) result;
        }catch(Exception e){
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham xoa mon an khoi database
     * @param food: mon an can xoa
     */
    public int deleteFood(Food food){
        try{
            openDataBase();
            long result = database.delete(Utils.TABLE_ITEM, Utils.ITEM_ID + " = ?", new String[] { String.valueOf(food.getmFoodId()) });
            database.close();
            return (int) result;
        }catch(Exception e){
            e.printStackTrace();
        }
        database.close();
        return Utils.INVALID_VALUE;
    }

    public int getCountUnitId(Unit unit){
        try {
            openDataBase();
            String sqlGetCountUnitID = "SELECT " +Utils.UNIT_ID + " FROM " + Utils.TABLE_ITEM + " WHERE " +Utils.UNIT_ID + "=" + unit.getmUnitID();
            Cursor cursor = database.rawQuery(sqlGetCountUnitID,null);
            int result = cursor.getCount();
            cursor.close();
            database.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }
}
