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
import com.ktck124.lop124LTDD04.nhom9.API.APIUSER;
import com.ktck124.lop124LTDD04.nhom9.Model.Users;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class listView_user_adapter extends ArrayAdapter<Users> {
    private Context context;
    private List<Users> listUsers;

    public listView_user_adapter(@NonNull Context context, List<Users> listUsers) {
        super(context, R.layout.item_user, listUsers);
        this.context = context;
        this.listUsers = listUsers;
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
        ImageView btn_xoa = (ImageView) view.findViewById(R.id.btn_xoa_item_user);

        if (users.isGioiTinh() == 1) {
            img_avatar.setImageResource(R.drawable.ic_male);
        } else {
            img_avatar.setImageResource(R.drawable.ic_fem);
        }

        tv_hoTen.setText(users.getHoTen());
        tv_maSv.setText(users.getMaSinhVien());

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeUserInModel(users);
                removeUserInDb(users);
            }
        });

        return view;

    }

    private void removeUserInDb(Users model) {
        APIUSER.APIUSER.DeleteSinhVien(model.getMaSinhVien()).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {

                } else {
                    // Xử lý lỗi nếu xóa không thành công
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // Xử lý lỗi khi không kết nối được với API
            }
        });
    }

    private void removeUserInModel(Users model) {
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getMaSinhVien().equals(model.getMaSinhVien())) {
                listUsers.remove(i);
                break;
            }
        }
        notifyDataSetChanged();

    }
}
