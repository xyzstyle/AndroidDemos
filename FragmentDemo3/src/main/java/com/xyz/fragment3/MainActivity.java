package com.xyz.fragment3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String[] array = { "text1,", "text2", "text3", "text4",
            "text5,", "text6", "text7", "text8" };

    private static final String TAG = "xyz";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public static class TitlesFragment extends ListFragment {

        boolean mDualPane;
        int mCurCheckPosition = 0;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "Fragment-->onCreate");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG,"Fragment-->onCreateView");
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG,"Fragment-->onPause");
        }


        @Override
        public void onStop() {
            super.onStop();

            Log.d(TAG,"Fragment-->onStop");
        }



        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d(TAG,"Fragment-->onAttach");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG,"Fragment-->onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG,"Fragment-->onResume");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG,"Fragment-->onDestroy");
        }



        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG,"Fragment-->onActivityCreted");
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, array));

            View detailsFrame = getActivity().findViewById(R.id.details);

            mDualPane = detailsFrame != null
                    && detailsFrame.getVisibility() == View.VISIBLE;

            if (savedInstanceState != null) {
                mCurCheckPosition = savedInstanceState.getInt("curChoice", 0); //从保存的状态中取出数据
            }

            if (mDualPane) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                showDetails(mCurCheckPosition);
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putInt("curChoice", mCurCheckPosition);//保存当前的下标
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            showDetails(position);
        }

        void showDetails(int index) {
            mCurCheckPosition = index;
            if (mDualPane) {
                getListView().setItemChecked(index, true);
                DetailsFragment details = (DetailsFragment) getFragmentManager()
                        .findFragmentById(R.id.details);
                if (details == null || details.getShownIndex() != index) {
                    details = DetailsFragment.newInstance(mCurCheckPosition);

                    //得到一个fragment 事务（类似sqlite的操作）
                    FragmentTransaction ft = getFragmentManager()
                            .beginTransaction();
                    ft.replace(R.id.details, details);//将得到的fragment 替换当前的viewGroup内容，add则不替换会依次累加
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//设置动画效果
                    ft.commit();//提交
                }
            } else {
                new AlertDialog.Builder(getActivity()).setTitle(
                        android.R.string.dialog_alert_title).setMessage(
                        array[index]).setPositiveButton(android.R.string.ok,
                        null).show();
            }
        }
    }

    /**
     * 作为界面的一部分，为fragment 提供一个layout
     * @author terry
     *
     */
    public static class DetailsFragment extends Fragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }
        public static DetailsFragment newInstance(int index) {
            DetailsFragment details = new DetailsFragment();
            Bundle args = new Bundle();
            args.putInt("index", index);
            details.setArguments(args);
            return details;
        }

        public int getShownIndex() {
            return getArguments().getInt("index", 0);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (container == null)
                return null;

            ScrollView scroller = new ScrollView(getActivity());
            TextView text = new TextView(getActivity());

            int padding = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
                            .getResources().getDisplayMetrics());
            text.setPadding(padding, padding, padding, padding);
            scroller.addView(text);

            text.setText(array[getShownIndex()]);
            return scroller;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add("Menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            Toast.makeText(getActivity(), "index is"+getShownIndex()+" && menu text is "+item.getTitle(), Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);
        }
    }
}
