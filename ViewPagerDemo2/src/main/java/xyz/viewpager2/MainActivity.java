package xyz.viewpager2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ArrayList<View> pageViews;
    private ViewGroup buttonsLine;
    private Button button01;
    private Button button02;
    private Button button03;
    private Button[] buttons;
    private ListView lv01;
    private ListView lv02;
    private ListView lv03;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        // 每页de界面
        View page01 = inflater.inflate(R.layout.page1, null);
        View page02 = inflater.inflate(R.layout.page2, null);
        View page03 = inflater.inflate(R.layout.page3, null);
        pageViews.add(page01); // lee
        pageViews.add(page02); // lee
        pageViews.add(page03); // lee
        lv01 = (ListView) page01.findViewById(R.id.lv01);
        lv02 = (ListView) page02.findViewById(R.id.lv02);
        lv03 = (ListView) page03.findViewById(R.id.lv03);
        lv01.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        lv02.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        lv03.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));

        // 按钮栏
        buttons = new Button[pageViews.size()];
        buttonsLine = (ViewGroup) inflater
                .inflate(R.layout.activity_main, null);
        button01 = (Button) buttonsLine.findViewById(R.id.pre_one_button);
        button02 = (Button) buttonsLine.findViewById(R.id.pre_two_button);
        button03 = (Button) buttonsLine.findViewById(R.id.pre_three_button);
        buttons[0] = button01;
        buttons[1] = button02;
        buttons[2] = button03;
        button01.setOnClickListener(new GuideButtonClickListener(0));
        button02.setOnClickListener(new GuideButtonClickListener(1));
        button03.setOnClickListener(new GuideButtonClickListener(2));

        viewPager = (ViewPager) buttonsLine.findViewById(R.id.guidePages);
        setContentView(buttonsLine);

        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }
    class GuidePageAdapter extends PagerAdapter {

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
            // TODO Auto-generated method stub
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).removeView(pageViews.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            ((ViewPager) arg0).addView(pageViews.get(arg1));
            return pageViews.get(arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }
    }

    class GuidePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
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

    class GuideButtonClickListener implements OnClickListener {
        private int index = 0;

        public GuideButtonClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index, true);
        }
    }
}
