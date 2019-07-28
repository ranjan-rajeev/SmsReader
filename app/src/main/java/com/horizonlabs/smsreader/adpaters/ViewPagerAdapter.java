package com.horizonlabs.smsreader.adpaters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.horizonlabs.smsreader.view.KeywordFragment;
import com.horizonlabs.smsreader.view.UserFragment;


/**
 * Created by Rajeev Ranjan -  ABPB on 27-07-2019.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new UserFragment();
            case 1:
                return new KeywordFragment();
            default:
                return new UserFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Numbers";
            case 1:
                return "Keywords";
            default:
                return "Numbers";
        }
    }
}