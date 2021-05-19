package com.hw.homework02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    Button buttonApply;
    RadioGroup radioGroup;
    private int themeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeId = getIntent().getExtras().getInt(MainActivity.PreferenceKey);
        doSetTheme(themeId);
        setContentView(R.layout.activity_settings);
        initView();
    }

    private void doSetTheme(int themeId) {
        switch (themeId) {
            case 0:
                setTheme(R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
                break;
            case 1:
                setTheme(R.style.Theme_Homework02);
                break;
            case 2:
                setTheme(R.style.MyTheme);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + themeId);
        }
    }

    private void initView() {
        radioGroup = findViewById(R.id.radioGroup);
        buttonApply = findViewById(R.id.buttonApply);
        initOnClickListeners();
        selectRadioButton();
    }

    private void selectRadioButton() {
        switch (themeId) {
            case 0:
                radioGroup.check(R.id.radioButtonDarkTheme);
                break;
            case 1:
                radioGroup.check(R.id.radioButtonLightTheme);
                break;
            case 2:
                radioGroup.check(R.id.radioButtonMyTheme);
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void initOnClickListeners() {
        buttonApply.setOnClickListener(v -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonDarkTheme:
                    themeId = 0;
                    break;
                case R.id.radioButtonLightTheme:
                    themeId = 1;
                    break;
                case R.id.radioButtonMyTheme:
                    themeId = 2;
                    break;
            }
            SharedPreferences pref = getSharedPreferences(MainActivity.PreferenceKey, MODE_PRIVATE);
            pref.edit().putInt(MainActivity.MyThemeKey, themeId).apply();
            Intent intentResult = new Intent();
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
