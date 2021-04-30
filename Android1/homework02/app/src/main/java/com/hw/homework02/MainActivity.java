package com.hw.homework02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_STATE = "CALC";
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button_point, button_plus, button_minus, button_multiply, button_divide,
            button_result, button_clear;
    TextView textView;
    Calc calc = new Calc();
    boolean lastTouchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initView();
    }

    private void initView() {
        // Получить пользовательские элементы по идентификатору
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
    }

    private void addDigit(String digit) {
        if (calc.getCurrentOperation() == Calc.Operation.NONE) {
            if (calc.getFirstNumber() == null && !calc.pointed) {
                if (!digit.equals("0")) {
                    calc.bufferString = digit;
                } else return;
            } else {
                calc.bufferString += digit;
            }
            calc.setFirstNumber(Double.parseDouble(calc.bufferString));
        } else {
            if (calc.getSecondNumber() == null && !calc.pointed) {
                if (!digit.equals("0")) {
                    calc.bufferString = digit;
                } else return;
            } else {
                calc.bufferString += digit;
            }
            calc.setSecondNumber(Double.parseDouble(calc.bufferString));
        }
        textView.setText(calc.bufferString);
    }

    private void buttonsInitOnClickListeners() {
        button0.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("0");
            lastTouchResult = false;
        });
        button1.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("1");
            lastTouchResult = false;
        });
        button2.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("2");
            lastTouchResult = false;
        });
        button3.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("3");
            lastTouchResult = false;
        });
        button4.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("4");
            lastTouchResult = false;
        });
        button5.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("5");
            lastTouchResult = false;
        });
        button6.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("6");
            lastTouchResult = false;
        });
        button7.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("7");
            lastTouchResult = false;
        });
        button8.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("8");
            lastTouchResult = false;
        });
        button9.setOnClickListener(v -> {
            if (lastTouchResult) {
                calc.clear();
            }
            addDigit("9");
            lastTouchResult = false;
        });
        button_point.setOnClickListener(v -> {
            if (!calc.bufferString.contains(".")) {
                if (calc.getCurrentOperation() == Calc.Operation.NONE) {
                    if (calc.getFirstNumber() == null) {
                        calc.bufferString = "0.";
                    } else {
                        calc.bufferString += ".";
                    }
                } else {
                    if (calc.getSecondNumber() == null) {
                        calc.bufferString = "0.";
                    } else {
                        calc.bufferString += ".";
                    }
                }
                calc.pointed = true;
                textView.setText(calc.bufferString);
            }
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
                switch (calc.getCurrentOperation()) {
                    case NONE:
                        break;
                    case PLUS:
                        calc.setResult(calc.getFirstNumber() + calc.getSecondNumber());
                        break;
                    case MINUS:
                        calc.setResult(calc.getFirstNumber() - calc.getSecondNumber());
                        break;
                    case MULTIPLY:
                        calc.setResult(calc.getFirstNumber() * calc.getSecondNumber());
                        break;
                    case DIVIDE:
                        if (Math.abs(calc.getSecondNumber()) < 0.0000000000001) {
                            Toast.makeText(this, "Cannot be divided by zero", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            calc.setResult(calc.getFirstNumber() / calc.getSecondNumber());
                        }
                        break;
                }
                DecimalFormat df = new DecimalFormat("#.#########");
                calc.bufferString = df.format(calc.getResult());
                calc.setFirstNumber(calc.getResult());
                calc.setResult(null);
                textView.setText(calc.bufferString);
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