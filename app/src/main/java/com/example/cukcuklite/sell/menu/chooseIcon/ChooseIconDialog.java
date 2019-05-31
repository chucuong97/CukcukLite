package com.example.cukcuklite.sell.menu.chooseIcon;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.cukcuklite.R;
import com.example.cukcuklite.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChooseIconDialog extends DialogFragment {

    @BindView(R.id.rc_choose_color)
    RecyclerView rcChooseColor;
    Unbinder unbinder;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private IconAdapter iconAdapter;
    private String iconCurrent;
    IgetIconChoosen igetIconChoosen;

    public static ChooseIconDialog newInstance(String iconCurrent, IgetIconChoosen igetIconChoosen) {
        ChooseIconDialog dialog = new ChooseIconDialog();
        dialog.iconCurrent  = iconCurrent;
        dialog.igetIconChoosen = igetIconChoosen;
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_icon, container);
        unbinder = ButterKnife.bind(this, view);
        registerCloseDialog();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iconAdapter = new IconAdapter(getContext(), iconCurrent, new OnClickChooseIcon() {
            @Override
            public void onClick(String icon) {
                igetIconChoosen.getIconChoosen(icon);
            }
        });
        rcChooseColor.setLayoutManager(new GridLayoutManager(getContext(), Utils.NUMBER_COLUMN_ICON));
        rcChooseColor.setAdapter(iconAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void registerCloseDialog(){
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setStyle(STYLE_NO_FRAME, android.R.style.Theme_Material_Light_NoActionBar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
