package com.ktck124.lop124LTDD04.nhom9.API;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ktck124.lop124LTDD04.nhom9.Model.API.NguoiDungAPIModel;
import com.ktck124.lop124LTDD04.nhom9.Model.API.SanPhamAPIModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {

  OkHttpClient okHttpClient = new OkHttpClient.Builder()
          .connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .writeTimeout(30, TimeUnit.SECONDS)
          .build();

  //    linkAPI root:
    public static String url ="https://foodtrack-wpjz.onrender.com/";

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:sss").create();
    APIService API_SERVICE = new Retrofit.Builder().baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    // hàm này dùng để login , gửi email và pass word: rồi
    @POST("user/login")
    Call<NguoiDungAPIModel> GetUserToLogin(@Body NguoiDungAPIModel userModel);




    // Home_Page
    // dùng để list sản phẩm deal hời: rồi
    @GET("food/bargain")
    Call<List<SanPhamAPIModel>> getListSanphamHomePage_DealHoi();
    // dùng để list sản phẩm banchay: rồi
    @GET("/food/bestseller")
    Call<List<SanPhamAPIModel>> getListSanphamHomePage_BanChay();
  // dùng để list sản phẩm monmoi: rồi
    @GET("food/new")
    Call<List<SanPhamAPIModel>> getListSanphamHomePage_MonMoi();

    // Explore
  // dùng để lấy các món ăn: rồi
    @GET("food/list")
    Call<List<SanPhamAPIModel>> getListMonAn_Explore();
    // lấy các thức uống; rồi
    @GET("food/list/drink")
    Call<List<SanPhamAPIModel>>getListDoUong_Explore();







  Retrofit retrofit = new Retrofit.Builder()
          .baseUrl("https://example.com/")
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create())
          .build();


//  // lấy chi tiết đơn hàng đang giao: roi
//  @GET("")
//  Call<List<ChiTietDonHangAPIModel>> GetChiTietDonHangDangGiao();
//
//  // lấy chi tiết đơn hàng đã hủy : ròi
//  @GET("")
//  Call<List<ChiTietDonHangAPIModel>> GetChiTietDonHangDaHuy();




  // thay đổi ảnh : roi
  @POST("user/update/avatar")
  @Multipart
  Call<NguoiDungAPIModel> ChangInfoUser(@Part("idNguoiDung") RequestBody idUser, @Part MultipartBody.Part image);

// cập nhật thông tin user: rồi
  @POST("user/update/info")
  Call<NguoiDungAPIModel> UpdateInfo(@Body NguoiDungAPIModel model);

  // Lấy thông tin user : roi
  @GET("user/info")
  Call<NguoiDungAPIModel> GetInfoUser(@Query("idNguoiDung")String idUser);

  // Tìm kiếm sản phẩm: rồi
  @GET("food/search")
  Call<List<SanPhamAPIModel>> GetSearchResult(@Query("query")String query);


}


