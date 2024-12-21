package com.ktck124.lop124LTDD04.nhom9.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ktck124.lop124LTDD04.nhom9.fragment_viewpager_ban_chay_homepage;
import com.ktck124.lop124LTDD04.nhom9.fragment_viewpager_mon_moi_homepage;

public class viewPager_mon_moi_ban_chay_home_page_adapter extends FragmentStateAdapter  {

    public viewPager_mon_moi_ban_chay_home_page_adapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        System.out.println("Fragment created for position: " + position);
        switch (position) {
            case 0:
                return new fragment_viewpager_ban_chay_homepage();
            case 1:
                return new fragment_viewpager_mon_moi_homepage();
            default:
                return new fragment_viewpager_ban_chay_homepage();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
