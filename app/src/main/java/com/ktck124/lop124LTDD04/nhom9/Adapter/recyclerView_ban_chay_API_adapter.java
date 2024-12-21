package com.ktck124.lop124LTDD04.nhom9.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.Model.API.SanPhamAPIModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recyclerView_ban_chay_API_adapter extends RecyclerView.Adapter<recyclerView_ban_chay_API_adapter.MyViewHolder> {


    Context context;
    List<SanPhamAPIModel> list;
    SharedPreferences sharedPreferencesDonHang ;


    public recyclerView_ban_chay_API_adapter(Context context, List<SanPhamAPIModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_home_page_ban_chay, parent, false);
        sharedPreferencesDonHang = context.getSharedPreferences("dataDonHangResponse", context.MODE_PRIVATE);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamAPIModel product = list.get(position);

        holder.title.setText(product.getTenSanPham());
        NumberFormat formatter = NumberFormat.getInstance(Locale.ITALY);
        String formattedPrice = formatter.format(product.getGiaTien());
        formattedPrice = formattedPrice + "vnđ";
        holder.price.setText(formattedPrice);
        holder.soLuongDaBan.setText(String.valueOf(product.getSoLuongDaBan()));

        String imageUrl = product.getImages();
        if (imageUrl.startsWith("http://")) {
            imageUrl = imageUrl.replace("http://", "https://");
        }
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        holder.img.setBackground(new BitmapDrawable(context.getResources(), resource));
                    }
                });

        // Khi click vào item, chuyển sang fragment chi tiết sản phẩm

//        holder.btn_AddToFavorite_banChay_monMoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChiTietDonHangAPIModel ctdh = new ChiTietDonHangAPIModel();
//                ctdh.setIdSanPham(product.getIdSanPham());
//                ctdh.setSoLuongDat(5);
//                String idDonHang = sharedPreferencesDonHang.getString("idDonHang", "");
//                if (idDonHang != null) {
//                    ctdh.setIdDonHang(idDonHang);
//                }
//
//                SharedPreferences sharedPreferences = context.getSharedPreferences("shareUserResponseLogin", Context.MODE_PRIVATE);
//                String idUser = sharedPreferences.getString("idUser", "-1");
//                if (!idUser.equals("-1")) {
//                    ctdh.setIdUser(idUser);
//                    PostSanPhamToGioHang(ctdh);
//                } else {
//                    Toast.makeText(context, "Không tìm thấy ID người dùng", Toast.LENGTH_SHORT).show();
//                }
//
//                CreatePopup(view);
//            }
//        });

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_listview_sanpham);
        holder.itemView.startAnimation(animation);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        FrameLayout container;
        TextView title, price, btn_AddToFavorite_banChay_monMoi, description, soLuongDaBan;
        ConstraintLayout img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title_product);
            price = itemView.findViewById(R.id.item_price_product);
            img = itemView.findViewById(R.id.item_image_product);
            description = itemView.findViewById(R.id.item_description_product);
            soLuongDaBan = itemView.findViewById(R.id.tv_luot_ban_home_page_ban_chay);
            container = itemView.findViewById(R.id.container_item_ban_chay_mon_moi);
            btn_AddToFavorite_banChay_monMoi = itemView.findViewById(R.id.btn_add_to_yeuThich_banChay);
        }
    }




}

