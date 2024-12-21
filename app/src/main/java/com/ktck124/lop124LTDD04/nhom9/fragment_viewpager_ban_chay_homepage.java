package com.ktck124.lop124LTDD04.nhom9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nhom9.lop224LTTD04.R;
import com.google.gson.JsonSyntaxException;
import com.ktck124.lop124LTDD04.nhom9.API.APIService;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_ban_chay_API_adapter;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_ban_chay_adapter;
import com.ktck124.lop124LTDD04.nhom9.Model.API.SanPhamAPIModel;
import com.ktck124.lop124LTDD04.nhom9.Model.SanPhamModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_viewpager_ban_chay_homepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_viewpager_ban_chay_homepage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<SanPhamModel> listProduct;
    private RecyclerView rvBanChay;


    public fragment_viewpager_ban_chay_homepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_mon_ban_chay_homepage.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_viewpager_ban_chay_homepage newInstance(String param1, String param2) {
        fragment_viewpager_ban_chay_homepage fragment = new fragment_viewpager_ban_chay_homepage();
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
        View view = inflater.inflate(R.layout.fragment_viewpager_ban_chay_homepage, container, false);
        Mapping(view);
        return view;
    }

    private void Mapping(View view) {
        listProduct = new ArrayList<>();

        rvBanChay = view.findViewById(R.id.recyclerView_ban_chay_homepage);


//        InitializeData();
//        GridLayoutManager layoutManager
//                = new GridLayoutManager(requireContext(), 1);
//        rvBanChay.setLayoutManager(layoutManager);
//        recyclerView_ban_chay_adapter dealAdapter = new recyclerView_ban_chay_adapter(getContext(), listProduct);
//        rvBanChay.setAdapter(dealAdapter);



        // lấy dữ liệu từ api
        GetMonBanChay();

    }

    private void GetMonBanChay(){
        APIService.API_SERVICE.getListSanphamHomePage_BanChay().enqueue(new Callback<List<SanPhamAPIModel>>() {
            @Override
            public void onResponse(Call<List<SanPhamAPIModel>> call, Response<List<SanPhamAPIModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SanPhamAPIModel> listSanPham = response.body();
                    Log.d("API_SUCCESS", "Data size: " + listSanPham.size());
//
                    UpdateRecyclerViewAPI(listSanPham);
                } else {
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
        // Khởi tạo dữ liệu giả lập
    }

    private void UpdateRecyclerView(List<SanPhamModel> data) {
        // Cập nhật RecyclerView với dữ liệu (có thể từ API hoặc dữ liệu khởi tạo)
        GridLayoutManager layoutManager
                = new GridLayoutManager(requireContext(), 1);
        rvBanChay.setLayoutManager(layoutManager);
        recyclerView_ban_chay_adapter dealAdapter = new recyclerView_ban_chay_adapter(getContext(), data);
        rvBanChay.setAdapter(dealAdapter);
    }

    private void UpdateRecyclerViewAPI(List<SanPhamAPIModel> ApiData){
        GridLayoutManager layoutManager
                = new GridLayoutManager(requireContext(), 1);
        rvBanChay.setLayoutManager(layoutManager);
        recyclerView_ban_chay_API_adapter dealAdapter = new recyclerView_ban_chay_API_adapter(getContext(), ApiData);
        rvBanChay.setAdapter(dealAdapter);
    }

}