package com.ktck124.lop124LTDD04.nhom9;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import  com.ktck124.lop124LTDD04.nhom9.API.APIService;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_deal_hoi_API_adapter;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_deal_hoi_adapter;
import  com.ktck124.lop124LTDD04.nhom9.Adapter.viewPager_mon_moi_ban_chay_home_page_adapter;
import com.ktck124.lop124LTDD04.nhom9.Model.API.SanPhamAPIModel;
import  com.ktck124.lop124LTDD04.nhom9.Model.SanPhamModel;
import com.example.nhom9.lop224LTTD04.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home_page extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Handler handler = new Handler();
    private Runnable debounceRunnable;

    private TextView btn_DoUong_homepage, btn_DoAn_homepage;
    private ImageView chatIcon, imageViewRotate1, imageViewRotate2, imageViewRotate_DealHoi;
    private EditText edt_search;

    private List<SanPhamModel> listProduct;
    private RecyclerView rvDealHoi;

    private TabLayout tlMonMoiBanChay;
    private ViewPager2 vpMonMoiBanChay;


    public home_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Page.
     */
    // TODO: Rename and change types and number of parameters
    public static home_page newInstance(String param1, String param2) {
        home_page fragment = new home_page();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        Mapping(view);
        return view;

    }

    private void Mapping(View view) {
        btn_DoUong_homepage = view.findViewById(R.id.btn_DoUong_homepage);
        btn_DoAn_homepage = view.findViewById(R.id.btn_DoAn_homepage);
        chatIcon = view.findViewById(R.id.chatIcon);
        rvDealHoi = view.findViewById(R.id.recyclerView_deal_hoi_home_page);
        imageViewRotate1 = view.findViewById(R.id.imageViewRotate1);
        imageViewRotate2 = view.findViewById(R.id.imageViewRotate2);
        imageViewRotate_DealHoi = view.findViewById(R.id.imageViewRotate_DealHoi);

        Animation anim_rotate = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);
        Animation anim_rotate_dealhoi = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate_dealhoi);

        imageViewRotate_DealHoi.startAnimation(anim_rotate_dealhoi);
        imageViewRotate1.startAnimation(anim_rotate);
        imageViewRotate2.startAnimation(anim_rotate);

        edt_search = (EditText) view.findViewById(R.id.edt_search_home_page);


        listProduct = new ArrayList<>();

        GetDealHoi();

        tlMonMoiBanChay = view.findViewById(R.id.tabLayout_banChay_monMoi_home_page);
        vpMonMoiBanChay = view.findViewById(R.id.view_pager_mon_moi_ban_chay_home_page);
        viewPager_mon_moi_ban_chay_home_page_adapter adapter = new viewPager_mon_moi_ban_chay_home_page_adapter(this);
        vpMonMoiBanChay.setAdapter(adapter);


        new TabLayoutMediator(tlMonMoiBanChay, vpMonMoiBanChay,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Món bán Chạy");
                            break;
                        case 1:
                            tab.setText("Món Mới");
                            break;
                    }
                }).attach();
    }

    private void GetDealHoi() {
        APIService.API_SERVICE.getListSanphamHomePage_DealHoi().enqueue(new Callback<List<SanPhamAPIModel>>() {
            @Override
            public void onResponse(Call<List<SanPhamAPIModel>> call, Response<List<SanPhamAPIModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SanPhamAPIModel> listSanPhamDeaHoi = response.body();
                    Log.d("API_SUCCESS", "Data size: " + listSanPhamDeaHoi.size());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    rvDealHoi.setLayoutManager(layoutManager);
                    recyclerView_deal_hoi_API_adapter dealAdapter = new recyclerView_deal_hoi_API_adapter(getContext(), listSanPhamDeaHoi);
                    rvDealHoi.setAdapter(dealAdapter);
//                    Log.d("Text.....", listSanPhamDeaHoi.get(0).getImages());

                } else {
                    UseFallbackData();
                    Log.e("API_ERROR", "Response not successful: " + response.code());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("API_ERROR", "Error body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }


            @Override
            public void onFailure(Call<List<SanPhamAPIModel>> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
                if (t instanceof JsonSyntaxException) {
                    JsonSyntaxException jsonError = (JsonSyntaxException) t;
                    Log.e("API_ERROR", "JSON Error: " + jsonError.getCause());
                }
                t.printStackTrace();
                Toast.makeText(getContext(), "Lỗi dữ liệu: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UseFallbackData() {

    }

    private void UpdateRecyclerView(List<SanPhamModel> data) {
        // Cập nhật RecyclerView với dữ liệu (có thể từ API hoặc dữ liệu khởi tạo) rút gọn code cho dễ nhìn
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rvDealHoi.setLayoutManager(layoutManager);
        recyclerView_deal_hoi_adapter dealAdapter = new recyclerView_deal_hoi_adapter(getContext(), data);
        rvDealHoi.setAdapter(dealAdapter);
    }


}