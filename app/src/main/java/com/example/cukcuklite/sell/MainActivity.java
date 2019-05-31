package com.example.cukcuklite.sell;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cukcuklite.R;
import com.example.cukcuklite.orderFood.OderFoodActivity;
import com.example.cukcuklite.sell.menu.insert.InsertFoodToMenuActivity;
import com.example.cukcuklite.sell.menu.showmenu.MenuFragment;
import com.example.cukcuklite.sell.menu.unit.Unit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by: cvcuong
 * date: 5/22/2019
 * target :Lop ban hang
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.imgbtn_menu)
    ImageButton imgbtnMenu;
    @BindView(R.id.imgbtn_add)
    ImageButton imgbtnAdd;
    @BindView(R.id.tv_title_sell_bar)
    TextView tvTitleSellBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sell_food);
            ButterKnife.bind(this);
            setupToolbar();
            registerListener();
            navView.setNavigationItemSelectedListener(this);
            displaySelectedScreen(R.id.nav_sell);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target :Thiet lap cac cai dat cho toolBar
     */
    public void setupToolbar() {
        try {
            imgbtnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        drawerLayout.openDrawer(GravityCompat.START);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            imgbtnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(MainActivity.this, OderFoodActivity.class);
                        startActivityForResult(intent, 1001);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target :Dang ky cac su kien click
     */
    public void registerListener() {
        try {
            drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(@NonNull View view, float v) {

                }

                @Override
                public void onDrawerOpened(@NonNull View view) {

                }

                @Override
                public void onDrawerClosed(@NonNull View view) {

                }

                @Override
                public void onDrawerStateChanged(int i) {

                }
            });

            navView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            menuItem.setChecked(true);
                            drawerLayout.closeDrawers();
                            return true;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        displaySelectedScreen(menuItem.getItemId());
        return true;
    }

    /**
     * created by: cvcuong
     * date: 5/22/2019
     * target : Ham hien thi man hinh chuc nang theo id
     *
     * @param itemId: id man hinh can hien thi
     */
    private void displaySelectedScreen(int itemId) {

        try {
            Fragment fragment = null;
            switch (itemId) {
                case R.id.nav_sell: {
                    fragment = new SellFragment();
                    imgbtnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(MainActivity.this, OderFoodActivity.class);
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    tvTitleSellBar.setText("Bán Hàng");
                    break;
                }
                case R.id.nav_menu: {
                    try {
                        fragment = new MenuFragment();
                        imgbtnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(MainActivity.this, InsertFoodToMenuActivity.class);
                                    startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        tvTitleSellBar.setText("Thực đơn");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case R.id.nav_report: {
                    try {
                        fragment = new ReportFragment();
                        tvTitleSellBar.setText("Báo cáo");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                default:
                    break;
            }
            //replacing the fragment
            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame_nav, fragment);
                ft.commit();
            }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
