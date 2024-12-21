package com.ktck124.lop124LTDD04.nhom9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom9.lop224LTTD04.R;
import com.ktck124.lop124LTDD04.nhom9.API.APIUSER;
import com.ktck124.lop124LTDD04.nhom9.Model.Users;
import com.ktck124.lop124LTDD04.nhom9.Adapter.listView_user_adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class list_user extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ListView lv_users;
    TextView btn_them;
    List<Users> userList;

    public list_user() {
        // Required empty public constructor
    }

    public static list_user newInstance(String param1, String param2) {
        list_user fragment = new list_user();
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
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);

        lv_users = view.findViewById(R.id.lv_thanh_vien_list_user);
        btn_them = view.findViewById(R.id.btn_them_list_user);
        userList = new ArrayList<>();

        GetListUser();

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.ReplaceFragment(new frag_user());
                }
            }
        });

        lv_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Users selectedUser = userList.get(position);

                Fragment detailFragment = new frag_user();


                Bundle bundle = new Bundle();
                bundle.putSerializable("user_data", selectedUser); // Đưa đối tượng vào Bundle


                detailFragment.setArguments(bundle);


                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.ReplaceFragment(detailFragment);
                }

            }
        });

        return view;
    }

    private void GetListUser() {
        APIUSER.APIUSER.GetListUser().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userList = response.body();
                    listView_user_adapter adapter = new listView_user_adapter(getContext(), userList);
                    lv_users.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Failed to load users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
