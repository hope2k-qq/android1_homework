package com.example.android1_homework;

import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiplication;
    private Button btnDivision;
    private Button btnPercent;
    private Button btnPlusMinus;
    private Button btnClear;
    private Button btnComa;
    private Button btnEqual;
    private TextView textViewResult;

    private double result = 0;
    private int maxChars = 6;
    private int maxLength = 12;
    private int originalTextSize = 110;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnZero = findViewById(R.id.zero);
        btnOne = findViewById(R.id.one);
        btnTwo = findViewById(R.id.two);
        btnThree = findViewById(R.id.three);
        btnFour = findViewById(R.id.four);
        btnFive = findViewById(R.id.five);
        btnSix = findViewById(R.id.six);
        btnSeven = findViewById(R.id.seven);
        btnEight = findViewById(R.id.eight);
        btnNine = findViewById(R.id.nine);
        btnPlus = findViewById(R.id.plus);
        btnMinus = findViewById(R.id.minus);
        btnMultiplication = findViewById(R.id.multiplication);
        btnDivision = findViewById(R.id.division);
        btnPercent = findViewById(R.id.percent);
        btnPlusMinus = findViewById(R.id.plusMinus);
        btnClear = findViewById(R.id.clear);
        btnComa = findViewById(R.id.coma);
        btnEqual = findViewById(R.id.equal);
        textViewResult = findViewById(R.id.result);


        InputFilter[] filters = new InputFilter[] { new InputFilter.LengthFilter(maxLength) };
        textViewResult.setFilters(filters);

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("0");
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("1");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("2");
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("3");
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("4");
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("5");
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("6");
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("7");
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("8");
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("9");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("-");
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("*");
            }
        });

        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView("/");
            }
        });

        btnComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToTextView(".");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewResult.setText("0");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePercent();
            }
        });

        btnPlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusMinus();
            }
        });

        textViewResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int currentLength = textViewResult.length();

                if (currentLength >= maxChars) {
                    float targetTextSize = originalTextSize - 53f;
                    textViewResult.setTextSize(targetTextSize);
                } else {
                    textViewResult.setTextSize(originalTextSize);
                }
            }
        });
    }
    private void appendToTextView(String digit) {
        String currentResult = textViewResult.getText().toString();

        if (currentResult.equals("0") && !isOperator(digit)) {
            textViewResult.setText(digit);
        } else if (isOperator(digit) && !currentResult.equals("NaN")) {
            if (isOperator(currentResult.substring(currentResult.length() - 1))) {
                currentResult = currentResult.substring(0, currentResult.length() - 1);
            }
            currentResult += digit;
            textViewResult.setText(currentResult);
        }  else if (Double.isNaN(result)) {
            if (!isOperator(digit)) {
                result = 0;
                textViewResult.setText(digit);
            } else if(isOperator(digit)){
                result = 0;
                textViewResult.setText("0");
                appendToTextView(digit);
            }
        } else {
            currentResult += digit;
            textViewResult.setText(currentResult);
        }
    }

    private void calculateResult() {
        String expression = textViewResult.getText().toString();
        try {
            result = evaluateExpression(expression);
            textViewResult.setText(formatResult(result));
        } catch (Exception e) {
            result = Double.NaN;
            textViewResult.setText("NaN");
        }
    }

    private double evaluateExpression(String expression) {
        try {
            Expression e = new ExpressionBuilder(expression)
                    .build();
            return e.evaluate();
        } catch (ArithmeticException | IllegalArgumentException e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/")
                || text.equals(".");
    }

    private void calculatePercent() {
        String currentResult = textViewResult.getText().toString();
        try {
            double value = Double.parseDouble(currentResult);
            value /= 100;
            textViewResult.setText(Double.toString(value));
        } catch (NumberFormatException e) {
            result = Double.NaN;
            textViewResult.setText("NaN");
        }
    }

    public void plusMinus() {
        String currentText = textViewResult.getText().toString();
        try {
            if (!currentText.isEmpty()) {
                double number = Double.parseDouble(currentText);
                number *= -1;
                textViewResult.setText(formatResult(number));
            }
        } catch (NumberFormatException e) {
            result = Double.NaN;
            textViewResult.setText("NaN");
        }
    }

    private String formatResult(double value) {
        if (value == (int) value) {
            return Integer.toString((int) value);
        } else {
            return Double.toString(value);
        }
    }
}