package xyz.tablayout2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xyz on 2017/2/12.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};
    private Context context;
    private int[] icons;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        icons = new int[]{android.R.drawable.ic_dialog_email, android.R.drawable.ic_dialog_info, android.R.drawable.ic_dialog_map};

    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {



        CharSequence text = "★  " + tabTitles[position];
        //CharSequence text = tabTitles[position]+"  ★";
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        String specialString = "★";
        Pattern pattern = Pattern.compile(specialString);
        Matcher matcher = pattern.matcher(text);
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), icons[position], null);
        int x = drawable.getIntrinsicWidth();
        int y = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, x * 2 / 3, y * 2 / 3);

        if (matcher.find()) {
            builder.setSpan(
                    new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM), matcher.start(), matcher
                            .end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return builder;

    }





}