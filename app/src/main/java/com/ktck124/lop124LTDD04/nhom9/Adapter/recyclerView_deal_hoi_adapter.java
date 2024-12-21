package com.ktck124.lop124LTDD04.nhom9.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.MainActivity;
import com.ktck124.lop124LTDD04.nhom9.Model.SanPhamModel;
import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class recyclerView_deal_hoi_adapter extends RecyclerView.Adapter<recyclerView_deal_hoi_adapter.MyViewHolder> {

    Context context;
    List<SanPhamModel> list;

    public recyclerView_deal_hoi_adapter(Context context, List<SanPhamModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view__item_deal_hoi_homepage, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SanPhamModel product = list.get(position);
        holder.title.setText(product.getTenSanPham());

        NumberFormat formatter = NumberFormat.getInstance(Locale.ITALY);
        String formattedPrice = formatter.format(product.getGiaTien()) + "vnđ";
        holder.price.setText(formattedPrice);

        Glide.with(context).load(product.getImages()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView container;
        TextView title, price;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_deal_hoi_item);
            price = itemView.findViewById(R.id.price_deal_hoi_item);
            img = itemView.findViewById(R.id.img_deal_hoi_item);
            container = itemView.findViewById(R.id.containter_item_deal_hoi_homepage);
        }
    }
}
