package com.example.calculator;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.Stack;

public class EvaluateInput extends AppCompatActivity {

    public Double evaluate(String string) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] individualValues = string.toCharArray();

        for(int i=0; i<individualValues.length; i++) {
            char ch = individualValues[i];
            if(Character.isDigit(ch) || ch == '.') {
                String str = "";
                while((i < individualValues.length)
                        && (Character.isDigit(individualValues[i]) || individualValues[i] == '.')) {
                    str += individualValues[i++];
                }
                i--;
                numbers.push(Double.parseDouble(str));
            }
            else if(isOperator(ch)) {
                while(!operators.isEmpty() && (helper(ch) < helper(operators.peek()))) {
                    double output = calculate(numbers.pop(), numbers.pop(), operators.pop());
                    numbers.push(output);
                }
                operators.push(ch);
            }
        }
        while(!operators.isEmpty()) {
            double output = calculate(numbers.pop(), numbers.pop(), operators.pop());
            numbers.push(output);
        }
        return numbers.peek();
    }

    public int helper(char op) {
        if(op == '*' || op == '/') {
            return 2;
        }
        else if(op == '+' || op == '-') {
            return 1;
        }
        return -1;
    }

    public boolean isOperator(char op) {
        return (op == '+' || op == '-' || op == '*' || op == '/');
    }

    public Double calculate(Double a, Double b, Character op) {
        Double temp = 0.0;
        if(a == 0.0 && op == '/') {
            Toast.makeText(this, "Can't divide by zero", Toast.LENGTH_SHORT).show();
            return temp;
        }

        switch(op) {
            case '+':
                temp = a + b;
                break;
            case '-':
                temp = b - a;
                break;
            case '*':
                temp = a * b;
                break;
            case '/':
                temp = b / a;
        }

        DecimalFormat newFormat = new DecimalFormat("#.######");
        return Double.valueOf(newFormat.format(temp));
    }
}
