package com.ktck124.lop124LTDD04.nhom9;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//import com.example.foodtrack.Adapter.NotificationHelper;
//import com.example.foodtrack.Fragment.Home_Page;
//import com.example.foodtrack.Fragment.fragment_product_detail;
//import com.example.foodtrack.Fragment.product_detail_change_info;
//import com.example.foodtrack.R;
//import com.example.foodtrack.Fragment.checkout;
//import com.example.foodtrack.SocketManager;
//import com.example.foodtrack.databinding.ActivityMainBinding;
//import com.example.foodtrack.Fragment.favorite_fragment;
//import com.example.foodtrack.Fragment.food_fragment;
//import com.example.foodtrack.Fragment.fragment_profile;

import com.example.nhom9.lop224LTTD04.R;
import com.example.nhom9.lop224LTTD04.databinding.ActivityMainBinding;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    private SharedPreferences shareUserResponseLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String fragmentToLoad = intent.getStringExtra("fragmentToLoad");
        String ghiChu = intent.getStringExtra("ghiChu");
        Double tongTien = intent.getDoubleExtra("tongTien", 0);

        shareUserResponseLogin = getSharedPreferences("shareUserResponseLogin", MODE_PRIVATE);

        ReplaceFragment(new home_page());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                ReplaceFragment(new home_page());
            } else if (item.getItemId() == R.id.explore) {
                ReplaceFragment(new frag_user());
            }
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    public void ReplaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}