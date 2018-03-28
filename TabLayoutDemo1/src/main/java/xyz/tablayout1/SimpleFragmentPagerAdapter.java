package xyz.tablayout1;

/**
 * My File Created by xyz on 2018/3/27.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by xyz on 2017/2/12.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};
    private Context context;
    private PageFragment[]  pages;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        pages = new PageFragment[3];
        for(int i=0;i<3;i++) {
            pages[i]=PageFragment.newInstance(i + 1);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
