package xyz.tablayout3;


import android.graphics.drawable.Icon;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;
    private int[] icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icons = new int[]{android.R.drawable.ic_dialog_email, android.R.drawable.ic_dialog_info, android.R.drawable.ic_dialog_map};
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        icons = new int[]{android.R.drawable.ic_dialog_email, android.R.drawable.ic_dialog_info, android.R.drawable.ic_dialog_map};


        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            /**
             * 方法一是使用Tab的setIcon方法，图标的位置只能在文字的上方
             */
            // tabLayout.getTabAt(i).setIcon(icons[i]);
            /**
             * 方法二使用setCustomView可以根据需求生成布局（此应用中为tab_item）
             * 在布局中可以在任意位置出现icon
             */
            // 任意设定icon的位置。
            tabLayout.getTabAt(i).setCustomView(getTabView(i));


        }

    }

    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};

    private View getTabView(int position) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(tabTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(icons[position]);
        return view;
    }
}
