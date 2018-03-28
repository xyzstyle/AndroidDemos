package xyz.viewpager1;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        List<View> viewPage = new ArrayList<View>();
        viewPage.add(page1);
        viewPage.add(page2);
        viewPage.add(page3);
        MyPageAdapter myAdapter=new MyPageAdapter(viewPage, this);
        viewPager.setAdapter(myAdapter);
    }
    //PagerAdapter是android-support-v4.jar中的类
    public class MyPageAdapter extends PagerAdapter {

        private List<View> mViewList;
        private Context mContext;

        public MyPageAdapter(List<View> mViewList, Context mContext) {
            super();
            this.mViewList = mViewList;
            this.mContext = mContext;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            // TODO Auto-generated method stub
            Log.d("CODE", "destroyItem");
            ((ViewPager)arg0).removeView(mViewList.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            Log.d("CODE", "finishUpdate");
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return mViewList.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            // TODO Auto-generated method stub
            Log.d("CODE", "instantiateItem");
            ((ViewPager)arg0).addView(mViewList.get(arg1));
            return mViewList.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            Log.d("CODE", "isViewFromObject");
            return arg0 == arg1;
        }





    }
}
