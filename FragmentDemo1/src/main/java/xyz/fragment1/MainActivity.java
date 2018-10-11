package xyz.fragment1;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xyz:AppCompatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: before contContentView");
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: after contContentView");
        if (savedInstanceState == null) {
            Fragment fragment = MyFragment.NewInstance("fragment1");
            Log.i(TAG, "onCreate: begin to transaction");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.frame, fragment, "my");
            transaction.commit();
        }

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:replace ");
                Fragment fragment = MyFragment.NewInstance("fragment2");
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

            }
        });
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: jump");
                Fragment fragment = MyFragment.NewInstance("fragment3");

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack("aa");
                transaction.replace(R.id.frame, fragment).commit();


            }
        });
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");//注意这里log信息必须在super之前，才能真正体现生命周期的顺序
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState: ");
        super.onRestoreInstanceState(savedInstanceState);
    }
}
