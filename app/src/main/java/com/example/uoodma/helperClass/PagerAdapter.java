package com.example.uoodma.healperClass;

import com.example.uoodma.UploadDocFrags.UploadDocFragmentFive;
import com.example.uoodma.UploadDocFrags.UploadDocFragmentFour;
import com.example.uoodma.UploadDocFrags.UploadDocFragmentOne;
import com.example.uoodma.UploadDocFrags.UploadDocFragmentThree;
import com.example.uoodma.UploadDocFrags.UploadDocFragmentTwo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs)
    {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                UploadDocFragmentOne uploadDocFragmentOne = new UploadDocFragmentOne();
                return uploadDocFragmentOne;
            case 1:
                UploadDocFragmentTwo uploadDocFragmentTwo = new UploadDocFragmentTwo();
                return  uploadDocFragmentTwo;
            case 2:
                UploadDocFragmentThree uploadDocFragmentThree = new UploadDocFragmentThree();
                return  uploadDocFragmentThree;
            case 3:
                UploadDocFragmentFour uploadDocFragmentFour = new UploadDocFragmentFour();
                return  uploadDocFragmentFour;
            case 4:
                UploadDocFragmentFive uploadDocFragmentFive = new UploadDocFragmentFive();
                return  uploadDocFragmentFive;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}

