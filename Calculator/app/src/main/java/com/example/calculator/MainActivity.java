package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.calculator.EvaluateInput;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private Double ans = Double.MIN_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumber(View view) {
        if(isResultAvailable()) return;

        inputField = (EditText) findViewById(R.id.inputField);
        String number = inputField.getText().toString();

        switch(view.getId()) {
            case R.id.noZero:
                number += "0";
                break;
            case R.id.noOne:
                number += "1";
                break;
            case R.id.noTwo:
                number += "2";
                break;
            case R.id.noThree:
                number += "3";
                break;
            case R.id.noFour:
                number += "4";
                break;
            case R.id.noFive:
                number += "5";
                break;
            case R.id.noSix:
                number += "6";
                break;
            case R.id.noSeven:
                number += "7";
                break;
            case R.id.noEight:
                number += "8";
                break;
            case R.id.noNine:
                number += "9";
                break;
            case R.id.decimal:
                number += ".";
                break;
        }

        inputField.setText(number);
    }

    public void onClickOperation(View view) {
        if(isResultAvailable()) return;

        String operation = inputField.getText().toString();

        switch (view.getId()) {
            case R.id.add:
                operation += "+";
                break;
            case R.id.subtract:
                operation += "-";
                break;
            case R.id.multiply:
                operation += "*";
                break;
            case R.id.divide:
                operation += "/";
                break;
        }

        inputField.setText(operation);
    }

    public void onClickClear(View view) {
        inputField = (EditText) findViewById(R.id.inputField);
        inputField.setText("");
        ans = Double.MIN_VALUE;
    }

    public void onClickEquals(View view) {
        if(ans != Double.MIN_VALUE) return;
        inputField = (EditText) findViewById(R.id.inputField);

        if (TextUtils.isEmpty(inputField.getText().toString())) {
            Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        String inputText = inputField.getText().toString();
        if(inputText.endsWith("+") || inputText.endsWith("-")
                || inputText.endsWith("*") || inputText.endsWith("/")) {
            Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            EvaluateInput ins = new EvaluateInput();
            ans = ins.evaluate(inputText);
            inputField.setText(inputField.getText() + "\n= " + ans);
        }
        catch(NullPointerException e) {
            Toast.makeText(this, "Divide by zero not allowed.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isResultAvailable() {
        return (ans != Double.MIN_VALUE);
    }
}