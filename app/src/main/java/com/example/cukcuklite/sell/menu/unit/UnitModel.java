package com.example.cukcuklite.sell.menu.unit;

import android.content.Context;

import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

/**
 * created by: cvcuong
 * date: 5/29/2019
 * target :Lop model giao tiep voi db
 */
public class UnitModel implements IUnitModel {

    private Context context;
    private MyDatabaseCommand databaseCommand;

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : Ham khoi tao model
     * @param context: context cua activity goi den model
     */
    public UnitModel(Context context) {
        try {
            this.context = context;
            databaseCommand = MyDatabaseCommand.getInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : ham lay danh sach unit tu trong db
     * @return
     */
    @Override
    public ArrayList<Unit> getListUnit() {
        try {
            return databaseCommand.getListUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Unit>();
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target :Ham goi db them unit
     * @param unit: unit can them vao db
     * @return id cua unit neu thanh cong
     *         -1 neu that bai
     */
    @Override
    public int insertUnit(Unit unit) {
        try{
            return databaseCommand.insertUnit(unit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target :Ham goi db cap nhat unit
     * @param newNameUnit: ten unit moi can cap nhat
     * @param unit: unit can cap nhat
     * @return so luong hang cap nhat neu thanh cong
     *         -1 neu that bai
     */
    @Override
    public int updateUnit(String newNameUnit, Unit unit) {
        try{
            return databaseCommand.updateUnit(newNameUnit, unit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target : Ham goi db xoa unit khoi danh sach
     * @param unit: unit can xoa
     * @return so luong hang xoa neu thanh cong
     *         -1 neu that bai.
     */
    @Override
    public int deleteUnit(Unit unit) {
        try{
            return databaseCommand.deleteUnit(unit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Utils.INVALID_VALUE;
    }

    /**
     * created by: cvcuong
     * date: 5/29/2019
     * target :Ham goi db kiem tra Unit da co trong db hay chua
     * @param nameUnit ten unit can check
     * @return: true neu da ton tai
     *          false neu khong co trong db
     */
    @Override
    public boolean checkUnitExist(String nameUnit) {
        try{
            return databaseCommand.checkUnitExist(nameUnit);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getCountUnitID(Unit unit) {
        return databaseCommand.getCountUnitId(unit);
    }
}
