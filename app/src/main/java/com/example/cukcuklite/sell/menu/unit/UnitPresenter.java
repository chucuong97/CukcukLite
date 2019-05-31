package com.example.cukcuklite.sell.menu.unit;

import android.content.Context;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * created by: cvcuong
 * date: 5/23/2019
 * target : Lop presenter giao tiep giua man hinh unit va co so du lieu
 */
public class UnitPresenter implements UnitContract.Presenter {

    private UnitContract.View iView;
    private Context context;
    private IUnitModel iModel;

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham khoi tao unit presenter
     * @param v
     * @param iModel: interface chua cac phuong thuc giao tiep voi db.
     */
    public UnitPresenter(UnitContract.View v, IUnitModel iModel, Context c){
        try {
            this.iView = v;
            this.iModel = iModel;
            this.context = c;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setiView(UnitContract.View iView) {
        try {
            this.iView = iView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham goi den cau truy van database va tra ve ket qua cho view
     */
    @Override
    public void getListUnit() {
        try{
            ArrayList<Unit> listUnit = new ArrayList<>();
            listUnit = iModel.getListUnit();
            if (listUnit.size() > 0){
                iView.getListUnitSuccess(listUnit);
            }else{
                iView.getListUnitFail(Utils.ERROR_GET_DATABASE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return;
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham them 1 unit vao danh sach
     * @param unit: unit can them vao danh sach
     */
    @Override
    public void insertUnit(Unit unit) {
        try {
            if (unit != null) {
                if (unit.getmUnitName().equals("")){
                    iView.insertUnitFail("" + context.getString(R.string.unit_cannot_empty));
                }else{
                    if (iModel.checkUnitExist(unit.getmUnitName())){
                        iView.insertUnitFail(""+String.format(context.getString(R.string.unit_already_exist), unit.getmUnitName()));
                    }else {
                        int resultInsert = iModel.insertUnit(unit);
                        if (resultInsert > 0){
                            unit.setmUnitID(resultInsert);
                            iView.insertUnitSuccess(unit);
                        }else{
                            iView.insertUnitFail(Utils.ERROR_INSERT_DATABASE);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham cap nhat unit
     * @param newNameUnit: ten unit moi can cap nhat
     * @param unit: unit can cap nhat
     */
    @Override
    public void updateUnit(String newNameUnit, Unit unit) {
        try {
            if (newNameUnit != null && unit != null) {
                if (newNameUnit.equals("")) {
                    iView.updateUnitFail("" + context.getString(R.string.unit_cannot_empty));
                } else {
                    if (iModel.checkUnitExist(newNameUnit)){
                        iView.updateUnitFail(""+String.format(context.getString(R.string.unit_already_exist), newNameUnit));
                    }else{
                        if (iModel.updateUnit(newNameUnit,unit) > 0){
                            unit.setmUnitName(newNameUnit);
                            iView.updateUnitSuccess(unit);
                        }else{
                            iView.updateUnitFail(Utils.ERROR_UPDATE_DATABASE);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham xoa unit khoi danh sach
     * @param unit: unit can xoa
     */
    @Override
    public void deleteUnit(Unit unit) {
        try {
            if (unit != null) {
                if (iModel.getCountUnitID(unit) > 0){
                    iView.deleteUnitFail(""+ String.format(context.getString(R.string.cannot_delete_unit),unit.getmUnitName()));
                }else{
                    if (iModel.deleteUnit(unit) > 0){
                        iView.deleteUnitSuccess(unit);
                    }else{
                        iView.deleteUnitFail(context.getString(R.string.delete_unit_fail));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham cap nhat unit duoc chon
     * @param unit: unit duoc chon
     * @param listUnit: danh sach unit
     * @return danh sach unit da co unit duoc chon
     */
    public ArrayList<Unit> updateUnitSelected(Unit unit, ArrayList<Unit> listUnit){
        try {
            if (unit != null && listUnit.size() > 0) {
                for (Unit u : listUnit){
                    if (u.getmUnitName().equals(unit.getmUnitName())){
                        u.setmIsSelected(Utils.SELECTED);
                        break;
                    }
                }
                return listUnit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Unit>();
    }


}
