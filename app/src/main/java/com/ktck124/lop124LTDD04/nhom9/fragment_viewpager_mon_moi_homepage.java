package com.ktck124.lop124LTDD04.nhom9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.API.APIService;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_mon_moi_API_adapter;
import com.ktck124.lop124LTDD04.nhom9.Adapter.recyclerView_mon_moi_adapter;
import com.ktck124.lop124LTDD04.nhom9.Model.API.SanPhamAPIModel;
import com.ktck124.lop124LTDD04.nhom9.Model.SanPhamModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_viewpager_mon_moi_homepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_viewpager_mon_moi_homepage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<SanPhamModel> listProduct;
    private RecyclerView rvMonMoi;

    public fragment_viewpager_mon_moi_homepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_viewpager_mon_moi_homepage.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_viewpager_mon_moi_homepage newInstance(String param1, String param2) {
        fragment_viewpager_mon_moi_homepage fragment = new fragment_viewpager_mon_moi_homepage();
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
        View view = inflater.inflate(R.layout.fragment_viewpager_mon_moi_homepage, container, false);
        Mapping(view);
        return view;
    }

    private void Mapping(View view) {
        listProduct = new ArrayList<>();

        rvMonMoi = view.findViewById(R.id.recyclerView_mon_moi_homepage);

        GetMonMoi();

    }
    private void GetMonMoi() {
        APIService.API_SERVICE.getListSanphamHomePage_MonMoi().enqueue(new Callback<List<SanPhamAPIModel>>() {
            @Override
            public void onResponse(Call<List<SanPhamAPIModel>> call, Response<List<SanPhamAPIModel>> response) {
                if(response.isSuccessful()&&response.body()!=null && !response.body().isEmpty()){
                    List<SanPhamAPIModel> listMonBanChay = response.body();
//                    UpdateRecyclerView(listMonBanChay);
                    UpdateRecyclerViewAPI(listMonBanChay);

                }else{
                    // UseFallbackData();
                }
            }

            @Override
            public void onFailure(Call<List<SanPhamAPIModel>> call, Throwable t) {
                //  UseFallbackData();
            }
        });
    }

    private void UseFallbackData() {
        // Khởi tạo dữ liệu giả lập
        listProduct = new ArrayList<>();
        // InitializeData(); // Hàm này sẽ thêm dữ liệu vào listProduct
        UpdateRecyclerView(listProduct);
    }

    private void UpdateRecyclerView(List<SanPhamModel> data) {
        // Cập nhật RecyclerView với dữ liệu (có thể từ API hoặc dữ liệu khởi tạo)
        GridLayoutManager layoutManager
                = new GridLayoutManager(requireContext(),1);
        rvMonMoi.setLayoutManager(layoutManager);
        recyclerView_mon_moi_adapter dealAdapter = new recyclerView_mon_moi_adapter(getContext(), listProduct);
        rvMonMoi.setAdapter(dealAdapter);
    }

    private void UpdateRecyclerViewAPI(List<SanPhamAPIModel> ApiData){
        GridLayoutManager layoutManager
                = new GridLayoutManager(requireContext(), 1);
        rvMonMoi.setLayoutManager(layoutManager);
        recyclerView_mon_moi_API_adapter dealAdapter = new recyclerView_mon_moi_API_adapter(getContext(), ApiData);
        rvMonMoi.setAdapter(dealAdapter);
    }
}