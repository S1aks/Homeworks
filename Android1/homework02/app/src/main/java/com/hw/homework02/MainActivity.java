package com.hw.homework02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_STATE = "CALC";
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonPoint;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonMultiply;
    Button buttonDivide;
    Button buttonResult;
    Button buttonClear;
    TextView textView;
    private Calc calc = new Calc();
    private boolean lastTouchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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
        buttonPoint = findViewById(R.id.buttonPoint);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonResult = findViewById(R.id.buttonResult);
        buttonClear = findViewById(R.id.buttonClear);
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
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("0");
                lastTouchResult = false;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("1");
                lastTouchResult = false;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("2");
                lastTouchResult = false;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("3");
                lastTouchResult = false;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("4");
                lastTouchResult = false;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("5");
                lastTouchResult = false;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("6");
                lastTouchResult = false;
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("7");
                lastTouchResult = false;
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("8");
                lastTouchResult = false;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastTouchResult) {
                    calc.clear();
                }
                addDigit("9");
                lastTouchResult = false;
            }
        });
        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.clear();
                textView.setText("0");
                lastTouchResult = false;
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setCurrentOperation(Calc.Operation.PLUS);
                if (lastTouchResult) {
                    calc.setSecondNumber(null);
                }
                lastTouchResult = false;
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setCurrentOperation(Calc.Operation.MINUS);
                if (lastTouchResult) {
                    calc.setSecondNumber(null);
                }
                lastTouchResult = false;
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setCurrentOperation(Calc.Operation.MULTIPLY);
                if (lastTouchResult) {
                    calc.setSecondNumber(null);
                }
                lastTouchResult = false;
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setCurrentOperation(Calc.Operation.DIVIDE);
                if (lastTouchResult) {
                    calc.setSecondNumber(null);
                }
                lastTouchResult = false;
            }
        });
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastTouchResult) {
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
                            calc.setResult((Double) (calc.getFirstNumber() / calc.getSecondNumber()));
                            break;
                    }
                    DecimalFormat df = new DecimalFormat("#.#########");
                    calc.bufferString = df.format(calc.getResult());
                    calc.setFirstNumber(calc.getResult());
                    calc.setResult(null);
                    textView.setText(calc.bufferString);
                    lastTouchResult = true;
                }
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