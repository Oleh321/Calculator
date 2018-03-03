package balychev.oleh.nk.ua.blch.calculator;

import android.util.Log;

public class Controller {
    // Ввод чисел
    private final int MAX_LENGTH = 12;
    private StringBuilder displayNumber;

    public Controller() {
        this.displayNumber = new StringBuilder("0");
        previousState = State.EQUAL;
        calculator = new Calculator();
    }

    public String getNumber() {
        return displayNumber.toString();
    }

    private void addChar(char digit){
        if((digit == ',' && displayNumber.toString().indexOf(',') != -1)
                || displayNumber.length() >= MAX_LENGTH){
             return;
        }
        if(displayNumber.toString().equals("0")){
            if(digit == ','){
                displayNumber.append(digit);
                return;
            }
            displayNumber = new StringBuilder(digit);
        }
        displayNumber.append(digit);
    }

    public void clear() {
        this.displayNumber = new StringBuilder("0");
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
            displayNumber = new StringBuilder();
            previousState = State.NUMBER;
        }
        addChar(c);
    }

    public void operation(char c){
        if(previousState != State.OPERATION){
            Log.d("myLogs", String.valueOf(parseNumber()));
            calculator.setSecondValue(parseNumber());
            calculator.setOperation(c);
            double res = calculator.calculate();
            Log.d("myLogs", String.valueOf(res));
            calculator.setFirstValue(res);
            previousState = State.OPERATION;
        }

        calculator.setOperation(c);
    }


}
