package balychev.oleh.nk.ua.blch.calculator;

public class Controller {
    private final int MAX_INPUT_LENGTH = 12;

    private StringBuilder displayNumber;
    private State previousState;
    private Calculator calculator;

    public Controller() {
        this.displayNumber = new StringBuilder("0");
        previousState = State.EQUALITY;
        calculator = new Calculator();
    }

    public String getNumber() {
        // Отображение во время набора чисел
        if (previousState == State.NUMBER)
            return displayNumber.toString();

        // Вывод целого числа
        double res = parseNumber();
        if(res == (int) res) {
            return String.format("%1.0f", res);
        }

        if (res > Math.pow(10, 10)){
            return String.format("%1.8e", res);
        }

        // Вывод дробных чисел с предотвращением погрешности
        String format = String.format("%1.12f", Double.parseDouble(displayNumber.toString()));
        format = Double.valueOf(format.replace(',', '.')).toString();
        return format;
    }

    private void addChar(char digit){
        //Условия, при котором число или запятая не будет введено
        if((digit == '.' && displayNumber.toString().indexOf('.') != -1)
                || displayNumber.length() >= MAX_INPUT_LENGTH){
             return;
        }
        if(displayNumber.toString().equals("0")){
            if(digit != '.'){
                displayNumber.delete(0, displayNumber.length());
            }
    }
        displayNumber.append(digit);
    }

    public void clear() {
        displayNumber.delete(0, displayNumber.length());
        displayNumber.append("0");
        calculator.reset();
    }

    private double parseNumber(){
       /* if(displayNumber.indexOf(".") == displayNumber.length()-1)
            displayNumber.deleteCharAt(displayNumber.length()-1);*/
       return Double.parseDouble(displayNumber.toString());
    }

    public void number(char c){
        if(previousState != State.NUMBER) {
            displayNumber = new StringBuilder("0");
            if (previousState == State.EQUALITY)
                calculator.reset();
        }
        previousState = State.NUMBER;
        addChar(c);
    }

    public void operation(char c){
        if(previousState == State.NUMBER){
            double res = calculator.calculate(parseNumber());
            displayNumber = new StringBuilder(String.valueOf(res));
            calculator.setValue(res);
        }
        previousState = State.OPERATION;
        calculator.setOperation(c);
    }

    public void equality() {
        if(previousState == State.NUMBER){
            double result = calculator.calculate(parseNumber());
            displayNumber = new StringBuilder(String.valueOf(result));
            calculator.setValue(result);
            previousState = State.EQUALITY;
        }
    }
}
