package xyz.viewpager1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View> viewList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TextView page1 = new TextView(this);
        page1.setText("1111111111111111111111111");
        TextView page2 = new TextView(this);
        page2.setText("2222222222222222222222222");
        TextView page3 = new TextView(this);
        page3.setText("33333333333333333333333333");

        viewList.add(page1);
        viewList.add(page2);
        viewList.add(page3);
        MyPageAdapter myAdapter=new MyPageAdapter();
        viewPager.setAdapter(myAdapter);
    }
    //PagerAdapter是android-support-v4.jar中的类


    private class MyPageAdapter extends PagerAdapter{


        @Override
        public int getCount() {
            return viewList.size();
        }
//

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position) ;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

           container .removeView(viewList.get(position));
        }



        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }
    }
}
