package ccomunities.alashka.com.ccommunities_dev.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ccomunities.alashka.com.ccommunities_dev.Fragment.AchievementFragment;
import ccomunities.alashka.com.ccommunities_dev.Fragment.PublicationFragment;

/**
 * Created by nicaela on 18/10/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        //return mFragmentList.get(position);
        switch (position) {
            case 0:

                return new PublicationFragment();
            case 1:
                return new AchievementFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mFragmentTitleList.get(position);
    }

}
