package com.example.cukcuklite.sell.menu.caculate;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cukcuklite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CaculatorDialog extends DialogFragment {

    @BindView(R.id.iv_close_dialog)
    ImageView ivCloseDialog;
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.tv_key_c)
    TextView tvKeyC;
    @BindView(R.id.tv_key_decreament)
    TextView tvKeyDecreament;
    @BindView(R.id.tv_key_increament)
    TextView tvKeyIncreament;
    @BindView(R.id.tv_key_clear)
    ImageView tvKeyClear;
    @BindView(R.id.tv_key_7)
    TextView tvKey7;
    @BindView(R.id.tv_key_8)
    TextView tvKey8;
    @BindView(R.id.tv_key_9)
    TextView tvKey9;
    @BindView(R.id.tv_key_sub)
    TextView tvKeySub;
    @BindView(R.id.tv_key_4)
    TextView tvKey4;
    @BindView(R.id.tv_key_5)
    TextView tvKey5;
    @BindView(R.id.tv_key_6)
    TextView tvKey6;
    @BindView(R.id.tv_key_add)
    TextView tvKeyAdd;
    @BindView(R.id.tv_key_1)
    TextView tvKey1;
    @BindView(R.id.tv_key_2)
    TextView tvKey2;
    @BindView(R.id.tv_key_3)
    TextView tvKey3;
    @BindView(R.id.tv_key_abs)
    TextView tvKeyAbs;
    @BindView(R.id.tv_key_0)
    TextView tvKey0;
    @BindView(R.id.tv_key_000)
    TextView tvKey000;
    @BindView(R.id.tv_comma)
    TextView tvComma;
    @BindView(R.id.tv_done)
    TextView tvDone;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        View view = inflater.inflate(R.layout.dialog_caculator, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_close_dialog, R.id.edt_price, R.id.tv_key_c, R.id.tv_key_decreament, R.id.tv_key_increament, R.id.tv_key_clear, R.id.tv_key_7, R.id.tv_key_8, R.id.tv_key_9, R.id.tv_key_sub, R.id.tv_key_4, R.id.tv_key_5, R.id.tv_key_6, R.id.tv_key_add, R.id.tv_key_1, R.id.tv_key_2, R.id.tv_key_3, R.id.tv_key_abs, R.id.tv_key_0, R.id.tv_key_000, R.id.tv_comma, R.id.tv_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close_dialog:
                dismiss();
                break;
            case R.id.edt_price:
                break;
            case R.id.tv_key_c:
                edtPrice.setText("0");
                break;
            case R.id.tv_key_decreament:
                break;
            case R.id.tv_key_increament:
                break;
            case R.id.tv_key_clear:
                break;
            case R.id.tv_key_7:
                break;
            case R.id.tv_key_8:
                break;
            case R.id.tv_key_9:
                break;
            case R.id.tv_key_sub:
                break;
            case R.id.tv_key_4:
                break;
            case R.id.tv_key_5:
                break;
            case R.id.tv_key_6:
                break;
            case R.id.tv_key_add:
                break;
            case R.id.tv_key_1:
                break;
            case R.id.tv_key_2:
                break;
            case R.id.tv_key_3:
                break;
            case R.id.tv_key_abs:
                break;
            case R.id.tv_key_0:
                break;
            case R.id.tv_key_000:
                break;
            case R.id.tv_comma:
                break;
            case R.id.tv_done:
                break;
        }
    }
}
