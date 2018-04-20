package com.example.michel.go4lunch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by michel on 31/01/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    //get count return 3 for the 3 pages
    @Override
    public int getCount() {
        return (3);
    }

    // implement page adapter
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //Page number 1
                return MapViewFragment.newInstance();
            case 1: //Page number 2
                return ListViewFragment.newInstance();
            case 2: //Page number 3
                return WorkmatesFragment.newInstance();
            default:
                return null;
        }
    }



    //set the name of the pages
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "Map View";
            case 1: //Page number 2
                return "List View";
            case 2: //Page number 3
                return "Workmates";
            default:
                return null;
        }
    }
}
