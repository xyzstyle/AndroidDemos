package com.xyz.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText ageText;
    private EditText heightText;
    public static final String PREFERENCE_NAME = "SaveSetting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText) findViewById(R.id.edit_name);
        ageText = (EditText) findViewById(R.id.edit_age);
        heightText = (EditText) findViewById(R.id.edit_height);

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPreferences();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSharedPreferences();
    }

    private void loadSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                PREFERENCE_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "Tom");
        int age = sharedPreferences.getInt("Age", 20);
        float height = sharedPreferences.getFloat("Height", 180);
        nameText.setText(name);
        ageText.setText(String.valueOf(age));
        heightText.setText(String.valueOf(height));
    }

    private void saveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(
                PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Name", nameText.getText().toString());
        editor.putInt("Age", Integer.parseInt(ageText.getText().toString()));
        editor.putFloat("Height",
                Float.parseFloat(heightText.getText().toString()));
        editor.commit();
    }
}
