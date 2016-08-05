package Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.util.Log;
//import android.R;
import com.example.sneh.myapplication.R;

import Tabs.InProgress;
import Tabs.JobDone;

/**
 * Created by sneh on 23/10/15.
 */
public class TaskAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private Context con;
    private String tabTitles[] = new String[PAGE_COUNT];

    public TaskAdapter(FragmentManager fm,Context context,String tab1,String tab2) {
        super(fm);
        con=context;
        tabTitles[0]=tab1;
        tabTitles[1]=tab2;
        Log.d("task_adapter_context",con.getResources().getString(R.string.in_progress));
        Log.d("h","kkiran....jobdone");

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
            InProgress tab1 = new InProgress();
            tab1.getContext(con);
            return tab1;
        }
        else                             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            JobDone tab2 = new JobDone();
            tab2.getContext(con);
            return tab2;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("hello","kkkkkkkkkkkkkkkkkkkk");
        // Generate title based on item position
        return tabTitles[position];
    }
}
