package com.hw.homework02;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_STATE = "CALC";
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button_point, button_plus, button_minus, button_multiply, button_divide,
            button_result, button_clear, buttonNightMode, buttonMyTheme;
    TextView textView;
    Calc calc = new Calc();
    boolean lastTouchResult;
    boolean nightMode;

    private static final String PreferenceKey = "THEME";
    private static final String MyThemeKey = "SET_MY_THEME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSetMyTheme()) {
            setTheme(R.style.MyTheme);
        } else {
            setTheme(R.style.Theme_Homework02);
        }
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initView();
    }

    private boolean getSetMyTheme() {
        SharedPreferences pref = getSharedPreferences(PreferenceKey, MODE_PRIVATE);
        return pref.getBoolean(MyThemeKey, false);
    }

    private void initView() {
        // Получить пользовательские элементы по идентификатору
        buttonNightMode = findViewById(R.id.buttonNightMode);
        buttonMyTheme = findViewById(R.id.buttonMyTheme);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button_point = findViewById(R.id.button_point);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_multiply = findViewById(R.id.button_multiply);
        button_divide = findViewById(R.id.button_divide);
        button_result = findViewById(R.id.button_result);
        button_clear = findViewById(R.id.button_clear);
        textView = findViewById(R.id.textView);
        buttonsInitOnClickListeners();
        lastTouchResult = false;
        nightMode = false;
    }

    private void buttonsInitOnClickListeners() {
        buttonNightMode.setOnClickListener(v -> {
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            nightMode = !nightMode;
            SharedPreferences pref = getSharedPreferences(PreferenceKey, MODE_PRIVATE);
            pref.edit().putBoolean(MyThemeKey, false).apply();
        });
        buttonMyTheme.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences(PreferenceKey, MODE_PRIVATE);
            pref.edit().putBoolean(MyThemeKey, true).apply();
            recreate();
        });
        button0.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("0"));
            lastTouchResult = false;
        });
        button1.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("1"));
            lastTouchResult = false;
        });
        button2.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("2"));
            lastTouchResult = false;
        });
        button3.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("3"));
            lastTouchResult = false;
        });
        button4.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("4"));
            lastTouchResult = false;
        });
        button5.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("5"));
            lastTouchResult = false;
        });
        button6.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("6"));
            lastTouchResult = false;
        });
        button7.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("7"));
            lastTouchResult = false;
        });
        button8.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("8"));
            lastTouchResult = false;
        });
        button9.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            textView.setText(calc.addDigit("9"));
            lastTouchResult = false;
        });
        button_point.setOnClickListener(v -> {
            textView.setText(calc.addPoint());
            lastTouchResult = false;
        });
        button_clear.setOnClickListener(v -> {
            calc.clear();
            textView.setText("0");
            lastTouchResult = false;
        });
        button_plus.setOnClickListener(v -> {
            calc.setCurrentOperation(Calc.Operation.PLUS);
            if (lastTouchResult) {
                calc.setSecondNumber(null);
            }
            lastTouchResult = false;
        });
        button_minus.setOnClickListener(v -> {
            calc.setCurrentOperation(Calc.Operation.MINUS);
            if (lastTouchResult) {
                calc.setSecondNumber(null);
            }
            lastTouchResult = false;
        });
        button_multiply.setOnClickListener(v -> {
            calc.setCurrentOperation(Calc.Operation.MULTIPLY);
            if (lastTouchResult) {
                calc.setSecondNumber(null);
            }
            lastTouchResult = false;
        });
        button_divide.setOnClickListener(v -> {
            calc.setCurrentOperation(Calc.Operation.DIVIDE);
            if (lastTouchResult) {
                calc.setSecondNumber(null);
            }
            lastTouchResult = false;
        });
        button_result.setOnClickListener(v -> {
            if (!lastTouchResult && (calc.getSecondNumber() != null)) {
                String calcResult = calc.calculate();
                if (!calcResult.equals("Error")) {
                    textView.setText(calcResult);
                } else {
                    Toast.makeText(this, R.string.errorString, Toast.LENGTH_SHORT).show();
                }
                lastTouchResult = true;
            }
        });
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_STATE, calc);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calc = savedInstanceState.getParcelable(KEY_STATE);
        textView.setText(calc.bufferString);
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