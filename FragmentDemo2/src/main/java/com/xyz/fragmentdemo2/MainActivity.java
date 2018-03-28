package com.xyz.fragmentdemo2;



import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "xyz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.myfragment, new MyFragment("abc"));
            //transaction.add(R.id.fragment, MyFragment.newInstance("have parameter"));
            transaction.commit();
        }
    }


    /**
     * 每个Fragment必须有一个空的构造函数，这样才可以在恢复Fragment活动的状态时被实例化。
     * 强烈建议Fragment的子类中不能有其它带参数的构造函数，因为这些构造函数在重新实例化时不会被调用;
     * 因为Fragment都是使用无参构造函数，所以Fragment的调用者使用setArguments（Bundle）提供参数给Fragment，
     * 稍后由Fragment的getArguments（）获取参数
     * 以下例子中，分别验证Fragment在有参构造函数和无参构造函数时的情况，
     * 在旋转屏幕时，Fragment重新构造，请观察重新构造时分别执行哪个构造函数
     * 以下强制使用@SuppressLint("ValidFragment")使得有参构造函数有效，以达到实验的效果
     *
     */
    @SuppressLint("ValidFragment")
    public static class MyFragment extends Fragment {
        private String mString = "have no parameter";

        public static MyFragment newInstance(String arg) {
            MyFragment myFragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putString("arg", arg);
            myFragment.setArguments(bundle);
            return myFragment;
        }

        public MyFragment() {
            Log.d(LOG_TAG, "MyFragment with no parameter constructor");
        }


        public MyFragment(String arg) {
            mString = "have parameter";
            Log.d(LOG_TAG, "MyFragment with  parameter constructor");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment, container, false);
            TextView showTextView = (TextView) view.findViewById(R.id.show_tv);
            //String  myArg= getArguments().getString("arg");
            //showTextView.setText(myArg);
            showTextView.setText(mString);
            return view;
        }
    }
}
