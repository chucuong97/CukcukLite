package com.example.cukcuklite.sell.menu.chooseColor;

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

public class ChooseColorDialog extends DialogFragment {

    @BindView(R.id.rc_choose_color)
    RecyclerView rcChooseColor;
    Unbinder unbinder;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private ColorAdapter colorAdapter;
    private String colorCurrent;
    private IgetColorChoosen igetColorChoosen;

    public static ChooseColorDialog newInstance(String colorCurrent, IgetColorChoosen igetColorChoosen) {
        ChooseColorDialog dialog = new ChooseColorDialog();
        dialog.colorCurrent = colorCurrent;
        dialog.igetColorChoosen = igetColorChoosen;
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_color, container);
        unbinder = ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        registerCloseDialog();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorAdapter = new ColorAdapter(getContext(),colorCurrent, new OnClickChooseColor() {
            @Override
            public void onClick(String color) {
                igetColorChoosen.getColorChoosen(color);
            }
        });
        rcChooseColor.setLayoutManager(new GridLayoutManager(getContext(), Utils.NUMBER_COLUMN_COLOR));
        rcChooseColor.setAdapter(colorAdapter);
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
