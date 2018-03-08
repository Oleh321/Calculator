package balychev.oleh.nk.ua.blch.calculator;

import android.util.Log;


public class Calculator {

    private double value;
    private char operation;


    public Calculator() {
        reset();
    }

    public double calculate(double next){
        double res = 0;
        switch(operation){
            case '+':
                res = value + next;
                break;
            case '-':
                res = value - next;
                break;
            case '*':
                res = value * next;
                break;
            case '/':
                res = value / next;
                break;
        }
        return res;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void reset() {
        value = 0;
        operation = '+';
    }
}
