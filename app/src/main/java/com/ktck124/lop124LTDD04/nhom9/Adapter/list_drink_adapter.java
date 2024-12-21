package com.ktck124.lop124LTDD04.nhom9.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.foodtrack.Model.SanPhamModel;
import com.example.foodtrack.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class list_drink_adapter extends ArrayAdapter<SanPhamModel> {

    public list_drink_adapter(Context context, ArrayList<SanPhamModel> arrayListDrink) {
        super(context, R.layout.fragment_food_drink_item, arrayListDrink);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SanPhamModel drink = getItem(position); // Lấy đối tượng Drink ở vị trí hiện tại

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_food_drink_item, parent, false);
        }

        TextView title = view.findViewById(R.id.item_title_product);
        TextView price = view.findViewById(R.id.item_price_product);
        ConstraintLayout img = view.findViewById(R.id.item_image_product);
        TextView addToCartBtn = view.findViewById(R.id.btn_AddToCart_food_drink);

        if (drink != null) {

//            img.setImageResource(drink.getImages());
            Glide.with(getContext())
                    .asBitmap()
                    .load(drink.getImages())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }

                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img.setBackground(new BitmapDrawable(getContext().getResources(), resource));

                        }
                    });
            title.setText(drink.getTenSanPham());

            NumberFormat formatter = NumberFormat.getInstance(Locale.ITALY);
            String formattedPrice= formatter.format(drink.getGiaTien());
            formattedPrice = formattedPrice + "vnđ";
            price.setText(formattedPrice);
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Toast.makeText(getContext(), "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_LONG).show();
                CreatePopup(view);
            }
        });
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.scale_listview_sanpham);
        view.startAnimation(animation);
        return view;
    }
    private void CreatePopup(View view) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        View popupView = inflater.inflate(R.layout.popup_add_to_cart, null);

        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        view.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
            }
        });
        int delay = 1100;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, delay);

    }
}