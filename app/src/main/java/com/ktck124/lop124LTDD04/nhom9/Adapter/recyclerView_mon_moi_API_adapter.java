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

public class recyclerView_mon_moi_API_adapter extends RecyclerView.Adapter<recyclerView_mon_moi_API_adapter.MyViewHolder> {


    Context context;
    List<SanPhamAPIModel> list;
    SharedPreferences sharedPreferencesDonHang;

    public recyclerView_mon_moi_API_adapter(Context context, List<SanPhamAPIModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_home_page_mon_moi, parent, false);

        sharedPreferencesDonHang = context.getSharedPreferences("dataDonHangResponse", context.MODE_PRIVATE);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SanPhamAPIModel product = list.get(position);
//        Log.d("Product", "Product Title: " + product.getTitle());
        holder.title.setText(product.getTenSanPham());

        NumberFormat formatter = NumberFormat.getInstance(Locale.ITALY);
        String formattedPrice = formatter.format(product.getGiaTien());
        formattedPrice = formattedPrice + "vnđ";
        holder.price.setText(formattedPrice);

        String imageUrl = product.getImages();
        if (imageUrl.startsWith("http://")) {
            imageUrl = imageUrl.replace("http://", "https://");
        }

//        Glide.with(context)
//                .load(imageUrl)
//                .placeholder(R.drawable.icon_food2)
//                .error(R.drawable.icon_food1)
//                .into(holder.img);
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



        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_listview_sanpham);
        holder.itemView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        FrameLayout container;
        TextView title, price, btn_AddToCart_banChay_monMoi;
        ConstraintLayout img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title_product);
            price = itemView.findViewById(R.id.item_price_product);
            img = itemView.findViewById(R.id.item_image_product);
            container = itemView.findViewById(R.id.container_item_ban_chay_mon_moi);
            btn_AddToCart_banChay_monMoi = itemView.findViewById(R.id.btn_AddToCart_banChay_monMoi);
        }
    }



   
}

