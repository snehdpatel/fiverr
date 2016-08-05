package Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Tabs.InProgress;
import Tabs.JobDone;
import Tabs.TodaysDone;
import Tabs.TodaysTasks;

/**
 * Created by sneh on 5/11/15.
 */
public class TodaysTaskAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Tasks", "Done" };
    Context context;
    public TodaysTaskAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
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
            TodaysTasks tab1 = new TodaysTasks();
            tab1.getContext(context);
            return tab1;
        }
        else                             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            TodaysDone tab2 = new TodaysDone();
            tab2.getContext(context);
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
