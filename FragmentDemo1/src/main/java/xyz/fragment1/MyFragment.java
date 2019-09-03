package xyz.fragment1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xyz on 2017/2/16.
 */

public class MyFragment extends Fragment {

    private static final String TAG = "xyz:MyFragment";
    private String mFragmentName = "no argument Fragment";

    public static Fragment NewInstance(String name) {

        Log.d(TAG, "NewInstance");
        Fragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        return fragment;
    }







    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (getArguments() != null) {
            mFragmentName = (String) getArguments().get("name");
        }
        Log.i(TAG, "onAttach: " + mFragmentName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: " + mFragmentName);
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: " + mFragmentName);
        View view = inflater.inflate(R.layout.fragment, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        if (mFragmentName != null) {
            tv.setText(mFragmentName);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated: " + mFragmentName);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated: " + mFragmentName);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart: " + mFragmentName);
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: " + mFragmentName);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause: " + mFragmentName);
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop: " + mFragmentName);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: " + mFragmentName);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: " + mFragmentName);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach: " + mFragmentName);
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: " + mFragmentName);
        super.onSaveInstanceState(outState);
        outState.putString("xyz", "zheng");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewStateRestored: " + mFragmentName);
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            String name = (String) savedInstanceState.get("xyz");
            Log.i(TAG, "onViewStateRestored: xyz" + name);
        }

    }
}
