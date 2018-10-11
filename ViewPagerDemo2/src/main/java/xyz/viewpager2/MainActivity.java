package xyz.viewpager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private Button[] buttons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = getLayoutInflater();
        viewPager = (ViewPager) findViewById(R.id.guidePages);
        pageViews = new ArrayList<>();
        // 每页de界面
        View page01 = inflater.inflate(R.layout.page1, viewPager, false);
        View page02 = inflater.inflate(R.layout.page2, viewPager, false);
        View page03 = inflater.inflate(R.layout.page3, viewPager, false);
        pageViews.add(page01); // lee
        pageViews.add(page02); // lee
        pageViews.add(page03); // lee
        ListView lv01 = (ListView) page01.findViewById(R.id.lv01);
        ListView lv02 = (ListView) page02.findViewById(R.id.lv02);
        ListView lv03 = (ListView) page03.findViewById(R.id.lv03);
        lv01.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        lv02.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        lv03.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));

        // 按钮栏
        buttons = new Button[pageViews.size()];
        Button button01 = (Button) findViewById(R.id.pre_one_button);
        Button button02 = (Button) findViewById(R.id.pre_two_button);
        Button button03 = (Button) findViewById(R.id.pre_three_button);
        buttons[0] = button01;
        buttons[1] = button02;
        buttons[2] = button03;
        button01.setOnClickListener(new GuideButtonClickListener(0));
        button02.setOnClickListener(new GuideButtonClickListener(1));
        button03.setOnClickListener(new GuideButtonClickListener(2));
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }

    private List<String> getData() {

        List<String> data = new ArrayList<>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }

    private class GuidePageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pageViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageViews.get(position));
            return pageViews.get(position);
        }
    }

    private class GuidePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < buttons.length; i++) {
                buttons[arg0].setBackgroundResource(R.drawable.button_selected);
                if (arg0 != i) {
                    buttons[i]
                            .setBackgroundResource(R.drawable.button_unselected);
                }
            }
        }
    }

    private class GuideButtonClickListener implements OnClickListener {
        private int index = 0;

        GuideButtonClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index, true);
        }
    }
}
