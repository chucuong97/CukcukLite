package com.example.cukcuklite.orderFood;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.database.MyDatabaseCommand;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target : Lớp oder food
 */

public class OderFoodActivity extends AppCompatActivity {


    private ArrayList<Food> listFood;
    private OderFoodAdapter myAdapter;
    Context context;
    @BindView(R.id.imgbtn_back)
    ImageButton imgbtnBack;
    @BindView(R.id.tv_title_bar)
    TextView tvTitleBar;
    @BindView(R.id.tv_collect_money)
    TextView tvCollectMoney;
    @BindView(R.id.rc_food)
    RecyclerView rcFood;
    @BindView(R.id.bottom_bar)
    LinearLayout bottomBar;
    private MyDatabaseCommand databaseCommand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_oder_food);
            ButterKnife.bind(this);
            context = OderFoodActivity.this;
            initDataTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham tạo dữ liệu mẫu
     */
    public void initDataTest() {
        try {
            listFood = new ArrayList<>();
            databaseCommand = MyDatabaseCommand.getInstance(context);
            listFood = databaseCommand.getListFood();
            myAdapter = new OderFoodAdapter(context, listFood);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            rcFood.setLayoutManager(layoutManager);
            rcFood.setAdapter(myAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
