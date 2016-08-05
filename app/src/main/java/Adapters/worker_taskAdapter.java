package Adapters;

/**
 * Created by Himanshu on 11/13/2015.
 */import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import com.example.sneh.myapplication.R;

import Tabs.InProgress;
import Tabs.JobDone;
import Tabs.active_worker;
import Tabs.inactive_worker;

/**
 * Created by sneh on 23/10/15.
 */
public class worker_taskAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private Context con;
    private int cicle_id,building_id;
    String[] tabTitles;

    public worker_taskAdapter(FragmentManager fm,Context context,int cicle_id,int building_id, String[] tabTitles) {
        super(fm);
        con=context;
        this.tabTitles = tabTitles;
        this.cicle_id=cicle_id;
        this.building_id=building_id;
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
          active_worker aw=new active_worker();
            aw.get_ids(cicle_id,building_id);
            aw.getContext(con);
            return aw;

        }
        else                             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            inactive_worker tab2 = new inactive_worker();
            tab2.get_ids(cicle_id,building_id);
            tab2.getContext(con);
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
