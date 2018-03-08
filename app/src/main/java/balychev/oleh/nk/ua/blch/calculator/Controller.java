package balychev.oleh.nk.ua.blch.calculator;

import android.util.Log;

public class Controller {
    private final int MAX_INPUT_LENGTH = 12;
    private StringBuilder displayNumber;

    public Controller() {
        this.displayNumber = new StringBuilder();
        previousState = State.EQUAL;
        calculator = new Calculator();
    }

    public String getNumber() {
        return displayNumber.toString();
    }

    private void addChar(char digit){
        if((digit == ',' && displayNumber.toString().indexOf(',') != -1)
                || displayNumber.length() >= MAX_INPUT_LENGTH){
             return;
        }
        if(displayNumber.toString().equals("0")){
            if(digit != ','){
                displayNumber = new StringBuilder(digit);
            }
        }
        displayNumber.append(digit);
    }

    public void clear() {
        this.displayNumber = new StringBuilder("0");
        calculator.reset();
    }

    public double parseNumber(){
        displayNumber = new StringBuilder(displayNumber.toString().replace(',','.'));
        if(displayNumber.toString().indexOf('.') == displayNumber.length()-1)
            displayNumber = displayNumber.deleteCharAt(displayNumber.length()-1);
        return Double.parseDouble(displayNumber.toString());
    }

    // Логика кнопок
    private State previousState;
    private Calculator calculator;

    public void number(char c){
        if(previousState != State.NUMBER) {
            displayNumber = new StringBuilder("0");
            if (previousState == State.EQUAL)
                calculator.reset();

        }
        previousState = State.NUMBER;
        addChar(c);
    }

    public void operation(char c){
        if(previousState != State.OPERATION){
            if(previousState == State.NUMBER){
                double res = calculator.calculate(parseNumber());
                displayNumber = new StringBuilder(String.valueOf(res));
                calculator.setValue(res);
            }
        }
        previousState = State.OPERATION;
        calculator.setOperation(c);
    }


    public void equally() {
        if(previousState == State.NUMBER){
            double res = calculator.calculate(parseNumber());
            displayNumber = new StringBuilder(String.valueOf(res));
            calculator.setValue(res);
            previousState = State.EQUAL;
        }
    }
}
