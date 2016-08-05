package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sneh.myapplication.Consommation;
import com.example.sneh.myapplication.Food;
import com.example.sneh.myapplication.R;

import Tabs.InProgress;
import Tabs.JobDone;

/**
 * Created by sneh on 16/11/15.
 */
public class FoodAdapter extends FragmentPagerAdapter {

    int cicle_id,building_id;
    final int PAGE_COUNT = 2;
    String tabTitles[];
    private Context con;

    public FoodAdapter(FragmentManager fm, Context context, int cicle_id, int building_id, String[] tabTitles) {
        super(fm);
        this.con=context;
        this.tabTitles = tabTitles;
        this.cicle_id = cicle_id;
        this.building_id = building_id;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0)                // if the position is 0 we are returning the First tab
        {
            Food tab1 = new Food();
            //tab1.getContext(con);
            tab1.setid(cicle_id, building_id);
            return tab1;
        }
        else                             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Consommation tab2 = new Consommation();
            //tab2.getContext(con);
            tab2.setid(cicle_id, building_id);
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
