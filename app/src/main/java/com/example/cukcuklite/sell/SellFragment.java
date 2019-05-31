package com.example.cukcuklite.sell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cukcuklite.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lớp Fragment bán hàng
 */
public class SellFragment extends Fragment {
    @BindView(R.id.rc_table)
    RecyclerView rcTable;
    Unbinder unbinder;
    ArrayList<Table> listTable;
    SellAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_sell, container, false);
            unbinder = ButterKnife.bind(this, view);
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataTest();
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Hàm tạo dữ liệu mẫu
     */
    private void initDataTest() {
        try {
            listTable = new ArrayList<>();
            Table t1 = new Table("Banh my1, Banhmy2",40000,1,1);

            Table t2 = new Table("Banh my1, Banhmy2",40000,2,2);
            Table t3 = new Table("Banh my1, Banhmy2",40000,3,3);
            Table t4 = new Table("Banh my1, Banhmy2",40000,4,4);

            listTable.add(t1);
            listTable.add(t2);
            listTable.add(t3);
            listTable.add(t4);

            myAdapter = new SellAdapter(listTable);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rcTable.setLayoutManager(layoutManager);
            rcTable.setAdapter(myAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
