package com.ktck124.lop124LTDD04.nhom9.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.Model.Users;

import java.util.List;

public class listView_user_adapter extends ArrayAdapter<Users> {
    private Context context;

    public listView_user_adapter(@NonNull Context context, List<Users> listUsers) {
        super(context, R.layout.item_user, listUsers);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Users users = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        TextView tv_hoTen = (TextView) view.findViewById(R.id.tv_hoTen_item_user);
        TextView tv_maSv = (TextView) view.findViewById(R.id.tv_maSv_item_user);
        ImageView img_avatar = (ImageView) view.findViewById(R.id.img_avatar_item_user);

        if(users.isGioiTinh()){
            img_avatar.setImageResource(R.drawable.ic_male);
        }
        else{
            img_avatar.setImageResource(R.drawable.ic_fem);
        }

        tv_hoTen.setText(users.getHoTen());
        tv_maSv.setText(users.getMaSinhVien());

        return view;

    }
}
