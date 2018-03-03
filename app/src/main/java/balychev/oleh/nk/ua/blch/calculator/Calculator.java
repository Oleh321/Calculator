package balychev.oleh.nk.ua.blch.calculator;

import android.util.Log;


public class Calculator {

    private double firstValue = 0;
    private double secondValue = 0;
    private char operation = '+';

    public double calculate(){
        double res = 0;
        switch(operation){
            case '+':
                res = firstValue + secondValue;
                break;
            case '-':
                res = firstValue - secondValue;
                break;
            case '*':
                res = firstValue * secondValue;
                break;
            case '/':
                res = firstValue/secondValue;
                break;
        }
        return res;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }
}
