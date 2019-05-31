package com.example.cukcuklite.sell.menu.showmenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;
import com.example.cukcuklite.orderFood.Food;
import com.example.cukcuklite.sell.menu.insert.MenuAdapter;
import com.example.cukcuklite.utils.Common;
import com.example.cukcuklite.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lop thuc don
 */
public class MenuFragment extends Fragment implements MenuContract.View {
    @BindView(R.id.rc_menu_item)
    RecyclerView rcMenuItem;
    Unbinder unbinder;
    private ArrayList<Food> listFood;
    private MenuAdapter myAdapter;
    private MenuPresenter menuPresenter;
    private BroadcastReceiver myBroadcast;

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham khoi tao View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        initBroadcast();
        return view;
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :Ham huy View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(myBroadcast);
        unbinder.unbind();
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target :
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuPresenter = new MenuPresenter(this,new MenuModel(getContext()));
        menuPresenter.setView(this);
        menuPresenter.getListFood();
    }

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham hien thi du lieu lay duoc tu database
     * @param listFood
     */
    @Override
    public void getDataSuccess(ArrayList<Food> listFood) {
        try {
            myAdapter = new MenuAdapter(listFood,getActivity());
            Common.sortListFood(listFood);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rcMenuItem.setLayoutManager(layoutManager);
            rcMenuItem.setAdapter(myAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/23/2019
     * target : Ham xu ly du lieu khi lay du lieu that bai.
     * @param error
     */
    @Override
    public void getDataFail(String error) {
        try {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/28/2019
     * target : Ham dang ky broadcast
     */
    public void initBroadcast(){
        try {
            myBroadcast = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(Utils.ACTION_NOTIFY_DATA_CHANGE)){
                        menuPresenter.getListFood();
                        myAdapter.notifyDataSetChanged();
                    }
                }
            };

            final IntentFilter filter = new IntentFilter(Utils.ACTION_NOTIFY_DATA_CHANGE);
            getActivity().registerReceiver(myBroadcast, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
