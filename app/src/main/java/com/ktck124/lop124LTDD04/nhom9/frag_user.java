package com.ktck124.lop124LTDD04.nhom9;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.API.APIUSER;
import com.ktck124.lop124LTDD04.nhom9.Model.Users;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_user extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView maSinhVien, hoTen, lop, gioiTinh, ngaySinh, sdt, btnLuu;
    ImageView imgCalendar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Users user;
    public frag_user() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_user.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_user newInstance(String param1, String param2) {
        frag_user fragment = new frag_user();
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


        if (getArguments() != null) {
            user = (Users) getArguments().getSerializable("user_data");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_user, container, false);

        // Ánh xạ các TextView
        maSinhVien = view.findViewById(R.id.edt_maSinhVien);
        hoTen = view.findViewById(R.id.edt_hoTen);
        lop = view.findViewById(R.id.edt_lop);
        gioiTinh = view.findViewById(R.id.edt_gioiTinh);
        ngaySinh = view.findViewById(R.id.edt_ngaySinh);
        sdt = view.findViewById(R.id.edt_sdt);
        btnLuu = view.findViewById(R.id.luuBtn_editProfile);
        imgCalendar = view.findViewById(R.id.imgCalendar);

        TextView tv_masinhvien = view.findViewById(R.id.tv_masinhvien);
        TextView tv_name = view.findViewById(R.id.tv_name);

        // Hiển thị dữ liệu lên giao diện nếu user không null
        if (user != null) {
            maSinhVien.setText(user.getMaSinhVien());
            hoTen.setText(user.getHoTen());
            lop.setText(user.getLop());
            if(user.isGioiTinh()==1){
                gioiTinh.setText("Nam");
            }else{
                gioiTinh.setText("Nữ");
            }
            ngaySinh.setText(user.getNgaySinh());
            sdt.setText(user.getSdt());

            tv_masinhvien.setText(user.getMaSinhVien());
            tv_name.setText(user.getHoTen());
        }

        imgCalendar.setOnClickListener(v -> showDatePickerDialog());
        if(getArguments()==null){
            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String updatedMaSinhVien = maSinhVien.getText().toString();
                    String updatedHoTen = hoTen.getText().toString();
                    String updatedLop = lop.getText().toString();
                    String updatedGioiTinh = gioiTinh.getText().toString(); // "Nam" hoặc "Nữ"
                    String updatedNgaySinh = ngaySinh.getText().toString();
                    String updatedSdt = sdt.getText().toString();

                    Users createUser = new Users(updatedMaSinhVien, updatedHoTen, updatedLop, updatedGioiTinh.equals("Nam") ? 1 : 0, updatedNgaySinh, updatedSdt);

                    // Cập nhật đối tượng user
//                    user.setMaSinhVien(updatedMaSinhVien);
//                    user.setHoTen(updatedHoTen);
//                    user.setLop(updatedLop);
//                    user.setGioiTinh(updatedGioiTinh.equals("Nam") ? 1 : 0); // Chuyển đổi Nam/Nữ về giá trị số
//                    user.setNgaySinh(updatedNgaySinh);
//                    user.setSdt(updatedSdt);

                    AddUser(createUser);
                }
            });
        }else{
            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String updatedMaSinhVien = maSinhVien.getText().toString();
                    String updatedHoTen = hoTen.getText().toString();
                    String updatedLop = lop.getText().toString();
                    String updatedGioiTinh = gioiTinh.getText().toString(); // "Nam" hoặc "Nữ"
                    String updatedNgaySinh = ngaySinh.getText().toString();
                    String updatedSdt = sdt.getText().toString();

                    // Cập nhật đối tượng user
                    user.setMaSinhVien(updatedMaSinhVien);
                    user.setHoTen(updatedHoTen);
                    user.setLop(updatedLop);
                    user.setGioiTinh(updatedGioiTinh.equals("Nam") ? 1 : 0); // Chuyển đổi Nam/Nữ về giá trị số
                    user.setNgaySinh(updatedNgaySinh);
                    user.setSdt(updatedSdt);

                    UpdateUser(user);
                }
            });
        }
        return view;
    }

    public void AddUser(Users users){
        APIUSER.APIUSER.UpdateInfo(users).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(getContext(),"thêm thành công",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }

    public void UpdateUser(Users users){
        APIUSER.APIUSER.UpdateInfo(users).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Toast.makeText(getContext(),"thêm thành công",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Cập nhật ngày vào EditText
                    // String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    String formattedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;

                    ngaySinh.setText(formattedDate);
                },
                year,
                month,
                day
        );

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

}